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
 * Created on 13. März 2003, 21:06
 */

package net.sourceforge.wisim.controller;

import net.sourceforge.wisim.dao.*;
import net.sourceforge.wisim.model.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;

/**
 * JPanelLieferantenliste zeigt Liste aller Lieferanten.
 */

public class JPanelViewSuppliers extends javax.swing.JPanel {
  private WiSimDAO dao;
  private Vector alleArtikel;
  private Vector listeArtikel;
  private Vector einzelteile;
  private Hashtable lieferantenObjekte;  
  private Hashtable lieferantenAuswahl;
  private Hashtable zubehoerTabelle;
  private Hashtable einzelteileTabelle;
  private int position;
  private int positionen;
  //Logger
  private WiSimLogger wiSimLogger;
  
  /** Creates new form JPanelLieferantenliste
     * @param wiSimMainController Der Maincontroller
     */
   public JPanelViewSuppliers(WiSimMainController wiSimMainController) {
        wiSimLogger = wiSimMainController.getWiSimLogger();  
        initComponents();
        initDAO(wiSimMainController);
        zubehoerTabelle = new Hashtable();
        einzelteileTabelle = new Hashtable();
        lieferantenAuswahl = new Hashtable();
        lieferantenObjekte = new Hashtable();
        einzelteile = new Vector();
        alleArtikel = new Vector();
        listeArtikel = new Vector();
        listeArtikel.add("Bitte wählen");
  }
  
  private void initDAO(WiSimMainController wiSimMainController) {
        dao = wiSimMainController.getDAO();
  }  

  private void initComponents() {//GEN-BEGIN:initComponents
    jLabelLieferantBearbeitenUeberschrift = new javax.swing.JLabel();
    jTextFieldLieferantName = new javax.swing.JTextField();
    jTextFieldLieferantVorname = new javax.swing.JTextField();
    jTextFieldLieferantStrasse = new javax.swing.JTextField();
    jTextFieldLieferantFirma = new javax.swing.JTextField();
    jTextFieldLieferantOrt = new javax.swing.JTextField();
    jTextFieldLieferantPLZ = new javax.swing.JTextField();
    jTextFieldLieferantFax = new javax.swing.JTextField();
    jLabelLieferantFax = new javax.swing.JLabel();
    jTextFieldLieferantTelefon = new javax.swing.JTextField();
    jLabelLieferantTelefon = new javax.swing.JLabel();
    jTextFieldLieferantEMail = new javax.swing.JTextField();
    jLabelLieferantLieferqualitaet = new javax.swing.JLabel();
    jLabelLieferantZuverlaessigkeit = new javax.swing.JLabel();
    jScrollPanePositionen = new javax.swing.JScrollPane();
    jTable1 = new javax.swing.JTable();
    jTable1.setSelectionMode(0);
    jTable1.getTableHeader().setReorderingAllowed(false);

    jLabelLieferantLieferqualitaet1 = new javax.swing.JLabel();
    jLabelLieferantLieferqualitaet2 = new javax.swing.JLabel();
    jLabel1 = new javax.swing.JLabel();
    jScrollPaneListe = new javax.swing.JScrollPane();
    jTableLieferanten = new javax.swing.JTable();
    jTableLieferanten.setSelectionMode(0);
    jTableLieferanten.getTableHeader().setReorderingAllowed(false);

    jLabel2 = new javax.swing.JLabel();
    jTextLieferantLieferqualitaet = new javax.swing.JTextField();
    jTextLieferantZuverlaessigkeit = new javax.swing.JTextField();

    setLayout(null);

    jLabelLieferantBearbeitenUeberschrift.setFont(new java.awt.Font("Dialog", 1, 24));
    jLabelLieferantBearbeitenUeberschrift.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabelLieferantBearbeitenUeberschrift.setText("Lieferantenliste");
    add(jLabelLieferantBearbeitenUeberschrift);
    jLabelLieferantBearbeitenUeberschrift.setBounds(0, 0, 800, 40);

    jTextFieldLieferantName.setDocument(new JTextFieldValidation(50));
    jTextFieldLieferantName.setEditable(false);
    add(jTextFieldLieferantName);
    jTextFieldLieferantName.setBounds(610, 220, 160, 20);

    jTextFieldLieferantVorname.setDocument(new JTextFieldValidation(50));
    jTextFieldLieferantVorname.setEditable(false);
    add(jTextFieldLieferantVorname);
    jTextFieldLieferantVorname.setBounds(410, 220, 190, 20);

    jTextFieldLieferantStrasse.setDocument(new JTextFieldValidation(50));
    jTextFieldLieferantStrasse.setEditable(false);
    add(jTextFieldLieferantStrasse);
    jTextFieldLieferantStrasse.setBounds(410, 100, 250, 20);

    jTextFieldLieferantFirma.setDocument(new JTextFieldValidation(50));
    jTextFieldLieferantFirma.setEditable(false);
    add(jTextFieldLieferantFirma);
    jTextFieldLieferantFirma.setBounds(410, 70, 250, 20);

    jTextFieldLieferantOrt.setDocument(new JTextFieldValidation(50));
    jTextFieldLieferantOrt.setEditable(false);
    add(jTextFieldLieferantOrt);
    jTextFieldLieferantOrt.setBounds(410, 130, 190, 20);

    jTextFieldLieferantPLZ.setColumns(5);
    jTextFieldLieferantPLZ.setDocument(new JTextFieldValidation(5));
    jTextFieldLieferantPLZ.setEditable(false);
    add(jTextFieldLieferantPLZ);
    jTextFieldLieferantPLZ.setBounds(610, 130, 50, 20);

    jTextFieldLieferantFax.setDocument(new JTextFieldValidation(30));
    jTextFieldLieferantFax.setEditable(false);
    add(jTextFieldLieferantFax);
    jTextFieldLieferantFax.setBounds(410, 310, 190, 20);

    jLabelLieferantFax.setFont(new java.awt.Font("Dialog", 0, 10));
    jLabelLieferantFax.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    jLabelLieferantFax.setText("Fax");
    add(jLabelLieferantFax);
    jLabelLieferantFax.setBounds(610, 310, 90, 20);

    jTextFieldLieferantTelefon.setDocument(new JTextFieldValidation(30));
    jTextFieldLieferantTelefon.setEditable(false);
    add(jTextFieldLieferantTelefon);
    jTextFieldLieferantTelefon.setBounds(410, 280, 190, 20);

    jLabelLieferantTelefon.setFont(new java.awt.Font("Dialog", 0, 10));
    jLabelLieferantTelefon.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    jLabelLieferantTelefon.setText("Telefon");
    add(jLabelLieferantTelefon);
    jLabelLieferantTelefon.setBounds(610, 280, 90, 20);

    jTextFieldLieferantEMail.setDocument(new JTextFieldValidation(50));
    jTextFieldLieferantEMail.setEditable(false);
    add(jTextFieldLieferantEMail);
    jTextFieldLieferantEMail.setBounds(410, 250, 360, 20);

    jLabelLieferantLieferqualitaet.setFont(new java.awt.Font("Dialog", 0, 12));
    jLabelLieferantLieferqualitaet.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    jLabelLieferantLieferqualitaet.setText("Lieferqualit\u00e4t");
    add(jLabelLieferantLieferqualitaet);
    jLabelLieferantLieferqualitaet.setBounds(410, 370, 130, 20);

    jLabelLieferantZuverlaessigkeit.setFont(new java.awt.Font("Dialog", 0, 12));
    jLabelLieferantZuverlaessigkeit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    jLabelLieferantZuverlaessigkeit.setText("Zuverl\u00e4ssigkeit");
    add(jLabelLieferantZuverlaessigkeit);
    jLabelLieferantZuverlaessigkeit.setBounds(410, 410, 130, 20);

    jScrollPanePositionen.setMaximumSize(new java.awt.Dimension(300, 320));
    jScrollPanePositionen.setPreferredSize(new java.awt.Dimension(53, 3));
    jTable1.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {

      },
      new String [] {
        "Artikelname", "Mindestabnahme", "Artikelpreis"
      }
    ) {
      boolean[] canEdit = new boolean [] {
        false, false, false
      };

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
      }
    });
    jScrollPanePositionen.setViewportView(jTable1);

    add(jScrollPanePositionen);
    jScrollPanePositionen.setBounds(30, 370, 350, 100);

    jLabelLieferantLieferqualitaet1.setFont(new java.awt.Font("Dialog", 0, 10));
    jLabelLieferantLieferqualitaet1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    jLabelLieferantLieferqualitaet1.setText("Punkte");
    add(jLabelLieferantLieferqualitaet1);
    jLabelLieferantLieferqualitaet1.setBounds(550, 370, 130, 20);

    jLabelLieferantLieferqualitaet2.setFont(new java.awt.Font("Dialog", 0, 10));
    jLabelLieferantLieferqualitaet2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    jLabelLieferantLieferqualitaet2.setText("Punkte");
    add(jLabelLieferantLieferqualitaet2);
    jLabelLieferantLieferqualitaet2.setBounds(550, 410, 130, 20);

    jLabel1.setFont(new java.awt.Font("Dialog", 0, 10));
    jLabel1.setText("[Bewertung nach Schulnotensystem]");
    add(jLabel1);
    jLabel1.setBounds(410, 450, 210, 20);

    jTableLieferanten.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {

      },
      new String [] {
        "Lieferant"
      }
    ) {
      boolean[] canEdit = new boolean [] {
        false
      };

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
      }
    });
    jTableLieferanten.addAncestorListener(new javax.swing.event.AncestorListener() {
      public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
      }
      public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
        jTableLieferantenAncestorAdded(evt);
      }
      public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
      }
    });

    jTableLieferanten.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        jTableLieferantenMouseClicked(evt);
      }
    });

    jScrollPaneListe.setViewportView(jTableLieferanten);

    add(jScrollPaneListe);
    jScrollPaneListe.setBounds(30, 70, 350, 270);

    jLabel2.setFont(new java.awt.Font("Dialog", 0, 12));
    jLabel2.setText("Ansprechpartner");
    add(jLabel2);
    jLabel2.setBounds(410, 190, 210, 20);

    jTextLieferantLieferqualitaet.setEditable(false);
    add(jTextLieferantLieferqualitaet);
    jTextLieferantLieferqualitaet.setBounds(510, 370, 30, 20);

    jTextLieferantZuverlaessigkeit.setEditable(false);
    add(jTextLieferantZuverlaessigkeit);
    jTextLieferantZuverlaessigkeit.setBounds(510, 410, 30, 20);

  }//GEN-END:initComponents

    private void jTableLieferantenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableLieferantenMouseClicked
        ladeLieferant();
    }//GEN-LAST:event_jTableLieferantenMouseClicked

    private void jTableLieferantenAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTableLieferantenAncestorAdded
        setzeStandard();
        ladeLieferanten();
        ladeLieferant();
    }//GEN-LAST:event_jTableLieferantenAncestorAdded
   
//Füllt die Tabelle Lieferantenliste mit den in der DB vorhandenen Lieferanten
private void ladeLieferanten() {
     try {
        Collection lieferanten = null;
        lieferanten = dao.getLieferanten();
        Iterator it_lieferanten = lieferanten.iterator();
        positionen = 0;
        int row;
        
        while (it_lieferanten.hasNext()) {
        
        //gesamter Tabelleninhalt wird Zwischengespeichert
            Vector tableTempRow = new Vector();
            row = 0;
            
                while (row < positionen) {
                    tableTempRow.add(jTableLieferanten.getValueAt(row, 0));
                    row++;
                }
                    
          //DefaultTableModel mit Variablen Zeilen, 3 TableHeads und nicht editierbaren Zellen
            boolean Delete = false;
            updateLieferantenTable(Delete);
            
            if (tableTempRow.size() > 0) {
                Iterator it_tableTempRow = tableTempRow.iterator();
                row = 0;
                while (it_tableTempRow.hasNext()) {
                    String complete = (String) it_tableTempRow.next();
                    String[] chunks = complete.split(",");
                    jTableLieferanten.setValueAt(chunks[0], row, 0);
                    row++;
                }
            }
            
            Supplier liste = (Supplier) it_lieferanten.next();
            lieferantenObjekte.put((String.valueOf(positionen)),liste);
            lieferantenAuswahl.put((String.valueOf(positionen)),String.valueOf(liste.getId()));
            jTableLieferanten.setValueAt(liste.getFirma(), positionen, 0);
            positionen++;
        }
    } catch (WiSimDAOException e) {
            System.err.println(e.getMessage());
    }
}
       
//Lädt einen Kunden zum Bearbeiten aus der Datenbank
 private void ladeLieferant() {  
      
     //liefert listItem des selektierten Eintrags 
    String listItem = String.valueOf(jTableLieferanten.getSelectedRow());
    Supplier auswahlLieferant = (Supplier)lieferantenObjekte.get(listItem); 

    position = 0;
    boolean Deleted = true;
    updatePositionsTable(Deleted);
        
    if (auswahlLieferant != null){   
        jTextFieldLieferantName.setText(auswahlLieferant.getNachname());
        jTextFieldLieferantVorname.setText(auswahlLieferant.getVorname());
        jTextFieldLieferantFirma.setText(auswahlLieferant.getFirma());
        jTextFieldLieferantStrasse.setText(auswahlLieferant.getStrasse());
        jTextFieldLieferantTelefon.setText(auswahlLieferant.getTelefon());
        jTextFieldLieferantFax.setText(auswahlLieferant.getFax());
        jTextFieldLieferantEMail.setText(auswahlLieferant.getEmail());
        jTextFieldLieferantPLZ.setText(String.valueOf(auswahlLieferant.getPlz()));
        jTextFieldLieferantOrt.setText(auswahlLieferant.getOrt());
        jTextLieferantLieferqualitaet.setText(auswahlLieferant.getLieferqualitaet());
        jTextLieferantZuverlaessigkeit.setText(auswahlLieferant.getZuverlaessigkeit());
        zubehoerTabelle.clear();
        ladeZugehoerigeEinzelteile(auswahlLieferant.getId());                
    }
}
        
  
// Ladet die Einzelteile des Lieferanten 
private void ladeZugehoerigeEinzelteile(int id){      
  
    try {
        Collection lieferliste = null;
        WiSimComponent einzelteil = null;
        lieferliste = dao.getLieferliste(id);
        Iterator it_lieferlisten = lieferliste.iterator();
      
        while (it_lieferlisten.hasNext()) {
            SupplyList liste = (SupplyList) it_lieferlisten.next();
            einzelteil = dao.getEinzelteil(liste.getEinzelteilID());
                    
          //gesamter Tabelleninhalt wird Zwischengespeichert
            Vector tableTempRow = new Vector();
            int row = 0;
            
                while (row < position) {
                    tableTempRow.add(jTable1.getValueAt(row, 0) + "," + jTable1.getValueAt(row, 1) + "," + jTable1.getValueAt(row, 2));
                    row++;
                }
                    
          //DefaultTableModel mit Variablen Zeilen, 3 TableHeads und nicht editierbaren Zellen
            boolean Deleted = false;
            updatePositionsTable(Deleted);
            
            if (tableTempRow.size() > 0) {
                Iterator it_tableTempRow = tableTempRow.iterator();
                row = 0;
                while (it_tableTempRow.hasNext()) {
                    String complete = (String) it_tableTempRow.next();
                    String[] chunks = complete.split(",");
                    jTable1.setValueAt(chunks[0], row, 0);
                    jTable1.setValueAt(chunks[1], row, 1);
                    jTable1.setValueAt(chunks[2], row, 2);
                    row++;
                }
            }
           
            zubehoerTabelle.put(einzelteil.getName(),String.valueOf(einzelteil.getNr()));
            jTable1.setValueAt(einzelteil.getName(), position, 0);
            jTable1.setValueAt(String.valueOf(liste.getMindestBestellMenge()), position, 1);
            jTable1.setValueAt(String.valueOf(liste.getPreis()), position, 2);
            position++;
       }
    } catch (WiSimDAOException e) {
            System.err.println(e.getMessage());
    }
}

  
/** Schreibt die Positions-Tabelle neu
 * @param Deleted boolean
 */  
public void updatePositionsTable(boolean Deleted) {
    int rows;
    
    if (Deleted == true) {
        rows = position;
    } else {
        rows = position + 1;
    }
    //DefaultTableModel mit Variablen Zeilen, 3 TableHeads und nicht editierbaren Zellen
    Object[][] tableInit = new Object[rows][3];
    DefaultTableModel defTable = new DefaultTableModel(
        tableInit,
        new String [] {
            "Article", "MinAbnahme", "Preis/Stk"
        }
    ) 
    {
        boolean[] canEdit = new boolean [] {
            false, false, false
        };
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    };
    jTable1.setModel(defTable);
    jTable1.setFocusable(false);
    javax.swing.table.TableColumn column = null;
        
        //Spaltenbreiten der Tabelle werden gesetzt
        for (int i = 0; i < 3; i++) {
            column = jTable1.getColumnModel().getColumn(i);
            switch (i) {
                    //Article
                case 0:
                    column.setPreferredWidth(120);
                    break;
                    //Menge
                case 1:
                    column.setPreferredWidth(15);
                    break;
                    //Preis
                case 2:
                    column.setPreferredWidth(15);
                    break;                   
            }       
        }
}

/** Schreibt die Lieferanten-Tabelle neu
 * @param Delete boolean
 */  
public void updateLieferantenTable(boolean Delete) {
    int rows;
    
    if (Delete == true) {
        rows = positionen;
    } else {
        rows = positionen + 1;
    }
    //DefaultTableModel mit Variablen Zeilen, 1 TableHead und nicht editierbaren Zellen
    Object[][] tableInit = new Object[rows][1];
    DefaultTableModel defTable = new DefaultTableModel(
        tableInit,
        new String [] {
                "Lieferant"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
    };
    jTableLieferanten.setModel(defTable);
    jTableLieferanten.setFocusable(false);
}

// Setzt nach dem Speichern und Löschen eines Lieferanten die Werte auf Standard
private void setzeStandard() {
    jTextFieldLieferantVorname.setText("");
    jTextFieldLieferantName.setText("");
    jTextFieldLieferantFirma.setText("");
    jTextFieldLieferantStrasse.setText("");
    jTextFieldLieferantOrt.setText("");
    jTextFieldLieferantPLZ.setText("");
    jTextFieldLieferantTelefon.setText("");
    jTextFieldLieferantEMail.setText("");
    jTextFieldLieferantFax.setText("");
    jTextLieferantLieferqualitaet.setText("");
    jTextLieferantZuverlaessigkeit.setText("");
    position = 0;
    boolean Deleted = true;
    updatePositionsTable(Deleted);
}
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JTextField jTextFieldLieferantStrasse;
  private javax.swing.JLabel jLabelLieferantBearbeitenUeberschrift;
  private javax.swing.JLabel jLabelLieferantLieferqualitaet2;
  private javax.swing.JLabel jLabelLieferantLieferqualitaet1;
  private javax.swing.JTextField jTextFieldLieferantOrt;
  private javax.swing.JTextField jTextLieferantZuverlaessigkeit;
  private javax.swing.JTable jTable1;
  private javax.swing.JTextField jTextFieldLieferantVorname;
  private javax.swing.JTextField jTextFieldLieferantFirma;
  private javax.swing.JScrollPane jScrollPaneListe;
  private javax.swing.JTextField jTextLieferantLieferqualitaet;
  private javax.swing.JTextField jTextFieldLieferantFax;
  private javax.swing.JTextField jTextFieldLieferantName;
  private javax.swing.JTextField jTextFieldLieferantTelefon;
  private javax.swing.JLabel jLabelLieferantZuverlaessigkeit;
  private javax.swing.JLabel jLabelLieferantLieferqualitaet;
  private javax.swing.JScrollPane jScrollPanePositionen;
  private javax.swing.JLabel jLabelLieferantFax;
  private javax.swing.JLabel jLabelLieferantTelefon;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JTextField jTextFieldLieferantPLZ;
  private javax.swing.JTextField jTextFieldLieferantEMail;
  private javax.swing.JTable jTableLieferanten;
  // End of variables declaration//GEN-END:variables
  
}
