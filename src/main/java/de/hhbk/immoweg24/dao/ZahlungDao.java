package de.hhbk.immoweg24.dao;

import de.hhbk.immoweg24.model.Bankdaten;
import de.hhbk.immoweg24.model.Mieter;
import de.hhbk.immoweg24.model.Mietobjekt;
import de.hhbk.immoweg24.model.Zahlung;
import de.hhbk.immoweg24.model.enums.StatusZahlung;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ZahlungDao extends GenericDao<Zahlung> {
    
    public ZahlungDao() {
        super(Zahlung.class);
    }
    
        
    //-------------------------------------------------------------------------
    //  GET WITH FILTER
    
    public Zahlung getOrCreate(BigDecimal betrag, Mieter mieter, Bankdaten bankdaten, Mietobjekt mietobjekt, String verwendungszweck, LocalDate datum, StatusZahlung status) throws Exception {
        HashMap<String, Object> searchFilters = new HashMap<>();
        
        if (betrag != null) searchFilters.put("betrag", betrag);
        if (mieter != null) searchFilters.put("mieter", mieter);
        if (bankdaten != null) searchFilters.put("bankdaten", bankdaten);
        if (mietobjekt != null) searchFilters.put("mietobjekt", mietobjekt);
        if (verwendungszweck != null && !verwendungszweck.isEmpty()) searchFilters.put("verwendungszweck", verwendungszweck);
        if (datum != null) searchFilters.put("datum", datum);
        if (status != null) searchFilters.put("status", status);
        
        return getOrCreate(searchFilters);
    }
    
    public Zahlung getOrCreate(Map<String, Object> values) throws Exception {
        Zahlung matchingZahlung = null;
        
        try {
            matchingZahlung = findByValues(values);
        } catch (Exception e) {
            throw e;
        }
        if (matchingZahlung == null) {
            Zahlung newZahlung = new Zahlung();
            try {
                BigDecimal betrag = (BigDecimal) values.get("betrag"); 
                if (betrag != null) newZahlung.setBetrag(betrag);
                
                Mieter mieter = (Mieter) values.get("mieter"); 
                if (mieter != null) newZahlung.setMieter(mieter);
                
                Bankdaten bankdaten = (Bankdaten) values.get("bankdaten"); 
                if (bankdaten != null) newZahlung.setBankdaten(bankdaten);
                
                Mietobjekt mietobjekt = (Mietobjekt) values.get("mietobjekt"); 
                if (mietobjekt != null) newZahlung.setMietobjekt(mietobjekt);
                
                String verwendungszweck = String.valueOf(values.get("verwendungszweck")); 
                if (verwendungszweck != null && !verwendungszweck.isEmpty()) newZahlung.setVerwendungszweck(verwendungszweck);
                
                LocalDate datum = (LocalDate) values.get("datum"); 
                if (datum != null) newZahlung.setDatum(datum);
                
                StatusZahlung status = (StatusZahlung) values.get("status");
                if (status != null) newZahlung.setStatus(status);
            } catch (Exception e) {
                throw e;
            }
            return newZahlung;
        } else {
            return matchingZahlung;
        }
    }
    
}
