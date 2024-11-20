/*-------------------------------------------------------------------*
 * Class Name: MySQLQuerySerializer                                  *
 *                                                                   *
 * Description: abstraction for querying a databse for info          *
 *                                                                   *
 *-------------------------------------------------------------------*/

import java.sql.*;

public class MySQLQuerySerializer extends QuerySerializer {

    /*----------------------------------------------------------------*
     * Fields                                                         *
     *----------------------------------------------------------------*/
    // the string that represents the query to the database
    String queryString;




    /*----------------------------------------------------------------*
     * Public Methods                                                 *
     *----------------------------------------------------------------*/

    /*------------------------------------------------------------------*
    * Method: save(Account)                                             *
    *                                                                   *
    * Description: save the account in the database. if the account     *
    *              to be saved already exists in the database, then     *
    *              then the account is updated in the database instead  *
    *                                                                   *
    *-------------------------------------------------------------------*/
    @Override
    public boolean save(Account account) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {


        // call the helper method
        return saveAccount(account);

    }


    /*------------------------------------------------------------------*
    * Method: save(Group)                                               *
    *                                                                   *
    * Description: save the group in the database                       *
    *                                                                   *
    *-------------------------------------------------------------------*/
    //public boolean save(Group group) {}


    /*------------------------------------------------------------------*
    * Method: retrieve(Account)                                         *
    *                                                                   *
    * Description: retrieve an account from the database and return the *
    *              Account object. if the account cannot be found in    *
    *              the database, then null is returned. uses uid as key *
    *                                                                   *
    *-------------------------------------------------------------------*/
    @Override
    public Account retrieve(Account account) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {


        // declare account object that will be returned
        Account returnAccount = null;

        // create connection to database
        ConnectionSerializer connSerializer = new MySQLConnectionSerializer();
        Connection connection = connSerializer.connect();

        // create string that will be used to query the database
        queryString = "select * from Accounts WHERE AccountID=?";

        // query the database and get the result set back
        PreparedStatement pstmt = connection.prepareStatement(queryString);
        pstmt.setInt(1, account.getUID());
        ResultSet rs = pstmt.executeQuery();

        // use the result set object to create the Account object that is to be returned
        while( rs.next() ) {
            int uid = rs.getInt("AccountID");
            String username = rs.getString("Username");
            String phoneNumber = rs.getString("PhoneNumber");
            String password = rs.getString("Password");

            // if the stored username is not the same as the argument username
            if (uid == account.getUID()) {
                returnAccount = new Account(username, phoneNumber, uid, password);
            }
            else {
                System.out.println("cannot retrieve account because specified uid does not exist in database");
            }
        }

        // disconnect from the database
        connSerializer.disconnect(connection);

        // send the returnAccount back to the caller even if it is null
        return returnAccount;
        
    }


    /*------------------------------------------------------------------*
    * Method: retrieve(Group)                                           *
    *                                                                   *
    * Description: retrieve a group from the database and return the    *
    *              group object                                         *
    *                                                                   *
    *-------------------------------------------------------------------*/
    //public Group retrieve(Group group) {}


    /*------------------------------------------------------------------*
    * Method: update(Account)                                           *
    *                                                                   *
    * Description: update the account in the database using the account *
    *              passed into the method                               *
    *                                                                   *
    *-------------------------------------------------------------------*/
    @Override
    public boolean update(Account account) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {


        // call the helper methods to delete the account first, then save the updated version
        deleteAccount(account);
        return saveAccount(account);

    }


    /*------------------------------------------------------------------*
    * Method: update(Group)                                             *
    *                                                                   *
    * Description: update the group in the database using the group     *
    *              passed into the method                               *
    *                                                                   *
    *-------------------------------------------------------------------*/
    //public boolean update(Group group) {}


    /*------------------------------------------------------------------*
    * Method: delete(Account)                                           *
    *                                                                   *
    * Description: delete the account information from the database.    *
    *              if the account does not exists in the database,      *
    *              then nothing occurs and false is returned. true      *
    *              is returned in all other cases                       *
    *                                                                   *
    *-------------------------------------------------------------------*/
    @Override
    public boolean delete(Account account) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {


        return deleteAccount(account);

    }


    /*------------------------------------------------------------------*
    * Method: delete(Group)                                             *
    *                                                                   *
    * Description: delete the group information from the database       *
    *                                                                   *
    *-------------------------------------------------------------------*/
    //public boolean delete(Group group) {}







    /*----------------------------------------------------------------*
     * Private Helper Methods                                         *
     *----------------------------------------------------------------*/

    /*------------------------------------------------------------------*
    * Method: saveAccount(Account)                                      *
    *                                                                   *
    * Description: helper method for the save(Account) and update()     *
    *              methods. Uses the account's UID to identify it in    *
    *              the database. if the account to be saved already     *
    *              exists, then the account is updated instead          *
    *                                                                   *
    *-------------------------------------------------------------------*/
    private boolean saveAccount(Account account) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {


        // create connection to the database
        ConnectionSerializer connSerializer = new MySQLConnectionSerializer();
        Connection connection = connSerializer.connect();

        // if the account already exists, call the update() method instead
        if (isAlreadyStored(account)) {
            return update(account);
        }

        // create the sql query and insert the account into the database
        queryString = "INSERT INTO Accounts (AccountID, Username, Password, PhoneNumber) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(queryString);
        pstmt.setInt(1, account.getUID());
        pstmt.setString(2, account.getUsername());
        pstmt.setString(3, account.getPassword());
        pstmt.setString(4, account.getPhoneNumber());
        pstmt.executeUpdate();

        // disconnect from the database
        connSerializer.disconnect(connection);

        return true;

    }


    /*------------------------------------------------------------------*
    * Method: deleteAccount(Account)                                    *
    *                                                                   *
    * Description: helper method for the delete(Account) and update()   *
    *              methods. uses the account's UID to identify it in    *
    *              the database                                         *
    *                                                                   *
    *-------------------------------------------------------------------*/
    private boolean deleteAccount(Account account) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {


        // connect to the database
        ConnectionSerializer connSerializer = new MySQLConnectionSerializer();
        Connection connection = connSerializer.connect();

        // if the account does not exist in the database, then return false since we did not delete an account
        if (!isAlreadyStored(account)) {
            System.out.println("account does not exist in the database and thus could not be deleted");
            return false;
        }

        // create query string and delete the account from the database
        queryString = "DELETE FROM Accounts WHERE AccountID=?";
        PreparedStatement pstmt = connection.prepareStatement(queryString);
        pstmt.setInt(1, account.getUID());
        pstmt.executeUpdate();

        // disconnect from the database
        connSerializer.disconnect(connection);

        return true;

    }



    // checks to see if the database already has an account with the specified account uid. returns true if yes, false otherwise
    @Override
    public boolean isAlreadyStored(int uid) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

        boolean isStored = false;

        // create connection to database
        ConnectionSerializer connSerializer = new MySQLConnectionSerializer();
        Connection connection = connSerializer.connect();

        // create string that will be used to query the database
        queryString = "select * from Accounts WHERE AccountID=?";

        // query the database and get the result set back
        PreparedStatement pstmt = connection.prepareStatement(queryString);
        pstmt.setInt(1, uid);
        ResultSet rs = pstmt.executeQuery();

        // use the result set object to create the Account object that is to be returned
        while( rs.next() ) {
            int dbUID = rs.getInt("AccountID");

            // if the stored username is not the same as the argument username
            if (dbUID == uid) {
                return true;
            }
        }

        // disconnect from the database
        connSerializer.disconnect(connection);

        // send the returnAccount back to the caller even if it is null
        return isStored;


    }

}