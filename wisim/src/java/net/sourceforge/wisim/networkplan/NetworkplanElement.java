/*   ********************************************************************   **
**   Copyright notice                                                       **
**                                                                          **
**   (c) 2003-2021 WiSim Development Team					                    					**
**   https://github.com/kpatzwald/WiSim   			                            			**
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

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

/**
 * Class for generating one network plan element
 * @author Benjamin Pasero
 * @version 0.7a
 */
public class NetworkplanElement {

	private int index;
	private int number;
	private double duration;
	private boolean criticalPath;
	private int[] parent;
	private int[] child;

	private double faz;
	private double fez;
	private double saz;
	private double sez;
	private double gp;
	private double fp;

	private String description;

	private Collection parentBasket;
	private Collection childBasket;

	private int layoutManager;

	private boolean childSet;
	
	private boolean pseudoActivity;

	/**
	 * Class representating one networkplan element. 
	 * The user sets the child elements of the activity. The parent elements are calculated.
	 * @param number Number of the activity
	 * @param duration Duration of the activity
	 * @param child dependant Child(s) of the activity 
	 * @param description Description of the activity
	 */
	public NetworkplanElement(int number, double duration, int[] child, String description) {
		childSet = true;
		this.number = number;
		this.duration = duration;
		this.child = child;
		parentBasket = new Vector();
		childBasket = new Vector();
		this.description = description;
		criticalPath = false;
		pseudoActivity = false;
	}

	/**
	 * Class representating one networkplan element. 
	 * The user sets the parent elements of the activity. The child elements are calculated.
	 * wants to set the parents and not the childs.
	 * @param number of the activity
	 * @param duration of the activity
	 * @param description of the activity
	 * @param parent dependant Parent(s) of the activity
	 */
	public NetworkplanElement(int number, double duration, String description, int[] parent) {
		childSet = false;
		this.number = number;
		this.duration = duration;
		childBasket = new Vector();
		this.description = description;
		criticalPath = false;
		this.parent = parent;
	}

	/**
	 * Get the duration of the network plan element
	 * @return duration
	 */
	public double getDuration() {
		return duration;
	}

	/**
	 * Set the duration
	 * @param duration
	 */
	public void setDuration(double duration) {
		this.duration = duration;
	}

	/**
	 * Get the earliest start
	 * @return faz
	 */
	public double getFaz() {
		return faz;
	}

	/**
	 * Get the earliest finish
	 * @return fez
	 */
	public double getFez() {
		return fez;
	}

	/**
	 * Get the free buffer
	 * @return fp
	 */
	public double getFp() {
		return fp;
	}

	/**
	 * Get whole buffer
	 * @return gp
	 */
	public double getGp() {
		return gp;
	}

	/**
	 * Get the latest start
	 * @return saz
	 */
	public double getSaz() {
		return saz;
	}

	/**
	 * Get the latest finish
	 * @return sez
	 */
	public double getSez() {
		return sez;
	}

	/**
	 * Set the earliest start
	 * @param faz
	 */
	public void setFaz(double faz) {
		this.faz = faz;
	}

	/**
	 * Set the latest start
	 * @param fez
	 */
	public void setFez(double fez) {
		this.fez = fez;
	}

	/**
	 * Set the free buffer
	 * @param fp
	 */
	public void setFp(double fp) {
		this.fp = fp;
	}

	/**
	 * Set the whole buffer
	 * @param gp
	 */
	public void setGp(double gp) {
		this.gp = gp;
	}

	/**
	 * Set the latest finish
	 * @param saz
	 */
	public void setSaz(double saz) {
		this.saz = saz;
	}

	/**
	 * Set the latest finish
	 * @param sez
	 */
	public void setSez(double sez) {
		this.sez = sez;
	}

	/**
	 * Get number
	 * @return index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Set the index in the vector of all networkplan elements
	 * @param index
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * Get array with number of parent elements
	 * @return parent
	 */
	public int[] getParent() {
		return parent;
	}

	/**
	 * Set parents
	 * @param parent
	 */
	public void setParent(int[] parent) {
		this.parent = parent;
	}

	/**
	 * Get array with number of child elements
	 * @return child
	 */
	public int[] getChild() {
		return child;
	}

	/**
	 * Set childs
	 * @param child
	 */
	public void setChild(int[] child) {
		this.child = child;
	}

	/**
	 * Get a collection holding the parents
	 * @return parentBasket
	 */
	public Collection getParentBasket() {
		return parentBasket;
	}

	/**
	 * Set the collection holding the parents
	 * @param parentBasket
	 */
	public void setParentBasket(Collection parentBasket) {
		this.parentBasket = parentBasket;
	}

	/** Set the collection holding the childs
	* @param childBasket
	*/
	public void setChildBasket(Collection childBasket) {
		this.childBasket = childBasket;
	}

	/**
	 * Add one parent into the basket
	 * @param parent's index
	 */
	public void addIntoParentBasket(Integer parent) {
		parentBasket.add(parent);
	}

	/** Add one child into the basket 
	 * @param child's index
	 */
	public void addIntoChildBasket(Integer child) {
		childBasket.add(child);
	}

	/** Set this elements parents */
	public void getFromParentBasket() {
		Iterator parentBasketIt = parentBasket.iterator();
		parent = new int[parentBasket.size()];
		int i = 0;
		while (parentBasketIt.hasNext()) {
			parent[i] = ((Integer) parentBasketIt.next()).intValue();
			i++;
		}
	}

	/** Set this elements childs */
	public void getFromChildBasket() {
		Iterator childBasketIt = childBasket.iterator();
		child = new int[childBasket.size()];
		int i = 0;
		while (childBasketIt.hasNext()) {
			child[i] = ((Integer) childBasketIt.next()).intValue();
			i++;
		}
	}

	/**
	 * Get the description
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the description
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return TRUE if element is the start element of the network plan
	 */
	public boolean isStartElem() {
		if (parent[0] == 0)
			return true;

		return false;
	}

	/**
	 * @return TRUE if element is the end element of the network plan
	 */
	public boolean isEndElem() {
		if (child[0] == 0)
			return true;

		return false;
	}
	/**
	 * @return TRUE if element is member of the critical path
	 */
	public boolean isCriticalPath() {
		return criticalPath;
	}

	/**
	 * Set TRUE if element is member of the critial path
	 * @param isCriticalPath
	 */
	public void setCriticalPath(boolean isCriticalPath) {
		criticalPath = isCriticalPath;
	}

	/**
	 * Get the number of the networkplan element
	 * @return number of the element
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Set the number of the networkplan element
	 * @param number of the element
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * Get the LayoutManager of this element. The int-Value represents the
	 * position within the array of the JPanels displaying the networkplan elements
	 * @return
	 */
	public int getLayoutManager() {
		return layoutManager;
	}

	/**
	 * Set the LayoutManager of this element. The int-Value represents the
	 * position within the array of the JPanels displaying the networkplan elements
	 * @param i
	 */
	public void setLayoutManager(int i) {
		layoutManager = i;
	}

	/**
	 * @return TRUE if the user has set the child's in the constructor
	 */
	public boolean isChildSet() {
		return childSet;
	}

	/**
	 * @param TRUE if the user has set the child's in the constructor
	 */
	public void setChildSet(boolean childSet) {
		this.childSet = childSet;
	}

	/**
	 * @return Vector containing the numbers of all childs
	 */
	public Collection getChildBasket() {
		return childBasket;
	}
	/**
	 * @return TRUE if this element is a pseudo activity
	 */
	public boolean isPseudoActivity() {
		return pseudoActivity;
	}

	/**
	 * @param pseudoActivity
	 */
	public void setPseudoActivity(boolean pseudoActivity) {
		this.pseudoActivity = pseudoActivity;
	}
}