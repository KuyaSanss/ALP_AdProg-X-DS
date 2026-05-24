package User;

import App.App;
import Request.Request;

public class UDD extends User{
    private String alamat;

    public UDD(String username, String password, String noTelp, String alamat, String nama) {
        super(username, password, noTelp,nama);
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
        System.out.println("1. Buat Cek Permintaan");
        System.out.println("0. Log Out");
        System.out.print("input: ");
        String input = app.getSc().next() + app.getSc().nextLine();
        System.out.println();
        switch (input) {
            case "0":
                app.menuAwal();
                break;
            case "1":
                checkRequest(app);
                break;
            case "2":

                break;
            case "3":

                break;
            default:
                System.out.println("Invalid Input!!");
                tampilkanMenuUtama(app);
        }
    }

    public void checkRequest(App app){
        Request request = Request.displayRequests(app);
        request.approveRequest(app);
    }

}
