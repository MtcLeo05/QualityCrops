package com.mtcleo05.qualitycrops.items;

import com.mtcleo05.qualitycrops.QualityCrops;
import com.mtcleo05.qualitycrops.quality.QualityItem;
import com.mtcleo05.qualitycrops.rarities.ModRarities;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModCrops {

    public static RegistryObject<Item>[] SWEET_BERRIES = new RegistryObject[3];
    public static RegistryObject<Item>[] GLOW_BERRIES = new RegistryObject[3];

    public static final DeferredRegister<Item> CROPS = DeferredRegister.create(ForgeRegistries.ITEMS, QualityCrops.MODID);

    public static void registerVanillaCropQuality(String id){
        CROPS.register(id + "_iron", () ->
                new QualityItem(
                        new Item.Properties()
                                .rarity(ModRarities.IRON)
                                .tab(ModCreativeTabs.CROPS),
                        1
                ));

        CROPS.register(id + "_gold", () ->
                new QualityItem(
                        new Item.Properties()
                                .rarity(ModRarities.GOLD)
                                .tab(ModCreativeTabs.CROPS),
                        2
                ));

        CROPS.register(id + "_diamond", () ->
                new QualityItem(
                        new Item.Properties()
                                .rarity(ModRarities.DIAMOND)
                                .tab(ModCreativeTabs.CROPS),
                        3
                ));

    }

    public static void registerVanillaFoodCropQuality(String id, int nutrition, float saturation){
        CROPS.register(id + "_iron", () ->
                new QualityItem(
                        new Item.Properties()
                                .rarity(ModRarities.IRON)
                                .tab(ModCreativeTabs.CROPS)
                                .food(new FoodProperties.Builder()
                                        .nutrition(Math.round(nutrition * 1.25f))
                                        .saturationMod(saturation * 1.25f)
                                        .build()),
                        1
                ));

        CROPS.register(id + "_gold", () ->
                new QualityItem(
                        new Item.Properties()
                                .rarity(ModRarities.GOLD)
                                .tab(ModCreativeTabs.CROPS)
                                .food(new FoodProperties.Builder()
                                        .nutrition(Math.round(nutrition * 1.5f))
                                        .saturationMod(saturation * 1.5f)
                                        .build()),
                        2
                ));

        CROPS.register(id + "_diamond", () ->
                new QualityItem(
                        new Item.Properties()
                                .rarity(ModRarities.DIAMOND)
                                .tab(ModCreativeTabs.CROPS)
                                .food(new FoodProperties.Builder()
                                        .nutrition(Math.round(nutrition * 2f))
                                        .saturationMod(saturation * 2f)
                                        .build()),
                        3
                ));

    }

    public static RegistryObject<Item>[] registerVanillaFoodCropQualityWithReturn(String id, int nutrition, float saturation){

        RegistryObject<Item>[] toReturn = new RegistryObject[3];

        toReturn[0] = CROPS.register(id + "_iron", () ->
                new QualityItem(
                        new Item.Properties()
                                .rarity(ModRarities.IRON)
                                .tab(ModCreativeTabs.CROPS)
                                .food(new FoodProperties.Builder()
                                        .nutrition(Math.round(nutrition * 1.25f))
                                        .saturationMod(saturation * 1.25f)
                                        .build()),
                        1
                ));

        toReturn[1] = CROPS.register(id + "_gold", () ->
                new QualityItem(
                        new Item.Properties()
                                .rarity(ModRarities.GOLD)
                                .tab(ModCreativeTabs.CROPS)
                                .food(new FoodProperties.Builder()
                                        .nutrition(Math.round(nutrition * 1.5f))
                                        .saturationMod(saturation * 1.5f)
                                        .build()),
                        2
                ));

        toReturn[2] = CROPS.register(id + "_diamond", () ->
                new QualityItem(
                        new Item.Properties()
                                .rarity(ModRarities.DIAMOND)
                                .tab(ModCreativeTabs.CROPS)
                                .food(new FoodProperties.Builder()
                                        .nutrition(Math.round(nutrition * 2f))
                                        .saturationMod(saturation * 2f)
                                        .build()),
                        3
                ));

        return toReturn;
    }


    public static void register(IEventBus eventBus){
        registerVanillaCropQuality("wheat");
        registerVanillaCropQuality("sugar_cane");
        registerVanillaCropQuality("red_mushroom");
        registerVanillaCropQuality("brown_mushroom");
        registerVanillaCropQuality("kelp");
        registerVanillaCropQuality("cocoa_beans");

        registerVanillaFoodCropQuality("carrot", 3, 0.6f);
        registerVanillaFoodCropQuality("potato", 1, 0.3f);
        registerVanillaFoodCropQuality("beetroot", 1, 0.6f);

        SWEET_BERRIES = registerVanillaFoodCropQualityWithReturn("sweet_berries",2, 0.1f);
        GLOW_BERRIES = registerVanillaFoodCropQualityWithReturn("glow_berries",2, 0.1f);
        CROPS.register(eventBus);
    }

}
