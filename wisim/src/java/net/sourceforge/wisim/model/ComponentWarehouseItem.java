/*   ********************************************************************   **
**   Copyright notice                                                       **
**                                                                          **
**   (c) 2003-2021 WiSim Development Team					    **
**   https://github.com/kpatzwald/WiSim   			            **
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
/**
 * @author benjamin.pasero
 *
 *
 */
package net.sourceforge.wisim.model;

import java.util.ArrayList;

/**
 * Die Summe der Einzelteile eines bestimmten Typs (Name, Einzelteile Nr,
 * Bestand, Mindestbestand, Maximalbestand und eine Liste mit Lagerplätzen wo
 * sich dieses WiSimComponent befindet.
 *
 * @author benjamin.pasero
 */
public class ComponentWarehouseItem {

  private String einzelteilName;
  private int id;
  private int minBestand;
  private int maxBestand;
  private int bestand;
  private ArrayList<String> workPlaces; // TODO Warum String?

  /**
   * Erstellt eine neue Instanz ComponentWarehouseItem. Es kann sich um einen
   * Article oder WiSimComponent im Lager handeln.
   *
   * @param einzelteilName Der Name des Einzelteils oder Article
   * @param id Nummer des Artikels oder Einzelteiles
   * @param minBestand Mindestbestand
   * @param maxBestand Maximalbestand
   * @param bestand aktueller Bestand
   * @param workplaces Lagerplätze an denen der Article oder das
   * WiSimComponent lagert
   */
  public ComponentWarehouseItem(String einzelteilName, int id, int minBestand, int maxBestand, int bestand, ArrayList<String> workplaces) {
    this.einzelteilName = einzelteilName;
    this.id = id;
    this.minBestand = minBestand;
    this.maxBestand = maxBestand;
    this.bestand = bestand;
    this.workPlaces = workplaces;
  }

  /**
   * Erzeugt ein neues ComponentWarehouseItem
   */
  public ComponentWarehouseItem() {
  }

  /**
   * Returns the bestand.
   *
   * @return int
   */
  public int getBestand() {
    return bestand;
  }

  /**
   * Gibt die Id zurück
   *
   * @return id
   */
  public int getId() {
    return id;
  }

  /**
   * Returns the einzelteilName.
   *
   * @return String
   */
  public String getEinzelteilName() {
    return einzelteilName;
  }

  /**
   * Returns the maxBestand.
   *
   * @return int
   */
  public int getMaxBestand() {
    return maxBestand;
  }

  /**
   * Returns the minBestand.
   *
   * @return int
   */
  public int getMinBestand() {
    return minBestand;
  }

  /**
   * @return Anzahl der Lagerplätze
   */
  public ArrayList<String> getLagerplaetze() {
    return workPlaces;
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
   * Sets the einzelteilName.
   *
   * @param einzelteilName The einzelteilName to set
   */
  public void setEinzelteilName(String einzelteilName) {
    this.einzelteilName = einzelteilName;
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
   * Sets the minBestand.
   *
   * @param minBestand The minBestand to set
   */
  public void setMinBestand(int minBestand) {
    this.minBestand = minBestand;
  }

  /**
   * @param workplaces
   */
  public void setLagerplaetze(ArrayList<String> workplaces) {
    this.workPlaces = workplaces;
  }

  /**
   * @param id
   */
  public void setId(int id) {
    this.id = id;
  }
}
