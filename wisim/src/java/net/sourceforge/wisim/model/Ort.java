/*
 * Ort.java
 *
 * Created on 27. Februar 2003, 12:15
 */

package net.sourceforge.wisim.model;

/** Klasse für einen Ort mit PLZ und Ortsname
 * @author benjamin.pasero
 */
public class Ort {
    
    int Nr;
    String plz;
    String name;
    
    /** Creates a new instance of Ort
     * @param Nr Nr
     * @param plz Postleitzahl des Ortes
     * @param name Ortsname
     */
    public Ort(int Nr, String plz, String name) {
        this.Nr = Nr;
        this.plz = plz;
        this.name = name;
    }
    
    /** Erstellt einen Ort */    
    public Ort() {
    }
    
    /** Setzt die PLZ
     * @param plz PLZ
     */    
    public void setPlz(String plz) {
        this.plz = plz;
    }
    
    /** Setzt den Namen
     * @param name Name
     */    
    public void setName(String name) {
        this.name = name;
    }
    
    /** Setzt die ID
     * @param nr NR
     */    
    public void setNr(int nr) {
        this.Nr = nr;
    }
    
    /** Liefert die PLZ
     * @return String
     */    
    public String getPlz() {
        return plz;
    }
    
    /** Liefert den Namen
     * @return String
     */    
    public String getName() {
        return name;
    }   
    
    /** Liefert die Nr
     * @return int
     */    
    public int getNr() {
        return Nr;
    }
}
