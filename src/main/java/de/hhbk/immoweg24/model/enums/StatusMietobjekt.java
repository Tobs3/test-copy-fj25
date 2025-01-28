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
    
    public static StatusMietobjekt fromString(String description) {
        for (StatusMietobjekt status : StatusMietobjekt.values()) {
            if (status.getBeschreibung().equalsIgnoreCase(description)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No enum constant for description: " + description);
    }
}