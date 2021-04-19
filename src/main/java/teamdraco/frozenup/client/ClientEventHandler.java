package teamdraco.frozenup.client;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import teamdraco.frozenup.FrozenUp;
import teamdraco.frozenup.client.renderer.ChillooRenderer;
import teamdraco.frozenup.init.FrozenUpBlocks;
import teamdraco.frozenup.init.FrozenUpEntities;
import teamdraco.frozenup.item.FrozenUpSpawnEggItem;
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
@Mod.EventBusSubscriber(modid = FrozenUp.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventHandler {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(FrozenUpEntities.CHILLOO.get(), ChillooRenderer::new);
        RenderTypeLookup.setRenderLayer(FrozenUpBlocks.EMPTY_MUG.get(), RenderType.getCutout());
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void itemColors(ColorHandlerEvent.Item event) {
        ItemColors handler = event.getItemColors();
        IItemColor eggColor = (stack, tintIndex) -> ((FrozenUpSpawnEggItem) stack.getItem()).getColor(tintIndex);
        for (FrozenUpSpawnEggItem e : FrozenUpSpawnEggItem.UNADDED_EGGS) handler.register(eggColor, e);
    }
}
