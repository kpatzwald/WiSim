/**
 * @author benjamin.pasero
 *
 * 
 */
package net.sourceforge.wisim.model;
import java.util.*;
/** Die Summe der Einzelteile eines bestimmten Typs (Name, Einzelteile Nr, Bestand,
 * Mindestbestand, Maximalbestand und eine Liste mit Lagerplätzen wo sich dieses
 * Einzelteil befindet.
 * @author benjamin.pasero
 */
public class EinzelteilLagerElement {
    
    private String einzelteilName;
    private int id;
    private int minBestand;
    private int maxBestand;
    private int bestand;
    private Collection lagerplaetze;
    
    /** Erstellt eine neue Instanz EinzelteilLagerElement.
     * Es kann sich um einen Artikel oder Einzelteil im Lager handeln.
     * @param einzelteilName Der Name des Einzelteils oder Artikel
     * @param id Nummer des Artikels oder Einzelteiles
     * @param minBestand Mindestbestand
     * @param maxBestand Maximalbestand
     * @param bestand aktueller Bestand
     * @param lagerplaetze Lagerplätze an denen der Artikel oder das Einzelteil lagert
     */    
    public EinzelteilLagerElement (String einzelteilName, int id, int minBestand, int maxBestand, int bestand, Collection lagerplaetze) {
        this.einzelteilName = einzelteilName;
        this.id = id;
        this.minBestand = minBestand;
        this.maxBestand = maxBestand;
        this.bestand = bestand;
        this.lagerplaetze = lagerplaetze;
    }
    
    /** Erzeugt ein neues EinzelteilLagerElement */    
    public EinzelteilLagerElement  () {
    }
    
    /**
     * Returns the bestand.
     * @return int
     */
    public int getBestand() {
        return bestand;
    }
    
    /** Gibt die Id zurück
     * @return id
     */    
    public int getId() {
        return id;
    }
    
    /**
     * Returns the einzelteilName.
     * @return String
     */
    public String getEinzelteilName() {
        return einzelteilName;
    }
    
    /**
     * Returns the maxBestand.
     * @return int
     */
    public int getMaxBestand() {
        return maxBestand;
    }
    
    /**
     * Returns the minBestand.
     * @return int
     */
    public int getMinBestand() {
        return minBestand;
    }
    
    /**
     * @return Anzahl der Lagerplätze
     */    
    public Collection getLagerplaetze() {
        return lagerplaetze;
    }
    
    /**
     * Sets the bestand.
     * @param bestand The bestand to set
     */
    public void setBestand(int bestand) {
        this.bestand = bestand;
    }
    
    /**
     * Sets the einzelteilName.
     * @param einzelteilName The einzelteilName to set
     */
    public void setEinzelteilName(String einzelteilName) {
        this.einzelteilName = einzelteilName;
    }
    
    /**
     * Sets the maxBestand.
     * @param maxBestand The maxBestand to set
     */
    public void setMaxBestand(int maxBestand) {
        this.maxBestand = maxBestand;
    }
    
    /**
     * Sets the minBestand.
     * @param minBestand The minBestand to set
     */
    public void setMinBestand(int minBestand) {
        this.minBestand = minBestand;
    }  
    
    /**
     * @param lagerplaetze
     */    
    public void setLagerplaetze(Collection lagerplaetze) {
        this.lagerplaetze = lagerplaetze;
    }
    
    /**
     * @param id
     */    
    public void setId(int id) {
        this.id = id;
    }
}

