package User;

public abstract class User {

    private String idPengguna,username,password;
    private int noTelp;
    //todo check datatype

    public User(String idPengguna, String username, String password, int noTelp) {
        this.idPengguna = idPengguna;
        this.username = username;
        this.password = password;
        this.noTelp = noTelp;
    }

    
    //abstract classes

    public abstract void tampilkanMenuUtama();

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


    //#endregion
}
