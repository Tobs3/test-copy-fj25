package de.hhbk.immoweg24.dao;

import de.hhbk.immoweg24.model.Bankdaten;
import java.util.HashMap;
import java.util.Map;


/**
 * Das BankdatenDao ist für den Zugriff auf Bankdaten von Mietern zuständig.
 * Es ermöglicht das Speichern und Abrufen von Bankverbindungen.
 * 
 * Hauptfunktionen:
 * - Speichern und Aktualisieren von IBAN, BIC und Kontoinhaber-Daten
 * - Verknüpfung der Bankdaten mit den Mietern
 */
public class BankdatenDao extends GenericDao<Bankdaten> {
    
    public BankdatenDao() {
        super(Bankdaten.class);
    }
    
    
    //  --
    
    public Bankdaten getOrCreate(String iban, String bic, String kontoinhaber) throws Exception {
        Map<String, Object> searchFilters = new HashMap<>();
        
        if (iban != null && !iban.isEmpty()) searchFilters.put("iban", iban);
        if (bic != null && !bic.isEmpty()) searchFilters.put("bic", bic);
        if (kontoinhaber != null && !kontoinhaber.isEmpty()) searchFilters.put("kontoinhaber", kontoinhaber);
        
        return getOrCreate(searchFilters);
    }
    
    public Bankdaten getOrCreate(Map<String, Object> searchFilters) throws Exception {
        String iban = String.valueOf(searchFilters.get("iban"));
        String bic = String.valueOf(searchFilters.get("bic")); 
        String kontoinhaber = String.valueOf(searchFilters.get("kontoinhaber"));
        
        // check for existing or matching data
        Bankdaten matchingBankdaten = null;
        if (searchFilters.size() > 1) {
            try {
                matchingBankdaten = findByValues(searchFilters);
            } catch (Exception e) {
                throw e;
            }
        }
        if (matchingBankdaten == null) {
            // Bankdaten values were not found, create new Bankdaten.
            Bankdaten newBankdaten = new Bankdaten(iban, bic, kontoinhaber);
            return newBankdaten;
        } else {
            // Bankdaten values already exist, return result.
            return matchingBankdaten;
        }
    }

}
