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
 * JPanelEtatEinsehen.java
 *
 * Created on 10. März 2003, 21:33
 */

package net.sourceforge.wisim.controller;
import java.text.DecimalFormat;
import java.util.*;
import net.sourceforge.wisim.model.*;
import net.sourceforge.wisim.dao.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.*;

/** Gibt eine Übersicht aller Einzelteilaufträge aus.
 * @author benjamin.pasero
 */
public class JPanelViewOrders extends javax.swing.JPanel implements SimulationPane {

	private WiSimDAO dao;
	private Vector etatListe;
	private Collection etatPos;
	private int etatAnzahl;
	private int etatPosAnzahl;
	private double summe;
	private double skontoToCalc;
	private double lieferrabattToCalc;
	private double calcedLieferrabatt;
	private double calcedSkonto;
	private double calcedGesamt;
	private static Color darkgreen = new Color(51, 153, 51);
	private static Color red = new Color(255, 0, 0);
	private boolean isBuilt;
	private WiSimMainController wiSimMainController;
	private DecimalFormat format;

	//Logger
	private WiSimLogger wiSimLogger;

	/** Creates new form JPanelEtatEinsehen
	 * @param wiSimMainController Der WiSimMainController
	 */
	public JPanelViewOrders(WiSimMainController wiSimMainController) {
		this.wiSimMainController = wiSimMainController;
		wiSimLogger = wiSimMainController.getWiSimLogger();
		initDAO(wiSimMainController);
		etatListe = new Vector();
		etatPos = new Vector();
		etatAnzahl = 0;
		etatPosAnzahl = 0;
		summe = 0;
		skontoToCalc = 0;
		lieferrabattToCalc = 0;
		calcedLieferrabatt = 0;
		calcedSkonto = 0;
		calcedGesamt = 0;
		isBuilt = false;
		initComponents();
		initializeEtatListe();
		setEtatTable();
		format = new DecimalFormat("###,##0.00");
	}

	private void initDAO(WiSimMainController wiSimMainController) {
		dao = wiSimMainController.getDAO();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	private void initComponents() { //GEN-BEGIN:initComponents
		jLabelEtatEinsehen = new javax.swing.JLabel();
		jScrollPaneEtatListe = new javax.swing.JScrollPane();
		jTableEtatListe = new javax.swing.JTable();
		jTableEtatListe.setSelectionMode(0);
		jTableEtatListe.getTableHeader().setReorderingAllowed(false);

		jLabelAuftraege = new javax.swing.JLabel();
		jPanelDetailansicht = new javax.swing.JPanel();
		jLabelPositionen = new javax.swing.JLabel();
		jScrollPaneEtatPos = new javax.swing.JScrollPane();
		jTableEtatPos = new javax.swing.JTable();
		jTableEtatPos.getTableHeader().setReorderingAllowed(false);
		jLabelLieferant = new javax.swing.JLabel();
		jTextLTFirma = new javax.swing.JTextField();
		jTextLTSumme = new javax.swing.JTextField();
		jLabelZwischensumme = new javax.swing.JLabel();
		jTextFieldLieferrabatt = new javax.swing.JTextField();
		jTextFieldSkonto = new javax.swing.JTextField();
		jLabelNettozahldatum = new javax.swing.JLabel();
		jTextFieldNettozahldatum = new javax.swing.JTextField();
		jLabelProzent2 = new javax.swing.JLabel();
		jLabelProzent = new javax.swing.JLabel();
		jTextFieldAnsprechperson = new javax.swing.JTextField();
		jLabelEuro = new javax.swing.JLabel();
		jLabelAbzLieferrabatt = new javax.swing.JLabel();
		jTextFieldCalcLieferrabatt = new javax.swing.JTextField();
		jLabelEuro3 = new javax.swing.JLabel();
		jLabelAbzSkonto = new javax.swing.JLabel();
		jTextFieldCalcSkonto = new javax.swing.JTextField();
		jLabelEuro4 = new javax.swing.JLabel();
		jLabelGesamt = new javax.swing.JLabel();
		jTextFieldCalcGesamt = new javax.swing.JTextField();
		jLabelEuro5 = new javax.swing.JLabel();
		jSeparator = new javax.swing.JSeparator();
		jLabelAbz2 = new javax.swing.JLabel();
		jLabelAbz = new javax.swing.JLabel();
		jLabelFirma = new javax.swing.JLabel();
		jLabelName = new javax.swing.JLabel();
		jLabelQualitaet = new javax.swing.JLabel();
		jLabelZUverlaessigkeit = new javax.swing.JLabel();
		jTextQualitaet = new javax.swing.JTextField();
		jTextZuverlaessigkeit = new javax.swing.JTextField();
		jPanelLegende = new javax.swing.JPanel();
		jLabelGruen = new javax.swing.JLabel();
		jLabelRot = new javax.swing.JLabel();

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

		jLabelEtatEinsehen.setFont(new java.awt.Font("Dialog", 1, 24));
		jLabelEtatEinsehen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabelEtatEinsehen.setText("Einzelteilauftr\u00e4ge einsehen");
		add(jLabelEtatEinsehen);
		jLabelEtatEinsehen.setBounds(150, 10, 550, 32);

		jTableEtatListe.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {
		}, new String[] { "Lieferdatum", "Auftragsdatum", "Nr", "Status" }) {
			boolean[] canEdit = new boolean[] { false, false, false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		jTableEtatListe.addAncestorListener(new javax.swing.event.AncestorListener() {
			public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
			}
			public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
				jTableEtatListeAncestorAdded(evt);
			}
			public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
			}
		});
		jTableEtatListe.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jTableEtatListeMouseClicked(evt);
			}
		});

		jScrollPaneEtatListe.setViewportView(jTableEtatListe);

		add(jScrollPaneEtatListe);
		jScrollPaneEtatListe.setBounds(10, 70, 270, 380);

		jLabelAuftraege.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabelAuftraege.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabelAuftraege.setText("Auftr\u00e4ge");
		add(jLabelAuftraege);
		jLabelAuftraege.setBounds(10, 50, 270, 16);

		jPanelDetailansicht.setLayout(null);

		jPanelDetailansicht.setBorder(new javax.swing.border.TitledBorder("Detailansicht"));
		jLabelPositionen.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabelPositionen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabelPositionen.setText("Positionen");
		jPanelDetailansicht.add(jLabelPositionen);
		jLabelPositionen.setBounds(100, 20, 270, 16);

		jTableEtatPos.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {
		}, new String[] { "Artikel", "Menge", "Preis" }));
		jScrollPaneEtatPos.setViewportView(jTableEtatPos);

		jPanelDetailansicht.add(jScrollPaneEtatPos);
		jScrollPaneEtatPos.setBounds(20, 40, 440, 130);

		jLabelLieferant.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabelLieferant.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabelLieferant.setText("Lieferant");
		jPanelDetailansicht.add(jLabelLieferant);
		jLabelLieferant.setBounds(190, 330, 80, 20);

		jTextLTFirma.setEditable(false);
		jPanelDetailansicht.add(jTextLTFirma);
		jTextLTFirma.setBounds(70, 360, 160, 20);

		jTextLTSumme.setEditable(false);
		jTextLTSumme.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		jPanelDetailansicht.add(jTextLTSumme);
		jTextLTSumme.setBounds(320, 180, 120, 20);

		jLabelZwischensumme.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabelZwischensumme.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabelZwischensumme.setText("Zwischensumme");
		jPanelDetailansicht.add(jLabelZwischensumme);
		jLabelZwischensumme.setBounds(180, 180, 130, 20);

		jTextFieldLieferrabatt.setEditable(false);
		jTextFieldLieferrabatt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		jPanelDetailansicht.add(jTextFieldLieferrabatt);
		jTextFieldLieferrabatt.setBounds(160, 220, 40, 20);

		jTextFieldSkonto.setEditable(false);
		jTextFieldSkonto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		jPanelDetailansicht.add(jTextFieldSkonto);
		jTextFieldSkonto.setBounds(160, 250, 40, 20);

		jLabelNettozahldatum.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabelNettozahldatum.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabelNettozahldatum.setText("Nettozahldatum");
		jPanelDetailansicht.add(jLabelNettozahldatum);
		jLabelNettozahldatum.setBounds(30, 280, 120, 19);

		jTextFieldNettozahldatum.setEditable(false);
		jTextFieldNettozahldatum.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		jPanelDetailansicht.add(jTextFieldNettozahldatum);
		jTextFieldNettozahldatum.setBounds(160, 280, 80, 20);

		jLabelProzent2.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabelProzent2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabelProzent2.setText("%");
		jPanelDetailansicht.add(jLabelProzent2);
		jLabelProzent2.setBounds(200, 250, 30, 20);

		jLabelProzent.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabelProzent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabelProzent.setText("%");
		jPanelDetailansicht.add(jLabelProzent);
		jLabelProzent.setBounds(200, 220, 30, 20);

		jTextFieldAnsprechperson.setEditable(false);
		jPanelDetailansicht.add(jTextFieldAnsprechperson);
		jTextFieldAnsprechperson.setBounds(300, 360, 160, 20);

		jLabelEuro.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabelEuro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabelEuro.setText("\u20ac");
		jPanelDetailansicht.add(jLabelEuro);
		jLabelEuro.setBounds(440, 180, 20, 20);

		jLabelAbzLieferrabatt.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabelAbzLieferrabatt.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabelAbzLieferrabatt.setText("Lieferrabatt");
		jPanelDetailansicht.add(jLabelAbzLieferrabatt);
		jLabelAbzLieferrabatt.setBounds(220, 220, 90, 19);

		jTextFieldCalcLieferrabatt.setEditable(false);
		jTextFieldCalcLieferrabatt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		jPanelDetailansicht.add(jTextFieldCalcLieferrabatt);
		jTextFieldCalcLieferrabatt.setBounds(320, 220, 120, 20);

		jLabelEuro3.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabelEuro3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabelEuro3.setText("\u20ac");
		jPanelDetailansicht.add(jLabelEuro3);
		jLabelEuro3.setBounds(440, 220, 20, 20);

		jLabelAbzSkonto.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabelAbzSkonto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabelAbzSkonto.setText("Skonto");
		jPanelDetailansicht.add(jLabelAbzSkonto);
		jLabelAbzSkonto.setBounds(220, 250, 90, 19);

		jTextFieldCalcSkonto.setEditable(false);
		jTextFieldCalcSkonto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		jPanelDetailansicht.add(jTextFieldCalcSkonto);
		jTextFieldCalcSkonto.setBounds(320, 250, 120, 20);

		jLabelEuro4.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabelEuro4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabelEuro4.setText("\u20ac");
		jPanelDetailansicht.add(jLabelEuro4);
		jLabelEuro4.setBounds(440, 250, 20, 20);

		jLabelGesamt.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabelGesamt.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabelGesamt.setText("Gesamt");
		jPanelDetailansicht.add(jLabelGesamt);
		jLabelGesamt.setBounds(240, 280, 70, 19);

		jTextFieldCalcGesamt.setEditable(false);
		jTextFieldCalcGesamt.setFont(new java.awt.Font("Dialog", 1, 12));
		jTextFieldCalcGesamt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		jPanelDetailansicht.add(jTextFieldCalcGesamt);
		jTextFieldCalcGesamt.setBounds(320, 280, 120, 20);

		jLabelEuro5.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabelEuro5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabelEuro5.setText("\u20ac");
		jPanelDetailansicht.add(jLabelEuro5);
		jLabelEuro5.setBounds(440, 280, 20, 20);

		jPanelDetailansicht.add(jSeparator);
		jSeparator.setBounds(10, 320, 460, 10);

		jLabelAbz2.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabelAbz2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabelAbz2.setText("abz.");
		jPanelDetailansicht.add(jLabelAbz2);
		jLabelAbz2.setBounds(120, 220, 30, 19);

		jLabelAbz.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabelAbz.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabelAbz.setText("abz.");
		jPanelDetailansicht.add(jLabelAbz);
		jLabelAbz.setBounds(120, 250, 30, 19);

		jLabelFirma.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabelFirma.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabelFirma.setText("Firma");
		jPanelDetailansicht.add(jLabelFirma);
		jLabelFirma.setBounds(10, 360, 50, 19);

		jLabelName.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabelName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabelName.setText("Name");
		jPanelDetailansicht.add(jLabelName);
		jLabelName.setBounds(240, 360, 50, 19);

		jLabelQualitaet.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabelQualitaet.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabelQualitaet.setText("Lieferqualit\u00e4t");
		jPanelDetailansicht.add(jLabelQualitaet);
		jLabelQualitaet.setBounds(50, 400, 110, 19);

		jLabelZUverlaessigkeit.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabelZUverlaessigkeit.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabelZUverlaessigkeit.setText("Zuverl\u00e4ssigkeit");
		jPanelDetailansicht.add(jLabelZUverlaessigkeit);
		jLabelZUverlaessigkeit.setBounds(230, 400, 130, 19);

		jTextQualitaet.setEditable(false);
		jPanelDetailansicht.add(jTextQualitaet);
		jTextQualitaet.setBounds(170, 400, 30, 20);

		jTextZuverlaessigkeit.setEditable(false);
		jPanelDetailansicht.add(jTextZuverlaessigkeit);
		jTextZuverlaessigkeit.setBounds(370, 400, 30, 20);

		add(jPanelDetailansicht);
		jPanelDetailansicht.setBounds(290, 70, 480, 460);

		jPanelLegende.setLayout(null);

		jPanelLegende.setBorder(new javax.swing.border.TitledBorder("Legende"));
		jLabelGruen.setText("Lieferung eingegangen");
		jPanelLegende.add(jLabelGruen);
		jLabelGruen.setBounds(10, 20, 180, 16);

		jLabelRot.setText("Lieferung noch nicht eingegangen");
		jPanelLegende.add(jLabelRot);
		jLabelRot.setBounds(10, 50, 290, 16);

		add(jPanelLegende);
		jPanelLegende.setBounds(10, 450, 270, 80);

	} //GEN-END:initComponents

	private void formAncestorRemoved(javax.swing.event.AncestorEvent evt) { //GEN-FIRST:event_formAncestorRemoved
		wiSimMainController.removeActivPanel(this);
	} //GEN-LAST:event_formAncestorRemoved

	private void formAncestorAdded(javax.swing.event.AncestorEvent evt) { //GEN-FIRST:event_formAncestorAdded
		wiSimMainController.addActivPanel(this);
	} //GEN-LAST:event_formAncestorAdded

	private void jTableEtatListeMouseClicked(java.awt.event.MouseEvent evt) { //GEN-FIRST:event_jTableEtatListeMouseClicked
		getEtatInfo();
	} //GEN-LAST:event_jTableEtatListeMouseClicked

	private void jTableEtatListeAncestorAdded(javax.swing.event.AncestorEvent evt) { //GEN-FIRST:event_jTableEtatListeAncestorAdded
		getEtatListe();
		showLegende();
	} //GEN-LAST:event_jTableEtatListeAncestorAdded

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLabel jLabelAbz;
	private javax.swing.JLabel jLabelAbz2;
	private javax.swing.JLabel jLabelAbzLieferrabatt;
	private javax.swing.JLabel jLabelAbzSkonto;
	private javax.swing.JLabel jLabelAuftraege;
	private javax.swing.JLabel jLabelEtatEinsehen;
	private javax.swing.JLabel jLabelEuro;
	private javax.swing.JLabel jLabelEuro3;
	private javax.swing.JLabel jLabelEuro4;
	private javax.swing.JLabel jLabelEuro5;
	private javax.swing.JLabel jLabelFirma;
	private javax.swing.JLabel jLabelGesamt;
	private javax.swing.JLabel jLabelGruen;
	private javax.swing.JLabel jLabelLieferant;
	private javax.swing.JLabel jLabelName;
	private javax.swing.JLabel jLabelNettozahldatum;
	private javax.swing.JLabel jLabelPositionen;
	private javax.swing.JLabel jLabelProzent;
	private javax.swing.JLabel jLabelProzent2;
	private javax.swing.JLabel jLabelQualitaet;
	private javax.swing.JLabel jLabelRot;
	private javax.swing.JLabel jLabelZUverlaessigkeit;
	private javax.swing.JLabel jLabelZwischensumme;
	private javax.swing.JPanel jPanelDetailansicht;
	private javax.swing.JPanel jPanelLegende;
	private javax.swing.JScrollPane jScrollPaneEtatListe;
	private javax.swing.JScrollPane jScrollPaneEtatPos;
	private javax.swing.JSeparator jSeparator;
	private javax.swing.JTable jTableEtatListe;
	private javax.swing.JTable jTableEtatPos;
	private javax.swing.JTextField jTextFieldAnsprechperson;
	private javax.swing.JTextField jTextFieldCalcGesamt;
	private javax.swing.JTextField jTextFieldCalcLieferrabatt;
	private javax.swing.JTextField jTextFieldCalcSkonto;
	private javax.swing.JTextField jTextFieldLieferrabatt;
	private javax.swing.JTextField jTextFieldNettozahldatum;
	private javax.swing.JTextField jTextFieldSkonto;
	private javax.swing.JTextField jTextLTFirma;
	private javax.swing.JTextField jTextLTSumme;
	private javax.swing.JTextField jTextQualitaet;
	private javax.swing.JTextField jTextZuverlaessigkeit;
	// End of variables declaration//GEN-END:variables

	/** Erstellt die Einzelteilaufträge-Tabelle. */
	private void setEtatTable() {
		try {
			etatListe = dao.getEinzelteilauftraege();
		} catch (WiSimDAOException e) {
			wiSimLogger.log("setEtatTable()", e);
		}

		etatAnzahl = etatListe.size();

		//DefaultTableModel mit Variablen Zeilen, 3 TableHeads und nicht editierbaren Zellen
		Object[][] tableInit = new Object[etatAnzahl][4];
		DefaultTableModel defTable = new DefaultTableModel(tableInit, new String[] { "Auftragsdatum", "Lieferdatum", "Nr", "Status" }) {
			boolean[] canEdit = new boolean[] { false, false, false, false };
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
			public Class getColumnClass(int c) {
				return getValueAt(0, c).getClass();
			}
		};

		jTableEtatListe.setModel(defTable);
		jTableEtatListe.setFocusable(false);

		//Spaltenbreite
		javax.swing.table.TableColumn column = null;
		for (int i = 0; i < 4; i++) {
			column = jTableEtatListe.getColumnModel().getColumn(i);
			if (i == 2) {
				column.setPreferredWidth(30);
			} else if (i == 3) {
				column.setPreferredWidth(50);
			} else {
				column.setPreferredWidth(100);
			}
		}

		jTableEtatListe.getTableHeader().setReorderingAllowed(false);
		jTableEtatListe.setDragEnabled(false);
	}

	/** Erstellt die Einzelteilauftrags-Positionen Tabelle. */
	private void setEtatPosTable() {
		//DefaultTableModel mit Variablen Zeilen, 3 TableHeads und nicht editierbaren Zellen
		Object[][] tableInit = new Object[etatPosAnzahl][3];
		DefaultTableModel defTable = new DefaultTableModel(tableInit, new String[] { "Article", "Menge", "Preis" }) {
			boolean[] canEdit = new boolean[] { false, false, false };
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		};
		jTableEtatPos.setModel(defTable);
		jTableEtatPos.setEnabled(false);
		jTableEtatPos.getTableHeader().setReorderingAllowed(false);
	}

	/** Formatiert ein Date Objekt zu einem String TT.MM.JJJJ.
	 * @param dateToFormat Das zu formatierende Datum.
	 * @return Das formatierte Datum.
	 */
	private String formatDate(java.sql.Date dateToFormat) {
		String date = String.valueOf(dateToFormat);
		String year = date.substring(0, 4);
		String month = date.substring(5, 7);
		String day = date.substring(8, 10);
		String formattedDate = day + "." + month + "." + year;
		return formattedDate;
	}

	/** Berechnet den Gesamtpreis des Einzelteilauftrages, zusammen mit dem Skonto und
	 * Lieferrabatt.
	 */
	private void calculateSum() {
		calcedLieferrabatt = 0;
		calcedSkonto = 0;
		calcedGesamt = 0;

		calcedLieferrabatt = summe / 100 * lieferrabattToCalc;
		calcedSkonto = (summe - calcedLieferrabatt) / 100 * skontoToCalc;
		calcedGesamt = summe - calcedSkonto - calcedLieferrabatt;

		jTextFieldCalcLieferrabatt.setText(format.format(Math.rint(calcedLieferrabatt * 100) / 100.));
		jTextFieldCalcSkonto.setText(format.format(Math.rint(calcedSkonto * 100) / 100.));
		jTextFieldCalcGesamt.setText(format.format(Math.rint(calcedGesamt * 100) / 100.));
	}

	/** Zeigt die Positionen des selektierten Einzelteilauftrages */
	private void getEtatInfo() {
		ComponentContract etat = (ComponentContract) etatListe.get(jTableEtatListe.getSelectedRow());

		jTextFieldLieferrabatt.setText(format.format(etat.getLieferrabatt()));
		jTextFieldSkonto.setText(format.format(etat.getSkonto()));

		long nettozahlDatumMili = etat.getAuftragsdatum().getTime() + etat.getSkontofrist() * 1000 * 60 * 60 * 24;

		GregorianCalendar nettoDatum = new GregorianCalendar();
		nettoDatum.setTimeInMillis(nettozahlDatumMili);

		String tag = String.valueOf(nettoDatum.get(Calendar.DATE));
		String monat = String.valueOf(nettoDatum.get(Calendar.MONTH) + 1);
		String jahr = String.valueOf(nettoDatum.get(Calendar.YEAR));
		jTextFieldNettozahldatum.setText(tag + "." + monat + "." + jahr);

		try {
			etatPos = dao.getEinzelteilAuftragsPositionen(etat.getNr());
			ComponentContractAccount etatr = dao.getEinzelteilauftragsrechnung(etat.getNr());
			jTextLTSumme.setText(format.format(etatr.getBetrag()));
			summe = etatr.getBetrag();
		} catch (WiSimDAOException e) {
			wiSimLogger.log("getEtatInfo()", e);
		}

		etatPosAnzahl = etatPos.size();
		setEtatPosTable();
		int i = 0;

		Iterator etatPos_it = etatPos.iterator();
		while (etatPos_it.hasNext()) {
			ComponentContractItem etatPosItem = (ComponentContractItem) etatPos_it.next();

			try {
				WiSimComponent et = dao.getEinzelteil(etatPosItem.getEtNr());
				jTableEtatPos.setValueAt(et.getName(), i, 0);
				jTableEtatPos.setValueAt(String.valueOf(etatPosItem.getBestellmenge()), i, 1);
				SupplyList lieferliste = dao.getLieferliste(etat.getLieferantNr(), etatPosItem.getEtNr());
				double preis = lieferliste.getPreis() * etatPosItem.getBestellmenge();
				jTableEtatPos.setValueAt(format.format(preis), i, 2);

			} catch (WiSimDAOException e) {
				wiSimLogger.log("getEtatInfo()", e);
			}
			i++;
		}

		try {
			Supplier lt = dao.getLieferant(etat.getLieferantNr());
			jTextLTFirma.setText(lt.getFirma());
			jTextFieldAnsprechperson.setText(lt.getVorname() + " " + lt.getNachname());
			jTextQualitaet.setText(lt.getLieferqualitaet());
			jTextZuverlaessigkeit.setText(lt.getZuverlaessigkeit());

		} catch (WiSimDAOException e) {
			wiSimLogger.log("getEtatInfo()", e);
		}

		skontoToCalc = etat.getSkonto();
		lieferrabattToCalc = etat.getLieferrabatt();
		calculateSum();
	}

	/** Holt alle Einzelteilaufträge aus der Datenbank */
	private void getEtatListe() {
		try {
			etatListe = dao.getEinzelteilauftraege();
		} catch (WiSimDAOException e) {
			wiSimLogger.log("getEtatListe()", e);
		}

		etatAnzahl = etatListe.size();
		setEtatTable();
		int i = 0;

		Iterator etatListe_it = etatListe.iterator();

		while (etatListe_it.hasNext()) {
			ComponentContract etat = (ComponentContract) etatListe_it.next();

			jTableEtatListe.setValueAt(formatDate(etat.getAuftragsdatum()), i, 0);
			jTableEtatListe.setValueAt(formatDate(etat.getLieferdatum()), i, 1);
			jTableEtatListe.setValueAt(String.valueOf(etat.getNr()), i, 2);

			//Status Icon
			Image image = new BufferedImage(28, 30, 2);
			Graphics g = image.getGraphics();

			if (etat.getLieferdatum().before(wiSimMainController.getActDate())) {
				g.setColor(darkgreen);
			} else {
				g.setColor(red);
			}
			g.fillRoundRect(10, 11, 10, 10, 3, 3);

			ImageIcon ic = new ImageIcon(image);

			jTableEtatListe.setValueAt(ic, i, 3);
			i++;
		}
	}

	/** Zeigt die Legende an */
	private void showLegende() {
		//Legende
		Image imageIconGreen = new BufferedImage(28, 30, 2);
		Graphics g = imageIconGreen.getGraphics();
		g.setColor(darkgreen);
		g.fillRoundRect(10, 11, 10, 10, 3, 3);
		ImageIcon ic = new ImageIcon(imageIconGreen);
		jLabelGruen.setIcon(ic);

		Image imageIconRed = new BufferedImage(28, 30, 2);
		g = imageIconRed.getGraphics();
		g.setColor(red);
		g.fillRoundRect(10, 11, 10, 10, 3, 3);
		ic = new ImageIcon(imageIconRed);
		jLabelRot.setIcon(ic);
	}

	/** Erneuert die Einzelteile-Auftrags-Tabelle */
	private void refreshEtatListe() {
		try {
			etatListe = dao.getEinzelteilauftraege();
		} catch (WiSimDAOException e) {
			wiSimLogger.log("refreshEtatListe()", e);
		}

		int i = 0;

		Iterator etatListe_it = etatListe.iterator();

		while (etatListe_it.hasNext()) {
			ComponentContract etat = (ComponentContract) etatListe_it.next();

			//Status Icon
			Image image = new BufferedImage(28, 30, 2);
			Graphics g = image.getGraphics();

			if (etat.getLieferdatum().before(new java.sql.Date(wiSimMainController.getActDate().getTime()))) {
				g.setColor(darkgreen);
			} else {
				g.setColor(red);
			}
			g.fillRoundRect(10, 11, 10, 10, 3, 3);

			ImageIcon ic = new ImageIcon(image);

			jTableEtatListe.setValueAt(ic, i, 3);
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
	 *  Wichtig ist diese Variable für die Simulation: Wurde das Pane
	 *  schon einmal aufgebaut, so müssen die Simulationsthread das Pane
	 *  nicht noch einmal initialisieren!
	 * @return boolean isBuilt
	 */
	public boolean getIsBuilt() {
		return isBuilt;
	}

	/**
	 * Initialisiert die Einzelteilauftrags Liste
	 */
	private void initializeEtatListe() {
		try {
			etatListe = dao.getEinzelteilauftraege();
		} catch (WiSimDAOException e) {
			wiSimLogger.log("JPanelEtatEinsehen()", e);
		}

		etatAnzahl = etatListe.size();
	}

	/* (non-Javadoc)
	 * @see net.sourceforge.wisim.model.SimulationPane#refresh()
	 */
	public void refresh() {
		refreshEtatListe();
	}
}