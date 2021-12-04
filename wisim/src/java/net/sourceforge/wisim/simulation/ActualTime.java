/*   ********************************************************************   **
**   Copyright notice                                                       **
**                                                                          **
**   (c) 2003-2021 WiSim Development Team                                   **
**   https://github.com/kpatzwald/WiSim                                     **
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

package net.sourceforge.wisim.simulation;

/*
 * ActualTime.java
 *
 * Created on 17. März 2003, 14:14
 */
import java.util.*;
/** Speichert die aktuelle Zeit.
 * Der CoreThread greift auf dieses Objekt zu um die Zeit zu ändern.
 * @author benjamin.pasero
 */
public class ActualTime {

	private boolean newMinute;
	private Date actDate;
	private GregorianCalendar actDateGC;

	/** Creates a new instance of ActualTime */
	public ActualTime() {
		newMinute = false;
		actDate = new Date(new GregorianCalendar(2003, 8, 1, 0, 0).getTimeInMillis());
		//Standard-Start-Datum: 1.9.2003
		actDateGC = new GregorianCalendar();
	}

	/** Inkrementiert die aktuelle Minute. */
	public synchronized void increaseTime() {
		//Inkrementiere um 1 Minute
		actDate.setTime(actDate.getTime() + 60000);
		actDateGC.setTimeInMillis(actDate.getTime());
		newMinute = true;
		notify();
	}

	/** Gibt das aktuelle Datum zurück. Läßt den Thread der getDate() abruft solange warten, bis ein neue Minute
	 * angebrochen ist (newMinute == true)
	 * @return aktuelles Datum
	 */
	public synchronized Date getDate(UpdateSimulationAnalysis thread) {
		while (!newMinute) {
			try {
				wait();
			} catch (InterruptedException e) {
				thread.interrupt();
				break;
			}
		}
		newMinute = false;
		return actDate;
	}
}