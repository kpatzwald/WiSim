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
 * Created on 05.03.2003
 *
 */
package net.sourceforge.wisim.model;

/** WorkPlace
 * @author Kay Patzwald
 */
public class WorkPlace
{
	private int nr;
	private String beschreibung;
	private int dauer;
	private int[] vorgaenger;
	private int[] nachfolger;
	private int anzahlArbeiter;

	/** Creates a new instance of WorkPlace
	 * 
	 */
	public WorkPlace()
	{
	}

	/** Creates a new instance of WorkPlace
   * @param nr Nummer
   * @param beschreibung Beschreibung
   * @param dauer Dauer
   */
	public WorkPlace(int nr, String beschreibung, int dauer)
	{
		this.nr = nr;
		this.beschreibung = beschreibung;
		this.dauer = dauer;
	}

	/** Creates a new instance of WorkPlace
   * @param nr Nr
   * @param beschreibung Beschreibung
   * @param dauer Dauer
   * @param vorgaenger Vorgänger
   * @param nachfolger Nachfolger
   */
	public WorkPlace(
		int nr,
		String beschreibung,
		int dauer,
		int[] vorgaenger,
		int[] nachfolger)
	{
		this.nr = nr;
		this.beschreibung = beschreibung;
		this.dauer = dauer;
		this.vorgaenger = vorgaenger;
		this.nachfolger = nachfolger;
	}

	/** Creates a new instance of WorkPlace
   * @param nr Nr
   * @param beschreibung Beschreibung
   * @param dauer Dauer
   * @param vorgaenger Vorgänger
   * @param nachfolger Nachfolger
   * @param anzahlArbeiter Anzahl der Arbeiter
   */
	public WorkPlace(
		int nr,
		String beschreibung,
		int dauer,
		int[] vorgaenger,
		int[] nachfolger,
		int anzahlArbeiter)
	{
		this.nr = nr;
		this.beschreibung = beschreibung;
		this.dauer = dauer;
		this.vorgaenger = vorgaenger;
		this.nachfolger = nachfolger;
		this.anzahlArbeiter = anzahlArbeiter;
	}

	/** Creates a new instance of WorkPlace
   * @param nr Nr
   * @param beschreibung Beschreibung
   * @param dauer Dauer
   * @param anzahlArbeiter Anzahl der Arbeiter
   */
	public WorkPlace(
		int nr,
		String beschreibung,
		int dauer,
		int anzahlArbeiter)
	{
		this.nr = nr;
		this.beschreibung = beschreibung;
		this.dauer = dauer;
		this.anzahlArbeiter = anzahlArbeiter;
	}

	/** Liefert die Beschreibung zurück
   * @return String
   */
	public String getBeschreibung()
	{
		return beschreibung;
	}

	/** Liefert die Dauer
   * @return int
   */
	public int getDauer()
	{
		return dauer;
	}

	/** Liefert die Nummer des Arbeitsplatzes
   * @return int
   */
	public int getNr()
	{
		return nr;
	}

	/**
	 * Sets the beschreibung.
	 * @param beschreibung The beschreibung to set
	 */
	public void setBeschreibung(String beschreibung)
	{
		this.beschreibung = beschreibung;
	}

	/**
	 * Sets the dauer.
	 * @param dauer The dauer to set
	 */
	public void setDauer(int dauer)
	{
		this.dauer = dauer;
	}

	/**
	 * Sets the nr.
	 * @param nr The nr to set
	 */
	public void setNr(int nr)
	{
		this.nr = nr;
	}

	/** Liefert eine Liste der Nachfolger
   * @return int[]
   */
	public int[] getNachfolger()
	{
		return nachfolger;
	}

	/** Liefert eine Liste der Vorgänger
   * @return int[]
   */
	public int[] getVorgaenger()
	{
		return vorgaenger;
	}

	/**
	 * Sets the nachfolger.
	 * @param nachfolger The nachfolger to set
	 */
	public void setNachfolger(int[] nachfolger)
	{
		this.nachfolger = nachfolger;
	}

	/**
	 * Sets the vorgaenger.
	 * @param vorgaenger The vorgaenger to set
	 */
	public void setVorgaenger(int[] vorgaenger)
	{
		this.vorgaenger = vorgaenger;
	}
	/** Liefert die Anzahl der Arbeiter
   * @return int
   */
	public int getAnzahlArbeiter()
	{
		return anzahlArbeiter;
	}

	/**
	 * Sets the anzahlArbeiter.
	 * @param anzahlArbeiter The anzahlArbeiter to set
	 */
	public void setAnzahlArbeiter(int anzahlArbeiter)
	{
		this.anzahlArbeiter = anzahlArbeiter;
	}

}
