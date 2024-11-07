/*-------------------------------------------------------------------*
 * Class Name: ConnectionSerializer                                  *
 *                                                                   *
 * Description: abstraction for a way to connect to the database     *
 *                                                                   *
 *-------------------------------------------------------------------*/

import java.sql.SQLException;

public abstract class ConnectionSerializer {

    /*----------------------------------------------------------------*
     * Fields                                                         *
     *----------------------------------------------------------------*/
    // string used to connect to the database
    String connectionString;


    /*----------------------------------------------------------------*
     * Methods                                                        *
     *----------------------------------------------------------------*/
    // connects the program to the database
    abstract boolean connect() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;

    // disconnects the program from the database
    abstract boolean disconnect() throws SQLException;

}
