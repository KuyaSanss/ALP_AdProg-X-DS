package User;

public class BDRS extends User{
    private String alamat;

    public BDRS(String idPengguna, String username, String password, String noTelp, String alamat) {
        super(idPengguna, username, password, noTelp);
        this.alamat = alamat;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @Override
    public void tampilkanMenuUtama() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tampilkanMenuUtama'");
    }

    
}
