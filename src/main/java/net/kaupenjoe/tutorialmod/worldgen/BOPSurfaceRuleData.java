package net.kaupenjoe.tutorialmod.worldgen;

import com.google.common.collect.ImmutableList;
import net.kaupenjoe.tutorialmod.api.BOPBiomes;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

public class BOPSurfaceRuleData {

    private static final SurfaceRules.RuleSource AIR = makeStateRule(Blocks.AIR);
    private static final SurfaceRules.RuleSource BEDROCK = makeStateRule(Blocks.BEDROCK);
    private static final SurfaceRules.RuleSource LIGHT_BLUE_TERRACOTTA = makeStateRule(Blocks.LIGHT_BLUE_TERRACOTTA);
    private static final SurfaceRules.RuleSource CYAN_TERRACOTTA = makeStateRule(Blocks.CYAN_TERRACOTTA);
    private static final SurfaceRules.RuleSource LIGHT_GRAY_TERRACOTTA = makeStateRule(Blocks.LIGHT_GRAY_TERRACOTTA);
    private static final SurfaceRules.RuleSource TERRACOTTA = makeStateRule(Blocks.TERRACOTTA);
    private static final SurfaceRules.RuleSource STONE = makeStateRule(Blocks.STONE);
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource PODZOL = makeStateRule(Blocks.PODZOL);
    private static final SurfaceRules.RuleSource COARSE_DIRT = makeStateRule(Blocks.COARSE_DIRT);
    private static final SurfaceRules.RuleSource GRAVEL = makeStateRule(Blocks.GRAVEL);
    private static final SurfaceRules.RuleSource SAND = makeStateRule(Blocks.SAND);
    private static final SurfaceRules.RuleSource SANDSTONE = makeStateRule(Blocks.SANDSTONE);
    private static final SurfaceRules.RuleSource SNOW_BLOCK = makeStateRule(Blocks.SNOW_BLOCK);
    private static final SurfaceRules.RuleSource POWDER_SNOW = makeStateRule(Blocks.POWDER_SNOW);
    private static final SurfaceRules.RuleSource WATER = makeStateRule(Blocks.WATER);
    private static final SurfaceRules.RuleSource LAVA = makeStateRule(Blocks.LAVA);
    private static final SurfaceRules.RuleSource MAGMA = makeStateRule(Blocks.MAGMA_BLOCK);
    private static final SurfaceRules.RuleSource OBSIDIAN = makeStateRule(Blocks.OBSIDIAN);
    private static final SurfaceRules.RuleSource TUFF = makeStateRule(Blocks.TUFF);
    private static final SurfaceRules.RuleSource SMOOTH_BASALT = makeStateRule(Blocks.SMOOTH_BASALT);

    // Nether
    private static final SurfaceRules.RuleSource NETHERRACK = makeStateRule(Blocks.NETHERRACK);
    private static final SurfaceRules.RuleSource BASALT = makeStateRule(Blocks.BASALT);
    private static final SurfaceRules.RuleSource BLACKSTONE = makeStateRule(Blocks.BLACKSTONE);


    private static SurfaceRules.RuleSource makeStateRule(Block p_194811_) {
        return SurfaceRules.state(p_194811_.defaultBlockState());
    }

    public static SurfaceRules.RuleSource overworld()
    {
        SurfaceRules.RuleSource surfaceRules = SurfaceRules.sequence(
                makeBOPOverworldRules());

        ImmutableList.Builder<SurfaceRules.RuleSource> builder = ImmutableList.builder();

        SurfaceRules.RuleSource surfacerules$rulesource9 = SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), surfaceRules);
        builder.add(surfacerules$rulesource9);
        return SurfaceRules.sequence(builder.build().toArray((p_198379_) -> {
            return new SurfaceRules.RuleSource[p_198379_];
        }));
    }


    private static SurfaceRules.RuleSource makeBOPOverworldRules()
    {
        // Conditions
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);
        SurfaceRules.ConditionSource sixBelowWater = SurfaceRules.waterStartCheck(-6, -1);
        SurfaceRules.ConditionSource isAbove62 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(62), 0);
        SurfaceRules.ConditionSource isAbove63 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(63), 0);

        SurfaceRules.RuleSource powderedSnowSurface = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, POWDER_SNOW),
                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, POWDER_SNOW)
        );

        SurfaceRules.RuleSource snowSurface = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SNOW_BLOCK),
                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SNOW_BLOCK)
        );

        SurfaceRules.RuleSource gravelStoneSurface = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, GRAVEL),
                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, STONE)
        );

        SurfaceRules.RuleSource gravelBeachSurface = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.sequence(SurfaceRules.ifTrue(isAbove63, AIR), SurfaceRules.sequence(SurfaceRules.ifTrue(isAbove62, WATER), GRAVEL))),
                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, GRAVEL)
        );

        SurfaceRules.RuleSource originBeach = SurfaceRules.sequence(
                SurfaceRules.ifTrue(surfaceNoiseAbove(1.5D), GRAVEL), SAND
        );

//        SurfaceRules.RuleSource mixedLushDesertSurface = SurfaceRules.sequence(
//                SurfaceRules.ifTrue(surfaceNoiseAbove(1.9D), ORANGE_SANDSTONE),
//                ORANGE_SAND
//        );

        SurfaceRules.RuleSource mixedColdDesertSurface = SurfaceRules.sequence(
                SurfaceRules.ifTrue(surfaceNoiseAbove(3.4D), powderedSnowSurface),
                SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(2.6D), snowSurface),
                        gravelStoneSurface
                ));

        SurfaceRules.RuleSource volcanoSurface = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, BASALT),
                SMOOTH_BASALT
        );

        // Sandstone linings
        SurfaceRules.RuleSource sandstoneLinedSand = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, SANDSTONE), SAND);
//        SurfaceRules.RuleSource lushDesertSandstoneLinedOrangeSand = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, ORANGE_SANDSTONE), mixedLushDesertSurface);
//        SurfaceRules.RuleSource blackSandstoneLining = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, BLACK_SANDSTONE), BLACK_SAND);
        SurfaceRules.RuleSource coldDesertStoneLinedGravelSnow = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, STONE), mixedColdDesertSurface);
// Define Variables for Conditions
//        SurfaceRules.RuleSource  cragBiomeRule = SurfaceRules.ifTrue(SurfaceRules.isBiome(BOPBiomes.CRAG),
//                SurfaceRules.ifTrue(surfaceNoiseAbove(1.9D), STONE));

//        SurfaceRules.RuleSource  rockyRainforestBiomeRule = SurfaceRules.ifTrue(SurfaceRules.isBiome(BOPBiomes.ROCKY_RAINFOREST),
//                SurfaceRules.sequence(
//                        SurfaceRules.ifTrue(surfaceNoiseAbove(4.0D), LIGHT_BLUE_TERRACOTTA),
//                        SurfaceRules.sequence(
//                                SurfaceRules.ifTrue(surfaceNoiseAbove(3.0D), CYAN_TERRACOTTA),
//                                SurfaceRules.sequence(
//                                        SurfaceRules.ifTrue(surfaceNoiseAbove(2.0D), LIGHT_GRAY_TERRACOTTA),
//                                        SurfaceRules.ifTrue(surfaceNoiseAbove(1.0D), TERRACOTTA)
//                                )
//                        )
//                )
//        );

//        SurfaceRules.RuleSource  dryLandRule = SurfaceRules.ifTrue(SurfaceRules.isBiome(BOPBiomes.DRYLAND),
//                SurfaceRules.ifTrue(surfaceNoiseAbove(1.75D), sandstoneLinedSand));

//        SurfaceRules.RuleSource  duneBeachRule = SurfaceRules.ifTrue(SurfaceRules.isBiome(BOPBiomes.DUNE_BEACH), sandstoneLinedSand);
//        SurfaceRules.RuleSource  coldDesertRule = SurfaceRules.ifTrue(SurfaceRules.isBiome(BOPBiomes.COLD_DESERT), coldDesertStoneLinedGravelSnow);
//        SurfaceRules.RuleSource  lushDesertRule = SurfaceRules.ifTrue(SurfaceRules.isBiome(BOPBiomes.LUSH_DESERT), lushDesertSandstoneLinedOrangeSand);
//        SurfaceRules.RuleSource  volcanicPlainsRule = SurfaceRules.ifTrue(SurfaceRules.isBiome(BOPBiomes.VOLCANIC_PLAINS), blackSandstoneLining);

// Define Water-related Conditions
//        SurfaceRules.RuleSource  swampWaterRule = SurfaceRules.ifTrue(SurfaceRules.isBiome(BOPBiomes.MARSH, BOPBiomes.FLOODPLAIN),
//                SurfaceRules.ifTrue(isAbove62,
//                        SurfaceRules.ifTrue(SurfaceRules.not(isAbove63),
//                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SWAMP, 0.0D), WATER)
//                        )
//                )
//        );

//        SurfaceRules.RuleSource  lushSavannaRule = SurfaceRules.ifTrue(SurfaceRules.isBiome(BOPBiomes.LUSH_SAVANNA),
//                SurfaceRules.ifTrue(surfaceNoiseAbove(2.0D), ORANGE_SAND));
//
//        SurfaceRules.RuleSource  overgrownGreensRule = SurfaceRules.ifTrue(SurfaceRules.isBiome(BOPBiomes.OVERGROWN_GREENS),
//                SurfaceRules.ifTrue(surfaceNoiseAbove(1.7D), COARSE_DIRT));
//
//        SurfaceRules.RuleSource  hotSpringsRule = SurfaceRules.ifTrue(SurfaceRules.isBiome(BOPBiomes.HOT_SPRINGS),
//                SurfaceRules.ifTrue(surfaceNoiseAbove(2.0D), COARSE_DIRT));
//
//        SurfaceRules.RuleSource  oldGrowthWoodlandRule = SurfaceRules.ifTrue(SurfaceRules.isBiome(BOPBiomes.OLD_GROWTH_WOODLAND),
//                SurfaceRules.ifTrue(surfaceNoiseAbove(2.6D), COARSE_DIRT));
//
//                SurfaceRules.ifTrue(surfaceNoiseAbove(2.3D), PODZOL));

//        SurfaceRules.RuleSource mediterraneanForestRule = SurfaceRules.ifTrue(SurfaceRules.isBiome(BOPBiomes.MEDITERRANEAN_FOREST),
//                SurfaceRules.ifTrue(surfaceNoiseAbove(1.9D), PODZOL));

// Define Additional Surface Conditions
//        SurfaceRules.RuleSource gravelBeachSurfaceRule = SurfaceRules.ifTrue(SurfaceRules.isBiome(BOPBiomes.GRAVEL_BEACH), gravelBeachSurface);

//        SurfaceRules.RuleSource originValleySurfaceRule = SurfaceRules.ifTrue(SurfaceRules.isBiome(BOPBiomes.ORIGIN_VALLEY, BOPBiomes.WINTRY_ORIGIN_VALLEY),
//                SurfaceRules.sequence(
//                        SurfaceRules.ifTrue(SurfaceRules.yStartCheck(VerticalAnchor.absolute(66), 0), SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, true, 0, CaveSurface.FLOOR), DIRT)),
//                        SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, true, 0, CaveSurface.FLOOR), originBeach)
//                )
//        );

// Combine All Surface Conditions into a Sequence
        return SurfaceRules.sequence(
//                cragBiomeRule,
//                rockyRainforestBiomeRule,
//                SurfaceRules.ifTrue(
//                        sixBelowWater,
//                        SurfaceRules.sequence(
//                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR,
//                                        SurfaceRules.sequence(
//                                                dryLandRule,
//                                                duneBeachRule,
//                                                coldDesertRule,
//                                                lushDesertRule,
//                                                volcanicPlainsRule
//                                        )
//                                ),
//                                SurfaceRules.ifTrue(SurfaceRules.DEEP_UNDER_FLOOR,
//                                        SurfaceRules.sequence(
//                                                dryLandRule,
//                                                coldDesertRule,
//                                                lushDesertRule,
//                                                volcanicPlainsRule
//                                        )
//                                )
//                        )
//                ),
//                SurfaceRules.ifTrue(
//                        SurfaceRules.ON_FLOOR,
//                        SurfaceRules.sequence(
//                                swampWaterRule,
//                                SurfaceRules.ifTrue(
//                                        isAtOrAboveWaterLevel,
//                                        SurfaceRules.sequence(
//                                                lushSavannaRule,
//                                                overgrownGreensRule,
//                                                hotSpringsRule,
//                                                oldGrowthWoodlandRule,
////                                                oldGrowthDeadForestRule,
//                                                mediterraneanForestRule,
//                                                SurfaceRules.ifTrue(SurfaceRules.isBiome(BOPBiomes.REDWOOD_FOREST), PODZOL)
//                                        )
//                                ),
//                                SurfaceRules.ifTrue(
//                                        SurfaceRules.not(isAtOrAboveWaterLevel),
//                                        SurfaceRules.sequence(
//                                                SurfaceRules.ifTrue(SurfaceRules.isBiome(BOPBiomes.CRAG, BOPBiomes.ROCKY_RAINFOREST), GRAVEL),
//                                                SurfaceRules.ifTrue(SurfaceRules.isBiome(BOPBiomes.VOLCANO),
//                                                        SurfaceRules.ifTrue(surfaceNoiseAbove(2.7D), SMOOTH_BASALT)
//                                                )
//                                        )
//                                )
//                        )
//                ),
//                SurfaceRules.ifTrue(SurfaceRules.isBiome(BOPBiomes.COLD_DESERT),
//                        SurfaceRules.sequence(
//                                SurfaceRules.ifTrue(surfaceNoiseAbove(3.4D), powderedSnowSurface),
//                                SurfaceRules.sequence(
//                                        SurfaceRules.ifTrue(surfaceNoiseAbove(2.6D), snowSurface),
//                                        gravelStoneSurface
//                                )
//                        )
//                ),
//                SurfaceRules.ifTrue(SurfaceRules.isBiome(BOPBiomes.CRAG), STONE),
//                SurfaceRules.ifTrue(
//                        SurfaceRules.isBiome(BOPBiomes.VOLCANO),
//                        SurfaceRules.sequence(
//                                SurfaceRules.ifTrue(surfaceNoiseAbove(3.7D), MAGMA),
//                                SurfaceRules.sequence(
//                                        SurfaceRules.ifTrue(surfaceNoiseAbove(2.6D), BLACK_SANDSTONE),
//                                        SurfaceRules.sequence(
//                                                SurfaceRules.ifTrue(surfaceNoiseAbove(1.5D), SMOOTH_BASALT),
//                                                volcanoSurface
//                                        )
//                                )
//                        )
//                ),
//                gravelBeachSurfaceRule,
//                originValleySurfaceRule,
//                SurfaceRules.ifTrue(SurfaceRules.isBiome(BOPBiomes.ROCKY_SHRUBLAND),
//                        SurfaceRules.ifTrue(surfaceNoiseAbove(1.4D), STONE)
//                ),
//                SurfaceRules.ifTrue(SurfaceRules.isBiome(BOPBiomes.WASTELAND), DRIED_SALT)
        );

    }


    private static SurfaceRules.ConditionSource surfaceNoiseAbove(double p_194809_) {
        return SurfaceRules.noiseCondition(Noises.SURFACE, p_194809_ / 8.25D, Double.MAX_VALUE);
    }

}
