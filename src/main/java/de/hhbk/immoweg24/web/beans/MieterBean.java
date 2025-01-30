package de.hhbk.immoweg24.web.beans;

import de.hhbk.immoweg24.dao.MieterDao;
import de.hhbk.immoweg24.model.Mieter;
import de.hhbk.immoweg24.services.ServiceTemplate;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

/**
 * Das MieterBean dient als Managed Bean für die Verwaltung von Mietern im Frontend.
 * Es stellt Methoden bereit, um Mieterinformationen anzuzeigen und zu bearbeiten.
 * 
 * Hauptfunktionen:
 * - Laden von Mieterdaten für die UI-Darstellung
 * - Erstellen und Bearbeiten von Mietern
 * - Verknüpfung von Mietern mit Mietobjekten und Zahlungen
 */
@Named(value = "mieter")
@SessionScoped
public class MieterBean extends BeanTemplate<Mieter, ServiceTemplate<Mieter, MieterDao>>{
    public MieterBean() { super(new ServiceTemplate<Mieter, MieterDao>(new MieterDao())); }

    @PostConstruct
    public void init(){
        resetItem();
    }
}
