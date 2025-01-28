package de.hhbk.immoweg24.web.beans;

import de.hhbk.immoweg24.model.ModelTemplate;
import de.hhbk.immoweg24.services.ServiceTemplate;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List; 
import org.primefaces.PrimeFaces;


public abstract class BeanTemplate<T extends ModelTemplate, S extends ServiceTemplate> implements Serializable
{
  //-------------------------------------------------------------------------
  //  Constructor(s)
  //-------------------------------------------------------------------------     
    public BeanTemplate() { super(); } 

    public BeanTemplate(S service) { this(); this.service = service; } 
    
    
  //-------------------------------------------------------------------------
  //  Service
  //-------------------------------------------------------------------------     
    protected S service = null;
    
    protected S getService() { return this.service; }
    
    
  //-------------------------------------------------------------------------
  //  List
  //-------------------------------------------------------------------------     
    protected List<T> itemList = null; 
    
    public void resetItemList() 
    { 
        itemList = null; 
        updateContentForm(); 
    };
    
    public List<T> getItemList()
    {
        if (itemList == null) 
        { 
            try            
            {
                itemList = getService().getItemList(); 
            } 
            catch (Exception e) { this.itemList = null; }
            if (itemList == null) { itemList = new ArrayList<>(); }
        }
        return itemList;
    }

    public void setItemList(List itemList)
    {
        this.itemList = itemList;
    }
    

  //-------------------------------------------------------------------------
  //  Item
  //-------------------------------------------------------------------------     
    protected T item = null; 
    
    public T getItem() { if (item == null) { item = (T) getService().getNewItemInstance(); } return item; }

    public void setItem(T item) { this.item = item; }
    
    public long getItemId() { return getItem().getId(); }
    
    public void setItemId(long id) 
    { 
        try        
        {
            setItem((T) getService().getDao().getById(id)); 
        } 
        catch (Exception e)
        {
            handleException("Fehler", e);
        }
    }
    
    public void resetItem() { this.item = null; }
    
    public void saveItem(T item)
    {
        if (item == null) { return; } 
        if (!item.hasId()) { resetItemList(); }
        try        
        {
            if (getService().getDao().save(item) != null) { handleInfo("OK", "Eintrag gespeichert."); } 
        } 
        catch (Exception e)
        {
            System.out.println(">>> ERROR --> " + e.getMessage());
            handleException("Fehler", e);
        }
        resetItemList(); 
    }
    
    public void saveItem() { saveItem(this.item); } 
 
    public void removeItem(T item) 
    {  
        try        
        {
            if (getService().getDao().deleteItem(item)) { handleInfo("OK", "Eintrag entfernt."); }
        } 
        catch (Exception e)
        {
            handleException("Fehler", e);
        }
        resetItemList(); 
    }
    
    public void removeItem() { removeItem(this.item); }
    

    
  //-------------------------------------------------------------------------
  //  Else ... 
  //-------------------------------------------------------------------------     
    protected void setMessage(String comonentId, FacesMessage.Severity type, String header, String msg) { FacesContext.getCurrentInstance().addMessage(comonentId, new FacesMessage(type, header, msg)); }    
    
    protected void setErrorMessage(String header, String msg) { setMessage(null, FacesMessage.SEVERITY_ERROR, header, msg); }
    
    protected void setInfoMessage(String header, String msg) { setMessage(null, FacesMessage.SEVERITY_INFO, header, msg); }
    
    public void componentAjaxUpdate(String s) { if (s!=null && !s.isBlank()) { PrimeFaces.current().ajax().update(s); } }
     
    public void updateContentForm() { componentAjaxUpdate(":contentForm"); }

    public void updateMessageBox() { componentAjaxUpdate(":messageBox"); }

    protected void handleException(String header, Exception e) 
    {
        setErrorMessage(header, e.getMessage());
        updateMessageBox();
    }
    
    protected void handleInfo(String header, String msg) 
    {
        setInfoMessage(header, msg);
        updateMessageBox();
    }
    
    
    
}