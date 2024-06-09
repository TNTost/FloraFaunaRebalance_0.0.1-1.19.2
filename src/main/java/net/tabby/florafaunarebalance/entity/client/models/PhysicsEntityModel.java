package net.tabby.florafaunarebalance.entity.client.models;// Made with Blockbench 4.10.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class PhysicsEntityModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart sling;
	private final ModelPart rock;

	public PhysicsEntityModel(ModelPart root) {
		this.sling = root.getChild("sling");
		this.rock = sling.getChild("rock");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition sling = partdefinition.addOrReplaceChild("sling", CubeListBuilder.create().texOffs(9, 0).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition back_r1 = sling.addOrReplaceChild("back_r1", CubeListBuilder.create().texOffs(8, 11).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5F, -0.75F, 0.2182F, 0.0F, 0.0F));

		PartDefinition right_r1 = sling.addOrReplaceChild("right_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -1.0F, -1.5F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.1F, -1.5F, 0.25F, -0.4943F, -0.2316F, 0.1231F));

		PartDefinition left_r1 = sling.addOrReplaceChild("left_r1", CubeListBuilder.create().texOffs(0, 6).addBox(-0.5F, -1.0F, -1.5F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1F, -1.5F, 0.25F, -0.4943F, 0.2316F, -0.1231F));

		PartDefinition rock = sling.addOrReplaceChild("rock", CubeListBuilder.create().texOffs(7, 4).addBox(-1.0F, -2.15F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(9, 8).addBox(-1.0F, -1.15F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		sling.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return sling;
	}
}