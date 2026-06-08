package User;

import App.App;
import Darah.KantongDarah;
import Enum.Provinsi;
import Enum.WilayahIndonesia;
import Model.RiwayatDonor;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class UDD extends FasilitasDarah {
    private static int counterIdDarah = 1;

    public UDD(App app, String username, String password, String noTelp, String alamat, String nama, Provinsi provinsi,
            WilayahIndonesia wilayahIndonesia) {
        super(app, username, password, noTelp, nama, alamat, provinsi, wilayahIndonesia);
    }

    @Override
    public void tampilkanMenuUtama(App app) {

        System.out.println("=== MENU UTAMA ===");
        System.out.println("1. Menu Permintaan");
        System.out.println("2. Input Darah Masuk");
        System.out.println("0. Log Out");
        System.out.print("input: ");
        String input = app.getSc().next() + app.getSc().nextLine();
        System.out.println();
        switch (input) {
            case "0":
                app.menuAwal();
                break;
            case "1":
                menuPermintaan(app);
                break;
            case "2":
                inputDarahMasuk(app);
                break;
            default:
                System.out.println("Invalid Input!!");
                tampilkanMenuUtama(app);
        }
    }

    private void inputDarahMasuk(App app) {
        Scanner sc = app.getSc();

        System.out.println("=== DAFTAR PENDONOR ===");

        for (User user : app.getDataUser().getDaftarPendonor().values()) {

            Pendonor pendonor = (Pendonor) user;

            System.out.println("ID: " + pendonor.getIdPengguna() + " | Nama: " + pendonor.getUsername());
        }

        User user;

        do {
            System.out.print("ID Pendonor: ");
            String idPendonor = sc.next() + sc.nextLine();

            user = app.getDataUser().getDaftarPendonor().get(idPendonor);

            if (user == null) {
                System.out.println("Pendonor tidak ditemukan!");
            } else {
                break;
            }
        } while (true);

        Pendonor pendonor = (Pendonor) user;

        System.out.println("Pendonor ditemukan: " + pendonor.getUsername());

        if (pendonor.getTanggalTerakhirDonor() != null) {

            try {

                LocalDate tanggalTerakhir = pendonor.getTanggalTerakhirDonor();

                LocalDate bolehDonorLagi = tanggalTerakhir.plusDays(90);

                long sisaHari = ChronoUnit.DAYS.between(
                        LocalDate.now(),
                        bolehDonorLagi);

                if (sisaHari > 0) {

                    System.out.println();
                    System.out.println(
                            "Pendonor belum layak donor.");

                    System.out.println(
                            "Silakan tunggu "
                                    + sisaHari
                                    + " hari lagi.");

                    return;
                }

            } catch (Exception e) {

                System.out.println(
                        "Format tanggal donor salah");

                return;
            }
        }

        System.out.print("ID Kantong Darah: ");
        String idDarah = String.valueOf(counterIdDarah++);

        KantongDarah darah = new KantongDarah(idDarah, pendonor.getIdPengguna(), pendonor.getGolDarah(),
                pendonor.getRhesus(), this);

        stokDarah.add(darah);

        RiwayatDonor riwayat = new RiwayatDonor(darah.getTanggalMasuk(), darah.getIdDarah(), this.getNama());

        pendonor.addRiwayatDonor(riwayat);
        pendonor.setTanggalTerakhirDonor(darah.getTanggalMasuk());

        System.out.println("Kantong darah berhasil ditambahkan!");
        System.out.println("Golongan: " + darah.getJenisDarah() + " " + darah.getRhesus());

        System.out.println("Kadaluarsa: " + darah.getTanggalKadaluarsa());
    }

}
