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
