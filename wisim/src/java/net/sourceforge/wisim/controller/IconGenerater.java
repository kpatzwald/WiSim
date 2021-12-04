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
 * IconGenerater.java
 *
 * Created on 17. März 2003, 10:23
 */
package net.sourceforge.wisim.controller;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

/**
 * Erzeugt eine neue Instanz IconGenerater
 *
 * @author benjamin.pasero
 */
public class IconGenerater {

  private static final Color DARKGREEN = new Color(51, 153, 51);
  private static final Color RED = new Color(255, 0, 0);
  private static final Color ORANGE = new Color(255, 153, 0);
  private static final Color DARKGREENDITHER = new Color(51, 153, 51, 50);
  private static final Color REDDITHER = new Color(255, 0, 0, 50);
  private static final Color ORANGEDITHER = new Color(255, 153, 0, 50);
  private final Image icon;
  private final Graphics g;
  private final int abstand;

  /**
   * Creates a new instance of IconGenerater
   */
  public IconGenerater() {
    icon = new BufferedImage(83, 30, 2);
    g = icon.getGraphics();
    abstand = 12;
  }

  /**
   * Erzeugt ein ImageIcon je nach Parameterangabe.
   *
   * @param type Der Typ gibt die Fabe der Rechtecke im ImageIcon an. 0 steht
   * für grün, 1 für orange und 2 für rot.
   * @param filledRects Steht für die Anzahl der gefüllten Rechtecke.
   * @return ImageIcon Objekt bestehend aus 5 Rechtecken.
   */
  public ImageIcon generateIcon(int type, int filledRects) {
    int i = 0;
    switch (type) {

      //Grünes Icon
      case 0:
        g.setColor(DARKGREEN);
        while (i < filledRects) {
          g.fillRect((abstand * (i + 1)), 11, 10, 10);
          i++;
        }

        i = filledRects + 1;
        while (i <= 5) {
          g.setColor(DARKGREEN);
          g.drawRect((abstand * i), 11, 9, 9);
          g.setColor(DARKGREENDITHER);
          g.fillRect((abstand * i), 11, 9, 9);
          i++;
        }
        break;

      //Oranges Icon   
      case 1:
        g.setColor(ORANGE);
        while (i < filledRects) {
          g.fillRect((abstand * (i + 1)), 11, 10, 10);
          i++;
        }

        i = filledRects + 1;
        while (i <= 5) {
          g.setColor(ORANGE);
          g.drawRect((abstand * i), 11, 9, 9);
          g.setColor(ORANGEDITHER);
          g.fillRect((abstand * i), 11, 9, 9);
          i++;
        }
        break;

      //Rotes Icon   
      case 2:
        g.setColor(RED);
        while (i < filledRects) {
          g.fillRect((abstand * (i + 1)), 11, 10, 10);
          i++;
        }

        i = filledRects + 1;
        while (i <= 5) {
          g.setColor(RED);
          g.drawRect((abstand * i), 11, 9, 9);
          g.setColor(REDDITHER);
          g.fillRect((abstand * i), 11, 9, 9);
          i++;
        }
        break;
    }
    return new ImageIcon(icon);
  }
}
