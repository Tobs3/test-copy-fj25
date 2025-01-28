package de.hhbk.immoweg24.web.beans;

import de.hhbk.immoweg24.dao.MieterDao;
import de.hhbk.immoweg24.model.Mieter;
import de.hhbk.immoweg24.services.ServiceTemplate;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;


@Named(value = "mieter")
@SessionScoped
public class MieterBean extends BeanTemplate<Mieter, ServiceTemplate<Mieter, MieterDao>>{
    public MieterBean() { super(new ServiceTemplate<Mieter, MieterDao>(new MieterDao())); }

    @PostConstruct
    public void init(){
        resetItem();
    }
}
