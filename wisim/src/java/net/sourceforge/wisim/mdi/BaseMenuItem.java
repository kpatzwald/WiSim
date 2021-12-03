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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * This class creates a generic base menu item. ActionListener, mnemonic, 
 * keyboard shortcut, and title are set via the constructor.
 *
 * @author <a href="mailto:tessier@gabinternet.com">Tom Tessier</a>
 * @version 1.0  29-Jan-2001
 */

public class BaseMenuItem extends JMenuItem  {


    /**
     * creates the BaseMenuItem
     *
     * @param listener the action listener to assign
     * @param itemTitle the title of the item
     * @param mnemonic the mnemonic used to access the menu
     * @param shortcut the keyboard shortcut used to access the menu. 
     *      -1 indicates no shortcut.
     */
      public BaseMenuItem(ActionListener listener, 
                             String itemTitle, 
                             int mnemonic, 
                             int shortcut) {

            super(itemTitle, mnemonic);


            // set the alt-Shortcut accelerator
            if (shortcut != -1) {
                  setAccelerator(
                        KeyStroke.getKeyStroke(
                                shortcut, ActionEvent.ALT_MASK)); 
            }

            setActionCommand(itemTitle);
            addActionListener(listener);

      }

}