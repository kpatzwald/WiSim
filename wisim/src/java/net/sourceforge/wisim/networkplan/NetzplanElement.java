/*   ********************************************************************   **
**   Copyright notice                                                       **
**                                                                          **
**   (c) 2003 WiSim Development Team					                    **
**   http://wisim.sourceforge.net/   			                            **
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

package net.sourceforge.wisim.networkplan;

import java.util.Collection;
import java.util.Vector;
import java.util.Iterator;

/**
 * Class for generating one network plan element
 * @author Benjamin Pasero
 * @version 0.5a
 */
public class NetzplanElement {

	private int nummer;
	private double dauer;
	private boolean criticalPath;
	private int[] vorgaenger;
	private int[] nachfolger;

	private double faz;
	private double fez;
	private double saz;
	private double sez;
	private double gp;
	private double fp;

	private String bezeichnung;

	private Collection vorgaengerBasket;

	private double anchorTopXPos = 0;
	private double anchorBottomXPos = 0;
	private double anchorTopYPos = 0;
	private double anchorBottomYPos = 0;

	/**
	 * Class representating one networkplan element
	 * @param nummer
	 * @param dauer
	 * @param nachfolger
	 * @param bezeichnung
	 */
	public NetzplanElement(int nummer, double dauer, int[] nachfolger, String bezeichnung) {
		this.nummer = nummer;
		this.dauer = dauer;
		this.nachfolger = nachfolger;
		vorgaengerBasket = new Vector();
		this.bezeichnung = bezeichnung;
		criticalPath = false;
	}

	/**
	 * Get the duration of the network plan element
	 * @return
	 */
	public double getDauer() {
		return dauer;
	}

	/**
	 * Set the duration
	 * @param dauer
	 */
	public void setDauer(double dauer) {
		this.dauer = dauer;
	}

	/**
	 * Get the earliest timepoint to begin
	 * @return faz
	 */
	public double getFaz() {
		return faz;
	}

	/**
	 * Get the earliest timepoint to end
	 * @return fez
	 */
	public double getFez() {
		return fez;
	}

	/**
	 * Get the free buffer
	 * @return fp
	 */
	public double getFp() {
		return fp;
	}

	/**
	 * Get whole buffer
	 * @return gp
	 */
	public double getGp() {
		return gp;
	}

	/**
	 * Get the latest timepoint to begin
	 * @return saz
	 */
	public double getSaz() {
		return saz;
	}

	/**
	 * Get the latest timepoint to end
	 * @return
	 */
	public double getSez() {
		return sez;
	}

	/**
	 * Set the earliest timepoint to begin
	 * @param faz
	 */
	public void setFaz(double faz) {
		this.faz = faz;
	}

	/**
	 * Set the latest timepoint to begin
	 * @param fez
	 */
	public void setFez(double fez) {
		this.fez = fez;
	}

	/**
	 * Set the free buffer
	 * @param fp
	 */
	public void setFp(double fp) {
		this.fp = fp;
	}

	/**
	 * Set the whole buffer
	 * @param gp
	 */
	public void setGp(double gp) {
		this.gp = gp;
	}

	/**
	 * Set the latest timepoint to begin
	 * @param saz
	 */
	public void setSaz(double saz) {
		this.saz = saz;
	}

	/**
	 * Set the latest timepoint to end
	 * @param sez
	 */
	public void setSez(double sez) {
		this.sez = sez;
	}

	/**
	 * Get number
	 * @return nummer
	 */
	public int getNummer() {
		return nummer;
	}

	/**
	 * Set number
	 * @param nummer
	 */
	public void setNummer(int nummer) {
		this.nummer = nummer;
	}

	/**
	 * Get array with number of parent elements
	 * @return vorgaenger
	 */
	public int[] getVorgaenger() {
		return vorgaenger;
	}

	/**
	 * Set parents
	 * @param vorgaenger
	 */
	public void setVorgaenger(int[] vorgaenger) {
		this.vorgaenger = vorgaenger;
	}

	/**
	 * Get array with number of child elements
	 * @return nachfolger
	 */
	public int[] getNachfolger() {
		return nachfolger;
	}

	/**
	 * Set childs
	 * @param nachfolger
	 */
	public void setNachfolger(int[] nachfolger) {
		this.nachfolger = nachfolger;
	}

	/**
	 * @return vorgaengerBasket
	 */
	public Collection getVorgaengerBasket() {
		return vorgaengerBasket;
	}

	/**
	 * @param vorgaengerBasket
	 */
	public void setVorgaengerBasket(Collection vorgaengerBasket) {
		this.vorgaengerBasket = vorgaengerBasket;
	}

	/**
	 * @param vorgaenger
	 */
	public void addIntoVorgaengerBasket(Integer vorgaenger) {
		vorgaengerBasket.add(vorgaenger);
	}

	/** Set this elements parents */
	public void getFromVorgaengerBasket() {
		Iterator vorgaengerBasketIt = vorgaengerBasket.iterator();
		vorgaenger = new int[vorgaengerBasket.size()];
		int i = 0;
		while (vorgaengerBasketIt.hasNext()) {
			vorgaenger[i] = ((Integer) vorgaengerBasketIt.next()).intValue();
			i++;
		}
	}

	/**
	 * Get the description
	 * @return bezeichnung
	 */
	public String getBezeichnung() {
		return bezeichnung;
	}

	/**
	 * Set the description
	 * @param description
	 */
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	/**
	 * @return
	 */
	public boolean isStartElem() {
		if (vorgaenger[0] == 0)
			return true;
		else
			return false;
	}

	/**
	 * @return
	 */
	public boolean isEndElem() {
		if (nachfolger[0] == 0)
			return true;
		else
			return false;
	}
	/**
	 * @return
	 */
	public boolean isCriticalPath() {
		return criticalPath;
	}

	/**
	 * @param isCriticalPath
	 */
	public void setCriticalPath(boolean isCriticalPath) {
		criticalPath = isCriticalPath;
	}

	/**
	 * Get x-Position of the middle-connection-line at the bottom of the element
	 * @return anchorBottomXPos
	 */
	public double getAnchorBottomXPos() {
		return anchorBottomXPos;
	}

	/**
	 * Get y-Position of the middle-connection-line at the bottom of the element
	 * @return anchorBottomYPos
	 */
	public double getAnchorBottomYPos() {
		return anchorBottomYPos;
	}

	/**
	 * Get x-Position of the middle-connection-line at the top of the element
	 * @return anchorTopXPos
	 */
	public double getAnchorTopXPos() {
		return anchorTopXPos;
	}

	/**
	 * Get y-Position of the middle-connection-line at the top of the element
	 * @return anchorTopYPos
	 */
	public double getAnchorTopYPos() {
		return anchorTopYPos;
	}

	/**
	 * Set x-Position of the middle-connection-line at the bottom of the element
	 * @param anchorBottomXPos
	 */
	public void setAnchorBottomXPos(double anchorBottomXPos) {
		this.anchorBottomXPos = anchorBottomXPos;
	}

	/**
	 * Set y-Position of the middle-connection-line at the bottom of the element
	 * @param anchorBottomYPos
	 */
	public void setAnchorBottomYPos(double anchorBottomYPos) {
		this.anchorBottomYPos = anchorBottomYPos;
	}

	/**
	 * Set x-Position of the middle-connection-line at the top of the element
	 * @param anchorTopXPos
	 */
	public void setAnchorTopXPos(double anchorTopXPos) {
		this.anchorTopXPos = anchorTopXPos;
	}

	/**
	 * Set y-Position of the middle-connection-line at the top of the element
	 * @param anchorTopYPos
	 */
	public void setAnchorTopYPos(double anchorTopYPos) {
		this.anchorTopYPos = anchorTopYPos;
	}
}