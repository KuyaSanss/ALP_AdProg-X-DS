package Request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import Enum.*;
import User.BDRS;

public class Request {
    //untuk id
    private static long requestTerbuat;
    //general
    private String idPermintaan;
    private golDarahEnum golonganDarah;
    private BDRS bdrs;
    //form
    private String namaRumahSakit;
    private String alamat;
    private String telepon;
    private BDRS unitBDRS;
    private LocalDate tanggalPermintaan;
    private LocalTime jamPermintaan;

    private String namaPasien;
    private String tanggalLahirAtauUsia;
    private String nomorRekamMedis;
    private JenisKelamin jenisKelamin;
    private String ruangPerawatan;
    private String diagnosaKlinis;

    private KomponenDarah komponenDarah;
    private int jumlahKantong;
    private LocalDateTime rencanaWaktuTransfusi;
    private AlasanKlinis alasanKlinis;

    private String namaDokter;
    private String jabatan;
    private String nomorSIP;
    

    public Request(BDRS bdrs){
        idPermintaan="RQ"+requestTerbuat;
    }

    // TODO the most important function
    public void getUrgency(){

    }

    

    public void tampilkanForm() {

        System.out.println("\n=== FORM PERMINTAAN DARAH ===");

        System.out.println("Nama Rumah Sakit        : " + getNamaRumahSakit());
        System.out.println("Alamat                  : " + getAlamat());
        System.out.println("Telepon                 : " + getTelepon());
        System.out.println("Unit BDRS               : " + getUnitBDRS());
        System.out.println("Tanggal Permintaan      : " + getTanggalPermintaan());
        System.out.println("Jam Permintaan          : " + getJamPermintaan());

        System.out.println("\nA. DATA PASIEN");

        System.out.println("Nama Pasien             : " + getNamaPasien());
        System.out.println("Tanggal Lahir atau Usia : " + getTanggalLahirAtauUsia());
        System.out.println("Nomor Rekam Medis       : " + getNomorRekamMedis());
        System.out.println("Jenis Kelamin           : " + getJenisKelamin());
        System.out.println("Ruang Perawatan         : " + getRuangPerawatan());
        System.out.println("Diagnosa Klinis         : " + getDiagnosaKlinis());

        System.out.println("\nB. DATA PERMINTAAN DARAH");

        System.out.println("Komponen Darah          : " + getKomponenDarah());
        System.out.println("Jumlah Kantong          : " + getJumlahKantong());
        System.out.println("Rencana Transfusi       : " + getRencanaWaktuTransfusi());
        System.out.println("Alasan Klinis           : " + getAlasanKlinis());

        System.out.println("\nC. DATA DOKTER PEMINTA");

        System.out.println("Nama Dokter             : " + getNamaDokter());
        System.out.println("Jabatan                 : " + getJabatan());
        System.out.println("Nomor SIP               : " + getNomorSIP());

    }


    
    //  #region Getter Setter

        public String getNamaRumahSakit() {
        return namaRumahSakit;
    }

    public void setNamaRumahSakit(String namaRumahSakit) {
        this.namaRumahSakit = namaRumahSakit;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public BDRS getUnitBDRS() {
        return unitBDRS;
    }

    public void setUnitBDRS(BDRS unitBDRS) {
        this.unitBDRS = unitBDRS;
    }

    public LocalDate getTanggalPermintaan() {
    return tanggalPermintaan;
}

    public void setTanggalPermintaan(
            LocalDate tanggalPermintaan) {

        this.tanggalPermintaan = tanggalPermintaan;
    }

    public LocalTime getJamPermintaan() {
        return jamPermintaan;
    }

    public void setJamPermintaan(
            LocalTime jamPermintaan) {

        this.jamPermintaan = jamPermintaan;
    }

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public String getTanggalLahirAtauUsia() {
        return tanggalLahirAtauUsia;
    }

    public void setTanggalLahirAtauUsia(String tanggalLahirAtauUsia) {
        this.tanggalLahirAtauUsia = tanggalLahirAtauUsia;
    }

    public String getNomorRekamMedis() {
        return nomorRekamMedis;
    }

    public void setNomorRekamMedis(String nomorRekamMedis) {
        this.nomorRekamMedis = nomorRekamMedis;
    }

    public JenisKelamin getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(JenisKelamin jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getRuangPerawatan() {
        return ruangPerawatan;
    }

    public void setRuangPerawatan(String ruangPerawatan) {
        this.ruangPerawatan = ruangPerawatan;
    }

    public String getDiagnosaKlinis() {
        return diagnosaKlinis;
    }

    public void setDiagnosaKlinis(String diagnosaKlinis) {
        this.diagnosaKlinis = diagnosaKlinis;
    }

    public KomponenDarah getKomponenDarah() {
        return komponenDarah;
    }

    public void setKomponenDarah(KomponenDarah komponenDarah) {
        this.komponenDarah = komponenDarah;
    }

    public int getJumlahKantong() {
        return jumlahKantong;
    }

    public void setJumlahKantong(int jumlahKantong) {
        this.jumlahKantong = jumlahKantong;
    }

    public LocalDateTime getRencanaWaktuTransfusi() {
        return rencanaWaktuTransfusi;
    }

    public void setRencanaWaktuTransfusi(LocalDateTime rencanaWaktuTransfusi) {
        this.rencanaWaktuTransfusi = rencanaWaktuTransfusi;
    }

    public AlasanKlinis getAlasanKlinis() {
        return alasanKlinis;
    }

    public void setAlasanKlinis(AlasanKlinis alasanKlinis) {
        this.alasanKlinis = alasanKlinis;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getNomorSIP() {
        return nomorSIP;
    }

    public void setNomorSIP(String nomorSIP) {
        this.nomorSIP = nomorSIP;
    }


    //  #endregion

}
