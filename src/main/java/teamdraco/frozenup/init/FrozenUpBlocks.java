package teamdraco.frozenup.init;

import java.util.function.ToIntFunction;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamdraco.frozenup.FrozenUp;
import teamdraco.frozenup.block.*;

public class FrozenUpBlocks {
    public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, FrozenUp.MOD_ID);

    public static final RegistryObject<Block> CHILLOO_FEATHER_BLOCK = REGISTER.register("chilloo_feather_block", () -> new Block(AbstractBlock.Properties.create(Material.ORGANIC).sound(SoundType.SNOW).hardnessAndResistance(0.1f).sound(FrozenUpSoundTypes.CHILLOO_FEATHER_BLOCK)));
    public static final RegistryObject<Block> CHILLOO_FEATHER_BLOCK_CARPET = REGISTER.register("chilloo_feather_block_carpet", () -> new CarpetBlock(DyeColor.WHITE, AbstractBlock.Properties.create(Material.ORGANIC).sound(SoundType.SNOW).hardnessAndResistance(0.1f).sound(FrozenUpSoundTypes.CHILLOO_FEATHER_BLOCK)));

    public static final RegistryObject<Block> TRUFFLE_CAKE = REGISTER.register("truffle_cake", () -> new CakeBlock(AbstractBlock.Properties.create(Material.CAKE).hardnessAndResistance(0.5F).sound(SoundType.CLOTH)));
    public static final RegistryObject<Block> CHILLOO_FEATHER_LAMP = REGISTER.register("chilloo_feather_lamp", () -> new FeatherLampBlock(AbstractBlock.Properties.create(Material.SNOW_BLOCK).setLightLevel(getLightValueLit(10)).sound(SoundType.SNOW).hardnessAndResistance(0.3F)));

    public static final RegistryObject<Block> EMPTY_MUG = REGISTER.register("empty_mug", () -> new MugBlock(AbstractBlock.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.5f).notSolid()));
    public static final RegistryObject<Block> MUG_OF_MILK = REGISTER.register("mug_of_milk", () -> new MugBlock(FrozenUpItems.MUG_OF_MILK, AbstractBlock.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.5f).notSolid()));
    public static final RegistryObject<Block> MUG_OF_CHOCOLATE_MILK = REGISTER.register("mug_of_chocolate_milk", () -> new MugBlock(FrozenUpItems.MUG_OF_CHOCOLATE_MILK, AbstractBlock.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.5f).notSolid()));
    public static final RegistryObject<Block> MUG_OF_TRUFFLE_HOT_CHOCOLATE = REGISTER.register("mug_of_truffle_hot_chocolate", () -> new MugBlock(FrozenUpItems.MUG_OF_TRUFFLE_HOT_CHOCOLATE, AbstractBlock.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.5f).notSolid()));

    private static ToIntFunction<BlockState> getLightValueLit(int lightValue) {
        return (state) -> state.get(BlockStateProperties.LIT) ? lightValue : 0;
    }
}
