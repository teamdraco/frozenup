package teamdraco.frozenup.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import teamdraco.frozenup.init.FrozenUpSoundEvents;

import javax.annotation.Nullable;

@SuppressWarnings("deprecation")
public class FeatherLampBlock extends Block {
    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;

    public FeatherLampBlock(Properties settings) {
        super(settings);
        this.setDefaultState(this.stateContainer.getBaseState().with(LIT, false));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext ctx) {
        BlockState state = super.getStateForPlacement(ctx);
        PlayerEntity player = ctx.getPlayer();
        return state == null ? null : state.with(LIT, player != null && player.isSneaking());
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        boolean newLit = !state.get(LIT);

        world.setBlockState(pos, state.with(LIT, newLit));
        if (!world.isRemote) {
            world.playMovingSound(null, player, newLit ? FrozenUpSoundEvents.BLOCK_CHILLOO_FEATHER_LAMP_TOGGLE_ON : FrozenUpSoundEvents.BLOCK_CHILLOO_FEATHER_LAMP_TOGGLE_OFF, SoundCategory.PLAYERS, 1.0f, 1.0f);
        }

        return ActionResultType.SUCCESS;
    }
}
