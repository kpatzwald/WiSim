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
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Class for displaying the networkplan in a JFrame
 * @author benjamin.pasero
 * @version 0.6a
 */
public class ShowNetworkplan extends JFrame {

	private Vector npElemente;
	private JNetworkplan netzplanGrafik;
	private JScrollPane holdAll;

	/**
	 * Set up the JFrame that holds a JScrollPane with the network plan
	 */
	public ShowNetworkplan() {

		npElemente = getNetworkPlanElements();

		getContentPane().setLayout(null);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width, screenSize.height);

		/** Listener for closing the JFrame */
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				exitForm(evt);
			}
		});

		long calculationTime = 0;
		try {

			/** Start stopwatch */
			long startTime = System.currentTimeMillis();

			netzplanGrafik = new JNetworkplan(npElemente);

			/** Get the Panel to use. */
			netzplanGrafik.setLayout(null);
			netzplanGrafik.setBackground(Color.WHITE);
			netzplanGrafik.setPreferredSize(
				new Dimension(netzplanGrafik.getMaxWidthPos() * 430, netzplanGrafik.getMaxHeightPos() * 280));

			/** Stop stopwatch */
			long endTime = System.currentTimeMillis();

			calculationTime = endTime - startTime;

			JLabel showCalculationTime = new JLabel("Rendertime: " + (double) calculationTime / 1000 + " sec.");
			showCalculationTime.setForeground(Color.BLACK);
			netzplanGrafik.add(showCalculationTime);
			showCalculationTime.setBounds(20, 20, 200, 20);

		}

		/** There was an error while generating and calculating the network plan */
		catch (Exception e) {
			String errorMessage =
				"Error: The network plan seems not to be correct!"
					+ "\n\nPlease check your networkplan:"
					+ "\n\n- Make sure each element has a child element that exists"
					+ "\n- Make sure you dont used one number for two elements"
					+ "\n- Make sure have only 1 start- and 1 end-element"
					+ "\n- Use 0 for the child of the last element";

			JTextArea errorDescription = new JTextArea(errorMessage);
			errorDescription.setEditable(false);
			errorDescription.setSelectionColor(Color.WHITE);
			errorDescription.setForeground(Color.RED);
			errorDescription.setBackground(new Color(204, 204, 204));
			errorDescription.setFont(new Font("Dialog", 1, 20));

			netzplanGrafik.add(errorDescription);
		}

		/** JScrollPane holding the network plan */
		holdAll = new JScrollPane();
		holdAll.setBounds(0, 0, screenSize.width - 10, screenSize.height - 67);

		holdAll.getViewport().add(netzplanGrafik);
		holdAll.getVerticalScrollBar().setUnitIncrement(20);
		holdAll.getHorizontalScrollBar().setUnitIncrement(20);

		getContentPane().add(holdAll);
	}

	/** Display the JFrame */
	public static void main(String[] args) {
		new ShowNetworkplan().show();
	}

	/**
	 * Gets a filled Vector with the network plan elements
	 * @return Vector with network plan elements
	 */
	public Vector getNetworkPlanElements() {

		Vector filled = new Vector();

		/** Selected network plan */
		int show = 3;

		/** Some network plans to choose */
		switch (show) {
			case 0 :
				filled.add(new NetworkplanElement(1, 20, new int[] { 2, 6, 8 }, "Entwurf, Planung"));
				filled.add(new NetworkplanElement(2, 3, new int[] { 3 }, "Erdaushub Fundamente"));
				filled.add(new NetworkplanElement(3, 2, new int[] { 4 }, "Ausgieﬂen Fundamente"));
				filled.add(new NetworkplanElement(4, 5, new int[] { 5 }, "Verschalung Betonsockel"));
				filled.add(new NetworkplanElement(5, 3, new int[] { 9 }, "Betonierung Betonsockel"));
				filled.add(new NetworkplanElement(6, 10, new int[] { 7 }, "Bestellung und Auslieferung Betonteile"));
				filled.add(new NetworkplanElement(7, 2, new int[] { 9 }, "Aushub Ver- und Entsorgungsleitung"));
				filled.add(new NetworkplanElement(8, 5, new int[] { 9 }, "Leitungsverlegung"));
				filled.add(new NetworkplanElement(9, 7, new int[] { 10 }, "Montage Lagerhalle"));
				filled.add(new NetworkplanElement(10, 4, new int[] { 0 }, "Installationsarbeiten"));
				break;

			case 1 :
				filled.add(new NetworkplanElement(1, 3, new int[] { 2 }, "Erdaushub Fundamente"));
				filled.add(new NetworkplanElement(2, 2, new int[] { 3, 4 }, "Ausgieﬂen Fundamente"));
				filled.add(new NetworkplanElement(3, 5, new int[] { 5 }, "Verschalung Betonsockel"));
				filled.add(new NetworkplanElement(4, 3, new int[] { 5 }, "Betonierung Betonsockel"));
				filled.add(new NetworkplanElement(5, 10, new int[] { 6 }, "Bestellung Betonteile"));
				filled.add(new NetworkplanElement(6, 2, new int[] { 0 }, "Aushub Versorgungsleitung"));
				break;

			case 2 :
				filled.add(new NetworkplanElement(1, 2, new int[] { 2, 3 }, "Ausgieﬂen Fundamente"));
				filled.add(new NetworkplanElement(2, 2, new int[] { 4, 5 }, "Ausgieﬂen Fundamente"));
				filled.add(new NetworkplanElement(3, 5, new int[] { 6 }, "Verschalung Betonsockel"));
				filled.add(new NetworkplanElement(4, 3, new int[] { 6 }, "Betonierung Betonsockel"));
				filled.add(new NetworkplanElement(5, 10, new int[] { 6 }, "Bestellung Betonteile"));
				filled.add(new NetworkplanElement(6, 2, new int[] { 0 }, "Aushub Versorgungsleitung"));
				break;

			case 3 :
				filled.add(new NetworkplanElement(1, 20, new int[] { 2, 3 }, "Entwurf, Planung"));
				filled.add(new NetworkplanElement(2, 3, new int[] { 4, 5 }, "Erdaushub Fundamente"));
				filled.add(new NetworkplanElement(3, 2, new int[] { 6, 7 }, "Ausgieﬂen Fundamente"));
				filled.add(new NetworkplanElement(4, 5, new int[] { 8 }, "Verschalung Betonsockel"));
				filled.add(new NetworkplanElement(5, 3, new int[] { 8 }, "Betonierung Betonsockel"));
				filled.add(new NetworkplanElement(6, 10, new int[] { 9 }, "Bestellung und Auslieferung Betonteile"));
				filled.add(new NetworkplanElement(7, 2, new int[] { 9 }, "Aushub Ver- und Entsorgungsleitung"));
				filled.add(new NetworkplanElement(8, 5, new int[] { 10 }, "Leitungsverlegung"));
				filled.add(new NetworkplanElement(9, 7, new int[] { 10 }, "Montage Lagerhalle"));
				filled.add(new NetworkplanElement(10, 4, new int[] { 0 }, "Installationsarbeiten"));
				break;

			case 4 :
				filled.add(new NetworkplanElement(1, 2, new int[] { 2, 3 }, "Ausgieﬂen Fundamente"));
				filled.add(new NetworkplanElement(2, 2, new int[] { 4, 5 }, "Ausgieﬂen Fundamente"));
				filled.add(new NetworkplanElement(3, 5, new int[] { 8 }, "erschalung Betonsockel"));
				filled.add(new NetworkplanElement(4, 3, new int[] { 8 }, "Betonierung Betonsockel"));
				filled.add(new NetworkplanElement(5, 10, new int[] { 6, 7 }, "Bestellung Betonteile"));
				filled.add(new NetworkplanElement(6, 3, new int[] { 8 }, "Betonierung Betonsockel"));
				filled.add(new NetworkplanElement(7, 10, new int[] { 8 }, "Bestellung Betonteile"));
				filled.add(new NetworkplanElement(8, 2, new int[] { 0 }, "Aushub Versorgungsleitung"));
				break;

			case 5 : //Testing a wrong networkplan element
				filled.add(new NetworkplanElement(0, 2, new int[] { 1 }, "Ausgieﬂen Fundamente"));
				filled.add(new NetworkplanElement(1, 2, new int[] { 0 }, "Ausgieﬂen Fundamente"));
				break;
		}
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