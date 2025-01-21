package de.hhbk.immoweg24.model;

import java.io.Serializable;

public class Benutzerrollen extends ModelTemplate implements Serializable {
    private long id;
    private String name;
    private String berechtigungen; // JSON
    private int gewicht;

    public Benutzerrollen(long id, String name, String berechtigungen, int gewicht) {
        this.id = id;
        this.name = name;
        this.berechtigungen = berechtigungen;
        this.gewicht = gewicht;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBerechtigungen() {
        return berechtigungen;
    }

    public void setBerechtigungen(String berechtigungen) {
        this.berechtigungen = berechtigungen;
    }
    
    public int getGewicht() {
        return gewicht;
    }
    
    public void setGewicht(int gewicht) {
        this.gewicht = gewicht;
    }
    
}
