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
public class EinzelteilauftragPosition {
    
    int etNr;
    int etatNr;
    int Bestellmenge;
    double preis;
    
    /** Creates a new instance of EinzelteilauftragPositionen */
    public EinzelteilauftragPosition() {
    }
    
    /**
     * @param Preis
     * @param etNr
     * @param etatNr
     * @param Bestellmenge
     */    
    public EinzelteilauftragPosition(int etNr, int etatNr, int Bestellmenge, double Preis) {
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
