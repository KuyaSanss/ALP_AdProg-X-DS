package HashTable;

import java.util.Hashtable;

import User.*;

public class DataUser {
    
    //all
    private Hashtable <String,User> daftarUser = new Hashtable<>();
    private Hashtable <String,User> daftarUsernameUser = new Hashtable<>();

    // User
    private Hashtable <Object,Object> daftarPendonor = new Hashtable<>();
    private Hashtable <Object,Object> daftarUDD = new Hashtable<>();
    private Hashtable <Object,Object> daftarBDRS = new Hashtable<>();
    private Hashtable <Object,Object> daftarAdmin = new Hashtable<>();

    //Pendonor
    private Hashtable <Object,Object> daftarGolDarahPendonor = new Hashtable<>();
    private Hashtable <Object,Object> daftarRhesusPendonor = new Hashtable<>();
    private Hashtable <Object,Object> daftarTanggalTerakhirDonorPendonor = new Hashtable<>();

    // UDD
    private Hashtable <Object,Object> daftarAlamatUDD = new Hashtable<>();

    // BDRS
    private Hashtable <Object,Object> daftarAlamatBDRS = new Hashtable<>();

    public DataUser(){}

    public void insertUser(User user){
        //all
        daftarUser.put(user.getIdPengguna(),user);
        daftarUser.put(user.getUsername(),user);
        //specific
        if(user instanceof Pendonor){
            insertPendonor(user);
        }else if(user instanceof UDD){
            insertPendonor(user);
        }else if(user instanceof BDRS){
            Pendonor pendonor = (Pendonor) user;
            daftarBDRS.put(pendonor.getIdPengguna(),pendonor);
        }else if(user instanceof Admin){
            Pendonor pendonor = (Pendonor) user;
            daftarAdmin.put(pendonor.getIdPengguna(),pendonor);
        }
    }

    private void insertPendonor(User user){
        Pendonor pendonor = (Pendonor) user;
        daftarPendonor.put(user.getIdPengguna(),user);
        daftarGolDarahPendonor.put(pendonor.getGolDarah(), pendonor);
        daftarRhesusPendonor.put(pendonor.getRhesus(),pendonor);
        daftarTanggalTerakhirDonorPendonor.put(pendonor.getTanggalTerakhirDonor(), pendonor);
    }

    private void insertUDD(User user){
        UDD uDD = (UDD) user;
        daftarUDD.put(uDD.getIdPengguna(),uDD);
    }

    private void insertBDRS(User user){
        
    }

    private void insertAdmin(User user){
        
    }

    
    //#region Getter
    public Hashtable<String, User> getDaftarUser() {
        return daftarUser;
    }

    public Hashtable<String, User> getDaftarUsernameUser() {
        return daftarUsernameUser;
    }

    public Hashtable<Object, Object> getDaftarPendonor() {
        return daftarPendonor;
    }

    public Hashtable<Object, Object> getDaftarUDD() {
        return daftarUDD;
    }

    public Hashtable<Object, Object> getDaftarBDRS() {
        return daftarBDRS;
    }

    public Hashtable<Object, Object> getDaftarAdmin() {
        return daftarAdmin;
    }

    public Hashtable<Object, Object> getDaftarGolDarahPendonor() {
        return daftarGolDarahPendonor;
    }

    public Hashtable<Object, Object> getDaftarRhesusPendonor() {
        return daftarRhesusPendonor;
    }

    public Hashtable<Object, Object> getDaftarTanggalTerakhirDonorPendonor() {
        return daftarTanggalTerakhirDonorPendonor;
    }

    public Hashtable<Object, Object> getDaftarAlamatUDD() {
        return daftarAlamatUDD;
    }

    public Hashtable<Object, Object> getDaftarAlamatBDRS() {
        return daftarAlamatBDRS;
    }

    //#endregion


}
