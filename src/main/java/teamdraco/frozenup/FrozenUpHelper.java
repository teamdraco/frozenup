package teamdraco.frozenup;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FrozenUpHelper
{
    public static void updateState(BlockState state, World worldIn, BlockPos pos)
    {
        worldIn.notifyBlockUpdate(pos, state, state, 2);
    }

    public static void updateState(World worldIn, BlockPos pos)
    {
        updateState(worldIn.getBlockState(pos), worldIn, pos);
    }
}
