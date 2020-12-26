package mod.teamdraco.frozenup.entity.ai;

import mod.teamdraco.frozenup.FrozenUp;
import mod.teamdraco.frozenup.entity.ChillooEntity;
import mod.teamdraco.frozenup.init.FrozenUpItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockStateMatcher;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSet;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.EnumSet;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class DiggingGoal extends Goal {
    private static final ResourceLocation DIGGING_LOOT = new ResourceLocation(FrozenUp.MOD_ID, "entities/chilloo_digging");
    private static final Predicate<BlockState> IS_GRASS = BlockStateMatcher.forBlock(Blocks.GRASS);

    private final ChillooEntity entity;
    private int eatingGrassTimer;

    public DiggingGoal(ChillooEntity entity) {
        this.entity = entity;
        setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP));
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean shouldExecute() {
        if (entity.isEntitySleeping()) return false;
        if (entity.getRNG().nextInt(entity.isChild() ? 100 : 1000) != 0) return false;
        else {
            BlockPos blockpos = entity.getPosition();
            if (IS_GRASS.test(entity.world.getBlockState(blockpos))) return true;
            else return entity.world.getBlockState(blockpos.down()).isIn(Blocks.GRASS_BLOCK);
        }
    }

    public void startExecuting() {
        eatingGrassTimer = 40;
        entity.world.setEntityState(entity, (byte) 10);
        entity.getNavigator().clearPath();
    }

    public void resetTask() {
        eatingGrassTimer = 0;
    }

    public boolean shouldContinueExecuting() {
        return eatingGrassTimer > 0;
    }

    public int getEatingGrassTimer() {
        return eatingGrassTimer;
    }

    public void tick() {
        if (eatingGrassTimer > 0) --eatingGrassTimer;
        if (eatingGrassTimer == 25) {
            BlockPos blockpos = entity.getPosition();
            if (IS_GRASS.test(entity.world.getBlockState(blockpos))) entity.eatGrassBonus();
            else {
                BlockPos blockpos1 = blockpos.down();
                if (entity.world.getBlockState(blockpos1).isIn(Blocks.GRASS_BLOCK)) {
                    entity.eatGrassBonus();
                    entity.world.playEvent(2001, blockpos1, Block.getStateId(Blocks.GRASS_BLOCK.getDefaultState()));
                    List<ItemStack> items = entity.world.getServer().getLootTableManager().getLootTableFromLocation(DIGGING_LOOT).generate(new LootContext.Builder((ServerWorld) entity.world).withRandom(entity.getRNG()).build(LootParameterSets.EMPTY));
                    InventoryHelper.dropItems(entity.world, blockpos, NonNullList.from(ItemStack.EMPTY, items.toArray(new ItemStack[0])));
                }
            }
        }
    }
}
