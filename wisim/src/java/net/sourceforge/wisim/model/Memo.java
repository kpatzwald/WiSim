/*   ********************************************************************   **
**   Copyright notice                                                       **
**                                                                          **
**   (c) 2003-2021 WiSim Development Team                                   **
**   https://github.com/kpatzwald/WiSim                                     **
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
 * Notiz.java
 *
 * Created on 26. Februar 2003, 22:22
 */

package net.sourceforge.wisim.model;

import java.sql.Date;

/** Klasse Memo
 * @author Benjamin Pasero
 */
public class Memo {
    
    private int id;
		private int kundenNr;
		private String text;
		private Date date;
    
    /** Creates a new instance of Memo
     * @param id ID
     * @param KundenNr Kundennummer
     * @param text Text
     * @param date Datum
     */
    public Memo(int id, int kundenNr, String text, Date date) {
        this.id = id;
        this.kundenNr = kundenNr;
        this.text = text;
        this.date = date;
    }
    
    /** Erstellt eine neue Memo */    
    public Memo () {
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
    public void setKundenNr(int kundenNr) {
        this.kundenNr = kundenNr;
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
    public void setDate(Date date) {
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
        return kundenNr;
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
    public Date getDate() {
        return date;
    }  
}
