/*   ********************************************************************   **
**   Copyright notice                                                       **
**                                                                          **
**   (c) 2003 WiSim Development Team					                    					**
**   http://wisim.sourceforge.net/   			                            			**
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Class for displaying the networkplan in a JFrame
 * @author benjamin.pasero
 * @version 0.7a
 */
public class ShowNetworkplan extends JFrame {

	/** Vector containing all networkplan elements */
	private Vector npElemente;

	/** Extends JPanel and holds all JNetworkplanElements */
	private JNetworkplan netzplanGrafik;

	/** Holds the JNetworkplan and displays scrollbars if necessary */
	private JScrollPane holdAll;

	/**
	 * Set up the JFrame that holds a JScrollPane with the network plan
	 */
	public ShowNetworkplan() {

		npElemente = getNetworkPlanElements();
		setTitle("Networkplan editor Ver. 0.7a");
		getContentPane().setLayout(null);

		/** Maximize JFrame to screen-resolution */
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
			netzplanGrafik.setPreferredSize(new Dimension(netzplanGrafik.getMaxWidthPos() * 430, netzplanGrafik.getMaxHeightPos() * 280));

			/** Stop stopwatch */
			long endTime = System.currentTimeMillis();

			/** Get the time for calculating and showing the networkplan */
			calculationTime = endTime - startTime;

			JLabel showCalculationTime = new JLabel("Rendertime: " + (double) calculationTime / 1000 + " sec.");
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

			case 6 :
				filled.add(new NetworkplanElement(1, 2, new int[] { 2, 3 }, "Ausgieﬂen Fundamente"));
				filled.add(new NetworkplanElement(2, 5, new int[] { 4 }, "Ausgieﬂen Fundamente"));
				filled.add(new NetworkplanElement(3, 20, new int[] { 4 }, "Ausgieﬂen Fundamente"));
				filled.add(new NetworkplanElement(4, 2, new int[] { 0 }, "Ausgieﬂen Fundamente"));
				break;

				/** Get the informations from the database */
			case 7 :

				try {

					DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());

					/** DB Settings */
					String hostname = "localhost";
					String port = "3306";
					String user = "root";
					String password = "";
					String dbname = "wisim";

					/** Get a connection */
					Connection conn = DriverManager.getConnection("jdbc:mysql://" + hostname + ":" + port + "/" + dbname, user, password);
					conn.setAutoCommit(false);

					/** Select Statement */
					Statement stmt = conn.createStatement();
					String sql = "SELECT ap_Nr, ap_Dauer, ap_Beschreibung, vg_vorgaenger FROM ap, vg WHERE ap_Nr = f_ap_nr";

					ResultSet rset = stmt.executeQuery(sql);

					Hashtable parentBasket = new Hashtable();
					Vector completed = new Vector();

					while (rset.next()) {

						NetworkplanElement np = new NetworkplanElement(rset.getInt(1), new Double(String.valueOf(rset.getInt(2))).doubleValue(), rset.getString(3), new int[0]);

						/** This is a new element */
						if (!completed.contains(new Integer(np.getNumber()))) {
							completed.add(new Integer(np.getNumber()));
							filled.add(np);
							Vector parent = new Vector();
							parent.add(new Integer(rset.getInt(4)));
							parentBasket.put(new Integer(np.getNumber()), parent);

							/** This is an existing element with more than one child */
						} else {
							((Vector) parentBasket.get(new Integer(np.getNumber()))).add(new Integer(rset.getInt(4)));
						}
					}

					int a = 0;

					/** Set each element's childs from the childBasket */
					while (a < filled.size()) {
						NetworkplanElement np = (NetworkplanElement) filled.get(a);
						Vector parent = (Vector) parentBasket.get(new Integer(np.getNumber()));

						int parents[] = new int[parent.size()];

						int b = 0;
						while (b < parent.size()) {
							parents[b] = ((Integer) parent.get(b)).intValue();
							b++;
						}

						np.setParent(parents);
						a++;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;

			default : // Example with setting of parent elements instead of childs
				filled.add(new NetworkplanElement(1, 2, "Ausgieﬂen Fundamente", new int[] { 0 }));
				filled.add(new NetworkplanElement(2, 2, "Ausgieﬂen Fundamente", new int[] { 1 }));
				filled.add(new NetworkplanElement(3, 5, "Verschalung Betonsockel", new int[] { 2 }));
				filled.add(new NetworkplanElement(4, 3, "Betonierung Betonsockel", new int[] { 3 }));
				filled.add(new NetworkplanElement(5, 10, "Bestellung Betonteile", new int[] { 4 }));
				filled.add(new NetworkplanElement(6, 3, "Betonierung Betonsockel", new int[] { 5 }));
				filled.add(new NetworkplanElement(7, 10, "Bestellung Betonteile", new int[] { 6 }));
				filled.add(new NetworkplanElement(8, 2, "Aushub Versorgungsleitung", new int[] { 6 }));
				filled.add(new NetworkplanElement(9, 3, "Betonierung Betonsockel", new int[] { 6 }));
				filled.add(new NetworkplanElement(10, 10, "Bestellung Betonteile", new int[] { 7, 8 }));
				filled.add(new NetworkplanElement(11, 3, "Betonierung Betonsockel", new int[] { 10 }));
				filled.add(new NetworkplanElement(12, 10, "Bestellung Betonteile", new int[] { 9, 11 }));
				filled.add(new NetworkplanElement(13, 2, "Aushub Versorgungsleitung", new int[] { 12 }));
				break;

			case 9 : //More then one start element
				filled.add(new NetworkplanElement(1, 2, "Ausgieﬂen Fundamente", new int[] { 0 }));
				filled.add(new NetworkplanElement(2, 5, "Ausgieﬂen Fundamente", new int[] { 0 }));
				filled.add(new NetworkplanElement(3, 20, "Ausgieﬂen Fundamente", new int[] { 1 }));
				filled.add(new NetworkplanElement(4, 2, "Ausgieﬂen Fundamente", new int[] { 2, 3 }));
				filled.add(new NetworkplanElement(5, 2, "Ausgieﬂen Fundamente", new int[] { 4 }));
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