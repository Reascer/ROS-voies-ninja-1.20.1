package ros.eagleoffire.rosvoiesninjas.ProgressionVoiesNinjas;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;
import net.minecraftforge.common.util.INBTSerializable;

@AutoRegisterCapability
public interface ProgressionVoiesNinjasInterface extends INBTSerializable<CompoundTag> {

    String getValue();

    void setMyValue(String myValue);
}

