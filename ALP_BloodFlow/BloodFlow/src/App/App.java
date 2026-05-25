package App;

import Enum.*;
import Model.DataUser;
import Model.RiwayatDonor;
import Request.Request;
import User.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class App {

    private DataUser dataUser = new DataUser();
    private User currentUser;
    private Scanner sc = new Scanner(System.in);

    // #region Getter Setter

    public DataUser getDataUser() {
        return dataUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public Scanner getSc() {
        return sc;
    }

    // #endregion

    public App() {
        onStartUp();
        while (true) {
            menuAwal();

            if (currentUser != null) {
                tampilkanMenuUtama(this);
            }
        }
    }

    // buat baca data dari txt dulu
    private void onStartUp() {
        dataUser.insertUser(new Admin("admin", "admin", "085887312500", "Budi"));
        dataUser.insertUser(new BDRS("Ciputra", "Hospital", "028317488396", "Made, Citraland", "Ciputra Hospital"));
        dataUser.insertUser(new BDRS("Siloam_1", "Hospital", "028317488396", "Tengah, Kota", "Siloam Hospital"));
        dataUser.insertUser(new UDD("PMI", "palang", "08303030", "Jl. Made me smile", "PMI-Made"));
        

        // #region form 1

        BDRS bdrs = (BDRS) dataUser.getDaftarUsernameUser().get("Ciputra");

        Request form = new Request(bdrs);

        form.setNamaRumahSakit(bdrs.getNama());

        form.setAlamat(bdrs.getAlamat());

        form.setTelepon(bdrs.getNoTelp());

        form.setUnitBDRS(bdrs);

        form.setTanggalPermintaan(LocalDate.now());

        form.setJamPermintaan(LocalTime.now());

        form.setNamaPasien("Bakrie");

        form.setGolonganDarah(golDarahEnum.A);

        form.setRhesus(rhesusEnum.POSITIVE);

        form.setTanggalLahirAtauUsia("67");

        form.setNomorRekamMedis("0");

        form.setJenisKelamin(JenisKelamin.LAKI_LAKI);

        form.setRuangPerawatan("A22");

        form.setDiagnosaKlinis("mengalami penyakit");

        form.setJumlahKantong(5);

        DateTimeFormatter format = DateTimeFormatter.ofPattern(
                "yyyy-MM-dd HH:mm");

        LocalDateTime waktuTransfusi = LocalDateTime.parse(
                "2026-05-30 17:00",
                format);

        form.setRencanaWaktuTransfusi(waktuTransfusi);

        form.setAlasanKlinis(AlasanKlinis.OPERASI_TERJADWAL);

        form.setNamaDokter("Dr. Tirta");

        form.setJabatan("Tertinggi");

        form.setNomorSIP("5515");

        Request.getLiveRequestList().add(form);

        // #endregion

        // #region form 2

        bdrs = (BDRS) dataUser.getDaftarUsernameUser().get("Siloam_1");

        form = new Request(bdrs);

        form.setNamaRumahSakit(bdrs.getNama());
        form.setAlamat(bdrs.getAlamat());
        form.setTelepon(bdrs.getNoTelp());
        form.setUnitBDRS(bdrs);
        form.setTanggalPermintaan(LocalDate.now());
        form.setJamPermintaan(LocalTime.now());

        form.setNamaPasien("Anita");
        form.setGolonganDarah(golDarahEnum.O);
        form.setRhesus(rhesusEnum.NEGATIVE);
        form.setTanggalLahirAtauUsia("45");
        form.setNomorRekamMedis("123456");
        form.setJenisKelamin(JenisKelamin.PEREMPUAN);
        form.setRuangPerawatan("B15");
        form.setDiagnosaKlinis("anemia berat dengan perdarahan");
        form.setJumlahKantong(3);

        waktuTransfusi = LocalDateTime.parse("2026-05-31 09:30", format);
        form.setRencanaWaktuTransfusi(waktuTransfusi);

        form.setAlasanKlinis(AlasanKlinis.ANEMIA_BERAT);
        form.setNamaDokter("Dr. Sari");
        form.setJabatan("Dokter Penanggung Jawab");
        form.setNomorSIP("6622");

        Request.getLiveRequestList().add(form);

        // #endregion

        // #region form 3

        bdrs = (BDRS) dataUser.getDaftarUsernameUser().get("Ciputra");

        form = new Request(bdrs);

        form.setNamaRumahSakit(bdrs.getNama());
        form.setAlamat(bdrs.getAlamat());
        form.setTelepon(bdrs.getNoTelp());
        form.setUnitBDRS(bdrs);
        form.setTanggalPermintaan(LocalDate.now());
        form.setJamPermintaan(LocalTime.now());

        form.setNamaPasien("Kevin");
        form.setGolonganDarah(golDarahEnum.B);
        form.setRhesus(rhesusEnum.POSITIVE);
        form.setTanggalLahirAtauUsia("28");
        form.setNomorRekamMedis("223344");
        form.setJenisKelamin(JenisKelamin.LAKI_LAKI);
        form.setRuangPerawatan("ICU-02");
        form.setDiagnosaKlinis("syok hemoragik akibat kecelakaan");
        form.setJumlahKantong(6);

        waktuTransfusi = LocalDateTime.parse(
                "2026-05-30 22:00",
                format);

        form.setRencanaWaktuTransfusi(waktuTransfusi);

        form.setAlasanKlinis(AlasanKlinis.SYOK_HEMORAGIK);
        form.setNamaDokter("Dr. Jonathan");
        form.setJabatan("Dokter Bedah");
        form.setNomorSIP("7788");

        //#endregion

        //#region form 4

        Request.getLiveRequestList().add(form);

        bdrs = (BDRS) dataUser.getDaftarUsernameUser().get("Siloam_1");

        form = new Request(bdrs);

        form.setNamaRumahSakit(bdrs.getNama());
        form.setAlamat(bdrs.getAlamat());
        form.setTelepon(bdrs.getNoTelp());
        form.setUnitBDRS(bdrs);
        form.setTanggalPermintaan(LocalDate.now());
        form.setJamPermintaan(LocalTime.now());

        form.setNamaPasien("Cynthia");
        form.setGolonganDarah(golDarahEnum.AB);
        form.setRhesus(rhesusEnum.POSITIVE);
        form.setTanggalLahirAtauUsia("33");
        form.setNomorRekamMedis("889900");
        form.setJenisKelamin(JenisKelamin.PEREMPUAN);
        form.setRuangPerawatan("C12");
        form.setDiagnosaKlinis("operasi caesar terjadwal");
        form.setJumlahKantong(2);

        waktuTransfusi = LocalDateTime.parse(
                "2026-06-01 07:00",
                format);

        form.setRencanaWaktuTransfusi(waktuTransfusi);

        form.setAlasanKlinis(AlasanKlinis.OPERASI_TERJADWAL);
        form.setNamaDokter("Dr. Felicia");
        form.setJabatan("Dokter Kandungan");
        form.setNomorSIP("9911");

        //#endregion

        //#region form 5

        Request.getLiveRequestList().add(form);

        bdrs = (BDRS) dataUser.getDaftarUsernameUser().get("Ciputra");

        form = new Request(bdrs);

        form.setNamaRumahSakit(bdrs.getNama());
        form.setAlamat(bdrs.getAlamat());
        form.setTelepon(bdrs.getNoTelp());
        form.setUnitBDRS(bdrs);
        form.setTanggalPermintaan(LocalDate.now());
        form.setJamPermintaan(LocalTime.now());

        form.setNamaPasien("Rudi");
        form.setGolonganDarah(golDarahEnum.A);
        form.setRhesus(rhesusEnum.NEGATIVE);
        form.setTanggalLahirAtauUsia("57");
        form.setNomorRekamMedis("445566");
        form.setJenisKelamin(JenisKelamin.LAKI_LAKI);
        form.setRuangPerawatan("ICU-08");
        form.setDiagnosaKlinis("perdarahan aktif pada saluran cerna");
        form.setJumlahKantong(5);

        waktuTransfusi = LocalDateTime.parse(
                "2026-05-30 18:15",
                format);

        form.setRencanaWaktuTransfusi(waktuTransfusi);

        form.setAlasanKlinis(AlasanKlinis.PERDARAHAN_AKTIF);
        form.setNamaDokter("Dr. Bima");
        form.setJabatan("Dokter Spesialis Penyakit Dalam");
        form.setNomorSIP("5566");

        Request.getLiveRequestList().add(form);
        
        //#endregion

        //#region form 6

        bdrs = (BDRS) dataUser.getDaftarUsernameUser().get("Siloam_1");

        form = new Request(bdrs);

        form.setNamaRumahSakit(bdrs.getNama());
        form.setAlamat(bdrs.getAlamat());
        form.setTelepon(bdrs.getNoTelp());
        form.setUnitBDRS(bdrs);
        form.setTanggalPermintaan(LocalDate.now());
        form.setJamPermintaan(LocalTime.now());

        form.setNamaPasien("Maria");
        form.setGolonganDarah(golDarahEnum.B);
        form.setRhesus(rhesusEnum.NEGATIVE);
        form.setTanggalLahirAtauUsia("61");
        form.setNomorRekamMedis("998877");
        form.setJenisKelamin(JenisKelamin.PEREMPUAN);
        form.setRuangPerawatan("D04");
        form.setDiagnosaKlinis("demam berdarah dengan trombosit rendah");
        form.setJumlahKantong(4);

        waktuTransfusi = LocalDateTime.parse(
                "2026-06-02 14:45",
                format);

        form.setRencanaWaktuTransfusi(waktuTransfusi);

        form.setAlasanKlinis(AlasanKlinis.DEMAM_BERDARAH);
        form.setNamaDokter("Dr. Clara");
        form.setJabatan("Dokter Umum");
        form.setNomorSIP("3344");

        Request.getLiveRequestList().add(form);

        // #endregion

    }

    public void menuAwal() {
        String input;

        System.out.println("""
                === BloodLink===
                1. Login
                2. Registrasi sebagai pendonor
                3. Exit
                """);
        System.out.print("Input: ");
        input = sc.next() + sc.nextLine();
        switch (input) {
            case "1":
                login();
                break;
            case "2":
                registrasi();
                break;
            case "3":
                System.exit(0);
                break;
            default:
                System.out.println("Invalid Input!!");
                menuAwal();
        }

    }

    private void login() {
        String username, password;
        boolean salah = false;
        System.out.println();

        do {
            salah = false;
            System.out.print("Username : ");
            username = sc.next() + sc.nextLine();
            System.out.print("Password : ");
            password = sc.next() + sc.nextLine();

            if (dataUser.getDaftarUsernameUser().containsKey(username)) {
                if (dataUser.getDaftarUsernameUser().isEmpty() ||
                        dataUser.getDaftarUsernameUser().get(username).getPassword().equals(password)) {
                    salah = false;
                } else {
                    System.out.println("Password salah");
                    salah = true;
                }
            } else {
                System.out.println("Username salah");
                salah = true;
            }
        } while (salah);

        System.out.println();

        currentUser = dataUser.getDaftarUsernameUser().get(username);
        System.out.println("Login berhasil sebagai: " + currentUser.getClass().getSimpleName());
        String input;
        do {
            System.out.println();
            currentUser.tampilkanMenuUtama(this);
        } while (true);
    }

    private void tampilkanMenuUtama(App app) {

        currentUser.tampilkanMenuUtama(app);

    }

    private void registrasi() {
        String username;

        do {
            System.out.print("Username: ");
            username = sc.next() + sc.nextLine();

            if (dataUser.getDaftarUser().contains(username)) {

                System.out.println("Username sudah digunakan");
            } else {
                break;
            }
        } while (true);

        String password;

        do {
            System.out.print("Password: ");
            password = sc.next() + sc.nextLine();

            System.out.print("Confirmed Password: ");
            String confirmedPassword = sc.next() + sc.nextLine();

            if (!password.equals(confirmedPassword)) {
                System.out.println("password and confirmed password are different!!");
            } else {
                break;
            }
        } while (true);

        String nama = "";

        do {
            System.out.print("Nama sesuai KTP: ");
            nama = sc.next() + sc.nextLine();

            if (username.equalsIgnoreCase("")) {

                System.out.println("Nama tidak boleh kosong");
            } else {
                break;
            }
        } while (true);

        golDarahEnum gol = null;
        String golDarah = "z";

        do {
            System.out.print("Golongan Darah: ");
            golDarah = sc.next() + sc.nextLine();
            try {
                gol = golDarahEnum.valueOf(
                        golDarah.toUpperCase());
            } catch (Exception e) {
                System.out.println("Golongan darah tidak valid!!");
            }
        } while (gol == null);

        String rhesusInput = null;
        rhesusEnum rhesus;

        do {
            System.out.print("Rhesus (+/-): ");
            rhesusInput = sc.next() + sc.nextLine();

            if (rhesusInput.equals("+")) {
                rhesus = rhesusEnum.POSITIVE;
                break;
            } else if (rhesusInput.equals("-")) {
                rhesus = rhesusEnum.NEGATIVE;
                break;
            } else {
                System.out.println("Rhesus tidak valid!!");
            }
        } while (true);

        String noTelp = "";
        boolean nonDigit = false;
        do {
            nonDigit = false;
            System.out.print("Nomor HP: ");
            noTelp = sc.next() + sc.nextLine();
            // digit check
            for (char c : noTelp.toCharArray()) {
                nonDigit = (Character.isDigit(c)) ? nonDigit : true;
            }
            if (noTelp.equals("") || nonDigit) {
                System.out.println("isi nomor hp dengan angka only!!");
            } else {
                break;
            }
        } while (true);

        dataUser.insertUser(new Pendonor(username, password, noTelp, gol, rhesus, nama));
        currentUser = dataUser.getDaftarUsernameUser().get(username);

        //dummy riwayat

        Pendonor pendonorBaru = (Pendonor) dataUser.getDaftarUsernameUser().get(username);
        pendonorBaru.addRiwayatDonor(new RiwayatDonor("20 Januari 2026", "KD001", "PMI Surabaya"));
        pendonorBaru.addRiwayatDonor(new RiwayatDonor("20 Mei  2026", "KD006", "PMI Surabaya"));


        System.out.println("Registrasi berhasil, silakan login");
        menuAwal();
    }

    private void menuPendonor() {
        tampilkanMenuUtama(this);
    }

    private void exit() {
        save();
        System.exit(0);
    }

    private void save() {

    }
}
