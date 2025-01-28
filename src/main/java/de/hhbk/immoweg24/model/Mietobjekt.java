package de.hhbk.immoweg24.model;

import de.hhbk.immoweg24.dao.AdresseDao;
import de.hhbk.immoweg24.model.enums.StatusMietobjekt;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "mietobjekt")
public class Mietobjekt extends ModelTemplate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "objektnummer", nullable = false, unique = true)
    private int objektnummer;

    @Column(name = "typ", length = 50)
    private String typ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adresse_id", referencedColumnName = "id")
    private Adresse adresse;

    @Column(name = "kaltkosten", nullable = false, precision = 10, scale = 2)
    private BigDecimal kaltkosten;

    @Column(name = "sum_nebenkosten", nullable = false, precision = 10, scale = 2)
    private BigDecimal sumNebenkosten; // Summe aller aktiven Nebekosten des Mietobjekts
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusMietobjekt status = StatusMietobjekt.VERFÜGBAR;

    public Mietobjekt() {}

    public Mietobjekt(int objektnummer, String typ, Adresse adresse, BigDecimal kaltkosten, BigDecimal sumNebenkosten, StatusMietobjekt status) {
        this.objektnummer = objektnummer;
        this.typ = typ;
        this.adresse = adresse;
        this.kaltkosten = kaltkosten;
        this.sumNebenkosten = sumNebenkosten;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getObjektnummer() {
        return objektnummer;
    }

    public void setObjektnummer(int objektnummer) {
        this.objektnummer = objektnummer;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public BigDecimal getKaltkosten() {
        return kaltkosten;
    }

    public void setKaltkosten(BigDecimal kaltkosten) {
        this.kaltkosten = kaltkosten;
    }
    
    public BigDecimal getSummeNebenkosten() {
        return sumNebenkosten;
    }

    public void setSummeNebenkosten(BigDecimal summeNebenkosten) {
        this.sumNebenkosten = summeNebenkosten;
    }
    
    public StatusMietobjekt getStatus() {
        return status;
    }

    public void setStatus(StatusMietobjekt status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Mietobjekt{" +
                "id=" + id +
                ", objektnummer=" + objektnummer +
                ", typ='" + typ + '\'' +
                ", adresse=" + (adresse != null ? adresse.getId() : null) +
                ", kaltkosten=" + kaltkosten +
                ", status=" + status +
                '}';
    }
}