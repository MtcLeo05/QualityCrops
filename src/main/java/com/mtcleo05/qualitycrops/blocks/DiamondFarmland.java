package com.mtcleo05.qualitycrops.blocks;

import com.mtcleo05.qualitycrops.items.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraftforge.common.ForgeHooks;

public class DiamondFarmland extends FarmBlock {
    public DiamondFarmland(Properties properties) {
        super(properties);
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (!level.isClientSide && ForgeHooks.onFarmlandTrample(level, pos, Blocks.DIRT.defaultBlockState(), fallDistance, entity)) { // Forge: Move logic to Entity#canTrample
            turnToDirt(state, level, pos);

            ItemEntity item = new ItemEntity(level, pos.getX(), pos.getY() + 1, pos.getZ(), new ItemStack(ModItems.DIAMOND_FERTILIZER.get()));

            item.spawnAtLocation(ModItems.DIAMOND_FERTILIZER.get());
        }
    }

    @Override
    public void randomTick(BlockState state, ServerLevel serverLevel, BlockPos pos, RandomSource randomSource) {
        super.randomTick(state, serverLevel, pos, randomSource);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
    }

}
