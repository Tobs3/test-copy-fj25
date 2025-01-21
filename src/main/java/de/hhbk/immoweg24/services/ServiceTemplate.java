package de.hhbk.immoweg24.services;

import de.hhbk.immoweg24.dao.GenericDao;
import de.hhbk.immoweg24.model.ModelTemplate;
import java.io.Serializable;
import java.util.List;

public class ServiceTemplate<T extends ModelTemplate, D extends GenericDao<T>> implements Serializable {
    
    protected D dao;
    
    
    /**
     * Constructor.
     * @param dao the database access object
     */
    public ServiceTemplate(D dao) {
        super();
        this.dao = dao;
    } 
    

    // -- 
    
    public D getDao() {
        return this.dao;
    }
   
    public T getNewItemInstance() {
        return getDao().getNewEntityInstance();
    }
    
    public List<T> getItemList() throws Exception {
        return getDao().list();
    }
    
}
