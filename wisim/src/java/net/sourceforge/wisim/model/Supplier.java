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
 * Lieferant.java
 *
 * Created on 26. Februar 2003, 20:04
 */

package net.sourceforge.wisim.model;

/**
 *
 * @author  Kay Patzwald
 */
public class Supplier  extends Person {
  private String lieferqualitaet;
  private String zuverlaessigkeit;
  
  /** Creates a new instance of Supplier */
  public Supplier() {
    super();
  }
  
  /** Creates a new instance of Supplier */
  public Supplier(int id, String firma, String name, String vorname, String telefon, String fax, 
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
