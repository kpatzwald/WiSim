package net.sourceforge.wisim.controller;

import javax.swing.table.*;
import java.awt.Component;
import javax.swing.*;

/**
 * @author Zafir Anjum
 * http://www.codeguru.com/java/articles/162.shtml
 */
public class JComponentCellRenderer implements TableCellRenderer {
    /**
     * @param table
     * @param value
     * @param isSelected
     * @param hasFocus
     * @param row
     * @param column
     * @return Component
     */
    public Component getTableCellRendererComponent(JTable table, Object value,
    boolean isSelected, boolean hasFocus, int row, int column) {
        return (JComponent)value;
    }
}