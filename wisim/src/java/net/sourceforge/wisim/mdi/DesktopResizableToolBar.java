/*   ********************************************************************   **
**   Copyright notice                                                       **
**                                                                          **
**   (c) 2003-2021 WiSim Development Team                                   **
**   https://github.com/kpatzwald/WiSim                                     **
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
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * This class provides the resizable toolbar for the scrollable desktop.
 *
 * @author <a href="mailto:tessier@gabinternet.com">Tom Tessier</a>
 * @version 1.0  11-Aug-2001
 */


public class DesktopResizableToolBar extends ResizableToolBar 
            implements DesktopConstants, ActionListener  {

      private DesktopMediator desktopMediator;


    /**
     * creates the DesktopResizableToolBar object
     *
     * @param desktopMediator a reference to the DesktopMediator object
     */
      public DesktopResizableToolBar(DesktopMediator desktopMediator) {

            super(MINIMUM_BUTTON_WIDTH, MAXIMUM_BUTTON_WIDTH);

            this.desktopMediator = desktopMediator;

            // prepare test button
            BaseToggleButton testButton = new BaseToggleButton("test");

            // now add a button-sized separator to the toolBar so that
            // the layout manager can properly setup
            addSeparator(new Dimension(0,testButton.getMinimumSize().height));

      }

     /** 
       * creates a BaseToggleButton and adds it to the toolbar
       *
       * @param title the title of the toggle button
       *
       * @return the toggle button that was created
       */
      public BaseToggleButton add(String title) {

            BaseToggleButton toolButton = 
                  new BaseToggleButton(" " + title + " ");
            toolButton.addActionListener(this);

            super.add(toolButton);

            return toolButton;
            
      }


      /**
        * propogates actionPerformed button event to DesktopMediator
        *
        * @param e the ActionEvent to propogate
        */
      public void actionPerformed(ActionEvent e) {
            desktopMediator.actionPerformed(e);
      }

}