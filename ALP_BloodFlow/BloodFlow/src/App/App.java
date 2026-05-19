package App;

import Enum.*;
import HashTable.*;
import User.*;
import java.util.Scanner;

public class App {
    // hashtable belum done aku bakal buat wrapper class per atribut biar nanti bisa
    // filter dengan O(1)

    private DataUser dataUser = new DataUser();
    private User currentUser;
    private Scanner sc = new Scanner(System.in);

    // #region Getter Setter

    public DataUser getDataUser() {
        return dataUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public Scanner getSc() {
        return sc;
    }

    // #endregion

    public App() {
        onStartUp();
        menuAwal();
        tampilkanMenuUtama(this);
    }

    // buat baca data dari txt dulu
    private void onStartUp() {
    dataUser.insertUser(new Admin("admin", "admin", "085887312500"));
    }

    private void menuAwal() {
        String input;

        System.out.println("""
                === BloodLink===
                1. Login
                2. Registrasi sebagai pendonor
                3. Exit
                """);
        System.out.print("Input: ");
        input = sc.next() + sc.nextLine();
        switch (input) {
            case "1":
                login();
                break;
            case "2":
                registrasi();
                break;
            case "3":
                System.exit(0);
                break;
            default:
                System.out.println("Invalid Input!!");
                menuAwal();
        }

    }

    private void login() {
        String username, password;
        boolean salah = false;
        System.out.println();

        do {
            salah = false;
            System.out.print("Username : ");
            username = sc.next() + sc.nextLine();
            System.out.print("Password : ");
            password = sc.next() + sc.nextLine();

            if (dataUser.getDaftarUsernameUser().containsKey(username)) {
                if (dataUser.getDaftarUsernameUser().isEmpty() ||
                        dataUser.getDaftarUsernameUser().get(username).getPassword().equals(password)) {
                    break;
                } else {
                    System.out.println("Password salah");
                    salah = true;
                }
            } else {
                System.out.println("Username salah");
                salah = true;
            }
        } while (salah);

        currentUser = dataUser.getDaftarUsernameUser().get(username);
        
    }

    private void tampilkanMenuUtama(App app) {
        currentUser.tampilkanMenuUtama(app);
    }

    private void registrasi() {
        String username;

        do {
            System.out.print("Username: ");
            username = sc.next() + sc.nextLine();

            if (dataUser.getDaftarUser().contains(username)) {

                System.out.println("Username sudah digunakan");
            } else {
                break;
            }
        } while (true);

        String password;

        do {
            System.out.print("Password: ");
            password = sc.next() + sc.nextLine();

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

        String noTelp = "";
        boolean nonDigit = false;
        do {
            nonDigit = false;
            System.out.print("Nomor HP: ");
            noTelp = sc.next() + sc.nextLine();
            // digit check
            for (char c : noTelp.toCharArray()) {
                nonDigit = (Character.isDigit(c)) ? nonDigit : true;
            }
            if (noTelp.equals("") || nonDigit) {
                System.out.println("isi nomor hp dengan angka only!!");
            } else {
                break;
            }
        } while (true);

        dataUser.insertUser(new Pendonor(username, password, noTelp, gol, rhesus));
        // dataUser.insertUser(new Pendonor(username, password, noTelp, gol, rhesus));
        currentUser = dataUser.getDaftarUsernameUser().get(username);
    }

}
