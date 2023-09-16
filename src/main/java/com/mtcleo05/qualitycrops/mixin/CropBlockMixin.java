package com.mtcleo05.qualitycrops.mixin;

import com.mtcleo05.qualitycrops.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(CropBlock.class)
public class CropBlockMixin extends BushBlock implements BonemealableBlock {

    @Shadow
    @Final
    public static final IntegerProperty AGE = BlockStateProperties.AGE_7;

    public CropBlockMixin(Properties properties) {
        super(properties);
    }

    @Shadow
    protected int getAge(BlockState p_52306_) {
        return p_52306_.getValue(this.getAgeProperty());
    }

    @Shadow
    public BlockState getStateForAge(int p_52290_) {
        return this.defaultBlockState().setValue(this.getAgeProperty(), Integer.valueOf(p_52290_));
    }

    @Shadow
    public boolean isValidBonemealTarget(BlockGetter p_52258_, BlockPos p_52259_, BlockState p_52260_, boolean p_52261_) {
        return !this.isMaxAge(p_52260_);
    }

    @Shadow
    public boolean isBonemealSuccess(Level p_221045_, RandomSource p_221046_, BlockPos p_221047_, BlockState p_221048_) {
        return true;
    }

    @Shadow
    public void performBonemeal(ServerLevel p_221040_, RandomSource p_221041_, BlockPos p_221042_, BlockState p_221043_) {
        this.growCrops(p_221040_, p_221042_, p_221043_);
    }

    @Shadow
    protected int getBonemealAgeIncrease(Level p_52262_) {
        return Mth.nextInt(p_52262_.random, 2, 5);
    }

    @Shadow
    public void growCrops(Level p_52264_, BlockPos p_52265_, BlockState p_52266_) {
        int i = this.getAge(p_52266_) + this.getBonemealAgeIncrease(p_52264_);
        int j = this.getMaxAge();
        if (i > j) {
            i = j;
        }

        p_52264_.setBlock(p_52265_, this.getStateForAge(i), 2);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter blockGetter, BlockPos pos) {
        return state.is(Blocks.FARMLAND) || state.is(ModBlocks.IRON_FARMLAND.get()) || state.is(ModBlocks.GOLD_FARMLAND.get()) || state.is(ModBlocks.DIAMOND_FARMLAND.get()) ;
    }
    @Shadow
    public IntegerProperty getAgeProperty() {
        return AGE;
    }
    @Shadow
    public int getMaxAge() {
        return 7;
    }
    @Shadow
    public boolean isMaxAge(BlockState p_52308_) {
        return p_52308_.getValue(this.getAgeProperty()) >= this.getMaxAge();
    }
    @Shadow
    public boolean isRandomlyTicking(BlockState p_52288_) {
        return !this.isMaxAge(p_52288_);
    }
}
