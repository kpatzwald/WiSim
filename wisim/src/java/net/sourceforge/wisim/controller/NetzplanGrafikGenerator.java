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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.Vector;

/**
 * Class for generating the network plan in a Buffered Image. 
 * Size is set to 2000x2000 px. Will be dynamic in future versions. 
 * @author Benjamin Pasero
 * @version 0.1a
 */
public class NetzplanGrafikGenerator {

	private Vector npElemente;
	private NetzplanCalculator npCalc;
	private Graphics g;
	private int[][] position;
	private double maxWidth;
	private BufferedImage netzplanGrafik;

	public NetzplanGrafikGenerator(Vector npElemente) {
		/** Matrix for positioning of the elements */
		position = new int[20][20];
		for (int a = 0; a < 20; a++)
			for (int b = 0; b < 20; b++)
				position[a][b] = 0;

		this.npElemente = npElemente;
		npCalc = new NetzplanCalculator(npElemente);
		npElemente = npCalc.getNpElemente();
		showCriticalPath();
		maxWidth = npCalc.getMaxWidthOfNetzplan();
		calculatePositions();

		/** Max. size of image: Width */
		int maxWidthPos = 0;
		for (int a = 0; a < 20; a++) {
			for (int b = 0; b < 20; b++) {
				if (position[a][b] != 0) {
					maxWidthPos = a + 1;
				}
			}
		}

		/** Max. size of image: Height */
		int maxHeightPos = 0;
		for (int a = 0; a < 20; a++) {
			for (int b = 0; b < 20; b++) {
				if (position[b][a] != 0) {
					maxHeightPos = a + 1;
				}
			}
		}

		netzplanGrafik = new BufferedImage(maxWidthPos * 400, maxHeightPos * 170, 2);
		g = netzplanGrafik.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 2000, 2000);
		g.setColor(Color.BLACK);
		paintGraphic();
	} /** Paints the network plan */
	public void paintGraphic() {
		Vector paintedElements = new Vector();
		NetzplanElementGrafikGenerator npGen[] = new NetzplanElementGrafikGenerator[npElemente.size()];
		int a = 0;
		while (a < npGen.length) {
			npGen[a] = new NetzplanElementGrafikGenerator();
			a++;
		}

		int i = 0;
		for (int b = 0; b < 20; b++) {
			for (int c = 0; c < 20; c++) {
				if (position[b][c] != 0) {
					NetzplanElement np = (NetzplanElement) npElemente.get(position[b][c] - 1);
					if (!paintedElements.contains(new Integer(np.getNummer()))) {
						g.drawImage(npGen[i].generateNetzplanelement(np), 30 + b * 350, 30 + c * 160, null);
						int nachfolger[] = np.getNachfolger();
						if (nachfolger.length > 1) {
							g.drawLine(170 + b * 350, 190 + c * 160, 520 + (nachfolger.length - 2) * 350, 190 + c * 160);
						}

						int vorgaenger[] = np.getVorgaenger();
						if (vorgaenger.length > 1) {
							g.drawLine(
								170 + b * 350,
								190 + (c - 1) * 160,
								520 + (vorgaenger.length - 2) * 350,
								190 + (c - 1) * 160);
						}

						i++;
					} else {
						g.drawLine(170 + b * 350, 10 + c * 160, 170 + b * 350, 190 + c * 160);
					}

					paintedElements.add(new Integer(np.getNummer()));
				}
			}
		}
	} /** 
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

		Vector tupel[] = new Vector[100];
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
			tupel[i] = new Vector(); /** Alle activitys of the followers-Vector are stored in the current tupel[] */
			while (a < nachfolgerBasket.size()) { /** Get the parent activitiys of the followers */
				int[] vorgaenger =
					((NetzplanElement) npElemente.get(((Integer) nachfolgerBasket.get(a)).intValue() - 1)).getVorgaenger();
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
				} /** Parents where already stored in the tupel */
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
			/**
			 * Get all followers of the current tupel[]-network elements
			 */
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
		} /** START Debug output */
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
		/** END Debug output */ /** Set the positions */
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

	public BufferedImage getNetzplanGraphic() {
		return netzplanGrafik;
	}

	public void showCriticalPath() {
		Vector criticalPath = npCalc.getCriticalPath();
		Iterator criticalPathIt = criticalPath.iterator();
		while (criticalPathIt.hasNext()) {
			((NetzplanElement) npElemente.get(((Integer) criticalPathIt.next()).intValue() - 1)).setCriticalPath(true);
		}
	}
}