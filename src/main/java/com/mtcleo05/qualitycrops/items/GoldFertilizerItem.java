package com.mtcleo05.qualitycrops.items;


import com.mtcleo05.qualitycrops.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;


public class GoldFertilizerItem extends Item {
    public GoldFertilizerItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {

        Level level = useOnContext.getLevel();
        BlockPos pos = useOnContext.getClickedPos();

        Block block = level.getBlockState(pos).getBlock();

        List<Block> acceptedBlocks = List.of(Blocks.FARMLAND, Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.DIRT_PATH, ModBlocks.DIAMOND_FARMLAND.get(), ModBlocks.IRON_FARMLAND.get());

        if (acceptedBlocks.contains(block)) {
            level.setBlock(pos, ModBlocks.GOLD_FARMLAND.get().defaultBlockState(), 1);
            useOnContext.getItemInHand().shrink(1);

            if (block == ModBlocks.DIAMOND_FARMLAND.get()) {
                ItemEntity item = new ItemEntity(level, pos.getX(), pos.getY() + 1, pos.getZ(), new ItemStack(ModItems.DIAMOND_FERTILIZER.get()));

                item.spawnAtLocation(ModItems.DIAMOND_FERTILIZER.get());
            }

            if (block == ModBlocks.IRON_FARMLAND.get()) {
                ItemEntity item = new ItemEntity(level, pos.getX(), pos.getY() + 1, pos.getZ(), new ItemStack(ModItems.IRON_FERTILIZER.get()));

                item.spawnAtLocation(ModItems.IRON_FERTILIZER.get());
            }
        }
        return super.useOn(useOnContext);
    }
}
