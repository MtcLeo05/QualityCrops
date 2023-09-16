package com.mtcleo05.qualitycrops.blocks;

import com.mtcleo05.qualitycrops.QualityCrops;
import com.mtcleo05.qualitycrops.items.ModCreativeTabs;
import com.mtcleo05.qualitycrops.items.ModItems;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> MOD_BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, QualityCrops.MODID);

    public static final RegistryObject<Block> IRON_FARMLAND = registerBlock("iron_farmland", () ->
            new IronFarmland(
                    BlockBehaviour.Properties.of(Material.DIRT)
                            .strength(2.5f)
            ));

    public static final RegistryObject<Block> GOLD_FARMLAND = registerBlock("gold_farmland", () ->
            new GoldFarmland(
                    BlockBehaviour.Properties.of(Material.DIRT)
                            .strength(2.5f)
    ));

    public static final RegistryObject<Block> DIAMOND_FARMLAND = registerBlock("diamond_farmland", () ->
            new DiamondFarmland(
                    BlockBehaviour.Properties.of(Material.DIRT)
                            .strength(2.5f)
            ));

    private static RegistryObject<Block> registerBlock(String name, Supplier<Block> block){
        RegistryObject<Block> toReturn = MOD_BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block){
        ModItems.NEW_ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()
                .tab(ModCreativeTabs.ITEMS)));
    }


    public static void register(IEventBus eventBus){

        MOD_BLOCKS.register(eventBus);
    }

}
