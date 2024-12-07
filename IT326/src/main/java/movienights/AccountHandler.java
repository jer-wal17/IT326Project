package main.java.movienights;

import java.sql.SQLException;

public class AccountHandler{
    private final AccountManager manager = new AccountManager();
    public AccountHandler(){

    }

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
    public boolean validateLogoutRequest() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        return manager.logOut();
    }
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
    public boolean validateChangePrefrencesRequest(Account changeTo) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        //Validate data given by user here
        int favDecade = changeTo.getDecade();
        if((favDecade>=10 && favDecade<=90 && favDecade%10!=0) || favDecade==0 || favDecade==404){
            return manager.changePrefrences(changeTo);
        }
        return false;
    }
    public boolean validateDeleteAccountRequest() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        return manager.deleteAccount();
    }
    
}