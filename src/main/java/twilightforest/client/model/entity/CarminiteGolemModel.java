package twilightforest.client.model.entity;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.IronGolemRenderState;
import net.minecraft.util.Mth;

public class CarminiteGolemModel extends EntityModel<IronGolemRenderState> {

	private final ModelPart head;
	private final ModelPart rightArm;
	private final ModelPart leftArm;
	private final ModelPart leftLeg;
	private final ModelPart rightLeg;

	public CarminiteGolemModel(ModelPart root) {
		super(root);
		this.head = root.getChild("head");
		this.rightArm = root.getChild("right_arm");
		this.leftArm = root.getChild("left_arm");
		this.rightLeg = root.getChild("right_leg");
		this.leftLeg = root.getChild("left_leg");
	}

	public static LayerDefinition create() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("head", CubeListBuilder.create()
				.texOffs(0, 0)
				.addBox(-3.5F, -10.0F, -3.0F, 7.0F, 8.0F, 6.0F)
				.texOffs(0, 14)
				.addBox(-4.0F, -6.0F, -3.5F, 8.0F, 4.0F, 6.0F),
			PartPose.offset(0.0F, -11.0F, -2.0F));

		partdefinition.addOrReplaceChild("body", CubeListBuilder.create()
				.texOffs(0, 26)
				.addBox(-8.0F, 0.0F, -5.0F, 16.0F, 10.0F, 10.0F),
			PartPose.offset(0.0F, -13.0F, 0.0F));

		partdefinition.addOrReplaceChild("ribs", CubeListBuilder.create()
				.texOffs(0, 46)
				.addBox(-5.0F, 0.0F, -3.0F, 10.0F, 6.0F, 6.0F),
			PartPose.offset(0.0F, -3.0F, 0.0F));

		partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create()
				.texOffs(52, 0) // arm
				.addBox(-5.0F, -2.0F, -1.5F, 3.0F, 14.0F, 3.0F)
				.texOffs(52, 17) // fist
				.addBox(-7.0F, 12.0F, -3.0F, 6.0F, 12.0F, 6.0F)
				.texOffs(52, 36) // shoulder top
				.addBox(-7.0F, -3.0F, -3.5F, 7.0F, 2.0F, 7.0F)
				.texOffs(52, 45) // shoulder front
				.addBox(-7.0F, -1.0F, -3.5F, 7.0F, 5.0F, 2.0F)
				.texOffs(52, 45) // shoulder back
				.addBox(-7.0F, -1.0F, 1.5F, 7.0F, 5.0F, 2.0F)
				.texOffs(52, 54) // shoulder inner
				.addBox(-2.0F, -1.0F, -2.0F, 2.0F, 5.0F, 3.0F),
			PartPose.offset(-8.0F, -12.0F, 0.0F));

		partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create()
				.mirror()
				.texOffs(52, 0) // arm
				.addBox(2.0F, -2.0F, -1.5F, 3.0F, 14.0F, 3.0F)
				.texOffs(52, 17) // fist
				.addBox(1.0F, 12.0F, -3.0F, 6.0F, 12.0F, 6.0F)
				.texOffs(52, 36) // shoulder top
				.addBox(0.0F, -.03F, -3.5F, 7.0F, 2.0F, 7.0F)
				.texOffs(52, 45) // shoulder front
				.addBox(0.0F, -1.0F, -3.5F, 7.0F, 5.0F, 2.0F)
				.texOffs(52, 45) // shoulder back
				.addBox(0.0F, -1.0F, 1.5F, 7.0F, 5.0F, 2.0F)
				.texOffs(52, 54) // shoulder inner
				.addBox(0.0F, -1.0F, -2.0F, 2.0F, 5.0F, 3.0F),
			PartPose.offset(8.0F, -12.0F, 0.0F));

		partdefinition.addOrReplaceChild("hips", CubeListBuilder.create()
				.texOffs(84, 25)
				.addBox(-5.0F, 0.0F, -2.0F, 10.0F, 3.0F, 4.0F),
			PartPose.offset(0.0F, 1.0F, 0.0F));

		partdefinition.addOrReplaceChild("spine", CubeListBuilder.create()
				.texOffs(84, 18)
				.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F),
			PartPose.offset(0.0F, -3.0F, 0.0F));

		partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create()
				.texOffs(84, 32)
				.addBox(-3.0F, 0.0F, -1.5F, 3.0F, 8.0F, 3.0F)
				.texOffs(84, 43)
				.addBox(-5.5F, 8.0F, -4.0F, 6.0F, 14.0F, 7.0F),
			PartPose.offset(-1.0F, 2.0F, 0.0F));

		partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create()
				.mirror()
				.texOffs(84, 32)
				.addBox(0.0F, 0.0F, -1.5F, 3.0F, 8.0F, 3.0F)
				.texOffs(84, 43)
				.addBox(-0.5F, 8.0F, -4.0F, 6.0F, 14.0F, 7.0F),
			PartPose.offset(1.0F, 2.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void setupAnim(IronGolemRenderState state) {
		super.setupAnim(state);
		float timer = state.attackTicksRemaining;
		if (timer > 0.0F) {
			this.rightArm.xRot = -2.0F + 1.5F * Mth.triangleWave(timer, 10.0F);
			this.leftArm.xRot = -2.0F + 1.5F * Mth.triangleWave(timer, 10.0F);
		} else {
			this.rightArm.xRot = (-0.2F + 1.5F * Mth.triangleWave(state.walkAnimationPos, 13.0F)) * state.walkAnimationSpeed;
			this.leftArm.xRot = (-0.2F - 1.5F * Mth.triangleWave(state.walkAnimationPos, 13.0F)) * state.walkAnimationSpeed;
		}

		this.head.yRot = state.yRot * Mth.DEG_TO_RAD;
		this.head.xRot = state.xRot * Mth.DEG_TO_RAD;
		this.leftLeg.xRot = -1.5F * Mth.triangleWave(state.walkAnimationPos, 13.0F) * state.walkAnimationSpeed;
		this.rightLeg.xRot = 1.5F * Mth.triangleWave(state.walkAnimationPos, 13.0F) * state.walkAnimationSpeed;
		this.leftLeg.yRot = 0.0F;
		this.rightLeg.yRot = 0.0F;

		this.rightArm.zRot = Mth.cos(state.ageInTicks * 0.09F) * 0.05F + 0.05F;
		this.leftArm.zRot = -Mth.cos(state.ageInTicks * 0.09F) * 0.05F - 0.05F;
	}
}
