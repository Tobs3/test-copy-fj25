package de.hhbk.immoweg24.utils.dataimport;

import de.hhbk.immoweg24.dao.GenericDao;
import de.hhbk.immoweg24.model.Bankdaten;


public class BankdatenImporter {
    
    private final GenericDao<Bankdaten> bankdatenDao = new GenericDao<>(Bankdaten.class);
            
    // -- 
    
    
    public BankdatenImporter() {
        
    }
    
    // --
}
