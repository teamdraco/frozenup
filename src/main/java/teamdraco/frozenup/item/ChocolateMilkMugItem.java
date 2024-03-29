package teamdraco.frozenup.item;

import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class ChocolateMilkMugItem extends MilkMugItem {
    public ChocolateMilkMugItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, LivingEntity user) {
        ItemStack rstack = super.onItemUseFinish(stack, world, user);

        if (!world.isRemote) {
            user.addPotionEffect(new EffectInstance(Effects.REGENERATION, 200, 0));
            user.addPotionEffect(new EffectInstance(Effects.SPEED, 600, 1));
        }

        return rstack;
    }
}
