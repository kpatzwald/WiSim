package net.sourceforge.wisim.controller;
import java.util.Collection;
import java.util.Vector;
import java.util.Iterator;

/*
 * Created on 25.05.2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */

/**
 * @author Benjamin Pasero
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class NetzplanElement {

	private int nummer;
	private double dauer;
	private int[] vorgaenger;
	private int[] nachfolger;

	private double faz;
	private double fez;
	private double saz;
	private double sez;
	private double gp;
	private double fp;
	
	private String bezeichnung;

	private Collection vorgaengerBasket;

	public NetzplanElement(int nummer, double dauer, int[] nachfolger, String bezeichnung) {
		this.nummer = nummer;
		this.dauer = dauer;
		this.nachfolger = nachfolger;
		vorgaengerBasket = new Vector();
		this.bezeichnung = bezeichnung;
	}

	/**
	 * @return
	 */
	public double getDauer() {
		return dauer;
	}

	/**
	 * @param d
	 */
	public void setDauer(double d) {
		dauer = d;
	}

	/**
	 * @return
	 */
	public double getFaz() {
		return faz;
	}

	/**
	 * @return
	 */
	public double getFez() {
		return fez;
	}

	/**
	 * @return
	 */
	public double getFp() {
		return fp;
	}

	/**
	 * @return
	 */
	public double getGp() {
		return gp;
	}

	/**
	 * @return
	 */
	public double getSaz() {
		return saz;
	}

	/**
	 * @return
	 */
	public double getSez() {
		return sez;
	}

	/**
	 * @param d
	 */
	public void setFaz(double d) {
		faz = d;
	}

	/**
	 * @param d
	 */
	public void setFez(double d) {
		fez = d;
	}

	/**
	 * @param d
	 */
	public void setFp(double d) {
		fp = d;
	}

	/**
	 * @param d
	 */
	public void setGp(double d) {
		gp = d;
	}

	/**
	 * @param d
	 */
	public void setSaz(double d) {
		saz = d;
	}

	/**
	 * @param d
	 */
	public void setSez(double d) {
		sez = d;
	}

	/**
	 * @return
	 */
	public int getNummer() {
		return nummer;
	}

	/**
	 * @param i
	 */
	public void setNummer(int i) {
		nummer = i;
	}

	/**
	 * @return
	 */
	public int[] getVorgaenger() {
		return vorgaenger;
	}

	/**
	 * @param is
	 */
	public void setVorgaenger(int[] is) {
		vorgaenger = is;
	}

	/**
	 * @return
	 */
	public int[] getNachfolger() {
		return nachfolger;
	}

	/**
	 * @param is
	 */
	public void setNachfolger(int[] is) {
		nachfolger = is;
	}

	/**
	 * @return
	 */
	public Collection getVorgaengerBasket() {
		return vorgaengerBasket;
	}

	/**
	 * @param collection
	 */
	public void setVorgaengerBasket(Collection collection) {
		vorgaengerBasket = collection;
	}

	public void addIntoVorgaengerBasket(Integer vorgaenger) {
		vorgaengerBasket.add(vorgaenger);
	}

	public void getFromVorgaengerBasket() {
		Iterator vorgaengerBasketIt = vorgaengerBasket.iterator();
		vorgaenger = new int[vorgaengerBasket.size()];
		int i = 0;
		while (vorgaengerBasketIt.hasNext()) {
			vorgaenger[i] = ((Integer) vorgaengerBasketIt.next()).intValue();
			i++;
		}
	}

	/**
	 * @return
	 */
	public String getBezeichnung() {
		return bezeichnung;
	}

	/**
	 * @param string
	 */
	public void setBezeichnung(String string) {
		bezeichnung = string;
	}
	
	public boolean isStartElem() {
		if (vorgaenger[0] == 0)
			return true;
		else
			return false;
	}
	
	public boolean isEndElem() {
			if (nachfolger[0] == 0)
				return true;
			else
				return false;
		}

}
