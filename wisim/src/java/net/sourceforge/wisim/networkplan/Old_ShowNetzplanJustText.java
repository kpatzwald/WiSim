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

//		npElemente.add(new NetworkplanElement(1, 20, new int[] { 2, 6, 7 }, "Entwurf, Planung und der Kay ist"));
//		npElemente.add(new NetworkplanElement(2, 3, new int[] { 3 }, "Erdaushub Fundamente"));
//		npElemente.add(new NetworkplanElement(3, 2, new int[] { 4 }, "Ausgieﬂen Fundamente"));
//		npElemente.add(new NetworkplanElement(4, 5, new int[] { 5 }, "Verschalung Betonsockel"));
//		npElemente.add(new NetworkplanElement(5, 3, new int[] { 9 }, "Betonierung Betonsockel"));
//		npElemente.add(new NetworkplanElement(6, 10, new int[] { 9 }, "Bestellung Betonteile"));
//		npElemente.add(new NetworkplanElement(7, 2, new int[] { 8 }, "Aushub Versorgungsleitung"));
//		npElemente.add(new NetworkplanElement(8, 5, new int[] { 9 }, "Leitungsverlegung"));
//		npElemente.add(new NetworkplanElement(9, 7, new int[] { 10 }, "Montage Lagerhalle"));
//		npElemente.add(new NetworkplanElement(10, 4, new int[] { 0 }, "Installationsarbeiten"));

		NetworkplanCalculator npCalc = new NetworkplanCalculator(npElemente, true);
		npElemente = npCalc.getNpElemente();

		Iterator npElemIt = npElemente.iterator();
		while (npElemIt.hasNext()) {
			NetworkplanElement npElem = (NetworkplanElement) npElemIt.next();

			String vorgang = "Vorgang: " + npElem.getIndex() + "\n";
			String dauer = "Dauer: " + npElem.getDuration() + "\n";
			String faz_fez = "FAZ: " + npElem.getFaz() + "\tFEZ: " + npElem.getFez() + "\n";
			String saz_sez = "SAZ: " + npElem.getSaz() + "\tSEZ: " + npElem.getSez() + "\n";
			String gp_fp = "GP: " + npElem.getGp() + "\tFP: " + npElem.getFp() + "\n";

			System.out.println(vorgang + dauer + faz_fez + saz_sez + gp_fp);
		}

		Vector criticalPath = npCalc.getCriticalPath();
		String criticalPathString = "";
		Iterator npElemCriticalIt = criticalPath.iterator();
		while (npElemCriticalIt.hasNext()) {
			NetworkplanElement npElemCritical = (NetworkplanElement) npElemCriticalIt.next();
			criticalPathString = criticalPathString + " " + npElemCritical.getIndex();
		}

		System.out.println("Kritischer Pfad: " + criticalPathString);

		System.out.println("Anzahl paralleler T‰tigkeiten: " + npCalc.getCountedBranches());
	}
}