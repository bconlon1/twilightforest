package twilightforest.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import twilightforest.TwilightForestMod;
import twilightforest.client.model.TFModelLayers;
import twilightforest.client.model.entity.BlockChainGoblinModel;
import twilightforest.client.model.entity.ChainModel;
import twilightforest.client.model.entity.SpikeBlockModel;
import twilightforest.client.state.BlockChainGoblinRenderState;
import twilightforest.entity.monster.BlockChainGoblin;

public class BlockChainGoblinRenderer extends HumanoidMobRenderer<BlockChainGoblin, BlockChainGoblinRenderState, BlockChainGoblinModel> {

	private static final ResourceLocation GOBLIN_TEXTURE = TwilightForestMod.getModelTexture("blockgoblin.png");
	private static final ResourceLocation BLOCK_AND_CHAIN_TEXTURE = TwilightForestMod.getModelTexture("block_and_chain.png");

	private final Model model;
	private final Model chainModel;

	public BlockChainGoblinRenderer(EntityRendererProvider.Context context) {
		super(context, new BlockChainGoblinModel(context.bakeLayer(TFModelLayers.BLOCKCHAIN_GOBLIN)), 0.4F);
		this.model = new SpikeBlockModel(context.bakeLayer(TFModelLayers.CHAIN_BLOCK));
		this.chainModel = new ChainModel(context.bakeLayer(TFModelLayers.CHAIN));
	}

	@Override
	public void render(BlockChainGoblinRenderState state, PoseStack stack, MultiBufferSource source, int light) {
		super.render(state, stack, source, light);

		stack.pushPose();

		double blockInX = (state.chainBlockPos.x() - state.x);
		double blockInY = (state.chainBlockPos.y() - state.y);
		double blockInZ = (state.chainBlockPos.z() - state.z);

		VertexConsumer consumer = source.getBuffer(this.model.renderType(BLOCK_AND_CHAIN_TEXTURE));
		stack.translate(blockInX, blockInY, blockInZ);

		stack.mulPose(Axis.YP.rotationDegrees(180 - Mth.wrapDegrees(state.yRot)));
		stack.mulPose(Axis.XP.rotationDegrees(state.xRot));

		stack.scale(-1.0F, -1.0F, 1.0F);

		this.model.renderToBuffer(stack, consumer, light, OverlayTexture.NO_OVERLAY);
		stack.popPose();

		if (state.deathTime <= 0) {
			stack.pushPose();
			stack.translate(0.0D, state.eyeHeight, 0.0D);
			Vec3 xyz = state.chainStartPos;
			BlockChainRenderer.renderChain(false, xyz, 0.00D, stack, source, light, this.chainModel);
			BlockChainRenderer.renderChain(false, xyz, 0.25D, stack, source, light, this.chainModel);
			BlockChainRenderer.renderChain(false, xyz, 0.50D, stack, source, light, this.chainModel);
			BlockChainRenderer.renderChain(false, xyz, 0.75D, stack, source, light, this.chainModel);
			stack.popPose();
		}
	}

	@Override
	public BlockChainGoblinRenderState createRenderState() {
		return new BlockChainGoblinRenderState();
	}

	@Override
	public void extractRenderState(BlockChainGoblin entity, BlockChainGoblinRenderState state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		state.chainBlockPos = entity.block.position();
		state.chainStartPos = entity.block.getEyePosition().subtract(entity.getEyePosition()).multiply(1.0D, 0.5D, 1.0D);
	}

	@Override
	public boolean shouldRender(BlockChainGoblin entity, Frustum frustum, double camX, double camY, double camZ) {
		if (super.shouldRender(entity, frustum, camX, camY, camZ)) {
			return true;
		} else {
			Vec3 vec3d = this.getPosition(entity.block, entity.block.getBbHeight() * 0.5D);
			Vec3 vec3d1 = this.getPosition(entity.block, entity.block.getEyeHeight());
			return frustum.isVisible(new AABB(vec3d1.x, vec3d1.y, vec3d1.z, vec3d.x, vec3d.y, vec3d.z));
		}
	}

	private Vec3 getPosition(Entity entity, double yOffset) {
		double d0 = Mth.lerp(1.0F, entity.xOld, entity.getX());
		double d1 = Mth.lerp(1.0F, entity.yOld, entity.getY()) + yOffset;
		double d2 = Mth.lerp(1.0F, entity.zOld, entity.getZ());
		return new Vec3(d0, d1, d2);
	}

	@Override
	public ResourceLocation getTextureLocation(BlockChainGoblinRenderState entity) {
		return GOBLIN_TEXTURE;
	}
}
