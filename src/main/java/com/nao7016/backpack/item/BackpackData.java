package com.nao7016.backpack.item;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;

public class BackpackData extends WorldSavedData {

    public static final String DataName = "backpack_data";

    private Map<String, BackpackInfo> backpackMap = new HashMap<>();

    public BackpackData() {
        super(DataName);
    }

    public BackpackData(String name) {
        super(name);
    }

    public static BackpackData get(World world) {
        MapStorage storage = world.perWorldStorage;
        BackpackData data = (BackpackData) storage.loadData(BackpackData.class, DataName);

        if (data == null) {
            data = new BackpackData();
            storage.setData(DataName, data);
        }
        return data;
    }

    public BackpackInfo getInfo(String uuid) {
        return backpackMap.computeIfAbsent(uuid, k -> {
            BackpackInfo info = new BackpackInfo();
            info.tier = 1;
            info.items = new ItemStack[getSizeForTier(info.tier)];
            return info;
        });
    }

    public void setInfo(String uuid, BackpackInfo info) {
        backpackMap.put(uuid, info);
        markDirty();
    }

    /**
     * reads in data from the NBTTagCompound into this MapDataBase
     *
     * @param nbt
     */
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        NBTTagList list = nbt.getTagList("Backpacks", 10);

        for (int i = 0; i < list.tagCount(); i++) {
            NBTTagCompound BP = list.getCompoundTagAt(i);
            String uuid = BP.getString("UUID");

            BackpackInfo info = new BackpackInfo();
            info.tier = BP.getInteger("Tier");

            int size = getSizeForTier(info.tier);
            info.items = new ItemStack[size];

            NBTTagList items = BP.getTagList("Items", 10);

            for (int j = 0; j < items.tagCount(); j++) {
                NBTTagCompound itemTag = items.getCompoundTagAt(j);
                int slot = itemTag.getByte("Slot");
                info.items[slot] = ItemStack.loadItemStackFromNBT(itemTag);
            }
            backpackMap.put(uuid, info);
        }
    }

    /**
     * write data to NBTTagCompound from this MapDataBase, similar to Entities and TileEntities
     *
     * @param nbt
     */
    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        NBTTagList list = new NBTTagList();

        for (String uuid : backpackMap.keySet()) {
            BackpackInfo info = backpackMap.get(uuid);

            NBTTagCompound BP = new NBTTagCompound();
            BP.setString("UUID", uuid);
            BP.setInteger("Tier", info.tier);

            NBTTagList items = new NBTTagList();

            for (int i = 0; i < info.items.length; i++) {
                if (info.items[i] != null) {
                    NBTTagCompound itemTag = new NBTTagCompound();
                    itemTag.setByte("Slot", (byte) i);
                    info.items[i].writeToNBT(itemTag);
                    items.appendTag(itemTag);
                }
            }
            BP.setTag("Items", items);
            list.appendTag(BP);
        }
        nbt.setTag("Backpacks", list);
    }

    public static int getSizeForTier(int tier) {
        switch (tier) {
            case 1:
                return 27;
            case 2:
                return 45;
            case 3:
                return 63;
            case 4:
                return 77;
            default:
                return 27;
        }
    }

    public static int getColsForTier(int tier) {
        switch (tier) {
            case 1, 2, 3:
                return 9;
            case 4:
                return 11;
            default:
                return 9;
        }
    }

    public void upgradeTier(String uuid) {
        BackpackInfo info = getInfo(uuid);
        if (info.tier < 4) {
            int oldSize = BackpackData.getSizeForTier(info.tier);
            int newSize = BackpackData.getSizeForTier(info.tier + 1);

            ItemStack[] newItems = new ItemStack[newSize];

            for (int i = 0; i < oldSize; i++) {
                newItems[i] = info.items[i];
            }

            info.items = newItems;
            info.tier += 1;

            markDirty();
        }
    }

    public static class BackpackInfo {

        public int tier;
        public ItemStack[] items;
    }
}
