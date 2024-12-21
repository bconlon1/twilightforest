package twilightforest.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import twilightforest.TwilightForestMod;
import twilightforest.client.model.TFModelLayers;
import twilightforest.client.model.entity.MistWolfModel;
import twilightforest.client.state.MistWolfRenderState;
import twilightforest.entity.monster.MistWolf;

public class MistWolfRenderer extends MobRenderer<MistWolf, MistWolfRenderState, MistWolfModel> {

	private static final ResourceLocation TEXTURE = TwilightForestMod.getModelTexture("mistwolf.png");

	public MistWolfRenderer(EntityRendererProvider.Context context) {
		super(context, new MistWolfModel(context.bakeLayer(TFModelLayers.HOSTILE_WOLF)), 1.0F);
	}

	@Override
	protected int getModelTint(MistWolfRenderState state) {
		float misty = Math.min(1.0F, state.brightness * 3.0F + 0.25F);
		float smoky = state.brightness * 2.0F + 0.6F;
		return ARGB.colorFromFloat(smoky, misty, misty, misty);
	}

	@Override
	public MistWolfRenderState createRenderState() {
		return new MistWolfRenderState();
	}

	@Override
	public void extractRenderState(MistWolf entity, MistWolfRenderState state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		state.brightness = entity.level().getMaxLocalRawBrightness(entity.blockPosition());
	}

	@Override
	public ResourceLocation getTextureLocation(MistWolfRenderState state) {
		return TEXTURE;
	}
}
