/*
 * Einzelteile.java
 *
 * Created on 1. März 2003, 11:38
 */

package net.sourceforge.wisim.model;

/**
 *
 * @author  Kay Patzwald
 */
public class Einzelteil {
  
  private int nr;
  private String name;
  private int mindestbestand;
  
  /** Creates a new instance of Einzelteile */
  public Einzelteil() {
  }
  
  /** Creates a new instance of Einzelteile */
  public Einzelteil(int nr, String name, int mindestbestand) {
    this.nr = nr;
    this.name = name;
    this.mindestbestand = mindestbestand;
  }
  
  public int getNr()
  {
    return nr;
  }
  
  public int getMindestbestand()
  {
    return mindestbestand;
  }
  
  public String getName()
  {
    return name;
  }
  
  public void setNr(int nr)
  {
    this.nr = nr;
  }
    
  public void setName(String name)
  {
    this.name = name;
  }
    
  public void setMindestbestand(int mindestbestand)
  {
    this.mindestbestand = mindestbestand;
  }
}
