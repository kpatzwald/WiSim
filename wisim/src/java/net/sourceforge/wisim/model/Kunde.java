package net.sourceforge.wisim.model;

/*
 * Kunde.java
 *
 * Created on 19. Januar 2003, 11:56
 */

/**
 *
 * @author  Kay Patzwald
 */
public class Kunde extends Person{

    private String kundentyp;
    private String zahlungsmoral;
    private String anspruch;
    //private String notiz;
    
    /** Creates a new instance of Kunde
     * @param id
     * @param vorname
     * @param nachname
     * @param firma
     * @param strasse
     * @param telefon
     * @param fax
     * @param email
     * @param anspruch
     * @param zahlungsmoral
     * @param plz
     * @param plzId
     * @param ort
     * @param kundentyp
     */
    public Kunde(int id, String vorname, String nachname, String firma, String strasse, String telefon,
    String fax, String email, String anspruch, String zahlungsmoral,
    String plz, int plzId, String ort, String kundentyp) {

        super(id, vorname, nachname, firma, strasse, telefon, fax, email, plz, plzId, ort);
        this.zahlungsmoral = zahlungsmoral;
        this.anspruch = anspruch;
        this.kundentyp = kundentyp;
        
    }
    
    /** Creates a new instance of Kunde */
    public Kunde(){
    }
    
    /**
     * @param zahlungsmoral
     */    
    public void setZahlungsmoral(String zahlungsmoral) {
        this.zahlungsmoral = zahlungsmoral;
    }
    
    /**
     * @param anspruch
     */     
    public void setAnspruch(String anspruch) {
        this.anspruch = anspruch;
    }
    
    /**
     * @param kundentyp
     */    
    public void setKundentyp(String kundentyp) {
        this.kundentyp = kundentyp;
    }
    
    /**
     * @return Zahlungsmoral
     */    
    public String getZahlungsmoral() {
        return zahlungsmoral;
    }

    /** Anspruch
     * @return Anspruch
     */     
    public String getAnspruch() {
        return anspruch;
    }
    
    /** Kundentyp
     * @return Kundentyp
     */    
    public String getKundentyp() {
        return kundentyp;
    }
}
