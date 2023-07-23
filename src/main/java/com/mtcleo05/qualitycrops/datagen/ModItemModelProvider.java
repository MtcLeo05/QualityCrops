package com.mtcleo05.qualitycrops.datagen;

import com.mtcleo05.qualitycrops.QualityCrops;
import com.mtcleo05.qualitycrops.items.ModItems;
import com.mtcleo05.qualitycrops.quality.QualityBowlFoodItem;
import com.mtcleo05.qualitycrops.quality.QualityItem;
import com.mtcleo05.qualitycrops.items.ModCrops;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, QualityCrops.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        Iterable<Item> qualityCrops = ModCrops.CROPS.getEntries().stream().map(RegistryObject::get)::iterator;

        qualityCrops.forEach(item -> {
            QualityItem qualityItem = (QualityItem) item;
            qualityItem(item, qualityItem.cropQuality, "minecraft");
        });

        Iterable<Item> vanillaQuality = ModItems.VANILLA_QUALITY.getEntries().stream().map(RegistryObject::get)::iterator;
        Iterable<Item> newItems = ModItems.NEW_ITEMS.getEntries().stream().map(RegistryObject::get)::iterator;

        vanillaQuality.forEach(item -> {
            if(item instanceof QualityItem qualityItem){
                qualityItem(item, qualityItem.cropQuality, "minecraft");
            }else if(item instanceof QualityBowlFoodItem qualityBowlFoodItem){
                qualityItem(item, qualityBowlFoodItem.cropQuality, "minecraft");
            }else{
                simpleItem(item);
            }
        });

        newItems.forEach(item -> {
            if(item instanceof QualityItem qualityItem){
                qualityItem(item, qualityItem.cropQuality, "qualitycrops");
            }else if(item instanceof QualityBowlFoodItem qualityBowlFoodItem){
                qualityItem(item, qualityBowlFoodItem.cropQuality, "qualitycrops");
            }else{
                simpleItem(item);
            }
        });

    }

    private ItemModelBuilder simpleItem(Item item){
        return withExistingParent(getItemName(item),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(QualityCrops.MODID, "item/"+ getItemName(item)));
    }

    private ItemModelBuilder handheldItem(Item item){
        return withExistingParent(getItemName(item),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(QualityCrops.MODID, "item/"+ getItemName(item)));
    }

    private ItemModelBuilder qualityItem(Item item, int cropQuality, String originalId){
        return withExistingParent(getItemName(item),
                new ResourceLocation("item/generated"))
                .texture(
                        "layer0",
                        new ResourceLocation(originalId, "item/"+ removeQualityID(getItemName(item)))
                )
                .texture(
                        "layer1",
                        switch (cropQuality){
                            case 1 -> new ResourceLocation(QualityCrops.MODID, "item/iron_overlay");
                            case 2 -> new ResourceLocation(QualityCrops.MODID, "item/gold_overlay");
                            case 3 -> new ResourceLocation(QualityCrops.MODID, "item/diamond_overlay");
                            default -> null;
                        }
                );
    }

    private ItemModelBuilder blockItem(Block block) {
        return withExistingParent(getBlockName(block),modid + ":blocks/" + getBlockName(block));
    }

    private static String getItemName(Item item){
        return Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).getPath();
    }

    private String getBlockName(Block block){
        return Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath();
    }

    public static String removeQualityID(String input) {
        int lastUnderscoreIndex = input.lastIndexOf("_");
        if (lastUnderscoreIndex != -1) {
            return input.substring(0, lastUnderscoreIndex);
        } else {
            // No underscore found, return the original input
            return input;
        }
    }

    public static Item getItemFromID(String id){
        Iterable<Item> qualityCrops = ModCrops.CROPS.getEntries().stream().map(RegistryObject::get)::iterator;

        for (Item item : qualityCrops) {
            if (getItemName(item).equals(id)) {
                return item;
            }
        }

        return null;
    }
}
