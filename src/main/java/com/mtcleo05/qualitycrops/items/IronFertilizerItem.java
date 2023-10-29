package com.mtcleo05.qualitycrops.items;


import com.mtcleo05.qualitycrops.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;

public class IronFertilizerItem extends Item {
    public IronFertilizerItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {

        Level level = useOnContext.getLevel();
        BlockPos pos = useOnContext.getClickedPos();

        Block block = level.getBlockState(pos).getBlock();

        List<Block> acceptedBlocks = List.of(Blocks.FARMLAND, Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.DIRT_PATH, ModBlocks.DIAMOND_FARMLAND.get(), ModBlocks.GOLD_FARMLAND.get());

        if (acceptedBlocks.contains(block)) {
            level.setBlock(pos, ModBlocks.IRON_FARMLAND.get().defaultBlockState(), 1);
            useOnContext.getItemInHand().shrink(1);

            if (block == ModBlocks.DIAMOND_FARMLAND.get()) {
                ItemEntity item = new ItemEntity(level, pos.getX(), pos.getY() + 1, pos.getZ(), new ItemStack(ModItems.DIAMOND_FERTILIZER.get()));

                item.spawnAtLocation(ModItems.DIAMOND_FERTILIZER.get());
            }

            if (block == ModBlocks.GOLD_FARMLAND.get()) {
                ItemEntity item = new ItemEntity(level, pos.getX(), pos.getY() + 1, pos.getZ(), new ItemStack(ModItems.GOLD_FERTILIZER.get()));

                item.spawnAtLocation(ModItems.GOLD_FERTILIZER.get());
            }
        }

        return super.useOn(useOnContext);
    }
}
