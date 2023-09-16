package com.mtcleo05.qualitycrops.utils;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class Utils {

    public static Item getItemFromID(String id){
        String[] splitId = splitComma(id);

        ResourceLocation ID = new ResourceLocation(splitId[0], splitId[1]);

        return ForgeRegistries.ITEMS.getValue(ID);
    }

    public static String getItemName(Item item){
        return Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).getPath();
    }


    public static String[] splitComma(String input){
        return input.split(":");
    }

    public static String removeQualityID(String input) {
        int lastUnderscoreIndex = input.lastIndexOf("_");
        if (lastUnderscoreIndex != -1) {
            return input.substring(0, lastUnderscoreIndex);
        } else {
            return input;
        }
    }


    public static Item getItemFromID(String id, String quality){
        return getItemFromID(getQualityID(id, quality));
    }

    public static String getQualityID(String id, String quality){
        return  id + "_" + quality;
    }
}
