package Darah;

import App.App;

public class TesIMLTD extends TesDarah{

    // 1. Atribut Form CLIA
    private String idSampel;
    private String namaAlatCLIA;
    private String nomorLotReagen;
    private boolean statusQCHariIni;

    // Nilai S/CO untuk 4 Parameter Utama IMLTD
    private double scoHiv;
    private double scoHbsag;
    private double scoHcv;
    private double scoSifilis;

    // 2. Method untuk Menerima Input dari Terminal (Simulasi Form)
    public void isiFormDariTerminal(App app) {

        System.out.println("=== FORM INPUT HASIL LAB CLIA (IMLTD) ===");

        System.out.print("Masukkan ID Sampel/Barcode : ");
        this.idSampel = app.getSc().nextLine();

        System.out.print("Nama Alat CLIA yang Digunakan: ");
        this.namaAlatCLIA = app.getSc().nextLine();

        System.out.print("Nomor Lot/Batch Reagen     : ");
        this.nomorLotReagen = app.getSc().nextLine();


        System.out.println("\n--- Masukkan Nilai S/CO dari Printout Mesin CLIA ---");
        System.out.print("1. S/CO HIV     : ");
        this.scoHiv = app.getSc().nextDouble();
        System.out.print("2. S/CO HBsAg   : ");
        this.scoHbsag = app.getSc().nextDouble();
        System.out.print("3. S/CO Anti-HCV: ");
        this.scoHcv = app.getSc().nextDouble();
        System.out.print("4. S/CO Sifilis : ");
        this.scoSifilis = app.getSc().nextDouble();

        cetakHasilVerifikasiForm();
    }

    // 3. Logika Interpretasi Otomatis dari Form
    private String interpretasiSco(double sco) {
        if (sco < 1.0)
            return "NON-REAKTIF (Aman)";
        if (sco >= 1.0 && sco <= 5.0)
            return "INITIAL REAKTIF (Zona Abu-Abu / Wajib Uji Ulang)";
        return "REAKTIF (High Load / Positif Kuat)";
    }

    // 4. Menampilkan Hasil Form ke Layar
    public void cetakHasilVerifikasiForm() {
        System.out.println("\n==================================================");
        System.out.println("          HASIL VERIFIKASI FORM DATA CLIA         ");
        System.out.println("==================================================");
        System.out.println("ID Sampel   : " + idSampel);
        System.out.println("Alat / Lot  : " + namaAlatCLIA + " / " + nomorLotReagen);
        System.out.println("Status QC   : " + (statusQCHariIni ? "PASSED" : "FAILED"));
        System.out.println("--------------------------------------------------");
        System.out.println("Hasil Analisis Parameter:");
        System.out.printf("- HIV     (S/CO: %5.2f) -> %s\n", scoHiv, interpretasiSco(scoHiv));
        System.out.printf("- HBsAg   (S/CO: %5.2f) -> %s\n", scoHbsag, interpretasiSco(scoHbsag));
        System.out.printf("- Anti-HCV(S/CO: %5.2f) -> %s\n", scoHcv, interpretasiSco(scoHcv));
        System.out.printf("- Sifilis (S/CO: %5.2f) -> %s\n", scoSifilis, interpretasiSco(scoSifilis));
        System.out.println("==================================================");
    }

}
