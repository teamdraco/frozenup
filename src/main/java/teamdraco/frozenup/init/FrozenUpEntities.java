package teamdraco.frozenup.init;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamdraco.frozenup.FrozenUp;
import teamdraco.frozenup.entity.ChillooEntity;

public class FrozenUpEntities {
    public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITIES, FrozenUp.MOD_ID);

    public static final RegistryObject<EntityType<ChillooEntity>> CHILLOO = REGISTER.register("chilloo", () -> EntityType.Builder.create(ChillooEntity::new, EntityClassification.CREATURE).size(1.0f, 1.0f).build(new ResourceLocation(FrozenUp.MOD_ID, "chilloo").toString()));
}
