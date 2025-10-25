package net.alminoris.aestheticladders.datagen;

import net.alminoris.aestheticladders.AestheticLadders;
import net.alminoris.aestheticladders.util.helper.BlockSetsHelper;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ModLanguageProviderEnUs extends FabricLanguageProvider
{
    public ModLanguageProviderEnUs(FabricDataGenerator output)
    {
        super(output,"en_us");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder)
    {
        Registry.BLOCK.stream()
                .filter(block ->
                {
                    Identifier id = Registry.BLOCK.getId(block);
                    return AestheticLadders.MOD_ID.equals(id.getNamespace());
                })
                .forEach(block ->
                {
                    Identifier id = Registry.BLOCK.getId(block);
                    String path = id.getPath();

                    String[] parts = path.split("_");

                    String displayName = Arrays.stream(parts)
                            .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                            .collect(Collectors.joining(" "));

                    displayName = displayName.replace(" Nss", "");

                    translationBuilder.add("block." + AestheticLadders.MOD_ID + "." + path, displayName);
                });

        Registry.ITEM.stream()
                .filter(item ->
                {
                    Identifier id = Registry.ITEM.getId(item);
                    return AestheticLadders.MOD_ID.equals(id.getNamespace());
                })
                .forEach(item ->
                {
                    Identifier id = Registry.ITEM.getId(item);
                    String path = id.getPath();

                    String[] parts = path.split("_");

                    String displayName = Arrays.stream(parts)
                            .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                            .collect(Collectors.joining(" "));

                    translationBuilder.add("item." + AestheticLadders.MOD_ID + "." + path, displayName);
                });

        translationBuilder.add("itemGroup.aestheticladders.aladrstab", "Aesthetic Ladders");
    }
}