package mod.teamdraco.freezedup.client;

import mod.teamdraco.freezedup.FreezedUp;
import mod.teamdraco.freezedup.client.renderer.ChillooRenderer;
import mod.teamdraco.freezedup.init.FreezedUpEntities;
import mod.teamdraco.freezedup.item.FreezedUpSpawnEggItem;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = FreezedUp.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventHandler {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(FreezedUpEntities.CHILLOO.get(), ChillooRenderer::new);
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void itemColors(ColorHandlerEvent.Item event) {
        ItemColors handler = event.getItemColors();
        IItemColor eggColor = (stack, tintIndex) -> ((FreezedUpSpawnEggItem) stack.getItem()).getColor(tintIndex);
        for (FreezedUpSpawnEggItem e : FreezedUpSpawnEggItem.UNADDED_EGGS) handler.register(eggColor, e);
    }
}
