package Enum;

public enum AlasanKlinis {

    PERDARAHAN_AKTIF(5),
    OPERASI_DARURAT(5),
    SYOK_HEMORAGIK(5),

    ICU(4),
    ANEMIA_BERAT(4),
    TRAUMA(4),

    OPERASI_TERJADWAL(3),
    DEMAM_BERDARAH(3),

    ANEMIA_RINGAN(2),

    STANDBY(1);

    private final int skor;

    AlasanKlinis(int skor) {
        this.skor = skor;
    }

    public int getSkor() {
        return skor;
    }
    
}
