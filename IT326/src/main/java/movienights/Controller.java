package main.java.movienights;

import java.sql.SQLException;
import java.util.Scanner;
public class Controller{
    public final AccountHandler accountH = new AccountHandler();
    public final GroupHandler groupH = new GroupHandler();
    private final Scanner keyboard = new Scanner(System.in);
    public Account currentAccount;

    public Controller(){
    }
    public boolean createAccount(String username, String password, String phoneNumber, int UID) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        this.currentAccount=accountH.validteCreateAccount(username, password, phoneNumber, UID);
        return this.currentAccount!=null;
    }
    public boolean logIn(int uid, String password) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        this.currentAccount=accountH.validateLoginRequest(uid, password);
        return this.currentAccount!=null;
    }
    public boolean logOut() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        return accountH.validateLogoutRequest();
    }
    public boolean editAccount() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        if(this.currentAccount!=null){
            Account changeAccount = this.currentAccount;
            int selection = 10;
            while(selection!=8||selection!=9){
                switch(selection){
                    case 1: //Change Account information
                        this.currentAccount=accountH.validateEditAccountRequest();
                        break;
                    case 2: //Change Prefrences
                        System.out.print("New Password: ");
                        changeAccount.password=keyboard.nextLine();
                        break;
                    case 3: //Delete Account
                        System.out.print("New Phone Number: ");
                        changeAccount.phoneNumber=keyboard.nextLine();
                        break;
                    case 4: //Exit
                        System.out.println("Changes Saved");
                        this.curAccount=changeAccount;
                        dataBase.update(this.curAccount);
                        return this.curAccount;
                    default:
                        System.out.println("Provide an entry within 1-4");
                }
            }
        }
        Account testNotNull=accountH.validateEditAccountRequest();
        if(testNotNull!=null){
            this.currentAccount=testNotNull;
        }
        else{
            this.currentAccount=null;
        }
        return this.currentAccount!=null;
    }
    public boolean joinGroup(){
        return groupH.validateFindGroupRequest(this.currentAccount);
    }
    public boolean leaveGroup(){
        return groupH.validateLeaveGroupRequest();
    }
    public boolean retrieveMovies(String title){
        OMDbAPI myAPI = (OMDbAPI) APIFactory.getAPI(APIFactory.APIType.OMDB);
        return myAPI.retrieveTop3Movies(title);
    }
}