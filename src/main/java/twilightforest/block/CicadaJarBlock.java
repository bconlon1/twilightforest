package twilightforest.block;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import twilightforest.config.TFConfig;
import twilightforest.init.TFBlocks;
import twilightforest.init.TFItems;
import twilightforest.init.TFSounds;

public class CicadaJarBlock extends JarBlock {
	public CicadaJarBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult result) {
		if (player.isShiftKeyDown()) {
			if (level instanceof ServerLevel sl) {
				ItemEntity cicada = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(TFBlocks.CICADA));
				level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
				cicada.spawnAtLocation(sl, cicada.getItem());
				cicada.spawnAtLocation(sl, TFItems.MASON_JAR.get());
			}
			level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
			return InteractionResult.SUCCESS;
		}
		return super.useWithoutItem(state, level, pos, player, result);
	}

	@Override
	public Item getDefaultLid() {
		return TFBlocks.CANOPY_LOG.asItem();
	}

	@Override
	public void destroy(LevelAccessor accessor, BlockPos pos, BlockState state) {
		super.destroy(accessor, pos, state);
		if (accessor.isClientSide())
			Minecraft.getInstance().getSoundManager().stop(TFSounds.CICADA.get().location(), SoundSource.BLOCKS);
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
		double dx = pos.getX() + ((random.nextFloat() - random.nextFloat()) * 0.2F + 0.5F);
		double dy = pos.getY() + 0.4F + ((random.nextFloat() - random.nextFloat()) * 0.2F);
		double dz = pos.getZ() + ((random.nextFloat() - random.nextFloat()) * 0.2F + 0.5F);
		level.addParticle(ParticleTypes.NOTE, dx, dy, dz, 0, 0, 0);
		if (level.getRandom().nextInt(75) == 0 && !TFConfig.silentCicadas) {
			level.playLocalSound(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, TFSounds.CICADA.get(), SoundSource.BLOCKS, 1.0F, 1.0F, false);
		}
	}
}
