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
 * WiSimDAOException.java
 *
 * Created on 09. February 2003, 12:02
 */

package net.sourceforge.wisim.dao;
/** An exception that provides information on a database access error.
 *
 * @author Kay Patzwald
 */
public class WiSimDAOException extends java.lang.Exception {

    /**
     * Creates a new instance of <code>WiSimDAOException</code> without detail message.
     */
    public WiSimDAOException() {
    }

    /**
     * Constructs an instance of <code>WiSimDAOException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public WiSimDAOException(String msg) {
        super(msg);
    }
}