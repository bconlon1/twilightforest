package twilightforest.client.model.block.connected;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedOverrides;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.ChunkRenderTypeSet;
import net.neoforged.neoforge.client.RenderTypeGroup;
import net.neoforged.neoforge.client.model.IDynamicBakedModel;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.client.model.data.ModelProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@SuppressWarnings("deprecation")
public class ConnectedTextureModel implements IDynamicBakedModel {

	private final EnumSet<Direction> enabledFaces;
	private final boolean renderOnDisabledFaces;
	private final List<BakedQuad>@Nullable[] baseQuads;
	private final BakedQuad[][][] quads;
	private final TextureAtlasSprite particle;
	private final BakedOverrides overrides;
	private final ItemTransforms transforms;
	@Nullable
	private final ChunkRenderTypeSet blockRenderTypes;
	@Nullable
	private final List<RenderType> itemRenderTypes;
	private final List<Block> validConnectors;
	private static final ModelProperty<ConnectedTextureData> DATA = new ModelProperty<>();

	public ConnectedTextureModel(EnumSet<Direction> enabledFaces, boolean renderOnDisabledFaces, List<Block> connectableBlocks, List<BakedQuad>@Nullable[] baseQuads, BakedQuad[][][] quads, TextureAtlasSprite particle, BakedOverrides overrides, ItemTransforms transforms, RenderTypeGroup group) {
		this.enabledFaces = enabledFaces;
		this.renderOnDisabledFaces = renderOnDisabledFaces;
		this.validConnectors = connectableBlocks;
		this.baseQuads = baseQuads;
		this.quads = quads;
		this.particle = particle;
		this.overrides = overrides;
		this.transforms = transforms;
		this.blockRenderTypes = !group.isEmpty() ? ChunkRenderTypeSet.of(group.block()) : null;
		this.itemRenderTypes = !group.isEmpty() ? List.of(group.entity()) : null;
	}

	@NotNull
	@Override
	public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @NotNull RandomSource random, @NotNull ModelData extraData, @Nullable RenderType type) {
		if (side != null) {
			int faceIndex = side.get3DDataValue();
			ConnectedTextureData data = extraData.get(DATA);
			ArrayList<BakedQuad> quads = new ArrayList<>(4 + (this.baseQuads != null ? 4 : 0));
			if (this.baseQuads != null) {
				quads.addAll(this.baseQuads[faceIndex]);
			}

			if (this.enabledFaces.contains(side) || this.renderOnDisabledFaces) {
				for (int quad = 0; quad < 4; ++quad) {
					//if our model data is null (I really hope it isn't) we can skip connected textures since we dont have the info we need
					//i'd rather do this than crash the game or skip rendering the block entirely
					ConnectionLogic connectionType = data != null && this.enabledFaces.contains(side) ? data.logic[faceIndex][quad] : ConnectionLogic.NONE;
					quads.add(this.quads[faceIndex][quad][connectionType.ordinal()]);
				}
			}

			return quads;
		} else {
			return List.of();
		}
	}

	@NotNull
	@Override
	public ModelData getModelData(@NotNull BlockAndTintGetter getter, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull ModelData modelData) {
		ConnectedTextureData data = new ConnectedTextureData();

		for (Direction face : Direction.values()) {
			Direction[] directions = ConnectionLogic.AXIS_PLANE_DIRECTIONS[face.getAxis().ordinal()];
			boolean[] sideStates = new boolean[4];

			int faceIndex;
			for (faceIndex = 0; faceIndex < directions.length; faceIndex++) {
				sideStates[faceIndex] = this.shouldConnectSide(getter, pos, state, face, directions[faceIndex]);
			}

			faceIndex = face.get3DDataValue();

			for (int dir = 0; dir < directions.length; dir++) {
				int cornerOffset = (dir + 1) % directions.length;
				boolean side1 = sideStates[dir];
				boolean side2 = sideStates[cornerOffset];
				boolean corner = side1 && side2 && this.isCornerBlockPresent(getter, pos, state, face, directions[dir], directions[cornerOffset]);
				data.logic[faceIndex][dir] = dir % 2 == 0 ? ConnectionLogic.of(side1, side2, corner) : ConnectionLogic.of(side2, side1, corner);
			}
		}

		return modelData.derive().with(DATA, data).build();
	}

	private boolean shouldConnectSide(BlockAndTintGetter getter, BlockPos pos, BlockState state, Direction face, Direction side) {
		BlockState neighborState = getter.getBlockState(pos.relative(side));
		return this.validConnectors.stream().anyMatch(neighborState::is) && Block.shouldRenderFace(getter, pos, state, neighborState, face);
	}

	private boolean isCornerBlockPresent(BlockAndTintGetter getter, BlockPos pos, BlockState state, Direction face, Direction side1, Direction side2) {
		BlockState neighborState = getter.getBlockState(pos.relative(side1).relative(side2));
		return this.validConnectors.stream().anyMatch(neighborState::is) && Block.shouldRenderFace(getter, pos, state, neighborState, face);
	}

	@Override
	public boolean useAmbientOcclusion() {
		return true;
	}

	@Override
	public boolean isGui3d() {
		return true;
	}

	@Override
	public boolean usesBlockLight() {
		return true;
	}

	@Override
	public boolean isCustomRenderer() {
		return false;
	}

	@NotNull
	@Override
	public TextureAtlasSprite getParticleIcon() {
		return this.particle;
	}

	@Override
	public BakedOverrides overrides() {
		return this.overrides;
	}

	@NotNull
	@Override
	public ItemTransforms getTransforms() {
		return this.transforms;
	}

	@NotNull
	@Override
	public ChunkRenderTypeSet getRenderTypes(@NotNull BlockState state, @NotNull RandomSource rand, @NotNull ModelData data) {
		return this.blockRenderTypes != null ? this.blockRenderTypes : IDynamicBakedModel.super.getRenderTypes(state, rand, data);
	}

	@NotNull
	@Override
	public List<RenderType> getRenderTypes(@NotNull ItemStack stack) {
		return this.itemRenderTypes != null ? this.itemRenderTypes : IDynamicBakedModel.super.getRenderTypes(stack);
	}

	private static final class ConnectedTextureData {
		private final ConnectionLogic[][] logic = new ConnectionLogic[6][4];

		private ConnectedTextureData() {
		}
	}
}