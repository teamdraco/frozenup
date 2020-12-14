package mod.teamdraco.freezedup.init;

import mod.teamdraco.freezedup.FreezedUp;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class FreezedUpBlocks {
    public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, FreezedUp.MOD_ID);

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
        RegistryObject<T> object = REGISTER.register(name, block);
        FreezedUpItems.REGISTER.register(name, () -> new BlockItem(object.get(), new Item.Properties().group(ItemGroup.REDSTONE)));
        return object;
    }
}
