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
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Locale;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import net.sourceforge.wisim.dao.WiSimDAO;
import net.sourceforge.wisim.dao.WiSimDAOException;
import net.sourceforge.wisim.dao.WiSimDAOFactory;
import net.sourceforge.wisim.mdi.JScrollableDesktopPane;

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
	}

	/*Hashtable with all possible actions. Every action represent a JPanel / JInternalFrame.*/
	private void initActions() {
		actions = new Hashtable();
		actions.put("Order", new JPanelOrder(this));
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
		actions.put("ViewEtat", new JPanelViewEtat(this));
		actions.put("SimulationStart", new JPanelSimulationStart(this));
		actions.put("WorkPlaceStore", new JPanelWorkPlaceStore(this));
		actions.put("Networkplan", new JPanelNetworkplan(this));
		actions.put("IncomingPayment", new JPanelIncomingPayments(this));
	}

	/*Hashtable titles with the german titles of the JInternalFrames.*/
	private void initTitles() {
		titles = new Hashtable();
		titles.put("NewCustomer", "Neuer Kunde");
		titles.put("ModifyCustomer", "Kunde bearbeiten");
		titles.put("ViewCustomers", "Kunden�bersicht");
		titles.put("NewContract", "Neuer Vertrag");
		titles.put("ViewContract", "Vertrag einsehen");
		titles.put("Warehouse", "Lager");
		titles.put("Help", "Hilfe");
		titles.put("Order", "Einzelteil bestellen");
		titles.put("ModifySupplier", "Lieferanten bearbeiten");
		titles.put("ViewSuppliers", "Lieferanten einsehen");
		titles.put("NewSupplier", "Neuer Lieferant");
		titles.put("Options", "Optionen");
		titles.put("ViewEtat", "Einzelteilauftrag einsehen");
		titles.put("SimulationStart", "Simulation");
		titles.put("Networkplan", "Netzplan zeigen");
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
        jMenuItemSimStart = new javax.swing.JMenuItem();

        setTitle("Wirtschaftssimulation");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabelDate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelDate.setText("Montag, 1. September 2003 ");
        jLabelDate.setPreferredSize(new java.awt.Dimension(250, 25));
        jPanel1.add(jLabelDate, java.awt.BorderLayout.EAST);

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.X_AXIS));

        jButtonSimStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/run.gif")));
        jButtonSimStart.setText("Start");
        jButtonSimStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSimStartActionPerformed(evt);
            }
        });

        jPanel2.add(jButtonSimStart);

        jButtonSimStop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/stop.gif")));
        jButtonSimStop.setText("Stop");
        jButtonSimStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSimStopActionPerformed(evt);
            }
        });

        jPanel2.add(jButtonSimStop);

        jButtonSimReset.setText("Reset");
        jButtonSimReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSimResetActionPerformed(evt);
            }
        });

        jPanel2.add(jButtonSimReset);

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

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
        jMenuItemNetzplan.setText("Netzplan zeigen");
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
        jMenuItemSimStart.setMnemonic('S');
        jMenuItemSimStart.setText("Start / Stop");
        jMenuItemSimStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSimStartActionPerformed(evt);
            }
        });

        jMenuSimulation.add(jMenuItemSimStart);

        jMenuBarWiSim.add(jMenuSimulation);

        setJMenuBar(jMenuBarWiSim);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-800)/2, (screenSize.height-600)/2, 800, 600);
    }//GEN-END:initComponents

  private void jMenuItemNetzplanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNetzplanActionPerformed
		addPanel("Networkplan");
  }//GEN-LAST:event_jMenuItemNetzplanActionPerformed

	private void jButtonSimStartActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jButtonSimStartActionPerformed
		if (simulationState == false)
			startSimulation();
		else
			suspendSimulation();
	} //GEN-LAST:event_jButtonSimStartActionPerformed

	private void jButtonSimResetActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jButtonSimResetActionPerformed
		// Add your handling code here:
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

	private void jMenuItemSimStartActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuItemSimStartActionPerformed
		addPanel("SimulationStart");
	} //GEN-LAST:event_jMenuItemSimStartActionPerformed

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
		addPanel("Order");
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

	/** Setzt das Datumsfeld dieses Panes zur�ck */
	public void resetTextFieldDate() {
		actDate = new Date(new GregorianCalendar(2003, 8, 1, 0, 0).getTimeInMillis());
		jLabelDate.setText(justDate.format(actDate));
	}

	/** Actualice the simulation time
	 * @param actDate The simulation time to set
	 */
	public void refreshTextFieldDate(java.util.Date actDate) {
		this.actDate = actDate;
		actDateGC.setTimeInMillis(actDate.getTime());
		jLabelDate.setText(df.format(actDate) + " ");
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

	/** Starts the simulation if jToggledButtonWiSimStart is pressed
	 *
	 */
	private void startSimulation() {
		jButtonSimStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/suspend.gif")));
		jButtonSimStart.setText("Pause");
		JPanelOptions jPanelOptions = (JPanelOptions) actions.get("Options");
		jPanelOptions.setJButtonRefreshEnable(false);

		resetFields();
		//jComboBoxZeitfaktor.setEnabled(false);
		jButtonSimReset.setEnabled(false);
		//jCheckBoxEineWoche.setEnabled(false);
		actTime = new ActualTime();
		int faktor = 1; //KTODO Provisorisch, solange der Faktor nicht �ber ein Swing-Element vom Benutzer gew�hlt werden kann.
		coreTime = new CoreTime(actTime, faktor, TIMESTEP);
		gc.setTime(actDate);
		boolean beendeNachEinerWoche = false; //KTODO Provisorisch, solange der Wert nicht �ber ein Swing-Element vom Benutzer gew�hlt werden kann.
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
			wiSimLogger.log("startStopSimulation()", e);
		}

		runController = new ProductionController(this);
		try {
			threads = new ProductionSimulationThread[anzahlArbeitsplaetze + 1];
			for (int i = 1; i <= anzahlArbeitsplaetze; i++) {

				threads[i] = new ProductionSimulationThread(dao.getArbeitsplatz(i), runController, this, faktor, TIMESTEP);
				threads[i].start();

			}
		} catch (WiSimDAOException e) {
			wiSimLogger.log("startStopSimulation()", e);
		}
		// End of simulation of the production

		simulationState = true;
	}

	private void suspendSimulation() {
		// KTODO Provisorische suspendSimulation-Funktion Entspricht noch stopSimulation
		// KTODO Buttons zur Simulationssteuerung d�rfen nicht ihre Gr��e �ndern.
		if (simulationState) {
			jButtonSimStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/resume.gif")));
			jButtonSimStart.setText("Weiter");
			jButtonSimStart.setSelected(false);
			jButtonSimReset.setEnabled(true);
			JPanelOptions jPanelOptions = (JPanelOptions) actions.get("Options");
			jPanelOptions.setJButtonRefreshEnable(true);

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
		}
	}

	/** Stops the simulation
	 *
	 */
	private void stopSimulation() {
		jButtonSimStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/run.gif")));
		jButtonSimStart.setText("Start");
		jButtonSimStart.setSelected(false);
		jButtonSimReset.setEnabled(true);
		JPanelOptions jPanelOptions = (JPanelOptions) actions.get("Options");
		jPanelOptions.setJButtonRefreshEnable(true);
		
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
		}
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSimReset;
    private javax.swing.JButton jButtonSimStart;
    private javax.swing.JButton jButtonSimStop;
    private javax.swing.JLabel jLabelDate;
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
    private javax.swing.JMenuItem jMenuItemSimStart;
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
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables

}
