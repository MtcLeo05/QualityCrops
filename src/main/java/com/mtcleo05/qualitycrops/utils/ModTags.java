package com.mtcleo05.qualitycrops.utils;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {

    public static TagKey<Item> QUALITY_IRON = qualityItemTag("quality/iron");
    public static TagKey<Item> QUALITY_GOLD = qualityItemTag("quality/gold");
    public static TagKey<Item> QUALITY_DIAMOND = qualityItemTag("quality/diamond");

    private static TagKey<Item> qualityItemTag(String path) {
        return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("qualitycrops", path));
    }
}
