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
import java.awt.Rectangle;
import java.beans.PropertyVetoException;

import javax.swing.DefaultDesktopManager;
import javax.swing.JInternalFrame;

/**
 * This class provides a custom desktop manager for 
 * {@link com.tomtessier.scrollabledesktop.BaseDesktopPane BaseDesktopPane}.
 *
 * @author <a href="mailto:tessier@gabinternet.com">Tom Tessier</a>
 * @version 1.0  9-Aug-2001
 */

public class BaseDesktopManager extends DefaultDesktopManager {

      private BaseDesktopPane desktopPane;

     /** 
       *  creates the BaseDesktopManager
       *
       * @param desktopPane a reference to BaseDesktopPane
       */
      public BaseDesktopManager(BaseDesktopPane desktopPane) {
            this.desktopPane = desktopPane;
      }

     /** 
       * maximizes the internal frame to the viewport bounds rather 
       * than the desktop bounds 
       *
       * @param f the internal frame being maximized
       */
      public void maximizeFrame(JInternalFrame f) {

            Rectangle p = desktopPane.getScrollPaneRectangle();
            f.setNormalBounds(f.getBounds());
            setBoundsForFrame(f, p.x, p.y, p.width, p.height);
            try { 
                  f.setSelected(true); 
            } catch (PropertyVetoException pve) {
                  System.out.println(pve.getMessage());
            }

            removeIconFor(f);

     }

      /**
        * insures that the associated toolbar and menu buttons of 
        * the internal frame are activated as well
        *
        * @param f the internal frame being activated
        */
      public void activateFrame(JInternalFrame f) {

            super.activateFrame(f);
            ((BaseInternalFrame)f).selectFrameAndAssociatedButtons();

      }


      /**
        * closes the internal frame and removes any associated button 
        * and menu components
        *
        * @param f the internal frame being closed
        */
      public void closeFrame(JInternalFrame f) {

            super.closeFrame(f);

      // possible to retrieve the associated buttons right here via 
      // f.getAssociatedButton(), and then with a call to getParent() the item 
      // can be directly removed from its parent container, but I find the 
      // below message propogation to DesktopPane a cleaner implementation...

            desktopPane.removeAssociatedComponents((BaseInternalFrame)f);
            desktopPane.resizeDesktop();        

      }


    /* could override iconifyFrame here as well, but much simpler
       to define an EmptyDesktopIconUI look and feel class in BaseDesktopPane */


}
