package teamdraco.frozenup.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.FoodStats;
import net.minecraft.world.World;
import teamdraco.frozenup.block.MugBlock;

public class ChocolateMilkMugItem extends MilkMugItem {
    public ChocolateMilkMugItem(MugBlock block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void applyEffects(ItemStack stack, World world, LivingEntity livingEntity) {
        super.applyEffects(stack, world, livingEntity);
        if (!world.isRemote()) {
            livingEntity.addPotionEffect(new EffectInstance(Effects.REGENERATION, 200, 0));
            livingEntity.addPotionEffect(new EffectInstance(Effects.SPEED, 600, 1));
        }
        if (livingEntity instanceof PlayerEntity) {
            FoodStats foodStats = ((PlayerEntity) livingEntity).getFoodStats();
            foodStats.addStats(3, 3);
        }
    }
}
