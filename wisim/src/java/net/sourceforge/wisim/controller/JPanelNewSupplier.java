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
 * JPanelNeuerLieferant.java
 *
 * Created on 10. Februar 2003, 21:00
 */

package net.sourceforge.wisim.controller;

import net.sourceforge.wisim.dao.*;
import net.sourceforge.wisim.model.*;
import javax.swing.*;

import java.util.*;

/** Das Panel Neuer Supplier
 * @author Kay Patzwald
 */
public class JPanelNewSupplier extends javax.swing.JPanel {
	private static final int HUB = 26;
  private WiSimDAO dao;
  private Hashtable artikel;
  private Hashtable einzelteileTabelle;
  private WiSimLogger wiSimLogger;
  
  /** Creates new form JPanelNeuerLieferant
   * @param wiSimMainController
   */
  public JPanelNewSupplier(WiSimMainController wiSimMainController) {
    initComponents();
    initDAO(wiSimMainController);
    artikel = new Hashtable();
    einzelteileTabelle = new Hashtable();
    wiSimLogger = wiSimMainController.getWiSimLogger();
  }
  
  private void initDAO(WiSimMainController wiSimMainController) {
    dao = wiSimMainController.getDAO();
  }
  
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  private void initComponents() {//GEN-BEGIN:initComponents
    jLabelUeberschriftNeuerLieferant = new javax.swing.JLabel();
    jLabelLieferantenFirma = new javax.swing.JLabel();
    jLabelUeberschriftArtikelBearbeiten = new javax.swing.JLabel();
    jScrollPaneNeuerLieferantArtikel = new javax.swing.JScrollPane();
    jListNeuerLieferantArtikel = new javax.swing.JList();
    jLabelNeuerLieferantArtikel = new javax.swing.JLabel();
    jLabelNeuerLieferantArtikelPreis = new javax.swing.JLabel();
    jTextFieldNeuerLieferantPreis = new javax.swing.JTextField();
    jButtonNeuerLieferantArtikelHinzufuegen = new javax.swing.JButton();
    jButtonNeuerLieferantArtieklEntfernen = new javax.swing.JButton();
    jLabelNeuerLieferantMindestAbnahme = new javax.swing.JLabel();
    jTextFieldNeuerLieferantMindestAbnahme = new javax.swing.JTextField();
    jTextFieldNeuerLieferantFirma = new javax.swing.JTextField();
    jLabelNeuerLieferantZuverlaessigkeit = new javax.swing.JLabel();
    jLabelNeuerLieferantLieferqualitaet = new javax.swing.JLabel();
    jButtonLieferantenAnlegen = new javax.swing.JButton();
    jLabelNeuerLieferantName = new javax.swing.JLabel();
    jTextFieldNeuerLieferantName = new javax.swing.JTextField();
    jLabelNeuerLieferantVorname = new javax.swing.JLabel();
    jTextFieldNeuerLieferantVorname = new javax.swing.JTextField();
    jLabelNeuerLieferantStrasse = new javax.swing.JLabel();
    jTextFieldNeuerLieferantStrasse = new javax.swing.JTextField();
    jLabelNeuerLieferantOrt = new javax.swing.JLabel();
    jTextFieldNeuerLieferantOrt = new javax.swing.JTextField();
    jLabelNeuerLieferantPLZ = new javax.swing.JLabel();
    jTextFieldNeuerLieferantPLZ = new javax.swing.JTextField();
    jLabelNeuerLieferantEMail = new javax.swing.JLabel();
    jTextFieldNeuerLieferantEMail = new javax.swing.JTextField();
    jLabelNeuerLieferantTelefon = new javax.swing.JLabel();
    jLabelNeuerLieferantFax = new javax.swing.JLabel();
    jTextFieldNeuerLieferantTelefon = new javax.swing.JTextField();
    jTextFieldNeuerLieferantFax = new javax.swing.JTextField();
    jComboBoxNeuerLieferantLieferqualitaet = new javax.swing.JComboBox();
    jComboBoxNeuerLieferantZuverlaessigkeit = new javax.swing.JComboBox();
    jComboBoxNeuerLieferantArtikel = new javax.swing.JComboBox();
    jLabel1 = new javax.swing.JLabel();

    setLayout(null);

    setPreferredSize(new java.awt.Dimension(800, 600));
    jLabelUeberschriftNeuerLieferant.setFont(new java.awt.Font("Dialog", 1, 24));
    jLabelUeberschriftNeuerLieferant.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabelUeberschriftNeuerLieferant.setText("Neuer Lieferant");
    add(jLabelUeberschriftNeuerLieferant);
    jLabelUeberschriftNeuerLieferant.setBounds(0, 0, 800, 40);

    jLabelLieferantenFirma.setFont(new java.awt.Font("Dialog", 1, 14));
    jLabelLieferantenFirma.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelLieferantenFirma.setText("Firma");
    add(jLabelLieferantenFirma);
    jLabelLieferantenFirma.setBounds(40, 80, 90, 20);

    jLabelUeberschriftArtikelBearbeiten.setFont(new java.awt.Font("Dialog", 1, 16));
    jLabelUeberschriftArtikelBearbeiten.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabelUeberschriftArtikelBearbeiten.setText("Artikel bearbeiten");
    add(jLabelUeberschriftArtikelBearbeiten);
    jLabelUeberschriftArtikelBearbeiten.setBounds(0, 260, 800, 21);

    jListNeuerLieferantArtikel.setModel(new DefaultListModel());
    jListNeuerLieferantArtikel.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
      public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
        jListNeuerLieferantArtikelValueChanged(evt);
      }
    });

    jScrollPaneNeuerLieferantArtikel.setViewportView(jListNeuerLieferantArtikel);

    add(jScrollPaneNeuerLieferantArtikel);
    jScrollPaneNeuerLieferantArtikel.setBounds(430, 300, 340, 80);

    jLabelNeuerLieferantArtikel.setFont(new java.awt.Font("Dialog", 1, 14));
    jLabelNeuerLieferantArtikel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelNeuerLieferantArtikel.setLabelFor(jComboBoxNeuerLieferantArtikel);
    jLabelNeuerLieferantArtikel.setText("Artikel");
    add(jLabelNeuerLieferantArtikel);
    jLabelNeuerLieferantArtikel.setBounds(20, 300, 120, 20);

    jLabelNeuerLieferantArtikelPreis.setFont(new java.awt.Font("Dialog", 1, 14));
    jLabelNeuerLieferantArtikelPreis.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelNeuerLieferantArtikelPreis.setLabelFor(jTextFieldNeuerLieferantPreis);
    jLabelNeuerLieferantArtikelPreis.setText("Artikelpreis");
    add(jLabelNeuerLieferantArtikelPreis);
    jLabelNeuerLieferantArtikelPreis.setBounds(20, 330, 120, 20);

    jTextFieldNeuerLieferantPreis.setDocument(new JTextFieldValidation(10));
    jTextFieldNeuerLieferantPreis.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
    jTextFieldNeuerLieferantPreis.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(java.awt.event.FocusEvent evt) {
        jTextFieldNeuerLieferantPreisFocusLost(evt);
      }
    });

    add(jTextFieldNeuerLieferantPreis);
    jTextFieldNeuerLieferantPreis.setBounds(150, 330, 80, 20);

    jButtonNeuerLieferantArtikelHinzufuegen.setText(">>");
    jButtonNeuerLieferantArtikelHinzufuegen.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButtonNeuerLieferantArtikelHinzufuegenActionPerformed(evt);
      }
    });

    add(jButtonNeuerLieferantArtikelHinzufuegen);
    jButtonNeuerLieferantArtikelHinzufuegen.setBounds(350, 300, 60, 26);

    jButtonNeuerLieferantArtieklEntfernen.setText("ENTF");
    jButtonNeuerLieferantArtieklEntfernen.setMargin(new java.awt.Insets(2, 10, 2, 10));
    jButtonNeuerLieferantArtieklEntfernen.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButtonNeuerLieferantArtieklEntfernenActionPerformed(evt);
      }
    });

    add(jButtonNeuerLieferantArtieklEntfernen);
    jButtonNeuerLieferantArtieklEntfernen.setBounds(350, 350, 60, 26);

    jLabelNeuerLieferantMindestAbnahme.setFont(new java.awt.Font("Dialog", 1, 14));
    jLabelNeuerLieferantMindestAbnahme.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelNeuerLieferantMindestAbnahme.setLabelFor(jTextFieldNeuerLieferantMindestAbnahme);
    jLabelNeuerLieferantMindestAbnahme.setText("Mindestabnahme");
    add(jLabelNeuerLieferantMindestAbnahme);
    jLabelNeuerLieferantMindestAbnahme.setBounds(10, 360, 130, 20);

    jTextFieldNeuerLieferantMindestAbnahme.setDocument(new JTextFieldValidation(10));
    jTextFieldNeuerLieferantMindestAbnahme.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
    jTextFieldNeuerLieferantMindestAbnahme.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(java.awt.event.FocusEvent evt) {
        jTextFieldNeuerLieferantMindestAbnahmeFocusLost(evt);
      }
    });

    add(jTextFieldNeuerLieferantMindestAbnahme);
    jTextFieldNeuerLieferantMindestAbnahme.setBounds(150, 360, 80, 20);

    jTextFieldNeuerLieferantFirma.setDocument(new JTextFieldValidation(50));
    add(jTextFieldNeuerLieferantFirma);
    jTextFieldNeuerLieferantFirma.setBounds(140, 80, 210, 20);

    jLabelNeuerLieferantZuverlaessigkeit.setFont(new java.awt.Font("Dialog", 1, 14));
    jLabelNeuerLieferantZuverlaessigkeit.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelNeuerLieferantZuverlaessigkeit.setText("Zuverl\u00e4ssigkeit");
    add(jLabelNeuerLieferantZuverlaessigkeit);
    jLabelNeuerLieferantZuverlaessigkeit.setBounds(340, 210, 130, 20);

    jLabelNeuerLieferantLieferqualitaet.setFont(new java.awt.Font("Dialog", 1, 14));
    jLabelNeuerLieferantLieferqualitaet.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelNeuerLieferantLieferqualitaet.setText("Lieferqualit\u00e4t");
    add(jLabelNeuerLieferantLieferqualitaet);
    jLabelNeuerLieferantLieferqualitaet.setBounds(340, 170, 130, 20);

    jButtonLieferantenAnlegen.setFont(new java.awt.Font("Dialog", 1, 16));
    jButtonLieferantenAnlegen.setText("Lieferanten anlegen");
    jButtonLieferantenAnlegen.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButtonLieferantenAnlegenActionPerformed(evt);
      }
    });

    add(jButtonLieferantenAnlegen);
    jButtonLieferantenAnlegen.setBounds(310, 420, 190, 31);

    jLabelNeuerLieferantName.setFont(new java.awt.Font("Dialog", 1, 14));
    jLabelNeuerLieferantName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelNeuerLieferantName.setLabelFor(jTextFieldNeuerLieferantName);
    jLabelNeuerLieferantName.setText("Name");
    add(jLabelNeuerLieferantName);
    jLabelNeuerLieferantName.setBounds(40, 50, 90, 20);

    jTextFieldNeuerLieferantName.setDocument(new JTextFieldValidation(50));
    add(jTextFieldNeuerLieferantName);
    jTextFieldNeuerLieferantName.setBounds(140, 50, 210, 20);

    jLabelNeuerLieferantVorname.setFont(new java.awt.Font("Dialog", 1, 14));
    jLabelNeuerLieferantVorname.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelNeuerLieferantVorname.setLabelFor(jTextFieldNeuerLieferantVorname);
    jLabelNeuerLieferantVorname.setText("Vorname");
    add(jLabelNeuerLieferantVorname);
    jLabelNeuerLieferantVorname.setBounds(380, 50, 90, 20);

    jTextFieldNeuerLieferantVorname.setDocument(new JTextFieldValidation(50));
    add(jTextFieldNeuerLieferantVorname);
    jTextFieldNeuerLieferantVorname.setBounds(480, 50, 210, 20);

    jLabelNeuerLieferantStrasse.setFont(new java.awt.Font("Dialog", 1, 14));
    jLabelNeuerLieferantStrasse.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelNeuerLieferantStrasse.setText("Stra\u00dfe");
    add(jLabelNeuerLieferantStrasse);
    jLabelNeuerLieferantStrasse.setBounds(380, 80, 90, 20);

    jTextFieldNeuerLieferantStrasse.setDocument(new JTextFieldValidation(50));
    add(jTextFieldNeuerLieferantStrasse);
    jTextFieldNeuerLieferantStrasse.setBounds(480, 80, 210, 20);

    jLabelNeuerLieferantOrt.setFont(new java.awt.Font("Dialog", 1, 14));
    jLabelNeuerLieferantOrt.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelNeuerLieferantOrt.setText("Ort");
    add(jLabelNeuerLieferantOrt);
    jLabelNeuerLieferantOrt.setBounds(40, 110, 90, 20);

    jTextFieldNeuerLieferantOrt.setDocument(new JTextFieldValidation(50));
    add(jTextFieldNeuerLieferantOrt);
    jTextFieldNeuerLieferantOrt.setBounds(140, 110, 210, 20);

    jLabelNeuerLieferantPLZ.setFont(new java.awt.Font("Dialog", 1, 14));
    jLabelNeuerLieferantPLZ.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelNeuerLieferantPLZ.setText("PLZ");
    add(jLabelNeuerLieferantPLZ);
    jLabelNeuerLieferantPLZ.setBounds(380, 110, 90, 20);

    jTextFieldNeuerLieferantPLZ.setColumns(5);
    jTextFieldNeuerLieferantPLZ.setDocument(new JTextFieldValidation(5));
    jTextFieldNeuerLieferantPLZ.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(java.awt.event.FocusEvent evt) {
        jTextFieldNeuerLieferantPLZFocusLost(evt);
      }
    });

    add(jTextFieldNeuerLieferantPLZ);
    jTextFieldNeuerLieferantPLZ.setBounds(480, 110, 40, 20);

    jLabelNeuerLieferantEMail.setFont(new java.awt.Font("Dialog", 1, 14));
    jLabelNeuerLieferantEMail.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelNeuerLieferantEMail.setLabelFor(jTextFieldNeuerLieferantVorname);
    jLabelNeuerLieferantEMail.setText("eMail");
    add(jLabelNeuerLieferantEMail);
    jLabelNeuerLieferantEMail.setBounds(40, 170, 90, 20);

    jTextFieldNeuerLieferantEMail.setDocument(new JTextFieldValidation(50));
    jTextFieldNeuerLieferantEMail.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(java.awt.event.FocusEvent evt) {
        jTextFieldNeuerLieferantEMailFocusLost(evt);
      }
    });

    add(jTextFieldNeuerLieferantEMail);
    jTextFieldNeuerLieferantEMail.setBounds(140, 170, 210, 20);

    jLabelNeuerLieferantTelefon.setFont(new java.awt.Font("Dialog", 1, 14));
    jLabelNeuerLieferantTelefon.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelNeuerLieferantTelefon.setLabelFor(jTextFieldNeuerLieferantVorname);
    jLabelNeuerLieferantTelefon.setText("Telefon");
    add(jLabelNeuerLieferantTelefon);
    jLabelNeuerLieferantTelefon.setBounds(40, 140, 90, 20);

    jLabelNeuerLieferantFax.setFont(new java.awt.Font("Dialog", 1, 14));
    jLabelNeuerLieferantFax.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelNeuerLieferantFax.setLabelFor(jTextFieldNeuerLieferantName);
    jLabelNeuerLieferantFax.setText("Fax");
    add(jLabelNeuerLieferantFax);
    jLabelNeuerLieferantFax.setBounds(380, 140, 90, 20);

    jTextFieldNeuerLieferantTelefon.setDocument(new JTextFieldValidation(30));
    add(jTextFieldNeuerLieferantTelefon);
    jTextFieldNeuerLieferantTelefon.setBounds(140, 140, 210, 20);

    jTextFieldNeuerLieferantFax.setDocument(new JTextFieldValidation(30));
    add(jTextFieldNeuerLieferantFax);
    jTextFieldNeuerLieferantFax.setBounds(480, 140, 210, 20);

    jComboBoxNeuerLieferantLieferqualitaet.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "3" }));
    jComboBoxNeuerLieferantLieferqualitaet.setEnabled(false);
    add(jComboBoxNeuerLieferantLieferqualitaet);
    jComboBoxNeuerLieferantLieferqualitaet.setBounds(480, 170, 50, 25);

    jComboBoxNeuerLieferantZuverlaessigkeit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "3" }));
    jComboBoxNeuerLieferantZuverlaessigkeit.setEnabled(false);
    add(jComboBoxNeuerLieferantZuverlaessigkeit);
    jComboBoxNeuerLieferantZuverlaessigkeit.setBounds(480, 210, 50, 25);

    jComboBoxNeuerLieferantArtikel.addAncestorListener(new javax.swing.event.AncestorListener() {
      public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
      }
      public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
        jComboBoxNeuerLieferantArtikelAncestorAdded(evt);
      }
      public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
      }
    });

    add(jComboBoxNeuerLieferantArtikel);
    jComboBoxNeuerLieferantArtikel.setBounds(150, 300, 180, 25);

    jLabel1.setFont(new java.awt.Font("Dialog", 1, 16));
    jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel1.setText("\u20ac");
    jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    add(jLabel1);
    jLabel1.setBounds(230, 330, 20, 21);

  }//GEN-END:initComponents

  private void jListNeuerLieferantArtikelValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListNeuerLieferantArtikelValueChanged
    if (!jListNeuerLieferantArtikel.isSelectionEmpty())
      artikelBearbeiten();
  }//GEN-LAST:event_jListNeuerLieferantArtikelValueChanged
  /* Der "Entfernen"-Button wurde gedr�ckt
   * @author Kay Patzwald
   */
  private void jButtonNeuerLieferantArtieklEntfernenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNeuerLieferantArtieklEntfernenActionPerformed
    if (!jListNeuerLieferantArtikel.isSelectionEmpty())
      loescheArtikel();
  }//GEN-LAST:event_jButtonNeuerLieferantArtieklEntfernenActionPerformed
  /* Der "Hinzuf�gen"-Button wurde gedr�ckt
   * @author Kay Patzwald
   */
  private void jButtonNeuerLieferantArtikelHinzufuegenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNeuerLieferantArtikelHinzufuegenActionPerformed
    Vector check = checkFormArtikel();
    if (check.isEmpty())
      artikelHinzufuegen();
    else {

      if (check.size() > 1)
        JOptionPane.showMessageDialog(this, "Folgende Felder m�ssen ausgef�llt werden: "
        + check.toString().substring(1,check.toString().length()-1), "Fehler beim Hinzuf�gen des Artikels", JOptionPane.ERROR_MESSAGE);
      else
        JOptionPane.showMessageDialog(this, "Das folgende Feld muss ausgef�llt werden: "
        + check.toString().substring(1,check.toString().length()-1), "Fehler beim Hinzuf�gen des Artikels", JOptionPane.ERROR_MESSAGE);
    }
  }//GEN-LAST:event_jButtonNeuerLieferantArtikelHinzufuegenActionPerformed
  /* Das jTextField Mindestabnahme ist nicht mehr im Focus
   * @author Kay Patzwald
   */
  private void jTextFieldNeuerLieferantMindestAbnahmeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNeuerLieferantMindestAbnahmeFocusLost
    Validator validate = new Validator();
    String zahl = jTextFieldNeuerLieferantMindestAbnahme.getText();
    
    JOptionPane errorPane = new JOptionPane();
    
    boolean zahlOk = false;
    if (!zahl.equals("")) {
      while (!zahlOk && zahl != null) {
        if (!validate.checkZahl(zahl)) {
          errorPane.setWantsInput(true);
          zahl = JOptionPane.showInputDialog("Ung�ltige Zahl!", zahl);
        } else {
          zahlOk = true;
        }
      }
      if (zahl != null) {
        jTextFieldNeuerLieferantMindestAbnahme.setText(zahl);
      } else {
        jTextFieldNeuerLieferantMindestAbnahme.setText("");
      }
    }
  }//GEN-LAST:event_jTextFieldNeuerLieferantMindestAbnahmeFocusLost
  /* Das jTextField Preis ist nicht mehr im Focus
   * @author Kay Patzwald
   */
  private void jTextFieldNeuerLieferantPreisFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNeuerLieferantPreisFocusLost
    Validator validate = new Validator();
    String preis = jTextFieldNeuerLieferantPreis.getText();
    
    //Falls Zahl ohne "." eingegeben wurde wird ".0" erg�nzt
    if (preis.matches("^[0-9]{1,7}$")) {
        preis = preis + ".00";
        jTextFieldNeuerLieferantPreis.setText(preis);
    }
    
    //Falls Zahl mit "," statt "." wird "," mit "." ersetzt
    if (preis.matches("^[0-9]{1,7}[,][0-9]{1,2}$")) {
        preis = preis.replaceAll(",", ".");
        jTextFieldNeuerLieferantPreis.setText(preis);
    }
    
    JOptionPane errorPane = new JOptionPane();
    
    boolean preisOk = false;
    if (!preis.equals("")) {
      while (!preisOk && preis != null) {
        if (!validate.checkPreis(preis)) {
          errorPane.setWantsInput(true);
          preis = JOptionPane.showInputDialog("Ung�ltiger Peis! Bitte geben Sie den Preis implements Format xx.xx ein!", preis);
        
        //Falls Zahl ohne "." eingegeben wurde wird ".0" erg�nzt
        if (preis != null) {
            if (preis.matches("^[0-9]{1,7}$")) {
                preis = preis + ".00";
                jTextFieldNeuerLieferantPreis.setText(preis);
            }
            
            //Falls Zahl mit "," statt "." wird "," mit "." ersetzt
            if (preis.matches("^[0-9]{1,7}[,][0-9]{1,2}$")) {
                preis = preis.replaceAll(",", ".");
                jTextFieldNeuerLieferantPreis.setText(preis);
            }
        }
        
        } else {
          preisOk = true;
        }
      }
      if (preis != null) {
        jTextFieldNeuerLieferantPreis.setText(preis);
      } else {
        jTextFieldNeuerLieferantPreis.setText("");
      }
    }
  }//GEN-LAST:event_jTextFieldNeuerLieferantPreisFocusLost
  
  private void jComboBoxNeuerLieferantArtikelAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jComboBoxNeuerLieferantArtikelAncestorAdded
    ladeEinzelteile();
  }//GEN-LAST:event_jComboBoxNeuerLieferantArtikelAncestorAdded
  /* Das jTextField eMail ist nicht mehr im Focus
   * @author Kay Patzwald
   */
  private void jTextFieldNeuerLieferantEMailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNeuerLieferantEMailFocusLost
    Validator validate = new Validator();
    String email = jTextFieldNeuerLieferantEMail.getText();
    
    JOptionPane errorPane = new JOptionPane();
    
    boolean emailOk = false;
    if (!email.equals("") && email != null) {
      while (!emailOk && email != null) {
        if (!validate.checkEMail(email)) {
          errorPane.setWantsInput(true);
          errorPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
          email = JOptionPane.showInputDialog("Ung�ltige eMail! Bitte neu eingeben:", email);
        } else {
          emailOk = true;
        }
      }
      if (email != null) {
        jTextFieldNeuerLieferantEMail.setText(email);
      } else {
        jTextFieldNeuerLieferantEMail.setText("");
      }
    }
  }//GEN-LAST:event_jTextFieldNeuerLieferantEMailFocusLost
  /* Das jTextField PLZ ist nicht mehr im Focus
   * @author Kay Patzwald
   */
  private void jTextFieldNeuerLieferantPLZFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNeuerLieferantPLZFocusLost
    Validator validate = new Validator();
    String plz = jTextFieldNeuerLieferantPLZ.getText();
    
    JOptionPane errorPane = new JOptionPane();
    
    boolean plzOk = false;
    if (!plz.equals("") && plz != null) {
      while (!plzOk && plz != null) {
        if (!validate.checkPlz(plz)) {
          errorPane.setWantsInput(true);
          errorPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
          plz = JOptionPane.showInputDialog("Ung�ltige PLZ! Bitte neu eingeben:", plz);
        } else {
          plzOk = true;
        }
      }
      if (plz != null) {
        jTextFieldNeuerLieferantPLZ.setText(plz);
      } else {
        jTextFieldNeuerLieferantPLZ.setText("");
      }
    }
  }//GEN-LAST:event_jTextFieldNeuerLieferantPLZFocusLost
  /* Der Button "Lieferant anlegen" wurde gedr�ckt
   * @author Kay Patzwald
   */
  private void jButtonLieferantenAnlegenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLieferantenAnlegenActionPerformed
    Vector check = checkFormLieferant();
    if (check.isEmpty())
      neuerLieferant();
    else {
      if (check.size() > 1)
        JOptionPane.showMessageDialog(this, "Folgende Felder m�ssen ausgef�llt werden: "
        + check.toString().substring(1,check.toString().length()-1), "Fehler beim Anlegen des neuen Lieferanten", JOptionPane.ERROR_MESSAGE);
      else
        JOptionPane.showMessageDialog(this, "Das folgende Feld muss ausgef�llt werden: "
        + check.toString().substring(1,check.toString().length()-1), "Fehler beim Anlegen des neuen Lieferanten", JOptionPane.ERROR_MESSAGE);
    }
  }//GEN-LAST:event_jButtonLieferantenAnlegenActionPerformed
  /** Legt einen neuen Lieferanten an */
  public void neuerLieferant() {
    String firma = jTextFieldNeuerLieferantFirma.getText();
    String name = jTextFieldNeuerLieferantName.getText();
    String vorname = jTextFieldNeuerLieferantVorname.getText();
    String telefon = jTextFieldNeuerLieferantTelefon.getText();
    String fax = jTextFieldNeuerLieferantFax.getText();
    String strasse = jTextFieldNeuerLieferantStrasse.getText();
    String ort = jTextFieldNeuerLieferantOrt.getText();
    String plz = jTextFieldNeuerLieferantPLZ.getText();
    String eMail = jTextFieldNeuerLieferantEMail.getText();
    String zuverlaessigkeit = jComboBoxNeuerLieferantZuverlaessigkeit.getSelectedItem().toString();
    String lieferqualitaet = jComboBoxNeuerLieferantLieferqualitaet.getSelectedItem().toString();
    
    Supplier lieferant = new Supplier(1, firma, name, vorname, telefon, fax, strasse, ort, plz, 0, eMail, zuverlaessigkeit, lieferqualitaet);
    int lieferantenID = 0;
    try {
      try {
        lieferantenID = dao.neuerLieferant(lieferant);
      } catch (WiSimDAOException e) {
        wiSimLogger.log("neuerLieferant()",e);
      }
    }catch (WiSimDAOWriteException e) {
			wiSimLogger.log("neuerLieferant()",e);
    }
    
    DefaultListModel model = (DefaultListModel) jListNeuerLieferantArtikel.getModel();
    if (lieferantenID > 0 && model.getSize() > 0)
    {
      for (int i = 0; i < model.getSize(); i++)
      {
        WiSimComponent teil = null;
        try
        {
          teil = dao.getEinzelteil(Integer.parseInt(einzelteileTabelle.get(model.getElementAt(i).toString()).toString()));
        } catch (WiSimDAOException e)
        {
					wiSimLogger.log("neuerLieferant()",e);
        }
        
        String[] eingabe = (String[]) artikel.get(model.getElementAt(i).toString());
        SupplyList liste = new SupplyList();
        liste.setLieferantenID(lieferantenID);
        liste.setEinzelteilID(teil.getNr());
        liste.setPreis(Double.parseDouble(eingabe[0]));
        liste.setMindestBestellMenge(Long.parseLong(eingabe[1]));
        try {
          try {
            dao.setLieferliste(liste);
          } catch (WiSimDAOException e) {
						wiSimLogger.log("neuerLieferant()",e);
          }
        } catch (WiSimDAOWriteException e) {
					wiSimLogger.log("neuerLieferant()",e);		
        }
      }
    }
    
    clearForm();
  }
  
  /** Pr�ft, ob alle notwendigen Felder ausgef�llt sind
   * @author Kay Patzwald
   * @return Vector
   */
  private Vector checkFormLieferant() {
    Vector errors = new Vector();
    if (jTextFieldNeuerLieferantFirma.getText().equals(""))
      errors.add("Firma");
    if (jTextFieldNeuerLieferantName.getText().equals(""))
      errors.add("Name");
    if (jTextFieldNeuerLieferantVorname.getText().equals(""))
      errors.add("Vorname");
    if (jTextFieldNeuerLieferantTelefon.getText().equals(""))
      errors.add("Telefon");
    if (jTextFieldNeuerLieferantStrasse.getText().equals(""))
      errors.add("Strasse");
    if (jTextFieldNeuerLieferantOrt.getText().equals(""))
      errors.add("Ort");
    if (jTextFieldNeuerLieferantPLZ.getText().equals(""))
      errors.add("PLZ");
    return errors;
  }
  
  /** Pr�ft, ob alle notwendigen Felder ausgef�llt sind
   * @author Kay Patzwald
   * @return Vector
   */
  private Vector checkFormArtikel() {
    Vector errors = new Vector();
    if (jTextFieldNeuerLieferantPreis.getText().equals(""))
      errors.add("Preis");
    if (jTextFieldNeuerLieferantMindestAbnahme.getText().equals(""))
      errors.add("Mindestabnahme");
    return errors;
  }
  
  /** Diese Funktion l�scht das Formular nach Bet�tigung des Buttons "Lieferanten anlegen"
   * @author Kay Patzwald
   */
  private void clearForm() {
    jTextFieldNeuerLieferantEMail.setText("");
    jTextFieldNeuerLieferantFax.setText("");
    jTextFieldNeuerLieferantFirma.setText("");
    jTextFieldNeuerLieferantName.setText("");
    jTextFieldNeuerLieferantOrt.setText("");
    jTextFieldNeuerLieferantPLZ.setText("");
    jTextFieldNeuerLieferantStrasse.setText("");
    jTextFieldNeuerLieferantTelefon.setText("");
    jTextFieldNeuerLieferantVorname.setText("");
    jComboBoxNeuerLieferantLieferqualitaet.setSelectedIndex(0);
    jComboBoxNeuerLieferantZuverlaessigkeit.setSelectedIndex(0);
    jComboBoxNeuerLieferantArtikel.setSelectedIndex(0);
    jTextFieldNeuerLieferantMindestAbnahme.setText("");
    jTextFieldNeuerLieferantPreis.setText("");
    DefaultListModel model = (DefaultListModel) jListNeuerLieferantArtikel.getModel();
    model.removeAllElements();
    jListNeuerLieferantArtikel.setModel(model);
    artikel.clear();
  }
  
  /** F�llt die ComboBox Einzelteile mit den in der DB vorhandenen Teilen
   * @author Kay Patzwald
   */
  private void ladeEinzelteile() {
    Collection teile = null;
    try {
      teile = dao.getEinzelteile();
    } catch (WiSimDAOException e) {
			wiSimLogger.log("ladeEinzelteile()",e);
    }
    
    DefaultComboBoxModel model = (DefaultComboBoxModel) jComboBoxNeuerLieferantArtikel.getModel();
    model.removeAllElements();
    
    // Verhindert NullPointerException bei einer leeren Liste
    if (teile != null) {
      Iterator it = teile.iterator();
      while (it.hasNext()) {
        WiSimComponent teil = (WiSimComponent) it.next();
        if (teil.getNr() != HUB)
        	model.addElement(teil.getName());
        einzelteileTabelle.put(teil.getName(), String.valueOf(teil.getNr()));
      }
      jComboBoxNeuerLieferantArtikel.setModel(model);
    }
  }
  
  /* Nimmt einen Article in die Liste auf
   * @author Kay Patzwald
   */
  private void artikelHinzufuegen() {
    boolean ueberschreiben = true;
    int position = -1;
    String [] teile = new String[2];
    teile[0] = jTextFieldNeuerLieferantPreis.getText();
    teile[1] = jTextFieldNeuerLieferantMindestAbnahme.getText();
    if (artikel.containsKey(jComboBoxNeuerLieferantArtikel.getSelectedItem().toString())) {
      JOptionPane errorPane = new JOptionPane();
      errorPane.setMessageType(JOptionPane.WARNING_MESSAGE);
      int i = JOptionPane.showConfirmDialog(this, "Dieser Article ist bereits in der Liste. Wollen Sie ihn �berschreiben?", "Fehler beim Hinzuf�gen des Artikels", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
      
      if (i == 0) {
        ueberschreiben = true;
        DefaultListModel model = (DefaultListModel) jListNeuerLieferantArtikel.getModel();
        position = model.indexOf(jComboBoxNeuerLieferantArtikel.getSelectedItem().toString());
        model.removeElement(jComboBoxNeuerLieferantArtikel.getSelectedItem().toString());
        jListNeuerLieferantArtikel.setModel(model);
      }
      else
        ueberschreiben = false;
    }
    
    if (ueberschreiben) {
      artikel.put(jComboBoxNeuerLieferantArtikel.getSelectedItem().toString(), teile);
      DefaultListModel model = (DefaultListModel) jListNeuerLieferantArtikel.getModel();
      if (position == -1)
        model.addElement(jComboBoxNeuerLieferantArtikel.getSelectedItem().toString());
      else
        model.add(position, jComboBoxNeuerLieferantArtikel.getSelectedItem().toString());
      jListNeuerLieferantArtikel.setModel(model);
    }
  }
  
  /* L�scht die ausgew�hlten Article
   * @author Kay Patzwald
   */
  private void loescheArtikel() {
    int [] indizies = jListNeuerLieferantArtikel.getSelectedIndices();
    DefaultListModel model = (DefaultListModel) jListNeuerLieferantArtikel.getModel();
    for (int i = indizies.length-1; i >= 0; i--) {
      artikel.remove(model.getElementAt(indizies[i]));
      model.removeElementAt(indizies[i]);
    }
    jListNeuerLieferantArtikel.setModel(model);
  }
  
  /* L�dt den ausgew�hlten Article ins Formular. Wenn mehrere ausgew�hlt worden sind
   * wird der erste ins Formular geladen.
   * @author Kay Patzwald
   */
  private void artikelBearbeiten() {
    int [] indizies = jListNeuerLieferantArtikel.getSelectedIndices();
    DefaultListModel model = (DefaultListModel) jListNeuerLieferantArtikel.getModel();
    jComboBoxNeuerLieferantArtikel.setSelectedItem(model.getElementAt(indizies[0]));
    String[] teil = (String[]) artikel.get(model.getElementAt(indizies[0]));
    jTextFieldNeuerLieferantPreis.setText(teil[0]);
    jTextFieldNeuerLieferantMindestAbnahme.setText(teil[1]);
  }
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButtonLieferantenAnlegen;
  private javax.swing.JButton jButtonNeuerLieferantArtieklEntfernen;
  private javax.swing.JButton jButtonNeuerLieferantArtikelHinzufuegen;
  private javax.swing.JComboBox jComboBoxNeuerLieferantArtikel;
  private javax.swing.JComboBox jComboBoxNeuerLieferantLieferqualitaet;
  private javax.swing.JComboBox jComboBoxNeuerLieferantZuverlaessigkeit;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabelLieferantenFirma;
  private javax.swing.JLabel jLabelNeuerLieferantArtikel;
  private javax.swing.JLabel jLabelNeuerLieferantArtikelPreis;
  private javax.swing.JLabel jLabelNeuerLieferantEMail;
  private javax.swing.JLabel jLabelNeuerLieferantFax;
  private javax.swing.JLabel jLabelNeuerLieferantLieferqualitaet;
  private javax.swing.JLabel jLabelNeuerLieferantMindestAbnahme;
  private javax.swing.JLabel jLabelNeuerLieferantName;
  private javax.swing.JLabel jLabelNeuerLieferantOrt;
  private javax.swing.JLabel jLabelNeuerLieferantPLZ;
  private javax.swing.JLabel jLabelNeuerLieferantStrasse;
  private javax.swing.JLabel jLabelNeuerLieferantTelefon;
  private javax.swing.JLabel jLabelNeuerLieferantVorname;
  private javax.swing.JLabel jLabelNeuerLieferantZuverlaessigkeit;
  private javax.swing.JLabel jLabelUeberschriftArtikelBearbeiten;
  private javax.swing.JLabel jLabelUeberschriftNeuerLieferant;
  private javax.swing.JList jListNeuerLieferantArtikel;
  private javax.swing.JScrollPane jScrollPaneNeuerLieferantArtikel;
  private javax.swing.JTextField jTextFieldNeuerLieferantEMail;
  private javax.swing.JTextField jTextFieldNeuerLieferantFax;
  private javax.swing.JTextField jTextFieldNeuerLieferantFirma;
  private javax.swing.JTextField jTextFieldNeuerLieferantMindestAbnahme;
  private javax.swing.JTextField jTextFieldNeuerLieferantName;
  private javax.swing.JTextField jTextFieldNeuerLieferantOrt;
  private javax.swing.JTextField jTextFieldNeuerLieferantPLZ;
  private javax.swing.JTextField jTextFieldNeuerLieferantPreis;
  private javax.swing.JTextField jTextFieldNeuerLieferantStrasse;
  private javax.swing.JTextField jTextFieldNeuerLieferantTelefon;
  private javax.swing.JTextField jTextFieldNeuerLieferantVorname;
  // End of variables declaration//GEN-END:variables
  
}
