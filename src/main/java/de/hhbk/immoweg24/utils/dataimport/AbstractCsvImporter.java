package de.hhbk.immoweg24.utils.dataimport;

import java.util.List;


public abstract class AbstractCsvImporter<T> {
    
    protected final List<String> EXPECTED_HEADER;
    protected final String TITLE;
    protected final List<String> csvHeader;
    protected final List<String[]> csvData;
    
    // --
    
    
    public AbstractCsvImporter(String title, List<String> expectedHeader, List<String> csvHeader, List<String[]> csvData) {
        this.TITLE = title;
        this.EXPECTED_HEADER = expectedHeader;
        this.csvHeader = csvHeader;
        this.csvData = csvData;
    }
    
    // -- 
    
    
    public abstract List<T> processData();
    
    // -- 
    
    
    
    /**
     * Prüft, ob die gelesene CSV-Datei valide für die Datenverarbeitung ist.
     * @return True wenn valide, False wenn invalide.
     * @throws IllegalArgumentException wenn Daten fehlen oder die Quelldatei
     *           dem geforderten Format entspricht.
     */
    public boolean isValid() {
        if (!validateRowCount()) throw new IllegalArgumentException("Daten sind leer.");
        if (!validateRowLength()) throw new IllegalArgumentException("Ungültige Spaltenanzahl.");
        if (!validateHeaderContent()) throw new IllegalArgumentException("Unbekannter Header.");
        return true;
    }

    /**
     * Prüft, ob die definierten Header Zeilen mit den erwarteten Header Zeilen
     * übereinstimmen.
     *
     * @return True wenn die Header übereinstimmen; False andernfalls.
     */
    public boolean validateHeaderContent() {
        // TODO: interface or superclass for Importer
        return csvHeader.equals(EXPECTED_HEADER);
    }
    
    /**
     * Prüft, ob die definierten CSV-Daten in allen Zeilen mindestens 
     * so viele Spalten hat, wie der EXPECTED_HEADER es vorgibt.
     * 
     * @param rows die Zeilen einer CSV
     * @return True wenn jede Zeile ausreichend Spalten hat; False andernfalls.
     */
    private boolean validateRowLength() {
        // TODO: interface or superclass for Importer
        return csvData.stream().allMatch(row -> row.length >= EXPECTED_HEADER.size()); 
    }
    
    /**
     * Prüft, ob Datensätze existieren.
     * @param csvRows
     * @return True wenn csvRows mehr als zwei Zeilen hat; False andernfalls.
     */
    private boolean validateRowCount() {
        return !csvData.isEmpty();
    }

}
