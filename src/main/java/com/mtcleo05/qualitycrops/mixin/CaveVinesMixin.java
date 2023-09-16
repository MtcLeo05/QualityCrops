package com.mtcleo05.qualitycrops.mixin;

import com.mtcleo05.qualitycrops.items.ModCrops;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.spongepowered.asm.mixin.*;

@Mixin(CaveVines.class)
public interface CaveVinesMixin {

    @Shadow
    @Final
    BooleanProperty BERRIES = BlockStateProperties.BERRIES;

    /**
     * @author MtcLeo05
     * @reason add chance for quality
     */
    @Overwrite
    static InteractionResult use(BlockState state, Level level, BlockPos pos) {
        if (state.getValue(BERRIES)) {
            Block.popResource(level, pos, new ItemStack(itemToPop(level), 1));
            float f = Mth.randomBetween(level.random, 0.8F, 1.2F);
            level.playSound(null, pos, SoundEvents.CAVE_VINES_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, f);
            level.setBlock(pos, state.setValue(BERRIES, Boolean.FALSE), 2);
            return InteractionResult.sidedSuccess(level.isClientSide);
        } else {
            return InteractionResult.PASS;
        }
    }

    @Unique
    private static Item itemToPop(Level level) {

        if(level.getRandom().nextFloat() <= 0.05f) {
            return ModCrops.GLOW_BERRIES[2].get();
        } else if (level.getRandom().nextFloat() <= 0.10f) {
            return ModCrops.GLOW_BERRIES[1].get();
        } else if (level.getRandom().nextFloat() <= 0.15f) {
            return ModCrops.GLOW_BERRIES[0].get();
        }

        return Items.GLOW_BERRIES;
    }
}
