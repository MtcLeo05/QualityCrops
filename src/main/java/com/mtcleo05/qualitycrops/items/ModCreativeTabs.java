package com.mtcleo05.qualitycrops.items;

import com.mtcleo05.qualitycrops.QualityCrops;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.rmi.registry.Registry;
import java.util.Iterator;
import java.util.Objects;

public class ModCreativeTabs {


    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, QualityCrops.MODID);

    public static final RegistryObject<CreativeModeTab> CROPS = CREATIVE_TABS.register("qualitycrops_crops",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.qualitycrops.crops"))
                    .icon(() -> new ItemStack(getItemFromID("wheat_iron", ModCrops.CROPS)))
                    .displayItems((idk, output) -> {
                        Iterable<Item> items = ModCrops.CROPS.getEntries().stream().map(RegistryObject::get)::iterator;
                        items.forEach(output::accept);
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> ITEMS = CREATIVE_TABS.register("qualitycrops_items",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.qualitycrops.items"))
                    .icon(() -> new ItemStack(getItemFromID("dough", ModItems.NEW_ITEMS)))
                    .displayItems((idk, output) -> {
                        Iterable<Item> items = ModItems.NEW_ITEMS.getEntries().stream().map(RegistryObject::get)::iterator;
                        items.forEach(output::accept);
                        items = ModItems.VANILLA_QUALITY.getEntries().stream().map(RegistryObject::get)::iterator;
                        items.forEach(output::accept);
                    })
                    .build());

    public static void register(IEventBus eventBus){
        CREATIVE_TABS.register(eventBus);
    }

    public static Item getItemFromID(String id, DeferredRegister<Item> register){
        Iterable<Item> qualityCrops = register.getEntries().stream().map(RegistryObject::get)::iterator;
        for (Item item : qualityCrops) {
            if (getItemName(item).equals(id)) {
                return item;
            }
        }
        return Items.BARRIER;
    }

    private static String getItemName(Item item){
        return Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).getPath();
    }

}
