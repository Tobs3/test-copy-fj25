package de.hhbk.immoweg24.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "adresse")
public class Adresse extends ModelTemplate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "strasse", length = 100, nullable = false)
    private String strasse;

    @Column(name = "hausnummer", length = 10, nullable = false)
    private String hausnummer;

    @Column(name = "plz", length = 10, nullable = false)
    private String plz;

    @Column(name = "stadt", length = 50, nullable = false)
    private String stadt;

    @Column(name = "land", length = 50, nullable = false)
    private String land;
    
    @Column(name="hauptadresse", nullable = false)
    private boolean hauptadresse;

    public Adresse() {}

    public Adresse(String strasse, String hausnummer, String plz, String stadt, String land, boolean hauptadresse) {
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.plz = plz;
        this.stadt = stadt;
        this.land = land;
        this.hauptadresse = hauptadresse;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getStadt() {
        return stadt;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }
    
    public boolean getHauptadresse () {
        return hauptadresse;
    }
    
    public void setHauptadresse(boolean hauptadresse) {
        this.hauptadresse = hauptadresse;
    }

    @Override
    public String toString() {
        return "Adresse{" +
                "id=" + id +
                ", strasse='" + strasse + '\'' +
                ", hausnummer='" + hausnummer + '\'' +
                ", plz='" + plz + '\'' +
                ", stadt='" + stadt + '\'' +
                ", land='" + land + '\'' +
                ", hauptadresse='" + hauptadresse + '\'' +
                '}';
    }
}