/*   ********************************************************************    **
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
 * Created on 02.07.2003
 */
package net.sourceforge.wisim.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

/**
 * @author Benjamin Pasero
 */
public class WiSimSplashscreen extends JFrame {

  private static JProgressBar loadStatusBar;
  private static JLabel statusText;

  private final int splashScreenWidth = 582;
  private final int splashScreenHeight = 476;

  private JPanel splashContainer;
  private WiSimMainController wiSim;

  private Thread splashScreenThread;
  private Thread wiSimThread;

  private boolean introIsBuilt;

  public WiSimSplashscreen(JProgressBar loadStatusBar, JLabel statusText) {

    WiSimSplashscreen.loadStatusBar = loadStatusBar;
    WiSimSplashscreen.statusText = statusText;

    introIsBuilt = false;

    /**
     * Listener for closing the JFrame
     */
    addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent evt) {
        exitForm(evt);
      }
    });

    /**
     * Just show the splashscreen
     */
    setUndecorated(true);

    getContentPane().setLayout(null);

    /**
     * Thread for displaying the splashscreen and progress bar
     */
    splashScreenThread = new Thread() {
      public void run() {
        while (true) {
          if (isInterrupted()) {
            break;
          }

          /**
           * Only build once
           */
          if (!introIsBuilt) {
            splashContainer = new JPanel() {

              /**
               * Display a JPG in the Panel
               */
              @Override
              public void paint(Graphics g) {
                URL imgURL = getClass().getResource("/icons/splash.jpg");
                Toolkit tk = Toolkit.getDefaultToolkit();
                Image img = null;
                try {
                  MediaTracker m = new MediaTracker(this);
                  img = tk.getImage(imgURL);
                  m.addImage(img, 0);
                  m.waitForAll();
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }

                /**
                 * Draw the image on the Panel
                 */
                g.drawImage(img, 0, 0, this);
              }
            };

            splashContainer.setLayout(null);
            getContentPane().add(splashContainer);

            /**
             * Panel holding the status text
             */
            JPanel statusHold = new JPanel();
            statusHold.setBackground(new Color(128, 128, 128));
            getContentPane().add(statusHold, 0);
            statusHold.setBounds(50, 440, 300, 30);

            /**
             * Label for displaying the status text
             */
            WiSimSplashscreen.statusText.setFont(new Font("Dialog", 0, 15));
            WiSimSplashscreen.statusText.setForeground(Color.WHITE);
            statusHold.add(WiSimSplashscreen.statusText);
            WiSimSplashscreen.statusText.setBounds(0, 0, 300, 30);

            /**
             * Label bottom right with Infos
             */
            String info = "Kay Patzwald, Benjamin Pasero" + "\nVersion: 2.0 Milestone 1, 2003" + "\nhttps://github.com/kpatzwald/WiSim" + "\nPublished under terms of GPL";

            JPanel infoHold = new JPanel();
            JTextArea infoText = new JTextArea();
            infoText.setEditable(false);
            infoText.setFocusable(false);
            infoText.setBackground(new Color(128, 128, 128));
            infoText.setForeground(Color.WHITE);
            infoText.setText(info);
            infoHold.add(infoText);
            infoText.setBounds(0, 0, 176, 80);
            infoHold.setBackground(new Color(128, 128, 128));
            getContentPane().add(infoHold, 0);
            infoHold.setBounds(400, 400, 176, 74);

            /**
             * Center the splashscreen
             */
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            int midHeight = (int) screenSize.getHeight() / 2;
            int midWidth = (int) screenSize.getWidth() / 2;

            splashContainer.setBounds(0, 0, splashScreenWidth, splashScreenHeight);
            setBounds(midWidth - splashScreenWidth / 2, midHeight - splashScreenHeight / 2, splashScreenWidth, splashScreenHeight);

            /**
             * Get a JProgressBar for displaying the load status
             */
            splashContainer.add(WiSimSplashscreen.loadStatusBar);
            WiSimSplashscreen.loadStatusBar.setBounds(50, 420, 300, 20);
            WiSimSplashscreen.loadStatusBar.setStringPainted(true);

            introIsBuilt = true;
            setVisible(true);

            /**
             * Set the ProgressBar visible, even if its 0%
             */
            WiSimSplashscreen.loadStatusBar.setValue(1);
            WiSimSplashscreen.loadStatusBar.setValue(0);

            /**
             * Wait 100ms befor loading the wiSimThread
             */
            try {
              sleep(100);
            } catch (InterruptedException e) {
              this.interrupt();
            }

            /**
             * Run the wiSimThread
             */
            wiSimThread.start();

          } else {
            try {
              sleep(1000);
            } catch (InterruptedException e) {
              this.interrupt();
            }
          }
        }
      }
    };

    splashScreenThread.setName("Display SplashScreen Thread");

    /**
     * Thread for loading the wiSimMainController
     */
    wiSimThread = new Thread() {
      @Override
      public void run() {
        while (true) {
          if (isInterrupted()) {
            break;
          }

          /**
           * Instantiate new WiSimMainController
           */
          wiSim = new WiSimMainController();
          wiSim.show();

          /**
           * Hide the splashscreen
           */
          setVisible(false);
          dispose();

          /**
           * Stop the two threads
           */
          splashScreenThread.interrupt();
          wiSimThread.interrupt();
        }
      }
    };
    wiSimThread.setName("Load WiSim Thread");
  }

  /**
   * Quit if the user closes the splashscreen
   */
  private void exitForm(java.awt.event.WindowEvent evt) {
    System.exit(0);
  }

  /**
   * Run this splashscreen
   */
  public void runSplashscreen() {
    splashScreenThread.start();
  }
}
