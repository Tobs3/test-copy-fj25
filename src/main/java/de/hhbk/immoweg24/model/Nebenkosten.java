package de.hhbk.immoweg24.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import de.hhbk.immoweg24.model.enums.StatusNebenkosten;

@Entity
@Table(name = "Nebenkosten")
public class Nebenkosten {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "mietobjekt_id", nullable = false)
    private Mietobjekt mietobjekt;

    @Column(name = "bezeichnung", nullable = false, length = 50)
    private String bezeichnung;

    @Column(name = "betrag", nullable = false, precision = 10, scale = 2)
    private BigDecimal betrag;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusNebenkosten status;

    public Nebenkosten(Long id, Mietobjekt mietobjekt, String bezeichnung, BigDecimal betrag, StatusNebenkosten status) {
        this.id = id;
        this.mietobjekt = mietobjekt;
        this.bezeichnung = bezeichnung;
        this.betrag = betrag;
        this.status = status;
    }

    public Nebenkosten() {
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Mietobjekt getMietobjekt() {
        return mietobjekt;
    }

    public void setMietobjekt(Mietobjekt mietobjekt) {
        this.mietobjekt = mietobjekt;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public BigDecimal getBetrag() {
        return betrag;
    }

    public void setBetrag(BigDecimal betrag) {
        this.betrag = betrag;
    }

    public StatusNebenkosten getStatus() {
        return status;
    }

    public void setStatus(StatusNebenkosten status) {
        this.status = status;
    }
    
    

}