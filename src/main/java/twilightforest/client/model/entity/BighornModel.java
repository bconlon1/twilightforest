package twilightforest.client.model.entity;

import net.minecraft.client.model.SheepModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.SheepRenderState;
import twilightforest.client.JappaPackReloadListener;

public class BighornModel extends SheepModel {

	public BighornModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition checkForPack() {
		return JappaPackReloadListener.INSTANCE.isJappaPackLoaded() ? createJappaModel() : create();
	}

	private static LayerDefinition create() {
		MeshDefinition meshdefinition = SheepModel.createBodyMesh(0, CubeDeformation.NONE);
		PartDefinition partdefinition = meshdefinition.getRoot();

		var head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create()
				.texOffs(0, 0)
				.addBox(-3.0F, -4.0F, -6.0F, 6.0F, 6.0F, 7.0F),
			PartPose.offset(0.0F, 6.0F, -8.0F));

		head.addOrReplaceChild("left_horn", CubeListBuilder.create()
				.texOffs(28, 16)
				.addBox(-5.0F, -4.0F, -4.0F, 2.0F, 2.0F, 2.0F)
				.texOffs(16, 13)
				.addBox(-6.0F, -5.0F, -3.0F, 2.0F, 2.0F, 4.0F)
				.texOffs(16, 19)
				.addBox(-7.0F, -4.0F, 0.0F, 2.0F, 5.0F, 2.0F)
				.texOffs(18, 27)
				.addBox(-8.0F, 0.0F, -2.0F, 2.0F, 2.0F, 3.0F)
				.texOffs(28, 27)
				.addBox(-9.0F, -1.0F, -3.0F, 2.0F, 2.0F, 1.0F),
			PartPose.ZERO);

		head.addOrReplaceChild("right_horn", CubeListBuilder.create()
				.texOffs(28, 16)
				.addBox(3.0F, -4.0F, -4.0F, 2.0F, 2.0F, 2.0F)
				.texOffs(16, 13)
				.addBox(4.0F, -5.0F, -3.0F, 2.0F, 2.0F, 4.0F)
				.texOffs(16, 19)
				.addBox(5.0F, -4.0F, 0.0F, 2.0F, 5.0F, 2.0F)
				.texOffs(18, 27)
				.addBox(6.0F, 0.0F, -2.0F, 2.0F, 2.0F, 3.0F)
				.texOffs(28, 27)
				.addBox(7.0F, -1.0F, -3.0F, 2.0F, 2.0F, 1.0F),
			PartPose.ZERO);

		partdefinition.addOrReplaceChild("body", CubeListBuilder.create()
				.texOffs(36, 10)
				.addBox(-4.0F, -9.0F, -7.0F, 8.0F, 15.0F, 6.0F),
			PartPose.offsetAndRotation(0.0F, 5.0F, 2.0F, 1.5707963267948966F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("left_front_leg", CubeListBuilder.create()
				.texOffs(0, 16)
				.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F),
			PartPose.offset(-3.0F, 12.0F, 7.0F));

		partdefinition.addOrReplaceChild("right_front_leg", CubeListBuilder.create()
				.texOffs(0, 16)
				.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F),
			PartPose.offset(3.0F, 12.0F, 7.0F));

		partdefinition.addOrReplaceChild("left_hind_leg", CubeListBuilder.create()
				.texOffs(0, 16)
				.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F),
			PartPose.offset(-3.0F, 12.0F, -5.0F));

		partdefinition.addOrReplaceChild("right_hind_leg", CubeListBuilder.create()
				.texOffs(0, 16)
				.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F),
			PartPose.offset(3.0F, 12.0F, -5.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	private static LayerDefinition createJappaModel() {
		MeshDefinition meshdefinition = SheepModel.createBodyMesh(0, CubeDeformation.NONE);
		PartDefinition partdefinition = meshdefinition.getRoot();

		var head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create()
				.texOffs(38, 0)
				.addBox(-3.0F, -4.0F, -6.0F, 6.0F, 6.0F, 7.0F),
			PartPose.offset(0.0F, 5.0F, -8.0F));

		head.addOrReplaceChild("right_horn", CubeListBuilder.create()
				.texOffs(0, 0)
				.addBox(-3.0F, -1.0F, -7.0F, 3.0F, 3.0F, 5.0F)
				.texOffs(0, 8)
				.addBox(-4.0F, 2.0F, -9.0F, 3.0F, 2.0F, 5.0F)
				.texOffs(4, 15)
				.addBox(-4.0F, 0.0F, -11.0F, 2.0F, 3.0F, 2.0F),
			PartPose.offsetAndRotation(-2.0F, -3.0F, -1.0F, 0.0F, 0.39269908169872414F, 0.2181661564992912F));

		head.addOrReplaceChild("left_horn", CubeListBuilder.create()
				.texOffs(16, 0)
				.addBox(0.0F, -1.0F, -7.0F, 3.0F, 3.0F, 5.0F)
				.texOffs(16, 8)
				.addBox(1.0F, 2.0F, -9.0F, 3.0F, 2.0F, 5.0F)
				.texOffs(20, 15)
				.addBox(2.0F, 0.0F, -11.0F, 2.0F, 3.0F, 2.0F),
			PartPose.offsetAndRotation(2.0F, -3.0F, -1.0F, 0.0F, -0.39269908169872414F, -0.2181661564992912F));

		partdefinition.addOrReplaceChild("body", CubeListBuilder.create()
				.texOffs(34, 13)
				.addBox(-4.5F, -14.0F, -3.0F, 9.0F, 16.0F, 6.0F),
			PartPose.offsetAndRotation(0.0F, 10.0F, 6.0F, 1.5707963267948966F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("right_hind_leg", CubeListBuilder.create()
				.texOffs(0, 48)
				.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F),
			PartPose.offset(-3.0F, 12.0F, 7.0F));

		partdefinition.addOrReplaceChild("left_hind_leg", CubeListBuilder.create()
				.texOffs(16, 48)
				.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F),
			PartPose.offset(3.0F, 12.0F, 7.0F));

		partdefinition.addOrReplaceChild("right_front_leg", CubeListBuilder.create()
				.texOffs(0, 32)
				.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F),
			PartPose.offset(-3.0F, 12.0F, -5.0F));

		partdefinition.addOrReplaceChild("left_front_leg", CubeListBuilder.create()
				.texOffs(16, 32)
				.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F),
			PartPose.offset(3.0F, 12.0F, -5.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(SheepRenderState state) {
		this.head.getChild("left_horn").visible = !state.isBaby;
		this.head.getChild("right_horn").visible = !state.isBaby;
		super.setupAnim(state);
	}
}
