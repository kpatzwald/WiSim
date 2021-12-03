/*   ********************************************************************   **
**   Copyright notice                                                       **
**                                                                          **
**   (c) 2003 WiSim Development Team					                              **
**   https://github.com/kpatzwald/WiSim   			                                  **
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
 * WiSimDAO.java
 *
 * Created on 09. February 2003, 11:50
 *
 */
package net.sourceforge.wisim.dao;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Vector;

import net.sourceforge.wisim.model.Article;
import net.sourceforge.wisim.model.City;
import net.sourceforge.wisim.model.ComponentContract;
import net.sourceforge.wisim.model.ComponentContractAccount;
import net.sourceforge.wisim.model.Contract;
import net.sourceforge.wisim.model.ContractAccount;
import net.sourceforge.wisim.model.Customer;
import net.sourceforge.wisim.model.Memo;
import net.sourceforge.wisim.model.OrderItem;
import net.sourceforge.wisim.model.Supplier;
import net.sourceforge.wisim.model.SupplyList;
import net.sourceforge.wisim.model.WiSimComponent;
import net.sourceforge.wisim.model.WorkPlace;
import net.sourceforge.wisim.model.WorkPlaceStore;

/** Interface for accessing persitent data, saved in a sql database.
 *
 * @author Kay Patzwald
 */
public interface WiSimDAO {

	/** method to create a new customer
	 * @param kunde Objekt: kunde
	 * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
	 * @throws WiSimDAOWriteException if a database problem occurs or the connection was never initialized
	 * @return KundenNr
	 */
	public int neuerKunde(Customer kunde) throws WiSimDAOException, WiSimDAOWriteException;

	/** method to get all customer
	 * @return a <code>Collection</code> object containing all customers
	 * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
	 */
	public Collection getKunden() throws WiSimDAOException;

	/** Gibt eine ContractAccount eines Auftrages zurück.
	 * @param atrNr Auftragrechnungs Nummer
	 * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
	 * @return ContractAccount
	 */
	public ContractAccount getAuftragsrechnung(int atrNr) throws WiSimDAOException;

	/** gibt alle Auftragsrechnungen aus
	 * @return Collection von ContractAccount
	 * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
	 */
	public Collection getAuftragsrechnungen() throws WiSimDAOException;

	/** Holt einen Kunden aus der Datenbank
	 * @return Object Customer
	 * @param kdNr Kunden Nummer
	 * @throws WiSimDAOException Fehler beim Abfragen der DB
	 */
	public Customer getKunde(int kdNr) throws WiSimDAOException;

	/** Aendert Kundendaten
	 * @param kunde the object, containing the changed data of a customer
	 * @throws com.pixelpark.wisim.dao.WiSimDAOException if a database problem occurs or the connection was never initialized
	 * @throws com.pixelpark.wisim.dao.WiSimDAOWriteException if there was a db constaint violation
	 * @return int
	 */
	public int aendereKunden(Customer kunde) throws WiSimDAOException, WiSimDAOWriteException;

	/** Markiert einen Kunden als geloescht bzw sichtbar
	 * @throws com.pixelpark.wisim.dao.WiSimDAOException if a database problem occurs or the connection was never initialized
	 * @throws com.pixelpark.wisim.dao.WiSimDAOWriteException if there was a db constaint violation
	 * @return int
	 * @param KdNr Kunden ID
	 * @param status Kundenstatus
	 */
	public int setKundenLoeschStatus(int KdNr, boolean status) throws WiSimDAOException, WiSimDAOWriteException;

	/** Diese Funktion speichert einen neuen Lieferanten in der Datenbank. Wenn der
	 * angegebene City noch nicht vorhanden ist, wird er angelegt.
	 * @return int Lieferantennummer des neuen Supplier oder 0 wenn ein Fehler aufgetreten ist.
	 * @param lieferant Objekt: Supplier
	 * @throws WiSimDAOWriteException Fehler beim Schreiben in die DB
	 * @throws com.pixelpark.wisim.dao.WiSimDAOException if a database problem occurs or the connection was never initialized
	 */
	public int neuerLieferant(Supplier lieferant) throws WiSimDAOException, WiSimDAOWriteException;

	/** Liest einen Lieferanten aus der DB aus
	 * @return com.pixelpark.wisim.model.Supplier oder null wenn kein Supplier mit der übergebenen Nummer existiert.
	 * @param lieferantenId LieferantenNr
	 * @throws com.pixelpark.wisim.dao.WiSimDAOException if a database problem occurs or the connection was never initialized
	 */
	public Supplier getLieferant(int lieferantenId) throws WiSimDAOException;

	/** Aendert die Werte eines Lieferanten aus der DB
	 * @param lieferant beinhaltet die geaenderten Daten des Lieferanten
	 * @throws com.pixelpark.wisim.dao.WiSimDAOException if a database problem occurs or the connection was never initialized
	 * @throws com.pixelpark.wisim.dao.WiSimDAOWriteException if there was a db constaint violation
	 * @return Integer
	 */
	public int aendereLieferant(Supplier lieferant) throws WiSimDAOException, WiSimDAOWriteException;

	/** Erstellung einer neuen Memo
	 * @param notiz Objekt: Memo
	 * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
	 * @throws WiSimDAOWriteException if a database problem occurs or the connection was never initialized
	 */
	public void neueNotiz(Memo notiz) throws WiSimDAOException, WiSimDAOWriteException;

	/** Laden aller Notizen eines Kunden zurück
	 * @return Collection mit allen Notizen zu einem Kunden
	 * @param KdNr Kunden Nummer
	 * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
	 */
	public Collection getNotizen(int KdNr) throws WiSimDAOException;

	/** Laden einer Memo
	 * @return Object Memo
	 * @param noteNr Memo Nummer
	 * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
	 */
	public Memo getNotiz(int noteNr) throws WiSimDAOException;

	/** Loeschen einer Memo
	 * @throws WiSimDAOException if there was a db constaint violation
	 * @return int
	 * @param noteNr Objekt
	 */
	public int delNotiz(int noteNr) throws WiSimDAOWriteException;

	/** Gibt das Objekt City zurück das zu der Postleitzahl gehört
	 * @return City der zu der PLZ gehört
	 * @param plz Postleitzahl des Ortes
	 * @throws WiSimDAOException ToDo
	 */
	public City getOrt(int plz) throws WiSimDAOException;

	/** Erstellt einen neuen City in der DB
	 * @param ort City
	 * @throws WiSimDAOWriteException Fehler beim Schreiben in die DB
	 * @throws WiSimDAOException ToDo
	 * @return Die ID des neuen Ortes
	 */
	public int neuerOrt(City ort) throws WiSimDAOException, WiSimDAOWriteException;

	/** Collection mit allen Lieferanten
	 * @throws WiSimDAOException Fehler beim Abfragen der DB
	 * @return Collection mit Objekten vom Typ Supplier
	 */
	public Collection getLieferanten() throws WiSimDAOException;

	/** Markiert einen Lieferanten als geloescht bzw sichtbar
	 * @throws com.pixelpark.wisim.dao.WiSimDAOException if a database problem occurs or the connection was never initialized
	 * @throws com.pixelpark.wisim.dao.WiSimDAOWriteException if there was a db constaint violation
	 * @return int
	 * @param LtNr Lieferanten ID
	 * @param status Lieferantenstatus
	 */
	public int setLieferantLoeschStatus(int LtNr, boolean status) throws WiSimDAOException, WiSimDAOWriteException;

	/** Holt alle WiSimComponent aus der Datenbank
	 * @return Collection
	 * @throws WiSimDAOException ToDo
	 */
	public Collection getEinzelteile() throws WiSimDAOException;

	/** Holt ein WiSimComponent aus der Datenbank
	 * @return WiSimComponent
	 * @param id Einzelteile Nummer
	 * @throws WiSimDAOException ToDo
	 */
	public WiSimComponent getEinzelteil(int id) throws WiSimDAOException;

	/** Erstellt eine SupplyList
	 * @throws WiSimDAOWriteException if there was a db constaint violation
	 * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
	 * @param liste SupplyList
	 */
	public void setLieferliste(SupplyList liste) throws WiSimDAOException, WiSimDAOWriteException;

	/** Löscht einen Lieferlisten-Eintrag aus der Datenbank
	 * @return int
	 * @param ltId Lieferanten Id
	 * @param etNr WiSimComponent ID
	 * @throws WiSimDAOWriteException if there was a db constaint violation
	 */
	public int loescheLieferliste(int ltId, int etNr) throws WiSimDAOWriteException;

	/** Holt einen Lieferlisten-Eintrag aus der Datenbank
	 * @return SupplyList
	 * @param lieferantenID Lieferanten ID
	 * @param einzelteilID WiSimComponent ID
	 * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
	 */
	public SupplyList getLieferliste(int lieferantenID, int einzelteilID) throws WiSimDAOException;

	/** Collection mit allen Einzelteilen die ein Supplier liefert.
	 * @param lieferantenID LieferantenNr
	 * @throws WiSimDAOException Fehler beim Abfragen der DB
	 * @return Collection mit Objekten des Typs Einzeiteil
	 */
	public Collection getLieferliste(int lieferantenID) throws WiSimDAOException;

	/** Diese Funktion verringert den Bestand eines Einzelteils an einem
	* bestimmten WorkPlace um die Anzahl der benötigten Teil pro Arbeitszeit.
	 * @param arbeitsplatzNr
	 * @param einzelteilNr
	 * @param lagerTyp
	 * @throws WiSimDAOException
	 * @throws WiSimDAOWriteException
	 * @return boolean
	 * True: Bestand wurde erniedrigt.
	 * False:
	 * Der Bestand dieses Einzelteils ist schon auf 0.
	 */
	public boolean setEinzelteilArbeitsplatzBestand(int arbeitsplatzNr, int einzelteilNr, String lagerTyp) throws WiSimDAOException, WiSimDAOWriteException;

	/** Diese Funktion verringert / erhöht den Bestand eines Einzelteils an einem
	* bestimmten WorkPlace.
	 * @param arbeitsplatzNr
	 * @param einzelteilNr
	 * @param anzahl
	 * @param lagerTyp
	 * @throws WiSimDAOWriteException
	 * @throws WiSimDAOException
	 * @return int Anzahl der tatsächlich in die DB geschrieben Einzelteile.
	 */
	public int setEinzelteilArbeitsplatzBestand(int arbeitsplatzNr, int einzelteilNr, int anzahl, String lagerTyp) throws WiSimDAOWriteException, WiSimDAOException;

	/** Liefert die Dauer eines Arbeitsschrittes an einem bestimmten WorkPlace
	* @param arbeitsplatzNr Nr. des Arbeitsplatzes, für dem die Dauer zurückgegeben werden soll.
	* @throws WiSimDAOException Wenn ein Fehler beim Zugriff auf die DB auftritt.
	* @return Dauer des Prozesses
	*/
	public WorkPlace getArbeitsplatz(int arbeitsplatzNr) throws WiSimDAOException;

	/** Liefert alle Einzelteile an einem WorkPlace zurück.
	* @param arbeitsplatzNr Nummer des WorkPlace
	* @throws WiSimDAOException Wenn ein Fehler beim Zugriff auf die DB auftritt.
	* @return Liste mit Einzelteilen
	*/
	public Collection getArbeitsplatzLager(int arbeitsplatzNr) throws WiSimDAOException;

	/** Erstellt einen neuen Einzelteileauftrag
	 * @param einzelteilauftrag Der ComponentContract.
	 * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
	 * @throws WiSimDAOWriteException if a database problem occurs or the connection was never initialized
	 * @return EinzelteilauftragsNrID des Einzelteilauftrags
	 */
	public int setEinzelteilauftrag(ComponentContract einzelteilauftrag) throws WiSimDAOException, WiSimDAOWriteException;

	/** Erstellt eine neue EinzelteilAuftragsRechnung
	 * @param einzelteilauftragsrechnung Die ComponentContractAccount.
	 * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
	 * @throws WiSimDAOWriteException if a database problem occurs or the connection was never initialized
	 * @return EinzelteilauftragsrechnungsNr
	 */
	public int setEinzelteilauftragsrechnung(ComponentContractAccount einzelteilauftragsrechnung) throws WiSimDAOException, WiSimDAOWriteException;

	/** Erstellt eine neue AuftragsRechnung
	* @param Die ContractAccount
	* @throws WiSimDAOException if a database problem occurs or the connection was never initialized
	* @throws WiSimDAOWriteException if a database problem occurs or the connection was never initialized
	* @return AuftragsrechnungsNr
	*/
	public int setAuftragsrechnung(ContractAccount auftragsrechnung) throws WiSimDAOException, WiSimDAOWriteException;

	/** Holt alle Article aus der Datenbank
	 * @return Collection
	 * @throws WiSimDAOException ToDo
	 */
	public Collection getAlleArtikel() throws WiSimDAOException;

	/** Holt ein Article aus der Datenbank
	 * @return Article
	 * @param id
	 * @throws WiSimDAOException ToDo
	 */
	public Article getArtikel(int id) throws WiSimDAOException;

	/** Legt einen neuen Contract an
	 * @param vertrag
	 * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
	 * @throws WiSimDAOWriteException if a database problem occurs or the connection was never initialized
	 * @return Vertragsnummer
	 */
	public int setNeuenVertrag(Contract at) throws WiSimDAOException, WiSimDAOWriteException;

	/** Gibt den MwSt-Satz aus der Datenbank zurück.
	 * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
	 * @return Mehrwertsteuer-Satz
	 */
	public float getMwSt() throws WiSimDAOException;

	/** Gibt eine Liste aller Einzelteilaufträge zurück.
	 * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
	 * @return Vector mit allen Einzelteilaufträgen
	 */
	public Vector getEinzelteilauftraege() throws WiSimDAOException;

	/** Gibt alle Positionen eines Einzelteilauftrages zurück.
	 * @param etatNr Einzelteilauftrags Nummer
	 * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
	 * @return Collection mit allen Einzelteilauftragspositionen
	 */
	public Collection getEinzelteilAuftragsPositionen(int etatNr) throws WiSimDAOException;

	/** Gibt alle Positionen eines Auftrages zurück.
	 * @param atNr Auftrags Nummer
	 * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
	 * @return Collection mit allen Auftragspositionen
	 */
	public Collection getAuftragsPositionen(int atNr) throws WiSimDAOException;

	/** Gibt die ComponentContractAccount zurück die zu dem entsprechenden
	 * ComponentContract gehört.
	 * @param etatrNr Einzelteilauftrags Nummer
	 * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
	 * @return Die ComponentContractAccount.
	 */
	public ComponentContractAccount getEinzelteilauftragsrechnung(int etatrNr) throws WiSimDAOException;

	/** Gibt alle Lagerplätz des Lagers aus
	 * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
	 * @return Collection aller Lagerplätze
	 */
	public Collection getLagerplaetze() throws WiSimDAOException;

	/** Gibt eine Liste aller Einzelteile die sich im Lager befinden zurück.
	 * Jeder Eintrag hat neben den Informationen WiSimComponent-Name auch die Zahlen für
	 * Bestand, Mindestbestand und MaxBestand.
	 * Jedes WiSimComponent kommt nur einmal vor. Befindt sich ein WiSimComponent auf mehrern
	 * Lagerplätzen, so werden die Bestände entsprechend summiert.
	 * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
	 * @return Vector mit allen Einzelteilen die sich im Lager befinden.
	 */
	public Vector getEinzelteilLagerElement() throws WiSimDAOException;

	/** Gibt eine Liste aller Einzelteile die sich auf dem angegebenen WarehouseLocation befinden zurück.
	 * Jeder Eintrag hat neben den Informationen WiSimComponent-Name auch die Zahlen für
	 * Bestand, Mindestbestand und MaxBestand.
	 * @param lagerplatz Der WarehouseLocation
	 * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
	 * @return Collection mit allen Einzelteilen des Lagerplatzes
	 */
	public Collection getEinzelteilLagerElement(String lagerplatz) throws WiSimDAOException;

	/** Liefert alle Lagerplätze zurück, die einen bestimmten Article enthalten
	     * @return Collection mit den Lagerplätzen wo das WiSimComponent liegt.
	     * @param etNr WiSimComponent Nummer
	     * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
	     */
	public Collection getLagerplaetze(int etNr) throws WiSimDAOException;

	/** Gibt eine Liste aller Article die sich im Lager befinden zurück.
	 * Jeder Eintrag hat neben den Informationen Article-Name auch die Zahlen für
	 * Bestand, Mindestbestand und MaxBestand.
	 * Jeder Article kommt nur einmal vor. Befindt sich ein Article auf mehrern
	 * Lagerplätzen, so werden die Bestände entsprechend summiert.
	 * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
	 * @return Vector mit allen Artikeln die im Lager sind.
	 */
	public Vector getArtikelLagerElement() throws WiSimDAOException;

	/** Gibt eine Liste aller Article die sich auf dem angegebenen WarehouseLocation befinden zurück.
	 * Jeder Eintrag hat neben den Informationen Article-Name auch die Zahlen für
	 * Bestand, Mindestbestand und MaxBestand.
	 * @param lagerplatz Der WarehouseLocation
	 * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
	 * @return Collection mit allen Artikeln die auf dem WarehouseLocation liegen
	 */
	public Collection getArtikelLagerElement(String lagerplatz) throws WiSimDAOException;

	/** Erhöht / Erniedrigt den Bestand eines Einzelteils im Lager.
	 * @param etNr WiSimComponent Nummer
	 * @param menge neue Menge = aktuelleMenge + menge
	 * Das heißt, wenn eine negative Menge angegeben wird, so erniedrigt man den Bestand.
	 * @throws WiSimDAOWriteException if a database problem occurs or the connection was never initialized
	 * @return True: Bestand wurde erhöht / erniedrigt.
	 * False:
	 * Bei Erhöhung: Gelieferte Menege übertrifft den Maximal Bestand. Der neue Bestand
	 * ist jetzt der Maximal Bestand, der Rest der Lieferung wird ignoriert.
	 * Bei Erniedrigung:
	 * Der Bestand dieses Einzelteils ist schon auf 0.
	 */
	public int setEinzelteilLagerBestand(int etNr, int menge) throws WiSimDAOWriteException;

	/** Erhöht / Erniedrigt den Bestand eines Artikels im Lager.
	 * @param artNr Article Nummer
	 * @param menge neue Menge = aktuelleMenge + menge
	 * Das heißt, wenn eine negative Menge angegeben wird, so erniedrigt man den Bestand.
	 * @throws WiSimDAOException wenn ein Problem beim Lesen der DB auftritt
	 * @throws WiSimDAOWriteException if a database problem occurs or the connection was never initialized
	 * @return True: Bestand wurde erhöht / erniedrigt.
	 * False:
	 * Bei Erhöhung: Gelieferte Menege übertrifft den Maximal Bestand. Der neue Bestand
	 * ist jetzt der Maximal Bestand, der Rest der Lieferung wird ignoriert.
	 * Bei Erniedrigung:
	 * Der Bestand dieses Artikels ist schon auf 0.
	 */
	public boolean setArtikelLagerBestand(int artNr, int menge) throws WiSimDAOWriteException, WiSimDAOException;

	/** Liefert eine Liste aller Arbeitsplaetze.
	 * @throws WiSimDAOException if an error occurs
	 * @return Vector mit allen Arbeitsplaetzen
	 */
	public Vector getArbeitsplaetze() throws WiSimDAOException;

	/**
	 * @param arbeitsplatzNr
	 * @param einzelteilNr
	 * @param lagerTyp
	 * @return WorkPlaceStore
	 * @throws WiSimDAOException
	 */
	public WorkPlaceStore getArbeitsplatzLager(int arbeitsplatzNr, int einzelteilNr, String lagerTyp) throws WiSimDAOException;

	/**
	 * @param arbeitsplatzNr
	 * @param lagerTyp
	 * @return Collection
	 * @throws WiSimDAOException
	 */
	public Collection getArbeitsplatzLager(int arbeitsplatzNr, String lagerTyp) throws WiSimDAOException;

	/** Gibt Stueckliste für einen bestimmten Article zurück. Der Key der Hashtable ist
	 * die WiSimComponent-Nummer, der Value ist die erforderliche Menge um 1 Stück von
	 * diesem Article zu produzieren.
	 * @param artNr Article Nummer
	 * @throws WiSimDAOException if an database error occurs
	 * @return Hashtable (Stückliste)
	 */
	public Hashtable getStueckliste(int artNr) throws WiSimDAOException;

	/** Holt alle Verträge aus der Datenbank
	 * @return Collection
	 * @throws WiSimDAOException
	 */
	public Collection getVertraege() throws WiSimDAOException;

	/** Holt einen Contract aus der Datenbank
	 * @return Contract
	 * @throws WiSimDAOException
	 * @param int id
	 */
	public Contract getVertrag(int id) throws WiSimDAOException;

	/** Gibt eine Position eines Auftrages zurück.
		* @param atNr Auftrags Nummer
		* @throws WiSimDAOException if a database problem occurs or the connection was never initialized
		* @return Auftragsposition
		*/
	public OrderItem getAuftragsPosition(int atNr) throws WiSimDAOException;

	/** Gibt die Auftragsposition zurück die zu dem entsprechenden
		* Auftrag gehört.
		* @param atNr Auftrags Nummer
		* @param artNr Article Nummer
		* @throws WiSimDAOException if a database problem occurs or the connection was never initialized
		* @return Die Auftragsposition.
		*/
	public OrderItem getAuftragsPosition(int atNr, int artNr) throws WiSimDAOException;

	/** Gibt die Bestellmenge eines bestimmten Artikels in einem Contract zurück.
	 * @param atNr Auftrags Nr.
	 * @param artNr Article Nr.
	 * @throws WiSimDAOException if an database error occurs
	 * @return Bestellmenge
	 */
	public int getVertragsPositionMenge(int atNr, int artNr) throws WiSimDAOException;

	/** Setzt die Position eines bestimmten Auftrags in einem Contract.
	 * @param OrderItem atp.
	 * @throws WiSimDAOException if an database error occurs
	 * @return -1
	 */
	public int setAuftragsPosition(OrderItem atp) throws WiSimDAOWriteException;

	/** Setzt die Zahl der Arbeiter für einen WorkPlace
	 * @param apNr WorkPlace Nummer
	 * @param anzahl Anzahl der Mitarbeiter
	 * @throws WiSimDAOWriteException If an error occurs
	 */
	public void setArbeiterZahl(int apNr, int anzahl) throws WiSimDAOWriteException;

	/** Setzt Rechnungsstatus auf bezahlt
	 * @param Nr ContractAccount Nummer
	 * @param status
	 * @throws WiSimDAOWriteException If an error occurs
	 */
	public int aendereAuftragsrechnung(int Nr, boolean status) throws WiSimDAOException, WiSimDAOWriteException;

	/** Anzahl der Arbeitsplätze
	 * @return int Anzahl
	 * @throws WiSimDAOException if an error occurs
	 */
	public int getAnzahlArbeitsplaetze() throws WiSimDAOException;
	
	/**
	 * Holt eine Liste mit NetzplanElementen aus der DB
	 * @return Collection aller Vorgaenge (NetzplanElemente)
	 * @throws WiSimDAOException If an error occurs
	 */
	public Vector getNetworkplanElements() throws WiSimDAOException;
	
	/**
	 * Update a workplace
	 * @param workplace
	 * @throws WiSimDAOWriteException If an error occurs
	 */
	public void updateWorkplace(WorkPlace workplace) throws WiSimDAOWriteException;
	
	/** 
	 * Resets the db
	 * 
	 * @throws WiSimDAOException If an error occurs
	 */
	public void dbReset() throws WiSimDAOException;
	
	/** Resets the simulation and related db-relations
	 * 
	 * @throws WiSimDAOException
	 */
	public void simulationReset() throws WiSimDAOException;
	
	public Hashtable getEtCapacity() throws WiSimDAOException;
}