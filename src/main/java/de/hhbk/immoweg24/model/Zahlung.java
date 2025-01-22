package de.hhbk.immoweg24.model;

import de.hhbk.immoweg24.model.enums.StatusZahlung;
import jakarta.persistence.*;
import java.io.Serializable;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "zahlung")
public class Zahlung extends ModelTemplate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "betrag", nullable = false, precision = 10, scale = 2)
    private BigDecimal betrag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mieter_id", referencedColumnName = "id")
    private Mieter mieter;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bankdaten_id", referencedColumnName = "id")
    private Bankdaten bankdaten;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mietobjekt_id", referencedColumnName = "id")
    private Mietobjekt mietobjekt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusZahlung status = StatusZahlung.AUSSTEHEND;

    @Column(name = "verwendungszweck", columnDefinition = "TEXT")
    private String verwendungszweck;

    @Column(name = "datum", nullable = false)
    private LocalDate datum;

    public Zahlung() {
    }

    public Zahlung(BigDecimal betrag, Mieter mieter, Bankdaten bankdaten, Mietobjekt mietobjekt, StatusZahlung status, String verwendungszweck, LocalDate datum) {
        this.betrag = betrag;
        this.mieter = mieter;
        this.bankdaten = bankdaten;
        this.mietobjekt = mietobjekt;
        this.status = status;
        this.verwendungszweck = verwendungszweck;
        this.datum = datum;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getBetrag() {
        return betrag;
    }

    public void setBetrag(BigDecimal betrag) {
        this.betrag = betrag;
    }

    public Mieter getMieter() {
        return mieter;
    }

    public void setMieter(Mieter mieter) {
        this.mieter = mieter;
    }
    
    public Bankdaten getBankdaten() {
        return bankdaten;
    }

    public void setBankDaten(Bankdaten bankdaten) {
        this.bankdaten = bankdaten;
    }
    
    public Mietobjekt getMietobjekt() {
        return mietobjekt;
    }

    public void setMietobjekt(Mietobjekt mietobjekt) {
        this.mietobjekt = mietobjekt;
    }

    public StatusZahlung getStatus() {
        return status;
    }

    public void setStatus(StatusZahlung status) {
        this.status = status;
    }

    public String getVerwendungszweck() {
        return verwendungszweck;
    }

    public void setVerwendungszweck(String verwendungszweck) {
        this.verwendungszweck = verwendungszweck;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public boolean isZahlungEingegangen() {
        return StatusZahlung.EINGEGANGEN.equals(this.status);
    }

    public boolean isBetragNegativ() {
        return this.betrag.compareTo(BigDecimal.ZERO) < 0;
    }

    @Override
    public String toString() {
        return "Zahlung{" +
                "id=" + id +
                ", betrag=" + betrag +
                ", mieter=" + (mieter != null ? mieter.getId() : null) +
                ", mietobjekt=" + (mietobjekt != null ? mietobjekt.getId() : null) +
                ", status=" + status +
                ", verwendungszweck='" + verwendungszweck + '\'' +
                ", datum=" + datum +
                '}';
    }
}