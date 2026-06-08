package Darah;

import App.App;
import User.FasilitasDarah;

public class TesIMLTD extends TesDarah{

    public TesIMLTD(FasilitasDarah fasilitasDarah, SampelDarah sampelDarahPendonor) {
        super(fasilitasDarah, sampelDarahPendonor);
    }
    private boolean aman;
    
    private String namaAlatCLIA;
    private String nomorLotReagen;

    private double scoHiv;
    private double scoHbsag;
    private double scoHcv;
    private double scoSifilis;

    @Override
    public void formInput(App app) {
        aman=true;
        System.out.println("=== FORM INPUT HASIL LAB CLIA (IMLTD) ===");

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

        cetakHasil();
    }

    private String interpretasiSco(double sco) {
        if (sco < 1.0)
            return "NON-REAKTIF";
        aman=false;
        return "REAKTIF";
    }

    @Override
    public void cetakHasil() {
        System.out.println("\n==================================================");
        System.out.println("          HASIL VERIFIKASI FORM DATA CLIA         ");
        System.out.println("==================================================");
        System.out.println("ID Sampel   : " + sampelDarahPendonor.getIdSampel());
        System.out.println("Alat / Lot  : " + namaAlatCLIA + " / " + nomorLotReagen);
        System.out.println("--------------------------------------------------");
        System.out.println("Hasil Analisis Parameter:");
        System.out.printf("- HIV     (S/CO: %5.2f) -> %s\n", scoHiv, interpretasiSco(scoHiv));
        System.out.printf("- HBsAg   (S/CO: %5.2f) -> %s\n", scoHbsag, interpretasiSco(scoHbsag));
        System.out.printf("- Anti-HCV(S/CO: %5.2f) -> %s\n", scoHcv, interpretasiSco(scoHcv));
        System.out.printf("- Sifilis (S/CO: %5.2f) -> %s\n", scoSifilis, interpretasiSco(scoSifilis));
        System.out.printf("- Sifilis (S/CO: %5.2f) -> %s\n", scoSifilis, interpretasiSco(scoSifilis));
        
        System.out.println("KESIMPULAN: ");
        if(aman){
            System.out.println("AMAN UNTUK DIGUNAKAN TRANSFUSI");
        }else{
            System.out.println("TIDAK DAPAT DIGUNAKAN UNTUK TRANSFUSI");
        }
        
        System.out.println("==================================================");
    }

    public boolean isAman() {
        return aman;
    }

    public void setAman(boolean aman) {
        this.aman = aman;
    }

    public String getNamaAlatCLIA() {
        return namaAlatCLIA;
    }

    public void setNamaAlatCLIA(String namaAlatCLIA) {
        this.namaAlatCLIA = namaAlatCLIA;
    }

    public String getNomorLotReagen() {
        return nomorLotReagen;
    }

    public void setNomorLotReagen(String nomorLotReagen) {
        this.nomorLotReagen = nomorLotReagen;
    }

    public double getScoHiv() {
        return scoHiv;
    }

    public void setScoHiv(double scoHiv) {
        this.scoHiv = scoHiv;
    }

    public double getScoHbsag() {
        return scoHbsag;
    }

    public void setScoHbsag(double scoHbsag) {
        this.scoHbsag = scoHbsag;
    }

    public double getScoHcv() {
        return scoHcv;
    }

    public void setScoHcv(double scoHcv) {
        this.scoHcv = scoHcv;
    }

    public double getScoSifilis() {
        return scoSifilis;
    }

    public void setScoSifilis(double scoSifilis) {
        this.scoSifilis = scoSifilis;
    }


}
