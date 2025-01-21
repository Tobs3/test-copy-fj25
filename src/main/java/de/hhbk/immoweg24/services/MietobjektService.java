package de.hhbk.immoweg24.services;

import de.hhbk.immoweg24.dao.MietobjektDao;
import de.hhbk.immoweg24.model.Mietobjekt;
import de.hhbk.immoweg24.model.Nebenkosten;
import de.hhbk.immoweg24.model.enums.StatusMietobjekt;
import java.util.List;

/**
 * Geschäfts-Logik für Mietobjekte.
 */
public class MietobjektService {
    
    private final MietobjektDao mietobjektDao;
    
    public MietobjektService() {
        this.mietobjektDao = new MietobjektDao();
    }

    
    //-------------------------------------------------------------------------
    //  GET WITH FILTER
    
    /**
     * Gibt alle Mietobjekte mit dem angegebenen Status zurück.
     * 
     * @param status Der Status des Mietobjekts (z.B. VERFÜGBAR).
     * @return Liste der Mietobjekte mit diesem Status.
     * @throws Exception Wenn die Datenbankabfrage fehlschlägt.
     */
    public List<Mietobjekt> getMietobjekteByStatus(StatusMietobjekt status) throws Exception {
        return mietobjektDao.getByStatus(status);
    }

    /**
     * Gibt alle Mietobjekte zurück, die innerhalb des angegebenen Kostenbereichs und
     * mit dem angegebenen Kostentyp liegen.
     * 
     * @param minKosten Die minimalen Kosten.
     * @param maxKosten Die maximalen Kosten.
     * @param kostenTyp Der Kostentyp ("kaltkosten", "sum_nebenkosten", oder "warmkosten").
     * @return Liste der Mietobjekte im angegebenen Kostenbereich und mit dem angegebenen Kostentyp.
     * @throws Exception Wenn die Datenbankabfrage fehlschlägt.
     */
    public List<Mietobjekt> getMietobjekteByCostRange(double minKosten, double maxKosten, String kostenTyp) throws Exception {
        return mietobjektDao.getByCostRange(minKosten, maxKosten, kostenTyp);
    }
    
    
    //-------------------------------------------------------------------------
    // Do Stuff

    /**
     * Berechnet anhand der gegebenen kaltmiete und der gegebenen Summe an Nebenkosten die Warmkosten.
     * 
     * @param kaltmiete die Kaltmiete des Mietobjekts
     * @param sumNebenkosten die Summe der Nebenkosten des Mietobjekts
     * @return die Wamrkosten als double
     */
    public double berechneWarmmiete(double kaltmiete, double sumNebenkosten) {
        return kaltmiete + sumNebenkosten;
    }
    
    /**
     * Berechnet anhand der gegebenen kaltmiete und der gegebenen Liste an Nebenkosten die Warmkosten.
     * 
     * @param kaltmiete die Kaltmiete des Mietobjekts
     * @param nebenkosten eine Liste der Nebenkosten des Mietobjekts
     * @return die Wamrkosten als double
     */
    public double berechneWarmmiete(double kaltmiete, List<Nebenkosten> nebenkosten) {
        double sumNebenkosten = 0;
        for (Nebenkosten kostenPosition : nebenkosten) {
            sumNebenkosten += kostenPosition.getBetrag().doubleValue();
        }
        return kaltmiete + sumNebenkosten;
    }
}
