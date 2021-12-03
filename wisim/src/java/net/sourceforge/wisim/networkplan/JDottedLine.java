/*   ********************************************************************   **
**   Copyright notice                                                       **
**                                                                          **
**   (c) 2003 WiSim Development Team					                    					**
**   https://github.com/kpatzwald/WiSim   			                            			**
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

import java.awt.Graphics;
import javax.swing.JSeparator;

/**
 * Class extends JSeparator and draws a dotted line.
 * @author Benjamin Pasero
 * @version 0.7a
 */
public class JDottedLine extends JSeparator {

	/** Space between two lines */
	public static final int GRANULATION = 5;

	/** Paint a dotted line in dependance of the orientation */
	public void paintComponent(Graphics g) {
		if (getOrientation() == JSeparator.VERTICAL) {

			int height = (int) getSize().getHeight();
			int progress = 0;
			int a = 0;
			progress = GRANULATION;

			while (progress < height) {
				if (a % 2 == 1)
					g.drawLine(0, progress - GRANULATION, 0, progress);

				progress += GRANULATION;
				a++;
			}
			
			/** For the corners */
			g.drawLine(0, height - 3, 0, height);
			
		} else if (getOrientation() == JSeparator.HORIZONTAL){

			int width = (int) getSize().getWidth();
			int progress = 0;
			int a = 0;
			progress = GRANULATION;

			while (progress < width) {
				if (a % 2 == 1)
					g.drawLine(progress - GRANULATION, 0, progress, 0);

				progress += GRANULATION;
				a++;
			}
			
			/** For the corners */
			g.drawLine(width - 3, 0, width, 0);
		
		}
	}
}