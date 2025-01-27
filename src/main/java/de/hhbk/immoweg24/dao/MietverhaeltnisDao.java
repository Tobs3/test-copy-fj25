package de.hhbk.immoweg24.dao;

import de.hhbk.immoweg24.model.Mietverhaeltnis;
import de.hhbk.immoweg24.model.enums.StatusMietobjekt;
import de.hhbk.immoweg24.model.enums.StatusMietverhaeltnis;

public class MietverhaeltnisDao extends GenericDao<Mietverhaeltnis>{
    
    public MietverhaeltnisDao() { super(Mietverhaeltnis.class); }
    
    public void MietobjektFreistellen(Mietverhaeltnis m) throws Exception {
        executeTransaction(session ->{
            Mietverhaeltnis mietverhaeltnis = session.get(Mietverhaeltnis.class, m.getId());
            if (mietverhaeltnis != null){
                if (mietverhaeltnis.getMietobjekt().getStatus() != StatusMietobjekt.VERMIETET || mietverhaeltnis.getMietobjekt().getStatus() != StatusMietobjekt.INSTANDHALTUNG || mietverhaeltnis.getMietobjekt().getStatus() != StatusMietobjekt.RESERVIERT){
                    mietverhaeltnis.getMietobjekt().setStatus(StatusMietobjekt.VERFÜGBAR);
                }
                session.remove(mietverhaeltnis);
            }
            return null;
        });
    }
}
