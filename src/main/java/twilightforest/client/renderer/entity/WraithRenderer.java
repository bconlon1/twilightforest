package twilightforest.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.resources.ResourceLocation;
import twilightforest.TwilightForestMod;
import twilightforest.client.model.TFModelLayers;
import twilightforest.client.model.entity.WraithModel;
import twilightforest.entity.monster.Wraith;

public class WraithRenderer extends HumanoidMobRenderer<Wraith, HumanoidRenderState, WraithModel> {

	private static final ResourceLocation TEXTURE = TwilightForestMod.getModelTexture("ghost.png");

	public WraithRenderer(EntityRendererProvider.Context context) {
		super(context, new WraithModel(context.bakeLayer(TFModelLayers.WRAITH)), 0.5F);
	}

	@Override
	public HumanoidRenderState createRenderState() {
		return new HumanoidRenderState();
	}

	@Override
	public ResourceLocation getTextureLocation(HumanoidRenderState state) {
		return TEXTURE;
	}
}
