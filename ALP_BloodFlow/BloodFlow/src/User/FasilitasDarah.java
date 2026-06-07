package User;

import java.util.ArrayList;
import java.util.LinkedList;

import App.App;
import Darah.CrossMatch;
import Darah.KantongDarah;
import Darah.TesDarah;
import Enum.Provinsi;
import Enum.WilayahIndonesia;
import Request.Request;

public abstract class FasilitasDarah extends User {

    //ArrayLists
    private ArrayList<Request> listRequest = new ArrayList<>();
    private LinkedList<Request> listApproveRequest = new LinkedList<>();
    private ArrayList<Request> listRequestTuntas = new ArrayList<>();
    private ArrayList<TesDarah> listTesDarah = new ArrayList<>();
    private ArrayList<CrossMatch> listCrossMatch = new ArrayList<>();
    //LinkedLists
    private LinkedList<KantongDarah> stokDarah = new LinkedList<>() ;
    //normal
    private String alamat;
    
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

        }
        
    }

    private void testSample(App app, Request request){
        System.out.println("=== Test Sample ===");

    }

    private void pembayaran(){
        
    }

    public void tampilkanApprovedRequests(){
        System.out.println("=== Approved Requests ===");
        for (int i = 0; i < listApproveRequest.size(); i++) {
            System.out.println((i + 1) + " =====================");
            listApproveRequest.get(i).getForm().tampilkanForm();
            System.out.println();
        }
    }

    //#endregion

    //#region Getter Setter

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public ArrayList<Request> getListRequest() {
        return listRequest;
    }

    public LinkedList<Request> getListApproveRequest() {
        return listApproveRequest;
    }
    public void setListRequest(ArrayList<Request> listRequest) {
        this.listRequest = listRequest;
    }

    public void setListApproveRequest(LinkedList<Request> listApproveRequest) {
        this.listApproveRequest = listApproveRequest;
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

    public LinkedList<KantongDarah> getStokDarah() {
        return stokDarah;
    }

    public void setStokDarah(LinkedList<KantongDarah> stokDarah) {
        this.stokDarah = stokDarah;
    }

    //#endregion

}
