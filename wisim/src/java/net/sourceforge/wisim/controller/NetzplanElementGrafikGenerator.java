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
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * Class for generating a buffered image that represents
 * one element of the network plan. The element's size is
 * 400x200 px. 
 * If the description is very long it is splitted into
 * two lines. This provides enough space for a longer
 * description. But there is a limit. If one of the two
 * lines is too long it gets cut. In some cases the split
 * and cut is buggy working with very large descriptions.
 * @author Benjamin Pasero
 * @version 0.1a
 */
public class NetzplanElementGrafikGenerator {

	private BufferedImage npElem;
	private Graphics g;
	private Font font;
	private int scaleX;
	private int width;
	private int height;

	/** Creates a new instance of IconGenerater */
	public NetzplanElementGrafikGenerator() {
		npElem = new BufferedImage(400, 200, 2);
		scaleX = 0;
		g = npElem.getGraphics();
	}

	public Image generateNetzplanelement(NetzplanElement np) {

		scaleX = (np.getBezeichnung().length() - 10) * 6;

		String bezeichnung = np.getBezeichnung();
		String s1 = "";
		String s2 = "";
		boolean twoLines = false;

		if (scaleX < 0) {
			scaleX = 0;
		}

		/** 
		 * Splitting the description into two lines and
		 * cut the longer line if scaleX is over 199
		 */
		else if (scaleX > 199) {

			int textMiddle = np.getBezeichnung().length() / 2;

			/** Search for whitespace left to the middle */
			int a = textMiddle;
			int stepsLeft = 0;
			while (bezeichnung.charAt(a) != 32) {
				a--;
				stepsLeft++;
			}

			/** Search for whitespace right to the middle */
			int b = textMiddle;
			int stepsRight = 0;
			while (bezeichnung.charAt(b) != 32) {
				b++;
				stepsRight++;
			}

			/** Split the String at the left whitespace of the Middle */
			if (stepsLeft < stepsRight) {
				s1 = bezeichnung.substring(0, a);
				s2 = bezeichnung.substring(a + 1, bezeichnung.length());

				/** Split the String at the right whitespace of the Middle */
			} else {
				s1 = bezeichnung.substring(0, b);
				s2 = bezeichnung.substring(b + 1, bezeichnung.length());
			}

			twoLines = true;

			String longerLine = "";

			/** Determine the longer line of the splitted string */
			if (s1.length() > s2.length()) {
				scaleX = (s1.length() - 10) * 6;
				longerLine = s1;
			} else {
				scaleX = (s2.length() - 10) * 6;
				longerLine = s2;
			}

			String tempLongerLine = longerLine;

			/** If the longer line is still over scaleX of 199, cut it */
			if (scaleX > 199) {
				while (scaleX > 199) {
					longerLine = longerLine.substring(0, longerLine.length() - 1);
					scaleX = (longerLine.length() - 10) * 6;
				}
				longerLine = longerLine.substring(0, longerLine.length() - 3) + "...";
				scaleX = 199;

				/** Set the cutted line */
				if (s1.length() > s2.length())
					s1 = longerLine;
				else
					s2 = longerLine;
			}
		}

		if (np.isCriticalPath())
			g.setColor(Color.RED);
		else
			g.setColor(Color.BLACK);

		/** Draw whole rectangle of the element */
		g.drawRect(0, 30, 200 + scaleX, 100);

		/** Vertical line between number & duration */
		g.drawLine(60, 30, 60, 130);

		/** Horizontal Line that cuts the rect into halves */
		g.drawLine(0, 80, 200 + scaleX, 80);

		/** Vertical line between total float and free float */
		g.drawLine(130 + scaleX / 2 - 5, 80, 130 + scaleX / 2 - 5, 130);

		/** Connection-line between two elements on top */
		if (!np.isStartElem())
			g.drawLine(140, 0, 140, 30);

		/** Connection-line between two elements on bottom */
		if (!np.isEndElem())
			g.drawLine(140, 130, 140, 160);

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
		g.drawString(String.valueOf(np.getFez()), 173 + scaleX, 25);

		/** Drawing latest point in time of begin */
		g.drawString(String.valueOf(np.getSaz()), 0, 147);

		/** Drawing latest point in time of finish */
		g.drawString(String.valueOf(np.getSez()), 173 + scaleX, 147);

		/** Drawing total float and free float */
		g.setFont(new Font("SansSerif", 0, 25));

		if (String.valueOf(np.getGp()).length() < 4)
			g.drawString(String.valueOf(np.getGp()), 77 + scaleX / 4, 116);
		else
			g.drawString(String.valueOf(np.getGp()), 70 + scaleX / 4, 116);

		if (String.valueOf(np.getFp()).length() < 4)
			g.drawString(String.valueOf(np.getFp()), 147 + (scaleX / 4) * 3, 116);
		else
			g.drawString(String.valueOf(np.getFp()), 138 + (scaleX / 4) * 3, 116);

		/** Drawing the description */
		g.setFont(new Font("SansSerif", 0, 15));

		/** Paint two lines if so */
		if (twoLines) {
			g.drawString(s1, 90, 50);
			g.drawString(s2, 90, 70);
		} else {
			g.drawString(bezeichnung, 90, 63);
		}

		return npElem;
	}
}