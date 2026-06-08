package Request;

import java.time.LocalDateTime;
import java.util.Locale;

import User.FasilitasDarah;

public class Transaksi {
// Transaksi
// Menunjukkan bukti pembayaran transaksi darah yang telah disetujui
// Atribut;
// id faktur
// referensi permintaan
// total biaya
// tanggaltransaksi
// status pembayaran
// Method:
// hitungTotalBiaya()
// cetakFaktur()
// ubahStatusLunas()

    //berdasarkan No. 582-63/S.KP/PMI/VIII/2023 Disahkan 1 Agustus 2023 https://pmikotasemarang.or.id/seputar-donor-darah/
    private static final double biayaKantongBiasa = 490_000;
    private static int transaksiTerbuat;

    private String idFaktur;
    private LocalDateTime waktuTransaksi;
    private double totalBiaya;
    private boolean statusTransaksi;
    private FasilitasDarah penerimaUang;
    private Request request;

    public Transaksi(Request request) {
        this.request = request;
        statusTransaksi=false;
        idFaktur="TF"+transaksiTerbuat;
    }

    private void hitungTotalBiaya(){
        totalBiaya=biayaKantongBiasa * (double) request.getForm().getJumlahKantong();
    }

    //faktur berdasarkan https://pmipangkalpinang.wordpress.com
    public void cetakFaktur(){
        int jumlahKantong = request.getForm().getJumlahKantong();
        
        int biayaJasaAdmin      = 20000*jumlahKantong;
        int biayaPemeliharaan   = 110120*jumlahKantong;
        int biayaKantongDarah   = 93772*jumlahKantong; 
        int biayaGolonganDarah  = 32479*jumlahKantong;
        int biayaReagensia      = 46278*jumlahKantong;
        
        int biayaHepatitisB     = 33480*jumlahKantong;
        int biayaHepatitisC     = 46114*jumlahKantong;
        int biayaSyphilis       = 34162*jumlahKantong;
        int biayaHiv            = 52743*jumlahKantong;
        
        int biayaPenunjang      = 20852*jumlahKantong;

        int totalBiaya = biayaJasaAdmin + biayaPemeliharaan + 
                         biayaKantongDarah + biayaGolonganDarah + biayaReagensia + 
                         biayaHepatitisB + biayaHepatitisC + biayaSyphilis + 
                         biayaHiv + biayaPenunjang;

        System.out.println("ID Faktur: "+idFaktur);
        String border = "+-----+----------------------------------------------------+--------------+";
        
        // Header Tabel
        System.out.println(border);
        System.out.printf("|  No.  |                      Keterangan                      |     Biaya      |%n");
        System.out.println(border);
        
        // Baris I
        System.out.printf("|   I   | %-52s | %12s |%n", "Darah","     Gratis    ");
        System.out.println(border);
        
        // Baris II
        System.out.printf("|   II  | %-52s | %12s |%n", "Jasa dan Administrasi", formatRupiah(biayaJasaAdmin));
        System.out.println(border);
        
        // Baris III
        System.out.printf("|  III  | %-52s | %12s |%n", "Pemeliharaan, Penyusutan Alat dan Pengembangan SDM", formatRupiah(biayaPemeliharaan));
        System.out.println(border);
        
        // Baris IV
        System.out.printf("|   IV  | %-52s |              |%n", "Kelompok Habis Pakai");
        System.out.println(border);
        System.out.printf("|       | %-52s | %12s |%n", "a. Kantong Darah", formatRupiah(biayaKantongDarah));
        System.out.println(border);
        System.out.printf("|       | %-52s | %12s |%n", "b. Golongan Darah,Rh dan HB", formatRupiah(biayaGolonganDarah));
        System.out.println(border);
        System.out.printf("|       | %-52s | %12s |%n", "c. Reagensia Pemeriksaan Uji Silang Serasi", formatRupiah(biayaReagensia));
        System.out.println(border);
        
        // Baris V
        System.out.printf("|   V   | %-52s |              |%n", "Screening *IMLTD dengan metode CLIA");
        System.out.println(border);
        System.out.printf("|       | %-52s | %12s |%n", "a. Hepatitis B (HBSAG)", formatRupiah(biayaHepatitisB));
        System.out.println(border);
        System.out.printf("|       | %-52s | %12s |%n", "b. Hepatitis C (HCV)", formatRupiah(biayaHepatitisC));
        System.out.println(border);
        System.out.printf("|       | %-52s | %12s |%n", "c. Syphilis (TPHA)", formatRupiah(biayaSyphilis));
        System.out.println(border);
        System.out.printf("|       | %-52s | %12s |%n", "d. HIV", formatRupiah(biayaHiv));
        System.out.println(border);
        
        // Baris VI
        System.out.printf("|   VI  | %-52s | %12s |%n", "Bahan Penunjang Lainnya", formatRupiah(biayaPenunjang));
        System.out.println(border);
        
        // Baris Total
        System.out.printf("|                            TOTAL                             | %12s |%n", formatRupiah(totalBiaya));
        System.out.println(border);
        System.out.println("Jumlah Kantong darah: "+jumlahKantong);
    }


    public static String formatRupiah(int nilai) {
        Locale lokasiIndo = Locale.forLanguageTag("id-ID");
        String angkaFormat = String.format(lokasiIndo, "%,d", nilai);
        
        return "Rp " + String.format("%11s", angkaFormat);
    }

    public void ubahStatusLunas(){
        statusTransaksi=true;
    }

    //#region

    public static double getBiayakantongbiasa() {
        return biayaKantongBiasa;
    }

    public static int getTransaksiTerbuat() {
        return transaksiTerbuat;
    }

    public static void setTransaksiTerbuat(int transaksiTerbuat) {
        Transaksi.transaksiTerbuat = transaksiTerbuat;
    }

    public String getIdFaktur() {
        return idFaktur;
    }

    public void setIdFaktur(String idFaktur) {
        this.idFaktur = idFaktur;
    }

    public LocalDateTime getWaktuTransaksi() {
        return waktuTransaksi;
    }

    public void setWaktuTransaksi(LocalDateTime waktuTransaksi) {
        this.waktuTransaksi = waktuTransaksi;
    }

    public double getTotalBiaya() {
        return totalBiaya;
    }

    public void setTotalBiaya(double totalBiaya) {
        this.totalBiaya = totalBiaya;
    }

    public boolean isStatusTransaksi() {
        return statusTransaksi;
    }

    public void setStatusTransaksi(boolean statusTransaksi) {
        this.statusTransaksi = statusTransaksi;
    }

    public FasilitasDarah getPenerimaUang() {
        return penerimaUang;
    }

    public void setPenerimaUang(FasilitasDarah penerimaUang) {
        this.penerimaUang = penerimaUang;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    //#endregion

}
