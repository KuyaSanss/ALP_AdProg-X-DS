package User;

import App.App;
import Enum.golDarahEnum;
import Enum.rhesusEnum;
import Model.Notification;
import Model.RiwayatDonor;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Hashtable;

public class Pendonor extends User {
    private golDarahEnum golDarah;
    private rhesusEnum rhesus;
    private String tanggalTerakhirDonor;
    private ArrayList<RiwayatDonor> riwayatDonor;
    private Hashtable<Integer, String> inbox;
    private static final int MASA_TUNGGU_DONOR = 90;

    public Pendonor(String username, String password, String noTelp, golDarahEnum golDarah, rhesusEnum rhesus,
            String nama) {
        super(username, password, noTelp, nama);
        this.golDarah = golDarah;
        this.rhesus = rhesus;
        this.riwayatDonor = new ArrayList<>();
    }

    public golDarahEnum getGolDarah() {
        return golDarah;
    }

    public void setGolDarah(golDarahEnum golDarah) {
        this.golDarah = golDarah;
    }

    public rhesusEnum getRhesus() {
        return rhesus;
    }

    public void setRhesus(rhesusEnum rhesus) {
        this.rhesus = rhesus;
    }

    public String getTanggalTerakhirDonor() {
        return tanggalTerakhirDonor;
    }

    public void setTanggalTerakhirDonor(String tanggalTerakhirDonor) {
        this.tanggalTerakhirDonor = tanggalTerakhirDonor;
    }

    public ArrayList<RiwayatDonor> getRiwayatDonor() {
        return riwayatDonor;
    }

    public void setRiwayatDonor(ArrayList<RiwayatDonor> riwayatDonor) {
        this.riwayatDonor = riwayatDonor;
    }

    //public Hashtable<Integer, String> getInbox() {
   //     return inbox;
   // }

    @Override
    public void tampilkanMenuUtama(App app) {
        String input;
        System.out.println("=== MENU PENDONOR ===");
        System.out.println("1. Lihat Riwayat Donor");
        System.out.println("2. Cek Notifikasi");
        System.out.println("3. logout");
        System.out.print("Input: ");
        input = app.getSc().next() + app.getSc().nextLine();

        switch (input) {
            case "1":
                ((Pendonor) app.getCurrentUser()).tampilkanRiwayatDonor();
                break;
            case "2":
                cekKelayakanDonor();
                Notification.tampilkanSemuaNotifikasi(app.getCurrentUser());
                break;
            case "3":
                app.menuAwal();
                return;
            default:
                System.out.println("Input tidak valid");
        }
    }

    public void addRiwayatDonor(RiwayatDonor riwayat) {
        riwayatDonor.add(riwayat);
    }

    public void tampilkanRiwayatDonor() {

        System.out.println();
        System.out.println("===== RIWAYAT DONOR =====");

        if (riwayatDonor.isEmpty()) {
            System.out.println("Anda belum memiliki riwayat donor");
            return;
        }

        for (int i = 0; i < riwayatDonor.size(); i++) {
            RiwayatDonor riwayat = riwayatDonor.get(i);
            System.out.println("Riwayat Ke-" + (i + 1));
            System.out.println("Tanggal Donor : " + riwayat.getTanggalDonor());
            System.out.println("ID Kantong Darah : " + riwayat.getIdKantongDarah());
            System.out.println("Lokasi Donor : " + riwayat.getLokasiDonor());
            System.out.println();
        }

        RiwayatDonor donorTerakhir = riwayatDonor.get(riwayatDonor.size() - 1);
        System.out.println();
        System.out.println("===== DONOR TERAKHIR =====");
        System.out.println("Tanggal : " + donorTerakhir.getTanggalDonor());

        this.tanggalTerakhirDonor = donorTerakhir.getTanggalDonor();

        System.out.println("Total Donor : " + riwayatDonor.size() + " kali");
    }

    public void tambahNotifikasi(String pesan) {
        inbox.put(inbox.size() + 1, pesan);
    }

    public void tampilkanInbox() {
        cekKelayakanDonor();
        System.out.println("\n===== INBOX =====");
        if (inbox == null) {
            inbox = new Hashtable<>();
        }
        if (inbox.isEmpty()) {
            System.out.println("Belum ada notifikasi");
            return;
        }
        for (Integer key : inbox.keySet()) {
            System.out.println(key + ". " + inbox.get(key));
        }
    }

    public boolean layakDonor() {

        if (tanggalTerakhirDonor == null ||
                tanggalTerakhirDonor.isEmpty()) {
            return true;
        }

        LocalDate terakhirDonor = LocalDate.parse(tanggalTerakhirDonor);

        LocalDate bolehDonorLagi = terakhirDonor.plusDays(90);

        return !LocalDate.now()
                .isBefore(bolehDonorLagi);
    }

    public void cekKelayakanDonor() {

        if (tanggalTerakhirDonor == null || tanggalTerakhirDonor.isEmpty()) {

            System.out.println();
            System.out.println("=================================");
            System.out.println("STATUS DONOR");
            System.out.println("Anda belum pernah donor darah");
            System.out.println("Anda dapat donor kapan saja");
            System.out.println("=================================");
            return;
        }

        try {

            LocalDate tanggalSekarang = LocalDate.now();

            LocalDate tanggalTerakhirDonor = LocalDate.parse(this.tanggalTerakhirDonor);

            LocalDate tanggalBolehDonorLagi = tanggalTerakhirDonor.plusDays(90);

            long sisaHari = ChronoUnit.DAYS.between(tanggalSekarang,tanggalBolehDonorLagi);

            System.out.println();
            System.out.println("=================================");
            System.out.println("STATUS DONOR");

            System.out.println("Donor terakhir : "+ tanggalTerakhirDonor);

            if (sisaHari <= 0) {
                String pesan = "Anda sudah dapat melakukan donor darah kembali sejak "+ tanggalBolehDonorLagi;
                tambahNotifikasi(pesan);
                System.out.println(
                        "Status : SUDAH BISA DONOR");
                System.out.println(
                        "Boleh donor lagi sejak : "
                                + tanggalBolehDonorLagi);
            } else {
                System.out.println("Status : BELUM BISA DONOR");
                System.out.println( "Boleh donor lagi pada : "+ tanggalBolehDonorLagi);
                System.out.println("Sisa waktu : "+ sisaHari+ " hari lagi");
            }
            System.out.println("=================================");

        } catch (Exception e) {

            System.out.println(
                    "Format tanggal donor salah");
        }
    }
}
