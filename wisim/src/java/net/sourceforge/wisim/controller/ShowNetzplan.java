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

package net.sourceforge.wisim.controller;
import java.awt.Dimension;
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
 * @version 0.1a
 */
public class ShowNetzplan extends JPanel {

	private ScrollablePicture picture;
	private static Vector npElemente;
	private static NetzplanGrafikGenerator npGrafik;

	public ShowNetzplan() {
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

		npElemente = new Vector();

		/** Elements of the network plan */
		npElemente.add(new NetzplanElement(1, 20, new int[] { 2, 6, 8 }, "Entwurf, Planung"));
		npElemente.add(new NetzplanElement(2, 3, new int[] { 3 }, "Erdaushub Fundamente"));
		npElemente.add(new NetzplanElement(3, 2, new int[] { 4 }, "Ausgieﬂen Fundamente"));
		npElemente.add(new NetzplanElement(4, 5, new int[] { 5 }, "Verschalung Betonsockel"));
		npElemente.add(new NetzplanElement(5, 3, new int[] { 9 }, "Betonierung Betonsockel"));
		npElemente.add(new NetzplanElement(6, 10, new int[] { 7 }, "Bestellung Betonteile"));
		npElemente.add(new NetzplanElement(7, 2, new int[] { 9 }, "Aushub Versorgungsleitung"));
		npElemente.add(new NetzplanElement(8, 5, new int[] { 9 }, "Leitungsverlegung"));
		npElemente.add(new NetzplanElement(9, 7, new int[] { 10 }, "Montage Lagerhalle"));
		npElemente.add(new NetzplanElement(10, 4, new int[] { 0 }, "Installationsarbeiten"));

		/*npElemente.add(new NetzplanElement(1, 3, new int[] { 2,3 }, "Erdaushub Fundamente und so wei"));
		npElemente.add(new NetzplanElement(2, 2, new int[] { 4,5 }, "Ausgieﬂen Fundamente"));
		npElemente.add(new NetzplanElement(3, 5, new int[] { 6 }, "Verschalung Betonsockel"));
		npElemente.add(new NetzplanElement(4, 3, new int[] { 6 }, "Betonierung Betonsockel"));
		npElemente.add(new NetzplanElement(5, 10, new int[] { 6 }, "Bestellung Betonteile"));
		npElemente.add(new NetzplanElement(6, 2, new int[] { 0 }, "Aushub Versorgungsleitung"));*/

		/*npElemente.add(new NetzplanElement(1, 3, new int[] { 2 }, "Erdaushub Fundamente"));
		npElemente.add(new NetzplanElement(2, 2, new int[] { 3, 4 }, "Ausgieﬂen Fundamente"));
		npElemente.add(new NetzplanElement(3, 5, new int[] { 5 }, "Verschalung Betonsockel"));
		npElemente.add(new NetzplanElement(4, 3, new int[] { 5 }, "Betonierung Betonsockel"));
		npElemente.add(new NetzplanElement(5, 10, new int[] { 6 }, "Bestellung Betonteile"));
		npElemente.add(new NetzplanElement(6, 2, new int[] { 0 }, "Aushub Versorgungsleitung"));*/

		npGrafik = new NetzplanGrafikGenerator(npElemente);

		/** Get the image to use. */
		ImageIcon netzplanGrafik = new ImageIcon();
		netzplanGrafik.setImage(npGrafik.getNetzplanGraphic());

		/** Set up the scroll pane. */
		picture = new ScrollablePicture(netzplanGrafik, 30);
		JScrollPane pictureScrollPane = new JScrollPane(picture);
		pictureScrollPane.setPreferredSize(new Dimension(950, 650));

		/** Put it in this panel. */
		add(pictureScrollPane);
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	}

	public static void main(String[] args) {

		JFrame frame = new JFrame("Netzplan Viewer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JComponent newContentPane = new ShowNetzplan();
		newContentPane.setOpaque(true);
		frame.setContentPane(newContentPane);

		frame.pack();
		frame.setVisible(true);
	}
}