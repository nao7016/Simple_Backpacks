package com.nao7016.backpack.core;

import net.minecraft.item.Item;

import com.nao7016.backpack.item.BackpackTypes;
import com.nao7016.backpack.item.ItemBackpack;

import cpw.mods.fml.common.registry.GameRegistry;

import java.util.ArrayList;

public class BPItems {

    public static Item basicBackpack;
    public static Item ironBackpack;
    public static Item goldBackpack;
    public static Item diamondBackpack;

    public static void registerItems() {
        basicBackpack = register(new ItemBackpack(BackpackTypes.basic), BackpackTypes.basic.getName());
        ironBackpack = register(new ItemBackpack(BackpackTypes.iron), BackpackTypes.iron.getName());
        goldBackpack = register(new ItemBackpack(BackpackTypes.gold), BackpackTypes.gold.getName());
        diamondBackpack = register(new ItemBackpack(BackpackTypes.diamond), BackpackTypes.diamond.getName());
    }

    public static ArrayList<Item> getAllBackpacks() {
        ArrayList<Item> backpacks = new ArrayList<Item>();
        backpacks.add(basicBackpack);
        backpacks.add(ironBackpack);
        backpacks.add(goldBackpack);
        backpacks.add(diamondBackpack);
        return backpacks;
    }

    private static Item register(Item item, String name) {
        GameRegistry.registerItem(item, name);
        return item;
    }
}
