package de.hhbk.immoweg24.web.beans;

import de.hhbk.immoweg24.dao.MieterDao;
import de.hhbk.immoweg24.model.Mieter;
import de.hhbk.immoweg24.model.Zahlung;
import de.hhbk.immoweg24.services.ServiceTemplate;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.util.Collections;
import java.util.List;

@Named(value = "mieter")
@SessionScoped
public class MieterBean extends BeanTemplate<Mieter, ServiceTemplate<Mieter, MieterDao>> {
    private Mieter mieter;
    

    public List<Zahlung> getZahlungen() {
        return mieter != null ? mieter.getZahlungen() : Collections.emptyList();
    }
}