package Request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import App.*;
import Enum.*;
import User.*;

public class Form implements Comparable<Form> {
    // general
    private boolean permintaanPasien;

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

    public Form(FasilitasDarah fasilitasDarah) {
        setNamaRumahSakit(fasilitasDarah.getNama());
        setAlamat(fasilitasDarah.getAlamat());
        setTelepon(fasilitasDarah.getNoTelp());
        setTanggalPermintaan(LocalDate.now());
        setJamPermintaan(LocalTime.now());
    }

    public void menuForm(App app) {
        String input;

        System.out.println("""
                === Jenis Request ===
                1. Darah untuk Pasien
                2. Darah untuk Stok
                3. Exit
                """);
        System.out.print("Input: ");
        input = app.getSc().next() + app.getSc().nextLine();
        switch (input) {
            case "1":
                formPasien(app);
                break;
            case "2":
                formStok(app);
                break;
            case "3":
                app.getCurrentUser().tampilkanMenuUtama(app);
                break;
            default:
                System.out.println("Invalid Input!!");
        }

    }

    private void formStok(App app){

        System.out.println("=== INPUT FORM PERMINTAAN DARAH ===");

        System.out.println("\nA. DATA PERMINTAAN");

        golonganDarah(app);
        rhesus(app);
        jumlahKantong(app);

    }

    private void formPasien(App app) {

        System.out.println("=== INPUT FORM PERMINTAAN DARAH ===");

        System.out.println("\nA. DATA PASIEN");

        System.out.print("Nama Pasien: ");
        setNamaPasien(app.getSc().nextLine());

        golonganDarah(app);
        rhesus(app);

        System.out.print("Nomor Rekam Medis: ");
        setNomorRekamMedis(app.getSc().nextLine());

        jenisKelamin(app);

        System.out.print("Ruang Perawatan: ");
        setRuangPerawatan(app.getSc().nextLine());

        System.out.print("Diagnosa Klinis: ");
        setDiagnosaKlinis(app.getSc().nextLine());

        System.out.println("\nB. DATA KLINIS PASIEN");

        usiaPasien(app);
        kadarHb(app);
        tekananDarahSistolik(app);
        gcsMataLangsung(app);
        gcsVerbalLangsung(app);
        gcsMotorikLangsung(app);
        spo2(app);
        frekuensiNapas(app);
        frekuensiNadi(app);
        suhuTubuh(app);
        skalaNyeri(app);

        System.out.println("\nC. KEBUTUHAN TINDAKAN / RESOURCE MEDIS");

        resusitasiCairan(app);
        intubasiAtauManajemenJalanNapas(app);
        defibrilasi(app);
        pemeriksaanLaboratorium(app);
        pemeriksaanRadiologi(app);
        konsultasiSpesialis(app);
        obatIVAtauIM(app);
        nebulizer(app);
        prosedurTindakan(app);
        reassessmentTandaVital(app);

        System.out.println("\nD. DATA PERMINTAAN DARAH");

        jumlahKantong(app);
        rencanaWaktuTransfusi(app);

        System.out.println("\nE. DATA DOKTER PEMINTA");

        System.out.print("Nama Dokter: ");
        setNamaDokter(app.getSc().nextLine());

        System.out.print("Jabatan: ");
        setJabatan(app.getSc().nextLine());

        System.out.print("Nomor SIP: ");
        setNomorSIP(app.getSc().nextLine());

        System.out.println("permintaan darah berhasil terkirim");
        app.getCurrentUser().tampilkanMenuUtama(app);//kembali ke menu user
    }

    private void tampilkanFormPasien() {
        System.out.println("ESI Priority            : " + hitungWeight());

        System.out.println("\n=== FORM PERMINTAAN DARAH ===");

        System.out.println("Nama Rumah Sakit        : " + getNamaRumahSakit());
        System.out.println("Alamat                  : " + getAlamat());
        System.out.println("Telepon                 : " + getTelepon());
        System.out.println("Unit Fasilitas Darah    : " + getfasilitasDarah().getNama());
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

        System.out.println("Jumlah Resource Medis   : " + (getJumlahJenisResourceMedis() == 1 ? "YA" : "TIDAK"));
        System.out.println("Laboratorium             : " + (getPemeriksaanLaboratorium() == 1 ? "YA" : "TIDAK"));
        System.out.println("Radiologi                : " + (getPemeriksaanRadiologi() == 1 ? "YA" : "TIDAK"));
        System.out.println("Konsultasi Spesialis     : " + (getKonsultasiSpesialis() == 1 ? "YA" : "TIDAK"));
        System.out.println("Obat IV / IM             : " + (getObatIVAtauIM() == 1 ? "YA" : "TIDAK"));
        System.out.println("Nebulizer                : " + (getNebulizer() == 1 ? "YA" : "TIDAK"));
        System.out.println("Prosedur Tindakan        : " + (getProsedurTindakan() == 1 ? "YA" : "TIDAK"));
        System.out.println("Reassessment Tanda Vital : " + (getReassessmentTandaVital() == 1 ? "YA" : "TIDAK"));
        System.out.println("Transfusi Darah          : " + "YA");

        System.out.println("\nD. TINDAKAN EMERGENSI");

        System.out.println("Resusitasi Cairan        : " + (getResusitasiCairan() == 1 ? "YA" : "TIDAK"));
        System.out.println("Intubasi / Jalan Napas   : " + (getIntubasiAtauManajemenJalanNapas() == 1 ? "YA" : "TIDAK"));
        System.out.println("Defibrilasi              : " + (getDefibrilasi() == 1 ? "YA" : "TIDAK"));
    }

    public void tampilkanForm(){
        if(permintaanPasien){
            tampilkanFormPasien();
        }else{
            tampilkanFormStok();
        }
    }

    private void tampilkanFormStok(){
        System.out.println("\n=== FORM PERMINTAAN DARAH ===");

        System.out.println("Nama Rumah Sakit        : " + getNamaRumahSakit());
        System.out.println("Alamat                  : " + getAlamat());
        System.out.println("Telepon                 : " + getTelepon());
        System.out.println("Unit Fasilitas Darah    : " + getfasilitasDarah().getNama());
        System.out.println("Tanggal Permintaan      : " + getTanggalPermintaan());
        System.out.println("Jam Permintaan          : " + getJamPermintaan());

        System.out.println("\nB. DATA PERMINTAAN DARAH");

        System.out.println("Jumlah Kantong          : " + getJumlahKantong());
        System.out.println("Rencana Transfusi       : " + getRencanaWaktuTransfusi());
        System.out.println("Golongan Darah          : " + getGolonganDarah());
        System.out.println("Rhesus Darah            : " + getRhesus());

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
    public int compareTo(Form other) {
        int thisWeight = this.hitungWeight();
        int otherWeight = other.hitungWeight();

        int thisBool = ((this.getPermintaanPasien())?1:0);
        int otherBool = ((other.getPermintaanPasien())?1:0);

        if (thisBool<otherBool){ //cek kalau stok
            return 1;
        }else if (otherBool<thisBool){
            return -1;
        }else if (thisBool==1 && otherBool==1){//dua-duanya pasien
            if (thisWeight < otherWeight) {
                return -1;
            } else if (thisWeight > otherWeight) {
                return 1;
            }
        }
        return 0;
    }

    public int getGcsTotal() {
        return gcsMata + gcsVerbal + gcsMotorik;
    }

    
    // #region Questions

    private void jenisKelamin(App app) {
        while (true) {
            System.out.println("Jenis Kelamin");
            System.out.println("1. LAKI LAKI");
            System.out.println("2. PEREMPUAN");
            System.out.print("Pilih: ");

            String input = app.getSc().nextLine();

            // Check if input is empty
            if (input.isEmpty()) {
                System.out.println("Error: Input tidak boleh kosong!");
                continue;
            }

            // Check if all characters are digits
            boolean isAllDigit = true;
            for (int i = 0; i < input.length(); i++) {
                if (!Character.isDigit(input.charAt(i))) {
                    isAllDigit = false;
                    break;
                }
            }

            if (!isAllDigit) {
                System.out.println("Error: Input harus berupa angka!");
                continue;
            }

            int pilihan = Integer.parseInt(input);

            if (pilihan == 1) {
                setJenisKelamin(JenisKelamin.LAKI_LAKI);
                break;
            } else if (pilihan == 2) {
                setJenisKelamin(JenisKelamin.PEREMPUAN);
                break;
            } else {
                System.out.println("Error: Pilihan tidak valid! Masukkan 1 atau 2.");
                continue;
            }
        }
    }

    private void jumlahKantong(App app) {
        while (true) {
            System.out.print("Jumlah Kantong: ");
            String input = app.getSc().nextLine();

            // Check if input is empty
            if (input.isEmpty()) {
                System.out.println("Error: Input tidak boleh kosong!");
                continue;
            }

            // Check if all characters are digits
            boolean isAllDigit = true;
            for (int i = 0; i < input.length(); i++) {
                if (!Character.isDigit(input.charAt(i))) {
                    isAllDigit = false;
                    break;
                }
            }

            if (!isAllDigit) {
                System.out.println("Error: Input harus berupa angka!");
                continue;
            }

            int jumlah = Integer.parseInt(input);

            if (jumlah <= 0) {
                System.out.println("Error: Jumlah kantong harus lebih dari 0!");
                continue;
            }

            setJumlahKantong(jumlah);
            break;
        }
    }

    private void rencanaWaktuTransfusi(App app) {
        while (true) {

            try {

                System.out.print(
                        "Rencana Waktu Transfusi "
                                + "(YYYY-MM-DD HH:MM): ");

                String inputWaktu = app.getSc().nextLine();

                DateTimeFormatter format = DateTimeFormatter.ofPattern(
                        "yyyy-MM-dd HH:mm");

                LocalDateTime waktuTransfusi = LocalDateTime.parse(
                        inputWaktu,
                        format);

                if (waktuTransfusi.isBefore(
                        LocalDateTime.now())) {

                    System.out.println(
                            "Waktu transfusi tidak boleh "
                                    + "sebelum waktu sekarang.");

                    continue;
                }

                setRencanaWaktuTransfusi(
                        waktuTransfusi);

                break;

            } catch (DateTimeParseException e) {

                System.out.println(
                        "Format salah.");

                System.out.println(
                        "Gunakan format:");

                System.out.println(
                        "YYYY-MM-DD HH:MM");
            }
        }
    }

    private void golonganDarah(App app) {
        while (true) {
            System.out.println("Golongan Darah");
            System.out.println("1. A");
            System.out.println("2. B");
            System.out.println("3. AB");
            System.out.println("4. O");
            System.out.print("Pilih: ");

            String input = app.getSc().nextLine();

            // Check if input is empty
            if (input.isEmpty()) {
                System.out.println("Error: Input tidak boleh kosong!");
                continue;
            }

            // Check if all characters are digits
            boolean isAllDigit = true;
            for (int i = 0; i < input.length(); i++) {
                if (!Character.isDigit(input.charAt(i))) {
                    isAllDigit = false;
                    break;
                }
            }

            if (!isAllDigit) {
                System.out.println("Error: Input harus berupa angka!");
                continue;
            }

            int pilihan = Integer.parseInt(input);

            switch (pilihan) {
                case 1:
                    setGolonganDarah(golDarahEnum.A);
                    break;
                case 2:
                    setGolonganDarah(golDarahEnum.B);
                    break;
                case 3:
                    setGolonganDarah(golDarahEnum.AB);
                    break;
                case 4:
                    setGolonganDarah(golDarahEnum.O);
                    break;
                default:
                    System.out.println("Error: Pilihan tidak valid! Masukkan angka 1-4.");
                    continue;
            }
            break;
        }
    }

    private void rhesus(App app) {
        while (true) {
            System.out.println("Rhesus");
            System.out.println("1. Positif (+)");
            System.out.println("2. Negatif (-)");
            System.out.print("Pilih: ");

            String input = app.getSc().nextLine();

            // Check if input is empty
            if (input.isEmpty()) {
                System.out.println("Error: Input tidak boleh kosong!");
                continue;
            }

            // Check if all characters are digits
            boolean isAllDigit = true;
            for (int i = 0; i < input.length(); i++) {
                if (!Character.isDigit(input.charAt(i))) {
                    isAllDigit = false;
                    break;
                }
            }

            if (!isAllDigit) {
                System.out.println("Error: Input harus berupa angka!");
                continue;
            }

            int pilihan = Integer.parseInt(input);

            if (pilihan == 1) {
                setRhesus(rhesusEnum.POSITIVE);
                break;
            } else if (pilihan == 2) {
                setRhesus(rhesusEnum.NEGATIVE);
                break;
            } else {
                System.out.println("Error: Pilihan tidak valid! Masukkan 1 atau 2.");
                continue;
            }
        }
    }

    public void usiaPasien(App app) {
        setUsiaPasien(inputInt(app, "Usia pasien dalam tahun.", 0, 150,
                "Usia harus di antara 0 sampai 150 tahun."));
    }

    public void tekananDarahSistolik(App app) {
        setTekananDarahSistolik(inputInt(app, "Tekanan darah sistolik pasien dalam mmHg.", 0, 300,
                "Tekanan darah sistolik harus di antara 0 sampai 300 mmHg."));
    }

    public void spo2(App app) {
        setSpo2(inputInt(app, "SpO2 pasien dalam persen.", 0, 100,
                "SpO2 harus di antara 0 sampai 100 persen."));
    }

    public void frekuensiNapas(App app) {
        setFrekuensiNapas(inputInt(app, "Frekuensi napas pasien per menit.", 0, 100,
                "Frekuensi napas harus di antara 0 sampai 100 per menit."));
    }

    public void frekuensiNadi(App app) {
        setFrekuensiNadi(inputInt(app, "Frekuensi nadi pasien per menit.", 0, 250,
                "Frekuensi nadi harus di antara 0 sampai 250 per menit."));
    }

    public void kadarHb(App app) {
        while (true) {

            System.out.print("Kadar Hb pasien (g/dL): ");

            String input = app.getSc().nextLine();

            if (input.isEmpty()) {
                System.out.println("Error: Input tidak boleh kosong!");
                continue;
            }

            try {

                double hb = Double.parseDouble(input);

                if (hb < 0 || hb > 25) {
                    System.out.println("Error: Nilai Hb 0-25!");
                    continue;
                }

                setKadarHb(hb);
                break;

            } catch (NumberFormatException e) {
                System.out.println("Error: Input harus berupa angka!");
            }
        }
    }

    public void suhuTubuh(App app) {
        setSuhuTubuh(inputDouble(app, "Suhu tubuh pasien dalam derajat Celsius.", 25.0, 45.0,
                "Suhu tubuh harus di antara 25.0 sampai 45.0 derajat Celsius."));
    }

    public void skalaNyeri(App app) {
        setSkalaNyeri(inputInt(app, "Skala nyeri pasien 0 sampai 10.", 0, 10,
                "Skala nyeri harus di antara 0 sampai 10."));
    }

    public void resusitasiCairan(App app) {
        setResusitasiCairan(inputYaTidak(app,
                "Apakah pasien membutuhkan tindakan resusitasi cairan, isi 1 untuk ya, 0 untuk tidak."));
    }

    public void intubasiAtauManajemenJalanNapas(App app) {
        setIntubasiAtauManajemenJalanNapas(inputYaTidak(app,
                "Apakah pasien membutuhkan intubasi atau manajemen jalan napas, isi 1 untuk ya, 0 untuk tidak."));
    }

    public void defibrilasi(App app) {
        setDefibrilasi(inputYaTidak(app, "Apakah pasien membutuhkan defibrilasi, isi 1 untuk ya, 0 untuk tidak."));
    }

    public void pemeriksaanLaboratorium(App app) {
        setPemeriksaanLaboratorium(inputYaTidak(app,
                "Apakah pasien membutuhkan pemeriksaan laboratorium, isi 1 untuk ya, 0 untuk tidak."));
    }

    public void pemeriksaanRadiologi(App app) {
        setPemeriksaanRadiologi(
                inputYaTidak(app, "Apakah pasien membutuhkan pemeriksaan radiologi, isi 1 untuk ya, 0 untuk tidak."));
    }

    public void konsultasiSpesialis(App app) {
        setKonsultasiSpesialis(
                inputYaTidak(app, "Apakah pasien membutuhkan konsultasi spesialis, isi 1 untuk ya, 0 untuk tidak."));
    }

    public void obatIVAtauIM(App app) {
        setObatIVAtauIM(
                inputYaTidak(app, "Apakah pasien membutuhkan obat IV atau IM, isi 1 untuk ya, 0 untuk tidak."));
    }

    public void nebulizer(App app) {
        setNebulizer(inputYaTidak(app, "Apakah pasien membutuhkan nebulizer, isi 1 untuk ya, 0 untuk tidak."));
    }

    public void prosedurTindakan(App app) {
        setProsedurTindakan(
                inputYaTidak(app, "Apakah pasien membutuhkan prosedur tindakan, isi 1 untuk ya, 0 untuk tidak."));
    }

    public void reassessmentTandaVital(App app) {
        setReassessmentTandaVital(inputYaTidak(app,
                "Apakah pasien membutuhkan reassessment tanda vital, isi 1 untuk ya, 0 untuk tidak."));
    }

    public void gcsMataLangsung(App app) {
        while (true) {
            System.out.println("Form pertanyaan GCS langsung, versi mata.");
            System.out.println("1. Apakah mata pasien terbuka spontan.");
            System.out.println("2. Apakah mata pasien terbuka saat dipanggil dengan suara.");
            System.out.println("3. Apakah mata pasien terbuka saat diberi rangsang nyeri.");
            System.out.println("4. Apakah mata pasien tidak membuka mata.");
            System.out.print("Pilih: ");

            String input = app.getSc().nextLine();

            if (input.isEmpty()) {
                System.out.println("Error: Input tidak boleh kosong!");
                continue;
            }

            boolean isAllDigit = true;
            for (int i = 0; i < input.length(); i++) {
                if (!Character.isDigit(input.charAt(i))) {
                    isAllDigit = false;
                    break;
                }
            }

            if (!isAllDigit) {
                System.out.println("Error: Input harus berupa angka!");
                continue;
            }

            int pilihan = Integer.parseInt(input);

            if (pilihan == 1) {
                setGcsMata(4);
                break;
            } else if (pilihan == 2) {
                setGcsMata(3);
                break;
            } else if (pilihan == 3) {
                setGcsMata(2);
                break;
            } else if (pilihan == 4) {
                setGcsMata(1);
                break;
            } else {
                System.out.println("Error: Pilihan tidak valid! Masukkan 1 sampai 4.");
            }
        }
    }

    public void gcsVerbalLangsung(App app) {
        while (true) {
            System.out.println("Form pertanyaan GCS langsung, versi verbal.");
            System.out.println("1. Apakah respon verbal pasien orientasi baik dan berbicara sesuai.");
            System.out.println("2. Apakah respon verbal pasien bingung.");
            System.out.println("3. Apakah pasien mengucapkan kata kata yang tidak tepat.");
            System.out.println("4. Apakah pasien hanya mengeluarkan suara tidak jelas.");
            System.out.println("5. Apakah pasien tidak memberikan respons verbal.");
            System.out.print("Pilih: ");

            String input = app.getSc().nextLine();

            if (input.isEmpty()) {
                System.out.println("Error: Input tidak boleh kosong!");
                continue;
            }

            boolean isAllDigit = true;
            for (int i = 0; i < input.length(); i++) {
                if (!Character.isDigit(input.charAt(i))) {
                    isAllDigit = false;
                    break;
                }
            }

            if (!isAllDigit) {
                System.out.println("Error: Input harus berupa angka!");
                continue;
            }

            int pilihan = Integer.parseInt(input);

            if (pilihan == 1) {
                setGcsVerbal(5);
                break;
            } else if (pilihan == 2) {
                setGcsVerbal(4);
                break;
            } else if (pilihan == 3) {
                setGcsVerbal(3);
                break;
            } else if (pilihan == 4) {
                setGcsVerbal(2);
                break;
            } else if (pilihan == 5) {
                setGcsVerbal(1);
                break;
            } else {
                System.out.println("Error: Pilihan tidak valid! Masukkan 1 sampai 5.");
            }
        }
    }

    public void gcsMotorikLangsung(App app) {
        while (true) {
            System.out.println("Form pertanyaan GCS langsung, versi motorik.");
            System.out.println("1. Apakah pasien mengikuti perintah.");
            System.out.println("2. Apakah pasien melokalisasi nyeri.");
            System.out.println("3. Apakah pasien menarik diri dari rangsang nyeri.");
            System.out.println("4. Apakah pasien menunjukkan fleksi abnormal.");
            System.out.println("5. Apakah pasien menunjukkan ekstensi abnormal.");
            System.out.println("6. Apakah pasien tidak memberikan respons motorik.");
            System.out.print("Pilih: ");

            String input = app.getSc().nextLine();

            if (input.isEmpty()) {
                System.out.println("Error: Input tidak boleh kosong!");
                continue;
            }

            boolean isAllDigit = true;
            for (int i = 0; i < input.length(); i++) {
                if (!Character.isDigit(input.charAt(i))) {
                    isAllDigit = false;
                    break;
                }
            }

            if (!isAllDigit) {
                System.out.println("Error: Input harus berupa angka!");
                continue;
            }

            int pilihan = Integer.parseInt(input);

            if (pilihan == 1) {
                setGcsMotorik(6);
                break;
            } else if (pilihan == 2) {
                setGcsMotorik(5);
                break;
            } else if (pilihan == 3) {
                setGcsMotorik(4);
                break;
            } else if (pilihan == 4) {
                setGcsMotorik(3);
                break;
            } else if (pilihan == 5) {
                setGcsMotorik(2);
                break;
            } else if (pilihan == 6) {
                setGcsMotorik(1);
                break;
            } else {
                System.out.println("Error: Pilihan tidak valid! Masukkan 1 sampai 6.");
            }
        }
    }

    // #endregion

    //#region Input

    private int inputInt(App app, String judul, int min, int max, String errorRange) {
        while (true) {
            System.out.println(judul);
            System.out.print("Pilih: ");

            String input = app.getSc().nextLine();

            if (input.isEmpty()) {
                System.out.println("Error: Input tidak boleh kosong!");
                continue;
            }

            boolean isAllDigit = true;
            for (int i = 0; i < input.length(); i++) {
                if (!Character.isDigit(input.charAt(i))) {
                    isAllDigit = false;
                    break;
                }
            }

            if (!isAllDigit) {
                System.out.println("Error: Input harus berupa angka!");
                continue;
            }

            int nilai = Integer.parseInt(input);

            if (nilai < min || nilai > max) {
                System.out.println("Error: " + errorRange);
                continue;
            }

            return nilai;
        }
    }

    private double inputDouble(App app, String judul, double min, double max, String errorRange) {
        while (true) {
            System.out.println(judul);
            System.out.print("Pilih: ");

            String input = app.getSc().nextLine();

            if (input.isEmpty()) {
                System.out.println("Error: Input tidak boleh kosong!");
                continue;
            }

            try {
                double nilai = Double.parseDouble(input);

                if (nilai < min || nilai > max) {
                    System.out.println("Error: " + errorRange);
                    continue;
                }

                return nilai;
            } catch (NumberFormatException e) {
                System.out.println("Error: Input harus berupa angka!");
            }
        }
    }

    private int inputYaTidak(App app, String pertanyaan) {
        while (true) {
            System.out.println(pertanyaan);
            System.out.println("1. YA");
            System.out.println("0. TIDAK");
            System.out.print("Pilih: ");

            String input = app.getSc().nextLine();

            if (input.isEmpty()) {
                System.out.println("Error: Input tidak boleh kosong!");
                continue;
            }

            boolean isAllDigit = true;
            for (int i = 0; i < input.length(); i++) {
                if (!Character.isDigit(input.charAt(i))) {
                    isAllDigit = false;
                    break;
                }
            }

            if (!isAllDigit) {
                System.out.println("Error: Input harus berupa angka!");
                continue;
            }

            int pilihan = Integer.parseInt(input);

            if (pilihan == 1 || pilihan == 0) {
                return pilihan;
            } else {
                System.out.println("Error: Pilihan tidak valid! Masukkan 1 atau 0.");
            }
        }
    }

    //#endregion

    // #region Getter Setter

        public boolean getPermintaanPasien() {
        return permintaanPasien;
    }

    public void setPermintaanPasien(boolean permintaanPasien) {
        this.permintaanPasien = permintaanPasien;
    }

    public BDRS getUnitBDRS() {
        return unitBDRS;
    }

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

    public BDRS getfasilitasDarah() {
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