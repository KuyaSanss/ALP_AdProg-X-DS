package User;

import java.util.ArrayList;
import java.util.LinkedList;

import App.App;
import Darah.CrossMatch;
import Darah.KantongDarah;
import Darah.TesDarah;
import Darah.TesIMLTD;
import Enum.Provinsi;
import Enum.WilayahIndonesia;
import Enum.golDarahEnum;
import Enum.rhesusEnum;
import Request.Request;
import Request.Transaksi;

public abstract class FasilitasDarah extends User {

    // ArrayLists
    protected ArrayList<Request> listRequestTuntas = new ArrayList<>();
    protected ArrayList<TesDarah> listTesDarah = new ArrayList<>();
    protected ArrayList<CrossMatch> listCrossMatch = new ArrayList<>();
    protected ArrayList<Transaksi> listTransaksiTuntas = new ArrayList<>();
    protected ArrayList<Request> listApproveRequestTuntas = new ArrayList<>();
    // LinkedLists
    protected LinkedList<Request> listRequest = new LinkedList<>();
    protected LinkedList<KantongDarah> stokDarah = new LinkedList<>();
    protected LinkedList<Request> listApproveRequest = new LinkedList<>();// approve it yang buat fasilitas darah lain
    protected LinkedList<Transaksi> listTransaksi = new LinkedList<>();
    // normal
    protected String alamat;

    public FasilitasDarah(App app, String username, String password, String noTelp, String nama, String alamat,
            Provinsi provinsi, WilayahIndonesia wilayahIndonesia) {
        super(app, username, password, noTelp, nama, provinsi, wilayahIndonesia);
        this.alamat = alamat;
    }



    protected void makeRequest(App app) {
        Request request = new Request(this);
        listRequest.add(request);
        request.buatRequest(app);
    }

    public void checkLiveRequest(App app) {
        Request request = Request.displayRequests(app);
        if (request != null) {
            boolean approved = request.approveRequest(app, this);
            if (approved) {
                testSample(app, request);
            }
        }
    }

    protected void menuPermintaan(App app){
        System.out.println("=== Menu Permintaan ===");
        System.out.println("1. Cek Permintaan Global");
        System.out.println("2. Cek Permintaan Pribadi");
        System.out.println("3. Cek Permintaan Luar yg diApprove");
        System.out.println("4. Buat Permintaan");
        System.out.println("5. Cek Stok darah pribadi");
        System.out.println("6. Cek Stok darah fasilitas kesehatan" );
        System.out.println("0. Kembali ke Menu");
        String input;
        while(true){
            System.out.print("Input: ");
            input=app.getSc().next()+app.getSc().nextLine();

            switch (input) {
                case "0":
                    tampilkanMenuUtama(app);
                    return;
                case "1":
                    checkLiveRequest(app);
                    break;
                case "2":
                    cekPermintaanPribadi(app);
                    break;
                case "3":
                    cekPermintaanDiApprove(app);
                    break;
                case "4":
                    makeRequest(app);
                    break;
                case "5":
                    cekStokDarah(app);
                    break;
                default:
                    System.out.println("Input tidak sesuai!");
                    break;
            }
            System.out.println("=== Menu Permintaan ===");
            System.out.println("1. Cek Permintaan Global");
            System.out.println("2. Cek Permintaan Pribadi");
            System.out.println("3. Cek Permintaan Luar yg diApprove");
            System.out.println("4. Buat Permintaan");
            System.out.println("5. Cek Stok darah pribadi");
            System.out.println("6. Cek Stok darah fasilitas kesehatan" );
            System.out.println("0. Kembali ke Menu");
        }
    }

    private void cekPermintaanPribadi(App app) {
        if (listRequest.isEmpty()) {
            System.out.println("Belum ada permintaan pribadi.");
            return;
        }
        System.out.println("=== Permintaan Pribadi ===");
        for (int i = 0; i < listRequest.size(); i++) {
            System.out.println((i + 1) + ". Request ID: " + listRequest.get(i).getIdPermintaan());
            listRequest.get(i).tampilkanRequest();
        }
        System.out.print("Pilih nomor request untuk cek transaksi (0 untuk kembali): ");
        String input = app.getSc().nextLine().trim();
        if(input.equals("0") || input.isEmpty()) return;
        
        try {
            int pil = Integer.parseInt(input);
            if (pil > 0 && pil <= listRequest.size()) {
                Request req = listRequest.get(pil - 1);
                if (req.isDone()) {
                    System.out.println("Permintaan ini sudah selesai (Darah telah diterima).");
                } else if (req.getFasilitasDarahApprove() != null && req.getTransaksi() != null) {
                    if (req.getTransaksi().getKodeTransaksi() != null) {
                        System.out.println("Anda sudah menginput pembayaran. Menunggu verifikasi dari " + req.getFasilitasDarahApprove().getNama() + ".");
                    } else {
                        bayarPermintaan(app, req);
                    }
                } else {
                    System.out.println("Permintaan ini belum sampai ke tahap transaksi atau belum di-approve.");
                }
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Pilihan harus berupa angka.");
        }
    }

    private void bayarPermintaan(App app, Request request) {
        System.out.println("\n=== PEMBAYARAN TRANSAKSI (PEMINTA) ===");
        request.getTransaksi().cetakFaktur();
        System.out.println("Silahkan transfer ke Rekening: " + request.getTransaksi().getRekening());
        System.out.print("Masukkan Kode Transaksi: ");
        String kode = app.getSc().nextLine().trim();
        System.out.print("Masukkan Kode Unik (2 digit): ");
        String unik = app.getSc().nextLine().trim();
        
        request.getTransaksi().setKodeTransaksi(kode);
        request.getTransaksi().setKodeUnik(unik);
        System.out.println("Data pembayaran berhasil disimpan.");
    }

    private void cekPermintaanDiApprove(App app) {
        if (listApproveRequest.isEmpty()) {
            System.out.println("Belum ada request yang diApprove.");
            return;
        }
        System.out.println("=== Permintaan Luar yg diApprove ===");
        for (int i = 0; i < listApproveRequest.size(); i++) {
            System.out.println((i + 1) + ". Request ID: " + listApproveRequest.get(i).getIdPermintaan());
            listApproveRequest.get(i).tampilkanRequest();
        }
        System.out.print("Pilih nomor request untuk verifikasi (0 untuk kembali): ");
        String input = app.getSc().nextLine().trim();
        if(input.equals("0") || input.isEmpty()) return;
        
        try {
            int pil = Integer.parseInt(input);
            if (pil > 0 && pil <= listApproveRequest.size()) {
                Request req = listApproveRequest.get(pil - 1);
                if (req.isDone()) {
                    System.out.println("Permintaan ini sudah selesai.");
                } else if (req.getTransaksi() != null && req.getTransaksi().getKodeTransaksi() != null) {
                    if (!req.getTransaksi().isStatusTransaksi()) {
                        verifikasiPembayaran(app, req);
                    } else {
                        System.out.println("Transaksi sudah lunas.");
                    }
                } else {
                    System.out.println("Menunggu peminta melakukan pembayaran.");
                }
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Pilihan harus berupa angka.");
        }
    }

    private void verifikasiPembayaran(App app, Request request) {
        System.out.println("\n=== VERIFIKASI PEMBAYARAN ===");
        System.out.println("Kode Transaksi dari Peminta: " + request.getTransaksi().getKodeTransaksi());
        System.out.print("Masukkan Kode Unik untuk verifikasi: ");
        String unik = app.getSc().nextLine().trim();
        
        if (unik.equals(request.getTransaksi().getKodeUnik())) {
            System.out.println("Verifikasi Berhasil! Pembayaran Lunas.");
            request.getTransaksi().ubahStatusLunas();
            pengiriman(request);
        } else {
            System.out.println("Kode Unik salah! Verifikasi gagal.");
        }
    }

    private void testSample(App app, Request request) {
        System.out.println("\n=== MELAKUKAN TES DARAH (IMLTD) ===");
        menuTesIMLTD(app, request);
        System.out.println("\n=== MELAKUKAN TES DARAH (CrossMatch) ===");
        menuCrossMatch(app, request);
        
        System.out.println("\nApakah hasil tes IMLTD dan CrossMatch aman?");
        System.out.println("1. Ya, Aman");
        System.out.println("2. Tidak, Gagal");
        String input;
        while(true) {
            System.out.print("Input: ");
            input = app.getSc().nextLine().trim();
            if (input.equals("1") || input.equals("2")) {
                break;
            }
            System.out.println("Input tidak valid!");
        }

        if (input.equals("1")) {
            System.out.println("Tes berhasil. Lanjut ke transaksi...");
            lanjutTransaksiApprover(app, request);
        } else {
            tesGagal(request);
        }
    }

    private void menuTesIMLTD(App app, Request request) {
        KantongDarah kantongDarah = cekStokDarah(app);
        if (kantongDarah != null) {
            TesIMLTD tesIMLTD = new TesIMLTD(this, kantongDarah.getSampelDarah());
            tesIMLTD.formInput(app);
            listTesDarah.add(tesIMLTD);
            request.getListTesDarah().add(tesIMLTD);
            System.out.println("Tes IMLTD berhasil ditambahkan");
        }
    }

    private void menuCrossMatch(App app, Request request) {
        KantongDarah kantongDarah = cekStokDarah(app);
        if (kantongDarah != null) {
            CrossMatch crossMatch = new CrossMatch(this, kantongDarah.getSampelDarah(), request.getSampelDarahPeminta());
            crossMatch.formInput(app);
            listTesDarah.add(crossMatch);
            request.getListTesDarah().add(crossMatch);
            System.out.println("Crossmatch berhasil ditambahkan");
        }
    }

    private void lanjutTransaksiApprover(App app, Request request) {
        System.out.println("\n=== TRANSAKSI (PENG-APPROVE) ===");
        Transaksi transaksi = new Transaksi(request);
        System.out.print("Masukkan Rekening Anda: ");
        String rekening = app.getSc().nextLine();
        transaksi.setRekening(rekening);
        transaksi.setPenerimaUang(this);
        request.setTransaksi(transaksi);
        System.out.println("Rekening berhasil diisi. Menunggu peminta mengisi kode transaksi.");
    }

    private void tesGagal(Request request) {
        System.out.println("Tes gagal. Mengembalikan permintaan ke daftar global...");
        listApproveRequest.remove(request);
        request.setFasilitasDarahApprove(null);
        if (request.getListTesDarah() != null) {
            request.getListTesDarah().clear();
        }
        Request.getLiveRequestList().add(request);
    }

    private void pengiriman(Request request) {
        System.out.println("\n=== PENGIRIMAN ===");
        System.out.println("Mengirim dari: " + this.getNama());
        System.out.println("Kepada Penerima: " + request.getFasilitasDarahPeminta().getNama());
        request.setDone(true);
        this.listTransaksiTuntas.add(request.getTransaksi());
        this.listApproveRequestTuntas.add(request);
        request.getFasilitasDarahPeminta().getListRequestTuntas().add(request);
        System.out.println("Pengiriman selesai!");
    }

    public void tampilkanApprovedRequests() {
        System.out.println("=== Approved Requests ===");
        for (int i = 0; i < listApproveRequest.size(); i++) {
            System.out.println((i + 1) + " =====================");
            listApproveRequest.get(i).getForm().tampilkanForm();
            System.out.println();
        }
    }

    //#region cekStokDarah

    protected KantongDarah cekStokDarah(App app) {

        System.out.println("=== Stok Darah ===");
        System.out.println();
        System.out.println("Jenis darah |  Jumlah Kantong");
        System.out.println("-----------------------------------");
        System.out.println("A+          |   " + hitungStokDarah(golDarahEnum.A, rhesusEnum.POSITIVE));
        System.out.println("A-          |   " + hitungStokDarah(golDarahEnum.A, rhesusEnum.NEGATIVE));
        System.out.println("B+          |   " + hitungStokDarah(golDarahEnum.B, rhesusEnum.POSITIVE));
        System.out.println("B-          |   " + hitungStokDarah(golDarahEnum.B, rhesusEnum.NEGATIVE));
        System.out.println("AB+         |   " + hitungStokDarah(golDarahEnum.AB, rhesusEnum.POSITIVE));
        System.out.println("AB-         |   " + hitungStokDarah(golDarahEnum.AB, rhesusEnum.NEGATIVE));
        System.out.println("O+          |   " + hitungStokDarah(golDarahEnum.O, rhesusEnum.POSITIVE));
        System.out.println("O-          |   " + hitungStokDarah(golDarahEnum.O, rhesusEnum.NEGATIVE));
        System.out.println("====================================");
        System.out.println();
        System.out.println("List Kantong Darah A+");
        tampilFilterBerdasarkanGolDarahRhesus(golDarahEnum.A, rhesusEnum.POSITIVE);
        System.out.println("-----------------------------------");
        System.out.println("List Kantong Darah A-");
        tampilFilterBerdasarkanGolDarahRhesus(golDarahEnum.A, rhesusEnum.NEGATIVE);
        System.out.println("-----------------------------------");
        System.out.println("List Kantong Darah B+");
        tampilFilterBerdasarkanGolDarahRhesus(golDarahEnum.B, rhesusEnum.POSITIVE);
        System.out.println("-----------------------------------");
        System.out.println("List Kantong Darah B-");
        tampilFilterBerdasarkanGolDarahRhesus(golDarahEnum.B, rhesusEnum.NEGATIVE);
        System.out.println("-----------------------------------");
        System.out.println("List Kantong Darah AB+");
        tampilFilterBerdasarkanGolDarahRhesus(golDarahEnum.AB, rhesusEnum.POSITIVE);
        System.out.println("-----------------------------------");
        System.out.println("List Kantong Darah AB-");
        tampilFilterBerdasarkanGolDarahRhesus(golDarahEnum.AB, rhesusEnum.NEGATIVE);
        System.out.println("-----------------------------------");
        System.out.println("List Kantong Darah O+");
        tampilFilterBerdasarkanGolDarahRhesus(golDarahEnum.O, rhesusEnum.POSITIVE);
        System.out.println("-----------------------------------");
        System.out.println("List Kantong Darah O-");
        tampilFilterBerdasarkanGolDarahRhesus(golDarahEnum.O, rhesusEnum.NEGATIVE);
        System.out.println("-----------------------------------");
        KantongDarah output = null;
        while (true) {
            System.out.print("Input ID Kantong Darah (masukan \"exit\" untuk exit): ");
            String input = app.getSc().next() + app.getSc().nextLine();

            // 1. Validasi jika input kosong
            if (input.trim().isEmpty()) {
                System.out.println("[Error] ID tidak boleh kosong! Silakan coba lagi.\n");
                continue;
            }

            // 2. Validasi fitur EXIT menggunakanequalsIgnoreCase agar bisa "exit" atau
            // "EXIT"
            if (input.trim().equalsIgnoreCase("exit")) {
                System.out.println("Keluar dari pengecekan stok darah...");
                app.getCurrentUser().tampilkanMenuUtama(app);
                break;
            }

            // 3. Cari kantong darah berdasarkan ID
            output = apakahKantongDarahAda(input);

            // 4. Validasi jika data tidak ditemukan
            if (output == null) {
                System.out.println("[Error] ID Kantong Darah '" + input + "' tidak ditemukan! Silakan coba lagi.\n");
                continue;
            }

            System.out.println("Kantong Darah berhasil ditemukan!");
            break;
        }
        return output;
    }

    private KantongDarah apakahKantongDarahAda(String idKantong) {
        for (KantongDarah d : stokDarah) {
            if (d.getIdDarah().equalsIgnoreCase(idKantong))
                return d;
        }
        return null;
    }

    private int hitungStokDarah(golDarahEnum golDarah, rhesusEnum rhesus) {
        int count = 0;
        for (KantongDarah d : stokDarah) {
            if (d.getJenisDarah() == golDarah && d.getRhesus() == rhesus && d.getRequest() == null) {
                count++;
            }
        }
        return count;
    }

    private void tampilFilterBerdasarkanGolDarahRhesus(golDarahEnum golDarah, rhesusEnum rhesus) {
        if (hitungStokDarah(golDarah, rhesus) == 0) {
            System.out.println("Tidak ada Kantong Darah");
            return;
        }
        int i = 0;
        for (KantongDarah d : stokDarah) {
            if (d.getJenisDarah() == golDarah && d.getRhesus() == rhesus && d.getRequest() == null) {
                System.out.println(i + "-------");
                d.tampilkanDataKantongDarah();
            }
        }
    }

    //#endregion

    // #region Getter Setter

    public ArrayList<Request> getListRequestTuntas() {
        return listRequestTuntas;
    }

    public void setListRequestTuntas(ArrayList<Request> listRequestTuntas) {
        this.listRequestTuntas = listRequestTuntas;
    }

    public ArrayList<TesDarah> getListTesDarah() {
        return listTesDarah;
    }

    public void setListTesDarah(ArrayList<TesDarah> listTesDarah) {
        this.listTesDarah = listTesDarah;
    }

    public ArrayList<CrossMatch> getListCrossMatch() {
        return listCrossMatch;
    }

    public void setListCrossMatch(ArrayList<CrossMatch> listCrossMatch) {
        this.listCrossMatch = listCrossMatch;
    }

    public ArrayList<Transaksi> getListTransaksiTuntas() {
        return listTransaksiTuntas;
    }

    public void setListTransaksiTuntas(ArrayList<Transaksi> listTransaksiTuntas) {
        this.listTransaksiTuntas = listTransaksiTuntas;
    }

    public ArrayList<Request> getListApproveRequestTuntas() {
        return listApproveRequestTuntas;
    }

    public void setListApproveRequestTuntas(ArrayList<Request> listApproveRequestTuntas) {
        this.listApproveRequestTuntas = listApproveRequestTuntas;
    }

    public LinkedList<Request> getListRequest() {
        return listRequest;
    }

    public void setListRequest(LinkedList<Request> listRequest) {
        this.listRequest = listRequest;
    }

    public LinkedList<KantongDarah> getStokDarah() {
        return stokDarah;
    }

    public void setStokDarah(LinkedList<KantongDarah> stokDarah) {
        this.stokDarah = stokDarah;
    }

    public LinkedList<Request> getListApproveRequest() {
        return listApproveRequest;
    }

    public void setListApproveRequest(LinkedList<Request> listApproveRequest) {
        this.listApproveRequest = listApproveRequest;
    }

    public LinkedList<Transaksi> getListTransaksi() {
        return listTransaksi;
    }

    public void setListTransaksi(LinkedList<Transaksi> listTransaksi) {
        this.listTransaksi = listTransaksi;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    // #endregion

}
