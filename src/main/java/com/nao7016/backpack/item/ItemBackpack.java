package com.nao7016.backpack.item;

import java.util.List;
import java.util.UUID;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.nao7016.backpack.BackpackMain;
import com.nao7016.backpack.core.BPTabs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBackpack extends Item {

    public ItemBackpack() {
        setCreativeTab(BPTabs.bp_tab);
        setMaxStackSize(1);
        setNoRepair();
    }

    @Override
    public ItemStack onItemRightClick(ItemStack backpack, World world, EntityPlayer player) {
        getOrCreateUUID(backpack);

        if (!world.isRemote) {
            player.openGui(BackpackMain.instance, 0, world, 0, 0, 0);
        }
        return backpack;
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon("simplebackpacks:basic_backpack");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack backpack, EntityPlayer player, List<String> list, boolean advanced) {
        if (backpack.hasTagCompound() && backpack.getTagCompound()
            .hasKey("BackpackUUID")) {
            list.add(
                "UUID: " + backpack.getTagCompound()
                    .getString("BackpackUUID"));
        }
    }

    /**
     * Get or create a UUID for the given backpack ItemStack.
     * 
     * @param backpack
     * @return The UUID string associated with the backpack.
     */
    public static String getOrCreateUUID(ItemStack backpack) {
        if (!backpack.hasTagCompound()) {
            backpack.setTagCompound(new NBTTagCompound());
        }

        NBTTagCompound nbt = backpack.getTagCompound();

        if (!nbt.hasKey("BackpackUUID")) {
            String uuid = UUID.randomUUID()
                .toString();
            nbt.setString("BackpackUUID", uuid);
            return uuid;
        }
        return nbt.getString("BackpackUUID");
    }
}
