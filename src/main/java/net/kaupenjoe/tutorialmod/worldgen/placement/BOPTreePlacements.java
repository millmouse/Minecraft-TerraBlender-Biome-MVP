package net.kaupenjoe.tutorialmod.worldgen.placement;

import net.kaupenjoe.tutorialmod.api.block.BOPBlocks;
import net.kaupenjoe.tutorialmod.worldgen.BOPPlacementUtils;
import net.kaupenjoe.tutorialmod.worldgen.feature.BOPTreeFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class BOPTreePlacements {

    public static final ResourceKey<PlacedFeature> REDWOOD_TREE_CHECKED = BOPPlacementUtils.createKey("redwood_tree");
    public static final ResourceKey<PlacedFeature> REDWOOD_TREE_LARGE_CHECKED = BOPPlacementUtils.createKey("redwood_tree_large");
    public static final ResourceKey<PlacedFeature> REDWOOD_TREE_MEDIUM_CHECKED = BOPPlacementUtils.createKey("redwood_tree_medium");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureGetter = context.lookup(Registries.CONFIGURED_FEATURE);

        final Holder<ConfiguredFeature<?, ?>> REDWOOD_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.REDWOOD_TREE);
        final Holder<ConfiguredFeature<?, ?>> REDWOOD_TREE_LARGE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.REDWOOD_TREE_LARGE);
        final Holder<ConfiguredFeature<?, ?>> REDWOOD_TREE_MEDIUM = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.REDWOOD_TREE_MEDIUM);

        //Todo: bring back all when creative tab is solved.

//        register(context, BOPTreePlacements.REDWOOD_TREE_CHECKED, REDWOOD_TREE, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.REDWOOD_SAPLING)));
//        register(context, BOPTreePlacements.REDWOOD_TREE_LARGE_CHECKED, REDWOOD_TREE_LARGE, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.REDWOOD_SAPLING)));
//        register(context, BOPTreePlacements.REDWOOD_TREE_MEDIUM_CHECKED, REDWOOD_TREE_MEDIUM, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.REDWOOD_SAPLING)));
    }

    protected static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> placedFeatureKey, Holder<ConfiguredFeature<?, ?>> configuredFeature, PlacementModifier... modifiers) {
        register(context, placedFeatureKey, configuredFeature, List.of(modifiers));
    }

    protected static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> placedFeatureKey, Holder<ConfiguredFeature<?, ?>> configuredFeature, List<PlacementModifier> modifiers) {
        context.register(placedFeatureKey, new PlacedFeature(configuredFeature, modifiers));
    }
}
