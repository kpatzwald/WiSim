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

package net.sourceforge.wisim.controller;
import java.util.Iterator;
import java.util.Vector;

/*
 * Created on 25.05.2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */

/**
 * @author Benjamin Pasero
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class NetzplanCalculator {

	private Vector npElemente;
	private Iterator npElemIt;

	public NetzplanCalculator(Vector npElemente) {
		this.npElemente = npElemente;
		npElemIt = npElemente.iterator();

		/** Bestimmung der Vorgaenger */
		setVorgaenger();

		/** Kalkulation */
		calculateFazFez();
		calculateSazSez();
		calculatePuffer();
	}

	/** Bestimmung der Vorgaenger */
	public void setVorgaenger() {
		npElemIt = npElemente.iterator();
		while (npElemIt.hasNext()) {
			NetzplanElement npElem = (NetzplanElement) npElemIt.next();
			int[] nachfolger = npElem.getNachfolger();
			int i = 0;
			while (i < nachfolger.length && nachfolger[i] != 0) {
				((NetzplanElement) npElemente.get(nachfolger[i] - 1)).addIntoVorgaengerBasket(new Integer(npElem.getNummer()));
				i++;
			}
		}

		npElemIt = npElemente.iterator();
		while (npElemIt.hasNext()) {
			NetzplanElement npElem = (NetzplanElement) npElemIt.next();
			if (npElem.getVorgaengerBasket().size() > 0) {
				npElem.getFromVorgaengerBasket();
			}
			else {
				npElem.addIntoVorgaengerBasket(new Integer(0));
				npElem.getFromVorgaengerBasket();
			}
		}
	}

	/** Kalkulation des frühsten Anfangs- und Endzeitpunkts (Forwärtsrechnung) */
	public void calculateFazFez() {
		npElemIt = npElemente.iterator();
		while (npElemIt.hasNext()) {
			NetzplanElement npElem = (NetzplanElement) npElemIt.next();

			int vorgaenger[] = npElem.getVorgaenger();
			if (vorgaenger[0] == 0) {
				npElem.setFaz(0);
				npElem.setFez(npElem.getDauer());
			} else {
				int i = 0;
				double maxFez = 0;
				while (i < vorgaenger.length) {
					NetzplanElement npElemVorgaenger = (NetzplanElement) npElemente.get(vorgaenger[i] - 1);
					if (maxFez < npElemVorgaenger.getFez())
						maxFez = npElemVorgaenger.getFez();
					i++;
				}
				npElem.setFaz(maxFez);
				npElem.setFez(npElem.getFaz() + npElem.getDauer());
			}
		}
	}

	/** Kalkulation des spätesten Anfangs- und Endzeitpunkts (Rückwärtsrechnung) */
	public void calculateSazSez() {
		Vector npElementeDesc = new Vector();
		int a = npElemente.size() - 1;

		while (a >= 0) {
			npElementeDesc.add(npElemente.get(a));
			a--;
		}

		npElemIt = npElementeDesc.iterator();
		while (npElemIt.hasNext()) {
			NetzplanElement npElem = (NetzplanElement) npElemIt.next();

			int nachfolger[] = npElem.getNachfolger();
			if (nachfolger[0] == 0) {
				npElem.setSaz(npElem.getFaz());
				npElem.setSez(npElem.getFez());
			} else {
				int i = 0;
				double minSaz = 9999999;
				while (i < nachfolger.length) {
					NetzplanElement npElemNachfolger = (NetzplanElement) npElemente.get(nachfolger[i] - 1);
					if (minSaz > npElemNachfolger.getSaz())
						minSaz = npElemNachfolger.getSaz();
					i++;
				}
				npElem.setSaz(minSaz - npElem.getDauer());
				npElem.setSez(minSaz);
			}
		}
	}

	/** Kalkulation des Gesamtpuffers und des Freien Puffers */
	public void calculatePuffer() {

		/** Gesamtpuffer (SAZ - FAZ) */
		npElemIt = npElemente.iterator();
		while (npElemIt.hasNext()) {
			NetzplanElement npElem = (NetzplanElement) npElemIt.next();
			npElem.setGp(npElem.getSaz() - npElem.getFaz());
		}

		/** Freier Puffer (FAZ[i+1] - FEZ[i]) **/
		npElemIt = npElemente.iterator();
		while (npElemIt.hasNext()) {
			NetzplanElement npElem = (NetzplanElement) npElemIt.next();

			int nachfolger[] = npElem.getNachfolger();
			if (nachfolger[0] != 0) {
				int i = 0;
				double minFaz = 9999999;
				while (i < nachfolger.length) {
					NetzplanElement npElemVorgaenger = (NetzplanElement) npElemente.get(nachfolger[i] - 1);
					if (minFaz > npElemVorgaenger.getFaz())
						minFaz = npElemVorgaenger.getFaz();
					i++;
				}
				npElem.setFp(minFaz - npElem.getFez());
			}
		}
	}

	public Vector getCriticalPath() {
		Vector criticalPath = new Vector();

		npElemIt = npElemente.iterator();
		while (npElemIt.hasNext()) {
			NetzplanElement npElem = (NetzplanElement) npElemIt.next();
			if (npElem.getFp() == 0 && npElem.getGp() == 0)
				criticalPath.add(npElem);
		}
		return criticalPath;
	}

	public double getMaxWidthOfNetzplan() {
		double maxWidth = 1;
		npElemIt = npElemente.iterator();
		while (npElemIt.hasNext()) {
			NetzplanElement npElem = (NetzplanElement) npElemIt.next();
			int actWidth = npElem.getNachfolger().length;

			if (actWidth > 1)
				maxWidth += actWidth;
		}
		return maxWidth;
	}

	/**
	 * @return
	 */
	public Vector getNpElemente() {
		return npElemente;
	}

	/**
	 * @param vector
	 */
	public void setNpElemente(Vector vector) {
		npElemente = vector;
	}
}