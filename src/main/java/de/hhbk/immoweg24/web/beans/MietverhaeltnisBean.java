package de.hhbk.immoweg24.web.beans;

import de.hhbk.immoweg24.dao.MietverhaeltnisDao;
import de.hhbk.immoweg24.model.Mietverhaeltnis;
import de.hhbk.immoweg24.services.ServiceTemplate;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named(value = "mietverhaeltnisse")
@ViewScoped
public class MietverhaeltnisBean extends BeanTemplate<Mietverhaeltnis, ServiceTemplate<Mietverhaeltnis, MietverhaeltnisDao>>{
    
    public MietverhaeltnisBean() { super(new ServiceTemplate<Mietverhaeltnis, MietverhaeltnisDao>(new MietverhaeltnisDao())); }
    
    @PostConstruct
    public void init(){
        resetItem();
    }
}
