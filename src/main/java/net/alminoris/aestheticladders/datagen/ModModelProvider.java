package net.alminoris.aestheticladders.datagen;

import net.alminoris.aestheticladders.AestheticLadders;
import net.alminoris.aestheticladders.block.ModBlocks;
import net.alminoris.aestheticladders.item.ModItemGroups;
import net.alminoris.aestheticladders.item.ModItems;
import net.alminoris.aestheticladders.util.helper.BlockSetsHelper;
import net.alminoris.aestheticladders.util.helper.ModJsonHelper;
import net.alminoris.aestheticladders.util.helper.ModJsonTemplates;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.util.Identifier;

public class ModModelProvider extends FabricModelProvider
{
    public ModModelProvider(FabricDataGenerator dataGenerator)
    {
        super(dataGenerator);
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

        for(String name : BlockSetsHelper.STONES)
        {
            ModJsonHelper.createBlockModel(ModJsonTemplates.STONE_LADDER_BLOCK_MODEL, name+"_stone_ladder", "minecraft:block/"+name);
            ModJsonHelper.createBlockModel(ModJsonTemplates.STONE_MOSSED_LADDER_BLOCK_MODEL, name+"_stone_ladder_mossed", "minecraft:block/"+name);
            ModJsonHelper.createBlockstate(ModJsonTemplates.STONE_LADDER_BLOCKSTATE_TEMPLATE, name+"_stone_ladder");
            blockStateModelGenerator.registerParentedItemModel(ModBlocks.STONE_LADDERS.get(name), Identifier.of(AestheticLadders.MOD_ID, "block/"+name+"_stone_ladder"));
        }

        for(String name : ModItemGroups.EXTRA_STONES_WF)
        {
            ModJsonHelper.createBlockModel(ModJsonTemplates.STONE_LADDER_BLOCK_MODEL, name+"_stone_ladder", "aestheticladders:block/"+name);
            ModJsonHelper.createBlockModel(ModJsonTemplates.STONE_MOSSED_LADDER_BLOCK_MODEL, name+"_stone_ladder_mossed", "aestheticladders:block/"+name);
            ModJsonHelper.createBlockstate(ModJsonTemplates.STONE_LADDER_BLOCKSTATE_TEMPLATE, name+"_stone_ladder");
            blockStateModelGenerator.registerParentedItemModel(ModBlocks.STONE_LADDERS.get(name), Identifier.of(AestheticLadders.MOD_ID, "block/"+name+"_stone_ladder"));
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