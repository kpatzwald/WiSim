/*
 * Auftragsrechnung.java
 *
 * Created on 21. März 2003, 03:30
 */

package net.sourceforge.wisim.model;

/** Auftragsrechnung
 * @author Denise freitag
 */
public class Auftragsrechnung {
    
    int Nr;
    double Betrag;
    int AuftragNr;
    float MwSt;
    boolean zEingang;    
    
    /** Auftragsrechnung wenn die HUBFirma einen neuen Vertrag mit 
     * 	einem Kunden macht.
     */
    public Auftragsrechnung() {
    }
    
    /** Auftragsrechnung
     * @param Nr Auftrags-Rechnungs-ID
     * @param Betrag Summe der Positionen aus dem Auftrag (Netto/Angebotspreis)
     * @param AuftragNr Referenz auf den Auftrag
     * @param MwSt Mehrwertsteuer
     */
    public Auftragsrechnung(int Nr, double Betrag, int AuftragNr, float MwSt, boolean zEingang) {
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

