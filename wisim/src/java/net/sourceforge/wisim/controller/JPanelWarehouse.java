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
 * JPanelLager.java
 *
 * Created on 10. Februar 2003, 20:58
 */

package net.sourceforge.wisim.controller;

import java.util.*;
import net.sourceforge.wisim.model.*;
import net.sourceforge.wisim.dao.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

/** Gibt eine �bersicht des Lager aus. Best�nde und Kapazit�ten werden ebenfalls
 * ausgegeben. Jeder WarehouseLocation kann einzelnd eingesehen werden.
 * @author Benjamin Pasero
 */
public class JPanelWarehouse extends javax.swing.JPanel {
    private WiSimDAO dao;
    private Collection lagerplaetze;
    private Vector einzelteileListe;
    private Collection artikelListe;
    private int etAnzahl;
    private Vector etElems;
    private String actLgPlatz;
    private boolean isBuilt;
    private boolean isActive;
    private static Color darkgreen = new Color(51, 153, 51);
    private static Color red = new Color(255, 0, 0);
    private static Color orange = new Color(255, 153, 0);
    private static Color darkgreenDither = new Color(51, 153, 51, 50);
    private static Color redDither = new Color(255, 0, 0, 50);
    private static Color orangeDither = new Color(255, 153, 0, 50);
    private static Color blackDither = new Color(255, 255, 255, 50);
    
    //Logger
    private WiSimLogger wiSimLogger;
    
    /** Creates new form JPanelLager
     * @param wiSimMainController
     */
    public JPanelWarehouse(WiSimMainController wiSimMainController) {
        wiSimLogger = wiSimMainController.getWiSimLogger();
        initDAO(wiSimMainController);
        lagerplaetze = new Vector();
        einzelteileListe = new Vector();
        artikelListe = new Vector();
        etElems = new Vector();
        etAnzahl = 0;
        actLgPlatz = "";
        isBuilt = false;
        isActive = false;
        initComponents();
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
    jLabelUeberschriftLager = new javax.swing.JLabel();
    jPanelGesamtliste = new javax.swing.JPanel();
    jScrollPaneGesamtliste = new javax.swing.JScrollPane();
    jTableGesamtliste = new javax.swing.JTable();
    jPanelLagerplatzAnsicht = new javax.swing.JPanel();
    jLabelLagerplatz = new javax.swing.JLabel();
    jComboBoxLagerplatz = new javax.swing.JComboBox();
    jLabelArtikel = new javax.swing.JLabel();
    jComboBoxArtikelImLager = new javax.swing.JComboBox();
    jLabelBestand = new javax.swing.JLabel();
    jTextFieldBestand = new javax.swing.JTextField();
    jLabelFrei = new javax.swing.JLabel();
    jTextFieldFrei = new javax.swing.JTextField();
    jLabelBelegung = new javax.swing.JLabel();
    jTextFieldBelegung = new javax.swing.JTextField();
    jTextFieldMindestbestand = new javax.swing.JTextField();
    jLabelMindestbestand = new javax.swing.JLabel();
    jLabelLagerProzent = new javax.swing.JLabel();
    jLabelStk = new javax.swing.JLabel();
    jPanelLegende = new javax.swing.JPanel();
    jLabelGruen = new javax.swing.JLabel();
    jLabelOrange = new javax.swing.JLabel();
    jLabelRot = new javax.swing.JLabel();
    jLabelKapazBaldErsch�pft = new javax.swing.JLabel();
    jLabelKapazAusreichend = new javax.swing.JLabel();
    jLabelKapazErsch�pft = new javax.swing.JLabel();

    setLayout(null);

    setPreferredSize(new java.awt.Dimension(800, 600));
    addAncestorListener(new javax.swing.event.AncestorListener() {
      public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
      }
      public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
        formAncestorAdded(evt);
      }
      public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
        formAncestorRemoved(evt);
      }
    });

    jLabelUeberschriftLager.setFont(new java.awt.Font("Dialog", 1, 24));
    jLabelUeberschriftLager.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabelUeberschriftLager.setText("Lager");
    add(jLabelUeberschriftLager);
    jLabelUeberschriftLager.setBounds(0, 0, 800, 40);

    jPanelGesamtliste.setLayout(null);

    jPanelGesamtliste.setBorder(new javax.swing.border.TitledBorder("Gesamtliste"));
    jTableGesamtliste.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {

      },
      new String [] {
        "Artikelname", "aktueller Bestand", "Min. Bestand", "Max. Bestand", "Lagerpl�tze", "Belegung"
      }
    ) {
      boolean[] canEdit = new boolean [] {
        false, false, false, false, false, false
      };

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
      }
    });
    jTableGesamtliste.addAncestorListener(new javax.swing.event.AncestorListener() {
      public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
      }
      public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
        jTableGesamtlisteAncestorAdded(evt);
      }
      public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
      }
    });

    jScrollPaneGesamtliste.setViewportView(jTableGesamtliste);

    jPanelGesamtliste.add(jScrollPaneGesamtliste);
    jScrollPaneGesamtliste.setBounds(10, 20, 750, 290);

    add(jPanelGesamtliste);
    jPanelGesamtliste.setBounds(10, 30, 770, 320);

    jPanelLagerplatzAnsicht.setLayout(null);

    jPanelLagerplatzAnsicht.setBorder(new javax.swing.border.TitledBorder("Lagerplatzansicht"));
    jLabelLagerplatz.setFont(new java.awt.Font("Dialog", 1, 14));
    jLabelLagerplatz.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelLagerplatz.setText("Lagerplatz");
    jPanelLagerplatzAnsicht.add(jLabelLagerplatz);
    jLabelLagerplatz.setBounds(20, 30, 73, 20);

    jComboBoxLagerplatz.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select:" }));
    jComboBoxLagerplatz.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jComboBoxLagerplatzActionPerformed(evt);
      }
    });
    jComboBoxLagerplatz.addAncestorListener(new javax.swing.event.AncestorListener() {
      public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
      }
      public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
        jComboBoxLagerplatzAncestorAdded(evt);
      }
      public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
      }
    });

    jPanelLagerplatzAnsicht.add(jComboBoxLagerplatz);
    jComboBoxLagerplatz.setBounds(110, 30, 70, 25);

    jLabelArtikel.setFont(new java.awt.Font("Dialog", 1, 14));
    jLabelArtikel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelArtikel.setText("Artikel");
    jPanelLagerplatzAnsicht.add(jLabelArtikel);
    jLabelArtikel.setBounds(30, 70, 60, 20);

    jComboBoxArtikelImLager.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jComboBoxArtikelImLagerActionPerformed(evt);
      }
    });

    jPanelLagerplatzAnsicht.add(jComboBoxArtikelImLager);
    jComboBoxArtikelImLager.setBounds(110, 70, 170, 25);

    jLabelBestand.setFont(new java.awt.Font("Dialog", 1, 14));
    jLabelBestand.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelBestand.setText("Bestand");
    jPanelLagerplatzAnsicht.add(jLabelBestand);
    jLabelBestand.setBounds(310, 70, 120, 20);

    jTextFieldBestand.setEditable(false);
    jTextFieldBestand.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
    jPanelLagerplatzAnsicht.add(jTextFieldBestand);
    jTextFieldBestand.setBounds(440, 70, 80, 20);

    jLabelFrei.setFont(new java.awt.Font("Dialog", 1, 14));
    jLabelFrei.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelFrei.setText("Frei");
    jPanelLagerplatzAnsicht.add(jLabelFrei);
    jLabelFrei.setBounds(590, 70, 50, 20);

    jTextFieldFrei.setEditable(false);
    jTextFieldFrei.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
    jPanelLagerplatzAnsicht.add(jTextFieldFrei);
    jTextFieldFrei.setBounds(650, 70, 70, 20);

    jLabelBelegung.setFont(new java.awt.Font("Dialog", 1, 14));
    jLabelBelegung.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelBelegung.setText("Belegung");
    jPanelLagerplatzAnsicht.add(jLabelBelegung);
    jLabelBelegung.setBounds(560, 30, 80, 20);

    jTextFieldBelegung.setEditable(false);
    jTextFieldBelegung.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
    jPanelLagerplatzAnsicht.add(jTextFieldBelegung);
    jTextFieldBelegung.setBounds(650, 30, 40, 20);

    jTextFieldMindestbestand.setEditable(false);
    jTextFieldMindestbestand.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
    jPanelLagerplatzAnsicht.add(jTextFieldMindestbestand);
    jTextFieldMindestbestand.setBounds(440, 30, 80, 20);

    jLabelMindestbestand.setFont(new java.awt.Font("Dialog", 1, 14));
    jLabelMindestbestand.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelMindestbestand.setText("Mindestbestand");
    jPanelLagerplatzAnsicht.add(jLabelMindestbestand);
    jLabelMindestbestand.setBounds(310, 30, 120, 20);

    jLabelLagerProzent.setFont(new java.awt.Font("Dialog", 1, 16));
    jLabelLagerProzent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabelLagerProzent.setText("%");
    jPanelLagerplatzAnsicht.add(jLabelLagerProzent);
    jLabelLagerProzent.setBounds(690, 30, 30, 20);

    jLabelStk.setFont(new java.awt.Font("Dialog", 1, 14));
    jLabelStk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabelStk.setText("Stk.");
    jPanelLagerplatzAnsicht.add(jLabelStk);
    jLabelStk.setBounds(720, 70, 40, 20);

    add(jPanelLagerplatzAnsicht);
    jPanelLagerplatzAnsicht.setBounds(10, 420, 770, 120);

    jPanelLegende.setLayout(null);

    jPanelLegende.setBorder(new javax.swing.border.TitledBorder("Legende"));
    jLabelGruen.setText("Bestand ausreichend");
    jPanelLegende.add(jLabelGruen);
    jLabelGruen.setBounds(10, 20, 260, 16);

    jLabelOrange.setText("Bestand bald zu gering");
    jPanelLegende.add(jLabelOrange);
    jLabelOrange.setBounds(270, 20, 270, 16);

    jLabelRot.setText("Bestand zu gering");
    jPanelLegende.add(jLabelRot);
    jLabelRot.setBounds(540, 20, 270, 16);

    jLabelKapazBaldErsch�pft.setText("Kapazit\u00e4ten bald ersch\u00f6pft");
    jPanelLegende.add(jLabelKapazBaldErsch�pft);
    jLabelKapazBaldErsch�pft.setBounds(270, 40, 240, 16);

    jLabelKapazAusreichend.setText("Kapazit\u00e4ten ausreichend");
    jPanelLegende.add(jLabelKapazAusreichend);
    jLabelKapazAusreichend.setBounds(10, 40, 240, 16);

    jLabelKapazErsch�pft.setText("Kapazit\u00e4ten ersch\u00f6pft");
    jPanelLegende.add(jLabelKapazErsch�pft);
    jLabelKapazErsch�pft.setBounds(540, 40, 230, 16);

    add(jPanelLegende);
    jPanelLegende.setBounds(10, 350, 770, 70);

  }//GEN-END:initComponents
    
    private void formAncestorRemoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorRemoved
        setIsActive(false);
    }//GEN-LAST:event_formAncestorRemoved
    
    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded
        buildLegende();
        setIsBuilt(true);
        setIsActive(true);
    }//GEN-LAST:event_formAncestorAdded
    
    private void jComboBoxArtikelImLagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxArtikelImLagerActionPerformed
        getArtikelImLagerplatz();
    }//GEN-LAST:event_jComboBoxArtikelImLagerActionPerformed
    
    private void jComboBoxLagerplatzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxLagerplatzActionPerformed
        getArtikelImLagerplatzInfo();
    }//GEN-LAST:event_jComboBoxLagerplatzActionPerformed
    
    private void jTableGesamtlisteAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTableGesamtlisteAncestorAdded
        buildGesamtlisteTabelle();
    }//GEN-LAST:event_jTableGesamtlisteAncestorAdded
    
    private void jComboBoxLagerplatzAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jComboBoxLagerplatzAncestorAdded
        getLagerplaetze();
    }//GEN-LAST:event_jComboBoxLagerplatzAncestorAdded
    
    
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JComboBox jComboBoxArtikelImLager;
  private javax.swing.JComboBox jComboBoxLagerplatz;
  private javax.swing.JLabel jLabelArtikel;
  private javax.swing.JLabel jLabelBelegung;
  private javax.swing.JLabel jLabelBestand;
  private javax.swing.JLabel jLabelFrei;
  private javax.swing.JLabel jLabelGruen;
  private javax.swing.JLabel jLabelKapazAusreichend;
  private javax.swing.JLabel jLabelKapazBaldErsch�pft;
  private javax.swing.JLabel jLabelKapazErsch�pft;
  private javax.swing.JLabel jLabelLagerProzent;
  private javax.swing.JLabel jLabelLagerplatz;
  private javax.swing.JLabel jLabelMindestbestand;
  private javax.swing.JLabel jLabelOrange;
  private javax.swing.JLabel jLabelRot;
  private javax.swing.JLabel jLabelStk;
  private javax.swing.JLabel jLabelUeberschriftLager;
  private javax.swing.JPanel jPanelGesamtliste;
  private javax.swing.JPanel jPanelLagerplatzAnsicht;
  private javax.swing.JPanel jPanelLegende;
  private javax.swing.JScrollPane jScrollPaneGesamtliste;
  private javax.swing.JTable jTableGesamtliste;
  private javax.swing.JTextField jTextFieldBelegung;
  private javax.swing.JTextField jTextFieldBestand;
  private javax.swing.JTextField jTextFieldFrei;
  private javax.swing.JTextField jTextFieldMindestbestand;
  // End of variables declaration//GEN-END:variables
    
    /** Erstellt die Einzelteilauftrags-Positionen Tabelle. */
    public void setGesamtlisteTable() {
        //DefaultTableModel mit Variablen Zeilen, 3 TableHeads und nicht editierbaren Zellen
        Object[][] tableInit = new Object[etAnzahl][6];
        DefaultTableModel defTable = new DefaultTableModel(
        tableInit,
        new String [] {
            "Artikelname", "aktueller Bestand", "Min. Bestand", "Max. Bestand", "Lagerpl�tze", "Belegung"
        }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
            public Class getColumnClass(int c) {
                return getValueAt(0, c).getClass();
            }
        };
        jTableGesamtliste.setModel(defTable);
        jTableGesamtliste.setFocusable(false);
        jTableGesamtliste.setSelectionMode(0);
        jTableGesamtliste.getTableHeader().setReorderingAllowed(false);
        
        javax.swing.table.TableColumn column = null;
        
        //Spaltenbreiten der Tabelle werden gesetzt
        for (int i = 0; i < 6; i++) {
            column = jTableGesamtliste.getColumnModel().getColumn(i);
            switch (i) {
                //Artikelname
                case 0:
                    column.setPreferredWidth(150);
                    break;
                    //aktueller Bestand
                case 1:
                    column.setPreferredWidth(60);
                    break;
                    //Min. Bestand
                case 2:
                    column.setPreferredWidth(50);
                    break;
                    //Max. Bestand
                case 3:
                    column.setPreferredWidth(50);
                    break;
                    //Lagerpl�tze
                case 4:
                    column.setPreferredWidth(100);
                    break;
                    //Belegung
                case 5:
                    column.setPreferredWidth(40);
                    break;
            }
        }
    }
    
    /** Baut die Gesamtliste der Lager�bersicht auf */
    public void buildGesamtlisteTabelle() {
        try {
            einzelteileListe = dao.getEinzelteilLagerElement();
            artikelListe = dao.getArtikelLagerElement();
        } catch (WiSimDAOException e) {
            wiSimLogger.log("buildGesamtListeTabelle()", e);
        }
        etAnzahl = einzelteileListe.size() + artikelListe.size();
        setGesamtlisteTable();
        int i=0;
        
        Iterator art_it = artikelListe.iterator();
        while (art_it.hasNext()) {
            ComponentWarehouseItem artikel = (ComponentWarehouseItem) art_it.next();
            jTableGesamtliste.setValueAt(artikel.getEinzelteilName(), i, 0);
            jTableGesamtliste.setValueAt(String.valueOf(artikel.getBestand()), i, 1);
            jTableGesamtliste.setValueAt(String.valueOf(artikel.getMinBestand()), i, 2);
            jTableGesamtliste.setValueAt(String.valueOf(artikel.getMaxBestand()), i, 3);
            
            Iterator lgPlaetze_it = artikel.getLagerplaetze().iterator();
            String lagerplaetze = "";
            while (lgPlaetze_it.hasNext()) {
                String nextLagerplatz = (String) lgPlaetze_it.next();
                if (!lgPlaetze_it.hasNext()) {
                    lagerplaetze = lagerplaetze + nextLagerplatz;
                } else {
                    lagerplaetze = lagerplaetze + nextLagerplatz + ", ";
                }
            }
            jTableGesamtliste.setValueAt(lagerplaetze, i, 4);
            
            IconGenerater iconGen = new IconGenerater();
            
            double actBestand = artikel.getBestand();
            double actMaxBestand = artikel.getMaxBestand();
            
            double rects = (double)(actBestand/actMaxBestand)*5;
            int filledrectsCount = (int) Math.round(rects);
            
            if (artikel.getBestand() < artikel.getMinBestand()) {
                jTableGesamtliste.setValueAt(iconGen.generateIcon(2, filledrectsCount), i, 5);
            } else if ((artikel.getBestand() - artikel.getMinBestand()/2) < artikel.getMinBestand()) {
                jTableGesamtliste.setValueAt(iconGen.generateIcon(1, filledrectsCount), i, 5);
            } else {
                jTableGesamtliste.setValueAt(iconGen.generateIcon(0, filledrectsCount), i, 5);
            }
            i++;
        }
        
        Iterator et_it = einzelteileListe.iterator();
        while (et_it.hasNext()) {
            ComponentWarehouseItem einzelteil = (ComponentWarehouseItem) et_it.next();
            jTableGesamtliste.setValueAt(einzelteil.getEinzelteilName(), i, 0);
            jTableGesamtliste.setValueAt(String.valueOf(einzelteil.getBestand()), i, 1);
            jTableGesamtliste.setValueAt(String.valueOf(einzelteil.getMinBestand()), i, 2);
            jTableGesamtliste.setValueAt(String.valueOf(einzelteil.getMaxBestand()), i, 3);
            
            Iterator lgPlaetze_it = einzelteil.getLagerplaetze().iterator();
            String lagerplaetze = "";
            while (lgPlaetze_it.hasNext()) {
                String nextLagerplatz = (String) lgPlaetze_it.next();
                if (!lgPlaetze_it.hasNext()) {
                    lagerplaetze = lagerplaetze + nextLagerplatz;
                } else {
                    lagerplaetze = lagerplaetze + nextLagerplatz + ", ";
                }
            }
            jTableGesamtliste.setValueAt(lagerplaetze, i, 4);
            
            IconGenerater iconGen = new IconGenerater();
            
            double actBestand = einzelteil.getBestand();
            double actMaxBestand = einzelteil.getMaxBestand();
            
            double rects = (double)(actBestand/actMaxBestand)*5;
            int filledrectsCount = (int) Math.round(rects);
            
            if (einzelteil.getBestand() < einzelteil.getMinBestand()) {
                jTableGesamtliste.setValueAt(iconGen.generateIcon(2, filledrectsCount), i, 5);
            } else if ((einzelteil.getBestand() - einzelteil.getMinBestand()/2) < einzelteil.getMinBestand()) {
                jTableGesamtliste.setValueAt(iconGen.generateIcon(1, filledrectsCount), i, 5);
            } else {
                jTableGesamtliste.setValueAt(iconGen.generateIcon(0, filledrectsCount), i, 5);
            }
            i++;
        }
    }
    
    /** Zeigt die Legende an */
    public void buildLegende() {
        //Legende
        //Gr�n
        Image imageIconGreen = new BufferedImage(70, 30, 2);
        Graphics g = imageIconGreen.getGraphics();
        g.setColor(darkgreen);
        g.fillRect(10, 11, 10, 10);
        g.fillRect(22, 11, 10, 10);
        g.fillRect(34, 11, 10, 10);
        
        g.drawRect(46, 11, 9, 9);
        g.drawRect(58, 11, 9, 9);
        g.setColor(darkgreenDither);
        g.fillRect(46, 11, 9, 9);
        g.fillRect(58, 11, 9, 9);
        
        ImageIcon ic = new ImageIcon(imageIconGreen);
        jLabelGruen.setIcon(ic);
        jLabelGruen.setToolTipText("Der Bestand dieses Einzelteils ist ausreichend!");
        
        //Orange
        Image imageIconOrange = new BufferedImage(70, 30, 2);
        g = imageIconOrange.getGraphics();
        g.setColor(orange);
        g.fillRect(10, 11, 10, 10);
        g.fillRect(22, 11, 10, 10);
        g.fillRect(34, 11, 10, 10);
        
        g.drawRect(46, 11, 9, 9);
        g.drawRect(58, 11, 9, 9);
        g.setColor(orangeDither);
        g.fillRect(46, 11, 9, 9);
        g.fillRect(58, 11, 9, 9);
        ic = new ImageIcon(imageIconOrange);
        jLabelOrange.setIcon(ic);
        jLabelOrange.setToolTipText("Der Bestand dieses Einzelteils hat den Mindestbestand bald unterschritten!");
        
        //Rot
        Image imageIconRed = new BufferedImage(70, 30, 2);
        g = imageIconRed.getGraphics();
        g.setColor(red);
        g.fillRect(10, 11, 10, 10);
        g.fillRect(22, 11, 10, 10);
        g.fillRect(34, 11, 10, 10);
        
        g.drawRect(46, 11, 9, 9);
        g.drawRect(58, 11, 9, 9);
        g.setColor(redDither);
        g.fillRect(46, 11, 9, 9);
        g.fillRect(58, 11, 9, 9);
        ic = new ImageIcon(imageIconRed);
        jLabelRot.setIcon(ic);
        jLabelRot.setToolTipText("Der Bestand dieses Einzelteils hat den Mindestbestand unterschritten!");
        
        //Kapazit�ten ausreichend
        Image imageIconKapazAusreichend = new BufferedImage(70, 30, 2);
        g = imageIconKapazAusreichend.getGraphics();
        g.setColor(Color.black);
        g.fillRect(10,11, 10, 10);
        
        g.drawRect(22,11,9, 9);
        g.drawRect(34,11, 9, 9);
        g.drawRect(46,11, 9, 9);
        g.drawRect(58,11, 9, 9);
        g.setColor(blackDither);
        g.fillRect(22,11, 9, 9);
        g.fillRect(34,11, 9, 9);
        g.fillRect(46,11, 9, 9);
        g.fillRect(58,11, 9, 9);
        ic = new ImageIcon(imageIconKapazAusreichend);
        jLabelKapazAusreichend.setIcon(ic);
        jLabelKapazAusreichend.setToolTipText("Es gibt ausreichend Lagerplatz f�r dieses Einzelteil!");
        
        //Kapazit�ten bald ersch�pft
        Image imageIconKapazBaldErsch�pft = new BufferedImage(70, 30, 2);
        g = imageIconKapazBaldErsch�pft.getGraphics();
        g.setColor(Color.black);
        g.fillRect(10,11, 10, 10);
        g.fillRect(22,11, 10, 10);
        g.fillRect(34,11, 10, 10);
        g.fillRect(46,11, 10, 10);
        
        g.drawRect(58,11, 9, 9);
        g.setColor(blackDither);
        g.fillRect(58,11, 9, 9);
        ic = new ImageIcon(imageIconKapazBaldErsch�pft);
        jLabelKapazBaldErsch�pft.setIcon(ic);
        jLabelKapazBaldErsch�pft.setToolTipText("Es gibt kaum noch Lagerplatz f�r dieses Einzelteil!");
        
        //Kapazit�ten ersch�pft
        Image imageIconKapazErsch�pft = new BufferedImage(70, 30, 2);
        g = imageIconKapazErsch�pft.getGraphics();
        g.setColor(Color.black);
        g.fillRect(10,11, 10, 10);
        g.fillRect(22,11, 10, 10);
        g.fillRect(34,11, 10, 10);
        g.fillRect(46,11, 10, 10);
        g.fillRect(58,11, 10, 10);
        ic = new ImageIcon(imageIconKapazErsch�pft);
        jLabelKapazErsch�pft.setIcon(ic);
        jLabelKapazErsch�pft.setToolTipText("Es gibt keinen Lagerplatz mehr f�r dieses Einzelteil!");
    }
    
    /** Zeigt die Article an die in dem selektierten WarehouseLocation liegen */
    public void getArtikelImLagerplatz() {
        if (jComboBoxArtikelImLager.getSelectedItem() != null) {
            if (!jComboBoxArtikelImLager.getSelectedItem().toString().equals("leer")) {
                ComponentWarehouseItem selectedElem = (ComponentWarehouseItem) etElems.get(jComboBoxArtikelImLager.getSelectedIndex());
                jTextFieldMindestbestand.setText(String.valueOf(selectedElem.getMinBestand()));
                jTextFieldBestand.setText(String.valueOf(selectedElem.getBestand()));
                jTextFieldBelegung.setText(String.valueOf(selectedElem.getBestand()*100/selectedElem.getMaxBestand()));
                jTextFieldFrei.setText(String.valueOf(selectedElem.getMaxBestand()-selectedElem.getBestand()));
            } else {
                jTextFieldMindestbestand.setText("");
                jTextFieldBestand.setText("");
                jTextFieldBelegung.setText("0");
                jTextFieldFrei.setText("");
            }
        } else {
            jTextFieldMindestbestand.setText("");
            jTextFieldBestand.setText("");
            jTextFieldBelegung.setText("0");
            jTextFieldFrei.setText("");
        }
    }
    
    /** Zeigt Infos zu dem Article auf dem selektierten WarehouseLocation an */
    public void getArtikelImLagerplatzInfo() {
        
        if (!jComboBoxLagerplatz.getSelectedItem().toString().equals("Select:") && !jComboBoxLagerplatz.getSelectedItem().toString().equals(actLgPlatz)) {
            jComboBoxArtikelImLager.removeAllItems();
            etElems.removeAllElements();
            
            actLgPlatz = jComboBoxLagerplatz.getSelectedItem().toString();
            Collection etElemsCol = new Vector();
            Collection artElemsCol = new Vector();
            try {
                etElemsCol = dao.getEinzelteilLagerElement(jComboBoxLagerplatz.getSelectedItem().toString());
                artElemsCol = dao.getArtikelLagerElement(jComboBoxLagerplatz.getSelectedItem().toString());
            } catch (WiSimDAOException e) {
                wiSimLogger.log("getArtikelImLagerplatzInfo()", e);
            }
            
            Iterator artElems_it = artElemsCol.iterator();
            Iterator etElems_it = etElemsCol.iterator();
            int i = 0;
            
            while (artElems_it.hasNext()) {
                ComponentWarehouseItem etElem = (ComponentWarehouseItem) artElems_it.next();
                Article art = new Article();
                try {
                    art = dao.getArtikel(etElem.getId());
                } catch (WiSimDAOException e ) {
                    wiSimLogger.log("getArtikelImLagerplatzInfo()", e);
                }
                
                etElem.setEinzelteilName(art.getName());
                etElem.setMinBestand(art.getMindestbestand());
                etElems.add(etElem);
                
                jComboBoxArtikelImLager.insertItemAt(art.getName(), i);
                i++;
            }
            
            while (etElems_it.hasNext()) {
                ComponentWarehouseItem etElem = (ComponentWarehouseItem) etElems_it.next();
                WiSimComponent et = new WiSimComponent();
                try {
                    et = dao.getEinzelteil(etElem.getId());
                } catch (WiSimDAOException e ) {
                    wiSimLogger.log("getArtikelImLagerplatzInfo()", e);
                }
                
                etElem.setEinzelteilName(et.getName());
                etElem.setMinBestand(et.getMindestbestand());
                etElems.add(etElem);
                
                jComboBoxArtikelImLager.insertItemAt(et.getName(), i);
                i++;
            }
            if (jComboBoxArtikelImLager.getItemCount() == 0) {
                jComboBoxArtikelImLager.insertItemAt("leer", 0);
            }
            jComboBoxArtikelImLager.setSelectedIndex(0);
        }
        
        if (jComboBoxLagerplatz.getSelectedItem().toString().equals("Select:")) {
            actLgPlatz = jComboBoxLagerplatz.getSelectedItem().toString();
            jComboBoxArtikelImLager.removeAllItems();
            etElems.removeAllElements();
            jTextFieldMindestbestand.setText("");
            jTextFieldBestand.setText("");
            jTextFieldBelegung.setText("");
            jTextFieldFrei.setText("");
            jComboBoxArtikelImLager.setSelectedIndex(-1);
        }
    }
    
    /** Gibt alle Lagerplaetze aus der DB aus */
    public void getLagerplaetze() {
        jComboBoxLagerplatz.removeAllItems();
        jComboBoxLagerplatz.addItem("Select:");
        try {
            lagerplaetze = dao.getLagerplaetze();
        } catch (WiSimDAOException e) {
            wiSimLogger.log("getLagerplaetze()", e);
        }
        
        Iterator lgPlatz_it = lagerplaetze.iterator();
        while (lgPlatz_it.hasNext()) {
            WarehouseLocation lgplatz = (WarehouseLocation) lgPlatz_it.next();
            jComboBoxLagerplatz.addItem(lgplatz.getStellplatzNr());
        }
    }
    
    /** Aktualisiert die Gesamtliste */
    public void refreshLagerGesamtliste() {
        
        //Update der Lagerplatz-Detailansicht
        if (!jComboBoxLagerplatz.getSelectedItem().equals("Select:") && !jComboBoxArtikelImLager.getSelectedItem().equals("leer")) {
            etElems.removeAllElements();
            
            Collection etElemsCol = new Vector();
            Collection artElemsCol = new Vector();
            try {
                etElemsCol = dao.getEinzelteilLagerElement(jComboBoxLagerplatz.getSelectedItem().toString());
                artElemsCol = dao.getArtikelLagerElement(jComboBoxLagerplatz.getSelectedItem().toString());
            } catch (WiSimDAOException e) {
                wiSimLogger.log("refreshLagerGesamtliste()", e);
            }
            
            Iterator artElems_it = artElemsCol.iterator();
            Iterator etElems_it = etElemsCol.iterator();
            int i = 0;
            
            while (artElems_it.hasNext()) {
                ComponentWarehouseItem etElem = (ComponentWarehouseItem) artElems_it.next();
                Article art = new Article();
                try {
                    art = dao.getArtikel(etElem.getId());
                } catch (WiSimDAOException e ) {
                    wiSimLogger.log("refreshLagerGesamtliste()", e);
                }
                
                etElem.setEinzelteilName(art.getName());
                etElem.setMinBestand(art.getMindestbestand());
                etElems.add(etElem);
                
                i++;
            }
            
            while (etElems_it.hasNext()) {
                ComponentWarehouseItem etElem = (ComponentWarehouseItem) etElems_it.next();
                WiSimComponent et = new WiSimComponent();
                try {
                    et = dao.getEinzelteil(etElem.getId());
                } catch (WiSimDAOException e ) {
                    wiSimLogger.log("refreshLagerGesamtliste()", e);
                }
                
                etElem.setEinzelteilName(et.getName());
                etElem.setMinBestand(et.getMindestbestand());
                etElems.add(etElem);
            }
            
            ComponentWarehouseItem selectedElem = (ComponentWarehouseItem) etElems.get(jComboBoxArtikelImLager.getSelectedIndex());
            
            //Aktualisiere nur wenn sich der Bestand ge�ndert hat!
            if (selectedElem.getBestand() != Integer.parseInt(jTextFieldBestand.getText())) {
                jTextFieldMindestbestand.setText(String.valueOf(selectedElem.getMinBestand()));
                jTextFieldBestand.setText(String.valueOf(selectedElem.getBestand()));
                jTextFieldBelegung.setText(String.valueOf(selectedElem.getBestand()*100/selectedElem.getMaxBestand()));
                jTextFieldFrei.setText(String.valueOf(selectedElem.getMaxBestand()-selectedElem.getBestand()));
            }
        }
        
        //Update der Gesamtliste
        try {
            einzelteileListe = dao.getEinzelteilLagerElement();
            artikelListe = dao.getArtikelLagerElement();
        } catch (WiSimDAOException e) {
            wiSimLogger.log("refreshLagerGesamtliste()", e);
        }
        
        int i=0;
        
        Iterator art_it = artikelListe.iterator();
        while (art_it.hasNext()) {
            ComponentWarehouseItem artikel = (ComponentWarehouseItem) art_it.next();
            
            //Aktualisiere nur wenn sich der Bestand ge�ndert hat!
            if (artikel.getBestand() != Integer.parseInt((String) jTableGesamtliste.getValueAt(i, 1))) {
                jTableGesamtliste.setValueAt(String.valueOf(artikel.getBestand()), i, 1);
                
                IconGenerater iconGen = new IconGenerater();
                
                double actBestand = artikel.getBestand();
                double actMaxBestand = artikel.getMaxBestand();
                
                double rects = (double)(actBestand/actMaxBestand)*5;
                int filledrectsCount = (int) Math.round(rects);
                
                if (artikel.getBestand() < artikel.getMinBestand()) {
                    jTableGesamtliste.setValueAt(iconGen.generateIcon(2, filledrectsCount), i, 5);
                } else if ((artikel.getBestand() - artikel.getMinBestand()/2) < artikel.getMinBestand()) {
                    jTableGesamtliste.setValueAt(iconGen.generateIcon(1, filledrectsCount), i, 5);
                } else {
                    jTableGesamtliste.setValueAt(iconGen.generateIcon(0, filledrectsCount), i, 5);
                }
            }
            i++;
        }
        
        Iterator et_it = einzelteileListe.iterator();
        while (et_it.hasNext()) {
            ComponentWarehouseItem einzelteil = (ComponentWarehouseItem) et_it.next();
            
            if (einzelteil.getBestand() != Integer.parseInt((String) jTableGesamtliste.getValueAt(i, 1))) {
                jTableGesamtliste.setValueAt(String.valueOf(einzelteil.getBestand()), i, 1);
                
                IconGenerater iconGen = new IconGenerater();
                
                double actBestand = einzelteil.getBestand();
                double actMaxBestand = einzelteil.getMaxBestand();
                
                double rects = (double)(actBestand/actMaxBestand)*5;
                int filledrectsCount = (int) Math.round(rects);
                
                if (einzelteil.getBestand() < einzelteil.getMinBestand()) {
                    jTableGesamtliste.setValueAt(iconGen.generateIcon(2, filledrectsCount), i, 5);
                } else if ((einzelteil.getBestand() - einzelteil.getMinBestand()/2) < einzelteil.getMinBestand()) {
                    jTableGesamtliste.setValueAt(iconGen.generateIcon(1, filledrectsCount), i, 5);
                } else {
                    jTableGesamtliste.setValueAt(iconGen.generateIcon(0, filledrectsCount), i, 5);
                }
            }
            i++;
        }
    }
    
    /** Setzt die Variable isBuilt
     * @param isBuilt
     */
    public void setIsBuilt(boolean isBuilt) {
        this.isBuilt = isBuilt;
    }
    
    /** Wurde das Pane schon einmal aufgebaut, so ist "isBuilt" = TRUE.
     *  Wichtig ist diese Variable f�r die Simulation: Wurde das Pane
     *  schon einmal aufgebaut, so m�ssen die Simulationsthread das Pane
     *  nicht noch einmal initialisieren!
     * @return boolean isBuilt
     */
    public boolean getIsBuilt() {
        return isBuilt;
    }
    
    /** Setzt die Variable isActive auf TRUE oder FALSE
     * @param isActive
     */
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    /** Befindet sich der Benutzer auf diesem Pane, so ist "isActive" = TRUE.
     *  Verl�sst der Benutzer das Pane, so ist "isActive" = FALSE.
     *  Wichtig ist diese Variable f�r die Simulation: Es wird nur das Pane
     *  aktualisiert, auf dem sich der Benutzer gerade befindet!
     * @return boolean isActive
     */
    public boolean getIsActive() {
        return isActive;
    }
}
