package Request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;

import App.*;
import Enum.*;
import Model.MyMinHeap;
import User.*;

public class Request implements Comparable<Request> {
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
    private String nomorRekamMedis;
    private JenisKelamin jenisKelamin;
    private String ruangPerawatan;
    private String diagnosaKlinis;

    private int jumlahKantong;
    private LocalDateTime rencanaWaktuTransfusi;

    private String namaDokter;
    private String jabatan;
    private String nomorSIP;

    // tambahan data klinis / triase
    private int usiaPasien;
    private double kadarHb;
    

    private int tekananDarahSistolik;
    private int gcsMata;
    private int gcsVerbal;
    private int gcsMotorik;
    private int spo2;
    private int frekuensiNapas;
    private int frekuensiNadi;
    private double suhuTubuh;
    private int skalaNyeri;
    private int resusitasiCairan;
    private int intubasiAtauManajemenJalanNapas;
    private int defibrilasi;
    private int pemeriksaanLaboratorium;
    private int pemeriksaanRadiologi;
    private int jumlahJenisResourceMedis;
    private int konsultasiSpesialis;
    private int obatIVAtauIM;
    private int nebulizer;
    private int prosedurTindakan;
    private int reassessmentTandaVital;

    public Request(BDRS unitBDRS) {
        idPermintaan = "RQ" + requestTerbuat;
        this.unitBDRS = unitBDRS;
    }

    public void tampilkanForm() {
        System.out.println("ESI Priority            : "+hitungWeight());

        System.out.println("\n=== FORM PERMINTAAN DARAH ===");

        System.out.println("Nama Rumah Sakit        : " + getNamaRumahSakit());
        System.out.println("Alamat                  : " + getAlamat());
        System.out.println("Telepon                 : " + getTelepon());
        System.out.println("Unit BDRS               : " + getUnitBDRS().getNama());
        System.out.println("Tanggal Permintaan      : " + getTanggalPermintaan());
        System.out.println("Jam Permintaan          : " + getJamPermintaan());

        System.out.println("\nA. DATA PASIEN");

        System.out.println("Nama Pasien             : " + getNamaPasien());
        System.out.println("Golongan Darah          : " + getGolonganDarah());
        System.out.println("Rhesus Darah            : " + getRhesus());
        System.out.println("Nomor Rekam Medis       : " + getNomorRekamMedis());
        System.out.println("Jenis Kelamin           : " + getJenisKelamin());
        System.out.println("Ruang Perawatan         : " + getRuangPerawatan());
        System.out.println("Diagnosa Klinis         : " + getDiagnosaKlinis());

        System.out.println("\nB. DATA PERMINTAAN DARAH");

        System.out.println("Jumlah Kantong          : " + getJumlahKantong());
        System.out.println("Rencana Transfusi       : " + getRencanaWaktuTransfusi());

        System.out.println("\nC. DATA DOKTER PEMINTA");

        System.out.println("Nama Dokter             : " + getNamaDokter());
        System.out.println("Jabatan                 : " + getJabatan());
        System.out.println("Nomor SIP               : " + getNomorSIP());

        System.out.println("\nC. DATA KLINIS PASIEN");

        System.out.println("Usia Pasien             : " + getUsiaPasien());
        System.out.println("Kadar Hb                : " + getKadarHb() + " g/dL");
        System.out.println("Tekanan Sistolik        : " + getTekananDarahSistolik());
        System.out.println("GCS Mata                : " + getGcsMata());
        System.out.println("GCS Verbal              : " + getGcsVerbal());
        System.out.println("GCS Motorik             : " + getGcsMotorik());
        System.out.println("SpO2                    : " + getSpo2());
        System.out.println("Frekuensi Napas         : " + getFrekuensiNapas());
        System.out.println("Frekuensi Nadi          : " + getFrekuensiNadi());
        System.out.println("Suhu Tubuh              : " + getSuhuTubuh());
        System.out.println("Skala Nyeri             : " + getSkalaNyeri());
        System.out.println("Jumlah Resource Medis   : " + getJumlahJenisResourceMedis());

        System.out.println("\nC. DATA RESOURCE MEDIS");

        System.out.println("Jumlah Resource Medis   : " + (getJumlahJenisResourceMedis()==1 ?"YA":"TIDAK"));
        System.out.println("Laboratorium             : " + (getPemeriksaanLaboratorium()==1 ?"YA":"TIDAK"));
        System.out.println("Radiologi                : " + (getPemeriksaanRadiologi()==1 ?"YA":"TIDAK"));
        System.out.println("Konsultasi Spesialis     : " + (getKonsultasiSpesialis()==1 ?"YA":"TIDAK"));
        System.out.println("Obat IV / IM             : " + (getObatIVAtauIM()==1 ?"YA":"TIDAK"));
        System.out.println("Nebulizer                : " + (getNebulizer()==1 ?"YA":"TIDAK"));
        System.out.println("Prosedur Tindakan        : " + (getProsedurTindakan()==1 ?"YA":"TIDAK"));
        System.out.println("Reassessment Tanda Vital : " + (getReassessmentTandaVital()==1 ?"YA":"TIDAK"));
        System.out.println("Transfusi Darah          : " + "YA");

        System.out.println("\nD. TINDAKAN EMERGENSI");

        System.out.println("Resusitasi Cairan        : " + (getResusitasiCairan()==1 ?"YA":"TIDAK"));
        System.out.println("Intubasi / Jalan Napas   : " + (getIntubasiAtauManajemenJalanNapas()==1 ?"YA":"TIDAK"));
        System.out.println("Defibrilasi              : " + (getDefibrilasi()==1 ?"YA":"TIDAK"));
    }

    public int hitungWeight() {
        int gcsTotal = gcsMata + gcsVerbal + gcsMotorik;

        // ESI 1: paling gawat / butuh resusitasi
        if (resusitasiCairan == 1
                || intubasiAtauManajemenJalanNapas == 1
                || defibrilasi == 1
                || kadarHb < 5
                || (spo2 > 0 && spo2 < 90)
                || (tekananDarahSistolik > 0 && tekananDarahSistolik < 90)
                || (gcsTotal > 0 && gcsTotal <= 8)) {
            return 1;
        }

        // ESI 2: sangat urgent
        if (konsultasiSpesialis == 1
                || prosedurTindakan == 1
                || pemeriksaanRadiologi == 1
                || pemeriksaanLaboratorium == 1
                || kadarHb < 7
                || skalaNyeri >= 7
                || (spo2 >= 90 && spo2 <= 94)
                || (gcsTotal >= 9 && gcsTotal <= 12)
                || (tekananDarahSistolik >= 90 && tekananDarahSistolik <= 100 && usiaPasien > 0)) {
            return 2;
        }

        // ESI 3: butuh beberapa resource
        if (jumlahJenisResourceMedis >= 2) {
            return 3;
        }

        // ESI 4: butuh satu resource
        if (jumlahJenisResourceMedis == 1) {
            return 4;
        }

        // ESI 5: tidak butuh resource
        return 5;
    }

    public void approveRequest(App app) {
        System.out.println("=== APPROVE REQUEST ===");
        tampilkanForm();
        System.out.println();
        System.out.println("1. Approve");
        System.out.println("0. Back to menu");
        boolean wrong = false;
        do {
            String input = app.getSc().next() + app.getSc().nextLine();
            if (input.equals("1")) {

            } else if (input.equals("0")) {

            } else {
                System.out.println("Wrong input! Only 1 or 0");
                wrong = true;
            }
        } while (wrong);
    }

    @Override
    public int compareTo(Request other) {
        int thisWeight = this.hitungWeight();
        int otherWeight = other.hitungWeight();

        if (thisWeight < otherWeight) {
            return -1;
        } else if (thisWeight > otherWeight) {
            return 1;
        } else {
            return this.idPermintaan.compareTo(other.idPermintaan);
        }
    }

    public int getGcsTotal() {
        return gcsMata + gcsVerbal + gcsMotorik;
    }



    // #region static

    public static Request displayRequests(App app) {
        MyMinHeap<Request> maxHeap = new MyMinHeap<>(liveRequestList);
        int size = liveRequestList.size();
        Request[] list = new Request[size];

        System.out.println("=== REQUEST LIST ===");
        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + " =====================");
            list[i] = maxHeap.extractMin();
            list[i].tampilkanForm();
            System.out.println();
        }

        int choice = -1;
        boolean isValid = false;

        while (!isValid) {
            System.out.print("Input (1-" + size + " to select, 0 to exit): ");
            String input = app.getSc().next() + app.getSc().nextLine();

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

        if (choice == 0) {
            app.getCurrentUser().tampilkanMenuUtama(app);
        } else {
            Request selectedRequest = list[choice - 1];
            System.out.println("\nYou selected request ID: " + selectedRequest.getIdPermintaan());
            return selectedRequest;
        }
        return null;
    }

    // #endregion

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

    public int getUsiaPasien() {
        return usiaPasien;
    }

    public void setUsiaPasien(int usiaPasien) {
        this.usiaPasien = usiaPasien;
    }

    public int getTekananDarahSistolik() {
        return tekananDarahSistolik;
    }

    public void setTekananDarahSistolik(int tekananDarahSistolik) {
        this.tekananDarahSistolik = tekananDarahSistolik;
    }

    public int getGcsMata() {
        return gcsMata;
    }

    public void setGcsMata(int gcsMata) {
        this.gcsMata = gcsMata;
    }

    public int getGcsVerbal() {
        return gcsVerbal;
    }

    public void setGcsVerbal(int gcsVerbal) {
        this.gcsVerbal = gcsVerbal;
    }

    public int getGcsMotorik() {
        return gcsMotorik;
    }

    public void setGcsMotorik(int gcsMotorik) {
        this.gcsMotorik = gcsMotorik;
    }

    public int getSpo2() {
        return spo2;
    }

    public void setSpo2(int spo2) {
        this.spo2 = spo2;
    }

    public int getFrekuensiNapas() {
        return frekuensiNapas;
    }

    public void setFrekuensiNapas(int frekuensiNapas) {
        this.frekuensiNapas = frekuensiNapas;
    }

    public int getFrekuensiNadi() {
        return frekuensiNadi;
    }

    public void setFrekuensiNadi(int frekuensiNadi) {
        this.frekuensiNadi = frekuensiNadi;
    }

    public double getSuhuTubuh() {
        return suhuTubuh;
    }

    public void setSuhuTubuh(double suhuTubuh) {
        this.suhuTubuh = suhuTubuh;
    }

    public int getSkalaNyeri() {
        return skalaNyeri;
    }

    public void setSkalaNyeri(int skalaNyeri) {
        this.skalaNyeri = skalaNyeri;
    }

    public int getJumlahJenisResourceMedis() {
        return jumlahJenisResourceMedis;
    }

    public void setJumlahJenisResourceMedis(int jumlahJenisResourceMedis) {
        this.jumlahJenisResourceMedis = jumlahJenisResourceMedis;
    }

    public int getResusitasiCairan() {
        return resusitasiCairan;
    }

    public void setResusitasiCairan(int resusitasiCairan) {
        this.resusitasiCairan = resusitasiCairan;
    }

    public int getIntubasiAtauManajemenJalanNapas() {
        return intubasiAtauManajemenJalanNapas;
    }

    public void setIntubasiAtauManajemenJalanNapas(int intubasiAtauManajemenJalanNapas) {
        this.intubasiAtauManajemenJalanNapas = intubasiAtauManajemenJalanNapas;
    }

    public int getDefibrilasi() {
        return defibrilasi;
    }

    public void setDefibrilasi(int defibrilasi) {
        this.defibrilasi = defibrilasi;
    }

    public int getPemeriksaanLaboratorium() {
        return pemeriksaanLaboratorium;
    }

    public void setPemeriksaanLaboratorium(int pemeriksaanLaboratorium) {
        this.pemeriksaanLaboratorium = pemeriksaanLaboratorium;
    }

    public int getPemeriksaanRadiologi() {
        return pemeriksaanRadiologi;
    }

    public void setPemeriksaanRadiologi(int pemeriksaanRadiologi) {
        this.pemeriksaanRadiologi = pemeriksaanRadiologi;
    }

    public int getKonsultasiSpesialis() {
        return konsultasiSpesialis;
    }

    public void setKonsultasiSpesialis(int konsultasiSpesialis) {
        this.konsultasiSpesialis = konsultasiSpesialis;
    }

    public int getObatIVAtauIM() {
        return obatIVAtauIM;
    }

    public void setObatIVAtauIM(int obatIVAtauIM) {
        this.obatIVAtauIM = obatIVAtauIM;
    }

    public int getNebulizer() {
        return nebulizer;
    }

    public void setNebulizer(int nebulizer) {
        this.nebulizer = nebulizer;
    }

    public int getProsedurTindakan() {
        return prosedurTindakan;
    }

    public void setProsedurTindakan(int prosedurTindakan) {
        this.prosedurTindakan = prosedurTindakan;
    }

    public int getReassessmentTandaVital() {
        return reassessmentTandaVital;
    }

    public void setReassessmentTandaVital(int reassessmentTandaVital) {
        this.reassessmentTandaVital = reassessmentTandaVital;
    }
    
    public double getKadarHb() {
        return kadarHb;
    }

    public void setKadarHb(double kadarHb) {
        this.kadarHb = kadarHb;
    }

    // #endregion

}