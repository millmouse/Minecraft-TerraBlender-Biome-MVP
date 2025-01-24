package net.kaupenjoe.tutorialmod.api.block;


import net.kaupenjoe.tutorialmod.api.BOPAPI;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;

import static net.minecraft.world.level.block.state.properties.BlockSetType.register;

public class BOPBlockSetTypes
{
    public static final BlockSetType REDWOOD = register(new BlockSetType(BOPAPI.MOD_ID + ":redwood"));
    public static final BlockSetType WILLOW = register(new BlockSetType(BOPAPI.MOD_ID + ":willow"));
    public static final BlockSetType DEAD = register(new BlockSetType(BOPAPI.MOD_ID + ":dead"));
}