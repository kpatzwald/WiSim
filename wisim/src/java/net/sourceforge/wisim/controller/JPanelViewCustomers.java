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
 * JPanelLieferantenliste.java
 *
 * Created on 13. M�rz 2003, 21:06
 */

package net.sourceforge.wisim.controller;

import net.sourceforge.wisim.dao.*;
import net.sourceforge.wisim.model.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/** Auflistung der Kundendaten
 */
public class JPanelViewCustomers extends javax.swing.JPanel {
    private WiSimDAO dao;
    private Vector verlauf;
    private Hashtable kundenObjekte;
    private Hashtable kundenAuswahl;
    private int positionen;
    private WiSimLogger logger;
    
    /** Creates new form JPanelLieferantBearbeiten
     * @param wiSimMainController wiSimMainController
     */
    public JPanelViewCustomers(WiSimMainController wiSimMainController) {
        initComponents();
        initDAO(wiSimMainController);
        kundenAuswahl = new Hashtable();
        kundenObjekte = new Hashtable();
        verlauf = new Vector();
        logger = wiSimMainController.getWiSimLogger();
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
        jLabelKundenUebersichtUeberschrift = new javax.swing.JLabel();
        jLabelKundenUebersichtSchulnoten = new javax.swing.JLabel();
        jScrollPaneListe = new javax.swing.JScrollPane();
        jTableKunden = new javax.swing.JTable();
        jTableKunden.setSelectionMode(0);
        jTextFieldKundenUebersichtVorname = new javax.swing.JTextField();
        jTextFieldKundenUebersichtNachname = new javax.swing.JTextField();
        jTextFieldKundenUebersichtFirma = new javax.swing.JTextField();
        jTextFieldKundenUebersichtStrasse = new javax.swing.JTextField();
        jTextFieldKundenUebersichtPLZ = new javax.swing.JTextField();
        jTextFieldKundenUebersichtOrt = new javax.swing.JTextField();
        jTextFieldKundenUebersichtTelefon = new javax.swing.JTextField();
        jTextFieldKundenUebersichtFax = new javax.swing.JTextField();
        jTextFieldKundenUebersichtEMail = new javax.swing.JTextField();
        jLabelKundenUebersichtEMail = new javax.swing.JLabel();
        jLabelKundenUebersichtFax = new javax.swing.JLabel();
        jLabelKundenUebersichtTelefon = new javax.swing.JLabel();
        jLabelKundenUebersichtPLZ = new javax.swing.JLabel();
        jLabelKundenUebersichtStrasse = new javax.swing.JLabel();
        jLabelKundenUebersichtFirma = new javax.swing.JLabel();
        jLabelKundenUebersichtNachname = new javax.swing.JLabel();
        jLabelKundenUebersichtVorname = new javax.swing.JLabel();
        jLabelKundenUebersichtZahlungsmoral = new javax.swing.JLabel();
        jLabelKundenUebersichtAnsruch = new javax.swing.JLabel();
        jLabelKundenUebersichtKundenTyp = new javax.swing.JLabel();
        jTextFieldKundenUebersichtAnspruch = new javax.swing.JTextField();
        jTextFieldKundenUebersichtKundenTyp = new javax.swing.JTextField();
        jTextFieldKundenUebersichtZahlungsmoral = new javax.swing.JTextField();
        jLabelKundenUebersichtOrt = new javax.swing.JLabel();
        jLabelKundenUebersichtBemerkungen = new javax.swing.JLabel();
        jTabbedPaneKundenUebersichtNotizen = new javax.swing.JTabbedPane();
        jScrollPaneKundenUebersichtBemerkung = new javax.swing.JScrollPane();
        jTextAreaKundenUebersichtBemerkung = new javax.swing.JTextArea();
        jScrollPaneKundenUebersichtVerlauf = new javax.swing.JScrollPane();
        jListTextFieldKundenUebersichtVerlauf = new javax.swing.JList();
        jListTextFieldKundenUebersichtVerlauf.setSelectionMode(0);

        setLayout(null);

        setPreferredSize(new java.awt.Dimension(800, 600));
        jLabelKundenUebersichtUeberschrift.setFont(new java.awt.Font("Dialog", 1, 24));
        jLabelKundenUebersichtUeberschrift.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelKundenUebersichtUeberschrift.setText("Kunden\u00fcbersicht");
        add(jLabelKundenUebersichtUeberschrift);
        jLabelKundenUebersichtUeberschrift.setBounds(0, 0, 800, 40);

        jLabelKundenUebersichtSchulnoten.setFont(new java.awt.Font("Dialog", 0, 10));
        jLabelKundenUebersichtSchulnoten.setText("[Bewertung nach Schulnotensystem]");
        add(jLabelKundenUebersichtSchulnoten);
        jLabelKundenUebersichtSchulnoten.setBounds(515, 370, 210, 20);

        jTableKunden.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kunden"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableKunden.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jTableKundenAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jTableKunden.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableKundenMouseClicked(evt);
            }
        });

        jScrollPaneListe.setViewportView(jTableKunden);

        add(jScrollPaneListe);
        jScrollPaneListe.setBounds(30, 70, 300, 290);

        jTextFieldKundenUebersichtVorname.setDocument(new JTextFieldValidation(50));
        jTextFieldKundenUebersichtVorname.setEditable(false);
        add(jTextFieldKundenUebersichtVorname);
        jTextFieldKundenUebersichtVorname.setBounds(480, 70, 210, 20);

        jTextFieldKundenUebersichtNachname.setDocument(new JTextFieldValidation(50));
        jTextFieldKundenUebersichtNachname.setEditable(false);
        add(jTextFieldKundenUebersichtNachname);
        jTextFieldKundenUebersichtNachname.setBounds(480, 100, 210, 20);

        jTextFieldKundenUebersichtFirma.setDocument(new JTextFieldValidation(50));
        jTextFieldKundenUebersichtFirma.setEditable(false);
        add(jTextFieldKundenUebersichtFirma);
        jTextFieldKundenUebersichtFirma.setBounds(480, 130, 210, 20);

        jTextFieldKundenUebersichtStrasse.setDocument(new JTextFieldValidation(50));
        jTextFieldKundenUebersichtStrasse.setEditable(false);
        add(jTextFieldKundenUebersichtStrasse);
        jTextFieldKundenUebersichtStrasse.setBounds(480, 160, 210, 20);

        jTextFieldKundenUebersichtPLZ.setDocument(new JTextFieldValidation(5));
        jTextFieldKundenUebersichtPLZ.setEditable(false);
        add(jTextFieldKundenUebersichtPLZ);
        jTextFieldKundenUebersichtPLZ.setBounds(480, 190, 40, 20);

        jTextFieldKundenUebersichtOrt.setDocument(new JTextFieldValidation(50));
        jTextFieldKundenUebersichtOrt.setEditable(false);
        add(jTextFieldKundenUebersichtOrt);
        jTextFieldKundenUebersichtOrt.setBounds(560, 190, 130, 20);

        jTextFieldKundenUebersichtTelefon.setDocument(new JTextFieldValidation(30));
        jTextFieldKundenUebersichtTelefon.setEditable(false);
        add(jTextFieldKundenUebersichtTelefon);
        jTextFieldKundenUebersichtTelefon.setBounds(480, 220, 210, 20);

        jTextFieldKundenUebersichtFax.setDocument(new JTextFieldValidation(30));
        jTextFieldKundenUebersichtFax.setEditable(false);
        add(jTextFieldKundenUebersichtFax);
        jTextFieldKundenUebersichtFax.setBounds(480, 250, 210, 20);

        jTextFieldKundenUebersichtEMail.setDocument(new JTextFieldValidation(50));
        jTextFieldKundenUebersichtEMail.setEditable(false);
        add(jTextFieldKundenUebersichtEMail);
        jTextFieldKundenUebersichtEMail.setBounds(480, 280, 210, 20);

        jLabelKundenUebersichtEMail.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabelKundenUebersichtEMail.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelKundenUebersichtEMail.setText("E-Mail");
        add(jLabelKundenUebersichtEMail);
        jLabelKundenUebersichtEMail.setBounds(350, 280, 120, 20);

        jLabelKundenUebersichtFax.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabelKundenUebersichtFax.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelKundenUebersichtFax.setText("Fax");
        add(jLabelKundenUebersichtFax);
        jLabelKundenUebersichtFax.setBounds(350, 250, 120, 20);

        jLabelKundenUebersichtTelefon.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabelKundenUebersichtTelefon.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelKundenUebersichtTelefon.setText("Telefon");
        add(jLabelKundenUebersichtTelefon);
        jLabelKundenUebersichtTelefon.setBounds(350, 220, 120, 20);

        jLabelKundenUebersichtPLZ.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabelKundenUebersichtPLZ.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelKundenUebersichtPLZ.setText("PLZ");
        add(jLabelKundenUebersichtPLZ);
        jLabelKundenUebersichtPLZ.setBounds(430, 190, 40, 20);

        jLabelKundenUebersichtStrasse.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabelKundenUebersichtStrasse.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelKundenUebersichtStrasse.setText("Strasse");
        add(jLabelKundenUebersichtStrasse);
        jLabelKundenUebersichtStrasse.setBounds(350, 160, 120, 20);

        jLabelKundenUebersichtFirma.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabelKundenUebersichtFirma.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelKundenUebersichtFirma.setText("Firma");
        add(jLabelKundenUebersichtFirma);
        jLabelKundenUebersichtFirma.setBounds(350, 130, 120, 16);

        jLabelKundenUebersichtNachname.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabelKundenUebersichtNachname.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelKundenUebersichtNachname.setText("Nachname");
        add(jLabelKundenUebersichtNachname);
        jLabelKundenUebersichtNachname.setBounds(350, 100, 120, 16);

        jLabelKundenUebersichtVorname.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabelKundenUebersichtVorname.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelKundenUebersichtVorname.setText("Vorname");
        add(jLabelKundenUebersichtVorname);
        jLabelKundenUebersichtVorname.setBounds(350, 70, 120, 20);

        jLabelKundenUebersichtZahlungsmoral.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabelKundenUebersichtZahlungsmoral.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelKundenUebersichtZahlungsmoral.setText("Zahlungsmoral");
        add(jLabelKundenUebersichtZahlungsmoral);
        jLabelKundenUebersichtZahlungsmoral.setBounds(380, 370, 90, 20);

        jLabelKundenUebersichtAnsruch.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabelKundenUebersichtAnsruch.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelKundenUebersichtAnsruch.setText("Anspruch");
        add(jLabelKundenUebersichtAnsruch);
        jLabelKundenUebersichtAnsruch.setBounds(360, 310, 110, 20);

        jLabelKundenUebersichtKundenTyp.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabelKundenUebersichtKundenTyp.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelKundenUebersichtKundenTyp.setText("Kunden - Typ");
        add(jLabelKundenUebersichtKundenTyp);
        jLabelKundenUebersichtKundenTyp.setBounds(390, 343, 80, 16);

        jTextFieldKundenUebersichtAnspruch.setEditable(false);
        add(jTextFieldKundenUebersichtAnspruch);
        jTextFieldKundenUebersichtAnspruch.setBounds(480, 310, 210, 20);

        jTextFieldKundenUebersichtKundenTyp.setEditable(false);
        add(jTextFieldKundenUebersichtKundenTyp);
        jTextFieldKundenUebersichtKundenTyp.setBounds(480, 340, 30, 20);

        jTextFieldKundenUebersichtZahlungsmoral.setEditable(false);
        add(jTextFieldKundenUebersichtZahlungsmoral);
        jTextFieldKundenUebersichtZahlungsmoral.setBounds(480, 370, 30, 20);

        jLabelKundenUebersichtOrt.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabelKundenUebersichtOrt.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelKundenUebersichtOrt.setText("Ort");
        add(jLabelKundenUebersichtOrt);
        jLabelKundenUebersichtOrt.setBounds(520, 190, 30, 20);

        jLabelKundenUebersichtBemerkungen.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabelKundenUebersichtBemerkungen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelKundenUebersichtBemerkungen.setText("Bemerkungen");
        add(jLabelKundenUebersichtBemerkungen);
        jLabelKundenUebersichtBemerkungen.setBounds(30, 370, 110, 20);

        jTabbedPaneKundenUebersichtNotizen.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jTextAreaKundenUebersichtBemerkung.setEditable(false);
        jTextAreaKundenUebersichtBemerkung.setLineWrap(true);
        jScrollPaneKundenUebersichtBemerkung.setViewportView(jTextAreaKundenUebersichtBemerkung);

        jTabbedPaneKundenUebersichtNotizen.addTab("aktuell", jScrollPaneKundenUebersichtBemerkung);

        jListTextFieldKundenUebersichtVerlauf.setModel(new DefaultListModel());
        jListTextFieldKundenUebersichtVerlauf.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListTextFieldKundenUebersichtVerlaufValueChanged(evt);
            }
        });

        jScrollPaneKundenUebersichtVerlauf.setViewportView(jListTextFieldKundenUebersichtVerlauf);

        jTabbedPaneKundenUebersichtNotizen.addTab("Verlauf", jScrollPaneKundenUebersichtVerlauf);

        add(jTabbedPaneKundenUebersichtNotizen);
        jTabbedPaneKundenUebersichtNotizen.setBounds(30, 391, 660, 140);

    }//GEN-END:initComponents
    
    private void jListTextFieldKundenUebersichtVerlaufValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListTextFieldKundenUebersichtVerlaufValueChanged
        if (!jListTextFieldKundenUebersichtVerlauf.isSelectionEmpty()){
            showNotiz(jListTextFieldKundenUebersichtVerlauf.getMaxSelectionIndex());
        }
    }//GEN-LAST:event_jListTextFieldKundenUebersichtVerlaufValueChanged
    
    private void jTableKundenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableKundenMouseClicked
        ladeKunde();
    }//GEN-LAST:event_jTableKundenMouseClicked
    
    private void jTableKundenAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTableKundenAncestorAdded
        setzeStandard();
        ladeKunden();
    }//GEN-LAST:event_jTableKundenAncestorAdded
    
    /** F�llt die Tabelle Kundenliste mit den in der DB vorhandenen Kunden */
    private void ladeKunden() {
        try {
            Collection kunden = null;
            kunden = dao.getKunden();
            Iterator it_kunden = kunden.iterator();
            positionen = 0;
            int row;
            
            while (it_kunden.hasNext()) {
                
                //gesamter Tabelleninhalt wird Zwischengespeichert
                Vector tableTempRow = new Vector();
                row = 0;
                
                while (row < positionen) {
                    tableTempRow.add(jTableKunden.getValueAt(row, 0));
                    row++;
                }
                
                //DefaultTableModel mit Variablen Zeilen, 3 TableHeads und nicht editierbaren Zellen
                boolean Delete = false;
                updateKundenTable(Delete);
                
                if (tableTempRow.size() > 0) {
                    Iterator it_tableTempRow = tableTempRow.iterator();
                    row = 0;
                    while (it_tableTempRow.hasNext()) {
                        String complete = (String) it_tableTempRow.next();
                        String[] chunks = complete.split(",");
                        jTableKunden.setValueAt(chunks[0], row, 0);
                        row++;
                    }
                }
                
                Customer liste = (Customer) it_kunden.next();
                kundenObjekte.put((String.valueOf(positionen)),liste);
                kundenAuswahl.put((String.valueOf(positionen)),String.valueOf(liste.getId()));
                jTableKunden.setValueAt(liste.getNachname(), positionen, 0);
                positionen++;
            }
        } catch (WiSimDAOException e) {
            logger.log("ladeKunden()",e);
        }
    }
    
    /** L�dt einen Kunden zum Bearbeiten aus der Datenbank */
    private void ladeKunde() {
        
        //liefert listItem des selektierten Eintrags
        String listItem = String.valueOf(jTableKunden.getSelectedRow());
        Customer lkunde = (Customer)kundenObjekte.get(listItem);
        
        jTextFieldKundenUebersichtNachname.setText(lkunde.getNachname());
        jTextFieldKundenUebersichtVorname.setText(lkunde.getVorname());
        jTextFieldKundenUebersichtFirma.setText(lkunde.getFirma());
        jTextFieldKundenUebersichtStrasse.setText(lkunde.getStrasse());
        jTextFieldKundenUebersichtTelefon.setText(lkunde.getTelefon());
        jTextFieldKundenUebersichtFax.setText(lkunde.getFax());
        jTextFieldKundenUebersichtEMail.setText(lkunde.getEmail());
        jTextFieldKundenUebersichtPLZ.setText(lkunde.getPlz());
        jTextFieldKundenUebersichtOrt.setText(lkunde.getOrt());
        jTextFieldKundenUebersichtKundenTyp.setText(lkunde.getKundentyp());
        jTextFieldKundenUebersichtAnspruch.setText(lkunde.getAnspruch());
        jTextFieldKundenUebersichtZahlungsmoral.setText(lkunde.getZahlungsmoral());
        ladeVerlauf(lkunde.getId());
			jTabbedPaneKundenUebersichtNotizen.setSelectedComponent(jScrollPaneKundenUebersichtBemerkung);
    }
    
    //Schreibt die Positions-Tabelle neu
    public void updateKundenTable(boolean Delete) {
        int rows;
        
        if (Delete) {
            rows = positionen;
        } else {
            rows = positionen + 1;
        }
        //DefaultTableModel mit Variablen Zeilen, 1 TableHead und nicht editierbaren Zellen
        Object[][] tableInit = new Object[rows][1];
        DefaultTableModel defTable = new DefaultTableModel(
        tableInit,
        new String [] {
            "Kunden"
        }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };
            
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        jTableKunden.setModel(defTable);
        jTableKunden.setFocusable(false);
    }
    
    // Setzt nach dem Speichern und L�schen eines Lieferanten die Werte auf Standard
    private void setzeStandard() {
        jTextFieldKundenUebersichtVorname.setText("");
        jTextFieldKundenUebersichtNachname.setText("");
        jTextFieldKundenUebersichtFirma.setText("");
        jTextFieldKundenUebersichtOrt.setText("");
        jTextFieldKundenUebersichtStrasse.setText("");
        jTextFieldKundenUebersichtPLZ.setText("");
        jTextFieldKundenUebersichtTelefon.setText("");
        jTextFieldKundenUebersichtEMail.setText("");
        jTextFieldKundenUebersichtFax.setText("");
        jTextFieldKundenUebersichtZahlungsmoral.setText("");
        jTextFieldKundenUebersichtAnspruch.setText("");
        jTextFieldKundenUebersichtKundenTyp.setText("");
        jTextFieldKundenUebersichtAnspruch.setText("");
        jTextFieldKundenUebersichtZahlungsmoral.setText("");
        jTextFieldKundenUebersichtKundenTyp.setText("");
        jTextAreaKundenUebersichtBemerkung.setText("");
        DefaultListModel clearmodel1 = (DefaultListModel) jListTextFieldKundenUebersichtVerlauf.getModel();
        clearmodel1.removeAllElements();
    }
    
    /** L�dt Kundenverlauf zum Bearbeiten aus der Datenbank
     * @param KdNr Kunden Nummer
     */
    private void ladeVerlauf(int KdNr){
        DefaultListModel mymodel = (DefaultListModel) jListTextFieldKundenUebersichtVerlauf.getModel();
        mymodel.removeAllElements();
        try {
            verlauf.clear();
            verlauf = (Vector)dao.getNotizen(KdNr);
            Iterator it = verlauf.iterator();
            Memo einzelnotiz = new Memo();
            while (it.hasNext()) {
                einzelnotiz = (Memo)it.next();
                mymodel.addElement(einzelnotiz.getDate()+": "+einzelnotiz.getText());
            }
            //Eintragen der Bemerkungen in Verlauf Tab
            jListTextFieldKundenUebersichtVerlauf.setModel(mymodel);
            showNotiz(verlauf.lastIndexOf(einzelnotiz));
            
        }catch (WiSimDAOException wde) {
            logger.log("ladeVerlauf()",wde);
        }
    }
    
    /** Gibt Notizobjekt in aktuell TAB aus
     * @param noteNr Nummer der Bemerkung
     */
    private void showNotiz(int noteNr){
        if (verlauf.size() > 0) {
            Memo aktuell = new Memo();
            aktuell = (Memo)verlauf.elementAt(noteNr);
            jTextAreaKundenUebersichtBemerkung.setText(aktuell.getDate() + ": " + aktuell.getText());
        } else {
            jTextAreaKundenUebersichtBemerkung.setText("");
        }
    } 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelKundenUebersichtAnsruch;
    private javax.swing.JLabel jLabelKundenUebersichtBemerkungen;
    private javax.swing.JLabel jLabelKundenUebersichtEMail;
    private javax.swing.JLabel jLabelKundenUebersichtFax;
    private javax.swing.JLabel jLabelKundenUebersichtFirma;
    private javax.swing.JLabel jLabelKundenUebersichtKundenTyp;
    private javax.swing.JLabel jLabelKundenUebersichtNachname;
    private javax.swing.JLabel jLabelKundenUebersichtOrt;
    private javax.swing.JLabel jLabelKundenUebersichtPLZ;
    private javax.swing.JLabel jLabelKundenUebersichtSchulnoten;
    private javax.swing.JLabel jLabelKundenUebersichtStrasse;
    private javax.swing.JLabel jLabelKundenUebersichtTelefon;
    private javax.swing.JLabel jLabelKundenUebersichtUeberschrift;
    private javax.swing.JLabel jLabelKundenUebersichtVorname;
    private javax.swing.JLabel jLabelKundenUebersichtZahlungsmoral;
    private javax.swing.JList jListTextFieldKundenUebersichtVerlauf;
    private javax.swing.JScrollPane jScrollPaneKundenUebersichtBemerkung;
    private javax.swing.JScrollPane jScrollPaneKundenUebersichtVerlauf;
    private javax.swing.JScrollPane jScrollPaneListe;
    private javax.swing.JTabbedPane jTabbedPaneKundenUebersichtNotizen;
    private javax.swing.JTable jTableKunden;
    private javax.swing.JTextArea jTextAreaKundenUebersichtBemerkung;
    private javax.swing.JTextField jTextFieldKundenUebersichtAnspruch;
    protected javax.swing.JTextField jTextFieldKundenUebersichtEMail;
    protected javax.swing.JTextField jTextFieldKundenUebersichtFax;
    protected javax.swing.JTextField jTextFieldKundenUebersichtFirma;
    private javax.swing.JTextField jTextFieldKundenUebersichtKundenTyp;
    protected javax.swing.JTextField jTextFieldKundenUebersichtNachname;
    protected javax.swing.JTextField jTextFieldKundenUebersichtOrt;
    protected javax.swing.JTextField jTextFieldKundenUebersichtPLZ;
    protected javax.swing.JTextField jTextFieldKundenUebersichtStrasse;
    protected javax.swing.JTextField jTextFieldKundenUebersichtTelefon;
    protected javax.swing.JTextField jTextFieldKundenUebersichtVorname;
    private javax.swing.JTextField jTextFieldKundenUebersichtZahlungsmoral;
    // End of variables declaration//GEN-END:variables
}