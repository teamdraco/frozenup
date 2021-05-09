package teamdraco.frozenup.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import teamdraco.frozenup.block.MugBlock;
import teamdraco.frozenup.util.EffectUtil;

public class MilkMugItem extends AbstractDrinkableMugItem {
    public MilkMugItem(MugBlock block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void applyEffects(ItemStack stack, World world, LivingEntity livingEntity) {
        EffectUtil.removeEffectsAsMilk(livingEntity);
    }
}
