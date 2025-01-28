package de.hhbk.immoweg24.dao;

import de.hhbk.immoweg24.model.Benutzer;
public class BenutzerDao extends GenericDao<Benutzer> {

    //-------------------------------------------------------------------------
    //  Konstruktor
    //-------------------------------------------------------------------------
    public BenutzerDao() {
        super(Benutzer.class);
    }

    //-------------------------------------------------------------------------
    //  Login-Überprüfung
    //-------------------------------------------------------------------------
    public boolean checkLogin(String email, String passwort) throws Exception {
        return (boolean) executeQuery(session -> {
            return session.createNamedQuery("findByEmailAndPassword", Benutzer.class)
                          .setParameter("email", email)
                          .setParameter("passwort", passwort)
                          .uniqueResultOptional()
                          .isPresent();
        });
    }

    //-------------------------------------------------------------------------
    //  Benutzer löschen
    //-------------------------------------------------------------------------
    public void deleteItemOnly(Benutzer b) throws Exception {
        executeTransaction(session -> {
            Benutzer benutzer = session.get(Benutzer.class, b.getId());
            if (benutzer != null) {
                session.remove(benutzer);  // Direktes Entfernen des Benutzers
            }
            return null;
        });
    }
}
