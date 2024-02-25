package net.tabby.florafaunarebalance.entity.client.models;// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class DuckModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart duck;
	private final ModelPart head;

	public DuckModel(ModelPart root) {
		this.duck = root.getChild("duck");
		this.head = duck.getChild("spine").getChild("neck");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition duck = partdefinition.addOrReplaceChild("duck", CubeListBuilder.create().texOffs(43, 16).addBox(-2.0F, -3.5F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 1.0F));

		PartDefinition bun_r1 = duck.addOrReplaceChild("bun_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -1.0F, -5.0F, 7.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, 0.0F, 0.0F, 0.0F, 0.0F));

		PartDefinition wings = duck.addOrReplaceChild("wings", CubeListBuilder.create().texOffs(36, 1).addBox(-2.5F, -9.0F, -2.0F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition L_wing = wings.addOrReplaceChild("L_wing", CubeListBuilder.create().texOffs(49, 32).addBox(-1.25F, -0.5F, -3.5F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(18, 35).addBox(-4.0F, 0.0F, -4.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -8.75F, 1.0F, 0.0F, 0.0F, 0.0F));

		PartDefinition left_shoulder_tendon = L_wing.addOrReplaceChild("left_shoulder_tendon", CubeListBuilder.create().texOffs(0, 27).addBox(-0.25F, -0.5F, -2.75F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 23).addBox(-1.25F, -0.5F, -3.75F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.25F, 0.25F, 0.0F));

		PartDefinition left_tendon_fold = left_shoulder_tendon.addOrReplaceChild("left_tendon_fold", CubeListBuilder.create().texOffs(0, 46).addBox(-0.5F, -0.75F, 0.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.25F, 0.25F, -2.75F));

		PartDefinition left_elbow_joint = L_wing.addOrReplaceChild("left_elbow_joint", CubeListBuilder.create().texOffs(45, 26).addBox(0.25F, -1.0F, -0.5F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(41, 32).addBox(0.25F, 0.0F, -0.5F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(11, 21).addBox(0.45F, 0.5F, -2.0F, 0.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 0.5F, -3.0F));

		PartDefinition left_carpal_joint = left_elbow_joint.addOrReplaceChild("left_carpal_joint", CubeListBuilder.create().texOffs(34, 20).addBox(-0.5F, 1.25F, -7.25F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(15, 14).addBox(-0.5F, -2.75F, -9.25F, 1.0F, 4.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(11, 23).addBox(-0.5F, -0.5F, -0.25F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 2.0F, 3.75F, 0.0F, 0.0F, 0.0F));

		PartDefinition left_digits = left_carpal_joint.addOrReplaceChild("left_digits", CubeListBuilder.create().texOffs(31, 28).addBox(7.0F, -11.0F, -6.0F, 1.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.5F, 7.25F, -4.75F));

		PartDefinition left_alula = left_elbow_joint.addOrReplaceChild("left_alula", CubeListBuilder.create().texOffs(26, 20).addBox(-1.0F, -0.25F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.25F, 0.25F, 5.0F));

		PartDefinition R_wing = wings.addOrReplaceChild("R_wing", CubeListBuilder.create().texOffs(34, 49).addBox(-0.75F, -0.5F, -3.5F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(34, 16).addBox(0.0F, 0.0F, -4.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -8.75F, 1.0F, 0.0F, 0.0F, 0.0F));

		PartDefinition right_shoulder_tendon = R_wing.addOrReplaceChild("right_shoulder_tendon", CubeListBuilder.create().texOffs(0, 52).addBox(-0.75F, -0.5F, -2.75F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(48, 12).addBox(0.25F, -0.5F, -3.75F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.25F, 0.25F, 0.0F));

		PartDefinition right_tendon_fold = right_shoulder_tendon.addOrReplaceChild("right_tendon_fold", CubeListBuilder.create().texOffs(46, 46).addBox(-0.5F, -0.75F, 0.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.25F, 0.25F, -2.75F));

		PartDefinition right_elbow_joint = R_wing.addOrReplaceChild("right_elbow_joint", CubeListBuilder.create().texOffs(45, 40).addBox(-1.25F, -1.0F, -0.5F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(37, 41).addBox(-1.25F, 0.0F, -0.5F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(26, 10).addBox(-0.45F, 0.5F, -2.0F, 0.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 0.5F, -3.0F));

		PartDefinition right_carpal_joint = right_elbow_joint.addOrReplaceChild("right_carpal_joint", CubeListBuilder.create().texOffs(0, 38).addBox(-0.5F, 1.25F, -7.25F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 23).addBox(-0.5F, -2.75F, -9.25F, 1.0F, 4.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(5, 23).addBox(-0.5F, -0.5F, -0.25F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 2.0F, 3.75F, 0.0F, 0.0F, 0.0F));

		PartDefinition right_digits = right_carpal_joint.addOrReplaceChild("right_digits", CubeListBuilder.create().texOffs(13, 35).addBox(-3.0F, -10.0F, -9.0F, 1.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, 6.25F, -1.75F));

		PartDefinition right_alula = right_elbow_joint.addOrReplaceChild("right_alula", CubeListBuilder.create().texOffs(22, 0).addBox(0.0F, -0.25F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.25F, 0.25F, 5.0F));

		PartDefinition spine = duck.addOrReplaceChild("spine", CubeListBuilder.create(), PartPose.offset(0.0F, -5.0F, 1.5F));

		PartDefinition fluff = spine.addOrReplaceChild("fluff", CubeListBuilder.create().texOffs(0, 13).addBox(-3.0F, -4.0F, -3.5F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(20, 27).addBox(-2.5F, -4.9999F, -3.0F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(22, 0).addBox(-2.5F, -0.25F, -3.0F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 2.5F));

		PartDefinition neck = spine.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(14, 45).addBox(-1.5F, -8.0F, -1.5F, 3.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(24, 47).addBox(-1.0F, -7.0F, -2.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 2.0F));

		PartDefinition skull = neck.addOrReplaceChild("skull", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -4.15F, 1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(9, 36).addBox(-1.5F, -3.5F, 2.5F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.5F, -2.0F));

		PartDefinition beak = skull.addOrReplaceChild("beak", CubeListBuilder.create().texOffs(0, 6).addBox(-1.0F, -0.5F, 5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(43, 21).addBox(-1.5F, 0.0F, 4.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(22, 5).addBox(-1.0F, -1.0F, 3.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.5F));

		PartDefinition jaw = beak.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(50, 0).addBox(-1.0F, 0.25F, -1.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.75F, 4.0F));

		PartDefinition tail = duck.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(26, 6).addBox(-2.0F, -3.0F, -8.0F, 4.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(41, 6).addBox(-2.5F, 0.0F, -7.0F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, -1.0F));

		PartDefinition tip = tail.addOrReplaceChild("tip", CubeListBuilder.create().texOffs(29, 38).addBox(-1.5F, -4.0F, -5.0F, 3.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -4.0F));

		PartDefinition paws = duck.addOrReplaceChild("paws", CubeListBuilder.create(), PartPose.offset(0.0F, -4.0F, -2.5F));

		PartDefinition right_paw = paws.addOrReplaceChild("right_paw", CubeListBuilder.create().texOffs(35, 20).addBox(-0.5F, 0.5F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(15, 13).addBox(-1.0F, 4.5F, -0.25F, 2.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.75F, -1.0F, 0.0F));

		PartDefinition left_paw = paws.addOrReplaceChild("left_paw", CubeListBuilder.create().texOffs(0, 13).addBox(-0.5F, 0.5F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(15, 16).addBox(-1.0F, 4.5F, -0.25F, 2.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.75F, -1.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		//# TODO: add animation of [idle, swim, dive, takeoff, fly, flap in flight, landing water, landing land, intimidate, wash/oil glands, waddle/walk, fold wings]...
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		duck.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
	@Override
	public ModelPart root() {
		return duck;
	}
}