package mod.teamdraco.frozenup.init;

import mod.teamdraco.frozenup.FrozenUp;
import mod.teamdraco.frozenup.item.ChocolateMilkItem;
import mod.teamdraco.frozenup.item.FrozenUpSpawnEggItem;
import mod.teamdraco.frozenup.item.HotChocolateItem;
import mod.teamdraco.frozenup.item.MilkMugItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FrozenUpItems {
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, FrozenUp.MOD_ID);

    public static final RegistryObject<Item> FROZEN_TRUFFLE = REGISTER.register("frozen_truffle", () -> new Item(new Item.Properties().group(FrozenUp.GROUP)));
    public static final RegistryObject<Item> TRUFFLE = REGISTER.register("truffle", () -> new Item(new Item.Properties().group(FrozenUp.GROUP).food(new Food.Builder().hunger(6).saturation(0.5f).build())));
    public static final RegistryObject<Item> TRUFFLE_MUFFIN = REGISTER.register("truffle_muffin", () -> new Item(new Item.Properties().group(FrozenUp.GROUP).food(new Food.Builder().hunger(4).saturation(0.4f).fastToEat().build())));
    public static final RegistryObject<Item> CHILLOO_SPAWN_EGG = REGISTER.register("chilloo_spawn_egg", () -> new FrozenUpSpawnEggItem(FrozenUpEntities.CHILLOO, 0xbadada, 0x32383c, new Item.Properties().group(FrozenUp.GROUP)));
    public static final RegistryObject<Item> CHILLOO_FEATHER = REGISTER.register("chilloo_feather", () -> new Item(new Item.Properties().group(FrozenUp.GROUP)));
    public static final RegistryObject<Item> EMPTY_MUG = REGISTER.register("empty_mug", () -> new Item(new Item.Properties().group(FrozenUp.GROUP).maxStackSize(16)));
    public static final RegistryObject<Item> MUG_OF_MILK = REGISTER.register("mug_of_milk", () -> new MilkMugItem(new Item.Properties().group(FrozenUp.GROUP).maxStackSize(1)));
    public static final RegistryObject<Item> MUG_OF_CHOCOLATE_MILK = REGISTER.register("mug_of_chocolate_milk", () -> new ChocolateMilkItem(new Item.Properties().group(FrozenUp.GROUP).maxStackSize(1)));
    public static final RegistryObject<Item> MUG_OF_TRUFFLE_HOT_CHOCOLATE = REGISTER.register("mug_of_truffle_hot_chocolate", () -> new HotChocolateItem(new Item.Properties().group(FrozenUp.GROUP).maxStackSize(1)));
    
    public static final RegistryObject<BlockItem> CHILLOO_FEATHER_BLOCK = REGISTER.register("chilloo_feather_block", () -> new BlockItem(FrozenUpBlocks.CHILLOO_FEATHER_BLOCK.get(), new Item.Properties().group(FrozenUp.GROUP)));
    public static final RegistryObject<BlockItem> CHILLOO_FEATHER_BLOCK_CARPET = REGISTER.register("chilloo_feather_block_carpet", () -> new BlockItem(FrozenUpBlocks.CHILLOO_FEATHER_BLOCK_CARPET.get(), new Item.Properties().group(FrozenUp.GROUP)));
    public static final RegistryObject<BlockItem> TRUFFLE_CAKE = REGISTER.register("truffle_cake", () -> new BlockItem(FrozenUpBlocks.TRUFFLE_CAKE.get(), new Item.Properties().group(FrozenUp.GROUP).maxStackSize(1)));
    public static final RegistryObject<BlockItem> CHILLOO_FEATHER_LAMP = REGISTER.register("chilloo_feather_lamp", () -> new BlockItem(FrozenUpBlocks.CHILLOO_FEATHER_LAMP.get(), new Item.Properties().group(FrozenUp.GROUP)));
    public static final RegistryObject<BlockItem> FROST_FLOWER = REGISTER.register("frost_flower", () -> new BlockItem(FrozenUpBlocks.FROST_FLOWER.get(), new Item.Properties().group(FrozenUp.GROUP)));
}
