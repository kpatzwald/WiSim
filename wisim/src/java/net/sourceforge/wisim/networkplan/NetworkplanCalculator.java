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

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

/**
 * Class for calculating a network plan element.
 * @author Benjamin Pasero
 * @version 0.6a
 */
public class NetworkplanCalculator {

	private Vector npElemente;
	private Iterator npElemIt;

	/**
	 * Calculates a networkplan element
	 * @param npElemente
	 */
	public NetworkplanCalculator(Vector npElemente) {
		this.npElemente = npElemente;
		npElemIt = npElemente.iterator();

		/** Set index in the vector storing the networkplan elements */
		setIndex();

		/** Calculate the parent networkplan elements */
		setParents();

		/** Calculation */
		calculateFazFez();
		calculateSazSez();
		calculatePuffer();
	}

	/** 
	 * Determination of the parent network elements 
	 * */
	public void setParents() {
		npElemIt = npElemente.iterator();
		while (npElemIt.hasNext()) {
			NetworkplanElement npElem = (NetworkplanElement) npElemIt.next();
			int[] child = npElem.getChild();
			int i = 0;
			while (i < child.length && child[i] != 0) {
				((NetworkplanElement) npElemente.get(child[i] - 1)).addIntoParentBasket(new Integer(npElem.getIndex()));
				i++;
			}
		}

		npElemIt = npElemente.iterator();
		while (npElemIt.hasNext()) {
			NetworkplanElement npElem = (NetworkplanElement) npElemIt.next();
			if (npElem.getParentBasket().size() > 0) {
				npElem.getFromParentBasket();
			} else {
				npElem.addIntoParentBasket(new Integer(0));
				npElem.getFromParentBasket();
			}
		}
	}

	/** Forward Calculation */
	public void calculateFazFez() {
		npElemIt = npElemente.iterator();
		while (npElemIt.hasNext()) {
			NetworkplanElement npElem = (NetworkplanElement) npElemIt.next();

			int parent[] = npElem.getParent();
			if (parent[0] == 0) {
				npElem.setFaz(0);
				npElem.setFez(npElem.getDuration());
			} else {
				int i = 0;
				double maxFez = 0;
				while (i < parent.length) {
					NetworkplanElement npElemParent = (NetworkplanElement) npElemente.get(parent[i] - 1);
					if (maxFez < npElemParent.getFez())
						maxFez = npElemParent.getFez();
					i++;
				}
				npElem.setFaz(maxFez);
				npElem.setFez(npElem.getFaz() + npElem.getDuration());
			}
		}
	}

	/** Backward Calculation */
	public void calculateSazSez() {
		Vector npElementeDesc = new Vector();
		int a = npElemente.size() - 1;

		while (a >= 0) {
			npElementeDesc.add(npElemente.get(a));
			a--;
		}

		npElemIt = npElementeDesc.iterator();
		while (npElemIt.hasNext()) {
			NetworkplanElement npElem = (NetworkplanElement) npElemIt.next();

			int child[] = npElem.getChild();
			if (child[0] == 0) {
				npElem.setSaz(npElem.getFaz());
				npElem.setSez(npElem.getFez());
			} else {
				int i = 1;
				double minSaz = ((NetworkplanElement) npElemente.get(child[0] - 1)).getSaz();
				while (i < child.length) {
					NetworkplanElement npElemChild = (NetworkplanElement) npElemente.get(child[i] - 1);
					if (minSaz > npElemChild.getSaz())
						minSaz = npElemChild.getSaz();
					i++;
				}
				npElem.setSaz(minSaz - npElem.getDuration());
				npElem.setSez(minSaz);
			}
		}
	}

	/** Calculation of total float and free float */
	public void calculatePuffer() {

		/** total float (SAZ - FAZ) */
		npElemIt = npElemente.iterator();
		while (npElemIt.hasNext()) {
			NetworkplanElement npElem = (NetworkplanElement) npElemIt.next();
			npElem.setGp(npElem.getSaz() - npElem.getFaz());
		}

		/** free float (FAZ[i+1] - FEZ[i]) **/
		npElemIt = npElemente.iterator();
		while (npElemIt.hasNext()) {
			NetworkplanElement npElem = (NetworkplanElement) npElemIt.next();

			int child[] = npElem.getChild();
			if (child[0] != 0) {
				int i = 1;
				double minFaz = ((NetworkplanElement) npElemente.get(child[0] - 1)).getFaz();
				;
				while (i < child.length) {
					NetworkplanElement npElemParent = (NetworkplanElement) npElemente.get(child[i] - 1);
					if (minFaz > npElemParent.getFaz())
						minFaz = npElemParent.getFaz();
					i++;
				}
				npElem.setFp(minFaz - npElem.getFez());
			}
		}
	}

	/** 
	 * Critical path containing network plan elements that have total
	 * float = 0 and free float = 0
	 * @return Vector with network plan elements of the critical path
	 */
	public Vector getCriticalPath() {
		Vector criticalPath = new Vector();

		npElemIt = npElemente.iterator();
		while (npElemIt.hasNext()) {
			NetworkplanElement npElem = (NetworkplanElement) npElemIt.next();
			if (npElem.getFp() == 0 && npElem.getGp() == 0)
				criticalPath.add(new Integer(npElem.getIndex()));
		}
		return criticalPath;
	}

	/** 
	 * Returns the number of branches in the network plan 
	 * @return Width (Branches) of the network plan
	 */
	public int getCountedBranches() {
		int countedBranches = 1;
		npElemIt = npElemente.iterator();
		while (npElemIt.hasNext()) {
			NetworkplanElement npElem = (NetworkplanElement) npElemIt.next();
			int actWidth = npElem.getChild().length;

			if (actWidth > 1)
				countedBranches += actWidth;
		}
		return countedBranches;
	}

	/**
	 * @return The calculated network plan elements in a Vector
	 */
	public Vector getNpElemente() {
		return npElemente;
	}

	/** Set Index */
	public void setIndex() {
		int a = 0;
		Hashtable newElemPos = new Hashtable();

		while (a < npElemente.size()) {
			NetworkplanElement np = ((NetworkplanElement) npElemente.get(a));
			np.setIndex(a + 1);
			newElemPos.put(new Integer(np.getNumber()), new Integer(np.getIndex()));
			a++;
		}

		/** Reset the child-Numbers */
		a = 0;
		while (a < npElemente.size()) {
			NetworkplanElement np = (NetworkplanElement) npElemente.get(a);
			int child[] = np.getChild();

			int b = 0;
			while (b < child.length && child[b] != 0) {
				child[b] = ((Integer) newElemPos.get(new Integer(child[b]))).intValue();
				b++;
			}
			a++;
		}
	}
}