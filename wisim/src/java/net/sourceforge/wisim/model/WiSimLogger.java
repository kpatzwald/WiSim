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
 * WiSimLogger.java
 *
 * Created on 30. März 2003, 18:33
 */
package net.sourceforge.wisim.model;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/** Der Logger für die gesamte WiSim
 * @author benjamin.pasero
 */
public class WiSimLogger {

	private FileHandler fh;
	private String logFile;
	private static Logger logger = Logger.getLogger("com.pixelpark.wisim.controller");

	/** Creates a new instance of WiSimLogger */
	public WiSimLogger(String logFile) {
		try {
			fh = new FileHandler(logFile);
		} catch (IOException e) {
		}
		logger.addHandler(fh);
		this.logFile = logFile;
		logger.setLevel(Level.FINE);
	}

	/**
	 * @param t
	 */
	public void log(Throwable t) {
		logger.log(Level.WARNING, "No Message", t);
		JOptionPane jp = new JOptionPane();
		jp.setMessageType(javax.swing.JOptionPane.ERROR_MESSAGE);
		JOptionPane.showMessageDialog(null, "Unerwarteter Fehler! Programm wird beendet!\n(Geloggt in c:\\ " + logFile + ")", "Fehler", JOptionPane.ERROR_MESSAGE);
		System.exit(1);
	}

	/**
	 * @param msg
	 * @param t
	 */
	public void log(String msg, Throwable t) {
		logger.log(Level.WARNING, msg, t);
		JOptionPane jp = new JOptionPane();
		jp.setMessageType(javax.swing.JOptionPane.ERROR_MESSAGE);
		JOptionPane.showMessageDialog(null, "Unerwarteter Fehler! Programm wird beendet!\n(Geloggt in c:\\ " + logFile + ")", "Fehler", JOptionPane.ERROR_MESSAGE);
		System.exit(1);
	}

	public void log(Level level, String msg, Throwable t, boolean exit) {
		if (exit) {
			logger.log(level, msg, t);
			JOptionPane jp = new JOptionPane();
			jp.setMessageType(javax.swing.JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "Unerwarteter Fehler! Programm wird beendet!\n(Geloggt in c:\\ " + logFile + ")", "Fehler", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		} else {
			logger.log(level, msg, t);
			JOptionPane jp = new JOptionPane();
			jp.setMessageType(javax.swing.JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "Unerwarteter Fehler!\n(Geloggt in c:\\ " + logFile + ")", "Fehler", JOptionPane.ERROR_MESSAGE);
		}
	}

	/** Logger, mit der Option, das Programm zu beenden und eine Fehlermeldung zu zeigen
	 * @param level
	 * @param msg
	 * @param t
	 * @param exit
	 * @param showErrorPane
	 */
	public void log(Level level, String msg, Throwable t, boolean exit, boolean showErrorPane) {
		if (showErrorPane) {
			if (exit) {
				logger.log(level, msg, t);
				JOptionPane jp = new JOptionPane();
				jp.setMessageType(javax.swing.JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Unerwarteter Fehler! Programm wird beendet!\n(Geloggt in c:\\ " + logFile + ")", "Fehler", JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			} else {
				logger.log(level, msg, t);
				JOptionPane jp = new JOptionPane();
				jp.setMessageType(javax.swing.JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "Unerwarteter Fehler!\n(Geloggt in c:\\ " + logFile + ")", "Fehler", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			if (exit) {
				logger.log(level, msg, t);
				System.exit(1);
			} else {
				logger.log(level, msg, t);
			}
		}
	}
}
