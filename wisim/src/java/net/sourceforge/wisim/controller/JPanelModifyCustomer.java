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
 * JPanelKundeBearbeiten.java
 *
 * Created on 10. Februar 2003, 20:39
 */
package net.sourceforge.wisim.controller;

import net.sourceforge.wisim.dao.*;
import net.sourceforge.wisim.model.*;
import javax.swing.*;
import java.util.*;
/** Das Panel Customer Bearbeiten
 */
public class JPanelModifyCustomer extends javax.swing.JPanel {

	private WiSimDAO dao;
	private Hashtable kundenAuswahl;
	private Hashtable kundenObjekte;
	private Vector verlauf;
	private WiSimLogger logger;
	private WiSimMainController wiSimMainController;
	private String actualEMail;
	private String actualPLZ;

	/** Creates new form JPanelKundeBearbeiten
	 * @param wiSimMainController Der wiSimMainController
	 */
	public JPanelModifyCustomer(WiSimMainController wiSimMainController) {
		this.wiSimMainController = wiSimMainController;
		initComponents();
		initDAO(wiSimMainController);
		kundenAuswahl = new Hashtable();
		kundenObjekte = new Hashtable();
		verlauf = new Vector();
		logger = wiSimMainController.getWiSimLogger();
	}

	/** Das Data Access Object
	 * @param wiSimMainController Der wiSimMainController
	 */
	private void initDAO(WiSimMainController wiSimMainController) {
		dao = wiSimMainController.getDAO();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	private void initComponents() { //GEN-BEGIN:initComponents
		jPanelNeuerKunde3 = new javax.swing.JPanel();
		jLabelNeuerKunde3 = new javax.swing.JLabel();
		jLabelVorname3 = new javax.swing.JLabel();
		jTextFieldVorname3 = new javax.swing.JTextField();
		jLabelNachname3 = new javax.swing.JLabel();
		jTextFieldNachname3 = new javax.swing.JTextField();
		jLabelFirma3 = new javax.swing.JLabel();
		jTextFieldFirma3 = new javax.swing.JTextField();
		jLabelTelefon3 = new javax.swing.JLabel();
		jTextFieldTelefon3 = new javax.swing.JTextField();
		jLabelBewertung3 = new javax.swing.JLabel();
		jComboBoxBewertung3 = new javax.swing.JComboBox();
		jLabelBemerkungen3 = new javax.swing.JLabel();
		jScrollPane5 = new javax.swing.JScrollPane();
		jTextArea5 = new javax.swing.JTextArea();
		jLabelZuverkaessigkeit2 = new javax.swing.JLabel();
		jComboBoxZuverlaessigkeit2 = new javax.swing.JComboBox();
		jLabelPreisLage2 = new javax.swing.JLabel();
		jLabelKundeBearbeiten = new javax.swing.JLabel();
		jComboBoxKundenBearbeiten = new javax.swing.JComboBox();
		jTextFieldKundeBearbeitenVorname = new javax.swing.JTextField();
		jTextFieldKundeBearbeitenNachname = new javax.swing.JTextField();
		jTextFieldKundeBearbeitenFirma = new javax.swing.JTextField();
		jTextFieldKundeBearbeitenStrasse = new javax.swing.JTextField();
		jTextFieldKundeBearbeitenPLZ = new javax.swing.JTextField();
		jTextFieldKundeBearbeitenOrt = new javax.swing.JTextField();
		jTextFieldKundeBearbeitenTelefon = new javax.swing.JTextField();
		jTextFieldKundeBearbeitenFax = new javax.swing.JTextField();
		jTextFieldKundeBearbeitenEMail = new javax.swing.JTextField();
		jButtonKundeBearbeiten = new javax.swing.JButton();
		jButtonKundeLoeschen = new javax.swing.JButton();
		jLabelEMail = new javax.swing.JLabel();
		jLabelFax = new javax.swing.JLabel();
		jLabelTelefon = new javax.swing.JLabel();
		jLabelPLZ = new javax.swing.JLabel();
		jLabelStrasse = new javax.swing.JLabel();
		jLabelFirma = new javax.swing.JLabel();
		jLabelNachname = new javax.swing.JLabel();
		jLabelVorname = new javax.swing.JLabel();
		jLabelOrt = new javax.swing.JLabel();
		jLabelKundeBearbeitenKundentyp = new javax.swing.JLabel();
		jLabelKundeBearbeitenZuferlaessigkeit = new javax.swing.JLabel();
		jLabel1 = new javax.swing.JLabel();
		jComboBoxKundeBearbeitenZahlungsmoral = new javax.swing.JComboBox();
		jComboBoxKundeBearbeitenAnspruch = new javax.swing.JComboBox();
		jComboBoxKundeBearbeitenKundentyp = new javax.swing.JComboBox();
		jButtonNotizHinzufuegen = new javax.swing.JButton();
		jButtonNotizEntfernen = new javax.swing.JButton();
		jLabelNotiz = new javax.swing.JLabel();
		jTabbedPaneKundeBearbeitenNotizen = new javax.swing.JTabbedPane();
		jScrollPaneKundeBearbeitenBemerkung = new javax.swing.JScrollPane();
		jTextAreaKundeBearbeitenBemerkung = new javax.swing.JTextArea();
		jScrollPaneKundeBearbeitenVerlauf = new javax.swing.JScrollPane();
		jListTextFieldKundeBearbeitenVerlauf = new javax.swing.JList();
		jListTextFieldKundeBearbeitenVerlauf.setSelectionMode(0);

		setLayout(null);

		setPreferredSize(new java.awt.Dimension(800, 600));
		jPanelNeuerKunde3.setLayout(null);

		jLabelNeuerKunde3.setFont(new java.awt.Font("Dialog", 1, 24));
		jLabelNeuerKunde3.setText("Neuer Kunde");
		jPanelNeuerKunde3.add(jLabelNeuerKunde3);
		jLabelNeuerKunde3.setBounds(220, 0, 150, 40);

		jLabelVorname3.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabelVorname3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabelVorname3.setText("Vorname");
		jPanelNeuerKunde3.add(jLabelVorname3);
		jLabelVorname3.setBounds(10, 90, 120, 19);

		jPanelNeuerKunde3.add(jTextFieldVorname3);
		jTextFieldVorname3.setBounds(140, 90, 200, 20);

		jLabelNachname3.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabelNachname3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabelNachname3.setText("Nachname");
		jPanelNeuerKunde3.add(jLabelNachname3);
		jLabelNachname3.setBounds(10, 130, 120, 19);

		jPanelNeuerKunde3.add(jTextFieldNachname3);
		jTextFieldNachname3.setBounds(140, 130, 200, 20);

		jLabelFirma3.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabelFirma3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabelFirma3.setText("Firma");
		jPanelNeuerKunde3.add(jLabelFirma3);
		jLabelFirma3.setBounds(10, 170, 120, 19);

		jPanelNeuerKunde3.add(jTextFieldFirma3);
		jTextFieldFirma3.setBounds(140, 170, 200, 20);

		jLabelTelefon3.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabelTelefon3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabelTelefon3.setText("Telefon");
		jPanelNeuerKunde3.add(jLabelTelefon3);
		jLabelTelefon3.setBounds(10, 210, 120, 20);

		jPanelNeuerKunde3.add(jTextFieldTelefon3);
		jTextFieldTelefon3.setBounds(140, 210, 200, 20);

		jLabelBewertung3.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabelBewertung3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabelBewertung3.setText("Bewertung");
		jPanelNeuerKunde3.add(jLabelBewertung3);
		jLabelBewertung3.setBounds(10, 250, 120, 20);

		jPanelNeuerKunde3.add(jComboBoxBewertung3);
		jComboBoxBewertung3.setBounds(140, 250, 80, 25);

		jLabelBemerkungen3.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabelBemerkungen3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabelBemerkungen3.setText("Bemerkungen");
		jPanelNeuerKunde3.add(jLabelBemerkungen3);
		jLabelBemerkungen3.setBounds(10, 330, 120, 19);

		jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jTextArea5.setLineWrap(true);
		jScrollPane5.setViewportView(jTextArea5);

		jPanelNeuerKunde3.add(jScrollPane5);
		jScrollPane5.setBounds(140, 330, 210, 80);

		jLabelZuverkaessigkeit2.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabelZuverkaessigkeit2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabelZuverkaessigkeit2.setText("Zuverl\u00e4ssigkeit");
		jPanelNeuerKunde3.add(jLabelZuverkaessigkeit2);
		jLabelZuverkaessigkeit2.setBounds(10, 290, 120, 20);

		jPanelNeuerKunde3.add(jComboBoxZuverlaessigkeit2);
		jComboBoxZuverlaessigkeit2.setBounds(141, 290, 80, 25);

		jLabelPreisLage2.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabelPreisLage2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabelPreisLage2.setText("Preislage");
		jPanelNeuerKunde3.add(jLabelPreisLage2);
		jLabelPreisLage2.setBounds(230, 250, 120, 19);

		add(jPanelNeuerKunde3);
		jPanelNeuerKunde3.setBounds(0, 0, 0, 0);

		jLabelKundeBearbeiten.setFont(new java.awt.Font("Dialog", 1, 24));
		jLabelKundeBearbeiten.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabelKundeBearbeiten.setText("Kunde bearbeiten");
		add(jLabelKundeBearbeiten);
		jLabelKundeBearbeiten.setBounds(0, 0, 820, 40);

		jComboBoxKundenBearbeiten.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jComboBoxKundenBearbeitenActionPerformed(evt);
			}
		});
		jComboBoxKundenBearbeiten.addAncestorListener(new javax.swing.event.AncestorListener() {
			public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
			}
			public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
				jComboBoxKundenBearbeitenAncestorAdded(evt);
			}
			public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
			}
		});

		add(jComboBoxKundenBearbeiten);
		jComboBoxKundenBearbeiten.setBounds(130, 70, 210, 25);

		jTextFieldKundeBearbeitenVorname.setDocument(new JTextFieldValidation(50));
		add(jTextFieldKundeBearbeitenVorname);
		jTextFieldKundeBearbeitenVorname.setBounds(130, 110, 210, 20);

		jTextFieldKundeBearbeitenNachname.setDocument(new JTextFieldValidation(50));
		add(jTextFieldKundeBearbeitenNachname);
		jTextFieldKundeBearbeitenNachname.setBounds(130, 140, 210, 20);

		jTextFieldKundeBearbeitenFirma.setDocument(new JTextFieldValidation(50));
		add(jTextFieldKundeBearbeitenFirma);
		jTextFieldKundeBearbeitenFirma.setBounds(130, 170, 210, 20);

		jTextFieldKundeBearbeitenStrasse.setDocument(new JTextFieldValidation(50));
		add(jTextFieldKundeBearbeitenStrasse);
		jTextFieldKundeBearbeitenStrasse.setBounds(130, 200, 210, 20);

		jTextFieldKundeBearbeitenPLZ.setDocument(new JTextFieldValidation(5));
		jTextFieldKundeBearbeitenPLZ.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				jTextFieldKundeBearbeitenPLZFocusGained(evt);
			}
			public void focusLost(java.awt.event.FocusEvent evt) {
				jTextFieldKundeBearbeitenPLZFocusLost(evt);
			}
		});

		add(jTextFieldKundeBearbeitenPLZ);
		jTextFieldKundeBearbeitenPLZ.setBounds(130, 230, 40, 20);

		jTextFieldKundeBearbeitenOrt.setDocument(new JTextFieldValidation(50));
		add(jTextFieldKundeBearbeitenOrt);
		jTextFieldKundeBearbeitenOrt.setBounds(210, 230, 130, 20);

		jTextFieldKundeBearbeitenTelefon.setDocument(new JTextFieldValidation(30));
		add(jTextFieldKundeBearbeitenTelefon);
		jTextFieldKundeBearbeitenTelefon.setBounds(130, 260, 210, 20);

		jTextFieldKundeBearbeitenFax.setDocument(new JTextFieldValidation(30));
		add(jTextFieldKundeBearbeitenFax);
		jTextFieldKundeBearbeitenFax.setBounds(130, 290, 210, 20);

		jTextFieldKundeBearbeitenEMail.setDocument(new JTextFieldValidation(50));
		jTextFieldKundeBearbeitenEMail.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				jTextFieldKundeBearbeitenEMailFocusGained(evt);
			}
			public void focusLost(java.awt.event.FocusEvent evt) {
				jTextFieldKundeBearbeitenEMailFocusLost(evt);
			}
		});

		add(jTextFieldKundeBearbeitenEMail);
		jTextFieldKundeBearbeitenEMail.setBounds(130, 320, 210, 20);

		jButtonKundeBearbeiten.setText("Speichern");
		jButtonKundeBearbeiten.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonKundeBearbeitenActionPerformed(evt);
			}
		});

		add(jButtonKundeBearbeiten);
		jButtonKundeBearbeiten.setBounds(130, 360, 100, 26);

		jButtonKundeLoeschen.setText("L\u00f6schen");
		jButtonKundeLoeschen.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonKundeLoeschenActionPerformed(evt);
			}
		});

		add(jButtonKundeLoeschen);
		jButtonKundeLoeschen.setBounds(240, 360, 100, 26);

		jLabelEMail.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabelEMail.setText("E-Mail");
		add(jLabelEMail);
		jLabelEMail.setBounds(0, 320, 120, 20);

		jLabelFax.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabelFax.setText("Fax");
		add(jLabelFax);
		jLabelFax.setBounds(0, 290, 120, 20);

		jLabelTelefon.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabelTelefon.setText("Telefon");
		add(jLabelTelefon);
		jLabelTelefon.setBounds(0, 260, 120, 20);

		jLabelPLZ.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabelPLZ.setText("PLZ");
		add(jLabelPLZ);
		jLabelPLZ.setBounds(80, 230, 40, 20);

		jLabelStrasse.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabelStrasse.setText("Strasse");
		add(jLabelStrasse);
		jLabelStrasse.setBounds(0, 200, 120, 20);

		jLabelFirma.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabelFirma.setText("Firma");
		add(jLabelFirma);
		jLabelFirma.setBounds(0, 170, 120, 16);

		jLabelNachname.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabelNachname.setText("Nachname");
		add(jLabelNachname);
		jLabelNachname.setBounds(0, 140, 120, 16);

		jLabelVorname.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabelVorname.setText("Vorname");
		add(jLabelVorname);
		jLabelVorname.setBounds(0, 110, 120, 20);

		jLabelOrt.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabelOrt.setText("Ort");
		add(jLabelOrt);
		jLabelOrt.setBounds(180, 230, 18, 20);

		jLabelKundeBearbeitenKundentyp.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabelKundeBearbeitenKundentyp.setText("Kunden Typ");
		add(jLabelKundeBearbeitenKundentyp);
		jLabelKundeBearbeitenKundentyp.setBounds(360, 110, 120, 20);

		jLabelKundeBearbeitenZuferlaessigkeit.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabelKundeBearbeitenZuferlaessigkeit.setText("Anspr\u00fcche");
		add(jLabelKundeBearbeitenZuferlaessigkeit);
		jLabelKundeBearbeitenZuferlaessigkeit.setBounds(370, 170, 110, 20);

		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabel1.setText("Zahlungsmoral");
		add(jLabel1);
		jLabel1.setBounds(390, 140, 90, 20);

		jComboBoxKundeBearbeitenZahlungsmoral.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6" }));
		add(jComboBoxKundeBearbeitenZahlungsmoral);
		jComboBoxKundeBearbeitenZahlungsmoral.setBounds(490, 140, 40, 25);

		jComboBoxKundeBearbeitenAnspruch.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bitte w�hlen", "Preisfeilscher", "Service-Fan", "Termin-Fan", "Qualit�ts-Fan", "Atmosph�re-Typ" }));
		add(jComboBoxKundeBearbeitenAnspruch);
		jComboBoxKundeBearbeitenAnspruch.setBounds(490, 170, 140, 25);

		jComboBoxKundeBearbeitenKundentyp.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A", "B", "C" }));
		add(jComboBoxKundeBearbeitenKundentyp);
		jComboBoxKundeBearbeitenKundentyp.setBounds(490, 110, 40, 25);

		jButtonNotizHinzufuegen.setText("Hinzuf\u00fcgen");
		jButtonNotizHinzufuegen.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonNotizHinzufuegenActionPerformed(evt);
			}
		});

		add(jButtonNotizHinzufuegen);
		jButtonNotizHinzufuegen.setBounds(490, 360, 100, 26);

		jButtonNotizEntfernen.setText("Entfernen");
		jButtonNotizEntfernen.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonNotizEntfernenActionPerformed(evt);
			}
		});

		add(jButtonNotizEntfernen);
		jButtonNotizEntfernen.setBounds(600, 360, 100, 26);

		jLabelNotiz.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabelNotiz.setText("Bemerkungen");
		add(jLabelNotiz);
		jLabelNotiz.setBounds(360, 230, 120, 20);

		jTabbedPaneKundeBearbeitenNotizen.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
		jTextAreaKundeBearbeitenBemerkung.setEditable(false);
		jTextAreaKundeBearbeitenBemerkung.setLineWrap(true);
		jScrollPaneKundeBearbeitenBemerkung.setViewportView(jTextAreaKundeBearbeitenBemerkung);

		jTabbedPaneKundeBearbeitenNotizen.addTab("aktuell", jScrollPaneKundeBearbeitenBemerkung);

		jListTextFieldKundeBearbeitenVerlauf.setModel(new DefaultListModel());
		jListTextFieldKundeBearbeitenVerlauf.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
			public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
				jListTextFieldKundeBearbeitenVerlaufValueChanged(evt);
			}
		});

		jScrollPaneKundeBearbeitenVerlauf.setViewportView(jListTextFieldKundeBearbeitenVerlauf);

		jTabbedPaneKundeBearbeitenNotizen.addTab("Verlauf", jScrollPaneKundeBearbeitenVerlauf);

		add(jTabbedPaneKundeBearbeitenNotizen);
		jTabbedPaneKundeBearbeitenNotizen.setBounds(490, 210, 280, 130);

	} //GEN-END:initComponents

	private void jTextFieldKundeBearbeitenPLZFocusGained(java.awt.event.FocusEvent evt) { //GEN-FIRST:event_jTextFieldKundeBearbeitenPLZFocusGained
		actualPLZ = jTextFieldKundeBearbeitenPLZ.getText();
	} //GEN-LAST:event_jTextFieldKundeBearbeitenPLZFocusGained

	private void jTextFieldKundeBearbeitenEMailFocusGained(java.awt.event.FocusEvent evt) { //GEN-FIRST:event_jTextFieldKundeBearbeitenEMailFocusGained
		actualEMail = jTextFieldKundeBearbeitenEMail.getText();
	} //GEN-LAST:event_jTextFieldKundeBearbeitenEMailFocusGained

	private void jButtonKundeLoeschenActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jButtonKundeLoeschenActionPerformed
		int auswahl = getSelKundeID();
		if (auswahl != 0) {
			loescheKunde(getSelKundeID());
		} else {
			JOptionPane.showMessageDialog(this, "Sie m�ssen erst einen Kunden ausw�hlen.", "Fehler beim L�schen eines Kunden", JOptionPane.ERROR_MESSAGE);
		}
	} //GEN-LAST:event_jButtonKundeLoeschenActionPerformed

	private void jTextFieldKundeBearbeitenEMailFocusLost(java.awt.event.FocusEvent evt) { //GEN-FIRST:event_jTextFieldKundeBearbeitenEMailFocusLost
		Validator validate = new Validator();
		String email = jTextFieldKundeBearbeitenEMail.getText();
		boolean emailOk = false;
		if (!email.equals("") && email != null) {
			while (!emailOk && email != null) {
				if (!validate.checkEMail(email)) {
					email = JOptionPane.showInputDialog("Ung�ltige EMail! Bitte neu eingeben:", email);
				} else {
					emailOk = true;
				}
			}
			if (email != null) {
				jTextFieldKundeBearbeitenEMail.setText(email);
			} else {
				jTextFieldKundeBearbeitenEMail.setText(actualEMail);
			}
		}
	} //GEN-LAST:event_jTextFieldKundeBearbeitenEMailFocusLost

	private void jTextFieldKundeBearbeitenPLZFocusLost(java.awt.event.FocusEvent evt) { //GEN-FIRST:event_jTextFieldKundeBearbeitenPLZFocusLost
		Validator validate = new Validator();
		String plz = jTextFieldKundeBearbeitenPLZ.getText();
		boolean plzOk = false;
		if (!plz.equals("") && plz != null) {
			while (!plzOk && plz != null) {
				if (!validate.checkPlz(plz)) {
					plz = JOptionPane.showInputDialog("Ung�ltige PLZ! Bitte neu eingeben:", plz);
				} else {
					plzOk = true;
				}
			}
			if (plz != null) {
				jTextFieldKundeBearbeitenPLZ.setText(plz);
			} else {
				jTextFieldKundeBearbeitenPLZ.setText(actualPLZ);
			}
		}
	} //GEN-LAST:event_jTextFieldKundeBearbeitenPLZFocusLost

	private void jButtonKundeBearbeitenActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jButtonKundeBearbeitenActionPerformed
		int auswahl = getSelKundeID();
		if (auswahl == 0) {
			JOptionPane.showMessageDialog(this, "Sie m�ssen erst einen Kunden ausw�hlen.", "Fehler beim Speichern eines Kunden", JOptionPane.ERROR_MESSAGE);
		} else {
			Vector check = new Vector();
			if (jTextFieldKundeBearbeitenFirma.getText().equals(""))
				check.add("Firma");
			if (jTextFieldKundeBearbeitenNachname.getText().equals(""))
				check.add("Name");
			if (jTextFieldKundeBearbeitenVorname.getText().equals(""))
				check.add("Vorname");
			if (jTextFieldKundeBearbeitenTelefon.getText().equals(""))
				check.add("Telefon");
			if (jTextFieldKundeBearbeitenStrasse.getText().equals(""))
				check.add("Strasse");
			if (jTextFieldKundeBearbeitenOrt.getText().equals(""))
				check.add("Ort");
			if (jTextFieldKundeBearbeitenPLZ.getText().equals(""))
				check.add("PLZ");

			if (!check.isEmpty()) {
				if (check.size() > 1)
					JOptionPane.showMessageDialog(this, "Folgende Felder m�ssen ausgef�llt werden: " + check.toString().substring(1, check.toString().length() - 1), "Fehler beim Speichern des Kunden", JOptionPane.ERROR_MESSAGE);
				else
					JOptionPane.showMessageDialog(this, "Das folgende Feld muss ausgef�llt werden: " + check.toString().substring(1, check.toString().length() - 1), "Fehler beim Speichern des Kunden", JOptionPane.ERROR_MESSAGE);
			} else {
				int submit = JOptionPane.showConfirmDialog(this, "�nderungen an diesem Kunden Speichern?", "Kunden Speichern", JOptionPane.YES_NO_OPTION);
				if (submit == 0) {
					kundenSpeichern();
				}
			}
		}
	} //GEN-LAST:event_jButtonKundeBearbeitenActionPerformed

	private void jButtonNotizEntfernenActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jButtonNotizEntfernenActionPerformed
		if (jListTextFieldKundeBearbeitenVerlauf.isShowing()) {
			if (!jListTextFieldKundeBearbeitenVerlauf.isSelectionEmpty()) {
				loescheNotiz(jListTextFieldKundeBearbeitenVerlauf.getAnchorSelectionIndex());
			} else {
				JOptionPane.showMessageDialog(this, "Sie m�ssen im Verlauf eine Bemerkung markieren um sie zu l�schen.", "Hinweis", JOptionPane.INFORMATION_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this, "Sie m�ssen in Verlauf wechseln um eine Bemerkung zu l�schen.", "Hinweis", JOptionPane.INFORMATION_MESSAGE);
		}
	} //GEN-LAST:event_jButtonNotizEntfernenActionPerformed

	private void jButtonNotizHinzufuegenActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jButtonNotizHinzufuegenActionPerformed
		jTextAreaKundeBearbeitenBemerkung.removeAll();
		neueNotiz();
	} //GEN-LAST:event_jButtonNotizHinzufuegenActionPerformed

	private void jListTextFieldKundeBearbeitenVerlaufValueChanged(javax.swing.event.ListSelectionEvent evt) { //GEN-FIRST:event_jListTextFieldKundeBearbeitenVerlaufValueChanged
		if (!jListTextFieldKundeBearbeitenVerlauf.isSelectionEmpty()) {
			showNotiz(jListTextFieldKundeBearbeitenVerlauf.getMaxSelectionIndex());
		}
	} //GEN-LAST:event_jListTextFieldKundeBearbeitenVerlaufValueChanged

	private void jComboBoxKundenBearbeitenActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jComboBoxKundenBearbeitenActionPerformed
		ladeKunde(getSelKundeID());
	} //GEN-LAST:event_jComboBoxKundenBearbeitenActionPerformed

	private void jComboBoxKundenBearbeitenAncestorAdded(javax.swing.event.AncestorEvent evt) { //GEN-FIRST:event_jComboBoxKundenBearbeitenAncestorAdded
		ladeKunden();
	} //GEN-LAST:event_jComboBoxKundenBearbeitenAncestorAdded

	/** L�scht die Eingabemasken
	 *
	 */
	private void clearScreen() {
		jTextFieldKundeBearbeitenNachname.setText("");
		jTextFieldKundeBearbeitenVorname.setText("");
		jTextFieldKundeBearbeitenFirma.setText("");
		jTextFieldKundeBearbeitenStrasse.setText("");
		jTextFieldKundeBearbeitenTelefon.setText("");
		jTextFieldKundeBearbeitenFax.setText("");
		jTextFieldKundeBearbeitenEMail.setText("");
		jTextFieldKundeBearbeitenOrt.setText("");
		jTextFieldKundeBearbeitenPLZ.setText("");
		jTextAreaKundeBearbeitenBemerkung.setText("");
		DefaultListModel clearmodel1 = (DefaultListModel) jListTextFieldKundeBearbeitenVerlauf.getModel();
		clearmodel1.removeAllElements();
		jTextAreaKundeBearbeitenBemerkung.setText("");
		DefaultComboBoxModel modell = (DefaultComboBoxModel) jComboBoxKundeBearbeitenAnspruch.getModel();
		modell.setSelectedItem("Bitte w�hlen");
		jComboBoxKundeBearbeitenAnspruch.setModel(modell);
		modell = (DefaultComboBoxModel) jComboBoxKundeBearbeitenKundentyp.getModel();
		modell.setSelectedItem("B");
		jComboBoxKundeBearbeitenKundentyp.setModel(modell);
		modell = (DefaultComboBoxModel) jComboBoxKundeBearbeitenZahlungsmoral.getModel();
		modell.setSelectedItem("3");
		jComboBoxKundeBearbeitenZahlungsmoral.setModel(modell);
		jTabbedPaneKundeBearbeitenNotizen.setSelectedComponent(jScrollPaneKundeBearbeitenBemerkung);
	}

	/** F�llt die ComboBox Kundenliste mit den in der DB vorhandenen Kunden */
	private void ladeKunden() {
		clearScreen();
		Collection kundenliste = null;
		try {
			kundenliste = dao.getKunden();
		} catch (WiSimDAOException e) {
			logger.log("ladeKunden()", e);
		}

		DefaultComboBoxModel model = (DefaultComboBoxModel) jComboBoxKundenBearbeiten.getModel();
		model.removeAllElements();
		model.addElement("Bitte w�hlen");
		// Verhindert NullPointerException bei einer leeren Liste
		int indexcounter = 0;
		if (kundenliste != null) {
			Iterator it = kundenliste.iterator();
			while (it.hasNext()) {
				indexcounter++;
				Customer listenkunde = (Customer) it.next();
				//Eintragen der Kundennamen (Nachname, Vorname) in die ComboBox
				String listItem = String.valueOf(listenkunde.getNachname()).concat(", ");
				listItem = listItem.concat(String.valueOf(listenkunde.getVorname()));
				model.addElement(listItem);
				kundenObjekte.put((String.valueOf(indexcounter)), listenkunde);
				kundenAuswahl.put((String.valueOf(indexcounter)), String.valueOf(listenkunde.getId()));
			}
			jComboBoxKundenBearbeiten.setModel(model);
		}
	}

	/** Liefert die KD_Nr des aktiven Kunden
	 * @return int
	 */
	private int getSelKundeID() {
		//liefert listItem des selektierten Eintrags
		String listItem = String.valueOf(jComboBoxKundenBearbeiten.getSelectedIndex());
		//sucht das aktive KundenObjekt in Hashtabelle kundenObjekte
		Customer auswahlKunde = (Customer) kundenObjekte.get(listItem);
		if (auswahlKunde != null) {
			return auswahlKunde.getId();
		} else
			return 0;
	}

	/** L�dt einen Kunden zum Bearbeiten aus der Datenbank
	 * @param KdID Kunden ID
	 */
	private void ladeKunde(int KdID) {
		if (KdID != 0) {
			Customer lkunde = new Customer();
			try {
				lkunde = dao.getKunde(KdID);
			} catch (WiSimDAOException e) {
				logger.log("ladeKunden(int)", e);
			}
			jTextFieldKundeBearbeitenNachname.setText(lkunde.getNachname());
			jTextFieldKundeBearbeitenVorname.setText(lkunde.getVorname());
			jTextFieldKundeBearbeitenFirma.setText(lkunde.getFirma());
			jTextFieldKundeBearbeitenStrasse.setText(lkunde.getStrasse());
			jTextFieldKundeBearbeitenTelefon.setText(lkunde.getTelefon());
			jTextFieldKundeBearbeitenFax.setText(lkunde.getFax());
			jTextFieldKundeBearbeitenEMail.setText(lkunde.getEmail());
			jTextFieldKundeBearbeitenPLZ.setText(lkunde.getPlz());
			jTextFieldKundeBearbeitenOrt.setText(lkunde.getOrt());
			ladeVerlauf(lkunde.getId());
			setTypSelection(lkunde.getKundentyp());
			setAnspruchSelection(lkunde.getAnspruch());
			setZMorelSelection(lkunde.getZahlungsmoral());
			jTabbedPaneKundeBearbeitenNotizen.setSelectedComponent(jScrollPaneKundeBearbeitenBemerkung);
		} else {
			clearScreen();
		}
	}

	/** L�dt Kundenverlauf zum Bearbeiten aus der Datenbank
	 * @param KdNr Kunden ID
	 */
	private void ladeVerlauf(int KdNr) {
		DefaultListModel mymodel = (DefaultListModel) jListTextFieldKundeBearbeitenVerlauf.getModel();
		mymodel.removeAllElements();
		try {
			verlauf.clear();
			verlauf = (Vector) dao.getNotizen(KdNr);
			Iterator it = verlauf.iterator();
			Memo einzelnotiz = new Memo();
			while (it.hasNext()) {
				einzelnotiz = (Memo) it.next();
				mymodel.addElement(einzelnotiz.getDate() + ": " + einzelnotiz.getText());
			}
			//Eintragen der Bemerkungen in Verlauf Tab
			jListTextFieldKundeBearbeitenVerlauf.setModel(mymodel);
			showNotiz(verlauf.lastIndexOf(einzelnotiz));

		} catch (WiSimDAOException wde) {
			logger.log("ladeVerlauf(int)", wde);
		}

	}

	/** Gibt Notizobjekt in aktuell TAB aus
	 * @param noteNr Nummer der Bemerkung
	 */
	private void showNotiz(int noteNr) {
		if (verlauf.size() > 0) {
			Memo aktuell = new Memo();
			aktuell = (Memo) verlauf.elementAt(noteNr);
			jTextAreaKundeBearbeitenBemerkung.setText(aktuell.getDate() + ": " + aktuell.getText());
		} else {
			jTextAreaKundeBearbeitenBemerkung.setText("");
		}
	}

	/** Gibt Notizobjekt in aktuell TAB aus */
	private void neueNotiz() {
		String neu = "";
		if (jComboBoxKundenBearbeiten.getSelectedItem().toString().equalsIgnoreCase("Bitte w�hlen")) {
			JOptionPane.showMessageDialog(null, "Sie haben keinen Kunden ausgew�hlt!", "Warunung", JOptionPane.WARNING_MESSAGE);
		} else {
			neu = JOptionPane.showInputDialog("Neue Notiz eingeben:", neu);
			if (neu != null && neu.length() > 1) {
				int KdNr = getSelKundeID();
				if (KdNr != 0) {
					Memo dieneu = new Memo();
					try {
						dieneu.setText(neu);
						dieneu.setDate(new java.sql.Date(wiSimMainController.getActDate().getTime()));
						dieneu.setKundenNr(KdNr);
						dao.neueNotiz(dieneu);
						ladeVerlauf(KdNr);
					} catch (WiSimDAOException e) {
						logger.log("neueNotiz()", e);
					} catch (WiSimDAOWriteException e) {
						logger.log("neueNotiz()", e);
					}
				}
			}
		}
	}
	/** Loescht ein Notizobjekt
	 * @param listenId Nummer der Bemerkung
	 */
	private void loescheNotiz(int listenId) {
		Memo lNotiz = new Memo();
		int submit = JOptionPane.showConfirmDialog(this, "Wollen Sie die Notiz aus dem Bemerkungsverlauf entfernen?", "Notiz entfernen", JOptionPane.YES_NO_OPTION);
		if (submit == 0) {
			if (!verlauf.isEmpty()) {
				lNotiz = (Memo) verlauf.elementAt(listenId);
				try {
					dao.delNotiz(lNotiz.getId());
				} catch (WiSimDAOWriteException e) {
					logger.log("loescheNotiz(int)", e);
				}
			}
			ladeVerlauf(lNotiz.getKundenNr());
		}
	}

	/** Loescht einen Kunden
	 * @param KdId ID des Kunden
	 */
	private void loescheKunde(int KdId) {
		int submit = JOptionPane.showConfirmDialog(this, "Wollen Sie den Kunden aus der Kundenliste l�schen?", "Kunden l�schen", JOptionPane.YES_NO_OPTION);
		if (submit == 0) {
			try {
				dao.setKundenLoeschStatus(KdId, true);
				ladeKunden();
			} catch (WiSimDAOException e) {
				logger.log("loescheKunde(int)", e);
			} catch (WiSimDAOWriteException e) {
				logger.log("loescheKunde(int)", e);
			}
		}
	}

	/** Selectiert den Kundentyp entsprechend der Datenbankeintraege
	 * @param selectitem Der ausgew�hle Wert
	 */
	private void setTypSelection(String selectitem) {
		DefaultComboBoxModel typmodel = (DefaultComboBoxModel) jComboBoxKundeBearbeitenKundentyp.getModel();
		int size = typmodel.getSize();
		String item = "";
		for (int s = 0; s < size; s++) {
			item = (String) jComboBoxKundeBearbeitenKundentyp.getItemAt(s);
			if (item.equalsIgnoreCase(selectitem)) {
				typmodel.setSelectedItem(item);
				jComboBoxKundeBearbeitenKundentyp.setModel(typmodel);
			}
		}
	}

	/** Selectiert den KundenAnspruch entsprechend der Datenbankeintraege
	 * @param selectitem Der ausgew�hlte Wert
	 */
	private void setAnspruchSelection(String selectitem) {
		DefaultComboBoxModel anmodel = (DefaultComboBoxModel) jComboBoxKundeBearbeitenAnspruch.getModel();
		int size = anmodel.getSize();
		if (selectitem.equals("")) {
			selectitem = "Bitte w�hlen";
		}
		String item = "";
		for (int s = 0; s < size; s++) {
			item = (String) jComboBoxKundeBearbeitenAnspruch.getItemAt(s);
			if (item.equalsIgnoreCase(selectitem)) {
				anmodel.setSelectedItem(item);
				jComboBoxKundeBearbeitenAnspruch.setModel(anmodel);
			}
		}
	}

	/** Selectiert den KundenAnspruch entsprechend der Datenbankeintraege
	 * @param selectitem Der ausgew�hlte Wert
	 */
	private void setZMorelSelection(String selectitem) {
		DefaultComboBoxModel zmodel = (DefaultComboBoxModel) jComboBoxKundeBearbeitenZahlungsmoral.getModel();
		int size = zmodel.getSize();
		String item = "";
		for (int s = 0; s < size; s++) {
			item = (String) jComboBoxKundeBearbeitenZahlungsmoral.getItemAt(s);
			if (item.equals(selectitem)) {
				zmodel.setSelectedItem(item);
				jComboBoxKundeBearbeitenZahlungsmoral.setModel(zmodel);
			}
		}
	}

	/** Speichert Kundendaten */
	private void kundenSpeichern() {
		//liefert listItem des selektierten Eintrags
		String selectedItem = String.valueOf(jComboBoxKundenBearbeiten.getSelectedIndex());
		//sucht das aktive KundenObjekt in Hashtabelle kundenAuswahl
		Customer changedKunde = (Customer) kundenObjekte.get(selectedItem);
		int kdID = changedKunde.getId();

		Customer kunde = new Customer();
		kunde.setId(kdID);
		kunde.setNachname(jTextFieldKundeBearbeitenNachname.getText());
		kunde.setVorname(jTextFieldKundeBearbeitenVorname.getText());
		kunde.setFirma(jTextFieldKundeBearbeitenFirma.getText());
		kunde.setStrasse(jTextFieldKundeBearbeitenStrasse.getText());
		kunde.setTelefon(jTextFieldKundeBearbeitenTelefon.getText());
		kunde.setFax(jTextFieldKundeBearbeitenFax.getText());
		kunde.setEmail(jTextFieldKundeBearbeitenEMail.getText());
		kunde.setOrt(jTextFieldKundeBearbeitenOrt.getText());
		kunde.setPlz(jTextFieldKundeBearbeitenPLZ.getText());
		kunde.setZahlungsmoral((String.valueOf(jComboBoxKundeBearbeitenZahlungsmoral.getSelectedItem())));
		kunde.setAnspruch(String.valueOf(jComboBoxKundeBearbeitenAnspruch.getSelectedItem()));
		kunde.setKundentyp(jComboBoxKundeBearbeitenKundentyp.getSelectedItem().toString());

		try {

			City ort = new City();
			ort.setName(kunde.getOrt());
			ort.setPlz(kunde.getPlz());
			kunde.setPlzId(dao.neuerOrt(ort));

		} catch (WiSimDAOException e) {
			logger.log("kundenSpeichern()", e);
		} catch (WiSimDAOWriteException e) {
			logger.log("kundenSpeichern()", e);
		}

		try {
			dao.aendereKunden(kunde);
			ladeKunden();
		} catch (WiSimDAOWriteException e) {
			logger.log("kundenSpeichern()", e);
		} catch (WiSimDAOException e) {
			logger.log("kundenSpeichern()", e);
		}
	}
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jButtonKundeBearbeiten;
	private javax.swing.JButton jButtonKundeLoeschen;
	private javax.swing.JButton jButtonNotizEntfernen;
	private javax.swing.JButton jButtonNotizHinzufuegen;
	private javax.swing.JComboBox jComboBoxBewertung3;
	private javax.swing.JComboBox jComboBoxKundeBearbeitenAnspruch;
	protected javax.swing.JComboBox jComboBoxKundeBearbeitenKundentyp;
	private javax.swing.JComboBox jComboBoxKundeBearbeitenZahlungsmoral;
	private javax.swing.JComboBox jComboBoxKundenBearbeiten;
	private javax.swing.JComboBox jComboBoxZuverlaessigkeit2;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabelBemerkungen3;
	private javax.swing.JLabel jLabelBewertung3;
	private javax.swing.JLabel jLabelEMail;
	private javax.swing.JLabel jLabelFax;
	private javax.swing.JLabel jLabelFirma;
	private javax.swing.JLabel jLabelFirma3;
	private javax.swing.JLabel jLabelKundeBearbeiten;
	private javax.swing.JLabel jLabelKundeBearbeitenKundentyp;
	private javax.swing.JLabel jLabelKundeBearbeitenZuferlaessigkeit;
	private javax.swing.JLabel jLabelNachname;
	private javax.swing.JLabel jLabelNachname3;
	private javax.swing.JLabel jLabelNeuerKunde3;
	private javax.swing.JLabel jLabelNotiz;
	private javax.swing.JLabel jLabelOrt;
	private javax.swing.JLabel jLabelPLZ;
	private javax.swing.JLabel jLabelPreisLage2;
	private javax.swing.JLabel jLabelStrasse;
	private javax.swing.JLabel jLabelTelefon;
	private javax.swing.JLabel jLabelTelefon3;
	private javax.swing.JLabel jLabelVorname;
	private javax.swing.JLabel jLabelVorname3;
	private javax.swing.JLabel jLabelZuverkaessigkeit2;
	private javax.swing.JList jListTextFieldKundeBearbeitenVerlauf;
	private javax.swing.JPanel jPanelNeuerKunde3;
	private javax.swing.JScrollPane jScrollPane5;
	private javax.swing.JScrollPane jScrollPaneKundeBearbeitenBemerkung;
	private javax.swing.JScrollPane jScrollPaneKundeBearbeitenVerlauf;
	private javax.swing.JTabbedPane jTabbedPaneKundeBearbeitenNotizen;
	private javax.swing.JTextArea jTextArea5;
	private javax.swing.JTextArea jTextAreaKundeBearbeitenBemerkung;
	private javax.swing.JTextField jTextFieldFirma3;
	protected javax.swing.JTextField jTextFieldKundeBearbeitenEMail;
	protected javax.swing.JTextField jTextFieldKundeBearbeitenFax;
	protected javax.swing.JTextField jTextFieldKundeBearbeitenFirma;
	protected javax.swing.JTextField jTextFieldKundeBearbeitenNachname;
	protected javax.swing.JTextField jTextFieldKundeBearbeitenOrt;
	protected javax.swing.JTextField jTextFieldKundeBearbeitenPLZ;
	protected javax.swing.JTextField jTextFieldKundeBearbeitenStrasse;
	protected javax.swing.JTextField jTextFieldKundeBearbeitenTelefon;
	protected javax.swing.JTextField jTextFieldKundeBearbeitenVorname;
	private javax.swing.JTextField jTextFieldNachname3;
	private javax.swing.JTextField jTextFieldTelefon3;
	private javax.swing.JTextField jTextFieldVorname3;
	// End of variables declaration//GEN-END:variables
}