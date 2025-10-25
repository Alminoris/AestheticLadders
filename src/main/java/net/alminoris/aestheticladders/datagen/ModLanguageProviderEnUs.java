package net.alminoris.aestheticladders.datagen;

import net.alminoris.aestheticladders.AestheticLadders;
import net.alminoris.aestheticladders.util.helper.BlockSetsHelper;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ModLanguageProviderEnUs extends FabricLanguageProvider
{
    public ModLanguageProviderEnUs(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture)
    {
        super(output,"en_us", registriesFuture);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder)
    {
        for (Block block : Registries.BLOCK)
        {
            Optional<RegistryKey<Block>> id = Registries.BLOCK.getKey(block);
            String path = id.get().getValue().getPath();

            String[] parts = path.split("_");

            String displayName = Arrays.stream(parts)
                    .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                    .collect(Collectors.joining(" "));

            displayName = displayName.replace(" Nss", "");

            translationBuilder.add("block." + AestheticLadders.MOD_ID + "." + path, displayName);
        }

        for (Item item : Registries.ITEM)
        {
            Optional<RegistryKey<Item>> id = Registries.ITEM.getKey(item);
            String path = id.get().getValue().getPath();

            String[] parts = path.split("_");

            String displayName = Arrays.stream(parts)
                    .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                    .collect(Collectors.joining(" "));

            translationBuilder.add("item." + AestheticLadders.MOD_ID + "." + path, displayName);
        }

        translationBuilder.add("itemgroup.aladrstab", "Aesthetic Ladders");
    }
}