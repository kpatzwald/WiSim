/*   ********************************************************************   **
**   Copyright notice                                                       **
**                                                                          **
**   (c) 2003 WiSim Development Team					                              **
**   https://github.com/kpatzwald/WiSim   			                                  **
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
 * Article.java
 *
 * Created on 4. März 2003, 23:12
 */

package net.sourceforge.wisim.model;

/**
 *
 * @author  Denise Freitag
 */

public class Article {
    
    private int nr;
    private String name;
    private float stueckpreis;
    private int mindestbestand;
    
    /** Creates a new instance of Article */
    public Article() {
    }
    
    /** Creates a new instance of Einzelteile */
    public Article(int nr, String name, float stueckpreis, int mindestbestand){
        this.nr = nr;
        this.name = name;
        this.stueckpreis = stueckpreis;
        this.mindestbestand = mindestbestand;    
    }
    
  public int getNr(){
    return nr;
  }
  
  public String getName(){
    return name;
  }
  
  public float getStueckpreis(){
    return stueckpreis;
  }
  
  public int getMindestbestand(){
    return mindestbestand;
  }
  
  public void setNr(int nr){
    this.nr = nr;
  }
    
  public void setName(String name){
    this.name = name;
  }
  
  public void setStueckpreis(float stueckpreis){
      this.stueckpreis = stueckpreis;
  }
    
  public void setMindestbestand(int mindestbestand){
    this.mindestbestand = mindestbestand;
  }
}
