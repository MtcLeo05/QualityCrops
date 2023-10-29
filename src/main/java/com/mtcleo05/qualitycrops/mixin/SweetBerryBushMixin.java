package com.mtcleo05.qualitycrops.mixin;

import com.mtcleo05.qualitycrops.items.ModCrops;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SweetBerryBushBlock.class)
public abstract class SweetBerryBushMixin {
    @Unique private Level qualityCrops$storedLevel;

    @Inject(method = "use", at = @At(value = "HEAD"))
    private void getLevel(final BlockState state, final Level level, final BlockPos position, final Player player, final InteractionHand hand, final BlockHitResult hitResult, final CallbackInfoReturnable<InteractionResult> callback) {
        qualityCrops$storedLevel = level;
    }

    @ModifyArg(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;<init>(Lnet/minecraft/world/level/ItemLike;I)V"))
    private ItemLike getRarityItem(final ItemLike original) {
        return qualityCrops$itemToPop(qualityCrops$storedLevel);
    }

    @Unique
    public Item qualityCrops$itemToPop(Level level) {
        if(level.getRandom().nextFloat() <= 0.05f) {
            return ModCrops.SWEET_BERRIES[2].get();
        } else if (level.getRandom().nextFloat() <= 0.10f) {
            return ModCrops.SWEET_BERRIES[1].get();
        } else if (level.getRandom().nextFloat() <= 0.15f) {
            return ModCrops.SWEET_BERRIES[0].get();
        }

        return Items.SWEET_BERRIES;
    }
}
