package Request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;

import App.App;
import DataStructures.MyMaxHeap;
import Enum.*;
import User.BDRS;

public class Request implements Comparable<Request>{
    // static
    private static long requestTerbuat;
    private static LinkedList<Request> liveRequestList = new LinkedList<>();
    // general
    private String idPermintaan;
    // form
    private String namaRumahSakit;
    private String alamat;
    private String telepon;
    private BDRS unitBDRS;
    private LocalDate tanggalPermintaan;
    private LocalTime jamPermintaan;

    private String namaPasien;
    private golDarahEnum golonganDarah;
    private rhesusEnum rhesus;
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

    public Request(BDRS unitBDRS) {
        idPermintaan = "RQ" + requestTerbuat;
        this.unitBDRS=unitBDRS;
    }


    public void tampilkanForm() {

        System.out.println("\n=== FORM PERMINTAAN DARAH ===");

        System.out.println("Nama Rumah Sakit        : " + getNamaRumahSakit());
        System.out.println("Alamat                  : " + getAlamat());
        System.out.println("Telepon                 : " + getTelepon());
        System.out.println("Unit BDRS               : " + getUnitBDRS().getNama());
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

    public int hitungWeight() {

        int urgency = alasanKlinis.getSkor();

        int komponen = komponenDarah.getSkor();

        int kantong = jumlahKantong;

        return urgency * 1000 +
                komponen * 100 +
                kantong * 10;
    }

    @Override
    public int compareTo(Request other) {

        if (this.hitungWeight() < other.hitungWeight()){
            return -1;
        }else if (this.hitungWeight() == other.hitungWeight()){
            if(idPermintaan.compareTo(other.getIdPermintaan())==-1){
                return -1;
            }else{
                return 1;
            }
        }else{
            return 1;
        }
    }
    
    //#region static
    public static void displayRequests(App app){
    MyMaxHeap<Request> maxHeap = new MyMaxHeap<>(liveRequestList);
    int size = liveRequestList.size();
    Request[] list = new Request[size];
    
    System.out.println("=== REQUEST LIST ===");
    for(int i = 0; i < size; i++){
        System.out.println((i + 1) + "=====================");
        list[i] = maxHeap.extractMax();
        list[i].tampilkanForm();
        System.out.println();
    }
    
    int choice = -1;
    boolean isValid = false;

    while (!isValid) {
        System.out.print("Input (1-" + size + " to select, 0 to exit): ");
        String input = app.getSc().next()+app.getSc().nextLine(); 

        if (input.isEmpty()) {
            System.out.println("Error: Input cannot be empty.\n");
            continue;
        }

        boolean isNumeric = true;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (!Character.isDigit(c)) {
                isNumeric = false;
                break; 
            }
        }

        if (!isNumeric) {
            System.out.println("Error: Input must contain numbers only (no letters or symbols).\n");
            continue;
        }

        choice = Integer.parseInt(input);

        if (choice >= 0 && choice <= size) {
            isValid = true;
        } else {
            System.out.println("Error: Number out of bounds. Please enter a number between 0 and " + size + ".\n");
        }
    }

    if (choice == 0){
        app.getCurrentUser().tampilkanMenuUtama(app);
    } else {
        Request selectedRequest = list[choice - 1];
        System.out.println("\nYou selected request ID: " + selectedRequest.getIdPermintaan());
    }
}



    //#endregion

    // #region Getter Setter

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

    public static LinkedList<Request> getLiveRequestList() {
        return liveRequestList;
    }


    public static void setLiveRequestList(LinkedList<Request> liveRequestList) {
        Request.liveRequestList = liveRequestList;
    }


    public String getIdPermintaan() {
        return idPermintaan;
    }


    public void setIdPermintaan(String idPermintaan) {
        this.idPermintaan = idPermintaan;
    }

    public golDarahEnum getGolonganDarah() {
        return golonganDarah;
    }


    public void setGolonganDarah(golDarahEnum golonganDarah) {
        this.golonganDarah = golonganDarah;
    }

    public rhesusEnum getRhesus() {
        return rhesus;
    }


    public void setRhesus(rhesusEnum rhesus) {
        this.rhesus = rhesus;
    }

    // #endregion


}
