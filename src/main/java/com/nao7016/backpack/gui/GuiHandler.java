package com.nao7016.backpack.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.nao7016.backpack.container.ContainerBackpack;
import com.nao7016.backpack.item.ItemBackpack;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if (id == 0) {
            ItemStack backpack = player.getHeldItem();

            if (backpack != null && backpack.getItem() instanceof ItemBackpack) {
                String uuid = ItemBackpack.getUUID(backpack);
                return new ContainerBackpack(player, uuid);
            }
        }
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if (id == 0) {
            ItemStack backpack = player.getHeldItem();

            if (backpack != null && backpack.getItem() instanceof ItemBackpack) {
                String uuid = ItemBackpack.getUUID(backpack);
                return new GuiBackpack(player, uuid);
            }
        }
        return null;
    }
}
