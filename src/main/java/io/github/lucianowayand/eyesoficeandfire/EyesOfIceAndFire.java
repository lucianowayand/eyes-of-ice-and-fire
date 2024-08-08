package io.github.lucianowayand.eyesoficeandfire;

import io.github.lucianowayand.eyesoficeandfire.registry.EyesOfIceAndFireItemsInit;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(EyesOfIceAndFire.MOD_ID)
public class EyesOfIceAndFire {
    public static final String MOD_ID = "eyesoficeandfire";

    public EyesOfIceAndFire() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        EyesOfIceAndFireItemsInit.register(bus);
        MinecraftForge.EVENT_BUS.register(this);
    }
}