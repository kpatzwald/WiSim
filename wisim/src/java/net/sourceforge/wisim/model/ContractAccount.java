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
 * ContractAccount.java
 *
 * Created on 21. März 2003, 03:30
 */

package net.sourceforge.wisim.model;

/** ContractAccount
 * @author Denise freitag
 */
public class ContractAccount {
    
    private int nr;
		private double betrag;
		private int auftragNr;
		private float mwSt;
		private boolean zEingang;    
    
    /** ContractAccount wenn die HUBFirma einen neuen Contract mit 
     * 	einem Kunden macht.
     */
    public ContractAccount() {
    }
    
    /** ContractAccount
     * @param Nr Auftrags-Rechnungs-ID
     * @param Betrag Summe der Positionen aus dem Auftrag (Netto/Angebotspreis)
     * @param AuftragNr Referenz auf den Auftrag
     * @param MwSt Mehrwertsteuer
     */
    public ContractAccount(int nr, double betrag, int auftragNr, float mwSt, boolean zEingang) {
        this.nr = nr;
        this.betrag = betrag;
        this.auftragNr = auftragNr;
        this.mwSt = mwSt;
        this.zEingang = zEingang;
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
    public int getAuftragNr() {
        return auftragNr;
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
     * Returns the zEingang
     * @return boolean
     */
    public boolean getzEingang() {
        return zEingang;
    }
    
    /**
     * Sets the betrag.
     * @param betrag The betrag to set
     */
    public void setBetrag(double betrag) {
        this.betrag = betrag;
    }
    
    /**
     * Sets the AuftragNr.
     * @param AuftragNr The AuftragNr to set
     */
    public void setAuftragNr(int auftragNr) {
        this.auftragNr = auftragNr;
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
    
    /**
     * Sets the zEingang
     * @param zEingang The zEingang to set
     */
    public void setzEingang(boolean zEingang) {
        this.zEingang = zEingang;
    }
}

