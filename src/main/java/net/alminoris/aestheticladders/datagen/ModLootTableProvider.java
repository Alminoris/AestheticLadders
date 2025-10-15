package net.alminoris.aestheticladders.datagen;

import net.alminoris.aestheticladders.block.ModBlocks;
import net.alminoris.aestheticladders.util.helper.BlockSetsHelper;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class ModLootTableProvider extends FabricBlockLootTableProvider
{
    public ModLootTableProvider(FabricDataGenerator dataGenerator)
    {
        super(dataGenerator);
    }

    @Override
    public void generateBlockLootTables()
    {
        for(String name : BlockSetsHelper.getWoods())
        {
            addDrop(ModBlocks.WOODEN_LADDERS.get(name));
        }

        for(String name : BlockSetsHelper.getStones())
        {
            addDrop(ModBlocks.STONE_LADDERS.get(name));
        }
    }
}