package User;

import java.util.ArrayList;

import App.App;
import Darah.CrossMatch;
import Darah.TesIMLTD;
import Enum.Provinsi;
import Enum.WilayahIndonesia;
import Request.Request;

public abstract class FasilitasDarah extends User {

    //ArrayLists
    private ArrayList<Request> listRequest = new ArrayList<>();
    private ArrayList<Request> listApproveRequest = new ArrayList<>();
    private ArrayList<TesIMLTD> listTesIMLTD = new ArrayList<>();
    private ArrayList<CrossMatch> listCrossMatch = new ArrayList<>();

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

    public ArrayList<Request> getListApproveRequest() {
        return listApproveRequest;
    }

    //#endregion

}
