package com.mtcleo05.qualitycrops.items;

import com.mtcleo05.qualitycrops.QualityCrops;
import com.mtcleo05.qualitycrops.quality.QualityBowlFoodItem;
import com.mtcleo05.qualitycrops.quality.QualityItem;
import com.mtcleo05.qualitycrops.rarities.ModRarities;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.BowlFoodItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> NEW_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, QualityCrops.MODID);
    public static final DeferredRegister<Item> VANILLA_QUALITY = DeferredRegister.create(ForgeRegistries.ITEMS, QualityCrops.MODID);

    public static void registerQualityItem(String id, DeferredRegister<Item> register){
        register.register(id, () ->
            new Item(
                    new Item.Properties()
                            .tab(ModCreativeTabs.ITEMS)
            ));

        register.register(id + "_iron", () ->
                new QualityItem(
                        new Item.Properties()
                                .rarity(ModRarities.IRON)
                                .tab(ModCreativeTabs.ITEMS),
                        1
                ));

        register.register(id + "_gold", () ->
                new QualityItem(
                        new Item.Properties()
                                .rarity(ModRarities.GOLD)
                                .tab(ModCreativeTabs.ITEMS),
                        2
                ));

        register.register(id + "_diamond", () ->
                new QualityItem(
                        new Item.Properties()
                                .rarity(ModRarities.DIAMOND)
                                .tab(ModCreativeTabs.ITEMS),
                        3
                ));
    }

    public static void registerVanillaQuality(String id, DeferredRegister<Item> register){
        register.register(id + "_iron", () ->
                new QualityItem(
                        new Item.Properties()
                                .rarity(ModRarities.IRON)
                                .tab(ModCreativeTabs.ITEMS),
                        1
                ));

        register.register(id + "_gold", () ->
                new QualityItem(
                        new Item.Properties()
                                .rarity(ModRarities.GOLD)
                                .tab(ModCreativeTabs.ITEMS),
                        2
                ));

        register.register(id + "_diamond", () ->
                new QualityItem(
                        new Item.Properties()
                                .rarity(ModRarities.DIAMOND)
                                .tab(ModCreativeTabs.ITEMS),
                        3
                ));

    }

    public static void registerQualityFood(String id, DeferredRegister<Item> register, FoodProperties foodProperties, boolean isSoup){
        register.register(id, () -> {
            if(isSoup){
                return new BowlFoodItem(
                        new Item.Properties()
                                .tab(ModCreativeTabs.ITEMS)
                                .stacksTo(1)
                                .food(new FoodProperties.Builder()
                                        .nutrition(foodProperties.getNutrition())
                                        .saturationMod(foodProperties.getSaturationModifier())
                                        .build())
                );
            }else{
                return new Item(
                        new Item.Properties()
                                .tab(ModCreativeTabs.ITEMS)
                                .food(new FoodProperties.Builder()
                                        .nutrition(foodProperties.getNutrition())
                                        .saturationMod(foodProperties.getSaturationModifier())
                                        .build())
                );
            }
        });
        
        register.register(id + "_iron", () -> {
            if(isSoup){
                return new QualityBowlFoodItem(
                        new Item.Properties()
                                .tab(ModCreativeTabs.ITEMS)
                                .stacksTo(1)
                                .rarity(ModRarities.IRON)
                                .food(new FoodProperties.Builder()
                                        .nutrition(Math.round(foodProperties.getNutrition() * 1.25f))
                                        .saturationMod(foodProperties.getSaturationModifier() * 1.25f)
                                        .build()),
                        1
                );
            }else{
                return new QualityItem(
                        new Item.Properties()
                                .tab(ModCreativeTabs.ITEMS)
                                .rarity(ModRarities.IRON)
                                .food(new FoodProperties.Builder()
                                        .nutrition(Math.round(foodProperties.getNutrition() * 1.25f))
                                        .saturationMod(foodProperties.getSaturationModifier() * 1.25f)
                                        .build()),
                        1
                );
            }
        });

        register.register(id + "_gold", () -> {
            if(isSoup){
                return new QualityBowlFoodItem(
                        new Item.Properties()
                                .tab(ModCreativeTabs.ITEMS)
                                .stacksTo(1)
                                .rarity(ModRarities.GOLD)
                                .food(new FoodProperties.Builder()
                                        .nutrition(Math.round(foodProperties.getNutrition() * 1.5f))
                                        .saturationMod(foodProperties.getSaturationModifier() * 1.5f)
                                        .build()),
                        2
                        
                );
            }else{
                return new QualityItem(
                        new Item.Properties()
                                .tab(ModCreativeTabs.ITEMS)
                                .rarity(ModRarities.GOLD)
                                .food(new FoodProperties.Builder()
                                        .nutrition(Math.round(foodProperties.getNutrition() * 1.5f))
                                        .saturationMod(foodProperties.getSaturationModifier() * 1.5f)
                                        .build()),
                        2
                );
            }
        });

        register.register(id + "_diamond", () -> {
            if(isSoup){
                return new QualityBowlFoodItem(
                        new Item.Properties()
                                .tab(ModCreativeTabs.ITEMS)
                                .stacksTo(1)
                                .rarity(ModRarities.DIAMOND)
                                .food(new FoodProperties.Builder()
                                        .nutrition(foodProperties.getNutrition() * 2)
                                        .saturationMod(foodProperties.getSaturationModifier() * 2f)
                                        .build()),
                        3

                );
            }else{
                return new QualityItem(
                        new Item.Properties()
                                .tab(ModCreativeTabs.ITEMS)
                                .rarity(ModRarities.DIAMOND)
                                .food(new FoodProperties.Builder()
                                        .nutrition(foodProperties.getNutrition() * 2)
                                        .saturationMod(foodProperties.getSaturationModifier() * 2f)
                                        .build()),
                        3
                );
            }
        });
    }

    public static void registerVanillaQualityFood(String id, DeferredRegister<Item> register, FoodProperties foodProperties, boolean isSoup){
        register.register(id + "_iron", () -> {
            if(isSoup){
                return new QualityBowlFoodItem(
                        new Item.Properties()
                                .tab(ModCreativeTabs.ITEMS)
                                .stacksTo(1)
                                .rarity(ModRarities.IRON)
                                .food(new FoodProperties.Builder()
                                        .nutrition(Math.round(foodProperties.getNutrition() * 1.25f))
                                        .saturationMod(foodProperties.getSaturationModifier() * 1.25f)
                                        .build()),
                        1
                );
            }else{
                return new QualityItem(
                        new Item.Properties()
                                .tab(ModCreativeTabs.ITEMS)
                                .rarity(ModRarities.IRON)
                                .food(new FoodProperties.Builder()
                                        .nutrition(Math.round(foodProperties.getNutrition() * 1.25f))
                                        .saturationMod(foodProperties.getSaturationModifier() * 1.25f)
                                        .build()),
                        1
                );
            }
        });

        register.register(id + "_gold", () -> {
            if(isSoup){
                return new QualityBowlFoodItem(
                        new Item.Properties()
                                .tab(ModCreativeTabs.ITEMS)
                                .stacksTo(1)
                                .rarity(ModRarities.GOLD)
                                .food(new FoodProperties.Builder()
                                        .nutrition(Math.round(foodProperties.getNutrition() * 1.5f))
                                        .saturationMod(foodProperties.getSaturationModifier() * 1.5f)
                                        .build()),
                        2

                );
            }else{
                return new QualityItem(
                        new Item.Properties()
                                .tab(ModCreativeTabs.ITEMS)
                                .rarity(ModRarities.GOLD)
                                .food(new FoodProperties.Builder()
                                        .nutrition(Math.round(foodProperties.getNutrition() * 1.5f))
                                        .saturationMod(foodProperties.getSaturationModifier() * 1.5f)
                                        .build()),
                        2
                );
            }
        });

        register.register(id + "_diamond", () -> {
            if(isSoup){
                return new QualityBowlFoodItem(
                        new Item.Properties()
                                .tab(ModCreativeTabs.ITEMS)
                                .stacksTo(1)
                                .rarity(ModRarities.DIAMOND)
                                .food(new FoodProperties.Builder()
                                        .nutrition(foodProperties.getNutrition() * 2)
                                        .saturationMod(foodProperties.getSaturationModifier() * 2f)
                                        .build()),
                        3
                );
            }else{
                return new QualityItem(
                        new Item.Properties()
                                .tab(ModCreativeTabs.ITEMS)
                                .rarity(ModRarities.DIAMOND)
                                .food(new FoodProperties.Builder()
                                        .nutrition(foodProperties.getNutrition() * 2)
                                        .saturationMod(foodProperties.getSaturationModifier() * 2f)
                                        .build()),
                        3
                );
            }
        });
    }

    public static void registerVanillaQualityFoodWithOneEffect(String id, DeferredRegister<Item> register, FoodProperties foodProperties, MobEffect effect, int duration, int amplifier, float chance){
        register.register(id + "_iron", () ->
                new QualityItem(
                        new Item.Properties()
                                .tab(ModCreativeTabs.ITEMS)
                                .rarity(ModRarities.IRON)
                                .food(new FoodProperties.Builder()
                                        .nutrition(Math.round(foodProperties.getNutrition() * 1.25f))
                                        .saturationMod(foodProperties.getSaturationModifier() * 1.25f)
                                        .effect(() -> new MobEffectInstance(effect, Math.round(duration * 1.25f), Math.round(amplifier * 1.25f)), chance)
                                        .build()),
                        1));

        register.register(id + "_gold", () -> new QualityItem(
                new Item.Properties()
                        .tab(ModCreativeTabs.ITEMS)
                        .rarity(ModRarities.GOLD)
                        .food(new FoodProperties.Builder()
                                .nutrition(Math.round(foodProperties.getNutrition() * 1.5f))
                                .saturationMod(foodProperties.getSaturationModifier() * 1.5f)
                                .effect(() -> new MobEffectInstance(effect, Math.round(duration * 1.5f), Math.round(amplifier * 1.5f)), chance)
                                .build()),
                2
        ));

        register.register(id + "_diamond", () -> new QualityItem(
                new Item.Properties()
                        .tab(ModCreativeTabs.ITEMS)
                        .rarity(ModRarities.DIAMOND)
                        .food(new FoodProperties.Builder()
                                .nutrition(foodProperties.getNutrition() * 2)
                                .saturationMod(foodProperties.getSaturationModifier() * 2f)
                                .effect(() -> new MobEffectInstance(effect, Math.round(duration * 2f), Math.round(amplifier * 2f)), chance)
                                .build()),
                3
        ));
    }

    public static void registerVanillaQualityFoodWithThreeEffect(String id, DeferredRegister<Item> register, FoodProperties foodProperties, MobEffect effect1, int duration1, int amplifier1, MobEffect effect2, int duration2, int amplifier2, MobEffect effect3, int duration3, int amplifier3, float chance){
        register.register(id + "_iron", () ->
                new QualityItem(
                        new Item.Properties()
                                .tab(ModCreativeTabs.ITEMS)
                                .rarity(ModRarities.IRON)
                                .food(new FoodProperties.Builder()
                                        .nutrition(Math.round(foodProperties.getNutrition() * 1.25f))
                                        .saturationMod(foodProperties.getSaturationModifier() * 1.25f)
                                        .effect(() -> new MobEffectInstance(effect1, Math.round(duration1 * 1.25f), Math.round(amplifier1 * 1.25f)), chance)
                                        .effect(() -> new MobEffectInstance(effect2, Math.round(duration2 * 1.25f), Math.round(amplifier2 * 1.25f)), chance)
                                        .effect(() -> new MobEffectInstance(effect3, Math.round(duration3 * 1.25f), Math.round(amplifier3 * 1.25f)), chance)
                                        .build()),
                        1));

        register.register(id + "_gold", () -> new QualityItem(
                new Item.Properties()
                        .tab(ModCreativeTabs.ITEMS)
                        .rarity(ModRarities.GOLD)
                        .food(new FoodProperties.Builder()
                                .nutrition(Math.round(foodProperties.getNutrition() * 1.5f))
                                .saturationMod(foodProperties.getSaturationModifier() * 1.5f)
                                .effect(() -> new MobEffectInstance(effect1, Math.round(duration1 * 1.5f), Math.round(amplifier1 * 1.5f)), chance)
                                .effect(() -> new MobEffectInstance(effect2, Math.round(duration2 * 1.5f), Math.round(amplifier2 * 1.5f)), chance)
                                .effect(() -> new MobEffectInstance(effect3, Math.round(duration3 * 1.5f), Math.round(amplifier3 * 1.5f)), chance)
                                .build()),
                2
        ));

        register.register(id + "_diamond", () -> new QualityItem(
                new Item.Properties()
                        .tab(ModCreativeTabs.ITEMS)
                        .rarity(ModRarities.DIAMOND)
                        .food(new FoodProperties.Builder()
                                .nutrition(foodProperties.getNutrition() * 2)
                                .saturationMod(foodProperties.getSaturationModifier() * 2f)
                                .effect(() -> new MobEffectInstance(effect1, Math.round(duration1 * 2f), Math.round(amplifier1 * 2f)), chance)
                                .effect(() -> new MobEffectInstance(effect2, Math.round(duration2 * 2f), Math.round(amplifier2 * 2f)), chance)
                                .effect(() -> new MobEffectInstance(effect3, Math.round(duration3 * 2f), Math.round(amplifier3 * 2f)), chance)
                                .build()),
                3
        ));
    }

    public static void register(IEventBus eventBus){
        registerQualityItem("dough", NEW_ITEMS);
        registerVanillaQuality("tropical_fish", VANILLA_QUALITY);
        registerVanillaQuality("egg", VANILLA_QUALITY);
        registerVanillaQualityFood("bread", VANILLA_QUALITY, Foods.BREAD, false);
        registerVanillaQualityFood("baked_potato", VANILLA_QUALITY, Foods.BAKED_POTATO, false);
        registerVanillaQualityFood("beetroot_soup", VANILLA_QUALITY, Foods.BEETROOT_SOUP, true);
        registerVanillaQualityFood("cod", VANILLA_QUALITY, Foods.COD, false);
        registerVanillaQualityFood("salmon", VANILLA_QUALITY, Foods.SALMON, false);
        registerVanillaQualityFood("cooked_cod", VANILLA_QUALITY, Foods.COOKED_COD, false);
        registerVanillaQualityFood("cooked_salmon", VANILLA_QUALITY, Foods.COOKED_SALMON, false);
        registerVanillaQualityFood("mushroom_stew", VANILLA_QUALITY, Foods.MUSHROOM_STEW, true);
        registerVanillaQualityFood("rabbit_stew", VANILLA_QUALITY, Foods.RABBIT_STEW, true);
        registerVanillaQualityFood("beef", VANILLA_QUALITY, Foods.BEEF, false);
        registerVanillaQualityFood("chicken", VANILLA_QUALITY, Foods.CHICKEN, false);
        registerVanillaQualityFood("rabbit", VANILLA_QUALITY, Foods.RABBIT, false);
        registerVanillaQualityFood("mutton", VANILLA_QUALITY, Foods.MUTTON, false);
        registerVanillaQualityFood("porkchop", VANILLA_QUALITY, Foods.PORKCHOP, false);
        registerVanillaQualityFood("cooked_beef", VANILLA_QUALITY, Foods.COOKED_BEEF, false);
        registerVanillaQualityFood("cooked_chicken", VANILLA_QUALITY, Foods.COOKED_CHICKEN, false);
        registerVanillaQualityFood("cooked_rabbit", VANILLA_QUALITY, Foods.COOKED_RABBIT, false);
        registerVanillaQualityFood("cooked_mutton", VANILLA_QUALITY, Foods.COOKED_MUTTON, false);
        registerVanillaQualityFood("cooked_porkchop", VANILLA_QUALITY, Foods.COOKED_PORKCHOP, false);
        registerVanillaQualityFood("apple", VANILLA_QUALITY, Foods.APPLE, false);
        registerVanillaQualityFood("cookie", VANILLA_QUALITY, Foods.COOKIE, false);
        registerVanillaQualityFood("melon_slice", VANILLA_QUALITY, Foods.MELON_SLICE, false);
        registerVanillaQualityFood("dried_kelp", VANILLA_QUALITY, Foods.DRIED_KELP, false);
        registerVanillaQualityFood("pumpkin_pie", VANILLA_QUALITY, Foods.PUMPKIN_PIE, false);
        registerVanillaQualityFoodWithOneEffect("poisonous_potato", VANILLA_QUALITY, Foods.POISONOUS_POTATO, MobEffects.POISON, 100, 0, 0.6f);
        registerVanillaQualityFoodWithThreeEffect("pufferfish", VANILLA_QUALITY, Foods.PUFFERFISH, MobEffects.POISON, 1200, 1, MobEffects.HUNGER, 300, 2, MobEffects.CONFUSION, 300, 0,1.0F);

        NEW_ITEMS.register(eventBus);
        VANILLA_QUALITY.register(eventBus);
    }
    
}
