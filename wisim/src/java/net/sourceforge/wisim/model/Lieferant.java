/*
 * Lieferant.java
 *
 * Created on 26. Februar 2003, 20:04
 */

package net.sourceforge.wisim.model;

/**
 *
 * @author  Kay Patzwald
 */
public class Lieferant  extends Person {
  private String lieferqualitaet;
  private String zuverlaessigkeit;
  
  /** Creates a new instance of Lieferant */
  public Lieferant() {
    super();
  }
  
  /** Creates a new instance of Lieferant */
  public Lieferant(int id, String firma, String name, String vorname, String telefon, String fax, 
      String strasse, String ort, String plz, int plzId, String eMail, String zuverlaessigkeit, String lieferqualitaet) {
    super(id, vorname, name, firma, strasse, telefon, fax, eMail, plz, plzId, ort);
    this.lieferqualitaet = lieferqualitaet;
    this.zuverlaessigkeit = zuverlaessigkeit;
  }
  
  public String getLieferqualitaet() {
    return lieferqualitaet;
  }
  
  public void setLieferqualitaet(String lieferqualitaet) {
    this.lieferqualitaet = lieferqualitaet;
  }

  public String getZuverlaessigkeit() {
    return zuverlaessigkeit;
  } 
  
  public void setZuverlaessigkeit(String zuverlaessigkeit) {
    this.zuverlaessigkeit = zuverlaessigkeit;
  }

}
