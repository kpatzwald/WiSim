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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * [DoItBen] Kommentar Klasse ShowNetzplanSwing
 * @author benjamin.pasero
 * @version 0.3a
 */
public class ShowNetzplanSwing extends JFrame {

	/**
	 * [DoItBen] Kommentar Konstruktor ShowNetzplanSwing()
	 * 
	 */
	public ShowNetzplanSwing() {

		Vector npElemente = getNetworkPlanElements();
		JPanel netzplanGrafik = new JPanel();

		getContentPane().setLayout(null);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width, screenSize.height);

		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				exitForm(evt);
			}
		});

		/** Instance for generating the networkin plan */
		NetzplanSwingGenerator npGrafik = new NetzplanSwingGenerator(npElemente);

		/** Get the Panel to use. */
		netzplanGrafik = npGrafik.getNetzplanGraphic();
		netzplanGrafik.setBackground(Color.WHITE);
		netzplanGrafik.setPreferredSize(new Dimension(npGrafik.getMaxWidthPos() * 430, npGrafik.getMaxHeightPos() * 280));

		/** JScrollPane holding the network plan */
		JScrollPane holdAll = new JScrollPane();
		holdAll.setBounds(0, 0, screenSize.width - 10, screenSize.height - 54);

		holdAll.getViewport().add(netzplanGrafik);
		holdAll.getVerticalScrollBar().setUnitIncrement(20);
		holdAll.getHorizontalScrollBar().setUnitIncrement(20);

		getContentPane().add(holdAll);
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
		filled.add(new NetzplanElement(1, 20, new int[] { 2, 6, 8 }, "Entwurf, Planung"));
		filled.add(new NetzplanElement(2, 3, new int[] { 3 }, "Erdaushub Fundamente"));
		filled.add(new NetzplanElement(3, 2, new int[] { 4 }, "Ausgie�en Fundamente"));
		filled.add(new NetzplanElement(4, 5, new int[] { 5 }, "Verschalung Betonsockel"));
		filled.add(new NetzplanElement(5, 3, new int[] { 9 }, "Betonierung Betonsockel"));
		filled.add(new NetzplanElement(6, 10, new int[] { 7 }, "Bestellung und Auslieferung Betonteile"));
		filled.add(new NetzplanElement(7, 2, new int[] { 9 }, "Aushub Ver- und Entsorgungsleitung"));
		filled.add(new NetzplanElement(8, 5, new int[] { 9 }, "Leitungsverlegung"));
		filled.add(new NetzplanElement(9, 7, new int[] { 10 }, "Montage Lagerhalle"));
		filled.add(new NetzplanElement(10, 4, new int[] { 0 }, "Installationsarbeiten"));

		//		filled.add(new NetzplanElement(1, 3, new int[] { 2,3 }, "Erdaushub Fundamente"));
		//		filled.add(new NetzplanElement(2, 2, new int[] { 4,5 }, "Ausgie�en Fundamente"));
		//		filled.add(new NetzplanElement(3, 5, new int[] { 6 }, "Verschalung Betonsockel"));
		//		filled.add(new NetzplanElement(4, 3, new int[] { 6 }, "Betonierung Betonsockel"));
		//		filled.add(new NetzplanElement(5, 10, new int[] { 6 }, "Bestellung Betonteile"));
		//		filled.add(new NetzplanElement(6, 2, new int[] { 0 }, "Aushub Versorgungsleitung"));

		//		filled.add(new NetzplanElement(1, 3, new int[] { 2 }, "Erdaushub Fundamente"));
		//		filled.add(new NetzplanElement(2, 2, new int[] { 3, 4 }, "Ausgie�en Fundamente"));
		//		filled.add(new NetzplanElement(3, 5, new int[] { 5 }, "Verschalung Betonsockel"));
		//		filled.add(new NetzplanElement(4, 3, new int[] { 6 }, "Betonierung Betonsockel"));
		//		filled.add(new NetzplanElement(5, 10, new int[] { 6 }, "Bestellung Betonteile"));
		//		filled.add(new NetzplanElement(6, 2, new int[] { 0 }, "Aushub Versorgungsleitung"));

		//filled.add(new NetzplanElement(1, 3, new int[] { 2 }, "Erdaushub Fundamente"));
		//filled.add(new NetzplanElement(2, 2, new int[] { 0 }, "Ausgie�en Fundamente"));

		//		filled.add(new NetzplanElement(1, 2, new int[] { 2, 3 }, "Ausgie�en Fundamente"));
		//		filled.add(new NetzplanElement(2, 2, new int[] { 4 }, "Ausgie�en Fundamente"));
		//		filled.add(new NetzplanElement(3, 5, new int[] { 4 }, "Verschalung Betonsockel"));
		//		filled.add(new NetzplanElement(4, 3, new int[] { 5 }, "Betonierung Betonsockel"));
		//		filled.add(new NetzplanElement(5, 10, new int[] { 6 }, "Bestellung Betonteile"));
		//		filled.add(new NetzplanElement(6, 2, new int[] { 0 }, "Aushub Versorgungsleitung"));

		/* Complex one (not working, will be fixed in future versions!) */
		//		filled.add(new NetzplanElement(1, 2, new int[] { 2, 3 }, "Ausgie�en Fundamente"));
		//		filled.add(new NetzplanElement(2, 2, new int[] { 4, 5 }, "Ausgie�en Fundamente"));
		//		filled.add(new NetzplanElement(3, 5, new int[] { 6 }, "Verschalung Betonsockel"));
		//		filled.add(new NetzplanElement(4, 3, new int[] { 6 }, "Betonierung Betonsockel"));
		//		filled.add(new NetzplanElement(5, 10, new int[] { 6 }, "Bestellung Betonteile"));
		//		filled.add(new NetzplanElement(6, 2, new int[] { 0 }, "Aushub Versorgungsleitung"));

		return filled;
	}

	/**
	 * Exit the application
	 * @param evt
	 */
	private void exitForm(java.awt.event.WindowEvent evt) {
		System.exit(0);
	}
}