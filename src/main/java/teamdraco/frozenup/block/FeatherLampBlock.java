package teamdraco.frozenup.block;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class FeatherLampBlock extends Block {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public FeatherLampBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(LIT, Boolean.valueOf(false)));
        
    }

    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (state.get(LIT)) {
            this.powerBlockOff(state, worldIn, pos);
            this.playSound(player, worldIn, pos);
            return ActionResultType.func_233537_a_(worldIn.isRemote);
        } else {
            this.powerBlockOn(state, worldIn, pos);
            this.playSound(player, worldIn, pos);
            return ActionResultType.func_233537_a_(worldIn.isRemote);
        }
    }

    public void powerBlockOn(BlockState state, World world, BlockPos pos) {
        world.setBlockState(pos, state.with(LIT, Boolean.valueOf(true)), 3);
    }

    public void powerBlockOff(BlockState state, World world, BlockPos pos) {
        world.setBlockState(pos, state.with(LIT, Boolean.valueOf(false)), 3);
    }

    protected void playSound(@Nullable PlayerEntity playerIn, IWorld worldIn, BlockPos pos) {
        worldIn.playSound(playerIn, pos, SoundEvents.BLOCK_WOODEN_PRESSURE_PLATE_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.5F);
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }
}
