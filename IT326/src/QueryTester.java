// not part of problem set. here exclusively to test queries to the database

import java.sql.*;

public class QueryTester {

    String queryString;

    public boolean retrieveAccount(String userToRet, Connection conn) throws SQLException {

        queryString = "select * from Accounts join AccountPreferences on Accounts.AccountID=AccountPreferences.AccountID";

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(queryString);

        while( rs.next() ) {
            int id = rs.getInt("AccountID");
            String username = rs.getString("Username");
            int favDecade = rs.getInt("FavoriteDecade");

            if (username.equals(userToRet)) {
                System.out.println("username = " + username + ", favDecade = " + favDecade);
            }
        }

        return true;
    }
    
}
