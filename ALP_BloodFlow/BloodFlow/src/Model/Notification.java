package Model;

import App.App;
import User.User;

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
}
