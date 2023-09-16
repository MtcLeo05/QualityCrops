package com.mtcleo05.qualitycrops.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mtcleo05.qualitycrops.blocks.ModBlocks;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

import static net.minecraft.core.RegistryAccess.LOGGER;

public class AddQualityModifier extends LootModifier {


    public static final Supplier<Codec<AddQualityModifier>> CODEC = Suppliers.memoize(()
            -> RecordCodecBuilder.create(inst -> codecStart(inst).and(ForgeRegistries.ITEMS.getCodec().fieldOf("ironItem").forGetter(m -> m.ironItem))
            .and(ForgeRegistries.ITEMS.getCodec().fieldOf("goldItem").forGetter(m -> m.goldItem))
            .and(ForgeRegistries.ITEMS.getCodec().fieldOf("diamondItem").forGetter(m -> m.diamondItem))
            .apply(inst, AddQualityModifier::new)));

    private final Item ironItem;
    private final Item goldItem;
    private final Item diamondItem;

    protected AddQualityModifier(LootItemCondition[] conditionsIn, Item ironItem, Item goldItem, Item diamondItem) {
        super(conditionsIn);
        this.ironItem = ironItem;
        this.goldItem = goldItem;
        this.diamondItem = diamondItem;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {


        float diamondChance = 0.05f;
        float goldChance = diamondChance + 0.05f;
        float ironChance = goldChance + 0.05f;

        ServerLevel level = context.getLevel();
        Vec3 pos = context.getParam(LootContextParams.ORIGIN);
        BlockState state = level.getBlockState(new BlockPos(pos.x, pos.y - 1, pos.z));

        if(state.getBlock() == ModBlocks.IRON_FARMLAND.get()) {
            ironChance = 0.5f;
        }

        if(state.getBlock() == ModBlocks.GOLD_FARMLAND.get()) {
            goldChance = 0.5f;
            ironChance = 0.35f;
        }

        if(state.getBlock() == ModBlocks.DIAMOND_FARMLAND.get()) {
            diamondChance = 0.5f;
            goldChance = 0.35f;
            ironChance = 0.2f;
        }


        if (context.getRandom().nextFloat() <= diamondChance) {
            generatedLoot.add(new ItemStack(diamondItem));
        } else if (context.getRandom().nextFloat() <= goldChance) {
            generatedLoot.add(new ItemStack(goldItem));
        } else if (context.getRandom().nextFloat() <= ironChance) {
            generatedLoot.add(new ItemStack(ironItem));
        }

        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }

}
