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
 * @version 0.2a
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

	private boolean selected;

	private double anchorTopXPos;
	private double anchorBottomXPos;
	private double anchorTopYPos;
	private double anchorBottomYPos;

	/**
	 * [DoItBen] Kommentar Konstruktor NetzplanElement()
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
		selected = false;
	}

	/**
	 * Get the duration of the network plan element
	 * @return
	 */
	public double getDauer() {
		return dauer;
	}

	/**
	 *  Set the duration of the network plan element
	 * @param d
	 */
	public void setDauer(double d) {
		dauer = d;
	}

	/**
	 * @return
	 */
	public double getFaz() {
		return faz;
	}

	/**
	 * @return
	 */
	public double getFez() {
		return fez;
	}

	/**
	 * @return
	 */
	public double getFp() {
		return fp;
	}

	/**
	 * @return
	 */
	public double getGp() {
		return gp;
	}

	/**
	 * @return
	 */
	public double getSaz() {
		return saz;
	}

	/**
	 * @return
	 */
	public double getSez() {
		return sez;
	}

	/**
	 * @param d
	 */
	public void setFaz(double d) {
		faz = d;
	}

	/**
	 * @param d
	 */
	public void setFez(double d) {
		fez = d;
	}

	/**
	 * @param d
	 */
	public void setFp(double d) {
		fp = d;
	}

	/**
	 * @param d
	 */
	public void setGp(double d) {
		gp = d;
	}

	/**
	 * @param d
	 */
	public void setSaz(double d) {
		saz = d;
	}

	/**
	 * @param d
	 */
	public void setSez(double d) {
		sez = d;
	}

	/**
	 * @return
	 */
	public int getNummer() {
		return nummer;
	}

	/**
	 * @param i
	 */
	public void setNummer(int i) {
		nummer = i;
	}

	/**
	 * @return
	 */
	public int[] getVorgaenger() {
		return vorgaenger;
	}

	/**
	 * @param is
	 */
	public void setVorgaenger(int[] is) {
		vorgaenger = is;
	}

	/**
	 * @return
	 */
	public int[] getNachfolger() {
		return nachfolger;
	}

	/**
	 * @param is
	 */
	public void setNachfolger(int[] is) {
		nachfolger = is;
	}

	/**
	 * @return
	 */
	public Collection getVorgaengerBasket() {
		return vorgaengerBasket;
	}

	/**
	 * @param collection
	 */
	public void setVorgaengerBasket(Collection collection) {
		vorgaengerBasket = collection;
	}

	/**
	 * [DoItBen] Kommentar für addIntoVorgaengerBasket()
	 * @param vorgaenger
	 */
	public void addIntoVorgaengerBasket(Integer vorgaenger) {
		vorgaengerBasket.add(vorgaenger);
	}

	/**
	 * [DoItBen] Kommentar für getFromVorgaengerBasket()
	 * 
	 */
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
	 * @return
	 */
	public String getBezeichnung() {
		return bezeichnung;
	}

	/**
	 * @param string
	 */
	public void setBezeichnung(String string) {
		bezeichnung = string;
	}

	/**
	 * [DoItBen] Kommentar für isStartElem()
	 * @return
	 */
	public boolean isStartElem() {
		if (vorgaenger[0] == 0)
			return true;
		else
			return false;
	}

	/**
	 * [DoItBen] Kommentar für isEndElem()
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
	 * @param b
	 */
	public void setCriticalPath(boolean b) {
		criticalPath = b;
	}
	/**
	 * [DoItBen] Kommentar für isSelected()
	 * @return
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * [DoItBen] Kommentar für setSelected()
	 * @param b
	 */
	public void setSelected(boolean b) {
		selected = b;
	}

	/**
	 * @return
	 */
	public double getAnchorBottomXPos() {
		return anchorBottomXPos;
	}

	/**
	 * @return
	 */
	public double getAnchorBottomYPos() {
		return anchorBottomYPos;
	}

	/**
	 * @return
	 */
	public double getAnchorTopXPos() {
		return anchorTopXPos;
	}

	/**
	 * @return
	 */
	public double getAnchorTopYPos() {
		return anchorTopYPos;
	}

	/**
	 * @param d
	 */
	public void setAnchorBottomXPos(double d) {
		anchorBottomXPos = d;
	}

	/**
	 * @param d
	 */
	public void setAnchorBottomYPos(double d) {
		anchorBottomYPos = d;
	}

	/**
	 * @param d
	 */
	public void setAnchorTopXPos(double d) {
		anchorTopXPos = d;
	}

	/**
	 * @param d
	 */
	public void setAnchorTopYPos(double d) {
		anchorTopYPos = d;
	}
}