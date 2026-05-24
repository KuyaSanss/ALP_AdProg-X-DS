package User;

import java.time.LocalDate;
import java.time.LocalTime;

import App.App;
import Enum.JenisKelamin;
import Enum.KomponenDarah;
import Request.Request;

public class BDRS extends User {
    private String alamat;

    public BDRS(String username, String password, String noTelp, String alamat) {
        super(username, password, noTelp);
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
        String input = app.getSc().next() + app.getSc().nextLine();
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

    public void makeRequest(App app) {

        Request form = new Request(this);

        System.out.println("=== INPUT FORM PERMINTAAN DARAH ===");

        System.out.print("Nama Rumah Sakit: ");
        form.setNamaRumahSakit(app.getSc().nextLine());

        System.out.print("Alamat: ");
        form.setAlamat(app.getSc().nextLine());

        System.out.print("Telepon: ");
        form.setTelepon(app.getSc().nextLine());

        System.out.print("Unit BDRS: ");
        form.setUnitBDRS(app.getSc().nextLine());

        form.setTanggalPermintaan(LocalDate.now());

        form.setJamPermintaan(LocalTime.now());

        System.out.println("\nA. DATA PASIEN");

        System.out.print("Nama Pasien: ");
        form.setNamaPasien(app.getSc().nextLine());

        System.out.print("Tanggal Lahir atau Usia: ");
        form.setTanggalLahirAtauUsia(app.getSc().nextLine());

        System.out.print("Nomor Rekam Medis: ");
        form.setNomorRekamMedis(app.getSc().nextLine());

        // Error checking for Jenis Kelamin
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

        System.out.print("Ruang Perawatan: ");
        form.setRuangPerawatan(app.getSc().nextLine());

        System.out.print("Diagnosa Klinis: ");
        form.setDiagnosaKlinis(app.getSc().nextLine());

        System.out.println("\nB. DATA PERMINTAAN DARAH");

        // Error checking for Komponen Darah
        while (true) {
            System.out.println("Komponen Darah");
            System.out.println("1. WB");
            System.out.println("2. PRC");
            System.out.println("3. FFP");
            System.out.println("4. TC");
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
                    form.setKomponenDarah(KomponenDarah.WB);
                    break;
                case 2:
                    form.setKomponenDarah(KomponenDarah.PRC);
                    break;
                case 3:
                    form.setKomponenDarah(KomponenDarah.FFP);
                    break;
                case 4:
                    form.setKomponenDarah(KomponenDarah.TC);
                    break;
                default:
                    System.out.println("Error: Pilihan tidak valid! Masukkan angka 1-5.");
                    continue;
            }
            break;
        }

        // Error checking for Jumlah Kantong
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

        System.out.print("Rencana Waktu Transfusi: ");
        form.setRencanaWaktuTransfusi(app.getSc().nextLine());

        System.out.print("Alasan Klinis Permintaan: ");
        form.setAlasanKlinis(app.getSc().nextLine());

        System.out.println("\nC. DATA DOKTER PEMINTA");

        System.out.print("Nama Dokter: ");
        form.setNamaDokter(app.getSc().nextLine());

        System.out.print("Jabatan: ");
        form.setJabatan(app.getSc().nextLine());

        System.out.print("Nomor SIP: ");
        form.setNomorSIP(app.getSc().nextLine());

    }

}
