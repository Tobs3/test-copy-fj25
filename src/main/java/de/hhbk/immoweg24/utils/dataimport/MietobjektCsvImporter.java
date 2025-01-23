package de.hhbk.immoweg24.utils.dataimport;

import de.hhbk.immoweg24.dao.GenericDao;
import de.hhbk.immoweg24.dao.MietobjektDao;
import de.hhbk.immoweg24.model.Adresse;
import de.hhbk.immoweg24.model.Mietobjekt;
import de.hhbk.immoweg24.model.enums.StatusMietobjekt;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MietobjektCsvImporter extends AbstractCsvImporter {
    
    private final AdresseImporter adresseImporter = new AdresseImporter();
    private final GenericDao<Mietobjekt> mietobjektDao;
    // --
    
    
    public MietobjektCsvImporter(List<String> expectedHeader, List<String> csvHeader, List<String[]> csvData) {
        super("Mietobjekte", expectedHeader, csvHeader, csvData);
        this.mietobjektDao = new MietobjektDao();
    }
    
    // --
    
    /**
     * Verarbeitet und validiert die Daten. Erstellt neue Mietobjekt- und Adresse-
     * Objekte, wenn sie noch nicht existieren.
     * 
     * <p>Note: Der Rückgabewert ist noch nicht in der Datenbank gespeichert!</p>
     *
     * @return Eine Liste der Mietobjekte.
     * @throws Exception wenn die Daten nicht verarbeitet werden können.
     */
    @Override
    public List<Mietobjekt> processData() throws Exception {
        if (!isValid()) throw new IllegalArgumentException("Mietobjekt CSV ist ungültig.");
        List<Mietobjekt> mietobjekte = new ArrayList<>();
        
        List<String[]> data = csvData;
        for (String[] row : data) {
            try {
                // get existing or new adress object
                Adresse adress = null;
                String[] adressValues = new String[5];
                adressValues[0] = row[2]; // Straße
                adressValues[1] = row[3]; // Hausnummer
                adressValues[2] = row[4]; // Postleitzahl
                adressValues[3] = row[5]; // Stadt
                adressValues[4] = row[6]; // Land
                try { 
                    adress = adresseImporter.getOrCreate(adressValues);
                } catch (Exception e) {
                    throw e;
                }
                
                // define Mietobjekt
                Map<String, Object> searchFilters = new HashMap<>();
                searchFilters.put("objektnummer", row[0]);
                searchFilters.put("typ", row[1]);
                searchFilters.put("adresse", adress);
                searchFilters.put("kaltkosten", row[7]);
                searchFilters.put("summeNebenkosten", row[8]);
                searchFilters.put("status", row[10]);
                try {
                    // check if exists, else create
                    mietobjekte.add(getOrCreate(searchFilters));
                } catch (Exception e) {
                    throw e;
                }
            } catch (Exception e) {
                throw e;
            }
        }
        return mietobjekte;
    }
    
    // --
    
    public Mietobjekt getOrCreate(Map<String, Object> values) throws Exception {
        Mietobjekt matchingMietobjekt = null;
        try {
            /* values could be outdated in CSV, so we only 
               check the ObjectNumber for matching data */
            Map<String, Object> reducedFilter = new HashMap<>();
            reducedFilter.put("objektnummer", values.get("objektnummer"));
            matchingMietobjekt = mietobjektDao.findByValues(reducedFilter);
        } catch (Exception e) {
            throw e;
        }
        if (matchingMietobjekt == null) { 
            // TODO: TEST ME !!
            Mietobjekt newMietobjekt = new Mietobjekt();
            try {
                newMietobjekt.setObjektnummer((int) values.get("objektnummer"));
                newMietobjekt.setTyp(String.valueOf(values.get("typ")));
                newMietobjekt.setAdresse((Adresse) values.get("adresse")); // sus
                newMietobjekt.setKaltkosten((BigDecimal) values.get("kaltkosten"));
                newMietobjekt.setSummeNebenkosten((BigDecimal) values.get("summeNebenkosten"));
                newMietobjekt.setStatus(StatusMietobjekt.valueOf((String) values.get("status")));
            } catch (Exception e) {
                throw e;
            }
            return newMietobjekt;
        } else {
            return matchingMietobjekt;
        }
    }
    
}
