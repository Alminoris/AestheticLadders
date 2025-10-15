package net.alminoris.aestheticladders.block;

import net.alminoris.aestheticladders.AestheticLadders;
import net.alminoris.aestheticladders.block.custom.StoneLadderBlock;
import net.alminoris.aestheticladders.item.ModItemGroups;
import net.alminoris.aestheticladders.util.helper.BlockSetsHelper;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Dictionary;
import java.util.Hashtable;

public class ModBlocks
{
    public static final Dictionary<String, Block> WOODEN_LADDERS = new Hashtable<>()
    {{
        for(String name : BlockSetsHelper.getWoods())
        {
            put(name, registerBlock(name+"_ladder", new LadderBlock(AbstractBlock.Settings.copy(Blocks.LADDER))));
        }
    }};

    public static final Dictionary<String, Block> STONE_LADDERS = new Hashtable<>()
    {{
        for(String name : BlockSetsHelper.getStones())
        {
            put(name, registerBlock(name+"_stone_ladder", new StoneLadderBlock()));
        }
    }};

    public static Block registerBlock(String name, Block block)
    {
        registerBlockItem(name, block);
        return Registry.register(Registry.BLOCK, new Identifier(AestheticLadders.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block)
    {
        Registry.register(Registry.ITEM, new Identifier(AestheticLadders.MOD_ID, name),
                new BlockItem(block, new Item.Settings().group(ModItemGroups.ALADRS_TAB)));
    }

    public static void registerBlocks()
    {

    }
}