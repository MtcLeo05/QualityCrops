package com.mtcleo05.qualitycrops;

import com.mojang.logging.LogUtils;
import com.mtcleo05.qualitycrops.blocks.ModBlocks;
import com.mtcleo05.qualitycrops.items.ModCrops;
import com.mtcleo05.qualitycrops.items.ModItems;
import com.mtcleo05.qualitycrops.loot.QualityLootModifiers;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(QualityCrops.MODID)
public class QualityCrops {
    public static final String MODID = "qualitycrops";
    private static final Logger LOG = LogUtils.getLogger();

    public QualityCrops() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCrops.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        QualityLootModifiers.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }
}
