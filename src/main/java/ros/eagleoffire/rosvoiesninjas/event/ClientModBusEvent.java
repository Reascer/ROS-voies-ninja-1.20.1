package ros.eagleoffire.rosvoiesninjas.event;

import net.minecraft.client.renderer.entity.ItemFrameRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ros.eagleoffire.rosvoiesninjas.ROSVoiesNinjas;
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterRenderers;
import ros.eagleoffire.rosvoiesninjas.entity.custom.SceauExplosifEntity;
import ros.eagleoffire.rosvoiesninjas.client.renderer.entity.SceauExplosifRenderer;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid= ROSVoiesNinjas.MODID, value=Dist.CLIENT, bus= Mod.EventBusSubscriber.Bus.MOD)
public class ClientModBusEvent {
	@SubscribeEvent
	public static void registerRenderersEvent(RegisterRenderers event) {
		//event.registerEntityRenderer(SceauExplosifEntity.SCEAU_EXPLOSIF.get(), SceauExplosifRenderer::new);
		event.registerEntityRenderer(EntityType.ITEM_FRAME, ItemFrameRenderer::new);
	}
}
