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
 * ContractAccount.java
 *
 * Created on 21. März 2003, 03:30
 */

package net.sourceforge.wisim.model;

/** ContractAccount
 * @author Denise freitag
 */
public class ContractAccount {
    
    int Nr;
    double Betrag;
    int AuftragNr;
    float MwSt;
    boolean zEingang;    
    
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
    public ContractAccount(int Nr, double Betrag, int AuftragNr, float MwSt, boolean zEingang) {
        this.Nr = Nr;
        this.Betrag = Betrag;
        this.AuftragNr = AuftragNr;
        this.MwSt = MwSt;
        this.zEingang = zEingang;
    }
       
    /**
     * Returns the betrag.
     * @return float
     */
    public double getBetrag() {
        return Betrag;
    }
    
    /**
     * Returns the einzelteilauftragNr.
     * @return int
     */
    public int getAuftragNr() {
        return AuftragNr;
    }
    
    /**
     * Returns the mwSt.
     * @return float
     */
    public float getMwSt() {
        return MwSt;
    }
    
    /**
     * Returns the nr.
     * @return int
     */
    public int getNr() {
        return Nr;
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
        this.Betrag = betrag;
    }
    
    /**
     * Sets the AuftragNr.
     * @param AuftragNr The AuftragNr to set
     */
    public void setAuftragNr(int AuftragNr) {
        this.AuftragNr = AuftragNr;
    }
    
    /**
     * Sets the mwSt.
     * @param mwSt The mwSt to set
     */
    public void setMwSt(float mwSt) {
        this.MwSt = mwSt;
    }
    
    /**
     * Sets the nr.
     * @param nr The nr to set
     */
    public void setNr(int nr) {
        this.Nr = nr;
    }
    
    /**
     * Sets the zEingang
     * @param zEingang The zEingang to set
     */
    public void setzEingang(boolean zEingang) {
        this.zEingang = zEingang;
    }
}

