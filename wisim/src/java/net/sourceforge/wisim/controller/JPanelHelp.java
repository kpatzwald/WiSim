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
 * JPanelHilfe.java
 *
 * Created on 10. Februar 2003, 20:26
 */

package net.sourceforge.wisim.controller;

import java.io.IOException;

import net.sourceforge.wisim.model.WiSimLogger;

/**
 *
 * @author  Kay Patzwald
 */
public class JPanelHelp extends javax.swing.JPanel {

	private WiSimLogger logger;
	
	/** Creates new form JPanelHilfe */
	public JPanelHelp(WiSimMainController wiSimMainController) {
		logger = wiSimMainController.getWiSimLogger();
		initComponents();
		modGui();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	private void initComponents() { //GEN-BEGIN:initComponents
		jOptionPaneVerInfo = new javax.swing.JOptionPane();
		jScrollPaneHilfe = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();
		jLabelHilfe = new javax.swing.JLabel();
		jButtonURL = new javax.swing.JButton();

		jOptionPaneVerInfo.setMessageType(1);

		setLayout(null);

		setPreferredSize(new java.awt.Dimension(800, 600));
		jTextArea1.setEditable(false);
		jTextArea1.setLineWrap(true);
		jTextArea1.setText(
			"Welcome to WiSim, a business game.\n\n\nVisit us at\n     http://wisim.sourceforge.net.\n\t\nVisit our forum for bugreports, feature requests, development or general dicussion.\n\nThe following topics were implemented in version 1.0: \n    - Single-User-Application \n    - simulation of production \n    - simulation of purchase \n    - simulation of sale \n    - analysis of the production / purchase / sale \n    - simulation of the store house \n    - customer and supplier administration\n\nIn Deutsch:\nDieses Programm ist ein Unternehmensplanspiel.\nFolgende Funktionen sind implementiert:\n\n    - Kundenverwaltung (Anlegen, Editieren, L\u00f6schen, \u00dcbersicht)\n    - Lieferantenverwaltung (Anlegen, Editieren, L\u00f6schen, \u00dcbersicht)\n    - Auftragsverwaltung (Anlegen, \u00dcbersicht)\n    - Arbeitsplatz- und Lager\u00fcbersicht mit Statusbalken\n    - Zahlungseingang verwalten\n    - Simulation der Produktion mit Auswertung\n\n\nCopyright notice                                                       \t\t\t\n\n(c) 2003 WiSim Development Team\nhttp://wisim.sourceforge.net/   \t\t\t                       \nAll rights reserved     \n                                  \nThis script is part of the WiSim Business Game project. The WiSim    \nproject is free software; you can redistribute it and/or modify        \nit under the terms of the GNU General Public License as published by   \nthe Free Software Foundation; either version 2 of the License, or      \n(at your option) any later version.                                    \n                                                                       \nThe GNU General Public License can be found at                         \nhttp://www.gnu.org/copyleft/gpl.html.                                  \nA copy is found in the textfile GPL.txt and important notices to the  \nlicense from the team is found in the textfile LICENSE.txt distributed \nin these package.                                                      \n\nSTAFF:\n------\n\n- Benjamin Pasero - Administrator and Developer\n- Kay Patzwald - Administrator and Developer\n");
		jScrollPaneHilfe.setViewportView(jTextArea1);

		add(jScrollPaneHilfe);
		jScrollPaneHilfe.setBounds(20, 40, 770, 330);

		jLabelHilfe.setFont(new java.awt.Font("Dialog", 1, 24));
		jLabelHilfe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabelHilfe.setText("Info");
		add(jLabelHilfe);
		jLabelHilfe.setBounds(370, 10, 70, 20);

		jButtonURL.setText("Projekt-Homepage \u00f6ffnen");
		jButtonURL.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonURLActionPerformed(evt);
			}
		});

		add(jButtonURL);
		jButtonURL.setBounds(290, 400, 200, 26);

	} //GEN-END:initComponents

	/* Workaround for a possible bug in the Netbeans GUI-Editor*/
	private void modGui() {
		jScrollPaneHilfe.setBounds(20, 40, 750, 355);
	}

	private void jButtonURLActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jButtonURLActionPerformed
		displayURL("http://wisim.sourceforge.net");
	} //GEN-LAST:event_jButtonURLActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jButtonURL;
	private javax.swing.JLabel jLabelHilfe;
	private javax.swing.JOptionPane jOptionPaneVerInfo;
	private javax.swing.JScrollPane jScrollPaneHilfe;
	private javax.swing.JTextArea jTextArea1;
	// End of variables declaration//GEN-END:variables

	//Used to identify the windows platform.
	private static final String WIN_ID = "Windows";
	// The default system browser under windows.
	private static final String WIN_PATH = "rundll32";
	// The flag to display a url.
	private static final String WIN_FLAG = "url.dll,FileProtocolHandler";
	// The default browser under unix.
	private static final String UNIX_PATH = "netscape";
	// The flag to display a url.
	private static final String UNIX_FLAG = "-remote openURL";

	/**
	* Display a file in the system browser.  If you want to display a
	* file, you must include the absolute path name.
	*
	* @param url the file's url (the url must start with either "http://" or
	* "file://").
	*/
	public void displayURL(String url) {
		boolean windows = isWindowsPlatform();
		String cmd = null;
		try {
			if (windows) {
				// cmd = 'rundll32 url.dll,FileProtocolHandler http://...'
				cmd = WIN_PATH + " " + WIN_FLAG + " " + url;
				Runtime.getRuntime().exec(cmd);
			} else {
				// Under Unix, Netscape has to be running for the "-remote"
				// command to work.  So, we try sending the command and
				// check for an exit value.  If the exit command is 0,
				// it worked, otherwise we need to start the browser.
				// cmd = 'netscape -remote openURL(http://www.javaworld.com)'
				cmd = UNIX_PATH + " " + UNIX_FLAG + "(" + url + ")";
				Process p = Runtime.getRuntime().exec(cmd);
				try {
					// wait for exit code -- if it's 0, command worked,
					// otherwise we need to start the browser up.
					int exitCode = p.waitFor();
					if (exitCode != 0) {
						// Command failed, start up the browser
						// cmd = 'netscape http://www.javaworld.com'
						cmd = UNIX_PATH + " " + url;
						p = Runtime.getRuntime().exec(cmd);
					}
				} catch (InterruptedException x) {
					logger.log("displayUrl(), Error bringing up browser", x);					
				}
			}
		} catch (IOException x) {
			logger.log("displayUrl(), Could not invoke browser", x);
		}
	}
	/**
	 * Try to determine whether this application is running under Windows
	 * or some other platform by examing the "os.name" property.
	 *
	 * @return true if this application is running under a Windows OS
	 */
	public boolean isWindowsPlatform() {
		String os = System.getProperty("os.name");
		if (os != null && os.startsWith(WIN_ID))
			return true;
		else
			return false;

	}
}