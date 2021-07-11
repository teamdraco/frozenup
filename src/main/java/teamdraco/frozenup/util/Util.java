package teamdraco.frozenup.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class Util {
    public static void removeEffects(LivingEntity entity, Predicate<EffectInstance> predicate) {
        if (!entity.getEntityWorld().isRemote()) {
            Set<Effect> toRemove = new HashSet<>();
            for (EffectInstance instance : entity.getActivePotionEffects()) {
                if (predicate.test(instance)) {
                    toRemove.add(instance.getPotion());
                }
            }
            for (Effect effect : toRemove) {
                entity.removePotionEffect(effect);
            }
        }
    }
}
