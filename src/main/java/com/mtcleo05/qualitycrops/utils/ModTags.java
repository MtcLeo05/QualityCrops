package com.mtcleo05.qualitycrops.utils;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {

    public static TagKey<Item> QUALITY_IRON = forgeItemTag("quality/iron");
    public static TagKey<Item> QUALITY_GOLD = forgeItemTag("quality/gold");
    public static TagKey<Item> QUALITY_DIAMOND = forgeItemTag("quality/diamond");

    private static TagKey<Item> forgeItemTag(String path) {
        return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("forge", path));
    }
}
