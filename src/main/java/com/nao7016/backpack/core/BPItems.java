package com.nao7016.backpack.core;

import net.minecraft.item.Item;

import com.nao7016.backpack.item.ItemBackpack;

import cpw.mods.fml.common.registry.GameRegistry;

public class BPItems {

    public static Item basicBackpack;

    private static Item register(Item item, String name) {
        GameRegistry.registerItem(item, name);
        return item;
    }

    public static void registerItems() {
        basicBackpack = register(new ItemBackpack(), "basic_backpack");
    }
}
