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
 * JPanelArbeitsplatzlager.java
 *
 * Created on 21. M�rz 2003, 21:04
 */

package net.sourceforge.wisim.controller;

import net.sourceforge.wisim.dao.*;
import net.sourceforge.wisim.model.*;
import java.util.*;
import javax.swing.table.*;
import javax.swing.*;

/**
 * Gibt eine Tabellen�bersicht aller Arbeitspl�tze mit den Best�nden der
 * jeweiligen Einzelteile aus. Es lassen sich die Anzahl der Arbeiter
 * pro WorkPlace einstellen.
 * @author  benjamin.pasero
 */
public class JPanelWorkPlaceStore extends javax.swing.JPanel implements SimulationPane {
    
    private WiSimDAO dao;
    private Vector arbeitsplaetze;
    private Collection arbeitsplatzLager;
    private int apAnzahl;
    private int apElemAnzahl;
    private JProgressBar jprg;
    private boolean isBuilt;
    private Vector tempEingangslagerBestand;
    private Vector tempAusgangslagerBestand;
    private WiSimMainController wiSimMainController;
    
    //Logger
    private WiSimLogger wiSimLogger;
    
    /** Creates new form JPanelArbeitsplatzlager
     * @param wiSimMainController
     */
    public JPanelWorkPlaceStore(WiSimMainController wiSimMainController) {
		this.wiSimMainController = wiSimMainController;
        wiSimLogger = wiSimMainController.getWiSimLogger();
        initDAO(wiSimMainController);
        arbeitsplaetze = new Vector();
        arbeitsplatzLager = new Vector();
        apAnzahl = 0;
        apElemAnzahl = 0;
        isBuilt = false;
        tempEingangslagerBestand = new Vector();
        tempAusgangslagerBestand = new Vector();
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
    jLabelArbeitsplatzlager = new javax.swing.JLabel();
    jPanelArbeitsplatzlager = new javax.swing.JPanel();
    jScrollPaneGesamtliste = new javax.swing.JScrollPane();
    jTableGesamtliste = new javax.swing.JTable();
    jPanelArbeitsplaetze = new javax.swing.JPanel();
    jScrollPaneArbeitsplaetze = new javax.swing.JScrollPane();
    jTableArbeitsplaetze = new JTable()
    {
      public TableCellRenderer getCellRenderer(int row, int column) {
        TableColumn tableColumn = getColumnModel().getColumn(column);
        TableCellRenderer renderer = tableColumn.getCellRenderer();
        if (renderer == null) {
          Class c = getColumnClass(column);
          if( c.equals(Object.class) )
          {
            Object o = getValueAt(row,column);
            if( o != null )
            c = getValueAt(row,column).getClass();
          }
          renderer = getDefaultRenderer(c);
        }
        return renderer;
      }

      public TableCellEditor getCellEditor(int row, int column) {
        TableColumn tableColumn = getColumnModel().getColumn(column);
        TableCellEditor editor = tableColumn.getCellEditor();
        if (editor == null) {
          Class c = getColumnClass(column);
          if( c.equals(Object.class) )
          {
            Object o = getValueAt(row,column);
            if( o != null )
            c = getValueAt(row,column).getClass();
          }
          editor = getDefaultEditor(c);
        }
        return editor;
      }

    };
    jTableArbeitsplaetze.setSelectionMode(0);
    jPanelArbeiter = new javax.swing.JPanel();
    jButtonOk = new javax.swing.JButton();
    jSpinnerArbeiter = new javax.swing.JSpinner();
    jLabelArbeitsplatz = new javax.swing.JLabel();
    jTextFieldArbeitsplatz = new javax.swing.JTextField();
    jLabelArbeiter = new javax.swing.JLabel();

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

    jLabelArbeitsplatzlager.setFont(new java.awt.Font("Dialog", 1, 24));
    jLabelArbeitsplatzlager.setText("Arbeitsplatzlager");
    add(jLabelArbeitsplatzlager);
    jLabelArbeitsplatzlager.setBounds(310, 6, 220, 32);

    jPanelArbeitsplatzlager.setLayout(null);

    jPanelArbeitsplatzlager.setBorder(new javax.swing.border.TitledBorder("Arbeitsplatzlager"));
    jTableGesamtliste.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {
        {null, null, null, null},
        {null, null, null, null},
        {null, null, null, null},
        {null, null, null, null}
      },
      new String [] {
        "Artikelname", "aktueller Bestand", "Max. Bestand", "Belegung"
      }
    ) {
      boolean[] canEdit = new boolean [] {
        false, false, false, false
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

    jPanelArbeitsplatzlager.add(jScrollPaneGesamtliste);
    jScrollPaneGesamtliste.setBounds(10, 20, 550, 84);

    add(jPanelArbeitsplatzlager);
    jPanelArbeitsplatzlager.setBounds(10, 430, 570, 110);

    jPanelArbeitsplaetze.setLayout(null);

    jPanelArbeitsplaetze.setBorder(new javax.swing.border.TitledBorder("Arbeitspl\u00e4tze"));
    jScrollPaneArbeitsplaetze.addAncestorListener(new javax.swing.event.AncestorListener() {
      public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
      }
      public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
        jScrollPaneArbeitsplaetzeAncestorAdded(evt);
      }
      public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
      }
    });

    jTableArbeitsplaetze.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {

      },
      new String [] {
        "Nr. ", "Beschreibung", "Vorg�nger", "Nachfolger", "Zeit", "Eingangslager", "Ausgangslager"
      }
    ) {
      boolean[] canEdit = new boolean [] {
        false, false, false, false, false, false, false
      };

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
      }
    });
    jTableArbeitsplaetze.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        jTableArbeitsplaetzeMouseClicked(evt);
      }
    });

    jScrollPaneArbeitsplaetze.setViewportView(jTableArbeitsplaetze);

    jPanelArbeitsplaetze.add(jScrollPaneArbeitsplaetze);
    jScrollPaneArbeitsplaetze.setBounds(10, 20, 750, 372);

    add(jPanelArbeitsplaetze);
    jPanelArbeitsplaetze.setBounds(10, 30, 770, 400);

    jPanelArbeiter.setLayout(null);

    jPanelArbeiter.setBorder(new javax.swing.border.TitledBorder("Arbeiter"));
    jPanelArbeiter.addAncestorListener(new javax.swing.event.AncestorListener() {
      public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
      }
      public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
        jPanelArbeiterAncestorAdded(evt);
      }
      public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
      }
    });

    jButtonOk.setText("OK");
    jButtonOk.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButtonOkActionPerformed(evt);
      }
    });

    jPanelArbeiter.add(jButtonOk);
    jButtonOk.setBounds(60, 80, 70, 20);

    jPanelArbeiter.add(jSpinnerArbeiter);
    jSpinnerArbeiter.setBounds(110, 50, 40, 24);

    jLabelArbeitsplatz.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelArbeitsplatz.setText("Arbeitsplatz");
    jPanelArbeiter.add(jLabelArbeitsplatz);
    jLabelArbeitsplatz.setBounds(20, 20, 80, 16);

    jTextFieldArbeitsplatz.setEditable(false);
    jPanelArbeiter.add(jTextFieldArbeitsplatz);
    jTextFieldArbeitsplatz.setBounds(110, 20, 40, 20);

    jLabelArbeiter.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelArbeiter.setText("Arbeiter");
    jPanelArbeiter.add(jLabelArbeiter);
    jLabelArbeiter.setBounds(40, 50, 60, 16);

    add(jPanelArbeiter);
    jPanelArbeiter.setBounds(590, 430, 190, 110);

  }//GEN-END:initComponents
    
    private void formAncestorRemoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorRemoved
        wiSimMainController.removeActivPanel(this);
    }//GEN-LAST:event_formAncestorRemoved
    
    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded
        setIsBuilt(true);
        wiSimMainController.addActivPanel(this);
    }//GEN-LAST:event_formAncestorAdded
    
    private void jPanelArbeiterAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanelArbeiterAncestorAdded
        //Reset des Arbeiter-Panes
        jTextFieldArbeitsplatz.setText("");
        jSpinnerArbeiter.setModel(new SpinnerNumberModel(0, 0, 0, 0));
    }//GEN-LAST:event_jPanelArbeiterAncestorAdded
    
    private void jButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOkActionPerformed
        if (!jTextFieldArbeitsplatz.getText().equals("")) {
            setArbeiterZahl(Integer.parseInt(jTextFieldArbeitsplatz.getText()), (Integer)jSpinnerArbeiter.getValue());
        }
        jTextFieldArbeitsplatz.grabFocus();
    }//GEN-LAST:event_jButtonOkActionPerformed
    
    private void jTableGesamtlisteAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTableGesamtlisteAncestorAdded
        resetGesamtListe();
    }//GEN-LAST:event_jTableGesamtlisteAncestorAdded
    
    private void jTableArbeitsplaetzeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableArbeitsplaetzeMouseClicked
        getArbeitsplatzLagerElemente();
    }//GEN-LAST:event_jTableArbeitsplaetzeMouseClicked
    
    private void jScrollPaneArbeitsplaetzeAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jScrollPaneArbeitsplaetzeAncestorAdded
        getArbeitsplatzlager();
    }//GEN-LAST:event_jScrollPaneArbeitsplaetzeAncestorAdded
    
    
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButtonOk;
  private javax.swing.JLabel jLabelArbeiter;
  private javax.swing.JLabel jLabelArbeitsplatz;
  private javax.swing.JLabel jLabelArbeitsplatzlager;
  private javax.swing.JPanel jPanelArbeiter;
  private javax.swing.JPanel jPanelArbeitsplaetze;
  private javax.swing.JPanel jPanelArbeitsplatzlager;
  private javax.swing.JScrollPane jScrollPaneArbeitsplaetze;
  private javax.swing.JScrollPane jScrollPaneGesamtliste;
  private javax.swing.JSpinner jSpinnerArbeiter;
  private javax.swing.JTable jTableArbeitsplaetze;
  private javax.swing.JTable jTableGesamtliste;
  private javax.swing.JTextField jTextFieldArbeitsplatz;
  // End of variables declaration//GEN-END:variables
    
    /** Holt alle Eingangs und Ausgangslager aus der DB. */
    public void getArbeitsplatzlager() {
        try {
            arbeitsplaetze = dao.getArbeitsplaetze();
        } catch (WiSimDAOException e) {
            wiSimLogger.log("getArbeitsplatzLager()", e);
        }
        apAnzahl = arbeitsplaetze.size();
        
        setArbeitsplatzTabelle();
        int i = 0;
        Iterator ap_it = arbeitsplaetze.iterator();
        while (ap_it.hasNext()) {
            WorkPlace ap = (WorkPlace) ap_it.next();
            
            jTableArbeitsplaetze.setValueAt(String.valueOf(ap.getNr()), i, 0);
            jTableArbeitsplaetze.setValueAt(ap.getBeschreibung(), i, 1);
            
            String vorgaenger = "";
            int[] vorgaengerNr = ap.getVorgaenger();
            if (vorgaengerNr.length > 0) {
                vorgaenger = String.valueOf(vorgaengerNr[0]);
                int x = 1;
                while (x < vorgaengerNr.length) {
                    vorgaenger = vorgaenger + ", " + vorgaengerNr[x];
                    x++;
                }
            }
            
            String nachfolger = "";
            int[] nachfolgerNr = ap.getNachfolger();
            if (nachfolgerNr.length > 0) {
                nachfolger = String.valueOf(nachfolgerNr[0]);
                int x = 1;
                while (x < nachfolgerNr.length) {
                    nachfolger = nachfolger + ", " + nachfolgerNr[x];
                    x++;
                }
            }
            
            if (vorgaenger.equals("0"))
                jTableArbeitsplaetze.setValueAt("-", i, 2);
            else
                jTableArbeitsplaetze.setValueAt(vorgaenger, i, 2);
            
            if (nachfolger.equals("0"))
                jTableArbeitsplaetze.setValueAt("-", i, 3);
            else
                jTableArbeitsplaetze.setValueAt(nachfolger, i, 3);
            
            jTableArbeitsplaetze.setValueAt(String.valueOf(ap.getDauer()), i, 4);
            
            WorkPlaceStore apLager = new WorkPlaceStore();
            try {
                apLager = dao.getArbeitsplatzLager(ap.getNr(), 26, "Eingang");
            } catch (WiSimDAOException e) {
                wiSimLogger.log("getArbeitsplatzLager()", e);
            }
            
            tempEingangslagerBestand.add(String.valueOf(apLager.getBestand()));
            
            jprg = new JProgressBar(0, 10);
            jprg.setValue(apLager.getBestand());
            jprg.setToolTipText("Bestand: " + apLager.getBestand());
            if (vorgaenger.equals("0"))
                jprg.setEnabled(false);
            jTableArbeitsplaetze.setValueAt(jprg, i, 5);
            
            try {
                apLager = dao.getArbeitsplatzLager(ap.getNr(), 26, "Ausgang");
            } catch (WiSimDAOException e) {
                wiSimLogger.log("getArbeitsplatzLager()", e);
            }
            
            tempAusgangslagerBestand.add(String.valueOf(apLager.getBestand()));
            
            jprg = new JProgressBar(0, 10);
            jprg.setValue(apLager.getBestand());
            jprg.setToolTipText("Bestand: " + apLager.getBestand());
            if (nachfolger.equals("0"))
                jprg.setEnabled(false);
            jTableArbeitsplaetze.setValueAt(jprg, i, 6);
            
            i++;
        }
    }
    
    /** Zeigt die Elemente eines Arbeitsplatzlagers an. */
    public void getArbeitsplatzLagerElemente() {
        WorkPlace ap = (WorkPlace) arbeitsplaetze.get(jTableArbeitsplaetze.getSelectedRow());
        
        //Arbeiter-Pane
        jTextFieldArbeitsplatz.setText(String.valueOf(ap.getNr()));
        jSpinnerArbeiter.setModel(new SpinnerNumberModel(ap.getAnzahlArbeiter(), 0, 10, 1));
        
        try {
            arbeitsplatzLager = dao.getArbeitsplatzLager(ap.getNr(), "Einzelteil");
        } catch (WiSimDAOException e) {
            wiSimLogger.log("getArbeitsplatzLagerElemente()", e);
        }
        apElemAnzahl = arbeitsplatzLager.size();
        
        if (apElemAnzahl > 0) {
            buildArbeitsplatzLagerElementeTabelle();
        } else {
            resetGesamtListe();
        }
    }
    
    /** Baut die WorkPlace-Tabelle */
    public void buildArbeitsplatzLagerElementeTabelle() {
        setArbeitsplatzLagerElementeTabelle();
        int i = 0;
        Iterator arbeitsplatzLager_it = arbeitsplatzLager.iterator();
        while (arbeitsplatzLager_it.hasNext()) {
            WorkPlaceStore apLager = (WorkPlaceStore) arbeitsplatzLager_it.next();
            
            WiSimComponent et = new WiSimComponent();
            try {
                et = dao.getEinzelteil(apLager.getEinzelteilNr());
            } catch (WiSimDAOException e) {
                wiSimLogger.log("buildArbeitsplatzLagerElementeTabelle()", e);
            }
            
            jTableGesamtliste.setValueAt(et.getName(), i, 0);
            jTableGesamtliste.setValueAt(String.valueOf(apLager.getBestand()), i, 1);
            jTableGesamtliste.setValueAt(String.valueOf(apLager.getMaxBestand()), i, 2);
            
            IconGenerater iconGen = new IconGenerater();
            
            double actBestand = apLager.getBestand();
            double actMaxBestand = apLager.getMaxBestand();
            
            double rects = (double)(actBestand/actMaxBestand)*5;
            int filledrectsCount = (int) Math.round(rects);
            
            if (apLager.getBestand() < (apLager.getMaxBestand()/4)) {
                jTableGesamtliste.setValueAt(iconGen.generateIcon(2, filledrectsCount), i, 3);
            } else if ((apLager.getBestand() < (apLager.getMaxBestand()/2))) {
                jTableGesamtliste.setValueAt(iconGen.generateIcon(1, filledrectsCount), i, 3);
            } else {
                jTableGesamtliste.setValueAt(iconGen.generateIcon(0, filledrectsCount), i, 3);
            }
            i++;
        }
    }
    
    /** Erstellt die ArbeitsplatzTabelle. */
    public void setArbeitsplatzTabelle() {
        //DefaultTableModel mit Variablen Zeilen, 4 TableHeads und nicht editierbaren Zellen
        Object[][] tableInit = new Object[apAnzahl][7];
        DefaultTableModel defTable = new DefaultTableModel(
        tableInit,
        new String [] {
            "Nr. ", "Beschreibung", "Vorg�nger", "Nachfolger", "Zeit", "Eingangslager", "Ausgangslager"
        }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
            public Class getColumnClass(int c) {
                return getValueAt(0, c).getClass();
            }
        };
        jTableArbeitsplaetze.setModel(defTable);
        jTableArbeitsplaetze.setFocusable(false);
        jTableArbeitsplaetze.getTableHeader().setReorderingAllowed(false);
        
        javax.swing.table.TableColumn column = null;
        
        //Spaltenbreiten der Tabelle werden gesetzt
        for (int i = 0; i < 7; i++) {
            column = jTableArbeitsplaetze.getColumnModel().getColumn(i);
            switch (i) {
                //Nr
                case 0:
                    column.setPreferredWidth(30);
                    break;
                    //Beschreibung
                case 1:
                    column.setPreferredWidth(300);
                    break;
                    //Vorg�nger
                case 2:
                    column.setPreferredWidth(80);
                    break;
                    //Nachfolger
                case 3:
                    column.setPreferredWidth(80);
                    break;
                    //Zeit
                case 4:
                    column.setPreferredWidth(40);
                    break;
                    //Eingangslager
                case 5:
                    column.setPreferredWidth(110);
                    break;
                    //Ausgangslager
                case 6:
                    column.setPreferredWidth(110);
                    break;
            }
        }
        jTableArbeitsplaetze.setDefaultRenderer( JComponent.class, new JComponentCellRenderer() );
        jTableArbeitsplaetze.setDefaultEditor( JComponent.class, new JComponentCellEditor() );
    }
    
    /** Erstellt die ArbeitsplatzLagerElementeTabelle. */
    public void setArbeitsplatzLagerElementeTabelle() {
        //DefaultTableModel mit Variablen Zeilen, 4 TableHeads und nicht editierbaren Zellen
        Object[][] tableInit = new Object[4][4];
        DefaultTableModel defTable = new DefaultTableModel(
        tableInit,
        new String [] {
            "Artikelname", "aktueller Bestand", "Max. Bestand", "Belegung"
        }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
            public Class getColumnClass(int c) {
                return getValueAt(0, c).getClass();
            }
        };
        jTableGesamtliste.setModel(defTable);
        jTableGesamtliste.setEnabled(false);
        jTableGesamtliste.getTableHeader().setReorderingAllowed(false);
    }
    
    /** Refreshed beide Tabellen */
    private void refreshArbeitsplatzLagerElementeTabelle() {
        
        //Refresh Tabelle Arbeitsplaetze
        try {
            arbeitsplaetze = dao.getArbeitsplaetze();
        } catch (WiSimDAOException e) {
            wiSimLogger.log("refreshArbeitsplatzLagerElementeTabelle()", e);
        }
        
        int i = 0;
        Iterator ap_it = arbeitsplaetze.iterator();
        while (ap_it.hasNext()) {
            WorkPlace ap = (WorkPlace) ap_it.next();
            
            WorkPlaceStore apLager = new WorkPlaceStore();
            try {
                apLager = dao.getArbeitsplatzLager(ap.getNr(), 26, "Eingang");
            } catch (WiSimDAOException e) {
                wiSimLogger.log("refreshArbeitsplatzLagerElementeTabelle()", e);
            }
            
            //Zelle wird nur refreshed wenn sich der Bestand ge�ndert hat!
            if (!((String)tempEingangslagerBestand.get(i)).equals(String.valueOf(apLager.getBestand()))) {
                tempEingangslagerBestand.setElementAt(String.valueOf(apLager.getBestand()), i);
                jprg = new JProgressBar(0, 10);
                jprg.setValue(apLager.getBestand());
                jprg.setToolTipText("Bestand: " + apLager.getBestand());
                jTableArbeitsplaetze.setValueAt(jprg, i, 5);
            }
            
            try {
                apLager = dao.getArbeitsplatzLager(ap.getNr(), 26, "Ausgang");
            } catch (WiSimDAOException e) {
                wiSimLogger.log("refreshArbeitsplatzLagerElementeTabelle()", e);
            }
            
            //Zelle wird nur refreshed wenn sich der Bestand ge�ndert hat!
            if (!((String)tempAusgangslagerBestand.get(i)).equals(String.valueOf(apLager.getBestand()))) {
                tempAusgangslagerBestand.setElementAt(String.valueOf(apLager.getBestand()), i);
                jprg = new JProgressBar(0, 10);
                jprg.setValue(apLager.getBestand());
                jprg.setToolTipText("Bestand: " + apLager.getBestand());
                jTableArbeitsplaetze.setValueAt(jprg, i, 6);
            }
            i++;
        }
        
        //Refresh Tabelle WorkPlaceStore
        if (jTableArbeitsplaetze.getSelectedRow() >= 0) {
            WorkPlace ap = (WorkPlace) arbeitsplaetze.get(jTableArbeitsplaetze.getSelectedRow());
            try {
                arbeitsplatzLager = dao.getArbeitsplatzLager(ap.getNr(), "Einzelteil");
            } catch (WiSimDAOException e) {
                wiSimLogger.log("refreshArbeitsplatzLagerElementeTabelle()", e);
            }
            apElemAnzahl = arbeitsplatzLager.size();
            i = 0;
            if (apElemAnzahl > 0) {
                Iterator arbeitsplatzLager_it = arbeitsplatzLager.iterator();
                while (arbeitsplatzLager_it.hasNext()) {
                    WorkPlaceStore apLager = (WorkPlaceStore) arbeitsplatzLager_it.next();
                    
                    //Zelle wird nur aktualisiert wenn sich der Bestand ge�ndert hat.
                    if (jTableGesamtliste.getValueAt(i, 1) != null && !jTableGesamtliste.getValueAt(i, 1).equals("")) {
                        if (apLager.getBestand() != Integer.parseInt((String) jTableGesamtliste.getValueAt(i, 1))) {
                            
                            jTableGesamtliste.setValueAt(String.valueOf(apLager.getBestand()), i, 1);
                            
                            IconGenerater iconGen = new IconGenerater();
                            
                            double actBestand = apLager.getBestand();
                            double actMaxBestand = apLager.getMaxBestand();
                            
                            double rects = (double)(actBestand/actMaxBestand)*5;
                            int filledrectsCount = (int) Math.round(rects);
                            
                            if (apLager.getBestand() < (apLager.getMaxBestand()/4)) {
                                jTableGesamtliste.setValueAt(iconGen.generateIcon(2, filledrectsCount), i, 3);
                            } else if ((apLager.getBestand() < (apLager.getMaxBestand()/2))) {
                                jTableGesamtliste.setValueAt(iconGen.generateIcon(1, filledrectsCount), i, 3);
                            } else {
                                jTableGesamtliste.setValueAt(iconGen.generateIcon(0, filledrectsCount), i, 3);
                            }
                        }
                    }
                    i++;
                }
            } else {
                resetGesamtListe();
            }
        }
    }
    
    /** L�scht die GesamtListe unterhalb der Arbeitsplatztabelle. */
    public void resetGesamtListe() {
        int i = jTableGesamtliste.getRowCount()-1;
        while (i >= 0) {
            jTableGesamtliste.setValueAt("", 3-i, 0);
            jTableGesamtliste.setValueAt("", 3-i, 1);
            jTableGesamtliste.setValueAt("", 3-i, 2);
            jTableGesamtliste.setValueAt("", 3-i, 3);
            i--;
        }
    }
    
    /** Setzt die Zahl an Arbeiter an diesem WorkPlace
     * @param apNr WorkPlace Nr
     * @param anzahl Anzahl der Mitarbeiter
     */
    public void setArbeiterZahl(int apNr, Integer anzahl) {
        try {
            dao.setArbeiterZahl(apNr, anzahl.intValue());
        } catch (WiSimDAOWriteException e) {
            wiSimLogger.log("setArbeiterZahl()", e);
        }
        
        //Aktualisierung der arbeitsplaetze-Collection
        try {
            arbeitsplaetze = dao.getArbeitsplaetze();
        } catch (WiSimDAOException e) {
            wiSimLogger.log("setArbeiterZahl()", e);
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

	/* (non-Javadoc)
	 * @see net.sourceforge.wisim.model.SimulationPane#refresh()
	 */
	public void refresh() {
		refreshArbeitsplatzLagerElementeTabelle();		
	}
}