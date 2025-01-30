package de.hhbk.immoweg24.dao;

import de.hhbk.immoweg24.model.Adresse;
import de.hhbk.immoweg24.model.Bankdaten;
import de.hhbk.immoweg24.model.Mieter;
import java.util.HashMap;
import java.util.Map;

/**
 * Das MieterDao ermöglicht den Datenzugriff auf Mieter.
 * Es bietet Methoden zum Suchen, Speichern und Verwalten von Mietern.
 * 
 * Hauptfunktionen:
 * - Finden eines Mieters anhand von ID oder anderen Eigenschaften
 * - Verwaltung der Beziehungen zwischen Mietern und ihren Mietobjekten
 */

public class MieterDao extends GenericDao<Mieter> {
    
    private AdresseDao adresseDao = new AdresseDao();
    
    public MieterDao() {
        super(Mieter.class);
    }
    
        
    //-------------------------------------------------------------------------
    //  GET WITH FILTER
    
    public Mieter getOrCreate(String vorname, String nachname, Adresse adresse, String telefon, String email, Bankdaten bankdaten) throws Exception {
        HashMap<String, Object> searchFilters = new HashMap<>();
        
        if (vorname != null && !vorname.isEmpty()) searchFilters.put("vorname", vorname);
        if (nachname != null && !nachname.isEmpty()) searchFilters.put("nachname", nachname);
//        if (adresse != null) searchFilters.put("adresse", adresse.getId());
        if (telefon != null && !telefon.isEmpty()) searchFilters.put("telefon", telefon);
        if (email != null && !email.isEmpty()) searchFilters.put("email", email);
//        if (bankdaten != null) searchFilters.put("bankdaten", bankdaten);
        
        return getOrCreate(searchFilters);
    }
    
    public Mieter getOrCreate(Map<String, Object> values) throws Exception {
        Mieter matchingMieter = null;
        try {
            matchingMieter = findByValues(values);
        } catch (Exception e) {
            throw e;
        }
        if (matchingMieter == null) {
            Mieter newMieter = new Mieter();
            try {
                String vorname = String.valueOf(values.get("vorname"));
                if (vorname != null && !vorname.isEmpty()) newMieter.setVorname(vorname);
                
                String nachname = String.valueOf(values.get("nachname"));
                if (nachname != null && !nachname.isEmpty()) newMieter.setNachname(nachname);
                
                Long adresseId = (Long) values.get("adresse");
                Adresse adresse = null;
                if (adresseId != null) {
                    adresse = (Adresse) adresseDao.getById(adresseId);
                }
                if (adresse != null) newMieter.setAdresse(adresse);
                
                String telefon = String.valueOf(values.get("telefon"));
                if (telefon != null && !telefon.isEmpty()) newMieter.setTelefon(telefon);
                
                String email = String.valueOf(values.get("email"));
                if (email != null && !email.isEmpty()) newMieter.setEmail(email);
                
            } catch (Exception e) {
                throw e;
            }
            return newMieter;
        } else {
            return matchingMieter;
        }
    }
}
