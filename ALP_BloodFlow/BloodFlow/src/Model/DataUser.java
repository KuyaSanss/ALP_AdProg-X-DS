package Model;

import java.util.Hashtable;

import Enum.*;
import User.*;

public class DataUser {
    
    // User
    private Hashtable <String,User> daftarUser = new Hashtable<>();//id pengguna
    private Hashtable <String,User> daftarUsernameUser = new Hashtable<>();//username user
    private Hashtable <Provinsi,User> daftarProvinsiUser = new Hashtable<>();//provinsi
    private Hashtable <WilayahIndonesia,User> daftarWilayahIndonesiaUser = new Hashtable<>();//wilayah indonesia

    // Jenis User
    private Hashtable <String,Pendonor> daftarPendonor = new Hashtable<>();//id pengguna
    private Hashtable <String,UDD> daftarUDD = new Hashtable<>();//id pengguna
    private Hashtable <String,BDRS> daftarBDRS = new Hashtable<>();//id pengguna
    private Hashtable <String,Admin> daftarAdmin = new Hashtable<>();//id pengguna

    //Pendonor
    private Hashtable <golDarahEnum,Pendonor> daftarGolDarahPendonor = new Hashtable<>();//goldarah
    private Hashtable <rhesusEnum,Pendonor> daftarRhesusPendonor = new Hashtable<>();//rhesus
    private Hashtable <Provinsi,Pendonor> daftarProvinsiPendonor = new Hashtable<>();//provinsi
    private Hashtable <WilayahIndonesia,Pendonor> daftarWilayahIndonesiaPendonor = new Hashtable<>();//wilayah indonesia
    //todo
    FilterWilayahIndonesia_GolonganDarahRhesus filterWilayahIndonesia_GolonganDarahRhesus= new FilterWilayahIndonesia_GolonganDarahRhesus();
    FilterProvinsi_GolonganDarahRhesus filterProvinsi_GolonganDarahRhesus = new FilterProvinsi_GolonganDarahRhesus();
    // UDD
    private Hashtable <String,UDD> daftarAlamatUDD = new Hashtable<>();//alamat
    private Hashtable <Provinsi,UDD> daftarProvinsiUDD = new Hashtable<>();//provinsi
    private Hashtable <WilayahIndonesia,UDD> daftarWilayahIndonesiaUDD = new Hashtable<>();//wilayah indonesia
    // BDRS
    private Hashtable <String,BDRS> daftarAlamatBDRS = new Hashtable<>();//alamat
    private Hashtable <Provinsi,BDRS> daftarProvinsiBDRS = new Hashtable<>();//provinsi
    private Hashtable <WilayahIndonesia,BDRS> daftarWilayahIndonesiaBDRS = new Hashtable<>();//wilayah indonesia

    //constructor kecil
    public DataUser(){}

    //inner class

    private class GolonganDarahRhesus{

        private Hashtable<golDarahEnum,Hashtable<rhesusEnum,Pendonor>> daftarGolonganDarahRhesusPendonor = new Hashtable<>();

        private GolonganDarahRhesus(){
            for(golDarahEnum gol : golDarahEnum.values()){
                daftarGolonganDarahRhesusPendonor.put(gol, new Hashtable<>());
            }
        }

        private void insertPendonor(Pendonor pendonor){
            Hashtable<rhesusEnum,Pendonor> inner= daftarGolonganDarahRhesusPendonor.get(pendonor.getGolDarah());
            inner.put(pendonor.getRhesus(),pendonor);
        }

    }

    private class FilterWilayahIndonesia_GolonganDarahRhesus{

        private Hashtable<WilayahIndonesia,GolonganDarahRhesus> table = new Hashtable<>();

        private FilterWilayahIndonesia_GolonganDarahRhesus(){
            for(WilayahIndonesia w : WilayahIndonesia.values()){
                table.put(w, new GolonganDarahRhesus());
            }
        }

        private void insertPendonor(Pendonor pendonor){



        }

    }

    private class FilterProvinsi_GolonganDarahRhesus{

        private FilterProvinsi_GolonganDarahRhesus(){

        }

        private void insertPendonor(){

        }

    }

    //#region insert
    public void insertUser(User user){
        //all
        daftarUser.put(user.getIdPengguna(),user);
        daftarUsernameUser.put(user.getUsername(),user);
        daftarProvinsiUser.put(user.getProvinsi(), user);
        daftarWilayahIndonesiaUser.put(user.getWilayahIndonesia(), user);
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
        daftarProvinsiPendonor.put(pendonor.getProvinsi(), pendonor);
        daftarWilayahIndonesiaPendonor.put(pendonor.getWilayahIndonesia(), pendonor);
    }

    private void insertUDD(User user){
        UDD uDD = (UDD) user;
        daftarUDD.put(uDD.getIdPengguna(),uDD);
        daftarProvinsiUDD.put(uDD.getProvinsi(), uDD);
        daftarWilayahIndonesiaUDD.put(uDD.getWilayahIndonesia(), uDD);
    }

    private void insertBDRS(User user){
        BDRS bDRS = (BDRS) user;
        daftarBDRS.put(bDRS.getIdPengguna(),bDRS);
        daftarProvinsiBDRS.put(bDRS.getProvinsi(), bDRS);
        daftarWilayahIndonesiaBDRS.put(bDRS.getWilayahIndonesia(), bDRS);
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
        daftarUsernameUser.remove(user.getUsername(),user);
        daftarProvinsiUser.remove(user.getProvinsi(), user);
        daftarWilayahIndonesiaUser.remove(user.getWilayahIndonesia(), user);
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
        daftarProvinsiPendonor.remove(pendonor.getProvinsi(), pendonor);
        daftarWilayahIndonesiaPendonor.remove(pendonor.getWilayahIndonesia(), pendonor);
    }

    private void deleteUDD(User user){
        UDD uDD = (UDD) user;
        daftarUDD.remove(uDD.getIdPengguna(),uDD);
        daftarProvinsiUDD.remove(uDD.getProvinsi(), uDD);
        daftarWilayahIndonesiaUDD.remove(uDD.getWilayahIndonesia(), uDD);
    }

    private void deleteBDRS(User user){
        BDRS bDRS = (BDRS) user;
        daftarBDRS.remove(bDRS.getIdPengguna(),bDRS);
        daftarProvinsiBDRS.remove(bDRS.getProvinsi(), bDRS);
        daftarWilayahIndonesiaBDRS.remove(bDRS.getWilayahIndonesia(), bDRS);
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

    public Hashtable<Provinsi, User> getDaftarProvinsiUser() {
        return daftarProvinsiUser;
    }

    public Hashtable<WilayahIndonesia, User> getDaftarWilayahIndonesiaUser() {
        return daftarWilayahIndonesiaUser;
    }

    public Hashtable<String, Pendonor> getDaftarPendonor() {
        return daftarPendonor;
    }

    public Hashtable<String, UDD> getDaftarUDD() {
        return daftarUDD;
    }

    public Hashtable<String, BDRS> getDaftarBDRS() {
        return daftarBDRS;
    }

    public Hashtable<String, Admin> getDaftarAdmin() {
        return daftarAdmin;
    }

    public Hashtable<golDarahEnum, Pendonor> getDaftarGolDarahPendonor() {
        return daftarGolDarahPendonor;
    }

    public Hashtable<rhesusEnum, Pendonor> getDaftarRhesusPendonor() {
        return daftarRhesusPendonor;
    }

    public Hashtable<Provinsi, Pendonor> getDaftarProvinsiPendonor() {
        return daftarProvinsiPendonor;
    }

    public Hashtable<WilayahIndonesia, Pendonor> getDaftarWilayahIndonesiaPendonor() {
        return daftarWilayahIndonesiaPendonor;
    }

    public Hashtable<String, UDD> getDaftarAlamatUDD() {
        return daftarAlamatUDD;
    }

    public Hashtable<Provinsi, UDD> getDaftarProvinsiUDD() {
        return daftarProvinsiUDD;
    }

    public Hashtable<WilayahIndonesia, UDD> getDaftarWilayahIndonesiaUDD() {
        return daftarWilayahIndonesiaUDD;
    }

    public Hashtable<String, BDRS> getDaftarAlamatBDRS() {
        return daftarAlamatBDRS;
    }

    public Hashtable<Provinsi, BDRS> getDaftarProvinsiBDRS() {
        return daftarProvinsiBDRS;
    }

    public Hashtable<WilayahIndonesia, BDRS> getDaftarWilayahIndonesiaBDRS() {
        return daftarWilayahIndonesiaBDRS;
    }

    //#endregion


}
