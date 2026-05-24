package User;

import App.App;
import java.util.LinkedList;
import java.util.Scanner;

public class UDD extends User {
    private String alamat;
    private static LinkedList<KantongDarah>daftarKantongDarah = new LinkedList<>();
    private static int counterIdDarah = 1;

    public UDD(String username, String password, String noTelp, String alamat) {
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
        
        Scanner sc = app.getSc();

        while (true) {
            System.out.println("");
            System.out.println("=== MENU UDD ===");
            System.out.println("1. Input Darah Masuk");
            System.out.println("2. Logout");
            System.out.println("Pilih: ");
            String pilih = sc.next() + sc.nextLine();

            switch (pilih) {
                case "1":
                    inputDarahMasuk(app);
                    break;
                case "2":
                    return;
            }
        }
    }
    
    

    private void inputDarahMasuk(App app){
    Scanner sc = app.getSc();

    System.out.println("=== DAFTAR PENDONOR ===");

    for (User user : app.getDataUser()
            .getDaftarPendonor()
            .values()) {

        Pendonor pendonor = (Pendonor) user;

        System.out.println(
                "ID: " + pendonor.getIdPengguna()
                + " | Nama: " + pendonor.getUsername()
        );
    }

    System.out.print("ID Pendonor: ");
    String idPendonor = sc.next() + sc.nextLine();

    User user = app.getDataUser()
                    .getDaftarPendonor()
                    .get(idPendonor);

    if(user == null){
        System.out.println("Pendonor tidak ditemukan!");
        return;
    }

    Pendonor pendonor = (Pendonor) user;

    System.out.println("Pendonor ditemukan: "+ pendonor.getUsername());

    System.out.print("ID Kantong Darah: ");
    String idDarah = String.valueOf(counterIdDarah++);

    KantongDarah darah = new KantongDarah( idDarah, pendonor.getIdPengguna(), pendonor.getGolDarah(), pendonor.getRhesus());

    daftarKantongDarah.add(darah);

    System.out.println("Kantong darah berhasil ditambahkan!");
    System.out.println("Golongan: "+ darah.getJenisDarah()+" "+ darah.getRhesus());

    System.out.println("Kadaluarsa: "+ darah.getTanggalKadaluarsa());
}
}
