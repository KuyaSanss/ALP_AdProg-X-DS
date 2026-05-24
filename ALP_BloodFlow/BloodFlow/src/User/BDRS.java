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
                makeRequest();
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

        System.out.println("Jenis Kelamin");
        System.out.println("1. LAKI LAKI");
        System.out.println("2. PEREMPUAN");
        System.out.print("Pilih: ");

        int pilihanJenisKelamin = Integer.parseInt(app.getSc().nextLine());

        if (pilihanJenisKelamin == 1) {
            form.setJenisKelamin(JenisKelamin.LAKI_LAKI);
        } else {
            form.setJenisKelamin(JenisKelamin.PEREMPUAN);
        }

        System.out.print("Ruang Perawatan: ");
        form.setRuangPerawatan(app.getSc().nextLine());

        System.out.print("Diagnosa Klinis: ");
        form.setDiagnosaKlinis(app.getSc().nextLine());

        System.out.println("\nB. DATA PERMINTAAN DARAH");

        System.out.println("Komponen Darah");
        System.out.println("1. WB");
        System.out.println("2. PRC");
        System.out.println("3. FFP");
        System.out.println("4. TC");
        System.out.println("5. LAINNYA");
        System.out.print("Pilih: ");

        int pilihanKomponen = Integer.parseInt(app.getSc().nextLine());

        switch (pilihanKomponen) {

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
                form.setKomponenDarah(KomponenDarah.LAINNYA);
        }

        System.out.print("Jumlah Kantong: ");
        form.setJumlahKantong(
                Integer.parseInt(app.getSc().nextLine()));

        System.out.print("Rencana Waktu Transfusi: ");
        form.setRencanaWaktuTransfusi(
                app.getSc().nextLine());

        System.out.print("Alasan Klinis Permintaan: ");
        form.setAlasanKlinis(
                app.getSc().nextLine());

        System.out.println("\nC. DATA DOKTER PEMINTA");

        System.out.print("Nama Dokter: ");
        form.setNamaDokter(app.getSc().nextLine());

        System.out.print("Jabatan: ");
        form.setJabatan(app.getSc().nextLine());

        System.out.print("Nomor SIP: ");
        form.setNomorSIP(app.getSc().nextLine());

    }

}
