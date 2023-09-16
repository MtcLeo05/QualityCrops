package com.mtcleo05.qualitycrops.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class AddVariableQualityModifier extends LootModifier {

    public static final Supplier<Codec<AddVariableQualityModifier>> CODEC = Suppliers.memoize(()
            -> RecordCodecBuilder.create(inst -> codecStart(inst)
            .and(ForgeRegistries.ITEMS.getCodec().fieldOf("ironItem").forGetter(m -> m.ironItem))
            .and(ForgeRegistries.ITEMS.getCodec().fieldOf("goldItem").forGetter(m -> m.goldItem))
            .and(ForgeRegistries.ITEMS.getCodec().fieldOf("diamondItem").forGetter(m -> m.diamondItem))
            .and(Codec.INT.optionalFieldOf("itemMin", 1).forGetter(m -> m.itemMin))
            .and(Codec.INT.optionalFieldOf("itemMax", 1).forGetter(m -> m.itemMax))
            .apply(inst, AddVariableQualityModifier::new)));

    private final Item ironItem;
    private final Item goldItem;
    private final Item diamondItem;
    private final int itemMin;
    private final int itemMax;

    protected AddVariableQualityModifier(LootItemCondition[] conditionsIn, Item ironItem, Item goldItem, Item diamondItem, int itemMin, int itemMax) {
        super(conditionsIn);
        this.ironItem = ironItem;
        this.goldItem = goldItem;
        this.diamondItem = diamondItem;
        this.itemMin = itemMin;
        this.itemMax = itemMax;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {

        int value = context.getRandom().nextIntBetweenInclusive(itemMin, itemMax);

        if(context.getRandom().nextFloat() <= 0.05f) {
            generatedLoot.add(new ItemStack(diamondItem, value));
        } else if (context.getRandom().nextFloat() <= 0.10f) {
            generatedLoot.add(new ItemStack(goldItem, value));
        } else if (context.getRandom().nextFloat() <= 0.15f) {
            generatedLoot.add(new ItemStack(ironItem, value));
        }

        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }

}
