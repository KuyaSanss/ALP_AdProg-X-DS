package Model;

import App.App;
import User.Pendonor;
import User.User;
import Request.*;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import Request.Form;

public class Notification implements Runnable {
    private App aplikasi;
    private boolean isRead;
    private String pesan;

    public Notification(App app) {
        this.aplikasi = app;
    }

    public Notification(String pesan) {
        this.pesan = pesan;
        this.isRead = false;
    }

    @Override
    public void run() {

        User currentUser = aplikasi.getCurrentUser();
        if(!(currentUser instanceof Pendonor))return;
        Pendonor pendonor = (Pendonor) currentUser;

        int unread = 0;

        for (Notification notif : pendonor.getInbox()) {

            if (!notif.isRead()) {
                unread++;
            }
        }

        if (unread > 0) {

            System.out.println(
                    "[!] Anda memiliki "
                            + unread +
                            " notifikasi");
        }
    }

    public App getAplikasi() {
        return aplikasi;
    }

    public void setAplikasi(App aplikasi) {
        this.aplikasi = aplikasi;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public static void tampilkanSemuaNotifikasi(Pendonor pendonor) {

        if (pendonor.getInbox().isEmpty()) {
            System.out.println("Tidak ada notifikasi");
            return;
        }

        System.out.println("===== NOTIFIKASI =====");

        for (Notification notif : pendonor.getInbox()) {

            String status = "";

            if (notif.isRead()) {
                status = " [READ]";
            }

            System.out.println(
                    "- "
                            + notif.getPesan()
                            + status);

            notif.setRead(true);
        }
    }

    public static void cekPengingatDonor(Pendonor pendonor) {

        if (pendonor.getRiwayatDonor().isEmpty()) {
            return;
        }

        for (Notification n : pendonor.getInbox()) {

            if (n.getPesan().equals(
                    "Anda sudah dapat melakukan donor darah kembali")) {
                return;
            }
        }

        RiwayatDonor terakhir = pendonor.getRiwayatDonor()
                .get(
                        pendonor.getRiwayatDonor().size() - 1);

        LocalDate tanggalDonor = terakhir.getTanggalDonor();

        long selisihHari = ChronoUnit.DAYS.between(
                tanggalDonor,
                LocalDate.now());

        if (selisihHari >= 90) {

            pendonor.getInbox().add(
                    new Notification(
                            "Anda sudah dapat melakukan donor darah kembali"));
        }
    }

    //todo benerin
    public static void cekDonorDarurat(Pendonor pendonor) {

        for (Request req : Request.getLiveRequestList()) {
            Form form = req.getForm();
            if (form.getGolonganDarah() == pendonor.getGolDarah() && form.getRhesus() == pendonor.getRhesus()) {

                String pesan = "Donor darurat dibutuhkan untuk golongan darah "
                        + form.getGolonganDarah()
                        + " "
                        + form.getRhesus();

                boolean sudahAda = false;

                for (Notification notif : pendonor.getInbox()) {

                    if (notif.getPesan().equals(pesan)) {
                        sudahAda = true;
                        break;
                    }
                }

                if (!sudahAda) {

                    pendonor.getInbox().add(
                            new Notification(pesan));
                }
            }
        }
    }

    

}
