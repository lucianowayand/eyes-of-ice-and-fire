package io.github.lucianowayand.eyesoficeandfire;

import io.github.lucianowayand.eyesoficeandfire.registry.EyesOfIceAndFireCreativeTabInit;
import io.github.lucianowayand.eyesoficeandfire.registry.EyesOfIceAndFireItemsInit;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(EyesOfIceAndFire.MODID)
public class EyesOfIceAndFire {
    public static final String MODID = "eyesoficeandfire";

    public EyesOfIceAndFire() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        EyesOfIceAndFireItemsInit.ITEMS.register(bus);
        EyesOfIceAndFireCreativeTabInit.TABS.register(bus);
    }
}