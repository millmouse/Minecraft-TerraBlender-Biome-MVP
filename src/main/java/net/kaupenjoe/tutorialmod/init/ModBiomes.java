package net.kaupenjoe.tutorialmod.init;

import net.kaupenjoe.tutorialmod.api.BOPBiomes;
import net.kaupenjoe.tutorialmod.biome.BOPOverworldBiomes;
import net.kaupenjoe.tutorialmod.worldgen.BOPOverworldRegionPrimary;
import net.kaupenjoe.tutorialmod.worldgen.BOPOverworldRegionRare;
import net.kaupenjoe.tutorialmod.worldgen.BOPOverworldRegionSecondary;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import terrablender.api.Regions;

import java.lang.reflect.Field;
import java.util.Map;

public class ModBiomes {
    public static void setup() {
        // Register our biomes
        registerVillagerTypes();
    }

    public static void setupTerraBlender() {
        // Register our regions
        Regions.register(new BOPOverworldRegionPrimary(ModConfig.generation.bopPrimaryOverworldRegionWeight));
        Regions.register(new BOPOverworldRegionSecondary(ModConfig.generation.bopSecondaryOverworldRegionWeight));
        Regions.register(new BOPOverworldRegionRare(ModConfig.generation.bopOverworldRareRegionWeight));
    }

    //NeoForge
    public static void bootstrapBiomes(BootstapContext<Biome> context) {
        HolderGetter<ConfiguredWorldCarver<?>> carverGetter = context.lookup(Registries.CONFIGURED_CARVER);
        HolderGetter<PlacedFeature> placedFeatureGetter = context.lookup(Registries.PLACED_FEATURE);

        register(context, BOPBiomes.REDWOOD_FOREST, BOPOverworldBiomes.redwoodForest(placedFeatureGetter, carverGetter));
    }

    private static void registerVillagerTypes() {
        registerVillagerType(BOPBiomes.REDWOOD_FOREST, VillagerType.PLAINS);
    }

    private static void register(BootstapContext<Biome> context, ResourceKey<Biome> key, Biome biome) {
        context.register(key, biome);
    }

    // This is successfully "ignored" by the accesstransformer.cfg in META-INF, referenced by build.gradle.
    private static void registerVillagerType(ResourceKey<Biome> key, VillagerType type) {
        if (ModConfig.isBiomeEnabled(key)) {
            VillagerType.BY_BIOME.put(key, type);
        }
    }
}