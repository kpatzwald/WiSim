/*
 * UpdateLagerThread.java
 *
 * Created on 28. März 2003, 18:51
 */

package net.sourceforge.wisim.controller;

/** Solange dieser Thread läuft aktualisiert er die Lagerplatz-Listen. Dazu gehört
 * das JPane Lager und Arbeitsplatz. Es wird außerdem die Einzelteilauftrags-Übersicht
 * aktualisiert.
 * @author benjamin.pasero
 */
public class UpdateLagerThread extends Thread {

    private boolean jPanelZahlungseingangBuilt;
    private WiSimMainController wiSimMainController;
    
    //JPanes
    private JPanelLager lager;
    private JPanelArbeitsplatzlager apLager;
    private JPanelEtatEinsehen etatEinsehen;
    private JPanelZahlungseingang jPanelZahlungseingang;
    
    /** Creates a new instance of UpdateLagerThread
     * @param wiSimMainController wiSimMainController
     */
    public UpdateLagerThread(WiSimMainController wiSimMainController) {
    	super("UpdateLagerThread");
        this.wiSimMainController = wiSimMainController;
        lager = (JPanelLager) wiSimMainController.getActions().get("Lager");
        apLager = (JPanelArbeitsplatzlager) wiSimMainController.getActions().get("Arbeitsplatzlager");
        etatEinsehen = (JPanelEtatEinsehen) wiSimMainController.getActions().get("EtatEinsehen");
        jPanelZahlungseingang = (JPanelZahlungseingang) wiSimMainController.getActions().get("Zahlungseingang");
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