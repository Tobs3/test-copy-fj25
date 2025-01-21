package de.hhbk.immoweg24.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bankdaten")
public class Bankdaten extends ModelTemplate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "iban", length = 43, nullable = false)
    private String iban;

    @Column(name = "bic", length = 11, nullable = false)
    private String bic;

    @Column(name = "kontoinhaber", length = 100, nullable = false)
    private String kontoinhaber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mieter_id", referencedColumnName = "id")
    private Mieter mieter;

    public Bankdaten(long id, String iban, String bic, String kontoinhaber, Mieter mieter) {
        this.id = id;
        this.iban = iban;
        this.bic = bic;
        this.kontoinhaber = kontoinhaber;
        this.mieter = mieter;
    }

    public Bankdaten() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getKontoinhaber() {
        return kontoinhaber;
    }

    public void setKontoinhaber(String kontoinhaber) {
        this.kontoinhaber = kontoinhaber;
    }

    public Mieter getMieter() {
        return mieter;
    }

    public void setMieter(Mieter mieter) {
        this.mieter = mieter;
    }

    @Override
    public String toString() {
        return "Bankdaten{" +
                "id=" + id +
                ", iban='" + iban + '\'' +
                ", bic='" + bic + '\'' +
                ", kontoinhaber='" + kontoinhaber + '\'' +
                '}';
    }
}