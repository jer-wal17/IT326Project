import java.sql.SQLException;

public class AccountHandler{
    private final AccountManager manager = new AccountManager();
    public AccountHandler(){

    }
    public Account validateLoginRequest(int uid, String password)  throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        return manager.logIn(uid, password);
    }
    public boolean validateLogoutRequest() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        return manager.logOut();
    }
    public Account validateEditAccountRequest() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        return manager.editAccount();
    }
    public Account validteCreateAccount(String username, String password, String phoneNumber, int uid) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        return manager.createAccount(username, password, phoneNumber, uid);
    }
    
}