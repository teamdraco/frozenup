package teamdraco.frozenup.item;

import net.minecraft.item.BlockItem;
import teamdraco.frozenup.init.FrozenUpBlocks;
import teamdraco.frozenup.init.FrozenUpItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DrinkHelper;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class HotChocolateMugItem extends BlockItem {
	public HotChocolateMugItem(Item.Properties builder) {
		super(FrozenUpBlocks.MUG_OF_TRUFFLE_HOT_CHOCOLATE.get(), builder);
	}

	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.BLINDNESS);
		if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.HUNGER);
		if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.MINING_FATIGUE);
		if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.NAUSEA);
		if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.POISON);
		if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.SLOWNESS);
		if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.UNLUCK);
		if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.WEAKNESS);
		if (!worldIn.isRemote) entityLiving.removePotionEffect(Effects.WITHER);

		if (!worldIn.isRemote) entityLiving.addPotionEffect(new EffectInstance(Effects.REGENERATION, 500, 0));

		if (entityLiving instanceof ServerPlayerEntity) {
			ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)entityLiving;
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
			serverplayerentity.addStat(Stats.ITEM_USED.get(this));
		}

		if (entityLiving instanceof PlayerEntity) {
			int newFoodLevel = ((PlayerEntity)entityLiving).getFoodStats().getFoodLevel() + 8;
			((PlayerEntity) entityLiving).getFoodStats().setFoodLevel(newFoodLevel);;  
		}

		if (entityLiving instanceof PlayerEntity && !((PlayerEntity)entityLiving).abilities.isCreativeMode) {
			stack.shrink(1);
		}

		return stack.isEmpty() ? new ItemStack(FrozenUpItems.EMPTY_MUG.get()) : stack;
	}

	public int getUseDuration(ItemStack stack) {
		return 32;
	}

	public UseAction getUseAction(ItemStack stack) {
		return UseAction.DRINK;
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		return DrinkHelper.startDrinking(worldIn, playerIn, handIn);
	}

	@Override
	public net.minecraftforge.common.capabilities.ICapabilityProvider initCapabilities(ItemStack stack, @javax.annotation.Nullable net.minecraft.nbt.CompoundNBT nbt) {
		return new net.minecraftforge.fluids.capability.wrappers.FluidBucketWrapper(stack);
	}
}
