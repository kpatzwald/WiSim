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
