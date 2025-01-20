package de.hhbk.immoweg24.model.enums;

public enum StatusMietobjekt {

    VERFÜGBAR("Verfügbar"),
    VERMIETET("Vermietet"),
    INSTANDHALTUNG("Instandhaltung"),
    RESERVIERT("Reserviert");

    private final String beschreibung;

    StatusMietobjekt(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getBeschreibung() {
        return beschreibung;
    }
}