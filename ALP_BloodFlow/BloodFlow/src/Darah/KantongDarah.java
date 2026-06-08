package Darah;

import java.time.LocalDate;

import Enum.golDarahEnum;
import Enum.rhesusEnum;
import Request.Request;
import User.FasilitasDarah;

public class KantongDarah {
    private String idDarah;
    private String idPendonor;
    private golDarahEnum jenisDarah;
    private rhesusEnum rhesus;
    private LocalDate tanggalMasuk;
    private LocalDate tanggalKadaluarsa;
    private FasilitasDarah fasilitasDarah;
    private SampelDarah sampelDarah;
    private Request request;

    public KantongDarah(String idDarah, String idPendonor, golDarahEnum jenisDarah, rhesusEnum rhesus,FasilitasDarah fasilitasDarah) {
        this.idDarah = idDarah;
        this.idPendonor = idPendonor;
        this.jenisDarah = jenisDarah;
        this.rhesus = rhesus;
        this.fasilitasDarah = fasilitasDarah;
        this.tanggalMasuk = LocalDate.now(); 
        this.tanggalKadaluarsa = this.tanggalMasuk.plusDays(35);
        sampelDarah = new SampelDarah(this);
    }

    public String getIdDarah() {
        return idDarah;
    }

    public String getIdPendonor() {
        return idPendonor;
    }

    public golDarahEnum getJenisDarah() {
        return jenisDarah;
    }

    public rhesusEnum getRhesus() {
        return rhesus;
    }

    public LocalDate getTanggalMasuk() {
        return tanggalMasuk;
    }

    public LocalDate getTanggalKadaluarsa() {
        return tanggalKadaluarsa;
    }

        public void setIdDarah(String idDarah) {
        this.idDarah = idDarah;
    }

    public void setIdPendonor(String idPendonor) {
        this.idPendonor = idPendonor;
    }

    public void setJenisDarah(golDarahEnum jenisDarah) {
        this.jenisDarah = jenisDarah;
    }

    public void setRhesus(rhesusEnum rhesus) {
        this.rhesus = rhesus;
    }

    public void setTanggalMasuk(LocalDate tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }

    public void setTanggalKadaluarsa(LocalDate tanggalKadaluarsa) {
        this.tanggalKadaluarsa = tanggalKadaluarsa;
    }

    public FasilitasDarah getFasilitasDarah() {
        return fasilitasDarah;
    }

        public SampelDarah getSampelDarah() {
        return sampelDarah;
    }

    public void setSampelDarah(SampelDarah sampelDarah) {
        this.sampelDarah = sampelDarah;
    }

    public void setFasilitasDarah(FasilitasDarah fasilitasDarah) {
        this.fasilitasDarah = fasilitasDarah;
    }

        public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

}
