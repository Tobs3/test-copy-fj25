package de.hhbk.immoweg24.utils.dataimport;

import de.hhbk.immoweg24.dao.AdresseDao;
import de.hhbk.immoweg24.dao.MietobjektDao;
import de.hhbk.immoweg24.model.Adresse;
import de.hhbk.immoweg24.model.Mietobjekt;
import de.hhbk.immoweg24.model.enums.StatusMietobjekt;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MietobjektCsvImporter extends AbstractCsvImporter {
    
    private final AdresseDao adresseDao;
    private final MietobjektDao mietobjektDao;
    // --
    
    
    public MietobjektCsvImporter(List<String> expectedHeader, List<String> csvHeader, List<String[]> csvData) {
        super("Mietobjekte", expectedHeader, csvHeader, csvData);
        this.mietobjektDao = new MietobjektDao();
        this.adresseDao = new AdresseDao();
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
                String strasse = row[2];
                String hausnummer = row[3];
                String plz = row[4];
                String stadt = row[5];
                String land = row[6];
                try { 
                    adress = adresseDao.getOrCreate(strasse, hausnummer, plz, stadt, land, false);
                } catch (Exception e) {
                    throw e;
                }
                
                // define Mietobjekt
                Integer objektnummer = Integer.getInteger(row[0]);
                String typ = row[1];
                Adresse adresse = adress;
                BigDecimal kaltkosten = BigDecimal.valueOf(Double.parseDouble(row[7]));
                BigDecimal sumNebenkosten = BigDecimal.valueOf(Double.parseDouble(row[8]));
                StatusMietobjekt status = StatusMietobjekt.valueOf((String) row[10]);
                try {
                    // check if exists, else create
                    mietobjekte.add(mietobjektDao.getOrCreate(objektnummer, typ, adresse, kaltkosten, sumNebenkosten, status));
                } catch (Exception e) {
                    throw e;
                }
            } catch (Exception e) {
                throw e;
            }
        }
        return mietobjekte;
    }
    
}
