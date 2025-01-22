package de.hhbk.immoweg24.utils.dataimport;

import de.hhbk.immoweg24.model.Mietobjekt;
import de.hhbk.immoweg24.model.enums.StatusMietobjekt;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MietobjektCsvImporter extends AbstractCsvImporter {
    
    // --
    
    
    public MietobjektCsvImporter(List<String> expectedHeader, List<String> csvHeader, List<String[]> csvData) {
        super("Mietobjekte", expectedHeader, csvHeader, csvData);

    }
    
    // --
    
    /**
     * Processes and validates the data rows, creating Mietobjekt entities.
     *
     * @return Eine Liste der Mietobjekte.
     * @throws IllegalArgumentException wenn die Daten nicht verarbeitet werden können.
     */
    public List<Mietobjekt> processData() {
        if (!isValid()) throw new IllegalArgumentException("Mietobjekt CSV ist ungültig.");
        List<Mietobjekt> mietobjekte = new ArrayList<>();
        
        List<String[]> data = csvData;
        for (String[] row : data) {
            try {
                Mietobjekt mietobjekt = new Mietobjekt();
                mietobjekt.setObjektnummer(Integer.parseInt(row[0]));
                mietobjekt.setTyp(row[1]);
                mietobjekt.setKaltkosten(new BigDecimal(row[7]));
                mietobjekt.setSummeNebenkosten(new BigDecimal(row[8]));
                mietobjekt.setStatus(StatusMietobjekt.valueOf(row[10].toLowerCase()));
                mietobjekte.add(mietobjekt);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return mietobjekte;
    }

    
    // --
    
    
}
