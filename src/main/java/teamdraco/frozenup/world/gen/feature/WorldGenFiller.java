package teamdraco.frozenup.world.gen.feature;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.World;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class WorldGenFiller {
    public ArrayList<BlockStateEntry> entries = new ArrayList<>();

    public WorldGenFiller() {

    }

    public void fill(ISeedReader reader, boolean safetyCheck) {
        for (BlockStateEntry entry : entries) {
            if (safetyCheck && !entry.canPlace(reader)) {
                continue;
            }
            reader.setBlockState(entry.pos, entry.state, 3);
            entry.additionalPlacement(reader);
            if (reader instanceof World) {
                BlockState state = reader.getBlockState(entry.pos);
                ((World) reader).notifyBlockUpdate(entry.pos, state, state, 2);
            }
        }
    }

    public static class BlockStateEntry {
        public final BlockState state;
        public final BlockPos pos;

        public BlockStateEntry(BlockState state, BlockPos pos) {
            this.state = state;

            this.pos = pos;
        }

        public boolean canPlace(ISeedReader reader) {
            return canPlace(reader, pos);
        }

        public boolean canPlace(ISeedReader reader, BlockPos pos) {
            if (World.isOutsideBuildHeight(pos)) {
                return false;
            }
            BlockState state = reader.getBlockState(pos);
            return reader.isAirBlock(pos) || state.getMaterial().isReplaceable();
        }

        public boolean canPlace(ISeedReader reader, BlockPos pos, Block block) {
            if (World.isOutsideBuildHeight(pos)) {
                return false;
            }
            BlockState state = reader.getBlockState(pos);
            return state.getBlock().equals(block) || reader.isAirBlock(pos) || state.getMaterial().isReplaceable();
        }

        @SuppressWarnings({ "EmptyMethod", "unused" })
        public void additionalPlacement(ISeedReader reader) {}
    }
}
