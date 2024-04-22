//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ros.eagleoffire.rosvoiesninjas.entity.decoration;

import com.mojang.logging.LogUtils;
import java.util.OptionalInt;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DiodeBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;

public class SceauExplosif extends HangingEntity{
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final EntityDataAccessor<ItemStack> DATA_ITEM;
    private static final EntityDataAccessor<Integer> DATA_ROTATION;
    public static final int NUM_ROTATIONS = 8;
    private float dropChance;
    private boolean fixed;

    public SceauExplosif(EntityType<? extends ItemFrame> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.dropChance = 1.0F;
    }

    public SceauExplosif(Level pLevel, BlockPos pPos, Direction pFacingDirection) {
        this(EntityType.ITEM_FRAME, pLevel, pPos, pFacingDirection);
    }

    public SceauExplosif(EntityType<? extends ItemFrame> pEntityType, Level pLevel, BlockPos pPos, Direction pDirection) {
        super(pEntityType, pLevel, pPos);
        this.dropChance = 1.0F;
        this.setDirection(pDirection);
    }

    protected float getEyeHeight(Pose pPose, EntityDimensions pSize) {
        return 0.0F;
    }

    protected void defineSynchedData() {
        this.getEntityData().define(DATA_ITEM, ItemStack.EMPTY);
        this.getEntityData().define(DATA_ROTATION, 0);
    }

    protected void setDirection(Direction pFacingDirection) {
        Validate.notNull(pFacingDirection);
        this.direction = pFacingDirection;
        if (pFacingDirection.getAxis().isHorizontal()) {
            this.setXRot(0.0F);
            this.setYRot((float)(this.direction.get2DDataValue() * 90));
        } else {
            this.setXRot((float)(-90 * pFacingDirection.getAxisDirection().getStep()));
            this.setYRot(0.0F);
        }

        this.xRotO = this.getXRot();
        this.yRotO = this.getYRot();
        this.recalculateBoundingBox();
    }

    protected void recalculateBoundingBox() {
        if (this.direction != null) {
            double $$0 = 0.46875;
            double $$1 = (double)this.pos.getX() + 0.5 - (double)this.direction.getStepX() * 0.46875;
            double $$2 = (double)this.pos.getY() + 0.5 - (double)this.direction.getStepY() * 0.46875;
            double $$3 = (double)this.pos.getZ() + 0.5 - (double)this.direction.getStepZ() * 0.46875;
            this.setPosRaw($$1, $$2, $$3);
            double $$4 = (double)this.getWidth();
            double $$5 = (double)this.getHeight();
            double $$6 = (double)this.getWidth();
            Direction.Axis $$7 = this.direction.getAxis();
            switch ($$7) {
                case X -> $$4 = 1.0;
                case Y -> $$5 = 1.0;
                case Z -> $$6 = 1.0;
            }

            $$4 /= 32.0;
            $$5 /= 32.0;
            $$6 /= 32.0;
            this.setBoundingBox(new AABB($$1 - $$4, $$2 - $$5, $$3 - $$6, $$1 + $$4, $$2 + $$5, $$3 + $$6));
        }
    }

    public boolean survives() {
        if (this.fixed) {
            return true;
        } else if (!this.level().noCollision(this)) {
            return false;
        } else {
            BlockState $$0 = this.level().getBlockState(this.pos.relative(this.direction.getOpposite()));
            return $$0.isSolid() || this.direction.getAxis().isHorizontal() && DiodeBlock.isDiode($$0) ? this.level().getEntities(this, this.getBoundingBox(), HANGING_ENTITY).isEmpty() : false;
        }
    }

    public void move(MoverType pType, Vec3 pPos) {
        if (!this.fixed) {
            super.move(pType, pPos);
        }

    }

    public void push(double pX, double pY, double pZ) {
        if (!this.fixed) {
            super.push(pX, pY, pZ);
        }

    }

    public float getPickRadius() {
        return 0.0F;
    }

    public void kill() {
        super.kill();
    }

    public boolean hurt(DamageSource pSource, float pAmount) {
        if (this.fixed) {
            return (pSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY) || pSource.isCreativePlayer()) && super.hurt(pSource, pAmount);
        } else if (this.isInvulnerableTo(pSource)) {
            return false;
        } else if (!pSource.is(DamageTypeTags.IS_EXPLOSION)) {
            if (!this.level().isClientSide) {
            }

            return true;
        } else {
            return super.hurt(pSource, pAmount);
        }
    }

    public SoundEvent getRemoveItemSound() {
        return SoundEvents.ITEM_FRAME_REMOVE_ITEM;
    }

    public int getWidth() {
        return 12;
    }

    public int getHeight() {
        return 12;
    }

    public boolean shouldRenderAtSqrDistance(double pDistance) {
        double $$1 = 16.0;
        $$1 *= 64.0 * getViewScale();
        return pDistance < $$1 * $$1;
    }

    public void dropItem(@Nullable Entity pBrokenEntity) {
        this.gameEvent(GameEvent.BLOCK_CHANGE, pBrokenEntity);
    }

    @Override
    public void playPlacementSound() {

    }


    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);

        pCompound.putByte("Facing", (byte)this.direction.get3DDataValue());
        pCompound.putBoolean("Invisible", this.isInvisible());
        pCompound.putBoolean("Fixed", this.fixed);
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        CompoundTag $$1 = pCompound.getCompound("Item");
        this.setDirection(Direction.from3DDataValue(pCompound.getByte("Facing")));
        this.setInvisible(pCompound.getBoolean("Invisible"));
        this.fixed = pCompound.getBoolean("Fixed");
    }

    public InteractionResult interact(Player pPlayer, InteractionHand pHand) {
        return InteractionResult.CONSUME;
    }

    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this, this.direction.get3DDataValue(), this.getPos());
    }

    public void recreateFromPacket(ClientboundAddEntityPacket pPacket) {
        super.recreateFromPacket(pPacket);
        this.setDirection(Direction.from3DDataValue(pPacket.getData()));
    }

    protected ItemStack getFrameItemStack() {
        return new ItemStack(Items.ITEM_FRAME);
    }

    static {
        DATA_ITEM = SynchedEntityData.defineId(SceauExplosif.class, EntityDataSerializers.ITEM_STACK);
        DATA_ROTATION = SynchedEntityData.defineId(SceauExplosif.class, EntityDataSerializers.INT);
    }

    @Override
    public boolean alwaysAccepts() {
        return super.alwaysAccepts();
    }
}
