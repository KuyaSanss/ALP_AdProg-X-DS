package User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import App.App;
import Enum.AlasanKlinis;
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
        System.out.println("0. Log Out");
        System.out.print("input: ");
        String input = app.getSc().next() + app.getSc().nextLine();
        System.out.println();
        switch (input) {
            case "0":
                app.menuAwal();
                break;
            case "1":
                makeRequest(app);
                break;
            case "2":

                break;
            case "3":

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

        System.out.print("Tanggal Lahir atau Usia: ");
        form.setTanggalLahirAtauUsia(app.getSc().nextLine());

        System.out.print("Nomor Rekam Medis: ");
        form.setNomorRekamMedis(app.getSc().nextLine());

        jenisKelamin(app, form);

        System.out.print("Ruang Perawatan: ");
        form.setRuangPerawatan(app.getSc().nextLine());

        System.out.print("Diagnosa Klinis: ");
        form.setDiagnosaKlinis(app.getSc().nextLine());

        System.out.println("\nB. DATA PERMINTAAN DARAH");

        jumlahKantong(app, form);

        rencanaWaktuTransfusi(app, form);

        alasanKlinis(app, form);

        System.out.println("\nC. DATA DOKTER PEMINTA");

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

    private void jenisKelamin(App app, Request form) {
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

    private void jumlahKantong(App app, Request form) {
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

    private void alasanKlinis(App app, Request form) {
        while (true) {

            System.out.println("Alasan Klinis Permintaan");

            System.out.println("1. PERDARAHAN AKTIF");
            System.out.println("2. OPERASI DARURAT");
            System.out.println("3. SYOK HEMORAGIK");
            System.out.println("4. ICU");
            System.out.println("5. ANEMIA BERAT");
            System.out.println("6. TRAUMA");
            System.out.println("7. OPERASI TERJADWAL");
            System.out.println("8. DEMAM BERDARAH");
            System.out.println("9. ANEMIA RINGAN");
            System.out.println("10. STANDBY");

            System.out.print("Pilih: ");

            String pilihan = app.getSc().nextLine();

            switch (pilihan) {

                case "1":
                    form.setAlasanKlinis(
                            AlasanKlinis.PERDARAHAN_AKTIF);
                    return;

                case "2":
                    form.setAlasanKlinis(
                            AlasanKlinis.OPERASI_DARURAT);
                    return;

                case "3":
                    form.setAlasanKlinis(
                            AlasanKlinis.SYOK_HEMORAGIK);
                    return;

                case "4":
                    form.setAlasanKlinis(
                            AlasanKlinis.ICU);
                    return;

                case "5":
                    form.setAlasanKlinis(
                            AlasanKlinis.ANEMIA_BERAT);
                    return;

                case "6":
                    form.setAlasanKlinis(
                            AlasanKlinis.TRAUMA);
                    return;

                case "7":
                    form.setAlasanKlinis(
                            AlasanKlinis.OPERASI_TERJADWAL);
                    return;

                case "8":
                    form.setAlasanKlinis(
                            AlasanKlinis.DEMAM_BERDARAH);
                    return;

                case "9":
                    form.setAlasanKlinis(
                            AlasanKlinis.ANEMIA_RINGAN);
                    return;

                case "10":
                    form.setAlasanKlinis(
                            AlasanKlinis.STANDBY);
                    return;

                default:

                    System.out.println(
                            "Pilihan tidak valid.");
            }
        }
    }

    private void rencanaWaktuTransfusi(App app, Request form) {
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

    private void golonganDarah(App app, Request form) {
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

    private void rhesus(App app, Request form) {
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
    // #endregion
}
