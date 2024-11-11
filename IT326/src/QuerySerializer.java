/*-------------------------------------------------------------------*
 * Class Name: QuerySerializer                                       *
 *                                                                   *
 * Description: abstraction for querying a databse for info          *
 *                                                                   *
 *-------------------------------------------------------------------*/

import java.sql.SQLException;

public abstract class QuerySerializer{

    /*----------------------------------------------------------------*
     * Fields                                                         *
     *----------------------------------------------------------------*/
    // the string that represents the query to the database
    String queryString;

    /*----------------------------------------------------------------*
     * Methods                                                        *
     *----------------------------------------------------------------*/
    // create an account and save in the database
    abstract public boolean save(Account account) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException;

    // create a group and save in the database
    //abstract public boolean save(Group group);

    // retrieve an account from the database and return the Account object
    abstract public Account retrieve(Account account) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException;

    // retrieve a group from the database and return the Group object
    //abstract public Group retrieve(Group group);

    // update the account in the database using the account passed into the method
    abstract public boolean update(Account account) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;

    // update the group in the database using the group passed into the method
    //abstract public boolean update(Group group);

    // delete the account information from the database
    abstract public boolean delete(Account account) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;

    // delete the group information from the database
    //abstract public boolean delete(Group group);

}