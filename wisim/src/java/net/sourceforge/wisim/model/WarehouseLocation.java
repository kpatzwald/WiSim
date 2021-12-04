/*   ********************************************************************   **
**   Copyright notice                                                       **
**                                                                          **
**   (c) 2003 WiSim Development Team					                              **
**   https://github.com/kpatzwald/WiSim   			                            **
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
 * Lagerplatz.java
 *
 * Created on 15. März 2003, 18:45
 */

package net.sourceforge.wisim.model;

/**
 *
 * @author  Ben
 */
public class WarehouseLocation {
    
    private String stellplatzNr;
    private int einzelteilNr;
    private int bestand;
    private int maxBestand;
    
    /** Creates a new instance of WarehouseLocation
     * @param stellplatzNr
     */
    public WarehouseLocation(String stellplatzNr) {
        this.stellplatzNr = stellplatzNr;
    }
		
		/** Creates a new instance of WarehouseLocation*/
    public WarehouseLocation()
    {
    }
    
		/** Creates a new instance of WarehouseLocation*/
		public WarehouseLocation(String stellplatzNr, int einzelteilNr, int bestand, int maxBestand)
		{
			this.stellplatzNr = stellplatzNr;
			this.einzelteilNr = einzelteilNr;
			this.bestand = bestand;
			this.maxBestand = maxBestand;
		}
  
    /**
     * @param stellplatzNr
     */    
    public void setStellplatzNr(String stellplatzNr) {
        this.stellplatzNr = stellplatzNr;
    }
    
    /**
     * @return Stellplatz
     */    
    public String getStellplatzNr() {
        return stellplatzNr;
    } 
	/**
	 * @return int
	 */
	public int getBestand()
	{
		return bestand;
	}

	/**
	 * @return int
	 */
	public int getEinzelteilNr()
	{
		return einzelteilNr;
	}

	/**
	 * @return int
	 */
	public int getMaxBestand()
	{
		return maxBestand;
	}

	/**
	 * Sets the bestand.
	 * @param bestand The bestand to set
	 */
	public void setBestand(int bestand)
	{
		this.bestand = bestand;
	}

	/**
	 * Sets the einzelteilNr.
	 * @param einzelteilNr The einzelteilNr to set
	 */
	public void setEinzelteilNr(int einzelteilNr)
	{
		this.einzelteilNr = einzelteilNr;
	}

	/**
	 * Sets the maxBestand.
	 * @param maxBestand The maxBestand to set
	 */
	public void setMaxBestand(int maxBestand)
	{
		this.maxBestand = maxBestand;
	}

}
