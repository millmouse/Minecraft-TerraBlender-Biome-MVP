package net.kaupenjoe.tutorialmod.init;

import com.google.common.collect.ImmutableList;
import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.api.item.BOPItems;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.lang.reflect.Field;
import java.util.List;
import java.util.function.BiConsumer;

public class ModCreativeTab
{
    @SuppressWarnings("deprecation")
    public static void registerCreativeTabs(BiConsumer<ResourceLocation, CreativeModeTab> func)
    {
        List<Item> ITEM_BLACKLIST = ImmutableList.of(BOPItems.BOP_ICON);

        var tab = CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                .icon(() -> new ItemStack(BOPItems.BOP_ICON))
                .title(Component.translatable("itemGroup.tutorialmod"))
                .displayItems((displayParameters, output) ->
                {
                    for (Field field : BOPItems.class.getFields())
                    {
                        if (field.getType() != Item.class) continue;

                        try
                        {
                            Item item = (Item) field.get(null);

                            if (item == null)
                                throw new IllegalStateException("Field " + field.getName() + " cannot be null!");

                            if (!ITEM_BLACKLIST.contains(item))
                                output.accept(new ItemStack(item));
                        }
                        catch (IllegalAccessException e)
                        {
                        }
                    }
                }).build();

        register(func, "main", tab);
    }

    private static CreativeModeTab register(BiConsumer<ResourceLocation, CreativeModeTab> func, String name, CreativeModeTab tab)
    {
        func.accept(new ResourceLocation(TutorialMod.MOD_ID, name), tab);
        return tab;
    }
}