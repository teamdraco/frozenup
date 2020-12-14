package mod.teamdraco.freezedup.init;

import mod.teamdraco.freezedup.FreezedUp;
import mod.teamdraco.freezedup.entity.ChillooEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FreezedUpEntities {
    public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITIES, FreezedUp.MOD_ID);

    public static final RegistryObject<EntityType<ChillooEntity>> CHILLOO = REGISTER.register("chilloo", () -> EntityType.Builder.<ChillooEntity>create(ChillooEntity::new, EntityClassification.CREATURE).size(1.0f, 1.0f).build(new ResourceLocation(FreezedUp.MOD_ID, "chilloo").toString()));
}