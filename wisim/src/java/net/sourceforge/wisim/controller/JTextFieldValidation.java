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
