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


    /*----------------------------------------------------------------*
     * Methods                                                        *
     *----------------------------------------------------------------*/
    // connects the program to the database *INCOMPLETE*
    @Override
    public Connection connect() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{

        // define correct strings for making a connection to the database
        connectionString = "jdbc:mysql://localhost:3306/it326_group_project";
        username = "root";
        password = "password";

        // connect to the database and get back a Connection object
        Connection conn = DriverManager.getConnection(connectionString, username, password);

        // return true because the connection was successful
        return conn;
        
    }


    
    // disconnects the program from the database *INCOMPLETE*
    @Override
    public boolean disconnect(Connection connection) throws SQLException{
        connection.close();
        return true;
    }

}
