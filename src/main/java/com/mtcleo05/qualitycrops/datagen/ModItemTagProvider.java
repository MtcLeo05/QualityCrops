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
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.tag.ForgeTags;

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


        addToTag(ForgeTags.BERRIES, Items.SWEET_BERRIES, Items.GLOW_BERRIES);
        addToTag(ForgeTags.BREAD_WHEAT, Items.BREAD);
        addToTag(ForgeTags.COOKED_BEEF, Items.COOKED_BEEF);
        addToTag(ForgeTags.COOKED_CHICKEN, Items.COOKED_CHICKEN);
        addToTag(ForgeTags.COOKED_PORK, Items.COOKED_PORKCHOP);
        addToTag(ForgeTags.COOKED_MUTTON, Items.COOKED_MUTTON);
        addToTag(ForgeTags.COOKED_FISHES_COD, Items.COOKED_COD);
        addToTag(ForgeTags.COOKED_FISHES_SALMON, Items.COOKED_SALMON);
        addToTag(ForgeTags.EGGS, Items.EGG);
        addToTag(ForgeTags.GRAIN_WHEAT, Items.WHEAT);
        addToTag(ForgeTags.RAW_BEEF, Items.BEEF);
        addToTag(ForgeTags.RAW_CHICKEN, Items.CHICKEN);
        addToTag(ForgeTags.RAW_PORK, Items.PORKCHOP);
        addToTag(ForgeTags.RAW_MUTTON, Items.MUTTON);
        addToTag(ForgeTags.RAW_FISHES_COD, Items.COD);
        addToTag(ForgeTags.RAW_FISHES_SALMON, Items.SALMON);
        addToTag(ForgeTags.RAW_FISHES_TROPICAL, Items.TROPICAL_FISH);
        addToTag(ForgeTags.VEGETABLES_BEETROOT, Items.BEETROOT);
        addToTag(ForgeTags.VEGETABLES_CARROT, Items.CARROT);
        addToTag(ForgeTags.VEGETABLES_POTATO, Items.POTATO);
    }

    private void addToTag(final TagKey<Item> tag, final Item... items) {
        for (Item item : items) {
            String basePath = ForgeRegistries.ITEMS.getKey(item).getPath();

            tag(tag).addOptional(new ResourceLocation(QualityCrops.MODID, basePath + "_iron"));
            tag(tag).addOptional(new ResourceLocation(QualityCrops.MODID, basePath + "_gold"));
            tag(tag).addOptional(new ResourceLocation(QualityCrops.MODID, basePath + "_diamond"));
        }
    }

    private static TagKey<Item> forgeItemTag(String path) {
        return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("forge", path));
    }
}