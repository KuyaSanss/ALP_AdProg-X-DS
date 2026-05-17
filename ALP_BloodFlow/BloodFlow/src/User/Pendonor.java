package User;
import Enum.golDarahEnum;
import Enum.rhesusEnum;

public class Pendonor extends User {
    private golDarahEnum golDarah;
    private rhesusEnum rhesus;
    private String tanggalTerakhirDonor;
    
    public Pendonor(String idPengguna, String username, String password, String noTelp, golDarahEnum golDarah, rhesusEnum rhesus) {
        super(idPengguna, username, password, noTelp);
        this.golDarah = golDarah;
        this.rhesus = rhesus;
    }

    public golDarahEnum getGolDarah() {
        return golDarah;
    }

    public void setGolDarah(golDarahEnum golDarah) {
        this.golDarah = golDarah;
    }

    public rhesusEnum getRhesus() {
        return rhesus;
    }

    public void setRhesus(rhesusEnum rhesus) {
        this.rhesus = rhesus;
    }

    public String getTanggalTerakhirDonor() {
        return tanggalTerakhirDonor;
    }

    public void setTanggalTerakhirDonor(String tanggalTerakhirDonor) {
        this.tanggalTerakhirDonor = tanggalTerakhirDonor;
    }

    @Override
    public void tampilkanMenuUtama() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tampilkanMenuUtama'");
    }
    
    
}
