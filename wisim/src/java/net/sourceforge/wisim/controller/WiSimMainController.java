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

import javax.swing.ButtonModel;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import net.sourceforge.wisim.dao.WiSimDAO;
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

	/** Creates new form WiSimMainController */
	public WiSimMainController() {
		wiSimLogger = new WiSimLogger("wisimlog.xml");
		initDAO();
		initComponents();
		initActions();
		initApplication();
		initTitles();

		ButtonModel bm = jMenuDatum.getModel();
		bm.setEnabled(true);
		jMenuDatum.setModel(bm);

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

	/*Hashtable with all possible actions. Every action represent a JPanel / JInternalFrame.*/
        private void initActions() {
		actions = new Hashtable();
		actions.put("Bestellung", new JPanelOrder(this));
		actions.put("Hilfe", new JPanelHelp(this));
		actions.put("ModifyCustomer", new JPanelModifyCustomer(this));
		actions.put("Lager", new JPanelWarehouse(this));
		actions.put("LieferantBearbeiten", new JPanelModifySupplier(this));
		actions.put("Lieferantenliste", new JPanelViewSuppliers(this));
		actions.put("NewCustomer", new JPanelNewCustomer(this));
		actions.put("ViewCustomers", new JPanelViewCustomers(this));
		actions.put("NeuerLieferant", new JPanelNewSupplier(this));
		actions.put("NeuerVertrag", new JPanelNewContract(this));
		actions.put("VertragEinsehen", new JPanelViewContract(this));
		actions.put("Options", new JPanelOptions(this));
		actions.put("EtatEinsehen", new JPanelViewEtat(this));
		actions.put("SimulationStart", new JPanelSimulationStart(this));
		actions.put("Arbeitsplatzlager", new JPanelWorkPlaceStore(this));
		actions.put("Zahlungseingang", new JPanelIncomingPayments(this));
	}
	
	/*Hashtable titles with the german titles of the JInternalFrames.*/
        private void initTitles()
	{
		titles = new Hashtable();
		titles.put("NewCustomer", "Neuer Kunde");
                titles.put("ModifyCustomer", "Kunde bearbeiten");
                titles.put("ViewCustomers", "Kundenübersicht");
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
        jMenuSeperator = new javax.swing.JMenu();
        jMenuItemSimStart1 = new javax.swing.JMenuItem();
        jMenuDatum = new javax.swing.JMenu();

        setTitle("Wirtschaftssimulation");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

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
        jMenuVertrieb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuVertriebActionPerformed(evt);
            }
        });

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
        jMenuVertrag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuVertragActionPerformed(evt);
            }
        });

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
        jMenuLieferanten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuLieferantenActionPerformed(evt);
            }
        });

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

        jMenuSeperator.setPreferredSize(new java.awt.Dimension(100, 21));
        jMenuSeperator.setEnabled(false);
        jMenuItemSimStart1.setText("Start / Stop");
        jMenuSeperator.add(jMenuItemSimStart1);

        jMenuBarWiSim.add(jMenuSeperator);

        jMenuDatum.setBorder(null);
        jMenuDatum.setText("Montag, 1. September 2003");
        jMenuDatum.setFont(new java.awt.Font("Dialog", 0, 14));
        jMenuDatum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenuDatum.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jMenuDatum.setPreferredSize(new java.awt.Dimension(270, 15));
        jMenuDatum.setEnabled(false);
        jMenuBarWiSim.add(jMenuDatum);

        setJMenuBar(jMenuBarWiSim);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-800)/2, (screenSize.height-600)/2, 800, 600);
    }//GEN-END:initComponents

	private void jMenuItemZahlungseingangActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuItemZahlungseingangActionPerformed
		addPanel("Zahlungseingang");
	} //GEN-LAST:event_jMenuItemZahlungseingangActionPerformed

	private void jMenuItemKundenuebersichtActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuItemKundenuebersichtActionPerformed
		addPanel("ViewCustomers");
	} //GEN-LAST:event_jMenuItemKundenuebersichtActionPerformed

	private void jMenuVertriebActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuVertriebActionPerformed
		// Add your handling code here:
	} //GEN-LAST:event_jMenuVertriebActionPerformed

	private void jMenuVertragActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuVertragActionPerformed
		// Add your handling code here:
	} //GEN-LAST:event_jMenuVertragActionPerformed

	private void jMenuItemArbeitsplatzlagerActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuItemArbeitsplatzlagerActionPerformed
		addPanel("Arbeitsplatzlager");
	} //GEN-LAST:event_jMenuItemArbeitsplatzlagerActionPerformed

	private void jMenuItemLieferantenlisteActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuItemLieferantenlisteActionPerformed
		addPanel("Lieferantenliste");
	} //GEN-LAST:event_jMenuItemLieferantenlisteActionPerformed

	private void jMenuLieferantenActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuLieferantenActionPerformed
		// Add your handling code here:
	} //GEN-LAST:event_jMenuLieferantenActionPerformed

	private void jMenuItemSimStartActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuItemSimStartActionPerformed
		addPanel("SimulationStart");
	} //GEN-LAST:event_jMenuItemSimStartActionPerformed

	private void jMenuItemEtatEinsehenActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuItemEtatEinsehenActionPerformed
		addPanel("EtatEinsehen");
	} //GEN-LAST:event_jMenuItemEtatEinsehenActionPerformed

	private void jMenuItemOptionsActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuItemOptionsActionPerformed
		addPanel("Options");
	} //GEN-LAST:event_jMenuItemOptionsActionPerformed

	private void jMenuItemBeendenActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuItemBeendenActionPerformed
		System.exit(0);
	} //GEN-LAST:event_jMenuItemBeendenActionPerformed

	private void jMenuItemLieferantenBearbeitenActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuItemLieferantenBearbeitenActionPerformed
		addPanel("LieferantBearbeiten");
	} //GEN-LAST:event_jMenuItemLieferantenBearbeitenActionPerformed

	private void jMenuItemVertragEinsehenActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuItemVertragEinsehenActionPerformed
		addPanel("VertragEinsehen");
	} //GEN-LAST:event_jMenuItemVertragEinsehenActionPerformed

	private void jMenuItemNeuerLieferantActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuItemNeuerLieferantActionPerformed
		addPanel("NeuerLieferant");
	} //GEN-LAST:event_jMenuItemNeuerLieferantActionPerformed

	private void jMenuItemLagerUebersichtActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuItemLagerUebersichtActionPerformed
		addPanel("Lager");
	} //GEN-LAST:event_jMenuItemLagerUebersichtActionPerformed

	private void jMenuItemBestellungActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuItemBestellungActionPerformed
		addPanel("Bestellung");
	} //GEN-LAST:event_jMenuItemBestellungActionPerformed

	private void jMenuItemNeuerVertragActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuItemNeuerVertragActionPerformed
		addPanel("NeuerVertrag");
	} //GEN-LAST:event_jMenuItemNeuerVertragActionPerformed

	private void jMenuItemInfoActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jMenuItemInfoActionPerformed
		addPanel("Hilfe");
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

	/** Liefert ein WiSimDao-Objekt
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
		jMenuDatum.setText(justDate.format(actDate));
	}

	/** Erneuert die Datumsanzeige.
	 * @param actDate
	 */
	public void refreshTextFieldDate(java.util.Date actDate) {
		this.actDate = actDate;
		actDateGC.setTimeInMillis(actDate.getTime());
		jMenuDatum.setText(df.format(actDate));
	}

	/** Gibt die Simulationszeit zurück
	 * @return Die Simulationszeit
	 */
	public Date getActDate() {
		return actDate;
	}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBarWiSim;
    private javax.swing.JMenu jMenuDatei;
    private javax.swing.JMenu jMenuDatum;
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
    private javax.swing.JMenuItem jMenuItemNeuerKunde;
    private javax.swing.JMenuItem jMenuItemNeuerLieferant;
    private javax.swing.JMenuItem jMenuItemNeuerVertrag;
    private javax.swing.JMenuItem jMenuItemOptions;
    private javax.swing.JMenuItem jMenuItemSimStart;
    private javax.swing.JMenuItem jMenuItemSimStart1;
    private javax.swing.JMenuItem jMenuItemVertragEinsehen;
    private javax.swing.JMenuItem jMenuItemZahlungseingang;
    private javax.swing.JMenu jMenuKunde;
    private javax.swing.JMenu jMenuLager;
    private javax.swing.JMenu jMenuLieferanten;
    private javax.swing.JMenu jMenuProduktion;
    private javax.swing.JMenu jMenuRechnungswesen;
    private javax.swing.JMenu jMenuSeperator;
    private javax.swing.JMenu jMenuSimulation;
    private javax.swing.JMenu jMenuVertrag;
    private javax.swing.JMenu jMenuVertrieb;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables

}
