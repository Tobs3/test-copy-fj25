package de.hhbk.immoweg24.services;

import de.hhbk.immoweg24.dao.ZahlungDao;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ZahlungService {
    private ZahlungDao zahlungDao;

    public ZahlungService(ZahlungDao zahlungDao) {
        this.zahlungDao = zahlungDao;
    }

    public void generiereReport(LocalDate startDatum, LocalDate endDatum) {
        BigDecimal zahlungIst = zahlungDao.berechneZahlungIst(startDatum, endDatum);
        BigDecimal zahlungSoll = zahlungDao.berechneZahlungSoll(startDatum, endDatum);

        System.out.println("Zahlung IST: " + zahlungIst);
        System.out.println("Zahlung SOLL: " + zahlungSoll);
    }
}
