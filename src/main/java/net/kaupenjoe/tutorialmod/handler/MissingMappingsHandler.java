package net.kaupenjoe.tutorialmod.handler;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.api.BOPBiomes;
import net.kaupenjoe.tutorialmod.api.block.BOPBlocks;
import net.kaupenjoe.tutorialmod.api.item.BOPItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.MissingMappingsEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MissingMappingsHandler
{
    @SubscribeEvent
    public static void onMissingMapping(MissingMappingsEvent event)
    {
        Remapper.create(Registries.BLOCK)
                .remap("pink_cherry_sapling", Blocks.CHERRY_SAPLING)
                .remap("pink_cherry_leaves", Blocks.CHERRY_LEAVES)
                .remap("cherry_log", Blocks.CHERRY_LOG)
                .remap("cherry_wood", Blocks.CHERRY_WOOD)
                .remap("stripped_cherry_log", Blocks.STRIPPED_CHERRY_LOG)
                .remap("stripped_cherry_wood", Blocks.STRIPPED_CHERRY_WOOD)
                .remap("cherry_planks", Blocks.CHERRY_PLANKS)
                .remap("cherry_stairs", Blocks.CHERRY_STAIRS)
                .remap("cherry_slab", Blocks.CHERRY_SLAB)
                .remap("cherry_fence", Blocks.CHERRY_FENCE)
                .remap("cherry_fence_gate", Blocks.CHERRY_FENCE_GATE)
                .remap("cherry_door", Blocks.CHERRY_DOOR)
                .remap("cherry_trapdoor", Blocks.CHERRY_TRAPDOOR)
                .remap("cherry_pressure_plate", Blocks.CHERRY_PRESSURE_PLATE)
                .remap("cherry_button", Blocks.CHERRY_BUTTON)
                .remap("cherry_sign", Blocks.CHERRY_SIGN)
                .remap("cherry_wall_sign", Blocks.CHERRY_WALL_SIGN)
                .remap("potted_pink_cherry_sapling", Blocks.POTTED_CHERRY_SAPLING)
                .remap("mud", Blocks.MUD)
                .remap("mud_bricks", Blocks.MUD_BRICKS)
                .remap("mud_brick_block", Blocks.MUD_BRICKS)
                .remap("mud_brick_stairs", Blocks.MUD_BRICK_STAIRS)
                .remap("mud_brick_slab", Blocks.MUD_BRICK_SLAB)
                .remap("mud_brick_wall", Blocks.MUD_BRICK_WALL)
                .remap("loamy_grass_block", Blocks.GRASS_BLOCK)
                .remap("loamy_dirt", Blocks.DIRT)
                .remap("coarse_loamy_dirt", Blocks.COARSE_DIRT)
                .remap("loamy_grass_path", Blocks.DIRT_PATH)
                .remap("loamy_farmland", Blocks.FARMLAND)
                .remap("silty_grass_block", Blocks.GRASS_BLOCK)
                .remap("silty_dirt", Blocks.DIRT)
                .remap("coarse_silty_dirt", Blocks.COARSE_DIRT)
                .remap("silty_grass_path", Blocks.DIRT_PATH)
                .remap("silty_farmland", Blocks.FARMLAND)
                .remap("sandy_grass_block", Blocks.GRASS_BLOCK)
                .remap("sandy_dirt", Blocks.DIRT)
                .remap("coarse_sandy_dirt", Blocks.COARSE_DIRT)
                .remap("sandy_grass_path", Blocks.DIRT_PATH)
                .remap("sandy_farmland", Blocks.FARMLAND)
                .remap("ash_block", Blocks.BASALT)
                .remap("rooted_sand", Blocks.SAND)
                .remap("root", Blocks.HANGING_ROOTS)
                .remap("ivy", BOPBlocks.WILLOW_VINE)
                .remap("grass", Blocks.GRASS)
                .remap("thorn", Blocks.DEAD_BUSH)
                .remap("deathbloom", Blocks.WITHER_ROSE)
                .remap("potted_clover", Blocks.FLOWER_POT)
                .remap("potted_wildflower", Blocks.FLOWER_POT)
                .run(event);

        Remapper.create(Registries.ITEM)
                .remap("cherry_sign", Items.CHERRY_SIGN)
                .remap("cherry_boat", Items.CHERRY_BOAT)
                .remap("cherry_chest_boat", Items.CHERRY_CHEST_BOAT)
                .run(event);

        Remapper.create(Registries.BIOME)
                .remap("alps", Biomes.JAGGED_PEAKS)
                .remap("alps_foothills", Biomes.GROVE)
                .remap("bamboo_grove", Biomes.CHERRY_GROVE)
                .remap("bamboo_blossom_grove", Biomes.CHERRY_GROVE)
                .remap("cherry_blossom_grove", Biomes.CHERRY_GROVE)
                .remap("dead_swamp", Biomes.SWAMP)
                .remap("gravel_beach", Biomes.STONY_SHORE)
                .remap("lush_grassland", Biomes.SPARSE_JUNGLE)
                .remap("lush_swamp", Biomes.SWAMP)
                .remap("mangrove", Biomes.MANGROVE_SWAMP)
                .remap("oasis", Biomes.DESERT)
                .remap("redwood_forest_edge", BOPBiomes.REDWOOD_FOREST)
                .remap("redwood_hills", BOPBiomes.REDWOOD_FOREST)
                .remap("temperate_rainforest", BOPBiomes.REDWOOD_FOREST)
                .remap("temperate_rainforest_hills", BOPBiomes.REDWOOD_FOREST)
                .run(event);
    }

    private static class Remapper<T>
    {
        private final ResourceKey<Registry<T>> registryKey;
        private Map<ResourceLocation, T> remaps = new HashMap<>();
        private Map<ResourceLocation, ResourceKey<T>> remapResourceKeys = new HashMap<>();

        private Remapper(ResourceKey<Registry<T>> registryKey)
        {
            this.registryKey = registryKey;
        }

        public static <T> Remapper<T> create(ResourceKey<Registry<T>> registry)
        {
            return new Remapper<>(registry);
        }

        public Remapper<T> remap(String oldId, T replacement)
        {
            this.remaps.put(new ResourceLocation(TutorialMod.MOD_ID, oldId), replacement);
            return this;
        }

        public Remapper<T> remap(String oldId, RegistryObject<T> replacement)
        {
            return remap(oldId, replacement.get());
        }

        public Remapper<T> remap(String oldId, ResourceKey<T> replacement)
        {
            this.remapResourceKeys.put(new ResourceLocation(TutorialMod.MOD_ID, oldId), replacement);
            return this;
        }

        public void run(MissingMappingsEvent event)
        {
            for (var mapping : event.getMappings(this.registryKey, TutorialMod.MOD_ID))
            {
                if (this.remaps.containsKey(mapping.getKey()))
                {
                    mapping.remap(this.remaps.get(mapping.getKey()));
                }
                else if (this.remapResourceKeys.containsKey(mapping.getKey()))
                {
                    IForgeRegistry<T> registry = mapping.getRegistry();
                    ResourceKey<T> replacement = this.remapResourceKeys.get(mapping.getKey());

                    if (registry.containsKey(replacement.location()))
                    {
                        mapping.remap(registry.getValue(replacement.location()));
                    }
                }
            }

            // Attempt to remap items based on block remappings
            if ((ResourceKey)this.registryKey == Registries.BLOCK)
            {
                for (var mapping : event.getMappings(Registries.ITEM, TutorialMod.MOD_ID))
                {
                    if (this.remaps.containsKey(mapping.getKey()))
                    {
                        Block block = (Block)this.remaps.get(mapping.getKey());
                        mapping.remap(block.asItem());
                    }
                }
            }
        }
    }
}
