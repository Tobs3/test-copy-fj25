package de.hhbk.immoweg24.dao;

import de.hhbk.immoweg24.model.Mietobjekt;
import de.hhbk.immoweg24.model.enums.StatusMietobjekt;
import java.util.List;
import org.hibernate.query.Query;


/**
 * Datenbankzugriff für Mietobjekte
 */
public class MietobjektDao extends GenericDao<Mietobjekt> {
    
    
    public MietobjektDao() {
        super(Mietobjekt.class);
    }
    
    
    //-------------------------------------------------------------------------
    //  GET WITH FILTER
    
    
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
}
