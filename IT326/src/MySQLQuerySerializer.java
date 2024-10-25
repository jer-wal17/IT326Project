/*-------------------------------------------------------------------*
 * Class Name: MySQLQuerySerializer                                  *
 *                                                                   *
 * Description: abstraction for querying a databse for info          *
 *                                                                   *
 *-------------------------------------------------------------------*/
public class MySQLQuerySerializer extends QuerySerializer {

    /*----------------------------------------------------------------*
     * Fields                                                         *
     *----------------------------------------------------------------*/
    // the string that represents the query to the database
    String queryString;

    /*----------------------------------------------------------------*
     * Methods                                                        *
     *----------------------------------------------------------------*/
    // create an account and save in the database *INCOMPLETE*
    public boolean create(Account account) {

    }

    // create a group and save in the database *INCOMPLETE*
    public boolean create(Group group) {

    }

    // retrieve an account from the database and return the Account object *INCOMPLETE*
    public Account retrieve(Account account) {
        
    }

    // retrieve a group from the database and return the Group object *INCOMPLETE*
    public Group retrieve(Group group) {
        
    }

    // update the account in the database using the account passed into the method *INCOMPLETE*
    public boolean update(Account account) {

    }

    // update the group in the database using the group passed into the method *INCOMPLETE*
    public boolean update(Group group) {

    }

    // delete the account information from the database *INCOMPLETE*
    public boolean delete(Account account) {

    }

    // delete the group information from the database *INCOMPLETE*
    public boolean delete(Group group) {

    }

}