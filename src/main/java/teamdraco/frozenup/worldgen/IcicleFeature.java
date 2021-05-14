package teamdraco.frozenup.worldgen;

import net.minecraft.block.*;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

import static net.minecraft.world.gen.feature.NoFeatureConfig.field_236558_a_;

public class IcicleFeature extends Feature<NoFeatureConfig>
{
    public IcicleFeature()
    {
        super(field_236558_a_);
    }

    //NOTE all random values below have 1 added to them when randomizing, the values determine the maximum possible output, not number of outputs

    public static int minimumIcicleHeight = 3; //minimum height of the center icicle
    public static int extraIcicleHeight = 1; //maximum possible randomized increase in height

    public static int minimumSideIcicleHeight = 1; //minimum height of side icicles
    public static int extraSideIcicleHeight = 1; //maximum possible randomized increase in height

    public static BlockState iceBlock()
    {
        return Blocks.PACKED_ICE.getDefaultState();
    }
    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config)
    {
        if (reader.isAirBlock(pos.down()))
        {
            return false;
        }

        WorldgenFiller filler = new WorldgenFiller();

        int height = minimumIcicleHeight + rand.nextInt(extraIcicleHeight + 1);
        Direction[] directions = new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST};

        for (int i = 0; i <= height; i++) //trunk placement
        {
            BlockPos icePos = pos.up(i);
            if (canPlace(reader, icePos))
            {
                filler.entries.add(new WorldgenFiller.BlockStateEntry(iceBlock(), icePos));
            }
            else
            {
                return false;
            }
        }
        for (Direction direction : directions) //side trunk placement
        {
            int sideHeight = minimumSideIcicleHeight + rand.nextInt(extraSideIcicleHeight + 1);
            for (int i = 0; i < sideHeight; i++)
            {
                BlockPos sideIcePos = pos.offset(direction).up(i);
                if (canPlace(reader, sideIcePos))
                {
                    filler.entries.add(new WorldgenFiller.BlockStateEntry(iceBlock(), sideIcePos));
                }
                else
                {
                    return false;
                }
            }
            downwardsIce(reader, filler, pos.offset(direction));
        }
        filler.fill(reader, true);
        return true;
    }

    public static void downwardsIce(ISeedReader reader, WorldgenFiller filler, BlockPos pos)
    {
        int i = 0;
        do
        {
            i++;
            BlockPos icePos = pos.down(i);
            if (canPlace(reader, icePos))
            {
                filler.entries.add(new WorldgenFiller.BlockStateEntry(iceBlock(), icePos));
            }
            else
            {
                break;
            }
            if (i > reader.getHeight())
            {
                break;
            }
        }
        while (true);
    }

    public static boolean canPlace(ISeedReader reader, BlockPos pos)
    {
        if (World.isOutsideBuildHeight(pos))
        {
            return false;
        }
        BlockState state = reader.getBlockState(pos);
        return reader.isAirBlock(pos) || state.getMaterial().isReplaceable();
    }
}