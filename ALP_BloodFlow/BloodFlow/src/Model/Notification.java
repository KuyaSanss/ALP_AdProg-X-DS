package Model;

import App.App;
import User.Pendonor;
import User.User;

import Request.*;
import User.Pendonor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import Request.Form;
import User.Pendonor;

import java.util.ArrayList;

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

        User user = aplikasi.getCurrentUser();

        if (user == null) {
            return;
        }

        int unread = 0;

        for (Notification notif : user.getInbox()) {

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

    public static void tampilkanSemuaNotifikasi(User user) {

        if (user.getInbox().isEmpty()) {
            System.out.println("Tidak ada notifikasi");
            return;
        }

        System.out.println("===== NOTIFIKASI =====");

        for (Notification notif : user.getInbox()) {

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

    public static void cekPengingatDonor(User user) {

        if (!(user instanceof Pendonor)) {
            return;
        }

        Pendonor pendonor = (Pendonor) user;

        if (pendonor.getRiwayatDonor().isEmpty()) {
            return;
        }

        for (Notification n : user.getInbox()) {

            if (n.getPesan().equals(
                    "Anda sudah dapat melakukan donor darah kembali")) {
                return;
            }
        }

        RiwayatDonor terakhir = pendonor.getRiwayatDonor()
                .get(
                        pendonor.getRiwayatDonor().size() - 1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "yyyy-MM-dd",
                new Locale("id", "ID"));

        LocalDate tanggalDonor = LocalDate.parse(
                terakhir.getTanggalDonor(),
                formatter);

        long selisihHari = ChronoUnit.DAYS.between(
                tanggalDonor,
                LocalDate.now());

        if (selisihHari >= 90) {

            user.tambahNotifikasi(
                    new Notification(
                            "Anda sudah dapat melakukan donor darah kembali"));
        }
    }

    //todo benerin
    public static void cekDonorDarurat(User user) {

        if (!(user instanceof Pendonor)) {
            return;
        }

        Pendonor pendonor = (Pendonor) user;

        for (Request req : Request.getLiveRequestList()) {
            Form form = req.getForm();
            if (form.getGolonganDarah() == pendonor.getGolDarah() && form.getRhesus() == pendonor.getRhesus()) {

                String pesan = "Donor darurat dibutuhkan untuk golongan darah "
                        + form.getGolonganDarah()
                        + " "
                        + form.getRhesus();

                boolean sudahAda = false;

                for (Notification notif : user.getInbox()) {

                    if (notif.getPesan().equals(pesan)) {
                        sudahAda = true;
                        break;
                    }
                }

                if (!sudahAda) {

                    user.tambahNotifikasi(
                            new Notification(pesan));
                }
            }
        }
    }

    

}
