package com.mtcleo05.qualitycrops.items;

import com.mtcleo05.qualitycrops.QualityCrops;
import com.mtcleo05.qualitycrops.quality.QualityBowlFoodItem;
import com.mtcleo05.qualitycrops.quality.QualityItem;
import com.mtcleo05.qualitycrops.rarities.ModRarities;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
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

    public static void registerQualityFood(String id, DeferredRegister<Item> register, int nutrition, float saturation, boolean isSoup){
        register.register(id, () -> {
            if(isSoup){
                return new BowlFoodItem(
                        new Item.Properties()
                                .tab(ModCreativeTabs.ITEMS)
                                .stacksTo(1)
                                .food(new FoodProperties.Builder()
                                        .nutrition(nutrition)
                                        .saturationMod(saturation)
                                        .build())
                );
            }else{
                return new Item(
                        new Item.Properties()
                                .tab(ModCreativeTabs.ITEMS)
                                .food(new FoodProperties.Builder()
                                        .nutrition(nutrition)
                                        .saturationMod(saturation)
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
                                        .nutrition(Math.round(nutrition * 1.25f))
                                        .saturationMod(saturation * 1.25f)
                                        .build()),
                        1
                );
            }else{
                return new QualityItem(
                        new Item.Properties()
                                .tab(ModCreativeTabs.ITEMS)
                                .rarity(ModRarities.IRON)
                                .food(new FoodProperties.Builder()
                                        .nutrition(Math.round(nutrition * 1.25f))
                                        .saturationMod(saturation * 1.25f)
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
                                        .nutrition(Math.round(nutrition * 1.5f))
                                        .saturationMod(saturation * 1.5f)
                                        .build()),
                        2
                        
                );
            }else{
                return new QualityItem(
                        new Item.Properties()
                                .tab(ModCreativeTabs.ITEMS)
                                .rarity(ModRarities.GOLD)
                                .food(new FoodProperties.Builder()
                                        .nutrition(Math.round(nutrition * 1.5f))
                                        .saturationMod(saturation * 1.5f)
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
                                        .nutrition(nutrition * 2)
                                        .saturationMod(saturation * 2f)
                                        .build()),
                        3

                );
            }else{
                return new QualityItem(
                        new Item.Properties()
                                .tab(ModCreativeTabs.ITEMS)
                                .rarity(ModRarities.DIAMOND)
                                .food(new FoodProperties.Builder()
                                        .nutrition(nutrition * 2)
                                        .saturationMod(saturation * 2f)
                                        .build()),
                        3
                );
            }
        });
    }

    public static void registerVanillaQualityFood(String id, DeferredRegister<Item> register, int nutrition, float saturation, boolean isSoup){
        register.register(id + "_iron", () -> {
            if(isSoup){
                return new QualityBowlFoodItem(
                        new Item.Properties()
                                .tab(ModCreativeTabs.ITEMS)
                                .stacksTo(1)
                                .rarity(ModRarities.IRON)
                                .food(new FoodProperties.Builder()
                                        .nutrition(Math.round(nutrition * 1.25f))
                                        .saturationMod(saturation * 1.25f)
                                        .build()),
                        1
                );
            }else{
                return new QualityItem(
                        new Item.Properties()
                                .tab(ModCreativeTabs.ITEMS)
                                .rarity(ModRarities.IRON)
                                .food(new FoodProperties.Builder()
                                        .nutrition(Math.round(nutrition * 1.25f))
                                        .saturationMod(saturation * 1.25f)
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
                                        .nutrition(Math.round(nutrition * 1.5f))
                                        .saturationMod(saturation * 1.5f)
                                        .build()),
                        2

                );
            }else{
                return new QualityItem(
                        new Item.Properties()
                                .tab(ModCreativeTabs.ITEMS)
                                .rarity(ModRarities.GOLD)
                                .food(new FoodProperties.Builder()
                                        .nutrition(Math.round(nutrition * 1.5f))
                                        .saturationMod(saturation * 1.5f)
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
                                        .nutrition(nutrition * 2)
                                        .saturationMod(saturation * 2f)
                                        .build()),
                        3
                );
            }else{
                return new QualityItem(
                        new Item.Properties()
                                .tab(ModCreativeTabs.ITEMS)
                                .rarity(ModRarities.DIAMOND)
                                .food(new FoodProperties.Builder()
                                        .nutrition(nutrition * 2)
                                        .saturationMod(saturation * 2f)
                                        .build()),
                        3
                );
            }
        });
    }

    public static void registerVanillaQualityFoodWithOneEffect(String id, DeferredRegister<Item> register, int nutrition, float saturation, MobEffect effect, int duration, int amplifier, float chance){
        register.register(id + "_iron", () ->
                new QualityItem(
                        new Item.Properties()
                                .tab(ModCreativeTabs.ITEMS)
                                .rarity(ModRarities.IRON)
                                .food(new FoodProperties.Builder()
                                        .nutrition(Math.round(nutrition * 1.25f))
                                        .saturationMod(saturation * 1.25f)
                                        .effect(() -> new MobEffectInstance(effect, Math.round(duration * 1.25f), Math.round(amplifier * 1.25f)), chance)
                                        .build()),
                        1));

        register.register(id + "_gold", () -> new QualityItem(
                new Item.Properties()
                        .tab(ModCreativeTabs.ITEMS)
                        .rarity(ModRarities.GOLD)
                        .food(new FoodProperties.Builder()
                                .nutrition(Math.round(nutrition * 1.5f))
                                .saturationMod(saturation * 1.5f)
                                .effect(() -> new MobEffectInstance(effect, Math.round(duration * 1.5f), Math.round(amplifier * 1.5f)), chance)
                                .build()),
                2
        ));

        register.register(id + "_diamond", () -> new QualityItem(
                new Item.Properties()
                        .tab(ModCreativeTabs.ITEMS)
                        .rarity(ModRarities.DIAMOND)
                        .food(new FoodProperties.Builder()
                                .nutrition(nutrition * 2)
                                .saturationMod(saturation * 2f)
                                .effect(() -> new MobEffectInstance(effect, Math.round(duration * 2f), Math.round(amplifier * 2f)), chance)
                                .build()),
                3
        ));
    }

    public static void registerVanillaQualityFoodWithThreeEffect(String id, DeferredRegister<Item> register, int nutrition, float saturation, MobEffect effect1, int duration1, int amplifier1, MobEffect effect2, int duration2, int amplifier2, MobEffect effect3, int duration3, int amplifier3, float chance){
        register.register(id + "_iron", () ->
                new QualityItem(
                        new Item.Properties()
                                .tab(ModCreativeTabs.ITEMS)
                                .rarity(ModRarities.IRON)
                                .food(new FoodProperties.Builder()
                                        .nutrition(Math.round(nutrition * 1.25f))
                                        .saturationMod(saturation * 1.25f)
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
                                .nutrition(Math.round(nutrition * 1.5f))
                                .saturationMod(saturation * 1.5f)
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
                                .nutrition(nutrition * 2)
                                .saturationMod(saturation * 2f)
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
        registerVanillaQualityFood("bread", VANILLA_QUALITY, 5, 0.6f, false);
        registerVanillaQualityFood("baked_potato", VANILLA_QUALITY, 5, 0.6f, false);
        registerVanillaQualityFood("beetroot_soup", VANILLA_QUALITY, 6, 0.6f, true);
        registerVanillaQualityFood("cod", VANILLA_QUALITY, 2, 0.1f, false);
        registerVanillaQualityFood("salmon", VANILLA_QUALITY, 2, 0.1f, false);
        registerVanillaQualityFood("cooked_cod", VANILLA_QUALITY, 5, 0.6f, false);
        registerVanillaQualityFood("cooked_salmon", VANILLA_QUALITY, 6, 0.8f, false);
        registerVanillaQualityFood("mushroom_stew", VANILLA_QUALITY, 6, 0.6f, true);
        registerVanillaQualityFood("rabbit_stew", VANILLA_QUALITY, 10, 0.6f, true);
        registerVanillaQualityFood("beef", VANILLA_QUALITY, 3, 0.3f, false);
        registerVanillaQualityFood("chicken", VANILLA_QUALITY, 2, 0.3f, false);
        registerVanillaQualityFood("rabbit", VANILLA_QUALITY, 3, 0.3f, false);
        registerVanillaQualityFood("mutton", VANILLA_QUALITY, 2, 0.3f, false);
        registerVanillaQualityFood("porkchop", VANILLA_QUALITY, 2, 0.3f, false);
        registerVanillaQualityFood("cooked_beef", VANILLA_QUALITY, 8, 0.8f, false);
        registerVanillaQualityFood("cooked_chicken", VANILLA_QUALITY, 6, 0.6f, false);
        registerVanillaQualityFood("cooked_rabbit", VANILLA_QUALITY, 5, 0.6f, false);
        registerVanillaQualityFood("cooked_mutton", VANILLA_QUALITY, 6, 0.8f, false);
        registerVanillaQualityFood("cooked_porkchop", VANILLA_QUALITY, 8, 0.8f, false);
        registerVanillaQualityFood("apple", VANILLA_QUALITY, 4, 0.3f, false);
        registerVanillaQualityFood("cookie", VANILLA_QUALITY, 2, 0.1f, false);
        registerVanillaQualityFood("melon_slice", VANILLA_QUALITY, 2, 0.3f, false);
        registerVanillaQualityFood("dried_kelp", VANILLA_QUALITY, 1, 0.3f, false);
        registerVanillaQualityFood("pumpkin_pie", VANILLA_QUALITY, 8, 0.3f, false);
        registerVanillaQualityFoodWithOneEffect("poisonous_potato", VANILLA_QUALITY, 2, 0.3f, MobEffects.POISON, 100, 0, 0.6f);
        registerVanillaQualityFoodWithThreeEffect("pufferfish", VANILLA_QUALITY, 1, 0.1f, MobEffects.POISON, 1200, 1, MobEffects.HUNGER, 300, 2, MobEffects.CONFUSION, 300, 0,1.0F);

        NEW_ITEMS.register(eventBus);
        VANILLA_QUALITY.register(eventBus);
    }
    
}
