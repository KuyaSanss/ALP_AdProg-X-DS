package User;

import java.util.ArrayList;

import App.App;
import Request.Request;

public class BDRS extends User {
    private String alamat;
    private ArrayList<Request> listRequest = new ArrayList<>();

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

    private void makeRequest(App app){
        Request request = new Request(this);
        listRequest.add(request);
        request.menuRequest(app);
    }

}