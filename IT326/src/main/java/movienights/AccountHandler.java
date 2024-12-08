/**
 * Author: Zachary Kunzer
 * Class: IT326
 * Date: 12/08/2024
 * AccountHandler.java - recieves incoming data from the controller. Validates all the data before calling AccountManager to compute data.
 */
package main.java.movienights;

import java.sql.SQLException;

public class AccountHandler{
    private final AccountManager manager = new AccountManager();
    public AccountHandler(){

    }

/**
 * Validates the data for account creation and formats the phone number before passing it to the manager.
 * 
 * @param username the username for the new account
 * @param password the password for the new account
 * @param phoneNumber the phone number for the new account
 * @param uid the unique ID for the account
 * @return the newly created Account object or null if validation fails
 */
    public Account validteCreateAccount(String username, String password, String phoneNumber, int uid) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        //Validate data given by user here
        if(username==null){
            System.out.println("Username Needed");
            return null;
        }
        else if(password==null){
            System.out.println("Password Needed");
            return null;
        }
        else if(phoneNumber==null){
            System.out.println("Phone Number Needed");
            return null;
        }
        if(uid<=0){
            System.out.println("UID cannot be negative");
            return null;
        }

        String digitsOnly = phoneNumber.replaceAll("\\D", "");
        if(digitsOnly.length()!=10){
            System.out.println("Not a valid phone number");
            return null;
        }
        String formattedNumber = "("+digitsOnly.substring(0,3)+")"+digitsOnly.substring(3,6)+"-"+digitsOnly.substring(6);
        
        return manager.createAccount(username, password, formattedNumber, uid);
    }

/**
 * Validates the data for login and passes the request to the manager.
 * 
 * @param uid the unique ID of the account to log into
 * @param password the password for the account
 * @return the logged-in Account object or null if validation fails
 */
    public Account validateLoginRequest(int uid, String password)  throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        //Validate data given by user here
        if(uid<=0){
            System.out.println("UID cannot be negative");
            return null;
        }
        else if(password==null){
            System.out.println("Password Needed");
            return null;
        }
        return manager.logIn(uid, password);
    }

/**
 * Validates the logout request and passes it to the manager.
 * 
 * @return true if the logout is successful, false otherwise
 */
    public boolean validateLogoutRequest() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        return manager.logOut();
    }

/**
 * Validates the data for editing an account and formats the phone number before passing the request to the manager.
 * 
 * @param changeTo the Account object with the updated details
 * @return true if the account details are successfully updated, false otherwise
 */
    public boolean validateEditAccountRequest(Account changeTo) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        //Validate data given by user here
        if(changeTo.getUsername()==null){
            System.out.println("Username Needed");
            return false;
        }
        else if(changeTo.getPassword()==null){
            System.out.println("Password Needed");
            return false;
        }
        else if(changeTo.getPhoneNumber()==null){
            System.out.println("Phone Number Needed");
            return false;
        }
        String phoneNumber = changeTo.getPhoneNumber();
        String digitsOnly = phoneNumber.replaceAll("\\D", "");
        if(digitsOnly.length()!=10){
            System.out.println("Not a valid phone number");
            return false;
        }
        String formattedNumber = "("+digitsOnly.substring(0,3)+")"+digitsOnly.substring(3,6)+"-"+digitsOnly.substring(6);
        changeTo.setPhoneNumber(formattedNumber);

        return manager.editAccount(changeTo);
    }

/**
 * Validates the data for changing preferences and passes the request to the manager.
 * 
 * @param changeTo the Account object with the updated preferences
 * @return true if the preferences are successfully updated, false otherwise
 */
    public boolean validateChangePrefrencesRequest(Account changeTo) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        //Validate data given by user here
        int favDecade = changeTo.getDecade();
        if((favDecade>=10 && favDecade<=90 && favDecade%10!=0) || favDecade==0 || favDecade==404){
            return manager.changePrefrences(changeTo);
        }
        return false;
    }

/**
 * Validates the delete account request and passes it to the manager.
 * 
 * @return true if the account is successfully deleted, false otherwise
 */
    public boolean validateDeleteAccountRequest() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        return manager.deleteAccount();
    }
    
}