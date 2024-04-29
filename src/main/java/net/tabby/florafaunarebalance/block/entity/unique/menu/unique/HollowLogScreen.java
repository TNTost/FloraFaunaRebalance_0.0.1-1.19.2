package net.tabby.florafaunarebalance.block.entity.unique.menu.unique;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.tabby.florafaunarebalance.FloraFaunaRebalance;

public class HollowLogScreen extends AbstractContainerScreen<HollowLogMenu> {
    private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(FloraFaunaRebalance.MOD_ID, "textures/gui/container/hollow_log_gui.png");
    public HollowLogScreen(HollowLogMenu p_97741_, Inventory p_97742_, Component p_97743_) {
        super(p_97741_, p_97742_, p_97743_);
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 10000; //# gone!
        this.titleLabelY = 10000;
    }

    @Override
    protected void renderBg(PoseStack poseStack, float p_97788_, int p_97789_, int p_97790_) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(poseStack, x, y, 0, 0, imageWidth, imageHeight);
    }
    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float p_97798_) {
        renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, p_97798_);
        renderTooltip(poseStack, mouseX, mouseY);
    }
}
