/*-------------------------------------------------------------------*
 * Class Name: MySQLConnectionSerializer                             *
 *                                                                   *
 * Description: facilitates connection to a MySQL database           *
 *                                                                   *
 *-------------------------------------------------------------------*/
public class MySQLConnectionSerializer extends ConnectionSerializer{

    /*----------------------------------------------------------------*
     * Fields                                                         *
     *----------------------------------------------------------------*/
    // string used to connect to the database
    String connectionString;


    /*----------------------------------------------------------------*
     * Methods                                                        *
     *----------------------------------------------------------------*/
    // connects the program to the database *INCOMPLETE*
    public boolean connect(){
        return true;
    }

    // disconnects the program from the database *INCOMPLETE*
    public boolean disconnect(){
        return true;
    }

}
