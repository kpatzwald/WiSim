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