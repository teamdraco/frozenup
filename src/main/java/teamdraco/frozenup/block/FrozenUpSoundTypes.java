package teamdraco.frozenup.block;

import net.minecraft.block.SoundType;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.util.ForgeSoundType;
import teamdraco.frozenup.init.FrozenUpSoundEvents;

public class FrozenUpSoundTypes {
    public static final SoundType CHILLOO_FEATHER_BLOCK = new ForgeSoundType(
        1, 1,
        () -> FrozenUpSoundEvents.BLOCK_CHILLOO_FEATHER_BLOCK_BREAK,
        () -> SoundEvents.BLOCK_WOOL_STEP,
        () -> FrozenUpSoundEvents.BLOCK_CHILLOO_FEATHER_BLOCK_PLACE,
        () -> SoundEvents.BLOCK_WOOL_HIT,
        () -> SoundEvents.BLOCK_WOOL_FALL
    );
}
