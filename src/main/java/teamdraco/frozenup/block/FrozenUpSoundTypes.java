package teamdraco.frozenup.block;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraftforge.common.util.ForgeSoundType;
import teamdraco.frozenup.init.FrozenUpSoundEvents;

public class FrozenUpSoundTypes {
    public static final SoundType CHILLOO_FEATHER_BLOCK = new ForgeSoundType(
        1F, 1F,
        () -> FrozenUpSoundEvents.BLOCK_CHILLOO_FEATHER_BLOCK_BREAK,
        () -> SoundEvents.WOOL_STEP,
        () -> FrozenUpSoundEvents.BLOCK_CHILLOO_FEATHER_BLOCK_PLACE,
        () -> SoundEvents.WOOL_HIT,
        () -> SoundEvents.WOOL_FALL
    );
}
