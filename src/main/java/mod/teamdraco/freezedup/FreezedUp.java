package mod.teamdraco.freezedup;

import mod.teamdraco.freezedup.entity.ChillooEntity;
import mod.teamdraco.freezedup.init.FreezedUpEntities;
import mod.teamdraco.freezedup.init.FreezedUpItems;
import mod.teamdraco.freezedup.init.FreezedUpSounds;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.FeatureSpreadConfig;
import net.minecraft.world.gen.feature.Features;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FreezedUp.MOD_ID)
@Mod.EventBusSubscriber(modid = FreezedUp.MOD_ID)
public class FreezedUp {
    public static final String MOD_ID = "freezedup";

    public FreezedUp() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::registerCommon);

        FreezedUpItems.REGISTER.register(bus);
        FreezedUpSounds.REGISTER.register(bus);
        FreezedUpEntities.REGISTER.register(bus);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerBiomes(BiomeLoadingEvent event) {
        Biome.Climate climate = event.getClimate();
        switch (event.getCategory()) {
            case ICY:
                float temperature = climate.temperature;
                if (climate.temperature <= 0.0f) {
                    event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(FreezedUpEntities.CHILLOO.get(), 5, 2, 4));
                }
                break;
        }
    }

    private void registerCommon(FMLCommonSetupEvent event) {
        registerEntityAttributes();
        EntitySpawnPlacementRegistry.register(FreezedUpEntities.CHILLOO.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
    }

    private void registerEntityAttributes() {
        GlobalEntityTypeAttributes.put(FreezedUpEntities.CHILLOO.get(), ChillooEntity.createAttributes().create());
    }

    public final static ItemGroup GROUP = new ItemGroup("freezedup_item_group") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(FreezedUpItems.CHILLOO_FEATHER.get());
        }
    };
}
