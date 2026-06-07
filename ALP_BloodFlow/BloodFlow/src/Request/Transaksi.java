package Request;

import java.time.LocalDateTime;

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

    private void hitungTotalBiaya(){
        totalBiaya=biayaKantongBiasa * (double) request.getForm().getJumlahKantong();
    }

    private void cetakFaktur(){
// todo
    }

    private void ubahStatusLunas(){
        // todo
    }

}
