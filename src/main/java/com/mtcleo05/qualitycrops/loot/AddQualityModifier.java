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
        if(context.getRandom().nextFloat() <= 0.05f) {
            generatedLoot.add(new ItemStack(diamondItem));
        } else if (context.getRandom().nextFloat() <= 0.10f) {
            generatedLoot.add(new ItemStack(goldItem));
        } else if (context.getRandom().nextFloat() <= 0.15f) {
            generatedLoot.add(new ItemStack(ironItem));
        }

        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }

}
