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

package net.sourceforge.wisim.controller;

import javax.swing.*;
import javax.swing.tree.*;

/** Ein IconNode ist ein Element im JTree. Das IconNode kann ein eigenes
 * Icon haben.
 * Diese Klasse wurde von:
 * http://www.codeguru.com/java/articles/187.shtml
 * heruntergeladen.
 * @version 1.0 01/12/99
 */
public class IconNode extends DefaultMutableTreeNode {

	protected Icon icon;
	protected String iconName;

	public IconNode() {
		this(null);
	}

	/**
	 * @param userObject
	 */
	public IconNode(Object userObject) {
		this(userObject, true, null);
	}

	/**
	 * @param userObject
	 * @param allowsChildren
	 * @param icon
	 */
	public IconNode(Object userObject, boolean allowsChildren, Icon icon) {
		super(userObject, allowsChildren);
		this.icon = icon;
	}

	/**
	 * @param icon
	 */
	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	/**
	 * @return icon
	 */
	public Icon getIcon() {
		return icon;
	}

	/**
	 * @return iconname
	 */
	public String getIconName() {
		String str = userObject.toString();
		int index = str.lastIndexOf(".");
		if (index != -1) {
			return str.substring(++index);
		} else {
			return iconName;
		}
	}

	/**
	 * @param name
	 */
	public void setIconName(String name) {
		iconName = name;
	}
}