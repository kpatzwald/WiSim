/*
 * Lagerplatz.java
 *
 * Created on 15. M�rz 2003, 18:45
 */

package net.sourceforge.wisim.model;

/**
 *
 * @author  Ben
 */
public class Lagerplatz {
    
    private String stellplatzNr;
    private int einzelteilNr;
    private int bestand;
    private int maxBestand;
    
    /** Creates a new instance of Lagerplatz
     * @param stellplatzNr
     */
    public Lagerplatz(String stellplatzNr) {
        this.stellplatzNr = stellplatzNr;
    }
		
		/** Creates a new instance of Lagerplatz*/
    public Lagerplatz()
    {
    }
    
		/** Creates a new instance of Lagerplatz*/
		public Lagerplatz(String stellplatzNr, int einzelteilNr, int bestand, int maxBestand)
		{
			this.stellplatzNr = stellplatzNr;
			this.einzelteilNr = einzelteilNr;
			this.bestand = bestand;
			this.maxBestand = maxBestand;
		}
  
    /**
     * @param stellplatzNr
     */    
    public void setStellplatzNr(String stellplatzNr) {
        this.stellplatzNr = stellplatzNr;
    }
    
    /**
     * @return Stellplatz
     */    
    public String getStellplatzNr() {
        return stellplatzNr;
    } 
	/**
	 * @return int
	 */
	public int getBestand()
	{
		return bestand;
	}

	/**
	 * @return int
	 */
	public int getEinzelteilNr()
	{
		return einzelteilNr;
	}

	/**
	 * @return int
	 */
	public int getMaxBestand()
	{
		return maxBestand;
	}

	/**
	 * Sets the bestand.
	 * @param bestand The bestand to set
	 */
	public void setBestand(int bestand)
	{
		this.bestand = bestand;
	}

	/**
	 * Sets the einzelteilNr.
	 * @param einzelteilNr The einzelteilNr to set
	 */
	public void setEinzelteilNr(int einzelteilNr)
	{
		this.einzelteilNr = einzelteilNr;
	}

	/**
	 * Sets the maxBestand.
	 * @param maxBestand The maxBestand to set
	 */
	public void setMaxBestand(int maxBestand)
	{
		this.maxBestand = maxBestand;
	}

}
