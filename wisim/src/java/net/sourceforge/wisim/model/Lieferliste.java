/*
 * Lieferliste.java
 *
 * Created on 2. März 2003, 00:39
 */

package net.sourceforge.wisim.model;

/**
 *
 * @author  Kay Patzwald
 */
public class Lieferliste {
  
  private int lieferantenID;
  private int einzelteilID;
  private double preis;
  private long mindestBestellMenge;
  
  /** Creates a new instance of Lieferliste */
  public Lieferliste() {
  }
  
  /** Creates a new instance of Lieferliste */
  public Lieferliste(int lieferantenID, int einzelteilID, double preis, long mindestBestellMenge) {
    this.lieferantenID = lieferantenID;
    this.einzelteilID = einzelteilID;
    this.preis = preis;
    this.mindestBestellMenge = mindestBestellMenge;
  }
  
  public void setLieferantenID(int id)
  {
    this.lieferantenID = id;
  }
  
  public void setEinzelteilID(int id)
  {
    this.einzelteilID = id;
  }
  
  public void setPreis(double preis)
  {
    this.preis = preis;
  }
  
  public void setMindestBestellMenge(long mindestBestellMenge)
  {
    this.mindestBestellMenge = mindestBestellMenge;
  }
  
  public int getLieferantenID()
  {
    return lieferantenID;
  }
  
  public int getEinzelteilID()
  {
    return einzelteilID;
  }
  
  public double getPreis()
  {
    return preis;
  }
  
  public long getMindestBestellMenge()
  {
    return mindestBestellMenge;
  }
}
