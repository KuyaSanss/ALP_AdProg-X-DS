package User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import App.App;
import Enum.PrioritasESI;
import Enum.JenisKelamin;
import Enum.golDarahEnum;
import Enum.rhesusEnum;
import Request.Form;
import Request.Request;

public class BDRS extends User {
    private String alamat;

    public BDRS(String username, String password, String noTelp, String alamat, String nama) {
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
        System.out.println("1. Buat permintaan darah");
        System.out.println("2. Log Out");
        System.out.print("input: ");
        String input = app.getSc().next() + app.getSc().nextLine();
        System.out.println();
        switch (input) {
            case "1":
                makeRequest(app);
                break;
            case "2":
                app.menuAwal();
                break;
            default:
                System.out.println("Invalid Input!!");
                tampilkanMenuUtama(app);
        }
    }

}