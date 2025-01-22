package net.kaupenjoe.tutorialmod.config;

import glitchcore.config.Config;
import glitchcore.util.Environment;
import net.kaupenjoe.tutorialmod.TutorialMod;

public class GameplayConfig extends Config
{
    public boolean wanderingTraderTrades;

    public GameplayConfig()
    {
        super(Environment.getConfigPath().resolve(TutorialMod.MOD_ID + "/gameplay.toml"));
    }

    @Override
    public void load()
    {
        wanderingTraderTrades = add("general.wandering_trader_trades", true, "Add various BOP resources to the Wandering Trader trade pool.");
    }
}