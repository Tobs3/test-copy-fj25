package de.hhbk.immoweg24.dao;

import de.hhbk.immoweg24.model.Adresse;
import java.util.HashMap;
import java.util.Map;

/**
 * Datenbankzugriff für Adressen
 */
public class AdresseDao extends GenericDao<Adresse> {
    
    public AdresseDao() {
        super(Adresse.class);
    }

    // --
    
    public Adresse getOrCreate(String strasse, String hausnummer, String plz, String stadt, String land, Boolean hauptadresse) throws Exception {
        HashMap<String, Object> searchFilters = new HashMap<>();
        
        if (strasse != null && !strasse.isEmpty()) searchFilters.put("strasse", strasse);
        if (hausnummer != null && !hausnummer.isEmpty()) searchFilters.put("hausnummer", hausnummer);
        if (plz != null && !plz.isEmpty()) searchFilters.put("plz", plz);
        if (stadt != null && !stadt.isEmpty()) searchFilters.put("stadt", stadt);
        if (land != null && !land.isEmpty()) searchFilters.put("land", land);
        if (hauptadresse != null) searchFilters.put("hauptadresse", hauptadresse);
        
        return getOrCreate(searchFilters);
    }
    
    public Adresse getOrCreate(Map<String, Object> searchFilters) throws Exception {
        Adresse resultAdress = null;
        try {
            resultAdress = findByValues(searchFilters);
        } catch (Exception e) {
            throw e;
        }
        if (resultAdress == null) {
            Adresse newAdress = new Adresse();
            try {
                String strasse = String.valueOf(searchFilters.get("strasse"));
                if (strasse != null && !strasse.isEmpty()) newAdress.setStrasse(strasse);
                
                String hausnummer = String.valueOf(searchFilters.get("hausnummer"));
                if (hausnummer != null && !hausnummer.isEmpty()) newAdress.setHausnummer(hausnummer);
                
                String plz = String.valueOf(searchFilters.get("plz"));
                if (plz != null && !plz.isEmpty()) newAdress.setPlz(plz);
                
                String stadt = String.valueOf(searchFilters.get("stadt"));
                if (stadt != null && !stadt.isEmpty()) newAdress.setStadt(stadt);
                
                String land = String.valueOf(searchFilters.get("land"));
                if (land != null && !land.isEmpty()) newAdress.setLand(land);
                
                boolean hauptadresse = Boolean.parseBoolean((String) searchFilters.get("hauptadresse"));
                newAdress.setHauptadresse(hauptadresse);
            } catch (Exception e) {
                throw e;
            }
            return newAdress;
        } else {
            return resultAdress;
        } 
    }
}
