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
 * JPanelHilfe.java
 *
 * Created on 10. Februar 2003, 20:26
 */

package net.sourceforge.wisim.controller;

import javax.swing.JOptionPane;

/**
 *
 * @author  Kay Patzwald
 */
public class JPanelHelp extends javax.swing.JPanel {

  /** Creates new form JPanelHilfe */
    public JPanelHelp() {
    initComponents();
    modGui();
  }


  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
    private void initComponents() {//GEN-BEGIN:initComponents
        jOptionPaneVerInfo = new javax.swing.JOptionPane();
        jScrollPaneHilfe = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabelHilfe = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jOptionPaneVerInfo.setMessageType(javax.swing.JOptionPane.INFORMATION_MESSAGE);

        setLayout(null);

        jTextArea1.setEditable(false);
        jTextArea1.setLineWrap(true);
        jTextArea1.setText("Welcome to WiSim, a business game.\n\n\nVisit us at\n     http://wisim.sourceforge.net.\n\t\nVisit our forum for bugreports, feature requests, development or general dicussion.\n\nThe following topics were implemented in version 1.0: \n    - Single-User-Application \n    - simulation of production \n    - simulation of purchase \n    - simulation of sale \n    - analysis of the production / purchase / sale \n    - simulation of the store house \n    - customer and supplier administration\n\nIn Deutsch:\nDieses Programm ist ein Unternehmensplanspiel.\nFolgende Funktionen sind implementiert:\n\n    - Kundenverwaltung (Anlegen, Editieren, L\u00f6schen, \u00dcbersicht)\n    - Lieferantenverwaltung (Anlegen, Editieren, L\u00f6schen, \u00dcbersicht)\n    - Auftragsverwaltung (Anlegen, \u00dcbersicht)\n    - WorkPlace- und Lager\u00fcbersicht mit Statusbalken\n    - Zahlungseingang verwalten\n    - Simulation der Produktion mit Auswertung\n\n\nCopyright notice                                                       \t\t\t\n\n(c) 2003 WiSim Development Team\nhttp://wisim.sourceforge.net/   \t\t\t                       \nAll rights reserved     \n                                  \nThis script is part of the WiSim Business Game project. The WiSim    \nproject is free software; you can redistribute it and/or modify        \nit under the terms of the GNU General Public License as published by   \nthe Free Software Foundation; either version 2 of the License, or      \n(at your option) any later version.                                    \n                                                                       \nThe GNU General Public License can be found at                         \nhttp://www.gnu.org/copyleft/gpl.html.                                  \nA copy is found in the textfile GPL.txt and important notices to the  \nlicense from the team is found in the textfile LICENSE.txt distributed \nin these package.                                                      \n\nSTAFF:\n------\n\n- Benjamin Pasero - Administrator and Developer\n- Kay Patzwald - Administrator and Developer\n");
        jScrollPaneHilfe.setViewportView(jTextArea1);

        add(jScrollPaneHilfe);
        jScrollPaneHilfe.setBounds(20, 40, 750, 851);

        jLabelHilfe.setFont(new java.awt.Font("Dialog", 1, 24));
        jLabelHilfe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelHilfe.setText("Info");
        add(jLabelHilfe);
        jLabelHilfe.setBounds(370, 10, 70, 20);

        jButton1.setText("Version");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        add(jButton1);
        jButton1.setBounds(370, 400, 81, 26);

    }//GEN-END:initComponents

    /* Workaround for a possible bug in the Netbeans GUI-Editor*/
    private void modGui()
    {
        jScrollPaneHilfe.setBounds(20, 40, 750, 355);
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JOptionPane.showMessageDialog(null, "Ver. 1.0");
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabelHilfe;
    private javax.swing.JOptionPane jOptionPaneVerInfo;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JScrollPane jScrollPaneHilfe;
    // End of variables declaration//GEN-END:variables

}