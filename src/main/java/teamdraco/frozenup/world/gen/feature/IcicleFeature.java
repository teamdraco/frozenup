package teamdraco.frozenup.world.gen.feature;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

public class IcicleFeature extends Feature<NoneFeatureConfiguration> {
    public IcicleFeature() {
        super(field_236558_a_);
    }

    //NOTE all random values below have 1 added to them when randomizing, the values determine the maximum possible output, not number of outputs

    public static int minimumIcicleHeight = 3; //minimum height of the center icicle
    public static int extraIcicleHeight = 1; //maximum possible randomized increase in height

    public static int minimumSideIcicleHeight = 1; //minimum height of side icicles
    public static int extraSideIcicleHeight = 1; //maximum possible randomized increase in height

    public static BlockState iceBlock() {
        return Blocks.PACKED_ICE.defaultBlockState();
    }

    @Override
    public boolean generate(LevelAccessor reader, ChunkGenerator generator, Random rand, BlockPos pos, NoneFeatureConfiguration config) {
        if (reader.isEmptyBlock(pos.below())) {
            return false;
        }

        WorldGenFiller filler = new WorldGenFiller();

        int height = minimumIcicleHeight + rand.nextInt(extraIcicleHeight + 1);
        Direction[] directions = new Direction[]{ Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST };

        for (int i = 0; i <= height; i++) //trunk placement
        {
            BlockPos icePos = pos.above(i);
            if (canPlace(reader, icePos)) {
                filler.entries.add(new WorldGenFiller.BlockStateEntry(iceBlock(), icePos));
            } else {
                return false;
            }
        }
        for (Direction direction : directions) //side trunk placement
        {
            int sideHeight = minimumSideIcicleHeight + rand.nextInt(extraSideIcicleHeight + 1);
            for (int i = 0; i < sideHeight; i++) {
                BlockPos sideIcePos = pos.offset(direction).up(i);
                if (canPlace(reader, sideIcePos)) {
                    filler.entries.add(new WorldGenFiller.BlockStateEntry(iceBlock(), sideIcePos));
                } else {
                    return false;
                }
            }
            downwardsIce(reader, filler, pos.offset(direction));
        }
        filler.fill(reader, true);
        return true;
    }

    public static void downwardsIce(LevelAccessor reader, WorldGenFiller filler, BlockPos pos) {
        int i = 0;
        do {
            i++;
            BlockPos icePos = pos.below(i);
            if (canPlace(reader, icePos)) {
                filler.entries.add(new WorldGenFiller.BlockStateEntry(iceBlock(), icePos));
            } else {
                break;
            }
            if (i > reader.getHeight()) {
                break;
            }
        } while (true);
    }

    public static boolean canPlace(LevelAccessor reader, BlockPos pos) {
        if (Level.isOutsideBuildHeight(pos)) {
            return false;
        }
        BlockState state = reader.getBlockState(pos);
        return reader.isEmptyBlock(pos) || state.getMaterial().isReplaceable();
    }
}
