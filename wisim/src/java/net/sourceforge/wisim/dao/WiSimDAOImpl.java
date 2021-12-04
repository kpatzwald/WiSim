/*   ********************************************************************    **
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

 /*
 * WiSimDAOImpl.java
 *
 * Created on 09. February 2003, 11:56
 */
package net.sourceforge.wisim.dao;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import net.sourceforge.wisim.model.Article;
import net.sourceforge.wisim.model.City;
import net.sourceforge.wisim.model.ComponentContract;
import net.sourceforge.wisim.model.ComponentContractInvoice;
import net.sourceforge.wisim.model.ComponentContractItem;
import net.sourceforge.wisim.model.ComponentWarehouseItem;
import net.sourceforge.wisim.model.Contract;
import net.sourceforge.wisim.model.ContractInvoice;
import net.sourceforge.wisim.model.ContractOrderItem;
import net.sourceforge.wisim.model.Customer;
import net.sourceforge.wisim.model.Memo;
import net.sourceforge.wisim.model.Supplier;
import net.sourceforge.wisim.model.SupplyList;
import net.sourceforge.wisim.model.WarehouseLocation;
import net.sourceforge.wisim.model.WiSimComponent;
import net.sourceforge.wisim.model.WorkPlace;
import net.sourceforge.wisim.model.WorkPlaceStore;
import net.sourceforge.wisim.networkplan.NetworkplanElement;

/**
 * Implements the interface <code>CWiSimDAO</code> to work with an mySQL
 * database.
 *
 * @author Kay Patzwald
 */
public class WiSimDAOImpl implements WiSimDAO, WiSimAuthentificationDAO {

  /**
   * Authentification-Informations
   *
   */
  private String hostName;
  private String port;
  private String dbName; //durch die Applikation vorgegeben
  private String user;
  private String password;

  /**
   * a java.util.Logger for all logging in this package...
   */
  private static final Logger logger = Logger.getLogger("com.pixelpark.wisim.controller");

  /**
   * Database-connection for the DAO-Object
   */
  private Connection conn;

  /**
   * Initializing the Database-connetcion with Database-Driver, user and
   * password...
   *
   * @throws net.sourceforge.wisim.dao.WiSimDAOException
   */
  protected void initialize() throws WiSimDAOException {

    //PLAF wird fï¿½r die JOptionPanes gesetzt
    com.incors.plaf.kunststoff.KunststoffLookAndFeel plaf = new com.incors.plaf.kunststoff.KunststoffLookAndFeel();

    try {
      UIManager.setLookAndFeel(plaf);
    } catch (UnsupportedLookAndFeelException e) {
      //TODO Fehler loggen
    }

    hostName = "";
    port = "";
    dbName = "wisim"; //durch die Applikation vorgegeben
    user = "";
    password = "";

    logger.finest("com.pixelpark.wisim.dao.WiSimDAOImpl.initialize() Action: start");
    // Serverlog
    try {
      // Load the MySQl JDBC driver
      DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());

      //Datei mit DB Infos wird eingelesen
      String result = "";
      try {
        FileInputStream file = new FileInputStream("config.dat");
        DataInputStream in = new DataInputStream(file);

        Base64.Decoder decoder = Base64.getDecoder();
        byte[] b = decoder.decode(in.readAllBytes());
        in.close();
        result = new String(b);
      } //Die Datei ist noch nicht vorhanden und wird angelegt mit den Defaultwerten
      catch (IOException e) {
        try {
          String defaultValues = "localhost\n3306\nroot";
          Encoder encoder = Base64.getEncoder();
          defaultValues = encoder.encodeToString(defaultValues.getBytes());
          File file = new File("config.dat");
          FileWriter fw = new FileWriter(file);
          fw.write(defaultValues);
          fw.close();
          result = "localhost\n3306\nroot";
        } catch (IOException ioE) {
        }
      }

      Pattern p = Pattern.compile("\n");
      String[] daten = p.split(result);

      //Manipulation verhindern
      if (daten.length < 3 || daten.length > 4) {
        result = "localhost\n3306\nroot";
        daten = p.split(result);
      }

      //Variablen bekommen die Werte aus der "config.dat"
      hostName = daten[0].trim();

      port = daten[1].trim();

      user = daten[2].trim();

      //Wurde kein Passwort gesetzt, so ist dieses ein Leerstring
      if (daten.length < 4) {
        password = "";
      } else {
        password = daten[3].trim();
      }

      // Connect to the database
      // You must put a database name after the @ sign in the connection URL.
      // You can use either the fully specified SQL*net syntax or a short cut
      // syntax as <host>:<port>:<sid>.
      //Zunächst wird überprüft, ob eine Datenbank mit dem Namen "wisim" vorhanden ist
      conn = DriverManager.getConnection("jdbc:mysql://" + hostName + ":" + port + "/?useUnicode=true&characterEncoding=utf-8", user, password);
      conn.setAutoCommit(false);

      boolean wisimExists = false;

      try {
        // Create a Statement
        Statement stmt = conn.createStatement();
        String sql = "SHOW DATABASES";
        ResultSet rset = stmt.executeQuery(sql);

        while (rset.next()) {
          if (rset.getString("Database").equals("wisim")) {
            wisimExists = true;
            break;
          }
        }

      } catch (SQLException sqlE) {
      }

      //Die Datenbank muss aufgesetzt werden
      if (!wisimExists) {

        int x = JOptionPane.showConfirmDialog(null, "Die Datenbank \"wisim\" wurde nicht gefunden!\n" + "Soll diese angelegt werden?", "Datenbank anlegen", JOptionPane.YES_NO_OPTION);
        if (x == 0) {
          // Create a Statement
          Statement stmt = conn.createStatement();
          String sql = "CREATE DATABASE wisim";
          stmt.execute(sql);

          URL url = getClass().getResource("/sql/complete.sql");

          try {
            InputStream in = url.openStream();
            DataInputStream data = new DataInputStream(in);

            byte buffer[] = new byte[in.available()];
            data.readFully(buffer);
            in.close();

            result = new String(buffer, 0, buffer.length);
            p = Pattern.compile("\n");

            String[] anweisungen = p.split(result);
            int n = 0;
            for (int i = 0; i < anweisungen.length; i++) {
              if (anweisungen[i].startsWith("#") || anweisungen[i].toCharArray().length == 1) {
                anweisungen[i] = null;
                n++;
              }
            }

            String[] queries = new String[anweisungen.length - n];
            int j = 0;
            for (String anweisungen1 : anweisungen) {
              if (anweisungen1 != null) {
                queries[j] = anweisungen1;
                j++;
              }
            }

            int i = 0;
            int m = 1;
            while (i < queries.length) {
              if (queries[i].startsWith(" ")) {
                queries[i - m] = queries[i - m].concat(queries[i]);
                m++;
                queries[i] = null;
              } else if (queries[i].startsWith(")")) {
                queries[i - m] = queries[i - m].concat(queries[i]);
                queries[i] = null;
              } else {
                m = 1;
              }
              i++;
            }

            int a = 0;
            for (String querie : queries) {
              if (querie == null) {
                a++;
              }
            }

            String[] queriesFinal = new String[queries.length - a];
            int c = 0;
            for (String querie : queries) {
              if (querie != null) {
                queriesFinal[c] = querie;
                c++;
              }
            }

            for (int z = 0; z < queriesFinal.length; z++) {
              char ersetzen[] = queriesFinal[z].toCharArray();
              for (int e = 0; e < ersetzen.length; e++) {
                if (ersetzen[e] == ';' || ersetzen[e] == '\n') {
                  ersetzen[e] = ' ';
                }
              }
              queriesFinal[z] = String.valueOf(ersetzen);
            }

            try {
              // Load the MySQl JDBC driver
              DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());

              // Connect to the database
              // You must put a database name after the @ sign in the connection URL.
              // You can use either the fully specified SQL*net syntax or a short cut
              // syntax as <host>:<port>:<sid>.
              conn = DriverManager.getConnection("jdbc:mysql://" + hostName + ":" + port + "/" + dbName + "?useUnicode=true&characterEncoding=utf-8", user, password);
              conn.setAutoCommit(false);

              try {

                stmt = conn.createStatement();
                for (String queriesFinal1 : queriesFinal) {
                  stmt.executeUpdate(queriesFinal1);
                }

              } catch (SQLException e) {

              }
            } catch (SQLException sqlE) {

            }

          } catch (IOException e) {

          }
          JOptionPane.showMessageDialog(null, "Die Datenbank wurde erfolgreich aufgesetzt!");
        } else {
          JOptionPane.showMessageDialog(null, "Ohne Datenbank läuft diese Applikation nicht!", "Fehler", JOptionPane.ERROR_MESSAGE);
          System.exit(1);
        }
      }

      conn = DriverManager.getConnection("jdbc:mysql://" + hostName + ":" + port + "/" + dbName + "?useUnicode=true&characterEncoding=utf-8", user, password);
      conn.setAutoCommit(false);
    } //Bei der Verbindung tritt ein Problem auf und der Benutzer wird gebeten die
    //DB-Einstellungen zu ï¿½berprï¿½fen (mit JOptionPanes)
    catch (SQLException sqlE) {
      JOptionPane.showMessageDialog(null, "MySQL meldet:\n" + sqlE.getMessage(), "Fehler beim Verbinden mit Datenbank", JOptionPane.ERROR_MESSAGE);

      String nachricht = "Verbindung zu der Datenbank konnte nicht hergestellt werden!\n" + "Bitte überprüfen Sie die vorhandenen Zugangsdaten.\n" + "(Zum Beenden bitte auf \"Abbrechen\" klicken)\n\n";

      //IP muss evtl. geï¿½ndert werden
      hostName = JOptionPane.showInputDialog(nachricht + "Schritt 1) IP überprüfen: ", hostName);
      if (hostName == null) {
        System.exit(1);
      }
      if (hostName.equals("")) {
        hostName = "localhost";
      }

      //Port muss evtl. geändert werden
      port = JOptionPane.showInputDialog(nachricht + "Schritt 2) Port überprüfen (Standard: 3306): ", port);
      if (port == null) {
        System.exit(1);
      }
      if (port.equals("")) {
        port = "3306";
      }

      //Username muss evtl. geändert werden
      user = JOptionPane.showInputDialog(nachricht + "Schritt 3) Benutzername überprüfen: ", user);
      if (user == null) {
        System.exit(1);
      }
      if (user.equals("")) {
        user = "root";
      }

      //PW muss evtl. geändert werden
      password = JOptionPane.showInputDialog(nachricht + "Schritt 4) Passwort überprüfen (wird nicht angezeigt!): ");
      if (password == null) {
        System.exit(1);
      }

      //Die Daten werden wieder in die "config.dat" geschrieben
      String values = hostName + "\n" + port + "\n" + user + "\n" + password;
      byte[] b = values.getBytes();
      Encoder encoder = Base64.getEncoder();
      values = encoder.encodeToString(b);

      try {
        File file = new File("config.dat");
        FileWriter fw = new FileWriter(file);
        fw.write(values);
        fw.close();
      } catch (IOException e) {
      }

      //Es wird erneut eine Verbindung aufgebaut
      initialize();
    }
  }

  /**
   * Method to create a new customer
   *
   * @param customer Object: customer
   * @throws WiSimDAOException if a database problem occurs or the connection
   * was never initialized
   * @throws WiSimDAOWriteException if a database problem occurs or the
   * connection was never initialized
   * @return KundenNr
   */
  @Override
  public int newCustomer(Customer customer) throws WiSimDAOException, WiSimDAOWriteException {
    // Serverlog
    logger.finest("com.pixelpark.wisim.dao.WiSimDAOImpl.createContract(Contract) Action: start");
    String sql;
    int key = -1;
    try {
      try {
        //Update der Tabelle: Ort
        Statement stmt = conn.createStatement();

        // Create a Statement
        //Statement stmt = conn.createStatement();
        // update statement for record in table contract
        // Neuer Kunde wird angelegt
        sql
                = "insert into kd "
                + "(kd_Name, kd_Vorname, kd_Firma, kd_Strasse, f_ort_Nr, kd_Telefon, kd_Fax, kd_Email, kd_Typ) values (\""
                + customer.getNachname()
                + "\", \""
                + customer.getVorname()
                + "\", \""
                + customer.getFirma()
                + "\", \""
                + customer.getStrasse()
                + "\", \""
                + customer.getPlzId()
                + "\", \""
                + customer.getTelefon()
                + "\", \""
                + customer.getFax()
                + "\", \""
                + customer.getEmail()
                + "\", \""
                + customer.getKundentyp()
                + "\") ";

        stmt.executeUpdate(sql);

        ResultSet lastKey = stmt.getGeneratedKeys();
        while (lastKey.next()) {
          key = lastKey.getInt(1);
        }
      } catch (SQLException sqlE) {
        conn.rollback();
        throw new WiSimDAOWriteException(sqlE.getMessage());
      }
    } catch (SQLException sqlE) {
      throw new WiSimDAOException(sqlE.getMessage());
    }
    return key;
  }

  /**
   * Holt alle Kunden aus der Datenbank
   *
   * @return ArrayList All customers
   * @throws WiSimDAOException Fehler beim Abfragen der DB
   */
  @Override
  public ArrayList<Customer> getCustomers() throws WiSimDAOException {
    String sql = "SELECT * FROM kd WHERE kd_deleted = 'FALSE'";
    ArrayList<Customer> customers = new ArrayList<Customer>();
    try {
      // Create a Statement
      Statement stmt = conn.createStatement();
      ResultSet rset = stmt.executeQuery(sql);
      City ort;

      while (rset.next()) {
        Customer customer = new Customer();
        customer.setId(rset.getInt("kd_Nr"));
        customer.setKundentyp(rset.getString("kd_Typ"));
        customer.setAnspruch(rset.getString("kd_Anspruch"));
        customer.setZahlungsmoral(rset.getString("kd_Zahlungsmoral"));
        customer.setFirma(rset.getString("kd_Firma"));
        customer.setVorname(rset.getString("kd_Vorname"));
        customer.setNachname(rset.getString("kd_Name"));
        customer.setStrasse(rset.getString("kd_Strasse"));
        customer.setEmail(rset.getString("kd_Email"));
        customer.setTelefon(rset.getString("kd_Telefon"));
        customer.setFax(rset.getString("kd_Fax"));
        customer.setPlzId(rset.getInt("f_ort_Nr"));
        //Kunden-Ort und PLZ wird aus Tabelle city geladen
        ort = getOrt(rset.getInt("f_ort_Nr"));
        customer.setPlz(ort.getPlz());
        customer.setOrt(ort.getName());
        customers.add(customer);
      }

    } catch (SQLException sqlE) {
      throw new WiSimDAOException(sqlE.getMessage());
    }
    return customers;
  }

  /**
   * Gibt alle Auftragsrechnungen aus
   *
   * @return Collection&lt;ContractInvoice&gt;
   * @throws WiSimDAOException if a database problem occurs or the connection
   * was never initialized
   */
  @Override
  public Collection<ContractInvoice> getAuftragsrechnungen() throws WiSimDAOException {
    String sql = "SELECT * FROM atr";
    Collection<ContractInvoice> atrechnungen = new ArrayList<>();
    try {
      // Create a Statement
      Statement stmt = conn.createStatement();
      ResultSet rset = stmt.executeQuery(sql);

      while (rset.next()) {
        ContractInvoice atr = new ContractInvoice();
        atr.setNr(rset.getInt("atr_Nr"));
        atr.setBetrag(rset.getDouble("atr_Betrag"));
        atr.setAuftragNr(rset.getInt("f_at_Nr"));
        atr.setMwSt(rset.getFloat("f_mwst_Satz"));
        atr.setzEingang(rset.getBoolean("atr_zleingang"));

        atrechnungen.add(atr);
      }

    } catch (SQLException sqlE) {
      throw new WiSimDAOException(sqlE.getMessage());
    }
    return atrechnungen;
  }

  /**
   * Holt einen Kunden aus der Datenbank
   *
   * @return Object Customer
   * @param customerID Customer ID
   * @throws WiSimDAOException Fehler beim Abfragen der DB
   */
  @Override
  public Customer getCustomer(int customerID) throws WiSimDAOException {
    String sql = "SELECT * FROM kd WHERE kd_Nr = " + customerID + "";
    try {
      // Create a Statement
      Statement stmt = conn.createStatement();
      ResultSet rset = stmt.executeQuery(sql);
      City city;
      Customer customer = new Customer();
      while (rset.next()) {
        customer.setId(rset.getInt("kd_Nr"));
        customer.setKundentyp(rset.getString("kd_Typ"));
        customer.setAnspruch(rset.getString("kd_Anspruch"));
        customer.setZahlungsmoral(rset.getString("kd_Zahlungsmoral"));
        customer.setFirma(rset.getString("kd_Firma"));
        customer.setVorname(rset.getString("kd_Vorname"));
        customer.setNachname(rset.getString("kd_Name"));
        customer.setStrasse(rset.getString("kd_Strasse"));
        customer.setEmail(rset.getString("kd_Email"));
        customer.setTelefon(rset.getString("kd_Telefon"));
        customer.setFax(rset.getString("kd_Fax"));
        customer.setPlzId(rset.getInt("f_ort_Nr"));
        //Kunden-Ort und PLZ wird aus Tabelle city geladen
        city = getOrt(rset.getInt("f_ort_Nr"));
        customer.setPlz(city.getPlz());
        customer.setOrt(city.getName());
        return customer;
      }
    } catch (SQLException sqlE) {
      throw new WiSimDAOException(sqlE.getMessage());
    }
    return null;
  }

  /**
   * Aendert Kundendaten
   *
   * @param kunde the object, containing the changed data of a customer
   * @return Boolean
   * @throws net.sourceforge.wisim.dao.WiSimDAOException Fehler beim Abfragen
   * der DB
   * @throws net.sourceforge.wisim.dao.WiSimDAOWriteException
   */
  @Override
  public int aendereKunden(Customer kunde) throws WiSimDAOException, WiSimDAOWriteException {
    // Serverlog
    logger.finest("com.pixelpark.wisim.dao.WiSimDAOImpl.createContract(Contract) Action: start");

    City ort = getOrt(kunde.getPlzId());
    int plz = kunde.getPlzId();

    if (ort == null) {
      ort = new City(kunde.getPlzId(), kunde.getPlz(), kunde.getOrt());
      plz = this.neuerOrt(ort);
    }

    String sql;
    int key = -1;
    try {
      try {

        Statement stmt = conn.createStatement();
        sql = "UPDATE kd SET "
                + "kd_Name = '"
                + kunde.getNachname()
                + "', "
                + "kd_Vorname = '"
                + kunde.getVorname()
                + "', "
                + "kd_Firma = '"
                + kunde.getFirma()
                + "', "
                + "kd_Strasse = '"
                + kunde.getStrasse()
                + "', "
                + "f_ort_Nr = '"
                + plz
                + "', "
                + "kd_Telefon = '"
                + kunde.getTelefon()
                + "', "
                + "kd_Fax = '"
                + kunde.getFax()
                + "', "
                + "kd_Email = '"
                + kunde.getEmail()
                + "', "
                + "kd_Typ = '"
                + kunde.getKundentyp()
                + "', "
                + "kd_Anspruch ='"
                + kunde.getAnspruch()
                + "', "
                + "kd_Zahlungsmoral ='"
                + kunde.getZahlungsmoral()
                + "' "
                + "WHERE kd_Nr = "
                + kunde.getId()
                + "";

        stmt.executeUpdate(sql);
        ResultSet lastKey = stmt.getGeneratedKeys();
        while (lastKey.next()) {
          key = lastKey.getInt(1);
        }
      } catch (SQLException sqlE) {
        conn.rollback();
        throw new WiSimDAOWriteException(sqlE.getMessage());
      }
    } catch (SQLException sqlE) {
      throw new WiSimDAOException(sqlE.getMessage());
    }
    return key;
  }

  /**
   * * Diese Funktion speichert einen neuen Lieferanten in der Datenbank.Wenn
   * der angegebene City noch nicht vorhanden ist, wird er angelegt.
   *
   * @return int Lieferantennummer des neuen Supplier oder 0 wenn ein Fehler
   * aufgetreten ist.
   * @param lieferant Supplier
   * @throws net.sourceforge.wisim.dao.WiSimDAOException Fehler beim Abfragen
   * der DB
   */
  @Override
  public int neuerLieferant(Supplier lieferant) throws WiSimDAOException, WiSimDAOWriteException {
    /* Fï¿½gt den Ort des neuen Lieferanten in die DB ein, wenn dieser noch
		 * nicht in der DB vorhanden ist
     */
    City ort = getOrt(lieferant.getPlzId());
    int plz = lieferant.getPlzId();

    if (ort == null) {
      ort = new City(lieferant.getPlzId(), lieferant.getPlz(), lieferant.getOrt());
      plz = this.neuerOrt(ort);
    }

    String sql
            = "INSERT INTO lt (lt_Name, lt_Vorname, lt_Strasse, lt_Firma"
            + ", lt_Email, lt_Telefon, lt_Fax, lt_deleted, f_ort_Nr, lt_Qualitaet"
            + ", lt_Zuverlaessigkeit) VALUES ('"
            + lieferant.getNachname()
            + "', '"
            + lieferant.getVorname()
            + "', '"
            + lieferant.getStrasse()
            + "', '"
            + lieferant.getFirma()
            + "', '"
            + lieferant.getEmail()
            + "', '"
            + lieferant.getTelefon()
            + "', '"
            + lieferant.getFax()
            + "', 'FALSE', "
            + plz
            + ", '"
            + lieferant.getLieferqualitaet()
            + "', '"
            + lieferant.getZuverlaessigkeit()
            + "')";

    try {
      Statement stmt = conn.createStatement();
      stmt.executeUpdate(sql);

      ResultSet lastKey = stmt.getGeneratedKeys();
      while (lastKey.next()) {
        return lastKey.getInt(1);
      }
      //conn.commit(); //Wird in MySQL 3.x nicht unterstï¿½tzt
    } catch (SQLException e) {
      //conn.rollback(); //Wird in MySQL 3.x nicht unterstï¿½tzt
      throw new WiSimDAOWriteException(e.getMessage());
    }

    return -1;
  }

  /**
   * Liest einen Lieferanten aus der DB aus
   *
   * @return com.pixelpark.wisim.model.Supplier oder null wenn kein Supplier mit
   * der ï¿½bergebenen Nummer existiert.
   * @param lieferantenId LieferantNr
   * @throws net.sourceforge.wisim.dao.WiSimDAOException Fehler beim Abfragen
   * der DB
   */
  @Override
  public Supplier getLieferant(int lieferantenId) throws WiSimDAOException {
    /* Fï¿½gt den Ort des neuen Lieferanten in die DB ein, wenn dieser noch
		 * nicht in der DB vorhanden ist
     */
    Supplier lieferant = null;
    String sql = "SELECT lt_Nr, lt_Name, lt_Vorname, lt_Strasse, lt_Firma," + " lt_Email, lt_Telefon, lt_Fax, lt_deleted, f_ort_Nr, lt_Qualitaet," + "lt_Zuverlaessigkeit FROM lt WHERE lt_Nr = " + lieferantenId;

    try {
      Statement stmt = conn.createStatement();
      ResultSet res = stmt.executeQuery(sql);

      while (res.next()) {
        lieferant = new Supplier();
        lieferant.setId(res.getInt(1));
        lieferant.setNachname(res.getString(2));
        lieferant.setVorname(res.getString(3));
        lieferant.setStrasse(res.getString(4));
        lieferant.setFirma(res.getString(5));
        lieferant.setEmail(res.getString(6));
        lieferant.setTelefon(res.getString(7));
        lieferant.setFax(res.getString(8));
        lieferant.setPlzId(res.getInt(10));
        lieferant.setLieferqualitaet(res.getString(11));
        lieferant.setZuverlaessigkeit(res.getString(12));
      }

      sql = "SELECT ort_Name from ort where ort_Nr = " + lieferant.getPlzId();

      res = stmt.executeQuery(sql);

      while (res.next()) {
        lieferant.setOrt(res.getString(1));
        return lieferant;
      }
      //conn.commit(); //Wird in MySQL 3.x nicht unterstï¿½tzt
    } catch (SQLException e) {
      //conn.rollback(); //Wird in MySQL 3.x nicht unterstï¿½tzt
      throw new WiSimDAOException(e.getMessage());
    }

    return null;
  }

  /**
   * Aendert Lieferantendaten
   *
   * @param lieferant beinhaltet die geaenderten Daten des Lieferanten
   * @return Boolean
   * @throws net.sourceforge.wisim.dao.WiSimDAOException Fehler beim Abfragen
   * der DB
   * @throws net.sourceforge.wisim.dao.WiSimDAOWriteException Fehler beim
   * Schreiben in die DB
   */
  @Override
  public int aendereLieferant(Supplier lieferant) throws WiSimDAOException, WiSimDAOWriteException {
    // Serverlog
    logger.finest("com.pixelpark.wisim.dao.WiSimDAOImpl.createContract(Contract) Action: start");

    City ort = getOrt(lieferant.getPlzId());
    int plz = lieferant.getPlzId();

    if (ort == null) {
      ort = new City(lieferant.getPlzId(), lieferant.getPlz(), lieferant.getOrt());
      plz = this.neuerOrt(ort);
    }

    String sql;
    int key = -1;
    try {
      try {

        Statement stmt = conn.createStatement();
        sql
                = "UPDATE lt SET "
                + "lt_Name = '"
                + lieferant.getNachname()
                + "', "
                + "lt_Vorname = '"
                + lieferant.getVorname()
                + "', "
                + "lt_Firma = '"
                + lieferant.getFirma()
                + "', "
                + "lt_Strasse = '"
                + lieferant.getStrasse()
                + "', "
                + "f_ort_Nr = '"
                + plz
                + "', "
                + "lt_Telefon = '"
                + lieferant.getTelefon()
                + "', "
                + "lt_Fax = '"
                + lieferant.getFax()
                + "', "
                + "lt_Email = '"
                + lieferant.getEmail()
                + "', "
                + "lt_Qualitaet = '"
                + lieferant.getLieferqualitaet()
                + "', "
                + "lt_Zuverlaessigkeit = '"
                + lieferant.getZuverlaessigkeit()
                + "' "
                + "WHERE lt_Nr = "
                + lieferant.getId()
                + "";
        stmt.executeUpdate(sql);
        ResultSet lastKey = stmt.getGeneratedKeys();
        while (lastKey.next()) {
          key = lastKey.getInt(1);
        }
      } catch (SQLException sqlE) {
        conn.rollback();
        throw new WiSimDAOWriteException(sqlE.getMessage());
      }
    } catch (SQLException sqlE) {
      throw new WiSimDAOException(sqlE.getMessage());
    }
    if (key == lieferant.getId()) {
      return key;
    }
    return -1;
  }

  /**
   * Aendert ContractInvoice
   *
   * @return Boolean
   * @param Nr Auftragsrechnungsnummer
   * @param status Status der Rechnung
   * @throws net.sourceforge.wisim.dao.WiSimDAOException Fehler beim Abfragen
   * der DB
   */
  @Override
  public int aendereAuftragsrechnung(int Nr, boolean status) throws WiSimDAOException, WiSimDAOWriteException {
    // Serverlog
    logger.finest("com.pixelpark.wisim.dao.WiSimDAOImpl.createContract(Contract) Action: start");

    String sql;
    int key = -1;
    try {
      try {
        Statement stmt = conn.createStatement();
        sql = "UPDATE atr SET " + "atr_zleingang = '" + status + "' " + "WHERE atr_Nr = " + Nr + "";
        stmt.executeUpdate(sql);
        ResultSet lastKey = stmt.getGeneratedKeys();
        while (lastKey.next()) {
          key = lastKey.getInt(1);
        }
      } catch (SQLException sqlE) {
        conn.rollback();
        throw new WiSimDAOWriteException(sqlE.getMessage());
      }
    } catch (SQLException sqlE) {
      throw new WiSimDAOException(sqlE.getMessage());
    }
    return key;
  }

  /**
   * Markiert einen Lieferanten als geloescht bzw sichtbar
   *
   * @return int
   * @param status Status des Lieferanten
   * @param LtNr the object, containing the changed data of a Supplier
   * @throws net.sourceforge.wisim.dao.WiSimDAOException Fehler beim Abfragen
   * der DB
   * @throws net.sourceforge.wisim.dao.WiSimDAOWriteException Fehler beim
   * Schreiben in die DB
   */
  @Override
  public int setLieferantLoeschStatus(int LtNr, boolean status) throws WiSimDAOException, WiSimDAOWriteException {
    // Serverlog
    logger.finest("com.pixelpark.wisim.dao.WiSimDAOImpl.setLieferantLoeschStatus Action: start");

    String sql;
    int key = -1;
    try {
      try {
        Statement stmt = conn.createStatement();
        sql = "UPDATE lt SET " + "lt_deleted ='" + status + "' " + "WHERE lt_Nr = " + LtNr + "";

        stmt.executeUpdate(sql);
        ResultSet lastKey = stmt.getGeneratedKeys();
        while (lastKey.next()) {
          key = lastKey.getInt(1);
        }
      } catch (SQLException sqlE) {
        conn.rollback();
        throw new WiSimDAOWriteException(sqlE.getMessage());
      }
    } catch (SQLException sqlE) {
      throw new WiSimDAOException(sqlE.getMessage());
    }
    return key;
  }

  /**
   * Gibt eine Collection aller Notizen zurï¿½ck
   *
   * @return Collection mit allen Notizen
   * @param KdNr Kundennummer
   * @throws WiSimDAOException if a database problem occurs or the connection
   * was never initialized
   */
  @Override
  public ArrayList<Memo> getNotizen(int KdNr) throws WiSimDAOException {
    // Serverlog
    logger.finest("com.pixelpark.wisim.dao.WiSimDAOImpl.getNotizen() Action: start");
    String sql;
    ArrayList<Memo> notizen = new ArrayList<>();
    try {
      // Create a Statement
      Statement stmt = conn.createStatement();
      sql = "SELECT * FROM note WHERE f_kd_Nr = " + KdNr + "";
      ResultSet rset = stmt.executeQuery(sql);
      Memo kundennotiz;
      while (rset.next()) {
        kundennotiz = new Memo();
        kundennotiz.setId(rset.getInt(1));
        kundennotiz.setText(rset.getString(2));
        kundennotiz.setDate(rset.getDate(3));
        kundennotiz.setKundenNr(rset.getInt(4));
        notizen.add(kundennotiz);
      }

    } catch (SQLException sqlE) {
      throw new WiSimDAOException(sqlE.getMessage());
    }
    return notizen;
  }

  /**
   * Laden einer Memo
   *
   * @return Object Memo
   * @param noteNr Notiznummer
   * @throws WiSimDAOException if a database problem occurs or the connection
   * was never initialized
   */
  @Override
  public Memo getNotiz(int noteNr) throws WiSimDAOException {
    // Serverlog
    logger.finest("com.pixelpark.wisim.dao.WiSimDAOImpl.createContract(Contract) Action: start");
    //Erzeugen eines neuen NotizObjektes
    String sql;
    try {
      Statement stmt = conn.createStatement();
      sql = "SELECT * FROM note WHERE note_Nr = '" + noteNr + "'";
      ResultSet rset = stmt.executeQuery(sql);
      //Es existieren keine Notizen fuer diesen Kunden
      Memo kundennotiz = new Memo();
      while (rset.next()) {
        kundennotiz.setId(rset.getInt(1));
        kundennotiz.setText(rset.getString(2));
        kundennotiz.setDate(rset.getDate(3));
        kundennotiz.setKundenNr(rset.getInt(4));
      }
      return kundennotiz;
    } catch (SQLException sqlE) {
      throw new WiSimDAOException(sqlE.getMessage());
    }
  }

  /**
   * Loeschen einer Memo
   *
   * @return int
   * @param noteNr Notiznummer
   * @throws WiSimDAOWriteException Wenn ein Fehler während des Schreibens in
   * die DB auftritt
   */
  @Override
  public int delNotiz(int noteNr) throws WiSimDAOWriteException {
    // Serverlog
    logger.finest("com.pixelpark.wisim.dao.WiSimDAOImpl.createContract(Contract) Action: start");
    String sql;
    try {
      Statement stmt = conn.createStatement();
      sql = "DELETE FROM note WHERE note_Nr = '" + noteNr + "'";
      int res = stmt.executeUpdate(sql);
      return res;
    } catch (SQLException sqlE) {
      throw new WiSimDAOWriteException(sqlE.getMessage());
    }
  }

  /**
   * Erstellung einer neuen Memo
   *
   * @param notiz Objekt: Memo
   * @throws WiSimDAOException if a database problem occurs or the connection
   * was never initialized
   * @throws WiSimDAOWriteException if a database problem occurs or the
   * connection was never initialized
   */
  @Override
  public void neueNotiz(Memo notiz) throws WiSimDAOException, WiSimDAOWriteException {
    // Serverlog
    logger.finest("com.pixelpark.wisim.dao.WiSimDAOImpl.createContract(Contract) Action: start");
    String sql;
    try {
      try {
        //Update der Tabelle: Ort
        Statement stmt = conn.createStatement();
        sql = "insert into note (note_Nr, note_txt, note_Date, f_kd_Nr) values (" + notiz.getId() + ", \"" + notiz.getText() + "\", \"" + notiz.getDate() + "\", " + notiz.getKundenNr() + ") ";

        stmt.executeUpdate(sql);
        conn.commit();
      } catch (SQLException sqlE) {
        conn.rollback();
        throw new WiSimDAOWriteException(sqlE.getMessage());
      }
    } catch (SQLException sqlE) {
      throw new WiSimDAOException(sqlE.getMessage());
    }
  }

  /**
   * Gibt das Objekt City zurück das zu der Postleitzahl gehört
   *
   * @return City der zu der PLZ gehört
   * @param Nr Id des Ortes
   * @throws net.sourceforge.wisim.dao.WiSimDAOException Fehler beim Abfragen
   * der DB
   */
  @Override
  public City getOrt(int Nr) throws WiSimDAOException {
    // Serverlog
    logger.finest("com.pixelpark.wisim.dao.WiSimDAOImpl.createContract(Contract) Action: start");
    String sql;

    try {
      //Update der Tabelle: Ort
      Statement stmt = conn.createStatement();
      sql = "select ort_Nr, ort_PLZ, ort_Name from ort where ort_Nr = " + Nr + "";
      ResultSet rset = stmt.executeQuery(sql);

      //Der Ort ist noch nicht in der Tabelle
      while (rset.next()) {
        City ort = new City();
        ort.setNr(rset.getInt(1));
        ort.setPlz(rset.getString(2));
        ort.setName(rset.getString(3));
        return ort;
      }
      // conn.commit(); //Wird in MySQL 3.x nicht unterstï¿½tzt
      return null;
    } catch (SQLException sqlE) {
      throw new WiSimDAOException(sqlE.getMessage());
    }
  }

  /**
   * Erstellt einen neuen City in der DB
   *
   * @return Die ID des neuen Ortes
   * @param ort City
   * @throws WiSimDAOException ToDo
   */
  @Override
  public int neuerOrt(City ort) throws WiSimDAOException {
    // Serverlog
    String sql;
    logger.finest("com.pixelpark.wisim.dao.WiSimDAOImpl.createContract(Contract) Action: start");
    int plzId = -1;
    try {
      Statement stmt = conn.createStatement();
      sql = "select ort_Nr, ort_PLZ, ort_Name from ort where ort_PLZ = " + ort.getPlz() + " AND ort_Name = '" + ort.getName() + "'";
      java.sql.ResultSet checkPlz = stmt.executeQuery(sql);

      //Der Ort ist noch nicht in der Tabelle und wird eingefï¿½gt
      if (!checkPlz.next()) {
        sql = "insert into ort (ort_PLZ, ort_Name) values (" + ort.getPlz() + ", '" + ort.getName() + "')";
        stmt.executeUpdate(sql);

        ResultSet lastKey = stmt.getGeneratedKeys();
        while (lastKey.next()) {
          return lastKey.getInt(1);
        }
      } else {
        return checkPlz.getInt(1);
      }

      // conn.commit(); //Wird in MySQL 3.x nicht unterstï¿½tzt
    } catch (SQLException sqlE) {
      throw new WiSimDAOException(sqlE.getMessage());
    }
    return plzId;
  }

  /**
   * Collection mit allen Lieferanten
   *
   * @throws WiSimDAOException Fehler beim Abfragen der DB
   * @return Collection mit Objekten vom Typ Supplier
   */
  @Override
  public ArrayList<Supplier> getSuppliers() throws WiSimDAOException {
    // Serverlog
    logger.finest("com.pixelpark.wisim.dao.WiSimDAOImpl.getLieferanten() Action: start");
    String sql;
    ArrayList<Supplier> suppliers = new ArrayList<>();
    try {
      Statement stmt = conn.createStatement();
      sql = "select * from lt WHERE lt_deleted = 'FALSE'";
      java.sql.ResultSet resLieferanten = stmt.executeQuery(sql);

      while (resLieferanten.next()) {

        City ort = getOrt(resLieferanten.getInt("f_ort_Nr"));

        Supplier supplier
                = new Supplier(
                        resLieferanten.getInt("lt_Nr"),
                        resLieferanten.getString("lt_Firma"),
                        resLieferanten.getString("lt_Name"),
                        resLieferanten.getString("lt_Vorname"),
                        resLieferanten.getString("lt_Telefon"),
                        resLieferanten.getString("lt_Fax"),
                        resLieferanten.getString("lt_Strasse"),
                        ort.getName(),
                        ort.getPlz(),
                        ort.getNr(),
                        resLieferanten.getString("lt_Email"),
                        resLieferanten.getString("lt_Zuverlaessigkeit"),
                        resLieferanten.getString("lt_Qualitaet"));
        suppliers.add(supplier);
      }
    } catch (SQLException sqlE) {
      throw new WiSimDAOException(sqlE.getMessage());
    }
    return suppliers;
  }

  /**
   * Holt alle Einzelteile aus der Datenbank
   *
   * @return Collection all Components
   * @throws WiSimDAOException Fehler beim Abfragen der DB
   */
  @Override
  public Collection<WiSimComponent> getAllComponents() throws WiSimDAOException {
    String sql = "SELECT * FROM et";

    Collection<WiSimComponent> components = new ArrayList<>();
    try {
      // Create a Statement
      Statement stmt = conn.createStatement();
      ResultSet rset = stmt.executeQuery(sql);

      while (rset.next()) {
        WiSimComponent component = new WiSimComponent();
        component.setNr(rset.getInt(1));
        component.setName(rset.getString(2));
        component.setMindestbestand(rset.getInt(3));
        components.add(component);
      }
    } catch (SQLException sqlE) {
      throw new WiSimDAOException(sqlE.getMessage());
    }
    return components;
  }

  /**
   * Holt ein WiSimComponent aus der Datenbank
   *
   * @return WiSimComponent
   * @param id Component ID
   * @throws WiSimDAOException Fehler beim Abfragen der DB
   */
  @Override
  public WiSimComponent getComponent(int id) throws WiSimDAOException {
    String sql = "SELECT * FROM et WHERE et_Nr = " + id;

    WiSimComponent component = new WiSimComponent();
    try {
      // Create a Statement
      Statement stmt = conn.createStatement();
      ResultSet rset = stmt.executeQuery(sql);

      while (rset.next()) {
        component.setNr(rset.getInt(1));
        component.setName(rset.getString(2));
        component.setMindestbestand(rset.getInt(3));
      }
    } catch (SQLException sqlE) {
      throw new WiSimDAOException(sqlE.getMessage());
    }
    return component;
  }

  /**
   * Erstellt eine SupplyList
   *
   * @param liste SupplyList
   * @throws WiSimDAOWriteException Fehler beim Schreiben in die DB
   * @throws WiSimDAOException Fehler beim Lesen von der DB
   */
  @Override
  public void setLieferliste(SupplyList liste) throws WiSimDAOException, WiSimDAOWriteException {
    String sql = "INSERT INTO rel_lt_et (f_et_Nr, f_lt_Nr, rel_lt_et_Stueckpreis, rel_lt_et_Mindestbestellmenge)" + "VALUES ( " + liste.getEinzelteilID() + ", " + liste.getLieferantenID() + ", " + liste.getPreis() + ", " + liste.getMindestBestellMenge() + ");";

    try {
      // Create a Statement
      Statement stmt = conn.createStatement();
      stmt.executeUpdate(sql);

    } catch (SQLException sqlE) {
      throw new WiSimDAOWriteException(sqlE.getMessage());
    }
  }

  /**
   * Holt einen Lieferlisten-Eintrag aus der Datenbank
   *
   * @return SupplyList
   * @param lieferantenID ID des Lieferanten
   * @param einzelteilID ID des Einzelteils
   * @throws WiSimDAOException Fehler beim Lesen von der DB
   */
  @Override
  public SupplyList getLieferliste(int lieferantenID, int einzelteilID) throws WiSimDAOException {
    String sql = "SELECT * FROM rel_lt_et WHERE f_et_Nr = " + einzelteilID + " AND f_lt_Nr = " + lieferantenID;

    SupplyList lieferliste = null;
    try {
      // Create a Statement
      Statement stmt = conn.createStatement();
      ResultSet rset = stmt.executeQuery(sql);

      while (rset.next()) {
        lieferliste = new SupplyList();
        lieferliste.setEinzelteilID(rset.getInt(1));
        lieferliste.setLieferantenID(rset.getInt(2));
        lieferliste.setPreis(rset.getDouble(3));
        lieferliste.setMindestBestellMenge(rset.getLong(4));
      }
    } catch (SQLException sqlE) {
      throw new WiSimDAOException(sqlE.getMessage());
    }
    return lieferliste;
  }

  /**
   * Collection mit allen Lieferlisten des Lieferanten
   *
   * @param supplierID LieferantNr
   * @throws WiSimDAOException Fehler beim Ablesen aus der DB
   * @return ArrayList mit Objekten vom Typ SupplyList
   */
  @Override
  public ArrayList<SupplyList> getSupplyLists(int supplierID) throws WiSimDAOException {
    ArrayList<SupplyList> supplyLists = new ArrayList<>();
    String sql;

    SupplyList supplyList;
    try {
      // Create a Statement
      Statement stmt = conn.createStatement();
      sql = "select * from rel_lt_et WHERE f_lt_Nr = " + supplierID;
      ResultSet rset = stmt.executeQuery(sql);

      while (rset.next()) {
        supplyList = new SupplyList();
        supplyList.setEinzelteilID(rset.getInt(1));
        supplyList.setLieferantenID(rset.getInt(2));
        supplyList.setPreis(rset.getDouble(3));
        supplyList.setMindestBestellMenge(rset.getLong(4));

        supplyLists.add(supplyList);
      }

    } catch (SQLException sqlE) {
      throw new WiSimDAOException(sqlE.getMessage());
    }
    return supplyLists;
  }

  /* (non-Javadoc)
	 * @see com.pixelpark.wisim.dao.WiSimDAO#setEinzelteilArbeitsplatzBestand(int, int, java.lang.String)
   */
  /**
   * Ändert den Bestand an Einzelteilen an einem WorkPlace
   *
   * @param arbeitsplatzNr Arbeitsplatznummer
   * @param einzelteilNr Einzelteilnummer
   * @param lagerTyp Typ des Lagers
   * @throws WiSimDAOException Fehler beim Lesen von der DB
   * @throws WiSimDAOWriteException Fehler beim Schreiben in die DB
   * @return boolean
   */
  @Override
  public synchronized boolean setEinzelteilArbeitsplatzBestand(int arbeitsplatzNr, int einzelteilNr, String lagerTyp) throws WiSimDAOException, WiSimDAOWriteException {
    String sql = "SELECT rel_et_ap_Benoetigt FROM rel_et_ap WHERE f_ap_Nr = " + arbeitsplatzNr + " AND f_et_Nr = " + einzelteilNr + " AND rel_et_ap_Lagertyp = 'Einzelteil'";

    ResultSet res;
    Statement stmt;
    try {
      stmt = conn.createStatement();
      res = stmt.executeQuery(sql);
    } catch (SQLException e) {
      throw new WiSimDAOException(e.getMessage());
    }

    int anzahl = 0;

    try {
      while (res.next()) {
        anzahl = res.getInt(1);
      }

    } catch (SQLException e) {
      throw new WiSimDAOException(e.getMessage());
    }

    sql = "SELECT rel_et_ap_Bestand, rel_et_ap_MaxBestand FROM rel_et_ap WHERE f_et_Nr = " + einzelteilNr + " AND f_ap_Nr = " + arbeitsplatzNr + " AND rel_et_ap_Lagertyp = 'Einzelteil'";

    int bestand;
    try {
      ResultSet rset = stmt.executeQuery(sql);
      bestand = -1;

      while (rset.next()) {
        bestand = rset.getInt(1);
      }
    } catch (SQLException e1) {
      throw new WiSimDAOException(e1.getMessage());
    }

    if ((bestand - anzahl) < 0) {
      return false;
    }

    sql = "UPDATE rel_et_ap SET rel_et_ap_Bestand = rel_et_ap_Bestand - " + anzahl + " WHERE f_et_Nr = " + einzelteilNr + " AND f_ap_Nr = " + arbeitsplatzNr + " AND rel_et_ap_Lagertyp = 'Einzelteil'";

    try {
      stmt.executeUpdate(sql);

      return true;

    } catch (SQLException sqlE) {
      throw new WiSimDAOWriteException(sqlE.getMessage());
    }
  }

  /* (non-Javadoc)
	 * @see com.pixelpark.wisim.dao.WiSimDAO#setEinzelteilArbeitsplatzBestand(int, int, int, java.lang.String)
   */
  /**
   * Ändert den Bestand an Einzelteilen an einem WorkPlace
   *
   * @param arbeitsplatzNr Arbeitsplatznummer
   * @param einzelteilNr Einzelteilnummer
   * @param anzahl Anzahl der Einzelteile
   * @param lagerTyp Typ des Lagers
   * @throws WiSimDAOWriteException Fehler beim Schreiben in die DB
   * @throws WiSimDAOException Fehler beim Lesen von der DB
   * @return int
   */
  @Override
  public synchronized int setEinzelteilArbeitsplatzBestand(int arbeitsplatzNr, int einzelteilNr, int anzahl, String lagerTyp) throws WiSimDAOWriteException, WiSimDAOException {
    String sql = "SELECT rel_et_ap_Bestand, rel_et_ap_MaxBestand FROM rel_et_ap" + " WHERE f_ap_Nr = " + arbeitsplatzNr + " AND f_et_Nr = " + einzelteilNr + " AND rel_et_ap_Lagertyp = '" + lagerTyp + "';";

    Statement stmt;
    int bestand = -1;
    int maxBestand = -1;
    try {
      stmt = conn.createStatement();
      ResultSet res = stmt.executeQuery(sql);
      while (res.next()) {
        bestand = res.getInt(1);
        maxBestand = res.getInt(2);
      }
    } catch (SQLException e1) {
      throw new WiSimDAOException(e1.getMessage());
    }

    if ((bestand + anzahl) < 0) {
      anzahl = bestand * -1;
    }

    if ((bestand + anzahl) > maxBestand) {
      anzahl = maxBestand - bestand;
    }

    sql = "UPDATE rel_et_ap SET rel_et_ap_Bestand = (rel_et_ap_Bestand + " + anzahl + ") WHERE f_ap_Nr = " + arbeitsplatzNr + " AND f_et_Nr = " + einzelteilNr + " AND rel_et_ap_Lagertyp = '" + lagerTyp + "';";

    try {
      stmt.executeUpdate(sql);
    } catch (SQLException e) {
      throw new WiSimDAOWriteException(e.getMessage());
    }
    return anzahl;
  }

  /**
   * Liefert die Dauer eines Arbeitsschrittes an einem bestimmten WorkPlace
   *
   * @param arbeitsplatzNr Nr. des Arbeitsplatzes, für dem die Dauer
   * zurückgegeben werden soll.
   * @throws WiSimDAOException Wenn ein Fehler beim Zugriff auf die DB auftritt.
   * @return Dauer des Prozesses
   */
  @Override
  public synchronized WorkPlace getArbeitsplatz(int arbeitsplatzNr) throws WiSimDAOException {
    String sql = "SELECT * FROM ap WHERE ap_Nr = " + arbeitsplatzNr + ";";

    try {
      Statement stmt = conn.createStatement();
      ResultSet res = stmt.executeQuery(sql);
      while (res.next()) {
        WorkPlace arbeitsplatz = new WorkPlace(res.getInt(1), res.getString(2), res.getInt(3), res.getInt(4));

        sql = "SELECT nf_nachfolger FROM nf WHERE f_ap_nr = " + arbeitsplatz.getNr();
        ResultSet res2 = stmt.executeQuery(sql);
        int rows = 0;
        while (res2.next()) {
          rows++;
        }
        res2.beforeFirst();
        int[] nf = new int[rows];
        int i = 0;
        while (res2.next()) {
          nf[i] = res2.getInt(1);
          i++;
        }
        arbeitsplatz.setNachfolger(nf);

        sql = "SELECT vg_vorgaenger FROM vg WHERE f_ap_nr = " + arbeitsplatz.getNr();
        res2 = stmt.executeQuery(sql);
        rows = 0;
        while (res2.next()) {
          rows++;
        }
        res2.beforeFirst();
        int[] vg = new int[rows];
        i = 0;
        while (res2.next()) {
          vg[i] = res2.getInt(1);
          i++;
        }
        arbeitsplatz.setVorgaenger(vg);
        return arbeitsplatz;
      }
    } catch (SQLException e) {
      throw new WiSimDAOException(e.getMessage());
    }
    return null;
  }

  /**
   * Liefert alle Einzelteile an einem WorkPlace zurï¿½ck.
   *
   * @param arbeitsplatzNr Nummer des WorkPlace
   * @throws WiSimDAOException Wenn ein Fehler beim Zugriff auf die DB auftritt.
   * @return Liste mit Einzelteilen
   */
  @Override
  public synchronized Collection<WorkPlaceStore> getArbeitsplatzLager(int arbeitsplatzNr) throws WiSimDAOException {
    String sql = "SELECT * FROM rel_et_ap WHERE f_ap_Nr = " + arbeitsplatzNr + " ;";
    Collection<WorkPlaceStore> arbeitsplatzLager = new ArrayList<>();

    try {
      Statement stmt = conn.createStatement();
      ResultSet res = stmt.executeQuery(sql);
      while (res.next()) {
        WorkPlaceStore apl = new WorkPlaceStore(res.getInt(1), res.getInt(2), res.getString(3), res.getInt(4), res.getInt(5), res.getInt(6));
        arbeitsplatzLager.add(apl);
      }
    } catch (SQLException e) {

      System.err.println(e.getMessage());
    }

    return arbeitsplatzLager;
  }

  /**
   * Erstellt einen neuen Einzelteileauftrag
   *
   * @return EinzelteilauftragsNrID des Einzelteilauftrags
   * @param etat ComponentContract
   * @throws WiSimDAOException if a database problem occurs or the connection
   * was never initialized
   * @throws WiSimDAOWriteException if a database problem occurs or the
   * connection was never initialized
   */
  @Override
  public int setEinzelteilauftrag(ComponentContract etat) throws WiSimDAOException, WiSimDAOWriteException {
    String sql
            = "INSERT INTO etat (etat_Lieferrabatt, etat_Skontofrist, etat_Lieferdatum, etat_Datum, etat_Skonto, f_lt_Nr, f_etatr_Nr)"
            + "VALUES ( "
            + etat.getLieferrabatt()
            + ", "
            + etat.getSkontofrist()
            + ", '"
            + etat.getLieferdatum()
            + "', '"
            + etat.getAuftragsdatum()
            + "', "
            + etat.getSkonto()
            + ", "
            + etat.getLieferantNr()
            + ", "
            + "0"
            + ");";

    int etatNr = -1;

    try {
      // Create a Statement
      Statement stmt = conn.createStatement();

      //Einzelteilauftrag wird geschrieben
      stmt.executeUpdate(sql);
      ResultSet lastKey = stmt.getGeneratedKeys();
      while (lastKey.next()) {
        etatNr = lastKey.getInt(1);
      }

      /*Key wird in f_etatr_Nr eingesetzt, da zwischen etat und etatr eine 1:1 Beziehung
			  besteht. Das bedeutet jeder neuer Einzelteilauftrag fï¿½hrt zu einer neuen Einzel-
			  teilAuftragsRechnung mit der selben ID.
       */
      sql = "update etat set f_etatr_Nr = " + etatNr + " where etat_Nr = " + etatNr;
      stmt.executeUpdate(sql);

      //EinzelteilAuftragsPositionen werden geschrieben
      Iterator etatPos_it = etat.getEinzelteilauftragPositionen().iterator();
      while (etatPos_it.hasNext()) {
        ComponentContractItem etatPos = (ComponentContractItem) etatPos_it.next();
        sql = "Insert into rel_etat_et (f_et_Nr, f_etat_Nr, rel_etat_et_Bestellmenge, rel_etat_et_Stueckpreis) values (" + etatPos.getEtNr() + ", " + etatNr + ", " + etatPos.getBestellmenge() + ", " + etatPos.getPreis() + ")";

        stmt.executeUpdate(sql);
      }
    } catch (SQLException sqlE) {
      throw new WiSimDAOWriteException(sqlE.getMessage());
    }
    return etatNr;
  }

  /**
   * Erstellt eine neue EinzelteilAuftragsRechnung
   *
   * @return EinzelteilauftragsrechnungsNr
   * @param etatr Einzelteilauftrags nummer
   * @throws WiSimDAOException if a database problem occurs or the connection
   * was never initialized
   * @throws WiSimDAOWriteException if a database problem occurs or the
   * connection was never initialized
   */
  @Override
  public int setEinzelteilauftragsrechnung(ComponentContractInvoice etatr) throws WiSimDAOException, WiSimDAOWriteException {
    String sql = "insert into etatr (etatr_Nr, etatr_Betrag, f_etat_Nr, f_mwst_Satz) " + "VALUES ( " + etatr.getNr() + ", " + etatr.getBetrag() + ", " + etatr.getEinzelteilauftragNr() + ", " + etatr.getMwSt() + ");";

    try {
      // Create a Statement
      Statement stmt = conn.createStatement();
      stmt.executeUpdate(sql);
      ResultSet lastKey = stmt.getGeneratedKeys();
      while (lastKey.next()) {
        return lastKey.getInt(1);
      }

    } catch (SQLException sqlE) {
      throw new WiSimDAOWriteException(sqlE.getMessage());
    }
    return -1;
  }

  /**
   * Erstellt eine neue AuftragsRechnung
   *
   * @return AuftragsrechnungsNr
   * @param atr Auftrags nummer
   * @throws WiSimDAOException if a database problem occurs or the connection
   * was never initialized
   * @throws WiSimDAOWriteException if a database problem occurs or the
   * connection was never initialized
   */
  @Override
  public int setAuftragsrechnung(ContractInvoice atr) throws WiSimDAOException, WiSimDAOWriteException {

    String dbZlEingang;

    if (atr.getzEingang() == false) {
      dbZlEingang = "'FALSE'";
    } else {
      dbZlEingang = "'TRUE'";
    }

    String sql = "insert into atr (atr_Nr, atr_Betrag, f_at_Nr, f_mwst_Satz, atr_zleingang) " + "VALUES ( " + atr.getNr() + ", " + atr.getBetrag() + ", " + atr.getAuftragNr() + ", " + atr.getMwSt() + ", " + dbZlEingang + ");";

    try {
      // Create a Statement
      Statement stmt = conn.createStatement();
      stmt.executeUpdate(sql);
      ResultSet lastKey = stmt.getGeneratedKeys();
      while (lastKey.next()) {
        return lastKey.getInt(1);
      }

    } catch (SQLException sqlE) {
      throw new WiSimDAOWriteException(sqlE.getMessage());
    }
    return -1;
  }

  /**
   * Gibt den MwSt-Satz aus der Datenbank zurï¿½ck.
   *
   * @throws WiSimDAOException if a database problem occurs or the connection
   * was never initialized
   * @return Mehrwertsteuer-Satz
   */
  @Override
  public float getMwSt() throws WiSimDAOException {
    String sql = "Select mwst_satz from mwst";
    try {
      // Create a Statement
      Statement stmt = conn.createStatement();
      ResultSet rset = stmt.executeQuery(sql);

      while (rset.next()) {
        return rset.getFloat(1);
      }
    } catch (SQLException sqlE) {
      throw new WiSimDAOException(sqlE.getMessage());
    }
    return -1;
  }

  /**
   * Holt alle Einzelteile aus der Datenbank
   *
   * @return Collection
   * @throws WiSimDAOException Fehler beim Lesen von der DB
   */
  @Override
  public Collection<Article> getAlleArtikel() throws WiSimDAOException {
    String sql = "SELECT * FROM art";

    Collection<Article> alleartikel = new ArrayList<>();
    try {
      // Create a Statement
      Statement stmt = conn.createStatement();
      ResultSet rset = stmt.executeQuery(sql);

      while (rset.next()) {
        Article artikel = new Article();
        artikel.setNr(rset.getInt(1));
        artikel.setName(rset.getString(2));
        artikel.setStueckpreis(rset.getFloat(3));
        artikel.setMindestbestand(rset.getInt(4));
        alleartikel.add(artikel);
      }
    } catch (SQLException sqlE) {
      throw new WiSimDAOException(sqlE.getMessage());
    }
    return alleartikel;
  }

  /**
   * Holt ein Article aus der Datenbank
   *
   * @return Article
   * @param id ID des Artikels
   * @throws WiSimDAOException Fehler beim Lesen von der DB
   */
  @Override
  public Article getArtikel(int id) throws WiSimDAOException {
    String sql = "SELECT * FROM art WHERE art_Nr = " + id;

    Article artikel = new Article();
    try {
      // Create a Statement
      Statement stmt = conn.createStatement();
      ResultSet rset = stmt.executeQuery(sql);

      while (rset.next()) {
        artikel.setNr(rset.getInt(1));
        artikel.setName(rset.getString(2));
        artikel.setStueckpreis(rset.getFloat(3));
        artikel.setMindestbestand(rset.getInt(4));
      }
    } catch (SQLException sqlE) {
      throw new WiSimDAOException(sqlE.getMessage());
    }
    return artikel;
  }

  /**
   * Holt alle Verträge aus der Datenbank
   *
   * @return ArrayList
   * @throws WiSimDAOException Fehler beim Lesen von der DB
   */
  @Override
  public ArrayList<Contract> getContracts() throws WiSimDAOException {
    String sql = "SELECT * FROM at";

    ArrayList<Contract> contracts = new ArrayList<>();
    try {
      // Create a Statement
      Statement stmt = conn.createStatement();
      ResultSet rset = stmt.executeQuery(sql);

      while (rset.next()) {
        Contract contract = new Contract();
        contract.setVertragsId(rset.getInt("at_Nr"));
        contract.setLieferdatum(rset.getDate("at_Lieferdatum"));
        contract.setSkonto(rset.getDouble("at_Skonto"));
        contract.setSkontofrist(rset.getLong("at_Skontofrist"));
        contract.setRabatt(rset.getDouble("at_Rabatt"));
        contract.setVertragsdatum(rset.getDate("at_Datum"));
        contract.setKundenId(rset.getInt("f_kd_Nr"));
        contract.setAuftragsrechnungsId(rset.getInt("f_atr_Nr"));
        contracts.add(contract);
      }
    } catch (SQLException sqlE) {
      throw new WiSimDAOException(sqlE.getMessage());
    }
    return contracts;
  }

  /**
   * Holt einen Contract aus der Datenbank
   *
   * @return Contract
   * @param id ID des Vertrages
   * @throws WiSimDAOException Fehler beim Lesen von der DB
   */
  @Override
  public Contract getVertrag(int id) throws WiSimDAOException {
    String sql = "SELECT * FROM at WHERE at_Nr = " + id;

    Contract vertrag = new Contract();
    try {
      // Create a Statement
      Statement stmt = conn.createStatement();
      ResultSet rset = stmt.executeQuery(sql);

      while (rset.next()) {
        vertrag.setVertragsId(rset.getInt(1));
        vertrag.setLieferdatum(rset.getDate(2));
        vertrag.setSkonto(rset.getDouble(3));
        vertrag.setSkontofrist(rset.getLong(4));
        vertrag.setRabatt(rset.getDouble(5));
        vertrag.setVertragsdatum(rset.getDate(6));
        vertrag.setKundenId(rset.getInt(7));
        vertrag.setAuftragsrechnungsId(rset.getInt(8));
      }
    } catch (SQLException sqlE) {
      throw new WiSimDAOException(sqlE.getMessage());
    }
    return vertrag;
  }

  /**
   * Legt einen neuen Contract an
   *
   * @param vertrag Contract
   * @throws WiSimDAOException if a database problem occurs or the connection
   * was never initialized
   * @throws WiSimDAOWriteException if a database problem occurs or the
   * connection was never initialized
   * @return int
   */
  @Override
  public int setNeuenVertrag(Contract vertrag) throws WiSimDAOException, WiSimDAOWriteException {
    // Serverlog
    logger.finest("com.pixelpark.wisim.dao.WiSimDAOImpl.createContract(Contract) Action: start");
    String sql;
    int key = -1;
    try {
      try {
        //Update der Tabelle: Ort
        Statement stmt = conn.createStatement();

        // Create a Statement
        //Statement stmt = conn.createStatement();
        // update statement for record in table contract
        // Neuer Vertrag wird angelegt
        sql
                = "insert into at "
                + "(at_Lieferdatum, at_Skonto, at_Skontofrist, at_Rabatt, at_Datum, f_kd_Nr, f_atr_Nr) values (\""
                + vertrag.getLieferdatum()
                + "\", \""
                + vertrag.getSkonto()
                + "\", \""
                + vertrag.getSkontofrist()
                + "\", \""
                + vertrag.getRabatt()
                + "\", \""
                + vertrag.getVertragsdatum()
                + "\", \""
                + vertrag.getKundenId()
                + "\", \""
                + vertrag.getAuftragsrechnungsId()
                + "\") ";

        stmt.executeUpdate(sql);

        ResultSet lastKey = stmt.getGeneratedKeys();
        while (lastKey.next()) {
          key = lastKey.getInt(1);
        }
      } catch (SQLException sqlE) {
        conn.rollback();
        throw new WiSimDAOWriteException(sqlE.getMessage());
      }
    } catch (SQLException sqlE) {
      throw new WiSimDAOException(sqlE.getMessage());
    }
    return key;
  }

  /**
   * Gibt eine Liste aller Einzelteilauftrï¿½ge zurï¿½ck.
   *
   * @throws WiSimDAOException if a database problem occurs or the connection
   * was never initialized
   * @return Vector mit allen Einzelteilauftrï¿½gen
   */
  @Override
  public ArrayList<ComponentContract> getEinzelteilauftraege() throws WiSimDAOException {
    // Serverlog
    logger.finest("com.pixelpark.wisim.dao.WiSimDAOImpl.getEtat Action: start");
    String sql;
    ArrayList<ComponentContract> etatListe = new ArrayList<>();
    try {
      sql = "SELECT etat_Nr, etat_Lieferrabatt, etat_Skontofrist, etat_Lieferdatum, etat_Datum, etat_Skonto, " + "f_lt_Nr, f_etatr_Nr FROM etat ORDER BY etat_Datum DESC";
      Statement stmt = conn.createStatement();
      ResultSet rset = stmt.executeQuery(sql);
      while (rset.next()) {
        ComponentContract etat = new ComponentContract();
        etat.setNr(rset.getInt(1));
        etat.setLieferrabatt(rset.getFloat(2));
        etat.setSkontofrist(rset.getInt(3));
        etat.setLieferdatum(rset.getDate(4));
        etat.setAuftragsdatum(rset.getDate(5));
        etat.setSkonto(rset.getFloat(6));
        etat.setLieferantNr(rset.getInt(7));
        etat.setEinzelteilAuftragsRechnungNr(rset.getInt(8));
        etatListe.add(etat);
      }

    } catch (SQLException sqlE) {
      throw new WiSimDAOException(sqlE.getMessage());
    }
    return etatListe;
  }

  /**
   * Gibt alle Positionen eines Einzelteilauftrages zurï¿½ck.
   *
   * @param etatNr Einzelteilauftrags Nummer
   * @throws WiSimDAOException if a database problem occurs or the connection
   * was never initialized
   * @return Collection mit allen Einzelteilauftragspositionen
   */
  @Override
  public ArrayList<ComponentContractItem> getEinzelteilAuftragsPositionen(int etatNr) throws WiSimDAOException {
    // Serverlog
    logger.finest("com.pixelpark.wisim.dao.WiSimDAOImpl.getEtat Action: start");
    String sql;
    ArrayList<ComponentContractItem> etatPositionen = new ArrayList<>();
    try {
      sql = "SELECT f_et_Nr, f_etat_Nr, rel_etat_et_Bestellmenge, rel_etat_et_Stueckpreis FROM rel_etat_et WHERE " + "f_etat_Nr = " + etatNr + "";
      Statement stmt = conn.createStatement();
      ResultSet rset = stmt.executeQuery(sql);

      while (rset.next()) {
        ComponentContractItem etatPos = new ComponentContractItem();
        etatPos.setEtNr(rset.getInt(1));
        etatPos.setEtatNr(rset.getInt(2));
        etatPos.setBestellmenge(rset.getInt(3));
        etatPos.setPreis(rset.getDouble(4));
        etatPositionen.add(etatPos);
      }

    } catch (SQLException sqlE) {
      throw new WiSimDAOException(sqlE.getMessage());
    }
    return etatPositionen;
  }

  /**
   * Gibt alle Positionen eines Auftrages zurï¿½ck.
   *
   * @param atNr Auftrags Nummer
   * @throws WiSimDAOException if a database problem occurs or the connection
   * was never initialized
   * @return Collection mit allen Auftragspositionen
   */
  @Override
  public Collection<ContractOrderItem> getAuftragsPositionen(int atNr) throws WiSimDAOException {
    // Serverlog
    logger.finest("com.pixelpark.wisim.dao.WiSimDAOImpl.getEtat Action: start");
    String sql;
    Collection<ContractOrderItem> atPositionen = new ArrayList<>();
    try {
      sql = "SELECT f_art_Nr, f_at_Nr, rel_at_art_Bestellmenge FROM rel_at_art WHERE " + "f_at_Nr = " + atNr + "";
      Statement stmt = conn.createStatement();
      ResultSet rset = stmt.executeQuery(sql);

      while (rset.next()) {
        ContractOrderItem atPos = new ContractOrderItem();
        atPos.setArtNr(rset.getInt(1));
        atPos.setAtNr(rset.getInt(2));
        atPos.setBestellmenge(rset.getInt(3));

        atPositionen.add(atPos);
      }

    } catch (SQLException sqlE) {
      throw new WiSimDAOException(sqlE.getMessage());
    }
    return atPositionen;
  }

  /**
   * Gibt eine Position eines Auftrages zurï¿½ck.
   *
   * @param atNr Auftrags Nummer
   * @throws WiSimDAOException if a database problem occurs or the connection
   * was never initialized
   * @return Auftragsposition
   */
  @Override
  public ContractOrderItem getAuftragsPosition(int atNr) throws WiSimDAOException {
    // Serverlog
    logger.finest("com.pixelpark.wisim.dao.WiSimDAOImpl.getVertrag Action: start");
    String sql;
    ContractOrderItem atp = new ContractOrderItem();
    try {
      sql = "SELECT f_art_Nr, f_at_Nr, rel_at_art_Bestellmenge FROM rel_at_art WHERE " + "f_at_Nr = " + atNr + "";
      Statement stmt = conn.createStatement();
      ResultSet rset = stmt.executeQuery(sql);

      while (rset.next()) {
        atp.setArtNr(rset.getInt(1));
        atp.setAtNr(rset.getInt(2));
        atp.setBestellmenge(rset.getInt(3));
      }

    } catch (SQLException sqlE) {
      throw new WiSimDAOException(sqlE.getMessage());
    }
    return atp;
  }

  /**
   * Gibt eine ContractInvoice eines Auftrages zurï¿½ck.
   *
   * @param atrNr Auftragrechnungs Nummer
   * @throws WiSimDAOException if a database problem occurs or the connection
   * was never initialized
   * @return ContractInvoice
   */
  @Override
  public ContractInvoice getAuftragsrechnung(int atrNr) throws WiSimDAOException {
    // Serverlog
    logger.finest("com.pixelpark.wisim.dao.WiSimDAOImpl.getVertrag Action: start");
    String sql;
    ContractInvoice atr = new ContractInvoice();
    try {
      sql = "SELECT atr_Nr, atr_Betrag, f_at_Nr, f_mwst_Satz, atr_zleingang FROM atr WHERE " + "atr_Nr = " + atrNr + "";
      Statement stmt = conn.createStatement();
      ResultSet rset = stmt.executeQuery(sql);

      while (rset.next()) {
        atr.setNr(rset.getInt(1));
        atr.setBetrag(rset.getDouble(2));
        atr.setAuftragNr(rset.getInt(3));
        atr.setMwSt(rset.getFloat(4));
        atr.setzEingang(rset.getBoolean(5));
      }

    } catch (SQLException sqlE) {
      throw new WiSimDAOException(sqlE.getMessage());
    }
    return atr;
  }

  /**
   * Gibt die ComponentContractInvoice zurï¿½ck die zu dem entsprechenden
   * ComponentContract gehï¿½rt.
   *
   * @param etatrNr Einzelteilauftrags Nummer
   * @throws WiSimDAOException if a database problem occurs or the connection
   * was never initialized
   * @return Die ComponentContractInvoice.
   */
  @Override
  public ComponentContractInvoice getEinzelteilauftragsrechnung(int etatrNr) throws WiSimDAOException {
    // Serverlog
    logger.finest("com.pixelpark.wisim.dao.WiSimDAOImpl.getEtat Action: start");
    String sql;
    ComponentContractInvoice etatr = new ComponentContractInvoice();
    try {
      sql = "SELECT etatr_Betrag, f_mwst_Satz FROM etatr WHERE etatr_Nr = " + etatrNr;
      Statement stmt = conn.createStatement();
      ResultSet rset = stmt.executeQuery(sql);

      while (rset.next()) {
        etatr.setBetrag(rset.getFloat(1));
        etatr.setMwSt(rset.getFloat(2));
      }

    } catch (SQLException sqlE) {
      throw new WiSimDAOException(sqlE.getMessage());
    }
    return etatr;
  }

  /**
   * Markiert einen Kunden als geloescht bzw sichtbar
   *
   * @return int
   * @param KdNr Kundennummer
   * @param status Löschstatus
   * @throws net.sourceforge.wisim.dao.WiSimDAOException Fehler beim Abfragen
   * der DB
   * @throws net.sourceforge.wisim.dao.WiSimDAOWriteException Fehler beim
   * Schreiben in die DB
   */
  @Override
  public int setKundenLoeschStatus(int KdNr, boolean status) throws WiSimDAOException, WiSimDAOWriteException {
    // Serverlog
    logger.finest("com.pixelpark.wisim.dao.WiSimDAOImpl.createContract(Contract) Action: start");

    String sql;
    int key = -1;
    try {
      try {
        Statement stmt = conn.createStatement();
        sql = "UPDATE kd SET " + "kd_deleted ='" + status + "' " + "WHERE kd_Nr = " + KdNr + "";

        stmt.executeUpdate(sql);
        ResultSet lastKey = stmt.getGeneratedKeys();
        while (lastKey.next()) {
          key = lastKey.getInt(1);
        }
      } catch (SQLException sqlE) {
        conn.rollback();
        throw new WiSimDAOWriteException(sqlE.getMessage());
      }
    } catch (SQLException sqlE) {
      throw new WiSimDAOException(sqlE.getMessage());
    }
    return key;
  }

  /**
   * Lï¿½scht einen Lieferlisten-Eintrag aus der Datenbank
   *
   * @return int
   * @param ltId Lieferanten-ID
   * @param etNr Einzelteilnummer
   * @throws WiSimDAOWriteException Fehler beim Schreiben in die DB
   */
  @Override
  public int loescheLieferliste(int ltId, int etNr) throws WiSimDAOWriteException {
    String sql;
    try {
      Statement stmt = conn.createStatement();
      sql = "DELETE FROM rel_lt_et WHERE f_et_Nr = '" + etNr + "' AND f_lt_Nr = '" + ltId + "'";
      int res = stmt.executeUpdate(sql);
      return res;
    } catch (SQLException sqlE) {
      throw new WiSimDAOWriteException(sqlE.getMessage());
    }
  }

  /**
   * Gibt alle Lagerplätz des Lagers aus
   *
   * @throws WiSimDAOException if a database problem occurs or the connection
   * was never initialized
   * @return Collection aller Lagerplätze
   */
  @Override
  public Collection<WarehouseLocation> getLagerplaetze() throws WiSimDAOException {
    // Serverlog
    logger.finest("com.pixelpark.wisim.dao.WiSimDAOImpl.getEtat Action: start");
    String sql;
    Collection<WarehouseLocation> lagerplaetze = new ArrayList<>();
    try {
      sql = "SELECT lg_StellplatzNr FROM lg";
      Statement stmt = conn.createStatement();
      ResultSet rset = stmt.executeQuery(sql);

      while (rset.next()) {
        WarehouseLocation lgplatz = new WarehouseLocation(rset.getString(1));
        lagerplaetze.add(lgplatz);
      }

    } catch (SQLException sqlE) {
      throw new WiSimDAOException(sqlE.getMessage());
    }
    return lagerplaetze;
  }

  /**
   * Liefert alle Lagerplätze zurück, die einen bestimmten Artikel enthalten
   *
   * @return Collection mit den Lagerplätzen wo das WiSimComponent liegt.
   * @param etNr WiSimComponent Nummer
   * @throws WiSimDAOException if a database problem occurs or the connection
   * was never initialized
   */
  @Override
  public Collection<WarehouseLocation> getLagerplaetze(int etNr) throws WiSimDAOException {
    String sql = "SELECT * FROM rel_et_lg WHERE f_et_Nr=" + etNr;

    Collection<WarehouseLocation> lager;
    try {
      Statement stmt = conn.createStatement();
      ResultSet res = stmt.executeQuery(sql);
      lager = new ArrayList<>();
      while (res.next()) {
        WarehouseLocation lgp = new WarehouseLocation();
        lgp.setStellplatzNr(res.getString(1));
        lgp.setEinzelteilNr(etNr);
        lgp.setBestand(res.getInt(3));
        lgp.setMaxBestand(res.getInt(4));
        lager.add(lgp);
      }
    } catch (SQLException e) {
      throw new WiSimDAOException(e.getMessage());
    }
    return lager;
  }

  /**
   * Gibt eine Liste aller Einzelteile die sich im Lager befinden zurück. Jeder
   * Eintrag hat neben den Informationen WiSimComponent-Name auch die Zahlen für
   * Bestand, Mindestbestand und MaxBestand. Jedes WiSimComponent kommt nur
   * einmal vor. Befindt sich ein WiSimComponent auf mehrern Lagerplätzen, so
   * werden die Bestände entsprechend summiert.
   *
   * @throws WiSimDAOException if a database problem occurs or the connection
   * was never initialized
   * @return Vector mit allen Einzelteilen die sich im Lager befinden.
   */
  @Override
  public ArrayList<ComponentWarehouseItem> getEinzelteilLagerElement() throws WiSimDAOException {
    // Serverlog
    logger.finest("com.pixelpark.wisim.dao.WiSimDAOImpl.getEtat Action: start");
    String sql;
    ArrayList<ComponentWarehouseItem> einzelteillagerelemente = new ArrayList<>();
    try {
      sql = "SELECT f_et_Nr, SUM(rel_et_lg_Bestand) AS Bestand, SUM(rel_et_lg_MaxBestand) AS MaxBestand FROM rel_et_lg GROUP BY f_et_Nr";
      Statement stmt = conn.createStatement();
      ResultSet rset = stmt.executeQuery(sql);
      int i = 0;
      while (rset.next()) {
        WiSimComponent et = getComponent(rset.getInt(1));
        ComponentWarehouseItem etElem = new ComponentWarehouseItem();
        etElem.setEinzelteilName(et.getName());
        etElem.setId(et.getNr());
        etElem.setBestand(rset.getInt(2));
        etElem.setMaxBestand(rset.getInt(3));
        etElem.setMinBestand(et.getMindestbestand());
        einzelteillagerelemente.add(i, etElem);
        i++;
      }

      int a = einzelteillagerelemente.size() - 1;
      while (a >= 0) {
        ComponentWarehouseItem etElem = einzelteillagerelemente.get(a);
        ArrayList<String> lagerplaetze = new ArrayList<>(); // TODO Warum String?
        sql = "SELECT f_lg_StellplatzNr FROM rel_et_lg WHERE f_et_Nr = " + etElem.getId();
        stmt = conn.createStatement();
        rset = stmt.executeQuery(sql);
        while (rset.next()) {
          lagerplaetze.add(rset.getString(1));
        }
        etElem.setLagerplaetze(lagerplaetze);
        einzelteillagerelemente.set(a, etElem);
        a--;
      }

    } catch (SQLException sqlE) {
      throw new WiSimDAOException(sqlE.getMessage());
    }
    return einzelteillagerelemente;
  }

  /**
   * Gibt eine Liste aller Einzelteile die sich auf dem angegebenen
   * WarehouseLocation befinden zurï¿½ck. Jeder Eintrag hat neben den
   * Informationen WiSimComponent-Name auch die Zahlen fï¿½r Bestand,
   * Mindestbestand und MaxBestand.
   *
   * @param lagerplatz Der WarehouseLocation
   * @throws WiSimDAOException if a database problem occurs or the connection
   * was never initialized
   * @return Collection mit allen Einzelteilen des Lagerplatzes
   */
  @Override
  public ArrayList<ComponentWarehouseItem> getEinzelteilLagerElement(String lagerplatz) throws WiSimDAOException {
    // Serverlog
    logger.finest("com.pixelpark.wisim.dao.WiSimDAOImpl.getEtat Action: start");
    String sql;
    ArrayList<ComponentWarehouseItem> einzelteillagerelemente = new ArrayList<>();

    try {
      sql = "SELECT f_et_Nr, rel_et_lg_Bestand, rel_et_lg_MaxBestand FROM rel_et_lg WHERE f_lg_StellplatzNr = '" + lagerplatz + "'";
      Statement stmt = conn.createStatement();
      ResultSet rset = stmt.executeQuery(sql);
      while (rset.next()) {
        ComponentWarehouseItem etElem = new ComponentWarehouseItem();
        etElem.setId(rset.getInt(1));
        etElem.setBestand(rset.getInt(2));
        etElem.setMaxBestand(rset.getInt(3));
        einzelteillagerelemente.add(etElem);
      }
    } catch (SQLException sqlE) {
      throw new WiSimDAOException(sqlE.getMessage());
    }
    return einzelteillagerelemente;
  }

  /**
   * Gibt eine Liste aller Article die sich im Lager befinden zurück. Jeder
   * Eintrag hat neben den Informationen Article-Name auch die Zahlen für
   * Bestand, Mindestbestand und MaxBestand. Jeder Artikel kommt nur einmal vor.
   * Befindet sich ein Artikel auf mehreren Lagerplätzen, so werden die Bestände
   * entsprechend summiert.
   *
   * @throws WiSimDAOException if a database problem occurs or the connection
   * was never initialized
   * @return ArrayList mit allen Artikeln die im Lager sind.
   */
  @Override
  public ArrayList<ComponentWarehouseItem> getArtikelLagerElement() throws WiSimDAOException {
    // Serverlog
    logger.finest("com.pixelpark.wisim.dao.WiSimDAOImpl.getArtikelLagerElement Action: start");
    String sql;
    ArrayList<ComponentWarehouseItem> artikellagerelemente = new ArrayList<>();
    try {
      sql = "SELECT f_art_Nr, SUM(rel_art_lg_Bestand) AS Bestand, SUM(rel_art_lg_MaxBestand) AS MaxBestand FROM rel_art_lg GROUP BY f_art_Nr";
      Statement stmt = conn.createStatement();
      ResultSet rset = stmt.executeQuery(sql);
      int i = 0;
      while (rset.next()) {
        Article art = getArtikel(rset.getInt(1));
        ComponentWarehouseItem etElem = new ComponentWarehouseItem();
        etElem.setEinzelteilName(art.getName());
        etElem.setId(art.getNr());
        etElem.setBestand(rset.getInt(2));
        etElem.setMaxBestand(rset.getInt(3));
        etElem.setMinBestand(art.getMindestbestand());
        artikellagerelemente.add(i, etElem);
        i++;
      }

      int a = artikellagerelemente.size() - 1;
      while (a >= 0) {
        ComponentWarehouseItem etElem = artikellagerelemente.get(a);
        ArrayList<String> lagerplaetze = new ArrayList<>();
        sql = "SELECT f_lg_StellplatzNr FROM rel_art_lg WHERE f_art_Nr = " + etElem.getId();
        stmt = conn.createStatement();
        rset = stmt.executeQuery(sql);
        while (rset.next()) {
          lagerplaetze.add(rset.getString(1));
        }
        etElem.setLagerplaetze(lagerplaetze);
        artikellagerelemente.set(a, etElem);
        a--;
      }

    } catch (SQLException sqlE) {
      throw new WiSimDAOException(sqlE.getMessage());
    }
    return artikellagerelemente;
  }

  /**
   * Gibt eine Liste aller Article die sich auf dem angegebenen
   * WarehouseLocation befinden zurï¿½ck. Jeder Eintrag hat neben den
   * Informationen Article-Name auch die Zahlen für Bestand, Mindestbestand und
   * MaxBestand.
   *
   * @param lagerplatz Der WarehouseLocation
   * @throws WiSimDAOException if a database problem occurs or the connection
   * was never initialized
   * @return ArrayList mit allen Artikeln die auf dem WarehouseLocation liegen
   */
  @Override
  public ArrayList<ComponentWarehouseItem> getArtikelLagerElement(String lagerplatz) throws WiSimDAOException {
    // Serverlog
    logger.finest("com.pixelpark.wisim.dao.WiSimDAOImpl.getEtat Action: start");
    String sql;
    ArrayList<ComponentWarehouseItem> artikellagerelemente = new ArrayList<>();

    try {
      sql = "SELECT f_art_Nr, rel_art_lg_Bestand, rel_art_lg_MaxBestand FROM rel_art_lg WHERE f_lg_StellplatzNr = '" + lagerplatz + "'";
      Statement stmt = conn.createStatement();
      ResultSet rset = stmt.executeQuery(sql);
      while (rset.next()) {
        ComponentWarehouseItem etElem = new ComponentWarehouseItem();
        etElem.setId(rset.getInt(1));
        etElem.setBestand(rset.getInt(2));
        etElem.setMaxBestand(rset.getInt(3));
        artikellagerelemente.add(etElem);
      }
    } catch (SQLException sqlE) {
      throw new WiSimDAOException(sqlE.getMessage());
    }
    return artikellagerelemente;
  }

  /**
   * Erhöht/verringert den Bestand eines Einzelteils im Lager.
   *
   * @param etNr WiSimComponent Nummer
   * @param menge neue Menge = aktuelleMenge + menge Das heißt, wenn eine
   * negative Menge angegeben wird, so erniedrigt man den Bestand.
   * @throws WiSimDAOWriteException if a database problem occurs or the
   * connection was never initialized
   * @return int Anzahl der tatsächlich in die DB eingetragenen Einzelteile
   */
  @Override
  public int setEinzelteilLagerBestand(int etNr, int menge) throws WiSimDAOWriteException {
    // Serverlog
    logger.finest("com.pixelpark.wisim.dao.WiSimDAOImpl.getEtat Action: start");
    String sql;

    try {
      sql = "SELECT rel_et_lg_Bestand, rel_et_lg_MaxBestand FROM rel_et_lg WHERE f_et_Nr = " + etNr;
      Statement stmt = conn.createStatement();
      ResultSet rset = stmt.executeQuery(sql);
      int bestand = -1;
      int maxBestand = -1;

      while (rset.next()) {
        bestand = rset.getInt(1);
        maxBestand = rset.getInt(2);
      }

      if ((bestand + menge) < 0) {
        if (bestand > 0) {
          menge = bestand * -1;
        } else {
          return 0;
        }
      }

      if ((bestand + menge) > maxBestand) {
        menge = maxBestand - bestand;
      }

      sql = "UPDATE rel_et_lg SET rel_et_lg_Bestand = rel_et_lg_Bestand + " + menge + " WHERE f_et_Nr = " + etNr;

      stmt = conn.createStatement();
      stmt.executeUpdate(sql);

      return menge;

    } catch (SQLException sqlE) {
      throw new WiSimDAOWriteException(sqlE.getMessage());
    }
  }

  /**
   * Erhöht/verringert den Bestand eines Artikels im Lager.
   *
   * @param artNr Article Nummer
   * @param menge neue Menge = aktuelleMenge + menge Das heißt, wenn eine
   * negative Menge angegeben wird, so verringert man den Bestand.
   * @throws WiSimDAOWriteException if a database problem occurs or the
   * connection was never initialized
   * @return True: Bestand wurde erhöht/verringert. False: Bei Erhöhung:
   * Gelieferte Menege übertrifft den Maximalbestand. Der neue Bestand ist jetzt
   * der Maximalbestand, der Rest der Lieferung wird ignoriert. Bei
   * Erniedrigung: Der Bestand dieses Artikels ist schon auf 0.
   */
  @Override
  public synchronized boolean setArtikelLagerBestand(int artNr, int menge) throws WiSimDAOWriteException {
    // Serverlog
    logger.finest("com.pixelpark.wisim.dao.WiSimDAOImpl.getEtat Action: start");
    String sql;

    try {
      sql = "SELECT rel_art_lg_Bestand, rel_art_lg_MaxBestand FROM rel_art_lg WHERE f_art_Nr = " + artNr;
      Statement stmt = conn.createStatement();
      ResultSet rset = stmt.executeQuery(sql);
      int bestand = -1;
      int maxBestand = -1;
      boolean status;

      while (rset.next()) {
        bestand = rset.getInt(1);
        maxBestand = rset.getInt(2);
      }

      if ((bestand + menge) < 0) {
        return false;
      }

      if ((bestand + menge) > maxBestand) {
        status = false;
        menge = maxBestand - bestand;
      } else {
        status = true;
      }

      sql = "UPDATE rel_art_lg SET rel_art_lg_Bestand = rel_art_lg_Bestand + " + menge + " WHERE f_art_Nr = " + artNr;
      stmt = conn.createStatement();
      stmt.executeUpdate(sql);
      return status;

    } catch (SQLException sqlE) {
      throw new WiSimDAOWriteException(sqlE.getMessage());
    }
  }

  /**
   * Liefert eine Liste aller Arbeitsplaetze.
   *
   * @throws WiSimDAOException if an error occurs
   * @return Vector mit allen Arbeitsplaetzen
   */
  @Override
  public synchronized ArrayList<WorkPlace> getArbeitsplaetze() throws WiSimDAOException {
    String sql = "SELECT * FROM ap";
    ArrayList<WorkPlace> arbeitsplaetze = new ArrayList<>();
    try {
      Statement stmt = conn.createStatement();
      ResultSet res = stmt.executeQuery(sql);
      WorkPlace arbeitsplatz;
      while (res.next()) {
        arbeitsplatz = new WorkPlace(res.getInt(1), res.getString(2), res.getInt(3), res.getInt(4));
        arbeitsplaetze.add(arbeitsplatz);
      }

      Iterator<WorkPlace> it = arbeitsplaetze.iterator();
      arbeitsplaetze = new ArrayList<>();
      while (it.hasNext()) {
        WorkPlace ap = it.next();
        sql = "SELECT nf_nachfolger FROM nf WHERE f_ap_nr = " + ap.getNr();
        ResultSet res2 = stmt.executeQuery(sql);

        int rows = 0;
        while (res2.next()) {
          rows++;
        }
        res2.beforeFirst();

        int[] nf = new int[rows];
        int i = 0;
        while (res2.next()) {
          nf[i] = res2.getInt(1);
          i++;
        }
        ap.setNachfolger(nf);

        sql = "SELECT vg_vorgaenger FROM vg WHERE f_ap_nr = " + ap.getNr();
        res2 = stmt.executeQuery(sql);
        rows = 0;
        while (res2.next()) {
          rows++;
        }
        res2.beforeFirst();
        int[] vg = new int[rows];
        i = 0;
        while (res2.next()) {
          vg[i] = res2.getInt(1);
          i++;
        }
        ap.setVorgaenger(vg);
        arbeitsplaetze.add(ap);
      }
    } catch (SQLException e) {
      throw new WiSimDAOException(e.getMessage());
    }
    return arbeitsplaetze;
  }

  /**
   * Holt ein Arbeitsplatzlager aus der DB
   *
   * @param arbeitsplatzNr Arbeitsplatznummer
   * @param lagerTyp Lagertyp
   * @throws WiSimDAOException Fehler beim Lesen aus der DB
   * @return Collection
   */
  @Override
  public synchronized ArrayList<WorkPlaceStore> getArbeitsplatzLager(int arbeitsplatzNr, String lagerTyp) throws WiSimDAOException {
    String sql = "SELECT * FROM rel_et_ap" + " WHERE f_ap_Nr = " + arbeitsplatzNr + " AND rel_et_ap_Lagertyp = '" + lagerTyp + "';";

    ArrayList<WorkPlaceStore> apLager = new ArrayList<>();

    try {
      Statement stmt = conn.createStatement();
      ResultSet res = stmt.executeQuery(sql);
      while (res.next()) {

        WorkPlaceStore apl = new WorkPlaceStore(res.getInt(1), res.getInt(2), res.getString(3), res.getInt(4), res.getInt(5), res.getInt(6));
        apLager.add(apl);
      }
    } catch (SQLException e) {
      throw new WiSimDAOException(e.getMessage());
      //FIXME ab und zu kommt es zu einem Datenbankfehler (fehlende Tabelle rel_et_ap), hï¿½ngt wahrscheinlich mit den Threads zusammen, die wï¿½hrend des Resets noch auf die DB zugreifen
    }

    return apLager;
  }

  /**
   * Holt ein Arbeitsplatzlager aus der DB
   *
   * @param arbeitsplatzNr Arbeitsplatznummer
   * @param einzelteilNr Einzelteilnummer
   * @param typ Lagertyp
   * @throws WiSimDAOException Fehler beim Lesen aus der DB
   * @return WorkPlaceStore
   */
  @Override
  public synchronized WorkPlaceStore getWorkPlaceStore(int arbeitsplatzNr, int einzelteilNr, String typ) throws WiSimDAOException {
    String sql = "SELECT * FROM rel_et_ap" + " WHERE f_ap_Nr = " + arbeitsplatzNr + " AND rel_et_ap_Lagertyp = '" + typ + "' AND f_et_Nr = " + einzelteilNr;

    WorkPlaceStore workPlaceStore = new WorkPlaceStore();

    try {
      Statement stmt = conn.createStatement();
      ResultSet res = stmt.executeQuery(sql);
      while (res.next()) {
        workPlaceStore = new WorkPlaceStore(res.getInt(1), res.getInt(2), res.getString(3), res.getInt(4), res.getInt(5), res.getInt(6));
      }
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
    return workPlaceStore;
  }

  /**
   * Gibt Stückliste für einen bestimmten Artikel zurück. Der Key der Hashmap
   * ist die WiSimComponent-Nummer, der Value ist die erforderliche Menge um 1
   * Stück von diesem Artikel zu produzieren.
   *
   * @param artNr Article nummer
   * @throws WiSimDAOException if an database error occurs
   * @return HashMap (parts list of the article)
   */
  @Override
  public HashMap<String, String> getPartsListForArticle(int artNr) throws WiSimDAOException {
    HashMap<String, String> partsList = new HashMap<>();
    String sql = "SELECT f_et_Nr, rel_art_et_EinzelteileMenge FROM rel_art_et WHERE f_art_Nr = " + artNr;

    try {
      Statement stmt = conn.createStatement();
      ResultSet rset = stmt.executeQuery(sql);
      while (rset.next()) {
        partsList.put(String.valueOf(rset.getInt(1)), String.valueOf(rset.getInt(2)));
      }
    } catch (SQLException e) {
      throw new WiSimDAOException(e.getMessage());
    }
    return partsList;
  }

  /**
   * Gibt die Auftragsposition zurück die zu dem entsprechenden Auftrag gehört.
   *
   * @param atNr Auftragsnummer
   * @param artNr Artikelnummer
   * @throws WiSimDAOException if a database problem occurs or the connection
   * was never initialized
   * @return ContractOrderItem.
   */
  @Override
  public ContractOrderItem getContractOrderItem(int atNr, int artNr) throws WiSimDAOException {
    // Serverlog
    logger.finest("com.pixelpark.wisim.dao.WiSimDAOImpl.getAuftrag Action: start");
    ContractOrderItem contractOrderItem = new ContractOrderItem();
    try {
      String sql = "SELECT f_art_Nr, f_at_Nr, rel_at_art_Bestellmenge FROM rel_at_art WHERE f_at_Nr = " + atNr + " AND f_art_Nr = " + artNr;
      Statement stmt = conn.createStatement();
      ResultSet rset = stmt.executeQuery(sql);

      while (rset.next()) {
        contractOrderItem.setArtNr(rset.getInt(1));
        contractOrderItem.setAtNr(rset.getInt(2));
        contractOrderItem.setBestellmenge(rset.getInt(3));
      }

    } catch (SQLException sqlE) {
      throw new WiSimDAOException(sqlE.getMessage());
    }
    return contractOrderItem;
  }

  /**
   * Gibt die Bestellmenge eines bestimmten Artikels in einem Contract zurï¿½ck.
   *
   * @param atNr Auftrags Nr.
   * @param artNr Article Nr.
   * @throws WiSimDAOException if an database error occurs
   * @return Bestellmenge
   */
  @Override
  public int getVertragsPositionMenge(int atNr, int artNr) throws WiSimDAOException {
    int menge = 0;
    try {
      String sql = "SELECT rel_at_art_Bestellmenge FROM rel_at_art WHERE f_at_Nr = " + atNr + " AND f_art_Nr = " + artNr;
      Statement stmt = conn.createStatement();
      ResultSet rset = stmt.executeQuery(sql);
      while (rset.next()) {
        menge = rset.getInt(1);
      }
    } catch (SQLException e) {
      throw new WiSimDAOException(e.getMessage());
    }
    return menge;
  }

  /**
   * Setzt die Position eines bestimmten Auftrags in einem Contract.
   *
   * @return int
   * @param atp Auftragspositions
   * @throws WiSimDAOWriteException Fehler beim Schreiben in die DB
   */
  @Override
  public int setAuftragsPosition(ContractOrderItem atp) throws WiSimDAOWriteException {
    String sql = "insert into rel_at_art (f_art_Nr, f_at_Nr, rel_at_art_Bestellmenge) " + "VALUES ( " + atp.getArtNr() + ", " + atp.getAtNr() + ", " + atp.getBestellmenge() + ");";

    try {
      // Create a Statement
      Statement stmt = conn.createStatement();
      stmt.executeUpdate(sql);

    } catch (SQLException e) {
      throw new WiSimDAOWriteException(e.getMessage());
    }
    return -1;
  }

  /**
   * Setzt die Zahl der Arbeiter fï¿½r einen WorkPlace
   *
   * @param apNr WorkPlace Nummer
   * @param anzahl Anzahl der Mitarbeiter
   * @throws WiSimDAOWriteException If an error occurs
   */
  @Override
  public void setArbeiterZahl(int apNr, int anzahl) throws WiSimDAOWriteException {
    try {
      String sql = "UPDATE ap SET ap_AnzArbeiter = " + anzahl + " WHERE ap_Nr = " + apNr;
      Statement stmt = conn.createStatement();
      stmt.executeUpdate(sql);
    } catch (SQLException e) {
      throw new WiSimDAOWriteException(e.getMessage());
    }
  }

  /**
   * Gibt die Anzahl der Arbeitsplï¿½tze im Lager zurï¿½ck
   *
   * @throws WiSimDAOException Fehler beim Lesen aus der DB
   * @return int
   */
  @Override
  public int getAnzahlArbeitsplaetze() throws WiSimDAOException {
    String sql = "SELECT MAX(ap_nr) FROM ap";
    int anzahl = -1;
    try {
      Statement stmt = conn.createStatement();
      ResultSet res = stmt.executeQuery(sql);

      while (res.next()) {
        anzahl = res.getInt(1);
      }
    } catch (SQLException e) {
      throw new WiSimDAOException(e.getMessage());
    }
    return anzahl;
  }

  /**
   * Holt eine Liste mit NetzplanElementen aus der DB
   *
   * @return Collection aller Vorgaenge (NetzplanElemente)
   * @throws WiSimDAOException If an error occurs
   */
  @Override
  public ArrayList<NetworkplanElement> getNetworkplanElements() throws WiSimDAOException {
    ArrayList<NetworkplanElement> networkplanElements = new ArrayList<>();

    try {
      /**
       * Select Statement
       */
      Statement stmt = conn.createStatement();
      String sql = "SELECT ap_Nr, ap_Dauer, ap_Beschreibung, vg_vorgaenger FROM ap, vg WHERE ap_Nr = f_ap_nr";

      ResultSet rset = stmt.executeQuery(sql);

      HashMap<Integer, ArrayList<Integer>> parentBasket = new HashMap<>();
      ArrayList<Integer> completed = new ArrayList<>();

      while (rset.next()) {

        NetworkplanElement np = new NetworkplanElement(rset.getInt(1), (double) (rset.getInt(2)), rset.getString(3), new int[0]);

        /**
         * This is a new element
         */
        if (!completed.contains(np.getNumber())) {
          completed.add(np.getNumber());
          networkplanElements.add(np);
          ArrayList<Integer> parent = new ArrayList<>();
          parent.add(rset.getInt(4));
          parentBasket.put(np.getNumber(), parent);

          /**
           * This is an existing element with more than one child
           */
        } else {
          (parentBasket.get(np.getNumber())).add(rset.getInt(4));
        }
      }

      int a = 0;

      /**
       * Set each element's childs from the childBasket
       */
      while (a < networkplanElements.size()) {
        NetworkplanElement np = networkplanElements.get(a);
        ArrayList<Integer> parent = parentBasket.get(np.getNumber());

        int parents[] = new int[parent.size()];

        int b = 0;
        while (b < parent.size()) {
          parents[b] = parent.get(b);
          b++;
        }

        np.setParent(parents);
        a++;
      }
    } catch (SQLException e) {
      throw new WiSimDAOException(e.getMessage());
    }
    return networkplanElements;
  }

  /**
   * Update a workplace
   *
   * @param workplace
   * @throws WiSimDAOWriteException If an error occurs
   */
  @Override
  public void updateWorkplace(WorkPlace workplace) throws WiSimDAOWriteException {
    String sql = "UPDATE ap SET ap_Beschreibung = '" + workplace.getBeschreibung() + "', ap_Dauer = '" + workplace.getDauer() + "' WHERE ap_Nr = " + workplace.getNr();

    try {
      // Create a Statement
      Statement stmt = conn.createStatement();
      stmt.executeUpdate(sql);

    } catch (SQLException e) {
      throw new WiSimDAOWriteException(e.getMessage());
    }
  }

  /**
   * Resets the DB
   *
   * @throws WiSimDAOException If an error occurs
   */
  @Override
  public void dbReset() throws WiSimDAOException {
    String[] queriesFinal = parseSQLFile("/sql/complete.sql");

    try {

      Statement stmt = conn.createStatement();
      for (String queriesFinal1 : queriesFinal) {
        stmt.executeUpdate(queriesFinal1);
      }

    } catch (SQLException e) {
      throw new WiSimDAOException(e.getMessage());
    }

  }

  /**
   * Resets the DB
   *
   * @throws WiSimDAOException If an error occurs
   */
  @Override
  public void simulationReset() throws WiSimDAOException {
    String[] queriesFinal = parseSQLFile("/sql/simulation.sql");

    try {

      Statement stmt = conn.createStatement();
      for (String queriesFinal1 : queriesFinal) {
        stmt.executeUpdate(queriesFinal1);
      }

    } catch (SQLException e) {
      throw new WiSimDAOException(e.getMessage());
    }

  }

  private String[] parseSQLFile(String file) throws WiSimDAOException {
    String result;
    Pattern p;
    URL url = getClass().getResource(file);

    byte buffer[];
    try {
      InputStream in = url.openStream();
      DataInputStream data = new DataInputStream(in);

      buffer = new byte[in.available()];
      data.readFully(buffer);
      in.close();
    } catch (IOException e) {
      throw new WiSimDAOException(e.getMessage());
    }

    result = new String(buffer, 0, buffer.length);
    p = Pattern.compile("\n");

    String[] anweisungen = p.split(result);
    int n = 0;
    for (int i = 0; i < anweisungen.length; i++) {
      if (anweisungen[i].startsWith("#") || anweisungen[i].toCharArray().length == 1) {
        anweisungen[i] = null;
        n++;
      }
    }

    String[] queries = new String[anweisungen.length - n];
    int j = 0;
    for (String anweisungen1 : anweisungen) {
      if (anweisungen1 != null) {
        queries[j] = anweisungen1;
        j++;
      }
    }

    int i = 0;
    int m = 1;
    while (i < queries.length) {
      if (queries[i].startsWith(" ")) {
        queries[i - m] = queries[i - m].concat(queries[i]);
        m++;
        queries[i] = null;
      } else if (queries[i].startsWith(")")) {
        queries[i - m] = queries[i - m].concat(queries[i]);
        queries[i] = null;
      } else {
        m = 1;
      }
      i++;
    }

    int a = 0;
    for (String querie : queries) {
      if (querie == null) {
        a++;
      }
    }

    String[] queriesFinal = new String[queries.length - a];
    int c = 0;
    for (String querie : queries) {
      if (querie != null) {
        queriesFinal[c] = querie;
        c++;
      }
    }

    for (int z = 0; z < queriesFinal.length; z++) {
      char ersetzen[] = queriesFinal[z].toCharArray();
      for (int e = 0; e < ersetzen.length; e++) {
        if (ersetzen[e] == ';' || ersetzen[e] == '\n') {
          ersetzen[e] = ' ';
        }
      }
      queriesFinal[z] = String.valueOf(ersetzen);
    }
    return queriesFinal;
  }

  /**
   * @return
   */
  @Override
  public String getDbName() {
    return dbName;
  }

  /**
   * @return
   */
  @Override
  public String getHostName() {
    return hostName;
  }

  /**
   * @return
   */
  @Override
  public String getPassword() {
    return password;
  }

  /**
   * @return
   */
  @Override
  public String getPort() {
    return port;
  }

  /**
   * @return
   */
  @Override
  public String getUser() {
    return user;
  }

  @Override
  public void updateDBSettings(String hostname, String port, String user, String password) {

    String values = hostname + "\n" + port + "\n" + user + "\n" + password;
    byte[] b = values.getBytes();
    Encoder encoder = Base64.getEncoder();
    values = encoder.encodeToString(b);

    try {
      File file = new File("config.dat");
      FileWriter fw = new FileWriter(file);
      fw.write(values);
      fw.close();
    } catch (IOException e) {
      //Todo
    }
  }

  /**
   * TODO
   *
   * @return
   * @throws WiSimDAOException
   */
  @Override
  public HashMap<String, ArrayList<Integer>> getEtCapacity() throws WiSimDAOException {
    HashMap<String, ArrayList<Integer>> etCapacity = new HashMap<>();
    String sql = "SELECT et_name , et_Mindestbestand, rel_et_lg_bestand FROM rel_et_lg, et WHERE f_et_nr = et_nr;";

    try {
      Statement stmt = conn.createStatement();
      ResultSet res = stmt.executeQuery(sql);

      while (res.next()) {
        ArrayList<Integer> bestand = new ArrayList<>();
        bestand.add(res.getInt(2));
        bestand.add(res.getInt(3));
        etCapacity.put(res.getString(1), bestand);
      }
    } catch (SQLException e) {
      throw new WiSimDAOException(e.getMessage());
    }
    return etCapacity;
  }
}
