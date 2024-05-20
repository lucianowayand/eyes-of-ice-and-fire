package io.github.lucianowayand.eyesoficeandfire.registry;

import io.github.lucianowayand.eyesoficeandfire.EyesOfIceAndFire;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class EyesOfIceAndFireCreativeTabInit {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EyesOfIceAndFire.MODID);

    public static final RegistryObject<CreativeModeTab> EYES_OF_ICE_AND_FIRE_TAB = TABS.register("eyesoficeandfire_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.eyesoficeandfire_tab"))
                    .icon(EyesOfIceAndFireItemsInit.EYE_OF_FIRE_DRAGON.get()::getDefaultInstance)
                    .displayItems((displayParams, output) -> {
                        output.accept(EyesOfIceAndFireItemsInit.EYE_OF_FIRE_DRAGON.get());
                        output.accept(EyesOfIceAndFireItemsInit.EYE_OF_ICE_DRAGON.get());
                        output.accept(EyesOfIceAndFireItemsInit.EYE_OF_LIGHTNING_DRAGON.get());
                    })
                    .build() );
}
