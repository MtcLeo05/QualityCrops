package com.mtcleo05.qualitycrops.mixin;

import com.mtcleo05.qualitycrops.items.ModCrops;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.*;

@Mixin(SweetBerryBushBlock.class)
public class SweetBerryBushMixin extends BushBlock implements BonemealableBlock {

    @Shadow
    @Final
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;

    public SweetBerryBushMixin(Properties properties) {
        super(properties);
    }

    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos pos, BlockState state, boolean p_50900_) {
        return state.getValue(AGE) < 3;
    }

    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos pos, BlockState state) {
        return true;
    }

    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos pos, BlockState state) {
        int i = Math.min(3, state.getValue(AGE) + 1);
        serverLevel.setBlock(pos, state.setValue(AGE, Integer.valueOf(i)), 2);
    }

    /**
     * @author MtcLeo05
     * @reason add chance for quality
     */
    @Overwrite
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand interactionHand, BlockHitResult hitResult) {
        int i = state.getValue(AGE);
        boolean flag = i == 3;
        if (!flag && player.getItemInHand(interactionHand).is(Items.BONE_MEAL)) {
            return InteractionResult.PASS;
        } else if (i > 1) {
            int j = 1 + level.random.nextInt(2);
            popResource(level, pos, new ItemStack(itemToPop(level), j + (flag ? 1 : 0)));
            level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
            BlockState blockstate = state.setValue(AGE, Integer.valueOf(1));
            level.setBlock(pos, blockstate, 2);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, blockstate));
            return InteractionResult.sidedSuccess(level.isClientSide);
        } else {
            return super.use(state, level, pos, player, interactionHand, hitResult);
        }
    }

    @Unique
    public Item itemToPop(Level level) {

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
