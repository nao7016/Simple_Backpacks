package com.nao7016.backpack.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.nao7016.backpack.item.ItemBackpack;

public class SlotBackpack extends Slot {

    public SlotBackpack(IInventory inventory, int index, int xPosition, int yPosition) {
        super(inventory, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return !(stack.getItem() instanceof ItemBackpack);
    }
}
