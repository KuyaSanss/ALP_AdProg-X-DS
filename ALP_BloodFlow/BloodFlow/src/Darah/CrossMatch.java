package Darah;

import java.util.Scanner;

import App.App;
import User.FasilitasDarah;

public class CrossMatch extends TesDarah {

    private boolean f1, f2, f3, ac,aman;

    public CrossMatch(FasilitasDarah fasilitasDarah, SampelDarah sampelDarahPendonor) {
        super(fasilitasDarah, sampelDarahPendonor);
    }

    public void formCrossMatch(App app) {
        System.out.println("==================================================");
        System.out.println("  SISTEM VERIFIKASI HASIL CROSS MATCHING DARAH    ");
        System.out.println("==================================================");
        System.out.println("Petunjuk: Jawab dengan 'Y' (Ya) atau 'T' (Tidak)\n");

        f1 = ambilInput(app, "1. Apakah ada aglutinasi/hemolisis pada Fase I (Suhu Kamar)? [Y/T]: ");
        f2 = ambilInput(app, "2. Apakah ada aglutinasi/hemolisis pada Fase II (Inkubasi 37°C)? [Y/T]: ");
        f3 = ambilInput(app, "3. Apakah ada aglutinasi/hemolisis pada Fase III (Coombs/AHG)? [Y/T]: ");
        ac = ambilInput(app, "4. Apakah terjadi aglutinasi pada Auto-Control (AC)? [Y/T]: ");

        kesimpulan();
        
    }

    private void kesimpulan(){
        System.out.println("\n================ HASIL VERIFIKASI ================");

        if (!f1 && !f2 && !f3 && !ac) {
            System.out.println("KESIMPULAN : KOMPATIBEL (COCOK)");
            System.out.println("STATUS     : Darah aman dan dapat ditransfusikan ke pasien.");
            aman=true;
        } else {
            System.out.println("KESIMPULAN : TIDAK KOMPATIBEL (TIDAK COCOK)");
            System.out.println("STATUS     : Terdapat antibodi spesifik pada serum pasien terhadap donor.");
            System.out.println("TINDAKAN   : Darah TIDAK BOLEH diberikan. Cari kantong donor baru!");

            System.out.println("==================================================");
            aman=false;
        }
    }

    private static boolean ambilInput(App app, String pertanyaan) {
        while (true) {
            System.out.print(pertanyaan);
            String input = app.getSc().nextLine().trim().toUpperCase();

            if (input.equals("Y")) {
                return true;
            } else if (input.equals("T")) {
                return false;
            } else {
                System.out.println("[!] Input salah. Harap masukkan 'Y' atau 'T'.");
            }
        }
    }

    public boolean isAman() {
        return aman;
    }

    public boolean isF1() {
        return f1;
    }

    public void setF1(boolean f1) {
        this.f1 = f1;
    }

    public boolean isF2() {
        return f2;
    }

    public void setF2(boolean f2) {
        this.f2 = f2;
    }

    public boolean isF3() {
        return f3;
    }

    public void setF3(boolean f3) {
        this.f3 = f3;
    }

    public boolean isAc() {
        return ac;
    }

    public void setAc(boolean ac) {
        this.ac = ac;
    }

    public void setAman(boolean aman) {
        this.aman = aman;
    }

}
