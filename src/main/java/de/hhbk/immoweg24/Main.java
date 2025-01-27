package de.hhbk.immoweg24;

import de.hhbk.immoweg24.dao.MietobjektDao;
import de.hhbk.immoweg24.dao.MietverhaeltnisDao;
import de.hhbk.immoweg24.model.Benutzer;
import de.hhbk.immoweg24.model.JRMietverhaeltnisDataSource;
import de.hhbk.immoweg24.model.Mietobjekt;
import de.hhbk.immoweg24.model.Mietverhaeltnis;
import net.sf.jasperreports.engine.*;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        File reportFile = new File("src\\main\\resources\\reports\\Rechnung.jasper");
        File targetFile = new File("src\\main\\resources\\reports\\ObjektBerichtReport.pdf");

        try {
            // DAO-Instanzen erstellen
            MietobjektDao mietobjektDao = new MietobjektDao();
            MietverhaeltnisDao mietverhaeltnisDao = new MietverhaeltnisDao();

            // Daten aus der Datenbank laden
            Mietobjekt mietobjekt = mietobjektDao.getMietobjekt(1L); // random ID als Beispiel
            Mietverhaeltnis mietverhaeltnis = mietverhaeltnisDao.getMietverhaeltnis(1L);
            List<Mietverhaeltnis> mietverhaeltnisList = mietverhaeltnisDao.getAlleMietverhaeltnisse();

            // Daten für Parameter
            //Benutzer benutzer = mietverhaeltnis.getBenutzer(); 
            int objektnummer = mietobjekt.getObjektnummer();
            String mieterVorname = mietverhaeltnis.getMieter().getVorname();
            String mieterNachname = mietverhaeltnis.getMieter().getNachname();

            // Parameter für den Bericht
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("Objektnummer", objektnummer);
            parameters.put("MieterVorname", mieterVorname);
            parameters.put("MieterNachname", mieterNachname);
            //parameters.put("Benutzer", benutzer.getNachname());

            // Bericht füllen
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    reportFile.getAbsolutePath(),
                    parameters,
                    new JRMietverhaeltnisDataSource(mietverhaeltnisList)
            );

            // Bericht als PDF exportieren
            JasperExportManager.exportReportToPdfFile(jasperPrint, targetFile.getAbsolutePath());
            System.out.println("Bericht erfolgreich erstellt: " + targetFile.getAbsolutePath());
        } catch (Exception e) {
            System.err.println("Fehler beim Erstellen des Berichts: " + e.getMessage());
            e.printStackTrace();
        }
    }
}