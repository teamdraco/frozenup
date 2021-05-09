package teamdraco.frozenup.util;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;

public class EffectUtil {
    private static final ItemStack MILK = Items.MILK_BUCKET.getDefaultInstance();

    public static boolean removeEffects(LivingEntity livingEntity, Predicate<EffectInstance> removalPredicate) {
        if (livingEntity.getEntityWorld().isRemote()) {
            return false;
        }
        boolean removed = false;
        Set<Effect> toRemove = new HashSet<>();
        for (EffectInstance instance : livingEntity.getActivePotionEffects()) {
            if (removalPredicate.test(instance)) {
                toRemove.add(instance.getPotion());
            }
        }
        for (Effect effect : toRemove) {
            if (livingEntity.removePotionEffect(effect)) {
                removed = true;
            }
        }
        return removed;
    }

    public static boolean removeEffects(LivingEntity livingEntity, ItemStack stack, Predicate<EffectInstance> removalPredicate) {
        return removeEffects(livingEntity, instance -> instance.isCurativeItem(stack) && removalPredicate.test(instance));
    }

    public static boolean removeEffectsAsMilk(LivingEntity livingEntity, Predicate<EffectInstance> removalPredicate) {
        return removeEffects(livingEntity, MILK, removalPredicate);
    }

    public static boolean removeEffectsAsMilk(LivingEntity livingEntity) {
        return removeEffectsAsMilk(livingEntity, instance -> true);
    }
}
