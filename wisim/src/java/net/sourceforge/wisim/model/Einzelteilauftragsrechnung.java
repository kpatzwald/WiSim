/*
 * Einzelteilauftragsrechnung.java
 *
 * Created on 6. März 2003, 19:57
 */

package net.sourceforge.wisim.model;

/** Einzelteilauftragsrechnung
 * @author Ben
 */
public class Einzelteilauftragsrechnung {
    
    int Nr;
    double Betrag;
    int EinzelteilauftragNr;
    float MwSt;
    
    /** Einzelteileauftragsrechnung wenn die HUBFirma Einzelteile bei einem
     * Lieferanten bestellt.
     */
    public Einzelteilauftragsrechnung() {
    }
    
    /** Einzelteilauftragsrechnung
     * @param Nr ID
     * @param Betrag Summe der Positionen aus dem Einzelteilauftrag (Netto)
     * @param EinzelteilauftragNr Referenz auf den Einzelteilauftrag
     * @param MwSt Mehrwertsteuer
     */
    public Einzelteilauftragsrechnung(int Nr, double Betrag, int EinzelteilauftragNr, float MwSt) {
        this.Nr = Nr;
        this.Betrag = Betrag;
        this.EinzelteilauftragNr = EinzelteilauftragNr;
        this.MwSt = MwSt;
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
    public int getEinzelteilauftragNr() {
        return EinzelteilauftragNr;
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
     * Sets the betrag.
     * @param betrag The betrag to set
     */
    public void setBetrag(double betrag) {
        Betrag = betrag;
    }
    
    /**
     * Sets the einzelteilauftragNr.
     * @param einzelteilauftragNr The einzelteilauftragNr to set
     */
    public void setEinzelteilauftragNr(int einzelteilauftragNr) {
        EinzelteilauftragNr = einzelteilauftragNr;
    }
    
    /**
     * Sets the mwSt.
     * @param mwSt The mwSt to set
     */
    public void setMwSt(float mwSt) {
        MwSt = mwSt;
    }
    
    /**
     * Sets the nr.
     * @param nr The nr to set
     */
    public void setNr(int nr) {
        Nr = nr;
    }
}
