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
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

/**
 * Class for generating the networkplan element on a JPanel
 * @author benjamin.pasero
 * @version 0.5a
 */
public class NetzplanSwingGenerator extends JPanel implements MouseListener, MouseMotionListener {

	private Vector npElemente;
	private NetzplanCalculator npCalc;
	private int[][] position;
	private Vector tupel[];

	private int maxWidthPos;
	private int maxHeightPos;

	private final int maxPosX;
	private final int maxPosY;

	private JPanel jPanelNetworkplanContainer;

	private int x1 = 0, y1 = 0;
	private boolean dragging;
	private int offsetX, offsetY;
	private String movedElementName = "";
	private Component movedElement;

	/**
	 * Initialize the position-Matrix and tupel-Array for each row.
	 * Add each networkplan element on a JPanel. 
	 * @param npElemente
	 */
	public NetzplanSwingGenerator(Vector npElemente) {

		addMouseListener(this);
		addMouseMotionListener(this);

		/** Guess the x and y spread of the network plan */
		maxPosX = npElemente.size();
		maxPosY = npElemente.size();
		tupel = new Vector[maxPosY + 1];

		/** 
		 * Matrix for positioning of the elements 
		 */
		position = new int[maxPosX][maxPosY];
		for (int a = 0; a < maxPosX; a++)
			for (int b = 0; b < maxPosY; b++)
				position[a][b] = 0;

		this.npElemente = npElemente;
		npCalc = new NetzplanCalculator(npElemente);
		npElemente = npCalc.getNpElemente();

		/** [DoItBen] Korrekte Anzeige des kritischen Pfades */
		//showCriticalPath(); <- Some Bugs. Fixes in future versions!

		calculatePositions();

		/** Max. size of networkplan: Width */
		maxWidthPos = 0;
		for (int a = 0; a < maxPosX; a++) {
			for (int b = 0; b < maxPosY; b++) {
				if (position[a][b] != 0) {
					maxWidthPos = a + 1;
				}
			}
		}

		/** Max. size of networkplan: Height */
		maxHeightPos = 0;
		for (int a = 0; a < maxPosY; a++) {
			for (int b = 0; b < maxPosX; b++) {
				if (position[b][a] != 0) {
					maxHeightPos = a + 1;
				}
			}
		}

		jPanelNetworkplanContainer = new JPanel();
		//jPanelNetworkplanContainer.setBounds(30, 30, 430 * maxWidthPos, 260 * maxHeightPos);
		jPanelNetworkplanContainer.setLayout(null);
		jPanelNetworkplanContainer.setAutoscrolls(true);

		paintSwingElmements();
	}

	/** Sets the swing elements and builds the network plan */
	public void paintSwingElmements() {

		boolean onlyOneElement = false;

		/** Count the elements of the tupel */
		int a = 0;
		int count = 0;
		while (a < tupel.length) {
			if (tupel[a] != null)
				count += tupel[a].size();
			else
				break;
			a++;
		}

		/** Array with networkplan elements swing generators */
		NetzplanElementSwingGenerator npGen[] = new NetzplanElementSwingGenerator[count];
		a = 0;
		while (a < npGen.length) {
			npGen[a] = new NetzplanElementSwingGenerator();
			a++;
		}

		int i = 0;
		int freeWidth = 0;

		/** JPanel holding the networkplan element */
		JPanel npElemPanel;

		/*********************************************************
		 *  Paint the middle of the tree with the most elements  *
		 *********************************************************/

		int middlePos = getMostWidthRow() - 1;
		int middlePosElements = tupel[middlePos].size();

		/** Width of the middle */
		int stomachSize = 430 * middlePosElements;

		Vector middleElements = tupel[middlePos];

		Iterator middleElementsIt = middleElements.iterator();

		int x = 0;

		while (middleElementsIt.hasNext()) {

			int currentElementNumber = ((Integer) middleElementsIt.next()).intValue();
			int checkIfLine = currentElementNumber;

			if (currentElementNumber < 0)
				currentElementNumber *= -1;

			NetzplanElement np = (NetzplanElement) npElemente.get(currentElementNumber - 1);

			int child[] = np.getChild();
			if (child[0] == 0)
				onlyOneElement = true;

			/** This element is not a vertical connection line */
			if (checkIfLine > 0) {
				npElemPanel = npGen[i].generateNetzplanelement(np);
				npElemPanel.setName(String.valueOf(np.getIndex()));
				this.add(npElemPanel);
				npElemPanel.setBounds(30 + x * 430, 30 + middlePos * 280, 360, 280);
			}

			/** Save the position of this element (Set the anchor points) */
			np.setAnchorTopXPos(30 + x * 430 + 180);
			np.setAnchorTopYPos(30 + middlePos * 280);
			np.setAnchorBottomXPos(30 + x * 430 + 180);
			np.setAnchorBottomYPos(30 + middlePos * 280 + 280);

			x++;
			i++;
		}

		/******************************************
		 *  Paint the elements top of the middle  *
		 ******************************************/

		int topPos = middlePos - 1;

		while (topPos >= 0 && !onlyOneElement) {

			Vector topElements = tupel[topPos];

			/** Calculate the count of the childs */
			int childCount = 0;
			Iterator topElementsIt = topElements.iterator();
			while (topElementsIt.hasNext()) {
				int currentElementNumber = ((Integer) topElementsIt.next()).intValue();

				if (currentElementNumber < 0)
					currentElementNumber *= -1;

				NetzplanElement np = (NetzplanElement) npElemente.get(currentElementNumber - 1);
				childCount += np.getChild().length;
			}

			int topElementsCount = tupel[topPos].size();
			int freeSize = stomachSize - (topElementsCount - 1) * 430;

			topElementsIt = topElements.iterator();

			int y = 0;
			int occupied = 0;

			while (topElementsIt.hasNext()) {

				int currentElementNumber = ((Integer) topElementsIt.next()).intValue();
				int checkIfLine = currentElementNumber;

				if (currentElementNumber < 0)
					currentElementNumber *= -1;

				NetzplanElement np = (NetzplanElement) npElemente.get(currentElementNumber - 1);

				int child[] = np.getChild();
				double relFreeSize = ((double) child.length / (double) childCount) * stomachSize; //korrekt

				/** Center the element */
				freeWidth = (int) relFreeSize / 2 - 215 + occupied;

				occupied = freeWidth * 2;

				/** This element is not a vertical connection line */
				if (checkIfLine > 0) {
					npElemPanel = npGen[i].generateNetzplanelement(np);
					npElemPanel.setName(String.valueOf(np.getIndex()));
					this.add(npElemPanel);
					npElemPanel.setBounds(30 + y * 430 + freeWidth, 30 + topPos * 280, 360, 280);
				}

				/** Save the position of this element (Set the anchor points) */
				np.setAnchorTopXPos(30 + y * 430 + freeWidth + 180);
				np.setAnchorTopYPos(30 + topPos * 280);
				np.setAnchorBottomXPos(30 + y * 430 + freeWidth + 180);
				np.setAnchorBottomYPos(30 + topPos * 280 + 280);

				y++;
				i++;
			}

			topPos--;
		}

		/****************************************************
		 *  Paint the elements in the bottom of the middle  *
		 ****************************************************/

		int bottomPos = middlePos + 1;

		while (tupel[bottomPos] != null && !onlyOneElement) {

			int y = 0;
			Vector bottomElements = tupel[bottomPos];

			int bottomElementsCount = tupel[bottomPos].size();
			int freeSize = stomachSize - (bottomElementsCount - 1) * 430;

			int occupied = 0;

			Iterator bottomElementsIt = bottomElements.iterator();
			while (bottomElementsIt.hasNext()) {

				int currentElementNumber = ((Integer) bottomElementsIt.next()).intValue();
				int checkIfLine = currentElementNumber;

				if (currentElementNumber < 0)
					currentElementNumber *= -1;

				NetzplanElement np = (NetzplanElement) npElemente.get(currentElementNumber - 1);

				Hashtable childWidth = getRelWidthOfChilds(bottomPos);
				double freeWidthProzent = ((Double) childWidth.get(new Integer(np.getIndex()))).doubleValue();
				freeWidth = (int) ((double) stomachSize / (double) 100 * freeWidthProzent) / 2 - 215 + occupied;

				occupied = freeWidth * 2;

				if (freeWidth < 0)
					freeWidth = 0;

				/** This element is not a vertical connection line */
				if (checkIfLine > 0) {
					npElemPanel = npGen[i].generateNetzplanelement(np);

					npElemPanel.setName(String.valueOf(np.getIndex()));
					this.add(npElemPanel);
					npElemPanel.setBounds(30 + y * 430 + freeWidth, 30 + bottomPos * 280, 360, 280);
				}

				/** Save the position of this element (Set the anchor points) */
				np.setAnchorTopXPos(30 + y * 430 + freeWidth + 180);
				np.setAnchorTopYPos(30 + bottomPos * 280);
				np.setAnchorBottomXPos(30 + y * 430 + freeWidth + 180);
				np.setAnchorBottomYPos(30 + bottomPos * 280 + 280);

				y++;
				i++;
			}
			bottomPos++;
		}

		/** Paint the horizontal and vertical connection lines */
		Enumeration npElemIt = npElemente.elements();
		while (npElemIt.hasMoreElements()) {
			NetzplanElement np = (NetzplanElement) npElemIt.nextElement();

			/** Horizontal Connection Line connecting the childs */
			int child[] = np.getChild();
			if (child.length > 1) {

				int r = 1;
				int width = 0;
				int xPosStart = (int) ((NetzplanElement) npElemente.get(child[0] - 1)).getAnchorTopXPos();
				int yPosStart = (int) ((NetzplanElement) npElemente.get(child[0] - 1)).getAnchorTopYPos();

				int xPosLength =
					(int) ((NetzplanElement) npElemente.get(child[child.length - 1] - 1)).getAnchorTopXPos() - xPosStart;

				JSeparator jSeparatorHorizontalCon = new JSeparator();
				jSeparatorHorizontalCon.setOrientation(SwingConstants.HORIZONTAL);
				jSeparatorHorizontalCon.setBounds(xPosStart, yPosStart, xPosLength, 1);
				jSeparatorHorizontalCon.setForeground(Color.BLACK);

				if (np.isCriticalPath())
					jSeparatorHorizontalCon.setForeground(Color.RED);

				this.add(jSeparatorHorizontalCon);
			}

			/** Horizontal Connection Line connecting the parents */
			int parent[] = np.getParent();
			if (parent.length > 1) {

				int r = 1;
				int width = 0;
				int xPosStartMin = (int) ((NetzplanElement) npElemente.get(parent[0] - 1)).getAnchorBottomXPos();
				int xPosStartMax = 0;

				int yPosStart = 0;

				int u = 0;

				/** 
				 * Get the max. Width and min. Width because the getParent-Function gives
				 * back an unsorted list of elements!! Perhaps i'll fix this in future versions
				 */
				while (u < parent.length) {
					if ((int) ((NetzplanElement) npElemente.get(parent[u] - 1)).getAnchorBottomXPos() < xPosStartMin)
						xPosStartMin = (int) ((NetzplanElement) npElemente.get(parent[u] - 1)).getAnchorBottomXPos();
					if ((int) ((NetzplanElement) npElemente.get(parent[u] - 1)).getAnchorBottomXPos() > xPosStartMax)
						xPosStartMax = (int) ((NetzplanElement) npElemente.get(parent[u] - 1)).getAnchorBottomXPos();

					if (yPosStart < (int) ((NetzplanElement) npElemente.get(parent[u] - 1)).getAnchorBottomYPos())
						yPosStart = (int) ((NetzplanElement) npElemente.get(parent[u] - 1)).getAnchorBottomYPos();
					u++;
				}

				int xPosLength = xPosStartMax - xPosStartMin;

				JSeparator jSeparatorHorizontalCon = new JSeparator();
				jSeparatorHorizontalCon.setOrientation(SwingConstants.HORIZONTAL);
				jSeparatorHorizontalCon.setBounds(xPosStartMin, yPosStart, xPosLength, 1);

				jSeparatorHorizontalCon.setForeground(Color.BLACK);

				this.add(jSeparatorHorizontalCon);
			}
		}

		/** Paint the vertical connection lines. */
		int z = 0;
		while (tupel[z] != null && !onlyOneElement) {
			Vector temp = tupel[z];

			int g = 0;
			while (g < temp.size()) {

				if (((Integer) temp.get(g)).intValue() < 0) {
					int currentNumber = ((Integer) temp.get(g)).intValue();

					NetzplanElement np = (NetzplanElement) npElemente.get(currentNumber * (-1) - 1);

					JSeparator jSeparatorVerticalCon = new JSeparator();
					jSeparatorVerticalCon.setOrientation(SwingConstants.VERTICAL);
					jSeparatorVerticalCon.setBounds((int) np.getAnchorTopXPos(), 30 + z * 280, 1, 280);
					jSeparatorVerticalCon.setForeground(Color.BLACK);

					if (np.isCriticalPath())
						jSeparatorVerticalCon.setForeground(Color.RED);

					this.add(jSeparatorVerticalCon);
				}
				g++;
			}
			z++;
		}
	}

	/**
	 * Calculate the positions of the elements in the matrix position[x][y]
	 * position is a coordinate system with x and y axis. Each x/y-coordinate
	 * can contain a number of a network plan element. This is used to get
	 * the row of the networkplan with the biggest width.
	 */
	public void calculatePositions() {

		/** START-Element is stored in  Vector Tupel */
		tupel[0] = new Vector();
		tupel[0].add(new Integer(((NetzplanElement) npElemente.get(0)).getIndex()));

		/** Followers of the START-Element */
		int child[] = ((NetzplanElement) npElemente.get(0)).getChild();

		/** All followers of the START-Element are stored in tupel[1] */
		int y = 0;
		tupel[1] = new Vector();
		while (y < child.length) {
			tupel[1].add(new Integer(child[y]));
			y++;
		}

		int i = 1;
		boolean lastElement = false;

		if (child[0] == 0)
			lastElement = true;

		while (!lastElement) {

			/** Get the followers of the current tupel (row) */
			int a = 0;

			tupel[i + 1] = new Vector();

			/** Foreach networkplan element of this tupel */
			while (a < tupel[i].size()) {

				int currentElem = ((Integer) tupel[i].get(a)).intValue();

				if (currentElem < 0) {
					currentElem *= -1;
				}

				child = ((NetzplanElement) npElemente.get(currentElem - 1)).getChild();

				/** Last tupel reached */
				if (child[0] == 0) {
					lastElement = true;
					break;
				}

				/** Foreach child-element */
				int b = 0;
				while (b < child.length) {

					int parent[] = ((NetzplanElement) npElemente.get(child[b] - 1)).getParent();

					/** Add this element because the parent exists */
					if (parent.length == 1) {
						tupel[i + 1].add(new Integer(child[b]));
					}

					/** The child-element has more than one parents */
					else {
						int c = 0;
						boolean parentsExists = true;
						while (c < parent.length) {

							/** The current tupel (row) does not contain the parents */
							if (!tupel[i].contains(new Integer(parent[c]))
								&& !tupel[i].contains(new Integer(parent[c] * (-1)))) {
								parentsExists = false;
								break;
							}
							c++;
						}

						/** Add this child because all parents exist */
						if (parentsExists) {

							/** Only add if its not yet in! */
							if (!tupel[i + 1].contains(new Integer(child[b])))
								tupel[i + 1].add(new Integer(child[b]));

							/** 
							 * Add the negative value of the parent again, because some parents for 
							 * this child dont exist 
							 */
						} else {
							tupel[i + 1].add(new Integer(((NetzplanElement) npElemente.get(currentElem - 1)).getIndex() * (-1)));
						}
					}
					b++;
				}
				a++;
			}
			i++;
		}

		/** Set the positions */
		int j = 0;
		while (j < i) {
			Vector actTupel = tupel[j];
			Iterator actTupelIt = actTupel.iterator();
			int k = 0;
			while (actTupelIt.hasNext()) {
				int actInt = ((Integer) actTupelIt.next()).intValue();
				if (actInt < 0)
					actInt *= -1;

				if (actInt > 0) {
					NetzplanElement actNpElem = (NetzplanElement) npElemente.get(actInt - 1);
					position[k][j] = actNpElem.getIndex();
				}
				k++;
			}
			j++;
		}

		/** START Debug output */
		int e = 0;
		while (e < i) {

			String string = "";
			Vector test = (Vector) tupel[e];
			Iterator testIt = test.iterator();
			while (testIt.hasNext()) {
				int tempInt = ((Integer) testIt.next()).intValue();
				int outInt = tempInt;
				if (tempInt < 0)
					tempInt *= -1;

				if (tempInt != 0) {
					NetzplanElement npEle = (NetzplanElement) npElemente.get(tempInt - 1);
					string = string + " " + (outInt);
				} else {
					string = string + " 0";
				}
			}
			System.out.println("Tupel[" + e + "]: " + string);
			e++;
		}
		/** END Debug output */

	}

	/**
	 * @return the network plan
	 */
	public JPanel getNetzplanGraphic() {
		return this;
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
	 * @return maxHeightPos
	 */
	public int getMaxHeightPos() {
		return maxHeightPos;
	}

	/**
	 * @return maxWidthPos
	 */
	public int getMaxWidthPos() {
		return maxWidthPos;
	}

	/**
	 * @return row with the most width
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

	/**
	 * Calculates the width that each child-Element has because of its parents
	 * @param tupelNr the row number
	 * @return childWidth
	 */
	public Hashtable getRelWidthOfChilds(int tupelNr) {

		Vector childs = tupel[tupelNr];
		Vector parents = new Vector();
		Hashtable childWidth = new Hashtable();
		int countParents;

		if (tupel[tupelNr].size() == 1) {
			childWidth.put(tupel[tupelNr].get(0), new Double(100));
			return childWidth;
		}

		int a = 0;

		/** Count the number of parents for the child elements */
		while (a < childs.size()) {

			int currentPos = ((Integer) childs.get(a)).intValue();

			int tempPos = currentPos;

			if (currentPos < 0)
				currentPos *= -1;

			if (tempPos > 0) {
				int parent[] = ((NetzplanElement) npElemente.get(currentPos - 1)).getParent();
				int b = 0;
				while (b < parent.length) {
					if (!parents.contains(new Integer(parent[b])))
						parents.add(new Integer(parent[b]));
					b++;
				}
			} else {
				if (!parents.contains(new Integer((tempPos))))
					parents.add(new Integer(tempPos));
			}

			a++;
		}

		countParents = parents.size();

		/** Get the width each childgroup of a parent */
		double widthForChildGroup = 100 / countParents;

		int c = 0;

		/** Get the width each child of the parent get */
		while (c < parents.size()) {

			int currentPos = ((Integer) parents.get(c)).intValue();

			int tempPos = currentPos;

			if (currentPos < 0)
				currentPos *= -1;

			if (tempPos > 0) {
				int child[] = ((NetzplanElement) npElemente.get(currentPos - 1)).getChild();

				int countChilds = child.length;

				double widthForChild = (double) widthForChildGroup / (double) countChilds;

				if (countChilds == 1)
					widthForChild = widthForChildGroup;

				int d = 0;
				while (d < child.length) {
					if (childWidth.containsKey(new Integer(child[d]))) {
						double temp = ((Double) childWidth.get(new Integer(child[d]))).doubleValue();
						childWidth.put(new Integer(child[d]), new Double(widthForChild + temp));
					} else {
						childWidth.put(new Integer(child[d]), new Double(widthForChild));
					}
					d++;
				}
			} else {
				double widthForChild = widthForChildGroup;
				childWidth.put(new Integer(tempPos * (-1)), new Double(widthForChild));
			}

			c++;
		}
		return childWidth;
	}

	public void mousePressed(MouseEvent evt) {
		if (dragging)
			return;

		int x = evt.getX();
		int y = evt.getY();

		if (getComponentAt(x, y).getName() != null) {
			if (x1 == 0 && y1 == 0) {
				x1 = (int) getComponentAt(x, y).getLocation().getX();
				y1 = (int) getComponentAt(x, y).getLocation().getY();
			}

			if (movedElementName.equals("")) {
				movedElementName = getComponentAt(x, y).getName();
				movedElement = getComponentAt(x, y);
			}

			dragging = true;
			offsetX = x - x1;
			offsetY = y - y1;
		}
	}

	public void mouseReleased(MouseEvent evt) {
		movedElementName = "";
		movedElement = null;
		dragging = false;
		x1 = 0;
		y1 = 0;
	}

	public void mouseDragged(MouseEvent evt) {
		if (dragging == false || evt.getX() < 0 || evt.getY() < 0)
			return;

		int x = evt.getX();
		int y = evt.getY();
		x1 = x - offsetX;
		y1 = y - offsetY;

		if (getComponentAt(x, y).getName() != null)
			movedElement.setLocation(x1, y1);
	}

	public void mouseMoved(MouseEvent evt) {
	}

	public void mouseClicked(MouseEvent evt) {
	}

	public void mouseEntered(MouseEvent evt) {
		/** Blue highlight */
	}
	public void mouseExited(MouseEvent evt) {
		/** Back to normal color */
	}

	private void exitForm(WindowEvent evt) {
		System.exit(0);
	}
}