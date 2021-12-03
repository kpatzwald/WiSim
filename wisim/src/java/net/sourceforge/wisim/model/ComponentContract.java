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
 * Einzelteilauftrag.java
 *
 * Created on 6. März 2003, 19:24
 */

package net.sourceforge.wisim.model;
import java.sql.Date;
import java.util.*;
/** ComponentContract für Einzelteile
 * @author benjamin.pasero
 */
public class ComponentContract {
    
		private int nr;
		private int lieferantNr;
		private int einzelteilAuftragsRechnungNr;
		private float lieferrabatt;
		private int skontofrist;
		private Date lieferdatum;
		private Date auftragsdatum;
		private float skonto;
		private Collection einzelteilauftragPositionen;
    
    
    /** Creates a new instance of ComponentContract */
    public ComponentContract() {
    }
    
    /** Creates a new instance of ComponentContract
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
    public ComponentContract(int nr, int lieferantNr, int einzelteilAuftragsRechnungNr, float lieferrabatt, int skontofrist, Date lieferdatum, Date auftragsdatum, float skonto, Collection einzelteilauftragPositionen) {
        this.nr = nr;
        this.lieferantNr = lieferantNr;
        this.einzelteilAuftragsRechnungNr = einzelteilAuftragsRechnungNr;
        this.lieferrabatt = lieferrabatt;
        this.skontofrist = skontofrist;
        this.lieferdatum = lieferdatum;
        this.auftragsdatum = auftragsdatum;
        this.skonto = skonto;
        this.einzelteilauftragPositionen = einzelteilauftragPositionen;
    }
    
    /**
     * Returns the auftragsdatum.
     * @return Date
     */
    public Date getAuftragsdatum() {
        return auftragsdatum;
    }
    
    /**
     * Returns the lieferdatum.
     * @return Date
     */
    public Date getLieferdatum() {
        return lieferdatum;
    }
    
    /**
     * Returns the lieferrabatt.
     * @return float
     */
    public float getLieferrabatt() {
        return lieferrabatt;
    }
    
    /**
     * Returns the nr.
     * @return int
     */
    public int getNr() {
        return nr;
    }
    
    /**
     * Returns the skonto.
     * @return float
     */
    public float getSkonto() {
        return skonto;
    }
    
    /**
     * Returns the skontofrist.
     * @return int
     */
    public int getSkontofrist() {
        return skontofrist;
    }
    
    /**
     * Returns the einzelteilAuftragsRechnungNr.
     * @return int
     */
    public int getEinzelteilAuftragsRechnungNr() {
        return einzelteilAuftragsRechnungNr;
    }
    
    /**
     * Returns the lieferantNr.
     * @return int
     */
    public int getLieferantNr() {
        return lieferantNr;
    }
    
    /** Liste der einzelnen Positionen aus dem EinzelteilAuftrag
     * @return Collection mit Objekten des Typs EinzelteilAuftragsPosition
     */
    public Collection getEinzelteilauftragPositionen() {
        return einzelteilauftragPositionen;
    }
    
    /**
     * Sets the auftragsdatum.
     * @param auftragsdatum The auftragsdatum to set
     */
    public void setAuftragsdatum(Date auftragsdatum) {
        this.auftragsdatum = auftragsdatum;
    }
    
    /**
     * Sets the lieferdatum.
     * @param lieferdatum The lieferdatum to set
     */
    public void setLieferdatum(Date lieferdatum) {
			this.lieferdatum = lieferdatum;
    }
    
    /**
     * Sets the lieferrabatt.
     * @param lieferrabatt The lieferrabatt to set
     */
    public void setLieferrabatt(float lieferrabatt) {
			this.lieferrabatt = lieferrabatt;
    }
    
    /**
     * Sets the nr.
     * @param nr The nr to set
     */
    public void setNr(int nr) {
			this.nr = nr;
    }
    
    /**
     * Sets the skonto.
     * @param skonto The skonto to set
     */
    public void setSkonto(float skonto) {
			this.skonto = skonto;
    }
    
    /**
     * Sets the skontofrist.
     * @param skontofrist The skontofrist to set
     */
    public void setSkontofrist(int skontofrist) {
			this.skontofrist = skontofrist;
    }
    
    /**
     * Sets the einzelteilAuftragsRechnungNr.
     * @param einzelteilAuftragsRechnungNr The einzelteilAuftragsRechnungNr to set
     */
    public void setEinzelteilAuftragsRechnungNr(int einzelteilAuftragsRechnungNr) {
			this.einzelteilAuftragsRechnungNr = einzelteilAuftragsRechnungNr;
    }
    
    /**
     * Sets the lieferantNr.
     * @param lieferantNr The lieferantNr to set
     */
    public void setLieferantNr(int lieferantNr) {
			this.lieferantNr = lieferantNr;
    }
    
    /**
     * @param col Collection mit Objekten vom Typ EinzelteilAuftragPosition
     */
    public void setEinzelteilauftragPositionen(Collection col) {
        this.einzelteilauftragPositionen = col;
    }  
}