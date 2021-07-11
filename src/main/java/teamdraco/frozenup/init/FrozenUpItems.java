package teamdraco.frozenup.init;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamdraco.frozenup.FrozenUp;
import teamdraco.frozenup.item.*;

@SuppressWarnings("unused")
public class FrozenUpItems {
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, FrozenUp.MOD_ID);

    public static final RegistryObject<Item> FROZEN_TRUFFLE = REGISTER.register("frozen_truffle", () -> new Item(new Item.Properties().group(FrozenUp.GROUP)));
    public static final RegistryObject<Item> TRUFFLE = REGISTER.register("truffle", () -> new Item(new Item.Properties().group(FrozenUp.GROUP).food(new Food.Builder().hunger(6).saturation(0.5f).build())));
    public static final RegistryObject<Item> TRUFFLE_MUFFIN = REGISTER.register("truffle_muffin", () -> new Item(new Item.Properties().group(FrozenUp.GROUP).food(new Food.Builder().hunger(4).saturation(0.4f).fastToEat().build())));
    public static final RegistryObject<Item> CHILLOO_FEATHER = REGISTER.register("chilloo_feather", () -> new Item(new Item.Properties().group(FrozenUp.GROUP)));

    public static final RegistryObject<Item> TEST_ITEM = REGISTER.register("test_item", () -> new TestItem(new Item.Properties().group(FrozenUp.GROUP).maxStackSize(1)));

    /*
     * BLOCK ITEMS
     */

    public static final RegistryObject<Item> EMPTY_MUG = REGISTER.register("empty_mug", () -> new BlockItem(FrozenUpBlocks.EMPTY_MUG.get(), new Item.Properties().group(FrozenUp.GROUP).maxStackSize(16)));
    public static final RegistryObject<Item> MUG_OF_MILK = REGISTER.register("mug_of_milk", () -> new MilkMugItem(FrozenUpBlocks.MUG_OF_MILK.get(), new Item.Properties().group(FrozenUp.GROUP).maxStackSize(1)));
    public static final RegistryObject<Item> MUG_OF_CHOCOLATE_MILK = REGISTER.register("mug_of_chocolate_milk", () -> new ChocolateMilkMugItem(FrozenUpBlocks.MUG_OF_CHOCOLATE_MILK.get(), new Item.Properties().food(new Food.Builder().hunger(3).saturation(3.0f).build()).group(FrozenUp.GROUP).maxStackSize(1)));
    public static final RegistryObject<Item> MUG_OF_TRUFFLE_HOT_CHOCOLATE = REGISTER.register("mug_of_truffle_hot_chocolate", () -> new TruffleHotChocolateMugItem(FrozenUpBlocks.MUG_OF_TRUFFLE_HOT_CHOCOLATE.get(), new Item.Properties().food(new Food.Builder().hunger(8).saturation(8.0f).effect(() -> new EffectInstance(Effects.REGENERATION, 500, 0), 1.0f).build()).group(FrozenUp.GROUP).maxStackSize(1)));

    public static final RegistryObject<Item> CHILLOO_FEATHER_BLOCK = REGISTER.register("chilloo_feather_block", () -> new BlockItem(FrozenUpBlocks.CHILLOO_FEATHER_BLOCK.get(), new Item.Properties().group(FrozenUp.GROUP)));
    public static final RegistryObject<Item> CHILLOO_FEATHER_BLOCK_CARPET = REGISTER.register("chilloo_feather_block_carpet", () -> new BlockItem(FrozenUpBlocks.CHILLOO_FEATHER_BLOCK_CARPET.get(), new Item.Properties().group(FrozenUp.GROUP)));
    public static final RegistryObject<Item> TRUFFLE_CAKE = REGISTER.register("truffle_cake", () -> new BlockItem(FrozenUpBlocks.TRUFFLE_CAKE.get(), new Item.Properties().group(FrozenUp.GROUP).maxStackSize(1)));
    public static final RegistryObject<Item> CHILLOO_FEATHER_LAMP = REGISTER.register("chilloo_feather_lamp", () -> new BlockItem(FrozenUpBlocks.CHILLOO_FEATHER_LAMP.get(), new Item.Properties().group(FrozenUp.GROUP)));

    public static final RegistryObject<Item> CHILLOO_SPAWN_EGG = REGISTER.register("chilloo_spawn_egg", () -> new FrozenUpSpawnEggItem(FrozenUpEntities.CHILLOO, 0xc2cbce, 0x32383c, new Item.Properties().group(FrozenUp.GROUP)));
}
