/*
 * WiSimLogger.java
 *
 * Created on 30. März 2003, 18:33
 */
package net.sourceforge.wisim.controller;

import java.util.logging.*;
import java.io.*;
import javax.swing.*;

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
            fh = new FileHandler("c:\\" + logFile);
        } catch (IOException e) {}
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
	public void log(
		Level level,
		String msg,
		Throwable t,
		boolean exit,
		boolean showErrorPane)
	{
		if (showErrorPane)
		{
			if (exit)
			{
				logger.log(level, msg, t);
				JOptionPane jp = new JOptionPane();
				jp.setMessageType(javax.swing.JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(
					null,
					"Unerwarteter Fehler! Programm wird beendet!\n(Geloggt in c:\\ "
						+ logFile
						+ ")",
					"Fehler",
					JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}
			else
			{
				logger.log(level, msg, t);
				JOptionPane jp = new JOptionPane();
				jp.setMessageType(javax.swing.JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(
					null,
					"Unerwarteter Fehler!\n(Geloggt in c:\\ " + logFile + ")",
					"Fehler",
					JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			if (exit)
			{
				logger.log(level, msg, t);
				System.exit(1);
			}
			else
			{
				logger.log(level, msg, t);
			}
		}
	}
}
