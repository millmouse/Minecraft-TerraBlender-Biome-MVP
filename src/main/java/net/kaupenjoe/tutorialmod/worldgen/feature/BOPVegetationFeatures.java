package net.kaupenjoe.tutorialmod.worldgen.feature;

import com.google.common.collect.ImmutableList;
import net.kaupenjoe.tutorialmod.worldgen.BOPFeatureUtils;
import net.kaupenjoe.tutorialmod.worldgen.placement.BOPTreePlacements;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static net.minecraft.data.worldgen.features.FeatureUtils.register;

public class BOPVegetationFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_REDWOOD_FOREST = BOPFeatureUtils.createKey("trees_redwood_forest");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_FERN = BOPFeatureUtils.createKey("patch_fern");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<PlacedFeature> placedFeatureGetter = context.lookup(Registries.PLACED_FEATURE);

        final Holder<PlacedFeature> REDWOOD_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.REDWOOD_TREE_CHECKED);
        final Holder<PlacedFeature> REDWOOD_TREE_LARGE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.REDWOOD_TREE_LARGE_CHECKED);
        final Holder<PlacedFeature> REDWOOD_TREE_MEDIUM_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.REDWOOD_TREE_MEDIUM_CHECKED);

        register(context, BOPVegetationFeatures.PATCH_FERN, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.FERN))));
        register(context, BOPVegetationFeatures.TREES_REDWOOD_FOREST, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(REDWOOD_TREE_CHECKED, 0.3f), new WeightedPlacedFeature(REDWOOD_TREE_LARGE_CHECKED, 0.5f)), REDWOOD_TREE_MEDIUM_CHECKED));

    }
}
