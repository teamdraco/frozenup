package teamdraco.frozenup.entity;

import java.util.EnumSet;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockStateMatcher;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.DyeColor;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.ForgeEventFactory;
import teamdraco.frozenup.FrozenUp;
import teamdraco.frozenup.init.FrozenUpEntities;
import teamdraco.frozenup.init.FrozenUpItems;
import teamdraco.frozenup.init.FrozenUpSoundEvents;

public class ChillooEntity extends TameableEntity {
    public static final int DIG_ANIMATION_ID = 10;
    private static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.WHEAT_SEEDS, Items.BEETROOT_SEEDS, Items.MELON_SEEDS, Items.COCOA_BEANS, Items.POTATO, Items.CARROT);
    private static final BiMap<Block, DyeColor> WOOL_BLOCKS = HashBiMap.create(16);
    private static final DataParameter<Byte> COLOR = EntityDataManager.createKey(ChillooEntity.class, DataSerializers.BYTE);
    public int timeUntilNextFeather = this.rand.nextInt(10000) + 2500;
    public int digTimer = 0;

    static {
        WOOL_BLOCKS.put(Blocks.WHITE_WOOL, DyeColor.WHITE);
        WOOL_BLOCKS.put(Blocks.ORANGE_WOOL, DyeColor.ORANGE);
        WOOL_BLOCKS.put(Blocks.MAGENTA_WOOL, DyeColor.MAGENTA);
        WOOL_BLOCKS.put(Blocks.LIGHT_BLUE_WOOL, DyeColor.LIGHT_BLUE);
        WOOL_BLOCKS.put(Blocks.YELLOW_WOOL, DyeColor.YELLOW);
        WOOL_BLOCKS.put(Blocks.LIME_WOOL, DyeColor.LIME);
        WOOL_BLOCKS.put(Blocks.PINK_WOOL, DyeColor.PINK);
        WOOL_BLOCKS.put(Blocks.GRAY_WOOL, DyeColor.GRAY);
        WOOL_BLOCKS.put(Blocks.LIGHT_GRAY_WOOL, DyeColor.LIGHT_GRAY);
        WOOL_BLOCKS.put(Blocks.CYAN_WOOL, DyeColor.CYAN);
        WOOL_BLOCKS.put(Blocks.PURPLE_WOOL, DyeColor.PURPLE);
        WOOL_BLOCKS.put(Blocks.BLUE_WOOL, DyeColor.BLUE);
        WOOL_BLOCKS.put(Blocks.BROWN_WOOL, DyeColor.BROWN);
        WOOL_BLOCKS.put(Blocks.GREEN_WOOL, DyeColor.GREEN);
        WOOL_BLOCKS.put(Blocks.RED_WOOL, DyeColor.RED);
        WOOL_BLOCKS.put(Blocks.BLACK_WOOL, DyeColor.BLACK);
    }

    public ChillooEntity(EntityType<? extends ChillooEntity> type, World worldIn) {
        super(type, worldIn);
        this.stepHeight = 1;
        this.setTamed(false);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new SitGoal(this));
        this.goalSelector.addGoal(2, new PanicGoal(this, 1.4D));
        this.goalSelector.addGoal(3, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(4, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new TemptGoal(this, 1.0D, false, TEMPTATION_ITEMS));
        this.goalSelector.addGoal(6, new DiggingGoal(this));
        this.goalSelector.addGoal(7, new FollowParentGoal(this, 1.1D) {
            @Override
            public boolean shouldExecute() {
                return !isTamed() && super.shouldExecute();
            }
        });
        this.goalSelector.addGoal(8, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(9, new LookAtGoal(this, PlayerEntity.class, 6.0F));
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return this.isChild() ? 0.5F : 0.7F;
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 12.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.2F);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.getItem() == Items.WHEAT_SEEDS || stack.getItem() == Items.BEETROOT_SEEDS || stack.getItem() == Items.MELON_SEEDS || stack.getItem() == Items.COCOA_BEANS || stack.getItem() == Items.POTATO || stack.getItem() == Items.CARROT;
    }

    public void setBandColor(DyeColor color) {
        dataManager.set(COLOR, color == null ? -1 : (byte) color.ordinal());
    }

    public DyeColor getBandColor() {
        byte color = dataManager.get(COLOR);
        return color == -1 || color >= 16 ? null : DyeColor.byId(color);
    }

    public void setSweaterColor(DyeColor color) {
        dataManager.set(COLOR, color == null ? -1 : (byte) (color.ordinal() + 16));
    }

    public DyeColor getSweaterColor() {
        byte color = dataManager.get(COLOR);
        return color < 16 ? null : DyeColor.byId(color - 16);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void setTamed(boolean tamed) {
        super.setTamed(tamed);
        if (tamed) {
            this.setBandColor(DyeColor.RED);
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20.0D);
            this.setHealth(20.0F);
        } else {
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(12.0D);
        }
    }

    @Override
    public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getHeldItem(hand);
        Item item = stack.getItem();
        if (item == FrozenUpItems.FROZEN_TRUFFLE.get() && !this.isTamed()) {
            if (!player.abilities.isCreativeMode) {
                stack.shrink(1);
            }
            if (!ForgeEventFactory.onAnimalTame(this, player)) {
                this.setTamedBy(player);
                this.navigator.clearPath();
                this.func_233687_w_(true);
                this.world.setEntityState(this, (byte) 7);
            } else {
                this.world.setEntityState(this, (byte) 6);
            }
            return ActionResultType.SUCCESS;
        }

        if (this.isOwner(player)) {
            if (item instanceof DyeItem) {
                setBandColor(((DyeItem) item).getDyeColor());
                stack.shrink(1);
            } else {
                DyeColor dyeColor = WOOL_BLOCKS.get(Block.getBlockFromItem(item));
                if (dyeColor == null) {
                    if (item == Items.SHEARS) {
                        boolean failed = false;
                        DyeColor bandColor = getBandColor();
                        if (bandColor == null) {
                            DyeColor sweaterColor = getSweaterColor();
                            if (sweaterColor == null) {
                                failed = true;
                            } else {
                                entityDropItem(WOOL_BLOCKS.inverse().get(sweaterColor));
                            }
                        }
                        if (!failed) {
                            setBandColor(DyeColor.RED);
                            this.world.playMovingSound(null, this, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.PLAYERS, 1.0F, 1.0F);
                            stack.damageItem(1, player, playerEntity -> playerEntity.sendBreakAnimation(hand));
                        }
                    } else {
                        func_233687_w_(!isEntitySleeping());
                        this.isJumping = false;
                        this.navigator.clearPath();
                        stack.shrink(1);
                    }
                } else {
                    setSweaterColor(dyeColor);
                    stack.shrink(1);
                }
            }
            return ActionResultType.SUCCESS;
        }

        return super.func_230254_b_(player, hand);
    }

    @Override
    public void livingTick() {
        super.livingTick();
        if (!this.world.isRemote) {
            if (this.isAlive() && !this.isChild() && --this.timeUntilNextFeather <= 0 && this.isTamed()) {
                this.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
                this.entityDropItem(FrozenUpItems.CHILLOO_FEATHER.get());
                this.timeUntilNextFeather = this.rand.nextInt(10000) + 5000;
            }
        } else if (digTimer > 0) {
            --digTimer;
        }
    }

    @Nullable
    @Override
    public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity ageable) {
        ChillooEntity chilloo = FrozenUpEntities.CHILLOO.get().create(this.world);
        UUID uuid = this.getOwnerId();
        if (chilloo != null) {
            chilloo.setOwnerId(uuid);
            chilloo.setTamed(true);
        }
        return chilloo;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return FrozenUpSoundEvents.ENTITY_CHILLOO_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return FrozenUpSoundEvents.ENTITY_CHILLOO_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return FrozenUpSoundEvents.ENTITY_CHILLOO_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.ENTITY_SHEEP_STEP, 0.15F, 1.0F);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(COLOR, (byte) 0);
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.dataManager.set(COLOR, compound.getByte("Colors"));
        if (compound.contains("FeatherLayTime")) {
            this.timeUntilNextFeather = compound.getInt("FeatherLayTime");
        }
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putByte("Colors", this.dataManager.get(COLOR));
        compound.putInt("FeatherLayTime", this.timeUntilNextFeather);
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(FrozenUpItems.CHILLOO_SPAWN_EGG.get());
    }

    @Override
    public void handleStatusUpdate(byte id) {
        if (id == DIG_ANIMATION_ID) {
            this.digTimer = 40;
        } else {
            super.handleStatusUpdate(id);
        }
    }

    static class DiggingGoal extends Goal {
        private static final ResourceLocation DIGGING_LOOT = new ResourceLocation(FrozenUp.MOD_ID, "entities/chilloo_digging");
        private static final Predicate<BlockState> IS_GRASS = BlockStateMatcher.forBlock(Blocks.GRASS);

        private final ChillooEntity entity;
        private int eatingGrassTimer;
        private int digTimer;

        public DiggingGoal(ChillooEntity entity) {
            this.entity = entity;
            setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP));
        }

        @Override
        public boolean shouldExecute() {
            if (digTimer > 0) {
                --digTimer;
                return false;
            }
            if (entity.isEntitySleeping()) {
                return false;
            }
            if (entity.getRNG().nextInt(entity.isChild() ? 100 : 1000) != 0) {
                return false;
            } else {
                BlockPos blockpos = entity.getPosition();
                if (IS_GRASS.test(entity.world.getBlockState(blockpos))) {
                    return true;
                } else {
                    return entity.world.getBlockState(blockpos.down()).isIn(Blocks.GRASS_BLOCK);
                }
            }
        }

        @Override
        public void startExecuting() {
            eatingGrassTimer = 40;
            digTimer = 6000;
            entity.world.setEntityState(entity, (byte) 10);
            entity.getNavigator().clearPath();
        }

        @Override
        public void resetTask() {
            eatingGrassTimer = 0;
        }

        @Override
        public boolean shouldContinueExecuting() {
            return eatingGrassTimer > 0;
        }

        @Override
        public void tick() {
            if (digTimer > 0) {
                --digTimer;
            }
            if (eatingGrassTimer > 0) {
                --eatingGrassTimer;
            }
            if (eatingGrassTimer == 25) {
                BlockPos blockpos = entity.getPosition();
                if (IS_GRASS.test(entity.world.getBlockState(blockpos))) {
                    entity.eatGrassBonus();
                } else {
                    BlockPos blockpos1 = blockpos.down();
                    if (entity.world.getBlockState(blockpos1).isIn(Blocks.GRASS_BLOCK)) {
                        entity.eatGrassBonus();
                        entity.world.playEvent(2001, blockpos1, Block.getStateId(Blocks.GRASS_BLOCK.getDefaultState()));
                        MinecraftServer server = entity.world.getServer();
                        if (server != null) {
                            List<ItemStack> items = server.getLootTableManager().getLootTableFromLocation(DIGGING_LOOT).generate(new LootContext.Builder((ServerWorld) entity.world).withRandom(entity.getRNG()).build(LootParameterSets.EMPTY));
                            InventoryHelper.dropItems(entity.world, blockpos, NonNullList.from(ItemStack.EMPTY, items.toArray(new ItemStack[0])));
                        }
                    }
                }
            }
        }
    }
}
