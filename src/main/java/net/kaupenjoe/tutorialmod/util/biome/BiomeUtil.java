package net.kaupenjoe.tutorialmod.util.biome;

import net.kaupenjoe.tutorialmod.init.ModConfig;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public class BiomeUtil
{
    public static ResourceKey<Biome> biomeOrFallback(Registry<Biome> biomeRegistry, ResourceKey<Biome>... biomes)
    {
        for (ResourceKey<Biome> key : biomes)
        {
            if (key == null)
                continue;

            if (ModConfig.isBiomeEnabled(key) || key.location().getNamespace().equals("minecraft"))
                return key;
        }

        throw new RuntimeException("Failed to find fallback for biome!");
    }
}