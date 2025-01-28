package de.hhbk.immoweg24.model.enums;

public enum StatusMietverhaeltnis {
    AKTIV("Aktiv"),
    BEENDET("Beendet"),
    GEKÜNDIGT("Gekündigt");

    private final String beschreibung;

    StatusMietverhaeltnis(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getBeschreibung() {
        return beschreibung;
    }
}