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
 * Vertrag.java
 *
 * Created on 4. März 2003, 23:46
 */

package net.sourceforge.wisim.model;

import java.util.Collection;

/**
 *
 * @author  Denise Freitag
 */
public class Contract {
    
    private int nr; //Vertrags ID
    private java.sql.Date lieferdatum;
    private double skonto;
    private long skontofrist;
    private double rabatt;
    private java.sql.Date vertragsdatum;
    private int kd_nr;
    private int atr_nr;//Auftrags-Rechnungs-ID
    private Collection AuftragPositionen;
    
    /** Creates a new instance of Contract */
    public Contract() {
    }
    
    /** Creates a new instance of Contract 
     * @param AuftragPositionen
     * @param Nr ID
     * @param KundenNr Referenz auf den Kunden
     * @param AuftragsRechnungNr Referenz auf die AuftragsRechnung
     * @param Rabatt
     * @param Skontofrist
     * @param Lieferdatum
     * @param Auftragsdatum
     * @param Skonto 
     */
    public Contract(int nr, java.sql.Date lieferdatum, double skonto, long skontofrist, double rabatt, java.sql.Date vertragsdatum, int kd_nr, int atr_nr, Collection AuftragPositionen){
        this.nr = nr;
        this.lieferdatum = lieferdatum;
        this.skonto = skonto;
        this.skontofrist = skontofrist;
        this.rabatt = rabatt;
        this.vertragsdatum = vertragsdatum;
        this.kd_nr = kd_nr;
        this.atr_nr = atr_nr;
        this.AuftragPositionen = AuftragPositionen;    
    }
    
    /** Gibt die Vertrags/AuftragsID zurück
     * @return Integer
     */
    public int getVertragsId(){
        return nr;
    }
    
    /** Gibt das Lieferdatum zurück
     * @return Date
     */
    public java.sql.Date getLieferdatum(){
        return lieferdatum;
    }
    
    /** Gibt den Skontowert zurück
     * @return Float
     */
    public double getSkonto(){
        return skonto;
    }
    
    /** Gibt die Skontofrist zurück
     * @return Integer
     */
    public long getSkontofrist(){
        return skontofrist;
    }
    
    /** Gibt den Rabattwert zurück
     * @return Float
     */
    public double getRabatt(){
        return rabatt;
    }
    
    /** Gibt das Vertrags-/Auftragsdatum zurück
     * @return Date
     */
    public java.sql.Date getVertragsdatum(){
        return vertragsdatum;
    }
    
    /** Gibt die KundenId zurück
     * @return Integer
     */
    public int getKundenId(){
        return kd_nr;
    }
    
    /** Gibt die AuftragsrechnungsId zurück
     * @return Integer
     */
    public int getAuftragsrechnungsId(){
        return atr_nr;
    }
    
    /** Liste der einzelnen Positionen aus dem Auftrag
     * @return Collection mit Objekten des Typs OrderItem
     */
    public Collection getAuftragPositionen() {
        return AuftragPositionen;
    }
    
    /**
     * Sets the VertragsId.
     * @param nr The nr to set
     */
    public void setVertragsId(int nr){
        this.nr = nr;
    }
    
    /**
     * Sets the lieferdatum.
     * @param lieferdatum The lieferdatum to set
     */
    public void setLieferdatum(java.sql.Date lieferdatum){
        this.lieferdatum = lieferdatum;
    }
    
    /**
     * Sets the skonto.
     * @param skonto The skonto to set
     */
    public void setSkonto(double skonto){
        this.skonto = skonto;
    }
    
    /**
     * Sets the skontofrist.
     * @param skontofrist The skontofrist to set
     */
    public void setSkontofrist(long skontofrist){
        this.skontofrist = skontofrist;
    }
    
    /**
     * Sets the rabatt.
     * @param rabatt The rabatt to set
     */
    public void setRabatt(double rabatt){
        this.rabatt = rabatt;
    }
    
    /**
     * Sets the vertragsdatum.
     * @param vertragsdatum The vertragsdatum to set
     */
    public void setVertragsdatum(java.sql.Date vertragsdatum){
        this.vertragsdatum = vertragsdatum;
    }
    
    /**
     * Sets the kundenid.
     * @param kd_nr The kd_nr to set
     */
    public void setKundenId(int kd_nr){
        this.kd_nr = kd_nr;
    }
    
    /**
     * Sets the auftragsrechnungsid.
     * @param atr_nr The atr_nr to set
     */
    public void setAuftragsrechnungsId(int atr_nr){
        this.atr_nr = atr_nr;
    }
    
    /**
     * @param col Collection mit Objekten vom Typ AuftragPosition
     */
    public void setAuftragPositionen(Collection col) {
        this.AuftragPositionen = col;
    }
    
}
