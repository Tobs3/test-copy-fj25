
package de.hhbk.immoweg24.model.enums;

public enum StatusZahlung {
    
    EINGEGANGEN("Eingegangen"),
    AUSSTEHEND("Ausstehend"),
    TEILZAHLUNG("Teilzahlung"),
    FEHLBUCHUNG("Fehlbuchung");

    private final String beschreibung;

    private StatusZahlung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getBeschreibung() {
        return beschreibung;
    }    
}
