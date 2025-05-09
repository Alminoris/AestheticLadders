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
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModLootTableProvider::new);
		pack.addProvider(ModBlockTagProvider::new);
	}
}
