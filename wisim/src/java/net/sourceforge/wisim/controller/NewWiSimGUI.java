/*   ********************************************************************    **
 **   Copyright notice                                                       **
 **                                                                          **
 **   (c) 2003 WiSim Development Team					                               **
 **   http://wisim.sourceforge.net/   			                                 **
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

/*
 * NewWiSimGUI.java
 *
 * Created on 23. Mai 2003, 14:36
 */

package net.sourceforge.wisim.controller;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.util.Hashtable;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import net.sourceforge.wisim.mdi.JScrollableDesktopPane;

/**
 *
 * @author  Kay Patzwald
 */
public class NewWiSimGUI extends javax.swing.JFrame {
	private Hashtable actions;
	private Hashtable titles;
	private JScrollableDesktopPane desktopPane;

	/** Creates new form NewWiSimGUI */
	public NewWiSimGUI() {
		initComponents();
		initActions();
		initTitles();
		initApplication();
	}

	private void initActions() {
		actions = new Hashtable();
		actions.put("NewCustomer", new JPanelNewGUITest1());
		actions.put("ModifyCustomer", new JPanelNewGUITest2());
	}
	
	private void initTitles()
	{
		titles = new Hashtable();
		titles.put("NewCustomer", "Neuer Kunde");
		titles.put("ModifyCustomer", "Kunde bearbeiten");
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	private void initComponents() { //GEN-BEGIN:initComponents
		jLabelWiSim = new javax.swing.JLabel();
		jToolBarMain = new javax.swing.JToolBar();
		jButton1 = new javax.swing.JButton();
		jMenuBarWiSim = new javax.swing.JMenuBar();
		jMenuFile = new javax.swing.JMenu();
		jMenuVertrieb = new javax.swing.JMenu();
		jMenuCustomer = new javax.swing.JMenu();
		jMenuItemNewCustomer = new javax.swing.JMenuItem();
		jMenuItemModifyCustomer = new javax.swing.JMenuItem();
		jMenuItemViewCustomers = new javax.swing.JMenuItem();

		jLabelWiSim.setFont(new java.awt.Font("Dialog", 1, 80));
		jLabelWiSim.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabelWiSim.setText("WiSim 2.0");
		jLabelWiSim.setRequestFocusEnabled(false);

		setTitle("WiSim");
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				exitForm(evt);
			}
		});

		jToolBarMain.setFloatable(false);
		jToolBarMain.setRollover(true);
		jButton1.setText("Start");
		jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		jToolBarMain.add(jButton1);

		getContentPane().add(jToolBarMain, java.awt.BorderLayout.NORTH);

		jMenuFile.setMnemonic('D');
		jMenuFile.setText("Datei");
		jMenuBarWiSim.add(jMenuFile);

		jMenuVertrieb.setMnemonic('V');
		jMenuVertrieb.setText("Vertrieb");
		jMenuCustomer.setText("Kunde");
		jMenuItemNewCustomer.setMnemonic('N');
		jMenuItemNewCustomer.setText("Neuer Kunde");
		jMenuItemNewCustomer.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItemNewCustomerActionPerformed(evt);
			}
		});

		jMenuCustomer.add(jMenuItemNewCustomer);

		jMenuItemModifyCustomer.setMnemonic('b');
		jMenuItemModifyCustomer.setText("Kunde bearbeiten");
		jMenuItemModifyCustomer.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItemModifyCustomerActionPerformed(evt);
			}
		});

		jMenuCustomer.add(jMenuItemModifyCustomer);

		jMenuItemViewCustomers.setMnemonic('u');
		jMenuItemViewCustomers.setText("Kunden\u00fcbersicht");
		jMenuItemViewCustomers.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItemViewCustomersActionPerformed(evt);
			}
		});

		jMenuCustomer.add(jMenuItemViewCustomers);

		jMenuVertrieb.add(jMenuCustomer);

		jMenuBarWiSim.add(jMenuVertrieb);

		setJMenuBar(jMenuBarWiSim);

		pack();
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		java.awt.Dimension dialogSize = getSize();
		setLocation((screenSize.width - dialogSize.width) / 2, (screenSize.height - dialogSize.height) / 2);
	} //GEN-END:initComponents

	private void jMenuItemViewCustomersActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuItemViewCustomersActionPerformed
		addInternalFrame("ViewCustomers");
	} //GEN-LAST:event_jMenuItemViewCustomersActionPerformed

	private void jMenuItemModifyCustomerActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuItemModifyCustomerActionPerformed
		addInternalFrame("ModifyCustomer");
	} //GEN-LAST:event_jMenuItemModifyCustomerActionPerformed

	private void jMenuItemNewCustomerActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuItemNewCustomerActionPerformed
		addInternalFrame("NewCustomer");
	} //GEN-LAST:event_jMenuItemNewCustomerActionPerformed

	/** Exit the Application */
	private void exitForm(java.awt.event.WindowEvent evt) { //GEN-FIRST:event_exitForm
		System.exit(0);
	} //GEN-LAST:event_exitForm

	/*
	 * Sets after NetBeans generated code properties
	 */
	private void initApplication() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		setSize(ge.getMaximumWindowBounds().width, ge.getMaximumWindowBounds().height);
		setLocation(0, 0);
		desktopPane = new JScrollableDesktopPane(jMenuBarWiSim);
		getContentPane().add(desktopPane, BorderLayout.CENTER);
	}

	/*
	 * Adds a new Frame to the JDesktopPane
	 */
	private void addInternalFrame(String iFrame) {
		JInternalFrame frames[] = desktopPane.getAllFrames();
		boolean isOpen = false;
		int frameIndex = -1;
		for (int i = 0; i < frames.length; i++) {
			if (frames[i].getTitle().equals((String)titles.get(iFrame))) {
				isOpen = true;
				frameIndex = i;
			}
		}
		if (isOpen) {
			desktopPane.setSelectedFrame(frames[frameIndex]);
		} else {
			desktopPane.add((String)titles.get(iFrame), (JPanel) actions.get(iFrame));
		}
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		new NewWiSimGUI().show();
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jButton1;
	private javax.swing.JLabel jLabelWiSim;
	private javax.swing.JMenuBar jMenuBarWiSim;
	private javax.swing.JMenu jMenuCustomer;
	private javax.swing.JMenu jMenuFile;
	private javax.swing.JMenuItem jMenuItemModifyCustomer;
	private javax.swing.JMenuItem jMenuItemNewCustomer;
	private javax.swing.JMenuItem jMenuItemViewCustomers;
	private javax.swing.JMenu jMenuVertrieb;
	private javax.swing.JToolBar jToolBarMain;
	// End of variables declaration//GEN-END:variables

}
