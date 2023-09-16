package com.mtcleo05.qualitycrops.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ModCreativeTabs {

    public static final CreativeModeTab CROPS = new CreativeModeTab( "qualitycrops_crops") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(getItemFromID("wheat_iron", ModCrops.CROPS));
        }

        @Override
        public @NotNull Component getDisplayName() {
            return Component.translatable("itemGroup.qualitycrops.crops");
        }
    };

    public static final CreativeModeTab QUALITY_ITEMS = new CreativeModeTab( "qualitycrops_quality_items") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(getItemFromID("dough", ModItems.NEW_ITEMS));
        }

        @Override
        public @NotNull Component getDisplayName() {
            return Component.translatable("itemGroup.qualitycrops.quality_items");
        }
    };

    public static final CreativeModeTab ITEMS = new CreativeModeTab( "qualitycrops_items") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(ModItems.IRON_FERTILIZER.get());
        }

        @Override
        public @NotNull Component getDisplayName() {
            return Component.translatable("itemGroup.qualitycrops.items");
        }
    };

    public static Item getItemFromID(String id, DeferredRegister<Item> register){
        Iterable<Item> qualityCrops = register.getEntries().stream().map(RegistryObject::get)::iterator;
        for (Item item : qualityCrops) {
            if (getItemName(item).equals(id)) {
                return item;
            }
        }
        return null;
    }

    private static String getItemName(Item item){
        return Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).getPath();
    }

}
