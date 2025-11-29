package com.nao7016.backpack.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import com.nao7016.backpack.container.ContainerBackpack;
import com.nao7016.backpack.item.BackpackData;

public class GuiBackpack extends GuiContainer {

    private static final ResourceLocation texture_7x9 = new ResourceLocation(
        "simplebackpacks:textures/gui/7x9_backpack.png");
    private static final ResourceLocation texture_7x11 = new ResourceLocation(
        "simplebackpacks:textures/gui/7x11_backpack.png");

    private final EntityPlayer player;
    private final String uuid;

    private final int rows;
    private final int cols;

    public GuiBackpack(EntityPlayer player, String uuid) {
        super(new ContainerBackpack(player, uuid));
        this.player = player;
        this.uuid = uuid;

        BackpackData data = BackpackData.get(player.worldObj);
        int size = BackpackData.getSizeForTier(data.getInfo(uuid).tier);
        this.cols = BackpackData.getColsForTier(data.getInfo(uuid).tier);

        this.rows = size / cols;

        if (this.cols == 9) {
            this.xSize = 176;
        } else {
            this.xSize = 212;
        }

        this.ySize = 17 + this.rows * 18 + 97;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fontRendererObj.drawString("Backpack", 8, 6, 4210752);
        this.fontRendererObj.drawString("Inventory", 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        if (this.cols == 9) {
            this.mc.getTextureManager()
                .bindTexture(texture_7x9);

            int x = (this.width - this.xSize) / 2;
            int y = (this.height - this.ySize) / 2;

            // backpack background
            drawTexturedModalRect(x, y, 0, 0, this.xSize, this.rows * 18 + 17);

            // player inventory background
            drawTexturedModalRect(x, y + this.rows * 18 + 17, 0, 143, this.xSize, 97);
        } else {
            this.mc.getTextureManager()
                .bindTexture(texture_7x11);

            int x = (this.width - this.xSize) / 2;
            int y = (this.height - this.ySize) / 2;

            drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);

        }
    }
}
