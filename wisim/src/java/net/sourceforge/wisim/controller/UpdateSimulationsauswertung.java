package net.sourceforge.wisim.controller;

import java.util.*;

/** Überprüft das ActualTime Objekt in dem die aktuelle Zeit gespeichert wird. Bei
 *  einem Minutenwechsel aktualisiert der Thread das jPanelSimulationStart, sowie die
 *  Datumsanzeige im Menü von dem aus sich alle Panes das Datum herholen.
 *  @author benjamin.pasero
 */
public class UpdateSimulationsauswertung extends Thread {
    private ActualTime actTime;
    private int actDay;
    private java.util.Date actDate;
    private WiSimMainController wiSimMainController;
    private GregorianCalendar gc;
    private boolean beendeNachEinerWoche;
    private int dayChanges;
    
    //JPanes
    private JPanelSimulationStart jPanelSimulationStart;
    
    
    /** Erzeugt eine neue Instanz UpdateSimulationsauswertung.
     * @param beendeNachEinerWoche
     * @param wiSimMainController
     * @param actTime Der aktuelle Tag
     */
    public UpdateSimulationsauswertung(ActualTime actTime, WiSimMainController wiSimMainController, boolean beendeNachEinerWoche) {
        super("UpdateSimulationsauswertung");
        this.actTime = actTime;
        this.wiSimMainController = wiSimMainController;
        this.beendeNachEinerWoche = beendeNachEinerWoche;
        actDay = 0;
        gc = new GregorianCalendar();
        dayChanges = 0;
        
        jPanelSimulationStart = (JPanelSimulationStart) wiSimMainController.getActions().get("SimulationStart");
    }
    
    /** Startet diesen Thread und wartet solange bis sich die Minute im Objekt actTime
     * geändert hat.
     */
    public void run() {
        while (true) {
            if (isInterrupted()) {
                break;
            }
            
            actDate = actTime.getDate(this);
            if (isInterrupted())
            {
            	break;
            }
            gc.setTimeInMillis(actDate.getTime());
            
            //Refresh
            jPanelSimulationStart.setActDate(new java.sql.Date(actDate.getTime()));
            
            if (jPanelSimulationStart.getIsActive())
                jPanelSimulationStart.refreshTextFieldDate(actDate);
            
            //Zeitanzeige im Menü wird aktualisiert
            wiSimMainController.refreshTextFieldDate(actDate);
            
            //Bei Tageswechsel Refresh der Auswertung
            if (gc.get(5) != actDay) {
                actDay = gc.get(5);
                dayChanges++;
                jPanelSimulationStart.refreshJTreeEinkauf();
                jPanelSimulationStart.refreshJTreeProduktion();
                jPanelSimulationStart.refreshJTreeVertrieb();
            }
            
            //Beende nach 5 Tagen falls beendeNachEinerWoche == TRUE
            if (beendeNachEinerWoche && dayChanges == 5) {
                jPanelSimulationStart.startStopButtonDoClick();
                jPanelSimulationStart.enableControll();
                break;
            }
        }
    }
}