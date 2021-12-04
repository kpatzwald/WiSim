/*   ********************************************************************   **
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
 * Lieferliste.java
 *
 * Created on 2. März 2003, 00:39
 */

package net.sourceforge.wisim.model;

/**
 *
 * @author  Kay Patzwald
 */
public class SupplyList {
  
  private int lieferantenID;
  private int einzelteilID;
  private double preis;
  private long mindestBestellMenge;
  
  /** Creates a new instance of SupplyList */
  public SupplyList() {
  }
  
  /** Creates a new instance of SupplyList */
  public SupplyList(int lieferantenID, int einzelteilID, double preis, long mindestBestellMenge) {
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
