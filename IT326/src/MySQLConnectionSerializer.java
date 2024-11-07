import java.sql.*;

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
    private String connectionString; // string used to find database
    private String username;         // string used to specify db username for login
    private String password;         // string used to specify db password for login
    private Connection conn;         // connection object used for connection and for queries


    /*----------------------------------------------------------------*
     * Methods                                                        *
     *----------------------------------------------------------------*/
    // connects the program to the database *INCOMPLETE*
    public boolean connect() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{

        // define correct strings for making a connection to the database
        connectionString = "jdbc:mysql://localhost:3306/it326_group_project";
        username = "root";
        password = "password";

        // connect to the database and get back a Connection object
        conn = DriverManager.getConnection(connectionString, username, password);

        // return true because the connection was successful
        return true;
        
    }

    // disconnects the program from the database *INCOMPLETE*
    public boolean disconnect() throws SQLException{
        conn.close();
        return true;
    }

    // get method for the Connection object
    public Connection getConn() {
        return conn;
    }

}
