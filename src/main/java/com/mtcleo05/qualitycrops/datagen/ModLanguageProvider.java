package com.mtcleo05.qualitycrops.datagen;

import com.mtcleo05.qualitycrops.QualityCrops;
import com.mtcleo05.qualitycrops.items.ModCrops;
import com.mtcleo05.qualitycrops.items.ModItems;
import com.mtcleo05.qualitycrops.quality.QualityBowlFoodItem;
import com.mtcleo05.qualitycrops.quality.QualityItem;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;

import static com.mtcleo05.qualitycrops.utils.Utils.getItemName;
import static com.mtcleo05.qualitycrops.utils.Utils.removeQualityID;


public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(DataGenerator gen, String locale) {
        super(gen, QualityCrops.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        Iterable<Item> newItems = ModItems.NEW_ITEMS.getEntries().stream().map(RegistryObject::get)::iterator;

        newItems.forEach(item -> {
            if(item instanceof QualityItem){
                this.add(item, cTC(removeQualityID(getItemName(item))));
            }else if(item instanceof QualityBowlFoodItem){
                this.add(item, cTC(removeQualityID(getItemName(item))));
            }else{
                this.add(item, cTC(getItemName(item)));
            }
        });

        newItems = ModCrops.CROPS.getEntries().stream().map(RegistryObject::get)::iterator;

        newItems.forEach(item -> {
            if(item instanceof QualityItem){
                this.add(item, cTC(removeQualityID(getItemName(item))));
            }else if(item instanceof QualityBowlFoodItem){
                this.add(item, cTC(removeQualityID(getItemName(item))));
            }else{
                this.add(item, cTC(getItemName(item)));
            }
        });
    }

    //convert title case: alfa_beta -> Alfa Beta
    public static String cTC(String input) {
        String[] words = input.split("_");

        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)));
                result.append(word.substring(1).toLowerCase());
                result.append(" ");
            }
        }

        return result.toString().trim();
    }


}
