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
 * Einzelteile.java
 *
 * Created on 1. März 2003, 11:38
 */

package net.sourceforge.wisim.model;

/** Describe a WiSim Component
 *
 * @author  Kay Patzwald
 */
public class WiSimComponent {
  
  private int nr;
  private String name;
  private int mindestbestand;
  
  /** Creates a new instance of Einzelteile */
  public WiSimComponent() {
  }
  
  /** Creates a new instance of Einzelteile */
  public WiSimComponent(int nr, String name, int mindestbestand) {
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
