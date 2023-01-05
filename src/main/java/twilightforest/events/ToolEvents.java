package twilightforest.events;

import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import twilightforest.TwilightForestMod;
import twilightforest.data.tags.BlockTagGenerator;
import twilightforest.init.TFItems;
import twilightforest.item.EnderBowItem;
import twilightforest.item.GiantItem;
import twilightforest.item.MazebreakerPickItem;
import twilightforest.item.MinotaurAxeItem;

import org.jetbrains.annotations.Nullable;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = TwilightForestMod.ID)
public class ToolEvents {

	private static final int KNIGHTMETAL_BONUS_DAMAGE = 2;
	private static final int MINOTAUR_AXE_BONUS_DAMAGE = 7;

	@SubscribeEvent
	public static void onEnderBowHit(ProjectileImpactEvent evt) {
		Projectile arrow = evt.getProjectile();
		if (arrow.getOwner() instanceof Player player
				&& evt.getRayTraceResult() instanceof EntityHitResult result
				&& result.getEntity() instanceof LivingEntity living
				&& arrow.getOwner() != result.getEntity()) {

			if (arrow.getPersistentData().contains(EnderBowItem.KEY)) {
				double sourceX = player.getX(), sourceY = player.getY(), sourceZ = player.getZ();
				float sourceYaw = player.getYRot(), sourcePitch = player.getXRot();
				@Nullable Entity playerVehicle = player.getVehicle();

				player.setYRot(living.getYRot());
				player.teleportTo(living.getX(), living.getY(), living.getZ());
				player.invulnerableTime = 40;
				player.getLevel().broadcastEntityEvent(player, (byte) 46);
				if (living.isPassenger() && living.getVehicle() != null) {
					player.startRiding(living.getVehicle(), true);
					living.stopRiding();
				}
				player.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);

				living.setYRot(sourceYaw);
				living.setXRot(sourcePitch);
				living.teleportTo(sourceX, sourceY, sourceZ);
				living.getLevel().broadcastEntityEvent(player, (byte) 46);
				if (playerVehicle != null) {
					living.startRiding(playerVehicle, true);
					player.stopRiding();
				}
				living.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
			}
		}
	}

	@SubscribeEvent
	public static void fieryToolSetFire(LivingAttackEvent event) {
		if (event.getSource().getEntity() instanceof LivingEntity living && (living.getMainHandItem().is(TFItems.FIERY_SWORD.get()) || living.getMainHandItem().is(TFItems.FIERY_PICKAXE.get())) && !event.getEntity().fireImmune()) {
			event.getEntity().setSecondsOnFire(1);
		}
	}

	@SubscribeEvent
	public static void onKnightmetalToolDamage(LivingHurtEvent event) {
		LivingEntity target = event.getEntity();

		if (!target.getLevel().isClientSide() && event.getSource().getDirectEntity() instanceof LivingEntity living) {
			ItemStack weapon = living.getMainHandItem();

			if (!weapon.isEmpty()) {
				if (target.getArmorValue() > 0 && (weapon.is(TFItems.KNIGHTMETAL_PICKAXE.get()) || weapon.is(TFItems.KNIGHTMETAL_SWORD.get()))) {
					if (target.getArmorCoverPercentage() > 0) {
						int moreBonus = (int) (KNIGHTMETAL_BONUS_DAMAGE * target.getArmorCoverPercentage());
						event.setAmount(event.getAmount() + moreBonus);
					} else {
						event.setAmount(event.getAmount() + KNIGHTMETAL_BONUS_DAMAGE);
					}
					// enchantment attack sparkles
					((ServerLevel) target.getLevel()).getChunkSource().broadcastAndSend(target, new ClientboundAnimatePacket(target, 5));
				} else if (target.getArmorValue() == 0 && weapon.is(TFItems.KNIGHTMETAL_AXE.get())) {
					event.setAmount(event.getAmount() + KNIGHTMETAL_BONUS_DAMAGE);
					// enchantment attack sparkles
					((ServerLevel) target.getLevel()).getChunkSource().broadcastAndSend(target, new ClientboundAnimatePacket(target, 5));
				}
			}
		}
	}

	@SubscribeEvent
	public static void onMinotaurAxeCharge(LivingHurtEvent event) {
		LivingEntity target = event.getEntity();
		Entity source = event.getSource().getDirectEntity();
		if (!target.getLevel().isClientSide() && source instanceof LivingEntity living && source.isSprinting() && (event.getSource().getMsgId().equals("player") || event.getSource().getMsgId().equals("mob"))) {
			ItemStack weapon = living.getMainHandItem();
			if (!weapon.isEmpty() && weapon.getItem() instanceof MinotaurAxeItem) {
				event.setAmount(event.getAmount() + MINOTAUR_AXE_BONUS_DAMAGE);
				// enchantment attack sparkles
				((ServerLevel) target.getLevel()).getChunkSource().broadcastAndSend(target, new ClientboundAnimatePacket(target, 5));
			}
		}
	}


	@SubscribeEvent
	public static void damageToolsExtra(BlockEvent.BreakEvent event) {
		ItemStack stack = event.getPlayer().getMainHandItem();
		if (event.getState().is(BlockTagGenerator.MAZESTONE) || event.getState().is(BlockTagGenerator.CASTLE_BLOCKS)) {
			if (stack.isDamageableItem() && !(stack.getItem() instanceof MazebreakerPickItem)) {
				stack.hurtAndBreak(16, event.getPlayer(), (user) -> user.broadcastBreakEvent(InteractionHand.MAIN_HAND));
			}
		}
	}

	@SubscribeEvent
	public static void onEntityInteract(PlayerInteractEvent event) {
		if (event instanceof PlayerInteractEvent.EntityInteractSpecific entityInteractSpecific) {
			checkEntityTooFar(entityInteractSpecific, entityInteractSpecific.getTarget(), entityInteractSpecific.getEntity(), entityInteractSpecific.getHand());
		} else if (event instanceof PlayerInteractEvent.EntityInteract entityInteract) {
			checkEntityTooFar(entityInteract, entityInteract.getTarget(), entityInteract.getEntity(), entityInteract.getHand());
		} else if (event instanceof PlayerInteractEvent.RightClickBlock rightClickBlock) {
			checkBlockTooFar(event, rightClickBlock.getEntity(), rightClickBlock.getHand());
		} else if (event instanceof PlayerInteractEvent.RightClickItem rightClickItem) {
			checkInteractionTooFar(event, rightClickItem.getEntity(), rightClickItem.getHand());
		}
	}

	private static void checkEntityTooFar(PlayerInteractEvent event, Entity target, Player player, InteractionHand hand) {
		if (!event.isCanceled()) {
			ItemStack heldStack = player.getItemInHand(hand);
			if (hasGiantItemInOneHand(player) && !(heldStack.getItem() instanceof GiantItem) && hand == InteractionHand.OFF_HAND) {
				UUID uuidForOppositeHand = GiantItem.GIANT_RANGE_MODIFIER;
				AttributeInstance attackRange = player.getAttribute(ForgeMod.ATTACK_RANGE.get());
				if (attackRange != null) {
					AttributeModifier giantModifier = attackRange.getModifier(uuidForOppositeHand);
					if (giantModifier != null) {
						double totalRange = player.getAttackRange();
						double giantRange = giantModifier.getAmount();
						double baseRange = totalRange - giantRange;
						event.setCanceled(!player.isCloseEnough(target, baseRange));
					}
				}
			}
		}
	}

	private static void checkBlockTooFar(PlayerInteractEvent event, Player player, InteractionHand hand) {
		if (!event.isCanceled()) {
			ItemStack heldStack = player.getItemInHand(hand);
			if (hasGiantItemInOneHand(player) && !(heldStack.getItem() instanceof GiantItem) && hand == InteractionHand.OFF_HAND) {
				UUID uuidForOppositeHand = GiantItem.GIANT_REACH_MODIFIER;
				AttributeInstance reachDistance = player.getAttribute(ForgeMod.REACH_DISTANCE.get());
				if (reachDistance != null) {
					AttributeModifier giantModifier = reachDistance.getModifier(uuidForOppositeHand);
					if (giantModifier != null) {
						double totalReach = player.getReachDistance();
						double giantReach = giantModifier.getAmount();
						double baseReach = totalReach - giantReach;
						event.setCanceled(player.pick(baseReach, 0.0F, false).getType() != HitResult.Type.BLOCK);
					}
				}
			}
		}
	}

	private static void checkInteractionTooFar(PlayerInteractEvent event, Player player, InteractionHand hand) {
		if (!event.isCanceled()) {
			if (!event.isCanceled()) {
				ItemStack heldStack = player.getItemInHand(hand);
				if (hasGiantItemInOneHand(player) && !(heldStack.getItem() instanceof GiantItem) && hand == InteractionHand.OFF_HAND) {
					UUID uuidForOppositeHand = GiantItem.GIANT_REACH_MODIFIER;
					AttributeInstance reachDistance = player.getAttribute(ForgeMod.REACH_DISTANCE.get());
					if (reachDistance != null) {
						AttributeModifier giantModifier = reachDistance.getModifier(uuidForOppositeHand);
						if (giantModifier != null) {
							double totalReach = player.getReachDistance();
							double giantReach = giantModifier.getAmount();
							double baseReach = totalReach - giantReach;
							if (player.pick(totalReach, 0.0F, true).getType() == HitResult.Type.BLOCK) {
								event.setCanceled(getPlayerPOVHitResult(player.getLevel(), player, baseReach, ClipContext.Fluid.ANY).getType() != HitResult.Type.BLOCK);
							} else if (player.pick(totalReach, 0.0F, false).getType() == HitResult.Type.BLOCK) {
								event.setCanceled(getPlayerPOVHitResult(player.getLevel(), player, baseReach, ClipContext.Fluid.NONE).getType() != HitResult.Type.BLOCK);
							}
						}
					}
				}
			}
		}
	}

	private static boolean hasGiantItemInOneHand(Player player) {
		ItemStack mainHandStack = player.getMainHandItem();
		ItemStack offHandStack = player.getOffhandItem();
		return (mainHandStack.getItem() instanceof GiantItem && !(offHandStack.getItem() instanceof GiantItem));
	}

	private static BlockHitResult getPlayerPOVHitResult(Level level, Player player, double reach, ClipContext.Fluid fluidClip) {
		float f = player.getXRot();
		float f1 = player.getYRot();
		Vec3 vec3 = player.getEyePosition();
		float f2 = Mth.cos(-f1 * ((float) Math.PI / 180.0F) - (float) Math.PI);
		float f3 = Mth.sin(-f1 * ((float) Math.PI / 180.0F) - (float) Math.PI);
		float f4 = -Mth.cos(-f * ((float) Math.PI / 180.0F));
		float f5 = Mth.sin(-f * ((float) Math.PI / 180.0F));
		float f6 = f3 * f4;
		float f7 = f2 * f4;
		Vec3 vec31 = vec3.add((double) f6 * reach, (double) f5 * reach, (double) f7 * reach);
		return level.clip(new ClipContext(vec3, vec31, ClipContext.Block.OUTLINE, fluidClip, player));
	}
}
