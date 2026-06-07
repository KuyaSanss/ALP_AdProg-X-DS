package User;

import App.App;
import Enum.Provinsi;
import Enum.WilayahIndonesia;


public class BDRS extends FasilitasDarah {
    

    public BDRS(App app,String username, String password, String noTelp, String alamat, String nama,Provinsi provinsi, WilayahIndonesia wilayahIndonesia) {
        super(app, username, password, noTelp, nama,alamat,provinsi,wilayahIndonesia);
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
                super.makeRequest(app);
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