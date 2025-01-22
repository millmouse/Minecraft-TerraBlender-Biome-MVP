package net.kaupenjoe.tutorialmod.worldgen.feature;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.worldgen.feature.configurations.TaigaTreeConfiguration;
import net.kaupenjoe.tutorialmod.worldgen.feature.tree.BOPTreeFeature;
import net.kaupenjoe.tutorialmod.worldgen.feature.tree.RedwoodTreeFeature;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

import java.util.function.BiConsumer;

public class BOPBaseFeatures {
    public static BOPTreeFeature<TaigaTreeConfiguration> REDWOOD_TREE;


    public static void registerFeatures(BiConsumer<ResourceLocation, Feature<?>> func) {
            REDWOOD_TREE = register(func, "redwood_tree", new RedwoodTreeFeature(TaigaTreeConfiguration.CODEC));
    }
    private static <C extends FeatureConfiguration, F extends Feature<C>> F register(BiConsumer<ResourceLocation, Feature<?>> func, String name, F feature)
    {
        func.accept(new ResourceLocation(TutorialMod.MOD_ID, name), feature);
        return feature;
    }
}
