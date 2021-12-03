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

package net.sourceforge.wisim.controller;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.tree.*;

/** Der CellRenderer für einen JTree mit IconNodes. Erlaubt es jedem Node im JTree ein eigenes Icon
 * zuzuweisen.
 * Diese Klasse wurde von:
 * http://www.codeguru.com/java/articles/187.shtml
 * heruntergeladen.
 * @version 1.0 01/12/99
 */
public class IconNodeRenderer extends DefaultTreeCellRenderer {

	/**
	 * @param tree
	 * @param value
	 * @param sel
	 * @param expanded
	 * @param leaf
	 * @param row
	 * @param hasFocus
	 * @return WiSimComponent
	 */
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {

		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

		Icon icon = ((IconNode) value).getIcon();

		if (icon == null) {
			Hashtable icons = (Hashtable) tree.getClientProperty("JTree.icons");
			String name = ((IconNode) value).getIconName();
			if ((icons != null) && (name != null)) {
				icon = (Icon) icons.get(name);
				if (icon != null) {
					setIcon(icon);
				}
			}
		} else {
			setIcon(icon);
		}

		return this;
	}
}