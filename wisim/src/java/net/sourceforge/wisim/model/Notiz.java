/*
 * Notiz.java
 *
 * Created on 26. Februar 2003, 22:22
 */

package net.sourceforge.wisim.model;

/** Klasse Notiz
 * @author Benjamin Pasero
 */
public class Notiz {
    
    int id;
    int KundenNr;
    String text;
    java.sql.Date date;
    
    /** Creates a new instance of Notiz
     * @param id ID
     * @param KundenNr Kundennummer
     * @param text Text
     * @param date Datum
     */
    public Notiz(int id, int KundenNr, String text, java.sql.Date date) {
        this.id = id;
        this.KundenNr = KundenNr;
        this.text = text;
        this.date = date;
    }
    
    /** Erstellt eine neue Notiz */    
    public Notiz () {
    }
    
    /** Setzt die ID
     * @param id ID
     */    
    public void setId(int id) {
        this.id = id;
    }
    
    /** Setzt die Kundennummer
     * @param KundenNr Kundennummer
     */    
    public void setKundenNr(int KundenNr) {
        this.KundenNr = KundenNr;
    }
    
    /** Setzt den Text
     * @param text Text
     */    
    public void setText(String text) {
        this.text = text;
    }
    
    /** Setzt das Datum
     * @param date Datum
     */    
    public void setDate(java.sql.Date date) {
        this.date = date;
    }
    
    /** Liefert die ID
     * @return int
     */    
    public int getId() {
        return id;
    }
    
    /** Liefert die Kundennummer
     * @return int
     */    
    public int getKundenNr() {
        return KundenNr;
    }
    
    /** Liefert den Text
     * @return String
     */    
    public String getText() {
        return text;
    }
    
    /** Liefert das Datum
     * @return java.sql.Date
     */    
    public java.sql.Date getDate() {
        return date;
    }  
}
