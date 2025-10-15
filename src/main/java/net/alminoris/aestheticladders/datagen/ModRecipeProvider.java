package net.alminoris.aestheticladders.datagen;

import net.alminoris.aestheticladders.block.ModBlocks;
import net.alminoris.aestheticladders.item.ModItems;
import net.alminoris.aestheticladders.util.helper.BlockSetsHelper;
import net.alminoris.aestheticladders.util.helper.ModJsonHelper;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider
{
    public ModRecipeProvider(FabricDataOutput output)
    {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> recipeExporter)
    {
        for(String name : BlockSetsHelper.WOODS)
        {
            ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModItems.WOODEN_STICKS.get(name), 4)
                    .input('#', Registries.BLOCK.get(Identifier.of("minecraft",name+"_planks")))
                    .pattern("# ")
                    .pattern(" #")
                    .criterion(hasItem(Registries.BLOCK.get(Identifier.of("minecraft",name+"_planks"))),
                            conditionsFromItem(Registries.BLOCK.get(Identifier.of("minecraft",name+"_planks"))))
                    .offerTo(recipeExporter);

            ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.WOODEN_LADDERS.get(name), 3)
                    .input('#', ModItems.WOODEN_STICKS.get(name))
                    .pattern("# #")
                    .pattern("###")
                    .pattern("# #")
                    .criterion(hasItem(ModItems.WOODEN_STICKS.get(name)), conditionsFromItem(ModItems.WOODEN_STICKS.get(name)))
                    .offerTo(recipeExporter);
        }

        for(String name : BlockSetsHelper.EXTRA_WOODS_AN)
        {
            ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.WOODEN_LADDERS.get(name), 3)
                    .input('#', ModItems.WOODEN_STICKS.get(name))
                    .pattern("# #")
                    .pattern("###")
                    .pattern("# #")
                    .criterion(hasItem(ModItems.WOODEN_STICKS.get(name)), conditionsFromItem(ModItems.WOODEN_STICKS.get(name)))
                    .offerTo(recipeExporter);
        }

        for(String name : BlockSetsHelper.EXTRA_WOODS_WF)
        {
            ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.WOODEN_LADDERS.get(name), 3)
                    .input('#', ModItems.WOODEN_STICKS.get(name))
                    .pattern("# #")
                    .pattern("###")
                    .pattern("# #")
                    .criterion(hasItem(ModItems.WOODEN_STICKS.get(name)), conditionsFromItem(ModItems.WOODEN_STICKS.get(name)))
                    .offerTo(recipeExporter);
        }

        for(String name : BlockSetsHelper.STONES)
        {
            Block block = Registries.BLOCK.get(Identifier.of("minecraft", name.equals("basalt_side") ? "basalt" :
                    (name.equals("quartz_block_bottom") ? "quartz_block" : name)));

            offerStonecuttingRecipe(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.STONE_LADDERS.get(name), block, 1);
        }

        for(String name : BlockSetsHelper.EXTRA_STONES_WF)
        {
            ModJsonHelper.createStonecuttingRecipe("wildfields:"+name,
                    Registries.BLOCK.getId(ModBlocks.STONE_LADDERS.get(name)).getPath(), "1");
        }
    }
}