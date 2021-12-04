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
 * Created on 05.03.2003
 *
 */
package net.sourceforge.wisim.model;

/**
 * WorkPlaceStore
 *
 * @author Kay Patzwald
 */
public class WorkPlaceStore {

  private int arbeitsplatzNr;
  private int einzelteilNr;
  private String lagerTyp;
  private int bestand;
  private int maxBestand;
  private int benoetigt;

  /**
   * Creates a new instance of Arbeitsplatzlager
   */
  public WorkPlaceStore() {

  }

  /**
   * Creates a new instance of Arbeitsplatzlager
   *
   * @param arbeitsplatzNr Arbeitsplatznummer
   * @param einzelteilNr Einzelteilnummer
   * @param lagerTyp Lagertyp
   * @param bestand Bestand
   * @param maxBestand Max. Bestand
   * @param benoetigt benötigte Teile
   */
  public WorkPlaceStore(
          int arbeitsplatzNr,
          int einzelteilNr,
          String lagerTyp,
          int bestand,
          int maxBestand,
          int benoetigt) {
    this.arbeitsplatzNr = arbeitsplatzNr;
    this.einzelteilNr = einzelteilNr;
    this.lagerTyp = lagerTyp;
    this.bestand = bestand;
    this.maxBestand = maxBestand;
    this.benoetigt = benoetigt;
  }

  /**
   * Liefert die Arbeitsplatznummer
   *
   * @return int
   */
  public int getArbeitsplatzNr() {
    return arbeitsplatzNr;
  }

  /**
   * Liefert den aktuellen Bestand
   *
   * @return int
   */
  public int getBestand() {
    return bestand;
  }

  /**
   * Liefert die Einzelteile-Nummer
   *
   * @return int
   */
  public int getEinzelteilNr() {
    return einzelteilNr;
  }

  /**
   * Liefert den max. Bestand
   *
   * @return int
   */
  public int getMaxBestand() {
    return maxBestand;
  }

  /**
   * Sets the arbeitsplatzNr.
   *
   * @param arbeitsplatzNr The arbeitsplatzNr to set
   */
  public void setArbeitsplatzNr(int arbeitsplatzNr) {
    this.arbeitsplatzNr = arbeitsplatzNr;
  }

  /**
   * Sets the bestand.
   *
   * @param bestand The bestand to set
   */
  public void setBestand(int bestand) {
    this.bestand = bestand;
  }

  /**
   * Sets the einzelteilNr.
   *
   * @param einzelteilNr The einzelteilNr to set
   */
  public void setEinzelteilNr(int einzelteilNr) {
    this.einzelteilNr = einzelteilNr;
  }

  /**
   * Sets the maxBestand.
   *
   * @param maxBestand The maxBestand to set
   */
  public void setMaxBestand(int maxBestand) {
    this.maxBestand = maxBestand;
  }

  /**
   * Liefert die Anzahl der benötigten Teile
   *
   * @return int
   */
  public int getBenoetigt() {
    return benoetigt;
  }

  /**
   * Sets the benoetigt.
   *
   * @param benoetigt The benoetigt to set
   */
  public void setBenoetigt(int benoetigt) {
    this.benoetigt = benoetigt;
  }

  /**
   * Liefert den Lagertyp
   *
   * @return String
   */
  public String getLagerTyp() {
    return lagerTyp;
  }

  /**
   * Sets the lagerTyp.
   *
   * @param lagerTyp The lagerTyp to set
   */
  public void setLagerTyp(String lagerTyp) {
    this.lagerTyp = lagerTyp;
  }

}
