package teamdraco.frozenup.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class MugBlock extends Block {
    protected static final VoxelShape MUG = makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 9.0D, 12.0D);
    protected static final VoxelShape HANDLE = makeCuboidShape(7.0D, 2.5D, 2.0D, 9.0D, 7.5D, 4.0D);
    protected static final VoxelShape SHAPE = VoxelShapes.or(MUG, HANDLE);

    public MugBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }
}
