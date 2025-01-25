package net.kaupenjoe.tutorialmod.worldgen.feature.tree;

import com.mojang.serialization.Codec;
import net.kaupenjoe.tutorialmod.util.biome.GeneratorUtil;
import net.kaupenjoe.tutorialmod.worldgen.feature.configurations.TaigaTreeConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;

import java.util.function.BiConsumer;

public class RedwoodTreeFeature extends BOPTreeFeature<TaigaTreeConfiguration> {
    public RedwoodTreeFeature(Codec<TaigaTreeConfiguration> codec) {
        super(codec);
    }


    public boolean checkSpace(LevelAccessor world, BlockPos pos, int baseHeight, int height, TaigaTreeConfiguration config) {

        //EXPERIMENT: This changes the thickness of the trunks.
        double trunkWidthScalingFactor = 0.5;

        for (int y = 0; y <= height; y++) {

            int trunkWidth = (int) (((config.trunkWidth * (height - y) / height) + 1) * trunkWidthScalingFactor);
            int trunkStart = Mth.ceil(0.25D - trunkWidth / 2.0D);
            int trunkEnd = Mth.floor(0.25D + trunkWidth / 2.0D);

            //EXPERIMENT: This changes the spacing between the trees.
            int spacingMultiplier = 20;     //EXPERIMENTAL
            int start = (y <= baseHeight ? trunkStart : trunkStart - 1) * spacingMultiplier;  //EXPERIMENTAL
            int end = (y <= baseHeight ? trunkEnd : trunkEnd + 1) * spacingMultiplier;  //EXPERIMENTAL

            for (int x = start; x <= end; x++) {
                for (int z = start; z <= end; z++) {
                    BlockPos pos1 = pos.offset(x, y, z);
                    if (pos1.getY() >= 255 || !this.canReplace(world, pos1)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // generates a layer of leafs
    public void generateLeafLayer(LevelAccessor world, RandomSource rand, BlockPos pos, int leavesRadius, int trunkStart, int trunkEnd, FoliagePlacer.FoliageSetter leaves, TaigaTreeConfiguration config) {

        int modifiedLeavesRadius = (int) (leavesRadius * 0.7);

        int start = trunkStart - modifiedLeavesRadius;
        int end = trunkEnd + modifiedLeavesRadius;

        for (int x = start; x <= end; x++) {
            for (int z = start; z <= end; z++) {
                // skip corners
                if ((modifiedLeavesRadius > 0) && (x == start || x == end) && (z == start || z == end)) {
                    continue;
                }
                int distFromTrunk = (x < 0 ? trunkStart - x : x - trunkEnd) + (z < 0 ? trunkStart - z : z - trunkEnd);

                // set leaves as long as it's not too far from the trunk to survive
                if (distFromTrunk < 4 || (distFromTrunk == 4 && rand.nextInt(2) == 0)) {
                    this.placeLeaves(world, pos.offset(x, 0, z), leaves, config);
                }
            }
        }
    }

    public void generateBranch(LevelAccessor world, RandomSource rand, BlockPos pos, Direction direction, int length, BiConsumer<BlockPos, BlockState> logs, FoliagePlacer.FoliageSetter leaves, TaigaTreeConfiguration config) {


        Direction.Axis axis = direction.getAxis();
        Direction sideways = direction.getClockWise();

        //EXPERIMENT: Reduce the radius of the branch.
        // Modifier variables for the radius reduction
        float initialRadius = 0.5f;  // Starting radius of the branch at the base
        float reductionFactor = 0.3f; // Factor by which the radius decreases each step (less than 1 reduces radius)
        float minRadius = 0.4f; // Minimum radius the branch can reach


        //EXPERIMENT: Make the appearance of the leaves weirder.
        int randomLeafCount = 0;

        for (int i = 1; i <= length; i++) {
            BlockPos pos1 = pos.relative(direction, i);

            // Calculate the radius at this step with a smooth reduction
            float currentRadius = Math.max(minRadius, initialRadius * (float)Math.pow(reductionFactor, i));
            int r = Math.round(currentRadius); // Round to nearest integer for block placement

            // Place leaves based on the current radius
            for (int j = -r; j <= r; j++) {
                if (i < length || rand.nextInt(2) == 0) {
                    this.placeLeaves(world, pos1.relative(sideways, j), leaves, config);
                }
            }

            // Place additional leaves and logs as usual
            if (length - i > 2) {
                this.placeLeaves(world, pos1.above(), leaves, config);
                this.placeLeaves(world, pos1.above().relative(sideways, -1), leaves, config);
                this.placeLeaves(world, pos1.above().relative(sideways, 1), leaves, config);
                this.placeLog(world, pos1, axis, logs, config);
            }

            // Now, place random leaves
            // Ensure at least two random leaf placements during the branch generation
            int randomArea = 2;
            if (randomLeafCount < 2) {
                // Try placing random leaves at varying distances in XYZ directions
                for (int attempt = 0; attempt < 5; attempt++) {  // Limit to 5 attempts to avoid excessive loops
                    int randX = rand.nextInt(2 * randomArea + 1) - randomArea; // Random number between -6 and 6 for X
                    int randY = rand.nextInt(2 * randomArea + 1) - randomArea; // Random number between -6 and 6 for Y
                    int randZ = rand.nextInt(2 * randomArea + 1) - randomArea; // Random number between -6 and 6 for Z

                    // Create a random position offset by the above values
                    BlockPos randomPos = pos1.offset(randX, randY, randZ);

                    // Ensure we are placing leaves at a valid position (e.g., air block)
                    if (world.getBlockState(randomPos).isAir()) {
                        this.placeLeaves(world, randomPos, leaves, config);
                        randomLeafCount++;  // Increment the count of random leaves placed
                        break;  // Once a valid leaf spot is found, stop trying
                    }
                }
            }
        }
    }






    @Override
    protected boolean doPlace(WorldGenLevel world, RandomSource random, BlockPos startPos, BiConsumer<BlockPos, BlockState> roots, BiConsumer<BlockPos, BlockState> logs, FoliagePlacer.FoliageSetter leaves, TreeConfiguration configBase) {
        TaigaTreeConfiguration config = (TaigaTreeConfiguration) configBase;

        // Move down until we reach the ground
        while (startPos.getY() >= world.getMinBuildHeight() + 1 && world.isEmptyBlock(startPos) || world.getBlockState(startPos).is(BlockTags.LEAVES)) {
            startPos = startPos.below();
        }


        //EXPERIMENT: Shorten heights
        double heightShortenModifier = 0.7;
        int height = GeneratorUtil.nextIntBetween(random, (int)(config.minHeight - heightShortenModifier), (int)(config.maxHeight- heightShortenModifier));
        int baseHeight = GeneratorUtil.nextIntBetween(random, 2, 3);
        int leavesHeight = height - baseHeight;
        if (leavesHeight < 3) {
            return false;
        }

        if (!this.checkSpace(world, startPos.above(), baseHeight, height, config)) {
            // Abandon if there isn't enough room
            return false;
        }

        // Start at the top of the tree
        BlockPos pos = startPos.above(height);

        // Leaves at the top
        this.placeLeaves(world, pos, leaves, config);
        pos.below();

        // Add layers of leaves
        for (int i = 0; i < leavesHeight; i++) {
            int trunkWidth = (config.trunkWidth * i / height) + 1;
            int trunkStart = Mth.ceil(0.25D - trunkWidth / 2.0D);
            int trunkEnd = Mth.floor(0.25D + trunkWidth / 2.0D);


            //EXPERIMENT: Reduce the leaf width.
            int radius = Math.min(Math.min((i + 2) / 4, 2 + (leavesHeight - i)), 4);
            int modifiedRadius = radius / 2;

            if (radius == 0) {
                this.placeLeaves(world, pos, leaves, config);
            } else if (radius < 2) {
                this.generateLeafLayer(world, random, pos, radius, trunkStart, trunkEnd, leaves, config);
            } else {
                if (i % 5 == 0) {
                    this.generateBranch(world, random, pos.offset(trunkStart, 0, trunkStart), Direction.NORTH, radius / 2, logs, leaves, config);
                    this.generateBranch(world, random, pos.offset(trunkEnd, 0, trunkStart), Direction.EAST, radius / 2, logs, leaves, config);
                    this.generateBranch(world, random, pos.offset(trunkEnd, 0, trunkEnd), Direction.SOUTH, radius / 2, logs, leaves, config);
                    this.generateBranch(world, random, pos.offset(trunkStart, 0, trunkEnd), Direction.WEST, radius / 2, logs, leaves, config);
                } else {
                    this.generateBranch(world, random, pos.offset(trunkStart, 0, trunkStart), Direction.NORTH, radius, logs, leaves, config);
                    this.generateBranch(world, random, pos.offset(trunkEnd, 0, trunkStart), Direction.EAST, radius, logs, leaves, config);
                    this.generateBranch(world, random, pos.offset(trunkEnd, 0, trunkEnd), Direction.SOUTH, radius, logs, leaves, config);
                    this.generateBranch(world, random, pos.offset(trunkStart, 0, trunkEnd), Direction.WEST, radius, logs, leaves, config);
                }
            }
            pos = pos.below();
        }


        // Create the trunk widths scales
        //EXPERIMENT: Thinner Tree Trunks.
        double[] scalingFactors = new double[]{
                (0.3 + random.nextDouble() * 0.1), // Narrower scaling for a thinner trunk
                (0.05 + random.nextDouble() * 0.1),
                (random.nextDouble() * 0.05)
        };

        if (config.trunkWidth == 3) {
            scalingFactors = new double[]{
                    (0.35 + random.nextDouble() * 0.1), // Narrower
                    (0.05 + random.nextDouble() * 0.1),
                    (0.02 + random.nextDouble() * 0.04)
            };
        }

        // Generate the trunk
        for (int x = -config.trunkWidth; x <= config.trunkWidth; x++) {
            for (int z = -config.trunkWidth; z <= config.trunkWidth; z++) {
                int dist = Math.abs(x) + Math.abs(z);

                int heightHere = height - 2;

                // If we're not the center of the trunk on a single trunk width, give up
                if (config.trunkWidth == 1 && dist > 0) {
                    continue;
                }

                // Scale bigger widths
                if (dist == 1) {
                    heightHere = (int) (height * scalingFactors[0]);
                } else if (dist == 2) {
                    heightHere = (int) (height * scalingFactors[1]);
                } else if (dist == 3) {
                    heightHere = (int) (height * scalingFactors[2]);
                } else if (dist > 3) {
                    continue;
                }

                heightHere += random.nextInt(2);

                boolean didPlace = false;
                for (int y = 0; y < heightHere; y++) {
                    BlockPos local = startPos.offset(x, y, z);
                    didPlace |= this.placeLog(world, local, logs, config);

                    if (dist > 0 && y > 6 && y < (baseHeight - 2) && random.nextInt(15) == 0) {
                        double theta;
                        if (x == 0 && z == 0) {
                            // Prevents bushes originating from the center from generating too low
                            if (y < 10) {
                                continue;
                            }

                            theta = Math.PI * random.nextDouble() * 2;
                        } else {
                            // Make sure the branches only go in the same direction of the current trunk position from the center
                            double angleFromCenter = Math.atan2(x, z);

                            theta = angleFromCenter + (Math.PI * (random.nextDouble() * 0.5 - 0.25));
                        }

                        int branchLength = (3 - dist) + 1 + random.nextInt(2);

                        BlockPos branchPos = null;
                        for (int i = 0; i < branchLength; i++) {
                            branchPos = local.offset(Mth.floor(Math.cos(theta) * i), i / 2, Mth.floor(Math.sin(theta) * i));

                            this.placeLog(world, branchPos, logs, config);
                        }

                        this.generateBush(logs, leaves, world, random, branchPos, config);
                    }
                }

                if (didPlace) {
                    // Place dirt 3 blocks below the trunk if no solid block is found
                    for (int y = 1; y < 4; y++) {
                        BlockPos local = startPos.offset(x, -y, z);
                        BlockState state = world.getBlockState(local);
                        if (!state.isSolid() || isDirt(state)) {
                            world.setBlock(local, Blocks.DIRT.defaultBlockState(), 3);
                        }
                    }
                }
            }
        }

        return true;
    }

    protected boolean generateBush(BiConsumer<BlockPos, BlockState> logs, FoliagePlacer.FoliageSetter leaves, LevelAccessor world, RandomSource random, BlockPos pos, TaigaTreeConfiguration config) {
        int height = 2;

        //Generate a bush 3 blocks tall, with the center block set to a log
        for (int y = 0; y < height; ++y) {
            // log in the center
            if (height - y > 1) {
                this.placeLog(world, pos.offset(0, y, 0), logs, config);
            }

            //Reduces the radius closer to the top of the bush
            int leavesRadius = (height - y > 1 ? 2 : 1);
            int modifiedLeavesRadius = (int) (leavesRadius * 0.7);

            for (int x = -modifiedLeavesRadius; x <= modifiedLeavesRadius; ++x) {
                for (int z = -modifiedLeavesRadius; z <= modifiedLeavesRadius; ++z) {
                    //Randomly prevent the generation of leaves on the corners of each layer
                    if (Math.abs(x) < modifiedLeavesRadius || Math.abs(z) < modifiedLeavesRadius) {
                        if (config.altFoliageProvider.getState(random, pos) != Blocks.AIR.defaultBlockState()) {
                            if (random.nextInt(4) == 0) {
                                this.placeAltLeaves(world, pos.offset(x, y, z), leaves, config);
                            } else {
                                this.placeLeaves(world, pos.offset(x, y, z), leaves, config);
                            }
                        } else {
                            this.placeLeaves(world, pos.offset(x, y, z), leaves, config);
                        }
                    }
                }
            }
        }

        return true;
    }
}