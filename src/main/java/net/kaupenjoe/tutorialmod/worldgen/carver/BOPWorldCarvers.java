package net.kaupenjoe.tutorialmod.worldgen.carver;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.carver.CarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.WorldCarver;

import java.util.function.BiConsumer;

public class BOPWorldCarvers
{
    public static WorldCarver<CaveCarverConfiguration> ORIGIN_CAVE;

    public static void registerCarvers(BiConsumer<ResourceLocation, WorldCarver<?>> func)
    {
        ORIGIN_CAVE = register(func, "origin_cave", new OriginCaveWorldCarver(CaveCarverConfiguration.CODEC));
    }

    private static <C extends CarverConfiguration> WorldCarver<C> register(BiConsumer<ResourceLocation, WorldCarver<?>> func, String name, WorldCarver<C> carver)
    {
        func.accept(new ResourceLocation(TutorialMod.MOD_ID, name), carver);
        return carver;
    }
}