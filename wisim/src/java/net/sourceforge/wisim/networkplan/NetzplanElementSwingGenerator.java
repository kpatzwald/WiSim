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

/*
 * NetworkplanElementSwingGenerator.java
 */
package net.sourceforge.wisim.networkplan;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 * TODOBen Kommentar Klasse NetworkplanElementSwingGenerator
 * @author benjamin.pasero
 */
public class NetzplanElementSwingGenerator {

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
	private JSeparator jSeparatorBetweenGPandFP;
	private JSeparator jSeparatorCutMiddle;
	private JSeparator jSeparatorFromNumToDur;
	private JSeparator jSeparatorConTop;
	private JSeparator jSeparatorConBottom;
	private JPanel npElementContainer;
	private JPanel npElementRect;
	private boolean isCriticalPathElement;

	private static final int width = 360;

	public NetzplanElementSwingGenerator() {
		npElementContainer = new JPanel();
		npElementRect = new JPanel();
		jSeparatorCutMiddle = new JSeparator();
		jSeparatorFromNumToDur = new JSeparator();
		jSeparatorBetweenGPandFP = new JSeparator();
		jSeparatorConTop = new JSeparator();
		jSeparatorConBottom = new JSeparator();
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

		npElementContainer.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			public void mouseMoved(java.awt.event.MouseEvent evt) {
				npElementContainerMouseMoved(evt);
			}
		});

		npElementContainer.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseExited(java.awt.event.MouseEvent evt) {
				npElementContainerMouseExited(evt);
			}
		});
	}

	public JPanel generateNetzplanelement(NetzplanElement np) {

		/** Container that holds all swing-elements */
		npElementContainer.setLayout(null);

		npElementContainer.setBackground(new java.awt.Color(255, 255, 255));
		npElementContainer.setPreferredSize(new java.awt.Dimension(350, 210));

		/** Rectangle of the network plan element */
		npElementRect.setLayout(null);

		npElementRect.setBackground(new java.awt.Color(255, 255, 255));

		Color lineColor = new Color(0, 0, 0);
		if (np.isCriticalPath()) {
			lineColor = new Color(255, 0, 0);
			isCriticalPathElement = true;
		}

		npElementRect.setBorder(new LineBorder(lineColor));
		npElementRect.setPreferredSize(new java.awt.Dimension(300, 150));

		/** Horizontal line cutting the rectangle in the middle*/
		jSeparatorCutMiddle.setForeground(lineColor);
		npElementRect.add(jSeparatorCutMiddle);
		jSeparatorCutMiddle.setBounds(1, 70, 298, 2);

		/** Vertical line from Number to Duration */
		jSeparatorFromNumToDur.setForeground(lineColor);
		jSeparatorFromNumToDur.setOrientation(SwingConstants.VERTICAL);
		npElementRect.add(jSeparatorFromNumToDur);
		jSeparatorFromNumToDur.setBounds(80, 1, 2, 148);

		/** Vertical line seperating GP and FP */
		jSeparatorBetweenGPandFP.setForeground(lineColor);
		jSeparatorBetweenGPandFP.setOrientation(SwingConstants.VERTICAL);
		npElementRect.add(jSeparatorBetweenGPandFP);
		jSeparatorBetweenGPandFP.setBounds(190, 71, 2, 78);

		/** Connection-line between two elements on top */
		if (!np.isStartElem()) {
			jSeparatorConTop.setForeground(lineColor);
			jSeparatorConTop.setOrientation(SwingConstants.VERTICAL);
			npElementContainer.add(jSeparatorConTop);
			jSeparatorConTop.setBounds(width / 2, 0, width / 2, 30);
		}

		/** Connection-line between two elements on bottom */
		if (!np.isEndElem()) {
			jSeparatorConBottom.setForeground(lineColor);
			jSeparatorConBottom.setOrientation(SwingConstants.VERTICAL);
			npElementContainer.add(jSeparatorConBottom);
			jSeparatorConBottom.setBounds(width / 2, 180, width / 2, 205);
		}

		/** Writing GP */
		jLabelGP.setFont(new java.awt.Font("Dialog", 1, 24));
		jLabelGP.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelGP.setText(String.valueOf(np.getGp()));
		npElementRect.add(jLabelGP);
		jLabelGP.setBounds(90, 80, 90, 60);

		/** Writing FP */
		jLabelFP.setFont(new java.awt.Font("Dialog", 1, 24));
		jLabelFP.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelFP.setText(String.valueOf(np.getFp()));
		npElementRect.add(jLabelFP);
		jLabelFP.setBounds(200, 80, 90, 60);

		/** Writing Duration */
		jLabelDuration.setFont(new java.awt.Font("Dialog", 1, 24));
		jLabelDuration.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelDuration.setText(String.valueOf(np.getDauer()));
		npElementRect.add(jLabelDuration);
		jLabelDuration.setBounds(10, 80, 60, 60);

		/** Writing Number */
		jLabelNumber.setFont(new java.awt.Font("Dialog", 1, 24));
		jLabelNumber.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelNumber.setText(String.valueOf(np.getNummer()));
		npElementRect.add(jLabelNumber);
		jLabelNumber.setBounds(10, 10, 60, 50);

		/** Get the width of the description in pixel */
		int textWidth = getTextLength(np.getBezeichnung());
		int maxTextWidth = 200;

		String description = np.getBezeichnung();
		String s1 = description;
		String s2 = "";
		boolean twoLines = false;

		/** Case: Description length is > 400px */
		if (textWidth > maxTextWidth * 2) {

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

			/** Get the first line that is not longer than 220px (s1) */
			s1 = chunks[0];
			textLen = getTextLength(s1);
			while (i < chunks.length && textLen < maxTextWidth) {
				temp = s1;
				s1 = s1 + " " + chunks[i];

				textLen = getTextLength(s1);
				i++;

				if (textLen > maxTextWidth) {
					s1 = temp;
					i--;
					break;
				}
			}

			textLen = 0;

			/** Get the second line that is not longer than 220px (s2). Append "..." */
			if (i < chunks.length) {
				s2 = chunks[i];
				textLen = getTextLength(s2);
				i++;
				while (i < chunks.length && textLen < maxTextWidth) {
					temp = s2;
					s2 = s2 + " " + chunks[i];

					textLen = getTextLength(s2);
					i++;

					if (textLen > maxTextWidth) {
						s2 = temp;
						break;
					}
				}
				s2 = s2.substring(0, s2.length() - 3) + "...";
			}
		}

		/** Case: Description length is > 200px */
		else if (textWidth > maxTextWidth) {

			s1 = "";
			twoLines = true;

			/** Split description into two lines */
			String chunks[] = description.split(" ");

			/** Handle Exception: String has no white space */
			if (chunks.length == 1) {

				int textLen = 0;
				while (textLen > maxTextWidth) {
					chunks[0] = chunks[0].substring(0, chunks[0].length() - 1);
					textLen = getTextLength(chunks[0]);
				}
				chunks[0] = chunks[0].substring(0, chunks[0].length() - 3) + "...";
			}

			int textLen = 0;
			int i = 1;
			String temp = "";

			/** Get the first line that is not longer than 220px (s1) */
			s1 = chunks[0];
			textLen = getTextLength(s1);

			while (i < chunks.length && textLen < maxTextWidth) {
				temp = s1;
				s1 = s1 + " " + chunks[i];

				textLen = getTextLength(s1);
				i++;

				if (textLen > maxTextWidth) {
					s1 = temp;
					i--;
					break;
				}
			}
			textLen = 0;

			/** Get the second line that is not longer than 220px (s2). */
			if (i < chunks.length) {
				s2 = chunks[i];
				textLen = getTextLength(s2);
				i++;

				while (i < chunks.length && textLen < maxTextWidth) {
					temp = s2;
					s2 = s2 + " " + chunks[i];

					textLen = getTextLength(s2);
					i++;

					if (textLen > maxTextWidth) {
						s2 = temp;
					}
				}
			} else {
				twoLines = false;
			}
		}

		/** Paint two lines if so. Center the Strings between 65 and 240px */
		if (twoLines) {
			jLabelLine1.setFont(new java.awt.Font("Dialog", 1, 14));
			jLabelLine1.setHorizontalAlignment(SwingConstants.CENTER);
			jLabelLine1.setText(s1);
			npElementRect.add(jLabelLine1);
			jLabelLine1.setBounds(85, 20, 210, 19);

			jLabelLine2.setFont(new java.awt.Font("Dialog", 1, 14));
			jLabelLine2.setHorizontalAlignment(SwingConstants.CENTER);
			jLabelLine2.setText(s2);
			npElementRect.add(jLabelLine2);
			jLabelLine2.setBounds(85, 40, 210, 19);
		} else {
			int middle = (200 - textWidth) / 2;
			if (middle < 0)
				middle = 0;

			jLabelLine1.setFont(new java.awt.Font("Dialog", 1, 14));
			jLabelLine1.setHorizontalAlignment(SwingConstants.CENTER);
			jLabelLine1.setText(s1);
			npElementRect.add(jLabelLine1);
			jLabelLine1.setBounds(85, 30, 210, 19);
		}

		npElementContainer.add(npElementRect);
		npElementRect.setBounds(30, 30, 300, 150);

		/** Writing FAZ */
		jLabelFAZ.setFont(new java.awt.Font("Dialog", 1, 16));
		jLabelFAZ.setText(String.valueOf(np.getFaz()));
		npElementContainer.add(jLabelFAZ);
		jLabelFAZ.setBounds(30, 6, 40, 20);

		/** Writing FEZ */
		jLabelFEZ.setFont(new java.awt.Font("Dialog", 1, 16));
		jLabelFEZ.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabelFEZ.setText(String.valueOf(np.getFez()));
		npElementContainer.add(jLabelFEZ);
		jLabelFEZ.setBounds(290, 6, 40, 20);

		/** Writing SAZ */
		jLabelSAZ.setFont(new java.awt.Font("Dialog", 1, 16));
		jLabelSAZ.setText(String.valueOf(np.getSaz()));
		npElementContainer.add(jLabelSAZ);
		jLabelSAZ.setBounds(30, 182, 40, 20);

		/** Writing SEZ */
		jLabelSEZ.setFont(new java.awt.Font("Dialog", 1, 16));
		jLabelSEZ.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabelSEZ.setText(String.valueOf(np.getSez()));
		npElementContainer.add(jLabelSEZ);
		jLabelSEZ.setBounds(290, 182, 40, 20);

		npElementContainer.setBounds(60, 290, 360, 210);

		return npElementContainer;
	}

	/**
		 * @return Width of the network plan element
		 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns the length of the text in pixel.
	 * @param text
	 * @return
	 */
	public int getTextLength(String text) {
		return (int) Math.round(
			(new Font("Dialog", 0, 14)
				.getStringBounds(text, 0, text.length(), new FontRenderContext(new AffineTransform(), false, false)))
				.getWidth());
	}

	/** Paint the element blue if the mouse moves over it */
	private void npElementContainerMouseMoved(java.awt.event.MouseEvent evt) {
		npElementRect.setBorder(new LineBorder(new java.awt.Color(0, 0, 255), 2));
		jSeparatorBetweenGPandFP.setForeground(Color.BLUE);
		jSeparatorCutMiddle.setForeground(Color.BLUE);
		jSeparatorFromNumToDur.setForeground(Color.BLUE);
		jSeparatorBetweenGPandFP.setForeground(Color.BLUE);
	}

	/** Paint the element to its original color when the mouse moves out of it */
	private void npElementContainerMouseExited(java.awt.event.MouseEvent evt) {

		Color lineColor = new Color(0, 0, 0);
		if (isCriticalPathElement)
			lineColor = new Color(255, 0, 0);

		npElementRect.setBorder(new LineBorder(lineColor, 1));
		jSeparatorBetweenGPandFP.setForeground(lineColor);
		jSeparatorCutMiddle.setForeground(lineColor);
		jSeparatorFromNumToDur.setForeground(lineColor);
		jSeparatorBetweenGPandFP.setForeground(lineColor);
	}
}