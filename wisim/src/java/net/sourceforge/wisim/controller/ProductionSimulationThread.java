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
 * ProductionSimulationThread.java
 *
 * Created on 5. März 2003, 18:14
 */

package net.sourceforge.wisim.controller;

import java.util.logging.Level;

import net.sourceforge.wisim.dao.WiSimDAO;
import net.sourceforge.wisim.dao.WiSimDAOException;
import net.sourceforge.wisim.model.WorkPlace;
import net.sourceforge.wisim.model.WorkPlaceStore;

/** Produktionsimulations-Thread
 * @author Kay Patzwald
 */
public class ProductionSimulationThread extends Thread
{
	private WorkPlace ap;
	private ProductionController runController;
	private WiSimDAO dao;
	private WiSimMainController wiSimMainController;
	private int timestep;
	private static final int HUB = 26;
	private int faktor;
	private WiSimLogger wiSimLogger;

	/** Creates a new instance of ProductionSimulationThread
   * @param ap
   * @param runController
   * @param wiSimMainController
   * @param faktor
   * @param timestep
   */
	public ProductionSimulationThread(
		WorkPlace ap,
		ProductionController runController,
		WiSimMainController wiSimMainController,
		int faktor,
		int timestep)
	{
		super("ProduktionsThreat-" + ap.getNr());
		this.runController = runController;
		this.ap = ap;
		this.wiSimMainController = wiSimMainController;
		this.faktor = faktor;
		this.timestep = timestep;
		this.dao = wiSimMainController.getDAO();
		this.wiSimLogger = wiSimMainController.getWiSimLogger();
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	/** Produktions-Thread */
	public void run()
	{
		while (true)
		{
			if (isInterrupted())
				break;

			try
			{
				ap = dao.getArbeitsplatz(ap.getNr());
			}
			catch (WiSimDAOException e1)
			{
				wiSimLogger.log("ProductionSimulationThread.run()", e1);
			}

			if (ap.getVorgaenger()[0] != 0)
			{
				WorkPlaceStore apLager = null;
				try
				{
					apLager =
						dao.getArbeitsplatzLager(ap.getNr(), HUB, "Eingang");
				}
				catch (WiSimDAOException e4)
				{
					wiSimLogger.log("ProductionSimulationThread.run()", e4);
				}

				if (apLager.getBestand() == 0)
					runController.holeHubs(ap, this);

				if (isInterrupted())
				{
					break;
				}
				if (ap.getNachfolger()[0] != 0)
				{
					runController.hubAbgeholt(ap, this);
				}

				if (isInterrupted())
				{
					break;
				}

				try
				{
					Thread.sleep(ap.getDauer() * timestep * faktor);
				}
				catch (InterruptedException e)
				{
					wiSimLogger.log(
						Level.FINE,
						getName() + ": ProductionSimulationThread.run()",
						e,
						false,
						false);
					this.interrupt();
				}

				if (isInterrupted())
				{
					break;
				}
				runController.hubFertig(
					ap,
					(timestep * faktor),
					this);

				if (isInterrupted())
				{
					break;
				}

				// Letzter WorkPlace
				if (ap.getNachfolger()[0] == 0)
				{
					runController.hubKomplett(getName(), ap);
				}
			}

			// Erster WorkPlace ohne Vorgänger
			else
			{

				runController.hubAbgeholt(ap, this);
				if (isInterrupted())
				{
					break;
				}

				try
				{
					Thread.sleep(ap.getDauer() * timestep * faktor);
				}
				catch (InterruptedException e)
				{
					wiSimLogger.log(
						Level.FINE,
						getName() + ": ProductionSimulationThread.run()",
						e,
						false,
						false);
					this.interrupt();
				}

				if (isInterrupted())
				{
					break;
				}
				runController.hubFertig(
					ap,
					(timestep * faktor),
					this);
			}
		}
	}
}
