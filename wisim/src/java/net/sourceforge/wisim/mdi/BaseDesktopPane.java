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

import java.awt.Rectangle;

import javax.swing.JDesktopPane;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

/**
 * This class provides a custom desktop pane.
 * The drag mode is set to 
 * {@link javax.swing.JDesktopPane#OUTLINE_DRAG_MODE outline}
 * by default, the desktop manager is
 * set to {@link com.tomtessier.scrollabledesktop.BaseDesktopManager 
 * BaseDesktopManager}, and the look and feel DesktopIconUI is 
 * replaced by the blank icon generator, 
 * {@link com.tomtessier.scrollabledesktop.EmptyDesktopIconUI EmptyDesktopIconUI}.
 *
 * @author <a href="mailto:tessier@gabinternet.com">Tom Tessier</a>
 * @version 1.0  9-Aug-2001
 */

public class BaseDesktopPane extends JDesktopPane  {

      private DesktopScrollPane desktopScrollpane;

     /** 
       *  creates the BaseDesktopPane
       *
       * @param desktopScrollpane a reference to DesktopScrollPane
       */
      public BaseDesktopPane(DesktopScrollPane desktopScrollpane) {

        this.desktopScrollpane = desktopScrollpane;
        
        // setup the UIManager to replace the look and feel DesktopIconUI
        // with an empty one (EmptyDesktopIconUI) so that the desktop icon 
        // for the internal frame is not painted
        // (ie: when internal frame iconified...) 

        UIDefaults defaults = UIManager.getDefaults(); 
        defaults.put( "DesktopIconUI",
                   getClass().getPackage().getName() + ".EmptyDesktopIconUI"); 

        // set up some defaults
        setDesktopManager(new BaseDesktopManager(this));

// pre-1.3 code (has no effect in JFC implementations before Swing 1.1.1 Beta 1)
//      putClientProperty("JDesktopPane.dragMode", "outline"); 
//
// replace the following line with the above to execute under JDK 1.2
        setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);

      }

     /** 
       * returns the view rectangle associated with the 
       * {@link com.tomtessier.scrollabledesktop.DesktopScrollPane DesktopScrollPane}
       * viewport
       *
       * @return the Rectangle object of the viewport
       */
      public Rectangle getScrollPaneRectangle() {
            return desktopScrollpane.getViewport().getViewRect();
      }

      /** 
        * propogates the removeAssociatedComponents() call to 
        * {@link com.tomtessier.scrollabledesktop.DesktopScrollPane DesktopScrollPane}
        *
        * @param f the internal frame whose associated components are to be removed
        */
      public void removeAssociatedComponents(BaseInternalFrame f) {
            desktopScrollpane.removeAssociatedComponents(f);
      }

      /** 
        * propogates the resizeDesktop() call to 
        * {@link com.tomtessier.scrollabledesktop.DesktopScrollPane DesktopScrollPane}
        */
      public void resizeDesktop() {
            desktopScrollpane.resizeDesktop();
      }


}