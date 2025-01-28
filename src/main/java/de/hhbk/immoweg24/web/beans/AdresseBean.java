package de.hhbk.immoweg24.web.beans;

import de.hhbk.immoweg24.dao.AdresseDao;
import de.hhbk.immoweg24.dao.MieterDao;
import de.hhbk.immoweg24.model.Adresse;
import de.hhbk.immoweg24.model.Mieter;
import de.hhbk.immoweg24.services.ServiceTemplate;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.util.List;


@Named(value = "adresse")
@SessionScoped
public class AdresseBean extends BeanTemplate<Adresse, ServiceTemplate<Adresse, AdresseDao>>{
    public AdresseBean() { super(new ServiceTemplate<Adresse, AdresseDao>(new AdresseDao())); }

    @PostConstruct
    public void init(){
        resetItem();
    }

    public List<Adresse> getAdresseOptionen() { return this.itemList; }
}
