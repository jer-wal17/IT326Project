// not part of problem set. here exclusively to test queries to the database

import java.sql.*;

public class QueryTester {

    private String queryString;


    public boolean retrieveAccount(String userToRet) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {

        ConnectionSerializer connSerializer = new MySQLConnectionSerializer();
        Connection connection = connSerializer.connect();

        queryString = "select * from Accounts join AccountPreferences on Accounts.AccountID=AccountPreferences.AccountID";

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(queryString);

        while( rs.next() ) {
            int id = rs.getInt("AccountID");
            String username = rs.getString("Username");
            int favDecade = rs.getInt("FavoriteDecade");

            if (username.equals(userToRet)) {
                System.out.println("username = " + username + ", favDecade = " + favDecade);
            }
        }

        connSerializer.disconnect(connection);

        return true;
    }
    
}
