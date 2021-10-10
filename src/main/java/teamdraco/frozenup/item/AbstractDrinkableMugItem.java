package teamdraco.frozenup.item;

import teamdraco.frozenup.init.FrozenUpItems;

public abstract class AbstractDrinkableMugItem extends BlockItem {
    public AbstractDrinkableMugItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, LivingEntity user) {
        super.onItemUseFinish(stack, world, user);
        if (!stack.isFood() && user instanceof PlayerEntity) {
            if (!((PlayerEntity) user).isCreative()) {
                stack.shrink(1);
            }
        }

        return stack.isEmpty() ? new ItemStack(FrozenUpItems.EMPTY_MUG.get()) : stack;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 32;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        return DrinkHelper.startDrinking(worldIn, playerIn, handIn);
    }
}
