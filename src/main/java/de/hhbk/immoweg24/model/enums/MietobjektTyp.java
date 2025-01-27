package de.hhbk.immoweg24.model.enums;

public enum MietobjektTyp {
    
    PRIVAT("Privat"),
    GEWERBLICH("Gewerblich");

    private final String beschreibung;

    MietobjektTyp(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getBeschreibung() {
        return beschreibung;
    }
    
}
