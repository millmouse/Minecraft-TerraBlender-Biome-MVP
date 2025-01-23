package net.kaupenjoe.tutorialmod.block.trees;

import net.kaupenjoe.tutorialmod.worldgen.feature.BOPTreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.Optional;

public class BOPTreeGrowers {

    private static BOPMegaTreeGrowerImpl register(float secondaryChance,
                                                  Optional<ResourceKey<ConfiguredFeature<?, ?>>> megaTree,
                                                  Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryMegaTree,
                                                  Optional<ResourceKey<ConfiguredFeature<?, ?>>> tree,
                                                  Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryTree,
                                                  Optional<ResourceKey<ConfiguredFeature<?, ?>>> flowers,
                                                  Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryFlowers) {
        return new BOPMegaTreeGrowerImpl(secondaryChance,
                megaTree, secondaryMegaTree, tree, secondaryTree, flowers, secondaryFlowers);
    }

    private static BOPTreeGrowerImpl register(
            Optional<ResourceKey<ConfiguredFeature<?, ?>>> tree,
            Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryTree) {
        return new BOPTreeGrowerImpl(0.1F, tree, secondaryTree, Optional.empty(), Optional.empty());
    }

    private static BOPMegaTreeGrowerImpl register(
            Optional<ResourceKey<ConfiguredFeature<?, ?>>> tree,
            Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryTree,
            Optional<ResourceKey<ConfiguredFeature<?, ?>>> megaTree,
            Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryMegaTree) {
        return register(0.1F, megaTree, secondaryMegaTree, tree, secondaryTree, Optional.empty(), Optional.empty());
    }


    public static final BOPMegaTreeGrowerImpl REDWOOD = register(Optional.of(BOPTreeFeatures.REDWOOD_TREE), Optional.empty(), Optional.of(BOPTreeFeatures.REDWOOD_TREE_MEDIUM), Optional.of(BOPTreeFeatures.REDWOOD_TREE_LARGE));
}
