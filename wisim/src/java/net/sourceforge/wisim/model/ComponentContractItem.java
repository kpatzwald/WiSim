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
 * EinzelteilauftragPositionen.java
 *
 * Created on 8. März 2003, 15:46
 */

package net.sourceforge.wisim.model;

/**
 *
 * @author  benjamin.pasero
 */
public class ComponentContractItem {
    
    int etNr;
    int etatNr;
    int Bestellmenge;
    double preis;
    
    /** Creates a new instance of EinzelteilauftragPositionen */
    public ComponentContractItem() {
    }
    
    /**
     * @param Preis
     * @param etNr
     * @param etatNr
     * @param Bestellmenge
     */    
    public ComponentContractItem(int etNr, int etatNr, int Bestellmenge, double Preis) {
        this.etNr = etNr;
        this.etatNr = etatNr;
        this.Bestellmenge = Bestellmenge;
        this.preis = Preis;
    }
    
    /**
     * @return Einzelteilenummer
     */    
    public int getEtNr(){
        return etNr;
    }
    
    /**
     * @return Einzelteilauftragsnummer
     */    
    public int getEtatNr() {
        return etatNr;
    }
    
    /**
     * @return Bestellmenge
     */    
    public int getBestellmenge() {
        return Bestellmenge;
    }
    
    /**
     * @return Preis
     */    
    public double getPreis() {
        return preis;
    }
    
    /**
     * @param etNr
     */    
    public void setEtNr(int etNr){
        this.etNr = etNr;
    }
    
    /**
     * @param etatNr
     */    
    public void setEtatNr(int etatNr) {
        this.etatNr = etatNr;
    }
    
    /**
     * @param Bestellmenge
     */    
    public void setBestellmenge(int Bestellmenge) {
        this.Bestellmenge = Bestellmenge;
    }   
    
    /**
     * @param Preis
     */    
    public void setPreis(double Preis) {
        this.preis = Preis;
    }
}
