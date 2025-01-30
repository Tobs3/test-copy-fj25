package de.hhbk.immoweg24.utils.dataimport;

import de.hhbk.immoweg24.dao.BankdatenDao;
import de.hhbk.immoweg24.dao.MietobjektDao;
import de.hhbk.immoweg24.dao.ZahlungDao;
import de.hhbk.immoweg24.model.Bankdaten;
import de.hhbk.immoweg24.model.Mieter;
import de.hhbk.immoweg24.model.Mietobjekt;
import de.hhbk.immoweg24.model.Zahlung;
import de.hhbk.immoweg24.model.enums.StatusZahlung;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;


public class ZahlungCsvImporter extends AbstractCsvImporter {
    private final MietobjektDao mietobjektDao;
    private final ZahlungDao zahlungDao;
    private final BankdatenDao bankdatenDao;
    // --
    
    public ZahlungCsvImporter(List<String> expectedHeader, List<String> csvHeader, List<String[]> csvData) {
        super("Zahlungen", expectedHeader, csvHeader, csvData);
        this.mietobjektDao = new MietobjektDao();
        this.bankdatenDao = new BankdatenDao();
        this.zahlungDao = new ZahlungDao();
    }

    @Override
    public List<Zahlung> processData() throws Exception {
        throw new UnsupportedOperationException("Nicht implementiert.");
    }
    
//    @Override
//    public List<Zahlung> processData() throws Exception {
//        // Betrag, IBAN, BIC, Kontoinhaber, Objektnummer, Status, Verwendungszweck, Datum 
//        if (!isValid()) throw new IllegalArgumentException("Zahlung CSV ist ungültig.");
//        List<Zahlung> zahlungen = new ArrayList<>();
//        
//        List<String[]> data = csvData;
//        for (String[] row : data) {
//            try {
//                // expected kosten format: "622,00 €"
//                String rawBetrag = row[0].replaceAll("[^\\d,]", "").replace(',', '.');
//                BigDecimal betrag = BigDecimal.valueOf(Double.parseDouble(rawBetrag));
//                
//                String iban = row[1];
//                String bic = row[2];
//                String kontoinhaber = row[3];
//                String objektnummer = row[4];
//                StatusZahlung status = StatusZahlung.fromString(row[5]);
//                String verwendungszweck = row[6];
//                LocalDate datum = convertToDate(row[7]);
////                LocalDate datum = null;
//                
//                Bankdaten bankdaten = null;
//                try {
//                    bankdatenDao.getOrCreate(iban, bic, kontoinhaber);
//                } catch (Exception e) {
//                    throw e;
//                }
//                Mietobjekt mietobjekt = null;
//                try {
//                    mietobjektDao.findByValue("objektnummer", objektnummer);
//                } catch (Exception e) {
//                    throw e;
//                }
//                Mieter mieter = null; // maybe check relational table for mieter of this mietobjekt
//                
//                Zahlung zahlung = null;
//                try {
//                    zahlung = zahlungDao.getOrCreate(betrag, mieter, bankdaten, mietobjekt, verwendungszweck, datum, status);
//                } catch (Exception e) {
//                    throw e;
//                }
//                
//                zahlungen.add(zahlung);
//            } catch (Exception e) {
//                throw e;
//            }
//        }
//        return zahlungen;
//    }
//    
//    public LocalDate convertToDate(String dateString) {
//        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("MMM dd", java.util.Locale.GERMAN); // Okt 24
//        DateTimeFormatter format2 = DateTimeFormatter.ofPattern("dd.MM.yyyy"); // 01.10.2024
//
//        LocalDate localDate = null;
//        try {
//            localDate = LocalDate.parse(dateString, format1);
//        } catch (DateTimeParseException e) {
//            try {
//                localDate = LocalDate.parse(dateString, format2);
//            } catch (DateTimeParseException e2) {
//                throw new IllegalArgumentException("Ungültiges Datumsformat: " + dateString);
//            }
//        }
//        return localDate;
//    }
    
}
