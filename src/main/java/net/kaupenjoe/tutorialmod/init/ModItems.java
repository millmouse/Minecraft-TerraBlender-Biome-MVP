package net.kaupenjoe.tutorialmod.init;
import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.api.block.BOPBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;

import java.util.function.BiConsumer;

import static net.kaupenjoe.tutorialmod.api.item.BOPItems.*;

public class ModItems
{
    public static void setup(BiConsumer<ResourceLocation, Item> func)
    {
        registerItems(func);
        ModVanillaCompat.setup();
    }

    private static void registerItems(BiConsumer<ResourceLocation, Item> func)
    {
        registerBlockItems(func);

        BOP_ICON = register(func, new Item(new Item.Properties()), "bop_icon");
    }

    public static void registerBlockItems(BiConsumer<ResourceLocation, Item> func)
    {
        //todo bring back missing classes when creative tab works again.
        REDWOOD_SAPLING = register(func, "redwood_sapling", new BlockItem(BOPBlocks.REDWOOD_SAPLING, new Item.Properties()));
        REDWOOD_LEAVES = register(func, "redwood_leaves", new BlockItem(BOPBlocks.REDWOOD_LEAVES, new Item.Properties()));
        REDWOOD_LOG = register(func, "redwood_log", new BlockItem(BOPBlocks.REDWOOD_LOG, new Item.Properties()));
        REDWOOD_WOOD = register(func, "redwood_wood", new BlockItem(BOPBlocks.REDWOOD_WOOD, new Item.Properties()));
        WILLOW_VINE = register(func, "willow_vine", new BlockItem(BOPBlocks.WILLOW_VINE, new Item.Properties()));
//        SPANISH_MOSS = register(func, "spanish_moss", new BlockItem(BOPBlocks.SPANISH_MOSS, new Item.Properties()));
//        DEAD_BRANCH = register(func, "dead_branch", new BlockItem(BOPBlocks.DEAD_BRANCH, new Item.Properties()));
    }

    private static Item register(BiConsumer<ResourceLocation, Item> func, Item item, String name)
    {
        return register(func, name, item);
    }

    private static Item register(BiConsumer<ResourceLocation, Item> func, String name, Item item)
    {
        func.accept(new ResourceLocation(TutorialMod.MOD_ID, name), item);
        return item;
    }
}