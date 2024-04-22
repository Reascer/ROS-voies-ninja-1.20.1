package ros.eagleoffire.rosvoiesninjas.Items;

import net.minecraft.SharedConstants;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HangingEntityItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import ros.eagleoffire.rosvoiesninjas.entity.custom.SceauExplosifEntity;


public class SceauExplosifItem extends HangingEntityItem {
    public SceauExplosifItem(Item.Properties pProperties) {
        //super(SceauExplosifEntity.SCEAU_EXPLOSIF.get(), pProperties);
        super(EntityType.ITEM_FRAME, pProperties);
    }

    protected boolean mayPlace(Player pPlayer, Direction pDirection, ItemStack pItemStack, BlockPos pPos) {
        return !pPlayer.level().isOutsideBuildHeight(pPos) && pPlayer.mayUseItemAt(pPos, pDirection, pItemStack);
    }
}

