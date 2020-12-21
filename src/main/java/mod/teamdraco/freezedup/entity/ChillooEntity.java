package mod.teamdraco.freezedup.entity;

import mod.teamdraco.freezedup.init.FreezedUpEntities;
import mod.teamdraco.freezedup.init.FreezedUpItems;
import mod.teamdraco.freezedup.init.FreezedUpSounds;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.ForgeEventFactory;

import javax.annotation.Nullable;
import java.util.UUID;

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
        if (item == FreezedUpItems.FROZEN_TRUFFLE.get() && !this.isTamed()) {
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

        if (this.isOwner(playerEntity) && !this.isBreedingItem(itemstack) && this.isTamed() && item != Items.BLACK_DYE && item != Items.BLUE_DYE && item != Items.BROWN_DYE && item != Items.CYAN_DYE && item != Items.GRAY_DYE && item != Items.GREEN_DYE && item != Items.LIGHT_BLUE_DYE && item != Items.LIGHT_GRAY_DYE && item != Items.LIME_DYE && item != Items.MAGENTA_DYE && item != Items.ORANGE_DYE && item != Items.PINK_DYE && item != Items.PURPLE_DYE && item != Items.RED_DYE && item != Items.WHITE_DYE && item != Items.YELLOW_DYE) {
            func_233687_w_(!isEntitySleeping());
            this.isJumping = false;
            this.navigator.clearPath();

            return ActionResultType.SUCCESS;
        }else if (this.isTamed() && this.isOwner(playerEntity) && item == Items.BLACK_DYE && !this.hasBlackBand()) {
        	this.resetBandColor();
        	this.setHasBlackBand(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Items.BLUE_DYE && !this.hasBlueBand()) {
        	this.resetBandColor();
        	this.setHasBlueBand(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Items.BROWN_DYE && !this.hasBrownBand()) {
        	this.resetBandColor();
        	this.setHasBrownBand(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Items.CYAN_DYE && !this.hasCyanBand()) {
        	this.resetBandColor();
        	this.setHasCyanBand(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Items.GRAY_DYE && !this.hasGrayBand()) {
        	this.resetBandColor();
        	this.setHasGrayBand(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Items.GREEN_DYE && !this.hasGreenBand()) {
        	this.resetBandColor();
        	this.setHasGreenBand(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Items.LIGHT_BLUE_DYE && !this.hasLightBlueBand()) {
        	this.resetBandColor();
        	this.setHasLightBlueBand(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Items.LIGHT_GRAY_DYE && !this.hasLightGrayBand()) {
        	this.resetBandColor();
        	this.setHasLightGrayBand(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Items.LIME_DYE && !this.hasLimeBand()) {
        	this.resetBandColor();
        	this.setHasLimeBand(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Items.MAGENTA_DYE && !this.hasMagentaBand()) {
        	this.resetBandColor();
        	this.setHasMagentaBand(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Items.ORANGE_DYE && !this.hasOrangeBand()) {
        	this.resetBandColor();
        	this.setHasOrangeBand(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Items.PINK_DYE && !this.hasPinkBand()) {
        	this.resetBandColor();
        	this.setHasPinkBand(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Items.PURPLE_DYE && !this.hasPurpleBand()) {
        	this.resetBandColor();
        	this.setHasPurpleBand(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Items.RED_DYE && !this.hasRedBand()) {
        	this.resetBandColor();
        	this.setHasRedBand(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Items.WHITE_DYE && !this.hasWhiteBand()) {
        	this.resetBandColor();
        	this.setHasWhiteBand(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
        } else if (this.isTamed() && this.isOwner(playerEntity) && item == Items.YELLOW_DYE && !this.hasYellowBand()) {
        	this.resetBandColor();
        	this.setHasYellowBand(true);
            itemstack.shrink(1);
            return ActionResultType.SUCCESS;
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
                this.entityDropItem(FreezedUpItems.CHILLOO_FEATHER.get());
                this.timeUntilNextFeather = this.rand.nextInt(10000) + 5000;
            }
        }
    }

    @Nullable
    @Override
    public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity ageable) {
        ChillooEntity chilloo = FreezedUpEntities.CHILLOO.get().create(this.world);
        UUID uuid = this.getOwnerId();
        if (uuid != null) {
            chilloo.setOwnerId(uuid);
            chilloo.setTamed(true);
        }
        return chilloo;
    }

    protected SoundEvent getAmbientSound() {
        return FreezedUpSounds.CHILLOO_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return FreezedUpSounds.CHILLOO_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return FreezedUpSounds.CHILLOO_DEATH.get();
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
        compound.putInt("FeatherLayTime", this.timeUntilNextFeather);
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(FreezedUpItems.CHILLOO_SPAWN_EGG.get());
    }
}