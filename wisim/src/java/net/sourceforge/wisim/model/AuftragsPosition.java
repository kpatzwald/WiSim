/*
 * EinzelteilauftragPositionen.java
 *
 * Created on 21. März 2003, 04:14
 */

package net.sourceforge.wisim.model;

/**
 *
 * @author  Denise freitag
 */
public class AuftragsPosition {
    
    int atNr;
    int artNr;
    long Bestellmenge;
    
    
    /** Creates a new instance of AuftragPositionen */
    public AuftragsPosition() {
    }
    
    /**
     * @param atNr Referenz auf den Auftrag
     * @param artNr Referenz auf Artikel
     * @param Bestellmenge Menge eines Artikels
     */    
    public AuftragsPosition(int atNr, int artNr, long Bestellmenge) {
        this.atNr = atNr;
        this.artNr = artNr;
        this.Bestellmenge = Bestellmenge;
        
    }
    
    /**
     * @return Auftragsnummer
     */    
    public int getAtNr(){
        return atNr;
    }
    
    /**
     * @return Artikelnummer
     */    
    public int getArtNr() {
        return artNr;
    }
    
    /**
     * @return Bestellmenge
     */    
    public long getBestellmenge() {
        return Bestellmenge;
    }
    
    
    
    /**
     * @param atNr
     */    
    public void setAtNr(int atNr){
        this.atNr = atNr;
    }
    
    /**
     * @param artNr
     */    
    public void setArtNr(int artNr) {
        this.artNr = artNr;
    }
    
    /**
     * @param Bestellmenge
     */    
    public void setBestellmenge(long Bestellmenge) {
        this.Bestellmenge = Bestellmenge;
    }   
    
    
}
