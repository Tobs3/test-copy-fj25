package de.hhbk.immoweg24.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Die Mieter-Klasse repräsentiert einen Mieter eines Mietobjekts.
 * Sie speichert persönliche Informationen wie Name, Adresse, Telefonnummer, 
 * E-Mail und Bankdaten und hat eine Beziehung zu den entsprechenden Zahlungen.
 */

@Entity
@Table(name = "mieter")
public class Mieter extends ModelTemplate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "vorname", length = 100, nullable = false)
    private String vorname;
    
    @Column(name = "nachname", length = 100, nullable = false)
    private String nachname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adresse_id", referencedColumnName = "id")
    private Adresse adresse;

    @Column(name = "telefon", length = 20)
    private String telefon;

    @Column(name = "email", length = 100)
    private String email;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bankdaten> bankdaten;

    @OneToMany(mappedBy = "mieter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Zahlung> zahlungen;

    public List<Zahlung> getZahlungen() {
        return zahlungen;
    }

    public void setZahlungen(List<Zahlung> zahlungen) {
        this.zahlungen = zahlungen;
    }

    // --
    
    
    /**
     * Constructor.
     * @param id
     * @param vorname
     * @param nachname
     * @param adresse
     * @param telefon
     * @param email
     */
    public Mieter(long id, String vorname, String nachname, Adresse adresse, String telefon, String email) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.adresse = adresse;
        this.telefon = telefon;
        this.email = email;
    }

    
    public Mieter() {}
    
    // --
    
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }
    
    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
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

    public void addBankdaten(Bankdaten bankdaten) {
        if (!this.bankdaten.contains(bankdaten)) {
            this.bankdaten.add(bankdaten);
        }
    }
    
    public void removeBankdaten(Bankdaten bankdaten) {
        if (this.bankdaten.contains(bankdaten)) {
            this.bankdaten.remove(bankdaten);
        }
    }
    
    // --
    
    @Override
    public String toString() {
        return "Mieter{" + 
                "id=" + id + 
                ", vorname=" + vorname + 
                ", nachname=" + nachname + 
                ", adresse=" + adresse + 
                ", telefon=" + telefon + 
                ", email=" + email + 
                ", bankdaten=" + bankdaten + 
                '}';
    }
}