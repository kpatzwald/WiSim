package net.sourceforge.wisim.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

/*
 * Created on 22.05.2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */

/**
 * @author Benjamin Pasero
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class NetzplanElementGrafikGenerator {
	private BufferedImage npElem;
	private Graphics g;
	private Font font;
	private int scaleX;
	private int width;
	private int height;


	/** Creates a new instance of IconGenerater */
	public NetzplanElementGrafikGenerator() {
		npElem = new BufferedImage(400, 200, 2);
		scaleX = 0;
		g = npElem.getGraphics();
	}

	public Image generateNetzplanelement(NetzplanElement np) {

		scaleX = (np.getBezeichnung().length() - 10) * 6;
		if (scaleX < 0)
			scaleX = 0;

		g.setColor(Color.BLACK);
		
		// Gesamtes Rechteck des Elements
		g.drawRect(0, 30, 200 + scaleX, 100);
		//this.width = 200 + scaleX;
		
		// Senkrechte Linie von Nummer zu Dauer
		g.drawLine(60, 30, 60, 130);
		
		// Horizontale Linie halbiert das Rechteck exakt
		g.drawLine(0, 80, 200 + scaleX, 80);
		
		// Senkrechte Linie zwischen GP und FP
		g.drawLine(130 + scaleX / 2 - 5, 80, 130 + scaleX / 2 - 5, 130);

		// Verbindungslinie Oben
		if (!np.isStartElem())
			g.drawLine(140, 0, 140, 30);
		
		// Verbindungslinie Unten
		if (!np.isEndElem())
			g.drawLine(140, 130, 140, 160);
		
		/** Nummer wird gesetzt */
		g.setFont(new Font("SansSerif", 0, 30));

		if (String.valueOf(np.getNummer()).matches("^[0-9]{2}$"))
			g.drawString(String.valueOf(np.getNummer()), 12, 66);
		else
			g.drawString(String.valueOf(np.getNummer()), 22, 66);

		/** Dauer wird gesetzt */
		g.setFont(new Font("SansSerif", 0, 25));

		if (String.valueOf(np.getDauer()).length() < 4)
			g.drawString(String.valueOf(np.getDauer()), 12, 116);
		else
			g.drawString(String.valueOf(np.getDauer()), 5, 116);

		g.setFont(new Font("SansSerif", 0, 15));

		/** FAZ wird gesetzt */
		g.drawString(String.valueOf(np.getFaz()), 0, 25);
		/** FEZ wird gesetzt */
		g.drawString(String.valueOf(np.getFez()), 173 + scaleX, 25);
		/** SAZ wird gesetzt */
		g.drawString(String.valueOf(np.getSaz()), 0, 147);
		/** SEZ wird gesetzt */
		g.drawString(String.valueOf(np.getSez()), 173 + scaleX, 147);

		/** FP und GP werden gesetzt */
		g.setFont(new Font("SansSerif", 0, 25));

		if (String.valueOf(np.getGp()).length() < 4)
			g.drawString(String.valueOf(np.getGp()), 77 + scaleX / 4, 116);
		else
			g.drawString(String.valueOf(np.getGp()), 70 + scaleX / 4, 116);

		if (String.valueOf(np.getFp()).length() < 4)
			g.drawString(String.valueOf(np.getFp()), 147 + (scaleX / 4) * 3, 116);
		else
			g.drawString(String.valueOf(np.getFp()), 138 + (scaleX / 4) * 3, 116);

		/** Bezeichnung wird gesetzt */
		g.setFont(new Font("SansSerif", 0, 15));

		g.drawString(np.getBezeichnung(), 90, 63);

		return npElem;
	}
	
	/**
	 * TODO Kommentar für getHeight()
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * TODO Kommentar für getWidth()
	 * @return
	 */
	public int getWidth() {
		return width;
	}

}