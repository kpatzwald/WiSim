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

package net.sourceforge.wisim.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import net.sourceforge.wisim.dao.WiSimDAO;
import net.sourceforge.wisim.dao.WiSimDAOException;
import net.sourceforge.wisim.model.*;
import net.sourceforge.wisim.networkplan.JNetworkplan;

/**
 * [DoItBen] Kommentar Klasse JPanelNetworkplan
 * @author benjamin.pasero
 */
public class JPanelNetworkplan extends javax.swing.JPanel {

	/** Vector containing all networkplan elements */
	private Vector npElemente;

	/** Extends JPanel and holds all JNetworkplanElements */
	private JNetworkplan netzplanGrafik;

	/** Holds the JNetworkplan and displays scrollbars if necessary */
	private JScrollPane holdAll;
	private WiSimDAO dao;

	//Logger
	private WiSimLogger wiSimLogger;

	public JPanelNetworkplan(WiSimMainController wiSimMainController) {
		wiSimLogger = wiSimMainController.getWiSimLogger();
		initDAO(wiSimMainController);

		try {
			npElemente = dao.getNetworkplanElements();
		} catch (WiSimDAOException e) {
			wiSimLogger.log("getNetworkplanElemente", e);
		}

		initComponents();

		long renderTime = 0;
		try {

			/** Start stopwatch */
			long startTime = System.currentTimeMillis();

			netzplanGrafik = new JNetworkplan(wiSimMainController, npElemente);

			/** Get the Panel to use. */
			netzplanGrafik.setLayout(null);
			netzplanGrafik.setBackground(Color.WHITE);
			netzplanGrafik.setPreferredSize(new Dimension(netzplanGrafik.getMaxWidthPos() * 430, netzplanGrafik.getMaxHeightPos() * 280));

			/** Stop stopwatch */
			long endTime = System.currentTimeMillis();

			/** Get the time for calculating and showing the networkplan */
			renderTime = endTime - startTime;

			JLabel showCalculationTime = new JLabel("Rendertime: " + (double) renderTime / 1000 + " sec.");
			netzplanGrafik.add(showCalculationTime);
			showCalculationTime.setBounds(10, 5, 200, 20);

		}

		/** There was an error while generating and calculating the network plan */
		catch (Exception e) {
			String errorMessage =
				"Error: The network plan seems not to be correct!"
					+ "\n\nPlease check your networkplan:"
					+ "\n\n- Make sure the list of activities is in a correct order"
					+ "\n- Make sure each element has a child element that exists"
					+ "\n- Make sure you dont used one number for two elements"
					+ "\n- Use 0 for the child of the last element"
					+ "\n- Use 0 for the parent of the first element";

			JTextArea errorDescription = new JTextArea(errorMessage);
			errorDescription.setEditable(false);
			errorDescription.setSelectionColor(Color.WHITE);
			errorDescription.setForeground(Color.RED);
			errorDescription.setBackground(new Color(204, 204, 204));
			errorDescription.setFont(new Font("Dialog", 1, 20));
			setBackground(Color.WHITE);
			netzplanGrafik = new JNetworkplan();
			netzplanGrafik.add(errorDescription);
		}

		/** JScrollPane holding the network plan */
		holdAll = new JScrollPane();
		holdAll.setBounds(0, 0, (int)getPreferredSize().getWidth(), (int)getPreferredSize().getHeight());
		holdAll.getViewport().add(netzplanGrafik);
		holdAll.getVerticalScrollBar().setUnitIncrement(20);
		holdAll.getHorizontalScrollBar().setUnitIncrement(20);

		add(holdAll);
	}

	private void initDAO(WiSimMainController wiSimMainController) {
		dao = wiSimMainController.getDAO();
	}

	private void initComponents() {
		setLayout(new java.awt.GridLayout());
		setPreferredSize(new java.awt.Dimension(800, 600));
	}
}