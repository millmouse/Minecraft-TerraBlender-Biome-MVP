package net.kaupenjoe.tutorialmod.worldgen.placement;

import net.kaupenjoe.tutorialmod.worldgen.BOPPlacementUtils;
import net.kaupenjoe.tutorialmod.worldgen.feature.BOPVegetationFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class BOPVegetationPlacements {

    public static final ResourceKey<PlacedFeature> TREES_REDWOOD_FOREST = BOPPlacementUtils.createKey("trees_redwood_forest");
    public static final ResourceKey<PlacedFeature> PATCH_FERN_4 = BOPPlacementUtils.createKey("patch_fern_4");
    public static final ResourceKey<PlacedFeature> PATCH_LARGE_FERN_EXTRA = BOPPlacementUtils.createKey("patch_large_fern_extra");
    public static final ResourceKey<PlacedFeature> MOSS_SPLATTER = BOPPlacementUtils.createKey("moss_splatter");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureGetter = context.lookup(Registries.CONFIGURED_FEATURE);

        final Holder<ConfiguredFeature<?, ?>> TREES_REDWOOD_FOREST = configuredFeatureGetter.getOrThrow(BOPVegetationFeatures.TREES_REDWOOD_FOREST);
        final Holder<ConfiguredFeature<?, ?>> PATCH_FERN = configuredFeatureGetter.getOrThrow(BOPVegetationFeatures.PATCH_FERN);
        final Holder<ConfiguredFeature<?, ?>> PATCH_LARGE_FERN = configuredFeatureGetter.getOrThrow(VegetationFeatures.PATCH_LARGE_FERN);

        register(context, BOPVegetationPlacements.PATCH_FERN_4, PATCH_FERN, VegetationPlacements.worldSurfaceSquaredWithCount(4));
        register(context, BOPVegetationPlacements.PATCH_LARGE_FERN_EXTRA, PATCH_LARGE_FERN, RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
    }

    protected static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> placedFeatureKey, Holder<ConfiguredFeature<?, ?>> configuredFeature, PlacementModifier... modifiers)
    {
        register(context, placedFeatureKey, configuredFeature, List.of(modifiers));
    }

    protected static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> placedFeatureKey, Holder<ConfiguredFeature<?, ?>> configuredFeature, List<PlacementModifier> modifiers)
    {
        context.register(placedFeatureKey, new PlacedFeature(configuredFeature, modifiers));
    }
}
