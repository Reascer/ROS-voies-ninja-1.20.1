package ros.eagleoffire.rosvoiesninjas.ProgressionVoiesNinjas;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import ros.eagleoffire.rosvoiesninjas.VoiesNinjas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ProgressionVoiesNinjas {
    private int NombreDeVoiesMaitrise = 0;

    private VoiesNinjas Fuinjutsu = new VoiesNinjas("Fuinjutsu");
    private VoiesNinjas Sensorialite = new VoiesNinjas("Sensorialite");
    private VoiesNinjas Infiltration = new VoiesNinjas("Infiltration");
    private VoiesNinjas Invocation = new VoiesNinjas("Invocation");
    private VoiesNinjas KekkeiBarriere = new VoiesNinjas("Kekkei Barriere");

    public static final Capability<ProgressionVoiesNinjasInterface> INSTANCE = CapabilityManager.get(new CapabilityToken<>() {});

    public static void register(RegisterCapabilitiesEvent event) {
        event.register(ProgressionVoiesNinjasInterface.class);
    }

    ProgressionVoiesNinjas() {
    }

    public int getXP(VoiesNinjas voiesninjas){
        return voiesninjas.getXP();
    }
    public void addXP(VoiesNinjas voiesninjas, int XpPoint){
        voiesninjas.setXP(voiesninjas.getXP() + XpPoint);
    }
    public void subXP(VoiesNinjas voiesninjas, int XpPoint){
        voiesninjas.setXP(voiesninjas.getXP() - XpPoint);
    }
    public int getLVL(VoiesNinjas voiesninjas){
        return voiesninjas.getLVL();
    }
    public int getLVLmax(VoiesNinjas voiesninjas){
        return voiesninjas.getLVLmax();
    }

    public void saveNBTData(CompoundTag nbt){
        nbt.putInt("FuinjutsuXP", Fuinjutsu.getXP());
        nbt.putInt("SensorialiteXP", Sensorialite.getXP());
        nbt.putInt("InfiltrationXP", Infiltration.getXP());

        nbt.putInt("FuinjutsuLVL", Fuinjutsu.getLVL());
        nbt.putInt("SensorialiteLVL", Sensorialite.getLVL());
        nbt.putInt("InfiltrationLVL", Infiltration.getLVL());

        nbt.putInt("FuinjutsuLVLmax", Fuinjutsu.getLVLmax());
        nbt.putInt("SensorialiteLVLmax", Sensorialite.getLVLmax());
        nbt.putInt("InfiltrationLVLmax", Infiltration.getLVLmax());
    }
    public void loadNBTData(CompoundTag nbt){
        Fuinjutsu.setXP(nbt.getInt("FuinjutsuXP"));
        Sensorialite.setXP(nbt.getInt("SensorialiteXP"));
        Infiltration.setXP(nbt.getInt("InfiltrationXP"));

        Fuinjutsu.setLVL(nbt.getInt("FuinjutsuLVL"));
        Sensorialite.setLVL(nbt.getInt("SensorialiteLVL"));
        Infiltration.setLVL(nbt.getInt("InfiltrationLVL"));
    }
    public void copyFrom(ProgressionVoiesNinjas source) {
        this.Fuinjutsu = source.Fuinjutsu;
        this.Sensorialite = source.Sensorialite;
        this.Infiltration = source.Infiltration;
    }

    private int checkNewLVL(int XP, int LVL, int maxLVL){
        if (XP >= 1651 && LVL != 5 && maxLVL == 5){
            return 5;
        }else if (XP >= 951 && LVL != 4 && maxLVL == 4){
            return  4;
        }else if (XP >= 451 && LVL != 3 && maxLVL == 3){
            return  3;
        } else if (XP >= 151 && LVL != 2 && maxLVL == 2){
            return  2;
        }else if (XP >= 1 && LVL != 1 && maxLVL == 1){
            return 1;
        } else {return LVL;}
    }

    public VoiesNinjas getByName(String name){
        if (Objects.equals(name, Fuinjutsu.getNAME())){
            return Fuinjutsu;
        }
        if (Objects.equals(name, Sensorialite.getNAME())){
            return Sensorialite;
        }
        if (Objects.equals(name, Infiltration.getNAME())){
            return Infiltration;
        }
        if (Objects.equals(name, Invocation.getNAME())){
            return Invocation;
        }
        if (Objects.equals(name, KekkeiBarriere.getNAME())){
            return KekkeiBarriere;
        }
        return null;
    }
}
