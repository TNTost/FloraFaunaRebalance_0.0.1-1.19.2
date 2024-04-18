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

public class SkeeterModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart Skeeter;


	public SkeeterModel(ModelPart root) {
		this.Skeeter = root.getChild("Skeeter");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Skeeter = partdefinition.addOrReplaceChild("Skeeter", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -10.0F, -6.0F, 6.0F, 3.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(0, 19).addBox(-2.0F, -10.75F, -4.0F, 4.0F, 5.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(18, 40).addBox(-1.0F, -7.25F, -2.25F, 2.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition Head = Skeeter.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(54, 53).addBox(-1.5F, -8.75F, -7.0F, 3.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(54, 8).addBox(-2.5F, -8.25F, -8.0F, 5.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(19, 19).addBox(-2.0F, -9.75F, -11.5F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(62, 44).addBox(-1.5F, -10.25F, -10.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -2.0F));

		PartDefinition Antennae = Head.addOrReplaceChild("Antennae", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition R = Antennae.addOrReplaceChild("R", CubeListBuilder.create().texOffs(54, 16).addBox(-0.25F, -0.75F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -9.75F, -10.0F, -0.3054F, -0.3927F, 0.0F));

		PartDefinition r_tip = R.addOrReplaceChild("r_tip", CubeListBuilder.create().texOffs(57, 63).addBox(-0.5F, -0.25F, -4.0F, 1.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.0F, -2.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition r_tippier = r_tip.addOrReplaceChild("r_tippier", CubeListBuilder.create().texOffs(28, 12).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.25F, -4.0F, 0.829F, 0.0F, 0.0F));

		PartDefinition L = Antennae.addOrReplaceChild("L", CubeListBuilder.create().texOffs(17, 45).addBox(-0.75F, -0.75F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -9.75F, -10.0F, -0.3491F, 0.3927F, 0.0F));

		PartDefinition l_tip = L.addOrReplaceChild("l_tip", CubeListBuilder.create().texOffs(47, 53).addBox(-0.5F, -0.25F, -4.0F, 1.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, -2.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition l_tippier = l_tip.addOrReplaceChild("l_tippier", CubeListBuilder.create().texOffs(35, 12).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.25F, -4.0F, 0.6545F, 0.0F, 0.0F));

		PartDefinition right_front = Head.addOrReplaceChild("right_front", CubeListBuilder.create().texOffs(30, 67).addBox(-3.25F, -7.0F, -3.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -1.0F));

		PartDefinition joint_rf = right_front.addOrReplaceChild("joint_rf", CubeListBuilder.create().texOffs(68, 35).addBox(-3.75F, -6.5F, -4.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition upper_rf = joint_rf.addOrReplaceChild("upper_rf", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition FrontRFemur_r1 = upper_rf.addOrReplaceChild("FrontRFemur_r1", CubeListBuilder.create().texOffs(44, 62).addBox(-0.5F, -0.75F, -1.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, -4.25F, -7.75F, 0.3927F, 0.0F, 0.0F));

		PartDefinition lower_rf = upper_rf.addOrReplaceChild("lower_rf", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition FrontRTibia_r1 = lower_rf.addOrReplaceChild("FrontRTibia_r1", CubeListBuilder.create().texOffs(28, 0).addBox(-0.5F, -2.5F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, -1.75F, -8.5F, 0.1309F, 0.0F, 0.0F));

		PartDefinition rf_pad = lower_rf.addOrReplaceChild("rf_pad", CubeListBuilder.create().texOffs(59, 17).addBox(-6.0F, -1.0F, -12.0F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_front = Head.addOrReplaceChild("left_front", CubeListBuilder.create().texOffs(66, 52).addBox(1.5F, -7.0F, -3.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -1.0F));

		PartDefinition joint_lf = left_front.addOrReplaceChild("joint_lf", CubeListBuilder.create().texOffs(55, 68).addBox(2.75F, -6.5F, -4.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition upper_lf = joint_lf.addOrReplaceChild("upper_lf", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition FrontLFemur_r1 = upper_lf.addOrReplaceChild("FrontLFemur_r1", CubeListBuilder.create().texOffs(28, 52).addBox(-0.5F, -0.75F, -1.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, -4.25F, -7.75F, 0.3927F, 0.0F, 0.0F));

		PartDefinition lower_lf = upper_lf.addOrReplaceChild("lower_lf", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition FrontLTibia_r1 = lower_lf.addOrReplaceChild("FrontLTibia_r1", CubeListBuilder.create().texOffs(0, 45).addBox(-0.5F, -2.5F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, -1.75F, -8.5F, 0.1309F, 0.0F, 0.0F));

		PartDefinition lf_pad = lower_lf.addOrReplaceChild("lf_pad", CubeListBuilder.create().texOffs(0, 56).addBox(2.0F, -1.0F, -12.0F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Thorax = Skeeter.addOrReplaceChild("Thorax", CubeListBuilder.create().texOffs(49, 30).addBox(-2.5F, -8.5F, 7.0F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(22, 28).addBox(-3.0F, -8.5F, 11.0F, 6.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(28, 0).addBox(-4.0F, -9.0F, 11.5F, 8.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(41, 40).addBox(-2.5F, -10.0F, 12.0F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -1.0F));

		PartDefinition Bulb = Thorax.addOrReplaceChild("Bulb", CubeListBuilder.create().texOffs(15, 61).addBox(-2.0F, -8.0F, 18.5F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(57, 0).addBox(-3.0F, -8.5F, 19.0F, 6.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.0F, -9.5F, 19.5F, 4.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -0.5F));

		PartDefinition Ridges = Bulb.addOrReplaceChild("Ridges", CubeListBuilder.create().texOffs(0, 9).addBox(-1.5F, -7.5F, 21.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 63).addBox(-2.0F, -8.0F, 21.75F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(65, 24).addBox(-1.5F, -8.5F, 22.25F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 1.0F));

		PartDefinition Tip = Ridges.addOrReplaceChild("Tip", CubeListBuilder.create().texOffs(14, 54).addBox(-0.5F, -7.0F, 22.75F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(52, 63).addBox(-1.0F, -7.5F, 24.25F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition ExtentionAntenna = Tip.addOrReplaceChild("ExtentionAntenna", CubeListBuilder.create().texOffs(0, 9).addBox(0.0F, -7.0F, 25.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(19, 19).addBox(-0.5F, -7.0F, 26.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Appendage = Skeeter.addOrReplaceChild("Appendage", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_hind = Appendage.addOrReplaceChild("right_hind", CubeListBuilder.create().texOffs(42, 30).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -7.0F, 6.0F));

		PartDefinition joint_rh = right_hind.addOrReplaceChild("joint_rh", CubeListBuilder.create().texOffs(51, 0).addBox(-0.75F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 0.0F, 0.0F));

		PartDefinition upper_rh = joint_rh.addOrReplaceChild("upper_rh", CubeListBuilder.create(), PartPose.offset(-0.5F, 0.5F, 0.75F));

		PartDefinition HindRFemur_r1 = upper_rh.addOrReplaceChild("HindRFemur_r1", CubeListBuilder.create().texOffs(0, 19).addBox(-2.0F, -3.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 2.5F, 1.25F, 0.48F, 0.0F, 0.0F));

		PartDefinition lower_rh = upper_rh.addOrReplaceChild("lower_rh", CubeListBuilder.create().texOffs(35, 53).addBox(-8.0F, -2.0F, 8.0F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(5.5F, 6.5F, -6.75F));

		PartDefinition rh_pad = lower_rh.addOrReplaceChild("rh_pad", CubeListBuilder.create().texOffs(35, 12).addBox(-10.0F, -1.0F, 10.0F, 5.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_hind = Appendage.addOrReplaceChild("left_hind", CubeListBuilder.create().texOffs(68, 30).addBox(0.0F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -7.0F, 6.0F));

		PartDefinition joint_lh = left_hind.addOrReplaceChild("joint_lh", CubeListBuilder.create().texOffs(41, 40).addBox(-0.25F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 0.0F, 0.0F));

		PartDefinition upper_lh = joint_lh.addOrReplaceChild("upper_lh", CubeListBuilder.create(), PartPose.offset(0.5F, 0.5F, 0.75F));

		PartDefinition HindLFemur_r1 = upper_lh.addOrReplaceChild("HindLFemur_r1", CubeListBuilder.create().texOffs(32, 40).addBox(0.0F, -3.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 2.5F, 1.25F, 0.48F, 0.0F, 0.0F));

		PartDefinition lower_lh = upper_lh.addOrReplaceChild("lower_lh", CubeListBuilder.create().texOffs(16, 52).addBox(0.5F, 4.5F, 1.75F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -0.5F));

		PartDefinition lh_pad = lower_lh.addOrReplaceChild("lh_pad", CubeListBuilder.create().texOffs(0, 35).addBox(5.0F, -1.0F, 10.0F, 5.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.5F, 6.5F, -6.25F));

		PartDefinition right_center = Appendage.addOrReplaceChild("right_center", CubeListBuilder.create().texOffs(38, 22).addBox(-4.0F, -8.0F, 1.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -1.0F));

		PartDefinition joint_rc = right_center.addOrReplaceChild("joint_rc", CubeListBuilder.create().texOffs(10, 9).addBox(-5.0F, -7.5F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition upper_rc = joint_rc.addOrReplaceChild("upper_rc", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition PrimaryRFemur_r1 = upper_rc.addOrReplaceChild("PrimaryRFemur_r1", CubeListBuilder.create().texOffs(57, 39).addBox(-1.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, -6.0F, 1.0F, 0.0F, -0.2182F, 0.0F));

		PartDefinition lower_rc = upper_rc.addOrReplaceChild("lower_rc", CubeListBuilder.create().texOffs(39, 67).addBox(-11.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition rc_pad = lower_rc.addOrReplaceChild("rc_pad", CubeListBuilder.create().texOffs(42, 22).addBox(-14.0F, -1.0F, -4.0F, 5.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_center = Appendage.addOrReplaceChild("left_center", CubeListBuilder.create().texOffs(65, 65).addBox(3.0F, -8.0F, 1.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -1.0F));

		PartDefinition joint_lc = left_center.addOrReplaceChild("joint_lc", CubeListBuilder.create().texOffs(19, 40).addBox(3.75F, -7.5F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition upper_lc = joint_lc.addOrReplaceChild("upper_lc", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition PrimaryLFemur_r1 = upper_lc.addOrReplaceChild("PrimaryLFemur_r1", CubeListBuilder.create().texOffs(32, 62).addBox(-5.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.0F, -6.0F, 1.0F, 0.0F, 0.2182F, 0.0F));

		PartDefinition lower_lc = upper_lc.addOrReplaceChild("lower_lc", CubeListBuilder.create().texOffs(0, 35).addBox(10.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition lc_pad = lower_lc.addOrReplaceChild("lc_pad", CubeListBuilder.create().texOffs(0, 46).addBox(9.0F, -1.0F, -4.0F, 5.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Skeeter.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return Skeeter;
	}
}