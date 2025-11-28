package com.nao7016.backpack.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class BPTabs extends CreativeTabs {

    public static final BPTabs bp_tab = new BPTabs();

    private BPTabs() {
        super("BackpacksTab");
    }

    @Override
    public Item getTabIconItem() {
        return BPItems.basicBackpack;
    }
}
