package teamdraco.frozenup.common;

import teamdraco.frozenup.FrozenUp;
import teamdraco.frozenup.init.FrozenUpItems;
import net.minecraft.block.ComposterBlock;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = FrozenUp.MOD_ID, bus = Bus.FORGE)
public class CommonEvents {
	
	public static void setup() {
		registerCompostable(0.3F, FrozenUpItems.TRUFFLE.get());
		registerCompostable(0.3F, FrozenUpItems.TRUFFLE_MUFFIN.get());
		registerCompostable(0.5F, FrozenUpItems.TRUFFLE_CAKE.get());
	}
	
	public static void registerCompostable(float chance, IItemProvider item) {
		ComposterBlock.CHANCES.put(item.asItem(), chance);
	}

}
