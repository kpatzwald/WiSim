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

/*
 * JPanelOptions.java
 *
 * Created on 28. Februar 2003, 19:08
 */

package net.sourceforge.wisim.controller;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.gjt.mm.mysql.Driver;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * JPanelOptions ermöglicht dem Benutzer Einstellungen vorzunehmen.
 * @author benjamin.pasero
 */
public class JPanelOptions extends javax.swing.JPanel {

	private WiSimMainController wiSimMainController;

	//Logger
	private WiSimLogger wiSimLogger;
	private Connection conn;

	/** Creates new form JPanelOptions
	 * @param wiSimMainController Der Maincontroller
	 */
	public JPanelOptions(WiSimMainController wiSimMainController) {
		wiSimLogger = wiSimMainController.getWiSimLogger();
		initComponents();
		this.wiSimMainController = wiSimMainController;
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
  private void initComponents() {//GEN-BEGIN:initComponents
    jLabelOptionsUeberschrift = new javax.swing.JLabel();
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("Options");
    DefaultMutableTreeNode elementGeneral = new DefaultMutableTreeNode("General");
    elementGeneral.add(new DefaultMutableTreeNode("Layout"));
    root.add(elementGeneral);

    DefaultMutableTreeNode elementMySQL = new DefaultMutableTreeNode("MySQL");
    elementMySQL.add(new DefaultMutableTreeNode("Einstellungen"));
    elementMySQL.add(new DefaultMutableTreeNode("Datenbank zurücksetzen"));
    root.add(elementMySQL);

    DefaultTreeModel treeModel = new DefaultTreeModel(root);

    jTreeOptions = new JTree( treeModel );
    jPanelLayout = new javax.swing.JPanel();
    jPanelLayout.setVisible(false);
    UIManager.LookAndFeelInfo plafs[] = UIManager.getInstalledLookAndFeels();
    int i=0;
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

  }//GEN-END:initComponents

	private void jButtonUpdateSettingsActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jButtonUpdateSettingsActionPerformed
		updateDBSettings();
		JOptionPane.showMessageDialog(this, "Die Einstellungen wurden gespeichert.\n" + "Ein Neustart der Applikation ist erforderlich!");
	} //GEN-LAST:event_jButtonUpdateSettingsActionPerformed

	private void jPanelMySQLInfoAncestorAdded(javax.swing.event.AncestorEvent evt) { //GEN-FIRST:event_jPanelMySQLInfoAncestorAdded
		fillDBInfoFields();
	} //GEN-LAST:event_jPanelMySQLInfoAncestorAdded

	private void jLabelBeschreibungAncestorAdded(javax.swing.event.AncestorEvent evt) { //GEN-FIRST:event_jLabelBeschreibungAncestorAdded
		jLabelBeschreibung1.setText("Warte auf Best\u00e4tigung");
	} //GEN-LAST:event_jLabelBeschreibungAncestorAdded

	private void jButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jButtonRefreshActionPerformed
		einlesenQueries();
		JOptionPane.showMessageDialog(this, "Die Datenbank wurde resettet!");
	} //GEN-LAST:event_jButtonRefreshActionPerformed

	private void jComboBoxSelectSkinActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jComboBoxSelectSkinActionPerformed
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
	} //GEN-LAST:event_jComboBoxSelectSkinActionPerformed

	private void jTreeOptionsValueChanged(javax.swing.event.TreeSelectionEvent evt) { //GEN-FIRST:event_jTreeOptionsValueChanged
		if (!jTreeOptions.isSelectionEmpty()) {
			String menue = jTreeOptions.getLastSelectedPathComponent().toString();
			if (menue.equals("Einstellungen")) {
				jPanelLayout.setVisible(false);
				jPanelMySQLInfo.setVisible(true);
				jPanelMySQLAusfuehren.setVisible(false);
				jLabelBeschreibung1.setText("Warte auf Bestätigung");
			} else if (menue.equals("Layout")) {
				jPanelLayout.setVisible(true);
				jPanelMySQLInfo.setVisible(false);
				jPanelMySQLAusfuehren.setVisible(false);
				jLabelBeschreibung1.setText("Warte auf Bestätigung");
			} else if (menue.equals("Datenbank zurücksetzen")) {
				jPanelLayout.setVisible(false);
				jPanelMySQLInfo.setVisible(false);
				jPanelMySQLAusfuehren.setVisible(true);
				jLabelBeschreibung1.setText("Warte auf Bestätigung");
			}
		}
	} //GEN-LAST:event_jTreeOptionsValueChanged

	/** Liest eine SQL-Datei ein und entfernt alle Kommentare.
	 * Stellt die Datenbankverbindung her und setzt die Datenbank mit dem aus der Datei
	 * eingelesenen Werte zurück.
	 * @throws sqlException if a database problem occurs
	 */
	public void einlesenQueries() {
		jLabelBeschreibung1.setText("Bitte warten....");
		String hostname = "";
		String port = "";
		String dbname = "wisim"; //durch die Applikation vorgegeben
		String user = "";
		String password = "";

		//Datei mit DB Infos wird eingelesen
		String result = "";
		try {
			FileInputStream file = new FileInputStream("config.dat");
			DataInputStream in = new DataInputStream(file);

			BASE64Decoder decoder = new BASE64Decoder();
			byte[] b = decoder.decodeBuffer(in);
			in.close();
			result = new String(b);
		}

		//Die Datei ist noch nicht vorhanden und wird angelegt mit den Defaultwerten
		catch (IOException e) {
			try {
				String defaultValues = "localhost\n3306\nroot";
				BASE64Encoder encoder = new BASE64Encoder();
				defaultValues = encoder.encode(defaultValues.getBytes());

				File file = new File("config.dat");
				FileWriter fw = new FileWriter(file);
				fw.write(defaultValues);
				fw.close();
				result = "localhost\n3306\nroot";
			} catch (IOException ioE) {
			}
		}

		Pattern p = Pattern.compile("\n");
		String[] daten = p.split(result);

		//Manipulation verhindern
		if (daten.length < 3 || daten.length > 4) {
			result = "localhost\n3306\nroot";
			daten = p.split(result);
		}

		//Variablen bekommen die Werte aus der "config.dat"
		hostname = daten[0].trim();
		port = daten[1].trim();
		user = daten[2].trim();

		//Wurde kein Passwort gesetzt, so ist dieses ein Leerstring
		if (daten.length < 4)
			password = "";
		else
			password = daten[3].trim();
		URL url = getClass().getResource("/sql/complete.sql");

		try {
			InputStream in = url.openStream();
			DataInputStream data = new DataInputStream(in);

			byte buffer[] = new byte[in.available()];
			data.readFully(buffer);
			in.close();

			result = new String(buffer, 0, buffer.length);
			p = Pattern.compile("\n");

			String[] anweisungen = p.split(result);
			int n = 0;
			for (int i = 0; i < anweisungen.length; i++) {
				if (anweisungen[i].startsWith("#") || anweisungen[i].toCharArray().length == 1) {
					anweisungen[i] = null;
					n++;
				}
			}

			String[] queries = new String[anweisungen.length - n];
			int j = 0;
			for (int i = 0; i < anweisungen.length; i++) {
				if (anweisungen[i] != null) {
					queries[j] = anweisungen[i];
					j++;
				}
			}

			int i = 0;
			int m = 1;
			while (i < queries.length) {
				if (queries[i].startsWith(" ")) {
					queries[i - m] = queries[i - m].concat(queries[i]);
					m++;
					queries[i] = null;
				} else if (queries[i].startsWith(")")) {
					queries[i - m] = queries[i - m].concat(queries[i]);
					queries[i] = null;
				} else
					m = 1;
				i++;
			}

			int a = 0;
			for (int b = 0; b < queries.length; b++) {
				if (queries[b] == null) {
					a++;
				}
			}

			String[] queriesFinal = new String[queries.length - a];
			int c = 0;
			for (int d = 0; d < queries.length; d++) {
				if (queries[d] != null) {
					queriesFinal[c] = queries[d];
					c++;
				}
			}

			for (int z = 0; z < queriesFinal.length; z++) {
				char ersetzen[] = queriesFinal[z].toCharArray();
				for (int e = 0; e < ersetzen.length; e++) {
					if (ersetzen[e] == ';' || ersetzen[e] == '\n') {
						ersetzen[e] = ' ';
					}
				}
				queriesFinal[z] = String.valueOf(ersetzen);
			}

			try {
				// Load the MySQl JDBC driver
				DriverManager.registerDriver(new Driver());

				// Connect to the database
				// You must put a database name after the @ sign in the connection URL.
				// You can use either the fully specified SQL*net syntax or a short cut
				// syntax as <host>:<port>:<sid>.
				conn = DriverManager.getConnection("jdbc:mysql://" + hostname + ":" + port + "/" + dbname, user, password);
				conn.setAutoCommit(false);

				try {

					Statement stmt = conn.createStatement();
					for (int k = 0; k < queriesFinal.length; k++) {
						stmt.executeUpdate(queriesFinal[k]);
					}

				} catch (SQLException e) {
					wiSimLogger.log("einlesenQueries()", e);
					jLabelBeschreibung1.setText("Hat nicht geklappt!");
				}
			} catch (SQLException sqlE) {
				wiSimLogger.log("einlesenQueries()", sqlE);
				jLabelBeschreibung1.setText("Hat nicht geklappt!");
			}

		} catch (Exception e) {
			wiSimLogger.log("einlesenQueries()", e);
			jLabelBeschreibung1.setText("Hat nicht geklappt!");
		}

		jLabelBeschreibung1.setText("Fertig!");

	}

	/** Füllt die Textfelder mit den Daten aus der "config.dat" */
	public void fillDBInfoFields() {
		//Datei mit DB Infos wird eingelesen
		String result = "";
		try {
			FileInputStream file = new FileInputStream("config.dat");
			DataInputStream in = new DataInputStream(file);

			BASE64Decoder decoder = new BASE64Decoder();
			byte[] b = decoder.decodeBuffer(in);
			in.close();
			result = new String(b);

		} catch (IOException e) {
		}

		Pattern p = Pattern.compile("\n");
		String[] daten = p.split(result);

		//Manipulation verhindern
		if (daten.length < 3 || daten.length > 4) {
			result = "localhost\n3306\nroot";
			daten = p.split(result);
		}

		jTextFieldHostname.setText(daten[0].trim());
		jTextFieldPort.setText(daten[1].trim());
		jTextFieldUsername.setText(daten[2].trim());

		if (daten.length < 4)
			jPasswordField.setText("");
		else
			jPasswordField.setText(daten[3].trim());
	}

	/** Schreibt die Daten aus den Textfeldern in die "config.dat" */
	public void updateDBSettings() {
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

		String values = hostname + "\n" + port + "\n" + user + "\n" + String.valueOf(jPasswordField.getPassword());
		byte[] b = values.getBytes();
		BASE64Encoder encoder = new BASE64Encoder();
		values = encoder.encode(b);

		try {
			File file = new File("config.dat");
			FileWriter fw = new FileWriter(file);
			fw.write(values);
			fw.close();
		} catch (IOException e) {
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