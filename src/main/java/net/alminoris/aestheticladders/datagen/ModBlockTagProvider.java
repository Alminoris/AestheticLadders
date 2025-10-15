package net.alminoris.aestheticladders.datagen;

import net.alminoris.aestheticladders.block.ModBlocks;
import net.alminoris.aestheticladders.util.helper.BlockSetsHelper;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.tag.BlockTags;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider
{
    public ModBlockTagProvider(FabricDataGenerator dataGenerator)
    {
        super(dataGenerator);
    }

    @Override
    protected void generateTags()
    {
        for (String name : BlockSetsHelper.getWoods())
        {
            getOrCreateTagBuilder(BlockTags.CLIMBABLE)
                    .add(ModBlocks.WOODEN_LADDERS.get(name));
        }

        for (String name : BlockSetsHelper.getStones())
        {
            getOrCreateTagBuilder(BlockTags.CLIMBABLE)
                    .add(ModBlocks.STONE_LADDERS.get(name));
        }
    }
}