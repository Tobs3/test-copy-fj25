package de.hhbk.immoweg24.converter;

import de.hhbk.immoweg24.dao.AdresseDao;
import de.hhbk.immoweg24.model.Adresse;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

@FacesConverter(forClass = Adresse.class)
public class AdresseConverter implements Converter<Adresse> {
    @Inject
    private AdresseDao adresseDao;

    @Override
    public String getAsString(FacesContext context, UIComponent component, Adresse adresse) {
        if (adresse == null) {
            return "";
        }
        return String.valueOf(adresse.getId());
    }

    @Override
    public Adresse getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            return adresseDao.getAdresseById(Long.parseLong(value)); // Adresse aus der Datenbank laden
        } catch (Exception e) {
            return null;
        }
    }
}
