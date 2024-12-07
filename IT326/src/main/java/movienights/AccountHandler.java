package main.java.movienights;

import java.sql.SQLException;

public class AccountHandler{
    private final AccountManager manager = new AccountManager();
    public AccountHandler(){

    }

    public Account validteCreateAccount(String username, String password, String phoneNumber, int uid) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        //Validate data given by user here
        return manager.createAccount(username, password, phoneNumber, uid);
    }
    public Account validateLoginRequest(int uid, String password)  throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        //Validate data given by user here
        return manager.logIn(uid, password);
    }
    public boolean validateLogoutRequest() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        return manager.logOut();
    }
    public boolean validateEditAccountRequest(Account changeTo) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        //Validate data given by user here
        return true;
    }
    public boolean validateChangePrefrencesRequest() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        //Validate data given by user here
        return true;
    }
    public boolean validateDeleteAccountRequest() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        return manager.deleteAccount();
    }
    
}