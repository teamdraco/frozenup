package teamdraco.frozenup.worldgen;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

import static net.minecraft.world.gen.feature.NoFeatureConfig.field_236558_a_;

public class IceTreeFeature extends Feature<NoFeatureConfig>
{
    public IceTreeFeature()
    {
        super(field_236558_a_);
    }

    //NOTE all random values below have 1 added to them when randomizing, the values determine the maximum possible output, not number of outputs

    public static int minimumTrunkHeight = 5; //minimum height of the tree trunk
    public static int extraTrunkHeight = 1; //maximum possible randomized increase in height

    public static BlockState logBlock()
    {
        return Blocks.OAK_LOG.getDefaultState();
    }
    public static BlockState leavesBlock()
    {
        return Blocks.OAK_LEAVES.getDefaultState();
    }
    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config)
    {
        if (reader.isAirBlock(pos.down()))
        {
            return false;
        }

        WorldgenFiller trunkFiller = new WorldgenFiller();
        WorldgenFiller leavesFiller = new WorldgenFiller();

        boolean higherStart = rand.nextBoolean();
        int extraHeight = rand.nextInt(extraTrunkHeight+1);
        int height = minimumTrunkHeight + extraHeight + (higherStart ? 1 : 0);

        Direction[] directions = new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST};

        for (int i = 0; i <= height; i++) //trunk placement
        {
            BlockPos trunkPos = pos.up(i);
            if (canPlace(reader, trunkPos))
            {
                trunkFiller.entries.add(new WorldgenFiller.BlockStateEntry(logBlock(), trunkPos));
            }
            else
            {
                return false;
            }
        }
        int leavesEnd = (higherStart?2:1)+extraHeight;
        for (int i = height+4; i > leavesEnd; i--)
        {
            int size = height+4 - i;
            int finalSize = (size+2)/4;
            boolean noMoreCORNERS = size % 4 >= 2;
            BlockPos leavesCenterPos = pos.up(i);
            makeLeafSlice(leavesFiller, leavesCenterPos, finalSize, noMoreCORNERS);
            if (noMoreCORNERS && finalSize > 1 && size % 4 == 2)
            {
                for (Direction direction : directions)
                {
                    BlockPos leavesTipPos = leavesCenterPos.up(1).offset(direction,2);
                    leavesFiller.entries.add(new WorldgenFiller.BlockStateEntry(leavesBlock(), leavesTipPos));
                }
            }
        }
        makeLeafSlice(leavesFiller, pos.up(leavesEnd), 1, true);
        trunkFiller.fill(reader, true);
        leavesFiller.fill(reader, true);
        return true;
    }
    public static void makeLeafSlice(WorldgenFiller filler, BlockPos pos, int leavesSize, boolean removeCorners)
    {
        for (int x = -leavesSize; x <= leavesSize; x++)
        {
            for (int z = -leavesSize; z <= leavesSize; z++)
            {
                if (removeCorners)
                {
                    if (Math.abs(x) == leavesSize && Math.abs(z) == leavesSize)
                    {
                        continue;
                    }
                }
                BlockPos leavesPos = new BlockPos(pos).add(x, 0, z);
                filler.entries.add(new WorldgenFiller.BlockStateEntry(leavesBlock().with(LeavesBlock.DISTANCE, 1), leavesPos));
            }
        }
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