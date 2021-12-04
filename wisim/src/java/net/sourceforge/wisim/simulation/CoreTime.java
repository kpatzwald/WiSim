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
 * CoreTime.java
 *
 * Created on 17. März 2003, 10:23
 */

package net.sourceforge.wisim.simulation;

/** Dies ist der CoreTime-Thread der bei Simulationsstart startet. In einem
 * bestimmten Intervall wird die aktuelle Zeit im actTime-Objekt inkrementiert.
 * Der Thread läuft solange bis er unterbrochen (interrupted) wird.
 * @author  benjamin.pasero
 */
public class CoreTime extends Thread {
    private ActualTime actTime;
    private int timestep;
    private int faktor;
    
    /** Erzeugt einen neuen Thread CoreTime
     * @param actTime Das Zeitobjekt welches die aktuelle Zeit speichert.
     * @param faktor Der Faktor gibt an in welchen Zeitintervallen die Zeit inkrementiert werden soll.
     * 1 entspricht 20 Minuten pro Sekunde.
     */
    public CoreTime(ActualTime actTime, int faktor, int timestep) {
    	super("CoreTime");
        this.actTime = actTime;
        this.faktor = faktor;
        this.timestep = timestep;		//Warte 50*faktor Millisekunden bevor die Zeit geändert wird.       
    }
    
    /** Startet den CoreTime-Thread. Diese läuft solange bis er unterbrochen wird.*/
    public void run() {
        while (true) {
            if (isInterrupted()) {
                break;
            }
            
            try {
                Thread.sleep(timestep*faktor);
            }
            catch (InterruptedException e) {
                this.interrupt();
            }
            
            actTime.increaseTime();
        }
    }
}