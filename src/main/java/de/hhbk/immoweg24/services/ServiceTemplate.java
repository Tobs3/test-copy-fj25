package de.hhbk.immoweg24.services;

import de.hhbk.immoweg24.dao.GenericDao;
import de.hhbk.immoweg24.model.ModelTemplate;
import java.io.Serializable;
import java.util.List;


public class ServiceTemplate<T extends ModelTemplate, D extends GenericDao<T>> implements Serializable
{
  //-------------------------------------------------------------------------
  //  Constructor(s)
  //------------------------------------------------------------------------- 
    public ServiceTemplate(D dao) { super(); this.dao = dao; } 

  
  //-------------------------------------------------------------------------
  //  DAO
  //-------------------------------------------------------------------------     
    protected D dao;

    public D getDao() { return this.dao; }

    
  //-------------------------------------------------------------------------
  //  Item
  //-------------------------------------------------------------------------     
    public T getNewItemInstance() { return getDao().getNewEntityInstance(); }


  //-------------------------------------------------------------------------
  //  List
  //-------------------------------------------------------------------------     
    public List<T> getItemList() throws Exception 
    { 
        return getDao().list(); 
    }

    
    
}