package net.kaupenjoe.tutorialmod.api;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import java.util.List;

public class BOPBiomes {
    private static List<ResourceKey<Biome>> overworldBiomes = Lists.newArrayList();
    private static List<ResourceKey<Biome>> allBiomes = Lists.newArrayList();

    public static final ResourceKey<Biome> REDWOOD_FOREST = registerOverworld("redwood_forest");

    public static List<ResourceKey<Biome>> getOverworldBiomes()
    {
        return ImmutableList.copyOf(overworldBiomes);
    }

    public static List<ResourceKey<Biome>> getAllBiomes()
    {
        return ImmutableList.copyOf(allBiomes);
    }


    private static ResourceKey<Biome> registerOverworld(String name)
    {
        ResourceKey<Biome> key = ResourceKey.create(Registries.BIOME, new ResourceLocation(BOPAPI.MOD_ID, name));
        overworldBiomes.add(key);
        allBiomes.add(key);
        return key;
    }
}
