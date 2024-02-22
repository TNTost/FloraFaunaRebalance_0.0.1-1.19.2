package net.tabby.florafaunarebalance.entity.client;// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class SandDustCloudModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart dust;

	public SandDustCloudModel(ModelPart root) {
		this.dust = root.getChild("dust");
	}

	public static LayerDefinition createBodyLayer() { //# TODO: when updating model, only replace this part>...
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition dust = partdefinition.addOrReplaceChild("dust", CubeListBuilder.create(), PartPose.offset(-1.0F, 46.0F, 0.0F));

		PartDefinition main = dust.addOrReplaceChild("main", CubeListBuilder.create(), PartPose.offset(0.0F, -26.0F, -7.0F));

		PartDefinition bone = main.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-19.0F, -65.0F, -17.0F, 39.0F, 39.0F, 25.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 26.0F, 7.0F));

		PartDefinition bone2 = main.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(101, 129).addBox(-12.0F, -62.0F, 8.0F, 32.0F, 36.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 26.0F, 7.0F));

		PartDefinition sub = dust.addOrReplaceChild("sub", CubeListBuilder.create(), PartPose.offset(0.0F, -26.0F, -7.0F));

		PartDefinition bone3 = sub.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(0, 105).addBox(-1.0F, -50.0F, -26.0F, 25.0F, 25.0F, 25.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 26.0F, 7.0F));

		PartDefinition bone4 = sub.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(98, 82).addBox(-25.0F, -57.0F, -8.0F, 32.0F, 23.0F, 23.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 26.0F, 7.0F));

		PartDefinition bone5 = sub.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(129, 0).addBox(-3.0F, -69.0F, -4.0F, 15.0F, 23.0F, 31.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 26.0F, 7.0F));

		PartDefinition bone6 = sub.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(0, 65).addBox(-7.0F, -36.0F, -19.0F, 35.0F, 14.0F, 25.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 26.0F, 7.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		//# TODO: add animation of [appearance, idle]...
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		dust.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return dust;
	}
}