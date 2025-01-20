package de.hhbk.immoweg24.model;

import de.hhbk.immoweg24.model.enums.StatusMietobjekt;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "mietobjekt")
public class Mietobjekt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "objektnummer", nullable = false, unique = true)
    private Integer objektnummer;

    @Column(name = "typ", length = 50)
    private String typ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adresse_id", referencedColumnName = "id")
    private Adresse adresse;

    @Column(name = "kaltkosten", nullable = false, precision = 10, scale = 2)
    private BigDecimal kaltkosten;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusMietobjekt status = StatusMietobjekt.VERFÜGBAR;

    public Mietobjekt() {}

    public Mietobjekt(Integer objektnummer, String typ, Adresse adresse, BigDecimal kaltkosten, StatusMietobjekt status) {
        this.objektnummer = objektnummer;
        this.typ = typ;
        this.adresse = adresse;
        this.kaltkosten = kaltkosten;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getObjektnummer() {
        return objektnummer;
    }

    public void setObjektnummer(Integer objektnummer) {
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