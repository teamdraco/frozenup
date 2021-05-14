package teamdraco.frozenup.worldgen;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.World;
import teamdraco.frozenup.FrozenUpHelper;

import java.util.ArrayList;

public class WorldgenFiller
{
    public ArrayList<BlockStateEntry> entries = new ArrayList<>();

    public WorldgenFiller()
    {

    }

    public void fill(ISeedReader reader, boolean safetyCheck)
    {
        for (BlockStateEntry entry : entries)
        {
            if (safetyCheck && !entry.canPlace(reader))
            {
                continue;
            }
            reader.setBlockState(entry.pos, entry.state, 3);
            entry.additionalPlacement(reader);
            if (reader instanceof World)
            {
                FrozenUpHelper.updateState((World) reader, entry.pos);
            }
        }
    }

    public static class BlockStateEntry
    {
        public final BlockState state;
        public final BlockPos pos;

        public BlockStateEntry(BlockState state, BlockPos pos)
        {
            this.state = state;

            this.pos = pos;
        }

        public boolean canPlace(ISeedReader reader)
        {
            return canPlace(reader, pos);
        }

        public boolean canPlace(ISeedReader reader, BlockPos pos)
        {
            if (World.isOutsideBuildHeight(pos))
            {
                return false;
            }
            BlockState state = reader.getBlockState(pos);
            return reader.isAirBlock(pos) || state.getMaterial().isReplaceable();
        }

        public boolean canPlace(ISeedReader reader, BlockPos pos, Block block)
        {
            if (World.isOutsideBuildHeight(pos))
            {
                return false;
            }
            BlockState state = reader.getBlockState(pos);
            return state.getBlock().equals(block) || reader.isAirBlock(pos) || state.getMaterial().isReplaceable();
        }

        public void additionalPlacement(ISeedReader reader)
        {

        }
    }
}