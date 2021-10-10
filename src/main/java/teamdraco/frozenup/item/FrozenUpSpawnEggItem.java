package teamdraco.frozenup.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.common.util.NonNullSupplier;

@SuppressWarnings("unused")
public class FrozenUpSpawnEggItem extends SpawnEggItem {
    public static final List<FrozenUpSpawnEggItem> UNADDED_EGGS = new ArrayList<>();
    private final Lazy<? extends EntityType<?>> entityTypeSupplier;

    public FrozenUpSpawnEggItem(final NonNullSupplier<? extends EntityType<?>> entityTypeSupplier, final int primaryColour, final int secondaryColour, final Item.Properties properties) {
        super(entityTypeSupplier.get(), primaryColour, secondaryColour, properties);
        this.entityTypeSupplier = Lazy.of(entityTypeSupplier::get);
        UNADDED_EGGS.add(this);
    }

    @SuppressWarnings("ConstantConditions")
    public FrozenUpSpawnEggItem(final RegistryObject<? extends EntityType<?>> entityTypeSupplier, final int primaryColour, final int secondaryColour, final Item.Properties properties) {
        super(null, primaryColour, secondaryColour, properties);
        this.entityTypeSupplier = Lazy.of(entityTypeSupplier);
        UNADDED_EGGS.add(this);
    }

    public static void initSpawnEggs() {
        final Map<EntityType<?>, SpawnEggItem> EGGS = ObfuscationReflectionHelper.getPrivateValue(SpawnEggItem.class, null, "field_195987_b");
        DefaultDispenseItemBehavior dispenseBehaviour = new DefaultDispenseItemBehavior() {
            @Override
            protected ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
                Direction direction = source.getBlockState().get(DispenserBlock.FACING);
                EntityType<?> type = ((SpawnEggItem) stack.getItem()).getType(stack.getTag());
                type.spawn(source.getWorld(), stack, null, source.getBlockPos().offset(direction), SpawnReason.DISPENSER, direction != Direction.UP, false);
                stack.shrink(1);
                return stack;
            }
        };

        if (EGGS != null) {
            for (final SpawnEggItem spawnEgg : UNADDED_EGGS) {
                EGGS.put(spawnEgg.getType(null), spawnEgg);
                DispenserBlock.registerDispenseBehavior(spawnEgg, dispenseBehaviour);
            }
        }
        UNADDED_EGGS.clear();
    }

    @Override
    public EntityType<?> getType(CompoundNBT nbt) {
        return this.entityTypeSupplier.get();
    }
}
