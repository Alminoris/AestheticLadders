package net.alminoris.aestheticladders;

import net.alminoris.aestheticladders.block.ModBlocks;
import net.alminoris.aestheticladders.util.helper.BlockSetsHelper;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class AestheticLaddersClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        for(String name : BlockSetsHelper.getWoods())
        {
            BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WOODEN_LADDERS.get(name), RenderLayer.getCutout());
        }
    }
}