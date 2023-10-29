package com.mtcleo05.qualitycrops.mixin;

import com.mtcleo05.qualitycrops.items.ModCrops;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Block.class)
public abstract class BlockMixin {
    @ModifyVariable(method = "popResource(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/item/ItemStack;)V", at = @At("HEAD"), argsOnly = true)
    private static ItemStack applyRarity(final ItemStack itemStack, /* Method arguments: */ final Level level) {
        if (itemStack.is(Items.GLOW_BERRIES)) {
            return new ItemStack(qualityCrops$itemToPop(level), itemStack.getCount());
        }

        return itemStack;
    }

    @Unique
    private static Item qualityCrops$itemToPop(final Level level) {
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
