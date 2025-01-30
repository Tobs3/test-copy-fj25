package dataimport;

import de.hhbk.immoweg24.utils.dataimport.AbstractCsvImporter;
import de.hhbk.immoweg24.utils.dataimport.CsvProcessor;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CsvImportTest {
    public static void main(String[] args) {
        String basePath = "src//main//java//de//hhbk//immoweg24//utils//dataimport//testdata//csv//";
        
        testCsv(Paths.get(basePath + "Mietobjekte-2024.csv"));
        testCsv(Paths.get(basePath + "Mieter-2024.csv"));
//        testCsv(Paths.get(basePath + "Zahlungen-2024.csv"));
    }
    
    
    public static void testCsv(Path relativeFilePath) {
        try {
            String absolutePath = relativeFilePath.toAbsolutePath().toString();;
            // Lade die CSV-Datei
            CsvProcessor processor = new CsvProcessor(absolutePath);
            if (!processor.readCsv()) {
                throw new IllegalArgumentException("Die CSV-Datei konnte nicht gelesen werden.");
            }

            AbstractCsvImporter importer = (AbstractCsvImporter) processor.getImporter();

            // Verarbeite die Daten aus der CSV
            List<Object> objekte = importer.processData();
            System.out.println("Verarbeitete objekte:");
            objekte.forEach(System.out::println);

            // Speichere die Daten in der Datenbank
//            for (Object currentObject : objekte) {
//                currentObject.save(currentObject);
//            }
//            System.out.println("Alle Daten erfolgreich gespeichert!");
            System.out.println("/done objekte");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
