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
import javax.swing.UIManager;

/**
 * @author Ben
 */
public class WiSimSplashscreen extends JFrame {

	private static JProgressBar loadStatusBar;
	private static JLabel statusText;

	private JPanel splashContainer;
	private WiSimMainController wiSim;

	private Thread splashScreenThread;
	private Thread wiSimThread;

	private boolean introIsBuilt;

	public WiSimSplashscreen(JProgressBar loadStatusBar, JLabel statusText) {

		WiSimSplashscreen.loadStatusBar = loadStatusBar;
		WiSimSplashscreen.statusText = statusText;

		introIsBuilt = false;

		/** Set up the kunststoff PLAF */
		com.incors.plaf.kunststoff.KunststoffLookAndFeel plaf = new com.incors.plaf.kunststoff.KunststoffLookAndFeel();

		try {
			UIManager.setLookAndFeel(plaf);
		} catch (Exception e) {
		}

		/** Listener for closing the JFrame */
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				exitForm(evt);
			}
		});

		/** Just show the splashscreen */
		setUndecorated(true);

		getContentPane().setLayout(null);

		/** Thread for displaying the splashscreen and progress bar */
		splashScreenThread = new Thread() {
			public void run() {
				while (true) {
					if (isInterrupted()) {
						break;
					}

					/** Only build once */
					if (!introIsBuilt) {
						splashContainer = new JPanel() {

							/** Display a JPG in the Panel */
							public void paint(Graphics g) {
								URL imgURL = getClass().getResource("/icons/splash.jpg");
								Toolkit tk = Toolkit.getDefaultToolkit();
								Image img = null;
								try {
									MediaTracker m = new MediaTracker(this);
									img = tk.getImage(imgURL);
									m.addImage(img, 0);
									m.waitForAll();
								} catch (Exception e) {
									e.printStackTrace();
								}

								/** Draw the image on the Panel */
								g.drawImage(img, 0, 0, this);
							}
						};

						splashContainer.setLayout(null);
						getContentPane().add(splashContainer);

						/** Panel holding the status text */
						JPanel statusHold = new JPanel();
						statusHold.setBackground(new Color(128, 128, 128));
						getContentPane().add(statusHold, 0);
						statusHold.setBounds(100, 570, 300, 30);

						/** Label for displaying the status text */
						WiSimSplashscreen.statusText.setFont(new Font("Dialog", 0, 15));
						WiSimSplashscreen.statusText.setForeground(Color.WHITE);
						statusHold.add(WiSimSplashscreen.statusText);
						WiSimSplashscreen.statusText.setBounds(0, 0, 300, 30);

						/** Center the splashscreen */
						Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

						int midHeight = (int) screenSize.getHeight() / 2;
						int midWidth = (int) screenSize.getWidth() / 2;

						splashContainer.setBounds(0, 0, 752, 615);
						setBounds(midWidth - 375, midHeight - 312, 752, 615);

						/** Get a JProgressBar for displaying the load status */
						splashContainer.add(WiSimSplashscreen.loadStatusBar);
						WiSimSplashscreen.loadStatusBar.setBounds(100, 550, 300, 20);
						WiSimSplashscreen.loadStatusBar.setStringPainted(true);

						introIsBuilt = true;
						setVisible(true);

						/** Wait 100ms befor loading the wiSimThread */
						try {
							sleep(100);
						} catch (InterruptedException e) {
							this.interrupt();
						}

						/** Run the wiSimThread */
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

		/** Thread for loading the wiSimMainController */
		wiSimThread = new Thread() {
			public void run() {
				while (true) {
					if (isInterrupted()) {
						break;
					}

					/** Instantiate new WiSimMainController */
					wiSim = new WiSimMainController();
					wiSim.show();

					/** Hide the splashscreen */
					setVisible(false);

					/** Stop the two threads */
					splashScreenThread.interrupt();
					wiSimThread.interrupt();
				}
			}
		};
	}

	/** Quit if the user closes the splashscreen */
	private void exitForm(java.awt.event.WindowEvent evt) {
		System.exit(0);
	}

	/** Run this splashscreen */
	public void runSplashscreen() {
		splashScreenThread.start();
	}
}