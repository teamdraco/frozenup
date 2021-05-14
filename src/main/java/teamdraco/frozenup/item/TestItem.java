package teamdraco.frozenup.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import teamdraco.frozenup.init.FrozenUpFeatures;

public class TestItem extends Item
{
    public TestItem(Properties properties)
    {
        super(properties);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context)
    {
        World world = context.getWorld();
        if (world instanceof ServerWorld)
        {
            ServerWorld serverWorld = (ServerWorld) world;
            BlockPos growPos = context.getPos().offset(context.getFace());
            FrozenUpFeatures.ICICLE.generate(serverWorld, serverWorld.getChunkProvider().generator, world.rand, growPos, null);
            return ActionResultType.SUCCESS;
        }
        return super.onItemUse(context);
    }
}
