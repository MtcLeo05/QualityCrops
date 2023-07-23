package com.mtcleo05.qualitycrops.datagen;

import com.mtcleo05.qualitycrops.items.ModCrops;
import com.mtcleo05.qualitycrops.items.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        /*
        ShapelessRecipeBuilder.shapeless(getItemFromID("beetroot_soup_iron", ModItems.VANILLA_QUALITY))
                .requires(Items.BOWL)
                .requires(getItemFromID("beetroot_iron", ModCrops.CROPS))
                .requires(getItemFromID("beetroot_iron", ModCrops.CROPS))
                .requires(getItemFromID("beetroot_iron", ModCrops.CROPS))
                .requires(getItemFromID("beetroot_iron", ModCrops.CROPS))
                .requires(getItemFromID("beetroot_iron", ModCrops.CROPS))
                .requires(getItemFromID("beetroot_iron", ModCrops.CROPS))
                .unlockedBy("hasBowl", has(Items.BOWL))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(getItemFromID("beetroot_soup_gold", ModItems.VANILLA_QUALITY))
                .requires(Items.BOWL)
                .requires(getItemFromID("beetroot_gold", ModCrops.CROPS))
                .requires(getItemFromID("beetroot_gold", ModCrops.CROPS))
                .requires(getItemFromID("beetroot_gold", ModCrops.CROPS))
                .requires(getItemFromID("beetroot_gold", ModCrops.CROPS))
                .requires(getItemFromID("beetroot_gold", ModCrops.CROPS))
                .requires(getItemFromID("beetroot_gold", ModCrops.CROPS))
                .unlockedBy("hasBowl", has(Items.BOWL))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(getItemFromID("beetroot_soup_diamond", ModItems.VANILLA_QUALITY))
                .requires(Items.BOWL)
                .requires(getItemFromID("beetroot_diamond", ModCrops.CROPS))
                .requires(getItemFromID("beetroot_diamond", ModCrops.CROPS))
                .requires(getItemFromID("beetroot_diamond", ModCrops.CROPS))
                .requires(getItemFromID("beetroot_diamond", ModCrops.CROPS))
                .requires(getItemFromID("beetroot_diamond", ModCrops.CROPS))
                .requires(getItemFromID("beetroot_diamond", ModCrops.CROPS))
                .unlockedBy("hasBowl", has(Items.BOWL))
                .save(pFinishedRecipeConsumer);

        simpleQualityFoodRecipe("potato", ModCrops.CROPS, "baked_potato", ModItems.VANILLA_QUALITY, pFinishedRecipeConsumer);
        simpleQualityFoodRecipe("cod", ModItems.VANILLA_QUALITY, "cooked_cod", ModItems.VANILLA_QUALITY, pFinishedRecipeConsumer);
        simpleQualityFoodRecipe("salmon", ModItems.VANILLA_QUALITY, "cooked_salmon", ModItems.VANILLA_QUALITY, pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(getItemFromID("dough", ModItems.NEW_ITEMS), 1)
                .requires(Items.WATER_BUCKET)
                .requires(Items.WHEAT)
                .unlockedBy("hasWheat", has(Items.WHEAT))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(getItemFromID("dough_iron", ModItems.NEW_ITEMS), 1)
                .requires(Items.WATER_BUCKET)
                .requires(getItemFromID("wheat_iron", ModCrops.CROPS))
                .unlockedBy("hasIronWheat", has(getItemFromID("wheat_iron", ModCrops.CROPS)))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(getItemFromID("dough_gold", ModItems.NEW_ITEMS), 1)
                .requires(Items.WATER_BUCKET)
                .requires(getItemFromID("wheat_gold", ModCrops.CROPS))
                .unlockedBy("hasGoldWheat", has(getItemFromID("wheat_gold", ModCrops.CROPS)))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(getItemFromID("dough_diamond", ModItems.NEW_ITEMS), 1)
                .requires(Items.WATER_BUCKET)
                .requires(getItemFromID("wheat_diamond", ModCrops.CROPS))
                .unlockedBy("hasDiamondWheat", has(getItemFromID("wheat_diamond", ModCrops.CROPS)))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(getItemFromID("mushroom_stew_iron", ModItems.VANILLA_QUALITY), 1)
                .requires(Items.BOWL)
                .requires(getItemFromID("red_mushroom_iron", ModCrops.CROPS))
                .requires(getItemFromID("brown_mushroom_iron", ModCrops.CROPS))
                .unlockedBy("hasBowl", has(Items.BOWL))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(getItemFromID("mushroom_stew_gold", ModItems.VANILLA_QUALITY), 1)
                .requires(Items.BOWL)
                .requires(getItemFromID("red_mushroom_gold", ModCrops.CROPS))
                .requires(getItemFromID("brown_mushroom_gold", ModCrops.CROPS))
                .unlockedBy("hasBowl", has(Items.BOWL))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(getItemFromID("mushroom_stew_diamond", ModItems.VANILLA_QUALITY), 1)
                .requires(Items.BOWL)
                .requires(getItemFromID("red_mushroom_diamond", ModCrops.CROPS))
                .requires(getItemFromID("brown_mushroom_diamond", ModCrops.CROPS))
                .unlockedBy("hasBowl", has(Items.BOWL))
                .save(pFinishedRecipeConsumer);


        simpleQualityFoodRecipe("beef", ModItems.VANILLA_QUALITY, "cooked_beef", ModItems.VANILLA_QUALITY, pFinishedRecipeConsumer);
        simpleQualityFoodRecipe("chicken", ModItems.VANILLA_QUALITY, "cooked_chicken", ModItems.VANILLA_QUALITY, pFinishedRecipeConsumer);
        simpleQualityFoodRecipe("rabbit", ModItems.VANILLA_QUALITY, "cooked_rabbit", ModItems.VANILLA_QUALITY, pFinishedRecipeConsumer);
        simpleQualityFoodRecipe("mutton", ModItems.VANILLA_QUALITY, "cooked_mutton", ModItems.VANILLA_QUALITY, pFinishedRecipeConsumer);
        simpleQualityFoodRecipe("porkchop", ModItems.VANILLA_QUALITY, "cooked_porkchop", ModItems.VANILLA_QUALITY, pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(getItemFromID("rabbit_stew_iron", ModItems.VANILLA_QUALITY), 1)
                .requires(getItemFromID("baked_potato_iron", ModItems.VANILLA_QUALITY))
                .requires(getItemFromID("cooked_rabbit_iron", ModItems.VANILLA_QUALITY))
                .requires(Items.BOWL)
                .requires(getItemFromID("carrot_iron", ModCrops.CROPS))
                .requires(getItemFromID("red_mushroom_iron", ModCrops.CROPS))
                .unlockedBy("hasBowl", has(Items.BOWL))
                .save(pFinishedRecipeConsumer, "qualitycrops:rabbit_stew_iron_from_red_mushroom");

        ShapelessRecipeBuilder.shapeless(getItemFromID("rabbit_stew_iron", ModItems.VANILLA_QUALITY), 1)
                .requires(getItemFromID("baked_potato_iron", ModItems.VANILLA_QUALITY))
                .requires(getItemFromID("cooked_rabbit_iron", ModItems.VANILLA_QUALITY))
                .requires(Items.BOWL)
                .requires(getItemFromID("carrot_iron", ModCrops.CROPS))
                .requires(getItemFromID("brown_mushroom_iron", ModCrops.CROPS))
                .unlockedBy("hasBowl", has(Items.BOWL))
                .save(pFinishedRecipeConsumer, "qualitycrops:rabbit_stew_iron_from_brown_mushroom");

        ShapelessRecipeBuilder.shapeless(getItemFromID("rabbit_stew_gold", ModItems.VANILLA_QUALITY), 1)
                .requires(getItemFromID("baked_potato_gold", ModItems.VANILLA_QUALITY))
                .requires(getItemFromID("cooked_rabbit_gold", ModItems.VANILLA_QUALITY))
                .requires(Items.BOWL)
                .requires(getItemFromID("carrot_gold", ModCrops.CROPS))
                .requires(getItemFromID("red_mushroom_gold", ModCrops.CROPS))
                .unlockedBy("hasBowl", has(Items.BOWL))
                .save(pFinishedRecipeConsumer, "qualitycrops:rabbit_stew_gold_from_red_mushroom");

        ShapelessRecipeBuilder.shapeless(getItemFromID("rabbit_stew_gold", ModItems.VANILLA_QUALITY), 1)
                .requires(getItemFromID("baked_potato_gold", ModItems.VANILLA_QUALITY))
                .requires(getItemFromID("cooked_rabbit_gold", ModItems.VANILLA_QUALITY))
                .requires(Items.BOWL)
                .requires(getItemFromID("carrot_gold", ModCrops.CROPS))
                .requires(getItemFromID("brown_mushroom_gold", ModCrops.CROPS))
                .unlockedBy("hasBowl", has(Items.BOWL))
                .save(pFinishedRecipeConsumer, "qualitycrops:rabbit_stew_gold_from_brown_mushroom");

        ShapelessRecipeBuilder.shapeless(getItemFromID("rabbit_stew_diamond", ModItems.VANILLA_QUALITY), 1)
                .requires(getItemFromID("baked_potato_diamond", ModItems.VANILLA_QUALITY))
                .requires(getItemFromID("cooked_rabbit_diamond", ModItems.VANILLA_QUALITY))
                .requires(Items.BOWL)
                .requires(getItemFromID("carrot_diamond", ModCrops.CROPS))
                .requires(getItemFromID("red_mushroom_diamond", ModCrops.CROPS))
                .unlockedBy("hasBowl", has(Items.BOWL))
                .save(pFinishedRecipeConsumer, "qualitycrops:rabbit_stew_diamond_from_red_mushroom");

        ShapelessRecipeBuilder.shapeless(getItemFromID("rabbit_stew_diamond", ModItems.VANILLA_QUALITY), 1)
                .requires(getItemFromID("baked_potato_diamond", ModItems.VANILLA_QUALITY))
                .requires(getItemFromID("cooked_rabbit_diamond", ModItems.VANILLA_QUALITY))
                .requires(Items.BOWL)
                .requires(getItemFromID("carrot_diamond", ModCrops.CROPS))
                .requires(getItemFromID("brown_mushroom_diamond", ModCrops.CROPS))
                .unlockedBy("hasBowl", has(Items.BOWL))
                .save(pFinishedRecipeConsumer, "qualitycrops:rabbit_stew_diamond_from_brown_mushroom");

        ShapelessRecipeBuilder.shapeless(getItemFromID("pumpkin_pie_iron", ModItems.VANILLA_QUALITY), 1)
                .requires(Items.PUMPKIN)
                .requires(getItemFromID("egg_iron", ModItems.VANILLA_QUALITY))
                .requires(Items.SUGAR)
                .unlockedBy("hasPumpkin", has(Items.PUMPKIN))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(getItemFromID("pumpkin_pie_gold", ModItems.VANILLA_QUALITY), 1)
                .requires(Items.PUMPKIN)
                .requires(getItemFromID("egg_gold", ModItems.VANILLA_QUALITY))
                .requires(Items.SUGAR)
                .unlockedBy("hasPumpkin", has(Items.PUMPKIN))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(getItemFromID("pumpkin_pie_diamond", ModItems.VANILLA_QUALITY), 1)
                .requires(Items.PUMPKIN)
                .requires(getItemFromID("egg_diamond", ModItems.VANILLA_QUALITY))
                .requires(Items.SUGAR)
                .unlockedBy("hasPumpkin", has(Items.PUMPKIN))
                .save(pFinishedRecipeConsumer);

        simpleQualityFoodRecipe("kelp", ModCrops.CROPS, "dried_kelp", ModItems.VANILLA_QUALITY, pFinishedRecipeConsumer);


        ShapelessRecipeBuilder.shapeless(Items.COCOA_BEANS, 2)
                .requires(getItemFromID("cocoa_beans_iron", ModCrops.CROPS))
                .unlockedBy("hasIronCocoa", has(getItemFromID("cocoa_beans_iron", ModCrops.CROPS)))
                .save(pFinishedRecipeConsumer, "qualitycrops:cocoa_beans_from_iron_quality");

        ShapelessRecipeBuilder.shapeless(Items.COCOA_BEANS, 3)
                .requires(getItemFromID("cocoa_beans_gold", ModCrops.CROPS))
                .unlockedBy("hasIronCocoa", has(getItemFromID("cocoa_beans_gold", ModCrops.CROPS)))
                .save(pFinishedRecipeConsumer, "qualitycrops:cocoa_beans_from_gold_quality");

        ShapelessRecipeBuilder.shapeless(Items.COCOA_BEANS, 4)
                .requires(getItemFromID("cocoa_beans_diamond", ModCrops.CROPS))
                .unlockedBy("hasIronCocoa", has(getItemFromID("cocoa_beans_diamond", ModCrops.CROPS)))
                .save(pFinishedRecipeConsumer, "qualitycrops:cocoa_beans_from_diamond_quality");

        ShapelessRecipeBuilder.shapeless(getItemFromID("cookie_iron", ModItems.VANILLA_QUALITY), 8)
                .requires(Items.WHEAT)
                .requires(getItemFromID("cocoa_beans_iron", ModCrops.CROPS))
                .requires(Items.WHEAT)
                .unlockedBy("hasWheat", has(Items.WHEAT))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(getItemFromID("cookie_gold", ModItems.VANILLA_QUALITY), 8)
                .requires(Items.WHEAT)
                .requires(getItemFromID("cocoa_beans_gold", ModCrops.CROPS))
                .requires(Items.WHEAT)
                .unlockedBy("hasWheat", has(Items.WHEAT))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(getItemFromID("cookie_diamond", ModItems.VANILLA_QUALITY), 8)
                .requires(Items.WHEAT)
                .requires(getItemFromID("cocoa_beans_diamond", ModCrops.CROPS))
                .requires(Items.WHEAT)
                .unlockedBy("hasWheat", has(Items.WHEAT))
                .save(pFinishedRecipeConsumer);
         */

    }

    public static void simpleFoodRecipe(Item input, Item output, Consumer<FinishedRecipe> pFinishedRecipeConsumer){
        simpleCookingRecipe(pFinishedRecipeConsumer, "smelting", RecipeSerializer.SMELTING_RECIPE, 200, input, output, 0.35F);
        simpleCookingRecipe(pFinishedRecipeConsumer, "smoking", RecipeSerializer.SMOKING_RECIPE, 100, input, output, 0.35F);
        simpleCookingRecipe(pFinishedRecipeConsumer, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, 600, input, output, 0.35F);
    }

    public static void simpleQualityFoodRecipe(String input, DeferredRegister<Item> inputRegister, String output, DeferredRegister<Item> outputRegister, Consumer<FinishedRecipe> pFinishedRecipeConsumer){
        simpleFoodRecipe(getItemFromID(input + "_iron", inputRegister), getItemFromID(output + "_iron", outputRegister), pFinishedRecipeConsumer);
        simpleFoodRecipe(getItemFromID(input + "_gold", inputRegister), getItemFromID(output + "_gold", outputRegister), pFinishedRecipeConsumer);
        simpleFoodRecipe(getItemFromID(input + "_diamond", inputRegister), getItemFromID(output + "_diamond", outputRegister), pFinishedRecipeConsumer);
    }

    private static String getItemName(Item item){
        return Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).getPath();
    }

    public static Item getItemFromID(String id, DeferredRegister<Item> register){
        Iterable<Item> items = register.getEntries().stream().map(RegistryObject::get)::iterator;

        for (Item item : items) {
            if (getItemName(item).equals(id)) {
                return item;
            }
        }

        return Items.BARRIER;
    }
}
