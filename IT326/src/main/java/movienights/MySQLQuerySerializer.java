package main.java.movienights;
/*-------------------------------------------------------------------*
 * Class Name: MySQLQuerySerializer                                  *
 *                                                                   *
 * Description: abstraction for querying a databse for info          *
 *                                                                   *
 *-------------------------------------------------------------------*/

import java.sql.*;
import java.time.LocalDate;

public class MySQLQuerySerializer extends QuerySerializer {

    
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
    @Override
    public int save(Group group) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        // call the helper method
        return saveGroup(group);
    }


    /*------------------------------------------------------------------*
    * Method: retrieve(Account)                                         *
    *                                                                   *
    * Description: retrieve an account from the database based on the   *
    *              account uid and return the Account object. if the    *
    *              uid cannot be found in the database, then null is    *
    *              returned. uses uid as key to find account in the     *
    *              database.                                            *
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
                System.out.println("cannot retrieve account because specified uid does not exist in database. returning null");
            }
        }

        // if the returnAccount was able to be created from the database query then get the groups the account is a part of
        if (returnAccount != null) {

            // create string that will be used to query the database for the groups that the account is a part of
            queryString = "SELECT * FROM it326_group_project.GroupMembers JOIN it326_group_project.Groups ON it326_group_project.GroupMembers.GroupID=it326_group_project.Groups.GroupID where AccountID=?";

            // query the database and get the result set back
            pstmt = connection.prepareStatement(queryString);
            pstmt.setInt(1, returnAccount.getUID());
            rs = pstmt.executeQuery();

            // use the result set object to create the Group objects that is to be added to the Account object
            while( rs.next() ) {
                int groupID = rs.getInt("GroupID");
                String movieTitle = rs.getString("MovieTitle");
                Movie movie = new Movie(movieTitle);
                String meetingAddress = rs.getString("MeetingAddress");
                Date dbDate = rs.getDate("MeetingDate");
                LocalDate localDate = LocalDate.of(dbDate.getYear(), dbDate.getMonth(),dbDate.getDay());

                Group newGroup = new Group(groupID, movie, meetingAddress, localDate, returnAccount, 0);
                returnAccount.group.add(newGroup);

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
    @Override
    public Group retrieve(Group group) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {

        // declare Group object that will be returned
        Group returnGroup = null;


        // create connection to database
        ConnectionSerializer connSerializer = new MySQLConnectionSerializer();
        Connection connection = connSerializer.connect();

        // create string that will be used to query the database
        queryString = "SELECT * FROM it326_group_project.Groups WHERE GroupID=?";

        // query the database and get the result set back
        PreparedStatement pstmt = connection.prepareStatement(queryString);
        pstmt.setInt(1, group.getGroupID());
        ResultSet rs = pstmt.executeQuery();

        // use the result set object to create the Account object that is to be returned
        while( rs.next() ) {
            int groupID = rs.getInt("GroupID");
            String movieTitle = rs.getString("MovieTitle");
            Movie movie = new Movie(movieTitle);
            String meetingAddress = rs.getString("MeetingAddress");
            Date dbDate = rs.getDate("MeetingDate");
            LocalDate localDate = dbDate.toLocalDate();
            Account owner = new Account();

            // if the stored username is not the same as the argument username
            if (group.getGroupID() == groupID) {
                returnGroup = new Group(groupID, movie, meetingAddress, localDate, owner, 0);
            }
            else {
                System.out.println("cannot retrieve account because specified uid does not exist in database. returning null");
            }
        }

        if (returnGroup != null) {

            // create string that will be used to query the database for the groups that the account is a part of
            queryString = "SELECT * FROM it326_group_project.GroupMembers inner JOIN it326_group_project.Groups ON it326_group_project.GroupMembers.GroupID=it326_group_project.Groups.GroupID WHERE it326_group_project.Groups.GroupID=?";

            // query the database and get the result set back
            pstmt = connection.prepareStatement(queryString);
            pstmt.setInt(1, returnGroup.getGroupID());
            rs = pstmt.executeQuery();

            // use the result set object to create the Group objects that is to be added to the Account object
            while( rs.next() ) {
                int accountID = rs.getInt("AccountID");
                returnGroup.getMembers().add(retrieve(new Account(accountID)));

            }

        }

        // send the returnGroup back to the caller even if it is still null
        return returnGroup;
    }


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
    @Override
    public int update(Group group) throws InstantiationException, IllegalAccessException, SQLException, ClassNotFoundException {
        delete(group);
        return saveGroup(group);
    }


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
    @Override
    public boolean delete(Group group) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

        deleteGroup(group);
        return true;

    }







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
        if (hasAlreadyStored(account.getUID())) {
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
        if (!hasAlreadyStored(account.getUID())) {
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



    /*------------------------------------------------------------------*
    * Method: deleteGroup(Group)                                        *
    *                                                                   *
    * Description: helper method for the delete(Group) and update(Group)*
    *              methods. uses the group's id to identify it in       *
    *              the database                                         *
    *                                                                   *
    *-------------------------------------------------------------------*/
    private boolean deleteGroup(Group group) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {


        // connect to the database
        ConnectionSerializer connSerializer = new MySQLConnectionSerializer();
        Connection connection = connSerializer.connect();

        // if the account does not exist in the database, then return false since we did not delete an account
        if (!hasAlreadyStoredGroup(group.getGroupID())) {
            System.out.println("group does not exist in the database and thus could not be deleted");
            return false;
        }

        // create query string and delete the account from the database
        queryString = "DELETE FROM it326_group_project.Groups WHERE GroupID=?";
        PreparedStatement pstmt = connection.prepareStatement(queryString);
        pstmt.setInt(1, group.getGroupID());
        pstmt.executeUpdate();

        // disconnect from the database
        connSerializer.disconnect(connection);

        return true;

    }




    /*------------------------------------------------------------------*
    * Method: hasAlreadyStored(int uid)                                 *
    *                                                                   *
    * Description: checks to see if the database already has an account *
    *              with the specified account uid. returns true if yes, *
    *              false otherwise                                      *
    *                                                                   *
    *-------------------------------------------------------------------*/
    @Override
    public boolean hasAlreadyStored(int uid) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

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

        // use the result set object to get the uid
        while( rs.next() ) {
            int dbUID = rs.getInt("AccountID");

            // if the uid stored in the database equals the argument passed uid
            if (dbUID == uid) {
                isStored = true;
            }
        }

        // disconnect from the database
        connSerializer.disconnect(connection);

        // send the returnAccount back to the caller even if it is null
        return isStored;


    }



    /*------------------------------------------------------------------*
    * Method: hasAlreadyStoredGroup                                     *
    *                                                                   *
    * Description: checks to see if the database already has a Group    *
    *              with the specified group id. returns true if yes,    *
    *              false otherwise                                      *
    *                                                                   *
    *-------------------------------------------------------------------*/
    public boolean hasAlreadyStoredGroup(int groupID) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        boolean isStored = false;

        // create connection to database
        ConnectionSerializer connSerializer = new MySQLConnectionSerializer();
        Connection connection = connSerializer.connect();

        // create string that will be used to query the database
        queryString = "SELECT * FROM it326_group_project.Groups WHERE GroupID=?";

        // query the database and get the result set back
        PreparedStatement pstmt = connection.prepareStatement(queryString);
        pstmt.setInt(1, groupID);
        ResultSet rs = pstmt.executeQuery();

        // use the result set object to get the uid
        while( rs.next() ) {
            int dbGid = rs.getInt("GroupID");

            // if the uid stored in the database equals the argument passed uid
            if (dbGid == groupID) {
                isStored = true;
            }
        }

        // disconnect from the database
        connSerializer.disconnect(connection);

        // send the returnAccount back to the caller even if it is null
        return isStored;
    }



    /*------------------------------------------------------------------*
    * Method: saveGroup(Group)                                          *
    *                                                                   *
    * Description: helper method for the save(Group) and update(Group)  *
    *              methods. Uses the group's id to identify it in       *
    *              the database. if the group to be saved already       *
    *              exists, then the group is updated instead            *
    *                                                                   *
    *-------------------------------------------------------------------*/
    private int saveGroup(Group group) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

        // create connection to the database
        ConnectionSerializer connSerializer = new MySQLConnectionSerializer();
        Connection connection = connSerializer.connect();

        // if the group already exists, call the update(group) method instead
        if (hasAlreadyStoredGroup(group.getGroupID())) {
            return update(group);
        }

        // create the sql query and insert the group into the database
        queryString = "INSERT INTO it326_group_project.Groups(MovieTitle, MeetingAddress, MeetingDate) VALUES(?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(queryString,Statement.RETURN_GENERATED_KEYS);
        //pstmt.setInt(1, group.getGroupID());
        pstmt.setString(1, group.getMovie().getMovieName());
        pstmt.setString(2, group.getMeetingAddress());
        pstmt.setDate(3, Date.valueOf(group.getMeetingDate()));
        pstmt.executeUpdate();
        ResultSet rs = pstmt.getGeneratedKeys();
        int row = 0;
        if(rs.next()){
            row = rs.getInt(1);
        }

        // disconnect from the database
        connSerializer.disconnect(connection);

        return row;

    }

}