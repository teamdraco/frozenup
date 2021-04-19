package teamdraco.frozenup.init;

import java.util.function.ToIntFunction;

import teamdraco.frozenup.FrozenUp;
import teamdraco.frozenup.block.FeatherLampBlock;
import teamdraco.frozenup.block.CarpetBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CakeBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamdraco.frozenup.block.MugBlock;

public class FrozenUpBlocks {
	public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, FrozenUp.MOD_ID);

	public static final RegistryObject<Block> CHILLOO_FEATHER_BLOCK = REGISTER.register("chilloo_feather_block", () -> new Block(AbstractBlock.Properties.create(Material.ORGANIC).sound(SoundType.SNOW).hardnessAndResistance(0.1f)));
	public static final RegistryObject<Block> CHILLOO_FEATHER_BLOCK_CARPET = REGISTER.register("chilloo_feather_block_carpet", () -> new CarpetBlock(AbstractBlock.Properties.create(Material.ORGANIC).sound(SoundType.SNOW).hardnessAndResistance(0.1f)));
	public static final RegistryObject<Block> TRUFFLE_CAKE = REGISTER.register("truffle_cake", () -> new CakeBlock(AbstractBlock.Properties.create(Material.CAKE).hardnessAndResistance(0.5F).sound(SoundType.CLOTH)));
	public static final RegistryObject<Block> CHILLOO_FEATHER_LAMP = REGISTER.register("chilloo_feather_lamp", () -> new FeatherLampBlock(AbstractBlock.Properties.create(Material.SNOW_BLOCK).setLightLevel(getLightValueLit(10)).sound(SoundType.SNOW).hardnessAndResistance(0.3F)));
	public static final RegistryObject<Block> EMPTY_MUG = REGISTER.register("empty_mug", () -> new MugBlock(AbstractBlock.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.5f).notSolid()));
	public static final RegistryObject<Block> MUG_OF_MILK = REGISTER.register("mug_of_milk", () -> new MugBlock(AbstractBlock.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.5f).notSolid()));
	public static final RegistryObject<Block> MUG_OF_CHOCOLATE_MILK = REGISTER.register("mug_of_chocolate_milk", () -> new MugBlock(AbstractBlock.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.5f).notSolid()));
	public static final RegistryObject<Block> MUG_OF_TRUFFLE_HOT_CHOCOLATE = REGISTER.register("mug_of_truffle_hot_chocolate", () -> new MugBlock(AbstractBlock.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.5f).notSolid()));

	private static ToIntFunction<BlockState> getLightValueLit(int lightValue) {
		return (state) -> {
			return state.get(BlockStateProperties.LIT) ? lightValue : 0;
		};
	}
}
