package de.hhbk.immoweg24.utils.dataimport;

import java.util.List;


public class ZahlungCsvImporter extends AbstractCsvImporter {
    
    // TODO

    public ZahlungCsvImporter(List expectedHeader, List csvHeader, List csvData) {
        super("Zahlungen", expectedHeader, csvHeader, csvData);
    }

    @Override
    public List processData() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
