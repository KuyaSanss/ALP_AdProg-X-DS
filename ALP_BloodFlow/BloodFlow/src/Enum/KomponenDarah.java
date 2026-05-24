package Enum;

public enum KomponenDarah {

    WHOLE_BLOOD(4),
    PACKED_RED_CELL(5),
    FRESH_FROZEN_PLASMA(3),
    THROMBOCYTE_CONCENTRATE(4);

    private final int skor;

    KomponenDarah(int skor) {
        this.skor = skor;
    }

    public int getSkor() {
        return skor;
    }
}