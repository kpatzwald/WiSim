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
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

/**
 * [DoItBen] Kommentar Klasse NetzplanGrafikSwingGenerator
 * @author benjamin.pasero
 * @version 0.3a
 */
public class NetzplanSwingGenerator {

	private Vector npElemente;
	private NetzplanCalculator npCalc;
	private Graphics g;
	private int[][] position;
	private Vector tupel[];
	private double maxWidth;
	private BufferedImage netzplanGrafik;
	private int selected;

	private int maxWidthPos;
	private int maxHeightPos;

	private JPanel jPanelNetworkplanContainer;

	/**
	 * [DoItBen] Kommentar Konstruktor NetzplanSwingGenerator()
	 * @param npElemente
	 */
	public NetzplanSwingGenerator(Vector npElemente) {

		tupel = new Vector[100];

		/** Matrix for positioning of the elements */
		position = new int[20][20];
		for (int a = 0; a < 20; a++)
			for (int b = 0; b < 20; b++)
				position[a][b] = 0;

		this.npElemente = npElemente;
		npCalc = new NetzplanCalculator(npElemente);
		npElemente = npCalc.getNpElemente();

		//showCriticalPath(); <- Some Bugs. Fixes in next versions!

		maxWidth = npCalc.getMaxWidthOfNetzplan();
		calculatePositions();

		System.out.println("Row with most elements: " + getMostWidthRow());

		/** Max. size of image: Width */
		maxWidthPos = 0;
		for (int a = 0; a < 20; a++) {
			for (int b = 0; b < 20; b++) {
				if (position[a][b] != 0) {
					maxWidthPos = a + 1;
				}
			}
		}

		/** Max. size of image: Height */
		maxHeightPos = 0;
		for (int a = 0; a < 20; a++) {
			for (int b = 0; b < 20; b++) {
				if (position[b][a] != 0) {
					maxHeightPos = a + 1;
				}
			}
		}

		jPanelNetworkplanContainer = new JPanel();
		jPanelNetworkplanContainer.setBounds(30, 30, 500 * maxWidthPos, 260 * maxHeightPos);
		jPanelNetworkplanContainer.setLayout(null);
		jPanelNetworkplanContainer.setAutoscrolls(true);

		paintSwingElmements();
	}

	/** Paints the network plan */
	public void paintSwingElmements() {

		Vector paintedElements = new Vector();
		NetzplanElementSwingGenerator npGen[] = new NetzplanElementSwingGenerator[npElemente.size()];
		int a = 0;
		while (a < npGen.length) {
			npGen[a] = new NetzplanElementSwingGenerator();
			a++;
		}

		int i = 0;
		int freeWidth = 0;

		JPanel npElemPanel;

		for (int b = 0; b < 20; b++) {
			for (int c = 0; c < 20; c++) {
				if (position[b][c] != 0) {
					NetzplanElement np = (NetzplanElement) npElemente.get(position[b][c] - 1);
					if (!paintedElements.contains(new Integer(np.getNummer()))) {

						npElemPanel = npGen[i].generateNetzplanelement(np);

						/** Center the element */
						freeWidth = Math.round((400 - npGen[i].getWidth()) / 2);

						jPanelNetworkplanContainer.add(npElemPanel);
						npElemPanel.setBounds(30 + b * 430 + freeWidth, 30 + c * 280, 360, 280);

						int nachfolger[] = np.getNachfolger();

						/** Horizontal Connection Line connecting the childs */
						if (nachfolger.length > 1) {
							JSeparator jSeparatorHorizontalCon = new JSeparator();
							jSeparatorHorizontalCon.setOrientation(SwingConstants.HORIZONTAL);
							jSeparatorHorizontalCon.setBounds(230 + b * 430, 310 + c * 280, 430 + (nachfolger.length - 2) * 430, 1);
							jSeparatorHorizontalCon.setForeground(Color.BLACK);

							if (np.isCriticalPath())
								jSeparatorHorizontalCon.setForeground(Color.RED);

							jPanelNetworkplanContainer.add(jSeparatorHorizontalCon);
						}

						int vorgaenger[] = np.getVorgaenger();

						/** Horizontal Connection Line connecting the parents */
						if (vorgaenger.length > 1) {
							JSeparator jSeparatorHorizontalCon2 = new JSeparator();
							jSeparatorHorizontalCon2.setOrientation(SwingConstants.HORIZONTAL);
							jSeparatorHorizontalCon2.setBounds(230 + b * 430, 30 + c * 280, 430 + (vorgaenger.length - 2) * 430, 1);
							jSeparatorHorizontalCon2.setForeground(Color.BLACK);

							if (np.isCriticalPath())
								jSeparatorHorizontalCon2.setForeground(Color.RED);

							jPanelNetworkplanContainer.add(jSeparatorHorizontalCon2);
						}

						i++;
					}

					/** Vertical long Connection Line */
					else {
						JSeparator jSeparatorVerticalCon = new JSeparator();
						jSeparatorVerticalCon.setOrientation(SwingConstants.VERTICAL);
						jSeparatorVerticalCon.setBounds(230 + b * 430, 30 + c * 280, 1, 280);
						jSeparatorVerticalCon.setForeground(Color.BLACK);

						if (np.isCriticalPath())
							jSeparatorVerticalCon.setForeground(Color.RED);

						jPanelNetworkplanContainer.add(jSeparatorVerticalCon);
					}

					paintedElements.add(new Integer(np.getNummer()));
				}
			}
		}
	}

	/**
	 * Calculate the positions of the elements in the matrix position[x][y]
	 * position is a coordinate system with x and y axis. Each x/y-coordinate
	 * can contain a number of a network plan element
	 *
	 * German pseudo-code:
	 * 1.) Variablen
	 *
	 *	tupel[]: Array mit Vektoren die jeweils die Nummer von Vorgängen einer Zeile halten
	 *	completedAll: Vektor der die Nummer eines abgearbeiteten Vorganges speichert
	 *	completed: Vektor der die Nummer eines aktuell abgearbeiteten Vorganges speichert
	 *
	 *	nachfolgerBasket: Vektor mit Nummern von Vorgängen
	 *	nachfolger[]: Nummern der Vorgänge die einem Vorgang folgen
	 *
	 *
	 *	2.) Initialisierung
	 *
	 *	tupel[0] Vektor erhält die Nummer des ersten Vorganges
	 *	completedAll erhält die Nummer des ersten Vorganges
	 *	nachfolger[] des Startelementes werden geholt
	 *	nachfolgerBasket wird mit nachfolger[]-Nummern gefüllt
	 *
	 *
	 *	3.)
	 *	Wiederhole solange != Letztes Netzplanelement, i++
	 *
	 *		Zurücksetzen der Vektoren:
	 *			tupel[i] = new Vector();
	 *
	 *		Nachfolger bearbeiten:
	 *
	 *			Für jedes Element im nachfolgerBasket
	 *
	 *				Ermittle die Vorgänger
	 *
	 *				Wenn es mehr als einen Vorgänger für ein Vorgang gibt, dann:
	 *					Überprüfe ob jeder Vorgänger im Vektor completedAll liegt
	 *
	 *				Wenn alle Vorgänger in completedAll liegen und der aktuelle Vorgang
	 *				nicht in completed liegt, dann
	 *
	 *					Füge aktuelle Vorgangsnummer in Vektor(a) bei tupel[i]
	 *					Füge aktuelle Vorgangsnummer in Vektor completed
	 *
	 *				sonst
	 *
	 *					Füge in tupel[i] die Nummer des Vorganges, die im vorhergehenden
	 *					Tupel an dieser Stelle vorhanden war (a ist Breite des aktuellen Tupels)
	 *
	 *				Inkrementiere (a)
	 *
	 *		Füge Inhalt aus Vektor completed in Vektor completedAll
	 *
	 *		nachfolgerBasket = new Vector();
	 *
	 *		Für jedes Element im aktuellen Tupel werden die  Nummern der Nachfolger in
	 *		den nachfolgerBasket geschrieben
	 * */
	public void calculatePositions() {

		//		Vector tupel[] = new Vector[100];
		Vector nachfolgerBasket = new Vector();
		Vector completed = new Vector();
		Vector completedAll = new Vector();

		/** START-Element is stored in  Vector Tupel */
		tupel[0] = new Vector();
		tupel[0].add(new Integer(((NetzplanElement) npElemente.get(0)).getNummer()));
		completedAll.add(new Integer(((NetzplanElement) npElemente.get(0)).getNummer()));

		/** Followers of the START-Element */
		int nachfolger[] = ((NetzplanElement) npElemente.get(0)).getNachfolger();

		/** All followers of the START-Element are stored in a Vector */
		int y = 0;
		while (y < nachfolger.length) {
			nachfolgerBasket.add(new Integer(nachfolger[y]));
			y++;
		}

		int i = 1;
		boolean lastElement = false;
		while (!lastElement) {

			int a = 0;

			/** Alle activitys of the followers-Vector are stored in the current tupel[] */
			tupel[i] = new Vector();

			/** Get the parent activitiys of the followers */
			while (a < nachfolgerBasket.size()) {
				int[] vorgaenger = ((NetzplanElement) npElemente.get(((Integer) nachfolgerBasket.get(a)).intValue() - 1)).getVorgaenger();
				boolean complete = true;

				/** Check if all parent activites were already stored in the tupel */
				if (vorgaenger.length > 1) {

					int h = 0;
					while (h < vorgaenger.length) {
						NetzplanElement checkCompleted = (NetzplanElement) npElemente.get(vorgaenger[h] - 1);
						h++;
						if (!completedAll.contains(new Integer(checkCompleted.getNummer()))) {
							complete = false;
							break;
						}
					}
				}

				/** Parents where already stored in the tupel */
				if (complete && !completed.contains((Integer) nachfolgerBasket.get(a))) {
					tupel[i].add((Integer) nachfolgerBasket.get(a));
					completed.add(nachfolgerBasket.get(a));

					/** Parents are not yet stored in the tupel */
				} else if (!complete) {
					tupel[i].add(tupel[i - 1].get(a));
				}
				a++;
			}
			completedAll.addAll(completed);
			nachfolgerBasket = new Vector();
			int b = 0;

			/** Get all followers of the current tupel[]-network elements */
			while (b < tupel[i].size()) {
				nachfolger = ((NetzplanElement) npElemente.get(((Integer) tupel[i].get(b)).intValue() - 1)).getNachfolger();
				int c = 0;
				while (c < nachfolger.length) {
					if (nachfolger[c] != 0) {
						nachfolgerBasket.add(new Integer(nachfolger[c]));
					} else {
						lastElement = true;
					}
					c++;
				}
				b++;
			}
			i++;
		}

		/** START Debug output */
				int blub = 0;
				while (blub < i) {
		
					String string = "";
					Vector test = (Vector) tupel[blub];
					Iterator testIt = test.iterator();
					while (testIt.hasNext()) {
						int tempInt = ((Integer) testIt.next()).intValue();
						if (tempInt != 0) {
							NetzplanElement npEle = (NetzplanElement) npElemente.get(tempInt - 1);
							string = string + " " + (npEle.getNummer());
						} else {
							string = string + " 0";
						}
					}
					System.out.println("Tupel[" + blub + "]: " + string);
					blub++;
				}
		
				Iterator completeIt = completed.iterator();
				String completedList = "";
				while (completeIt.hasNext())
					completedList = completedList + " " + String.valueOf(((Integer) completeIt.next()).intValue());
				System.out.println("CompletedList: " + completedList);
		/** END Debug output */

		/** Set the positions */
		int j = 0;
		while (tupel[j] != null) {
			Vector actTupel = tupel[j];
			Iterator actTupelIt = actTupel.iterator();
			int k = 0;
			while (actTupelIt.hasNext()) {
				int actInt = ((Integer) actTupelIt.next()).intValue();
				if (actInt > 0) {
					NetzplanElement actNpElem = (NetzplanElement) npElemente.get(actInt - 1);
					//if (actTupel.size() < maxWidth - 1)
					//position[k + (int)Math.round((maxWidth/2)) - 1][j] = actNpElem.getNummer();
					//else
					position[k][j] = actNpElem.getNummer();
				}
				k++;
			}
			j++;
		}
	}

	/**
	 * @return the network plan as image
	 */
	public JPanel getNetzplanGraphic() {
		return jPanelNetworkplanContainer;
	}

	/** Display the critical path */
	public void showCriticalPath() {
		Vector criticalPath = npCalc.getCriticalPath();
		Iterator criticalPathIt = criticalPath.iterator();
		while (criticalPathIt.hasNext()) {
			((NetzplanElement) npElemente.get(((Integer) criticalPathIt.next()).intValue() - 1)).setCriticalPath(true);
		}
	}

	/**
	 * Returns the positions-matrix
	 * @return
	 */
	public int[][] getPosition() {
		return position;
	}
	/**
	 * [DoItBen] Kommentar für getMaxHeightPos()
	 * @return
	 */
	public int getMaxHeightPos() {
		return maxHeightPos;
	}

	/**
	 * [DoItBen] Kommentar für getMaxWidth()
	 * @return
	 */
	public int getMaxWidthPos() {
		return maxWidthPos;
	}

	/**
	 * [DoItBen] Kommentar für getMostWidthRow()
	 * @return
	 */
	public int getMostWidthRow() {

		int size = 0;
		int tupelPos = 0;

		for (int b = 0; b < tupel.length; b++) {
			if (tupel[b] != null) {
				if (size < tupel[b].size()) {
					tupelPos = b;
					size = tupel[b].size();
				}
			} else {
				break;
			}			
		}
		return tupelPos + 1;
	}
}