package com.nao7016.backpack.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import com.nao7016.backpack.container.ContainerBackpack;
import com.nao7016.backpack.item.BackpackData;

public class GuiBackpack extends GuiContainer {

    private static final ResourceLocation texture = new ResourceLocation("simplebackpacks:textures/gui/backpack.png");

    private final EntityPlayer player;
    private final String uuid;

    private final int rows;

    public GuiBackpack(EntityPlayer player, String uuid) {
        super(new ContainerBackpack(player, uuid));
        this.player = player;
        this.uuid = uuid;

        BackpackData data = BackpackData.get(player.worldObj);
        int size = BackpackData.getSizeForTier(data.getInfo(uuid).tier);

        this.rows = size / 9;

        this.ySize = 17 + this.rows * 18 + 97;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fontRendererObj.drawString("Backpack", 8, 6, 4210752);
        this.fontRendererObj.drawString("Inventory", 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager()
            .bindTexture(texture);

        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;

        // backpack background
        drawTexturedModalRect(x, y, 0, 0, this.xSize, this.rows * 18 + 17);

        // player inventory background
        // drawTexturedModalRect(x, y + this.rows * 18 + 17, 0, 179, this.xSize, 96);
    }
}
