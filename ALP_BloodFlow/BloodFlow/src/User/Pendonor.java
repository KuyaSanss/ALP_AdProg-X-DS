package User;

import App.App;
import Enum.Provinsi;
import Enum.WilayahIndonesia;
import Enum.golDarahEnum;
import Enum.rhesusEnum;
import Model.Notification;
import Model.RiwayatDonor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Pendonor extends User implements Comparable<Pendonor> {
    private static int jumlahPendonor = 0;
    private golDarahEnum golDarah;
    private rhesusEnum rhesus;
    private LocalDate tanggalTerakhirDonor;
    private ArrayList<RiwayatDonor> riwayatDonor;
    protected ArrayList<Notification> inbox = new ArrayList<>();
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final int MASA_TUNGGU_DONOR = 90;

    public Pendonor(App app, String username, String password, String noTelp, golDarahEnum golDarah, rhesusEnum rhesus,
            String nama, Provinsi provinsi, WilayahIndonesia wilayahIndonesia) {
        super(app, username, password, noTelp, nama, provinsi, wilayahIndonesia);
        jumlahPendonor++;
        this.idPengguna = "PD" + jumlahPendonor;
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

    public LocalDate getTanggalTerakhirDonor() {
        return tanggalTerakhirDonor;
    }

    public void setTanggalTerakhirDonor(LocalDate tanggalTerakhirDonor) {
        this.tanggalTerakhirDonor = tanggalTerakhirDonor;
    }

    public ArrayList<RiwayatDonor> getRiwayatDonor() {
        return riwayatDonor;
    }

    public void setRiwayatDonor(ArrayList<RiwayatDonor> riwayatDonor) {
        this.riwayatDonor = riwayatDonor;
    }

    // public Hashtable<Integer, String> getInbox() {
    // return inbox;
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
                (this).tampilkanRiwayatDonor();
                break;
            case "2":
                cekKelayakanDonor();
                Notification.tampilkanSemuaNotifikasi(this);
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
            // Asumsikan riwayat.getTanggalDonor() masih mengembalikan String
            String tglStr = riwayat.getTanggalDonor();
            LocalDate tgl = null;
            try {
                tgl = LocalDate.parse(tglStr, FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Tanggal Donor : " + tglStr + " (format salah)");
                continue;
            }
            System.out.println("Tanggal Donor : " + tgl.format(FORMATTER));
            System.out.println("ID Kantong Darah : " + riwayat.getIdKantongDarah());
            System.out.println("Lokasi Donor : " + riwayat.getLokasiDonor());
            System.out.println();
        }

        // Ambil donor terakhir, parse string tanggalnya ke LocalDate dan simpan ke field
        RiwayatDonor donorTerakhir = riwayatDonor.get(riwayatDonor.size() - 1);
        System.out.println();
        System.out.println("===== DONOR TERAKHIR =====");
        String tglTerakhirStr = donorTerakhir.getTanggalDonor();
        try {
            LocalDate tglTerakhir = LocalDate.parse(tglTerakhirStr, FORMATTER);
            this.tanggalTerakhirDonor = tglTerakhir;
            System.out.println("Tanggal : " + tglTerakhir.format(FORMATTER));
        } catch (DateTimeParseException e) {
            System.out.println("Tanggal donor terakhir tidak valid: " + tglTerakhirStr);
            this.tanggalTerakhirDonor = null;  // jaga agar field tetap valid
        }

        System.out.println("Total Donor : " + riwayatDonor.size() + " kali");
    }

    public boolean layakDonor() {
        // Jika belum pernah donor, langsung layak
        if (tanggalTerakhirDonor == null) {
            return true;
        }

        LocalDate bolehDonorLagi = tanggalTerakhirDonor.plusDays(MASA_TUNGGU_DONOR);
        return !LocalDate.now().isBefore(bolehDonorLagi);
    }

    public void cekKelayakanDonor() {
        if (tanggalTerakhirDonor == null) {
            System.out.println();
            System.out.println("=================================");
            System.out.println("STATUS DONOR");
            System.out.println("Anda belum pernah donor darah");
            System.out.println("Anda dapat donor kapan saja");
            System.out.println("=================================");
            return;
        }

        LocalDate sekarang = LocalDate.now();
        LocalDate bolehDonorLagi = tanggalTerakhirDonor.plusDays(MASA_TUNGGU_DONOR);
        long sisaHari = ChronoUnit.DAYS.between(sekarang, bolehDonorLagi);

        System.out.println();
        System.out.println("=================================");
        System.out.println("STATUS DONOR");
        System.out.println("Donor terakhir : " + tanggalTerakhirDonor.format(FORMATTER));

        if (sisaHari <= 0) {
            String pesan = "Anda sudah dapat melakukan donor darah kembali sejak " + bolehDonorLagi.format(FORMATTER);
            inbox.add(new Notification(pesan));
            System.out.println("Status : SUDAH BISA DONOR");
            System.out.println("Boleh donor lagi sejak : " + bolehDonorLagi.format(FORMATTER));
        } else {
            System.out.println("Status : BELUM BISA DONOR");
            System.out.println("Boleh donor lagi pada : " + bolehDonorLagi.format(FORMATTER));
            System.out.println("Sisa waktu : " + sisaHari + " hari lagi");
        }
        System.out.println("=================================");
    }
        public static int getJumlahPendonor() {
        return jumlahPendonor;
    }

    public static void setJumlahPendonor(int jumlahPendonor) {
        Pendonor.jumlahPendonor = jumlahPendonor;
    }

    public ArrayList<Notification> getInbox() {
        return inbox;
    }

    public void setInbox(ArrayList<Notification> inbox) {
        this.inbox = inbox;
    }

    public static int getMasaTungguDonor() {
        return MASA_TUNGGU_DONOR;
    }

    @Override
    public int compareTo(Pendonor other) {
        // int thisSize = riwayatDonor.size();
        // int otherSize = other.getRiwayatDonor().size();
        // //todo compare to
        // Local thisTerakhir  = ;
        // int otherTerakhir = ;
        return 0;
    }

}
