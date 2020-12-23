package mod.teamdraco.frozenup.init;

import mod.teamdraco.frozenup.FrozenUp;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FrozenUpSounds {
    public static final DeferredRegister<SoundEvent> REGISTER = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, FrozenUp.MOD_ID);

    public static final RegistryObject<SoundEvent> CHILLOO_AMBIENT = REGISTER.register("chilloo_ambient", () -> new SoundEvent(new ResourceLocation(FrozenUp.MOD_ID, "chilloo.ambient")));
    public static final RegistryObject<SoundEvent> CHILLOO_HURT = REGISTER.register("chilloo_hurt", () -> new SoundEvent(new ResourceLocation(FrozenUp.MOD_ID, "chilloo.hurt")));
    public static final RegistryObject<SoundEvent> CHILLOO_DEATH = REGISTER.register("chilloo_death", () -> new SoundEvent(new ResourceLocation(FrozenUp.MOD_ID, "chilloo.death")));
}
