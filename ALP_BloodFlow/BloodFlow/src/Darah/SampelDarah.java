package Darah;

import Enum.golDarahEnum;
import Enum.rhesusEnum;
import User.FasilitasDarah;

public class SampelDarah {

    private static int sampelTerbuat=0;
    
    private FasilitasDarah fasilitasDarah;
    public FasilitasDarah getFasilitasDarah() {
        return fasilitasDarah;
    }

    public void setFasilitasDarah(FasilitasDarah fasilitasDarah) {
        this.fasilitasDarah = fasilitasDarah;
    }

    private String idSampel;
    private KantongDarah kantongDarah;
    private boolean dariPendonor;
    private golDarahEnum golDarah;
    private rhesusEnum rhesus;

    public SampelDarah(KantongDarah kantongDarah) {
        this.dariPendonor = true;
        sampelTerbuat++;
        idSampel="SM"+sampelTerbuat;
        golDarah = kantongDarah.getJenisDarah();
        rhesus = kantongDarah.getRhesus();
        fasilitasDarah = kantongDarah.getFasilitasDarah();
    }

    public SampelDarah(golDarahEnum golDarah, rhesusEnum rhesus, FasilitasDarah fasilitasDarah) {
        this.dariPendonor = true;
        sampelTerbuat++;
        idSampel="SM"+sampelTerbuat;
        this.golDarah = golDarah;
        this.rhesus = rhesus;
        this.fasilitasDarah = fasilitasDarah;
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

    public String getIdSampel() {
        return idSampel;
    }

        public void setIdSampel(String idSampel) {
        this.idSampel = idSampel;
    }

    public golDarahEnum getGolDarah() {
        return golDarah;
    }

    public void setGolDarah(golDarahEnum golDarah) {
        this.golDarah = golDarah;
    }

    public rhesusEnum getRhesus() {
        return rhesus;
    }

    public void setRhesus(rhesusEnum rhesus) {
        this.rhesus = rhesus;
    }
    
}
