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
 * Validator.java
 *
 * Created on 26. Februar 2003, 19:36
 */

package net.sourceforge.wisim.controller;

import javax.swing.text.PlainDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.AttributeSet;

/**
 *
 * @author  Kay Patzwald
 */
public class JTextFieldValidation extends PlainDocument{
  private int limit;
  
  /**
   * Konstruktor für das Validationdokument
   * @param int limit: maximale Anzahl der einzugebenen Zeichen
   */
  public JTextFieldValidation(int newLimit){
    super();
    if (limit < 0){
      limit = 0;
    } else {
      limit = newLimit;
    }
  }
  
  /**
   * Funktion überschreibt die Methode insertString von PlaintDocument
   * @param int offset: Position
   * @param String str: der String
   * @param AttributeSet attr: Attributset
   */
  public void insertString(int offset, String str, AttributeSet attr)
  throws BadLocationException {
    if (str == null) return;
    
    if ((getLength() + str.length()) <= limit){
      super.insertString(offset, str, attr);
    }
  }
}
