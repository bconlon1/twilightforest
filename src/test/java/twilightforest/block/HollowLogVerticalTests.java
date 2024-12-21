package twilightforest.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import twilightforest.enums.HollowLogVariants;
import twilightforest.init.TFBlocks;
import twilightforest.junit.MockitoFixer;
import twilightforest.util.DirectionUtil;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoFixer.class)
public class HollowLogVerticalTests {

	@Mock
	private DirectionUtil directionUtil;

	@InjectMocks
	private VerticalHollowLogBlock instance;

	@BeforeEach
	public void setup() {
		instance = TFBlocks.HOLLOW_ACACIA_LOG_VERTICAL.value();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void useItemOnNotInside() {
		ItemStack stack = mock(ItemStack.class);
		BlockState state = mock(BlockState.class);
		Level level = mock(Level.class);
		Player player = mock(Player.class);
		BlockHitResult hitResult = mock(BlockHitResult.class);

		when(hitResult.getLocation()).thenReturn(Vec3.ZERO);

		InteractionResult result = instance.useItemOn(stack, state, level, BlockPos.ZERO, player, InteractionHand.MAIN_HAND, hitResult);

		assertSame(InteractionResult.TRY_WITH_EMPTY_HAND, result);
		verify(stack, never()).is(any(Item.class));
	}

	@Test
	public void useItemOnInside() {
		ItemStack stack = mock(ItemStack.class);
		BlockState state = mock(BlockState.class);
		Level level = mock(Level.class);
		Player player = mock(Player.class);
		BlockHitResult hitResult = mock(BlockHitResult.class);

		when(hitResult.getLocation()).thenReturn(new Vec3(0.5, 0.5, 0.5));
		when(stack.is(any(Item.class))).thenReturn(false);

		InteractionResult result = instance.useItemOn(stack, state, level, BlockPos.ZERO, player, InteractionHand.MAIN_HAND, hitResult);

		assertSame(InteractionResult.TRY_WITH_EMPTY_HAND, result);
		verify(stack, times(1)).is(Blocks.VINE.asItem());
		verify(stack, times(1)).is(Blocks.LADDER.asItem());
	}

	@Test
	public void useItemOnVine() {
		ItemStack stack = mock(ItemStack.class);
		BlockState state = mock(BlockState.class);
		Level level = mock(Level.class);
		Player player = mock(Player.class);
		BlockHitResult hitResult = mock(BlockHitResult.class);

		when(hitResult.getLocation()).thenReturn(new Vec3(0.5, 0.5, 0.5));
		when(stack.is(Blocks.VINE.asItem())).thenReturn(true);
		when(stack.is(Blocks.LADDER.asItem())).thenReturn(false);

		when(hitResult.getDirection()).thenReturn(Direction.NORTH);
		when(player.getDirection()).thenReturn(Direction.NORTH);
		when(directionUtil.horizontalOrElse(any(Direction.class), any(Direction.class))).thenReturn(Direction.NORTH);
		when(level.isClientSide()).thenReturn(false);

		InteractionResult result = instance.useItemOn(stack, state, level, BlockPos.ZERO, player, InteractionHand.MAIN_HAND, hitResult);

		assertSame(InteractionResult.CONSUME, result);

		verify(stack, times(1)).is(Blocks.VINE.asItem());
		ArgumentCaptor<BlockState> climbable = ArgumentCaptor.captor();
		verify(level, times(1)).setBlock(eq(BlockPos.ZERO), climbable.capture(), eq(3));
		assertSame(HollowLogVariants.Climbable.VINE, climbable.getValue().getValue(ClimbableHollowLogBlock.VARIANT));
		assertSame(Direction.NORTH, climbable.getValue().getValue(ClimbableHollowLogBlock.FACING));
		verify(level, times(1)).playSound(null, BlockPos.ZERO, SoundEvents.VINE_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
		verify(stack, times(1)).consume(1, player);

		verify(stack, never()).is(Blocks.LADDER.asItem());
	}

	@Test
	public void useItemOnLadder() {
		ItemStack stack = mock(ItemStack.class);
		BlockState state = mock(BlockState.class);
		Level level = mock(Level.class);
		Player player = mock(Player.class);
		BlockHitResult hitResult = mock(BlockHitResult.class);

		when(hitResult.getLocation()).thenReturn(new Vec3(0.5, 0.5, 0.5));
		when(stack.is(Blocks.VINE.asItem())).thenReturn(false);
		when(stack.is(Blocks.LADDER.asItem())).thenReturn(true);

		when(state.getValue(VerticalHollowLogBlock.WATERLOGGED)).thenReturn(false);
		when(hitResult.getDirection()).thenReturn(Direction.NORTH);
		when(player.getDirection()).thenReturn(Direction.NORTH);
		when(directionUtil.horizontalOrElse(any(Direction.class), any(Direction.class))).thenReturn(Direction.NORTH);
		when(level.isClientSide()).thenReturn(false);

		InteractionResult result = instance.useItemOn(stack, state, level, BlockPos.ZERO, player, InteractionHand.MAIN_HAND, hitResult);

		assertSame(InteractionResult.CONSUME, result);

		verify(stack, times(1)).is(Blocks.VINE.asItem());
		verify(stack, times(1)).is(Blocks.LADDER.asItem());
		ArgumentCaptor<BlockState> climbable = ArgumentCaptor.captor();
		verify(level, times(1)).setBlock(eq(BlockPos.ZERO), climbable.capture(), eq(3));
		assertSame(HollowLogVariants.Climbable.LADDER, climbable.getValue().getValue(ClimbableHollowLogBlock.VARIANT));
		assertSame(Direction.NORTH, climbable.getValue().getValue(ClimbableHollowLogBlock.FACING));
		verify(level, times(1)).playSound(null, BlockPos.ZERO, SoundEvents.LADDER_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
		verify(stack, times(1)).consume(1, player);
	}

	@Test
	public void useItemOnLadderWaterlogged() {
		ItemStack stack = mock(ItemStack.class);
		BlockState state = mock(BlockState.class);
		Level level = mock(Level.class);
		Player player = mock(Player.class);
		BlockHitResult hitResult = mock(BlockHitResult.class);

		when(hitResult.getLocation()).thenReturn(new Vec3(0.5, 0.5, 0.5));
		when(stack.is(Blocks.VINE.asItem())).thenReturn(false);
		when(stack.is(Blocks.LADDER.asItem())).thenReturn(true);

		when(state.getValue(VerticalHollowLogBlock.WATERLOGGED)).thenReturn(true);
		when(hitResult.getDirection()).thenReturn(Direction.NORTH);
		when(player.getDirection()).thenReturn(Direction.NORTH);
		when(directionUtil.horizontalOrElse(any(Direction.class), any(Direction.class))).thenReturn(Direction.NORTH);
		when(level.isClientSide()).thenReturn(false);

		InteractionResult result = instance.useItemOn(stack, state, level, BlockPos.ZERO, player, InteractionHand.MAIN_HAND, hitResult);

		assertSame(InteractionResult.CONSUME, result);

		verify(stack, times(1)).is(Blocks.VINE.asItem());
		verify(stack, times(1)).is(Blocks.LADDER.asItem());
		ArgumentCaptor<BlockState> climbable = ArgumentCaptor.captor();
		verify(level, times(1)).setBlock(eq(BlockPos.ZERO), climbable.capture(), eq(3));
		assertSame(HollowLogVariants.Climbable.LADDER_WATERLOGGED, climbable.getValue().getValue(ClimbableHollowLogBlock.VARIANT));
		assertSame(Direction.NORTH, climbable.getValue().getValue(ClimbableHollowLogBlock.FACING));
		verify(level, times(1)).playSound(null, BlockPos.ZERO, SoundEvents.LADDER_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
		verify(stack, times(1)).consume(1, player);
	}

}
