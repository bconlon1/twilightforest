package twilightforest.item;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;
import twilightforest.entity.projectile.IceBomb;
import twilightforest.init.TFEntities;
import twilightforest.init.TFSounds;

public class IceBombItem extends Item implements ProjectileItem {

	public IceBombItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		player.playSound(TFSounds.ICE_BOMB_FIRED.get(), 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));

		if (level instanceof ServerLevel serverlevel) {
			Projectile.spawnProjectileFromRotation((lev, entity, stacc) -> new IceBomb(TFEntities.THROWN_ICE.get(), level, player), serverlevel, stack, player, 0.0F, 1.5F, 1.0F);
		}

		player.awardStat(Stats.ITEM_USED.get(this));
		stack.consume(1, player);

		return InteractionResult.SUCCESS;
	}

	@Override
	public Projectile asProjectile(Level level, Position position, ItemStack stack, Direction direction) {
		return new IceBomb(level, position);
	}
}