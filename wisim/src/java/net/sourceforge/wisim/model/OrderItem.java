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
 * EinzelteilauftragPositionen.java
 *
 * Created on 21. März 2003, 04:14
 */

package net.sourceforge.wisim.model;

/**
 *
 * @author  Denise freitag
 */
public class OrderItem {
    
		private int atNr;
		private int artNr;
		private long bestellmenge;
    
    
    /** Creates a new instance of AuftragPositionen */
    public OrderItem() {
    }
    
    /**
     * @param atNr Referenz auf den Auftrag
     * @param artNr Referenz auf Article
     * @param Bestellmenge Menge eines Artikels
     */    
    public OrderItem(int atNr, int artNr, long bestellmenge) {
        this.atNr = atNr;
        this.artNr = artNr;
        this.bestellmenge = bestellmenge;
        
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
        return bestellmenge;
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
    public void setBestellmenge(long bestellmenge) {
        this.bestellmenge = bestellmenge;
    }   
    
    
}
