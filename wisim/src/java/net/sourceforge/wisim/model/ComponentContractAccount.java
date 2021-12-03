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
 * Einzelteilauftragsrechnung.java
 *
 * Created on 6. März 2003, 19:57
 */

package net.sourceforge.wisim.model;

/** ComponentContractAccount
 * @author Ben
 */
public class ComponentContractAccount {
    
		private int nr;
		private double betrag;
		private int einzelteilauftragNr;
		private float mwSt;
    
    /** Einzelteileauftragsrechnung wenn die HUBFirma Einzelteile bei einem
     * Lieferanten bestellt.
     */
    public ComponentContractAccount() {
    }
    
    /** ComponentContractAccount
     * @param Nr ID
     * @param Betrag Summe der Positionen aus dem ComponentContract (Netto)
     * @param EinzelteilauftragNr Referenz auf den ComponentContract
     * @param MwSt Mehrwertsteuer
     */
    public ComponentContractAccount(int nr, double betrag, int einzelteilauftragNr, float mwSt) {
        this.nr = nr;
        this.betrag = betrag;
        this.einzelteilauftragNr = einzelteilauftragNr;
        this.mwSt = mwSt;
    }
    
    /**
     * Returns the betrag.
     * @return float
     */
    public double getBetrag() {
        return betrag;
    }
    
    /**
     * Returns the einzelteilauftragNr.
     * @return int
     */
    public int getEinzelteilauftragNr() {
        return einzelteilauftragNr;
    }
    
    /**
     * Returns the mwSt.
     * @return float
     */
    public float getMwSt() {
        return mwSt;
    }
    
    /**
     * Returns the nr.
     * @return int
     */
    public int getNr() {
        return nr;
    }
    
    /**
     * Sets the betrag.
     * @param betrag The betrag to set
     */
    public void setBetrag(double betrag) {
        this.betrag = betrag;
    }
    
    /**
     * Sets the einzelteilauftragNr.
     * @param einzelteilauftragNr The einzelteilauftragNr to set
     */
    public void setEinzelteilauftragNr(int einzelteilauftragNr) {
        this.einzelteilauftragNr = einzelteilauftragNr;
    }
    
    /**
     * Sets the mwSt.
     * @param mwSt The mwSt to set
     */
    public void setMwSt(float mwSt) {
        this.mwSt = mwSt;
    }
    
    /**
     * Sets the nr.
     * @param nr The nr to set
     */
    public void setNr(int nr) {
        this.nr = nr;
    }
}