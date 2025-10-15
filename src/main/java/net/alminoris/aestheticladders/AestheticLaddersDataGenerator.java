package net.alminoris.aestheticladders;

import net.alminoris.aestheticladders.datagen.ModBlockTagProvider;
import net.alminoris.aestheticladders.datagen.ModLootTableProvider;
import net.alminoris.aestheticladders.datagen.ModModelProvider;
import net.alminoris.aestheticladders.datagen.ModRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class AestheticLaddersDataGenerator implements DataGeneratorEntrypoint
{
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator)
	{
		fabricDataGenerator.addProvider(ModBlockTagProvider::new);
		fabricDataGenerator.addProvider(ModModelProvider::new);
		fabricDataGenerator.addProvider(ModRecipeProvider::new);
		fabricDataGenerator.addProvider(ModLootTableProvider::new);
	}
}
