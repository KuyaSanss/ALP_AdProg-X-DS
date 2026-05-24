package User;

import Enum.golDarahEnum;
import Enum.rhesusEnum;
import java.time.LocalDate;

public class KantongDarah {
    String idDarah;
    String idPendonor;
    golDarahEnum jenisDarah;
    rhesusEnum rhesus;
    LocalDate tanggalMasuk;
    LocalDate tanggalKadaluarsa;

    public KantongDarah(String idDarah, String idPendonor, golDarahEnum jenisDarah, rhesusEnum rhesus) {
        this.idDarah = idDarah;
        this.idPendonor = idPendonor;
        this.jenisDarah = jenisDarah;
        this.rhesus = rhesus;
        this.tanggalMasuk = LocalDate.now(); 
        this.tanggalKadaluarsa = this.tanggalMasuk.plusDays(35); 
    }

    public String getIdDarah() {
        return idDarah;
    }

    public String getIdPendonor() {
        return idPendonor;
    }

    public golDarahEnum getJenisDarah() {
        return jenisDarah;
    }

    public rhesusEnum getRhesus() {
        return rhesus;
    }

    public LocalDate getTanggalMasuk() {
        return tanggalMasuk;
    }

    public LocalDate getTanggalKadaluarsa() {
        return tanggalKadaluarsa;
    }

    
}
