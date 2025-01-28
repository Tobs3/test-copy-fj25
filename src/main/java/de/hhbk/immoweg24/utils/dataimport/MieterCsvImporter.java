package de.hhbk.immoweg24.utils.dataimport;

import de.hhbk.immoweg24.dao.AdresseDao;
import de.hhbk.immoweg24.dao.BankdatenDao;
import de.hhbk.immoweg24.dao.MieterDao;
import de.hhbk.immoweg24.model.Adresse;
import de.hhbk.immoweg24.model.Bankdaten;
import de.hhbk.immoweg24.model.Mieter;
import java.util.ArrayList;
import java.util.List;


public class MieterCsvImporter extends AbstractCsvImporter {
    
    private final MieterDao mieterDao;
    private final AdresseDao adresseDao;
    private final BankdatenDao bankdatenDao;
    // --
    
    public MieterCsvImporter(List<String> expectedHeader, List<String> csvHeader, List<String[]> csvData) {
        super("Mieter", expectedHeader, csvHeader, csvData);
        this.mieterDao = new MieterDao();
        this.adresseDao = new AdresseDao();
        this.bankdatenDao = new BankdatenDao();
    }

    @Override
    public List<Mieter> processData() throws Exception {
        // Vorname, Nachname, Straße, Hausnummer, Plz, Stadt, Land, Telefon, E-Mail, IBAN, BIC
        if (!isValid()) throw new IllegalArgumentException("Mieter CSV ist ungültig.");
        List<Mieter> resultMieter = new ArrayList<>();
        
        List<String[]> data = csvData;
        for (String[] row : data) {
            try {
                String vorname = row[0];
                String nachname = row[1];
                String strasse = row[2];
                String hausnummer = row[3];
                String plz = row[4];
                String stadt = row[5];
                String land = row[6];
                String telefon = row[7];
                String email = row[8];
                String iban = row [9];
                String bic = row[10];
                
                // get existing or new adress 
                Adresse adress = null;
                try {
                    adress = adresseDao.getOrCreate(strasse, hausnummer, plz, stadt, land, null);
                } catch (Exception e) {
                    throw e;
                }
                // get existing or new bankdaten 
                Bankdaten bankdaten = null;
                try {
                    bankdaten = bankdatenDao.getOrCreate(iban, bic, (vorname + " " + nachname));
                } catch (Exception e) {
                    throw e;
                }
                // get existing or new mieter
                Mieter mieter = null;
                try {
                    mieter = mieterDao.getOrCreate(vorname, nachname, adress, telefon, email, bankdaten);
                } catch (Exception e) {
                    throw e;
                }
                resultMieter.add(mieter);
            } catch (Exception ex) {
                throw ex;
            }
        }
        return resultMieter;
    }
        
    
}
