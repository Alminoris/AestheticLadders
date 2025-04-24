package net.alminoris.aestheticladders.datagen;

import net.alminoris.aestheticladders.block.ModBlocks;
import net.alminoris.aestheticladders.item.ModItems;
import net.alminoris.aestheticladders.util.helper.BlockSetsHelper;
import net.alminoris.aestheticladders.util.helper.ModJsonHelper;
import net.alminoris.aestheticladders.util.helper.ModJsonTemplates;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider
{
    public ModModelProvider(FabricDataOutput output)
    {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator)
    {
        for(String name : BlockSetsHelper.getWoods())
        {
            ModJsonHelper.createBlockModel(ModJsonTemplates.LADDER_BLOCK_MODEL, name+"_ladder");
            blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.WOODEN_LADDERS.get(name));
            blockStateModelGenerator.registerItemModel(ModBlocks.WOODEN_LADDERS.get(name));
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator)
    {
        for(String name : BlockSetsHelper.getWoods())
        {
            itemModelGenerator.register(ModItems.WOODEN_STICKS.get(name), Models.GENERATED);
        }
    }
}