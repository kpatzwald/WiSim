/*
  * Created on 09.03.2003
 *
 */
package net.sourceforge.wisim.controller;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;

import net.sourceforge.wisim.dao.WiSimDAO;
import net.sourceforge.wisim.dao.WiSimDAOException;
import net.sourceforge.wisim.dao.WiSimDAOWriteException;
import net.sourceforge.wisim.model.Arbeitsplatz;
import net.sourceforge.wisim.model.ArbeitsplatzLager;
import net.sourceforge.wisim.model.Lagerplatz;

/** Stellt Funktionen f�r die Produktion zur Verf�gung
 * @author Kay Patzwald
 */
public class ProduktionsController
{
	private static final int HUB = 26;
	private WiSimDAO dao;
	private WiSimLogger wiSimLogger;
	private WiSimMainController wiSimMainController;

	/** Creates a new instance of ProduktionsController
   * @param wiSimMainController WiSimMainController
   */
	public ProduktionsController(WiSimMainController wiSimMainController)
	{
		this.wiSimMainController = wiSimMainController;
		dao = wiSimMainController.getDAO();
		wiSimLogger = wiSimMainController.getWiSimLogger();
	}

	/** Simuliert die Lieferung von Einzelteilen
   * @param etNr Einzelteilnummer
   * @param menge Menge
   * @return boolean
   */
	public synchronized boolean einzelteilLieferung(int etNr, int menge)
	{
		boolean status = false;
		try
		{
			int dbMenge = dao.setEinzelteilLagerBestand(etNr, menge);
			notifyAll();
			if (dbMenge == menge)
				status = true;
		}
		catch (WiSimDAOWriteException e)
		{
			wiSimLogger.log("ProduktionsController.einzelteilLieferung()", e);
		}
		return status;
	}

	/** Simuliert den Verbrauch von Einzelteilen
   * @param thread Der aktuelle Thread
   * @param apLager Arbeitsplatzlager
   * @param time Aktuelle Zeit
   * @param anzahl Anzahl der verbrauchten Einzelteile
   */
	public synchronized void einzelteilVerbrauch(
		ArbeitsplatzLager apLager,
		int time,
		int anzahl,
		ProduktionsSimulationThread thread)
	{
		try
		{
			if (apLager.getBestand() >= (apLager.getBenoetigt() * anzahl))
			{
				dao.setEinzelteilArbeitsplatzBestand(
					apLager.getArbeitsplatzNr(),
					apLager.getEinzelteilNr(),
					(apLager.getBenoetigt() * anzahl * -1),
					"Einzelteil");
			}
			// Bestand im Arbeitslager reicht nicht aus
			else
			{
				Collection lagerplaetze =
					dao.getLagerplaetze(apLager.getEinzelteilNr());

				// Ich gehe in der folgenden Implementation davon aus, 
				// dass es in dieser Version
				// nur einen Lagerplatz pro Einzelteil gibt.

				Iterator it = lagerplaetze.iterator();
				Lagerplatz lp = new Lagerplatz();
				lp = (Lagerplatz) it.next();
				int realMenge = 0;

				while ((realMenge = dao
					.setEinzelteilLagerBestand(
						apLager.getEinzelteilNr(),
						((apLager.getMaxBestand() - apLager.getBestand()) *
							- 1)))
					>= 0)
				{
					try
					{
						wait();
					}
					catch (InterruptedException e)
					{
						wiSimLogger.log(
							Level.FINE,
							"ProduktionsController.einzelteilVerbrauch()",
							e,
							false,
							false);
						thread.interrupt();
						return;
					}
				}
				// Sleep simuliert die Dauer (10 Zeiteinheiten) des Einzelteile-Holens aus dem Lager

				try
				{
					Thread.sleep(time * 10);
				}
				catch (InterruptedException e)
				{
					wiSimLogger.log(
						Level.FINE,
						"ProduktionsController.einzelteilVerbrauch()",
						e,
						false,
						false);
					thread.interrupt();
					return;
				}

				if (lp.getBestand()
					>= (apLager.getMaxBestand() - apLager.getBestand()))
				{
					dao.setEinzelteilArbeitsplatzBestand(
						apLager.getArbeitsplatzNr(),
						apLager.getEinzelteilNr(),
						(apLager.getMaxBestand() - apLager.getBestand()),
						"Einzelteil");
				}
				else
				{
					dao.setEinzelteilArbeitsplatzBestand(
						apLager.getArbeitsplatzNr(),
						apLager.getEinzelteilNr(),
						(realMenge * -1),
						"Einzelteil");
				}
				dao.setEinzelteilArbeitsplatzBestand(
					apLager.getArbeitsplatzNr(),
					apLager.getEinzelteilNr(),
					(apLager.getBenoetigt() * anzahl * -1),
					"Einzelteil");
				
				notifyAll();
			}
		}
		catch (WiSimDAOException e)
		{
			wiSimLogger.log("ProduktionsController.einzelteilVerbrauch()", e);
		}
		catch (WiSimDAOWriteException e)
		{
			wiSimLogger.log("ProduktionsController.einzelteilVerbrauch()", e);
		}
	}

	/** Holt Hubs von den Vorg�nger-Arbeitspl�tzen
   * @param thread Der aktuelle Thread
   * @param ap Arbeitsplatz
   */
	public synchronized void holeHubs(
		Arbeitsplatz ap,
		ProduktionsSimulationThread thread)
	{
		Collection apLagerVorgaenger = (Collection) new Vector();
		try
		{
			for (int i = 0; i < ap.getVorgaenger().length; i++)
			{
				apLagerVorgaenger.add(
					dao.getArbeitsplatzLager(
						ap.getVorgaenger()[i],
						26,
						"Ausgang"));
			}
		}
		catch (WiSimDAOException e1)
		{
			wiSimLogger.log("ProduktionsController.holeHubs()", e1);
		}

		boolean isLeer = false;
		Iterator it = apLagerVorgaenger.iterator();
		while (it.hasNext())
		{
			ArbeitsplatzLager apLager = (ArbeitsplatzLager) it.next();
			if (apLager.getBestand() == 0)
			{
				isLeer = true;
			}
		}

		while (isLeer)
		{
			try
			{
				// Vorg�nger noch nicht fertig
				wait();
			}
			catch (InterruptedException e)
			{
				wiSimLogger.log(
					Level.FINE,
					"ProduktionsController.holeHubs()",
					e,
					false,
					false);
				thread.interrupt();
				return;
			}
			apLagerVorgaenger = (Collection) new Vector();
			try
			{
				for (int i = 0; i < ap.getVorgaenger().length; i++)
				{
					apLagerVorgaenger.add(
						dao.getArbeitsplatzLager(
							ap.getVorgaenger()[i],
							26,
							"Ausgang"));
				}
			}
			catch (WiSimDAOException e1)
			{
				wiSimLogger.log("ProduktionsController.holeHubs()", e1);
			}

			isLeer = false;
			it = apLagerVorgaenger.iterator();
			while (it.hasNext())
			{
				ArbeitsplatzLager apLager = (ArbeitsplatzLager) it.next();
				if (apLager.getBestand() == 0)
				{
					isLeer = true;
				}
			}
		}

		// Vorg�nger fertig
		it = null;
		it = apLagerVorgaenger.iterator();
		ArbeitsplatzLager apl = (ArbeitsplatzLager) it.next();
		int minBestand = apl.getBestand();

		while (it.hasNext())
		{
			apl = (ArbeitsplatzLager) it.next();
			if (apl.getBestand() < minBestand)
			{
				minBestand = apl.getBestand();
			}
		}
		try
		{
			if (minBestand < 10)
			{
				dao.setEinzelteilArbeitsplatzBestand(
					ap.getNr(),
					HUB,
					minBestand,
					"Eingang");
				for (int i = 0; i < ap.getVorgaenger().length; i++)
				{
					dao.setEinzelteilArbeitsplatzBestand(
						ap.getVorgaenger()[i],
						HUB,
						(minBestand * -1),
						"Ausgang");
				}
				notifyAll();
			}
			else
			{
				dao.setEinzelteilArbeitsplatzBestand(
					ap.getNr(),
					HUB,
					10,
					"Eingang");
				for (int i = 0; i < ap.getVorgaenger().length; i++)
				{
					dao.setEinzelteilArbeitsplatzBestand(
						ap.getVorgaenger()[i],
						HUB,
						-10,
						"Ausgang");
				}
				notifyAll();
			}
		}
		catch (WiSimDAOWriteException e)
		{
			wiSimLogger.log("ProduktionsController.holeHubs()", e);
		}
		catch (WiSimDAOException e)
		{
			wiSimLogger.log("ProduktionsController.holeHubs()", e);
		}
	}

	/** Testet, ob das Ausgangslager leer ist.
   * @param thread Der aktuelle Thread
   * @param ap Arbeitsplatz
   */
	public synchronized void hubAbgeholt(
		Arbeitsplatz ap,
		ProduktionsSimulationThread thread)
	{
		ArbeitsplatzLager apLager = null;
		try
		{
			apLager = dao.getArbeitsplatzLager(ap.getNr(), HUB, "Ausgang");
		}
		catch (WiSimDAOException e1)
		{
			wiSimLogger.log("ProduktionsController.hubAbgeholt()", e1);
		}
		while (apLager.getBestand() == apLager.getMaxBestand())
		{
			// Hub noch nicht abgeholt
			try
			{
				if (ap.getVorgaenger()[0] != 0)
				{
					this.fuelleEingangslagerAuf(thread.getName(),ap);
				}
				wait();
			}
			catch (InterruptedException e)
			{
				wiSimLogger.log(
					Level.FINE,
					"ProduktionsController.hubAbgeholt()",
					e,
					false,
					false);
				thread.interrupt();
				return;
			}
			try
			{
				apLager = dao.getArbeitsplatzLager(ap.getNr(), HUB, "Ausgang");
			}
			catch (WiSimDAOException e2)
			{
				wiSimLogger.log("ProduktionsController.hubAbgeholt()", e2);
			}
		}
		// Hub wurde abgeholt
	}

	/** Simuliert, wie fertige Teilhubs in den Ausgang gelegt werden
   * @param thread Der aktuelle Thread
   * @param ap Arbeitsplatz
   * @param time Die aktuelle Zeit
   */
	public synchronized void hubFertig(
		Arbeitsplatz ap,
		int time,
		ProduktionsSimulationThread thread)
	{
		try
		{

			ArbeitsplatzLager aplAusgang =
				dao.getArbeitsplatzLager(ap.getNr(), HUB, "Ausgang");
			ArbeitsplatzLager aplEingang =
				dao.getArbeitsplatzLager(ap.getNr(), HUB, "Eingang");

			int kapazitaetAusgang =
				aplAusgang.getMaxBestand() - aplAusgang.getBestand();
			int bestandEingang = aplEingang.getBestand();
			int anzArbeiter = ap.getAnzahlArbeiter();
			int anzahlHubs = anzArbeiter;
			if (kapazitaetAusgang < anzahlHubs)
			{
				anzahlHubs = kapazitaetAusgang;
			}
			int anzahlHubsOhneVorgaenger = anzahlHubs;
			if (bestandEingang < anzahlHubs)
				anzahlHubs = bestandEingang;

			if (ap.getVorgaenger()[0] != 0)
			{
				Collection arbeitsplatzLager = null;
				try
				{
					arbeitsplatzLager =
						(Collection) dao.getArbeitsplatzLager(
							ap.getNr(),
							"Einzelteil");
				}
				catch (WiSimDAOException e1)
				{
					wiSimLogger.log("ProduktionsController.hubFertig()", e1);
				}
				Iterator it = arbeitsplatzLager.iterator();
				while (it.hasNext())
				{
					this.einzelteilVerbrauch(
						(ArbeitsplatzLager) it.next(),
						time,
						anzahlHubs,
						thread);
				}

				dao.setEinzelteilArbeitsplatzBestand(
					ap.getNr(),
					HUB,
					(anzahlHubs * -1),
					"Eingang");
				dao.setEinzelteilArbeitsplatzBestand(
					ap.getNr(),
					HUB,
					anzahlHubs,
					"Ausgang");
				notifyAll();
			}
			else
			{
				Collection arbeitsplatzLager = null;
				try
				{
					arbeitsplatzLager =
						(Collection) dao.getArbeitsplatzLager(
							ap.getNr(),
							"Einzelteil");
				}
				catch (WiSimDAOException e1)
				{
					wiSimLogger.log("ProduktionsController.hubFertig()", e1);
				}
				Iterator it = arbeitsplatzLager.iterator();
				while (it.hasNext())
				{
					this.einzelteilVerbrauch(
						(ArbeitsplatzLager) it.next(),
						time,
						anzahlHubsOhneVorgaenger,
						thread);
				}

				dao.setEinzelteilArbeitsplatzBestand(
					ap.getNr(),
					HUB,
					anzahlHubsOhneVorgaenger,
					"Ausgang");
				notifyAll();
			}
		}
		catch (WiSimDAOWriteException e)
		{
			wiSimLogger.log("ProduktionsController.hubFertig()", e);
		}
		catch (WiSimDAOException e)
		{
			wiSimLogger.log("ProduktionsController.hubFertig()", e);
		}
	}

	/** Simuliert wie fertige Hubs ins Lager gelegt werden
   * @param name Name des Thread
   * @param ap Arbeitsplatz
   */
	public synchronized void hubKomplett(String name, Arbeitsplatz ap)
	{
		try
		{
			try
			{
				ArbeitsplatzLager apl =
					dao.getArbeitsplatzLager(ap.getNr(), HUB, "Ausgang");

				boolean status =
					dao.setArtikelLagerBestand(1, apl.getBestand());
				if (status)
				{
					dao.setEinzelteilArbeitsplatzBestand(
						ap.getNr(),
						HUB,
						(apl.getBestand() * -1),
						"Ausgang");
				}
				notifyAll();
			}
			catch (WiSimDAOException e)
			{
				wiSimLogger.log("ProduktionsController.hubKomplett()", e);
			}
		}
		catch (WiSimDAOWriteException e)
		{
			wiSimLogger.log("ProduktionsController.hubKomplett()", e);
		}
	}

  /** F�llt das Eingangslager auf, wenn das Ausgangslager voll ist.
   * @param name Der Name des Thread
   * @param ap Arbeitsplatz
   */  
	public synchronized void fuelleEingangslagerAuf(
		String name,
		Arbeitsplatz ap)
	{
		try
		{
			ArbeitsplatzLager eingangsLager =
				dao.getArbeitsplatzLager(ap.getNr(), HUB, "Eingang");
			if (eingangsLager.getBestand() < eingangsLager.getMaxBestand())
			{
				Collection apLagerVorgaenger = (Collection) new Vector();
				for (int i = 0; i < ap.getVorgaenger().length; i++)
				{
					apLagerVorgaenger.add(
						dao.getArbeitsplatzLager(
							ap.getVorgaenger()[i],
							HUB,
							"Ausgang"));
				}
				boolean isLeer = false;
				Iterator it = apLagerVorgaenger.iterator();
				while (it.hasNext())
				{
					ArbeitsplatzLager apLager = (ArbeitsplatzLager) it.next();
					if (apLager.getBestand() == 0)
					{
						isLeer = true;
					}
				}
				if (!isLeer)
				{
					it = null;
					it = apLagerVorgaenger.iterator();
					ArbeitsplatzLager apl = (ArbeitsplatzLager) it.next();
					int minBestand = apl.getBestand();

					while (it.hasNext())
					{
						apl = (ArbeitsplatzLager) it.next();
						if (apl.getBestand() < minBestand)
						{
							minBestand = apl.getBestand();
						}
					}
					int eingangsBestand = eingangsLager.getBestand();
					int maxEingangsBestand = eingangsLager.getMaxBestand();
					if ((maxEingangsBestand - eingangsBestand) < minBestand)
					{
						minBestand = maxEingangsBestand - eingangsBestand;
					}
					try
					{
						if (minBestand < 10)
						{
							dao.setEinzelteilArbeitsplatzBestand(
								ap.getNr(),
								HUB,
								minBestand,
								"Eingang");
							for (int i = 0; i < ap.getVorgaenger().length; i++)
							{
								dao.setEinzelteilArbeitsplatzBestand(
									ap.getVorgaenger()[i],
									HUB,
									(minBestand * -1),
									"Ausgang");
							}
							notifyAll();
						}
						else
						{
							dao.setEinzelteilArbeitsplatzBestand(
								ap.getNr(),
								HUB,
								10,
								"Eingang");
							for (int i = 0; i < ap.getVorgaenger().length; i++)
							{
								dao.setEinzelteilArbeitsplatzBestand(
									ap.getVorgaenger()[i],
									HUB,
									-10,
									"Ausgang");
							}
							notifyAll();
						}
					}
					catch (WiSimDAOWriteException e)
					{
						wiSimLogger.log(
							"ProduktionsController.fuelleEingangslagerAuf()",
							e);
					}
				}
			}
		}
		catch (WiSimDAOException e)
		{
			wiSimLogger.log(
				"ProduktionsController.fuelleEingangslagerAuf()",
				e);
		}
	}
}
