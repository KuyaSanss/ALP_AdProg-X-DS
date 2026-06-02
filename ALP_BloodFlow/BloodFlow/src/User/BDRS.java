package User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import App.App;
import Enum.PrioritasESI;
import Enum.JenisKelamin;
import Enum.golDarahEnum;
import Enum.rhesusEnum;
import Request.Request;

public class BDRS extends User {
    private String alamat;

    public BDRS(String username, String password, String noTelp, String alamat, String nama) {
        super(username, password, noTelp, nama);
        this.alamat = alamat;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @Override
    public void tampilkanMenuUtama(App app) {
        System.out.println("=== MENU UTAMA ===");
        System.out.println("1. Buat permintaan darah");
        System.out.println("2. Log Out");
        System.out.print("input: ");
        String input = app.getSc().next() + app.getSc().nextLine();
        System.out.println();
        switch (input) {
            case "1":
                makeRequest(app);
                break;
            case "2":
                app.menuAwal();
                break;
            default:
                System.out.println("Invalid Input!!");
                tampilkanMenuUtama(app);
        }
    }

    // #region Form

        public void makeRequest(App app) {

        Request form = new Request(this);

        System.out.println("=== INPUT FORM PERMINTAAN DARAH ===");

        form.setNamaRumahSakit(nama);
        form.setAlamat(alamat);
        form.setTelepon(noTelp);
        form.setUnitBDRS(this);
        form.setTanggalPermintaan(LocalDate.now());
        form.setJamPermintaan(LocalTime.now());

        System.out.println("\nA. DATA PASIEN");

        System.out.print("Nama Pasien: ");
        form.setNamaPasien(app.getSc().nextLine());

        golonganDarah(app, form);
        rhesus(app, form);

        System.out.print("Nomor Rekam Medis: ");
        form.setNomorRekamMedis(app.getSc().nextLine());

        jenisKelamin(app, form);

        System.out.print("Ruang Perawatan: ");
        form.setRuangPerawatan(app.getSc().nextLine());

        System.out.print("Diagnosa Klinis: ");
        form.setDiagnosaKlinis(app.getSc().nextLine());

        System.out.println("\nB. DATA KLINIS PASIEN");

        usiaPasien(app, form);
        tekananDarahSistolik(app, form);
        gcsMataLangsung(app, form);
        gcsVerbalLangsung(app, form);
        gcsMotorikLangsung(app, form);
        spo2(app, form);
        frekuensiNapas(app, form);
        frekuensiNadi(app, form);
        suhuTubuh(app, form);
        skalaNyeri(app, form);

        System.out.println("\nC. KEBUTUHAN TINDAKAN / RESOURCE MEDIS");

        resusitasiCairan(app, form);
        intubasiAtauManajemenJalanNapas(app, form);
        defibrilasi(app, form);
        pemeriksaanLaboratorium(app, form);
        pemeriksaanRadiologi(app, form);
        konsultasiSpesialis(app, form);
        obatIVAtauIM(app, form);
        nebulizer(app, form);
        prosedurTindakan(app, form);
        reassessmentTandaVital(app, form);

        System.out.println("\nD. DATA PERMINTAAN DARAH");

        jumlahKantong(app, form);
        rencanaWaktuTransfusi(app, form);

        System.out.println("\nE. DATA DOKTER PEMINTA");

        System.out.print("Nama Dokter: ");
        form.setNamaDokter(app.getSc().nextLine());

        System.out.print("Jabatan: ");
        form.setJabatan(app.getSc().nextLine());

        System.out.print("Nomor SIP: ");
        form.setNomorSIP(app.getSc().nextLine());

        Request.getLiveRequestList().add(form);

        System.out.println("permintaan darah berhasil terkirim");
        tampilkanMenuUtama(app);
    }

    public void jenisKelamin(App app, Request form) {
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
                form.setJenisKelamin(JenisKelamin.LAKI_LAKI);
                break;
            } else if (pilihan == 2) {
                form.setJenisKelamin(JenisKelamin.PEREMPUAN);
                break;
            } else {
                System.out.println("Error: Pilihan tidak valid! Masukkan 1 atau 2.");
                continue;
            }
        }
    }

    public void jumlahKantong(App app, Request form) {
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

            form.setJumlahKantong(jumlah);
            break;
        }
    }

    public void rencanaWaktuTransfusi(App app, Request form) {
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

                form.setRencanaWaktuTransfusi(
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

        public void jumlahJenisResourceMedis(App app, Request form) {
        int totalResource = 0;

        System.out.println("Jumlah resource medis dihitung dari jawaban YA pada daftar berikut.");
        System.out.println("Resource medis yang dihitung:");
        System.out.println("1. Pemeriksaan laboratorium");
        System.out.println("2. Pemeriksaan radiologi (X-Ray / CT Scan / MRI / USG)");
        System.out.println("3. Konsultasi spesialis");
        System.out.println("4. Obat IV atau IM");
        System.out.println("5. Nebulizer");
        System.out.println("6. Prosedur tindakan (jahit luka, pasang kateter, dll)");
        System.out.println("7. Reassessment tanda vital berulang");
        System.out.println();

        form.setPemeriksaanLaboratorium(inputYaTidak(app, "Apakah pasien membutuhkan pemeriksaan laboratorium, isi 1 untuk ya, 0 untuk tidak."));
        totalResource += form.getPemeriksaanLaboratorium();

        form.setPemeriksaanRadiologi(inputYaTidak(app, "Apakah pasien membutuhkan pemeriksaan radiologi, isi 1 untuk ya, 0 untuk tidak."));
        totalResource += form.getPemeriksaanRadiologi();

        form.setKonsultasiSpesialis(inputYaTidak(app, "Apakah pasien membutuhkan konsultasi spesialis, isi 1 untuk ya, 0 untuk tidak."));
        totalResource += form.getKonsultasiSpesialis();

        form.setObatIVAtauIM(inputYaTidak(app, "Apakah pasien membutuhkan obat IV atau IM, isi 1 untuk ya, 0 untuk tidak."));
        totalResource += form.getObatIVAtauIM();

        form.setNebulizer(inputYaTidak(app, "Apakah pasien membutuhkan nebulizer, isi 1 untuk ya, 0 untuk tidak."));
        totalResource += form.getNebulizer();

        form.setProsedurTindakan(inputYaTidak(app, "Apakah pasien membutuhkan prosedur tindakan, isi 1 untuk ya, 0 untuk tidak."));
        totalResource += form.getProsedurTindakan();

        form.setReassessmentTandaVital(inputYaTidak(app, "Apakah pasien membutuhkan reassessment tanda vital berulang, isi 1 untuk ya, 0 untuk tidak."));
        totalResource += form.getReassessmentTandaVital();

        totalResource += 1;//transfusi darah

        form.setJumlahJenisResourceMedis(totalResource);

        System.out.println();

        System.out.println("Tindakan emergensi (tidak dihitung ke jumlah resource):");
        form.setResusitasiCairan(inputYaTidak(app, "Apakah pasien membutuhkan tindakan resusitasi cairan, isi 1 untuk ya, 0 untuk tidak."));
        form.setIntubasiAtauManajemenJalanNapas(inputYaTidak(app, "Apakah pasien membutuhkan intubasi atau manajemen jalan napas, isi 1 untuk ya, 0 untuk tidak."));
        form.setDefibrilasi(inputYaTidak(app, "Apakah pasien membutuhkan defibrilasi, isi 1 untuk ya, 0 untuk tidak."));

        System.out.println();
        System.out.println("Total resource medis yang dipilih: " + totalResource);
    }

    public void golonganDarah(App app, Request form) {
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
                    form.setGolonganDarah(golDarahEnum.A);
                    break;
                case 2:
                    form.setGolonganDarah(golDarahEnum.B);
                    break;
                case 3:
                    form.setGolonganDarah(golDarahEnum.AB);
                    break;
                case 4:
                    form.setGolonganDarah(golDarahEnum.O);
                    break;
                default:
                    System.out.println("Error: Pilihan tidak valid! Masukkan angka 1-4.");
                    continue;
            }
            break;
        }
    }

    public void rhesus(App app, Request form) {
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
                form.setRhesus(rhesusEnum.POSITIVE);
                break;
            } else if (pilihan == 2) {
                form.setRhesus(rhesusEnum.NEGATIVE);
                break;
            } else {
                System.out.println("Error: Pilihan tidak valid! Masukkan 1 atau 2.");
                continue;
            }
        }
    }

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

    public void usiaPasien(App app, Request form) {
        form.setUsiaPasien(inputInt(app, "Usia pasien dalam tahun.", 0, 150,
                "Usia harus di antara 0 sampai 150 tahun."));
    }

    public void tekananDarahSistolik(App app, Request form) {
        form.setTekananDarahSistolik(inputInt(app, "Tekanan darah sistolik pasien dalam mmHg.", 0, 300,
                "Tekanan darah sistolik harus di antara 0 sampai 300 mmHg."));
    }

    public void spo2(App app, Request form) {
        form.setSpo2(inputInt(app, "SpO2 pasien dalam persen.", 0, 100,
                "SpO2 harus di antara 0 sampai 100 persen."));
    }

    public void frekuensiNapas(App app, Request form) {
        form.setFrekuensiNapas(inputInt(app, "Frekuensi napas pasien per menit.", 0, 100,
                "Frekuensi napas harus di antara 0 sampai 100 per menit."));
    }

    public void frekuensiNadi(App app, Request form) {
        form.setFrekuensiNadi(inputInt(app, "Frekuensi nadi pasien per menit.", 0, 250,
                "Frekuensi nadi harus di antara 0 sampai 250 per menit."));
    }

    public void suhuTubuh(App app, Request form) {
        form.setSuhuTubuh(inputDouble(app, "Suhu tubuh pasien dalam derajat Celsius.", 25.0, 45.0,
                "Suhu tubuh harus di antara 25.0 sampai 45.0 derajat Celsius."));
    }

    public void skalaNyeri(App app, Request form) {
        form.setSkalaNyeri(inputInt(app, "Skala nyeri pasien 0 sampai 10.", 0, 10,
                "Skala nyeri harus di antara 0 sampai 10."));
    }


    public void resusitasiCairan(App app, Request form) {
        form.setResusitasiCairan(inputYaTidak(app, "Apakah pasien membutuhkan tindakan resusitasi cairan, isi 1 untuk ya, 0 untuk tidak."));
    }

    public void intubasiAtauManajemenJalanNapas(App app, Request form) {
        form.setIntubasiAtauManajemenJalanNapas(inputYaTidak(app, "Apakah pasien membutuhkan intubasi atau manajemen jalan napas, isi 1 untuk ya, 0 untuk tidak."));
    }

    public void defibrilasi(App app, Request form) {
        form.setDefibrilasi(inputYaTidak(app, "Apakah pasien membutuhkan defibrilasi, isi 1 untuk ya, 0 untuk tidak."));
    }

    public void pemeriksaanLaboratorium(App app, Request form) {
        form.setPemeriksaanLaboratorium(inputYaTidak(app, "Apakah pasien membutuhkan pemeriksaan laboratorium, isi 1 untuk ya, 0 untuk tidak."));
    }

    public void pemeriksaanRadiologi(App app, Request form) {
        form.setPemeriksaanRadiologi(inputYaTidak(app, "Apakah pasien membutuhkan pemeriksaan radiologi, isi 1 untuk ya, 0 untuk tidak."));
    }

    public void konsultasiSpesialis(App app, Request form) {
        form.setKonsultasiSpesialis(inputYaTidak(app, "Apakah pasien membutuhkan konsultasi spesialis, isi 1 untuk ya, 0 untuk tidak."));
    }

    public void obatIVAtauIM(App app, Request form) {
        form.setObatIVAtauIM(inputYaTidak(app, "Apakah pasien membutuhkan obat IV atau IM, isi 1 untuk ya, 0 untuk tidak."));
    }

    public void nebulizer(App app, Request form) {
        form.setNebulizer(inputYaTidak(app, "Apakah pasien membutuhkan nebulizer, isi 1 untuk ya, 0 untuk tidak."));
    }

    public void prosedurTindakan(App app, Request form) {
        form.setProsedurTindakan(inputYaTidak(app, "Apakah pasien membutuhkan prosedur tindakan, isi 1 untuk ya, 0 untuk tidak."));
    }

    public void reassessmentTandaVital(App app, Request form) {
        form.setReassessmentTandaVital(inputYaTidak(app, "Apakah pasien membutuhkan reassessment tanda vital, isi 1 untuk ya, 0 untuk tidak."));
    }

    public void gcsMataLangsung(App app, Request form) {
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
                form.setGcsMata(4);
                break;
            } else if (pilihan == 2) {
                form.setGcsMata(3);
                break;
            } else if (pilihan == 3) {
                form.setGcsMata(2);
                break;
            } else if (pilihan == 4) {
                form.setGcsMata(1);
                break;
            } else {
                System.out.println("Error: Pilihan tidak valid! Masukkan 1 sampai 4.");
            }
        }
    }

    public void gcsVerbalLangsung(App app, Request form) {
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
                form.setGcsVerbal(5);
                break;
            } else if (pilihan == 2) {
                form.setGcsVerbal(4);
                break;
            } else if (pilihan == 3) {
                form.setGcsVerbal(3);
                break;
            } else if (pilihan == 4) {
                form.setGcsVerbal(2);
                break;
            } else if (pilihan == 5) {
                form.setGcsVerbal(1);
                break;
            } else {
                System.out.println("Error: Pilihan tidak valid! Masukkan 1 sampai 5.");
            }
        }
    }

    public void gcsMotorikLangsung(App app, Request form) {
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
                form.setGcsMotorik(6);
                break;
            } else if (pilihan == 2) {
                form.setGcsMotorik(5);
                break;
            } else if (pilihan == 3) {
                form.setGcsMotorik(4);
                break;
            } else if (pilihan == 4) {
                form.setGcsMotorik(3);
                break;
            } else if (pilihan == 5) {
                form.setGcsMotorik(2);
                break;
            } else if (pilihan == 6) {
                form.setGcsMotorik(1);
                break;
            } else {
                System.out.println("Error: Pilihan tidak valid! Masukkan 1 sampai 6.");
            }
        }
    }
    
    
    
    // #endregion
}