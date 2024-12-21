package twilightforest.init;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.Nullable;
import twilightforest.TwilightForestMod;
import twilightforest.entity.*;
import twilightforest.entity.boss.*;
import twilightforest.entity.monster.*;
import twilightforest.entity.passive.*;
import twilightforest.entity.projectile.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class TFEntities {

	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, TwilightForestMod.ID);
	public static final DeferredRegister<Item> SPAWN_EGGS = DeferredRegister.create(Registries.ITEM, TwilightForestMod.ID);
	public static final Map<Holder<EntityType<?>>, Supplier<AttributeSupplier.Builder>> ATTRIBUTES = new HashMap<>();
	public static final Map<Holder<EntityType<?>>, SpawnPlacements.SpawnPredicate<?>> SPAWN_PREDICATES = new HashMap<>();

	public static final DeferredHolder<EntityType<?>, EntityType<Adherent>> ADHERENT = registerWithAttributes("adherent", EntityType.Builder.of(Adherent::new, MobCategory.MONSTER).sized(0.8F, 2.2F).clientTrackingRange(8), Adherent::registerAttributes);
	public static final DeferredHolder<EntityType<?>, EntityType<AlphaYeti>> ALPHA_YETI = registerWithEgg("alpha_yeti", EntityType.Builder.of(AlphaYeti::new, MobCategory.MONSTER).sized(3.8F, 5.0F).clientTrackingRange(16), 0xCDCDCD, 0x29486E, AlphaYeti::registerAttributes, Monster::checkAnyLightMonsterSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<ArmoredGiant>> ARMORED_GIANT = registerWithEgg("armored_giant", EntityType.Builder.of(ArmoredGiant::new, MobCategory.MONSTER).sized(2.4F, 7.2F).clientTrackingRange(16), 0x239391, 0x9A9A9A, ArmoredGiant::registerAttributes, ArmoredGiant::checkGiantSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<Bighorn>> BIGHORN_SHEEP = registerWithEgg("bighorn_sheep", EntityType.Builder.of(Bighorn::new, MobCategory.CREATURE).sized(0.9F, 1.3F).clientTrackingRange(8), 0xDBCEAF, 0xD7C771, Bighorn::createAttributes, Animal::checkAnimalSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<BlockChainGoblin>> BLOCKCHAIN_GOBLIN = registerWithEgg("block_and_chain_goblin", EntityType.Builder.of(BlockChainGoblin::new, MobCategory.MONSTER).sized(0.9F, 1.4F).clientTrackingRange(8), 0xD3E7BC, 0x1F3FFF, BlockChainGoblin::registerAttributes, Monster::checkMonsterSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<Boar>> BOAR = registerWithEgg("boar", EntityType.Builder.of(Boar::new, MobCategory.CREATURE).sized(0.9F, 0.9F).clientTrackingRange(8), 0x83653B, 0xFFEFCA, Boar::registerAttributes, Animal::checkAnimalSpawnRules);
	//public static final DeferredHolder<EntityType<?>, EntityType<Boggard>> BOGGARD = registerWithAttributes("boggard", EntityType.Builder.of(Boggard::new, MobCategory.MONSTER).sized(0.8F, 1.1F), Boggard::registerAttributes);
	public static final DeferredHolder<EntityType<?>, EntityType<TowerBroodling>> CARMINITE_BROODLING = registerWithEgg("carminite_broodling", EntityType.Builder.of(TowerBroodling::new, MobCategory.MONSTER).sized(0.7F, 0.5F).clientTrackingRange(8), 0x343C14, 0xBAEE02, TowerBroodling::registerAttributes, Monster::checkMonsterSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<CarminiteGhastguard>> CARMINITE_GHASTGUARD = registerWithEgg("carminite_ghastguard", EntityType.Builder.of(CarminiteGhastguard::new, MobCategory.MONSTER).sized(4.0F, 6.0F).clientTrackingRange(20).fireImmune(), 0xBCBCBC, 0xB77878, CarminiteGhastguard::registerAttributes, CarminiteGhastguard::checkGhastguardSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<CarminiteGhastling>> CARMINITE_GHASTLING = registerWithEgg("carminite_ghastling", EntityType.Builder.of(CarminiteGhastling::new, MobCategory.MONSTER).sized(1.1F, 1.5F).eyeHeight(0.5F).clientTrackingRange(10).fireImmune(), 0xBCBCBC, 0xA74343, CarminiteGhastling::registerAttributes, CarminiteGhastling::checkGhastlingSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<CarminiteGolem>> CARMINITE_GOLEM = registerWithEgg("carminite_golem", EntityType.Builder.of(CarminiteGolem::new, MobCategory.MONSTER).sized(1.4F, 2.9F).clientTrackingRange(8), 0x6B3D20, 0xE2DDDA, CarminiteGolem::registerAttributes, Monster::checkMonsterSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<ChainBlock>> CHAIN_BLOCK = registerMisc("chain_block", EntityType.Builder.<ChainBlock>of(ChainBlock::new, MobCategory.MISC).noLootTable().sized(0.6F, 0.6F).noSummon().clientTrackingRange(8).setUpdateInterval(1).fireImmune());
	public static final DeferredHolder<EntityType<?>, EntityType<CharmEffect>> CHARM_EFFECT = registerMisc("charm_effect", EntityType.Builder.<CharmEffect>of(CharmEffect::new, MobCategory.MISC).noLootTable().sized(0.0F, 0.0F).clientTrackingRange(4).updateInterval(Integer.MAX_VALUE).noSave().noSummon().fireImmune());
	public static final DeferredHolder<EntityType<?>, EntityType<CubeOfAnnihilation>> CUBE_OF_ANNIHILATION = registerMisc("cube_of_annihilation", EntityType.Builder.<CubeOfAnnihilation>of(CubeOfAnnihilation::new, MobCategory.MISC).noLootTable().sized(1.0F, 1.0F).noSummon().clientTrackingRange(4).setUpdateInterval(20).fireImmune());
	public static final DeferredHolder<EntityType<?>, EntityType<DeathTome>> DEATH_TOME = registerWithEgg("death_tome", EntityType.Builder.of(DeathTome::new, MobCategory.MONSTER).sized(0.6F, 1.1F).clientTrackingRange(8), 0x774E22, 0xDBCDBE, DeathTome::registerAttributes, Monster::checkMonsterSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<Deer>> DEER = registerWithEgg("deer", EntityType.Builder.of(Deer::new, MobCategory.CREATURE).sized(0.7F, 1.8F).clientTrackingRange(8), 0x7B4D2E, 0x4B241D, Deer::registerAttributes, Animal::checkAnimalSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<DwarfRabbit>> DWARF_RABBIT = registerWithEgg("dwarf_rabbit", EntityType.Builder.of(DwarfRabbit::new, MobCategory.CREATURE).sized(0.4F, 0.4F).clientTrackingRange(8), 0xFEFEEE, 0xCCAA99, DwarfRabbit::registerAttributes, Animal::checkAnimalSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<FallingIce>> FALLING_ICE = registerMisc("falling_ice", EntityType.Builder.<FallingIce>of(FallingIce::new, MobCategory.MISC).noLootTable().sized(1.0F, 1.0F).clientTrackingRange(4).updateInterval(10).fireImmune());
	public static final DeferredHolder<EntityType<?>, EntityType<FireBeetle>> FIRE_BEETLE = registerWithEgg("fire_beetle", EntityType.Builder.of(FireBeetle::new, MobCategory.MONSTER).sized(1.1F, 0.5F).clientTrackingRange(8).fireImmune(), 0x1D0B00, 0xCB6F25, FireBeetle::registerAttributes, Monster::checkMonsterSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<GiantMiner>> GIANT_MINER = registerWithEgg("giant_miner", EntityType.Builder.of(GiantMiner::new, MobCategory.MONSTER).sized(2.4F, 7.2F).clientTrackingRange(16), 0x211B52, 0x9A9A9A, GiantMiner::registerAttributes, GiantMiner::checkGiantSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<HarbingerCube>> HARBINGER_CUBE = registerWithAttributes("harbinger_cube", EntityType.Builder.of(HarbingerCube::new, MobCategory.MONSTER).sized(1.9F, 2.4F).clientTrackingRange(8).fireImmune(), HarbingerCube::registerAttributes);
	public static final DeferredHolder<EntityType<?>, EntityType<HedgeSpider>> HEDGE_SPIDER = registerWithEgg("hedge_spider", EntityType.Builder.of(HedgeSpider::new, MobCategory.MONSTER).sized(1.4F, 0.9F), 0x235F13, 0x562653, HedgeSpider::createAttributes, HedgeSpider::checkSpiderSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<HelmetCrab>> HELMET_CRAB = registerWithEgg("helmet_crab", EntityType.Builder.of(HelmetCrab::new, MobCategory.MONSTER).sized(0.8F, 1.1F).eyeHeight(0.45F).clientTrackingRange(8).fireImmune(), 0xFB904B, 0xD3E7BC, HelmetCrab::registerAttributes, Monster::checkMonsterSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<HostileWolf>> HOSTILE_WOLF = registerWithEgg("hostile_wolf", EntityType.Builder.of(HostileWolf::new, MobCategory.MONSTER).sized(0.6F, 0.85F).clientTrackingRange(8), 0xD7D3D3, 0xAB1E14, HostileWolf::registerAttributes, HostileWolf::checkWolfSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<Hydra>> HYDRA = registerWithEgg("hydra", EntityType.Builder.of(Hydra::new, MobCategory.MONSTER).sized(16.0F, 12.0F).clientTrackingRange(20).fireImmune(), 0x142940, 0x29806B, Hydra::registerAttributes, Monster::checkAnyLightMonsterSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<HydraMortar>> HYDRA_MORTAR = registerMisc("hydra_mortar", EntityType.Builder.<HydraMortar>of(HydraMortar::new, MobCategory.MISC).noLootTable().sized(0.75F, 0.75F).clientTrackingRange(8).updateInterval(10).fireImmune());
	public static final DeferredHolder<EntityType<?>, EntityType<IceArrow>> ICE_ARROW = registerMisc("ice_arrow", EntityType.Builder.<IceArrow>of(IceArrow::new, MobCategory.MISC).noLootTable().sized(0.5F, 0.5F).eyeHeight(0.13F).clientTrackingRange(4).updateInterval(20));
	public static final DeferredHolder<EntityType<?>, EntityType<IceBomb>> THROWN_ICE = registerMisc("ice_bomb", EntityType.Builder.<IceBomb>of(IceBomb::new, MobCategory.MISC).noLootTable().sized(1.0F, 1.0F).clientTrackingRange(8).updateInterval(10).fireImmune());
	public static final DeferredHolder<EntityType<?>, EntityType<IceCrystal>> ICE_CRYSTAL = registerWithEgg("ice_crystal", EntityType.Builder.of(IceCrystal::new, MobCategory.MONSTER).sized(0.6F, 1.8F).clientTrackingRange(8), 0xDCE9FE, 0xADCAFB, IceCrystal::registerAttributes, Monster::checkMonsterSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<IceSnowball>> ICE_SNOWBALL = registerMisc("ice_snowball", EntityType.Builder.<IceSnowball>of(IceSnowball::new, MobCategory.MISC).noLootTable().sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10));
	public static final DeferredHolder<EntityType<?>, EntityType<KingSpider>> KING_SPIDER = registerWithEgg("king_spider", EntityType.Builder.of(KingSpider::new, MobCategory.MONSTER).sized(1.6F, 1.6F).clientTrackingRange(8), 0x2C1A0E, 0xFFC017, KingSpider::registerAttributes, Monster::checkMonsterSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<KnightPhantom>> KNIGHT_PHANTOM = registerWithEgg("knight_phantom", EntityType.Builder.of(KnightPhantom::new, MobCategory.MONSTER).sized(1.25F, 2.5F).clientTrackingRange(10), 0xA6673B, 0xD3E7BC, KnightPhantom::registerAttributes, Monster::checkAnyLightMonsterSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<Kobold>> KOBOLD = registerWithEgg("kobold", EntityType.Builder.of(Kobold::new, MobCategory.MONSTER).sized(0.8F, 1.1F).clientTrackingRange(8), 0x372096, 0x895D1B, Kobold::registerAttributes, Monster::checkMonsterSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<Lich>> LICH = registerWithEgg("lich", EntityType.Builder.<Lich>of(Lich::new, MobCategory.MONSTER).sized(1.1F, 2.1F), 0xACA489, 0x360472, Lich::registerAttributes, Monster::checkAnyLightMonsterSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<LichBolt>> LICH_BOLT = registerMisc("lich_bolt", EntityType.Builder.<LichBolt>of(LichBolt::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(8).updateInterval(10));
	public static final DeferredHolder<EntityType<?>, EntityType<LichBomb>> LICH_BOMB = registerMisc("lich_bomb", EntityType.Builder.<LichBomb>of(LichBomb::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(8).updateInterval(10));
	public static final DeferredHolder<EntityType<?>, EntityType<LichMinion>> LICH_MINION = registerWithAttributes("lich_minion", EntityType.Builder.<LichMinion>of(LichMinion::new, MobCategory.MONSTER).sized(0.6F, 1.95F).eyeHeight(1.74F).passengerAttachments(2.0125F).ridingOffset(-0.7F).clientTrackingRange(8), LichMinion::createAttributes);
	public static final DeferredHolder<EntityType<?>, EntityType<LowerGoblinKnight>> LOWER_GOBLIN_KNIGHT = registerWithEgg("lower_goblin_knight", EntityType.Builder.of(LowerGoblinKnight::new, MobCategory.MONSTER).sized(0.7F, 1.1F).clientTrackingRange(10), 0x566055, 0xD3E7BC, LowerGoblinKnight::registerAttributes, Monster::checkMonsterSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<LoyalZombie>> LOYAL_ZOMBIE = registerWithAttributes("loyal_zombie", EntityType.Builder.of(LoyalZombie::new, MobCategory.MONSTER).sized(0.6F, 1.95F).eyeHeight(1.74F).passengerAttachments(2.0125F).ridingOffset(-0.7F), LoyalZombie::registerAttributes);
	public static final DeferredHolder<EntityType<?>, EntityType<MazeSlime>> MAZE_SLIME = registerWithEgg("maze_slime", EntityType.Builder.of(MazeSlime::new, MobCategory.MONSTER).sized(0.52F, 0.52F).eyeHeight(0.325F).spawnDimensionsScale(4.0F).clientTrackingRange(10), 0xA3A3A3, 0x2A3B17, MazeSlime::registerAttributes, MazeSlime::checkSlimeSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<Minoshroom>> MINOSHROOM = registerWithEgg("minoshroom", EntityType.Builder.of(Minoshroom::new, MobCategory.MONSTER).sized(1.49F, 2.5F).clientTrackingRange(10), 0xA81012, 0xAA7D66, Minoshroom::registerAttributes, Monster::checkAnyLightMonsterSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<Minotaur>> MINOTAUR = registerWithEgg("minotaur", EntityType.Builder.of(Minotaur::new, MobCategory.MONSTER).sized(0.6F, 2.1F).clientTrackingRange(8), 0x3F3024, 0xAA7D66, Minotaur::registerAttributes, Monster::checkMonsterSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<MistWolf>> MIST_WOLF = registerWithEgg("mist_wolf", EntityType.Builder.of(MistWolf::new, MobCategory.MONSTER).sized(1.4F, 1.9F).clientTrackingRange(8), 0x3A1411, 0xE2C88A, MistWolf::registerAttributes, Monster::checkMonsterSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<MoonwormShot>> MOONWORM_SHOT = registerMisc("moonworm_shot", EntityType.Builder.<MoonwormShot>of(MoonwormShot::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10));
	public static final DeferredHolder<EntityType<?>, EntityType<MosquitoSwarm>> MOSQUITO_SWARM = registerWithEgg("mosquito_swarm", EntityType.Builder.of(MosquitoSwarm::new, MobCategory.MONSTER).sized(0.7F, 1.9F).clientTrackingRange(10), 0x080904, 0x2D2F21, MosquitoSwarm::registerAttributes, Monster::checkMonsterSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<Naga>> NAGA = registerWithEgg("naga", EntityType.Builder.of(Naga::new, MobCategory.MONSTER).sized(2.0F, 3.0F).eyeHeight(2.0F).clientTrackingRange(10), 0xa4d316, 0x1b380b, Naga::registerAttributes, Monster::checkAnyLightMonsterSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<NatureBolt>> NATURE_BOLT = registerMisc("nature_bolt", EntityType.Builder.<NatureBolt>of(NatureBolt::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10));
	public static final DeferredHolder<EntityType<?>, EntityType<MagicPainting>> MAGIC_PAINTING = registerMisc("magic_painting", EntityType.Builder.of(MagicPainting::new, MobCategory.MISC).noLootTable().noSummon().sized(0.5F, 0.5F).clientTrackingRange(10).updateInterval(Integer.MAX_VALUE));
	public static final DeferredHolder<EntityType<?>, EntityType<Penguin>> PENGUIN = registerWithEgg("penguin", EntityType.Builder.of(Penguin::new, MobCategory.CREATURE).sized(0.5F, 0.9F).clientTrackingRange(8), 0x12151b, 0xf9edd2, Penguin::registerAttributes, Penguin::checkPenguinSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<PinchBeetle>> PINCH_BEETLE = registerWithEgg("pinch_beetle", EntityType.Builder.of(PinchBeetle::new, MobCategory.MONSTER).sized(1.2F, 0.5F).clientTrackingRange(8), 0xbc9327, 0x241609, PinchBeetle::registerAttributes, Monster::checkMonsterSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<PlateauBoss>> PLATEAU_BOSS = registerWithAttributes("plateau_boss", EntityType.Builder.of(PlateauBoss::new, MobCategory.MONSTER).noLootTable().noSave().noSummon().sized(1.0F, 1.0F).fireImmune().clientTrackingRange(10), PlateauBoss::registerAttributes);
	public static final DeferredHolder<EntityType<?>, EntityType<ProtectionBox>> PROTECTION_BOX = registerMisc("protection_box", EntityType.Builder.<ProtectionBox>of(ProtectionBox::new, MobCategory.MISC).noSave().noSummon().sized(0.0F, 0.0F).clientTrackingRange(16).fireImmune());
	public static final DeferredHolder<EntityType<?>, EntityType<QuestRam>> QUEST_RAM = registerWithEgg("quest_ram", EntityType.Builder.of(QuestRam::new, MobCategory.CREATURE).sized(1.25F, 2.9F).clientTrackingRange(10), 0xFEFEEE, 0x33AADD, QuestRam::registerAttributes, Animal::checkAnimalSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<Raven>> RAVEN = registerWithEgg("raven", EntityType.Builder.of(Raven::new, MobCategory.CREATURE).sized(0.3F, 0.5F).clientTrackingRange(10), 0x000011, 0x222233, Raven::registerAttributes, Animal::checkAnimalSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<Redcap>> REDCAP = registerWithEgg("redcap", EntityType.Builder.of(Redcap::new, MobCategory.MONSTER).sized(0.9F, 1.4F).ridingOffset(-0.4F).clientTrackingRange(8), 0x3B3A6C, 0xAB1E14, Redcap::registerAttributes, Monster::checkMonsterSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<RedcapSapper>> REDCAP_SAPPER = registerWithEgg("redcap_sapper", EntityType.Builder.of(RedcapSapper::new, MobCategory.MONSTER).sized(0.9F, 1.4F).ridingOffset(-0.4F).clientTrackingRange(8), 0x575D21, 0xAB1E14, RedcapSapper::registerAttributes, Monster::checkMonsterSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<RisingZombie>> RISING_ZOMBIE = registerWithAttributes("rising_zombie", EntityType.Builder.of(RisingZombie::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8).noSummon(), RisingZombie::createAttributes);
	public static final DeferredHolder<EntityType<?>, EntityType<RovingCube>> ROVING_CUBE = registerWithAttributes("roving_cube", EntityType.Builder.of(RovingCube::new, MobCategory.MONSTER).sized(1.2F, 2.1F).noLootTable().noSummon().clientTrackingRange(8).fireImmune(), RovingCube::registerAttributes);
	public static final DeferredHolder<EntityType<?>, EntityType<SeekerArrow>> SEEKER_ARROW = registerMisc("seeker_arrow", EntityType.Builder.<SeekerArrow>of(SeekerArrow::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20));
	public static final DeferredHolder<EntityType<?>, EntityType<SkeletonDruid>> SKELETON_DRUID = registerWithEgg("skeleton_druid", EntityType.Builder.of(SkeletonDruid::new, MobCategory.MONSTER).sized(0.6F, 1.99F).ridingOffset(-0.7F).clientTrackingRange(10), 0xA3A3A3, 0x2A3B17, SkeletonDruid::createAttributes, SkeletonDruid::checkDruidSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<SlideBlock>> SLIDER = registerMisc("slider", EntityType.Builder.<SlideBlock>of(SlideBlock::new, MobCategory.MISC).sized(0.98F, 0.98F).noSummon().clientTrackingRange(8));
	public static final DeferredHolder<EntityType<?>, EntityType<SlimeBeetle>> SLIME_BEETLE = registerWithEgg("slime_beetle", EntityType.Builder.of(SlimeBeetle::new, MobCategory.MONSTER).sized(0.9F, 0.5F).clientTrackingRange(8), 0x0C1606, 0x60A74C, SlimeBeetle::registerAttributes, Monster::checkMonsterSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<SlimeProjectile>> SLIME_BLOB = registerMisc("slime_projectile", EntityType.Builder.<SlimeProjectile>of(SlimeProjectile::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10));
	public static final DeferredHolder<EntityType<?>, EntityType<SnowGuardian>> SNOW_GUARDIAN = registerWithEgg("snow_guardian", EntityType.Builder.of(SnowGuardian::new, MobCategory.MONSTER).sized(0.6F, 1.8F).clientTrackingRange(8), 0xD3E7BC, 0xFEFEFE, SnowGuardian::registerAttributes, Monster::checkMonsterSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<SnowQueen>> SNOW_QUEEN = registerWithEgg("snow_queen", EntityType.Builder.of(SnowQueen::new, MobCategory.MONSTER).sized(0.7F, 2.5F).clientTrackingRange(10), 0xB1B2D4, 0x87006E, SnowGuardian::registerAttributes, Monster::checkAnyLightMonsterSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<Squirrel>> SQUIRREL = registerWithEgg("squirrel", EntityType.Builder.of(Squirrel::new, MobCategory.CREATURE).sized(0.3F, 0.5F).clientTrackingRange(8), 0x904f12, 0xeeeeee, Squirrel::registerAttributes, Animal::checkAnimalSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<StableIceCore>> STABLE_ICE_CORE = registerWithEgg("stable_ice_core", EntityType.Builder.of(StableIceCore::new, MobCategory.MONSTER).sized(0.8F, 1.8F).eyeHeight(1.35F).clientTrackingRange(8), 0xa1bff3, 0x7000f8, StableIceCore::registerAttributes, Monster::checkMonsterSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<SwarmSpider>> SWARM_SPIDER = registerWithEgg("swarm_spider", EntityType.Builder.of(SwarmSpider::new, MobCategory.MONSTER).sized(0.8F, 0.4F).clientTrackingRange(8), 0x32022e, 0x17251e, SwarmSpider::registerAttributes, SwarmSpider::checkSwarmSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<ThrownBlock>> THROWN_BLOCK = registerMisc("thrown_block", EntityType.Builder.<ThrownBlock>of(ThrownBlock::new, MobCategory.MISC).sized(1.0F, 1.0F).clientTrackingRange(4).updateInterval(10).fireImmune());
	public static final DeferredHolder<EntityType<?>, EntityType<ThrownWep>> THROWN_WEP = registerMisc("thrown_weapon", EntityType.Builder.<ThrownWep>of(ThrownWep::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(10));
	public static final DeferredHolder<EntityType<?>, EntityType<TinyBird>> TINY_BIRD = registerWithEgg("tiny_bird", EntityType.Builder.of(TinyBird::new, MobCategory.CREATURE).sized(0.3F, 0.3F).clientTrackingRange(8), 0x33aadd, 0x1188ee, TinyBird::registerAttributes, Animal::checkAnimalSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<TomeBolt>> TOME_BOLT = registerMisc("tome_bolt", EntityType.Builder.<TomeBolt>of(TomeBolt::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10));
	public static final DeferredHolder<EntityType<?>, EntityType<TowerwoodBorer>> TOWERWOOD_BORER = registerWithEgg("towerwood_borer", EntityType.Builder.of(TowerwoodBorer::new, MobCategory.MONSTER).sized(0.4F, 0.3F).eyeHeight(0.13F).clientTrackingRange(8), 0x5d2b21, 0xaca03a, TowerwoodBorer::registerAttributes, Monster::checkMonsterSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<Troll>> TROLL = registerWithEgg("troll", EntityType.Builder.of(Troll::new, MobCategory.MONSTER).sized(1.4F, 2.4F).clientTrackingRange(8), 0x9ea98f, 0xb0948e, Troll::registerAttributes, Monster::checkMonsterSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<TwilightWandBolt>> WAND_BOLT = registerMisc("wand_bolt", EntityType.Builder.<TwilightWandBolt>of(TwilightWandBolt::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10));
	public static final DeferredHolder<EntityType<?>, EntityType<UnstableIceCore>> UNSTABLE_ICE_CORE = registerWithEgg("unstable_ice_core", EntityType.Builder.of(UnstableIceCore::new, MobCategory.MONSTER).sized(0.8F, 1.8F).eyeHeight(1.35F).clientTrackingRange(8), 0x9aacf5, 0x9b0fa5, UnstableIceCore::registerAttributes, Monster::checkMonsterSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<UpperGoblinKnight>> UPPER_GOBLIN_KNIGHT = registerWithPlacement("upper_goblin_knight", EntityType.Builder.of(UpperGoblinKnight::new, MobCategory.MONSTER).sized(1.1F, 1.3F).clientTrackingRange(8), UpperGoblinKnight::registerAttributes, Monster::checkMonsterSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<UrGhast>> UR_GHAST = registerWithEgg("ur_ghast", EntityType.Builder.of(UrGhast::new, MobCategory.MONSTER).sized(14.0F, 18.0F).clientTrackingRange(24).fireImmune(), 0xbcbcbc, 0xb77878, UrGhast::registerAttributes, Monster::checkAnyLightMonsterSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<WinterWolf>> WINTER_WOLF = registerWithEgg("winter_wolf", EntityType.Builder.of(WinterWolf::new, MobCategory.MONSTER).sized(1.4F, 1.9F).eyeHeight(1.45F).clientTrackingRange(8), 0xdfe3e5, 0xb2bcca, WinterWolf::registerAttributes, WinterWolf::checkWinterSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<Wraith>> WRAITH = registerWithEgg("wraith", EntityType.Builder.of(Wraith::new, MobCategory.MONSTER).sized(0.6F, 2.1F).clientTrackingRange(8).fireImmune(), 0x505050, 0x838383, Wraith::registerAttributes, Wraith::checkMonsterSpawnRules);
	public static final DeferredHolder<EntityType<?>, EntityType<Yeti>> YETI = registerWithEgg("yeti", EntityType.Builder.of(Yeti::new, MobCategory.MONSTER).sized(1.4F, 2.4F).clientTrackingRange(8), 0xdedede, 0x4675bb, Yeti::registerAttributes, Yeti::checkYetiSpawnRules);

	public static final DeferredHolder<EntityType<?>, EntityType<Boat>> TWILIGHT_OAK_BOAT = registerMisc("twilight_oak_boat", EntityType.Builder.<Boat>of((type, level) -> new Boat(type, level, TFItems.TWILIGHT_OAK_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<ChestBoat>> TWILIGHT_OAK_CHEST_BOAT = registerMisc("twilight_oak_chest_boat", EntityType.Builder.<ChestBoat>of((type, level) -> new ChestBoat(type, level, TFItems.TWILIGHT_OAK_CHEST_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<Boat>> CANOPY_BOAT = registerMisc("canopy_boat", EntityType.Builder.<Boat>of((type, level) -> new Boat(type, level, TFItems.CANOPY_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<ChestBoat>> CANOPY_CHEST_BOAT = registerMisc("canopy_chest_boat", EntityType.Builder.<ChestBoat>of((type, level) -> new ChestBoat(type, level, TFItems.CANOPY_CHEST_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<Boat>> MANGROVE_BOAT = registerMisc("mangrove_boat", EntityType.Builder.<Boat>of((type, level) -> new Boat(type, level, TFItems.MANGROVE_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<ChestBoat>> MANGROVE_CHEST_BOAT = registerMisc("mangrove_chest_boat", EntityType.Builder.<ChestBoat>of((type, level) -> new ChestBoat(type, level, TFItems.MANGROVE_CHEST_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<Boat>> DARK_BOAT = registerMisc("dark_boat", EntityType.Builder.<Boat>of((type, level) -> new Boat(type, level, TFItems.DARK_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<ChestBoat>> DARK_CHEST_BOAT = registerMisc("dark_chest_boat", EntityType.Builder.<ChestBoat>of((type, level) -> new ChestBoat(type, level, TFItems.DARK_CHEST_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<Boat>> TIME_BOAT = registerMisc("time_boat", EntityType.Builder.<Boat>of((type, level) -> new Boat(type, level, TFItems.TIME_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<ChestBoat>> TIME_CHEST_BOAT = registerMisc("time_chest_boat", EntityType.Builder.<ChestBoat>of((type, level) -> new ChestBoat(type, level, TFItems.TIME_CHEST_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<Boat>> TRANSFORMATION_BOAT = registerMisc("transformation_boat", EntityType.Builder.<Boat>of((type, level) -> new Boat(type, level, TFItems.TRANSFORMATION_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<ChestBoat>> TRANSFORMATION_CHEST_BOAT = registerMisc("transformation_chest_boat", EntityType.Builder.<ChestBoat>of((type, level) -> new ChestBoat(type, level, TFItems.TRANSFORMATION_CHEST_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<Boat>> MINING_BOAT = registerMisc("mining_boat", EntityType.Builder.<Boat>of((type, level) -> new Boat(type, level, TFItems.MINING_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<ChestBoat>> MINING_CHEST_BOAT = registerMisc("mining_chest_boat", EntityType.Builder.<ChestBoat>of((type, level) -> new ChestBoat(type, level, TFItems.MINING_CHEST_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<Boat>> SORTING_BOAT = registerMisc("sorting_boat", EntityType.Builder.<Boat>of((type, level) -> new Boat(type, level, TFItems.SORTING_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<ChestBoat>> SORTING_CHEST_BOAT = registerMisc("sorting_chest_boat", EntityType.Builder.<ChestBoat>of((type, level) -> new ChestBoat(type, level, TFItems.SORTING_CHEST_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));

	public static <E extends Entity> DeferredHolder<EntityType<?>, EntityType<E>> registerMisc(String name, EntityType.Builder<E> builder) {
		return ENTITY_TYPES.register(name, () -> builder.build(createIDFor(name)));
	}

	public static <E extends LivingEntity> DeferredHolder<EntityType<?>, EntityType<E>> registerWithAttributes(String name, EntityType.Builder<E> builder, Supplier<AttributeSupplier.Builder> attributes) {
		DeferredHolder<EntityType<?>, EntityType<E>> ret = ENTITY_TYPES.register(name, () -> builder.build(createIDFor(name)));
		ATTRIBUTES.put(ret, attributes);
		return ret;
	}

	public static <E extends LivingEntity> DeferredHolder<EntityType<?>, EntityType<E>> registerWithPlacement(String name, EntityType.Builder<E> builder, Supplier<AttributeSupplier.Builder> attributes, @Nullable SpawnPlacements.SpawnPredicate<E> predicate) {
		DeferredHolder<EntityType<?>, EntityType<E>> ret = ENTITY_TYPES.register(name, () -> builder.build(createIDFor(name)));
		ATTRIBUTES.put(ret, attributes);
		if (predicate != null) {
			SPAWN_PREDICATES.put(ret, predicate);
		}
		return ret;
	}

	public static <E extends Mob> DeferredHolder<EntityType<?>, EntityType<E>> registerWithEgg(String name, EntityType.Builder<E> builder, int primaryColor, int secondaryColor, Supplier<AttributeSupplier.Builder> attributes, @Nullable SpawnPlacements.SpawnPredicate<E> predicate) {
		DeferredHolder<EntityType<?>, EntityType<E>> ret = ENTITY_TYPES.register(name, () -> builder.build(createIDFor(name)));
		SPAWN_EGGS.register(name + "_spawn_egg", () -> new DeferredSpawnEggItem(ret, primaryColor, secondaryColor, new Item.Properties()));
		ATTRIBUTES.put(ret, attributes);
		if (predicate != null) {
			SPAWN_PREDICATES.put(ret, predicate);
		}
		return ret;
	}

	private static ResourceKey<EntityType<?>> createIDFor(String name) {
		return ResourceKey.create(Registries.ENTITY_TYPE, TwilightForestMod.prefix(name));
	}
}
