package dataimport;

import de.hhbk.immoweg24.utils.dataimport.AbstractCsvImporter;
import de.hhbk.immoweg24.utils.dataimport.CsvProcessor;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CsvImportTest {
    public static void main(String[] args) {
        try {
            Path relativePath = Paths.get("src//main//java//de//hhbk//immoweg24//utils//dataimport//testdata//csv//Mietobjekte-2024.csv");
            String absolutePath = relativePath.toAbsolutePath().toString();;
            // 1. Lade die CSV-Datei
            CsvProcessor processor = new CsvProcessor(absolutePath);
            if (!processor.readCsv()) {
                throw new IllegalArgumentException("Die CSV-Datei konnte nicht gelesen werden.");
            }

            // 2. Initialisiere den MietobjektCsvImporter
            AbstractCsvImporter importer = (AbstractCsvImporter) processor.getImporter();

            // 3. Verarbeite die Daten aus der CSV
            List<Object> objekte = importer.processData();

            // 4. Ausgabe der Ergebnisse
            System.out.println("Verarbeitete objekte:");
            objekte.forEach(System.out::println);

            // 5. Optional: Speichere die Daten in der Datenbank
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
