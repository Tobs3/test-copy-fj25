package de.hhbk.immoweg24.dao;

import de.hhbk.immoweg24.model.Mietverhaeltnis;
import de.hhbk.immoweg24.model.enums.StatusMietobjekt;

public class MietverhaeltnisDao extends GenericDao<Mietverhaeltnis>{
    
    public MietverhaeltnisDao() { super(Mietverhaeltnis.class); }
    
    public void deleteItemOnly(Mietverhaeltnis m) throws Exception {
        executeTransaction(session ->{
            Mietverhaeltnis mietverhaeltnis = session.get(Mietverhaeltnis.class, m.getId());
            if (mietverhaeltnis != null){
                if (mietverhaeltnis.isGekuendigt()){
                    mietverhaeltnis.getMietobjekt().setStatus(StatusMietobjekt.VERFÜGBAR);
                }
                session.remove(mietverhaeltnis);
            }
            return null;
        });
    }
}
