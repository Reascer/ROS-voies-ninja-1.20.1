package ros.eagleoffire.rosvoiesninjas.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import ros.eagleoffire.rosvoiesninjas.entity.decoration.SceauExplosif;


@OnlyIn(Dist.CLIENT)
public class SceauExplosifRenderer<T extends SceauExplosif> extends EntityRenderer<T> {
   private static final ModelResourceLocation FRAME_LOCATION = ModelResourceLocation.vanilla("item_frame", "map=false");
   private final BlockRenderDispatcher blockRenderer;
      private final ItemRenderer itemRenderer;

   public SceauExplosifRenderer(EntityRendererProvider.Context pContext) {
      super(pContext);
      this.itemRenderer = pContext.getItemRenderer();
      this.blockRenderer = pContext.getBlockRenderDispatcher();
   }

   public void render(T pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
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
      pPoseStack.pushPose();
      pPoseStack.translate(-0.5F, -0.5F, -0.5F);
      pPoseStack.popPose();
      pPoseStack.popPose();
   }

   private ModelResourceLocation getFrameModelResourceLoc(T pEntity, ItemStack pItem) {
       return FRAME_LOCATION;
   }

   public Vec3 getRenderOffset(T pEntity, float pPartialTicks) {
      return new Vec3((double)((float)pEntity.getDirection().getStepX() * 0.3F), -0.25D, (double)((float)pEntity.getDirection().getStepZ() * 0.3F));
   }

   /**
    * Returns the location of an entity's texture.
    */
   public ResourceLocation getTextureLocation(T pEntity) {
      return TextureAtlas.LOCATION_BLOCKS;
   }
}

