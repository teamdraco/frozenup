package teamdraco.frozenup.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamdraco.frozenup.FrozenUp;

public class FrozenUpSoundEvents {
    public static final DeferredRegister<SoundEvent> REGISTER = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, FrozenUp.MOD_ID);

    public static final SoundEvent ENTITY_CHILLOO_AMBIENT = bouncer("ambient");
    public static final SoundEvent ENTITY_CHILLOO_DEATH = bouncer("death");
    public static final SoundEvent ENTITY_CHILLOO_HURT = bouncer("hurt");
    private static SoundEvent bouncer(String type) {
        return entity("chilloo", type);
    }

    public static final SoundEvent BLOCK_CHILLOO_FEATHER_BLOCK_BREAK = chillooFeatherBlock("break");
    public static final SoundEvent BLOCK_CHILLOO_FEATHER_BLOCK_PLACE = chillooFeatherBlock("place");
    private static SoundEvent chillooFeatherBlock(String type) {
        return block("chilloo_feather_block", type);
    }

    public static final SoundEvent BLOCK_CHILLOO_FEATHER_LAMP_TOGGLE_ON = chillooFeatherLamp("toggle_on");
    public static final SoundEvent BLOCK_CHILLOO_FEATHER_LAMP_TOGGLE_OFF = chillooFeatherLamp("toggle_off");
    private static SoundEvent chillooFeatherLamp(String type) {
        return block("chilloo_feather_lamp", type);
    }

    private static SoundEvent block(String block, String type) {
        return register("block." + block + "." + type);
    }
    private static SoundEvent entity(String entity, String type) {
        return register("entity." + entity + "." + type);
    }
    private static SoundEvent ambient(String id, String type) {
        return register("ambient." + type + "." + id);
    }

    private static SoundEvent register(String name) {
        ResourceLocation id = new ResourceLocation(FrozenUp.MOD_ID, name);
        SoundEvent event = new SoundEvent(id);
        REGISTER.register(name, () -> event);
        return event;
    }
}
