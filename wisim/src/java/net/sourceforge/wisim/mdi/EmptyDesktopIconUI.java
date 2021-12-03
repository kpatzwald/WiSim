/*   ********************************************************************   **
**   Copyright notice                                                       **
**                                                                          **
**   (c) 2003 WiSim Development Team					                              **
**   https://github.com/kpatzwald/WiSim   			                                  **
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
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.DesktopIconUI;


/**
 * This class provides an empty DesktopIconUI for 
 * {@link com.tomtessier.scrollabledesktop.BaseDesktopPane BaseDesktopPane}.
 *
 * @author <a href="mailto:tessier@gabinternet.com">Tom Tessier</a>
 * @version 1.0  29-Jul-2001
 */

public class EmptyDesktopIconUI extends DesktopIconUI {

  /**
    * stores the instance of this class. Used by 
    * {@link com.tomtessier.scrollabledesktop.EmptyDesktopIconUI#createUI(JComponent)
    * createUI}.
    */
  protected static EmptyDesktopIconUI desktopIconUI;


  /**
    * creates the EmptyDesktopIconUI object
    *
    * @param c the reference to the JComponent object required by createUI
    */
  public static ComponentUI createUI (JComponent c) {
    if (desktopIconUI == null) {
      desktopIconUI = new EmptyDesktopIconUI();
    }
    return desktopIconUI;
  }


  /**
    * overrides the paint method with a blank routine so that no 
    * component is displayed when an internal frame is iconified
    *
    * @param g the reference to the Graphics object used to paint the desktop
    */
  protected void paint(Graphics g) {}

}
