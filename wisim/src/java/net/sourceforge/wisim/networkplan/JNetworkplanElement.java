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
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 * JNetworkplanElement extends JPanel and represents one networkplan element
 * @author benjamin.pasero
 * @version 0.7a
 */
public class JNetworkplanElement extends JPanel {

	/** Swing components */
	private JLabel jLabelDuration;
	private JLabel jLabelFAZ;
	private JLabel jLabelFEZ;
	private JLabel jLabelFP;
	private JLabel jLabelGP;
	private JLabel jLabelLine1;
	private JLabel jLabelLine2;
	private JLabel jLabelNumber;
	private JLabel jLabelSAZ;
	private JLabel jLabelSEZ;
	private JPanel npElementRect;

	private boolean isCriticalPathElement;
	private boolean displayArrow;
	private NetworkplanElement np;

	/** Defining some Fonts */
	private Font innerTextFont;
	private Font outerTextFont;
	private Font descriptionFont;

	/** Constants for the four anchor points of the element */
	public final static int ANCHOR_TOP_MIDDLE = 1;
	public final static int ANCHOR_BOTTOM_MIDDLE = 2;
	public final static int ANCHOR_MIDDLE_LEFT = 3;
	public final static int ANCHOR_MIDDLE_RIGHT = 4;

	/** Size of the transparent rectangle holding the networkplan element */
	private static final int WIDTH = 300;
	private static final int HEIGHT = 190;

	/** Max. size of one textline in the description-box */
	private static final int MAX_TEXT_WIDTH = 200;

	/**
	 * Paints a new networkplan element in a JLabel
	 */
	public JNetworkplanElement(NetworkplanElement np) {
		npElementRect = new JPanel();
		jLabelGP = new JLabel();
		jLabelFP = new JLabel();
		jLabelDuration = new JLabel();
		jLabelNumber = new JLabel();
		jLabelLine1 = new JLabel();
		jLabelLine2 = new JLabel();
		jLabelFAZ = new JLabel();
		jLabelFEZ = new JLabel();
		jLabelSAZ = new JLabel();
		jLabelSEZ = new JLabel();
		isCriticalPathElement = false;
		innerTextFont = new Font("Dialog", 1, 24);
		outerTextFont = new Font("Dialog", 1, 16);
		descriptionFont = new Font("Dialog", 1, 14);
		this.np = np;
		displayArrow = true;

		/** Generate the networkplan element */
		generateNetzplanelement(np);
	}

	public JNetworkplanElement() {
		npElementRect = new JPanel();
		jLabelGP = new JLabel();
		jLabelFP = new JLabel();
		jLabelDuration = new JLabel();
		jLabelNumber = new JLabel();
		jLabelLine1 = new JLabel();
		jLabelLine2 = new JLabel();
		jLabelFAZ = new JLabel();
		jLabelFEZ = new JLabel();
		jLabelSAZ = new JLabel();
		jLabelSEZ = new JLabel();
		isCriticalPathElement = false;
		innerTextFont = new Font("Dialog", 1, 24);
		outerTextFont = new Font("Dialog", 1, 16);
		descriptionFont = new Font("Dialog", 1, 14);
		displayArrow = true;
	}

	/**
	 * Paints a new networkplan element in this JLabel
	 * @param np the networkplan element
	 */
	public void generateNetzplanelement(NetworkplanElement np) {

		this.setLayout(null);

		/** Alpha is set to 0, makes this JPanel transparent */
		this.setBackground(new Color(255, 255, 255, 0));

		/** Rectangle of the network plan element */
		npElementRect.setLayout(null);
		npElementRect.setBackground(new Color(255, 255, 255));
		setBackground(new Color(255, 255, 255, 0));

		Color lineColor = new Color(0, 0, 0);

		/** Paint element red if its member of the critical path */
		if (np.isCriticalPath()) {
			lineColor = new Color(255, 0, 0);
			isCriticalPathElement = true;
		}

		npElementRect.setBorder(new LineBorder(lineColor));

		/** Writing total buffer */
		jLabelGP.setFont(innerTextFont);
		jLabelGP.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelGP.setText(String.valueOf(np.getGp()));
		jLabelGP.setBorder(new LineBorder(lineColor));
		npElementRect.add(jLabelGP);
		jLabelGP.setBounds(79, 75, 110, 75);

		/** Writing free buffer */
		jLabelFP.setFont(innerTextFont);
		jLabelFP.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelFP.setText(String.valueOf(np.getFp()));
		jLabelFP.setBorder(new LineBorder(lineColor));
		npElementRect.add(jLabelFP);
		jLabelFP.setBounds(188, 75, 112, 75);

		/** Writing duration */
		jLabelDuration.setFont(innerTextFont);
		jLabelDuration.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelDuration.setText(String.valueOf(np.getDuration()));
		jLabelDuration.setBorder(new LineBorder(lineColor));
		npElementRect.add(jLabelDuration);
		jLabelDuration.setBounds(0, 75, 80, 75);

		/** Writing number */
		jLabelNumber.setFont(innerTextFont);
		jLabelNumber.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelNumber.setText(String.valueOf(np.getNumber()));
		jLabelNumber.setBorder(new LineBorder(lineColor));
		npElementRect.add(jLabelNumber);
		jLabelNumber.setBounds(0, 0, 80, 76);

		/** Get the width of the description in pixel */
		int textWidth = getTextLength(np.getDescription(), descriptionFont);

		String description = np.getDescription();
		String s1 = description;
		String s2 = "";
		boolean twoLines = false;

		/** Case: Description length is > 400px */
		if (textWidth > MAX_TEXT_WIDTH * 2) {

			s1 = "";
			twoLines = true;

			/** Split description into two lines and cut it. Append "..." to the end */
			String chunks[] = description.split(" ");

			/** Handle Exception: String has no white space */
			if (chunks.length == 1) {
				String temp =
					description.substring(0, description.length() / 2)
						+ " "
						+ description.substring(description.length() / 2, description.length());
				chunks = temp.split(" ");
			}

			int textLen = 0;
			int i = 1;
			String temp = "";

			/** Get the first line that is not longer than 200px (s1) */
			s1 = chunks[0];
			textLen = getTextLength(s1, descriptionFont);
			while (i < chunks.length && textLen < MAX_TEXT_WIDTH) {
				temp = s1;
				s1 = s1 + " " + chunks[i];

				textLen = getTextLength(s1, descriptionFont);
				i++;

				if (textLen > MAX_TEXT_WIDTH) {
					s1 = temp;
					i--;
					break;
				}
			}

			textLen = 0;

			/** Get the second line that is not longer than 200px (s2). Append "..." */
			if (i < chunks.length) {
				s2 = chunks[i];
				textLen = getTextLength(s2, descriptionFont);
				i++;
				while (i < chunks.length && textLen < MAX_TEXT_WIDTH) {
					temp = s2;
					s2 = s2 + " " + chunks[i];

					textLen = getTextLength(s2, descriptionFont);
					i++;

					if (textLen > MAX_TEXT_WIDTH) {
						s2 = temp;
						break;
					}
				}
				s2 = s2.substring(0, s2.length() - 3) + "...";
			}
		}

		/** Case: Description length is > 200px */
		else if (textWidth > MAX_TEXT_WIDTH) {

			s1 = "";
			twoLines = true;

			/** Split description into two lines */
			String chunks[] = description.split(" ");

			/** Handle Exception: String has no white space */
			if (chunks.length == 1) {

				int textLen = 0;
				while (textLen > MAX_TEXT_WIDTH) {
					chunks[0] = chunks[0].substring(0, chunks[0].length() - 1);
					textLen = getTextLength(chunks[0], descriptionFont);
				}
				chunks[0] = chunks[0].substring(0, chunks[0].length() - 3) + "...";
			}

			int textLen = 0;
			int i = 1;
			String temp = "";

			/** Get the first line that is not longer than 200px (s1) */
			s1 = chunks[0];
			textLen = getTextLength(s1, descriptionFont);

			while (i < chunks.length && textLen < MAX_TEXT_WIDTH) {
				temp = s1;
				s1 = s1 + " " + chunks[i];

				textLen = getTextLength(s1, descriptionFont);
				i++;

				if (textLen > MAX_TEXT_WIDTH) {
					s1 = temp;
					i--;
					break;
				}
			}
			textLen = 0;

			/** Get the second line that is not longer than 200px (s2). */
			if (i < chunks.length) {
				s2 = chunks[i];
				textLen = getTextLength(s2, descriptionFont);
				i++;

				while (i < chunks.length && textLen < MAX_TEXT_WIDTH) {
					temp = s2;
					s2 = s2 + " " + chunks[i];

					textLen = getTextLength(s2, descriptionFont);
					i++;

					if (textLen > MAX_TEXT_WIDTH) {
						s2 = temp;
					}
				}
			} else {
				twoLines = false;
			}
		}

		/** Write two lines if so. Center the Strings */
		if (twoLines) {
			jLabelLine1.setFont(descriptionFont);
			jLabelLine1.setHorizontalAlignment(SwingConstants.CENTER);
			jLabelLine1.setText(s1);
			npElementRect.add(jLabelLine1);
			jLabelLine1.setBounds(85, 20, 210, 19);

			jLabelLine2.setFont(descriptionFont);
			jLabelLine2.setHorizontalAlignment(SwingConstants.CENTER);
			jLabelLine2.setText(s2);
			npElementRect.add(jLabelLine2);
			jLabelLine2.setBounds(85, 40, 210, 19);
		} else {
			int middle = (MAX_TEXT_WIDTH - textWidth) / 2;
			if (middle < 0)
				middle = 0;

			jLabelLine1.setFont(descriptionFont);
			jLabelLine1.setHorizontalAlignment(SwingConstants.CENTER);
			jLabelLine1.setText(s1);
			npElementRect.add(jLabelLine1);
			jLabelLine1.setBounds(85, 30, 210, 19);
		}

		/** Add the rectangle to this JPanel (Leave 40px for FEZ, FAZ, SAZ and SEZ */
		this.add(npElementRect);
		npElementRect.setBounds(0, 20, WIDTH, HEIGHT - 40);

		/** Writing FAZ */
		jLabelFAZ.setFont(outerTextFont);
		jLabelFAZ.setText(String.valueOf(np.getFaz()));
		this.add(jLabelFAZ);
		jLabelFAZ.setBounds(0, 0, 40, 20);

		/** Writing FEZ */
		jLabelFEZ.setFont(outerTextFont);
		jLabelFEZ.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabelFEZ.setText(String.valueOf(np.getFez()));
		this.add(jLabelFEZ);
		jLabelFEZ.setBounds(260, 0, 40, 20);

		/** Writing SAZ */
		jLabelSAZ.setFont(outerTextFont);
		jLabelSAZ.setText(String.valueOf(np.getSaz()));
		this.add(jLabelSAZ);
		jLabelSAZ.setBounds(0, 168, 40, 20);

		/** Writing SEZ */
		jLabelSEZ.setFont(outerTextFont);
		jLabelSEZ.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabelSEZ.setText(String.valueOf(np.getSez()));
		this.add(jLabelSEZ);
		jLabelSEZ.setBounds(260, 168, 40, 20);

		this.setBounds(0, 0, WIDTH, HEIGHT);
	}

	/**
	 * @return Width of the network plan element
	 */
	public int getWidth() {
		return WIDTH;
	}

	/**
	 * @return Height of the network plan element
	 */
	public int getHeight() {
		return HEIGHT;
	}

	/**
	 * Returns the length of the description text in pixel.
	 * @param text The text to get the length from
	 * @param font Font that the text is written with
	 * @return Length of the text in pixel
	 */
	public int getTextLength(String text, Font font) {
		return (int) Math.round(
			(font.getStringBounds(text, 0, text.length(), new FontRenderContext(new AffineTransform(), false, false))).getWidth());
	}

	/** Paint the element blue if the mouse moves over it */
	public void npElementRectMouseMoved() {
		npElementRect.setBorder(new LineBorder(new Color(0, 0, 255), 2));
		jLabelGP.setBorder(new LineBorder(new Color(0, 0, 255)));
		jLabelFP.setBorder(new LineBorder(new Color(0, 0, 255)));
		jLabelDuration.setBorder(new LineBorder(new Color(0, 0, 255)));
		jLabelNumber.setBorder(new LineBorder(new Color(0, 0, 255)));
	}

	/** Paint the element to its original color when the mouse moves out of it */
	public void npElementRectMouseExited() {

		Color lineColor = new Color(0, 0, 0);
		if (isCriticalPathElement)
			lineColor = new Color(255, 0, 0);

		npElementRect.setBorder(new LineBorder(lineColor, 1));
		jLabelGP.setBorder(new LineBorder(lineColor));
		jLabelFP.setBorder(new LineBorder(lineColor));
		jLabelDuration.setBorder(new LineBorder(lineColor));
		jLabelNumber.setBorder(new LineBorder(lineColor));
	}

	/**
	 * Get the x- and y-Values of one of the four anchor points of this element
	 * @param anchor ANCHOR_TOP_MIDDLE or ANCHOR_BOTTOM_MIDDLE or ANCHOR_MIDDLE_LEFT
	 * or ANCHOR_MIDDLE_RIGHT
	 * @return Point of the anchor
	 */
	public Point getAnchorPoint(int anchor) {
		switch (anchor) {

			case ANCHOR_TOP_MIDDLE :
				return new Point((int) getLocation().getX() + WIDTH / 2, (int) getLocation().getY() + 20);

			case ANCHOR_BOTTOM_MIDDLE :
				return new Point((int) getLocation().getX() + WIDTH / 2, (int) getLocation().getY() + 170);

			case ANCHOR_MIDDLE_LEFT :
				return new Point((int) getLocation().getX(), (int) getLocation().getY() + 95);

			case ANCHOR_MIDDLE_RIGHT :
				return new Point((int) getLocation().getX() + WIDTH, (int) getLocation().getY() + 95);

			default :
				return new Point(0, 0);
		}
	}

	/**
	 * Get the networkplan element
	 * @return networkplan element of this JPanel
	 */
	public NetworkplanElement getNp() {
		return np;
	}

	/**
	 * Set the networkplan element for this JPanel
	 * @param element
	 */
	public void setNp(NetworkplanElement element) {
		np = element;
	}

	/** Paint an arrow if necessary */
	public void paintComponent(Graphics g) {
		if (displayArrow) {
			if (!np.isStartElem()) {
				if (np.isCriticalPath())
					g.setColor(Color.RED);
				g.fillPolygon(new int[] { 145, 150, 155 }, new int[] { 10, 20, 10 }, 3);
			}
		}
	}

	/**
	 * @return TRUE if the arrow should be displayed
	 */
	public boolean isDisplayArrow() {
		return displayArrow;
	}

	/**
	 * Set if the small arrow on top of the element should be drawn
	 * @param displayArrow TRUE: draw arrow FALSE: dont draw arrow
	 */
	public void setDisplayArrow(boolean displayArrow) {
		this.displayArrow = displayArrow;
	}
}