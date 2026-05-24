package User;

import App.App;

public class UDD extends User{
    private String alamat;
    private String nama;

    public UDD(String username, String password, String noTelp, String alamat, String nama) {
        super(username, password, noTelp,nama);
        this.alamat = alamat;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @Override
    public void tampilkanMenuUtama(App app) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tampilkanMenuUtama'");
    }
}
