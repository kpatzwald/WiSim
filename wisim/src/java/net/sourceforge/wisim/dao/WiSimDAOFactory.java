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
