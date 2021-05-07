package teamdraco.frozenup.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraft.util.FoodStats;
import net.minecraft.world.World;
import teamdraco.frozenup.block.MugBlock;
import teamdraco.frozenup.util.EffectUtil;

public class HotChocolateMugItem extends AbstractDrinkableMugItem {
    public HotChocolateMugItem(MugBlock block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void applyEffects(ItemStack stack, World world, LivingEntity livingEntity) {
        EffectUtil.removeEffectsAsMilk(livingEntity, instance -> instance.getPotion().getEffectType() == EffectType.HARMFUL);
        if (!world.isRemote()) {
            livingEntity.addPotionEffect(new EffectInstance(Effects.REGENERATION, 500, 0));
        }
        if (livingEntity instanceof PlayerEntity) {
            FoodStats foodStats = ((PlayerEntity) livingEntity).getFoodStats();
            foodStats.addStats(8, 8);
        }
    }
}
