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
 * JPanelNeuerKunde.java
 *
 * Created on 10. Februar 2003, 18:13
 */

package net.sourceforge.wisim.controller;

import net.sourceforge.wisim.model.*;
import net.sourceforge.wisim.dao.*;
import java.util.*;
import javax.swing.*;

/**
 * JPanelNeuerKunde erm�glicht das eintragen eines neuen Kunden.
 * @author Benjamin Pasero
 */
public class JPanelNewCustomer extends javax.swing.JPanel {
    
    private WiSimDAO dao;
    private WiSimMainController wiSimMainController;
    
    //Logger
    private WiSimLogger wiSimLogger;
    
    /** Creates new form JPanelNeuerKunde
     * @param wiSimMainController Der Maincontroller
     */
    public JPanelNewCustomer(WiSimMainController wiSimMainController) {
        this.wiSimMainController = wiSimMainController;
        wiSimLogger = wiSimMainController.getWiSimLogger();
        initComponents();
        initDAO(wiSimMainController);
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
        jOptionPaneEMailInvalid = new javax.swing.JOptionPane();
        jOptionMissedFields = new javax.swing.JOptionPane();
        jLabelNeuerKunde = new javax.swing.JLabel();
        jLabelVorname = new javax.swing.JLabel();
        jLabelNachname = new javax.swing.JLabel();
        jTextFieldNeuerKundeNachname = new javax.swing.JTextField();
        jLabelFirma = new javax.swing.JLabel();
        jTextFieldNeuerKundeFirma = new javax.swing.JTextField();
        jLabelStrasse = new javax.swing.JLabel();
        jTextFieldNeuerKundeStrasse = new javax.swing.JTextField();
        jLabelKundentyp = new javax.swing.JLabel();
        jComboBoxNeuerKundeKundentyp = new javax.swing.JComboBox();
        jLabelNotiz = new javax.swing.JLabel();
        jScrollPaneBemerkungen = new javax.swing.JScrollPane();
        jTextAreaNeuerKundeNotiz = new javax.swing.JTextArea();
        jButtonNeuerKundeSpeichern = new javax.swing.JButton();
        jLabelPLZ = new javax.swing.JLabel();
        jTextFieldNeuerKundePLZ = new javax.swing.JTextField();
        jLabelTelefon = new javax.swing.JLabel();
        jTextFieldNeuerKundeTelefon = new javax.swing.JTextField();
        jLabelFax = new javax.swing.JLabel();
        jTextFieldNeuerKundeFax = new javax.swing.JTextField();
        jLabelEMail = new javax.swing.JLabel();
        jTextFieldNeuerKundeEMail = new javax.swing.JTextField();
        jLabelOrt = new javax.swing.JLabel();
        jTextFieldNeuerKundeOrt = new javax.swing.JTextField();
        jButtonNeuerKundeReset = new javax.swing.JButton();
        jSeparator = new javax.swing.JSeparator();
        jTextFieldNeuerKundeVorname = new javax.swing.JTextField();
        jComboBoxNeuerLieferantZuverlaessigkeit = new javax.swing.JComboBox();
        jLabelZahlungsmoral = new javax.swing.JLabel();

        jOptionMissedFields.setMessageType(0);

        setLayout(null);

        setPreferredSize(new java.awt.Dimension(800, 600));
        jLabelNeuerKunde.setFont(new java.awt.Font("Dialog", 1, 24));
        jLabelNeuerKunde.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNeuerKunde.setText("Neuer Kunde");
        add(jLabelNeuerKunde);
        jLabelNeuerKunde.setBounds(0, 0, 800, 40);

        jLabelVorname.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabelVorname.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelVorname.setText("Vorname");
        add(jLabelVorname);
        jLabelVorname.setBounds(350, 60, 120, 19);

        jLabelNachname.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabelNachname.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelNachname.setText("Name");
        add(jLabelNachname);
        jLabelNachname.setBounds(-10, 60, 120, 19);

        jTextFieldNeuerKundeNachname.setDocument(new JTextFieldValidation(50));
        add(jTextFieldNeuerKundeNachname);
        jTextFieldNeuerKundeNachname.setBounds(120, 60, 210, 20);

        jLabelFirma.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabelFirma.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelFirma.setText("Firma");
        add(jLabelFirma);
        jLabelFirma.setBounds(-10, 90, 120, 19);

        jTextFieldNeuerKundeFirma.setDocument(new JTextFieldValidation(50));
        add(jTextFieldNeuerKundeFirma);
        jTextFieldNeuerKundeFirma.setBounds(120, 90, 210, 20);

        jLabelStrasse.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabelStrasse.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelStrasse.setText("Strasse");
        add(jLabelStrasse);
        jLabelStrasse.setBounds(350, 90, 120, 20);

        jTextFieldNeuerKundeStrasse.setDocument(new JTextFieldValidation(50));
        add(jTextFieldNeuerKundeStrasse);
        jTextFieldNeuerKundeStrasse.setBounds(480, 90, 210, 20);

        jLabelKundentyp.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabelKundentyp.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelKundentyp.setText("Kunden Typ");
        add(jLabelKundentyp);
        jLabelKundentyp.setBounds(350, 180, 120, 20);

        jComboBoxNeuerKundeKundentyp.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A", "B", "C" }));
        jComboBoxNeuerKundeKundentyp.setSelectedIndex(1);
        add(jComboBoxNeuerKundeKundentyp);
        jComboBoxNeuerKundeKundentyp.setBounds(480, 180, 40, 25);

        jLabelNotiz.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabelNotiz.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelNotiz.setText("Bemerkungen");
        add(jLabelNotiz);
        jLabelNotiz.setBounds(320, 240, 120, 19);

        jScrollPaneBemerkungen.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jTextAreaNeuerKundeNotiz.setLineWrap(true);
        jScrollPaneBemerkungen.setViewportView(jTextAreaNeuerKundeNotiz);

        add(jScrollPaneBemerkungen);
        jScrollPaneBemerkungen.setBounds(90, 270, 600, 120);

        jButtonNeuerKundeSpeichern.setText("Speichern");
        jButtonNeuerKundeSpeichern.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNeuerKundeSpeichernActionPerformed(evt);
            }
        });

        add(jButtonNeuerKundeSpeichern);
        jButtonNeuerKundeSpeichern.setBounds(270, 420, 110, 26);

        jLabelPLZ.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabelPLZ.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelPLZ.setText("PLZ");
        add(jLabelPLZ);
        jLabelPLZ.setBounds(430, 120, 40, 20);

        jTextFieldNeuerKundePLZ.setDocument(new JTextFieldValidation(5));
        jTextFieldNeuerKundePLZ.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldNeuerKundePLZFocusLost(evt);
            }
        });

        add(jTextFieldNeuerKundePLZ);
        jTextFieldNeuerKundePLZ.setBounds(480, 120, 40, 20);

        jLabelTelefon.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabelTelefon.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelTelefon.setText("Telefon");
        add(jLabelTelefon);
        jLabelTelefon.setBounds(-10, 150, 120, 20);

        jTextFieldNeuerKundeTelefon.setDocument(new JTextFieldValidation(30));
        add(jTextFieldNeuerKundeTelefon);
        jTextFieldNeuerKundeTelefon.setBounds(120, 150, 210, 20);

        jLabelFax.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabelFax.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelFax.setText("Fax");
        add(jLabelFax);
        jLabelFax.setBounds(350, 150, 120, 20);

        jTextFieldNeuerKundeFax.setDocument(new JTextFieldValidation(30));
        add(jTextFieldNeuerKundeFax);
        jTextFieldNeuerKundeFax.setBounds(480, 150, 210, 20);

        jLabelEMail.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabelEMail.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelEMail.setText("eMail");
        add(jLabelEMail);
        jLabelEMail.setBounds(-10, 180, 120, 20);

        jTextFieldNeuerKundeEMail.setDocument(new JTextFieldValidation(50));
        jTextFieldNeuerKundeEMail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldNeuerKundeEMailFocusLost(evt);
            }
        });

        add(jTextFieldNeuerKundeEMail);
        jTextFieldNeuerKundeEMail.setBounds(120, 180, 210, 20);

        jLabelOrt.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabelOrt.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelOrt.setText("Ort");
        add(jLabelOrt);
        jLabelOrt.setBounds(80, 120, 30, 20);

        jTextFieldNeuerKundeOrt.setDocument(new JTextFieldValidation(50));
        add(jTextFieldNeuerKundeOrt);
        jTextFieldNeuerKundeOrt.setBounds(120, 120, 210, 20);

        jButtonNeuerKundeReset.setText("L\u00f6schen");
        jButtonNeuerKundeReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNeuerKundeResetActionPerformed(evt);
            }
        });

        add(jButtonNeuerKundeReset);
        jButtonNeuerKundeReset.setBounds(420, 420, 110, 26);

        add(jSeparator);
        jSeparator.setBounds(90, 220, 600, 10);

        jTextFieldNeuerKundeVorname.setDocument(new JTextFieldValidation(50));
        add(jTextFieldNeuerKundeVorname);
        jTextFieldNeuerKundeVorname.setBounds(480, 60, 210, 20);

        jComboBoxNeuerLieferantZuverlaessigkeit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "3" }));
        jComboBoxNeuerLieferantZuverlaessigkeit.setEnabled(false);
        add(jComboBoxNeuerLieferantZuverlaessigkeit);
        jComboBoxNeuerLieferantZuverlaessigkeit.setBounds(650, 180, 40, 25);

        jLabelZahlungsmoral.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabelZahlungsmoral.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelZahlungsmoral.setText("Zahlungsmoral");
        add(jLabelZahlungsmoral);
        jLabelZahlungsmoral.setBounds(520, 180, 120, 20);

    }//GEN-END:initComponents
    
    private void jTextFieldNeuerKundePLZFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNeuerKundePLZFocusLost
        validatePLZ();
    }//GEN-LAST:event_jTextFieldNeuerKundePLZFocusLost
    
    private void jTextFieldNeuerKundeEMailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNeuerKundeEMailFocusLost
        validateEmail();
    }//GEN-LAST:event_jTextFieldNeuerKundeEMailFocusLost
    
    private void jButtonNeuerKundeResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNeuerKundeResetActionPerformed
        resetFields();
    }//GEN-LAST:event_jButtonNeuerKundeResetActionPerformed
    
  private void jButtonNeuerKundeSpeichernActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNeuerKundeSpeichernActionPerformed
        neuerKunde();
  }//GEN-LAST:event_jButtonNeuerKundeSpeichernActionPerformed
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonNeuerKundeReset;
    private javax.swing.JButton jButtonNeuerKundeSpeichern;
    protected javax.swing.JComboBox jComboBoxNeuerKundeKundentyp;
    private javax.swing.JComboBox jComboBoxNeuerLieferantZuverlaessigkeit;
    private javax.swing.JLabel jLabelEMail;
    private javax.swing.JLabel jLabelFax;
    private javax.swing.JLabel jLabelFirma;
    private javax.swing.JLabel jLabelKundentyp;
    private javax.swing.JLabel jLabelNachname;
    private javax.swing.JLabel jLabelNeuerKunde;
    private javax.swing.JLabel jLabelNotiz;
    private javax.swing.JLabel jLabelOrt;
    private javax.swing.JLabel jLabelPLZ;
    private javax.swing.JLabel jLabelStrasse;
    private javax.swing.JLabel jLabelTelefon;
    private javax.swing.JLabel jLabelVorname;
    private javax.swing.JLabel jLabelZahlungsmoral;
    private javax.swing.JOptionPane jOptionMissedFields;
    private javax.swing.JOptionPane jOptionPaneEMailInvalid;
    private javax.swing.JScrollPane jScrollPaneBemerkungen;
    private javax.swing.JSeparator jSeparator;
    protected javax.swing.JTextArea jTextAreaNeuerKundeNotiz;
    protected javax.swing.JTextField jTextFieldNeuerKundeEMail;
    protected javax.swing.JTextField jTextFieldNeuerKundeFax;
    protected javax.swing.JTextField jTextFieldNeuerKundeFirma;
    protected javax.swing.JTextField jTextFieldNeuerKundeNachname;
    protected javax.swing.JTextField jTextFieldNeuerKundeOrt;
    protected javax.swing.JTextField jTextFieldNeuerKundePLZ;
    protected javax.swing.JTextField jTextFieldNeuerKundeStrasse;
    protected javax.swing.JTextField jTextFieldNeuerKundeTelefon;
    protected javax.swing.JTextField jTextFieldNeuerKundeVorname;
    // End of variables declaration//GEN-END:variables
    
    /** Validiert die PLZ Eingabe */
    public void validatePLZ() {
        Validator validate = new Validator();
        String plz = jTextFieldNeuerKundePLZ.getText();
        boolean plzOk = false;
        if (!plz.equals("") && plz != null) {
            while (!plzOk && plz != null) {
                if (!validate.checkPlz(plz)) {
                    jOptionPaneEMailInvalid.setWantsInput(true);
                    plz = JOptionPane.showInputDialog("Ung�ltige PLZ! Bitte neu eingeben:", plz);
                } else {
                    plzOk = true;
                }
            }
            if (plz != null) {
                jTextFieldNeuerKundePLZ.setText(plz);
            } else {
                jTextFieldNeuerKundePLZ.setText("");
            }
        }
    }
    
    /** Validiert die Email Eingabe */
    public void validateEmail() {
        Validator validate = new Validator();
        String email = jTextFieldNeuerKundeEMail.getText();
        boolean emailOk = false;
        if (!email.equals("") && email != null) {
            while (!emailOk && email != null) {
                if (!validate.checkEMail(email)) {
                    jOptionPaneEMailInvalid.setWantsInput(true);
                    email = JOptionPane.showInputDialog("Ung�ltige EMail! Bitte neu eingeben:", email);
                } else {
                    emailOk = true;
                }
            }
            if (email != null) {
                jTextFieldNeuerKundeEMail.setText(email);
            } else {
                jTextFieldNeuerKundeEMail.setText("");
            }
        }
    }
    
    /** Setzt die Eingabefelder zur�ck */
    public void resetFields() {
        jTextFieldNeuerKundeNachname.setText("");
        jTextFieldNeuerKundeVorname.setText("");
        jTextFieldNeuerKundeFirma.setText("");
        jTextFieldNeuerKundeStrasse.setText("");
        jTextFieldNeuerKundeTelefon.setText("");
        jTextFieldNeuerKundeFax.setText("");
        jTextFieldNeuerKundeEMail.setText("");
        jTextFieldNeuerKundePLZ.setText("");
        jTextFieldNeuerKundeOrt.setText("");
        jComboBoxNeuerKundeKundentyp.setSelectedIndex(1);
        jTextAreaNeuerKundeNotiz.setText("");
    }
    
    /** Legt einen neuen Kunden an */
    public void neuerKunde() {
        Vector check = new Vector();
        if (jTextFieldNeuerKundeFirma.getText().equals(""))
            check.add("Firma");
        if (jTextFieldNeuerKundeNachname.getText().equals(""))
            check.add("Name");
        if (jTextFieldNeuerKundeVorname.getText().equals(""))
            check.add("Vorname");
        if (jTextFieldNeuerKundeTelefon.getText().equals(""))
            check.add("Telefon");
        if (jTextFieldNeuerKundeStrasse.getText().equals(""))
            check.add("Strasse");
        if (jTextFieldNeuerKundeOrt.getText().equals(""))
            check.add("Ort");
        if (jTextFieldNeuerKundePLZ.getText().equals(""))
            check.add("PLZ");
        
        if (!check.isEmpty()) {
            JOptionPane errorPane = new JOptionPane();
            if (check.size() > 1)
                JOptionPane.showMessageDialog(this, "Folgende Felder m�ssen ausgef�llt werden: "
                + check.toString().substring(1,check.toString().length()-1), "Fehler beim Anlegen des neuen Kunden", JOptionPane.ERROR_MESSAGE);
            else
                JOptionPane.showMessageDialog(this, "Das folgende Feld muss ausgef�llt werden: "
                + check.toString().substring(1,check.toString().length()-1), "Fehler beim Anlegen des neuen Kunden", JOptionPane.ERROR_MESSAGE);
        } else {
            Customer kunde = new Customer();
            kunde.setNachname(jTextFieldNeuerKundeNachname.getText());
            kunde.setVorname(jTextFieldNeuerKundeVorname.getText());
            kunde.setFirma(jTextFieldNeuerKundeFirma.getText());
            kunde.setStrasse(jTextFieldNeuerKundeStrasse.getText());
            kunde.setTelefon(jTextFieldNeuerKundeTelefon.getText());
            kunde.setFax(jTextFieldNeuerKundeFax.getText());
            kunde.setEmail(jTextFieldNeuerKundeEMail.getText());
            kunde.setPlz(jTextFieldNeuerKundePLZ.getText());
            kunde.setOrt(jTextFieldNeuerKundeOrt.getText());
            kunde.setKundentyp(jComboBoxNeuerKundeKundentyp.getSelectedItem().toString());
            
            City ort = new City();
            ort.setName(kunde.getOrt());
            ort.setPlz(kunde.getPlz());
            
            try {
                int plzId = dao.neuerOrt(ort);
                kunde.setPlzId(plzId);
                int id = dao.neuerKunde(kunde);
                kunde.setId(id);
                
                if (!jTextAreaNeuerKundeNotiz.getText().equals("")) {
                    // Notiz wird erzeugt mit der Id des Kunden als Fremdschl�ssel
                    Memo notiz = new Memo();
                    notiz.setText(jTextAreaNeuerKundeNotiz.getText());
                    notiz.setDate(new java.sql.Date(wiSimMainController.getActDate().getTime()));
                    notiz.setKundenNr(kunde.getId());
                    
                    dao.neueNotiz(notiz);
                }
                
                //Felder werden resettet
                resetFields();
                
            } catch (WiSimDAOException e) {
                wiSimLogger.log("neuerKunde()", e);
            } catch (WiSimDAOWriteException e) {
                wiSimLogger.log("neuerKunde()", e);
            }
        }
    }
}