package de.hhbk.immoweg24.model;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import de.hhbk.immoweg24.model.Mietverhaeltnis;

import java.util.List;

public class JRMietverhaeltnisDataSource implements JRDataSource {
    private int index = -1;
    private List<Mietverhaeltnis> mietverhaeltnisList;

    public JRMietverhaeltnisDataSource(List<Mietverhaeltnis> list) {
        this.mietverhaeltnisList = list;
    }

    @Override
    public boolean next() throws JRException {
        index++;
        return (mietverhaeltnisList != null && index < mietverhaeltnisList.size());
    }

    @Override
    public Object getFieldValue(JRField jrField) throws JRException {
        Mietverhaeltnis mietverhaeltnis = mietverhaeltnisList.get(index);

        if (mietverhaeltnis != null) {
            switch (jrField.getName()) {
                case "MietverhaeltnisId":
                    return mietverhaeltnis.getId();
                case "MieterName":
                    return mietverhaeltnis.getMieter().getNachname();
                case "MieteKalt":
                    return mietverhaeltnis.getMietobjekt().getKaltkosten();
                case "MieteWarm":
                    return mietverhaeltnis.getMietobjekt().getKaltkosten()
                            .add(mietverhaeltnis.getMietobjekt().getSummeNebenkosten());
                case "Status":
                    return mietverhaeltnis.getStatus();
                default:
                    return null;
            }
        }
        return null;
    }
}