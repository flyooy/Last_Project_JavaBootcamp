package de.sp.trashNothing_backend.entities.enumClass;

public enum Bewertung {
    EINS(1),
    ZWEI(2),
    DREI(3),
    VIER(4),
    FUENF(5);

    private final int wert;

    Bewertung(int wert) {
        this.wert = wert;
    }

    public int getWert() {
        return wert;
    }
}
