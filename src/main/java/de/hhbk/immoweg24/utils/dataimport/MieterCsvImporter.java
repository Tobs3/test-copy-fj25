package de.hhbk.immoweg24.utils.dataimport;

import java.util.List;


public class MieterCsvImporter extends AbstractCsvImporter {

    private final AdresseImporter adresseImporter = new AdresseImporter();
    private final BankdatenImporter bankdatenImporter = new BankdatenImporter();
    
    // --
    
    public MieterCsvImporter(List expectedHeader, List csvHeader, List csvData) {
        super("Mieter", expectedHeader, csvHeader, csvData);
    }

    @Override
    public List processData() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
