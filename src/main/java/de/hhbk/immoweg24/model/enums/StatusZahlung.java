
package de.hhbk.immoweg24.model.enums;

/**
 * Das Enum StatusZahlung definiert die möglichen Statuswerte einer Zahlung.
 * Mögliche Werte sind:
 * - EINGEGANGEN: Die Zahlung wurde erfolgreich empfangen.
 * - AUSSTEHEND: Die Zahlung ist noch nicht erfolgt.
 * - TEILZAHLUNG: Entspricht nicht der vollen Betrag
 * - FEHLBUCHUNG: Einen Fehler ist aufgetreten
 */
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
