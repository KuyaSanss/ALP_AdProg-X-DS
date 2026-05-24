package Model;

public class RiwayatDonor {

    private String tanggalDonor;
    private String idKantongDarah;
    private String lokasiDonor;

    public RiwayatDonor(
            String tanggalDonor,
            String idKantongDarah,
            String lokasiDonor
    ) {

        this.tanggalDonor = tanggalDonor;
        this.idKantongDarah = idKantongDarah;
        this.lokasiDonor = lokasiDonor;
    }

    public String getTanggalDonor() {
        return tanggalDonor;
    }

    public void setTanggalDonor(String tanggalDonor) {
        this.tanggalDonor = tanggalDonor;
    }

    public String getIdKantongDarah() {
        return idKantongDarah;
    }

    public void setIdKantongDarah(String idKantongDarah) {
        this.idKantongDarah = idKantongDarah;
    }

    public String getLokasiDonor() {
        return lokasiDonor;
    }

    public void setLokasiDonor(String lokasiDonor) {
        this.lokasiDonor = lokasiDonor;
    }
}