package App;

import java.util.Hashtable;

import User.User;

public class App {

    private User currentUser;
    private Hashtable <Integer,User> userHashtable = new Hashtable<>();
    // hashtable belum done aku bakal buat wrapper class per atribut biar nanti bisa filter dengan O(1) 

    public App(){
        onStartUp();
        loginAwal();
        tampilkanMenuUtama();
    }

    // buat baca data dari txt dulu
    private void onStartUp(){

    }

    private void loginAwal(){
        System.out.println("==== BLOODFLOW ====");
        System.out.println("");
    }

    private void tampilkanMenuUtama(){
        currentUser.tampilkanMenuUtama();
    }
}
