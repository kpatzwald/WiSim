/*   ********************************************************************   **
**   Copyright notice                                                       **
**                                                                          **
**   (c) 2003 WiSim Development Team					                              **
**   http://wisim.sourceforge.net/   			                                  **
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
public class Customer extends Person{

    private String kundentyp;
    private String zahlungsmoral;
    private String anspruch;
    //private String notiz;
    
    /** Creates a new instance of Customer
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
    public Customer(int id, String vorname, String nachname, String firma, String strasse, String telefon,
    String fax, String email, String anspruch, String zahlungsmoral,
    String plz, int plzId, String ort, String kundentyp) {

        super(id, vorname, nachname, firma, strasse, telefon, fax, email, plz, plzId, ort);
        this.zahlungsmoral = zahlungsmoral;
        this.anspruch = anspruch;
        this.kundentyp = kundentyp;
        
    }
    
    /** Creates a new instance of Customer */
    public Customer(){
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
