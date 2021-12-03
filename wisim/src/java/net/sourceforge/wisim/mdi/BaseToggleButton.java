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
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JToggleButton;

/**
 * This class creates a base toggle button. A
 * {@link com.tomtessier.scrollabledesktop.BaseInternalFrame BaseInternalFrame}
 * object is associated with every instance of this class.
 *
 * @author <a href="mailto:tessier@gabinternet.com">Tom Tessier</a>
 * @version 1.0  11-Aug-2001
 */

public class BaseToggleButton extends JToggleButton 
            implements DesktopConstants, FrameAccessorInterface {

      private BaseInternalFrame associatedFrame;
      private Color defaultColor;


    /**
     * creates the BaseToggleButton
     *
     * @param title the title of the button
     */
      public BaseToggleButton(String title) {

            super(title);

            setButtonFormat();
            setToolTipText(title);

            defaultColor = getForeground();

      }

      private void setButtonFormat() {
            Font buttonFont = getFont();
            setFont(new Font(buttonFont.getFontName(), 
                             buttonFont.getStyle(), 
                             buttonFont.getSize()-1));
            setMargin(new Insets(0,0,0,0));
      }

     /** 
       *  sets the associated frame
       *
       * @param associatedFrame the BaseInternalFrame object to associate with 
       * the menu item
       */
      public void setAssociatedFrame(BaseInternalFrame associatedFrame) {
            this.associatedFrame = associatedFrame;
      }

     /** 
       *  returns the associated frame
       *
       * @return the BaseInternalFrame object associated with this menu item
       */
      public BaseInternalFrame getAssociatedFrame() {
            return associatedFrame;
      }

     /** 
       *  flags the contents as "changed" by setting the foreground color to
       * {@link 
       * com.tomtessier.scrollabledesktop.DesktopConstants#CONTENTS_CHANGED_COLOR
       * CONTENTS_CHANGED_COLOR}.
       * Used to notify the user when the contents of an inactive internal frame
       * have changed.
       *
       * @param changed <code>boolean</code> indicating whether contents have 
       * changed
       */
      public void flagContentsChanged(boolean changed) {
            if (changed) {
                  setForeground(CONTENTS_CHANGED_COLOR);
            }
            else {
                  setForeground(defaultColor);
            }
      }


}