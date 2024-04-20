package ros.eagleoffire.rosvoiesninjas.ProgressionVoiesNinjas;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ProgressionVoiesNinjasProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<ProgressionVoiesNinjas> PROGRESSION_VOIES_NINJAS = CapabilityManager.get(new CapabilityToken<ProgressionVoiesNinjas>(){});
    private ProgressionVoiesNinjas progression = null;
    private final LazyOptional<ProgressionVoiesNinjas> optionalData = LazyOptional.of(this::createPlayerProgression);

    private ProgressionVoiesNinjas createPlayerProgression() {
        if(this.progression == null){
            this.progression = new ProgressionVoiesNinjas();
        }
        return this.progression;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap,@Nullable Direction side){
        if(cap == PROGRESSION_VOIES_NINJAS){
            return optionalData.cast();
        }
        return LazyOptional.empty();
    }

    void invalidate() {
        this.optionalData.invalidate();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerProgression().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerProgression().loadNBTData(nbt);
    }
}
