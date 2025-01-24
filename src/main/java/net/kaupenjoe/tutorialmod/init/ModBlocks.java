package net.kaupenjoe.tutorialmod.init;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.block.SaplingBlockBOP;
import net.kaupenjoe.tutorialmod.block.SpanishMossBlock;
import net.kaupenjoe.tutorialmod.block.SpanishMossBottomBlock;
import net.kaupenjoe.tutorialmod.block.trees.BOPTreeGrowers;
import net.kaupenjoe.tutorialmod.worldgen.BOPSurfaceRuleData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.PressurePlateBlock.Sensitivity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import terrablender.api.SurfaceRuleManager;

import java.util.function.BiConsumer;

import static net.kaupenjoe.tutorialmod.api.block.BOPBlocks.*;

public class ModBlocks
{
    public static void setup(BiConsumer<ResourceLocation, Block> func)
    {
        registerBlocks(func);
        //TODO: add back!
//        registerSurfaceRules();
    }

    private static void registerSurfaceRules()
    {
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, TutorialMod.MOD_ID, BOPSurfaceRuleData.overworld());
    }

    private static void registerBlocks(BiConsumer<ResourceLocation, Block> func)
    {
        //TODO: finish the missing classes !!
        REDWOOD_SAPLING = register(func, new SaplingBlockBOP(BOPTreeGrowers.REDWOOD, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "redwood_sapling");
        REDWOOD_LEAVES = register(func, new LeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never).ignitedByLava().isRedstoneConductor(ModBlocks::never)), "redwood_leaves");
        REDWOOD_LOG = register(func, log(MapColor.CRIMSON_NYLIUM, MapColor.TERRACOTTA_ORANGE, SoundType.WOOD), "redwood_log");
        REDWOOD_WOOD = register(func, new RotatedPillarBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.TERRACOTTA_ORANGE).strength(2.0F).sound(SoundType.WOOD)), "redwood_wood");
        WILLOW_VINE = register(func, new VineBlock(BlockBehaviour.Properties.of().randomTicks().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).replaceable().ignitedByLava().noCollission().strength(0.2F).sound(SoundType.GRASS)), "willow_vine");
        SPANISH_MOSS = register(func, new SpanishMossBottomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).replaceable().ignitedByLava().randomTicks().noCollission().instabreak().sound(SoundType.GRASS)), "spanish_moss");
        SPANISH_MOSS_PLANT = register(func, new SpanishMossBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).replaceable().ignitedByLava().noCollission().instabreak().sound(SoundType.GRASS)), "spanish_moss_plant");
        //        DEAD_BRANCH = register(func, new DeadBranchBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.COLOR_GRAY).ignitedByLava().noCollission().instabreak().sound(SoundType.WOOD)), "dead_branch");
    }

    private static RotatedPillarBlock log(MapColor MapColor, MapColor MapColor2, SoundType soundType) {
        return new RotatedPillarBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor((blockState) -> blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MapColor : MapColor2).strength(2.0F).sound(soundType));
    }

    private static RotatedPillarBlock logNonIgniting(MapColor MapColor, MapColor MapColor2, SoundType soundType) {
        return new RotatedPillarBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).mapColor((blockState) -> blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MapColor : MapColor2).strength(2.0F).sound(soundType));
    }

    private static Block register(BiConsumer<ResourceLocation, Block> func, Block block, String name)
    {
        func.accept(new ResourceLocation(TutorialMod.MOD_ID, name), block);
        return block;
    }

    private static Item register(BiConsumer<ResourceLocation, Item> func, String name, Item item)
    {
        func.accept(new ResourceLocation(TutorialMod.MOD_ID, name), item);
        return item;
    }

    private static Boolean always(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return (boolean)true;
    }

    private static Boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return (boolean)false;
    }

    private static Boolean ocelotOrParrot(BlockState p_50822_, BlockGetter p_50823_, BlockPos p_50824_, EntityType<?> p_50825_) {
        return (boolean)(p_50825_ == EntityType.OCELOT || p_50825_ == EntityType.PARROT);
    }
}
