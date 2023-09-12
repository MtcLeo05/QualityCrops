package com.mtcleo05.qualitycrops.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

import static com.mtcleo05.qualitycrops.utils.Utils.getItemFromID;


public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> pFinishedRecipeConsumer) {

        /*
        List<String> allItems = List.of(
                "wheat", "red_mushroom",  "brown_mushroom", "kelp", "cocoa_beans", "carrot",
                "potato", "beetroot", "tropical_fish", "egg", "bread",
                "baked_potato", "beetroot_soup", "cod", "salmon", "cooked_cod", "cooked_salmon",
                "mushroom_stew", "rabbit_stew", "beef", "chicken", "rabbit", "mutton",
                "porkchop", "cooked_beef", "cooked_chicken", "cooked_rabbit", "cooked_mutton", "cooked_porkchop",
                "apple", "cookie", "melon_slice", "dried_kelp", "pumpkin_pie", "poisonous_potato",
                "pufferfish", "sugar", "sweet_berries", "glow_berries"
        );

        allItems.forEach((currentItem) -> {
            ShapelessRecipeBuilder.shapeless(getItemFromID("minecraft:" + currentItem))
                    .unlockedBy("ironQuality", has(getItemFromID("qualitycrops:" + currentItem, "iron")))
                    .requires(getItemFromID("qualitycrops:" + currentItem, "iron"))
                    .save(pFinishedRecipeConsumer, "qualitycrops:" + currentItem + "_from_iron");

            ShapelessRecipeBuilder.shapeless(getItemFromID("minecraft:" + currentItem))
                    .unlockedBy("goldQuality", has(getItemFromID("qualitycrops:" + currentItem, "gold")))
                    .requires(getItemFromID("qualitycrops:" + currentItem, "gold"))
                    .save(pFinishedRecipeConsumer, "qualitycrops:" + currentItem + "_from_gold");

            ShapelessRecipeBuilder.shapeless(getItemFromID("minecraft:" + currentItem))
                    .unlockedBy("diamondQuality", has(getItemFromID("qualitycrops:" + currentItem, "diamond")))
                    .requires(getItemFromID("qualitycrops:" + currentItem, "diamond"))
                    .save(pFinishedRecipeConsumer, "qualitycrops:" + currentItem + "_from_diamond");
        });
         */

        ShapelessRecipeBuilder.shapeless(getItemFromID("qualitycrops:sugar", "iron"))
                .unlockedBy("ironQuality", has(getItemFromID("qualitycrops:sugar_cane", "iron")))
                .requires(getItemFromID("qualitycrops:sugar_cane", "iron"))
                .save(pFinishedRecipeConsumer, "qualitycrops:sugar" + "_from_cane_iron");

        ShapelessRecipeBuilder.shapeless(getItemFromID("qualitycrops:sugar", "gold"))
                .unlockedBy("goldQuality", has(getItemFromID("qualitycrops:sugar_cane", "gold")))
                .requires(getItemFromID("qualitycrops:sugar_cane", "gold"))
                .save(pFinishedRecipeConsumer, "qualitycrops:sugar" + "_from_cane_gold");

        ShapelessRecipeBuilder.shapeless(getItemFromID("qualitycrops:sugar", "diamond"))
                .unlockedBy("diamondQuality", has(getItemFromID("qualitycrops:sugar_cane", "diamond")))
                .requires(getItemFromID("qualitycrops:sugar_cane", "diamond"))
                .save(pFinishedRecipeConsumer, "qualitycrops:sugar" + "_from_cane_diamond");

    }

    public static void simpleFoodRecipe(Item input, Item output, Consumer<FinishedRecipe> pFinishedRecipeConsumer){
        simpleCookingRecipe(pFinishedRecipeConsumer, "smelting", RecipeSerializer.SMELTING_RECIPE, 200, input, output, 0.35F);
        simpleCookingRecipe(pFinishedRecipeConsumer, "smoking", RecipeSerializer.SMOKING_RECIPE, 100, input, output, 0.35F);
        simpleCookingRecipe(pFinishedRecipeConsumer, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, 600, input, output, 0.35F);
    }


    public static void simpleQualityFoodRecipe(String input, String output, Consumer<FinishedRecipe> pFinishedRecipeConsumer){
        simpleFoodRecipe(getItemFromID(input + "_iron"), getItemFromID(output + "_iron"), pFinishedRecipeConsumer);
        simpleFoodRecipe(getItemFromID(input + "_gold"), getItemFromID(output + "_gold"), pFinishedRecipeConsumer);
        simpleFoodRecipe(getItemFromID(input + "_diamond"), getItemFromID(output + "_diamond"), pFinishedRecipeConsumer);
    }


}
