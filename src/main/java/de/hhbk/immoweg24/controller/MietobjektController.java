package de.hhbk.immoweg24.controller;

import de.hhbk.immoweg24.model.Adresse;
import de.hhbk.immoweg24.model.Mietobjekt;
import de.hhbk.immoweg24.model.enums.StatusMietobjekt;
import de.hhbk.immoweg24.services.MietobjektService;
import java.math.BigDecimal;
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
    //  CRUD
    
    /**
     * Erstellt ein neues Mietobjekt, generiert seine ID und speichert es in 
     * der Datenbank.
     * 
     * @param objektnummer
     * @param typ
     * @param adresse
     * @param kaltkosten
     * @param sumNebenkosten
     * @param status
     * @return Mietobjekt mit ID
     */
    public Mietobjekt createMietobjekt(int objektnummer, String typ, Adresse adresse, BigDecimal kaltkosten, BigDecimal sumNebenkosten, StatusMietobjekt status) {
        Mietobjekt newMietobjekt = new Mietobjekt(objektnummer, typ, adresse, kaltkosten, sumNebenkosten, status);
        return saveMietobjekt(newMietobjekt);
    }
    
    /**
     * Speichert das gegebene Mietobjekt in der Datenbank.
     * 
     * @param mietobjekt 
     * @return das gespeicherte {@link Mietobjekt}
     */
    public Mietobjekt saveMietobjekt(Mietobjekt mietobjekt) {
        try {
            return mietobjektService.saveMietobjekt(mietobjekt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteMietobjekt(Mietobjekt mietobjekt) {
        try {
            return mietobjektService.deleteMietobjekt(mietobjekt);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
