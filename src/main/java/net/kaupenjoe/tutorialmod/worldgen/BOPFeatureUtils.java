package net.kaupenjoe.tutorialmod.worldgen;


import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.worldgen.feature.BOPVegetationFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class BOPFeatureUtils
{
    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context)
    {
//        BOPCaveFeatures.bootstrap(context);
//        BOPMiscOverworldFeatures.bootstrap(context);
//        BOPNetherFeatures.bootstrap(context);
//        BOPEndFeatures.bootstrap(context);
//        BOPTreeFeatures.bootstrap(context);
        BOPVegetationFeatures.bootstrap(context);
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name)
    {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(TutorialMod.MOD_ID, name));
    }
}