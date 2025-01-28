package de.hhbk.immoweg24.model.enums;

public enum StatusNebenkosten {  
    
    AKTIV("Aktiv"),
    INAKTIV("Inaktiv");

    private final String beschreibung;

    StatusNebenkosten(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getBeschreibung() {
        return beschreibung;
    }
}