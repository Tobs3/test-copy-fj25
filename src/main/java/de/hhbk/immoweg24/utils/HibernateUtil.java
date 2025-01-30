package de.hhbk.immoweg24.utils;
 
import de.hhbk.immoweg24.model.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 * Die Klasse HibernateUtils verwaltet die Hibernate-Sitzung (SessionFactory).
 * Sie stellt Methoden bereit, um die Verbindung zur Datenbank aufzubauen und zu verwalten.
 *
 * Hauptfunktionen:
 * - Initialisiert die Hibernate SessionFactory
 * - Bietet eine Methode zum Abrufen der SessionFactory für DAO-Klassen
 * - Behandelt das ordnungsgemäße Schließen der SessionFactory beim Beenden der Anwendung
 */

public class HibernateUtil 
{
  //-------------------------------------------------------------------------
  //  Hibernate-SessionFactory
  //-------------------------------------------------------------------------     
    private static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactory()
    {
        if (sessionFactory == null) 
        {
            sessionFactory = new Configuration() 
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect") 
                    .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                    .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/hhbk?zeroDateTimeBehavior=convertToNull&createDatabaseIfNotExist=true")
                    .setProperty("hibernate.connection.username", "root")
                    .setProperty("hibernate.hbm2ddl.auto", "update")
                    .setProperty("hibernate.enable_lazy_load_no_trans", "true")
                    .setProperty("show_sql", "true")
                    .setProperty("format_sql", "true")
                    .setProperty("use_sql_comments", "true")
                    .addAnnotatedClass(Adresse.class) 
                    .addAnnotatedClass(Mieter.class)
                    .addAnnotatedClass(Mietobjekt.class)
                    .addAnnotatedClass(Nebenkosten.class)
                    .addAnnotatedClass(Bankdaten.class)
                    .addAnnotatedClass(Zahlung.class)
                    .addAnnotatedClass(Benutzer.class)
                    .buildSessionFactory();
        }
        return sessionFactory; 
    }
    
    
    public static Session getCurrentSession() { return getSessionFactory().getCurrentSession(); } 
     
    
    public static Session openSession() { return getSessionFactory().openSession(); }

    
    public static void closeSession(Session session) { try { session.flush(); } catch(Exception e) { } try { session.close(); } catch(Exception e) { } }
 
    
  //-------------------------------------------------------------------------
  //  DB-Version
  //-------------------------------------------------------------------------  
    public static String getDbVersion()
    { 
        String r = "";
        Session session = null;
        try
        {
            session = getSessionFactory().openSession();
            Query query = session.createNativeQuery("SELECT Version()");
            r = (String) query.getSingleResult(); 
        }
        catch (Exception e) { r = null; }
        finally  
        { 
            try { session.flush(); } catch(Exception e) { }
            try { session.close(); } catch(Exception e) { }
        }
        return r;
    } 
    
    
    
}
