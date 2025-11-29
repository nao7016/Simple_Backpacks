package com.nao7016.backpack.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryBackpack implements IInventory {

    private final ItemStack[] items;

    public InventoryBackpack(ItemStack[] items) {
        this.items = items;
    }

    @Override
    public int getSizeInventory() {
        return items.length;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return items[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (items[index] != null) {
            ItemStack stack;

            if (items[index].stackSize <= count) {
                stack = items[index];
                items[index] = null;
            } else {
                stack = items[index].splitStack(count);
                if (items[index].stackSize == 0) items[index] = null;
            }

            return stack;
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        items[index] = stack;
    }

    @Override
    public String getInventoryName() {
        return "Backpack";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void markDirty() {}

    @Override
    public boolean isUseableByPlayer(net.minecraft.entity.player.EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }
}
