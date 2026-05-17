package HashTable;

import java.util.Hashtable;

import Enum.*;
import User.*;

public class DataUser {
    
    //all
    private Hashtable <String,User> daftarUser = new Hashtable<>();
    private Hashtable <String,User> daftarUsernameUser = new Hashtable<>();

    // User
    private Hashtable <String,User> daftarPendonor = new Hashtable<>();
    private Hashtable <String,User> daftarUDD = new Hashtable<>();
    private Hashtable <String,User> daftarBDRS = new Hashtable<>();
    private Hashtable <String,User> daftarAdmin = new Hashtable<>();

    //Pendonor
    private Hashtable <golDarahEnum,User> daftarGolDarahPendonor = new Hashtable<>();
    private Hashtable <rhesusEnum,User> daftarRhesusPendonor = new Hashtable<>();
    private Hashtable <String,User> daftarTanggalTerakhirDonorPendonor = new Hashtable<>();

    // UDD
    private Hashtable <String,User> daftarAlamatUDD = new Hashtable<>();

    // BDRS
    private Hashtable <String,User> daftarAlamatBDRS = new Hashtable<>();

    public DataUser(){}

    //#region insert
    public void insertUser(User user){
        //all
        daftarUser.put(user.getIdPengguna(),user);
        daftarUser.put(user.getUsername(),user);
        //specific
        if(user instanceof Pendonor){
            insertPendonor(user);
        }else if(user instanceof UDD){
            insertUDD(user);
        }else if(user instanceof BDRS){
            insertBDRS(user);
        }else if(user instanceof Admin){
            insertAdmin(user);
        }
    }

    private void insertPendonor(User user){
        Pendonor pendonor = (Pendonor) user;
        daftarPendonor.put(pendonor.getIdPengguna(),pendonor);
        daftarGolDarahPendonor.put(pendonor.getGolDarah(), pendonor);
        daftarRhesusPendonor.put(pendonor.getRhesus(),pendonor);
        daftarTanggalTerakhirDonorPendonor.put(pendonor.getTanggalTerakhirDonor(), pendonor);
    }

    private void insertUDD(User user){
        UDD uDD = (UDD) user;
        daftarUDD.put(uDD.getIdPengguna(),uDD);
    }

    private void insertBDRS(User user){
        BDRS bDRS = (BDRS) user;
        daftarBDRS.put(bDRS.getIdPengguna(),bDRS);
    }

    private void insertAdmin(User user){
        Admin admin = (Admin) user;
        daftarAdmin.put(admin.getIdPengguna(),admin);
    }

    //#endregion

    //#region delete

    public void deleteUser(User user){
        //all
        daftarUser.remove(user.getIdPengguna());
        daftarUser.remove(user.getUsername(),user);
        //specific
        if(user instanceof Pendonor){
            deletePendonor(user);
        }else if(user instanceof UDD){
            deleteUDD(user);
        }else if(user instanceof BDRS){
            deleteBDRS(user);
        }else if(user instanceof Admin){
            deleteAdmin(user);
        }
    }

    private void deletePendonor(User user){
        Pendonor pendonor = (Pendonor) user;
        daftarPendonor.remove(pendonor.getIdPengguna(),pendonor);
        daftarGolDarahPendonor.remove(pendonor.getGolDarah(), pendonor);
        daftarRhesusPendonor.remove(pendonor.getRhesus(),pendonor);
        daftarTanggalTerakhirDonorPendonor.remove(pendonor.getTanggalTerakhirDonor(), pendonor);
    }

    private void deleteUDD(User user){
        UDD uDD = (UDD) user;
        daftarUDD.remove(uDD.getIdPengguna(),uDD);
    }

    private void deleteBDRS(User user){
        BDRS bDRS = (BDRS) user;
        daftarBDRS.remove(bDRS.getIdPengguna(),bDRS);
    }

    private void deleteAdmin(User user){
        Admin admin = (Admin) user;
        daftarAdmin.remove(admin.getIdPengguna(),admin);
    }

    //#endregion
    
    //#region Getter
    
    public Hashtable<String, User> getDaftarUser() {
        return daftarUser;
    }

    public Hashtable<String, User> getDaftarUsernameUser() {
        return daftarUsernameUser;
    }

    public Hashtable<String, User> getDaftarPendonor() {
        return daftarPendonor;
    }

    public Hashtable<String, User> getDaftarUDD() {
        return daftarUDD;
    }

    public Hashtable<String, User> getDaftarBDRS() {
        return daftarBDRS;
    }

    public Hashtable<String, User> getDaftarAdmin() {
        return daftarAdmin;
    }

    public Hashtable<golDarahEnum, User> getDaftarGolDarahPendonor() {
        return daftarGolDarahPendonor;
    }

    public Hashtable<rhesusEnum, User> getDaftarRhesusPendonor() {
        return daftarRhesusPendonor;
    }

    public Hashtable<String, User> getDaftarTanggalTerakhirDonorPendonor() {
        return daftarTanggalTerakhirDonorPendonor;
    }

    public Hashtable<String, User> getDaftarAlamatUDD() {
        return daftarAlamatUDD;
    }

    public Hashtable<String, User> getDaftarAlamatBDRS() {
        return daftarAlamatBDRS;
    }

    //#endregion


}
