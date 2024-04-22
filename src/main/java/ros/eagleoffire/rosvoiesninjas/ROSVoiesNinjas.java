package ros.eagleoffire.rosvoiesninjas;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.ItemFrameRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import ros.eagleoffire.rosvoiesninjas.Items.ModCreativeModTabs;
import ros.eagleoffire.rosvoiesninjas.Items.ModItems;
import ros.eagleoffire.rosvoiesninjas.client.renderer.entity.SceauExplosifRenderer;
import ros.eagleoffire.rosvoiesninjas.entity.custom.SceauExplosifEntity;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraft.client.renderer.entity.EntityRenderers;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ROSVoiesNinjas.MODID)
public class ROSVoiesNinjas {
    public static final String MODID = "rosvoiesninjas";
    private static final Logger LOGGER = LogUtils.getLogger();

    public ROSVoiesNinjas() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModTabs.register(modEventBus);
        SceauExplosifEntity.register(modEventBus);
        ModItems.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            //EntityRenderers.register(SceauExplosifEntity.SCEAU_EXPLOSIF.get(), SceauExplosifRenderer::new);
            EntityRenderers.register(EntityType.ITEM_FRAME, ItemFrameRenderer::new);
        }
    }
}
