package ros.eagleoffire.rosvoiesninjas.entity.custom;

import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import ros.eagleoffire.rosvoiesninjas.ROSVoiesNinjas;
import ros.eagleoffire.rosvoiesninjas.entity.decoration.SceauExplosif;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SceauExplosifEntity{
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ROSVoiesNinjas.MODID);
/*    public static final RegistryObject<EntityType<SceauExplosif>> SCEAU_EXPLOSIF = ENTITIES.register("sceau_explosif", () ->
            EntityType.Builder.<SceauExplosif>of(SceauExplosif::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).clientTrackingRange(10).updateInterval(20).build("sceau_explosif"));;*/
    static {
    }

    public static void register(IEventBus eventBus){
        ENTITIES.register(eventBus);
    }
}
