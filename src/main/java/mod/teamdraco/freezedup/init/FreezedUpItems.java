package mod.teamdraco.freezedup.init;

import mod.teamdraco.freezedup.FreezedUp;
import mod.teamdraco.freezedup.item.FreezedUpSpawnEggItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FreezedUpItems {
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, FreezedUp.MOD_ID);

    public static final RegistryObject<Item> FROZEN_TRUFFLE = REGISTER.register("frozen_truffle", () -> new Item(new Item.Properties().group(FreezedUp.GROUP)));
    public static final RegistryObject<Item> TRUFFLE = REGISTER.register("truffle", () -> new Item(new Item.Properties().group(FreezedUp.GROUP).food(new Food.Builder().hunger(6).saturation(0.5f).build())));
    public static final RegistryObject<Item> TRUFFLE_MUFFIN = REGISTER.register("truffle_muffin", () -> new Item(new Item.Properties().group(FreezedUp.GROUP).food(new Food.Builder().hunger(4).saturation(0.4f).fastToEat().build())));
    public static final RegistryObject<Item> CHILLOO_SPAWN_EGG = REGISTER.register("chilloo_spawn_egg", () -> new FreezedUpSpawnEggItem(FreezedUpEntities.CHILLOO, 0xbadada, 0x32383c, new Item.Properties().group(FreezedUp.GROUP)));
    public static final RegistryObject<Item> CHILLOO_FEATHER = REGISTER.register("chilloo_feather", () -> new Item(new Item.Properties().group(FreezedUp.GROUP)));

    private static Item register(String name, Item item) {
        REGISTER.register(name, () -> item);
        return item;
    }
}
