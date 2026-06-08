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

    //ArrayLists
    protected ArrayList<Request> listRequestTuntas = new ArrayList<>();
    protected ArrayList<TesDarah> listTesDarah = new ArrayList<>();
    protected ArrayList<CrossMatch> listCrossMatch = new ArrayList<>();
    protected ArrayList<Transaksi> listTransaksiTuntas = new ArrayList<>();
    protected ArrayList<Request> listApproveRequestTuntas = new ArrayList<>();
    //LinkedLists
    protected LinkedList<Request> listRequest = new LinkedList<>();
    protected LinkedList<KantongDarah> stokDarah = new LinkedList<>();
    protected LinkedList<Request> listApproveRequest = new LinkedList<>();//approve it yang buat fasilitas darah lain
    protected LinkedList<Transaksi> listTransaksi = new LinkedList<>();
    //normal
    protected String alamat;

    public FasilitasDarah(App app, String username, String password, String noTelp, String nama, String alamat,Provinsi provinsi, WilayahIndonesia wilayahIndonesia) {
        super(app,username, password, noTelp, nama,provinsi,wilayahIndonesia);
        this.alamat=alamat;
    }

    //#region protected

    protected void makeRequest(App app){
        Request request = new Request(this);
        listRequest.add(request);
        request.menuRequest(app);
    }

    public void checkRequest(App app) {
        Request request = Request.displayRequests(app);
        request.approveRequest(app,this);
        tampilkanMenuUtama(app);
    }

    private void checkApprovedRequest(App app) {
        tampilkanApprovedRequests();

        if (listApproveRequest.isEmpty()) {
            System.out.println("Belum ada request yang diApprove");
            return;  // or continue to menu, as needed
        }

        int size = listApproveRequest.size();
        int pilihan=-1;
        while (true) {
            System.out.print("Pilih (1-" + size + "): ");
            String input = app.getSc().nextLine().trim();

            // 1. Check for empty input
            if (input.isEmpty()) {
                System.out.println("Error: Input tidak boleh kosong.\n");
                continue;
            }
            boolean isNumeric = true;
            for (int i = 0; i < input.length(); i++) {
                if (!Character.isDigit(input.charAt(i))) {
                    isNumeric = false;
                    break;
                }
            }

            if (!isNumeric) {
                System.out.println("Error: Input harus berupa angka (tidak boleh huruf atau simbol).\n");
                continue;
            }

            pilihan = Integer.parseInt(input);

            if (pilihan < 1 || pilihan > size) {
                System.out.println("Error: Pilihan di luar jangkauan. Masukkan angka antara 1 dan " + size + ".\n");
                continue;
            }

            break; 
        }

        Request selected = listApproveRequest.get(pilihan - 1);
        if(selected.getForm().getPermintaanPasien()){//true=pasien
            testSample(app, selected);
        }else{
            pembayaran();
        }
        
    }

    private void testSample(App app, Request request){
        String input;
        System.out.println("""
                === Test Sample ===
                1. Tes IMLTD
                2. CrossMatch
                3. Exit
                """);

        System.out.print("Input: ");
        input = app.getSc().next() + app.getSc().nextLine();
        switch (input) {
            case "1":
                menuTesIMLTD(app,request);
                break;
            case "2":
                menuCrossMatch(app,request);
                break;
            case "3":
                app.getCurrentUser().tampilkanMenuUtama(app);
                break;
            default:
                System.out.println("Invalid Input!!");
                testSample(app, request);
        }

    }

    private void menuTesIMLTD(App app,Request request){
        boolean pernah=false;
        for(TesDarah td:  listTesDarah){
            if (td instanceof TesIMLTD){
                pernah =true;
                System.out.println("Tes IMLTD sudah pernah dilakukan\n");
                td.cetakHasil();
                break;
            }
        }
        //tes
        TesIMLTD tesIMLTD = new TesIMLTD(this, );
        //todo
    }

    private void menuCrossMatch(App app,Request request){
        //todo
    }

    private void pembayaran(App app,Request request){
        Transaksi transaksi = new Transaksi(request);
    }

    private void pengiriman(){

    }

    public void tampilkanApprovedRequests(){
        System.out.println("=== Approved Requests ===");
        for (int i = 0; i < listApproveRequest.size(); i++) {
            System.out.println((i + 1) + " =====================");
            listApproveRequest.get(i).getForm().tampilkanForm();
            System.out.println();
        }
    }

    protected KantongDarah cekStokDarah(App app){

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
        KantongDarah output=null;
        while(true){
            System.out.print("Input ID Kantong Darah (masukan \"exit\" untuk exit): ");
            String input = app.getSc().next() + app.getSc().nextLine();

            // 1. Validasi jika input kosong
            if (input.trim().isEmpty()) {
                System.out.println("[Error] ID tidak boleh kosong! Silakan coba lagi.\n");
                continue;
            }
            
            // 2. Validasi fitur EXIT menggunakanequalsIgnoreCase agar bisa "exit" atau "EXIT"
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

    private KantongDarah apakahKantongDarahAda(String idKantong){
        for(KantongDarah d:stokDarah){
            if(d.getIdDarah().equalsIgnoreCase(idKantong))return d;
        }
        return null;
    }

    private int hitungStokDarah(golDarahEnum golDarah,rhesusEnum rhesus){
        int count=0;
        for (KantongDarah d:stokDarah){
            if(d.getJenisDarah()==golDarah && d.getRhesus()==rhesus && d.getRequest()==null){
                count++;
            }
        }
        return count;
    }

    private void tampilFilterBerdasarkanGolDarahRhesus(golDarahEnum golDarah,rhesusEnum rhesus){
        if(hitungStokDarah(golDarah, rhesus)==0){
            System.out.println("Tidak ada Kantong Darah");
            return;
        }
        int i=0;
        for(KantongDarah d: stokDarah){
            if(d.getJenisDarah()==golDarah && d.getRhesus()==rhesus && d.getRequest()==null){
                System.out.println(i+"-------");
                d.tampilkanDataKantongDarah();
            }
        }
    }

    //#endregion

    //#region Getter Setter

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

    //#endregion

}
