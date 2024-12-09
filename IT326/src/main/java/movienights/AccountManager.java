/**
 * Author: Zachary Kunzer
 * Class: IT326
 * Date: 12/08/2024
 * AccountManager.java - recieves validated data from account handler, talks to the database to save, update, retrieve, delete, and manage user accounts.
 */
package main.java.movienights;

import java.sql.SQLException;

public class AccountManager{
    Account curAccount;
    Account loginAccount;
    private final QuerySerializer dataBase = new MySQLQuerySerializer();

    public AccountManager(){
    }

/**
 * Creates a new account if the UID is not already associated with an existing account.
 * 
 * @param username the username for the new account
 * @param password the password for the new account
 * @param phoneNumber the phone number for the new account
 * @param uid the unique ID for the account
 * @return the newly created Account object or null if the UID is already in use
 */
    public Account createAccount(String username, String password, String phoneNumber, int uid) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        if(!dataBase.hasAlreadyStored(uid)){
            this.curAccount = new Account(username, phoneNumber, uid, password);
            dataBase.save(curAccount);
            return this.curAccount;
        }
        else{
            System.out.println("User ID has an Account");
            return null;
        }
    }

/**
 * Logs in a user by validating their UID and password.
 * 
 * @param uid the unique ID of the account to log into
 * @param password the password for the account
 * @return the logged-in Account object or null if login fails
 */
    public Account logIn(int uid, String password) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        if(dataBase.hasAlreadyStored(uid)){
            this.loginAccount=dataBase.retrieve(new Account(uid));
            if(loginAccount.getPassword().equals(password)){
                this.curAccount = this.loginAccount;
                listGroups();
                return this.curAccount;
            }
            else{
                System.out.println("\nPassword doesn't match");
            }
        }
        else{
            System.out.println("\nUsername or Password not found.");
        }
        return null;
    }

/**
 * Logs out the currently logged-in user and updates the database with the latest account data.
 * 
 * @return true if logout is successful, false otherwise
 */
    public boolean logOut() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        if(this.curAccount!=null && dataBase.hasAlreadyStored(this.curAccount.getUID())){
            dataBase.update(this.curAccount);
            return true;
        }
        return false;
    }

/**
 * Edits the details of the current account if logged in and updates the database.
 * 
 * @param changeTo the Account object containing the new details
 * @return true if the account details are successfully updated, false otherwise
 */
    public boolean editAccount(Account changeTo) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        if(this.curAccount!=null){
            if(dataBase.hasAlreadyStored(changeTo.getUID())){
                this.curAccount = changeTo;
                dataBase.update(this.curAccount);
                return true;
            }
            else{
                System.out.println("Cannot Save. UID was changed");
            }
        }
        System.out.println("Log in First");
        return false;
    }

/**
 * Changes the preferences of the current account and updates the database.
 * 
 * @param changeTo the Account object containing the updated preferences
 * @return true if the preferences are successfully updated, false otherwise
 */
    public boolean changePrefrences(Account changeTo) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        if(this.curAccount!=null){
            if(dataBase.hasAlreadyStored(changeTo.getUID())){
                this.curAccount = changeTo;
                dataBase.update(this.curAccount);
                return true;
            }
            else{
                System.out.println("Cannot Save. UID was changed");
            }
        }
        System.out.println("Log in First");
        return false;
    }

/**
 * Deletes the current account from the database if logged in.
 * 
 * @return true if the account is successfully deleted, false otherwise
 */
    public boolean deleteAccount() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        if(this.curAccount!=null){
            if(dataBase.hasAlreadyStored(this.curAccount.getUID())){
                dataBase.delete(this.curAccount);
                this.curAccount=null;
                return true;
            }
            else{
                System.out.println("Cannot Delete Account. UID Changed");
            }
        }
        System.out.println("Log in First");
        return false;
    }

/**
 * Lists the groups the current account has joined, if any.
 * Prints group details to the console or notifies the user if no groups are joined.
 */
    private void listGroups(){
        if(!this.curAccount.joinedGroup.isEmpty()){
            System.out.println("Here are your current groups");
            for(int i=0; i<this.curAccount.joinedGroup.size(); i++){
                System.out.println("Group ID: "+this.curAccount.joinedGroup.get(i).getGroupID()+"\nMovie: "+this.curAccount.joinedGroup.get(i).getMovie()+"\nMeeting Date: "+this.curAccount.joinedGroup.get(i).getMeetingDate()+"\nMeeting Address: "+this.curAccount.joinedGroup.get(i).getMeetingAddress()+"\n");
            }
        }
        else{
            System.out.println("You have not joined any groups.");
        }
    }
}