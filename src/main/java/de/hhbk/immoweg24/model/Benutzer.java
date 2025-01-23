package de.hhbk.immoweg24.model;

import de.hhbk.immoweg24.model.enums.PersonAnrede;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

@NamedQueries({
    @NamedQuery(name = "findByEmailAndPassword", query = "SELECT u FROM Benutzer u WHERE u.email = :email AND u.passwort = :passwort")
})
@Entity
public class Benutzer extends ModelTemplate {

    //-------------------------------------------------------------------------
    //  Variablen
    //-------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id = -1L;

    protected String vorname = null;
    protected String nachname = null;

    @Enumerated(EnumType.STRING)
    protected PersonAnrede anrede = PersonAnrede.EMPTY;

    protected String telefon = null;
    protected String email = null;
    protected String passwort = null;

    //-------------------------------------------------------------------------
    //  Konstruktoren
    //-------------------------------------------------------------------------
    public Benutzer() {
        super();
    }

    public Benutzer(String vorname, String nachname, String email, String passwort) {
        this(PersonAnrede.EMPTY, vorname, nachname, email, passwort);
    }

    public Benutzer(PersonAnrede anrede, String vorname, String nachname, String email, String passwort) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.anrede = anrede;
        this.passwort = passwort;
    }

    //-------------------------------------------------------------------------
    //  Getter und Setter
    //-------------------------------------------------------------------------
    @Override
    public long getId() {
        return this.id;
    }

    @Override
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

    public PersonAnrede getAnrede() {
        return anrede;
    }

    public void setAnrede(PersonAnrede anrede) {
        this.anrede = anrede;
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

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
}