package Model;

import java.time.LocalDate;

public class RiwayatDonor {

    private LocalDate tanggalDonor;
    private String idKantongDarah;
    private String lokasiDonor;

    public RiwayatDonor(
            LocalDate tanggalDonor,
            String idKantongDarah,
            String lokasiDonor
    ) {

        this.tanggalDonor = tanggalDonor;
        this.idKantongDarah = idKantongDarah;
        this.lokasiDonor = lokasiDonor;
    }

    public LocalDate getTanggalDonor() {
        return tanggalDonor;
    }

    public void setTanggalDonor(LocalDate tanggalDonor) {
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