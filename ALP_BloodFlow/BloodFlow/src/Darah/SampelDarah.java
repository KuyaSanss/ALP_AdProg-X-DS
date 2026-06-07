package Darah;

import java.util.ArrayList;

public class SampelDarah {

    private static int sampelTerbuat=0;
    
    private String idSampel;
    private KantongDarah kantongDarah;
    private boolean dariPendonor;
    private ArrayList<TesDarah> listTesDarah = new ArrayList<>();

    public SampelDarah(boolean dariPendonor) {
        this.dariPendonor = dariPendonor;
        sampelTerbuat++;
        idSampel="SM"+sampelTerbuat;
    }
    
    public static int getSampelTerbuat() {
        return sampelTerbuat;
    }

    public static void setSampelTerbuat(int sampelTerbuat) {
        SampelDarah.sampelTerbuat = sampelTerbuat;
    }

    public KantongDarah getKantongDarah() {
        return kantongDarah;
    }

    public void setKantongDarah(KantongDarah kantongDarah) {
        this.kantongDarah = kantongDarah;
    }

    public boolean isDariPendonor() {
        return dariPendonor;
    }

    public void setDariPendonor(boolean dariPendonor) {
        this.dariPendonor = dariPendonor;
    }

    public ArrayList<TesDarah> getListTesDarah() {
        return listTesDarah;
    }

    public void setListTesDarah(ArrayList<TesDarah> listTesDarah) {
        this.listTesDarah = listTesDarah;
    }

    public String getIdSampel() {
        return idSampel;
    }
    
}
