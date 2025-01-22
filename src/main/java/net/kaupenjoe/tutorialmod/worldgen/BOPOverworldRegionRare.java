package net.kaupenjoe.tutorialmod.worldgen;

import com.mojang.datafixers.util.Pair;
import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.biome.BOPRareOverworldBiomeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class BOPOverworldRegionRare extends Region
{
    public static final ResourceLocation LOCATION = new ResourceLocation(TutorialMod.MOD_ID, "overworld_rare");

    public BOPOverworldRegionRare(int weight)
    {
        super(LOCATION, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {
        (new BOPRareOverworldBiomeBuilder()).addBiomes(registry, mapper);
    }
}