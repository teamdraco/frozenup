package teamdraco.frozenup.item;

public class MilkMugItem extends AbstractDrinkableMugItem {
    public MilkMugItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, LivingEntity user) {
        user.clearActivePotions();
        return super.onItemUseFinish(stack, world, user);
    }
}
