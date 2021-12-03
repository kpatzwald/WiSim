/*   ********************************************************************   **
**   Copyright notice                                                       **
**                                                                          **
**   (c) 2003 WiSim Development Team					                              **
**   https://github.com/kpatzwald/WiSim   			                                  **
**                                                                          **
**   All rights reserved                                                    **
**                                                                          **
**   This script is part of the WiSim Business Game project. The WiSim      **
**   project is free software; you can redistribute it and/or modify        **
**   it under the terms of the GNU General Public License as published by   **
**   the Free Software Foundation; either version 2 of the License, or      **
**   (at your option) any later version.                                    **
**                                                                          **
**   The GNU General Public License can be found at                         **
**   http://www.gnu.org/copyleft/gpl.html.                                  **
**   A copy is found in the textfile GPL.txt and important notices to the   **
**   license from the team is found in the textfile LICENSE.txt distributed **
**   in these package.                                                      **
**                                                                          **
**   This copyright notice MUST APPEAR in all copies of the file!           **
**   ********************************************************************   */

/*
 * Person.java
 *
 * Created on 26. Februar 2003, 20:07
 */

package net.sourceforge.wisim.model;

/** Person: Erben sind Customer und Supplier
 * @author Benjamin Pasero
 */
public class Person {
    
    private int id;
    private String vorname;
    private String nachname;
    private String firma;
    private String strasse;
    private String telefon;
    private String fax;
    private String email;
    private String plz;
    private int plzId;
    private String ort;
    
    /** Creates a new instance of Person
     * @param plzId
     * @param id
     * @param vorname
     * @param nachname
     * @param firma
     * @param strasse
     * @param telefon
     * @param fax
     * @param email
     * @param plz
     * @param ort
     */
    public Person(int id, String vorname, String nachname, String firma, String strasse, String telefon,
    String fax, String email, String plz, int plzId, String ort) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.firma = firma;
        this.strasse = strasse;
        this.telefon = telefon;
        this.fax = fax;
        this.email = email;
        this.plz = plz;
        this.plzId = plzId;
        this.ort = ort;
    }
    
    /** Creates a new instance of Customer */
    public Person(){
    }
    
    /**
     * @param id
     */    
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * @param vorname
     */    
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }
    
    /**
     * @param nachname
     */    
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }
    
    /**
     * @param firma
     */    
    public void setFirma(String firma) {
        this.firma = firma;
    }
    
    /**
     * @param strasse
     */    
    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }
    
    /**
     * @param telefon
     */    
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
    
    /**
     * @param fax
     */    
    public void setFax(String fax) {
        this.fax = fax;
    }
    
    /**
     * @param email
     */    
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param ort
     */    
    public void setOrt(String ort) {
        this.ort = ort;
    }
    
    /**
     * @param plz
     */    
    public void setPlz(String plz) {
        this.plz = plz;
    }
    
    /**
     * @param plzId
     */    
    public void setPlzId(int plzId) {
        this.plzId = plzId;
    }
    
    /**
     * @return ID
     */    
    public int getId() {
        return id;
    }
    
    /**
     * @return Vorname
     */    
    public String getVorname() {
        return vorname;
    }
    
    /**
     * @return Nachname
     */    
    public String getNachname() {
        return nachname;
    }
    
    /**
     * @return Firma
     */    
    public String getFirma() {
        return firma;
    }
    
    /**
     * @return Strasse
     */    
    public String getStrasse() {
        return strasse;
    }
    
    /**
     * @return Telefon
     */    
    public String getTelefon() {
        return telefon;
    }
    
    /**
     * @return Fax
     */    
    public String getFax() {
        return fax;
    }
    
    /**
     * @return eMail
     */    
    public String getEmail() {
        return email;
    }
    
    /**
     * @return City
     */    
    public String getOrt() {
        return ort;
    }
    
    /**
     * @return PLZ
     */    
    public String getPlz() {
        return plz;
    }
    
    /**
     * @return PLZ-ID
     */    
    public int getPlzId() {
        return plzId;
    }
}
