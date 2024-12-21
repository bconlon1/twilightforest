package twilightforest.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import twilightforest.TwilightForestMod;
import twilightforest.client.model.TFModelLayers;
import twilightforest.client.model.entity.KoboldModel;
import twilightforest.client.state.KoboldRenderState;
import twilightforest.entity.monster.Kobold;

public class KoboldRenderer extends HumanoidMobRenderer<Kobold, KoboldRenderState, KoboldModel> {

	private static final ResourceLocation TEXTURE = TwilightForestMod.getModelTexture("kobold.png");

	public KoboldRenderer(EntityRendererProvider.Context context) {
		super(context, new KoboldModel(context.bakeLayer(TFModelLayers.KOBOLD)), 0.4F);
	}

	@Override
	public KoboldRenderState createRenderState() {
		return new KoboldRenderState();
	}

	@Override
	public void extractRenderState(Kobold entity, KoboldRenderState state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		state.jumping = !entity.isNoAi() && entity.getDeltaMovement().y() != 0;
	}

	@Override
	public ResourceLocation getTextureLocation(KoboldRenderState state) {
		return TEXTURE;
	}
}
