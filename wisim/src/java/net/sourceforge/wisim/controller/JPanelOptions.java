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

/*
 * JPanelOptions.java
 *
 * Created on 28. Februar 2003, 19:08
 */

package net.sourceforge.wisim.controller;

import java.util.logging.Level;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import net.sourceforge.wisim.dao.WiSimAuthentificationDAO;
import net.sourceforge.wisim.dao.WiSimDAO;
import net.sourceforge.wisim.dao.WiSimDAOException;
import net.sourceforge.wisim.model.WiSimLogger;

/**
 * JPanelOptions erm??glicht dem Benutzer Einstellungen vorzunehmen.
 * @author benjamin.pasero
 */
public class JPanelOptions extends javax.swing.JPanel {

	private WiSimMainController wiSimMainController;

	//Logger
	private WiSimLogger wiSimLogger;

	private WiSimAuthentificationDAO authDAO;

	/** Creates new form JPanelOptions
	 * @param wiSimMainController Der Maincontroller
	 */
	public JPanelOptions(WiSimMainController wiSimMainController) {
		wiSimLogger = wiSimMainController.getWiSimLogger();
		initComponents();
		this.wiSimMainController = wiSimMainController;
		authDAO = wiSimMainController.getAuthDAO();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	private void initComponents() { //GEN-BEGIN:initComponents
		jLabelOptionsUeberschrift = new javax.swing.JLabel();
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Options");
		DefaultMutableTreeNode elementGeneral = new DefaultMutableTreeNode("General");
		elementGeneral.add(new DefaultMutableTreeNode("Layout"));
		root.add(elementGeneral);

		DefaultMutableTreeNode elementDB = new DefaultMutableTreeNode("Datenbank");
		elementDB.add(new DefaultMutableTreeNode("Einstellungen"));
		elementDB.add(new DefaultMutableTreeNode("Datenbank zur??cksetzen"));
		root.add(elementDB);

		DefaultTreeModel treeModel = new DefaultTreeModel(root);

		jTreeOptions = new JTree(treeModel);
		jPanelLayout = new javax.swing.JPanel();
		jPanelLayout.setVisible(false);
		UIManager.LookAndFeelInfo plafs[] = UIManager.getInstalledLookAndFeels();
		int i = 0;
		String[] plafname = new String[plafs.length];
		while (i < plafs.length) {
			plafname[i] = plafs[i].getName();
			i++;
		}
		jComboBoxSelectSkin = new javax.swing.JComboBox(plafname);
		jComboBoxSelectSkin.setSelectedIndex(3);
		jLabelPlaf = new javax.swing.JLabel();
		jPanelMySQLInfo = new javax.swing.JPanel();
		jPanelMySQLInfo.setVisible(false);
		jLabelHostname = new javax.swing.JLabel();
		jLabelPort = new javax.swing.JLabel();
		jLabelDatabase = new javax.swing.JLabel();
		jTextFieldDatabase = new javax.swing.JTextField();
		jLabelHostname1 = new javax.swing.JLabel();
		jLabelUsername = new javax.swing.JLabel();
		jTextFieldHostname = new javax.swing.JTextField();
		jTextFieldPort = new javax.swing.JTextField();
		jTextFieldUsername = new javax.swing.JTextField();
		jLabelPassword = new javax.swing.JLabel();
		jPasswordField = new javax.swing.JPasswordField();
		jButtonUpdateSettings = new javax.swing.JButton();
		jPanelMySQLAusfuehren = new javax.swing.JPanel();
		jPanelMySQLAusfuehren.setVisible(false);
		jLabelDateien = new javax.swing.JLabel();
		jLabelBeschreibung = new javax.swing.JLabel();
		jButtonRefresh = new javax.swing.JButton();
		jLabelBeschreibung1 = new javax.swing.JLabel();

		setLayout(null);

		setPreferredSize(new java.awt.Dimension(800, 600));
		jLabelOptionsUeberschrift.setFont(new java.awt.Font("Dialog", 1, 24));
		jLabelOptionsUeberschrift.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabelOptionsUeberschrift.setText("Options");
		add(jLabelOptionsUeberschrift);
		jLabelOptionsUeberschrift.setBounds(210, 0, 400, 40);

		jTreeOptions.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
		jTreeOptions.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
			public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
				jTreeOptionsValueChanged(evt);
			}
		});

		add(jTreeOptions);
		jTreeOptions.setBounds(10, 50, 220, 300);

		jPanelLayout.setLayout(null);

		jPanelLayout.setBorder(new javax.swing.border.TitledBorder("Layout"));
		jComboBoxSelectSkin.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jComboBoxSelectSkinActionPerformed(evt);
			}
		});

		jPanelLayout.add(jComboBoxSelectSkin);
		jComboBoxSelectSkin.setBounds(100, 40, 90, 25);

		jLabelPlaf.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabelPlaf.setText("Skin");
		jPanelLayout.add(jLabelPlaf);
		jLabelPlaf.setBounds(40, 40, 41, 20);

		add(jPanelLayout);
		jPanelLayout.setBounds(290, 40, 460, 310);

		jPanelMySQLInfo.setLayout(null);

		jPanelMySQLInfo.setBorder(new javax.swing.border.TitledBorder("MySQL Info"));
		jPanelMySQLInfo.addAncestorListener(new javax.swing.event.AncestorListener() {
			public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
			}
			public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
				jPanelMySQLInfoAncestorAdded(evt);
			}
			public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
			}
		});

		jLabelHostname.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabelHostname.setText("Hostname");
		jPanelMySQLInfo.add(jLabelHostname);
		jLabelHostname.setBounds(60, 60, 60, 16);

		jLabelPort.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabelPort.setText("Port");
		jPanelMySQLInfo.add(jLabelPort);
		jLabelPort.setBounds(80, 90, 40, 16);

		jLabelDatabase.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabelDatabase.setText("Database");
		jPanelMySQLInfo.add(jLabelDatabase);
		jLabelDatabase.setBounds(50, 180, 70, 16);

		jTextFieldDatabase.setEditable(false);
		jTextFieldDatabase.setText("wisim");
		jPanelMySQLInfo.add(jTextFieldDatabase);
		jTextFieldDatabase.setBounds(130, 180, 160, 20);

		jLabelHostname1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabelHostname1.setText("Hostname");
		jPanelMySQLInfo.add(jLabelHostname1);
		jLabelHostname1.setBounds(60, 60, 60, 16);

		jLabelUsername.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabelUsername.setText("Username");
		jPanelMySQLInfo.add(jLabelUsername);
		jLabelUsername.setBounds(40, 120, 80, 16);

		jPanelMySQLInfo.add(jTextFieldHostname);
		jTextFieldHostname.setBounds(130, 60, 160, 20);

		jPanelMySQLInfo.add(jTextFieldPort);
		jTextFieldPort.setBounds(130, 90, 60, 20);

		jPanelMySQLInfo.add(jTextFieldUsername);
		jTextFieldUsername.setBounds(130, 120, 160, 20);

		jLabelPassword.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabelPassword.setText("Password");
		jPanelMySQLInfo.add(jLabelPassword);
		jLabelPassword.setBounds(40, 150, 80, 16);

		jPanelMySQLInfo.add(jPasswordField);
		jPasswordField.setBounds(130, 150, 160, 20);

		jButtonUpdateSettings.setText("Update Settings");
		jButtonUpdateSettings.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonUpdateSettingsActionPerformed(evt);
			}
		});

		jPanelMySQLInfo.add(jButtonUpdateSettings);
		jButtonUpdateSettings.setBounds(130, 230, 160, 26);

		add(jPanelMySQLInfo);
		jPanelMySQLInfo.setBounds(290, 40, 460, 310);

		jPanelMySQLAusfuehren.setLayout(null);

		jPanelMySQLAusfuehren.setBorder(new javax.swing.border.TitledBorder("MySQL Ausf\u00fchren"));
		jPanelMySQLAusfuehren.setAlignmentY(1.0F);
		jLabelDateien.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabelDateien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabelDateien.setText("Refresh der Datenbank");
		jLabelDateien.setIconTextGap(7);
		jLabelDateien.setMaximumSize(new java.awt.Dimension(70, 16));
		jLabelDateien.setMinimumSize(new java.awt.Dimension(70, 16));
		jLabelDateien.setPreferredSize(new java.awt.Dimension(70, 16));
		jPanelMySQLAusfuehren.add(jLabelDateien);
		jLabelDateien.setBounds(120, 40, 200, 20);

		jLabelBeschreibung.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabelBeschreibung.setText("Um die Datenbank zu erneuern, klicken Sie auf den \"Refresh\" Button!");
		jLabelBeschreibung.addAncestorListener(new javax.swing.event.AncestorListener() {
			public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
			}
			public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
				jLabelBeschreibungAncestorAdded(evt);
			}
			public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
			}
		});

		jPanelMySQLAusfuehren.add(jLabelBeschreibung);
		jLabelBeschreibung.setBounds(40, 220, 390, 20);

		jButtonRefresh.setText("REFRESH");
		jButtonRefresh.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonRefreshActionPerformed(evt);
			}
		});

		jPanelMySQLAusfuehren.add(jButtonRefresh);
		jButtonRefresh.setBounds(190, 110, 100, 30);

		jLabelBeschreibung1.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabelBeschreibung1.setForeground(new java.awt.Color(255, 51, 0));
		jLabelBeschreibung1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabelBeschreibung1.setText("Warte auf Best\u00e4tigung");
		jLabelBeschreibung1.setMaximumSize(new java.awt.Dimension(383, 16));
		jLabelBeschreibung1.setMinimumSize(new java.awt.Dimension(383, 16));
		jLabelBeschreibung1.setPreferredSize(new java.awt.Dimension(383, 16));
		jPanelMySQLAusfuehren.add(jLabelBeschreibung1);
		jLabelBeschreibung1.setBounds(40, 260, 383, 25);

		add(jPanelMySQLAusfuehren);
		jPanelMySQLAusfuehren.setBounds(290, 40, 460, 310);

	} //GEN-END:initComponents

	private void jButtonUpdateSettingsActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jButtonUpdateSettingsActionPerformed
		updateDBSettings();
	} //GEN-LAST:event_jButtonUpdateSettingsActionPerformed

	private void jPanelMySQLInfoAncestorAdded(javax.swing.event.AncestorEvent evt) { //GEN-FIRST:event_jPanelMySQLInfoAncestorAdded
		fillDBInfoFields();
	} //GEN-LAST:event_jPanelMySQLInfoAncestorAdded

	private void jLabelBeschreibungAncestorAdded(javax.swing.event.AncestorEvent evt) { //GEN-FIRST:event_jLabelBeschreibungAncestorAdded
		jLabelBeschreibung1.setText("Warte auf Best\u00e4tigung");
	} //GEN-LAST:event_jLabelBeschreibungAncestorAdded

	private void jButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jButtonRefreshActionPerformed
		resetDB();
	} //GEN-LAST:event_jButtonRefreshActionPerformed

	private void jComboBoxSelectSkinActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jComboBoxSelectSkinActionPerformed
		lookAndFeel();
	} //GEN-LAST:event_jComboBoxSelectSkinActionPerformed

	private void jTreeOptionsValueChanged(javax.swing.event.TreeSelectionEvent evt) { //GEN-FIRST:event_jTreeOptionsValueChanged
		treeOptions();
	} //GEN-LAST:event_jTreeOptionsValueChanged

	/** Resets the DB
	 */
	private void resetDB() {
		int choise = JOptionPane.showConfirmDialog(this, "Die Datenbank wird auf die initialen Werte der Datei \"complete.sql\" zur??ckgesetzt.\nAlle Benutzereingaben werden gel??scht.\nDatenbank zur??cksetzen?", "Achtung!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if (choise == JOptionPane.YES_OPTION) {
			jLabelBeschreibung1.setText("Bitte warten....");
			WiSimDAO dao = wiSimMainController.getDAO();
			try {
				dao.dbReset();
				jLabelBeschreibung1.setText("Fertig!");
			} catch (WiSimDAOException e) {
				jLabelBeschreibung1.setText("Probleme beim Zur??cksetzten der DB");
				wiSimLogger.log(Level.WARNING, "resetDB()", e, false);
			}
			JOptionPane.showMessageDialog(this, "Die Datenbank wurde zur??ckgesetzt!");
		}
	}

	/** F??llt die Textfelder mit den Daten aus der "config.dat" */
	private void fillDBInfoFields() {
		jTextFieldHostname.setText(authDAO.getHostName());
		jTextFieldPort.setText(authDAO.getPort());
		jTextFieldUsername.setText(authDAO.getUser());
		jPasswordField.setText(authDAO.getPassword());
	}

	/** Schreibt die Daten aus den Textfeldern in die "config.dat" */
	private void updateDBSettings() {
		String hostname = jTextFieldHostname.getText();
		if (hostname.equals("")) {
			hostname = "localhost";
		}

		String port = jTextFieldPort.getText();
		if (port.equals("")) {
			port = "3306";
		}

		String user = jTextFieldUsername.getText();
		if (user.equals("")) {
			user = "root";
		}

		String password = jPasswordField.getPassword().toString();

		authDAO.updateDBSettings(hostname, port, user, password);

		JOptionPane.showMessageDialog(this, "Die Einstellungen wurden gespeichert.\n" + "Ein Neustart der Applikation ist erforderlich!");
	}

	/** Enables the JButtonRefresh. It is important, that nobody can refresh
	 * the DB if the simulation is running
	 */
	public void setJButtonRefreshEnable(boolean enable) {
		jButtonRefresh.setEnabled(enable);
	}

	private void treeOptions() {
		if (!jTreeOptions.isSelectionEmpty()) {
			String menue = jTreeOptions.getLastSelectedPathComponent().toString();
			if (menue.equals("Einstellungen")) {
				jPanelLayout.setVisible(false);
				jPanelMySQLInfo.setVisible(true);
				jPanelMySQLAusfuehren.setVisible(false);
				jLabelBeschreibung1.setText("Warte auf Best??tigung");
			} else if (menue.equals("Layout")) {
				jPanelLayout.setVisible(true);
				jPanelMySQLInfo.setVisible(false);
				jPanelMySQLAusfuehren.setVisible(false);
				jLabelBeschreibung1.setText("Warte auf Best??tigung");
			} else if (menue.equals("Datenbank zur??cksetzen")) {
				jPanelLayout.setVisible(false);
				jPanelMySQLInfo.setVisible(false);
				jPanelMySQLAusfuehren.setVisible(true);
				jLabelBeschreibung1.setText("Warte auf Best??tigung");
			}
		}
	}

	private void lookAndFeel() {
		String skin = jComboBoxSelectSkin.getSelectedItem().toString();
		String plaf = "";

		UIManager.LookAndFeelInfo plafs[] = UIManager.getInstalledLookAndFeels();
		int i = 0;
		while (i < plafs.length) {
			if (skin.equals(plafs[i].getName())) {
				plaf = plafs[i].getClassName();
			}
			i++;
		}
		try {
			UIManager.setLookAndFeel(plaf);
			SwingUtilities.updateComponentTreeUI(this);
			SwingUtilities.updateComponentTreeUI(wiSimMainController);
		} catch (Exception e) {
			wiSimLogger.log("setLookAndFeel()", e);
		}
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jButtonRefresh;
	private javax.swing.JButton jButtonUpdateSettings;
	private javax.swing.JComboBox jComboBoxSelectSkin;
	private javax.swing.JLabel jLabelBeschreibung;
	private javax.swing.JLabel jLabelBeschreibung1;
	private javax.swing.JLabel jLabelDatabase;
	private javax.swing.JLabel jLabelDateien;
	private javax.swing.JLabel jLabelHostname;
	private javax.swing.JLabel jLabelHostname1;
	private javax.swing.JLabel jLabelOptionsUeberschrift;
	private javax.swing.JLabel jLabelPassword;
	private javax.swing.JLabel jLabelPlaf;
	private javax.swing.JLabel jLabelPort;
	private javax.swing.JLabel jLabelUsername;
	private javax.swing.JPanel jPanelLayout;
	private javax.swing.JPanel jPanelMySQLAusfuehren;
	private javax.swing.JPanel jPanelMySQLInfo;
	private javax.swing.JPasswordField jPasswordField;
	private javax.swing.JTextField jTextFieldDatabase;
	private javax.swing.JTextField jTextFieldHostname;
	private javax.swing.JTextField jTextFieldPort;
	private javax.swing.JTextField jTextFieldUsername;
	private javax.swing.JTree jTreeOptions;
	// End of variables declaration//GEN-END:variables

}