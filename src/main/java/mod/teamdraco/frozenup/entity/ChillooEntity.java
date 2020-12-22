package mod.teamdraco.frozenup.entity;

import java.util.UUID;

import javax.annotation.Nullable;

import mod.teamdraco.frozenup.init.FrozenUpEntities;
import mod.teamdraco.frozenup.init.FrozenUpItems;
import mod.teamdraco.frozenup.init.FrozenUpSounds;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SitGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.ForgeEventFactory;

public class ChillooEntity extends TameableEntity {
    private static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.WHEAT_SEEDS, Items.BEETROOT_SEEDS, Items.MELON_SEEDS, Items.COCOA_BEANS, Items.POTATO, Items.CARROT);
	private static final DataParameter<Boolean> BLACK_BAND = EntityDataManager.createKey(ChillooEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> BLUE_BAND = EntityDataManager.createKey(ChillooEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> BROWN_BAND = EntityDataManager.createKey(ChillooEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> CYAN_BAND = EntityDataManager.createKey(ChillooEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> GRAY_BAND = EntityDataManager.createKey(ChillooEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> GREEN_BAND = EntityDataManager.createKey(ChillooEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> LIGHT_BLUE_BAND = EntityDataManager.createKey(ChillooEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> LIGHT_GRAY_BAND = EntityDataManager.createKey(ChillooEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> LIME_BAND = EntityDataManager.createKey(ChillooEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> MAGENTA_BAND = EntityDataManager.createKey(ChillooEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> ORANGE_BAND = EntityDataManager.createKey(ChillooEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> PINK_BAND = EntityDataManager.createKey(ChillooEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> PURPLE_BAND = EntityDataManager.createKey(ChillooEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> RED_BAND = EntityDataManager.createKey(ChillooEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> WHITE_BAND = EntityDataManager.createKey(ChillooEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> YELLOW_BAND = EntityDataManager.createKey(ChillooEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> BLACK_SWEATER = EntityDataManager.createKey(ChillooEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> BLUE_SWEATER = EntityDataManager.createKey(ChillooEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> BROWN_SWEATER = EntityDataManager.createKey(ChillooEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> CYAN_SWEATER = EntityDataManager.createKey(ChillooEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> GRAY_SWEATER = EntityDataManager.createKey(ChillooEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> GREEN_SWEATER = EntityDataManager.createKey(ChillooEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> LIGHT_BLUE_SWEATER = EntityDataManager.createKey(ChillooEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> LIGHT_GRAY_SWEATER = EntityDataManager.createKey(ChillooEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> LIME_SWEATER = EntityDataManager.createKey(ChillooEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> MAGENTA_SWEATER = EntityDataManager.createKey(ChillooEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> ORANGE_SWEATER = EntityDataManager.createKey(ChillooEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> PINK_SWEATER = EntityDataManager.createKey(ChillooEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> PURPLE_SWEATER = EntityDataManager.createKey(ChillooEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> RED_SWEATER = EntityDataManager.createKey(ChillooEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> WHITE_SWEATER = EntityDataManager.createKey(ChillooEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> YELLOW_SWEATER = EntityDataManager.createKey(ChillooEntity.class, DataSerializers.BOOLEAN);
    public int timeUntilNextFeather = this.rand.nextInt(10000) + 5000;

    public boolean hasBlackBand() {
		return this.dataManager.get(BLACK_BAND);
	}
	
	private void setHasBlackBand(boolean hasBlackBand) {
		this.dataManager.set(BLACK_BAND, hasBlackBand);
	}
	
	public boolean hasBlueBand() {
		return this.dataManager.get(BLUE_BAND);
	}
	
	private void setHasBlueBand(boolean hasBlueBand) {
		this.dataManager.set(BLUE_BAND, hasBlueBand);
	}
	
	public boolean hasBrownBand() {
		return this.dataManager.get(BROWN_BAND);
	}
	
	private void setHasBrownBand(boolean hasBrownBand) {
		this.dataManager.set(BROWN_BAND, hasBrownBand);
	}
	
	public boolean hasCyanBand() {
		return this.dataManager.get(CYAN_BAND);
	}
	
	private void setHasCyanBand(boolean hasCyanBand) {
		this.dataManager.set(CYAN_BAND, hasCyanBand);
	}
	
	public boolean hasGrayBand() {
		return this.dataManager.get(GRAY_BAND);
	}
	
	private void setHasGrayBand(boolean hasGrayBand) {
		this.dataManager.set(GRAY_BAND, hasGrayBand);
	}
	
	public boolean hasGreenBand() {
		return this.dataManager.get(GREEN_BAND);
	}
	
	private void setHasGreenBand(boolean hasGreenBand) {
		this.dataManager.set(GREEN_BAND, hasGreenBand);
	}
	
	public boolean hasLightBlueBand() {
		return this.dataManager.get(LIGHT_BLUE_BAND);
	}
	
	private void setHasLightBlueBand(boolean hasLightBlueBand) {
		this.dataManager.set(LIGHT_BLUE_BAND, hasLightBlueBand);
	}
	
	public boolean hasLightGrayBand() {
		return this.dataManager.get(LIGHT_GRAY_BAND);
	}
	
	private void setHasLightGrayBand(boolean hasLightGrayBand) {
		this.dataManager.set(LIGHT_GRAY_BAND, hasLightGrayBand);
	}
	
	public boolean hasLimeBand() {
		return this.dataManager.get(LIME_BAND);
	}
	
	private void setHasLimeBand(boolean hasLimeBand) {
		this.dataManager.set(LIME_BAND, hasLimeBand);
	}
	
	public boolean hasMagentaBand() {
		return this.dataManager.get(MAGENTA_BAND);
	}
	
	private void setHasMagentaBand(boolean hasMagentaBand) {
		this.dataManager.set(MAGENTA_BAND, hasMagentaBand);
	}
	
	public boolean hasOrangeBand() {
		return this.dataManager.get(ORANGE_BAND);
	}
	
	private void setHasOrangeBand(boolean hasOrangeBand) {
		this.dataManager.set(ORANGE_BAND, hasOrangeBand);
	}
	
	public boolean hasPinkBand() {
		return this.dataManager.get(PINK_BAND);
	}
	
	private void setHasPinkBand(boolean hasPinkBand) {
		this.dataManager.set(PINK_BAND, hasPinkBand);
	}
	
	public boolean hasPurpleBand() {
		return this.dataManager.get(PURPLE_BAND);
	}
	
	private void setHasPurpleBand(boolean hasPurpleBand) {
		this.dataManager.set(PURPLE_BAND, hasPurpleBand);
	}
	
	public boolean hasRedBand() {
		return this.dataManager.get(RED_BAND);
	}
	
	private void setHasRedBand(boolean hasRedBand) {
		this.dataManager.set(RED_BAND, hasRedBand);
	}
	
	public boolean hasWhiteBand() {
		return this.dataManager.get(WHITE_BAND);
	}
	
	private void setHasWhiteBand(boolean hasWhiteBand) {
		this.dataManager.set(WHITE_BAND, hasWhiteBand);
	}
	
	public boolean hasYellowBand() {
		return this.dataManager.get(YELLOW_BAND);
	}
	
	private void setHasYellowBand(boolean hasYellowBand) {
		this.dataManager.set(YELLOW_BAND, hasYellowBand);
	}
	
	public boolean hasBlackSweater() {
		return this.dataManager.get(BLACK_SWEATER);
	}
	
	private void setHasBlackSweater(boolean hasBlackSweater) {
		this.dataManager.set(BLACK_SWEATER, hasBlackSweater);
	}
	
	public boolean hasBlueSweater() {
		return this.dataManager.get(BLUE_SWEATER);
	}
	
	private void setHasBlueSweater(boolean hasBlueSweater) {
		this.dataManager.set(BLUE_SWEATER, hasBlueSweater);
	}
	
	public boolean hasBrownSweater() {
		return this.dataManager.get(BROWN_SWEATER);
	}
	
	private void setHasBrownSweater(boolean hasBrownSweater) {
		this.dataManager.set(BROWN_SWEATER, hasBrownSweater);
	}
	
	public boolean hasCyanSweater() {
		return this.dataManager.get(CYAN_SWEATER);
	}
	
	private void setHasCyanSweater(boolean hasCyanSweater) {
		this.dataManager.set(CYAN_SWEATER, hasCyanSweater);
	}
	
	public boolean hasGraySweater() {
		return this.dataManager.get(GRAY_SWEATER);
	}
	
	private void setHasGraySweater(boolean hasGraySweater) {
		this.dataManager.set(GRAY_SWEATER, hasGraySweater);
	}
	
	public boolean hasGreenSweater() {
		return this.dataManager.get(GREEN_SWEATER);
	}
	
	private void setHasGreenSweater(boolean hasGreenSweater) {
		this.dataManager.set(GREEN_SWEATER, hasGreenSweater);
	}
	
	public boolean hasLightBlueSweater() {
		return this.dataManager.get(LIGHT_BLUE_SWEATER);
	}
	
	private void setHasLightBlueSweater(boolean hasLightBlueSweater) {
		this.dataManager.set(LIGHT_BLUE_SWEATER, hasLightBlueSweater);
	}
	
	public boolean hasLightGraySweater() {
		return this.dataManager.get(LIGHT_GRAY_SWEATER);
	}
	
	private void setHasLightGraySweater(boolean hasLightGraySweater) {
		this.dataManager.set(LIGHT_GRAY_SWEATER, hasLightGraySweater);
	}
	
	public boolean hasLimeSweater() {
		return this.dataManager.get(LIME_SWEATER);
	}
	
	private void setHasLimeSweater(boolean hasLimeSweater) {
		this.dataManager.set(LIME_SWEATER, hasLimeSweater);
	}
	
	public boolean hasMagentaSweater() {
		return this.dataManager.get(MAGENTA_SWEATER);
	}
	
	private void setHasMagentaSweater(boolean hasMagentaSweater) {
		this.dataManager.set(MAGENTA_SWEATER, hasMagentaSweater);
	}
	
	public boolean hasOrangeSweater() {
		return this.dataManager.get(ORANGE_SWEATER);
	}
	
	private void setHasOrangeSweater(boolean hasOrangeSweater) {
		this.dataManager.set(ORANGE_SWEATER, hasOrangeSweater);
	}
	
	public boolean hasPinkSweater() {
		return this.dataManager.get(PINK_SWEATER);
	}
	
	private void setHasPinkSweater(boolean hasPinkSweater) {
		this.dataManager.set(PINK_SWEATER, hasPinkSweater);
	}
	
	public boolean hasPurpleSweater() {
		return this.dataManager.get(PURPLE_SWEATER);
	}
	
	private void setHasPurpleSweater(boolean hasPurpleSweater) {
		this.dataManager.set(PURPLE_SWEATER, hasPurpleSweater);
	}
	
	public boolean hasRedSweater() {
		return this.dataManager.get(RED_SWEATER);
	}
	
	private void setHasRedSweater(boolean hasRedSweater) {
		this.dataManager.set(RED_SWEATER, hasRedSweater);
	}
	
	public boolean hasWhiteSweater() {
		return this.dataManager.get(WHITE_SWEATER);
	}
	
	private void setHasWhiteSweater(boolean hasWhiteSweater) {
		this.dataManager.set(WHITE_SWEATER, hasWhiteSweater);
	}
	
	public boolean hasYellowSweater() {
		return this.dataManager.get(YELLOW_SWEATER);
	}
	
	private void setHasYellowSweater(boolean hasYellowSweater) {
		this.dataManager.set(YELLOW_SWEATER, hasYellowSweater);
	}
    
    public ChillooEntity(EntityType<? extends ChillooEntity> type, World worldIn) {
        super(type, worldIn);
        this.stepHeight = 1;
        this.setTamed(false);
    }
    
    private void resetBandColor() {
    	this.setHasBlackBand(false);
    	this.setHasBlueBand(false);
    	this.setHasBrownBand(false);
    	this.setHasCyanBand(false);
    	this.setHasGrayBand(false);
    	this.setHasGreenBand(false);
    	this.setHasLightBlueBand(false);
    	this.setHasLightGrayBand(false);
    	this.setHasLimeBand(false);
    	this.setHasMagentaBand(false);
    	this.setHasOrangeBand(false);
    	this.setHasPinkBand(false);
    	this.setHasPurpleBand(false);
    	this.setHasRedBand(false);
    	this.setHasWhiteBand(false);
    	this.setHasYellowBand(false);
    }
    
    private void resetSweaterColor() {
    	this.setHasBlackSweater(false);
    	this.setHasBlueSweater(false);
    	this.setHasBrownSweater(false);
    	this.setHasCyanSweater(false);
    	this.setHasGraySweater(false);
    	this.setHasGreenSweater(false);
    	this.setHasLightBlueSweater(false);
    	this.setHasLightGraySweater(false);
    	this.setHasLimeSweater(false);
    	this.setHasMagentaSweater(false);
    	this.setHasOrangeSweater(false);
    	this.setHasPinkSweater(false);
    	this.setHasPurpleSweater(false);
    	this.setHasRedSweater(false);
    	this.setHasWhiteSweater(false);
    	this.setHasYellowSweater(false);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new SitGoal(this));
        this.goalSelector.addGoal(2, new PanicGoal(this, 1.4D));
        this.goalSelector.addGoal(3, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(4, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new TemptGoal(this, 1.0D, false, TEMPTATION_ITEMS));
        this.goalSelector.addGoal(6, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(9, new LookRandomlyGoal(this));
    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return this.isChild() ? 0.5F : 0.7F;
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 12.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, (double)0.2F);
    }

    public boolean isBreedingItem(ItemStack stack) {
        return stack.getItem() == Items.WHEAT_SEEDS || stack.getItem() == Items.BEETROOT_SEEDS || stack.getItem() == Items.MELON_SEEDS || stack.getItem() == Items.COCOA_BEANS || stack.getItem() == Items.POTATO || stack.getItem() == Items.CARROT;
    }

    public void setTamed(boolean tamed) {
        super.setTamed(tamed);
        if (tamed) {
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20.0D);
            this.setHealth(20.0F);
        } else {
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(12.0D);
        }
    }

    public ActionResultType func_230254_b_(PlayerEntity playerEntity, Hand hand) {
        ItemStack itemstack = playerEntity.getHeldItem(hand);
        Item item = itemstack.getItem();
        if (item == FrozenUpItems.FROZEN_TRUFFLE.get() && !this.isTamed()) {
            if (!playerEntity.abilities.isCreativeMode) {
                itemstack.shrink(1);
            }
            if (this.rand.nextInt(2) == 0 && !ForgeEventFactory.onAnimalTame(this, playerEntity)) {
                this.setTamedBy(playerEntity);
                this.navigator.clearPath();
                this.func_233687_w_(true);
                this.world.setEntityState(this, (byte)7);
            } else {
                this.world.setEntityState(this, (byte)6);
            }
            return ActionResultType.SUCCESS;
        }

        if (this.isOwner(playerEntity) && !this.isBreedingItem(itemstack) && this.isTamed() && item != Items.BLACK_DYE && item != Items.BLUE_DYE && item != Items.BROWN_DYE && item != Items.CYAN_DYE && item != Items.GRAY_DYE && item != Items.GREEN_DYE && item != Items.LIGHT_BLUE_DYE && item != Items.LIGHT_GRAY_DYE && item != Items.LIME_DYE && item != Items.MAGENTA_DYE && item != Items.ORANGE_DYE && item != Items.PINK_DYE && item != Items.PURPLE_DYE && item != Items.RED_DYE && item != Items.WHITE_DYE && item != Items.YELLOW_DYE && item != Blocks.BLACK_WOOL.asItem() && item != Blocks.BLUE_WOOL.asItem() && item != Blocks.BROWN_WOOL.asItem() && item != Blocks.BROWN_WOOL.asItem() && item != Blocks.CYAN_WOOL.asItem() && item != Blocks.GRAY_WOOL.asItem() && item != Blocks.GREEN_WOOL.asItem() && item != Blocks.LIGHT_BLUE_WOOL.asItem() && item != Blocks.LIGHT_GRAY_WOOL.asItem() && item != Blocks.LIME_WOOL.asItem() && item != Blocks.MAGENTA_WOOL.asItem() && item != Blocks.ORANGE_WOOL.asItem() && item != Blocks.PINK_WOOL.asItem() && item != Blocks.PURPLE_WOOL.asItem() && item != Blocks.RED_WOOL.asItem() && item != Blocks.WHITE_WOOL.asItem() && item != Blocks.YELLOW_WOOL.asItem() && item != Items.SHEARS) {
            func_233687_w_(!isEntitySleeping());
            this.isJumping = false;
            this.navigator.clearPath();

            return ActionResultType.SUCCESS;
        }else if (this.isTamed() && this.isOwner(playerEntity) && item == Items.BLACK_DYE && !this.hasBlackBand() && !this.hasBlackSweater()  && !this.hasBlueSweater() && !this.hasBrownSweater() && !this.hasCyanSweater() && !this.hasGraySweater() && !this.hasGreenSweater() && !this.hasLightBlueSweater() && !this.hasLightGraySweater() && !this.hasLimeSweater() && !this.hasMagentaSweater() && !this.hasOrangeSweater() && !this.hasPinkSweater() && !this.hasPurpleSweater() && !this.hasRedSweater() && !this.hasWhiteSweater() && !this.hasYellowSweater()) {
        	this.resetBandColor();
        	this.resetSweaterColor();
        	this.setHasBlackBand(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Items.BLUE_DYE && !this.hasBlueBand() && !this.hasBlackSweater()  && !this.hasBlueSweater() && !this.hasBrownSweater() && !this.hasCyanSweater() && !this.hasGraySweater() && !this.hasGreenSweater() && !this.hasLightBlueSweater() && !this.hasLightGraySweater() && !this.hasLimeSweater() && !this.hasMagentaSweater() && !this.hasOrangeSweater() && !this.hasPinkSweater() && !this.hasPurpleSweater() && !this.hasRedSweater() && !this.hasWhiteSweater() && !this.hasYellowSweater()) {
        	this.resetBandColor();
        	this.resetSweaterColor();
        	this.setHasBlueBand(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Items.BROWN_DYE && !this.hasBrownBand() && !this.hasBlackSweater()  && !this.hasBlueSweater() && !this.hasBrownSweater() && !this.hasCyanSweater() && !this.hasGraySweater() && !this.hasGreenSweater() && !this.hasLightBlueSweater() && !this.hasLightGraySweater() && !this.hasLimeSweater() && !this.hasMagentaSweater() && !this.hasOrangeSweater() && !this.hasPinkSweater() && !this.hasPurpleSweater() && !this.hasRedSweater() && !this.hasWhiteSweater() && !this.hasYellowSweater()) {
        	this.resetBandColor();
        	this.resetSweaterColor();
        	this.setHasBrownBand(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Items.CYAN_DYE && !this.hasCyanBand() && !this.hasBlackSweater()  && !this.hasBlueSweater() && !this.hasBrownSweater() && !this.hasCyanSweater() && !this.hasGraySweater() && !this.hasGreenSweater() && !this.hasLightBlueSweater() && !this.hasLightGraySweater() && !this.hasLimeSweater() && !this.hasMagentaSweater() && !this.hasOrangeSweater() && !this.hasPinkSweater() && !this.hasPurpleSweater() && !this.hasRedSweater() && !this.hasWhiteSweater() && !this.hasYellowSweater()) {
        	this.resetBandColor();
        	this.resetSweaterColor();
        	this.setHasCyanBand(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Items.GRAY_DYE && !this.hasGrayBand() && !this.hasBlackSweater()  && !this.hasBlueSweater() && !this.hasBrownSweater() && !this.hasCyanSweater() && !this.hasGraySweater() && !this.hasGreenSweater() && !this.hasLightBlueSweater() && !this.hasLightGraySweater() && !this.hasLimeSweater() && !this.hasMagentaSweater() && !this.hasOrangeSweater() && !this.hasPinkSweater() && !this.hasPurpleSweater() && !this.hasRedSweater() && !this.hasWhiteSweater() && !this.hasYellowSweater()) {
        	this.resetBandColor();
        	this.resetSweaterColor();
        	this.setHasGrayBand(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Items.GREEN_DYE && !this.hasGreenBand() && !this.hasBlackSweater()  && !this.hasBlueSweater() && !this.hasBrownSweater() && !this.hasCyanSweater() && !this.hasGraySweater() && !this.hasGreenSweater() && !this.hasLightBlueSweater() && !this.hasLightGraySweater() && !this.hasLimeSweater() && !this.hasMagentaSweater() && !this.hasOrangeSweater() && !this.hasPinkSweater() && !this.hasPurpleSweater() && !this.hasRedSweater() && !this.hasWhiteSweater() && !this.hasYellowSweater()) {
        	this.resetBandColor();
        	this.resetSweaterColor();
        	this.setHasGreenBand(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Items.LIGHT_BLUE_DYE && !this.hasLightBlueBand() && !this.hasBlackSweater()  && !this.hasBlueSweater() && !this.hasBrownSweater() && !this.hasCyanSweater() && !this.hasGraySweater() && !this.hasGreenSweater() && !this.hasLightBlueSweater() && !this.hasLightGraySweater() && !this.hasLimeSweater() && !this.hasMagentaSweater() && !this.hasOrangeSweater() && !this.hasPinkSweater() && !this.hasPurpleSweater() && !this.hasRedSweater() && !this.hasWhiteSweater() && !this.hasYellowSweater()) {
        	this.resetBandColor();
        	this.resetSweaterColor();
        	this.setHasLightBlueBand(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Items.LIGHT_GRAY_DYE && !this.hasLightGrayBand() && !this.hasBlackSweater()  && !this.hasBlueSweater() && !this.hasBrownSweater() && !this.hasCyanSweater() && !this.hasGraySweater() && !this.hasGreenSweater() && !this.hasLightBlueSweater() && !this.hasLightGraySweater() && !this.hasLimeSweater() && !this.hasMagentaSweater() && !this.hasOrangeSweater() && !this.hasPinkSweater() && !this.hasPurpleSweater() && !this.hasRedSweater() && !this.hasWhiteSweater() && !this.hasYellowSweater()) {
        	this.resetBandColor();
        	this.resetSweaterColor();
        	this.setHasLightGrayBand(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Items.LIME_DYE && !this.hasLimeBand() && !this.hasBlackSweater()  && !this.hasBlueSweater() && !this.hasBrownSweater() && !this.hasCyanSweater() && !this.hasGraySweater() && !this.hasGreenSweater() && !this.hasLightBlueSweater() && !this.hasLightGraySweater() && !this.hasLimeSweater() && !this.hasMagentaSweater() && !this.hasOrangeSweater() && !this.hasPinkSweater() && !this.hasPurpleSweater() && !this.hasRedSweater() && !this.hasWhiteSweater() && !this.hasYellowSweater()) {
        	this.resetBandColor();
        	this.resetSweaterColor();
        	this.setHasLimeBand(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Items.MAGENTA_DYE && !this.hasMagentaBand() && !this.hasBlackSweater()  && !this.hasBlueSweater() && !this.hasBrownSweater() && !this.hasCyanSweater() && !this.hasGraySweater() && !this.hasGreenSweater() && !this.hasLightBlueSweater() && !this.hasLightGraySweater() && !this.hasLimeSweater() && !this.hasMagentaSweater() && !this.hasOrangeSweater() && !this.hasPinkSweater() && !this.hasPurpleSweater() && !this.hasRedSweater() && !this.hasWhiteSweater() && !this.hasYellowSweater()) {
        	this.resetBandColor();
        	this.resetSweaterColor();
        	this.setHasMagentaBand(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Items.ORANGE_DYE && !this.hasOrangeBand() && !this.hasBlackSweater()  && !this.hasBlueSweater() && !this.hasBrownSweater() && !this.hasCyanSweater() && !this.hasGraySweater() && !this.hasGreenSweater() && !this.hasLightBlueSweater() && !this.hasLightGraySweater() && !this.hasLimeSweater() && !this.hasMagentaSweater() && !this.hasOrangeSweater() && !this.hasPinkSweater() && !this.hasPurpleSweater() && !this.hasRedSweater() && !this.hasWhiteSweater() && !this.hasYellowSweater()) {
        	this.resetBandColor();
        	this.resetSweaterColor();
        	this.setHasOrangeBand(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Items.PINK_DYE && !this.hasPinkBand() && !this.hasBlackSweater()  && !this.hasBlueSweater() && !this.hasBrownSweater() && !this.hasCyanSweater() && !this.hasGraySweater() && !this.hasGreenSweater() && !this.hasLightBlueSweater() && !this.hasLightGraySweater() && !this.hasLimeSweater() && !this.hasMagentaSweater() && !this.hasOrangeSweater() && !this.hasPinkSweater() && !this.hasPurpleSweater() && !this.hasRedSweater() && !this.hasWhiteSweater() && !this.hasYellowSweater()) {
        	this.resetBandColor();
        	this.resetSweaterColor();
        	this.setHasPinkBand(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Items.PURPLE_DYE && !this.hasPurpleBand() && !this.hasBlackSweater()  && !this.hasBlueSweater() && !this.hasBrownSweater() && !this.hasCyanSweater() && !this.hasGraySweater() && !this.hasGreenSweater() && !this.hasLightBlueSweater() && !this.hasLightGraySweater() && !this.hasLimeSweater() && !this.hasMagentaSweater() && !this.hasOrangeSweater() && !this.hasPinkSweater() && !this.hasPurpleSweater() && !this.hasRedSweater() && !this.hasWhiteSweater() && !this.hasYellowSweater()) {
        	this.resetBandColor();
        	this.resetSweaterColor();
        	this.setHasPurpleBand(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Items.RED_DYE && !this.hasRedBand() && !this.hasBlackSweater()  && !this.hasBlueSweater() && !this.hasBrownSweater() && !this.hasCyanSweater() && !this.hasGraySweater() && !this.hasGreenSweater() && !this.hasLightBlueSweater() && !this.hasLightGraySweater() && !this.hasLimeSweater() && !this.hasMagentaSweater() && !this.hasOrangeSweater() && !this.hasPinkSweater() && !this.hasPurpleSweater() && !this.hasRedSweater() && !this.hasWhiteSweater() && !this.hasYellowSweater()) {
        	this.resetBandColor();
        	this.resetSweaterColor();
        	this.setHasRedBand(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Items.WHITE_DYE && !this.hasWhiteBand() && !this.hasBlackSweater()  && !this.hasBlueSweater() && !this.hasBrownSweater() && !this.hasCyanSweater() && !this.hasGraySweater() && !this.hasGreenSweater() && !this.hasLightBlueSweater() && !this.hasLightGraySweater() && !this.hasLimeSweater() && !this.hasMagentaSweater() && !this.hasOrangeSweater() && !this.hasPinkSweater() && !this.hasPurpleSweater() && !this.hasRedSweater() && !this.hasWhiteSweater() && !this.hasYellowSweater()) {
        	this.resetBandColor();
        	this.resetSweaterColor();
        	this.setHasWhiteBand(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Items.YELLOW_DYE && !this.hasYellowBand() && !this.hasBlackSweater()  && !this.hasBlueSweater() && !this.hasBrownSweater() && !this.hasCyanSweater() && !this.hasGraySweater() && !this.hasGreenSweater() && !this.hasLightBlueSweater() && !this.hasLightGraySweater() && !this.hasLimeSweater() && !this.hasMagentaSweater() && !this.hasOrangeSweater() && !this.hasPinkSweater() && !this.hasPurpleSweater() && !this.hasRedSweater() && !this.hasWhiteSweater() && !this.hasYellowSweater()) {
        	this.resetBandColor();
        	this.resetSweaterColor();
        	this.setHasYellowBand(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Blocks.BLACK_WOOL.asItem() && !this.hasBlackSweater()  && !this.hasBlueSweater() && !this.hasBrownSweater() && !this.hasCyanSweater() && !this.hasGraySweater() && !this.hasGreenSweater() && !this.hasLightBlueSweater() && !this.hasLightGraySweater() && !this.hasLimeSweater() && !this.hasMagentaSweater() && !this.hasOrangeSweater() && !this.hasPinkSweater() && !this.hasPurpleSweater() && !this.hasRedSweater() && !this.hasWhiteSweater() && !this.hasYellowSweater()) {
        	this.resetSweaterColor();
        	this.resetBandColor();
        	this.setHasBlackSweater(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Blocks.BLUE_WOOL.asItem() && !this.hasBlackSweater()  && !this.hasBlueSweater() && !this.hasBrownSweater() && !this.hasCyanSweater() && !this.hasGraySweater() && !this.hasGreenSweater() && !this.hasLightBlueSweater() && !this.hasLightGraySweater() && !this.hasLimeSweater() && !this.hasMagentaSweater() && !this.hasOrangeSweater() && !this.hasPinkSweater() && !this.hasPurpleSweater() && !this.hasRedSweater() && !this.hasWhiteSweater() && !this.hasYellowSweater()) {
        	this.resetSweaterColor();
        	this.resetBandColor();
        	this.setHasBlueSweater(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Blocks.BROWN_WOOL.asItem() && !this.hasBlackSweater()  && !this.hasBlueSweater() && !this.hasBrownSweater() && !this.hasCyanSweater() && !this.hasGraySweater() && !this.hasGreenSweater() && !this.hasLightBlueSweater() && !this.hasLightGraySweater() && !this.hasLimeSweater() && !this.hasMagentaSweater() && !this.hasOrangeSweater() && !this.hasPinkSweater() && !this.hasPurpleSweater() && !this.hasRedSweater() && !this.hasWhiteSweater() && !this.hasYellowSweater()) {
        	this.resetSweaterColor();
        	this.resetBandColor();
        	this.setHasBrownSweater(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Blocks.CYAN_WOOL.asItem() && !this.hasBlackSweater()  && !this.hasBlueSweater() && !this.hasBrownSweater() && !this.hasCyanSweater() && !this.hasGraySweater() && !this.hasGreenSweater() && !this.hasLightBlueSweater() && !this.hasLightGraySweater() && !this.hasLimeSweater() && !this.hasMagentaSweater() && !this.hasOrangeSweater() && !this.hasPinkSweater() && !this.hasPurpleSweater() && !this.hasRedSweater() && !this.hasWhiteSweater() && !this.hasYellowSweater()) {
        	this.resetSweaterColor();
        	this.resetBandColor();
        	this.setHasCyanSweater(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Blocks.GRAY_WOOL.asItem() && !this.hasBlackSweater()  && !this.hasBlueSweater() && !this.hasBrownSweater() && !this.hasCyanSweater() && !this.hasGraySweater() && !this.hasGreenSweater() && !this.hasLightBlueSweater() && !this.hasLightGraySweater() && !this.hasLimeSweater() && !this.hasMagentaSweater() && !this.hasOrangeSweater() && !this.hasPinkSweater() && !this.hasPurpleSweater() && !this.hasRedSweater() && !this.hasWhiteSweater() && !this.hasYellowSweater()) {
        	this.resetSweaterColor();
        	this.resetBandColor();
        	this.setHasGraySweater(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Blocks.GREEN_WOOL.asItem() && !this.hasBlackSweater()  && !this.hasBlueSweater() && !this.hasBrownSweater() && !this.hasCyanSweater() && !this.hasGraySweater() && !this.hasGreenSweater() && !this.hasLightBlueSweater() && !this.hasLightGraySweater() && !this.hasLimeSweater() && !this.hasMagentaSweater() && !this.hasOrangeSweater() && !this.hasPinkSweater() && !this.hasPurpleSweater() && !this.hasRedSweater() && !this.hasWhiteSweater() && !this.hasYellowSweater()) {
        	this.resetSweaterColor();
        	this.resetBandColor();
        	this.setHasGreenSweater(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Blocks.LIGHT_BLUE_WOOL.asItem() && !this.hasBlackSweater()  && !this.hasBlueSweater() && !this.hasBrownSweater() && !this.hasCyanSweater() && !this.hasGraySweater() && !this.hasGreenSweater() && !this.hasLightBlueSweater() && !this.hasLightGraySweater() && !this.hasLimeSweater() && !this.hasMagentaSweater() && !this.hasOrangeSweater() && !this.hasPinkSweater() && !this.hasPurpleSweater() && !this.hasRedSweater() && !this.hasWhiteSweater() && !this.hasYellowSweater()) {
        	this.resetSweaterColor();
        	this.resetBandColor();
        	this.setHasLightBlueSweater(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Blocks.LIGHT_GRAY_WOOL.asItem() && !this.hasBlackSweater()  && !this.hasBlueSweater() && !this.hasBrownSweater() && !this.hasCyanSweater() && !this.hasGraySweater() && !this.hasGreenSweater() && !this.hasLightBlueSweater() && !this.hasLightGraySweater() && !this.hasLimeSweater() && !this.hasMagentaSweater() && !this.hasOrangeSweater() && !this.hasPinkSweater() && !this.hasPurpleSweater() && !this.hasRedSweater() && !this.hasWhiteSweater() && !this.hasYellowSweater()) {
        	this.resetSweaterColor();
        	this.resetBandColor();
        	this.setHasLightGraySweater(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Blocks.LIME_WOOL.asItem() && !this.hasBlackSweater()  && !this.hasBlueSweater() && !this.hasBrownSweater() && !this.hasCyanSweater() && !this.hasGraySweater() && !this.hasGreenSweater() && !this.hasLightBlueSweater() && !this.hasLightGraySweater() && !this.hasLimeSweater() && !this.hasMagentaSweater() && !this.hasOrangeSweater() && !this.hasPinkSweater() && !this.hasPurpleSweater() && !this.hasRedSweater() && !this.hasWhiteSweater() && !this.hasYellowSweater()) {
        	this.resetSweaterColor();
        	this.resetBandColor();
        	this.setHasLimeSweater(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Blocks.MAGENTA_WOOL.asItem() && !this.hasBlackSweater()  && !this.hasBlueSweater() && !this.hasBrownSweater() && !this.hasCyanSweater() && !this.hasGraySweater() && !this.hasGreenSweater() && !this.hasLightBlueSweater() && !this.hasLightGraySweater() && !this.hasLimeSweater() && !this.hasMagentaSweater() && !this.hasOrangeSweater() && !this.hasPinkSweater() && !this.hasPurpleSweater() && !this.hasRedSweater() && !this.hasWhiteSweater() && !this.hasYellowSweater()) {
        	this.resetSweaterColor();
        	this.resetBandColor();
        	this.setHasMagentaSweater(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Blocks.ORANGE_WOOL.asItem() && !this.hasBlackSweater()  && !this.hasBlueSweater() && !this.hasBrownSweater() && !this.hasCyanSweater() && !this.hasGraySweater() && !this.hasGreenSweater() && !this.hasLightBlueSweater() && !this.hasLightGraySweater() && !this.hasLimeSweater() && !this.hasMagentaSweater() && !this.hasOrangeSweater() && !this.hasPinkSweater() && !this.hasPurpleSweater() && !this.hasRedSweater() && !this.hasWhiteSweater() && !this.hasYellowSweater()) {
        	this.resetSweaterColor();
        	this.resetBandColor();
        	this.setHasOrangeSweater(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Blocks.PINK_WOOL.asItem() && !this.hasBlackSweater()  && !this.hasBlueSweater() && !this.hasBrownSweater() && !this.hasCyanSweater() && !this.hasGraySweater() && !this.hasGreenSweater() && !this.hasLightBlueSweater() && !this.hasLightGraySweater() && !this.hasLimeSweater() && !this.hasMagentaSweater() && !this.hasOrangeSweater() && !this.hasPinkSweater() && !this.hasPurpleSweater() && !this.hasRedSweater() && !this.hasWhiteSweater() && !this.hasYellowSweater()) {
        	this.resetSweaterColor();
        	this.resetBandColor();
        	this.setHasPinkSweater(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Blocks.PURPLE_WOOL.asItem() && !this.hasBlackSweater()  && !this.hasBlueSweater() && !this.hasBrownSweater() && !this.hasCyanSweater() && !this.hasGraySweater() && !this.hasGreenSweater() && !this.hasLightBlueSweater() && !this.hasLightGraySweater() && !this.hasLimeSweater() && !this.hasMagentaSweater() && !this.hasOrangeSweater() && !this.hasPinkSweater() && !this.hasPurpleSweater() && !this.hasRedSweater() && !this.hasWhiteSweater() && !this.hasYellowSweater()) {
        	this.resetSweaterColor();
        	this.resetBandColor();
        	this.setHasPurpleSweater(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Blocks.RED_WOOL.asItem() && !this.hasBlackSweater()  && !this.hasBlueSweater() && !this.hasBrownSweater() && !this.hasCyanSweater() && !this.hasGraySweater() && !this.hasGreenSweater() && !this.hasLightBlueSweater() && !this.hasLightGraySweater() && !this.hasLimeSweater() && !this.hasMagentaSweater() && !this.hasOrangeSweater() && !this.hasPinkSweater() && !this.hasPurpleSweater() && !this.hasRedSweater() && !this.hasWhiteSweater() && !this.hasYellowSweater()) {
        	this.resetSweaterColor();
        	this.resetBandColor();
        	this.setHasRedSweater(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Blocks.WHITE_WOOL.asItem() && !this.hasBlackSweater()  && !this.hasBlueSweater() && !this.hasBrownSweater() && !this.hasCyanSweater() && !this.hasGraySweater() && !this.hasGreenSweater() && !this.hasLightBlueSweater() && !this.hasLightGraySweater() && !this.hasLimeSweater() && !this.hasMagentaSweater() && !this.hasOrangeSweater() && !this.hasPinkSweater() && !this.hasPurpleSweater() && !this.hasRedSweater() && !this.hasWhiteSweater() && !this.hasYellowSweater()) {
        	this.resetSweaterColor();
        	this.resetBandColor();
        	this.setHasWhiteSweater(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Blocks.YELLOW_WOOL.asItem() && !this.hasBlackSweater()  && !this.hasBlueSweater() && !this.hasBrownSweater() && !this.hasCyanSweater() && !this.hasGraySweater() && !this.hasGreenSweater() && !this.hasLightBlueSweater() && !this.hasLightGraySweater() && !this.hasLimeSweater() && !this.hasMagentaSweater() && !this.hasOrangeSweater() && !this.hasPinkSweater() && !this.hasPurpleSweater() && !this.hasRedSweater() && !this.hasWhiteSweater() && !this.hasYellowSweater()) {
        	this.resetSweaterColor();
        	this.resetBandColor();
        	this.setHasYellowSweater(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && this.hasBlackSweater() && item == Items.SHEARS) {
        	this.resetSweaterColor();
        	this.resetBandColor();
        	this.setHasRedBand(true);
            this.world.playMovingSound((PlayerEntity)null, this, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.PLAYERS, 1.0F, 1.0F);
			this.world.addEntity(new ItemEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), new ItemStack(Blocks.BLACK_WOOL.asItem())));
            itemstack.damageItem(1, playerEntity, (p_213613_1_) -> {
               p_213613_1_.sendBreakAnimation(hand);
            });
        } else if (this.isTamed() && this.isOwner(playerEntity) && this.hasBlueSweater() && item == Items.SHEARS) {
        	this.resetSweaterColor();
        	this.resetBandColor();
        	this.setHasRedBand(true);
            this.world.playMovingSound((PlayerEntity)null, this, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.PLAYERS, 1.0F, 1.0F);
			this.world.addEntity(new ItemEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), new ItemStack(Blocks.BLUE_WOOL.asItem())));
            itemstack.damageItem(1, playerEntity, (p_213613_1_) -> {
               p_213613_1_.sendBreakAnimation(hand);
            });
        } else if (this.isTamed() && this.isOwner(playerEntity) && this.hasBrownSweater() && item == Items.SHEARS) {
        	this.resetSweaterColor();
        	this.resetBandColor();
        	this.setHasRedBand(true);
            this.world.playMovingSound((PlayerEntity)null, this, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.PLAYERS, 1.0F, 1.0F);
			this.world.addEntity(new ItemEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), new ItemStack(Blocks.BROWN_WOOL.asItem())));
            itemstack.damageItem(1, playerEntity, (p_213613_1_) -> {
               p_213613_1_.sendBreakAnimation(hand);
            });
        } else if (this.isTamed() && this.isOwner(playerEntity) && this.hasCyanSweater() && item == Items.SHEARS) {
        	this.resetSweaterColor();
        	this.resetBandColor();
        	this.setHasRedBand(true);
            this.world.playMovingSound((PlayerEntity)null, this, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.PLAYERS, 1.0F, 1.0F);
			this.world.addEntity(new ItemEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), new ItemStack(Blocks.CYAN_WOOL.asItem())));
            itemstack.damageItem(1, playerEntity, (p_213613_1_) -> {
               p_213613_1_.sendBreakAnimation(hand);
            });
        } else if (this.isTamed() && this.isOwner(playerEntity) && this.hasGraySweater() && item == Items.SHEARS) {
        	this.resetSweaterColor();
        	this.resetBandColor();
        	this.setHasRedBand(true);
            this.world.playMovingSound((PlayerEntity)null, this, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.PLAYERS, 1.0F, 1.0F);
			this.world.addEntity(new ItemEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), new ItemStack(Blocks.GRAY_WOOL.asItem())));
            itemstack.damageItem(1, playerEntity, (p_213613_1_) -> {
               p_213613_1_.sendBreakAnimation(hand);
            });
        } else if (this.isTamed() && this.isOwner(playerEntity) && this.hasGreenSweater() && item == Items.SHEARS) {
        	this.resetSweaterColor();
        	this.resetBandColor();
        	this.setHasRedBand(true);
            this.world.playMovingSound((PlayerEntity)null, this, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.PLAYERS, 1.0F, 1.0F);
			this.world.addEntity(new ItemEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), new ItemStack(Blocks.GREEN_WOOL.asItem())));
            itemstack.damageItem(1, playerEntity, (p_213613_1_) -> {
               p_213613_1_.sendBreakAnimation(hand);
            });
        } else if (this.isTamed() && this.isOwner(playerEntity) && this.hasLightBlueSweater() && item == Items.SHEARS) {
        	this.resetSweaterColor();
        	this.resetBandColor();
        	this.setHasRedBand(true);
            this.world.playMovingSound((PlayerEntity)null, this, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.PLAYERS, 1.0F, 1.0F);
			this.world.addEntity(new ItemEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), new ItemStack(Blocks.LIGHT_BLUE_WOOL.asItem())));
            itemstack.damageItem(1, playerEntity, (p_213613_1_) -> {
               p_213613_1_.sendBreakAnimation(hand);
            });
        } else if (this.isTamed() && this.isOwner(playerEntity) && this.hasLightGraySweater() && item == Items.SHEARS) {
        	this.resetSweaterColor();
        	this.resetBandColor();
        	this.setHasRedBand(true);
            this.world.playMovingSound((PlayerEntity)null, this, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.PLAYERS, 1.0F, 1.0F);
			this.world.addEntity(new ItemEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), new ItemStack(Blocks.LIGHT_GRAY_WOOL.asItem())));
            itemstack.damageItem(1, playerEntity, (p_213613_1_) -> {
               p_213613_1_.sendBreakAnimation(hand);
            });
        } else if (this.isTamed() && this.isOwner(playerEntity) && this.hasLimeSweater() && item == Items.SHEARS) {
        	this.resetSweaterColor();
        	this.resetBandColor();
        	this.setHasRedBand(true);
            this.world.playMovingSound((PlayerEntity)null, this, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.PLAYERS, 1.0F, 1.0F);
			this.world.addEntity(new ItemEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), new ItemStack(Blocks.LIME_WOOL.asItem())));
            itemstack.damageItem(1, playerEntity, (p_213613_1_) -> {
               p_213613_1_.sendBreakAnimation(hand);
            });
        } else if (this.isTamed() && this.isOwner(playerEntity) && this.hasMagentaSweater() && item == Items.SHEARS) {
        	this.resetSweaterColor();
        	this.resetBandColor();
        	this.setHasRedBand(true);
            this.world.playMovingSound((PlayerEntity)null, this, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.PLAYERS, 1.0F, 1.0F);
			this.world.addEntity(new ItemEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), new ItemStack(Blocks.MAGENTA_WOOL.asItem())));
            itemstack.damageItem(1, playerEntity, (p_213613_1_) -> {
               p_213613_1_.sendBreakAnimation(hand);
            });
        } else if (this.isTamed() && this.isOwner(playerEntity) && this.hasOrangeSweater() && item == Items.SHEARS) {
        	this.resetSweaterColor();
        	this.resetBandColor();
        	this.setHasRedBand(true);
            this.world.playMovingSound((PlayerEntity)null, this, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.PLAYERS, 1.0F, 1.0F);
			this.world.addEntity(new ItemEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), new ItemStack(Blocks.ORANGE_WOOL.asItem())));
            itemstack.damageItem(1, playerEntity, (p_213613_1_) -> {
               p_213613_1_.sendBreakAnimation(hand);
            });
        } else if (this.isTamed() && this.isOwner(playerEntity) && this.hasPinkSweater() && item == Items.SHEARS) {
        	this.resetSweaterColor();
        	this.resetBandColor();
        	this.setHasRedBand(true);
            this.world.playMovingSound((PlayerEntity)null, this, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.PLAYERS, 1.0F, 1.0F);
			this.world.addEntity(new ItemEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), new ItemStack(Blocks.PINK_WOOL.asItem())));
            itemstack.damageItem(1, playerEntity, (p_213613_1_) -> {
               p_213613_1_.sendBreakAnimation(hand);
            });
        } else if (this.isTamed() && this.isOwner(playerEntity) && this.hasPurpleSweater() && item == Items.SHEARS) {
        	this.resetSweaterColor();
        	this.resetBandColor();
        	this.setHasRedBand(true);
            this.world.playMovingSound((PlayerEntity)null, this, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.PLAYERS, 1.0F, 1.0F);
			this.world.addEntity(new ItemEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), new ItemStack(Blocks.PURPLE_WOOL.asItem())));
            itemstack.damageItem(1, playerEntity, (p_213613_1_) -> {
               p_213613_1_.sendBreakAnimation(hand);
            });
        } else if (this.isTamed() && this.isOwner(playerEntity) && this.hasRedSweater() && item == Items.SHEARS) {
        	this.resetSweaterColor();
        	this.resetBandColor();
        	this.setHasRedBand(true);
            this.world.playMovingSound((PlayerEntity)null, this, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.PLAYERS, 1.0F, 1.0F);
			this.world.addEntity(new ItemEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), new ItemStack(Blocks.RED_WOOL.asItem())));
            itemstack.damageItem(1, playerEntity, (p_213613_1_) -> {
               p_213613_1_.sendBreakAnimation(hand);
            });
        } else if (this.isTamed() && this.isOwner(playerEntity) && this.hasWhiteSweater() && item == Items.SHEARS) {
        	this.resetSweaterColor();
        	this.resetBandColor();
        	this.setHasRedBand(true);
            this.world.playMovingSound((PlayerEntity)null, this, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.PLAYERS, 1.0F, 1.0F);
			this.world.addEntity(new ItemEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), new ItemStack(Blocks.WHITE_WOOL.asItem())));
            itemstack.damageItem(1, playerEntity, (p_213613_1_) -> {
               p_213613_1_.sendBreakAnimation(hand);
            });
        } else if (this.isTamed() && this.isOwner(playerEntity) && this.hasYellowSweater() && item == Items.SHEARS) {
        	this.resetSweaterColor();
        	this.resetBandColor();
        	this.setHasRedBand(true);
            this.world.playMovingSound((PlayerEntity)null, this, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.PLAYERS, 1.0F, 1.0F);
			this.world.addEntity(new ItemEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), new ItemStack(Blocks.YELLOW_WOOL.asItem())));
            itemstack.damageItem(1, playerEntity, (p_213613_1_) -> {
               p_213613_1_.sendBreakAnimation(hand);
            });
        }

        return super.func_230254_b_(playerEntity, hand);
    }

    @Override
    public void setTamedBy(PlayerEntity player) {
        super.setTamedBy(player);
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
        }
    }

    @Nullable
    @Override
    public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity ageable) {
        ChillooEntity chilloo = FrozenUpEntities.CHILLOO.get().create(this.world);
        UUID uuid = this.getOwnerId();
        if (uuid != null) {
            chilloo.setOwnerId(uuid);
            chilloo.setTamed(true);
        }
        return chilloo;
    }

    protected SoundEvent getAmbientSound() {
        return FrozenUpSounds.CHILLOO_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return FrozenUpSounds.CHILLOO_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return FrozenUpSounds.CHILLOO_DEATH.get();
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.ENTITY_SHEEP_STEP, 0.15F, 1.0F);
    }
    
    protected void registerData() {
    	super.registerData();
    	this.dataManager.register(BLACK_BAND, false);
    	this.dataManager.register(BLUE_BAND, false);
    	this.dataManager.register(BROWN_BAND, false);
    	this.dataManager.register(CYAN_BAND, false);
    	this.dataManager.register(GRAY_BAND, false);
    	this.dataManager.register(GREEN_BAND, false);
    	this.dataManager.register(LIGHT_BLUE_BAND, false);
    	this.dataManager.register(LIGHT_GRAY_BAND, false);
    	this.dataManager.register(LIME_BAND, false);
    	this.dataManager.register(MAGENTA_BAND, false);
    	this.dataManager.register(ORANGE_BAND, false);
    	this.dataManager.register(PINK_BAND, false);
    	this.dataManager.register(PURPLE_BAND, false);
    	this.dataManager.register(RED_BAND, false);
    	this.dataManager.register(WHITE_BAND, false);
    	this.dataManager.register(YELLOW_BAND, false);
    	this.dataManager.register(BLACK_SWEATER, false);
    	this.dataManager.register(BLUE_SWEATER, false);
    	this.dataManager.register(BROWN_SWEATER, false);
    	this.dataManager.register(CYAN_SWEATER, false);
    	this.dataManager.register(GRAY_SWEATER, false);
    	this.dataManager.register(GREEN_SWEATER, false);
    	this.dataManager.register(LIGHT_BLUE_SWEATER, false);
    	this.dataManager.register(LIGHT_GRAY_SWEATER, false);
    	this.dataManager.register(LIME_SWEATER, false);
    	this.dataManager.register(MAGENTA_SWEATER, false);
    	this.dataManager.register(ORANGE_SWEATER, false);
    	this.dataManager.register(PINK_SWEATER, false);
    	this.dataManager.register(PURPLE_SWEATER, false);
    	this.dataManager.register(RED_SWEATER, false);
    	this.dataManager.register(WHITE_SWEATER, false);
    	this.dataManager.register(YELLOW_SWEATER, false);
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
		this.setHasBlackBand(compound.getBoolean("BlackBand"));
		this.setHasBlueBand(compound.getBoolean("BlueBand"));
		this.setHasBrownBand(compound.getBoolean("BrownBand"));
		this.setHasCyanBand(compound.getBoolean("CyanBand"));
		this.setHasGrayBand(compound.getBoolean("GrayBand"));
		this.setHasGreenBand(compound.getBoolean("GreenBand"));
		this.setHasLightBlueBand(compound.getBoolean("LightBlueBand"));
		this.setHasLightGrayBand(compound.getBoolean("LightGrayBand"));
		this.setHasLimeBand(compound.getBoolean("LimeBand"));
		this.setHasMagentaBand(compound.getBoolean("MagentaBand"));
		this.setHasOrangeBand(compound.getBoolean("OrangeBand"));
		this.setHasPinkBand(compound.getBoolean("PinkBand"));
		this.setHasPurpleBand(compound.getBoolean("PurpleBand"));
		this.setHasRedBand(compound.getBoolean("RedBand"));
		this.setHasWhiteBand(compound.getBoolean("WhiteBand"));
		this.setHasYellowBand(compound.getBoolean("YellowBand"));
		this.setHasBlackSweater(compound.getBoolean("BlackSweater"));
		this.setHasBlueSweater(compound.getBoolean("BlueSweater"));
		this.setHasBrownSweater(compound.getBoolean("BrownSweater"));
		this.setHasCyanSweater(compound.getBoolean("CyanSweater"));
		this.setHasGraySweater(compound.getBoolean("GraySweater"));
		this.setHasGreenSweater(compound.getBoolean("GreenSweater"));
		this.setHasLightBlueSweater(compound.getBoolean("LightBlueSweater"));
		this.setHasLightGraySweater(compound.getBoolean("LightGraySweater"));
		this.setHasLimeSweater(compound.getBoolean("LimeSweater"));
		this.setHasMagentaSweater(compound.getBoolean("MagentaSweater"));
		this.setHasOrangeSweater(compound.getBoolean("OrangeSweater"));
		this.setHasPinkSweater(compound.getBoolean("PinkSweater"));
		this.setHasPurpleSweater(compound.getBoolean("PurpleSweater"));
		this.setHasRedSweater(compound.getBoolean("RedSweater"));
		this.setHasWhiteSweater(compound.getBoolean("WhiteSweater"));
		this.setHasYellowSweater(compound.getBoolean("YellowSweater"));
        if (compound.contains("FeatherLayTime")) {
            this.timeUntilNextFeather = compound.getInt("FeatherLayTime");
        }
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putBoolean("BlackBand", this.hasBlackBand());
        compound.putBoolean("BlueBand", this.hasBlueBand());
        compound.putBoolean("BrownBand", this.hasBrownBand());
        compound.putBoolean("CyanBand", this.hasCyanBand());
        compound.putBoolean("GrayBand", this.hasGrayBand());
        compound.putBoolean("GreenBand", this.hasGreenBand());
        compound.putBoolean("LightBlueBand", this.hasLightBlueBand());
        compound.putBoolean("LightGrayBand", this.hasLightGrayBand());
        compound.putBoolean("LimeBand", this.hasLimeBand());
        compound.putBoolean("MagentaBand", this.hasMagentaBand());
        compound.putBoolean("OrangeBand", this.hasOrangeBand());
        compound.putBoolean("PinkBand", this.hasPinkBand());
        compound.putBoolean("PurpleBand", this.hasPurpleBand());
        compound.putBoolean("RedBand", this.hasRedBand());
        compound.putBoolean("WhiteBand", this.hasWhiteBand());
        compound.putBoolean("YellowBand", this.hasYellowBand());
        compound.putBoolean("BlackSweater", this.hasBlackSweater());
        compound.putBoolean("BlueSweater", this.hasBlueSweater());
        compound.putBoolean("BrownSweater", this.hasBrownSweater());
        compound.putBoolean("CyanSweater", this.hasCyanSweater());
        compound.putBoolean("GraySweater", this.hasGraySweater());
        compound.putBoolean("GreenSweater", this.hasGreenSweater());
        compound.putBoolean("LightBlueSweater", this.hasLightBlueSweater());
        compound.putBoolean("LightGraySweater", this.hasLightGraySweater());
        compound.putBoolean("LimeSweater", this.hasLimeSweater());
        compound.putBoolean("MagentaSweater", this.hasMagentaSweater());
        compound.putBoolean("OrangeSweater", this.hasOrangeSweater());
        compound.putBoolean("PinkSweater", this.hasPinkSweater());
        compound.putBoolean("PurpleSweater", this.hasPurpleSweater());
        compound.putBoolean("RedSweater", this.hasRedSweater());
        compound.putBoolean("WhiteSweater", this.hasWhiteSweater());
        compound.putBoolean("YellowSweater", this.hasYellowSweater());
        compound.putInt("FeatherLayTime", this.timeUntilNextFeather);
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(FrozenUpItems.CHILLOO_SPAWN_EGG.get());
    }
}