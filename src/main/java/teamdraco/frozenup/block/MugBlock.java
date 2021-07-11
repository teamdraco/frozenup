package teamdraco.frozenup.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import teamdraco.frozenup.init.FrozenUpBlocks;
import teamdraco.frozenup.item.AbstractDrinkableMugItem;

import javax.annotation.Nullable;
import java.util.function.Supplier;

@SuppressWarnings("deprecation")
public class MugBlock extends HorizontalBlock {
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    protected static final VoxelShape MUG = makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 9.0D, 12.0D);
    protected static final VoxelShape HANDLE = makeCuboidShape(7.0D, 2.5D, 2.0D, 9.0D, 7.5D, 4.0D);
    protected static final VoxelShape SHAPE = VoxelShapes.or(MUG, HANDLE);
    protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(MUG, HANDLE);
    protected static final VoxelShape SOUTH_SHAPE = VoxelShapes.or(Block.makeCuboidShape(7, 2.5, 12, 9, 7.5, 14), Block.makeCuboidShape(4, 0, 4, 12, 9, 12));
    protected static final VoxelShape EAST_SHAPE = VoxelShapes.or(Block.makeCuboidShape(12, 2.5, 7, 14, 7.5, 9), Block.makeCuboidShape(4, 0, 4, 12, 9, 12));
    protected static final VoxelShape WEST_SHAPE = VoxelShapes.or(Block.makeCuboidShape(2, 2.5, 7, 4, 7.5, 9), Block.makeCuboidShape(4, 0, 4, 12, 9, 12));

    @Nullable private final Supplier<Item> mugItem;

    public MugBlock(@Nullable Supplier<Item> mugItem, Properties settings) {
        super(settings);
        this.setDefaultState((this.stateContainer.getBaseState()).with(FACING, Direction.NORTH));

        this.mugItem = mugItem;
    }
    public MugBlock(Properties settings) {
        this(null, settings);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        Item item = mugItem == null ? null : mugItem.get();
        if (item instanceof AbstractDrinkableMugItem && !state.isIn(FrozenUpBlocks.EMPTY_MUG.get())) {
            item.onItemUseFinish(new ItemStack(item), world, player);
            world.setBlockState(pos, FrozenUpBlocks.EMPTY_MUG.get().getDefaultState().with(FACING, state.get(FACING)));
            if (!world.isRemote) {
                world.playMovingSound(null, player, SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.PLAYERS, 1.0f, 1.0f);
            }

            return ActionResultType.func_233537_a_(world.isRemote);
        }

        return super.onBlockActivated(state, world, pos, player, hand, hit);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)) {
            case NORTH:
                return NORTH_SHAPE;
            case SOUTH:
                return SOUTH_SHAPE;
            case EAST:
                return EAST_SHAPE;
            case WEST:
                return WEST_SHAPE;
            default:
                return SHAPE;
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext ctx) {
        BlockState state = super.getStateForPlacement(ctx);
        PlayerEntity player = ctx.getPlayer();
        HandSide arm = player != null ? player.getPrimaryHand() : HandSide.RIGHT;
        HandSide activeHandSide = ctx.getHand() == Hand.MAIN_HAND ? HandSide.RIGHT : HandSide.LEFT;
        Direction facing = ctx.getPlacementHorizontalFacing();

        return state == null
            ? null
            : state.with(
                FACING,
                (arm == HandSide.RIGHT && activeHandSide == HandSide.RIGHT) || (arm == HandSide.LEFT && activeHandSide == HandSide.LEFT)
                    ? facing.rotateY()
                    : facing.rotateYCCW()
            );
    }
}
