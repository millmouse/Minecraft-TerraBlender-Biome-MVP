package net.kaupenjoe.tutorialmod.worldgen.feature;

import net.kaupenjoe.tutorialmod.api.block.BOPBlocks;
import net.kaupenjoe.tutorialmod.worldgen.BOPFeatureUtils;
import net.kaupenjoe.tutorialmod.worldgen.feature.configurations.TaigaTreeConfiguration;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class BOPTreeFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> REDWOOD_TREE = BOPFeatureUtils.createKey("redwood_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> REDWOOD_TREE_MEDIUM = BOPFeatureUtils.createKey("redwood_tree_medium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> REDWOOD_TREE_LARGE = BOPFeatureUtils.createKey("redwood_tree_large");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context)
    {

        register(context, BOPTreeFeatures.REDWOOD_TREE, BOPBaseFeatures.REDWOOD_TREE, createRedwood(BOPBlocks.REDWOOD_LOG).minHeight(10).maxHeight(30).build());
        register(context, BOPTreeFeatures.REDWOOD_TREE_MEDIUM, BOPBaseFeatures.REDWOOD_TREE, createRedwood(BOPBlocks.REDWOOD_WOOD).minHeight(25).maxHeight(40).trunkWidth(2).build());
        register(context, BOPTreeFeatures.REDWOOD_TREE_LARGE, BOPBaseFeatures.REDWOOD_TREE, createRedwood(BOPBlocks.REDWOOD_WOOD).minHeight(45).maxHeight(60).trunkWidth(3).build());

    }
    private static TaigaTreeConfiguration.Builder createRedwood(Block log)
    {
        return new TaigaTreeConfiguration.Builder().trunk(BlockStateProvider.simple(log)).foliage(BlockStateProvider.simple(BOPBlocks.REDWOOD_LEAVES));
    }
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureKey, F feature, FC configuration)
    {
        context.register(configuredFeatureKey, new ConfiguredFeature<>(feature, configuration));
    }

}
