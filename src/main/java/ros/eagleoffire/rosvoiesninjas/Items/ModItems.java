package ros.eagleoffire.rosvoiesninjas.Items;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ros.eagleoffire.rosvoiesninjas.ROSVoiesNinjas;
import ros.eagleoffire.rosvoiesninjas.entity.custom.ModEntities;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ROSVoiesNinjas.MODID);

    public static final RegistryObject<Item> SCEAU_VIERGE = ITEMS.register("sceau_vierge",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SCEAU_EXPLOSIF_ITEM = ITEMS.register("sceau_explosif_item",
        () -> new SceauExplosifItem(new Item.Properties()));

    public static final RegistryObject<Item> SCEAU_SCELLEMENT = ITEMS.register("sceau_scellement",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SCEAU_CHAKRA = ITEMS.register("sceau_chakra",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SCEAU_CHAKRA_AUTRUI = ITEMS.register("sceau_chakra_autrui",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
