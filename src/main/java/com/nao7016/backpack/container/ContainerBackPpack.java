package com.nao7016.backpack.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ContainerBackPpack extends Container {

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return false;
    }
}
