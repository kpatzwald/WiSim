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

/*
 * ShowNetzplanSwing.java
 */
package net.sourceforge.wisim.networkplan;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * TODO Kommentar Klasse ShowNetzplanSwing
 * @author benjamin.pasero
 */
public class ShowNetzplanSwing extends JFrame {

	public ShowNetzplanSwing() {

		getContentPane().setLayout(null);

		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				exitForm(evt);
			}
		});

		Vector npElemente = getNetworkPlanElements();

		NetzplanSwingGenerator npGrafik = new NetzplanSwingGenerator(npElemente);

		/** Get the Panel to use. */
		JPanel netzplanGrafik = new JPanel();
		
		netzplanGrafik = npGrafik.getNetzplanGraphic();

		getContentPane().add(netzplanGrafik);
		//netzplanGrafik.setBounds(30, 30, 360, 210);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screenSize.width - 800) / 2, (screenSize.height - 600) / 2, 800, 600);
	}

	public static void main(String[] args) {
		new ShowNetzplanSwing().show();
	}

	/**
		 * Gets a filled Vector with the network plan elements
		 * @return Vector with network plan elements
		 */
	public Vector getNetworkPlanElements() {

		Vector filled = new Vector();

		/** Elements of the network plan */
		filled.add(new NetzplanElement(1, 20, new int[] { 2, 6, 8 }, "Entwicklung, Planung"));
		filled.add(new NetzplanElement(2, 3, new int[] { 3 }, "Erdaushub Fundamente"));
		filled.add(new NetzplanElement(3, 2, new int[] { 4 }, "Ausgieﬂen Fundamente"));
		filled.add(new NetzplanElement(4, 5, new int[] { 5 }, "Verschalung Betonsockel"));
		filled.add(new NetzplanElement(5, 3, new int[] { 9 }, "Betonierung Betonsockel"));
		filled.add(new NetzplanElement(6, 10, new int[] { 7 }, "Bestellung und Auslieferung Betonteile"));
		filled.add(new NetzplanElement(7, 2, new int[] { 9 }, "Aushub Ver- und Entsorgungsleitung"));
		filled.add(new NetzplanElement(8, 5, new int[] { 9 }, "Leitungsverlegung"));
		filled.add(new NetzplanElement(9, 7, new int[] { 10 }, "Montage Lagerhalle"));
		filled.add(new NetzplanElement(10, 4, new int[] { 0 }, "Installationsarbeiten"));

		/*npElemente.add(new NetzplanElement(1, 3, new int[] { 2,3 }, "Erdaushub Fundamente"));
		filled.add(new NetzplanElement(2, 2, new int[] { 4,5 }, "Ausgieﬂen Fundamente"));
		filled.add(new NetzplanElement(3, 5, new int[] { 6 }, "Verschalung Betonsockel"));
		filled.add(new NetzplanElement(4, 3, new int[] { 6 }, "Betonierung Betonsockel"));
		filled.add(new NetzplanElement(5, 10, new int[] { 6 }, "Bestellung Betonteile"));
		filled.add(new NetzplanElement(6, 2, new int[] { 0 }, "Aushub Versorgungsleitung"));*/

		/*npElemente.add(new NetzplanElement(1, 3, new int[] { 2 }, "Erdaushub Fundamente"));
		filled.add(new NetzplanElement(2, 2, new int[] { 3, 4 }, "Ausgieﬂen Fundamente"));
		filled.add(new NetzplanElement(3, 5, new int[] { 5 }, "Verschalung Betonsockel"));
		filled.add(new NetzplanElement(4, 3, new int[] { 5 }, "Betonierung Betonsockel"));
		filled.add(new NetzplanElement(5, 10, new int[] { 6 }, "Bestellung Betonteile"));
		filled.add(new NetzplanElement(6, 2, new int[] { 0 }, "Aushub Versorgungsleitung"));*/

		//filled.add(new NetzplanElement(1, 3, new int[] { 2 }, "Erdaushub Fundamente"));
		//filled.add(new NetzplanElement(2, 2, new int[] { 0 }, "Ausgieﬂen Fundamente"));

		return filled;
	}

	/** Exit the Application */
	private void exitForm(java.awt.event.WindowEvent evt) {
		System.exit(0);
	}
}