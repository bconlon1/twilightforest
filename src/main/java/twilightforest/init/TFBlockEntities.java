package twilightforest.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import twilightforest.TwilightForestMod;
import twilightforest.block.entity.*;
import twilightforest.block.entity.bookshelf.ChiseledCanopyShelfBlockEntity;
import twilightforest.block.entity.spawner.*;

public class TFBlockEntities {

	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, TwilightForestMod.ID);

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AntibuilderBlockEntity>> ANTIBUILDER = BLOCK_ENTITIES.register("antibuilder", () -> new BlockEntityType<>(AntibuilderBlockEntity::new, TFBlocks.ANTIBUILDER.get()));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CinderFurnaceBlockEntity>> CINDER_FURNACE = BLOCK_ENTITIES.register("cinder_furnace", () -> new BlockEntityType<>(CinderFurnaceBlockEntity::new, TFBlocks.CINDER_FURNACE.get()));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CarminiteReactorBlockEntity>> CARMINITE_REACTOR = BLOCK_ENTITIES.register("carminite_reactor", () -> new BlockEntityType<>(CarminiteReactorBlockEntity::new, TFBlocks.CARMINITE_REACTOR.get()));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ReactorDebrisBlockEntity>> REACTOR_DEBRIS = BLOCK_ENTITIES.register("reactor_debris", () -> new BlockEntityType<>(ReactorDebrisBlockEntity::new, TFBlocks.REACTOR_DEBRIS.get()));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<FireJetBlockEntity>> FLAME_JET = BLOCK_ENTITIES.register("flame_jet", () -> new BlockEntityType<>(FireJetBlockEntity::new, TFBlocks.FIRE_JET.get(), TFBlocks.ENCASED_FIRE_JET.get()));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GhastTrapBlockEntity>> GHAST_TRAP = BLOCK_ENTITIES.register("ghast_trap", () -> new BlockEntityType<>(GhastTrapBlockEntity::new, TFBlocks.GHAST_TRAP.get()));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TFSmokerBlockEntity>> SMOKER = BLOCK_ENTITIES.register("smoker", () -> new BlockEntityType<>(TFSmokerBlockEntity::new, TFBlocks.SMOKER.get(), TFBlocks.ENCASED_SMOKER.get()));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CarminiteBuilderBlockEntity>> TOWER_BUILDER = BLOCK_ENTITIES.register("tower_builder", () -> new BlockEntityType<>(CarminiteBuilderBlockEntity::new, TFBlocks.CARMINITE_BUILDER.get()));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TrophyBlockEntity>> TROPHY = BLOCK_ENTITIES.register("trophy", () -> new BlockEntityType<>(TrophyBlockEntity::new,
			TFBlocks.NAGA_TROPHY.get(), TFBlocks.LICH_TROPHY.get(), TFBlocks.MINOSHROOM_TROPHY.get(),
			TFBlocks.HYDRA_TROPHY.get(), TFBlocks.KNIGHT_PHANTOM_TROPHY.get(), TFBlocks.UR_GHAST_TROPHY.get(), TFBlocks.ALPHA_YETI_TROPHY.get(),
			TFBlocks.SNOW_QUEEN_TROPHY.get(), TFBlocks.QUEST_RAM_TROPHY.get(), TFBlocks.NAGA_WALL_TROPHY.get(), TFBlocks.LICH_WALL_TROPHY.get(),
			TFBlocks.MINOSHROOM_WALL_TROPHY.get(), TFBlocks.HYDRA_WALL_TROPHY.get(), TFBlocks.KNIGHT_PHANTOM_WALL_TROPHY.get(), TFBlocks.UR_GHAST_WALL_TROPHY.get(),
			TFBlocks.ALPHA_YETI_WALL_TROPHY.get(), TFBlocks.SNOW_QUEEN_WALL_TROPHY.get(), TFBlocks.QUEST_RAM_WALL_TROPHY.get()));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AlphaYetiSpawnerBlockEntity>> ALPHA_YETI_SPAWNER = BLOCK_ENTITIES.register("alpha_yeti_spawner", () -> new BlockEntityType<>(AlphaYetiSpawnerBlockEntity::new, TFBlocks.ALPHA_YETI_BOSS_SPAWNER.get()));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<FinalBossSpawnerBlockEntity>> FINAL_BOSS_SPAWNER = BLOCK_ENTITIES.register("final_boss_spawner", () -> new BlockEntityType<>(FinalBossSpawnerBlockEntity::new, TFBlocks.FINAL_BOSS_BOSS_SPAWNER.get()));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<HydraSpawnerBlockEntity>> HYDRA_SPAWNER = BLOCK_ENTITIES.register("hydra_boss_spawner", () -> new BlockEntityType<>(HydraSpawnerBlockEntity::new, TFBlocks.HYDRA_BOSS_SPAWNER.get()));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<KnightPhantomSpawnerBlockEntity>> KNIGHT_PHANTOM_SPAWNER = BLOCK_ENTITIES.register("knight_phantom_spawner", () -> new BlockEntityType<>(KnightPhantomSpawnerBlockEntity::new, TFBlocks.KNIGHT_PHANTOM_BOSS_SPAWNER.get()));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LichSpawnerBlockEntity>> LICH_SPAWNER = BLOCK_ENTITIES.register("lich_spawner", () -> new BlockEntityType<>(LichSpawnerBlockEntity::new, TFBlocks.LICH_BOSS_SPAWNER.get()));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MinoshroomSpawnerBlockEntity>> MINOSHROOM_SPAWNER = BLOCK_ENTITIES.register("minoshroom_spawner", () -> new BlockEntityType<>(MinoshroomSpawnerBlockEntity::new, TFBlocks.MINOSHROOM_BOSS_SPAWNER.get()));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<NagaSpawnerBlockEntity>> NAGA_SPAWNER = BLOCK_ENTITIES.register("naga_spawner", () -> new BlockEntityType<>(NagaSpawnerBlockEntity::new, TFBlocks.NAGA_BOSS_SPAWNER.get()));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SnowQueenSpawnerBlockEntity>> SNOW_QUEEN_SPAWNER = BLOCK_ENTITIES.register("snow_queen_spawner", () -> new BlockEntityType<>(SnowQueenSpawnerBlockEntity::new, TFBlocks.SNOW_QUEEN_BOSS_SPAWNER.get()));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<UrGhastSpawnerBlockEntity>> UR_GHAST_SPAWNER = BLOCK_ENTITIES.register("tower_boss_spawner", () -> new BlockEntityType<>(UrGhastSpawnerBlockEntity::new, TFBlocks.UR_GHAST_BOSS_SPAWNER.get()));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CicadaBlockEntity>> CICADA = BLOCK_ENTITIES.register("cicada", () -> new BlockEntityType<>(CicadaBlockEntity::new, TFBlocks.CICADA.get()));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<FireflyBlockEntity>> FIREFLY = BLOCK_ENTITIES.register("firefly", () -> new BlockEntityType<>(FireflyBlockEntity::new, TFBlocks.FIREFLY.get()));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MoonwormBlockEntity>> MOONWORM = BLOCK_ENTITIES.register("moonworm", () -> new BlockEntityType<>(MoonwormBlockEntity::new, TFBlocks.MOONWORM.get()));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<KeepsakeCasketBlockEntity>> KEEPSAKE_CASKET = BLOCK_ENTITIES.register("keepsake_casket", () -> new BlockEntityType<>(KeepsakeCasketBlockEntity::new, TFBlocks.KEEPSAKE_CASKET.get()));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TFChestBlockEntity>> TF_CHEST = BLOCK_ENTITIES.register("tf_chest", () -> new BlockEntityType<>(TFChestBlockEntity::new,
			TFBlocks.TWILIGHT_OAK_CHEST.get(), TFBlocks.CANOPY_CHEST.get(), TFBlocks.MANGROVE_CHEST.get(),
			TFBlocks.DARK_CHEST.get(), TFBlocks.TIME_CHEST.get(), TFBlocks.TRANSFORMATION_CHEST.get(),
			TFBlocks.MINING_CHEST.get(), TFBlocks.SORTING_CHEST.get()));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TFChestBlockEntity>> TF_TRAPPED_CHEST = BLOCK_ENTITIES.register("tf_trapped_chest", () -> new BlockEntityType<>(TFChestBlockEntity::new,
			TFBlocks.TWILIGHT_OAK_TRAPPED_CHEST.get(), TFBlocks.CANOPY_TRAPPED_CHEST.get(), TFBlocks.MANGROVE_TRAPPED_CHEST.get(),
			TFBlocks.DARK_TRAPPED_CHEST.get(), TFBlocks.TIME_TRAPPED_CHEST.get(), TFBlocks.TRANSFORMATION_TRAPPED_CHEST.get(),
			TFBlocks.MINING_TRAPPED_CHEST.get(), TFBlocks.SORTING_TRAPPED_CHEST.get()));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SkullCandleBlockEntity>> SKULL_CANDLE = BLOCK_ENTITIES.register("skull_candle", () -> new BlockEntityType<>(SkullCandleBlockEntity::new,
			TFBlocks.ZOMBIE_SKULL_CANDLE.get(), TFBlocks.ZOMBIE_WALL_SKULL_CANDLE.get(),
			TFBlocks.SKELETON_SKULL_CANDLE.get(), TFBlocks.SKELETON_WALL_SKULL_CANDLE.get(),
			TFBlocks.WITHER_SKELE_SKULL_CANDLE.get(), TFBlocks.WITHER_SKELE_WALL_SKULL_CANDLE.get(),
			TFBlocks.CREEPER_SKULL_CANDLE.get(), TFBlocks.CREEPER_WALL_SKULL_CANDLE.get(),
			TFBlocks.PLAYER_SKULL_CANDLE.get(), TFBlocks.PLAYER_WALL_SKULL_CANDLE.get(),
			TFBlocks.PIGLIN_SKULL_CANDLE.get(), TFBlocks.PIGLIN_WALL_SKULL_CANDLE.get()));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ChiseledCanopyShelfBlockEntity>> CHISELED_CANOPY_BOOKSHELF = BLOCK_ENTITIES.register("chiseled_canopy_bookshelf", () -> new BlockEntityType<>(ChiseledCanopyShelfBlockEntity::new, TFBlocks.CHISELED_CANOPY_BOOKSHELF.get()));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GrowingBeanstalkBlockEntity>> BEANSTALK_GROWER = BLOCK_ENTITIES.register("beanstalk_grower", () -> new BlockEntityType<>(GrowingBeanstalkBlockEntity::new, TFBlocks.BEANSTALK_GROWER.get()));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RedThreadBlockEntity>> RED_THREAD = BLOCK_ENTITIES.register("red_thread", () -> new BlockEntityType<>(RedThreadBlockEntity::new, TFBlocks.RED_THREAD.get()));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CandelabraBlockEntity>> CANDELABRA = BLOCK_ENTITIES.register("candelabra", () -> new BlockEntityType<>(CandelabraBlockEntity::new, TFBlocks.CANDELABRA.get()));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<JarBlockEntity>> JAR = BLOCK_ENTITIES.register("jar", () -> new BlockEntityType<>(JarBlockEntity::new, TFBlocks.FIREFLY_JAR.get(), TFBlocks.CICADA_JAR.get()));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MasonJarBlockEntity>> MASON_JAR = BLOCK_ENTITIES.register("mason_jar", () -> new BlockEntityType<>(MasonJarBlockEntity::new, TFBlocks.MASON_JAR.get()));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CursedSpawnerBlockEntity>> CURSED_SPAWNER = BLOCK_ENTITIES.register("cursed_spawner", () -> new BlockEntityType<>(CursedSpawnerBlockEntity::new, TFBlocks.CURSED_SPAWNER.value()));
}
