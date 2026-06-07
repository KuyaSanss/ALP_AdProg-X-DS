package App;

import Enum.*;
import Model.DataUser;
import Model.RiwayatDonor;
import Request.Form;
import User.*;
import Model.Notification;
import Request.Request;

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
        dataUser.insertUser(new Admin(this,"admin", "admin", "085887312500", "Budi",Provinsi.JAWA_TIMUR,WilayahIndonesia.KOTA_SURABAYA));
        dataUser.insertUser(new BDRS(this,"Ciputra", "Hospital", "028317488396", "Made, Citraland", "Ciputra Hospital",Provinsi.JAWA_TIMUR,WilayahIndonesia.KOTA_SURABAYA));
        dataUser.insertUser(new BDRS(this,"Siloam_1", "Hospital", "028317488396", "Tengah, Kota", "Siloam Hospital",Provinsi.JAWA_TIMUR,WilayahIndonesia.KOTA_SURABAYA));
        dataUser.insertUser(new UDD(this,"PMI", "palang", "08303030", "Jl. Made me smile", "PMI-Made",Provinsi.JAWA_TIMUR,WilayahIndonesia.KOTA_SURABAYA));

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // #region form 1
        // Bakrie - A+ - "mengalami penyakit" - kondisi sedang, ESI 2

        BDRS bdrs = (BDRS) dataUser.getDaftarUsernameUser().get("Ciputra");
        Request request = new Request(bdrs);
        Form form = new Form(bdrs);

        form.setNamaRumahSakit(bdrs.getNama());
        form.setAlamat(bdrs.getAlamat());
        form.setTelepon(bdrs.getNoTelp());
        form.setUnitBDRS(bdrs);
        form.setTanggalPermintaan(LocalDate.now());
        form.setJamPermintaan(LocalTime.now());

        form.setNamaPasien("Bakrie");
        form.setGolonganDarah(golDarahEnum.A);
        form.setRhesus(rhesusEnum.POSITIVE);
        form.setNomorRekamMedis("0");
        form.setJenisKelamin(JenisKelamin.LAKI_LAKI);
        form.setRuangPerawatan("A22");
        form.setDiagnosaKlinis("mengalami penyakit");
        form.setJumlahKantong(5);
        form.setRencanaWaktuTransfusi(LocalDateTime.parse("2026-05-30 17:00", format));
        form.setNamaDokter("Dr. Tirta");
        form.setJabatan("Tertinggi");
        form.setNomorSIP("5515");

        // Data klinis - kondisi sedang, sadar penuh
        form.setUsiaPasien(45);
        form.setKadarHb(7);
        form.setTekananDarahSistolik(110);
        form.setGcsMata(4); // mata terbuka spontan
        form.setGcsVerbal(5); // orientasi baik
        form.setGcsMotorik(6); // mengikuti perintah → GCS 15
        form.setSpo2(96);
        form.setFrekuensiNapas(20);
        form.setFrekuensiNadi(88);
        form.setSuhuTubuh(37.2);
        form.setSkalaNyeri(4);

        // Tindakan emergensi
        form.setResusitasiCairan(0);
        form.setIntubasiAtauManajemenJalanNapas(0);
        form.setDefibrilasi(0);

        // Resource medis (lab + obatIV + transfusi = 3)
        form.setPemeriksaanLaboratorium(1);
        form.setPemeriksaanRadiologi(0);
        form.setKonsultasiSpesialis(0);
        form.setObatIVAtauIM(1);
        form.setNebulizer(0);
        form.setProsedurTindakan(0);
        form.setReassessmentTandaVital(0);
        form.setJumlahJenisResourceMedis(3); // lab + obatIV + transfusi

        request.setForm(form);

        // #endregion

        // #region form 2
        // Anita - O- - "anemia berat dengan perdarahan" - kritis, ESI 1

        bdrs = (BDRS) dataUser.getDaftarUsernameUser().get("Siloam_1");
        request = new Request(bdrs);
        form = new Form(bdrs);

        form.setNamaRumahSakit(bdrs.getNama());
        form.setAlamat(bdrs.getAlamat());
        form.setTelepon(bdrs.getNoTelp());
        form.setUnitBDRS(bdrs);
        form.setTanggalPermintaan(LocalDate.now());
        form.setJamPermintaan(LocalTime.now());

        form.setNamaPasien("Anita");
        form.setGolonganDarah(golDarahEnum.O);
        form.setRhesus(rhesusEnum.NEGATIVE);
        form.setNomorRekamMedis("123456");
        form.setJenisKelamin(JenisKelamin.PEREMPUAN);
        form.setRuangPerawatan("B15");
        form.setDiagnosaKlinis("anemia berat dengan perdarahan");
        form.setJumlahKantong(3);
        form.setRencanaWaktuTransfusi(LocalDateTime.parse("2026-05-31 09:30", format));
        form.setNamaDokter("Dr. Sari");
        form.setJabatan("Dokter Penanggung Jawab");
        form.setNomorSIP("6622");

        // Data klinis - perdarahan aktif, semi-sadar
        form.setUsiaPasien(32);
        form.setKadarHb(7);
        form.setTekananDarahSistolik(95);
        form.setGcsMata(3); // terbuka saat dipanggil
        form.setGcsVerbal(4); // bingung
        form.setGcsMotorik(5); // melokalisasi nyeri → GCS 12
        form.setSpo2(92);
        form.setFrekuensiNapas(24);
        form.setFrekuensiNadi(118);
        form.setSuhuTubuh(36.8);
        form.setSkalaNyeri(7);

        // Tindakan emergensi - butuh resusitasi cairan
        form.setResusitasiCairan(1);
        form.setIntubasiAtauManajemenJalanNapas(0);
        form.setDefibrilasi(0);

        // Resource medis (lab + radiologi + konsultasi + obatIV + reassessment +
        // transfusi = 6)
        form.setPemeriksaanLaboratorium(1);
        form.setPemeriksaanRadiologi(1);
        form.setKonsultasiSpesialis(1);
        form.setObatIVAtauIM(1);
        form.setNebulizer(0);
        form.setProsedurTindakan(0);
        form.setReassessmentTandaVital(1);
        form.setJumlahJenisResourceMedis(6); // lab + rad + konsultasi + obatIV + reassessment + transfusi

        request.setForm(form);

        // #endregion

        // #region form 3
        // Kevin - B+ - "syok hemoragik akibat kecelakaan" - sangat kritis, ESI 1

        bdrs = (BDRS) dataUser.getDaftarUsernameUser().get("Ciputra");
        request = new Request(bdrs);
        form = new Form(bdrs);

        form.setNamaRumahSakit(bdrs.getNama());
        form.setAlamat(bdrs.getAlamat());
        form.setTelepon(bdrs.getNoTelp());
        form.setUnitBDRS(bdrs);
        form.setTanggalPermintaan(LocalDate.now());
        form.setJamPermintaan(LocalTime.now());

        form.setNamaPasien("Kevin");
        form.setGolonganDarah(golDarahEnum.B);
        form.setRhesus(rhesusEnum.POSITIVE);
        form.setNomorRekamMedis("223344");
        form.setJenisKelamin(JenisKelamin.LAKI_LAKI);
        form.setRuangPerawatan("ICU-02");
        form.setDiagnosaKlinis("syok hemoragik akibat kecelakaan");
        form.setJumlahKantong(6);
        form.setRencanaWaktuTransfusi(LocalDateTime.parse("2026-05-30 22:00", format));
        form.setNamaDokter("Dr. Jonathan");
        form.setJabatan("Dokter Bedah");
        form.setNomorSIP("7788");

        // Data klinis - syok, tidak sadar
        form.setUsiaPasien(28);
        form.setKadarHb(7);
        form.setTekananDarahSistolik(72);
        form.setGcsMata(2); // terbuka saat rangsang nyeri
        form.setGcsVerbal(3); // kata-kata tidak tepat
        form.setGcsMotorik(3); // fleksi abnormal → GCS 8
        form.setSpo2(87);
        form.setFrekuensiNapas(30);
        form.setFrekuensiNadi(145);
        form.setSuhuTubuh(36.0);
        form.setSkalaNyeri(9);

        // Tindakan emergensi - resusitasi + intubasi
        form.setResusitasiCairan(1);
        form.setIntubasiAtauManajemenJalanNapas(1);
        form.setDefibrilasi(0);

        // Resource medis (lab + radiologi + konsultasi + obatIV + prosedur +
        // reassessment + transfusi = 7)
        form.setPemeriksaanLaboratorium(1);
        form.setPemeriksaanRadiologi(1);
        form.setKonsultasiSpesialis(1);
        form.setObatIVAtauIM(1);
        form.setNebulizer(0);
        form.setProsedurTindakan(1);
        form.setReassessmentTandaVital(1);
        form.setJumlahJenisResourceMedis(7); // lab + rad + konsultasi + obatIV + prosedur + reassessment + transfusi

        request.setForm(form);

        // #endregion

        // #region form 4
        // Cynthia - AB+ - "operasi caesar terjadwal" - elektif, stabil, ESI 2

        bdrs = (BDRS) dataUser.getDaftarUsernameUser().get("Siloam_1");
        request = new Request(bdrs);
        form = new Form(bdrs);

        form.setNamaRumahSakit(bdrs.getNama());
        form.setAlamat(bdrs.getAlamat());
        form.setTelepon(bdrs.getNoTelp());
        form.setUnitBDRS(bdrs);
        form.setTanggalPermintaan(LocalDate.now());
        form.setJamPermintaan(LocalTime.now());

        form.setNamaPasien("Cynthia");
        form.setGolonganDarah(golDarahEnum.AB);
        form.setRhesus(rhesusEnum.POSITIVE);
        form.setNomorRekamMedis("889900");
        form.setJenisKelamin(JenisKelamin.PEREMPUAN);
        form.setRuangPerawatan("C12");
        form.setDiagnosaKlinis("operasi caesar terjadwal");
        form.setJumlahKantong(2);
        form.setRencanaWaktuTransfusi(LocalDateTime.parse("2026-06-01 07:00", format));
        form.setNamaDokter("Dr. Felicia");
        form.setJabatan("Dokter Kandungan");
        form.setNomorSIP("9911");

        // Data klinis - kondisi stabil, elektif
        form.setUsiaPasien(30);
        form.setKadarHb(7);
        form.setTekananDarahSistolik(120);
        form.setGcsMata(4); // terbuka spontan
        form.setGcsVerbal(5); // orientasi baik
        form.setGcsMotorik(6); // mengikuti perintah → GCS 15
        form.setSpo2(98);
        form.setFrekuensiNapas(18);
        form.setFrekuensiNadi(78);
        form.setSuhuTubuh(36.7);
        form.setSkalaNyeri(3);

        // Tidak ada tindakan emergensi
        form.setResusitasiCairan(0);
        form.setIntubasiAtauManajemenJalanNapas(0);
        form.setDefibrilasi(0);

        // Resource medis (lab + konsultasi + obatIV + prosedur + transfusi = 5)
        form.setPemeriksaanLaboratorium(1);
        form.setPemeriksaanRadiologi(0);
        form.setKonsultasiSpesialis(1);
        form.setObatIVAtauIM(1);
        form.setNebulizer(0);
        form.setProsedurTindakan(1);
        form.setReassessmentTandaVital(0);
        form.setJumlahJenisResourceMedis(5); // lab + konsultasi + obatIV + prosedur + transfusi

        request.setForm(form);

        // #endregion

        // #region form 5
        // Rudi - A- - "perdarahan aktif pada saluran cerna" - kritis, ESI 1

        bdrs = (BDRS) dataUser.getDaftarUsernameUser().get("Ciputra");
        request = new Request(bdrs);
        form = new Form(bdrs);

        form.setNamaRumahSakit(bdrs.getNama());
        form.setAlamat(bdrs.getAlamat());
        form.setTelepon(bdrs.getNoTelp());
        form.setUnitBDRS(bdrs);
        form.setTanggalPermintaan(LocalDate.now());
        form.setJamPermintaan(LocalTime.now());

        form.setNamaPasien("Rudi");
        form.setGolonganDarah(golDarahEnum.A);
        form.setRhesus(rhesusEnum.NEGATIVE);
        form.setNomorRekamMedis("445566");
        form.setJenisKelamin(JenisKelamin.LAKI_LAKI);
        form.setRuangPerawatan("ICU-08");
        form.setDiagnosaKlinis("perdarahan aktif pada saluran cerna");
        form.setJumlahKantong(5);
        form.setRencanaWaktuTransfusi(LocalDateTime.parse("2026-05-30 18:15", format));
        form.setNamaDokter("Dr. Bima");
        form.setJabatan("Dokter Spesialis Penyakit Dalam");
        form.setNomorSIP("5566");

        // Data klinis - perdarahan GI aktif, tekanan turun
        form.setUsiaPasien(55);
        form.setKadarHb(7);
        form.setTekananDarahSistolik(85);
        form.setGcsMata(3); // terbuka saat dipanggil
        form.setGcsVerbal(4); // bingung
        form.setGcsMotorik(5); // melokalisasi nyeri → GCS 12
        form.setSpo2(93);
        form.setFrekuensiNapas(22);
        form.setFrekuensiNadi(122);
        form.setSuhuTubuh(37.5);
        form.setSkalaNyeri(8);

        // Tindakan emergensi - butuh resusitasi cairan
        form.setResusitasiCairan(1);
        form.setIntubasiAtauManajemenJalanNapas(0);
        form.setDefibrilasi(0);

        // Resource medis (lab + radiologi + konsultasi + obatIV + prosedur +
        // reassessment + transfusi = 7)
        form.setPemeriksaanLaboratorium(1);
        form.setPemeriksaanRadiologi(1);
        form.setKonsultasiSpesialis(1);
        form.setObatIVAtauIM(1);
        form.setNebulizer(0);
        form.setProsedurTindakan(1);
        form.setReassessmentTandaVital(1);
        form.setJumlahJenisResourceMedis(7); // lab + rad + konsultasi + obatIV + prosedur + reassessment + transfusi

        request.setForm(form);

        // #endregion

        // #region form 6
        // Maria - B- - "demam berdarah dengan trombosit rendah" - sedang, ESI 2

        bdrs = (BDRS) dataUser.getDaftarUsernameUser().get("Siloam_1");
        request = new Request(bdrs);
        form = new Form(bdrs);

        form.setNamaRumahSakit(bdrs.getNama());
        form.setAlamat(bdrs.getAlamat());
        form.setTelepon(bdrs.getNoTelp());
        form.setUnitBDRS(bdrs);
        form.setTanggalPermintaan(LocalDate.now());
        form.setJamPermintaan(LocalTime.now());

        form.setNamaPasien("Maria");
        form.setGolonganDarah(golDarahEnum.B);
        form.setRhesus(rhesusEnum.NEGATIVE);
        form.setNomorRekamMedis("998877");
        form.setJenisKelamin(JenisKelamin.PEREMPUAN);
        form.setRuangPerawatan("D04");
        form.setDiagnosaKlinis("demam berdarah dengan trombosit rendah");
        form.setJumlahKantong(4);
        form.setRencanaWaktuTransfusi(LocalDateTime.parse("2026-06-02 14:45", format));
        form.setNamaDokter("Dr. Clara");
        form.setJabatan("Dokter Umum");
        form.setNomorSIP("3344");

        // Data klinis - DBD, demam tinggi, sadar penuh
        form.setUsiaPasien(22);
        form.setKadarHb(7);
        form.setTekananDarahSistolik(105);
        form.setGcsMata(4); // terbuka spontan
        form.setGcsVerbal(5); // orientasi baik
        form.setGcsMotorik(6); // mengikuti perintah → GCS 15
        form.setSpo2(97);
        form.setFrekuensiNapas(20);
        form.setFrekuensiNadi(96);
        form.setSuhuTubuh(38.9);
        form.setSkalaNyeri(5);

        // Tidak ada tindakan emergensi
        form.setResusitasiCairan(0);
        form.setIntubasiAtauManajemenJalanNapas(0);
        form.setDefibrilasi(0);

        // Resource medis (lab + konsultasi + obatIV + reassessment + transfusi = 5)
        form.setPemeriksaanLaboratorium(1);
        form.setPemeriksaanRadiologi(0);
        form.setKonsultasiSpesialis(1);
        form.setObatIVAtauIM(1);
        form.setNebulizer(0);
        form.setProsedurTindakan(0);
        form.setReassessmentTandaVital(1);
        form.setJumlahJenisResourceMedis(5); // lab + konsultasi + obatIV + reassessment + transfusi

        request.setForm(form);

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
                exit();
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
        Notification.cekPengingatDonor(currentUser);
        Notification.cekDonorDarurat(currentUser);
        Notification notif = new Notification(this);
        notif.run();

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

        Provinsi provinsi = Provinsi.inputProvinsi(this);
        WilayahIndonesia wilayahIndonesia = WilayahIndonesia.inputWilayahIndonesia(this, provinsi);
        System.out.println(golDarah);
        dataUser.insertUser(new Pendonor(this,username, password, noTelp, gol, rhesus, nama,provinsi,wilayahIndonesia));
        currentUser = dataUser.getDaftarUsernameUser().get(username);

        // dummy riwayat & notif

        Pendonor pendonorBaru = (Pendonor) dataUser.getDaftarUsernameUser().get(username);
        pendonorBaru.addRiwayatDonor(new RiwayatDonor("2026-01-04", "KD001", "PMI Surabaya"));
        pendonorBaru.addRiwayatDonor(new RiwayatDonor("2026-02-01", "KD006", "PMI Surabaya"));
        pendonorBaru.setTanggalTerakhirDonor("2026-02-01");
        pendonorBaru.tambahNotifikasi(new Notification("Selamat datang di BloodLink"));

        System.out.println("Registrasi berhasil, silakan login");
        menuAwal();
    }

    private void menuPendonor() {
        tampilkanMenuUtama(this);
    }

    private void exit() {
        save();
        System.out.println("Sampai Jumpa!");
        System.exit(0);
    }

    private void save() {

    }

}
