package mod.teamdraco.frozenup.init;

import mod.teamdraco.frozenup.FrozenUp;
import mod.teamdraco.frozenup.features.FrostFlowerFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpreadConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FrozenUpFeatures {
    public static final DeferredRegister<Feature<?>> REGISTER = DeferredRegister.create(ForgeRegistries.FEATURES, FrozenUp.MOD_ID);

    public static final RegistryObject<Feature<FeatureSpreadConfig>> FROST_FLOWER = REGISTER.register("frost_flower", () -> new FrostFlowerFeature(FeatureSpreadConfig.CODEC));
}
