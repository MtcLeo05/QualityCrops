package com.mtcleo05.qualitycrops.loot;

import com.google.common.base.Suppliers;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.darkhax.bookshelf.api.serialization.Serializers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Supplier;

public class AddVariableQualityModifier extends LootModifier {

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

    @NotNull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {

        int value = context.getRandom().nextInt(itemMin, itemMax + 1);

        if(context.getRandom().nextFloat() <= 0.05f) {
            generatedLoot.add(new ItemStack(diamondItem, value));
        } else if (context.getRandom().nextFloat() <= 0.10f) {
            generatedLoot.add(new ItemStack(goldItem, value));
        } else if (context.getRandom().nextFloat() <= 0.15f) {
            generatedLoot.add(new ItemStack(ironItem, value));
        }

        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<AddVariableQualityModifier> {
        @Override
        public AddVariableQualityModifier read(ResourceLocation name, JsonObject object, LootItemCondition[] conditionsIn) {

            Item ironItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(GsonHelper.getAsString(object, "ironItem")));
            Item goldItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(GsonHelper.getAsString(object, "goldItem")));
            Item diamondItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(GsonHelper.getAsString(object, "diamondItem")));

            int itemMin = Serializers.INT.fromJSONString(GsonHelper.getAsString(object, "itemMin"));
            int itemMax = Serializers.INT.fromJSONString(GsonHelper.getAsString(object, "itemMax"));

            return new AddVariableQualityModifier(conditionsIn, ironItem, goldItem, diamondItem, itemMin, itemMax);
        }

        @Override
        public JsonObject write(AddVariableQualityModifier instance) {
            JsonObject json = makeConditions(instance.conditions);
            json.addProperty("ironItem", ForgeRegistries.ITEMS.getKey(instance.ironItem).toString());
            json.addProperty("goldItem", ForgeRegistries.ITEMS.getKey(instance.goldItem).toString());
            json.addProperty("diamondItem", ForgeRegistries.ITEMS.getKey(instance.diamondItem).toString());
            return json;
        }
    }

}
