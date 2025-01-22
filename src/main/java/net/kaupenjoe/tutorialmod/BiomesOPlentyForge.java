package net.kaupenjoe.tutorialmod;

import glitchcore.forge.GlitchCoreForge;
import net.kaupenjoe.tutorialmod.init.ModClient;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(value = TutorialMod.MOD_ID)
public class BiomesOPlentyForge
{
    public static final DeferredRegister<FluidType> FORGE_FLUID_REGISTER = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, TutorialMod.MOD_ID);

    @SuppressWarnings("all")
    public BiomesOPlentyForge()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);

        FORGE_FLUID_REGISTER.register(bus);

        TutorialMod.init();
        GlitchCoreForge.prepareModEventHandlers(bus);

//        ModFluidTypes.setup();
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() ->
        {
            TutorialMod.setupTerraBlender();
//            ModFluidTypes.registerFluidInteractions();
        });
    }

    private void clientSetup(final FMLClientSetupEvent event)
    {
        event.enqueueWork(ModClient::setup);
    }
}