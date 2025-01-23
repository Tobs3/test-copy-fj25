package de.hhbk.immoweg24.utils.dataimport;

import de.hhbk.immoweg24.dao.GenericDao;
import de.hhbk.immoweg24.model.Adresse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AdresseImporter {
    
    private final GenericDao<Adresse> adresseDao = new GenericDao<>(Adresse.class);
    
    // -- 
    
    /* TODO: 
       ? ggf. Importer Interface
       ? ODER "getOrCreate" Methode in GenericDao
    */
    
    public AdresseImporter() {
        
    }
    
    // --
    
    /**
     * Prüft, ob die gegebene Adresse bereits in der Datenbank existiert.
     * Wenn die Adresse bereits existiert, wird die existierende Adresse 
     * zurückgegeben, andernfalls wird eine neue Adresse erstellt. 
     * 
     * <p>NOTE: Rückgabewert ist noch nicht in der DB gespeichert!</p>
     * 
     * @param srcAdress erwartet als [Straße, Hausnummer, Plz, Stadt, Land]
     * @return Adresse, existierende bei übereinstimmenden Werten, neue Adresse andernfalls.
     * @throws Exception 
     */
    public Adresse getOrCreate(String[] srcAdress) throws Exception {
        Adresse resultAdress = null;
        String street = srcAdress[0];
        String houseNumber = srcAdress[1];
        String zipCode = srcAdress[2];
        String city = srcAdress[3];
        String country = srcAdress[4];
        
        // ? could validate plausiblity of given data here
        
        // look for existing / matching data
        Map<String, Object> searchFilters = new HashMap<>();
        searchFilters.put("strasse", street);
        searchFilters.put("hausnummer", houseNumber);
        searchFilters.put("plz", zipCode);
        searchFilters.put("stadt", city);
        searchFilters.put("land", country);
        Adresse matchingAdress = null;
        try {
            matchingAdress = adresseDao.findByValues(searchFilters);
        } catch (Exception e) {
            throw e;
        }
        if (matchingAdress == null) {
            // TODO: create new adress (dont safe)
            Adresse newAdress = new Adresse(street, houseNumber, zipCode, city, country);
            resultAdress = newAdress;
        } else {
            resultAdress = matchingAdress;
        }
        return resultAdress;
    }
    
    public boolean saveItem(Adresse item) throws Exception {
        try {
            adresseDao.save(item);
        } catch (Exception e) {
            throw e;
        }
        return true;
    }
    
    public boolean saveItems(List<Adresse> items) throws Exception {
        for (Adresse item : items) {
            saveItem(item);
        }
        return true;
    }
}
