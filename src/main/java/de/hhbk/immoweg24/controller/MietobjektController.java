package de.hhbk.immoweg24.controller;

import de.hhbk.immoweg24.model.Mietobjekt;
import de.hhbk.immoweg24.model.enums.StatusMietobjekt;
import de.hhbk.immoweg24.service.MietobjektService;
import java.util.List;

/**
 * MietobjektController, ruft den MietobjektService auf und liefert die Daten 
 * an das Frontend (View). Anwendungsschicht.
 * 
 */
public class MietobjektController {
    private final MietobjektService mietobjektService;

    public MietobjektController() {
        this.mietobjektService = new MietobjektService();
    }
    
    
    //-------------------------------------------------------------------------
    //  GET WITH FILTER
    
    /**
     * Erhalte alle Mietobjekte, die den gegebenen Status besitzen als Liste.
     *
     * @param status der Status des Mietobjekts (e.g., StatusMietobjekt.VERFÜGBAR).
     * @return List aller Mietobjekte mit dem gegebenen Status.
     */
    public List<Mietobjekt> getMietobjekteByStatus(StatusMietobjekt status) {
        try {
            return mietobjektService.getMietobjekteByStatus(status);
        } catch (Exception e) {
            // Fehlerbehandlung
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Erhalte alle Mietobjekte, deren spezifizierter kostentyp innerhalb des
     * spezifizierten minKosten - maxKosten Wertebereich liegen.
     * 
     * <p>Mögliche Kostentypen: "kaltkosten", "sum_nebenkosten", "warmkosten".</p>
     *
     * @param minCost mindest Kosten (inklusiv).
     * @param maxCost Maximal Kosten (inklusiv).
     * @param costType Der Kostentyp, nach dem gesucht wird: ("kaltkosten", "sum_nebenkosten", or "warmkosten").
     * @return List eine Liste von Mietobjekten, welche den spezifizierten parametern entsprechen.
     */
    public List<Mietobjekt> getMietobjekteByCostRange(double minCost, double maxCost, String costType) {
        try {
            return mietobjektService.getMietobjekteByCostRange(minCost, maxCost, costType);
        } catch (Exception e) {
            // Fehlerbehandlung
            e.printStackTrace();
            return null;
        }
    }
}
