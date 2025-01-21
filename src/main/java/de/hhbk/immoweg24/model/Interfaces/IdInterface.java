package de.hhbk.immoweg24.model.Interfaces;
  
 
public interface IdInterface { 
    
    public long getId();
    
    public void setId(long id);
    
    default public boolean hasId() { return (getId() > 0); };     
    
}
