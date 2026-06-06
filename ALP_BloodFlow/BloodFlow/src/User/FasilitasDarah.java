package User;

import java.util.ArrayList;

import App.App;
import Request.Request;

public abstract class FasilitasDarah extends User {

    private ArrayList<Request> listRequest = new ArrayList<>();
    private String alamat;
    
    public FasilitasDarah(String username, String password, String noTelp, String nama, String alamat) {
        super(username, password, noTelp, nama);
        this.alamat=alamat;
    }

    private void makeRequest(App app){
        Request request = new Request(this);
        listRequest.add(request);
        request.menuRequest(app);
    }

    //#region Getter Setter

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    //#endregion

}
