/*   ********************************************************************   **
**   Copyright notice                                                       **
**                                                                          **
**   (c) 2003 WiSim Development Team                                        **
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

package net.sourceforge.wisim.networkplan;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 * JNetworkplan extends JPanel and represents the networkplan
 * @author benjamin.pasero
 * @version 0.7a
 */
public class JNetworkplan extends JPanel implements MouseListener, MouseMotionListener {

	private Vector npElemente;
	private NetworkplanCalculator npCalc;
	private int[][] position;
	private Vector tupel[];

	/** Variables showing the dimension of the JNetworkplan */
	private int maxWidthPos;
	private int maxHeightPos;
	private final int maxPosX;
	private final int maxPosY;

	/** Variables for movement of an element */
	private int x1, y1;
	private int maxX, maxY;
	private boolean dragging;
	private int offsetX, offsetY;
	private JNetworkplanElement movedElement;
	private JNetworkplanElement selectedElement;

	/** Array for each JNetworkplanElement */
	private JNetworkplanElement jNpElem[];

	/** Hashtable to save the positions of the JNetworkplanElements */
	private Hashtable elementsPosition;

	/** Hashtable for the connection lines */
	private Hashtable elementsConLine;

	/** Variables for the Edit panel */
	private JPanel jPanelEdit;
	private boolean editPanelOpen;
	private JNetworkplanElement clickedComponent;

	/** Bounds of each JNetworkplanElement */
	private final int jNpElemBoundsX = 430;
	private final int jNpElemBoundsY = 280;

	/** Size of each JNetworkplanElement */
	private final int jNpElemWidth = 300;
	private final int jNpElemHeight = 190;

	/** Padding of elements */
	private final int jNPaddingX = 30;
	private final int jNPaddingY = 30;

	/**
	 * Initialize the position-Matrix and tupel-Array for each row.
	 * Add each networkplan element on this JPanel. 
	 * @param npElemente Vector with all networkplan elements
	 */
	public JNetworkplan(Vector npElemente) {

		/** Initializing */
		x1 = 0;
		y1 = 0;
		elementsConLine = new Hashtable();
		elementsPosition = new Hashtable();
		editPanelOpen = false;
		this.npElemente = npElemente;

		/** Listener for user-interaction with the mouse */
		addMouseListener(this);
		addMouseMotionListener(this);

		/** Guess the x and y spread of the network plan */
		maxPosX = npElemente.size();
		maxPosY = npElemente.size();
		tupel = new Vector[maxPosY * 2]; //[DoItBen] Dynamische Tupel Größe!

		/** Matrix for positioning of the elements */
		position = new int[maxPosX][maxPosY];

		/** Calculate the networkplan elements */
		npCalc = new NetworkplanCalculator(npElemente, false);
		npElemente = npCalc.getNpElemente();

		/** Display the critical path */
		showCriticalPath();

		/** Get positions of each element */
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

		/** Get all JNetworkplanElements on this JNetworkplan */
		paintJNetworkplanElements();

		/** Get the max width and hight in px */
		for (int a = 0; a < jNpElem.length; a++) {
			if ((jNpElem[a].getLocation().getX() + jNpElem[a].getSize().getWidth()) > maxX)
				maxX = (int) jNpElem[a].getLocation().getX() + (int) jNpElem[a].getSize().getWidth();

			if ((jNpElem[a].getLocation().getY() + jNpElem[a].getSize().getHeight()) > maxY)
				maxY = (int) jNpElem[a].getLocation().getY() + (int) jNpElem[a].getSize().getHeight();
		}
	}

	/** Default constructor */
	public JNetworkplan() {
		maxPosX = 0;
		maxPosY = 0;
	}

	/** Sets the swing elements and builds the network plan */
	public void paintJNetworkplanElements() {

		/** TRUE if the networkplan has only one element */
		boolean onlyOneElement = false;

		int child[] = ((NetworkplanElement) npElemente.get(0)).getChild();

		/** This JNetworkplan has only one element */
		if (child[0] == 0)
			onlyOneElement = true;

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

		/** Array with JNetworkplanElements */
		jNpElem = new JNetworkplanElement[count];

		int i = 0;
		int freeWidth = 0;

		/*********************************************************
		 *  Paint the middle of the tree with the most elements  *
		 *********************************************************/

		/** Get the row thats the middle of the networkplan */
		int middlePos = getMostWidthRow() - 1;
		int middlePosElements = tupel[middlePos].size();

		/** Width of the middle */
		int stomachSize = jNpElemBoundsX * middlePosElements;

		Vector middleElements = tupel[middlePos];

		Iterator middleElementsIt = middleElements.iterator();

		int x = 0;

		while (middleElementsIt.hasNext()) {

			int currentElementNumber = ((Integer) middleElementsIt.next()).intValue();

			/** If this element is a long vertical connection line checkIfLine is negative */
			int checkIfLine = currentElementNumber;

			if (currentElementNumber < 0)
				currentElementNumber *= -1;

			NetworkplanElement np = (NetworkplanElement) npElemente.get(currentElementNumber - 1);

			/** This element is not a vertical connection line */
			if (checkIfLine > 0) {
				jNpElem[i] = new JNetworkplanElement(np);
				np.setLayoutManager(i);
				jNpElem[i].setName(String.valueOf(np.getIndex()));

				if (jNpElem[i].getNp().isCriticalPath())
					this.add(jNpElem[i], 0);
				else
					this.add(jNpElem[i]);

				/** Place the element on the JNetworkplan and save the position */
				jNpElem[i].setBounds(jNPaddingX + x * jNpElemBoundsX, jNPaddingY + middlePos * jNpElemBoundsY, jNpElemWidth, jNpElemHeight);
				elementsPosition.put(new Integer(np.getIndex()), new Point(jNPaddingX + x * jNpElemBoundsX, jNPaddingY + middlePos * jNpElemBoundsY));
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

			topElementsIt = topElements.iterator();

			int y = 0;
			int occupied = 0;

			while (topElementsIt.hasNext()) {

				int currentElementNumber = ((Integer) topElementsIt.next()).intValue();

				/** If this element is a long vertical connection line checkIfLine is negative */
				int checkIfLine = currentElementNumber;

				if (currentElementNumber < 0)
					currentElementNumber *= -1;

				NetworkplanElement np = (NetworkplanElement) npElemente.get(currentElementNumber - 1);

				child = np.getChild();
				double relFreeSize = ((double) child.length / (double) childCount) * stomachSize;

				/** Center the element */
				freeWidth = (int) relFreeSize / 2 - 215 + occupied;

				occupied = freeWidth * 2;

				/** This element is not a vertical connection line */
				if (checkIfLine > 0) {
					jNpElem[i] = new JNetworkplanElement(np);
					np.setLayoutManager(i);
					jNpElem[i].setName(String.valueOf(np.getIndex()));

					if (jNpElem[i].getNp().isCriticalPath())
						this.add(jNpElem[i], 0);
					else
						this.add(jNpElem[i]);

					/** Place the element on the JNetworkplan and save the position */
					jNpElem[i].setBounds(jNPaddingX + y * jNpElemBoundsX + freeWidth, jNPaddingY + topPos * jNpElemBoundsY, jNpElemWidth, jNpElemHeight);
					elementsPosition.put(new Integer(np.getIndex()), new Point(jNPaddingX + y * jNpElemBoundsX + freeWidth, jNPaddingY + topPos * jNpElemBoundsY));
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

			int occupied = 0;

			Iterator bottomElementsIt = bottomElements.iterator();
			while (bottomElementsIt.hasNext()) {

				int currentElementNumber = ((Integer) bottomElementsIt.next()).intValue();

				/** If this element is a long vertical connection line checkIfLine is negative */
				int checkIfLine = currentElementNumber;

				if (currentElementNumber < 0)
					currentElementNumber *= -1;

				NetworkplanElement np = (NetworkplanElement) npElemente.get(currentElementNumber - 1);

				/** This element is not a vertical connection line */
				if (checkIfLine > 0) {

					jNpElem[i] = new JNetworkplanElement(np);
					np.setLayoutManager(i);
					jNpElem[i].setName(String.valueOf(np.getIndex()));

					if (jNpElem[i].getNp().isCriticalPath())
						this.add(jNpElem[i], 0);
					else
						this.add(jNpElem[i]);

					/** Place the element on the JNetworkplan and save the position */

					/** 
					 * Check if child element has only one parent and vice versa. If true the
					 * element's position is right under the position of his parent. This
					 * position is get with the getAnchor()-Method.
					 */
					int parent[] = np.getParent();
					if (parent.length == 1) {
						child = ((NetworkplanElement) npElemente.get(parent[0] - 1)).getChild();
						if (child.length == 1) {
							int anchorX = (int) jNpElem[((NetworkplanElement) npElemente.get(parent[0] - 1)).getLayoutManager()].getAnchorPoint(JNetworkplanElement.ANCHOR_BOTTOM_MIDDLE).getX() - jNpElemWidth / 2;
							int anchorY = (int) jNpElem[((NetworkplanElement) npElemente.get(parent[0] - 1)).getLayoutManager()].getAnchorPoint(JNetworkplanElement.ANCHOR_BOTTOM_MIDDLE).getY() + 110;

							jNpElem[i].setBounds(anchorX, anchorY, jNpElemWidth, jNpElemHeight);
							elementsPosition.put(new Integer(np.getIndex()), new Point(anchorX, anchorY));
						}
					} else {
						Hashtable childWidth = getRelWidthOfChilds(bottomPos);
						double freeWidthProzent = ((Double) childWidth.get(new Integer(np.getIndex()))).doubleValue();
						freeWidth = (int) ((double) stomachSize / (double) 100 * freeWidthProzent) / 2 - 215 + occupied;

						if (freeWidth < 0)
							freeWidth = 0;

						occupied = freeWidth * 2;

						jNpElem[i].setBounds(jNPaddingX + y * jNpElemBoundsX + freeWidth, jNPaddingY + bottomPos * jNpElemBoundsY, jNpElemWidth, jNpElemHeight);
						elementsPosition.put(new Integer(np.getIndex()), new Point(jNPaddingX + y * jNpElemBoundsX + freeWidth, jNPaddingY + bottomPos * jNpElemBoundsY));
					}
					i++;
				}
				y++;
			}
			bottomPos++;
		}

		/** Paint the horizontal and vertical connection lines with JSeperators */
		paintConnectionLines();
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

		/** Check if the Networkplan only exists of one networkplan element */
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
							if (!tupel[i].contains(new Integer(parent[c])) && !tupel[i].contains(new Integer(parent[c] * (-1)))) {
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
							tupel[i + 1].add(new Integer(((NetworkplanElement) npElemente.get(currentElem - 1)).getIndex() * (-1)));
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

		/** START Debug output */
		//		int e = 0;
		//		while (e < i) {
		//
		//			String string = "";
		//			Vector test = (Vector) tupel[e];
		//			Iterator testIt = test.iterator();
		//			while (testIt.hasNext()) {
		//				int tempInt = ((Integer) testIt.next()).intValue();
		//				int outInt = tempInt;
		//				if (tempInt < 0)
		//					tempInt *= -1;
		//
		//				if (tempInt != 0) {
		//					NetworkplanElement npEle = (NetworkplanElement) npElemente.get(tempInt - 1);
		//					string = string + " " + (outInt);
		//				} else {
		//					string = string + " 0";
		//				}
		//			}
		//			System.out.println("Tupel[" + e + "]: " + string);
		//			e++;
		//		}
		/** END Debug output */
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
	 * Get the max Height of this JNetworkplan
	 * @return maxHeightPos
	 */
	public int getMaxHeightPos() {
		return maxHeightPos;
	}

	/**
	 * Get the max Width of this JNetworkplan
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

		/** Get the width each child of the parent gets */
		while (c < parents.size()) {

			int currentPos = ((Integer) parents.get(c)).intValue();

			int tempPos = currentPos;

			if (currentPos < 0)
				currentPos *= -1;

			if (tempPos > 0) {
				int child[] = ((NetworkplanElement) npElemente.get(currentPos - 1)).getChild();

				int countChilds = child.length;

				double widthForChild = (double) widthForChildGroup / (double) countChilds;

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

	/****************************************
	 *  			Mouse Event Listener  				*
	 ****************************************/

	/** Save this component if its a JNetworkplanElement for movement */
	public void mousePressed(MouseEvent evt) {
		if (dragging || editPanelOpen)
			return;

		int x = evt.getX();
		int y = evt.getY();

		if (getComponentAt(x, y).getName() != null) {
			if (x1 == 0 && y1 == 0) {
				x1 = (int) getComponentAt(x, y).getLocation().getX();
				y1 = (int) getComponentAt(x, y).getLocation().getY();
			}

			if (movedElement == null) {
				movedElement = (JNetworkplanElement) getComponentAt(x, y);

				/** Place this Element top of all */
				remove(movedElement);
				add(movedElement, 0);
			}

			dragging = true;
			offsetX = x - x1;
			offsetY = y - y1;

			movedElement.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		}
	}

	/** Forget this JNetworkplanElement after Mouse-Release*/
	public void mouseReleased(MouseEvent evt) {
		if (movedElement != null) {

			/** Save the new position of this element */
			elementsPosition.put(new Integer(((JNetworkplanElement) movedElement).getNp().getIndex()), movedElement.getLocation());

			movedElement.setCursor(new Cursor(Cursor.HAND_CURSOR));
			movedElement = null;
		}
		dragging = false;
		x1 = 0;
		y1 = 0;
	}

	/** Move the selected JNetworkPlan */
	public void mouseDragged(MouseEvent evt) {
		if (!dragging || evt.getX() < 0 || evt.getY() < 0 || editPanelOpen)
			return;

		int x = evt.getX();
		int y = evt.getY();
		x1 = x - offsetX;
		y1 = y - offsetY;

		if (getComponentAt(x, y).getName() != null) {

			/** Do not move the element out in the negative area */
			if (x1 < 0)
				x1 = 0;

			if (y1 < 0)
				y1 = 0;

			/** Set horizontal and vertical Scrollbars if needed */
			if ((x1 + movedElement.getSize().getWidth()) > maxX)
				maxX = x1 + (int) movedElement.getSize().getWidth();

			if ((y1 + movedElement.getSize().getHeight()) > maxY)
				maxY = y1 + (int) movedElement.getSize().getHeight();

			/** Set new dimension with padding */
			Dimension newD = new Dimension(maxX + jNPaddingX, maxY + jNPaddingY);
			scrollRectToVisible(new Rectangle(newD));
			setPreferredSize(newD);

			movedElement.setLocation(x1, y1);
			positionConnectionLines(movedElement);
		}
	}

	/** Highlight the JNetworkPlan element that becomes selected */
	public void mouseMoved(MouseEvent evt) {
		int x = evt.getX();
		int y = evt.getY();

		/** Only highlight if no Edit-Panel is opened */
		if (!editPanelOpen) {

			/** Only highlight if Mouselocation is on a JNetworkplanElement */
			if (getComponentAt(x, y).getName() != null && selectedElement == null) {
				selectedElement = (JNetworkplanElement) getComponentAt(x, y);
				selectedElement.npElementRectMouseMoved();
				selectedElement.setCursor(new Cursor(Cursor.HAND_CURSOR));

				/** Deselect the element when the user leaves it */
			} else if (getComponentAt(x, y).getName() == null && selectedElement != null) {
				selectedElement.npElementRectMouseExited();
				selectedElement = null;

				/** Case when JNetworkplanElements are overlapping */
			} else if (getComponentAt(x, y).getName() != null && selectedElement != null) {
				if (!getComponentAt(x, y).getName().equals(selectedElement.getName())) {
					selectedElement.npElementRectMouseExited();
					selectedElement = null;
				}
			}
		}
	}

	/** 
	 * When the user clicks on an element, show a JPanel where the user may edit
	 * the description and the duration 
	 */
	public void mouseClicked(MouseEvent evt) {
		int x = evt.getX();
		int y = evt.getY();

		/** 
		 * Open the JPanel for editing if the user clicked on an networkplan element 
		 * and no other JPanel for editing is opened at that time.
		 */
		if (getComponentAt(x, y).getName() != null && !editPanelOpen) {

			editPanelOpen = true;

			clickedComponent = (JNetworkplanElement) getComponentAt(x, y);

			jPanelEdit = new JPanel();
			JLabel jLabelDescription = new JLabel();
			JLabel jLabelDuration = new JLabel();
			final JTextField jTextFieldDuration = new JTextField();
			final JTextField jTextFieldDescription = new JTextField();
			JButton jButtonEdit = new JButton();
			JButton jButtonCancle = new JButton();

			jPanelEdit.setLayout(null);
			jPanelEdit.setBorder(new LineBorder(Color.BLACK));

			jLabelDescription.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabelDescription.setText("Beschreibung:");
			jPanelEdit.add(jLabelDescription);
			jLabelDescription.setBounds(0, 50, 90, 16);

			jLabelDuration.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabelDuration.setText("Dauer:");
			jPanelEdit.add(jLabelDuration);
			jLabelDuration.setBounds(10, 20, 80, 20);

			jPanelEdit.add(jTextFieldDuration);
			jTextFieldDuration.setBounds(100, 20, 70, 20);
			jTextFieldDuration.setBorder(new LineBorder(new Color(0, 0, 0)));
			jTextFieldDuration.setText(String.valueOf(clickedComponent.getNp().getDuration()));

			jPanelEdit.add(jTextFieldDescription);
			jTextFieldDescription.setBounds(100, 50, 160, 20);
			jTextFieldDescription.setBorder(new LineBorder(new Color(0, 0, 0)));
			jTextFieldDescription.setText(clickedComponent.getNp().getDescription());
			jTextFieldDescription.moveCaretPosition(0);

			jButtonEdit.setText("Edit");
			jButtonEdit.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {

					/** Check if the number is not a string. If its a string reset to old number */
					try {
						Double.parseDouble(jTextFieldDuration.getText());
					} catch (Exception e) {
						jTextFieldDuration.setText(String.valueOf(clickedComponent.getNp().getDuration()));
					}

					/** Check if the user made changes. If not, do nothing */
					if (clickedComponent.getNp().getDuration() == Double.parseDouble(jTextFieldDuration.getText()) && clickedComponent.getNp().getDescription().equals(jTextFieldDescription.getText())) {
						editPanelOpen = false;
						remove(jPanelEdit);
						repaint();
						return;
					}

					/** Set the changes the user made and repaint the network plan */
					clickedComponent.getNp().setDuration(Double.parseDouble(jTextFieldDuration.getText()));
					clickedComponent.getNp().setDescription(jTextFieldDescription.getText());

					((NetworkplanElement) npElemente.get(clickedComponent.getNp().getIndex() - 1)).setDuration(Double.parseDouble(jTextFieldDuration.getText()));
					((NetworkplanElement) npElemente.get(clickedComponent.getNp().getIndex() - 1)).setDescription(jTextFieldDescription.getText());

					/** Remove networkplan */
					removeAll();

					/** Start stopwatch */
					long startTime = System.currentTimeMillis();

					/** Make a new calculation (light) */
					npCalc = new NetworkplanCalculator(npElemente, true);
					npElemente = npCalc.getNpElemente();
					resetCriticalPath();
					showCriticalPath();

					/** Set positions of all JNetworkplanElements on this JNetworkplan */
					positionSavedElements();

					/** Set positions of all connection lines between the JNetworkplanElements */
					positionSavedConnectionLines();

					/** Re-draw the critical path (red lines) */
					updateCriticalPathLines();

					/** Stop stopwatch */
					long endTime = System.currentTimeMillis();

					/** Get the time for calculating and showing the networkplan */
					long calculationTime = endTime - startTime;

					JLabel showCalculationTime = new JLabel("Rendertime: " + (double) calculationTime / 1000 + " sec.");
					add(showCalculationTime);
					showCalculationTime.setBounds(10, 5, 200, 20);

					remove(jPanelEdit);
					editPanelOpen = false;
				}
			});

			jPanelEdit.add(jButtonEdit);
			jButtonEdit.setBounds(55, 82, 81, 26);
			jButtonEdit.setBorder(new LineBorder(Color.BLACK));

			jButtonCancle.setText("Cancle");

			jButtonCancle.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					remove(jPanelEdit);
					editPanelOpen = false;
					repaint();
				}
			});

			jPanelEdit.add(jButtonCancle);
			jButtonCancle.setBounds(145, 82, 81, 26);
			jButtonCancle.setBorder(new LineBorder(Color.BLACK));

			jPanelEdit.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			add(jPanelEdit, 0);

			/** Center the Edit-Panel over the JNetworkplanElement */
			int elemXPos = (int) clickedComponent.getLocation().getX() + 10;
			int elemYPos = (int) clickedComponent.getLocation().getY() + 35;

			/** The Edit-Panel will allways be shown in the visible rect */
			if (elemXPos < 0)
				elemXPos = 0;

			if (elemYPos < 0)
				elemYPos = 0;

			jPanelEdit.setBounds(elemXPos, elemYPos, 280, 120);
			jPanelEdit.setBackground(java.awt.SystemColor.controlHighlight);
		}
	}

	public void mouseEntered(MouseEvent evt) {
	}

	public void mouseExited(MouseEvent evt) {
	}

	/**
	 * Sets all elements criticalpath-values to FALSE
	 */
	public void resetCriticalPath() {
		int a = 0;
		while (a < npElemente.size()) {
			((NetworkplanElement) npElemente.get(a)).setCriticalPath(false);
			a++;
		}
	}

	/**
	 * Set the positions of each saved JNetworkplanElement
	 */
	public void positionSavedElements() {

		/** Get elements where arrows are disabled */
		Vector disabledArrowElements = new Vector();
		for (int b = 0; b < jNpElem.length && jNpElem[b] != null; b++) {
			if (!jNpElem[b].isDisplayArrow())
				disabledArrowElements.add(new Integer(jNpElem[b].getNp().getNumber()));
		}

		int a = 0;
		while (a < npElemente.size()) {

			NetworkplanElement np = (NetworkplanElement) npElemente.get(a);

			jNpElem[a] = new JNetworkplanElement(np);
			np.setLayoutManager(a);
			jNpElem[a].setName(String.valueOf(np.getIndex()));

			/** Remove the arrow if it's disabled */
			if (disabledArrowElements.contains(new Integer(jNpElem[a].getNp().getNumber())))
				jNpElem[a].setDisplayArrow(false);

			this.add(jNpElem[a]);

			Point location = (Point) elementsPosition.get(new Integer(np.getIndex()));

			jNpElem[a].setBounds((int) location.getX(), (int) location.getY(), jNpElemWidth, jNpElemHeight);

			a++;
		}
	}

	/**
	 * Set the positions of all saved connection lines
	 */
	public void positionSavedConnectionLines() {
		Enumeration connectionLines = elementsConLine.elements();
		while (connectionLines.hasMoreElements()) {
			add((JSeparator) connectionLines.nextElement());
		}
	}

	/**
	 * Paint the connection lines between the networkplan elements 
	 */
	public void paintConnectionLines() {

		/** Paint the horizontal and vertical connection lines with JSeperators */
		int a = 0;

		int child[] = ((NetworkplanElement) npElemente.get(0)).getChild();

		JSeparator horizontalCon[] = new JSeparator[jNpElem.length];

		while (a < jNpElem.length && jNpElem[a] != null) {

			/** Vertical small connection lines to the element at top and bottom */
			int xPosStart = (int) jNpElem[a].getAnchorPoint(JNetworkplanElement.ANCHOR_TOP_MIDDLE).getX();

			/** Top Connection */
			if (!jNpElem[a].getNp().isStartElem()) {

				int parent[] = jNpElem[a].getNp().getParent();

				int yPosStart = (int) jNpElem[a].getAnchorPoint(JNetworkplanElement.ANCHOR_TOP_MIDDLE).getY() - 65;

				JSeparator jSeparatorVerticalCon = new JSeparator();
				jSeparatorVerticalCon.setOrientation(SwingConstants.VERTICAL);
				jSeparatorVerticalCon.setBounds(xPosStart, yPosStart, 1, 65);

				if (jNpElem[a].getNp().isCriticalPath())
					jSeparatorVerticalCon.setForeground(Color.RED);
				else
					jSeparatorVerticalCon.setForeground(Color.BLACK);

				this.add(jSeparatorVerticalCon);

				/** Save this line */
				elementsConLine.put(jNpElem[a].getName() + "top", jSeparatorVerticalCon);

				/** Save a possible middle horizontal connection line */
				horizontalCon[a] = new JSeparator();
				add(horizontalCon[a]);
				elementsConLine.put(parent[0] + "middle" + jNpElem[a].getName(), horizontalCon[a]);
			}

			/** Bottom Connection */
			if (!jNpElem[a].getNp().isEndElem()) {
				int yPosStart = (int) jNpElem[a].getAnchorPoint(JNetworkplanElement.ANCHOR_BOTTOM_MIDDLE).getY();

				child = jNpElem[a].getNp().getChild();

				JSeparator jSeparatorVerticalCon = new JSeparator();
				jSeparatorVerticalCon.setOrientation(SwingConstants.VERTICAL);
				jSeparatorVerticalCon.setBounds(xPosStart, yPosStart, 1, 65);

				if (jNpElem[a].getNp().isCriticalPath())
					jSeparatorVerticalCon.setForeground(Color.RED);
				else
					jSeparatorVerticalCon.setForeground(Color.BLACK);

				this.add(jSeparatorVerticalCon);

				/** Save this line */
				elementsConLine.put(jNpElem[a].getName() + "bottom", jSeparatorVerticalCon);

				/** Save a possible middle horizontal connection line */
				horizontalCon[a] = new JSeparator();
				add(horizontalCon[a]);
				elementsConLine.put(jNpElem[a].getName() + "middle" + child[0], horizontalCon[a]);
			}
			a++;
		}

		/** Update the connection lines */
		int g = 0;
		while (g < jNpElem.length && jNpElem[g] != null) {
			movedElement = jNpElem[g];
			positionConnectionLines(movedElement);
			g++;
		}
		movedElement = null;
	}

	/**
	 * This method is called one on startup and then every time the user moves a 
	 * JNetworkplanElement with the mouse. The connected lines to this element move 
	 * in dependence of the movement of the JNetworkplanElement.
	 * @param jNpActElem Current JNetworkplanElement
	 */
	public void positionConnectionLines(JNetworkplanElement jNpActElem) {

		/** The moved JNetworkplanElement is not the End-Element and has a connection-line on the bottom */
		if (elementsConLine.containsKey(jNpActElem.getName() + "bottom")) {
			updateVerticalLinesBottom(jNpActElem);
		}

		/** The moved JNetworkplanElement is not the Start-Element and has a connection-line on the top */
		if (elementsConLine.containsKey(jNpActElem.getName() + "top")) {
			updateVerticalLinesTop(jNpActElem);
		}
	}

	/**
	 * This element has a vertical connection line on the bottom. Set its position!
	 * @param jNpActElem The current JNetworkplanElement
	 */
	public void updateVerticalLinesBottom(JNetworkplanElement jNpActElem) {

		JSeparator actLineBottom = (JSeparator) elementsConLine.get(jNpActElem.getName() + "bottom");

		int child[] = jNpActElem.getNp().getChild();
		JNetworkplanElement childJNpElem = jNpElem[((NetworkplanElement) npElemente.get(child[0] - 1)).getLayoutManager()];

		/** Set the position and size of the connection line */
		int difHeight = (int) ((JSeparator) elementsConLine.get(childJNpElem.getName() + "top")).getLocation().getY() - (int) jNpActElem.getLocation().getY() - 170;

		/** Position the horizontal middle line alaways in the middle of the elements */
		int childsParents[] = childJNpElem.getNp().getParent();
		if (child.length == 1 && childsParents.length == 1) {

			/** YPos of the bottom line of the rect of the parent */
			int line1YPos = (int) ((JSeparator) elementsConLine.get(jNpActElem.getName() + "bottom")).getLocation().getY();

			/** YPos ofthe top line of the rect of the child */
			int line2YPos = (int) ((JSeparator) elementsConLine.get(childJNpElem.getName() + "top")).getLocation().getY() + (int) ((JSeparator) elementsConLine.get(childJNpElem.getName() + "top")).getSize().getHeight();

			/** Middle-YPosition of the distance between Parent and Child */
			difHeight = (line2YPos - line1YPos) / 2;

			/** Update child */
			JSeparator actLineTop = (JSeparator) elementsConLine.get(childJNpElem.getName() + "top");

			int parent[] = childJNpElem.getNp().getParent();
			JNetworkplanElement parentJNpElem = jNpElem[((NetworkplanElement) npElemente.get(parent[0] - 1)).getLayoutManager()];

			/** Set the position and size of the connection line */

			/** YPosition of the ending point of the vertical connection line on bottom of the parent element */
			int anchorY = (int) ((JSeparator) elementsConLine.get(parentJNpElem.getName() + "bottom")).getLocation().getY() + (int) ((JSeparator) elementsConLine.get(parentJNpElem.getName() + "bottom")).getSize().getHeight();

			actLineTop.setLocation((int) childJNpElem.getLocation().getX() + jNpElemWidth / 2, anchorY);
			actLineTop.setSize(1, difHeight);

			/** Remove the arrow if difHeight is < 15px */
			if (difHeight < 15)
				childJNpElem.setDisplayArrow(false);
			else
				childJNpElem.setDisplayArrow(true);

			/** Update the horizontal connection line bottom of the element */
			updateHorizontalLinesBottom(childJNpElem);

			/** Update the horizontal connection line for the elements parents */
			if (parent[0] != 0) {
				for (int u = 0; u < parent.length; u++) {
					updateHorizontalLinesBottom(jNpElem[((NetworkplanElement) npElemente.get(parent[u] - 1)).getLayoutManager()]);
				}
			}

			/** Synchronize the position of the vertical top-line of the child */
			syncVerticalLinesTop(childJNpElem);
		}

		actLineBottom.setLocation((int) jNpActElem.getLocation().getX() + jNpElemWidth / 2, (int) jNpActElem.getLocation().getY() + 170);
		actLineBottom.setSize(1, difHeight);

		/** Update the horizontal connection line top of the element */
		updateHorizontalLinesTop(jNpActElem);

		/** Update the horizontal connection line for the elements childs */
		if (child[0] != 0) {
			for (int u = 0; u < child.length; u++) {
				updateHorizontalLinesTop(jNpElem[((NetworkplanElement) npElemente.get(child[u] - 1)).getLayoutManager()]);
			}
		}
	}

	/**
	 * This element has a vertical connection line on the top. Set its position!
	 * @param jNpActElem The current JNetworkplanElement
	 */
	public void updateVerticalLinesTop(JNetworkplanElement jNpActElem) {

		JSeparator actLineTop = (JSeparator) elementsConLine.get(jNpActElem.getName() + "top");

		int parent[] = jNpActElem.getNp().getParent();
		JNetworkplanElement parentJNpElem = jNpElem[((NetworkplanElement) npElemente.get(parent[0] - 1)).getLayoutManager()];

		/** Set the position and size of the connection line */

		/** YPosition of the ending point of the vertical connection line on bottom of the parent element */
		int anchorY = (int) ((JSeparator) elementsConLine.get(parentJNpElem.getName() + "bottom")).getLocation().getY() + (int) ((JSeparator) elementsConLine.get(parentJNpElem.getName() + "bottom")).getSize().getHeight();

		/** Position the horizontal middle line alaways in the middle of the elements */
		int parentsChilds[] = parentJNpElem.getNp().getChild();

		if (parentsChilds.length == 1 && parent.length == 1) {

			int line1YPos = (int) ((JSeparator) elementsConLine.get(jNpActElem.getName() + "top")).getLocation().getY() + (int) ((JSeparator) elementsConLine.get(jNpActElem.getName() + "top")).getSize().getHeight();
			int line2YPos = (int) ((JSeparator) elementsConLine.get(parentJNpElem.getName() + "bottom")).getLocation().getY();
			int difY = (line2YPos - line1YPos) / 2;

			anchorY = (int) ((JSeparator) elementsConLine.get(parentJNpElem.getName() + "bottom")).getLocation().getY() - difY;

			/** Update parent */
			JSeparator actLineBottom = (JSeparator) elementsConLine.get(parentJNpElem.getName() + "bottom");

			int child[] = parentJNpElem.getNp().getChild();
			JNetworkplanElement childJNpElem = jNpElem[((NetworkplanElement) npElemente.get(child[0] - 1)).getLayoutManager()];

			/** Set the position and size of the connection line */
			int difHeight = (int) ((JSeparator) elementsConLine.get(childJNpElem.getName() + "top")).getLocation().getY() - (int) parentJNpElem.getLocation().getY() - 170;

			actLineBottom.setLocation((int) parentJNpElem.getLocation().getX() + jNpElemWidth / 2, (int) parentJNpElem.getLocation().getY() + 170);
			actLineBottom.setSize(1, difHeight);

			/** Update the horizontal connection line top of the element */
			updateHorizontalLinesTop(parentJNpElem);

			/** Update the horizontal connection line for the elements childs */
			if (child[0] != 0) {
				for (int u = 0; u < child.length; u++) {
					updateHorizontalLinesTop(jNpElem[((NetworkplanElement) npElemente.get(child[u] - 1)).getLayoutManager()]);
				}
			}
			syncVerticalLinesBottom(parentJNpElem);
		}

		int difHeight = (int) jNpActElem.getLocation().getY() - anchorY + 20;

		actLineTop.setLocation((int) jNpActElem.getLocation().getX() + jNpElemWidth / 2, anchorY);
		actLineTop.setSize(1, difHeight);

		/** Check if to display the arrow */
		if (parentJNpElem.getNp().getChild().length > 1 || jNpActElem.getNp().getParent().length > 1) {

			/** Remove the arrow if difHeight is < 15px */
			if (difHeight < 15)
				jNpActElem.setDisplayArrow(false);
			else
				jNpActElem.setDisplayArrow(true);
		} else {

			/** Remove the arrow if the two elements are touching each other */
			if ((int) jNpActElem.getLocation().getY() < ((int) parentJNpElem.getLocation().getY() + 170))
				jNpActElem.setDisplayArrow(false);
			else
				jNpActElem.setDisplayArrow(true);
		}

		/** Update the horizontal connection line bottom of the element */
		updateHorizontalLinesBottom(jNpActElem);

		/** Update the horizontal connection line for the elements parents */
		if (parent[0] != 0) {
			for (int u = 0; u < parent.length; u++) {
				updateHorizontalLinesBottom(jNpElem[((NetworkplanElement) npElemente.get(parent[u] - 1)).getLayoutManager()]);
			}
		}
	}

	/**
	 * Update the horizontal connection line top of the element
	 * @param jNpActElem Current JNetworkplanElement
	 */
	public void updateHorizontalLinesTop(JNetworkplanElement jNpActElem) {
		int parent[] = jNpActElem.getNp().getParent();

		if (parent[0] != 0) {
			JNetworkplanElement parentJNpElem = jNpElem[((NetworkplanElement) npElemente.get(parent[0] - 1)).getLayoutManager()];

			/** The user moved the element on the X-Axis */
			int difX = (int) jNpActElem.getLocation().getX() - (int) parentJNpElem.getLocation().getX();
			if (difX != 0) {
				JSeparator horizontalCon = (JSeparator) elementsConLine.get(parentJNpElem.getName() + "middle" + jNpActElem.getName());
				horizontalCon.setOrientation(SwingConstants.HORIZONTAL);

				if (jNpActElem.getNp().isCriticalPath() && parentJNpElem.getNp().isCriticalPath())
					horizontalCon.setForeground(Color.RED);
				else
					horizontalCon.setForeground(Color.BLACK);

				/** YPosition of the ending point of the vertical connection line on bottom of the parent element */
				int anchorY = (int) ((JSeparator) elementsConLine.get(parentJNpElem.getName() + "bottom")).getLocation().getY() + (int) ((JSeparator) elementsConLine.get(parentJNpElem.getName() + "bottom")).getSize().getHeight();

				/** The user moved the element to the right */
				if (difX > 0) {

					/** Set the position and size of the connection line */
					int xStart = (int) parentJNpElem.getAnchorPoint(JNetworkplanElement.ANCHOR_TOP_MIDDLE).getX();
					int yStart = anchorY;
					int xLength = difX;

					horizontalCon.setBounds(xStart, yStart, xLength, 1);

					/** The user moved the element to the left */
				} else if (difX < 0) {

					/** Set the position and size of the connection line */
					int xStart = (int) parentJNpElem.getAnchorPoint(JNetworkplanElement.ANCHOR_TOP_MIDDLE).getX() + difX;
					int yStart = anchorY;
					int xLength = difX * (-1);

					horizontalCon.setBounds(xStart, yStart, xLength, 1);
				}

				/** Always display the critical path line in the foreground */
				if (horizontalCon.getForeground().equals(Color.RED)) {
					remove(horizontalCon);
					add(horizontalCon, 2);
				}
			}
		}
	}

	/**
	 * Update the horizontal connection line bottom of the element
	 * @param jNpActElem Current JNetworkplanElement
	 */
	public void updateHorizontalLinesBottom(JNetworkplanElement jNpActElem) {
		int child[] = jNpActElem.getNp().getChild();

		if (child[0] != 0) {
			JNetworkplanElement childJNpElem = jNpElem[((NetworkplanElement) npElemente.get(child[0] - 1)).getLayoutManager()];

			/** The user moved the element on the X-Axis */
			int difX = (int) jNpActElem.getLocation().getX() - (int) childJNpElem.getLocation().getX();
			if (difX != 0) {
				JSeparator horizontalCon = (JSeparator) elementsConLine.get(jNpActElem.getName() + "middle" + childJNpElem.getName());
				horizontalCon.setOrientation(SwingConstants.HORIZONTAL);

				if (jNpActElem.getNp().isCriticalPath() && childJNpElem.getNp().isCriticalPath())
					horizontalCon.setForeground(Color.RED);
				else
					horizontalCon.setForeground(Color.BLACK);

				/** The user moved the element to the right */
				if (difX > 0) {

					/** Set the position and size of the connection line */
					int xStart = (int) childJNpElem.getAnchorPoint(JNetworkplanElement.ANCHOR_BOTTOM_MIDDLE).getX();
					int yStart = (int) ((JSeparator) elementsConLine.get(childJNpElem.getName() + "top")).getLocation().getY();
					int xLength = difX;

					horizontalCon.setBounds(xStart, yStart, xLength, 1);

					/** The user moved the element to the left */
				} else if (difX < 0) {

					/** Set the position and size of the connection line */
					int xStart = (int) childJNpElem.getAnchorPoint(JNetworkplanElement.ANCHOR_BOTTOM_MIDDLE).getX() + difX;
					int yStart = (int) ((JSeparator) elementsConLine.get(childJNpElem.getName() + "top")).getLocation().getY();
					int xLength = difX * (-1);

					horizontalCon.setBounds(xStart, yStart, xLength, 1);
				}

				/** Always display the critical path line in the foreground */
				if (horizontalCon.getForeground().equals(Color.RED)) {
					remove(horizontalCon);
					add(horizontalCon, 2);
				}
			}
		}
	}

	/** Sets the JSeperators of the critical path to Color.RED */
	public void updateCriticalPathLines() {
		Enumeration keys = elementsConLine.keys();

		/** Foreach key */
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();

			/** Get the JSeperator with the key */
			JSeparator actSeparator = (JSeparator) elementsConLine.get(key);
			actSeparator.setForeground(Color.BLACK);

			/** This JSeperator is a vertical connection line on top of an element */
			if (key.matches("^[0-9]*top$")) {
				String index = key.replaceAll("top", "");

				/** Check if the element connected to this line is a critical path member */
				if (((NetworkplanElement) npElemente.get(Integer.parseInt(index) - 1)).isCriticalPath()) {
					actSeparator.setForeground(Color.RED);
				}

				/** This JSeperator is a vertical connection line on the bottom of an element */
			} else if (key.matches("^[0-9]*bottom$")) {
				String index = key.replaceAll("bottom", "");

				/** Check if the element connected to this line is a critical path member */
				if (((NetworkplanElement) npElemente.get(Integer.parseInt(index) - 1)).isCriticalPath()) {
					actSeparator.setForeground(Color.RED);
				}

				/** This JSeperator is a horizontal connection line */
			} else if (key.matches("^[0-9]*middle[0-9]*$")) {
				String index[] = key.split("middle");

				/** Check if the elements connected to this line is are critical path member */
				if (((NetworkplanElement) npElemente.get(Integer.parseInt(index[0]) - 1)).isCriticalPath() && ((NetworkplanElement) npElemente.get(Integer.parseInt(index[1]) - 1)).isCriticalPath()) {
					actSeparator.setForeground(Color.RED);
				}
			}
		}
	}

	/**
	 * Sync this elements vertical connection line in the top to the child element's line
	 * @param jNpActElem The current JNetworkplanElement
	 */
	public void syncVerticalLinesBottom(JNetworkplanElement jNpActElem) {

		JSeparator actLineBottom = (JSeparator) elementsConLine.get(jNpActElem.getName() + "bottom");

		int child[] = jNpActElem.getNp().getChild();
		JNetworkplanElement childJNpElem = jNpElem[((NetworkplanElement) npElemente.get(child[0] - 1)).getLayoutManager()];

		/** Set the position and size of the connection line */
		int difHeight = (int) ((JSeparator) elementsConLine.get(childJNpElem.getName() + "top")).getLocation().getY() - (int) jNpActElem.getLocation().getY() - 170;

		actLineBottom.setLocation((int) jNpActElem.getLocation().getX() + jNpElemWidth / 2, (int) jNpActElem.getLocation().getY() + 170);
		actLineBottom.setSize(1, difHeight);
	}

	/**
	 * Sync this elements vertical connection line in the top to the parent element's line
	 * @param jNpActElem The current JNetworkplanElement
	 */
	public void syncVerticalLinesTop(JNetworkplanElement jNpActElem) {

		JSeparator actLineTop = (JSeparator) elementsConLine.get(jNpActElem.getName() + "top");

		int parent[] = jNpActElem.getNp().getParent();
		JNetworkplanElement parentJNpElem = jNpElem[((NetworkplanElement) npElemente.get(parent[0] - 1)).getLayoutManager()];

		/** Set the position and size of the connection line */

		/** YPosition of the ending point of the vertical connection line on bottom of the parent element */
		int anchorY = (int) ((JSeparator) elementsConLine.get(parentJNpElem.getName() + "bottom")).getLocation().getY() + (int) ((JSeparator) elementsConLine.get(parentJNpElem.getName() + "bottom")).getSize().getHeight();

		int difHeight = (int) jNpActElem.getLocation().getY() - anchorY + 20;

		actLineTop.setLocation((int) jNpActElem.getLocation().getX() + jNpElemWidth / 2, anchorY);
		actLineTop.setSize(1, difHeight);

		/** Check if to display the arrow */
		if (parentJNpElem.getNp().getChild().length > 1 || jNpActElem.getNp().getParent().length > 1) {

			/** Remove the arrow if difHeight is < 15px */
			if (difHeight < 15)
				jNpActElem.setDisplayArrow(false);
			else
				jNpActElem.setDisplayArrow(true);
		} else {

			/** Remove the arrow if the two elements are touching each other */
			if ((int) jNpActElem.getLocation().getY() < ((int) parentJNpElem.getLocation().getY() + 170))
				jNpActElem.setDisplayArrow(false);
			else
				jNpActElem.setDisplayArrow(true);
		}
	}
}