/*   ********************************************************************   **
**   Copyright notice                                                       **
**                                                                          **
**   (c) 2003 WiSim Development Team					                              **
**   http://wisim.sourceforge.net/   			                                  **
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