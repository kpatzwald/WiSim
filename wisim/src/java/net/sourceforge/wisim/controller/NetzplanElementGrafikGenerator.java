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
 * The generator is not yet complete dynamic. If the network
 * plan element's description is too long, it gets cut.
 * Line-wrap for longer descriptions will be implemented in
 * next versions ("scaleX" is a temporary solution for this
 * problem).
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
		if (scaleX < 0)
			scaleX = 0;

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

		g.drawString(np.getBezeichnung(), 90, 63);

		return npElem;
	}
}