package net.kaupenjoe.tutorialmod.api.block;


import net.kaupenjoe.tutorialmod.api.BOPAPI;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.WoodType;

import static net.minecraft.world.level.block.state.properties.WoodType.register;
public class BOPWoodTypes
{
    public static final WoodType REDWOOD = register(new WoodType(BOPAPI.MOD_ID + ":redwood", BOPBlockSetTypes.REDWOOD));
    public static final WoodType WILLOW = register(new WoodType(BOPAPI.MOD_ID + ":willow", BOPBlockSetTypes.WILLOW));
    public static final WoodType DEAD = register(new WoodType(BOPAPI.MOD_ID + ":dead", BOPBlockSetTypes.DEAD));
}