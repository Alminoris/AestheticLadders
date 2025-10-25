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
        Registries.BLOCK.stream()
                .filter(block ->
                {
                    Identifier id = Registries.BLOCK.getId(block);
                    return AestheticLadders.MOD_ID.equals(id.getNamespace());
                })
                .forEach(block ->
                {
                    Identifier id = Registries.BLOCK.getId(block);
                    String path = id.getPath();

                    String[] parts = path.split("_");

                    String displayName = Arrays.stream(parts)
                            .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                            .collect(Collectors.joining(" "));

                    displayName = displayName.replace(" Nss", "");

                    translationBuilder.add("block." + AestheticLadders.MOD_ID + "." + path, displayName);
                });

        Registries.ITEM.stream()
                .filter(item ->
                {
                    Identifier id = Registries.ITEM.getId(item);
                    return AestheticLadders.MOD_ID.equals(id.getNamespace());
                })
                .forEach(item ->
                {
                    Identifier id = Registries.ITEM.getId(item);
                    String path = id.getPath();

                    String[] parts = path.split("_");

                    String displayName = Arrays.stream(parts)
                            .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                            .collect(Collectors.joining(" "));

                    translationBuilder.add("item." + AestheticLadders.MOD_ID + "." + path, displayName);
                });

        translationBuilder.add("itemgroup.aladrstab", "Aesthetic Ladders");
    }
}