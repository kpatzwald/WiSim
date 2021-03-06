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
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToggleButton;

/**
 * This class provides a custom internal frame. Each internal frame 
 * is assigned an associated toggle button and an optional radio button 
 * menu item. These buttons reside in the 
 * {@link com.tomtessier.scrollabledesktop.DesktopResizableToolBar 
 * DesktopResizableToolBar} and
 * {@link com.tomtessier.scrollabledesktop.DesktopMenu DesktopMenu}.
 * classes respectively.
 *
 * @author <a href="mailto:tessier@gabinternet.com">Tom Tessier</a>
 * @author <a href="mailto:francesco.furfari@guest.cnuce.cnr.it">Francesco Furfari</a>
 * @version 1.0  9-Aug-2001
 */

public class BaseInternalFrame extends JInternalFrame {

	private JToggleButton associatedButton;
	private JRadioButtonMenuItem associatedMenuButton;

	private boolean isClosable;
	private int initialWidth;
	private int initialHeight;

	/**
	 * @return
	 */
	public boolean isClosable() {
		return isClosable;
	}

	/**
	 * @param isClosable
	 */
	public void setClosable(boolean isClosable) {
		this.isClosable = isClosable;
	}

	/** 
	  *  creates the BaseInternalFrame
	  *
	  * @param title the string displayed in the title bar of the internal frame
	  * @param icon the ImageIcon displayed in the title bar of the internal frame
	  * @param frameContents the contents of the internal frame
	  * @param isClosable determines whether the frame is closable
	  */
	public BaseInternalFrame(String title, ImageIcon icon, JPanel frameContents, boolean isClosable) {

		super(title, // title
		true, //resizable
		isClosable, //closable
		true, //maximizable
		true); //iconifiable

		this.isClosable = isClosable;

		setBackground(Color.white);
		setForeground(Color.blue);

		if (icon != null) {
			setFrameIcon(icon);
		}

		// add the window contents
		getContentPane().add(frameContents);
		pack();

		saveSize();

		setVisible(true); // turn the frame on
	}

	private void saveSize() {
		initialWidth = getWidth();
		initialHeight = getHeight();
	}

	/**
	  * constructor provided for compatibility with JInternalFrame
	  */
	public BaseInternalFrame() {
		super();
		saveSize();
	}
	/**
	  * constructor provided for compatibility with JInternalFrame
	  */
	public BaseInternalFrame(String title) {
		super(title);
		saveSize();
	}
	/**
	  * constructor provided for compatibility with JInternalFrame
	  */
	public BaseInternalFrame(String title, boolean resizable) {
		super(title, resizable);
		saveSize();
	}
	/**
	  * constructor provided for compatibility with JInternalFrame
	  */
	public BaseInternalFrame(String title, boolean resizable, boolean closable) {
		super(title, resizable, closable);
		this.isClosable = closable;
		saveSize();
	}
	/**
	  * constructor provided for compatibility with JInternalFrame
	  */
	public BaseInternalFrame(String title, boolean resizable, boolean closable, boolean maximizable) {
		super(title, resizable, closable, maximizable);
		this.isClosable = closable;
		saveSize();
	}
	/**
	  * constructor provided for compatibility with JInternalFrame
	  */
	public BaseInternalFrame(String title, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable) {
		super(title, resizable, closable, maximizable, iconifiable);
		this.isClosable = closable;
		saveSize();
	}

	/** 
	  *  sets the associated menu button
	  *
	  * @param associatedMenuButton the menu button to associate with 
	  * the internal frame
	  */
	public void setAssociatedMenuButton(JRadioButtonMenuItem associatedMenuButton) {
		this.associatedMenuButton = associatedMenuButton;
	}
	/** 
	  *  returns the associated menu button
	  *
	  * @return the JRadioButtonMenuItem object associated with this internal frame
	  */
	public JRadioButtonMenuItem getAssociatedMenuButton() {
		return associatedMenuButton;
	}

	/** 
	  *  sets the associated toggle button
	  *
	  * @param associatedButton the toggle button to associate with 
	  * the internal frame
	  */
	public void setAssociatedButton(JToggleButton associatedButton) {
		this.associatedButton = associatedButton;
	}
	/** 
	  *  returns the associated toggle button
	  *
	  * @return the JToggleButton object associated with this internal frame
	  */
	public JToggleButton getAssociatedButton() {
		return associatedButton;
	}

	/** 
	  *  returns the initial dimensions of this internal frame. Necessary so that
	  * internal frames can be restored to their default sizes when the cascade
	  * frame positioning mode is chosen in 
	  * {@link com.tomtessier.scrollabledesktop.FramePositioning FramePositioning}.
	  *
	  * @return the Dimension object representing the initial dimensions of
	  * this internal frame
	  */
	public Dimension getInitialDimensions() {
		return new Dimension(initialWidth, initialHeight);
	}

	/** 
	  *  returns the toString() representation of this object. Useful for 
	  * debugging purposes.
	  *
	  * @return the toString() representation of this object
	  */
	public String toString() {
		return "BaseInternalFrame: " + getTitle();
	}

	/** 
	  *  selects the current frame, along with any toggle and menu 
	  * buttons that may be associated with it
	  */
	public void selectFrameAndAssociatedButtons() {
		// select associated toolbar button
		if (associatedButton != null) {
			associatedButton.setSelected(true);
			((BaseToggleButton) associatedButton).flagContentsChanged(false);
		}

		// select menu button
		if (associatedMenuButton != null) {
			associatedMenuButton.setSelected(true);
		}
		
		try {
			setSelected(true);
			setIcon(false); // select and de-iconify the frame
		} catch (java.beans.PropertyVetoException pve) {
			System.out.println(pve.getMessage());
		}
		setVisible(true); // and make sure the frame is turned on
	}
	
	/** 
		  *  deselects the current frame, along with any toggle and menu 
		  * buttons that may be associated with it
		  */
		public void deSelectFrameAndAssociatedButtons() {
			try {
				setIcon(true); // deselect and iconify the frame
				setSelected(false);
			} catch (java.beans.PropertyVetoException pve) {
				System.out.println(pve.getMessage());
			}
		}

	/** 
	  *  saves the size of the current internal frame for those frames whose
	  * initial width and heights have not been set. Called when the internal
	  * frame is added to the JDesktopPane. 
	  * <BR><BR>
	  * Manually-built internal frames won't display properly without this.
	  * <BR><BR>
	  * Fix by <a href="mailto:francesco.furfari@guest.cnuce.cnr.it">Francesco Furfari</a>
	  *
	  */
	public void addNotify() {
		super.addNotify();
		if (initialWidth == 0 && initialHeight == 0) {
			saveSize();
		}
	}

}