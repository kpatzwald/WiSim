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
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * This class provides common Component and Action Listeners for
 * other objects in the system.
 *
 * @author <a href="mailto:tessier@gabinternet.com">Tom Tessier</a>
 * @version 1.0  11-Aug-2001
 */

public class DesktopListener implements ComponentListener, ActionListener {

	private DesktopMediator desktopMediator;

	/**
	 * creates the DesktopListener object.
	 *
	 * @param desktopMediator a reference to the DesktopMediator object
	 */
	public DesktopListener(DesktopMediator desktopMediator) {
		this.desktopMediator = desktopMediator;
	}

	///
	// respond to component events...
	///

	/** 
	* updates the preferred size of the desktop when either an internal frame
	* or the scrollable desktop pane itself is resized
	*
	* @param e the ComponentEvent
	*/
	public void componentResized(ComponentEvent e) {
		desktopMediator.resizeDesktop();
	}

	/**
	* revalidates the desktop to ensure the viewport has the proper 
	* height/width settings when a new component is shown upon the desktop
	*
	* @param e the ComponentEvent
	*/
	public void componentShown(ComponentEvent e) {
		desktopMediator.revalidateViewport();
	}

	/**
	* updates the preferred size of the desktop when a component is moved
	*
	* @param e the ComponentEvent
	*/
	public void componentMoved(ComponentEvent e) {
		desktopMediator.resizeDesktop();
	}

	/**
	* interface placeholder
	*
	* @param e the ComponentEvent
	*/
	public void componentHidden(ComponentEvent e) {
	}

	///
	// respond to action events...
	///

	/**
	  * common actionPerformed method that responds to both button 
	  * and menu events. 
	  * If no action command provided in the ActionEvent, selects 
	  * the frame associated with the current button / menu item (if any). 
	  *
	  * @param e the ActionEvent 
	  */
	public void actionPerformed(ActionEvent e) {

		String actionCmd = e.getActionCommand();

		if (actionCmd.equals("Tile")) {
			desktopMediator.tileInternalFrames();
		} else if (actionCmd.equals("Cascade")) {
			desktopMediator.cascadeInternalFrames();
		} else if (actionCmd.equals("Close")) {
			desktopMediator.closeSelectedFrame();
		} else if (actionCmd.equals("TileRadio")) {
			desktopMediator.setAutoTile(true);
		} else if (actionCmd.equals("CascadeRadio")) {
			desktopMediator.setAutoTile(false);
		} else { // no action command? 
			// then select the associated frame (if any)

			BaseInternalFrame associatedFrame = ((FrameAccessorInterface) e.getSource()).getAssociatedFrame();

			if (associatedFrame != null) {
				if (associatedFrame.isIcon() || !associatedFrame.isVisible() || !associatedFrame.isSelected()) {
					if (!associatedFrame.equals(desktopMediator.getSelectedFrame()))
					{
						desktopMediator.setLastSelectedFrame(desktopMediator.getSelectedFrame());
					}
					
					associatedFrame.selectFrameAndAssociatedButtons();
					desktopMediator.centerView(associatedFrame);
				} else {
					associatedFrame.deSelectFrameAndAssociatedButtons();
					//System.out.println(desktopMediator.getLastSelectedFrame());
//					if (desktopMediator.getLastSelectedFrame() != null)
//					 ((BaseInternalFrame) desktopMediator.getLastSelectedFrame()).selectFrameAndAssociatedButtons();
				}
			}

		}

	}

}