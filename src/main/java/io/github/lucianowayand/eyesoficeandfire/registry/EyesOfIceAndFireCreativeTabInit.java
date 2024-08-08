package io.github.lucianowayand.eyesoficeandfire.registry;

import io.github.lucianowayand.eyesoficeandfire.EyesOfIceAndFire;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EyesOfIceAndFire.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EyesOfIceAndFireCreativeTabInit {
    public static final CreativeModeTab EYES_OF_ICE_AND_FIRE_TAB = new CreativeModeTab("eyesoficeandfire_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(EyesOfIceAndFireItemsInit.EYE_OF_FIRE_DRAGON.get());
        }
    };
}

