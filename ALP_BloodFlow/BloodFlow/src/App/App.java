package App;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

import Enum.golDarahEnum;
import Enum.rhesusEnum;
import User.Pendonor;
import User.User;

public class App {
    private Hashtable<String, User> userHashtable = new Hashtable<>();
    // hashtable belum done aku bakal buat wrapper class per atribut biar nanti bisa
    // filter dengan O(1)
    private ArrayList<User> daftarUser = new ArrayList<>();

    private User currentUser;

    private Scanner sc = new Scanner(System.in);

    public App() {
        onStartUp();
        loginAwal();
        tampilkanMenuUtama();
    }

    // buat baca data dari txt dulu
    private void onStartUp() {

    }

    private void loginAwal() {
        int input = 0;

        while (input != 3) {
            System.out.println("""
                    === BloodLink===
                    1. Login
                    2. Registrasi sebagai pendonor
                    3. Exit
                    """);
            System.out.print("Input: ");
            input = sc.nextInt();

            switch (input) {
                case 1:

                    break;
                case 2:
                    registrasi();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid Input!!");
            }
        }

    }

    private void tampilkanMenuUtama() {
        currentUser.tampilkanMenuUtama();
    }

    private void registrasi() {
        String username;

        do {
            System.out.print("Username: ");
            username = sc.next() + sc.nextLine();

            if (userHashtable.containsKey(username)) {

                System.out.println("Username sudah digunakan");
            } else {
                break;
            }
        } while (true);

        do {
            System.out.print("Password: ");
            String password = sc.next() + sc.nextLine();

            System.out.print("Confirmed Password: ");
            String confirmedPassword = sc.next() + sc.nextLine();

            if (!password.equals(confirmedPassword)) {
                System.out.println("password and confirmed password are different!!");
            } else {
                break;
            }
        } while (true);

        golDarahEnum gol = null;
        String golDarah = "z";

        do {
            System.out.print("Golongan Darah: ");
            golDarah = sc.next() + sc.nextLine();
            try {
                gol = golDarahEnum.valueOf(
                        golDarah.toUpperCase());
            } catch (Exception e) {
                System.out.println("Golongan darah tidak valid!!");
            }
        } while (gol == null);

        String rhesusInput = null;
        rhesusEnum rhesus;

        do {
            System.out.print("Rhesus (+/-): ");
            rhesusInput = sc.next() + sc.nextLine();

            if (rhesusInput.equals("+")) {
                rhesus = rhesusEnum.POSITIVE;
                break;
            } else if (rhesusInput.equals("-")) {
                rhesus = rhesusEnum.NEGATIVE;
                break;
            } else {
                System.out.println("Rhesus tidak valid!!");
            }
        } while (true);

        int noTelp;
        int noTelpFix = 0;

        do {
            try {
                System.out.print("Nomor HP: ");
                noTelp = sc.nextInt();
                noTelpFix = noTelp;
            } catch (Exception e) {
                System.out.println("isi nomor hp dengan angka only!!");
            }
        } while (noTelpFix == 0);

        String id = "P" + (daftarUser.size() + 1);

        daftarUser.add(new Pendonor(id, username, id, noTelpFix, gol, rhesus));
    }
}
