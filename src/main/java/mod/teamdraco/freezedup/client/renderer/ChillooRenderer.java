package mod.teamdraco.freezedup.client.renderer;

import mod.teamdraco.freezedup.FreezedUp;
import mod.teamdraco.freezedup.client.model.ChillooModel;
import mod.teamdraco.freezedup.entity.ChillooEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.CowModel;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChillooRenderer extends MobRenderer<ChillooEntity, ChillooModel<ChillooEntity>> {
   private static final ResourceLocation TEXTURE = new ResourceLocation(FreezedUp.MOD_ID, "textures/entity/chilloo.png");

   public ChillooRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new ChillooModel<>(), 0.5F);
   }

   public ResourceLocation getEntityTexture(ChillooEntity entity) {
      return TEXTURE;
   }
}
