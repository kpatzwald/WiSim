/*   ********************************************************************    **
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
 * Created on 01.07.2003
 *
 */
package net.sourceforge.wisim.dao;

/**
 * @author Kay Patzwald
 *
 */
public interface WiSimAuthentificationDAO {

  /**
   * @return
   */
  public String getDbName();

  /**
   * @return
   */
  public String getHostName();

  /**
   * @return
   */
  public String getPassword();

  /**
   * @return
   */
  public String getPort();

  /**
   * @return
   */
  public String getUser();

  public void updateDBSettings(String hostname, String port, String user, String password);
}
