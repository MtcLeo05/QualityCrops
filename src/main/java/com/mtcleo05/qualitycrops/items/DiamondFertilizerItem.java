package com.mtcleo05.qualitycrops.items;


import com.mtcleo05.qualitycrops.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;

public class DiamondFertilizerItem extends Item {
    public DiamondFertilizerItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        Level level = useOnContext.getLevel();
        BlockPos pos = useOnContext.getClickedPos();

        Block block = level.getBlockState(pos).getBlock();

        List<Block> acceptedBlocks = List.of(Blocks.FARMLAND, Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.DIRT_PATH, ModBlocks.GOLD_FARMLAND.get(), ModBlocks.IRON_FARMLAND.get());

        if (acceptedBlocks.contains(block) && level instanceof ServerLevel) {
            level.setBlock(pos, ModBlocks.DIAMOND_FARMLAND.get().defaultBlockState(), 1);
            useOnContext.getItemInHand().shrink(1);

            if (block == ModBlocks.IRON_FARMLAND.get()) {
                ItemEntity item = new ItemEntity(level, pos.getX(), pos.getY() + 1, pos.getZ(), new ItemStack(ModItems.IRON_FERTILIZER.get()));

                item.spawnAtLocation(ModItems.IRON_FERTILIZER.get());
            }

            if (block == ModBlocks.GOLD_FARMLAND.get()) {
                ItemEntity item = new ItemEntity(level, pos.getX(), pos.getY() + 1, pos.getZ(), new ItemStack(ModItems.GOLD_FERTILIZER.get()));

                item.spawnAtLocation(ModItems.GOLD_FERTILIZER.get());
            }
            return InteractionResult.CONSUME;
        }

        return super.useOn(useOnContext);
    }
}
