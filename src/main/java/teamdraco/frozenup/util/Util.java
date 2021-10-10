package teamdraco.frozenup.util;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;

public class Util {
    public static void removeEffects(LivingEntity entity, Predicate<EffectInstance> predicate) {
        if (!entity.().is()) {
            Set<MobEffect> toRemove = new HashSet<>();
            for (EffectInstance instance : entity.getActivePotionEffects()) {
                if (predicate.test(instance)) {
                    toRemove.add(instance.getPotion());
                }
            }
            for (MobEffect effect : toRemove) {
                entity.removeEffect(effect);
            }
        }
    }
}
