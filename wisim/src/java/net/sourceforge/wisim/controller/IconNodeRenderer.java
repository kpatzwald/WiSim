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
     * @return Component
     */
    public Component getTreeCellRendererComponent(JTree tree, Object value,
    boolean sel, boolean expanded, boolean leaf,
    int row, boolean hasFocus) {
        
        super.getTreeCellRendererComponent(tree, value,
        sel, expanded, leaf, row, hasFocus);
        
        Icon icon = ((IconNode)value).getIcon();
        
        if (icon == null) {
            Hashtable icons = (Hashtable)tree.getClientProperty("JTree.icons");
            String name = ((IconNode)value).getIconName();
            if ((icons != null) && (name != null)) {
                icon = (Icon)icons.get(name);
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