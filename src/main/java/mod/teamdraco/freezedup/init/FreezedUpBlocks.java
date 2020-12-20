package mod.teamdraco.freezedup.init;

import mod.teamdraco.freezedup.FreezedUp;
import mod.teamdraco.freezedup.block.FreezedUpCarpetBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.CarpetBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class FreezedUpBlocks {
    public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, FreezedUp.MOD_ID);

    public static final RegistryObject<Block> CHILLOO_FEATHER_BLOCK = REGISTER.register("chilloo_feather_block", () -> new Block(AbstractBlock.Properties.create(Material.ORGANIC).sound(SoundType.SNOW).hardnessAndResistance(0.1f)));
    public static final RegistryObject<Block> CHILLOO_FEATHER_BLOCK_CARPET = REGISTER.register("chilloo_feather_block_carpet", () -> new FreezedUpCarpetBlock(AbstractBlock.Properties.create(Material.ORGANIC).sound(SoundType.SNOW).hardnessAndResistance(0.1f)));
}
