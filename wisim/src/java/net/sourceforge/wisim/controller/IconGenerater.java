/*
 * IconGenerater.java
 *
 * Created on 17. März 2003, 10:23
 */

package net.sourceforge.wisim.controller;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

/** Erzeugt eine neue Instanz IconGenerater
 * @author benjamin.pasero
 */
public class IconGenerater {
    
    private static Color darkgreen = new Color(51, 153, 51);
    private static Color red = new Color(255, 0, 0);
    private static Color orange = new Color(255, 153, 0);
    private static Color darkgreenDither = new Color(51, 153, 51, 50);
    private static Color redDither = new Color(255, 0, 0, 50);
    private static Color orangeDither = new Color(255, 153, 0, 50);
    private Image icon;
    private Graphics g;
    private int abstand;
    
    /** Creates a new instance of IconGenerater */
    public IconGenerater() {
        icon = new BufferedImage(83, 30, 2);
        g = icon.getGraphics();
		abstand = 12;
    }
    
    /** Erzeugt ein ImageIcon je nach Parameterangabe.
     * @param type Der Typ gibt die Fabe der Rechtecke im ImageIcon an.
     * 0 steht für grün, 1 für orange und 2 für rot.
     * @param filledRects Steht für die Anzahl der gefüllten Rechtecke.
     * @return ImageIcon Objekt bestehend aus 5 Rechtecken.
     */
    public ImageIcon generateIcon(int type, int filledRects) {
        int i = 0;
        switch (type) {
            
            //Grünes Icon
            case 0:		
                g.setColor(darkgreen);
                while (i < filledRects) {
                    g.fillRect((abstand*(i+1)),11, 10, 10);
                    i++;
                }
                
                i = filledRects+1;
                while (i <= 5) {
                    g.setColor(darkgreen);
                    g.drawRect((abstand*i),11, 9, 9);
                    g.setColor(darkgreenDither);
                    g.fillRect((abstand*i),11, 9, 9);
                    i++;
                }
                break;
             
            //Oranges Icon   
            case 1:
                g.setColor(orange);
                while (i < filledRects) {
                    g.fillRect((abstand*(i+1)),11, 10, 10);
                    i++;
                }
                
                i = filledRects+1;
                while (i <= 5) {
                    g.setColor(orange);
                    g.drawRect((abstand*i),11, 9, 9);
                    g.setColor(orangeDither);
                    g.fillRect((abstand*i),11, 9, 9);
                    i++;
                }
                break;
             
            //Rotes Icon   
            case 2:
                g.setColor(red);
                while (i < filledRects) {
                    g.fillRect((abstand*(i+1)),11, 10, 10);
                    i++;
                }
                
                i = filledRects+1;
                while (i <= 5) {
                    g.setColor(red);
                    g.drawRect((abstand*i),11, 9, 9);
                    g.setColor(redDither);
                    g.fillRect((abstand*i),11, 9, 9);
                    i++;
                }
                break;
        }
        return new ImageIcon(icon);
    }
}