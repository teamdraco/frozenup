package mod.teamdraco.frozenup.block;

import mod.teamdraco.frozenup.FrozenUp;
import mod.teamdraco.frozenup.init.FrozenUpBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod.EventBusSubscriber(modid = FrozenUp.MOD_ID, bus = Bus.MOD)
public class FrozenUpRenderTypes {
	
	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
		if (FMLEnvironment.dist == Dist.CLIENT) {
			RenderType cutoutRenderType = RenderType.getCutout();
			RenderTypeLookup.setRenderLayer(FrozenUpBlocks.FROZEN_FLOWER.get(), cutoutRenderType);
			RenderTypeLookup.setRenderLayer(FrozenUpBlocks.POTTED_FROZEN_FLOWER.get(), cutoutRenderType);
		}
	}

}
