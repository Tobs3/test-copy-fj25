package de.hhbk.immoweg24.model;

public class Benutzerrollen {
    private int id;
    private String name;
    private String berechtigungen;

    public Benutzerrollen(int id, String name, String berechtigungen) {
        this.id = id;
        this.name = name;
        this.berechtigungen = berechtigungen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    
    
}
