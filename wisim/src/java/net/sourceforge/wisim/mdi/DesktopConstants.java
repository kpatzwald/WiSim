/*   ********************************************************************   **
**   Copyright notice                                                       **
**                                                                          **
**   (c) 2003 WiSim Development Team					                              **
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

package net.sourceforge.wisim.mdi;
import java.awt.Color;

/**
 * This interface provides a set of reusable constants for use by 
 * other classes in the system.
 *
 * @author <a href="mailto:tessier@gabinternet.com">Tom Tessier</a>
 * @version 1.0  29-Jul-2001
 */
public interface DesktopConstants {

      // all variables declared here are automatically public static final

      /** maximum number of internal frames allowed */
      int MAX_FRAMES = 20;

      /** default x offset of first frame in cascade mode, relative to desktop */
      int X_OFFSET = 30;

      /** default y offset of first frame in cascade mode, relative to desktop */
      int Y_OFFSET = 30;

      /** minimum width of frame toolbar buttons */
      int MINIMUM_BUTTON_WIDTH = 30;

      /** maximum width of frame toolbar buttons */
      int MAXIMUM_BUTTON_WIDTH = 120;

      /** the foreground color of inactive buttons whose associated frame 
            contents have changed */
      Color CONTENTS_CHANGED_COLOR = Color.red;

}