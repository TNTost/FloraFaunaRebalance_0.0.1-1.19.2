package net.tabby.florafaunarebalance.block.fluid.core;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.math.Vector3f;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class FluidTemplate extends FluidType {
    private final ResourceLocation stillTexture;
    private final ResourceLocation flowingTexture;
    private final ResourceLocation overlayTexture;
    private final int tintColour;
    private final Vector3f fogColour;


    public FluidTemplate(final ResourceLocation stillTexture, final ResourceLocation flowingTexture, final ResourceLocation overlayTexture, final int tintColour, final Vector3f fogColour, final Properties properties) {
        super(properties);
        this.stillTexture = stillTexture;
        this.flowingTexture = flowingTexture;
        this.overlayTexture = overlayTexture;
        this.tintColour = tintColour;
        this.fogColour = fogColour;
    }



    public ResourceLocation getStillTexture() {
        return this.stillTexture;
    }
    public ResourceLocation getFlowingTexture() {
        return this.flowingTexture;
    }
    public ResourceLocation getOverlayTexture() {
        return this.overlayTexture;
    }
    public int getTintColour() {
        return this.tintColour;
    }
    public Vector3f getFogColour() {
        return this.fogColour;
    }

    @Override
    public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
        consumer.accept(new IClientFluidTypeExtensions() {
            @Override
            public ResourceLocation getStillTexture() {
                return stillTexture;
            }
            @Override
            public ResourceLocation getFlowingTexture() {
                return flowingTexture;
            }
            @Override
            public ResourceLocation getOverlayTexture() {
                return overlayTexture;
            }
            @Override
            public int getTintColor() {
                return tintColour;
            }
            @Override
            public @NotNull Vector3f modifyFogColor(Camera cam, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColour) {
                return fogColour;
            }

            @Override
            public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape) {
                RenderSystem.setShaderFogStart(3f);
                RenderSystem.setShaderFogEnd(11f);
            }
        });
    }
}
