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
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

/**
 * JNetworkplan extends JPanel and represents the networkplan
 * @author benjamin.pasero
 * @version 0.6a
 */
public class JNetworkplan extends JPanel implements MouseListener, MouseMotionListener {

	private Vector npElemente;
	private NetworkplanCalculator npCalc;
	private int[][] position;
	private Vector tupel[];

	private int maxWidthPos;
	private int maxHeightPos;

	private final int maxPosX;
	private final int maxPosY;

	private int x1 = 0, y1 = 0;
	private boolean dragging;
	private int offsetX, offsetY;
	private Component movedElement;
	private JNetworkplanElement selectedElement;

	/**
	 * Initialize the position-Matrix and tupel-Array for each row.
	 * Add each networkplan element on this JPanel. 
	 * @param npElemente Vector with all networkplan elements
	 */
	public JNetworkplan(Vector npElemente) {

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
		npCalc = new NetworkplanCalculator(npElemente);
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
		JNetworkplanElement npGen[] = new JNetworkplanElement[count];

		int i = 0;
		int freeWidth = 0;

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

			NetworkplanElement np = (NetworkplanElement) npElemente.get(currentElementNumber - 1);

			int child[] = np.getChild();
			if (child[0] == 0)
				onlyOneElement = true;

			/** This element is not a vertical connection line */
			if (checkIfLine > 0) {
				npGen[i] = new JNetworkplanElement(np);
				np.setLayoutManager(i);
				npGen[i].setName(String.valueOf(np.getIndex()));
				this.add(npGen[i]);
				npGen[i].setBounds(30 + x * 430, 30 + middlePos * 280, 300, 190);
				i++;
			}
			x++;

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

				NetworkplanElement np = (NetworkplanElement) npElemente.get(currentElementNumber - 1);
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

				NetworkplanElement np = (NetworkplanElement) npElemente.get(currentElementNumber - 1);

				int child[] = np.getChild();
				double relFreeSize = ((double) child.length / (double) childCount) * stomachSize; //korrekt

				/** Center the element */
				freeWidth = (int) relFreeSize / 2 - 215 + occupied;

				occupied = freeWidth * 2;

				/** This element is not a vertical connection line */
				if (checkIfLine > 0) {
					npGen[i] = new JNetworkplanElement(np);
					np.setLayoutManager(i);
					npGen[i].setName(String.valueOf(np.getIndex()));
					this.add(npGen[i]);
					npGen[i].setBounds(30 + y * 430 + freeWidth, 30 + topPos * 280, 300, 190);
					i++;
				}
				y++;

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

				NetworkplanElement np = (NetworkplanElement) npElemente.get(currentElementNumber - 1);

				Hashtable childWidth = getRelWidthOfChilds(bottomPos);
				double freeWidthProzent = ((Double) childWidth.get(new Integer(np.getIndex()))).doubleValue();
				freeWidth = (int) ((double) stomachSize / (double) 100 * freeWidthProzent) / 2 - 215 + occupied;

				occupied = freeWidth * 2;

				if (freeWidth < 0)
					freeWidth = 0;

				/** This element is not a vertical connection line */
				if (checkIfLine > 0) {
					npGen[i] = new JNetworkplanElement(np);
					np.setLayoutManager(i);
					npGen[i].setName(String.valueOf(np.getIndex()));
					this.add(npGen[i]);
					npGen[i].setBounds(30 + y * 430 + freeWidth, 30 + bottomPos * 280, 300, 190);
					i++;
				}
				y++;

			}
			bottomPos++;
		}

		/** Paint the horizontal and vertical connection lines */
		a = 0;

		while (a < npGen.length && npGen[a] != null) {

			/** Vertical small connection lines to the element at top and bottom */
			int xPosStart = (int) npGen[a].getAnchorPoint(JNetworkplanElement.ANCHOR_TOP_MIDDLE).getX();

			/** Top Connection */
			if (!npGen[a].getNp().isStartElem()) {
				int yPosStart = (int) npGen[a].getAnchorPoint(JNetworkplanElement.ANCHOR_TOP_MIDDLE).getY() - 65;

				JSeparator jSeparatorVerticalCon = new JSeparator();
				jSeparatorVerticalCon.setOrientation(SwingConstants.VERTICAL);
				jSeparatorVerticalCon.setBounds(xPosStart, yPosStart, 1, 65);
				jSeparatorVerticalCon.setForeground(Color.BLACK);
				this.add(jSeparatorVerticalCon);
			}

			/** Bottom Connection */
			if (!npGen[a].getNp().isEndElem()) {
				int yPosStart = (int) npGen[a].getAnchorPoint(JNetworkplanElement.ANCHOR_BOTTOM_MIDDLE).getY();

				JSeparator jSeparatorVerticalCon = new JSeparator();
				jSeparatorVerticalCon.setOrientation(SwingConstants.VERTICAL);
				jSeparatorVerticalCon.setBounds(xPosStart, yPosStart, 1, 65);
				jSeparatorVerticalCon.setForeground(Color.BLACK);
				this.add(jSeparatorVerticalCon);
			}

			/** Horizontal Connection Line connecting the childs */
			int child[] = npGen[a].getNp().getChild();
			if (child.length > 1) {

				int r = 1;
				int width = 0;
				xPosStart =
					(int) npGen[((NetworkplanElement) npElemente.get(child[0] - 1))
						.getLayoutManager()]
						.getAnchorPoint(JNetworkplanElement.ANCHOR_TOP_MIDDLE)
						.getX();

				int yPosStart =
					(int) npGen[((NetworkplanElement) npElemente.get(child[0] - 1))
						.getLayoutManager()]
						.getAnchorPoint(JNetworkplanElement.ANCHOR_TOP_MIDDLE)
						.getY();

				int xPosLength =
					(int) npGen[((NetworkplanElement) npElemente.get(child[child.length - 1] - 1))
						.getLayoutManager()]
						.getAnchorPoint(JNetworkplanElement.ANCHOR_TOP_MIDDLE)
						.getX()
						- xPosStart;

				JSeparator jSeparatorHorizontalCon = new JSeparator();
				jSeparatorHorizontalCon.setOrientation(SwingConstants.HORIZONTAL);
				jSeparatorHorizontalCon.setBounds(xPosStart, yPosStart - 65, xPosLength, 1);
				jSeparatorHorizontalCon.setForeground(Color.BLACK);

				this.add(jSeparatorHorizontalCon);
			}

			/** Horizontal Connection Line connecting the parents */
			int parent[] = npGen[a].getNp().getParent();
			if (parent.length > 1) {

				int r = 1;
				int width = 0;
				int xPosStartMin =
					(int) npGen[((NetworkplanElement) npElemente.get(parent[0] - 1))
						.getLayoutManager()]
						.getAnchorPoint(JNetworkplanElement.ANCHOR_BOTTOM_MIDDLE)
						.getX();

				int xPosStartMax = 0;
				int yPosStart = 0;
				int u = 0;

				/** 
				 * Get the max. Width and min. Width because the getParent-Function gives
				 * back an unsorted list of elements!!
				 */
				while (u < parent.length) {
					if ((int) npGen[((NetworkplanElement) npElemente.get(parent[u] - 1))
						.getLayoutManager()]
						.getAnchorPoint(JNetworkplanElement.ANCHOR_BOTTOM_MIDDLE)
						.getX()
						< xPosStartMin)
						xPosStartMin =
							(int) npGen[((NetworkplanElement) npElemente.get(parent[u] - 1))
								.getLayoutManager()]
								.getAnchorPoint(JNetworkplanElement.ANCHOR_BOTTOM_MIDDLE)
								.getX();
					if ((int) npGen[((NetworkplanElement) npElemente.get(parent[u] - 1))
						.getLayoutManager()]
						.getAnchorPoint(JNetworkplanElement.ANCHOR_BOTTOM_MIDDLE)
						.getX()
						> xPosStartMax)
						xPosStartMax =
							(int) npGen[((NetworkplanElement) npElemente.get(parent[u] - 1))
								.getLayoutManager()]
								.getAnchorPoint(JNetworkplanElement.ANCHOR_BOTTOM_MIDDLE)
								.getX();

					if (yPosStart
						< (int) npGen[((NetworkplanElement) npElemente.get(parent[u] - 1))
							.getLayoutManager()]
							.getAnchorPoint(JNetworkplanElement.ANCHOR_BOTTOM_MIDDLE)
							.getY())
						yPosStart =
							(int) npGen[((NetworkplanElement) npElemente.get(parent[u] - 1))
								.getLayoutManager()]
								.getAnchorPoint(JNetworkplanElement.ANCHOR_BOTTOM_MIDDLE)
								.getY();
					u++;
				}

				int xPosLength = xPosStartMax - xPosStartMin;

				JSeparator jSeparatorHorizontalCon = new JSeparator();
				jSeparatorHorizontalCon.setOrientation(SwingConstants.HORIZONTAL);
				jSeparatorHorizontalCon.setBounds(xPosStartMin, yPosStart + 65, xPosLength, 1);
				jSeparatorHorizontalCon.setForeground(Color.BLACK);

				this.add(jSeparatorHorizontalCon);
			}
			a++;
		}

		/** Paint the vertical long connection lines if existing. */
		int z = 0;
		while (tupel[z] != null && !onlyOneElement) {
			Vector temp = tupel[z];

			int g = 0;
			while (g < temp.size()) {

				Vector completed = new Vector();

				if (((Integer) temp.get(g)).intValue() < 0) {
					int currentNumber = ((Integer) temp.get(g)).intValue();

					if (!completed.contains(new Integer(currentNumber))) {
						NetworkplanElement np = (NetworkplanElement) npElemente.get(currentNumber * (-1) - 1);
						int child[] = np.getChild();

						int xPosStart =
							(int) npGen[np.getLayoutManager()].getAnchorPoint(JNetworkplanElement.ANCHOR_TOP_MIDDLE).getX();
						int yPosStart =
							(int) npGen[np.getLayoutManager()].getAnchorPoint(JNetworkplanElement.ANCHOR_BOTTOM_MIDDLE).getY()
								+ 65;
						int yPosLength =
							(int) npGen[((NetworkplanElement) npElemente.get(child[0] - 1))
								.getLayoutManager()]
								.getAnchorPoint(JNetworkplanElement.ANCHOR_TOP_MIDDLE)
								.getY()
								- 65
								- yPosStart;

						JSeparator jSeparatorVerticalCon = new JSeparator();
						jSeparatorVerticalCon.setOrientation(SwingConstants.VERTICAL);
						jSeparatorVerticalCon.setBounds(xPosStart, yPosStart, 1, yPosLength);
						jSeparatorVerticalCon.setForeground(Color.BLACK);

						completed.add(new Integer(currentNumber));

						if (np.isCriticalPath())
							jSeparatorVerticalCon.setForeground(Color.RED);

						this.add(jSeparatorVerticalCon);
					}
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
		tupel[0].add(new Integer(((NetworkplanElement) npElemente.get(0)).getIndex()));

		/** Followers of the START-Element */
		int child[] = ((NetworkplanElement) npElemente.get(0)).getChild();

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

				child = ((NetworkplanElement) npElemente.get(currentElem - 1)).getChild();

				/** Last tupel reached */
				if (child[0] == 0) {
					lastElement = true;
					break;
				}

				/** Foreach child-element */
				int b = 0;
				while (b < child.length) {

					int parent[] = ((NetworkplanElement) npElemente.get(child[b] - 1)).getParent();

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
							tupel[i
								+ 1].add(new Integer(((NetworkplanElement) npElemente.get(currentElem - 1)).getIndex() * (-1)));
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
					NetworkplanElement actNpElem = (NetworkplanElement) npElemente.get(actInt - 1);
					position[k][j] = actNpElem.getIndex();
				}
				k++;
			}
			j++;
		}

		/** START Debug output
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
		END Debug output */
	}

	/**
	 * @return the network plan
	 */
	public JNetworkplan getNetzplanGraphic() {
		return this;
	}

	/** Display the critical path */
	public void showCriticalPath() {
		Vector criticalPath = npCalc.getCriticalPath();
		Iterator criticalPathIt = criticalPath.iterator();
		while (criticalPathIt.hasNext()) {
			((NetworkplanElement) npElemente.get(((Integer) criticalPathIt.next()).intValue() - 1)).setCriticalPath(true);
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
				int parent[] = ((NetworkplanElement) npElemente.get(currentPos - 1)).getParent();
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
				int child[] = ((NetworkplanElement) npElemente.get(currentPos - 1)).getChild();

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

	/** Save this component if its a JNetworkplanElement for movement */
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

			if (movedElement == null)
				movedElement = getComponentAt(x, y);

			dragging = true;
			offsetX = x - x1;
			offsetY = y - y1;

			movedElement.setCursor(new Cursor(13));
		}
	}

	/** Forget this JNetworkplanElement */
	public void mouseReleased(MouseEvent evt) {
		movedElement.setCursor(new Cursor(12));
		movedElement = null;
		dragging = false;
		x1 = 0;
		y1 = 0;
	}

	/** Move the saved JNetworkPlan */
	public void mouseDragged(MouseEvent evt) {
		if (dragging == false || evt.getX() < 0 || evt.getY() < 0)
			return;

		int x = evt.getX();
		int y = evt.getY();
		x1 = x - offsetX;
		y1 = y - offsetY;

		if (getComponentAt(x, y).getName() != null) {
			movedElement.setLocation(x1, y1);
		}
	}

	/** Highlight the JNetworkPlan */
	public void mouseMoved(MouseEvent evt) {
		int x = evt.getX();
		int y = evt.getY();
		if (getComponentAt(x, y).getName() != null && selectedElement == null) {
			selectedElement = (JNetworkplanElement) getComponentAt(x, y);
			selectedElement.npElementRectMouseMoved();
			selectedElement.setCursor(new Cursor(12));
		} else if (getComponentAt(x, y).getName() == null && selectedElement != null) {
			selectedElement.npElementRectMouseExited();
			selectedElement = null;
		} else if (getComponentAt(x, y).getName() != null && selectedElement != null) {
			if (!getComponentAt(x, y).getName().equals(selectedElement.getName())) {
				selectedElement.npElementRectMouseExited();
				selectedElement = null;
			}
		}
	}

	public void mouseClicked(MouseEvent evt) {
	}

	public void mouseEntered(MouseEvent evt) {
	}
	public void mouseExited(MouseEvent evt) {
	}

	private void exitForm(WindowEvent evt) {
		System.exit(0);
	}
}