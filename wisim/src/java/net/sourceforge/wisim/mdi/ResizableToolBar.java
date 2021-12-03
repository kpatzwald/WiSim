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

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

/**
 * Generic self-contained resizable toolbar class. When a button addition exceeds 
 * the width of the toolbar container, all buttons within the container are 
 * automatically resized to compensate, down to the minimum button width defined 
 * upon creation of the ResizableToolbar instance. 
 *
 * @author <a href="mailto:tessier@gabinternet.com">Tom Tessier</a>
 * @version 1.0  03-Mar-2001
 */

public class ResizableToolBar extends JToolBar implements ComponentListener {
	private Collection buttons;

	private int minButtonWidth;
	private int maxButtonWidth;

	/**
	 * creates the ResizableToolbar object
	 *
	 * @param minButtonWidth the minimum button width allowed
	 * @param maxButtonWidth the maximum button width allowed
	 */
	public ResizableToolBar(int minButtonWidth, int maxButtonWidth) {
		buttons = (Collection) new Vector();
		setFloatable(false);
		this.minButtonWidth = minButtonWidth;
		this.maxButtonWidth = maxButtonWidth;

		addComponentListener(this);

	}

	/**
	 * adds a button to the ResizableToolbar
	 *
	 * @param button the button to add
	 */
	public void add(AbstractButton button) {
		buttons.add(button);
		super.add(button);
		if (getSelectedButton() != null)
		{
			getSelectedButton().setSelected(false);
		}
		button.setSelected(true);
		resizeButtons();

	}
	
	public AbstractButton getSelectedButton ()
	{
		JToggleButton b = null;
		Iterator it = buttons.iterator();
		while (it.hasNext())
		{
			b = (JToggleButton) it.next();
			if (b.isSelected())
				return b;
		}
		return b;
	}

	/**
	 * removes a button from the ResizableToolbar
	 *
	 * @param button the button to remove
	 */
	public void remove(AbstractButton button) {
		super.remove(button);
		button.remove(button);
		resizeButtons();
		repaint();
	}

//	/**
//	 * returns the ResizableToolbar elements
//	 *
//	 * @return an Collection of the ResizableToolbar elements
//	 */
//	private Collection getElements() {
//		return buttons;
//	}

	/**
	 * returns the number of buttons stored within the ResizableToolbar
	 *
	 * @return the number of buttons
	 */
	public int getButtonCount() {
		return buttons.size();
	}

	/** 
	  * resizes the buttons of the toolbar, depending upon the total number 
	  * of components stored therein. 
	  * Executes as an "invoked later" thread for a slight perceived 
	  * performance boost.
	  */
	private void resizeButtons() {

		final float exactButtonWidth = getCurrentButtonWidth();

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {

				JToggleButton b = null;
				Iterator it = buttons.iterator();
				
				float currentButtonXLocation = 0.0f;

				// resize the buttons
				while (it.hasNext() ) {
					b = (JToggleButton) it.next();
					int buttonWidth = Math.round(currentButtonXLocation + exactButtonWidth) - Math.round(currentButtonXLocation);
					assignWidth(b, buttonWidth);

					currentButtonXLocation += exactButtonWidth;
				}

				revalidate();

			}
		});
	}

	/**
	 * returns the current button width, defined as the width of the ResizableToolbar
	 *      divided by the number of buttons. The value returned ranges from 
	 *      minButtonWidth to maxButtonWidth (two variables defined upon creation 
	 *      of the ResizableToolbar instance).
	 *
	 * @return the current button width as a float. 
	 */
	private float getCurrentButtonWidth() {

		int width = getWidth() - getInsets().left - getInsets().right;

		// if width <= 0, means JToolbar hasn't been displayed yet, so use
		// the maximum button width
		float buttonWidth = ((width <= 0) ? maxButtonWidth : width);

		int numButtons = getButtonCount();

		// have at least one button? then divide the width by the # of buttons
		// (ie: resultant buttonWidth = viewport width / # of buttons)
		if (numButtons > 0) {
			buttonWidth /= numButtons;
		}

		if (buttonWidth < minButtonWidth) {
			buttonWidth = minButtonWidth;
		} else if (buttonWidth > maxButtonWidth) {
			buttonWidth = maxButtonWidth;
		}

		return buttonWidth;
	}

	/**
	 * assigns a new width to the specified button
	 *
	 * @param b the button whose width is to be adjusted
	 * @param buttonWidth the new width 
	 */
	private void assignWidth(JToggleButton b, int buttonWidth) {

		b.setMinimumSize(new Dimension(buttonWidth - 2, b.getPreferredSize().height));
		b.setPreferredSize(new Dimension(buttonWidth, b.getPreferredSize().height));
		Dimension newSize = b.getPreferredSize();
		b.setMaximumSize(newSize);
		b.setSize(newSize);

	}

	/////
	// respond to resize events...
	/////

	/** 
	 * resize the buttons when the ResizableToolbar itself is resized
	 *
	 * @param e the ComponentEvent
	 */
	public void componentResized(ComponentEvent e) {
		resizeButtons();
	}

	/**
	* interface placeholder
	*
	* @param e the ComponentEvent
	*/
	public void componentShown(ComponentEvent e) {
	}
	/**
	* interface placeholder
	*
	* @param e the ComponentEvent
	*/
	public void componentMoved(ComponentEvent e) {
	}
	/**
	* interface placeholder
	*
	* @param e the ComponentEvent
	*/
	public void componentHidden(ComponentEvent e) {
	}

}