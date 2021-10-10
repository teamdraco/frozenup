package teamdraco.frozenup.client.renderer;

import java.util.function.Function;

import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamdraco.frozenup.FrozenUp;
import teamdraco.frozenup.client.model.ChillooModel;
import teamdraco.frozenup.entity.ChillooEntity;

@OnlyIn(Dist.CLIENT)
public class ChillooRenderer extends MobRenderer<ChillooEntity, ChillooModel<ChillooEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FrozenUp.MOD_ID, "textures/entity/chilloo.png");
    private static final ResourceLocation[] COLOR_TEXTURES = new ResourceLocation[32];

    public ChillooRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ChillooModel<>(), 0.5F);
    }

    @Override
    public ResourceLocation getEntityTexture(ChillooEntity entity) {
        DyeColor bandColor = entity.getBandColor();
        if (bandColor == null) {
            DyeColor sweaterColor = entity.getSweaterColor();
            if (sweaterColor == null) {
                return TEXTURE;
            }
            return getTexture(sweaterColor.ordinal() + 16, color -> new ResourceLocation(FrozenUp.MOD_ID, "textures/entity/sweaters/" + color.getTranslationKey() + ".png"));
        } else {
            return getTexture(bandColor.ordinal(), color -> new ResourceLocation(FrozenUp.MOD_ID, "textures/entity/bands/" + color.getTranslationKey() + ".png"));
        }
    }

    private ResourceLocation getTexture(int color, Function<DyeColor, ResourceLocation> colorSupplier) {
        ResourceLocation colorTexture = COLOR_TEXTURES[color];
        if (colorTexture == null) {
            colorTexture = colorSupplier.apply(DyeColor.byId(color % 16));
            COLOR_TEXTURES[color] = colorTexture;
        }
        return colorTexture;
    }
}
