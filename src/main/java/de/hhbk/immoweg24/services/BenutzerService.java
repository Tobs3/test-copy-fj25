package de.hhbk.immoweg24.services;

import de.hhbk.immoweg24.dao.BenutzerDao;
import de.hhbk.immoweg24.model.Benutzer;
import jakarta.transaction.Transactional;


public class BenutzerService extends ServiceTemplate<Benutzer, BenutzerDao>
{
  //-------------------------------------------------------------------------
  //  Constructor(s)
  //-------------------------------------------------------------------------     
    public BenutzerService()
    {
        super(new BenutzerDao());
    }
    
    
  //-------------------------------------------------------------------------
  //  Check login information
  //-------------------------------------------------------------------------  
    @Transactional
    public boolean checkLogin(String username, String password) throws Exception
    {
        return getDao().checkLogin(username, password);
    }



}