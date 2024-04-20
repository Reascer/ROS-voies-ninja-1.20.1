package ros.eagleoffire.rosvoiesninjas.ProgressionVoiesNinjas;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;

public class ProgressionVoiesNinjas {
    private int experience;

    public static final Capability<ProgressionVoiesNinjasInterface> INSTANCE = CapabilityManager.get(new CapabilityToken<>() {});

    public static void register(RegisterCapabilitiesEvent event) {
        event.register(ProgressionVoiesNinjasInterface.class);
    }

    ProgressionVoiesNinjas() {
    }

    public int getExperience(){
        return experience;
    }

    public void addExperience(int XpPoint){
        this.experience = experience + XpPoint;
    }

    public void subExperience(int XpPoint){
        this.experience = experience - XpPoint;
    }

    public void saveNBTData(CompoundTag nbt){
        nbt.putInt("experience", experience);
    }

    public void loadNBTData(CompoundTag nbt){
        experience = nbt.getInt("experience");
    }

    public void copyFrom(ProgressionVoiesNinjas source) {
        this.experience = source.experience;
    }
}
