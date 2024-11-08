public class AccountManager{
    Account curAccount;

    public AccountManager(Account userAccount){
        this.curAccount = userAccount;
    }

    public boolean editAccount(){
        return true;
    }
    public boolean deleteAccount(){
        return true;
    }
    public Account createAccount(String username, String password, int phoneNumber){
        return new Account(username, phoneNumber, 0000);
    }
    public Account logIn(String username, String password){
        return new Account(username, 0000,0000);
    }
    public boolean logOut(){
        return true;
    }
}