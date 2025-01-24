package net.kaupenjoe.tutorialmod.init;
import com.google.common.collect.Maps;
import glitchcore.event.EventManager;
import glitchcore.event.client.RegisterColorsEvent;
import glitchcore.event.client.RegisterParticleSpritesEvent;
import glitchcore.util.RenderHelper;
import glitchcore.util.SheetHelper;
import net.kaupenjoe.tutorialmod.api.block.BOPBlocks;
import net.kaupenjoe.tutorialmod.api.block.BOPWoodTypes;
import net.kaupenjoe.tutorialmod.config.GameplayConfig;
import net.kaupenjoe.tutorialmod.config.GenerationConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.state.BlockState;

import java.awt.*;
import java.util.Map;
import java.util.TreeMap;

import static net.kaupenjoe.tutorialmod.api.block.BOPBlocks.*;


public class ModClient {

    private static final String BIOME_CONFIG_FILE_NAME = "biome_toggles.json";
    private static Map<String, Boolean> biomeToggles;
    private static final TreeMap<String, Boolean> defaultBiomeToggles = Maps.newTreeMap();

    public static GenerationConfig generation = new GenerationConfig();
    public static GameplayConfig gameplay = new GameplayConfig();


    public static void setup()
    {
        setupRenderTypes();
        registerRenderers();
        registerWoodTypes();
    }

    public static void addClientHandlers()
    {
        // Coloring
        EventManager.addListener(ModClient::registerBlockColors);
        EventManager.addListener(ModClient::registerItemColors);

//         Particles
//        EventManager.addListener(ModClient::registerParticleSprites);
    }

    public static void setupRenderTypes()
    {
        RenderType transparentRenderType = RenderType.cutoutMipped();
        RenderType cutoutRenderType = RenderType.cutout();
        RenderType translucentRenderType = RenderType.translucent();

        // Somehow this works. I don't know how, since this only sets the render type with UnsupportedOperationException(), which causes the black(alpha) to turn transparent. ???
        RenderHelper.setRenderType(REDWOOD_LEAVES, transparentRenderType);
        RenderHelper.setRenderType(REDWOOD_SAPLING, cutoutRenderType);
        RenderHelper.setRenderType(WILLOW_VINE, cutoutRenderType);
        RenderHelper.setRenderType(SPANISH_MOSS, cutoutRenderType);
        RenderHelper.setRenderType(SPANISH_MOSS_PLANT, cutoutRenderType);
        RenderHelper.setRenderType(DEAD_BRANCH, cutoutRenderType);
    }

    public static void registerRenderers()
    {
        // Register boat layer definitions
        LayerDefinition boatLayerDefinition = BoatModel.createBodyModel();
        LayerDefinition chestBoatLayerDefinition = ChestBoatModel.createBodyModel();

        // Register block entity renderers
//        RenderHelper.registerBlockEntityRenderer((BlockEntityType<SignBlockEntityBOP>) BOPBlockEntities.SIGN, SignRenderer::new);
//        RenderHelper.registerBlockEntityRenderer((BlockEntityType<HangingSignBlockEntityBOP>)BOPBlockEntities.HANGING_SIGN, HangingSignRenderer::new);
//        RenderHelper.registerBlockEntityRenderer((BlockEntityType<AnomalyBlockEntity>)BOPBlockEntities.ANOMALY, AnomalyRenderer::new);
//
//        // Register entity renderers
//        RenderHelper.registerEntityRenderer((EntityType<BoatBOP>) BOPEntities.BOAT, context -> new BoatRendererBOP(context, false));
//        RenderHelper.registerEntityRenderer((EntityType<ChestBoatBOP>) BOPEntities.CHEST_BOAT, context -> new BoatRendererBOP(context, true));
    }

    public static void registerItemColors(RegisterColorsEvent.Item event)
    {
        //todo: bring back when creative tab mystery is solved.

        event.register((stack, tintIndex) -> {
                    BlockState state = ((BlockItem)stack.getItem()).getBlock().defaultBlockState();
                    BlockColors blockColors = Minecraft.getInstance().getBlockColors();
                    return blockColors.getColor(state, null, null, tintIndex);
                },
                BOPBlocks.WILLOW_VINE);
    }

    public static void registerBlockColors(RegisterColorsEvent.Block event)
    {

        //todo: bring back when creative tab mystery is solved.
//        //Grass Coloring
//        event.register((state, world, pos, tintIndex) ->
//                        world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0.5D, 1.0D));
//
        //Foliage Coloring
        event.register((state, world, pos, tintIndex) ->
                        world != null && pos != null ? BiomeColors.getAverageFoliageColor(world, pos) : FoliageColor.getDefaultColor(),
                 BOPBlocks.WILLOW_VINE);
//
//        //Rainbow Birch Leaf Coloring
//        event.register((state, world, pos, tintIndex) ->
//                        world != null && pos != null ? getRainbowBirchColor(world, pos) : FoliageColor.getDefaultColor()
//                );
//
//        //Flowerbed Coloring
//        event.register((state, world, pos, tintIndex) -> {
//                    if (tintIndex != 0) { return world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.getDefaultColor(); }
//                    else { return -1; }});
//
//        //Lily Pad Coloring
//        event.register((state, world, pos, tintIndex) -> {
//                    return world != null && pos != null ? 2129968 : 7455580; });
    }


    public static void registerWoodTypes()
    {
//        SheetHelper.addWoodType(BOPWoodTypes.FIR);
//        SheetHelper.addWoodType(BOPWoodTypes.PINE);
//        SheetHelper.addWoodType(BOPWoodTypes.MAPLE);
        SheetHelper.addWoodType(BOPWoodTypes.REDWOOD);
//        SheetHelper.addWoodType(BOPWoodTypes.MAHOGANY);
//        SheetHelper.addWoodType(BOPWoodTypes.JACARANDA);
//        SheetHelper.addWoodType(BOPWoodTypes.PALM);
        SheetHelper.addWoodType(BOPWoodTypes.WILLOW);
//        SheetHelper.addWoodType(BOPWoodTypes.DEAD);
//        SheetHelper.addWoodType(BOPWoodTypes.MAGIC);
//        SheetHelper.addWoodType(BOPWoodTypes.UMBRAN);
//        SheetHelper.addWoodType(BOPWoodTypes.HELLBARK);
    }

    public static int getRainbowBirchColor(BlockAndTintGetter world, BlockPos pos)
    {
        float saturation;
        if (world.getBlockState(pos.above()).is(BlockTags.SNOW))
        {
            saturation = 0.3F;
        }
        else if (world.getBlockState(pos.above(2)).is(BlockTags.SNOW))
        {
            saturation = 0.45F;
        }
        else
        {
            saturation = 0.6F;
        }

        Color foliage = Color.getHSBColor(((float)pos.getX() + (float)pos.getY() + (float)pos.getZ() + (Mth.sin(((float)pos.getX() + (float)pos.getY() + (float)pos.getZ()) / 16) * 16) % 100) / 100, saturation, 1.0F);

        return foliage.getRGB();
    }
}