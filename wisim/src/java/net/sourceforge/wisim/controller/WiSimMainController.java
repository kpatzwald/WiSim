/*   ********************************************************************    **
 **   Copyright notice                                                       **
 **                                                                          **
 **   (c) 2003 WiSim Development Team					                               **
 **   http://wisim.sourceforge.net/   			                                 **
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
 * WiSimMainController.java
 *
 * Created on 18. Januar 2003, 18:16
 */

package net.sourceforge.wisim.controller;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import net.sourceforge.wisim.dao.WiSimDAO;
import net.sourceforge.wisim.dao.WiSimDAOException;
import net.sourceforge.wisim.dao.WiSimDAOFactory;
import net.sourceforge.wisim.mdi.JScrollableDesktopPane;
import net.sourceforge.wisim.model.SimulationPane;
import net.sourceforge.wisim.model.WiSimLogger;
import net.sourceforge.wisim.simulation.ActualTime;
import net.sourceforge.wisim.simulation.CoreTime;
import net.sourceforge.wisim.simulation.ProductionController;
import net.sourceforge.wisim.simulation.ProductionSimulationThread;
import net.sourceforge.wisim.simulation.UpdateSimulationAnalysis;
import net.sourceforge.wisim.simulation.UpdateWarehouseThread;

/** Main Class of WiSim - Business Game
 * @author Kay Patzwald
 */
public class WiSimMainController extends javax.swing.JFrame {

	private Hashtable actions;
	private Hashtable titles;
	private WiSimDAO dao;
	private WiSimLogger wiSimLogger;
	private Date actDate;
	private GregorianCalendar actDateGC;
	private DateFormat df;
	private DateFormat justDate;
	private JScrollableDesktopPane desktopPane;
	private ActualTime actTime;
	private CoreTime coreTime;
	private GregorianCalendar gc;
	private boolean simulationState;
	private boolean suspendState;
	private Collection activPanels;

	// Sets the length of one timestep of the simulation
	// 1 Timestep = 1 min = 100 ms
	private final static int TIMESTEP = 100;

	//	Simulation of the production
	private ProductionController runController;
	private ProductionSimulationThread[] threads;

	// Simulation
	private UpdateSimulationAnalysis updateSimulationsauswertung;
	private UpdateWarehouseThread updateLagerThread;

	/** Creates new form WiSimMainController */
	public WiSimMainController() {
		wiSimLogger = new WiSimLogger("wisimlog.xml");
		initDAO();
		initComponents();
		initActions();
		initApplication();
		initTitles();
		initSimulationSettings();

		com.incors.plaf.kunststoff.KunststoffLookAndFeel plaf = new com.incors.plaf.kunststoff.KunststoffLookAndFeel();

		try {
			UIManager.setLookAndFeel(plaf);
		} catch (Exception e) {
		}

		actDate = new Date(new GregorianCalendar(2003, 8, 1, 0, 0).getTimeInMillis());
		actDateGC = new GregorianCalendar();
		df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.SHORT, Locale.GERMANY);
		justDate = DateFormat.getDateInstance(DateFormat.FULL, Locale.GERMANY);
	}

	/** Initializate the settings for the simulation
	 *
	 */
	private void initSimulationSettings() {
		actDate = new Date(new GregorianCalendar(2003, 8, 1, 0, 0).getTimeInMillis());
		gc = new GregorianCalendar();
		simulationState = false;
		suspendState = false;
		activPanels = (Collection) new Vector();
	}

	/*Hashtable with all possible actions. Every action represent a JPanel / JInternalFrame.*/
	private void initActions() {
		actions = new Hashtable();
		actions.put("NewOrder", new JPanelNewOrder(this));
		actions.put("Help", new JPanelHelp());
		actions.put("ModifyCustomer", new JPanelModifyCustomer(this));
		actions.put("Warehouse", new JPanelWarehouse(this));
		actions.put("ModifySupplier", new JPanelModifySupplier(this));
		actions.put("ViewSuppliers", new JPanelViewSuppliers(this));
		actions.put("NewCustomer", new JPanelNewCustomer(this));
		actions.put("ViewCustomers", new JPanelViewCustomers(this));
		actions.put("NewSupplier", new JPanelNewSupplier(this));
		actions.put("NewContract", new JPanelNewContract(this));
		actions.put("ViewContract", new JPanelViewContract(this));
		actions.put("Options", new JPanelOptions(this));
		actions.put("ViewOrders", new JPanelViewOrders(this));
		actions.put("SimulationAnalysis", new JPanelSimulationAnalysis(this));
		actions.put("WorkPlaceStore", new JPanelWorkPlaceStore(this));
		actions.put("Networkplan", new JPanelNetworkplan(this));
		actions.put("IncomingPayment", new JPanelIncomingPayments(this));
	}

	/*Hashtable titles with the german titles of the JInternalFrames.*/
	private void initTitles() {
		titles = new Hashtable();
		titles.put("NewCustomer", "Neuer Kunde");
		titles.put("ModifyCustomer", "Kunde bearbeiten");
		titles.put("ViewCustomers", "Kundenübersicht");
		titles.put("NewContract", "Neuer Vertrag");
		titles.put("ViewContract", "Vertrag einsehen");
		titles.put("Warehouse", "Lager");
		titles.put("Help", "Hilfe");
		titles.put("NewOrder", "Einzelteil bestellen");
		titles.put("ModifySupplier", "Lieferanten bearbeiten");
		titles.put("ViewSuppliers", "Lieferanten einsehen");
		titles.put("NewSupplier", "Neuer Lieferant");
		titles.put("Options", "Optionen");
		titles.put("ViewOrders", "Einzelteilauftrag einsehen");
		titles.put("SimulationAnalysis", "Simulation auswerten");
		titles.put("Networkplan", "Netzplaneditor");
		titles.put("WorkPlaceStore", "Arbeitsplatzlager");
		titles.put("IncomingPayment", "Zahlungseingang");
	}

	/*Initialize the data access object (dao)*/
	private void initDAO() {
		dao = null;
		try {
			WiSimDAOFactory factory = new WiSimDAOFactory();
			dao = factory.getDAO();
		} catch (Throwable t) {
		}
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
  private void initComponents() {//GEN-BEGIN:initComponents
    jPanel1 = new javax.swing.JPanel();
    jLabelDate = new javax.swing.JLabel();
    jPanel2 = new javax.swing.JPanel();
    jButtonSimStart = new javax.swing.JButton();
    jButtonSimStop = new javax.swing.JButton();
    jButtonSimReset = new javax.swing.JButton();
    jLabelFactor = new javax.swing.JLabel();
    jComboBoxFactor = new javax.swing.JComboBox();
    jCheckBoxOneWeek = new javax.swing.JCheckBox();
    jPanelStatus = new javax.swing.JPanel();
    jLabelGreen = new javax.swing.JLabel();
    jLabelYellow = new javax.swing.JLabel();
    jLabelRed = new javax.swing.JLabel();
    jMenuBarWiSim = new javax.swing.JMenuBar();
    jMenuDatei = new javax.swing.JMenu();
    jMenuItemInfo = new javax.swing.JMenuItem();
    jMenuItemOptions = new javax.swing.JMenuItem();
    jSeparator2 = new javax.swing.JSeparator();
    jMenuItemBeenden = new javax.swing.JMenuItem();
    jMenuVertrieb = new javax.swing.JMenu();
    jMenuKunde = new javax.swing.JMenu();
    jMenuItemNeuerKunde = new javax.swing.JMenuItem();
    jMenuItemKundeEditieren = new javax.swing.JMenuItem();
    jMenuItemKundenuebersicht = new javax.swing.JMenuItem();
    jMenuVertrag = new javax.swing.JMenu();
    jMenuItemNeuerVertrag = new javax.swing.JMenuItem();
    jMenuItemVertragEinsehen = new javax.swing.JMenuItem();
    jMenuLager = new javax.swing.JMenu();
    jMenuItemLagerUebersicht = new javax.swing.JMenuItem();
    jMenuProduktion = new javax.swing.JMenu();
    jMenuItemArbeitsplatzlager = new javax.swing.JMenuItem();
    jMenuItemNetzplan = new javax.swing.JMenuItem();
    jMenuEinkauf = new javax.swing.JMenu();
    jMenuEtat = new javax.swing.JMenu();
    jMenuItemEtatEinsehen = new javax.swing.JMenuItem();
    jMenuItemBestellung = new javax.swing.JMenuItem();
    jMenuLieferanten = new javax.swing.JMenu();
    jMenuItemNeuerLieferant = new javax.swing.JMenuItem();
    jMenuItemLieferantenBearbeiten = new javax.swing.JMenuItem();
    jMenuItemLieferantenliste = new javax.swing.JMenuItem();
    jMenuRechnungswesen = new javax.swing.JMenu();
    jMenuItemZahlungseingang = new javax.swing.JMenuItem();
    jMenuSimulation = new javax.swing.JMenu();
    jMenuItemSimulationAnalysis = new javax.swing.JMenuItem();

    setTitle("Wirtschaftssimulation");
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent evt) {
        exitForm(evt);
      }
    });

    jPanel1.setLayout(new java.awt.BorderLayout());

    jPanel1.setMaximumSize(new java.awt.Dimension(881, 28));
    jPanel1.setMinimumSize(new java.awt.Dimension(881, 28));
    jLabelDate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabelDate.setText("Montag, 1. September 2003   ");
    jLabelDate.setPreferredSize(new java.awt.Dimension(250, 25));
    jPanel1.add(jLabelDate, java.awt.BorderLayout.EAST);

    jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.X_AXIS));

    jPanel2.setMaximumSize(new java.awt.Dimension(631, 28));
    jButtonSimStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/run.gif")));
    jButtonSimStart.setText("Start");
    jButtonSimStart.setMaximumSize(new java.awt.Dimension(92, 26));
    jButtonSimStart.setMinimumSize(new java.awt.Dimension(92, 26));
    jButtonSimStart.setPreferredSize(new java.awt.Dimension(92, 26));
    jButtonSimStart.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButtonSimStartActionPerformed(evt);
      }
    });

    jPanel2.add(jButtonSimStart);

    jButtonSimStop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/stop.gif")));
    jButtonSimStop.setText("Stop");
    jButtonSimStop.setMaximumSize(new java.awt.Dimension(92, 26));
    jButtonSimStop.setMinimumSize(new java.awt.Dimension(92, 26));
    jButtonSimStop.setPreferredSize(new java.awt.Dimension(92, 26));
    jButtonSimStop.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButtonSimStopActionPerformed(evt);
      }
    });

    jPanel2.add(jButtonSimStop);

    jButtonSimReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/reset.gif")));
    jButtonSimReset.setText("Reset");
    jButtonSimReset.setMaximumSize(new java.awt.Dimension(92, 26));
    jButtonSimReset.setMinimumSize(new java.awt.Dimension(92, 26));
    jButtonSimReset.setPreferredSize(new java.awt.Dimension(92, 26));
    jButtonSimReset.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButtonSimResetActionPerformed(evt);
      }
    });

    jPanel2.add(jButtonSimReset);

    jLabelFactor.setText("  Zeitfaktor  ");
    jPanel2.add(jLabelFactor);

    jComboBoxFactor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1x", "1/2x", "1/4x", "1/8x", "1/16x" }));
    jComboBoxFactor.setMaximumSize(new java.awt.Dimension(59, 25));
    jPanel2.add(jComboBoxFactor);

    jCheckBoxOneWeek.setText("Beende nach 1 Woche");
    jPanel2.add(jCheckBoxOneWeek);

    jLabelGreen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ampel_aus.gif")));
    jLabelGreen.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
    jPanelStatus.add(jLabelGreen);

    jLabelYellow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ampel_aus.gif")));
    jLabelYellow.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
    jPanelStatus.add(jLabelYellow);

    jLabelRed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ampel_aus.gif")));
    jLabelRed.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
    jPanelStatus.add(jLabelRed);

    jPanel2.add(jPanelStatus);

    jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

    getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

    jMenuBarWiSim.setMaximumSize(new java.awt.Dimension(0, 24));
    jMenuBarWiSim.setMinimumSize(new java.awt.Dimension(0, 24));
    jMenuBarWiSim.setPreferredSize(new java.awt.Dimension(0, 24));
    jMenuDatei.setMnemonic('D');
    jMenuDatei.setText("Datei");
    jMenuItemInfo.setMnemonic('I');
    jMenuItemInfo.setText("Info");
    jMenuItemInfo.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jMenuItemInfoActionPerformed(evt);
      }
    });

    jMenuDatei.add(jMenuItemInfo);

    jMenuItemOptions.setMnemonic('O');
    jMenuItemOptions.setText("Options");
    jMenuItemOptions.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jMenuItemOptionsActionPerformed(evt);
      }
    });

    jMenuDatei.add(jMenuItemOptions);

    jMenuDatei.add(jSeparator2);

    jMenuItemBeenden.setMnemonic('B');
    jMenuItemBeenden.setText("Beenden");
    jMenuItemBeenden.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jMenuItemBeendenActionPerformed(evt);
      }
    });

    jMenuDatei.add(jMenuItemBeenden);

    jMenuBarWiSim.add(jMenuDatei);

    jMenuVertrieb.setMnemonic('V');
    jMenuVertrieb.setText("Vertrieb");
    jMenuKunde.setMnemonic('K');
    jMenuKunde.setText("Kunde");
    jMenuItemNeuerKunde.setMnemonic('N');
    jMenuItemNeuerKunde.setText("Neu");
    jMenuItemNeuerKunde.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jMenuItemNeuerKundeActionPerformed(evt);
      }
    });

    jMenuKunde.add(jMenuItemNeuerKunde);

    jMenuItemKundeEditieren.setMnemonic('B');
    jMenuItemKundeEditieren.setText("Bearbeiten");
    jMenuItemKundeEditieren.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jMenuItemKundeEditierenActionPerformed(evt);
      }
    });

    jMenuKunde.add(jMenuItemKundeEditieren);

    jMenuItemKundenuebersicht.setMnemonic('u');
    jMenuItemKundenuebersicht.setText("Kundenuebersicht");
    jMenuItemKundenuebersicht.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jMenuItemKundenuebersichtActionPerformed(evt);
      }
    });

    jMenuKunde.add(jMenuItemKundenuebersicht);

    jMenuVertrieb.add(jMenuKunde);

    jMenuVertrag.setMnemonic('V');
    jMenuVertrag.setText("Vertrag");
    jMenuItemNeuerVertrag.setMnemonic('N');
    jMenuItemNeuerVertrag.setText("Neu");
    jMenuItemNeuerVertrag.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jMenuItemNeuerVertragActionPerformed(evt);
      }
    });

    jMenuVertrag.add(jMenuItemNeuerVertrag);

    jMenuItemVertragEinsehen.setMnemonic('E');
    jMenuItemVertragEinsehen.setText("Einsehen");
    jMenuItemVertragEinsehen.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jMenuItemVertragEinsehenActionPerformed(evt);
      }
    });

    jMenuVertrag.add(jMenuItemVertragEinsehen);

    jMenuVertrieb.add(jMenuVertrag);

    jMenuBarWiSim.add(jMenuVertrieb);

    jMenuLager.setMnemonic('L');
    jMenuLager.setText("Lager");
    jMenuItemLagerUebersicht.setMnemonic('L');
    jMenuItemLagerUebersicht.setText("Lager\u00fcbersicht");
    jMenuItemLagerUebersicht.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jMenuItemLagerUebersichtActionPerformed(evt);
      }
    });

    jMenuLager.add(jMenuItemLagerUebersicht);

    jMenuBarWiSim.add(jMenuLager);

    jMenuProduktion.setMnemonic('P');
    jMenuProduktion.setText("Produktion");
    jMenuItemArbeitsplatzlager.setMnemonic('A');
    jMenuItemArbeitsplatzlager.setText("Arbeitsplatzlager");
    jMenuItemArbeitsplatzlager.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jMenuItemArbeitsplatzlagerActionPerformed(evt);
      }
    });

    jMenuProduktion.add(jMenuItemArbeitsplatzlager);

    jMenuItemNetzplan.setMnemonic('A');
    jMenuItemNetzplan.setText("Netzplan");
    jMenuItemNetzplan.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jMenuItemNetzplanActionPerformed(evt);
      }
    });

    jMenuProduktion.add(jMenuItemNetzplan);

    jMenuBarWiSim.add(jMenuProduktion);

    jMenuEinkauf.setMnemonic('E');
    jMenuEinkauf.setText("Einkauf");
    jMenuEtat.setMnemonic('E');
    jMenuEtat.setText("Ersatzteil Auftrag");
    jMenuItemEtatEinsehen.setMnemonic('A');
    jMenuItemEtatEinsehen.setText("Auftr\u00e4ge einsehen");
    jMenuItemEtatEinsehen.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jMenuItemEtatEinsehenActionPerformed(evt);
      }
    });

    jMenuEtat.add(jMenuItemEtatEinsehen);

    jMenuItemBestellung.setMnemonic('E');
    jMenuItemBestellung.setText("Ersatzteile bestellen");
    jMenuItemBestellung.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jMenuItemBestellungActionPerformed(evt);
      }
    });

    jMenuEtat.add(jMenuItemBestellung);

    jMenuEinkauf.add(jMenuEtat);

    jMenuLieferanten.setMnemonic('L');
    jMenuLieferanten.setText("Lieferanten");
    jMenuItemNeuerLieferant.setMnemonic('N');
    jMenuItemNeuerLieferant.setText("Neu");
    jMenuItemNeuerLieferant.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jMenuItemNeuerLieferantActionPerformed(evt);
      }
    });

    jMenuLieferanten.add(jMenuItemNeuerLieferant);

    jMenuItemLieferantenBearbeiten.setMnemonic('B');
    jMenuItemLieferantenBearbeiten.setText("Bearbeiten");
    jMenuItemLieferantenBearbeiten.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jMenuItemLieferantenBearbeitenActionPerformed(evt);
      }
    });

    jMenuLieferanten.add(jMenuItemLieferantenBearbeiten);

    jMenuItemLieferantenliste.setMnemonic('L');
    jMenuItemLieferantenliste.setText("Lieferantenliste");
    jMenuItemLieferantenliste.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jMenuItemLieferantenlisteActionPerformed(evt);
      }
    });

    jMenuLieferanten.add(jMenuItemLieferantenliste);

    jMenuEinkauf.add(jMenuLieferanten);

    jMenuBarWiSim.add(jMenuEinkauf);

    jMenuRechnungswesen.setMnemonic('R');
    jMenuRechnungswesen.setText("Rechnungswesen");
    jMenuItemZahlungseingang.setMnemonic('Z');
    jMenuItemZahlungseingang.setText("Zahlungseingang");
    jMenuItemZahlungseingang.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jMenuItemZahlungseingangActionPerformed(evt);
      }
    });

    jMenuRechnungswesen.add(jMenuItemZahlungseingang);

    jMenuBarWiSim.add(jMenuRechnungswesen);

    jMenuSimulation.setMnemonic('S');
    jMenuSimulation.setText("Simulation");
    jMenuItemSimulationAnalysis.setMnemonic('S');
    jMenuItemSimulationAnalysis.setText("Auswertung");
    jMenuItemSimulationAnalysis.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jMenuItemSimulationAnalysisActionPerformed(evt);
      }
    });

    jMenuSimulation.add(jMenuItemSimulationAnalysis);

    jMenuBarWiSim.add(jMenuSimulation);

    setJMenuBar(jMenuBarWiSim);

    java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    setBounds((screenSize.width-873)/2, (screenSize.height-600)/2, 873, 600);
  }//GEN-END:initComponents

	private void jMenuItemSimulationAnalysisActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuItemSimulationAnalysisActionPerformed
		addPanel("SimulationAnalysis");
	} //GEN-LAST:event_jMenuItemSimulationAnalysisActionPerformed

	private void jMenuItemNetzplanActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuItemNetzplanActionPerformed
		addPanel("Networkplan");
	} //GEN-LAST:event_jMenuItemNetzplanActionPerformed

	private void jButtonSimStartActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jButtonSimStartActionPerformed
		simulationController();
	} //GEN-LAST:event_jButtonSimStartActionPerformed

	private void jButtonSimResetActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jButtonSimResetActionPerformed
		resetSimulation();
	} //GEN-LAST:event_jButtonSimResetActionPerformed

	private void jButtonSimStopActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jButtonSimStopActionPerformed
		stopSimulation();
	} //GEN-LAST:event_jButtonSimStopActionPerformed

	private void jMenuItemZahlungseingangActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuItemZahlungseingangActionPerformed
		addPanel("IncomingPayment");
	} //GEN-LAST:event_jMenuItemZahlungseingangActionPerformed

	private void jMenuItemKundenuebersichtActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuItemKundenuebersichtActionPerformed
		addPanel("ViewCustomers");
	} //GEN-LAST:event_jMenuItemKundenuebersichtActionPerformed

	private void jMenuItemArbeitsplatzlagerActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuItemArbeitsplatzlagerActionPerformed
		addPanel("WorkPlaceStore");
	} //GEN-LAST:event_jMenuItemArbeitsplatzlagerActionPerformed

	private void jMenuItemLieferantenlisteActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuItemLieferantenlisteActionPerformed
		addPanel("ViewSuppliers");
	} //GEN-LAST:event_jMenuItemLieferantenlisteActionPerformed

	private void jMenuItemEtatEinsehenActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuItemEtatEinsehenActionPerformed
		addPanel("ViewEtat");
	} //GEN-LAST:event_jMenuItemEtatEinsehenActionPerformed

	private void jMenuItemOptionsActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuItemOptionsActionPerformed
		addPanel("Options");
	} //GEN-LAST:event_jMenuItemOptionsActionPerformed

	private void jMenuItemBeendenActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuItemBeendenActionPerformed
		System.exit(0);
	} //GEN-LAST:event_jMenuItemBeendenActionPerformed

	private void jMenuItemLieferantenBearbeitenActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuItemLieferantenBearbeitenActionPerformed
		addPanel("ModifySupplier");
	} //GEN-LAST:event_jMenuItemLieferantenBearbeitenActionPerformed

	private void jMenuItemVertragEinsehenActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuItemVertragEinsehenActionPerformed
		addPanel("ViewContract");
	} //GEN-LAST:event_jMenuItemVertragEinsehenActionPerformed

	private void jMenuItemNeuerLieferantActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuItemNeuerLieferantActionPerformed
		addPanel("NewSupplier");
	} //GEN-LAST:event_jMenuItemNeuerLieferantActionPerformed

	private void jMenuItemLagerUebersichtActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuItemLagerUebersichtActionPerformed
		addPanel("Warehouse");
	} //GEN-LAST:event_jMenuItemLagerUebersichtActionPerformed

	private void jMenuItemBestellungActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuItemBestellungActionPerformed
		addPanel("NewOrder");
	} //GEN-LAST:event_jMenuItemBestellungActionPerformed

	private void jMenuItemNeuerVertragActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuItemNeuerVertragActionPerformed
		addPanel("NewContract");
	} //GEN-LAST:event_jMenuItemNeuerVertragActionPerformed

	private void jMenuItemInfoActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuItemInfoActionPerformed
		addPanel("Help");
	} //GEN-LAST:event_jMenuItemInfoActionPerformed

	private void jMenuItemNeuerKundeActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuItemNeuerKundeActionPerformed
		addPanel("NewCustomer");
	} //GEN-LAST:event_jMenuItemNeuerKundeActionPerformed

	private void jMenuItemKundeEditierenActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuItemKundeEditierenActionPerformed
		addPanel("ModifyCustomer");
	} //GEN-LAST:event_jMenuItemKundeEditierenActionPerformed

	/** Exit the Application */
	private void exitForm(java.awt.event.WindowEvent evt) { //GEN-FIRST:event_exitForm
		System.exit(0);
	} //GEN-LAST:event_exitForm

	/*
	 * Sets after NetBeans generated code properties
	 */
	private void initApplication() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		setSize(ge.getMaximumWindowBounds().width, ge.getMaximumWindowBounds().height);
		setLocation(0, 0);
		desktopPane = new JScrollableDesktopPane(jMenuBarWiSim);
		getContentPane().add(desktopPane, BorderLayout.CENTER);
	}

	/** Adds a new JInternalFrame to the ScrollableDesktopPane
	 * @param String Name of the panel, representing the JInternalFrame-Content
	 */
	public void addPanel(String panelName) {
		//getContentPane().removeAll();

		//Update Plugable look and feel
		SwingUtilities.updateComponentTreeUI((JPanel) actions.get(panelName));
		SwingUtilities.updateComponentTreeUI(jMenuBarWiSim);

		//		getContentPane().add((JPanel) actions.get(panel), java.awt.BorderLayout.CENTER);
		//		getContentPane().validate();
		//		getContentPane().repaint();

		JInternalFrame frames[] = desktopPane.getAllFrames();
		boolean isOpen = false;
		int frameIndex = -1;
		for (int i = 0; i < frames.length; i++) {
			if (frames[i].getTitle().equals((String) titles.get(panelName))) {
				isOpen = true;
				frameIndex = i;
			}
		}
		if (isOpen) {
			desktopPane.setSelectedFrame(frames[frameIndex]);
		} else {
			desktopPane.add((String) titles.get(panelName), (JPanel) actions.get(panelName));
		}
	}

	/** Returns the WiSim-DAO
	 * @return WiSimDao
	 */
	public WiSimDAO getDAO() {
		return dao;
	}

	/** Returns the hashtable actions
	 * @return Hashtable
	 */
	public Hashtable getActions() {
		return actions;
	}

	/** Returns the logger
	 * @return WiSimLogger
	 */
	public WiSimLogger getWiSimLogger() {
		return wiSimLogger;
	}

	/** Main function of WiSim - Business Game
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		new WiSimMainController().show();
	}

	/** Setzt das Datumsfeld dieses Panes zurück */
	public void resetTextFieldDate() {
		actDate = new Date(new GregorianCalendar(2003, 8, 1, 0, 0).getTimeInMillis());
		jLabelDate.setText(justDate.format(actDate) + "   ");
	}

	/** Actualice the simulation time
	 * @param actDate The simulation time to set
	 */
	public void refreshTextFieldDate(java.util.Date actDate) {
		this.actDate = actDate;
		actDateGC.setTimeInMillis(actDate.getTime());
		jLabelDate.setText(df.format(actDate) + "   ");
	}

	/** Returns the simulation time
	 * @return Date The simulation time
	 */
	public Date getActDate() {
		return actDate;
	}

	/** Resets the date */
	private void resetFields() {
		actDate = new Date(new GregorianCalendar(2003, 8, 1, 0, 0).getTimeInMillis());
		resetTextFieldDate();
	}

	private void simulationController() {
		if (simulationState == false)
			startSimulation();
		else if (suspendState)
			resumeSimulation();
		else
			suspendSimulation();

	}

	/** Starts the simulation if jToggledButtonWiSimStart is pressed
	 *
	 */
	private void startSimulation() {
		setGreenTrafficLights("Produktion läuft");
		jButtonSimStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/suspend.gif")));
		jButtonSimStart.setText("Pause");
		JPanelOptions jPanelOptions = (JPanelOptions) actions.get("Options");
		jPanelOptions.setJButtonRefreshEnable(false);
		jComboBoxFactor.setEnabled(false);
		jCheckBoxOneWeek.setEnabled(false);

		/** Reset the jPanelSimulationAnalysis */
		 ((JPanelSimulationAnalysis) actions.get("SimulationAnalysis")).resetFields();

		resetFields();
		//jComboBoxZeitfaktor.setEnabled(false);
		jButtonSimReset.setEnabled(false);
		//jCheckBoxEineWoche.setEnabled(false);
		actTime = new ActualTime();
		int faktor = jComboBoxFactor.getSelectedIndex() + 1;
		coreTime = new CoreTime(actTime, faktor, TIMESTEP);
		gc.setTime(actDate);
		boolean beendeNachEinerWoche = false;
		if (jCheckBoxOneWeek.getSelectedObjects() != null && jCheckBoxOneWeek.getSelectedObjects().length > 0)
			beendeNachEinerWoche = true;
		else
			beendeNachEinerWoche = false;
		updateSimulationsauswertung = new UpdateSimulationAnalysis(actTime, this, beendeNachEinerWoche);
		updateLagerThread = new UpdateWarehouseThread(this);

		coreTime.start();
		updateSimulationsauswertung.start();
		updateLagerThread.start();

		// Simulation of the production
		int anzahlArbeitsplaetze = -1;
		try {
			anzahlArbeitsplaetze = dao.getAnzahlArbeitsplaetze();
		} catch (WiSimDAOException e) {
			wiSimLogger.log("startSimulation()", e);
		}

		runController = new ProductionController(this);

		/** Set the runController in the Analys of the Production */
		 ((JPanelSimulationAnalysis) actions.get("SimulationAnalysis")).setRunController(runController);

		try {
			threads = new ProductionSimulationThread[anzahlArbeitsplaetze + 1];
			for (int i = 1; i <= anzahlArbeitsplaetze; i++) {

				threads[i] = new ProductionSimulationThread(dao.getArbeitsplatz(i), runController, this, faktor, TIMESTEP);
				threads[i].start();

			}
		} catch (WiSimDAOException e) {
			wiSimLogger.log("startSimulation()", e);
		}
		// End of simulation of the production

		simulationState = true;
		suspendState = false;
	}

	/** Suspends the simulation */
	private void suspendSimulation() {
		if (simulationState) {
			jButtonSimStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/resume.gif")));
			jButtonSimStart.setText("Weiter");

			coreTime.interrupt();
			updateSimulationsauswertung.interrupt();
			updateLagerThread.interrupt();
			int anzahlArbeitsplaetze = -1;
			try {
				anzahlArbeitsplaetze = dao.getAnzahlArbeitsplaetze();
			} catch (WiSimDAOException e) {
				wiSimLogger.log("startStopSimulation()", e);
			}

			// Stops the simulation of the produktion
			for (int i = 1; i <= anzahlArbeitsplaetze; i++) {
				threads[i].interrupt();
			}
			// End of stoping the simulation of the produktion

			simulationState = true;
			suspendState = true;
		}
	}

	/** Resumes the simulation */
	private void resumeSimulation() {
		jButtonSimStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/suspend.gif")));
		jButtonSimStart.setText("Pause");

		//jComboBoxZeitfaktor.setEnabled(false);
		jButtonSimReset.setEnabled(false);
		//jCheckBoxEineWoche.setEnabled(false);
		int faktor = jComboBoxFactor.getSelectedIndex() + 1;
		coreTime = new CoreTime(actTime, faktor, TIMESTEP);
		gc.setTime(actDate);
		boolean beendeNachEinerWoche = false;
		if (jCheckBoxOneWeek.getSelectedObjects() != null && jCheckBoxOneWeek.getSelectedObjects().length > 0)
			beendeNachEinerWoche = true;
		else
			beendeNachEinerWoche = false;
		updateSimulationsauswertung = new UpdateSimulationAnalysis(actTime, this, beendeNachEinerWoche);
		updateLagerThread = new UpdateWarehouseThread(this);

		coreTime.start();
		updateSimulationsauswertung.start();
		updateLagerThread.start();

		// Simulation of the production
		int anzahlArbeitsplaetze = -1;
		try {
			anzahlArbeitsplaetze = dao.getAnzahlArbeitsplaetze();
		} catch (WiSimDAOException e) {
			wiSimLogger.log("startSimulation()", e);
		}

		runController = new ProductionController(this);
		try {
			threads = new ProductionSimulationThread[anzahlArbeitsplaetze + 1];
			for (int i = 1; i <= anzahlArbeitsplaetze; i++) {

				threads[i] = new ProductionSimulationThread(dao.getArbeitsplatz(i), runController, this, faktor, TIMESTEP);
				threads[i].start();

			}
		} catch (WiSimDAOException e) {
			wiSimLogger.log("startSimulation()", e);
		}
		// End of simulation of the production

		simulationState = true;
		suspendState = false;
	}

	/** Stops the simulation
	 *
	 */
	public void stopSimulation() {
		setTrafficLightsOff();
		jButtonSimStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/run.gif")));
		jButtonSimStart.setText("Start");
		jButtonSimStart.setSelected(false);
		jButtonSimReset.setEnabled(true);
		JPanelOptions jPanelOptions = (JPanelOptions) actions.get("Options");
		jPanelOptions.setJButtonRefreshEnable(true);
		jComboBoxFactor.setEnabled(true);
		jCheckBoxOneWeek.setEnabled(true);

		if (simulationState) {
			coreTime.interrupt();
			updateSimulationsauswertung.interrupt();
			updateLagerThread.interrupt();
			int anzahlArbeitsplaetze = -1;
			try {
				anzahlArbeitsplaetze = dao.getAnzahlArbeitsplaetze();
			} catch (WiSimDAOException e) {
				wiSimLogger.log("startStopSimulation()", e);
			}

			// Stops the simulation of the produktion
			for (int i = 1; i <= anzahlArbeitsplaetze; i++) {
				threads[i].interrupt();
			}
			// End of stoping the simulation of the produktion
			simulationState = false;
			suspendState = false;
		}
	}

	/** Resets the simulation */
	private void resetSimulation() {
		setTrafficLightsOff();
		WiSimDAO dao = this.getDAO();
		try {
			dao.dbReset();
			resetFields();
		} catch (WiSimDAOException e) {
			wiSimLogger.log(Level.WARNING, "resetDB()", e, false);
		}

		Iterator it = activPanels.iterator();
		while (it.hasNext()) {
			SimulationPane simPane = (SimulationPane) it.next();
			simPane.refresh();
		}
		JOptionPane.showMessageDialog(this, "Die Simulation wurde zurückgesetzt!");
	}

	/**
	 * @return
	 */
	public Collection getActivPanels() {
		return activPanels;
	}

	/**
	 * @param activPanel
	 */
	public void addActivPanel(JPanel activPanel) {
		this.activPanels.add(activPanel);
	}

	public void removeActivPanel(JPanel activPanel) {
		activPanels.remove(activPanel);
	}

	public void setGreenTrafficLights(String status) {
		jLabelGreen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ampel_gruen.gif")));
		jLabelYellow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ampel_aus.gif")));
		jLabelRed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ampel_aus.gif")));

		jLabelGreen.setToolTipText(status);
		jLabelYellow.setToolTipText("");
		jLabelRed.setToolTipText("");
	}

	public void setYellowTrafficLights(String status) {
		jLabelGreen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ampel_gruen.gif")));
		jLabelYellow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ampel_gelb.gif")));
		jLabelRed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ampel_aus.gif")));

		jLabelGreen.setToolTipText("");
		jLabelYellow.setToolTipText(status);
		jLabelRed.setToolTipText("");

	}

	public void setRedTrafficLights(String status) {
		jLabelGreen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ampel_aus.gif")));
		jLabelYellow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ampel_aus.gif")));
		jLabelRed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ampel_rot.gif")));

		jLabelGreen.setToolTipText("");
		jLabelYellow.setToolTipText("");
		jLabelRed.setToolTipText(status);
	}

	public void setTrafficLightsOff() {
		jLabelGreen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ampel_aus.gif")));
		jLabelYellow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ampel_aus.gif")));
		jLabelRed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ampel_aus.gif")));

		jLabelGreen.setToolTipText("");
		jLabelYellow.setToolTipText("");
		jLabelRed.setToolTipText("");
	}

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButtonSimReset;
  private javax.swing.JButton jButtonSimStart;
  private javax.swing.JButton jButtonSimStop;
  private javax.swing.JCheckBox jCheckBoxOneWeek;
  private javax.swing.JComboBox jComboBoxFactor;
  private javax.swing.JLabel jLabelDate;
  private javax.swing.JLabel jLabelFactor;
  private javax.swing.JLabel jLabelGreen;
  private javax.swing.JLabel jLabelRed;
  private javax.swing.JLabel jLabelYellow;
  private javax.swing.JMenuBar jMenuBarWiSim;
  private javax.swing.JMenu jMenuDatei;
  private javax.swing.JMenu jMenuEinkauf;
  private javax.swing.JMenu jMenuEtat;
  private javax.swing.JMenuItem jMenuItemArbeitsplatzlager;
  private javax.swing.JMenuItem jMenuItemBeenden;
  private javax.swing.JMenuItem jMenuItemBestellung;
  private javax.swing.JMenuItem jMenuItemEtatEinsehen;
  private javax.swing.JMenuItem jMenuItemInfo;
  private javax.swing.JMenuItem jMenuItemKundeEditieren;
  private javax.swing.JMenuItem jMenuItemKundenuebersicht;
  private javax.swing.JMenuItem jMenuItemLagerUebersicht;
  private javax.swing.JMenuItem jMenuItemLieferantenBearbeiten;
  private javax.swing.JMenuItem jMenuItemLieferantenliste;
  private javax.swing.JMenuItem jMenuItemNetzplan;
  private javax.swing.JMenuItem jMenuItemNeuerKunde;
  private javax.swing.JMenuItem jMenuItemNeuerLieferant;
  private javax.swing.JMenuItem jMenuItemNeuerVertrag;
  private javax.swing.JMenuItem jMenuItemOptions;
  private javax.swing.JMenuItem jMenuItemSimulationAnalysis;
  private javax.swing.JMenuItem jMenuItemVertragEinsehen;
  private javax.swing.JMenuItem jMenuItemZahlungseingang;
  private javax.swing.JMenu jMenuKunde;
  private javax.swing.JMenu jMenuLager;
  private javax.swing.JMenu jMenuLieferanten;
  private javax.swing.JMenu jMenuProduktion;
  private javax.swing.JMenu jMenuRechnungswesen;
  private javax.swing.JMenu jMenuSimulation;
  private javax.swing.JMenu jMenuVertrag;
  private javax.swing.JMenu jMenuVertrieb;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JPanel jPanelStatus;
  private javax.swing.JSeparator jSeparator2;
  // End of variables declaration//GEN-END:variables

}
