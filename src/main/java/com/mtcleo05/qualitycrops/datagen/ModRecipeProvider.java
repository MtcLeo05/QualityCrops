package com.mtcleo05.qualitycrops.datagen;

import com.mtcleo05.qualitycrops.items.ModItems;
import com.mtcleo05.qualitycrops.utils.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
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

        ShapedRecipeBuilder.shaped(ModItems.IRON_FERTILIZER.get())
                .unlockedBy("hasQuality", has(ModTags.QUALITY_IRON))
                .define('I', Items.IRON_INGOT)
                .define('Q', ModTags.QUALITY_IRON)
                .pattern("III")
                .pattern("QQQ")
                .pattern("III")
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(ModItems.GOLD_FERTILIZER.get())
                .unlockedBy("hasQuality", has(ModTags.QUALITY_GOLD))
                .define('I', Items.GOLD_INGOT)
                .define('Q', ModTags.QUALITY_GOLD)
                .define('F', ModItems.IRON_FERTILIZER.get())
                .pattern("III")
                .pattern("QFQ")
                .pattern("III")
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(ModItems.DIAMOND_FERTILIZER.get())
                .unlockedBy("hasQuality", has(ModTags.QUALITY_DIAMOND))
                .define('I', Items.DIAMOND)
                .define('Q', ModTags.QUALITY_DIAMOND)
                .define('F', ModItems.GOLD_FERTILIZER.get())
                .pattern("III")
                .pattern("QFQ")
                .pattern("III")
                .save(pFinishedRecipeConsumer);
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
