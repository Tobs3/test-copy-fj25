package de.hhbk.immoweg24.web.beans;
 
import de.hhbk.immoweg24.model.Benutzer;
import de.hhbk.immoweg24.model.enums.PersonAnrede;
import de.hhbk.immoweg24.services.BenutzerService;
import java.util.Arrays;
import java.util.List;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext; 
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;


@Named(value = "benutzer") 
@SessionScoped 
public class BenutzerBean extends BeanTemplate<Benutzer, BenutzerService>
{
  //-------------------------------------------------------------------------
  //  Constructor(s)
  //-------------------------------------------------------------------------     
    public BenutzerBean() { super(new BenutzerService()); } 

    @PostConstruct
    public void init()
    { 
        resetItem(); 
    }


  //-------------------------------------------------------------------------
  //  Else
  //-------------------------------------------------------------------------     
    public List getAnredeOptionen() { return Arrays.asList(PersonAnrede.values()); }
 
    
  //-------------------------------------------------------------------------
  //  Benutzerdaten
  //-------------------------------------------------------------------------     
    protected String benutzername = null;
    protected String passwort = null;

    public String getBenutzername() { return benutzername; }

    public void setBenutzername(String benutzername) { this.benutzername = benutzername; }

    public String getPasswort() { return passwort; }

    public void setPasswort(String passwort) { this.passwort = passwort; }
      
    
  //-------------------------------------------------------------------------
  //  Login / Logout
  //-------------------------------------------------------------------------     
    public String doLogin() 
    {   
        HttpSession websession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true); 
        websession.setAttribute("MyLoginObject", true);
        return "backend/empty.xhtml?faces-redirect=true"; 
    } 
    
    public String doLogout() 
    {  
        HttpSession websession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true); 
        websession.removeAttribute("MyLoginObject"); 
        return "/login.xhtml?faces-redirect=true";
    }  
    
    
    
}