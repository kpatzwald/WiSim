package net.sourceforge.wisim.controller;

import java.awt.Component;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.util.EventObject;
import javax.swing.tree.*;
import java.io.Serializable;
import javax.swing.*;


/**
 * @author Zafir Anjum
 * http://www.codeguru.com/java/articles/162.shtml
 */
public class JComponentCellEditor implements TableCellEditor, TreeCellEditor,
Serializable {
    
    protected EventListenerList listenerList = new EventListenerList();
    transient protected ChangeEvent changeEvent = null;
    
    protected JComponent editorComponent = null;
    protected JComponent container = null;		// Can be tree or table
    
    /**
     * @return editorComponent
     */
    public Component getComponent() {
        return editorComponent;
    }
       
    /**
     * @return editorComponent
     */
    public Object getCellEditorValue() {
        return editorComponent;
    }
    
    /**
     * @param anEvent
     * @return boolean
     */
    public boolean isCellEditable(EventObject anEvent) {
        return true;
    }
    
    /**
     * @param anEvent
     * @return boolean
     */
    public boolean shouldSelectCell(EventObject anEvent) {
        if( editorComponent != null && anEvent instanceof MouseEvent
        && ((MouseEvent)anEvent).getID() == MouseEvent.MOUSE_PRESSED ) {
            Component dispatchComponent = SwingUtilities.getDeepestComponentAt(editorComponent, 3, 3 );
            MouseEvent e = (MouseEvent)anEvent;
            MouseEvent e2 = new MouseEvent( dispatchComponent, MouseEvent.MOUSE_RELEASED,
            e.getWhen() + 100000, e.getModifiers(), 3, 3, e.getClickCount(),
            e.isPopupTrigger() );
            dispatchComponent.dispatchEvent(e2);
            e2 = new MouseEvent( dispatchComponent, MouseEvent.MOUSE_CLICKED,
            e.getWhen() + 100001, e.getModifiers(), 3, 3, 1,
            e.isPopupTrigger() );
            dispatchComponent.dispatchEvent(e2);
        }
        return false;
    }
    
    /**
     * @return boolean
     */
    public boolean stopCellEditing() {
        fireEditingStopped();
        return true;
    }
    
    public void cancelCellEditing() {
        fireEditingCanceled();
    }
    
    /**
     * @param l
     */
    public void addCellEditorListener(CellEditorListener l) {
        listenerList.add(CellEditorListener.class, l);
    }
    
    /**
     * @param l
     */
    public void removeCellEditorListener(CellEditorListener l) {
        listenerList.remove(CellEditorListener.class, l);
    }
    
    protected void fireEditingStopped() {
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==CellEditorListener.class) {
                // Lazily create the event:
                if (changeEvent == null)
                    changeEvent = new ChangeEvent(this);
                ((CellEditorListener)listeners[i+1]).editingStopped(changeEvent);
            }
        }
    }
    
    protected void fireEditingCanceled() {
        // Guaranteed to return a non-null array
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==CellEditorListener.class) {
                // Lazily create the event:
                if (changeEvent == null)
                    changeEvent = new ChangeEvent(this);
                ((CellEditorListener)listeners[i+1]).editingCanceled(changeEvent);
            }
        }
    }
    
    // implements javax.swing.tree.TreeCellEditor
    /**
     * @param tree
     * @param value
     * @param isSelected
     * @param expanded
     * @param leaf
     * @param row
     * @return editorComponent
     */
    public Component getTreeCellEditorComponent(JTree tree, Object value,
    boolean isSelected, boolean expanded, boolean leaf, int row) {
        String         stringValue = tree.convertValueToText(value, isSelected,
        expanded, leaf, row, false);
        
        editorComponent = (JComponent)value;
        container = tree;
        return editorComponent;
    }
    
    // implements javax.swing.table.TableCellEditor
    /**
     * @param table
     * @param value
     * @param isSelected
     * @param row
     * @param column
     * @return editorComponent
     */
    public Component getTableCellEditorComponent(JTable table, Object value,
    boolean isSelected, int row, int column) {
        
        editorComponent = (JComponent)value;
        container = table;
        return editorComponent;
    }
}