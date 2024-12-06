package main.java.movienights;
/*-------------------------------------------------------------------*
 * Class Name: ConnectionSerializer                                  *
 *                                                                   *
 * Description: abstraction for a way to connect to the database     *
 *                                                                   *
 *-------------------------------------------------------------------*/

import java.sql.*;

public abstract class ConnectionSerializer {

    /*----------------------------------------------------------------*
     * Fields                                                         *
     *----------------------------------------------------------------*/
    // string used to connect to the database
    private String connectionString;


    /*----------------------------------------------------------------*
     * Methods                                                        *
     *----------------------------------------------------------------*/
    // connects the program to the database
    abstract Connection connect() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;

    // disconnects the program from the database
    abstract boolean disconnect(Connection connection) throws SQLException;

}
