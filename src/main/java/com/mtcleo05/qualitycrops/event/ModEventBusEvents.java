package com.mtcleo05.qualitycrops.event;

import com.mtcleo05.qualitycrops.QualityCrops;
import com.mtcleo05.qualitycrops.loot.AddQualityModifier;
import com.mtcleo05.qualitycrops.loot.AddVariableQualityModifier;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = QualityCrops.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
        event.getRegistry().registerAll(
                new AddQualityModifier.Serializer().setRegistryName(new ResourceLocation(QualityCrops.MODID ,"add_quality")),
                new AddVariableQualityModifier.Serializer().setRegistryName(new ResourceLocation(QualityCrops.MODID, "add_variable_quality"))
        );
    }
}