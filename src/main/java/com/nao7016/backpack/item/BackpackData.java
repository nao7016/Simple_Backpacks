package com.nao7016.backpack.item;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSavedData;

public class BackpackData extends WorldSavedData {

    public BackpackData(String name) {
        super(name);
    }

    /**
     * reads in data from the NBTTagCompound into this MapDataBase
     *
     * @param p_76184_1_
     */
    @Override
    public void readFromNBT(NBTTagCompound p_76184_1_) {

    }

    /**
     * write data to NBTTagCompound from this MapDataBase, similar to Entities and TileEntities
     *
     * @param p_76187_1_
     */
    @Override
    public void writeToNBT(NBTTagCompound p_76187_1_) {

    }
}
