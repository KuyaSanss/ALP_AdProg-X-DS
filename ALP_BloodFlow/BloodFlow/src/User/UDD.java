package User;

import App.App;
import java.util.LinkedList;
import java.util.Scanner;
import Request.Request;

public class UDD extends User {
    private String alamat;
    private static LinkedList<KantongDarah> daftarKantongDarah = new LinkedList<>();
    private static int counterIdDarah = 1;

    public UDD(String username, String password, String noTelp, String alamat, String nama) {
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
        System.out.println("1. Buat Cek Permintaan");
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
                checkRequest(app);
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

            System.out.println(
                    "ID: " + pendonor.getIdPengguna()
                            + " | Nama: " + pendonor.getUsername());
        }

        System.out.print("ID Pendonor: ");
        String idPendonor = sc.next() + sc.nextLine();

        User user = app.getDataUser().getDaftarPendonor().get(idPendonor);

        if (user == null) {
            System.out.println("Pendonor tidak ditemukan!");
            return;
        }

        Pendonor pendonor = (Pendonor) user;

        System.out.println("Pendonor ditemukan: " + pendonor.getUsername());

        System.out.print("ID Kantong Darah: ");
        String idDarah = String.valueOf(counterIdDarah++);

        KantongDarah darah = new KantongDarah(idDarah, pendonor.getIdPengguna(), pendonor.getGolDarah(),
                pendonor.getRhesus());

        daftarKantongDarah.add(darah);

        System.out.println("Kantong darah berhasil ditambahkan!");
        System.out.println("Golongan: " + darah.getJenisDarah() + " " + darah.getRhesus());

        System.out.println("Kadaluarsa: " + darah.getTanggalKadaluarsa());
    }

    public void checkRequest(App app) {
        Request request = Request.displayRequests(app);
        request.approveRequest(app);
    }
}
