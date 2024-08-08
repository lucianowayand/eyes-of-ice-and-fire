package io.github.lucianowayand.eyesoficeandfire.registry;

import com.github.alexthe666.iceandfire.entity.DragonType;
import io.github.lucianowayand.eyesoficeandfire.EnderEyes;
import io.github.lucianowayand.eyesoficeandfire.EyesOfIceAndFire;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EyesOfIceAndFireItemsInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EyesOfIceAndFire.MOD_ID);

    public static final RegistryObject<Item> EYE_OF_FIRE_DRAGON = ITEMS.register("eye_of_fire_dragon",
            () -> new EnderEyes(new Item.Properties().stacksTo(16).tab(EyesOfIceAndFireCreativeTabInit.EYES_OF_ICE_AND_FIRE_TAB), DragonType.FIRE));

    public static final RegistryObject<Item> EYE_OF_ICE_DRAGON = ITEMS.register("eye_of_ice_dragon",
            () -> new EnderEyes(new Item.Properties().stacksTo(16).tab(EyesOfIceAndFireCreativeTabInit.EYES_OF_ICE_AND_FIRE_TAB), DragonType.ICE));

    public static final RegistryObject<Item> EYE_OF_LIGHTNING_DRAGON = ITEMS.register("eye_of_lightning_dragon",
            () -> new EnderEyes(new Item.Properties().stacksTo(16).tab(EyesOfIceAndFireCreativeTabInit.EYES_OF_ICE_AND_FIRE_TAB), DragonType.LIGHTNING));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
