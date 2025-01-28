package de.hhbk.immoweg24.dao;

import de.hhbk.immoweg24.model.Adresse;
import de.hhbk.immoweg24.model.Mietobjekt;
import de.hhbk.immoweg24.model.enums.StatusMietobjekt;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.query.Query;


/**
 * Datenbankzugriff für Mietobjekte
 */
public class MietobjektDao extends GenericDao<Mietobjekt> {
    
    private AdresseDao adresseDao = new AdresseDao();
    
    public MietobjektDao() {
        super(Mietobjekt.class);
    }
    
    
    //-------------------------------------------------------------------------
    //  GET WITH FILTER
    
    public Mietobjekt getOrCreate(Integer objektnummer, String typ, Adresse adresse, BigDecimal kaltkosten, BigDecimal sumNebenkosten, StatusMietobjekt status) throws Exception {
        HashMap<String, Object> searchFilters = new HashMap<>();
        
        // define search Filters that exist
        if (objektnummer != null && objektnummer > 0) searchFilters.put("objektnummer", objektnummer);
        if (typ != null && !typ.isEmpty()) searchFilters.put("typ", typ);
        if (adresse != null) searchFilters.put("adresse", adresse.getId());
        if (kaltkosten != null && kaltkosten.doubleValue() > 0) searchFilters.put("kaltkosten", kaltkosten);
        if (sumNebenkosten != null && sumNebenkosten.doubleValue() > 0) searchFilters.put("sumNebenkosten", sumNebenkosten);
        if (status != null) searchFilters.put("status", status);
        
        return getOrCreate(searchFilters);
    }
    
    public Mietobjekt getOrCreate(Map<String, Object> values) throws Exception {
        Mietobjekt matchingMietobjekt = null;
        try {
            /* values could be outdated in CSV, so we only 
               check the ObjectNumber for matching data 
               - Mietobjekt specific behavior - */
            
            // TODO: not sure about this one. maybe add possibility to check for all values in mietobjects..
            Map<String, Object> reducedFilter = new HashMap<>();
            reducedFilter.put("objektnummer", values.get("objektnummer"));
            matchingMietobjekt = findByValues(reducedFilter);
        } catch (Exception e) {
            throw e;
        }
        if (matchingMietobjekt == null) { 
            // TODO: TEST ME !!
            Mietobjekt newMietobjekt = new Mietobjekt();
            try {
                newMietobjekt.setObjektnummer((int) values.get("objektnummer"));
                newMietobjekt.setTyp(String.valueOf(values.get("typ")));
                newMietobjekt.setAdresse((Adresse) adresseDao.getById(Long.getLong((String) values.get("adresse")))); // sus // should contain FK(Adressen)
                newMietobjekt.setKaltkosten((BigDecimal) values.get("kaltkosten"));
                newMietobjekt.setSummeNebenkosten((BigDecimal) values.get("summeNebenkosten"));
                newMietobjekt.setStatus(StatusMietobjekt.valueOf((String) values.get("status")));
            } catch (Exception e) {
                throw e;
            }
            return newMietobjekt;
        } else {
            return matchingMietobjekt;
        }
    }
        
    
    /**
     * Erhalte alle Mietobjekte, die den gegebenen Status besitzen als Liste.
     *
     * @param status der Status des Mietobjekts (e.g., StatusMietobjekt.VERFÜGBAR).
     * @return List aller Mietobjekte mit dem gegebenen Status.
     * @throws Exception wenn die Datenbankabfrage fehlschlägt.
     * @deprecated should be osboslete, use {@link #findByFields} instead.
     */
    public List<Mietobjekt> getByStatus(StatusMietobjekt status) throws Exception {
        return (List<Mietobjekt>) executeQuery(session -> {
            Query<Mietobjekt> query = session.createQuery(
                "FROM Mietobjekt WHERE status = :status",
                Mietobjekt.class
            );
            query.setParameter("status", status.name());
            return query.getResultList();
        });
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
     * @throws Exception falls die Datenbankabfrage fehlschlägt.
     */
    public List<Mietobjekt> getByCostRange(double minCost, double maxCost, String costType) throws Exception {
        return (List<Mietobjekt>) executeQuery(session -> {
            String costColumn;
            switch (costType.toLowerCase()) {
                case "kaltkosten":
                    costColumn = "kaltkosten";
                    break;
                case "sum_nebenkosten":
                    costColumn = "sum_nebenkosten";
                    break;
                case "warmkosten":
                    costColumn = "(kaltkosten + sum_nebenkosten)";
                    break;
                default:
                    throw new IllegalArgumentException("Ungültiger Kostentyp: " 
                            + costType.toLowerCase() + " Erwartet: "
                            + "'kaltkosten', 'sum_nebenkosten', oder 'warmkosten'.");
            }

            Query<Mietobjekt> query = session.createQuery(
                "FROM Mietobjekt WHERE " + costColumn + 
                " BETWEEN :minCost AND :maxCost", Mietobjekt.class
            );
            query.setParameter("minCost", minCost);
            query.setParameter("maxCost", maxCost);
            return query.getResultList();
        });
    }
    
    public Mietobjekt getMietobjekt(Long id)  throws Exception {
        return (Mietobjekt) executeTransaction(session -> session.get(Mietobjekt.class, id));
    }
    
    
}
