package net.sourceforge.wisim.controller;

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
public class ActualTime
{

	private boolean newMinute;
	private Date actDate;
	private GregorianCalendar actDateGC;

	/** Creates a new instance of ActualTime */
	public ActualTime()
	{
		newMinute = false;
		actDate =
			new Date(new GregorianCalendar(2003, 8, 1, 0, 0).getTimeInMillis());
		//Standard-Start-Datum: 1.9.2003
		actDateGC = new GregorianCalendar();
	}

	/** Inkrementiert die aktuelle Minute. */
	public synchronized void increaseTime()
	{
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
	public synchronized Date getDate(UpdateSimulationsauswertung thread)
	{
		while (newMinute == false)
		{
			try
			{
				wait();
			}
			catch (InterruptedException e)
			{
				thread.interrupt();
				break;
			}
		}
		newMinute = false;
		return actDate;
	}
}
