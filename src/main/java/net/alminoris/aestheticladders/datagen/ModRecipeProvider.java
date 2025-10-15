package net.alminoris.aestheticladders.datagen;

import net.alminoris.aestheticladders.block.ModBlocks;
import net.alminoris.aestheticladders.item.ModItemGroups;
import net.alminoris.aestheticladders.item.ModItems;
import net.alminoris.aestheticladders.util.helper.BlockSetsHelper;
import net.alminoris.aestheticladders.util.helper.ModJsonHelper;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider
{
    public ModRecipeProvider(FabricDataGenerator dataGenerator) 
    {
        super(dataGenerator);
    }

    @Override
    public void generateRecipes(Consumer<RecipeJsonProvider> recipeExporter)
    {
        for(String name : BlockSetsHelper.WOODS)
        {
            ShapedRecipeJsonBuilder.create(ModItems.WOODEN_STICKS.get(name), 4)
                    .input('#', Registry.BLOCK.get(new Identifier("minecraft",name+"_planks")))
                    .pattern("# ")
                    .pattern(" #")
                    .criterion(hasItem(Registry.BLOCK.get(new Identifier("minecraft",name+"_planks"))),
                            conditionsFromItem(Registry.BLOCK.get(new Identifier("minecraft",name+"_planks"))))
                    .offerTo(recipeExporter);

            ShapedRecipeJsonBuilder.create(ModBlocks.WOODEN_LADDERS.get(name), 3)
                    .input('#', ModItems.WOODEN_STICKS.get(name))
                    .pattern("# #")
                    .pattern("###")
                    .pattern("# #")
                    .criterion(hasItem(ModItems.WOODEN_STICKS.get(name)), conditionsFromItem(ModItems.WOODEN_STICKS.get(name)))
                    .offerTo(recipeExporter);
        }

        for(String name : ModItemGroups.AN_WOOD_NAMES)
        {
            ShapedRecipeJsonBuilder.create(ModBlocks.WOODEN_LADDERS.get(name), 3)
                    .input('#', ModItems.WOODEN_STICKS.get(name))
                    .pattern("# #")
                    .pattern("###")
                    .pattern("# #")
                    .criterion(hasItem(ModItems.WOODEN_STICKS.get(name)), conditionsFromItem(ModItems.WOODEN_STICKS.get(name)))
                    .offerTo(recipeExporter);
        }

        for(String name : ModItemGroups.WF_WOOD_NAMES)
        {
            ShapedRecipeJsonBuilder.create(ModBlocks.WOODEN_LADDERS.get(name), 3)
                    .input('#', ModItems.WOODEN_STICKS.get(name))
                    .pattern("# #")
                    .pattern("###")
                    .pattern("# #")
                    .criterion(hasItem(ModItems.WOODEN_STICKS.get(name)), conditionsFromItem(ModItems.WOODEN_STICKS.get(name)))
                    .offerTo(recipeExporter);
        }

        for(String name : BlockSetsHelper.STONES)
        {
            Block block = Registry.BLOCK.get(new Identifier("minecraft", name.equals("basalt_side") ? "basalt" :
                    (name.equals("quartz_block_bottom") ? "quartz_block" : name)));

            offerStonecuttingRecipe(recipeExporter, ModBlocks.STONE_LADDERS.get(name), block, 1);
        }

        for(String name : ModItemGroups.EXTRA_STONES_WF)
        {
            ModJsonHelper.createStonecuttingRecipe("wildfields:"+name,
                    Registry.BLOCK.getId(ModBlocks.STONE_LADDERS.get(name)).getPath(), "1");
        }
    }
}