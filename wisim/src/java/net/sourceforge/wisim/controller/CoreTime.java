/*
 * CoreTime.java
 *
 * Created on 17. März 2003, 10:23
 */

package net.sourceforge.wisim.controller;

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