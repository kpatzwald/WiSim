/*
 * WiSimDAO.java
 *
 * Created on 09. February 2003, 11:50
 *
 */
package net.sourceforge.wisim.dao;

import java.util.Collection;
import java.util.Vector;
import java.util.Hashtable;

import net.sourceforge.wisim.model.Arbeitsplatz;
import net.sourceforge.wisim.model.Artikel;
import net.sourceforge.wisim.model.ArbeitsplatzLager;
import net.sourceforge.wisim.model.AuftragsPosition;
import net.sourceforge.wisim.model.Auftragsrechnung;
import net.sourceforge.wisim.model.Einzelteil;
import net.sourceforge.wisim.model.Einzelteilauftrag;
import net.sourceforge.wisim.model.Einzelteilauftragsrechnung;
import net.sourceforge.wisim.model.Kunde;
import net.sourceforge.wisim.model.Lieferant;
import net.sourceforge.wisim.model.Lieferliste;
import net.sourceforge.wisim.model.Notiz;
import net.sourceforge.wisim.model.Ort;
import net.sourceforge.wisim.model.Vertrag;

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
  public int neuerKunde(Kunde kunde) throws WiSimDAOException, WiSimDAOWriteException;

  /** method to get all customer
   * @return a <code>Collection</code> object containing all customers
   * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
   */
  public Collection getKunden() throws WiSimDAOException;
  
  /** Gibt eine Auftragsrechnung eines Auftrages zurück.
   * @param atrNr Auftragrechnungs Nummer
   * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
   * @return Auftragsrechnung
   */    
   public Auftragsrechnung getAuftragsrechnung(int atrNr) throws WiSimDAOException;
  
  /** gibt alle Auftragsrechnungen aus
   * @return Collection von Auftragsrechnung
   * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
   */
  public Collection getAuftragsrechnungen() throws WiSimDAOException;

  /** Holt einen Kunden aus der Datenbank
   * @return Object Kunde
   * @param kdNr Kunden Nummer
   * @throws WiSimDAOException Fehler beim Abfragen der DB
   */
  public Kunde getKunde(int kdNr) throws WiSimDAOException;  
  
  /** Aendert Kundendaten
   * @param kunde the object, containing the changed data of a customer
   * @throws com.pixelpark.wisim.dao.WiSimDAOException if a database problem occurs or the connection was never initialized
   * @throws com.pixelpark.wisim.dao.WiSimDAOWriteException if there was a db constaint violation
   * @return int
   */
  public int aendereKunden(Kunde kunde) throws WiSimDAOException, WiSimDAOWriteException;  
  
    /** Markiert einen Kunden als geloescht bzw sichtbar
     * @throws com.pixelpark.wisim.dao.WiSimDAOException if a database problem occurs or the connection was never initialized
     * @throws com.pixelpark.wisim.dao.WiSimDAOWriteException if there was a db constaint violation
     * @return int
     * @param KdNr Kunden ID
     * @param status Kundenstatus
     */
  public int setKundenLoeschStatus(int KdNr, boolean status) throws WiSimDAOException, WiSimDAOWriteException;  
  
  /** Diese Funktion speichert einen neuen Lieferanten in der Datenbank. Wenn der
   * angegebene Ort noch nicht vorhanden ist, wird er angelegt.
   * @return int Lieferantennummer des neuen Lieferant oder 0 wenn ein Fehler aufgetreten ist.
   * @param lieferant Objekt: Lieferant
   * @throws WiSimDAOWriteException Fehler beim Schreiben in die DB
   * @throws com.pixelpark.wisim.dao.WiSimDAOException if a database problem occurs or the connection was never initialized
   */
  public int neuerLieferant(Lieferant lieferant) throws WiSimDAOException, WiSimDAOWriteException;

  /** Liest einen Lieferanten aus der DB aus
   * @return com.pixelpark.wisim.model.Lieferant oder null wenn kein Lieferant mit der übergebenen Nummer existiert.
   * @param lieferantenId LieferantenNr
   * @throws com.pixelpark.wisim.dao.WiSimDAOException if a database problem occurs or the connection was never initialized
   */
  public Lieferant getLieferant(int lieferantenId) throws WiSimDAOException;

   /** Aendert die Werte eines Lieferanten aus der DB
    * @param lieferant beinhaltet die geaenderten Daten des Lieferanten
    * @throws com.pixelpark.wisim.dao.WiSimDAOException if a database problem occurs or the connection was never initialized
    * @throws com.pixelpark.wisim.dao.WiSimDAOWriteException if there was a db constaint violation
    * @return Integer
    */
  public int aendereLieferant(Lieferant lieferant) throws WiSimDAOException, WiSimDAOWriteException;
  
  /** Erstellung einer neuen Notiz
   * @param notiz Objekt: Notiz
   * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
   * @throws WiSimDAOWriteException if a database problem occurs or the connection was never initialized
   */
  public void neueNotiz(Notiz notiz) throws WiSimDAOException, WiSimDAOWriteException;

  /** Laden aller Notizen eines Kunden zurück
   * @return Collection mit allen Notizen zu einem Kunden
   * @param KdNr Kunden Nummer
   * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
   */
  public Collection getNotizen(int KdNr) throws WiSimDAOException;

  /** Laden einer Notiz
   * @return Object Notiz
   * @param noteNr Notiz Nummer
   * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
   */  
  public Notiz getNotiz(int noteNr) throws WiSimDAOException;  
  

  /** Loeschen einer Notiz
   * @throws WiSimDAOException if there was a db constaint violation
   * @return int
   * @param noteNr Objekt
   */  
  public int delNotiz(int noteNr) throws WiSimDAOWriteException; 
  
  /** Gibt das Objekt Ort zurück das zu der Postleitzahl gehört
   * @return Ort der zu der PLZ gehört
   * @param plz Postleitzahl des Ortes
   * @throws WiSimDAOException ToDo
   */
  public Ort getOrt(int plz) throws WiSimDAOException;

  /** Erstellt einen neuen Ort in der DB
   * @param ort Ort
   * @throws WiSimDAOWriteException Fehler beim Schreiben in die DB
   * @throws WiSimDAOException ToDo
   * @return Die ID des neuen Ortes
   */
  public int neuerOrt(Ort ort) throws WiSimDAOException, WiSimDAOWriteException;


  /** Collection mit allen Lieferanten
   * @throws WiSimDAOException Fehler beim Abfragen der DB
   * @return Collection mit Objekten vom Typ Lieferant
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
  
  /** Holt alle Einzelteil aus der Datenbank
   * @return Collection
   * @throws WiSimDAOException ToDo
   */
  public Collection getEinzelteile() throws WiSimDAOException;

  /** Holt ein Einzelteil aus der Datenbank
   * @return Einzelteil
   * @param id Einzelteile Nummer
   * @throws WiSimDAOException ToDo
   */
  public Einzelteil getEinzelteil(int id) throws WiSimDAOException;

  /** Erstellt eine Lieferliste
   * @throws WiSimDAOWriteException if there was a db constaint violation
   * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
   * @param liste Lieferliste
   */
  public void setLieferliste(Lieferliste liste) throws WiSimDAOException, WiSimDAOWriteException;

  /** Löscht einen Lieferlisten-Eintrag aus der Datenbank
   * @return int
   * @param ltId Lieferanten Id
   * @param etNr Einzelteil ID
   * @throws WiSimDAOWriteException if there was a db constaint violation
   */
  public int loescheLieferliste(int ltId, int etNr) throws WiSimDAOWriteException;
  
  /** Holt einen Lieferlisten-Eintrag aus der Datenbank
   * @return Lieferliste
   * @param lieferantenID Lieferanten ID
   * @param einzelteilID Einzelteil ID
   * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
   */
  public Lieferliste getLieferliste(int lieferantenID, int einzelteilID) throws WiSimDAOException;

  /** Collection mit allen Einzelteilen die ein Lieferant liefert.
   * @param lieferantenID LieferantenNr
   * @throws WiSimDAOException Fehler beim Abfragen der DB
   * @return Collection mit Objekten des Typs Einzeiteil
   */
  public Collection getLieferliste(int lieferantenID) throws WiSimDAOException;

/** Diese Funktion verringert den Bestand eines Einzelteils an einem
* bestimmten Arbeitsplatz um die Anzahl der benötigten Teil pro Arbeitszeit.
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
public boolean setEinzelteilArbeitsplatzBestand(int arbeitsplatzNr, int einzelteilNr, String lagerTyp)
	throws WiSimDAOException, WiSimDAOWriteException;

/** Diese Funktion verringert / erhöht den Bestand eines Einzelteils an einem
* bestimmten Arbeitsplatz.
 * @param arbeitsplatzNr
 * @param einzelteilNr
 * @param anzahl
 * @param lagerTyp
 * @throws WiSimDAOWriteException
 * @throws WiSimDAOException
 * @return int Anzahl der tatsächlich in die DB geschrieben Einzelteile.
 */
public int setEinzelteilArbeitsplatzBestand(
	int arbeitsplatzNr,
	int einzelteilNr,
	int anzahl,
	String lagerTyp)
	throws WiSimDAOWriteException, WiSimDAOException;

      /** Liefert die Dauer eines Arbeitsschrittes an einem bestimmten Arbeitsplatz
     * @param arbeitsplatzNr Nr. des Arbeitsplatzes, für dem die Dauer zurückgegeben werden soll.
     * @throws WiSimDAOException Wenn ein Fehler beim Zugriff auf die DB auftritt.
     * @return Dauer des Prozesses
     */    
    public Arbeitsplatz getArbeitsplatz(int arbeitsplatzNr) throws WiSimDAOException;
	
    /** Liefert alle Einzelteile an einem Arbeitsplatz zurück.
    * @param arbeitsplatzNr Nummer des Arbeitsplatz
    * @throws WiSimDAOException Wenn ein Fehler beim Zugriff auf die DB auftritt.
    * @return Liste mit Einzelteilen
    */  
    public Collection getArbeitsplatzLager(int arbeitsplatzNr) throws WiSimDAOException;
        
    /** Erstellt einen neuen Einzelteileauftrag
     * @param einzelteilauftrag Der Einzelteilauftrag.
     * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
     * @throws WiSimDAOWriteException if a database problem occurs or the connection was never initialized
     * @return EinzelteilauftragsNrID des Einzelteilauftrags
     */
    public int setEinzelteilauftrag(Einzelteilauftrag einzelteilauftrag) throws WiSimDAOException, WiSimDAOWriteException;

    /** Erstellt eine neue EinzelteilAuftragsRechnung
     * @param einzelteilauftragsrechnung Die Einzelteilauftragsrechnung.
     * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
     * @throws WiSimDAOWriteException if a database problem occurs or the connection was never initialized
     * @return EinzelteilauftragsrechnungsNr
     */
    public int setEinzelteilauftragsrechnung(Einzelteilauftragsrechnung einzelteilauftragsrechnung) throws WiSimDAOException, WiSimDAOWriteException;
    
     /** Erstellt eine neue AuftragsRechnung
     * @param Die Auftragsrechnung
     * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
     * @throws WiSimDAOWriteException if a database problem occurs or the connection was never initialized
     * @return AuftragsrechnungsNr
     */
    public int setAuftragsrechnung(Auftragsrechnung auftragsrechnung) throws WiSimDAOException, WiSimDAOWriteException;
    
    
    /** Holt alle Artikel aus der Datenbank
     * @return Collection
     * @throws WiSimDAOException ToDo
     */
    public Collection getAlleArtikel() throws WiSimDAOException;
  
    /** Holt ein Artikel aus der Datenbank
     * @return Artikel
     * @param id
     * @throws WiSimDAOException ToDo
     */
    public Artikel getArtikel(int id) throws WiSimDAOException;
    
    /** Legt einen neuen Vertrag an
     * @param vertrag
     * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
     * @throws WiSimDAOWriteException if a database problem occurs or the connection was never initialized
     * @return Vertragsnummer
     */
    public int setNeuenVertrag(Vertrag at) throws WiSimDAOException, WiSimDAOWriteException;
  
    
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
    
    
    /** Gibt die Einzelteilauftragsrechnung zurück die zu dem entsprechenden
     * Einzelteilauftrag gehört.
     * @param etatrNr Einzelteilauftrags Nummer
     * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
     * @return Die Einzelteilauftragsrechnung.
     */    
    public Einzelteilauftragsrechnung getEinzelteilauftragsrechnung(int etatrNr) throws WiSimDAOException;

    /** Gibt alle Lagerplätz des Lagers aus
     * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
     * @return Collection aller Lagerplätze
     */    
    public Collection getLagerplaetze() throws WiSimDAOException;
    
    /** Gibt eine Liste aller Einzelteile die sich im Lager befinden zurück.
     * Jeder Eintrag hat neben den Informationen Einzelteil-Name auch die Zahlen für
     * Bestand, Mindestbestand und MaxBestand.
     * Jedes Einzelteil kommt nur einmal vor. Befindt sich ein Einzelteil auf mehrern
     * Lagerplätzen, so werden die Bestände entsprechend summiert.
     * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
     * @return Vector mit allen Einzelteilen die sich im Lager befinden.
     */    
    public Vector getEinzelteilLagerElement() throws WiSimDAOException;
    
    /** Gibt eine Liste aller Einzelteile die sich auf dem angegebenen Lagerplatz befinden zurück.
     * Jeder Eintrag hat neben den Informationen Einzelteil-Name auch die Zahlen für
     * Bestand, Mindestbestand und MaxBestand.
     * @param lagerplatz Der Lagerplatz
     * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
     * @return Collection mit allen Einzelteilen des Lagerplatzes
     */    
    public Collection getEinzelteilLagerElement(String lagerplatz) throws WiSimDAOException;
    
	/** Liefert alle Lagerplätze zurück, die einen bestimmten Artikel enthalten
         * @return Collection mit den Lagerplätzen wo das Einzelteil liegt.
         * @param etNr Einzelteil Nummer
         * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
         */
	public Collection getLagerplaetze(int etNr) throws WiSimDAOException;
        
        /** Gibt eine Liste aller Artikel die sich im Lager befinden zurück.
         * Jeder Eintrag hat neben den Informationen Artikel-Name auch die Zahlen für
         * Bestand, Mindestbestand und MaxBestand.
         * Jeder Artikel kommt nur einmal vor. Befindt sich ein Artikel auf mehrern
         * Lagerplätzen, so werden die Bestände entsprechend summiert.
         * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
         * @return Vector mit allen Artikeln die im Lager sind.
         */        
        public Vector getArtikelLagerElement() throws WiSimDAOException;
        
        /** Gibt eine Liste aller Artikel die sich auf dem angegebenen Lagerplatz befinden zurück.
         * Jeder Eintrag hat neben den Informationen Artikel-Name auch die Zahlen für
         * Bestand, Mindestbestand und MaxBestand.
         * @param lagerplatz Der Lagerplatz
         * @throws WiSimDAOException if a database problem occurs or the connection was never initialized
         * @return Collection mit allen Artikeln die auf dem Lagerplatz liegen
         */        
        public Collection getArtikelLagerElement(String lagerplatz) throws WiSimDAOException;
        
        /** Erhöht / Erniedrigt den Bestand eines Einzelteils im Lager.
         * @param etNr Einzelteil Nummer
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
         * @param artNr Artikel Nummer
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
		 * @return ArbeitsplatzLager
		 * @throws WiSimDAOException
		 */
		public ArbeitsplatzLager getArbeitsplatzLager(int arbeitsplatzNr, int einzelteilNr, String lagerTyp) throws WiSimDAOException;
        
	/**
	 * @param arbeitsplatzNr
	 * @param lagerTyp
	 * @return Collection
	 * @throws WiSimDAOException
	 */
	public Collection getArbeitsplatzLager(int arbeitsplatzNr, String lagerTyp) throws WiSimDAOException;

        /** Gibt Stueckliste für einen bestimmten Artikel zurück. Der Key der Hashtable ist
         * die Einzelteil-Nummer, der Value ist die erforderliche Menge um 1 Stück von
         * diesem Artikel zu produzieren.
         * @param artNr Artikel Nummer
         * @throws WiSimDAOException if an database error occurs
         * @return Hashtable (Stückliste)
         */        
        public Hashtable getStueckliste(int artNr) throws WiSimDAOException;
        
        /** Holt alle Verträge aus der Datenbank
         * @return Collection
         * @throws WiSimDAOException
         */
        public Collection getVertraege() throws WiSimDAOException;
        
        /** Holt einen Vertrag aus der Datenbank
         * @return Vertrag
         * @throws WiSimDAOException
         * @param int id
         */
        public Vertrag getVertrag(int id) throws WiSimDAOException;
        
        /** Gibt eine Position eines Auftrages zurück.
     	* @param atNr Auftrags Nummer
     	* @throws WiSimDAOException if a database problem occurs or the connection was never initialized
     	* @return Auftragsposition
     	*/    
     	public AuftragsPosition getAuftragsPosition(int atNr) throws WiSimDAOException;
        
        /** Gibt die Auftragsposition zurück die zu dem entsprechenden
     	* Auftrag gehört.
     	* @param atNr Auftrags Nummer
     	* @param artNr Artikel Nummer
     	* @throws WiSimDAOException if a database problem occurs or the connection was never initialized
     	* @return Die Auftragsposition.
     	*/     
     	public AuftragsPosition getAuftragsPosition(int atNr, int artNr) throws WiSimDAOException;
        
        /** Gibt die Bestellmenge eines bestimmten Artikels in einem Vertrag zurück.
         * @param atNr Auftrags Nr.
         * @param artNr Artikel Nr.
         * @throws WiSimDAOException if an database error occurs
         * @return Bestellmenge
         */  
        public int getVertragsPositionMenge(int atNr, int artNr) throws WiSimDAOException;
        
        /** Setzt die Position eines bestimmten Auftrags in einem Vertrag.
         * @param AuftragsPosition atp.
         * @throws WiSimDAOException if an database error occurs
         * @return -1
         */  
        public int setAuftragsPosition(AuftragsPosition atp) throws WiSimDAOWriteException;

        /** Setzt die Zahl der Arbeiter für einen Arbeitsplatz
         * @param apNr Arbeitsplatz Nummer
         * @param anzahl Anzahl der Mitarbeiter
         * @throws WiSimDAOWriteException If an error occurs
         */        
        public void setArbeiterZahl(int apNr, int anzahl) throws WiSimDAOWriteException;
        
        
        /** Setzt Rechnungsstatus auf bezahlt
         * @param Nr Auftragsrechnung Nummer
         * @param status
         * @throws WiSimDAOWriteException If an error occurs
         */ 
        public int aendereAuftragsrechnung(int Nr, boolean status) throws WiSimDAOException, WiSimDAOWriteException;
        
	/** Anzahl der Arbeitsplätze
	 * @return int Anzahl
	 * @throws WiSimDAOException
	 */
	public int getAnzahlArbeitsplätze() throws WiSimDAOException;
}
