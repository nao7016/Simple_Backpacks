package com.nao7016.backpack.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

import com.nao7016.backpack.core.BPTabs;

public class ItemBackpack extends Item {

    public ItemBackpack() {
        setUnlocalizedName("basic_backpack");
        setCreativeTab(BPTabs.bp_tab);
        setMaxStackSize(1);
        setNoRepair();
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon("simplebackpacks:basic_backpack");
    }

}
