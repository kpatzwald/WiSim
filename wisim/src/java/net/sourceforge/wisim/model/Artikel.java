/*
 * Artikel.java
 *
 * Created on 4. März 2003, 23:12
 */

package net.sourceforge.wisim.model;

/**
 *
 * @author  Denise Freitag
 */

public class Artikel {
    
    private int nr;
    private String name;
    private float stueckpreis;
    private int mindestbestand;
    
    /** Creates a new instance of Artikel */
    public Artikel() {
    }
    
    /** Creates a new instance of Einzelteile */
    public Artikel(int nr, String name, float stueckpreis, int mindestbestand){
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
