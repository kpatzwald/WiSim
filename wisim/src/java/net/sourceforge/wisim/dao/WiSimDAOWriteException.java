/*
 * WiSimDAOWriteException.java
 *
 * Created on 09. February 2003, 11:55
 */

package net.sourceforge.wisim.dao;
/**
 * An exception that provides information on a database error.
 * The attempt to do an update or insert in the database failed because there was an
 * constrained violation or sqmething.
 *
 * @author Kay Patzwald
 */
public class WiSimDAOWriteException extends java.lang.Exception {

    /**
     * Creates a new instance of <code>WiSimDAOWriteException</code> without detail message.
     */
    public WiSimDAOWriteException() {
    }


    /**
     * Constructs an instance of <code>WiSimDAOWriteException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public WiSimDAOWriteException(String msg) {
        super(msg);
    }
}