package de.hhbk.immoweg24.model.enums;

public enum StatusDokument {
    
     AKTIV("Aktiv"),
    ARCHIVIERT("Archiviert"),
    GELOESCHT("Gelöscht");

    private final String beschreibung;

    StatusDokument(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getBeschreibung() {
        return beschreibung;
    }
}
