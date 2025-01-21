package de.hhbk.immoweg24.model;

import de.hhbk.immoweg24.model.enums.StatusDokument;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "dokument")
public class Dokument extends ModelTemplate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "dateiname", length = 255, nullable = false)
    private String dateiname;

    @Column(name = "beschreibung", length = 255)
    private String beschreibung;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mietobjekt_id", referencedColumnName = "id")
    private Mietobjekt mietobjekt;

    @Column(name = "dateiformat", length = 50, nullable = false)
    private String dateiformat;

    @Column(name = "datum", nullable = false)
    private LocalDate datum;

    @Column(name = "version", nullable = false, precision = 10, scale = 2)
    private Double version;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusDokument status = StatusDokument.AKTIV;

    @Column(name = "dateipfad", columnDefinition = "TEXT", nullable = false)
    private String dateipfad;

    public Dokument(long id, String dateiname, String beschreibung, Mietobjekt mietobjekt, String dateiformat, LocalDate datum, Double version, String dateipfad) {
        this.id = id;
        this.dateiname = dateiname;
        this.beschreibung = beschreibung;
        this.mietobjekt = mietobjekt;
        this.dateiformat = dateiformat;
        this.datum = datum;
        this.version = version;
        this.dateipfad = dateipfad;
    }

    public Dokument() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDateiname() {
        return dateiname;
    }

    public void setDateiname(String dateiname) {
        this.dateiname = dateiname;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public Mietobjekt getMietobjekt() {
        return mietobjekt;
    }

    public void setMietobjekt(Mietobjekt mietobjekt) {
        this.mietobjekt = mietobjekt;
    }

    public String getDateiformat() {
        return dateiformat;
    }

    public void setDateiformat(String dateiformat) {
        this.dateiformat = dateiformat;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public Double getVersion() {
        return version;
    }

    public void setVersion(Double version) {
        this.version = version;
    }

    public StatusDokument getStatus() {
        return status;
    }

    public void setStatus(StatusDokument status) {
        this.status = status;
    }

    public String getDateipfad() {
        return dateipfad;
    }

    public void setDateipfad(String dateipfad) {
        this.dateipfad = dateipfad;
    }
    


}