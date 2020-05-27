package twilightforest.client;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import twilightforest.block.TFBlocks;

public class RenderLayerRegistration {
	public static void init() {
		RenderType cutout = RenderType.getCutout();
		RenderTypeLookup.setRenderLayer(TFBlocks.twilight_portal_miniature_structure.get(), cutout);
		RenderTypeLookup.setRenderLayer(TFBlocks.hedge_maze_miniature_structure.get(), cutout);
		RenderTypeLookup.setRenderLayer(TFBlocks.hollow_hill_miniature_structure.get(), cutout);
		RenderTypeLookup.setRenderLayer(TFBlocks.quest_grove_miniature_structure.get(), cutout);
		RenderTypeLookup.setRenderLayer(TFBlocks.mushroom_tower_miniature_structure.get(), cutout);
		RenderTypeLookup.setRenderLayer(TFBlocks.naga_courtyard_miniature_structure.get(), cutout);
		RenderTypeLookup.setRenderLayer(TFBlocks.lich_tower_miniature_structure.get(), cutout);
		RenderTypeLookup.setRenderLayer(TFBlocks.minotaur_labyrinth_miniature_structure.get(), cutout);
		RenderTypeLookup.setRenderLayer(TFBlocks.hydra_lair_miniature_structure.get(), cutout);
		RenderTypeLookup.setRenderLayer(TFBlocks.goblin_stronghold_miniature_structure.get(), cutout);
		RenderTypeLookup.setRenderLayer(TFBlocks.dark_tower_miniature_structure.get(), cutout);
		RenderTypeLookup.setRenderLayer(TFBlocks.yeti_cave_miniature_structure.get(), cutout);
		RenderTypeLookup.setRenderLayer(TFBlocks.aurora_palace_miniature_structure.get(), cutout);
		RenderTypeLookup.setRenderLayer(TFBlocks.troll_cave_cottage_miniature_structure.get(), cutout);
		RenderTypeLookup.setRenderLayer(TFBlocks.final_castle_miniature_structure.get(), cutout);
		RenderTypeLookup.setRenderLayer(TFBlocks.firefly_jar.get(), cutout);
		RenderTypeLookup.setRenderLayer(TFBlocks.oak_sapling.get(), cutout);
		RenderTypeLookup.setRenderLayer(TFBlocks.canopy_sapling.get(), cutout);
		RenderTypeLookup.setRenderLayer(TFBlocks.mangrove_sapling.get(), cutout);
		RenderTypeLookup.setRenderLayer(TFBlocks.darkwood_sapling.get(), cutout);
		RenderTypeLookup.setRenderLayer(TFBlocks.time_sapling.get(), cutout);
		RenderTypeLookup.setRenderLayer(TFBlocks.transformation_sapling.get(), cutout);
		RenderTypeLookup.setRenderLayer(TFBlocks.mining_sapling.get(), cutout);
		RenderTypeLookup.setRenderLayer(TFBlocks.sorting_sapling.get(), cutout);
	}
}
