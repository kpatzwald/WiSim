/*
 * Einzelteilauftrag.java
 *
 * Created on 6. März 2003, 19:24
 */

package net.sourceforge.wisim.model;
import java.sql.Date;
import java.util.*;
/** Einzelteilauftrag für Einzelteile
 * @author benjamin.pasero
 */
public class Einzelteilauftrag {
    
    int Nr;
    int LieferantNr;
    int EinzelteilAuftragsRechnungNr;
    float Lieferrabatt;
    int Skontofrist;
    Date Lieferdatum;
    Date Auftragsdatum;
    float Skonto;
    Collection EinzelteilauftragPositionen;
    
    
    /** Creates a new instance of Einzelteilauftrag */
    public Einzelteilauftrag() {
    }
    
    /** Creates a new instance of Einzelteilauftrag
     * @param EinzelteilauftragPositionen
     * @param Nr ID
     * @param LieferantNr Referenz auf den Lieferanten
     * @param EinzelteilAuftragsRechnungNr Referenz auf die EinzelteilAuftragsRechnung
     * @param Lieferrabatt
     * @param Skontofrist
     * @param Lieferdatum
     * @param Auftragsdatum
     * @param Skonto
     */
    public Einzelteilauftrag(int Nr, int LieferantNr, int EinzelteilAuftragsRechnungNr, float Lieferrabatt, int Skontofrist, Date Lieferdatum, Date Auftragsdatum, float Skonto, Collection EinzelteilauftragPositionen) {
        this.Nr = Nr;
        this.LieferantNr = LieferantNr;
        this.EinzelteilAuftragsRechnungNr = EinzelteilAuftragsRechnungNr;
        this.Lieferrabatt = Lieferrabatt;
        this.Skontofrist = Skontofrist;
        this.Lieferdatum = Lieferdatum;
        this.Auftragsdatum = Auftragsdatum;
        this.Skonto = Skonto;
        this.EinzelteilauftragPositionen = EinzelteilauftragPositionen;
    }
    
    /**
     * Returns the auftragsdatum.
     * @return Date
     */
    public Date getAuftragsdatum() {
        return Auftragsdatum;
    }
    
    /**
     * Returns the lieferdatum.
     * @return Date
     */
    public Date getLieferdatum() {
        return Lieferdatum;
    }
    
    /**
     * Returns the lieferrabatt.
     * @return float
     */
    public float getLieferrabatt() {
        return Lieferrabatt;
    }
    
    /**
     * Returns the nr.
     * @return int
     */
    public int getNr() {
        return Nr;
    }
    
    /**
     * Returns the skonto.
     * @return float
     */
    public float getSkonto() {
        return Skonto;
    }
    
    /**
     * Returns the skontofrist.
     * @return int
     */
    public int getSkontofrist() {
        return Skontofrist;
    }
    
    /**
     * Returns the einzelteilAuftragsRechnungNr.
     * @return int
     */
    public int getEinzelteilAuftragsRechnungNr() {
        return EinzelteilAuftragsRechnungNr;
    }
    
    /**
     * Returns the lieferantNr.
     * @return int
     */
    public int getLieferantNr() {
        return LieferantNr;
    }
    
    /** Liste der einzelnen Positionen aus dem EinzelteilAuftrag
     * @return Collection mit Objekten des Typs EinzelteilAuftragsPosition
     */
    public Collection getEinzelteilauftragPositionen() {
        return EinzelteilauftragPositionen;
    }
    
    /**
     * Sets the auftragsdatum.
     * @param auftragsdatum The auftragsdatum to set
     */
    public void setAuftragsdatum(Date auftragsdatum) {
        Auftragsdatum = auftragsdatum;
    }
    
    /**
     * Sets the lieferdatum.
     * @param lieferdatum The lieferdatum to set
     */
    public void setLieferdatum(Date lieferdatum) {
        Lieferdatum = lieferdatum;
    }
    
    /**
     * Sets the lieferrabatt.
     * @param lieferrabatt The lieferrabatt to set
     */
    public void setLieferrabatt(float lieferrabatt) {
        Lieferrabatt = lieferrabatt;
    }
    
    /**
     * Sets the nr.
     * @param nr The nr to set
     */
    public void setNr(int nr) {
        Nr = nr;
    }
    
    /**
     * Sets the skonto.
     * @param skonto The skonto to set
     */
    public void setSkonto(float skonto) {
        Skonto = skonto;
    }
    
    /**
     * Sets the skontofrist.
     * @param skontofrist The skontofrist to set
     */
    public void setSkontofrist(int skontofrist) {
        Skontofrist = skontofrist;
    }
    
    /**
     * Sets the einzelteilAuftragsRechnungNr.
     * @param einzelteilAuftragsRechnungNr The einzelteilAuftragsRechnungNr to set
     */
    public void setEinzelteilAuftragsRechnungNr(int einzelteilAuftragsRechnungNr) {
        EinzelteilAuftragsRechnungNr = einzelteilAuftragsRechnungNr;
    }
    
    /**
     * Sets the lieferantNr.
     * @param lieferantNr The lieferantNr to set
     */
    public void setLieferantNr(int lieferantNr) {
        LieferantNr = lieferantNr;
    }
    
    /**
     * @param col Collection mit Objekten vom Typ EinzelteilAuftragPosition
     */
    public void setEinzelteilauftragPositionen(Collection col) {
        this.EinzelteilauftragPositionen = col;
    }
    
    
}
