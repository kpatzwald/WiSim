/*   ********************************************************************   **
**   Copyright notice                                                       **
**                                                                          **
**   (c) 2003-2021 WiSim Development Team                                   **
**   https://github.com/kpatzwald/WiSim                                     **
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
 * WiSimDAOWriteException.java
 *
 * Created on 09. February 2003, 11:55
 */
package net.sourceforge.wisim.dao;

/**
 * An exception that provides information on a database error. The attempt to do
 * an update or insert in the database failed because there was an constrained
 * violation or sqmething.
 *
 * @author Kay Patzwald
 */
public class WiSimDAOWriteException extends java.lang.Exception {

  /**
   * Creates a new instance of <code>WiSimDAOWriteException</code> without
   * detail message.
   */
  public WiSimDAOWriteException() {
  }

  /**
   * Constructs an instance of <code>WiSimDAOWriteException</code> with the
   * specified detail message.
   *
   * @param msg the detail message.
   */
  public WiSimDAOWriteException(String msg) {
    super(msg);
  }
}
