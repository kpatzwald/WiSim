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
 * WiSimDAOFactory.java
 *
 * Created on 23. April 2002, 10:15
 */

package net.sourceforge.wisim.dao;

/** Initializing the DOA-object
 *
 * @author Kay Patzwald
 */
public class WiSimDAOFactory {
    
    /** Creates a new instance of WiSimDAOFactory */
    public WiSimDAOFactory() {
    }
    
    /** returns a object with implements the <code>WiSimDAO</code> interface.
     * @throws com.pixelpark.wisim.dao.WiSimDAOException if a database problem occurs
     * @return the initialized DOA-object
     */
    public WiSimDAO getDAO() 
        throws WiSimDAOException 
    {
    
        WiSimDAOImpl daoI = new WiSimDAOImpl();
        daoI.initialize();
        return( (WiSimDAO) daoI);
    }
}
