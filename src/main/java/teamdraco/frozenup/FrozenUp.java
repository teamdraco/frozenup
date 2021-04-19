package teamdraco.frozenup;

import teamdraco.frozenup.common.CommonEvents;
import teamdraco.frozenup.entity.ChillooEntity;
import teamdraco.frozenup.init.FrozenUpBlocks;
import teamdraco.frozenup.init.FrozenUpEntities;
import teamdraco.frozenup.init.FrozenUpItems;
import teamdraco.frozenup.init.FrozenUpSounds;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FrozenUp.MOD_ID)
@Mod.EventBusSubscriber(modid = FrozenUp.MOD_ID)
public class FrozenUp {
    public static final String MOD_ID = "frozenup";

    public FrozenUp() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::registerCommon);

        FrozenUpBlocks.REGISTER.register(bus);
        FrozenUpItems.REGISTER.register(bus);
        FrozenUpSounds.REGISTER.register(bus);
        FrozenUpEntities.REGISTER.register(bus);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerBiomes(BiomeLoadingEvent event) {
        Biome.Climate climate = event.getClimate();
        switch (event.getCategory()) {
            case ICY:
                if (climate.temperature <= 0.0f) {
                    event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(FrozenUpEntities.CHILLOO.get(), 1, 2, 3));
                }
                break;
        }
    }

    private void registerCommon(FMLCommonSetupEvent event) {
    	CommonEvents.setup();
        registerEntityAttributes();
        EntitySpawnPlacementRegistry.register(FrozenUpEntities.CHILLOO.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
    }

    private void registerEntityAttributes() {
        GlobalEntityTypeAttributes.put(FrozenUpEntities.CHILLOO.get(), ChillooEntity.createAttributes().create());
    }

    public final static ItemGroup GROUP = new ItemGroup("frozenup_item_group") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(FrozenUpItems.CHILLOO_FEATHER.get());
        }
    };
}
