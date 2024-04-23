package ros.eagleoffire.rosvoiesninjas.entity.custom;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import ros.eagleoffire.rosvoiesninjas.ROSVoiesNinjas;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ros.eagleoffire.rosvoiesninjas.entity.decoration.SceauExplosifEntity;

public class ModEntities{
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ROSVoiesNinjas.MODID);

    public static final RegistryObject<EntityType<SceauExplosifEntity>> SCEAU_EXPLOSIF = ENTITIES.register("sceau_explosif", () ->
            EntityType.Builder.<SceauExplosifEntity>of(SceauExplosifEntity::new, MobCategory.MISC)
                    .sized(1F, 1F).clientTrackingRange(10).updateInterval(20).build("sceau_explosif"));

    public ModEntities(){}

    public static void register(IEventBus eventBus){
        ENTITIES.register(eventBus);
    }
}
