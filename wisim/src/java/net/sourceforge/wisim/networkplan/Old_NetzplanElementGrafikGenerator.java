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
import java.awt.Graphics;
import java.awt.Image;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * Class for generating a buffered image that represents
 * one element of the network plan. The element's size is
 * 400x200 px. The description gets centered in the
 * rectangle. If the description is very long it gets splitted into
 * two lines. This provides enough space for a longer
 * description. But there is a limit. If the second
 * line is too long it gets cut. 
 * 
 * @author Benjamin Pasero
 * @version 0.2a
 */
public class Old_NetzplanElementGrafikGenerator {

	private BufferedImage npElem;
	private Graphics g;
	private Font font;
	private int height;
	private static final int width = 300;

	/** Creates a new instance of IconGenerater */
	public Old_NetzplanElementGrafikGenerator() {
		npElem = new BufferedImage(400, 200, 2);
		g = npElem.getGraphics();
	}

	public Image generateNetzplanelement(NetzplanElement np) {

		/** Get the width of the description in pixel */
		int textWidth = getTextLength(np.getBezeichnung());

		String description = np.getBezeichnung();
		String s1 = description;
		String s2 = "";
		boolean twoLines = false;

		/** Case: Description length is > 400px */
		if (textWidth > 400) {

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
			while (i < chunks.length && textLen < 220) {
				temp = s1;
				s1 = s1 + " " + chunks[i];

				textLen = getTextLength(s1);
				i++;

				if (textLen > 220) {
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
				while (i < chunks.length && textLen < 220) {
					temp = s2;
					s2 = s2 + " " + chunks[i];

					textLen = getTextLength(s2);
					i++;

					if (textLen > 220) {
						s2 = temp;
						break;
					}
				}
				s2 = s2.substring(0, s2.length() - 3) + "...";
			}
		}

		/** Case: Description length is > 240px */
		else if (textWidth > 220) {

			s1 = "";
			twoLines = true;

			/** Split description into two lines */
			String chunks[] = description.split(" ");

			/** Handle Exception: String has no white space */
			if (chunks.length == 1) {

				int textLen = 0;
				while (textLen > 220) {
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

			while (i < chunks.length && textLen < 220) {
				temp = s1;
				s1 = s1 + " " + chunks[i];

				textLen = getTextLength(s1);
				i++;

				if (textLen > 220) {
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

				while (i < chunks.length && textLen < 220) {
					temp = s2;
					s2 = s2 + " " + chunks[i];

					textLen = getTextLength(s2);
					i++;

					if (textLen > 220) {
						s2 = temp;
					}
				}
			} else {
				twoLines = false;
			}
		}

		/** Draw whole rectangle of the element */
//		if (np.isSelected()) {		
//			g.setColor(Color.LIGHT_GRAY);
//			
//			g.fillRect(0, 30, width, 100);
//
//			g.setColor(Color.BLACK);
//			g.drawRect(0, 30, width, 100);
//
//		} else {
//			/** Paint critical path element */
//			if (np.isCriticalPath())
//				g.setColor(Color.RED);
//			else
//				g.setColor(Color.BLACK);
//			g.drawRect(0, 30, width, 100);
//
//			/** Paint critical path element */
//			if (np.isCriticalPath())
//				g.setColor(Color.RED);
//			else
//				g.setColor(Color.BLACK);
//		}

		/** Vertical line between number & duration */
		g.drawLine(60, 30, 60, 130);

		/** Horizontal Line that cuts the rect into halves */
		g.drawLine(0, 80, width, 80);

		/** Vertical line between total float and free float */
		g.drawLine(175, 80, 175, 130);

		/** Paint critical path element */
		if (np.isCriticalPath())
			g.setColor(Color.RED);
		else
			g.setColor(Color.BLACK);
			
		/** Connection-line between two elements on top */
		if (!np.isStartElem())
			g.drawLine(width / 2, 0, width / 2, 30);

		/** Connection-line between two elements on bottom */
		if (!np.isEndElem())
			g.drawLine(width / 2, 130, width / 2, 160);

		/** Drawing the number */
		g.setColor(Color.BLACK);
		g.setFont(new Font("SansSerif", 0, 30));

		if (String.valueOf(np.getNummer()).matches("^[0-9]{2}$"))
			g.drawString(String.valueOf(np.getNummer()), 12, 66);
		else
			g.drawString(String.valueOf(np.getNummer()), 22, 66);

		/** Drawing the duration */
		g.setFont(new Font("SansSerif", 0, 25));

		if (String.valueOf(np.getDauer()).length() < 4)
			g.drawString(String.valueOf(np.getDauer()), 12, 116);
		else
			g.drawString(String.valueOf(np.getDauer()), 5, 116);

		g.setFont(new Font("SansSerif", 0, 15));

		/** Drawing earliest point in time of begin */
		g.drawString(String.valueOf(np.getFaz()), 0, 25);

		/** Drawing earliest point in time of finish */
		g.drawString(String.valueOf(np.getFez()), 273, 25);

		/** Drawing latest point in time of begin */
		g.drawString(String.valueOf(np.getSaz()), 0, 147);

		/** Drawing latest point in time of finish */
		g.drawString(String.valueOf(np.getSez()), 273, 147);

		/** Drawing total float and free float */
		g.setFont(new Font("SansSerif", 0, 25));

		if (String.valueOf(np.getGp()).length() < 4)
			g.drawString(String.valueOf(np.getGp()), 102, 116);
		else
			g.drawString(String.valueOf(np.getGp()), 95, 116);

		if (String.valueOf(np.getFp()).length() < 4)
			g.drawString(String.valueOf(np.getFp()), 222, 116);
		else
			g.drawString(String.valueOf(np.getFp()), 213, 116);

		/** Drawing the description */
		g.setFont(new Font("SansSerif", 0, 15));

		/** Paint two lines if so. Center the Strings between 65 and 240px */
		if (twoLines) {
			int middle = (220 - getTextLength(s1)) / 2;
			if (middle < 0)
				middle = 0;
			g.drawString(s1, 65 + middle, 50);

			middle = (220 - getTextLength(s2)) / 2;
			if (middle < 0)
				middle = 0;

			g.drawString(s2, 65 + middle, 70);
		} else {
			int middle = (220 - textWidth) / 2;
			if (middle < 0)
				middle = 0;
			g.drawString(s1, 65 + middle, 62);
		}
		return npElem;
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
			(new Font("SansSerif", 0, 15)
				.getStringBounds(text, 0, text.length(), new FontRenderContext(new AffineTransform(), false, false)))
				.getWidth());
	}
}