package de.hhbk.immoweg24.model.enums;

public enum StatusObjektmieter {
    
    AKTIV("Aktiv"),
    GEKUENDIGT("Gekündigt"),
     AUSGEZOGEN("Ausgezogen");

    private final String beschreibung;

    StatusObjektmieter(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    
}
