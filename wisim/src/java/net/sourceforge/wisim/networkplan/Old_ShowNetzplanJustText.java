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

import java.util.Iterator;
import java.util.Vector;

/**
 * Class for debugging purposes of the network plan generator
 * @author benjamin.pasero
 * @version 0.1a
 */
public class Old_ShowNetzplanJustText {

	private static Vector npElemente;

	public static void main(String[] args) {

		npElemente = new Vector();

		npElemente.add(new NetzplanElement(1, 20, new int[] { 2, 6, 7 }, "Entwurf, Planung und der Kay ist"));
		npElemente.add(new NetzplanElement(2, 3, new int[] { 3 }, "Erdaushub Fundamente"));
		npElemente.add(new NetzplanElement(3, 2, new int[] { 4 }, "Ausgieﬂen Fundamente"));
		npElemente.add(new NetzplanElement(4, 5, new int[] { 5 }, "Verschalung Betonsockel"));
		npElemente.add(new NetzplanElement(5, 3, new int[] { 9 }, "Betonierung Betonsockel"));
		npElemente.add(new NetzplanElement(6, 10, new int[] { 9 }, "Bestellung Betonteile"));
		npElemente.add(new NetzplanElement(7, 2, new int[] { 8 }, "Aushub Versorgungsleitung"));
		npElemente.add(new NetzplanElement(8, 5, new int[] { 9 }, "Leitungsverlegung"));
		npElemente.add(new NetzplanElement(9, 7, new int[] { 10 }, "Montage Lagerhalle"));
		npElemente.add(new NetzplanElement(10, 4, new int[] { 0 }, "Installationsarbeiten"));

		NetzplanCalculator npCalc = new NetzplanCalculator(npElemente);
		npElemente = npCalc.getNpElemente();

		Iterator npElemIt = npElemente.iterator();
		while (npElemIt.hasNext()) {
			NetzplanElement npElem = (NetzplanElement) npElemIt.next();

			String vorgang = "Vorgang: " + npElem.getNummer() + "\n";
			String dauer = "Dauer: " + npElem.getDauer() + "\n";
			String faz_fez = "FAZ: " + npElem.getFaz() + "\tFEZ: " + npElem.getFez() + "\n";
			String saz_sez = "SAZ: " + npElem.getSaz() + "\tSEZ: " + npElem.getSez() + "\n";
			String gp_fp = "GP: " + npElem.getGp() + "\tFP: " + npElem.getFp() + "\n";

			System.out.println(vorgang + dauer + faz_fez + saz_sez + gp_fp);
		}

		Vector criticalPath = npCalc.getCriticalPath();
		String criticalPathString = "";
		Iterator npElemCriticalIt = criticalPath.iterator();
		while (npElemCriticalIt.hasNext()) {
			NetzplanElement npElemCritical = (NetzplanElement) npElemCriticalIt.next();
			criticalPathString = criticalPathString + " " + npElemCritical.getNummer();
		}

		System.out.println("Kritischer Pfad: " + criticalPathString);

		System.out.println("Anzahl paralleler T‰tigkeiten: " + npCalc.getCountedBranches());
	}
}