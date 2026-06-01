package User;

import App.App;
import Model.Notification;

import java.util.ArrayList;

public abstract class User {

    protected String idPengguna,username,password,noTelp,nama;
    private static int userTerbuat = 0;
    private ArrayList<Notification> inbox = new ArrayList<>();

    public User(String username, String password, String noTelp,String nama) {
        userTerbuat++;
        idPengguna= "PD"+userTerbuat;
        this.username = username;
        this.password = password;
        this.noTelp = noTelp;
    }

    
    //abstract classes

    public abstract void tampilkanMenuUtama(App app);

    //#region Getter Setter

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

        public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
    //#endregion


    public static int getUserTerbuat() {
        return userTerbuat;
    }


    public static void setUserTerbuat(int userTerbuat) {
        User.userTerbuat = userTerbuat;
    }

 public ArrayList<Notification> getInbox() {
        return inbox;
    }


    public void setInbox(ArrayList<Notification> inbox) {
        this.inbox = inbox;
    }
    

    public void tambahNotifikasi(Notification pesan){
        inbox.add(pesan);
    }


   
}
