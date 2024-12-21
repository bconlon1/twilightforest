package twilightforest.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import twilightforest.init.TFDataAttachments;
import twilightforest.init.TFSounds;
import twilightforest.util.TFItemStackUtils;

import javax.annotation.Nonnull;
import java.util.List;

public class FortificationWandItem extends Item {

	public FortificationWandItem(Properties properties) {
		super(properties);
	}

	@Nonnull
	@Override
	public InteractionResult use(Level level, Player player, @Nonnull InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);

		if (stack.getDamageValue() == stack.getMaxDamage() && !player.getAbilities().instabuild) {
			return InteractionResult.FAIL;
		}

		if (!level.isClientSide()) {
			player.getData(TFDataAttachments.FORTIFICATION_SHIELDS).setShields(player, 5, true);
			if(!player.getAbilities().instabuild) {
				TFItemStackUtils.hurtButDontBreak(stack, 1, (ServerLevel) level, player);
			}
		}
		player.playSound(TFSounds.SHIELD_ADD.get(), 1.0F, (player.getRandom().nextFloat() - player.getRandom().nextFloat()) * 0.2F + 1.0F);

		if (!player.isCreative())
			player.getCooldowns().addCooldown(stack, 1200);

		return InteractionResult.SUCCESS;
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return false;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flags) {
		super.appendHoverText(stack, context, tooltip, flags);
		tooltip.add(Component.translatable("item.twilightforest.scepter.desc", stack.getMaxDamage() - stack.getDamageValue()).withStyle(ChatFormatting.GRAY));
	}
}