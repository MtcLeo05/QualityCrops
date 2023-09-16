package com.mtcleo05.qualitycrops.items;

import com.mtcleo05.qualitycrops.QualityCrops;
import com.mtcleo05.qualitycrops.blocks.IronFarmland;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;

import static com.mtcleo05.qualitycrops.utils.Utils.getItemFromID;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, QualityCrops.MODID);

    public static final RegistryObject<CreativeModeTab> CROPS = CREATIVE_TABS.register("crops", () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.qualitycrops.crops")).icon(() -> new ItemStack(getItemFromID("qualitycrops:wheat", "iron"))).displayItems((idk, output) -> {
        Iterable<Item> items = ModCrops.CROPS.getEntries().stream().map(RegistryObject::get)::iterator;
        items.forEach(output::accept);
    }).build());

    public static final RegistryObject<CreativeModeTab> QUALITY_ITEMS = CREATIVE_TABS.register("quality_items", () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.qualitycrops.quality_items")).icon(() -> new ItemStack(getItemFromID("qualitycrops:dough", "iron"))).displayItems((idk, output) -> {
        Iterable<Item> items = ModItems.NEW_ITEMS.getEntries().stream().map(RegistryObject::get)::iterator;
        items.forEach((item) -> {
            if (!(item instanceof IronFertilizerItem) && !(item instanceof GoldFertilizerItem) && !(item instanceof DiamondFertilizerItem) && !(item instanceof BlockItem)) {
                output.accept(item);
            }
        });
        items = ModItems.VANILLA_QUALITY.getEntries().stream().map(RegistryObject::get)::iterator;
        items.forEach(output::accept);
    }).build());

    public static final RegistryObject<CreativeModeTab> ITEMS = CREATIVE_TABS.register("items", () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.qualitycrops.items")).icon(() -> new ItemStack(getItemFromID("qualitycrops:iron_fertilizer"))).displayItems((idk, output) -> {
        Iterable<Item> items = ModItems.NEW_ITEMS.getEntries().stream().map(RegistryObject::get)::iterator;
        items.forEach((item) -> {
            if (item instanceof IronFertilizerItem || item instanceof GoldFertilizerItem || item instanceof DiamondFertilizerItem || item instanceof BlockItem) {
                output.accept(item);
            }
        });
    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_TABS.register(eventBus);
    }
}
