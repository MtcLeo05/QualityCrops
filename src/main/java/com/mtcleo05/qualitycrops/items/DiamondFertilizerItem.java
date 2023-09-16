package com.mtcleo05.qualitycrops.items;


import com.mtcleo05.qualitycrops.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class DiamondFertilizerItem extends Item{
    public DiamondFertilizerItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {

        Level level = useOnContext.getLevel();
        BlockPos blockpos = useOnContext.getClickedPos();

        if(level.getBlockState(blockpos).getBlock() == Blocks.FARMLAND) {
            level.setBlock(blockpos, ModBlocks.DIAMOND_FARMLAND.get().defaultBlockState(), 1);
        }

        return super.useOn(useOnContext);
    }
}
