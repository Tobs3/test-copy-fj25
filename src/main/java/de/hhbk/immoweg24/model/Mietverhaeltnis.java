package de.hhbk.immoweg24.model;

import de.hhbk.immoweg24.model.enums.StatusMietverhaeltnis;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Mietverhaeltnis extends ModelTemplate {
    
    //-------------------------------------------------------------------------
    //  Variablen
    //-------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "mieter_id", nullable = false)
    private Mieter mieter; // Verknüpfung mit Mieter

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "mietobjekt_id", nullable = false)
    private Mietobjekt mietobjekt; // Verknüpfung mit Mietobjekt

    @Column(nullable = false)
    private LocalDate startDatum;

    private LocalDate endDatum;

    @Enumerated(EnumType.STRING)
    private StatusMietverhaeltnis status; // Enum  "AKTIV", "BEENDET", "GEKÜNDIGT"

    @Column
    private String notizen; // für weitere Infos
    
    //-------------------------------------------------------------------------
    //  Konstruktoren
    //-------------------------------------------------------------------------
    public Mietverhaeltnis() {}

    public Mietverhaeltnis(Mieter mieter, Mietobjekt mietobjekt, LocalDate startDatum) {
        this.mieter = mieter;
        this.mietobjekt = mietobjekt;
        this.startDatum = startDatum;
        this.status = StatusMietverhaeltnis.AKTIV; // Standardstatus
    }

    //-------------------------------------------------------------------------
    //  Getter und Setter
    //-------------------------------------------------------------------------
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Mieter getMieter() {
        return mieter;
    }

    public void setMieter(Mieter mieter) {
        this.mieter = mieter;
    }

    public Mietobjekt getMietobjekt() {
        return mietobjekt;
    }

    public void setMietobjekt(Mietobjekt mietobjekt) {
        this.mietobjekt = mietobjekt;
    }

    public LocalDate getStartDatum() {
        return startDatum;
    }

    public void setStartDatum(LocalDate startDatum) {
        this.startDatum = startDatum;
    }

    public LocalDate getEndDatum() {
        return endDatum;
    }

    public void setEndDatum(LocalDate endDatum) {
        this.endDatum = endDatum;
    }

    public StatusMietverhaeltnis getStatus() {
        return status;
    }

    public void setStatus(StatusMietverhaeltnis status) {
        this.status = status;
    }

    public String getNotizen() {
        return notizen;
    }

    public void setNotizen(String notizen) {
        this.notizen = notizen;
    }

    //-------------------------------------------------------------------------
    //  Hilfsmethoden
    //-------------------------------------------------------------------------
    public boolean isAktiv() {
        return this.status == StatusMietverhaeltnis.AKTIV;
    }

    public void beenden(LocalDate endDatum) {
        this.status = StatusMietverhaeltnis.BEENDET;
        this.endDatum = endDatum;
    }
    
    public boolean isGekuendigt() {
        return this.status == StatusMietverhaeltnis.GEKÜNDIGT;
    }

    /**
     * Dynamische Berechnung der Warmmiete basierend auf Mietobjekt.
     * @return Berechnete Warmmiete
     */
    public double getWarmmiete() {
        if (mietobjekt == null) {
            throw new IllegalStateException("Mietobjekt nicht definiert.");
        }
        return mietobjekt.getKaltkosten().add(mietobjekt.getSummeNebenkosten()).doubleValue();
    }
}