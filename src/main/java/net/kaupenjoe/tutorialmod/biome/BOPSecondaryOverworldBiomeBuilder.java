package net.kaupenjoe.tutorialmod.biome;

import net.kaupenjoe.tutorialmod.api.BOPBiomes;
import net.kaupenjoe.tutorialmod.util.biome.BiomeUtil;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;

public class BOPSecondaryOverworldBiomeBuilder extends BOPOverworldBiomeBuilder {
    private final ResourceKey<Biome>[][] MIDDLE_BIOMES_BOP = new ResourceKey[][]{
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
    };

    private final ResourceKey<Biome>[][] MIDDLE_BIOMES_VARIANT_BOP = new ResourceKey[][]{
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
    };

    private final ResourceKey<Biome>[][] PLATEAU_BIOMES_BOP = new ResourceKey[][]{
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
    };

    private final ResourceKey<Biome>[][] PLATEAU_BIOMES_VARIANT_BOP = new ResourceKey[][]{
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
    };

    private final ResourceKey<Biome>[][] SWAMP_BIOMES_BOP = new ResourceKey[][]{
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
    };

    private final ResourceKey<Biome>[][] RIVER_BIOMES_BOP = new ResourceKey[][]{
            {Biomes.FROZEN_RIVER, Biomes.FROZEN_RIVER, null, Biomes.FROZEN_RIVER, Biomes.RIVER},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
    };

    private final ResourceKey<Biome>[][] BEACH_BIOMES_BOP = new ResourceKey[][]{
            {Biomes.SNOWY_BEACH, Biomes.SNOWY_BEACH, Biomes.SNOWY_BEACH, Biomes.SNOWY_BEACH, null},
            {null, null, null, null, null},
            {null, null, null, Biomes.BEACH, Biomes.BEACH},
            {null, null, null, null, null},
            {null, null, null, null, null},
    };

    private final ResourceKey<Biome>[][] STONY_SHORES_BIOMES_BOP = new ResourceKey[][]{
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
    };

    @Override
    protected ResourceKey<Biome> pickMiddleBiomeBOP(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex, Climate.Parameter weirdness) {
        ResourceKey<Biome> middleBiome = BiomeUtil.biomeOrFallback(biomeRegistry, this.MIDDLE_BIOMES_BOP[temperatureIndex][humidityIndex], this.MIDDLE_BIOMES[temperatureIndex][humidityIndex]);

        if (weirdness.max() < 0) return middleBiome;
        else {
            return BiomeUtil.biomeOrFallback(biomeRegistry, this.MIDDLE_BIOMES_VARIANT_BOP[temperatureIndex][humidityIndex], middleBiome);
        }
    }

    @Override
    protected ResourceKey<Biome> pickPlateauBiomeBOP(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex, Climate.Parameter weirdness) {
        if (weirdness.max() < 0L)
            return BiomeUtil.biomeOrFallback(biomeRegistry, this.PLATEAU_BIOMES_BOP[temperatureIndex][humidityIndex], this.PLATEAU_BIOMES[temperatureIndex][humidityIndex]);
        else
            return BiomeUtil.biomeOrFallback(biomeRegistry, this.PLATEAU_BIOMES_VARIANT_BOP[temperatureIndex][humidityIndex], this.PLATEAU_BIOMES_BOP[temperatureIndex][humidityIndex], this.PLATEAU_BIOMES_VARIANT[temperatureIndex][humidityIndex], this.PLATEAU_BIOMES[temperatureIndex][humidityIndex]);
    }

    @Override
    protected ResourceKey<Biome> pickSwampBiomeBOP(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex, Climate.Parameter weirdness) {
        return BiomeUtil.biomeOrFallback(biomeRegistry, this.SWAMP_BIOMES_BOP[temperatureIndex][humidityIndex], this.pickSwampBiomeVanilla(temperatureIndex, humidityIndex, weirdness));
    }

    @Override
    protected ResourceKey<Biome> pickRiverBiomeBOP(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex) {
        return BiomeUtil.biomeOrFallback(biomeRegistry, this.RIVER_BIOMES_BOP[temperatureIndex][humidityIndex], temperatureIndex == 0 ? Biomes.FROZEN_RIVER : Biomes.RIVER);
    }

    @Override
    protected ResourceKey<Biome> pickBeachBiomeBOP(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex) {
        return BiomeUtil.biomeOrFallback(biomeRegistry, this.BEACH_BIOMES_BOP[temperatureIndex][humidityIndex], this.BEACH_BIOMES[temperatureIndex][humidityIndex]);
    }

    @Override
    protected ResourceKey<Biome> pickStonyShoresBiomeBOP(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex) {
        return BiomeUtil.biomeOrFallback(biomeRegistry, this.STONY_SHORES_BIOMES_BOP[temperatureIndex][humidityIndex], Biomes.STONY_SHORE);
    }
}