/*
 * ProduktionsSimulationThread.java
 *
 * Created on 5. März 2003, 18:14
 */

package net.sourceforge.wisim.controller;

import java.util.logging.Level;

import net.sourceforge.wisim.dao.WiSimDAO;
import net.sourceforge.wisim.dao.WiSimDAOException;
import net.sourceforge.wisim.model.Arbeitsplatz;
import net.sourceforge.wisim.model.ArbeitsplatzLager;

/** Produktionsimulations-Thread
 * @author Kay Patzwald
 */
public class ProduktionsSimulationThread extends Thread
{
	private Arbeitsplatz ap;
	private ProduktionsController runController;
	private WiSimDAO dao;
	private WiSimMainController wiSimMainController;
	private int timestep;
	private static final int HUB = 26;
	private int faktor;
	private WiSimLogger wiSimLogger;

	/** Creates a new instance of ProduktionsSimulationThread
   * @param ap
   * @param runController
   * @param wiSimMainController
   * @param faktor
   * @param timestep
   */
	public ProduktionsSimulationThread(
		Arbeitsplatz ap,
		ProduktionsController runController,
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
				wiSimLogger.log("ProduktionsSimulationThread.run()", e1);
			}

			if (ap.getVorgaenger()[0] != 0)
			{
				ArbeitsplatzLager apLager = null;
				try
				{
					apLager =
						dao.getArbeitsplatzLager(ap.getNr(), HUB, "Eingang");
				}
				catch (WiSimDAOException e4)
				{
					wiSimLogger.log("ProduktionsSimulationThread.run()", e4);
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
						getName() + ": ProduktionsSimulationThread.run()",
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

				// Letzter Arbeitsplatz
				if (ap.getNachfolger()[0] == 0)
				{
					runController.hubKomplett(getName(), ap);
				}
			}

			// Erster Arbeitsplatz ohne Vorgänger
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
						getName() + ": ProduktionsSimulationThread.run()",
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
