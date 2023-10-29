package com.mtcleo05.qualitycrops.blocks;

import com.mtcleo05.qualitycrops.items.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.FarmlandWaterManager;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

public class IronFarmland extends FarmBlock {
    public IronFarmland(Properties properties) {
        super(properties);
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (!level.isClientSide && ForgeHooks.onFarmlandTrample(level, pos, Blocks.DIRT.defaultBlockState(), fallDistance, entity)) {
            turnToDirt(state, level, pos);

            ItemEntity item = new ItemEntity(level, pos.getX(), pos.getY() + 1, pos.getZ(), new ItemStack(ModItems.IRON_FERTILIZER.get()));

            item.spawnAtLocation(ModItems.IRON_FERTILIZER.get());
        }
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
        PlantType type = plantable.getPlantType(world, pos.relative(facing));
        return type == PlantType.CROP || type == PlantType.PLAINS || type == PlantType.WATER;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int moisture = state.getValue(MOISTURE);

        if (!isNearWater(level, pos) && !level.isRainingAt(pos.above())) {
            if (moisture > 0) {
                level.setBlock(pos, state.setValue(MOISTURE, moisture - 1), 2);
            }
        } else if (moisture < 7) {
            level.setBlock(pos, state.setValue(MOISTURE, 7), 2);
        }
    }

    @Override
    public boolean isFertile(BlockState state, BlockGetter level, BlockPos pos) {
        return state.getValue(MOISTURE) > 0;
    }

    private static boolean isNearWater(LevelReader levelReader, BlockPos pos) {
        BlockState state = levelReader.getBlockState(pos);
        for(BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-4, 0, -4), pos.offset(4, 1, 4))) {
            if (state.canBeHydrated(levelReader, pos, levelReader.getFluidState(blockpos), blockpos)) {
                return true;
            }
        }

        return FarmlandWaterManager.hasBlockWaterTicket(levelReader, pos);
    }
}
