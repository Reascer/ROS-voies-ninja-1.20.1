package ros.eagleoffire.rosvoiesninjas.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;
import ros.eagleoffire.rosvoiesninjas.ROSVoiesNinjas;
import ros.eagleoffire.rosvoiesninjas.entity.client.ModModelLayers;
import ros.eagleoffire.rosvoiesninjas.entity.client.RhinoModel;
import ros.eagleoffire.rosvoiesninjas.entity.decoration.SceauExplosifEntity;

@OnlyIn(Dist.CLIENT)
public class SceauExplosifRenderer extends EntityRenderer<SceauExplosifEntity>{
   //private static final ModelResourceLocation FRAME_LOCATION = ModelResourceLocation.vanilla("glow_item_frame", "map=false");
   //private static final ModelResourceLocation FRAME_LOCATION = new ModelResourceLocation("minecraft", "item_frame", "map=false");
   private static final ModelResourceLocation FRAME_LOCATION = new ModelResourceLocation(new ResourceLocation(ROSVoiesNinjas.MODID, "sceau_explosif"), "sceau_explosif");
   //public static final ModelLayerLocation FRAME_LOCATION = new ModelLayerLocation(new ResourceLocation(ROSVoiesNinjas.MODID, "sceau_explosif"), "main");

   private final BlockRenderDispatcher blockRenderer;
   private final ItemRenderer itemRenderer;

   public SceauExplosifRenderer(EntityRendererProvider.Context pContext) {
      super(pContext);
      System.out.println("frame location");
      System.out.println(FRAME_LOCATION);

      this.itemRenderer = pContext.getItemRenderer();
      this.blockRenderer = pContext.getBlockRenderDispatcher();
   }

   @Override
   public void render(SceauExplosifEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
      super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
      pPoseStack.pushPose();
      Direction direction = pEntity.getDirection();
      Vec3 vec3 = this.getRenderOffset(pEntity, pPartialTicks);
      pPoseStack.translate(-vec3.x(), -vec3.y(), -vec3.z());
      double d0 = 0.46875D;
      pPoseStack.translate((double)direction.getStepX() * 0.46875D, (double)direction.getStepY() * 0.46875D, (double)direction.getStepZ() * 0.46875D);
      pPoseStack.mulPose(Axis.XP.rotationDegrees(pEntity.getXRot()));
      pPoseStack.mulPose(Axis.YP.rotationDegrees(180.0F - pEntity.getYRot()));
      ModelManager modelmanager = this.blockRenderer.getBlockModelShaper().getModelManager();
      ItemStack itemstack = pEntity.getItem();
      ModelResourceLocation modelresourcelocation = this.getFrameModelResourceLoc(pEntity, itemstack);
      pPoseStack.pushPose();
      pPoseStack.translate(-0.5F, -0.5F, -0.5F);
      this.blockRenderer.getModelRenderer().renderModel(pPoseStack.last(), pBuffer.getBuffer(Sheets.solidBlockSheet()), (BlockState)null, modelmanager.getModel(modelresourcelocation), 1.0F, 1.0F, 1.0F, pPackedLight, OverlayTexture.NO_OVERLAY);
      pPoseStack.popPose();
      pPoseStack.popPose();
   }

   @Override
   public ResourceLocation getTextureLocation(SceauExplosifEntity pEntity) {
      return new ResourceLocation(ROSVoiesNinjas.MODID, "textures/entity/rhino.png");
   }

   private ModelResourceLocation getFrameModelResourceLoc(SceauExplosifEntity pEntity, ItemStack pItem) {
       return FRAME_LOCATION;
   }

   public Vec3 getRenderOffset(SceauExplosifEntity pEntity, float pPartialTicks) {
      return new Vec3((double)((float)pEntity.getDirection().getStepX() * 0.3F), -0.25D, (double)((float)pEntity.getDirection().getStepZ() * 0.3F));
   }
}

