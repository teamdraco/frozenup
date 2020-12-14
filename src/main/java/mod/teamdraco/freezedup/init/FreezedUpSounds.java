package mod.teamdraco.freezedup.init;

import mod.teamdraco.freezedup.FreezedUp;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FreezedUpSounds {
    public static final DeferredRegister<SoundEvent> REGISTER = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, FreezedUp.MOD_ID);

    public static final RegistryObject<SoundEvent> CHILLOO_AMBIENT = REGISTER.register("chilloo_ambient", () -> new SoundEvent(new ResourceLocation(FreezedUp.MOD_ID, "chilloo.ambient")));
    public static final RegistryObject<SoundEvent> CHILLOO_HURT = REGISTER.register("chilloo_hurt", () -> new SoundEvent(new ResourceLocation(FreezedUp.MOD_ID, "chilloo.hurt")));
    public static final RegistryObject<SoundEvent> CHILLOO_DEATH = REGISTER.register("chilloo_death", () -> new SoundEvent(new ResourceLocation(FreezedUp.MOD_ID, "chilloo.death")));

}
