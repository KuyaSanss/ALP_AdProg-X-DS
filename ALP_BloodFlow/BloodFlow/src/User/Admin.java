package User;

import App.App;
import Enum.Provinsi;
import Enum.WilayahIndonesia;
import Model.DataUser;

public class Admin extends User {


    public Admin( String username, String password, String noTelp,String nama,Provinsi provinsi, WilayahIndonesia wilayahIndonesia) {
        super( username, password, noTelp,nama,provinsi,wilayahIndonesia);
    }

    public void buatAkunInstansi(App app) {

        DataUser dataUtama = app.getDataUser();

        System.out.println("");
        System.out.println("=== BUAT AKUN INSTANSI BARU ===");

        int pilihanInstansi = 0;
        boolean pilihanValid = false;

        while (!pilihanValid) {
            try {
                System.out.println("Pilih tipe instansi:");
                System.out.println("1. Rumah Sakit (BDRS)");
                System.out.println("2. Unit Donor Darah (UDD)");
                System.out.print("Pilihan (1/2): ");

                pilihanInstansi = Integer.parseInt(app.getSc().nextLine());

                if (pilihanInstansi == 1 || pilihanInstansi == 2) {
                    pilihanValid = true;
                } else {
                    System.out.println("Pilihan tidak valid. Harap masukkan angka 1 atau 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka! Silakan coba lagi.");
            }
        }


        String username = "";
        boolean usernameValid = false;

        while (!usernameValid) {
            System.out.print("Masukkan Username: ");
            username = app.getSc().nextLine();

            if (dataUtama.getDaftarUsernameUser().containsKey(username)) {
                System.out.println("Username '" + username + "' sudah terdaftar! Silakan buat ulang.\n");
            } else {
                usernameValid = true;
            }
        }

        System.out.print("Masukkan Password (sementara): ");
        String password = app.getSc().nextLine();
        System.out.print("Masukkan Nama Instansi       : ");
        String nama = app.getSc().nextLine();
        System.out.print("Masukkan Alamat              : ");
        String alamat = app.getSc().nextLine();
        System.out.print("Masukkan Nomor Telepon       : ");
        String noTelp = app.getSc().nextLine();
        Provinsi provinsi = Provinsi.inputProvinsi(app);
        WilayahIndonesia wilayahIndonesia = WilayahIndonesia.inputWilayahIndonesia(app);

        if (pilihanInstansi == 1) {
            BDRS rsBaru = new BDRS(username, password, noTelp, alamat, nama,provinsi,wilayahIndonesia);

            dataUtama.insertUser(rsBaru);

            System.out.println("Akun Rumah Sakit berhasil dibuat!");
            cetakRekapData(rsBaru, "Rumah Sakit");

        } else {
            UDD uddBaru = new UDD(username, password, noTelp, alamat, nama,provinsi,wilayahIndonesia);

            dataUtama.insertUser(uddBaru);

            System.out.println("Akun UDD berhasil dibuat!");
            cetakRekapData(uddBaru, "Unit Donor Darah");
        }
    }

    public void hapusAkun(App app) {

        DataUser dataUtama = app.getDataUser();

        //tampilkan user
        for(String key : dataUtama.getDaftarUser().keySet()){
            User user = dataUtama.getDaftarUser().get(key);
            System.out.println(user.getIdPengguna()+" "+user.getUsername());
        }

        System.out.println("=== HAPUS AKUN PENGGUNA ===");
        System.out.print("Masukkan ID Pengguna yang ingin dihapus: ");
        String idTarget = app.getSc().next()+app.getSc().nextLine();

        if (dataUtama.getDaftarUser().containsKey(idTarget)) {
            User targetHapus = dataUtama.getDaftarUser().get(idTarget);

            if (targetHapus instanceof Admin) {
                System.out.println("Anda tidak dapat menghapus sesama Admin!");
                return;
            }

            String usernameHapus = targetHapus.getUsername();
            String tipe = targetHapus.getClass().getSimpleName();

            dataUtama.deleteUser(targetHapus);

            System.out.println("Akun " + tipe + " dengan ID '" + idTarget + "' Username: " + usernameHapus
                    + " berhasil dihapus!");
        } else {
            System.out.println("Pengguna dengan ID '" + idTarget + "' tidak ditemukan di sistem.");
        }
    }

    public void editAkun(App app) {
        DataUser dataUtama= app.getDataUser();

        System.out.println("=== EDIT AKUN PENGGUNA ===");
        System.out.print("Masukkan ID Pengguna yang ingin diedit: ");
        String idTarget = app.getSc().nextLine();

        if (dataUtama.getDaftarUser().containsKey(idTarget)) {
            User targetEdit = dataUtama.getDaftarUser().get(idTarget);

            System.out.println("Data Ditemukan!");
            System.out.println("ID       : " + targetEdit.getIdPengguna());
            System.out.println("Username : " + targetEdit.getUsername());

            System.out.print("Masukkan Password baru (Tekan ENTER jika tidak ingin mengubah): ");
            String newPassword = app.getSc().nextLine();
            if (newPassword.equals("")) {
                    targetEdit.setPassword(targetEdit.getPassword());
                } else {
                    targetEdit.setPassword(newPassword);
                }

            if (targetEdit instanceof BDRS) {
                BDRS bdrs = (BDRS) targetEdit;
                System.out.println("Edit Data Spesifik Rumah Sakit ");

                System.out.print("Nama UDD baru: ");
                String newNama = app.getSc().nextLine();
                if (newNama.equals("")) {
                    bdrs.setUsername((bdrs.getUsername()));
                } else {
                    bdrs.setUsername(newNama);
                }

                System.out.print("Alamat baru ): ");
                String newAlamat = app.getSc().nextLine();
                if (newAlamat.equals("")) {
                    bdrs.setAlamat(bdrs.getAlamat());
                } else {
                    bdrs.setAlamat(newAlamat);
                }

                System.out.print("Nomor telepon baru ): ");
                String newNoTelepon = app.getSc().nextLine();
                if (newNoTelepon.equals("")) {
                    bdrs.setNoTelp(bdrs.getNoTelp());
                } else {
                    bdrs.setNoTelp(newNoTelepon);
                }

            } else if (targetEdit instanceof UDD) {
                UDD udd = (UDD) targetEdit;
                System.out.println("Edit Data Spesifik UDD");

                System.out.print("Nama UDD baru: ");
                String newNama = app.getSc().nextLine();
                if (newNama.equals("")) {
                    udd.setUsername(udd.getUsername());
                } else {
                    udd.setUsername(newNama);
                }

                System.out.print("Alamat baru: ");
                String newAlamat = app.getSc().nextLine();
                if (newAlamat.equals("")) {
                    udd.setAlamat(udd.getAlamat());
                } else {
                    udd.setAlamat(newAlamat);
                }

                System.out.print("Nomor telepon baru ): ");
                String newNoTelepon = app.getSc().nextLine();
                if (newNoTelepon.equals("")) {
                    udd.setNoTelp(udd.getNoTelp());
                } else {
                    udd.setNoTelp(newNoTelepon);
                }

            } else {
                System.out.println("Tipe akun ini tidak dapat mengedit akun tersebut.");
            }

            System.out.println("Data pengguna dengan ID '" + idTarget + "' berhasil diperbarui!");
        } else {
            System.out.println("Pengguna dengan ID '" + idTarget + "' tidak ditemukan.");
        }
    }

    private void cetakRekapData(User p, String tipe) {
        System.out.println("--- Rekap Data Baru ---");
        System.out.println("Tipe Instansi : " + tipe);
        System.out.println("ID Pengguna   : " + p.getIdPengguna());
        System.out.println("Username      : " + p.getUsername());
    }

    @Override
    public void tampilkanMenuUtama(App app) {
        boolean kondisi = true;

        while (kondisi) {
            System.out.println("");
            System.out.println("=== MENU ADMIN ===");
            System.out.println("1. Buat Akun Instansi Baru (BDRS/UDD)");
            System.out.println("2. Edit Akun Instansi");
            System.out.println("3. Hapus Akun Instansi");
            System.out.println("4. Logout");
            System.out.print("Pilih menu (1-4): ");
            String pilihan = app.getSc().nextLine();

            switch (pilihan) {
                case "1":
                    buatAkunInstansi(app);
                    break;
                case "2":
                    editAkun(app);
                    break;
                case "3":
                    hapusAkun(app);
                    break;
                case "4":
                    System.out.println("Berhasil logout dari menu admin.");
                    System.out.println("");
                    kondisi = false;
                    app.menuAwal();
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

}