package com.nao7016.backpack.item;

import java.util.List;
import java.util.UUID;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.nao7016.backpack.BackpackMain;
import com.nao7016.backpack.core.BPTabs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBackpack extends Item {

    private final int tier;
    private final int size;
    private final int cols;
    private final String name;

    private IIcon[] tierIcons;

    public ItemBackpack(int tier, int size, int cols, String name) {
        setCreativeTab(BPTabs.bp_tab);
        setUnlocalizedName("simplebackpacks." + name);
        setTextureName("simplebackpacks:" + name);
        setMaxStackSize(1);
        setNoRepair();

        this.tier = tier;
        this.size = size;
        this.cols = cols;
        this.name = name;
    }

    public ItemBackpack(BackpackTypes type) {
        this(type.getTier(), type.getSize(), type.getCols(), type.getName());
    }

    @Override
    public ItemStack onItemRightClick(ItemStack backpack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            createUUID(backpack);
            player.openGui(
                BackpackMain.instance,
                tier - 1,
                world,
                (int) player.posX,
                (int) player.posY,
                (int) player.posZ);
            return backpack;
        } else {
            return backpack;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack backpack, EntityPlayer player, List<String> list, boolean advanced) {
        if (backpack.hasTagCompound() && backpack.stackTagCompound.hasKey("BackpackUUID") && advanced) {
            list.add("UUID: " + getUUID(backpack));
        }
    }

    /**
     * Get or create a UUID for the given backpack ItemStack.
     *
     * @param backpack
     * @return The UUID string associated with the backpack.
     */
    public static void createUUID(ItemStack backpack) {
        if (backpack.stackTagCompound == null) {
            backpack.setTagCompound(new NBTTagCompound());
        }

        if (!backpack.stackTagCompound.hasKey("BackpackUUID")) {
            String newUUID = UUID.randomUUID()
                .toString();
            backpack.stackTagCompound.setString("BackpackUUID", newUUID);
        }
    }

    public static String getUUID(ItemStack backpack) {
        if (backpack.hasTagCompound() && backpack.getTagCompound()
            .hasKey("BackpackUUID")) {
            return backpack.getTagCompound()
                .getString("BackpackUUID");
        }
        return null;
    }

    public int getTier() {
        return tier;
    }

    public int getSize() {
        return size;
    }

    public int getCols() {
        return cols;
    }

    public String getName() {
        return name;
    }

}
