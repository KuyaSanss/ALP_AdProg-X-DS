package Darah;

import java.util.Scanner;

import App.App;
import User.FasilitasDarah;

public class CrossMatch extends TesDarah {

    private boolean f1, f2, f3, ac;

    public CrossMatch(FasilitasDarah fasilitasDarah, SampelDarah sampelDarahPendonor) {
        super(fasilitasDarah, sampelDarahPendonor);
    }

    public void formCrossMatch(App app) {
        System.out.println("==================================================");
        System.out.println("  SISTEM VERIFIKASI HASIL CROSS MATCHING DARAH    ");
        System.out.println("==================================================");
        System.out.println("Petunjuk: Jawab dengan 'Y' (Ya) atau 'T' (Tidak)\n");

        // Mengambil input dari user menggunakan fungsi helper
        f1 = ambilInput(app.getSc(), "1. Apakah ada aglutinasi/hemolisis pada Fase I (Suhu Kamar)? [Y/T]: ");
        f2 = ambilInput(app.getSc(), "2. Apakah ada aglutinasi/hemolisis pada Fase II (Inkubasi 37°C)? [Y/T]: ");
        f3 = ambilInput(app.getSc(), "3. Apakah ada aglutinasi/hemolisis pada Fase III (Coombs/AHG)? [Y/T]: ");
        ac = ambilInput(app.getSc(), "4. Apakah terjadi aglutinasi pada Auto-Control (AC)? [Y/T]: ");

        // Memproses dan Menampilkan Kesimpulan
        System.out.println("\n================ HASIL VERIFIKASI ================");

        if (!f1 && !f2 && !f3 && !ac) {
            System.out.println("KESIMPULAN : KOMPATIBEL (COCOK)");
            System.out.println("STATUS     : Darah aman dan dapat ditransfusikan ke pasien.");
        } else {
            System.out.println("KESIMPULAN : TIDAK KOMPATIBEL (TIDAK COCOK)");
            System.out.println("STATUS     : Terdapat antibodi spesifik pada serum pasien terhadap donor.");
            System.out.println("TINDAKAN   : Darah TIDAK BOLEH diberikan. Cari kantong donor baru!");

            System.out.println("==================================================");
        }
    }

    private static boolean ambilInput(Scanner scanner, String pertanyaan) {
        while (true) {
            System.out.print(pertanyaan);
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.equals("Y")) {
                return true;
            } else if (input.equals("T")) {
                return false;
            } else {
                System.out.println("[!] Input salah. Harap masukkan 'Y' atau 'T'.");
            }
        }
    }

}
