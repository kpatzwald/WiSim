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

package net.sourceforge.wisim.simulation;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import net.sourceforge.wisim.controller.WiSimMainController;
import net.sourceforge.wisim.dao.WiSimDAO;
import net.sourceforge.wisim.dao.WiSimDAOException;
import net.sourceforge.wisim.model.WiSimLogger;

/**
 * @author Benjamin Paseo
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class UpdateTrafficLight extends Thread {

	private WiSimMainController wiSimMainController;
	private WiSimDAO dao;
	private Hashtable etCapacity;
	private WiSimLogger wiSimLogger;

	public UpdateTrafficLight(WiSimMainController wiSimMainController) {
		this.wiSimMainController = wiSimMainController;
		initDAO(wiSimMainController);
		etCapacity = new Hashtable();
		wiSimLogger = wiSimMainController.getWiSimLogger();
	}

	private void initDAO(WiSimMainController wiSimMainController) {
		dao = wiSimMainController.getDAO();
	}

	public void run() {
		while (true) {
			if (isInterrupted()) {
				break;
			}

			/** Einzelteil Bestandsabfrage */
			try {
				etCapacity = dao.getEtCapacity();
			} catch (WiSimDAOException e) {
				wiSimLogger.log("UpdateTrafficLight.run()", e);
			}

			Vector yellowStatus = new Vector();
			Vector redStatus = new Vector();

			Enumeration ets = etCapacity.keys();
			while (ets.hasMoreElements()) {
				String et = (String) ets.nextElement();
				int minBestand = ((int[]) etCapacity.get(et))[0];
				int bestand = ((int[]) etCapacity.get(et))[1];

				/** Check red status */
				if (bestand < minBestand)
					redStatus.add(et);

				/** Check yellow status */
				else if (bestand - minBestand / 2 < minBestand)
					yellowStatus.add(et);
			}

			/** Set the traffic light in the WiSiMainController */
			if (redStatus.size() > 0) {

				String status = "Achtung! Mindestbestand von:\n";

				for (int a = 0; a < redStatus.size() - 1; a++)
					status += redStatus.get(a) + ", ";

				status += redStatus.get(redStatus.size() - 1);
				status += "\nunterschritten!";

				wiSimMainController.setRedTrafficLights(status);
			} else if (yellowStatus.size() > 0) {

				String status = "Achtung! Mindestbestand von:\n";

				for (int a = 0; a < yellowStatus.size() - 1; a++)
					status += yellowStatus.get(a) + ", ";

				status += yellowStatus.get(yellowStatus.size() - 1);
				status += "\nbald unterschritten!";

				wiSimMainController.setYellowTrafficLights(status);
			} else {
				wiSimMainController.setGreenTrafficLights("Produktion läuft!");
			}

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				this.interrupt();
			}
		}
	}
}