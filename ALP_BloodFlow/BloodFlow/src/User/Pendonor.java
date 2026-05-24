package User;

import java.util.ArrayList;

import App.App;
import Enum.golDarahEnum;
import Enum.rhesusEnum;
import Model.RiwayatDonor;

public class Pendonor extends User {
    private golDarahEnum golDarah;
    private rhesusEnum rhesus;
    private String tanggalTerakhirDonor;
    private ArrayList<RiwayatDonor> riwayatDonor;

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

    @Override
    public void tampilkanMenuUtama(App app) {
        System.out.println("=== MENU PENDONOR ===");
        System.out.println("1. Lihat Riwayat Donor");
        System.out.println("2. logout");
    }

    public void addRiwayatDonor(RiwayatDonor riwayat) {
        riwayatDonor.add(riwayat);
    }

    public void tampilkanRiwayatDonor() {

        System.out.println();
        System.out.println("===== RIWAYAT DONOR =====");

        if (riwayatDonor.isEmpty()) {
            System.out.println(
                    "Anda belum memiliki riwayat donor");
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
        System.out.println("===== DONOR TERAKHIR =====");
        System.out.println("Tanggal : " + donorTerakhir.getTanggalDonor());
    }

}
