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
  * Created on 09.03.2003
 *
 */
package net.sourceforge.wisim.simulation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import net.sourceforge.wisim.controller.WiSimMainController;
import net.sourceforge.wisim.dao.WiSimDAO;
import net.sourceforge.wisim.dao.WiSimDAOException;
import net.sourceforge.wisim.dao.WiSimDAOWriteException;
import net.sourceforge.wisim.model.WarehouseLocation;
import net.sourceforge.wisim.model.WiSimLogger;
import net.sourceforge.wisim.model.WorkPlace;
import net.sourceforge.wisim.model.WorkPlaceStore;

/**
 * Stellt Funktionen f??r die Produktion zur Verf??gung
 *
 * @author Kay Patzwald
 */
public class ProductionController {

  private static final int HUB = 26;
  private final WiSimDAO dao;
  private final WiSimLogger wiSimLogger;

  /**
   * Creates a new instance of ProductionController
   *
   * @param wiSimMainController WiSimMainController
   */
  public ProductionController(WiSimMainController wiSimMainController) {
    dao = wiSimMainController.getDAO();
    wiSimLogger = wiSimMainController.getWiSimLogger();
  }

  /**
   * Simuliert die Lieferung von Einzelteilen
   *
   * @param etNr Einzelteilnummer
   * @param menge Menge
   * @return boolean
   */
  public synchronized boolean einzelteilLieferung(int etNr, int menge) {
    boolean status = false;
    try {
      int dbMenge = dao.setEinzelteilLagerBestand(etNr, menge);
      notifyAll();
      if (dbMenge == menge) {
        status = true;
      }
    } catch (WiSimDAOWriteException e) {
      wiSimLogger.log("ProductionController.einzelteilLieferung()", e);
    }
    return status;
  }

  /**
   * Simuliert den Verbrauch von Einzelteilen
   *
   * @param thread Der aktuelle Thread
   * @param apLager Arbeitsplatzlager
   * @param time Aktuelle Zeit
   * @param anzahl Anzahl der verbrauchten Einzelteile
   */
  public synchronized void einzelteilVerbrauch(
          WorkPlaceStore apLager,
          int time,
          int anzahl,
          ProductionSimulationThread thread) {
    try {
      if (apLager.getBestand() >= (apLager.getBenoetigt() * anzahl)) {
        dao.setEinzelteilArbeitsplatzBestand(
                apLager.getArbeitsplatzNr(),
                apLager.getEinzelteilNr(),
                (apLager.getBenoetigt() * anzahl * -1),
                "Einzelteil");
      } // Bestand im Arbeitslager reicht nicht aus
      else {
        Collection<WarehouseLocation> lagerplaetze
                = dao.getLagerplaetze(apLager.getEinzelteilNr());

        // Ich gehe in der folgenden Implementation davon aus, 
        // dass es in dieser Version
        // nur einen Lagerplatz pro Einzelteil gibt.
        Iterator it = lagerplaetze.iterator();
        WarehouseLocation lp = new WarehouseLocation();
        lp = (WarehouseLocation) it.next();
        int realMenge = 0;

        while ((realMenge = dao
                .setEinzelteilLagerBestand(
                        apLager.getEinzelteilNr(),
                        ((apLager.getMaxBestand() - apLager.getBestand())
                        * - 1)))
                >= 0) {
          try {
            wait();
          } catch (InterruptedException e) {
            wiSimLogger.log(
                    Level.FINE,
                    "ProductionController.einzelteilVerbrauch()",
                    e,
                    false,
                    false);
            thread.interrupt();
            return;
          }
        }
        // Sleep simuliert die Dauer (10 Zeiteinheiten) des Einzelteile-Holens aus dem Lager

        try {
          Thread.sleep(time * 10);
        } catch (InterruptedException e) {
          wiSimLogger.log(
                  Level.FINE,
                  "ProductionController.einzelteilVerbrauch()",
                  e,
                  false,
                  false);
          thread.interrupt();
          return;
        }

        if (lp.getBestand()
                >= (apLager.getMaxBestand() - apLager.getBestand())) {
          dao.setEinzelteilArbeitsplatzBestand(
                  apLager.getArbeitsplatzNr(),
                  apLager.getEinzelteilNr(),
                  (apLager.getMaxBestand() - apLager.getBestand()),
                  "Einzelteil");
        } else {
          dao.setEinzelteilArbeitsplatzBestand(
                  apLager.getArbeitsplatzNr(),
                  apLager.getEinzelteilNr(),
                  (realMenge * -1),
                  "Einzelteil");
        }
        dao.setEinzelteilArbeitsplatzBestand(
                apLager.getArbeitsplatzNr(),
                apLager.getEinzelteilNr(),
                (apLager.getBenoetigt() * anzahl * -1),
                "Einzelteil");

        notifyAll();
      }
    } catch (WiSimDAOException | WiSimDAOWriteException e) {
      wiSimLogger.log("ProductionController.einzelteilVerbrauch()", e);
    }
  }

  /**
   * Holt Hubs von den Vorg??nger-Arbeitspl??tzen
   *
   * @param thread Der aktuelle Thread
   * @param ap WorkPlace
   */
  public synchronized void holeHubs(
          WorkPlace ap,
          ProductionSimulationThread thread) {
    ArrayList<WorkPlaceStore> apLagerVorgaenger = new ArrayList<>();
    try {
      for (int i = 0; i < ap.getVorgaenger().length; i++) {
        apLagerVorgaenger.add(
                dao.getWorkPlaceStore(
                        ap.getVorgaenger()[i],
                        26,
                        "Ausgang"));
      }
    } catch (WiSimDAOException e1) {
      wiSimLogger.log("ProductionController.holeHubs()", e1);
    }

    boolean isLeer = false;
    Iterator it = apLagerVorgaenger.iterator();
    while (it.hasNext()) {
      WorkPlaceStore apLager = (WorkPlaceStore) it.next();
      if (apLager.getBestand() == 0) {
        isLeer = true;
      }
    }

    while (isLeer) {
      try {
        // Vorg??nger noch nicht fertig
        wait();
      } catch (InterruptedException e) {
        wiSimLogger.log(
                Level.FINE,
                "ProductionController.holeHubs()",
                e,
                false,
                false);
        thread.interrupt();
        return;
      }
      apLagerVorgaenger = new ArrayList<>();
      try {
        for (int i = 0; i < ap.getVorgaenger().length; i++) {
          apLagerVorgaenger.add(
                  dao.getWorkPlaceStore(
                          ap.getVorgaenger()[i],
                          26,
                          "Ausgang"));
        }
      } catch (WiSimDAOException e1) {
        wiSimLogger.log("ProductionController.holeHubs()", e1);
      }

      isLeer = false;
      it = apLagerVorgaenger.iterator();
      while (it.hasNext()) {
        WorkPlaceStore apLager = (WorkPlaceStore) it.next();
        if (apLager.getBestand() == 0) {
          isLeer = true;
        }
      }
    }

    // Vorg??nger fertig
    it = null;
    it = apLagerVorgaenger.iterator();
    WorkPlaceStore apl = (WorkPlaceStore) it.next();
    int minBestand = apl.getBestand();

    while (it.hasNext()) {
      apl = (WorkPlaceStore) it.next();
      if (apl.getBestand() < minBestand) {
        minBestand = apl.getBestand();
      }
    }
    try {
      if (minBestand < 10) {
        dao.setEinzelteilArbeitsplatzBestand(
                ap.getNr(),
                HUB,
                minBestand,
                "Eingang");
        for (int i = 0; i < ap.getVorgaenger().length; i++) {
          dao.setEinzelteilArbeitsplatzBestand(
                  ap.getVorgaenger()[i],
                  HUB,
                  (minBestand * -1),
                  "Ausgang");
        }
        notifyAll();
      } else {
        dao.setEinzelteilArbeitsplatzBestand(
                ap.getNr(),
                HUB,
                10,
                "Eingang");
        for (int i = 0; i < ap.getVorgaenger().length; i++) {
          dao.setEinzelteilArbeitsplatzBestand(
                  ap.getVorgaenger()[i],
                  HUB,
                  -10,
                  "Ausgang");
        }
        notifyAll();
      }
    } catch (WiSimDAOWriteException | WiSimDAOException e) {
      wiSimLogger.log("ProductionController.holeHubs()", e);
    }
  }

  /**
   * Testet, ob das Ausgangslager leer ist.
   *
   * @param thread Der aktuelle Thread
   * @param ap WorkPlace
   */
  public synchronized void hubAbgeholt(
          WorkPlace ap,
          ProductionSimulationThread thread) {
    WorkPlaceStore apLager = null;
    try {
      apLager = dao.getWorkPlaceStore(ap.getNr(), HUB, "Ausgang");
    } catch (WiSimDAOException e1) {
      wiSimLogger.log("ProductionController.hubAbgeholt()", e1);
    }
    while (apLager.getBestand() == apLager.getMaxBestand()) {
      // Hub noch nicht abgeholt
      try {
        if (ap.getVorgaenger()[0] != 0) {
          this.fuelleEingangslagerAuf(ap);
        }
        wait();
      } catch (InterruptedException e) {
        wiSimLogger.log(
                Level.FINE,
                "ProductionController.hubAbgeholt()",
                e,
                false,
                false);
        thread.interrupt();
        return;
      }
      try {
        apLager = dao.getWorkPlaceStore(ap.getNr(), HUB, "Ausgang");
      } catch (WiSimDAOException e2) {
        wiSimLogger.log("ProductionController.hubAbgeholt()", e2);
      }
    }
    // Hub wurde abgeholt
  }

  /**
   * Simuliert, wie fertige Teilhubs in den Ausgang gelegt werden
   *
   * @param thread Der aktuelle Thread
   * @param ap WorkPlace
   * @param time Die aktuelle Zeit
   */
  public synchronized void hubFertig(
          WorkPlace ap,
          int time,
          ProductionSimulationThread thread) {
    try {

      WorkPlaceStore aplAusgang
              = dao.getWorkPlaceStore(ap.getNr(), HUB, "Ausgang");
      WorkPlaceStore aplEingang
              = dao.getWorkPlaceStore(ap.getNr(), HUB, "Eingang");

      int kapazitaetAusgang
              = aplAusgang.getMaxBestand() - aplAusgang.getBestand();
      int bestandEingang = aplEingang.getBestand();
      int anzArbeiter = ap.getAnzahlArbeiter();
      int anzahlHubs = anzArbeiter;
      if (kapazitaetAusgang < anzahlHubs) {
        anzahlHubs = kapazitaetAusgang;
      }
      int anzahlHubsOhneVorgaenger = anzahlHubs;
      if (bestandEingang < anzahlHubs) {
        anzahlHubs = bestandEingang;
      }

      if (ap.getVorgaenger()[0] != 0) {
        Collection <WorkPlaceStore> arbeitsplatzLager = null;
        try {
          arbeitsplatzLager
                  = dao.getArbeitsplatzLager(
                          ap.getNr(),
                          "Einzelteil");
        } catch (WiSimDAOException e1) {
          wiSimLogger.log("ProductionController.hubFertig()", e1);
        }
        Iterator it = arbeitsplatzLager.iterator();
        while (it.hasNext()) {
          this.einzelteilVerbrauch(
                  (WorkPlaceStore) it.next(),
                  time,
                  anzahlHubs,
                  thread);
        }

        dao.setEinzelteilArbeitsplatzBestand(
                ap.getNr(),
                HUB,
                (anzahlHubs * -1),
                "Eingang");
        dao.setEinzelteilArbeitsplatzBestand(
                ap.getNr(),
                HUB,
                anzahlHubs,
                "Ausgang");
        notifyAll();
      } else {
        Collection <WorkPlaceStore> arbeitsplatzLager = null;
        try {
          arbeitsplatzLager
                  = dao.getArbeitsplatzLager(
                          ap.getNr(),
                          "Einzelteil");
        } catch (WiSimDAOException e1) {
          wiSimLogger.log("ProductionController.hubFertig()", e1);
        }
        Iterator it = arbeitsplatzLager.iterator();
        while (it.hasNext()) {
          this.einzelteilVerbrauch(
                  (WorkPlaceStore) it.next(),
                  time,
                  anzahlHubsOhneVorgaenger,
                  thread);
        }

        dao.setEinzelteilArbeitsplatzBestand(
                ap.getNr(),
                HUB,
                anzahlHubsOhneVorgaenger,
                "Ausgang");
        notifyAll();
      }
    } catch (WiSimDAOWriteException | WiSimDAOException e) {
      wiSimLogger.log("ProductionController.hubFertig()", e);
    }
  }

  /**
   * Simuliert wie fertige Hubs ins Lager gelegt werden
   *
   * @param ap WorkPlace
   */
  public synchronized void hubKomplett(WorkPlace ap) {
    try {
      try {
        WorkPlaceStore apl
                = dao.getWorkPlaceStore(ap.getNr(), HUB, "Ausgang");

        boolean status
                = dao.setArtikelLagerBestand(1, apl.getBestand());
        if (status) {
          dao.setEinzelteilArbeitsplatzBestand(
                  ap.getNr(),
                  HUB,
                  (apl.getBestand() * -1),
                  "Ausgang");
        }
        notifyAll();
      } catch (WiSimDAOException e) {
        wiSimLogger.log("ProductionController.hubKomplett()", e);
      }
    } catch (WiSimDAOWriteException e) {
      wiSimLogger.log("ProductionController.hubKomplett()", e);
    }
  }

  /**
   * F??llt das Eingangslager auf, wenn das Ausgangslager voll ist.
   *
   * @param ap WorkPlace
   */
  public synchronized void fuelleEingangslagerAuf(
          WorkPlace ap) {
    try {
      WorkPlaceStore eingangsLager
              = dao.getWorkPlaceStore(ap.getNr(), HUB, "Eingang");
      if (eingangsLager.getBestand() < eingangsLager.getMaxBestand()) {
        ArrayList<WorkPlaceStore> apLagerVorgaenger = new ArrayList<>();
        for (int i = 0; i < ap.getVorgaenger().length; i++) {
          apLagerVorgaenger.add(
                  dao.getWorkPlaceStore(
                          ap.getVorgaenger()[i],
                          HUB,
                          "Ausgang"));
        }
        boolean isLeer = false;
        Iterator it = apLagerVorgaenger.iterator();
        while (it.hasNext()) {
          WorkPlaceStore apLager = (WorkPlaceStore) it.next();
          if (apLager.getBestand() == 0) {
            isLeer = true;
          }
        }
        if (!isLeer) {
          it = null;
          it = apLagerVorgaenger.iterator();
          WorkPlaceStore apl = (WorkPlaceStore) it.next();
          int minBestand = apl.getBestand();

          while (it.hasNext()) {
            apl = (WorkPlaceStore) it.next();
            if (apl.getBestand() < minBestand) {
              minBestand = apl.getBestand();
            }
          }
          int eingangsBestand = eingangsLager.getBestand();
          int maxEingangsBestand = eingangsLager.getMaxBestand();
          if ((maxEingangsBestand - eingangsBestand) < minBestand) {
            minBestand = maxEingangsBestand - eingangsBestand;
          }
          try {
            if (minBestand < 10) {
              dao.setEinzelteilArbeitsplatzBestand(
                      ap.getNr(),
                      HUB,
                      minBestand,
                      "Eingang");
              for (int i = 0; i < ap.getVorgaenger().length; i++) {
                dao.setEinzelteilArbeitsplatzBestand(
                        ap.getVorgaenger()[i],
                        HUB,
                        (minBestand * -1),
                        "Ausgang");
              }
              notifyAll();
            } else {
              dao.setEinzelteilArbeitsplatzBestand(
                      ap.getNr(),
                      HUB,
                      10,
                      "Eingang");
              for (int i = 0; i < ap.getVorgaenger().length; i++) {
                dao.setEinzelteilArbeitsplatzBestand(
                        ap.getVorgaenger()[i],
                        HUB,
                        -10,
                        "Ausgang");
              }
              notifyAll();
            }
          } catch (WiSimDAOWriteException e) {
            wiSimLogger.log(
                    "ProductionController.fuelleEingangslagerAuf()",
                    e);
          }
        }
      }
    } catch (WiSimDAOException e) {
      wiSimLogger.log(
              "ProductionController.fuelleEingangslagerAuf()",
              e);
    }
  }
}
