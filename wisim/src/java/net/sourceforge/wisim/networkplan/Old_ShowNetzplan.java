/*   ********************************************************************   **
**   Copyright notice                                                       **
**                                                                          **
**   (c) 2003 WiSim Development Team					                    **
**   http://wisim.sourceforge.net/   			                            **
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

package net.sourceforge.wisim.networkplan;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Builds a networkplan and displays it
 * @author Benjamin Pasero
 * @version 0.2a
 */
public class Old_ShowNetzplan extends JPanel {

	private Old_ScrollablePicture picture;
	private Old_NetzplanGrafikGenerator npGrafik;
	private JScrollPane pictureScrollPane;
	private Component pictureScrollPaneComponent;

	public Old_ShowNetzplan() {
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

		Vector npElemente = getNetworkPlanElements();

		npGrafik = new Old_NetzplanGrafikGenerator(npElemente);

		/** Get the image to use. */
		ImageIcon netzplanGrafik = new ImageIcon();
		netzplanGrafik.setImage(npGrafik.getNetzplanGraphic());

		/** Set up the scroll pane. */
		picture = new Old_ScrollablePicture(netzplanGrafik, 30);
		picture.setSize(800, 600);

		pictureScrollPane = new JScrollPane(picture);

		picture.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jScrollPane1MouseClicked(evt);
			}
		});

		picture.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			public void mouseMoved(java.awt.event.MouseEvent evt) {
				//jScrollPane1MouseMoved(evt);
				jScrollPane1MouseClicked(evt);
			}
		});

		pictureScrollPane.setPreferredSize(new Dimension(950, 650));

		/** Put it in this panel. */
		pictureScrollPaneComponent = add(pictureScrollPane);
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	}

	public static void main(String[] args) {

		JFrame frame = new JFrame("Netzplan Viewer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JComponent newContentPane = new Old_ShowNetzplan();
		newContentPane.setOpaque(true);
		frame.setContentPane(newContentPane);

		frame.pack();
		frame.setVisible(true);
	}

	private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {
		Point pos = evt.getPoint();
		double x = pos.getX();
		double y = pos.getY();

		/** Get the position that was clicked */
		int xPos = (int) x / 400;
		int yPos = (int) y / 160;

		int position[][] = npGrafik.getPosition();
		int selected = position[xPos][yPos];

		paintSelected(selected);

	}

	/**
	 * Repaints the networkplan and sets the selected networkplan element
	 * @param Number of the selected network plan element
	 */
	public void paintSelected(int selected) {

		Vector npElemente = getNetworkPlanElements();
		Iterator npElementeIt = npElemente.iterator();

		/** Set the selected network plan element */
		while (npElementeIt.hasNext()) {
//			NetworkplanElement npElem = (NetworkplanElement) npElementeIt.next();
//
//			if (npElem.getNummer() == selected) {
//				npElem.setSelected(true);
//			} else {
//				npElem.setSelected(false);
//			}
		}

		npGrafik = new Old_NetzplanGrafikGenerator(npElemente);

		/** Get the image to use. */
		ImageIcon netzplanGrafik = new ImageIcon();
		netzplanGrafik.setImage(npGrafik.getNetzplanGraphic());

		/** Set up the scroll pane. */
		picture = new Old_ScrollablePicture(netzplanGrafik, 30);

		picture.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jScrollPane1MouseClicked(evt);
			}
		});

		picture.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			public void mouseMoved(java.awt.event.MouseEvent evt) {
				jScrollPane1MouseClicked(evt);
			}
		});

		/** Save the positions of the scrollbars */
		int vScrollbarPos = pictureScrollPane.getVerticalScrollBar().getValue();
		int hScrollbarPos = pictureScrollPane.getHorizontalScrollBar().getValue();

		pictureScrollPane = new JScrollPane(picture);

		/** Restore the positions of the scrollbars */
		pictureScrollPane.getVerticalScrollBar().setValues(vScrollbarPos, 0, 0, 0);
		pictureScrollPane.getHorizontalScrollBar().setValues(hScrollbarPos, 0, 0, 0);

		/** Remove it and put it in this panel again. */
		remove(pictureScrollPaneComponent);
		pictureScrollPaneComponent = add(pictureScrollPane);

		/** Update GUI */
		validate();
		repaint();
	}

	/**
	 * Gets a filled Vector with the network plan elements
	 * @return Vector with network plan elements
	 */
	public Vector getNetworkPlanElements() {

		Vector filled = new Vector();

		/** Elements of the network plan */
//		filled.add(new NetworkplanElement(1, 20, new int[] { 2, 6, 8 }, "Entwicklung, Planung und Test der einzelnen"));
//		filled.add(new NetworkplanElement(2, 3, new int[] { 3 }, "Erdaushub Fundamente"));
//		filled.add(new NetworkplanElement(3, 2, new int[] { 4 }, "Ausgieﬂen Fundamente"));
//		filled.add(new NetworkplanElement(4, 5, new int[] { 5 }, "Verschalung Betonsockel"));
//		filled.add(new NetworkplanElement(5, 3, new int[] { 9 }, "Betonierung Betonsockel"));
//		filled.add(new NetworkplanElement(6, 10, new int[] { 7 }, "Bestellung und Auslieferung Betonteile"));
//		filled.add(new NetworkplanElement(7, 2, new int[] { 9 }, "Aushub Ver- und Entsorgungsleitung"));
//		filled.add(new NetworkplanElement(8, 5, new int[] { 9 }, "Leitungsverlegung"));
//		filled.add(new NetworkplanElement(9, 7, new int[] { 10 }, "Montage Lagerhalle"));
//		filled.add(new NetworkplanElement(10, 4, new int[] { 0 }, "Installationsarbeiten"));

		//		filled.add(new NetworkplanElement(1, 3, new int[] { 2,3 }, "Erdaushub Fundamente"));
		//		filled.add(new NetworkplanElement(2, 2, new int[] { 4,5 }, "Ausgieﬂen Fundamente"));
		//		filled.add(new NetworkplanElement(3, 5, new int[] { 6 }, "Verschalung Betonsockel"));
		//		filled.add(new NetworkplanElement(4, 3, new int[] { 6 }, "Betonierung Betonsockel"));
		//		filled.add(new NetworkplanElement(5, 10, new int[] { 6 }, "Bestellung Betonteile"));
		//		filled.add(new NetworkplanElement(6, 2, new int[] { 0 }, "Aushub Versorgungsleitung"));

		//		filled.add(new NetworkplanElement(1, 3, new int[] { 2 }, "Erdaushub Fundamente"));
		//		filled.add(new NetworkplanElement(2, 2, new int[] { 3, 4 }, "Ausgieﬂen Fundamente"));
		//		filled.add(new NetworkplanElement(3, 5, new int[] { 5 }, "Verschalung Betonsockel"));
		//		filled.add(new NetworkplanElement(4, 3, new int[] { 5 }, "Betonierung Betonsockel"));
		//		filled.add(new NetworkplanElement(5, 10, new int[] { 6 }, "Bestellung Betonteile"));
		//		filled.add(new NetworkplanElement(6, 2, new int[] { 0 }, "Aushub Versorgungsleitung"));

		return filled;
	}
}