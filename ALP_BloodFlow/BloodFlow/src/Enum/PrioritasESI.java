package Enum;

public enum PrioritasESI {
    ESI_1(1, "Resusitasi"),
    ESI_2(2, "Emergent"),
    ESI_3(3, "Urgent"),
    ESI_4(4, "Semi-Urgent"),
    ESI_5(5, "Non-Urgent");

    private final int level;
    private final String label;

    PrioritasESI(int level, String label) {
        this.level = level;
        this.label = label;
    }

    public int getLevel() {
        return level;
    }

    public String getLabel() {
        return label;
    }

    // dipakai untuk sorting: makin besar makin prioritas tinggi
    public int getSortScore() {
        return 6 - level; // ESI 1 -> 5, ESI 5 -> 1
    }

    @Override
    public String toString() {
        return "ESI " + level + " - " + label;
    }
}