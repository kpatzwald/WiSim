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

/*
 * NetworkplanElementSwingGenerator.java
 */
package net.sourceforge.wisim.networkplan;

import javax.swing.JPanel;

/**
 * TODO Kommentar Klasse NetworkplanElementSwingGenerator
 * @author benjamin.pasero
 */
public class NetzplanElementSwingGenerator {

	private javax.swing.JLabel jLabelDuration;
	private javax.swing.JLabel jLabelFAZ;
	private javax.swing.JLabel jLabelFEZ;
	private javax.swing.JLabel jLabelFP;
	private javax.swing.JLabel jLabelGP;
	private javax.swing.JLabel jLabelLine1;
	private javax.swing.JLabel jLabelNumber;
	private javax.swing.JLabel jLabelSAZ;
	private javax.swing.JLabel jLabelSEZ;
	private javax.swing.JSeparator jSeparatorBetweenGPandFP;
	private javax.swing.JSeparator jSeparatorCutMiddle;
	private javax.swing.JSeparator jSeparatorFromNumToDur;
	private javax.swing.JPanel npElementContainer;
	private javax.swing.JPanel npElementRect;

	private static final int width = 350;

	public NetzplanElementSwingGenerator() {
		npElementContainer = new javax.swing.JPanel();
		npElementRect = new javax.swing.JPanel();
		jSeparatorCutMiddle = new javax.swing.JSeparator();
		jSeparatorFromNumToDur = new javax.swing.JSeparator();
		jSeparatorBetweenGPandFP = new javax.swing.JSeparator();
		jLabelGP = new javax.swing.JLabel();
		jLabelFP = new javax.swing.JLabel();
		jLabelDuration = new javax.swing.JLabel();
		jLabelNumber = new javax.swing.JLabel();
		jLabelLine1 = new javax.swing.JLabel();
		jLabelFAZ = new javax.swing.JLabel();
		jLabelFEZ = new javax.swing.JLabel();
		jLabelSAZ = new javax.swing.JLabel();
		jLabelSEZ = new javax.swing.JLabel();
	}

	public JPanel generateNetzplanelement(NetzplanElement np) {

		/** Container that holds all swing-elements */
		npElementContainer.setLayout(null);

		npElementContainer.setBackground(new java.awt.Color(255, 255, 255));
		npElementContainer.setPreferredSize(new java.awt.Dimension(350, 210));

		/** Rectangle of the network plan element */
		npElementRect.setLayout(null);

		npElementRect.setBackground(new java.awt.Color(255, 255, 255));
		npElementRect.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
		npElementRect.setPreferredSize(new java.awt.Dimension(300, 150));

		/** Horizontal line cutting the rectangle in the middle*/
		jSeparatorCutMiddle.setForeground(new java.awt.Color(0, 0, 0));
		npElementRect.add(jSeparatorCutMiddle);
		jSeparatorCutMiddle.setBounds(1, 70, 298, 2);

		/** Vertical line from Number to Duration */
		jSeparatorFromNumToDur.setForeground(new java.awt.Color(0, 0, 0));
		jSeparatorFromNumToDur.setOrientation(javax.swing.SwingConstants.VERTICAL);
		npElementRect.add(jSeparatorFromNumToDur);
		jSeparatorFromNumToDur.setBounds(80, 1, 2, 148);

		/** Vertical line seperating GP and FP */
		jSeparatorBetweenGPandFP.setForeground(new java.awt.Color(0, 0, 0));
		jSeparatorBetweenGPandFP.setOrientation(javax.swing.SwingConstants.VERTICAL);
		npElementRect.add(jSeparatorBetweenGPandFP);
		jSeparatorBetweenGPandFP.setBounds(190, 71, 2, 78);

		/** Writing GP */
		jLabelGP.setFont(new java.awt.Font("Dialog", 1, 24));
		jLabelGP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabelGP.setText(String.valueOf(np.getGp()));
		npElementRect.add(jLabelGP);
		jLabelGP.setBounds(90, 80, 90, 60);

		/** Writing FP */
		jLabelFP.setFont(new java.awt.Font("Dialog", 1, 24));
		jLabelFP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabelFP.setText(String.valueOf(np.getFp()));
		npElementRect.add(jLabelFP);
		jLabelFP.setBounds(200, 80, 90, 60);

		/** Writing Duration */
		jLabelDuration.setFont(new java.awt.Font("Dialog", 1, 24));
		jLabelDuration.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabelDuration.setText(String.valueOf(np.getDauer()));
		npElementRect.add(jLabelDuration);
		jLabelDuration.setBounds(10, 80, 60, 60);

		/** Writing Number */
		jLabelNumber.setFont(new java.awt.Font("Dialog", 1, 24));
		jLabelNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabelNumber.setText(String.valueOf(np.getNummer()));
		npElementRect.add(jLabelNumber);
		jLabelNumber.setBounds(10, 10, 60, 50);

		/** Writing first line of description */
		jLabelLine1.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabelLine1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabelLine1.setText(np.getBezeichnung());
		npElementRect.add(jLabelLine1);
		jLabelLine1.setBounds(90, 30, 200, 19);

		npElementContainer.add(npElementRect);
		npElementRect.setBounds(30, 30, 300, 150);

		/** Writing FAZ */
		jLabelFAZ.setFont(new java.awt.Font("Dialog", 1, 16));
		jLabelFAZ.setText(String.valueOf(np.getFaz()));
		npElementContainer.add(jLabelFAZ);
		jLabelFAZ.setBounds(30, 6, 40, 20);

		/** Writing FEZ */
		jLabelFEZ.setFont(new java.awt.Font("Dialog", 1, 16));
		jLabelFEZ.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabelFEZ.setText(String.valueOf(np.getFez()));
		npElementContainer.add(jLabelFEZ);
		jLabelFEZ.setBounds(290, 6, 40, 20);

		/** Writing SAZ */
		jLabelSAZ.setFont(new java.awt.Font("Dialog", 1, 16));
		jLabelSAZ.setText(String.valueOf(np.getSaz()));
		npElementContainer.add(jLabelSAZ);
		jLabelSAZ.setBounds(30, 182, 40, 20);

		/** Writing SEZ */
		jLabelSEZ.setFont(new java.awt.Font("Dialog", 1, 16));
		jLabelSEZ.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabelSEZ.setText(String.valueOf(np.getSez()));
		npElementContainer.add(jLabelSEZ);
		jLabelSEZ.setBounds(290, 182, 40, 20);

		npElementContainer.setBounds(60, 290, 360, 210);

		return npElementContainer;
	}

	/**
		 * @return Width of the network plan element
		 */
	public int getWidth() {
		return width;
	}
}
