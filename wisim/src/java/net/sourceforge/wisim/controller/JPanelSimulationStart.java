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
 * JPanelSimulationStart.java
 *
 * Created on 17. März 2003, 14:55
 */

package net.sourceforge.wisim.controller;
import net.sourceforge.wisim.dao.*;
import net.sourceforge.wisim.model.*;
import net.sourceforge.wisim.simulation.*;

import javax.swing.tree.*;
import javax.swing.plaf.metal.*;
import java.util.*;
import java.sql.Date;
import javax.swing.*;
import javax.swing.event.*;

/** In diesem Fenster kann der Benutzer die Simulation starten. Ereignisse (z.B.
 * eingehende Lieferungen, produzierte Hubs, versendete Hubs) werden angezeigt.
 * @author benjamin.pasero
 */
public class JPanelSimulationStart extends javax.swing.JPanel {
    private WiSimDAO dao;
    private Date actDate;
    private int hubBestand;
    private Vector artikelLagerElemente;
    private Hashtable stueckliste;
    private Collection vertraege;
    private GregorianCalendar actDateGC;
    private GregorianCalendar lieferDateGC;
    private boolean isActive;
    private int actDay;
    
    //Treepath der JTrees werden gespeichert
    private Set treepathsEinkauf;
    private Set treepathsVertrieb;
    private Set treepathsProduktion;
    
    // Simulation der Produktion
    private WiSimMainController wiSimMainController;
    private ProductionController runController;
    
    // Dauer einer Zeiteinheit für die gesamte Simulation
    public final static int TIMESTEP = 100;
    
    // IconNodes als Elemente der JTrees
    private IconNode rootNodeLieferungen;
    private IconNode rootNodeVertrieb;
    private IconNode rootNodeProduktion;
    private IconNode dateNode;
    private IconNode lieferantNode;
    private IconNode positionNode;
    private IconNode hubProduktionNode;
    private IconNode hubEinzelteilVerbrauch;
    private IconNode hubVertrieb;
    private IconNode hubVertriebPos;
    
    // Icons für einzelene Elemente der JTrees
    private ImageIcon rightIcon;
    private ImageIcon vanIcon;
    private ImageIcon boxIcon;
    private ImageIcon hubIcon;
    private ImageIcon boxOpenIcon;
    private ImageIcon errorIcon;
    
    //Logger
    private WiSimLogger wiSimLogger;
    
    /** Creates new form JPanelSimulationStart
     * @param wiSimMainController
     */
    public JPanelSimulationStart(WiSimMainController wiSimMainController) {
        this.wiSimMainController = wiSimMainController;
        wiSimLogger = wiSimMainController.getWiSimLogger();
        initDAO(wiSimMainController);
        actDay = 0;
        this.wiSimMainController = wiSimMainController;
        actDate = new Date(new GregorianCalendar(2003, 8, 1, 0, 0).getTimeInMillis());
        hubBestand = 0;
        artikelLagerElemente = new Vector();
        stueckliste = new Hashtable();
        vertraege = new Vector();
        
        actDateGC = new GregorianCalendar();
        lieferDateGC = new GregorianCalendar();
        
        isActive = false;
        
        //Synchronisierte HashSets
        treepathsEinkauf = Collections.synchronizedSet(new HashSet());
        treepathsVertrieb = Collections.synchronizedSet(new HashSet());
        treepathsProduktion = Collections.synchronizedSet(new HashSet());
        
        //Icon Grafiken
        rightIcon = new ImageIcon(getClass().getResource("/icons/right.gif"));
        vanIcon = new ImageIcon(getClass().getResource("/icons/van.gif"));
        boxIcon = new ImageIcon(getClass().getResource("/icons/box.gif"));
        hubIcon = new ImageIcon(getClass().getResource("/icons/hub.gif"));
        boxOpenIcon = new ImageIcon(getClass().getResource("/icons/boxopen.gif"));
        errorIcon = new ImageIcon(getClass().getResource("/icons/error.gif"));
        
        // Initialisierung der IconNodes
        rootNodeLieferungen = new IconNode("");
        rootNodeVertrieb = new IconNode("");
        rootNodeProduktion = new IconNode("");
        rootNodeLieferungen.setIcon(MetalIconFactory.getFileChooserHomeFolderIcon());
        rootNodeVertrieb.setIcon(MetalIconFactory.getFileChooserHomeFolderIcon());
        rootNodeProduktion.setIcon(MetalIconFactory.getFileChooserHomeFolderIcon());
        
        initComponents();
        initializeCollections();
        addJTreeListener();
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
    jLabelSimulation = new javax.swing.JLabel();
    jTabbedPaneSimulationsverlauf = new javax.swing.JTabbedPane();
    jScrollPaneTreeHolderEinkauf = new javax.swing.JScrollPane();
    jTreeEinkauf = new javax.swing.JTree();
    jTreeEinkauf.setModel(null);
    jScrollPaneTreeHolderProduktion = new javax.swing.JScrollPane();
    jTreeProduktion = new javax.swing.JTree();
    jTreeProduktion.setModel(null);
    jScrollPaneTreeHolderVertrieb = new javax.swing.JScrollPane();
    jTreeVertrieb = new javax.swing.JTree();
    jTreeVertrieb.setModel(null);

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

    jLabelSimulation.setFont(new java.awt.Font("Dialog", 1, 24));
    jLabelSimulation.setText("Simulation");
    add(jLabelSimulation);
    jLabelSimulation.setBounds(350, 10, 130, 30);

    jTabbedPaneSimulationsverlauf.setFont(new java.awt.Font("Dialog", 0, 14));
    jTreeEinkauf.setFont(new java.awt.Font("Arial", 0, 14));
    jScrollPaneTreeHolderEinkauf.setViewportView(jTreeEinkauf);

    jTabbedPaneSimulationsverlauf.addTab("                    Einkauf                    ", new javax.swing.ImageIcon(""), jScrollPaneTreeHolderEinkauf);

    jTreeProduktion.setFont(new java.awt.Font("Arial", 0, 14));
    jScrollPaneTreeHolderProduktion.setViewportView(jTreeProduktion);

    jTabbedPaneSimulationsverlauf.addTab("                    Produktion                    ", jScrollPaneTreeHolderProduktion);

    jTreeVertrieb.setFont(new java.awt.Font("Arial", 0, 14));
    jScrollPaneTreeHolderVertrieb.setViewportView(jTreeVertrieb);

    jTabbedPaneSimulationsverlauf.addTab("                       Vetrieb                          ", jScrollPaneTreeHolderVertrieb);

    add(jTabbedPaneSimulationsverlauf);
    jTabbedPaneSimulationsverlauf.setBounds(20, 50, 750, 460);

  }//GEN-END:initComponents
    
    private void formAncestorRemoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorRemoved
        setIsActive(false);
    }//GEN-LAST:event_formAncestorRemoved
    
    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded
        setIsActive(true);
    }//GEN-LAST:event_formAncestorAdded
                    
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel jLabelSimulation;
  private javax.swing.JScrollPane jScrollPaneTreeHolderEinkauf;
  private javax.swing.JScrollPane jScrollPaneTreeHolderProduktion;
  private javax.swing.JScrollPane jScrollPaneTreeHolderVertrieb;
  private javax.swing.JTabbedPane jTabbedPaneSimulationsverlauf;
  private javax.swing.JTree jTreeEinkauf;
  private javax.swing.JTree jTreeProduktion;
  private javax.swing.JTree jTreeVertrieb;
  // End of variables declaration//GEN-END:variables
    
    /** Baut den JTree mit einer Liste aller Lieferungen auf. Fügt die gelieferten
     * Einzelteile außerdem in die Datenbank.
     */
    public void refreshJTreeEinkauf() {
        actDay++;
        Collection etatListe = new Vector();
        
        //Liste aller Einzelteilaufträge
        try {
            etatListe = dao.getEinzelteilauftraege();
        } catch (WiSimDAOException e) {
            wiSimLogger.log("refreshJTreeEinkauf()", e);
        }
        
        actDateGC.setTimeInMillis(actDate.getTime());
        
        //Neuer Node im JTree
        dateNode = new IconNode(actDateGC.get(5) + "." + (actDateGC.get(2) + 1) + "." + actDateGC.get(1));
        dateNode.setIcon(rightIcon);
        
        Iterator etatListe_it = etatListe.iterator();
        while (etatListe_it.hasNext()) {
            ComponentContract etat = (ComponentContract) etatListe_it.next();
            
            Date lieferDate = etat.getLieferdatum();
            lieferDateGC.setTimeInMillis(lieferDate.getTime());
            
            //Der Einzelteilauftrag hat das selbe Datum wie das aktuelle Datum der Simulation
            if (actDateGC.get(5) == lieferDateGC.get(5) && actDateGC.get(2) == lieferDateGC.get(2)) {
                Supplier lt = new Supplier();
                try {
                    lt = dao.getLieferant(etat.getLieferantNr());
                } catch (WiSimDAOException e) {
                    wiSimLogger.log("refreshJTreeEinkauf()", e);
                }
                
                //Neuer Node im JTree
                lieferantNode = new IconNode("Auftrags Nr.: " + etat.getNr() + "    Lieferant: " + lt.getFirma());
                lieferantNode.setIcon(vanIcon);
                
                Collection etatPosis = new Vector();
                try {
                    etatPosis = dao.getEinzelteilAuftragsPositionen(etat.getNr());
                } catch (WiSimDAOException e) {
                    wiSimLogger.log("refreshJTreeEinkauf()", e);
                }
                
                Iterator etatPosis_it = etatPosis.iterator();
                int i = 1;
                while (etatPosis_it.hasNext()) {
                    ComponentContractItem etatPos = (ComponentContractItem) etatPosis_it.next();
                    WiSimComponent et = new WiSimComponent();
                    try {
                        et = dao.getEinzelteil(etatPos.getEtNr());
                    } catch (WiSimDAOException e) {
                        wiSimLogger.log("refreshJTreeEinkauf()", e);
                    }
                    
                    boolean status = runController.einzelteilLieferung(et.getNr(), etatPos.getBestellmenge());
                    
                    //Neuer Node im JTree
                    if (status) {
                        positionNode = new IconNode("Position: " + i + "    Einzelteil: " + et.getName() + "    Menge: " + etatPos.getBestellmenge() + " wurde ins Lager geschafft!");
                        positionNode.setIcon(boxIcon);
                    } else {
                        positionNode = new IconNode("Position: " + i + "    Einzelteil: " + et.getName() + "    Menge: " + etatPos.getBestellmenge() + " überfüllt das Lager!");
                        positionNode.setIcon(errorIcon);
                    }
                    
                    lieferantNode.add(positionNode);
                    i++;
                }
                dateNode.add(lieferantNode);
            }
        }
        rootNodeLieferungen.add(dateNode);
        jTreeEinkauf.setModel(new DefaultTreeModel(rootNodeLieferungen));
        jTreeEinkauf.setCellRenderer(new IconNodeRenderer());
        
        try {
            Iterator tp_it = treepathsEinkauf.iterator();
            while (tp_it.hasNext()) {
                TreePath tp = (TreePath) tp_it.next();
                jTreeEinkauf.expandPath(tp);
            }
        } catch (ConcurrentModificationException c) {}
        
    }
    
    /** Baut den JTree mit einer Liste aller produzierten Hubs und verbrauchten Einzelteile auf. */
    public void refreshJTreeProduktion() {
        if (actDay != 1) {
            actDateGC.setTimeInMillis(actDate.getTime()-24*60*60*1000);
            
            //JTree Produktion Ereignisbaum wird aufgebaut
            //Neuer Node im JTree
            dateNode = new IconNode(actDateGC.get(5) + "." + (actDateGC.get(2) + 1) + "." + actDateGC.get(1));
            dateNode.setIcon(rightIcon);
            
            Vector artikelLagerElemente = new Vector();
            int hubBestandAktuell = 0;
            
            try {
                artikelLagerElemente = dao.getArtikelLagerElement();
            } catch (WiSimDAOException e) {
                wiSimLogger.log("refreshJTreeProduktion()", e);
            }
            
            Iterator artIt = artikelLagerElemente.iterator();
            while (artIt.hasNext()) {
                ComponentWarehouseItem etElem = (ComponentWarehouseItem) artIt.next();
                hubBestandAktuell = hubBestandAktuell + etElem.getBestand();
            }
            
            int hubBestandDifferenz = hubBestandAktuell - hubBestand;
            hubBestand = hubBestandAktuell;
            
            //Neuer Node im JTree
            hubProduktionNode = new IconNode("Heute wurden " + hubBestandDifferenz + " Hubs produziert!");
            hubProduktionNode.setIcon(hubIcon);
            
            //Neues Nodes im JTree
            if (hubBestandDifferenz > 0) {
                Enumeration keys = stueckliste.keys();
                while (keys.hasMoreElements()) {
                    String key = (String) keys.nextElement();
                    WiSimComponent et = new WiSimComponent();
                    
                    try {
                        et = dao.getEinzelteil(Integer.parseInt(key));
                    } catch (WiSimDAOException e) {
                        wiSimLogger.log("refreshJTreeProduktion()", e);
                    }
                    
                    hubEinzelteilVerbrauch = new IconNode("Einzelteil: " + et.getName() + "    Verbrauch: " + (Integer.parseInt((String) stueckliste.get(key)) * hubBestandDifferenz + " Stk."));
                    hubEinzelteilVerbrauch.setIcon(boxOpenIcon);
                    
                    hubProduktionNode.add(hubEinzelteilVerbrauch);
                }
            }
            dateNode.add(hubProduktionNode);
            
            rootNodeProduktion.add(dateNode);
            jTreeProduktion.setModel(new DefaultTreeModel(rootNodeProduktion));
            jTreeProduktion.setCellRenderer(new IconNodeRenderer());
            
            try {
                Iterator tp_it = treepathsProduktion.iterator();
                while (tp_it.hasNext()) {
                    TreePath tp = (TreePath) tp_it.next();
                    jTreeProduktion.expandPath(tp);
                }
            } catch (ConcurrentModificationException c) {}
        } else {
            rootNodeProduktion.add(new IconNode("Es wird produziert...", false, hubIcon));
            jTreeProduktion.setModel(new DefaultTreeModel(rootNodeProduktion));
            jTreeProduktion.setCellRenderer(new IconNodeRenderer());
        }
    }
    
    /** Baut den JTree mit einer Liste aller verkauften Hubs für einen Tag auf. Leert außerdem
     * das Lager je nach Anzahl der verkauften Hubs
     */
    public void refreshJTreeVertrieb() {
        actDateGC.setTimeInMillis(actDate.getTime());
        
        //JTree Vertrieb Ereignisbaum wird aufgebaut
        //Neuer Node im JTree
        dateNode = new IconNode(actDateGC.get(5) + "." + (actDateGC.get(2) + 1) + "." + actDateGC.get(1));
        dateNode.setIcon(rightIcon);
        
        Iterator vertragIt = vertraege.iterator();
        while (vertragIt.hasNext()) {
            Contract vertrag = (Contract) vertragIt.next();
            Date lieferDate = vertrag.getLieferdatum();
            
            lieferDateGC.setTimeInMillis(lieferDate.getTime());
            
            //Der Vertrag hat das selbe Datum wie das aktuelle Datum der Simulation
            if (actDateGC.get(5) == lieferDateGC.get(5) && actDateGC.get(2) == lieferDateGC.get(2)) {
                int menge = 0;
                
                try {
                    menge = dao.getVertragsPositionMenge(vertrag.getVertragsId(), 1);
                } catch (WiSimDAOException e) {
                    wiSimLogger.log("refreshJTreeVertrieb()", e);
                }
                
                //Lagermenge der Hubs wird verringert
                boolean status = false;
                try {
                    status = dao.setArtikelLagerBestand(1, -menge);
                } catch (WiSimDAOWriteException e) {
                    wiSimLogger.log("refreshJTreeVertrieb()", e);
                } catch (WiSimDAOException e) {
                    wiSimLogger.log("refreshJTreeVertrieb()", e);
                }
                
                Customer kunde = new Customer();
                
                try {
                    kunde = dao.getKunde(vertrag.getKundenId());
                } catch (WiSimDAOException e) {
                    wiSimLogger.log("refreshJTreeVertrieb()", e);
                }
                
                //Neuer Node im JTree
                hubVertrieb = new IconNode("Auftrags Nr.: " + vertrag.getVertragsId() + "    Kunde: " + kunde.getFirma() + " (" + kunde.getNachname() + " " + kunde.getVorname() + ")");
                hubVertrieb.setIcon(vanIcon);
                
                //Neuer Node im JTree
                if (status) {
                    hubVertriebPos = new IconNode("Article: Hub   Menge: " + menge + " Stk. wurden an den Kunden geliefert!");
                    hubVertriebPos.setIcon(hubIcon);
                    hubBestand = hubBestand - menge;
                } else {
                    hubVertriebPos = new IconNode("Article: Hub   Menge: " + menge + " Stk. konnte nicht geliefert werden (Lager leer)!");
                    hubVertriebPos.setIcon(errorIcon);
                }
                hubVertrieb.add(hubVertriebPos);
                dateNode.add(hubVertrieb);
            }
        }
        rootNodeVertrieb.add(dateNode);
        jTreeVertrieb.setModel(new DefaultTreeModel(rootNodeVertrieb));
        jTreeVertrieb.setCellRenderer(new IconNodeRenderer());
        
        try {
            Iterator tp_it = treepathsVertrieb.iterator();
            while (tp_it.hasNext()) {
                TreePath tp = (TreePath) tp_it.next();
                jTreeVertrieb.expandPath(tp);
            }
        } catch (ConcurrentModificationException c) {}
    }
    
    /** Erneuert die Datumsanzeige.
     * @param actDate
     */
    public void refreshTextFieldDate(java.util.Date actDate) {
        this.actDate.setTime(actDate.getTime());
        GregorianCalendar actDateGC = new GregorianCalendar();
        actDateGC.setTimeInMillis(actDate.getTime());
    }
    
    /** Setzt das actDate
     * @param actDate
     */
    public void setActDate(Date actDate) {
        this.actDate = actDate;
    }
    
    /** Löscht die JTrees und setzt das Datumsfeld zurück. */
    public void resetFields() {
        actDate = new Date(new GregorianCalendar(2003, 8, 1, 0, 0).getTimeInMillis());
        actDay = 0;
        jTreeEinkauf.setModel(null);
        jTreeVertrieb.setModel(null);
        jTreeProduktion.setModel(null);
        rootNodeLieferungen = new IconNode("");
        rootNodeVertrieb = new IconNode("");
        rootNodeProduktion = new IconNode("");
        rootNodeLieferungen.setIcon(MetalIconFactory.getFileChooserHomeFolderIcon());
        rootNodeVertrieb.setIcon(MetalIconFactory.getFileChooserHomeFolderIcon());
        rootNodeProduktion.setIcon(MetalIconFactory.getFileChooserHomeFolderIcon());
        treepathsEinkauf.clear();
        treepathsProduktion.clear();
        treepathsVertrieb.clear();
        resetDateFields();
    }
    
    /** Setzt das Datum des Menüs zurück */
    public void resetDateFields() {
        JPanelSimulationStart jPanelSimulationStart = (JPanelSimulationStart) wiSimMainController.getActions().get("SimulationStart");
        jPanelSimulationStart.resetTextFieldDate();
        wiSimMainController.resetTextFieldDate();
    }
    
    /** Setzt das Datumsfeld dieses Panes zurück */
    public void resetTextFieldDate() {
        actDate = new Date(new GregorianCalendar(2003, 8, 1, 0, 0).getTimeInMillis());
    }
    
    /** Setzt die Variable isActive auf TRUE oder FALSE
     * @param isActive
     */
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    /** Befindet sich der Benutzer auf diesem Pane, so ist "isActive" = TRUE.
     *  Verlässt der Benutzer das Pane, so ist "isActive" = FALSE.
     *  Wichtig ist diese Variable für die Simulation: Es wird nur das Pane
     *  aktualisiert, auf dem sich der Benutzer gerade befindet!
     * @return boolean
     */
    public boolean getIsActive() {
        return isActive;
    }
    
    /**
     * Datenbankinfos für die Simulation
     */
    public void initializeCollections() {
        try {
            artikelLagerElemente = dao.getArtikelLagerElement();
            stueckliste = dao.getStueckliste(1);
            vertraege = dao.getVertraege();
        } catch (WiSimDAOException e) {
            wiSimLogger.log("JPanelSimulationStart()", e);
        }
        
        Iterator artIt = artikelLagerElemente.iterator();
        while (artIt.hasNext()) {
            ComponentWarehouseItem etElem = (ComponentWarehouseItem) artIt.next();
            hubBestand = hubBestand + etElem.getBestand();
        }
    }
    
    /**
     * Expansion Listeners für die JTrees: Expandierte Nodes werden gespeichert
     * und gehen nach setModel(newModel) nicht verloren!
     */
    public void addJTreeListener() {
        jTreeEinkauf.addTreeExpansionListener(
        new javax.swing.event.TreeExpansionListener() {
            public void treeExpanded(TreeExpansionEvent e) {
                treepathsEinkauf.add(e.getPath());
            }
            
            public void treeCollapsed(TreeExpansionEvent e) {
                treepathsEinkauf.remove(e.getPath());
                Iterator tp_it = treepathsEinkauf.iterator();
                while (tp_it.hasNext()) {
                    TreePath tp = (TreePath) tp_it.next();
                    if (e.getPath().isDescendant(tp)) {
                        treepathsEinkauf.remove(tp);
                    }
                }
            }
        }
        );
        
        jTreeProduktion.addTreeExpansionListener(
        new javax.swing.event.TreeExpansionListener() {
            public void treeExpanded(TreeExpansionEvent e) {
                treepathsProduktion.add(e.getPath());
            }
            
            public void treeCollapsed(TreeExpansionEvent e) {
                treepathsProduktion.remove(e.getPath());
                Iterator tp_it = treepathsProduktion.iterator();
                while (tp_it.hasNext()) {
                    TreePath tp = (TreePath) tp_it.next();
                    if (e.getPath().isDescendant(tp)) {
                        treepathsProduktion.remove(tp);
                    }
                }
            }
        }
        );
        
        jTreeVertrieb.addTreeExpansionListener(
        new javax.swing.event.TreeExpansionListener() {
            public void treeExpanded(TreeExpansionEvent e) {
                treepathsVertrieb.add(e.getPath());
            }
            
            public void treeCollapsed(TreeExpansionEvent e) {
                treepathsVertrieb.remove(e.getPath());
                Iterator tp_it = treepathsVertrieb.iterator();
                while (tp_it.hasNext()) {
                    TreePath tp = (TreePath) tp_it.next();
                    if (e.getPath().isDescendant(tp)) {
                        treepathsVertrieb.remove(tp);
                    }
                }
            }
        }
        );
    }
}