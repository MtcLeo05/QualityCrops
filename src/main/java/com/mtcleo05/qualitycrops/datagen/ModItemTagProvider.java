package com.mtcleo05.qualitycrops.datagen;

import com.mtcleo05.qualitycrops.QualityCrops;
import com.mtcleo05.qualitycrops.items.ModCrops;
import com.mtcleo05.qualitycrops.items.ModItems;
import com.mtcleo05.qualitycrops.quality.QualityBowlFoodItem;
import com.mtcleo05.qualitycrops.quality.QualityItem;
import com.mtcleo05.qualitycrops.utils.ModTags;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.tags.TagKey;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

public class ModItemTagProvider extends ItemTagsProvider {


    public ModItemTagProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagsProvider, QualityCrops.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {

        Iterable<Item> items = ModItems.NEW_ITEMS.getEntries().stream().map(RegistryObject::get)::iterator;

        items.forEach((item) -> {
            if (item instanceof QualityItem qualityItem) {
                switch (qualityItem.cropQuality) {
                    case 2 -> tag(ModTags.QUALITY_GOLD).add(item);
                    case 3 -> tag(ModTags.QUALITY_DIAMOND).add(item);
                    default -> tag(ModTags.QUALITY_IRON).add(item);
                }
            } else if (item instanceof QualityBowlFoodItem qualityItem) {
                switch (qualityItem.cropQuality) {
                    case 2 -> tag(ModTags.QUALITY_GOLD).add(item);
                    case 3 -> tag(ModTags.QUALITY_DIAMOND).add(item);
                    default -> tag(ModTags.QUALITY_IRON).add(item);
                }
            }
        });

        items = ModItems.VANILLA_QUALITY.getEntries().stream().map(RegistryObject::get)::iterator;

        items.forEach((item) -> {
            if (item instanceof QualityItem qualityItem) {
                switch (qualityItem.cropQuality) {
                    case 2 -> tag(ModTags.QUALITY_GOLD).add(item);
                    case 3 -> tag(ModTags.QUALITY_DIAMOND).add(item);
                    default -> tag(ModTags.QUALITY_IRON).add(item);
                }
            } else if (item instanceof QualityBowlFoodItem qualityItem) {
                switch (qualityItem.cropQuality) {
                    case 2 -> tag(ModTags.QUALITY_GOLD).add(item);
                    case 3 -> tag(ModTags.QUALITY_DIAMOND).add(item);
                    default -> tag(ModTags.QUALITY_IRON).add(item);
                }
            }
        });

        items = ModCrops.CROPS.getEntries().stream().map(RegistryObject::get)::iterator;

        items.forEach((item) -> {
            if (item instanceof QualityItem qualityItem) {
                switch (qualityItem.cropQuality) {
                    case 2 -> tag(ModTags.QUALITY_GOLD).add(item);
                    case 3 -> tag(ModTags.QUALITY_DIAMOND).add(item);
                    default -> tag(ModTags.QUALITY_IRON).add(item);
                }
            } else if (item instanceof QualityBowlFoodItem qualityItem) {
                switch (qualityItem.cropQuality) {
                    case 2 -> tag(ModTags.QUALITY_GOLD).add(item);
                    case 3 -> tag(ModTags.QUALITY_DIAMOND).add(item);
                    default -> tag(ModTags.QUALITY_IRON).add(item);
                }
            }
        });

    }

    private static TagKey<Item> forgeItemTag(String path) {
        return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("forge", path));
    }
}