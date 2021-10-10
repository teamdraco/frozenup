package teamdraco.frozenup.item;

import teamdraco.frozenup.util.Util;

public class TruffleHotChocolateMugItem extends AbstractDrinkableMugItem {
    public TruffleHotChocolateMugItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, LivingEntity user) {
        Util.removeEffects(user, instance -> instance.getPotion().getEffectType() == EffectType.HARMFUL);
        return super.onItemUseFinish(stack, world, user);
    }
}
