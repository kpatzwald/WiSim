/*
 * Validator.java
 *
 * Created on 28. Februar 2003, 15:07
 */

package net.sourceforge.wisim.controller;

/** Verschiedene Methoden zum Validiern der User Eingabe in der Applikation
 * @author benjamin.pasero
 */
public class Validator {
    
    /** Creates a new instance of Validate */
    public Validator() {
    }
    
    /** Überprüft ob die PLZ 5 Zeichen besitzt und nur aus Ziffern besteht.
     * @param text Die PLZ als String
     * @return TRUE wenn die PLZ stimmt
     */
    public boolean checkPlz(String text) {
        String regExp = "^[0-9][1-9][0-9]{3}|[1-9][0-9][0-9]{3}$";
        if (text.matches(regExp))
            return true;
        else
            return false;
    }
    
    /** Überprüft die eingegebene email auf Gültigkeit.
     * @param text Die email als String
     * @return TRUE wenn die email korrekt ist
     */
    public boolean checkEMail(String text) {
        String regExpEmail = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        int length = text.length();
        int i=0;
        if (length > 0) {
            if (text.matches(regExpEmail)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    
    /** Überprüft, ob ein korrekter Preis eingegeben wurde.
     * @return boolean
     * @param text Zu überprüfender Text.
     */
    public boolean checkPreis(String text) {
        String regExp = "^([0-9]{0,7}[.]{1}[0-9]{2})$";
        if (text.matches(regExp))
            return true;
        else
            return false;
    }
    
    /** Überprüft, ob eine Zahl eingegeben wurde und das nicht mehr
     * als 10 Ziffern eingegeben worden sind.
     * @return boolean
     * @param text Zu überprüfender Text.
     */
    public boolean checkZahl(String text) {
        String regExp = "^([0-9]{0,10})$";
        if (text.matches(regExp))
            return true;
        else
            return false;
    }
    
    /** Erlaubt sind nur Eingaben von 1- oder 2-stellige Zahlen mit 1 Nachkommastelle
     * getrennt durch einen Punkt
     * @return boolean
     * @param text Zu überprüfender Text.
     */
    public boolean checkProzent(String text) {
        String regExp = "[0-9]{1,2}\\.[0-9]{1}";
        if (text.matches(regExp)) {
            return true;
        } else {
            return false;
        }
    }
    
    /** Erlaubt sind nur Eingaben von 4-stelligen Zahlen die nicht mit 0 beginnen
     * @param text Zu überprüfender Text.
     * @return boolean
     */
    public boolean checkYear(String text) {
        String regExp = "^([1-9]{1}[0-9]{3})$";
        if (text.matches(regExp)) {
            return true;
        } else {
            return false;
        }
    }
    
    /** Erlaubt sind nur Eingaben von 1- oder 2-stellige Zahlen
     * @param text Zu überprüfender Text.
     * @return boolean
     */
    public boolean checkTwoDigits(String text) {
        String regExp = "^([0-9]{1,2})$";
        if (text.matches(regExp)) {
            return true;
        } else {
            return false;
        }
    }
    
    /** Erlaubt sind nur Eingaben von 1- oder 2-stellige Zahlen
     * @param text Zu überprüfender Text.
     * @return boolean
     */
    public boolean checkThreeDigits(String text) {
        String regExp = "^[1-9]{1}[0-9]{0,2}$";
        if (text.matches(regExp)) {
            return true;
        } else {
            return false;
        }
    }    
}