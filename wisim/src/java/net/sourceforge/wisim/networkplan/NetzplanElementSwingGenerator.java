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
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 * @author benjamin.pasero
 * @version 0.5a
 */
public class NetzplanElementSwingGenerator extends JPanel {

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
	private JSeparator jSeparatorConTop;
	private JSeparator jSeparatorConBottom;
	private JPanel npElementRect;
	private boolean isCriticalPathElement;
	private NetzplanElement currentNpElem;
	private Font innerTextFont;
	private Font outerTextFont;
	private Font descriptionFont;

	/** Size of the transparent rectangle holding the networkplan element */
	private static final int width = 360;
	private static final int height = 280;

	/** Max. size of one textline in the description-box */
	private static final int maxTextWidth = 200;

	/**
	 * Paints a new networkplan element in a JLabel
	 */
	public NetzplanElementSwingGenerator() {
		npElementRect = new JPanel();
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
		innerTextFont = new Font("Dialog", 1, 24);
		outerTextFont = new Font("Dialog", 1, 16);
		descriptionFont = new Font("Dialog", 1, 14);

		//		npElementRect.addMouseMotionListener(new MouseMotionAdapter() {
		//			public void mouseMoved(java.awt.event.MouseEvent evt) {
		//				npElementRectMouseMoved(evt);				
		//			}
		//		});
		//
		//		npElementRect.addMouseListener(new MouseAdapter() {
		//			public void mouseExited(java.awt.event.MouseEvent evt) {
		//				npElementRectMouseExited(evt);				
		//			}
		//
		//			public void mouseClicked(MouseEvent evt) {
		//				npElementRectMouseClicked(evt);
		//			}
		//		});
	}

	/**
	 * Paints a new networkplan element in a JLabel
	 * @param np
	 * @return generated networkplan element
	 */
	public JPanel generateNetzplanelement(NetzplanElement np) {

		currentNpElem = np;

		/** Container that holds all swing-elements */
		this.setLayout(null);

		/** Alpha is set to 100, makes this JPanel transparent */
		Color c = new Color(255, 255, 255, 0);

		this.setBackground(c);
		//this.setPreferredSize(new Dimension(widht, height));

		/** Rectangle of the network plan element */
		npElementRect.setLayout(null);

		npElementRect.setBackground(new Color(255, 255, 255));

		Color lineColor = new Color(0, 0, 0);
		if (np.isCriticalPath()) {
			lineColor = new Color(255, 0, 0);
			isCriticalPathElement = true;
		}

		npElementRect.setBorder(new LineBorder(lineColor));

		//this.setPreferredSize(new Dimension(300, 150));

		/** Connection-line between two elements on top */
		if (!np.isStartElem()) {
			jSeparatorConTop.setForeground(lineColor);
			jSeparatorConTop.setOrientation(SwingConstants.VERTICAL);
			this.add(jSeparatorConTop);
			jSeparatorConTop.setBounds(width / 2, 0, width / 2, 65);
		}

		/** Connection-line between two elements on bottom */
		if (!np.isEndElem()) {
			jSeparatorConBottom.setForeground(lineColor);
			jSeparatorConBottom.setOrientation(SwingConstants.VERTICAL);
			this.add(jSeparatorConBottom);
			jSeparatorConBottom.setBounds(width / 2, 215, width / 2, 270);
		}

		/** Writing GP */
		jLabelGP.setFont(innerTextFont);
		jLabelGP.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelGP.setText(String.valueOf(np.getGp()));
		jLabelGP.setBorder(new LineBorder(lineColor));
		npElementRect.add(jLabelGP);
		jLabelGP.setBounds(79, 70, 110, 80);

		/** Writing FP */
		jLabelFP.setFont(innerTextFont);
		jLabelFP.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelFP.setText(String.valueOf(np.getFp()));
		jLabelFP.setBorder(new LineBorder(lineColor));
		npElementRect.add(jLabelFP);
		jLabelFP.setBounds(188, 70, 112, 80);

		/** Writing Duration */
		jLabelDuration.setFont(innerTextFont);
		jLabelDuration.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelDuration.setText(String.valueOf(np.getDuration()));
		jLabelDuration.setBorder(new LineBorder(lineColor));
		npElementRect.add(jLabelDuration);
		jLabelDuration.setBounds(0, 70, 80, 80);

		/** Writing Number */
		jLabelNumber.setFont(innerTextFont);
		jLabelNumber.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelNumber.setText(String.valueOf(np.getNumber()));
		jLabelNumber.setBorder(new LineBorder(lineColor));
		npElementRect.add(jLabelNumber);
		jLabelNumber.setBounds(0, 0, 80, 71);

		/** Get the width of the description in pixel */
		int textWidth = getTextLength(np.getDescription(), descriptionFont);

		String description = np.getDescription();
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

			/** Get the first line that is not longer than 200px (s1) */
			s1 = chunks[0];
			textLen = getTextLength(s1, descriptionFont);
			while (i < chunks.length && textLen < maxTextWidth) {
				temp = s1;
				s1 = s1 + " " + chunks[i];

				textLen = getTextLength(s1, descriptionFont);
				i++;

				if (textLen > maxTextWidth) {
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
				while (i < chunks.length && textLen < maxTextWidth) {
					temp = s2;
					s2 = s2 + " " + chunks[i];

					textLen = getTextLength(s2, descriptionFont);
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

			while (i < chunks.length && textLen < maxTextWidth) {
				temp = s1;
				s1 = s1 + " " + chunks[i];

				textLen = getTextLength(s1, descriptionFont);
				i++;

				if (textLen > maxTextWidth) {
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

				while (i < chunks.length && textLen < maxTextWidth) {
					temp = s2;
					s2 = s2 + " " + chunks[i];

					textLen = getTextLength(s2, descriptionFont);
					i++;

					if (textLen > maxTextWidth) {
						s2 = temp;
					}
				}
			} else {
				twoLines = false;
			}
		}

		/** Paint two lines if so. Center the Strings */
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
			int middle = (200 - textWidth) / 2;
			if (middle < 0)
				middle = 0;

			jLabelLine1.setFont(descriptionFont);
			jLabelLine1.setHorizontalAlignment(SwingConstants.CENTER);
			jLabelLine1.setText(s1);
			npElementRect.add(jLabelLine1);
			jLabelLine1.setBounds(85, 30, 210, 19);
		}

		this.add(npElementRect);
		npElementRect.setBounds(30, 65, 300, 150);

		/** Writing FAZ */
		jLabelFAZ.setFont(outerTextFont);
		jLabelFAZ.setText(String.valueOf(np.getFaz()));
		this.add(jLabelFAZ);
		jLabelFAZ.setBounds(30, 41, 40, 20);

		/** Writing FEZ */
		jLabelFEZ.setFont(outerTextFont);
		jLabelFEZ.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabelFEZ.setText(String.valueOf(np.getFez()));
		this.add(jLabelFEZ);
		jLabelFEZ.setBounds(290, 41, 40, 20);

		/** Writing SAZ */
		jLabelSAZ.setFont(outerTextFont);
		jLabelSAZ.setText(String.valueOf(np.getSaz()));
		this.add(jLabelSAZ);
		jLabelSAZ.setBounds(30, 217, 40, 20);

		/** Writing SEZ */
		jLabelSEZ.setFont(outerTextFont);
		jLabelSEZ.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabelSEZ.setText(String.valueOf(np.getSez()));
		this.add(jLabelSEZ);
		jLabelSEZ.setBounds(290, 217, 40, 20);

		//npElementContainer.setBounds(60, 290, 360, 275);
		return this;
	}

	/**
	 * @return Width of the network plan element
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return Height of the network plan element
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Returns the length of the description text in pixel.
	 * @param text The text to get the length from
	 * @param font Font that the text is written with
	 * @return Length of the text in pixel
	 */
	public int getTextLength(String text, Font font) {
		return (int) Math.round(
			(font.getStringBounds(text, 0, text.length(), new FontRenderContext(new AffineTransform(), false, false)))
				.getWidth());
	}

	/** Paint the element blue if the mouse moves over it */
	private void npElementRectMouseMoved(java.awt.event.MouseEvent evt) {
		npElementRect.setBorder(new LineBorder(new Color(0, 0, 255), 2));
		jLabelGP.setBorder(new LineBorder(new Color(0, 0, 255)));
		jLabelFP.setBorder(new LineBorder(new Color(0, 0, 255)));
		jLabelDuration.setBorder(new LineBorder(new Color(0, 0, 255)));
		jLabelNumber.setBorder(new LineBorder(new Color(0, 0, 255)));
	}

	/** Paint the element to its original color when the mouse moves out of it */
	private void npElementRectMouseExited(java.awt.event.MouseEvent evt) {

		Color lineColor = new Color(0, 0, 0);
		if (isCriticalPathElement)
			lineColor = new Color(255, 0, 0);

		npElementRect.setBorder(new LineBorder(lineColor, 1));
		jLabelGP.setBorder(new LineBorder(lineColor));
		jLabelFP.setBorder(new LineBorder(lineColor));
		jLabelDuration.setBorder(new LineBorder(lineColor));
		jLabelNumber.setBorder(new LineBorder(lineColor));
	}

	/** Something happens when the user clicks the element ;-) */
	private void npElementRectMouseClicked(java.awt.event.MouseEvent evt) {
		String message = String.valueOf(currentNpElem.getIndex());
		JOptionPane.showMessageDialog(this, "This is activity number " + message);
	}
}