package com.nao7016.backpack.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.nao7016.backpack.item.BackpackData;

public class ContainerBackpack extends Container {

    private EntityPlayer player;
    private String uuid;

    private final BackpackData.BackpackInfo info;

    private final int rows;

    public ContainerBackpack(EntityPlayer player, String uuid) {
        this.player = player;
        this.uuid = uuid;

        BackpackData data = BackpackData.get(player.worldObj);
        this.info = data.getInfo(uuid);

        int size = BackpackData.getSizeForTier(info.tier);
        this.rows = size / 9;

        int offsetY = 18;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < 9; col++) {
                int index = col + row * 9;
                this.addSlotToContainer(
                    new Slot(new InventoryBackpack(info.items), index, 8 + col * 18, offsetY + row * 18));
            }
        }

        int playerInvY = offsetY + rows * 18 + 14;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                int index = col + row * 9 + 9;
                this.addSlotToContainer(new Slot(player.inventory, index, 8 + col * 18, playerInvY + row * 18));
            }
        }

        for (int col = 0; col < 9; col++) {
            this.addSlotToContainer(new Slot(player.inventory, col, 8 + col * 18, playerInvY + 58));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }

    @Override
    public void onContainerClosed(EntityPlayer player) {
        super.onContainerClosed(player);

        BackpackData data = BackpackData.get(player.worldObj);
        data.setInfo(uuid, info);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack itemstack = null;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack stack = slot.getStack();
            itemstack = stack.copy();

            int backpackSize = this.rows * 9;

            if (index < backpackSize) {
                if (!this.mergeItemStack(stack, backpackSize, this.inventorySlots.size(), true)) {
                    return null;
                }
            } else if (!this.mergeItemStack(stack, 0, backpackSize, false)) {
                return null;
            }

            if (stack.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }
}
