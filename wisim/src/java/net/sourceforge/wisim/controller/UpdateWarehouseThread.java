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

/*
 * UpdateWarehouseThread.java
 *
 * Created on 28. März 2003, 18:51
 */

package net.sourceforge.wisim.controller;

/** Solange dieser Thread läuft aktualisiert er die WarehouseLocation-Listen. Dazu gehört
 * das JPane Lager und WorkPlace. Es wird außerdem die Einzelteilauftrags-Übersicht
 * aktualisiert.
 * @author benjamin.pasero
 */
public class UpdateWarehouseThread extends Thread {
    
    //JPanes
    private JPanelWarehouse lager;
    private JPanelWorkPlaceStore apLager;
    private JPanelViewEtat etatEinsehen;
    private JPanelIncomingPayments jPanelZahlungseingang;
    
    /** Creates a new instance of UpdateWarehouseThread
     * @param wiSimMainController wiSimMainController
     */
    public UpdateWarehouseThread(WiSimMainController wiSimMainController) {
    	super("UpdateWarehouseThread");
        lager = (JPanelWarehouse) wiSimMainController.getActions().get("Warehouse");
        apLager = (JPanelWorkPlaceStore) wiSimMainController.getActions().get("WorkPlaceStore");
        etatEinsehen = (JPanelViewEtat) wiSimMainController.getActions().get("ViewEtat");
        jPanelZahlungseingang = (JPanelIncomingPayments) wiSimMainController.getActions().get("IncomingPayment");
        initialize();
    }
    
    /** Initialisiert die JPanes einmalig */
    public void initialize() {
        //Die LagerGesamtliste wird einmalig initialisiert
        if (!lager.getIsBuilt()) {
            lager.buildGesamtlisteTabelle();
            lager.setIsBuilt(true);
        }
        
        //Die ArbeitsplatzLagerGesamtliste wird einmalig initialisiert
        if (!apLager.getIsBuilt()) {
            apLager.buildArbeitsplatzLagerElementeTabelle();
            apLager.getArbeitsplatzlager();
            apLager.setIsBuilt(true);
        }
        
        //Der ZahlungsEingang wird einmalig initialisiert
        if (!jPanelZahlungseingang.getIsBuilt()) {
            jPanelZahlungseingang.ladeRechnungen();
            jPanelZahlungseingang.showLegende();
            jPanelZahlungseingang.setIsBuilt(true);
        }
    }
    
    /** Aktualisiert Lageransichten alle 500ms solange der Thread läuft. */
    public void run() {
        while (true) {
            if (isInterrupted()) {
                break;
            }
            
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                this.interrupt();
            }

            //Refresh
            if (lager.getIsActive()) {
                lager.refreshLagerGesamtliste();
            }
            
            if (apLager.getIsActive()) {
                apLager.refreshArbeitsplatzLagerElementeTabelle();
            }
            
            if (etatEinsehen.getIsActive()) {
                etatEinsehen.refreshEtatListe();
            }

            if(jPanelZahlungseingang.getIsActive())
                jPanelZahlungseingang.refreshTabelle();
        }
    }  
}