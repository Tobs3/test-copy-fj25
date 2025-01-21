package de.hhbk.immoweg24.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "mieter")
public class Mieter extends ModelTemplate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adresse_id", referencedColumnName = "id")
    private Adresse adresse;

    @Column(name = "telefon", length = 20)
    private String telefon;

    @Column(name = "email", length = 100)
    private String email;

    @OneToMany(mappedBy = "mieter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bankdaten> bankdaten;

    public Mieter(long id, String name, Adresse adresse, String telefon, String email, List<Bankdaten> bankdaten) {
        this.id = id;
        this.name = name;
        this.adresse = adresse;
        this.telefon = telefon;
        this.email = email;
        this.bankdaten = bankdaten;
    }

    public Mieter() {
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

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Bankdaten> getBankdaten() {
        return bankdaten;
    }

    public void setBankdaten(List<Bankdaten> bankdaten) {
        this.bankdaten = bankdaten;
    }    

    @Override
    public String toString() {
        return "Mieter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", telefon='" + telefon + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}