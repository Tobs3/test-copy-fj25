package de.hhbk.immoweg24.web.beans;

import de.hhbk.immoweg24.dao.MietobjektDao;
import de.hhbk.immoweg24.model.Mietobjekt;
import de.hhbk.immoweg24.model.enums.StatusMietobjekt;
import de.hhbk.immoweg24.services.ServiceTemplate;
import java.util.Arrays;
import java.util.List;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;


/**
 * Das MietobjektBean verwaltet Mietobjekte in der Benutzeroberfläche.
 * Es stellt Methoden zur Verfügung, um Immobilien anzuzeigen und zu bearbeiten.
 * 
 * Hauptfunktionen:
 * - Anzeigen und Bearbeiten von Mietobjektdetails
 *
 */

@Named(value = "mietobjekt")
@SessionScoped
public class MietObjektBean extends BeanTemplate<Mietobjekt, ServiceTemplate<Mietobjekt, MietobjektDao>>{
    public MietObjektBean() { super(new ServiceTemplate<Mietobjekt, MietobjektDao>(new MietobjektDao())); }
    
    @PostConstruct
    public void init(){
        resetItem();
    }

    public List<StatusMietobjekt> getMietObjektOptionen() { return Arrays.asList(StatusMietobjekt.values()); }
}
