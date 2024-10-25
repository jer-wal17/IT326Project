/*-------------------------------------------------------------------*
 * Class Name: ConnectionSerializer                                  *
 *                                                                   *
 * Description: abstraction for a way to connect to the database     *
 *                                                                   *
 *-------------------------------------------------------------------*/
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
    abstract boolean connect();

    // disconnects the program from the database
    abstract boolean disconnect();

}
