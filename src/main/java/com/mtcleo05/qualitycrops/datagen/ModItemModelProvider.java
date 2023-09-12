package com.mtcleo05.qualitycrops.datagen;

import com.mtcleo05.qualitycrops.QualityCrops;
import com.mtcleo05.qualitycrops.items.ModCrops;
import com.mtcleo05.qualitycrops.items.ModItems;
import com.mtcleo05.qualitycrops.quality.QualityBowlFoodItem;
import com.mtcleo05.qualitycrops.quality.QualityItem;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraft.world.item.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import static com.mtcleo05.qualitycrops.utils.Utils.getItemName;
import static com.mtcleo05.qualitycrops.utils.Utils.removeQualityID;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, QualityCrops.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        Iterable<Item> items = ModItems.NEW_ITEMS.getEntries().stream().map(RegistryObject::get)::iterator;

        items.forEach(item -> {
            if(item instanceof QualityItem qualityItem){
                qualityItem(item, qualityItem.cropQuality, "minecraft");
            }else if(item instanceof QualityBowlFoodItem qualityBowlFoodItem){
                qualityItem(item, qualityBowlFoodItem.cropQuality, "minecraft");
            }else{
                simpleItem(item);
            }
        });

        items = ModCrops.CROPS.getEntries().stream().map(RegistryObject::get)::iterator;

        items.forEach(item -> {
            if(item instanceof QualityItem qualityItem){
                qualityItem(item, qualityItem.cropQuality, "minecraft");
            }else if(item instanceof QualityBowlFoodItem qualityBowlFoodItem){
                qualityItem(item, qualityBowlFoodItem.cropQuality, "minecraft");
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



}
