package mod.teamdraco.freezedup.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;

import mod.teamdraco.freezedup.FreezedUp;
import mod.teamdraco.freezedup.client.model.ChillooModel;
import mod.teamdraco.freezedup.entity.ChillooEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChillooRenderer extends MobRenderer<ChillooEntity, ChillooModel<ChillooEntity>> {
   private static final ResourceLocation TEXTURE = new ResourceLocation(FreezedUp.MOD_ID, "textures/entity/chilloo.png");
   private static final ResourceLocation BLACK_BAND = new ResourceLocation(FreezedUp.MOD_ID, "textures/entity/bands/black.png");
   private static final ResourceLocation BLUE_BAND = new ResourceLocation(FreezedUp.MOD_ID, "textures/entity/bands/blue.png");
   private static final ResourceLocation BROWN_BAND = new ResourceLocation(FreezedUp.MOD_ID, "textures/entity/bands/brown.png");
   private static final ResourceLocation CYAN_BAND = new ResourceLocation(FreezedUp.MOD_ID, "textures/entity/bands/cyan.png");
   private static final ResourceLocation GRAY_BAND = new ResourceLocation(FreezedUp.MOD_ID, "textures/entity/bands/gray.png");
   private static final ResourceLocation GREEN_BAND = new ResourceLocation(FreezedUp.MOD_ID, "textures/entity/bands/green.png");
   private static final ResourceLocation LIGHT_BLUE_BAND = new ResourceLocation(FreezedUp.MOD_ID, "textures/entity/bands/light_blue.png");
   private static final ResourceLocation LIGHT_GRAY_BAND = new ResourceLocation(FreezedUp.MOD_ID, "textures/entity/bands/light_gray.png");
   private static final ResourceLocation LIME_BAND = new ResourceLocation(FreezedUp.MOD_ID, "textures/entity/bands/lime.png");
   private static final ResourceLocation MAGENTA_BAND = new ResourceLocation(FreezedUp.MOD_ID, "textures/entity/bands/magenta.png");
   private static final ResourceLocation ORANGE_BAND = new ResourceLocation(FreezedUp.MOD_ID, "textures/entity/bands/orange.png");
   private static final ResourceLocation PINK_BAND = new ResourceLocation(FreezedUp.MOD_ID, "textures/entity/bands/pink.png");
   private static final ResourceLocation PURPLE_BAND = new ResourceLocation(FreezedUp.MOD_ID, "textures/entity/bands/purple.png");
   private static final ResourceLocation RED_BAND = new ResourceLocation(FreezedUp.MOD_ID, "textures/entity/bands/red.png");
   private static final ResourceLocation WHITE_BAND = new ResourceLocation(FreezedUp.MOD_ID, "textures/entity/bands/white.png");
   private static final ResourceLocation YELLOW_BAND = new ResourceLocation(FreezedUp.MOD_ID, "textures/entity/bands/yellow.png");
   private static final ResourceLocation RED = new ResourceLocation(FreezedUp.MOD_ID, "textures/entity/sweaters/red.png");

   public ChillooRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new ChillooModel<>(), 0.5F);
   }

   public ResourceLocation getEntityTexture(ChillooEntity entity) {
	   if(entity.isTamed() && !entity.hasBlackBand() && !entity.hasBlueBand() && !entity.hasBrownBand() && !entity.hasCyanBand() && !entity.hasGrayBand() && !entity.hasGreenBand() && !entity.hasLightBlueBand() && !entity.hasLightGrayBand() && !entity.hasLimeBand() && !entity.hasMagentaBand() && !entity.hasOrangeBand() && !entity.hasPinkBand() && !entity.hasPurpleBand() && !entity.hasRedBand() && !entity.hasWhiteBand() && !entity.hasYellowBand()) {
		   return RED_BAND;
	   } else if(entity.isTamed() && entity.hasBlackBand()) {
		   return BLACK_BAND;
	   } else if(entity.hasBlueBand()) {
		   return BLUE_BAND;
	   } else if(entity.hasBrownBand()) {
		   return BROWN_BAND;
	   } else if(entity.hasCyanBand()) {
		   return CYAN_BAND;
	   } else if(entity.hasGrayBand()) {
		   return GRAY_BAND;
	   } else if(entity.hasGreenBand()) {
		   return GREEN_BAND;
	   } else if(entity.hasLightBlueBand()) {
		   return LIGHT_BLUE_BAND;
	   } else if(entity.hasLightGrayBand()) {
		   return LIGHT_GRAY_BAND;
	   } else if(entity.hasLimeBand()) {
		   return LIME_BAND;
	   } else if(entity.hasMagentaBand()) {
		   return MAGENTA_BAND;
	   } else if(entity.hasOrangeBand()) {
		   return ORANGE_BAND;
	   } else if(entity.hasPinkBand()) {
		   return PINK_BAND;
	   } else if (entity.hasPurpleBand()) {
		   return PURPLE_BAND;
	   } else if(entity.hasRedBand()) {
		   return RED_BAND;
	   } else if (entity.hasWhiteBand()) {
		   return WHITE_BAND;
	   } else if(entity.hasYellowBand()) {
		   return YELLOW_BAND;
	   }
	   else {
		   return TEXTURE;
	   }
   }

   @Override
   protected void preRenderCallback(ChillooEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
      super.preRenderCallback(entitylivingbaseIn, matrixStackIn, partialTickTime);
      matrixStackIn.translate(0.25, 0, -0.3f);
   }
}
