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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;

/**
 * This class provides the optional "Window" menu for the scrollable desktop.
 *
 * @author <a href="mailto:tessier@gabinternet.com">Tom Tessier</a>
 * @version 1.0  11-Aug-2001
 */

public class DesktopMenu extends JMenu implements ActionListener {

	private DesktopMediator desktopMediator;

	private boolean tileMode;
	private int baseItemsEndIndex;
	//private ButtonGroup frameRadioButtonMenuItemGroup;
	private Collection buttons;

	/**
	 * creates the DesktopMenu object
	 *
	 * @param desktopMediator a reference to the DesktopMediator object
	 */
	public DesktopMenu(DesktopMediator desktopMediator) {
		this(desktopMediator, false);
	}

	/**
	 * creates the DesktopMenu object with the specified tileMode
	 *
	 * @param desktopMediator a reference to the DesktopMediator object
	 * @param tileMode the tile mode to use (<code>true</code> = tile 
	 *      internal frames, <code>false</code> = cascade internal frames)
	 */
	public DesktopMenu(DesktopMediator desktopMediator, boolean tileMode) {

		super("Fenster");
		
		setMnemonic(KeyEvent.VK_W);
		setMnemonic('F');

		this.desktopMediator = desktopMediator;
		this.tileMode = tileMode;

		buttons = (Collection) new Vector();
		//          frameRadioButtonMenuItemGroup = new ButtonGroup();

		new ConstructWindowMenu(this, desktopMediator, tileMode);

		// set the default item count (ie: number of items comprising
		// current menu contents)
		baseItemsEndIndex = getItemCount();

	}

	/** 
	  * adds a 
	  * {@link com.tomtessier.scrollabledesktop.BaseRadioButtonMenuItem
	  * BaseRadioButtonMenuItem} to the menu and associates it with an internal frame
	  *
	  * @param associatedFrame the internal frame to associate with the menu item
	  */
	public void add(BaseInternalFrame associatedFrame) {

		int displayedCount = getItemCount() - baseItemsEndIndex + 1;
		int currentMenuCount = displayedCount;

		// compute the key mnemonic based upon the currentMenuCount
		if (currentMenuCount > 9) {
			currentMenuCount /= 10;
		}

		BaseRadioButtonMenuItem menuButton = new BaseRadioButtonMenuItem(this, displayedCount + " " + associatedFrame.getTitle(), KeyEvent.VK_0 + currentMenuCount, -1, true, associatedFrame);

		associatedFrame.setAssociatedMenuButton(menuButton);

		add(menuButton);
		//        frameRadioButtonMenuItemGroup.add(menuButton);
		buttons.add(menuButton);

		if (getSelectedButton() != null)
		{
			getSelectedButton().setSelected(false);
		}
		
		menuButton.setSelected(true); // and reselect here, so that the 
		// buttongroup recognizes the change         

	}

	/**
	  * removes the specified radio menu button from the menu
	  *
	  * @param menuButton the JRadioButtonMenuItem to remove
	  */
	public void remove(JRadioButtonMenuItem menuButton) {
		//          frameRadioButtonMenuItemGroup.remove(menuButton);
		buttons.remove(menuButton);
		super.remove(menuButton);

		// cannot simply remove the radio menu button, as need to renumber the
		// keyboard shortcut keys as well. Hence, a call to refreshMenu is in order...

		refreshMenu(); // refresh the mnemonics associated with the other items
	}

	private void refreshMenu() {
		// refresh the associated mnemonics, so that the keyboard shortcut 
		// keys are properly renumbered...

		// get an enumeration to the elements of the current button group
		Iterator it = buttons.iterator();

		int displayedCount = 1;
		int currentMenuCount = 0;

		while (it.hasNext()) {
			BaseRadioButtonMenuItem b = (BaseRadioButtonMenuItem) it.next();

			// compute the key mnemonic based upon the currentMenuCount
			currentMenuCount = displayedCount;
			if (currentMenuCount > 9) {
				currentMenuCount /= 10;
			}
			b.setMnemonic(KeyEvent.VK_0 + currentMenuCount);
			b.setText(displayedCount + " " + b.getAssociatedFrame().getTitle());
			displayedCount++;
		}
	}
	
	public BaseRadioButtonMenuItem getSelectedButton ()
		{
			BaseRadioButtonMenuItem b = null;
			Iterator it = buttons.iterator();
			while (it.hasNext())
			{
				b = (BaseRadioButtonMenuItem) it.next();
				if (b.isSelected())
					return b;
			}
			return b;
		}

	/**
	  * propogates the actionPerformed menu event to DesktopMediator
	  *
	  * @param e the ActionEvent to propogate
	  */
	public void actionPerformed(ActionEvent e) {
		desktopMediator.actionPerformed(e);
	}

}