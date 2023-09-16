package com.mtcleo05.qualitycrops.datagen;

import com.mtcleo05.qualitycrops.QualityCrops;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import java.io.IOException;

@Mod.EventBusSubscriber(modid = QualityCrops.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper efh = event.getExistingFileHelper();

        BlockTagsProvider blockTags = new BlockTagsProvider(gen, QualityCrops.MODID, efh);

        gen.addProvider(true, new ModRecipeProvider(gen));

        gen.addProvider(true, new ModItemModelProvider(gen, efh));
        gen.addProvider(true, new ModLanguageProvider(gen, "en_us"));

        gen.addProvider(true, new ModItemTagProvider(gen, blockTags, efh));

        try {
            gen.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
