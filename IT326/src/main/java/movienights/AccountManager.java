package main.java.movienights;

import java.sql.SQLException;
import java.util.Scanner;

public class AccountManager{
    Account curAccount;
    Account loginAccount;
    private final QuerySerializer dataBase = new MySQLQuerySerializer();
    private final Scanner keyboard = new Scanner(System.in);

    public AccountManager(){
    }
    public Account editAccount() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        if(this.curAccount!=null){
            Account changeAccount = this.curAccount;
            int selection=6;
            while(selection!=5||selection!=4){
                switch(selection){
                    case 1:
                        System.out.print("New Username: ");
                        changeAccount.username=keyboard.nextLine();
                        break;
                    case 2:
                        System.out.print("New Password: ");
                        changeAccount.password=keyboard.nextLine();
                        break;
                    case 3:
                        System.out.print("New Phone Number: ");
                        changeAccount.phoneNumber=keyboard.nextLine();
                        break;
                    case 4:
                        System.out.println("Changes Saved");
                        this.curAccount=changeAccount;
                        dataBase.update(this.curAccount);
                        return this.curAccount;
                    case 5:
                        System.out.println("Changes Not Saved");
                        return this.curAccount;
                    default:
                        System.out.println("Provide an entry within 1-5");
                }
            System.out.println("1. username\n2. password\n3. Phone Number\4. Save Changes\n 5. Cancel Changes");
            selection=keyboard.nextInt();
            }
        }
        System.out.println("Log in First");
        return null;
    }

    public boolean deleteAccount() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        String confirm;
        String doubleConfirm;
        if(this.curAccount!=null){
            System.out.print("Would you like to go back? (y:n): ");
            confirm=keyboard.nextLine();
            if(confirm.toLowerCase().equals("n")){
                System.out.print("Delete Account? (y:n): ");
                doubleConfirm=keyboard.nextLine();
                if(confirm.toLowerCase().equals("n") && doubleConfirm.toLowerCase().equals("y")){
                    dataBase.delete(curAccount);
                }
            }
        }
        return false;
    }
    public Account createAccount(String username, String password, String phoneNumber, int uid) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        curAccount = new Account(username, phoneNumber, uid, password);
        if(dataBase.hasAlreadyStored(uid)){
            dataBase.save(curAccount);
            return curAccount;
        }
        else{
            System.out.println("User ID in use: create a new one.");
            return null;
        }
    }
    public Account logIn(int uid, String password) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        if(dataBase.hasAlreadyStored(uid)){
            this.loginAccount=dataBase.retrieve(new Account(uid));
            if(loginAccount.password.equals(password)){
                this.curAccount = this.loginAccount;
                return curAccount;
            }
            else{
                System.out.println("Password doesn't match");
            }
        }
        else{
            System.out.println("Username or Password not found.");
        }
        return null;
    }
    public boolean logOut() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        if(this.curAccount!=null && dataBase.hasAlreadyStored(this.curAccount.uid)){
            dataBase.update(this.curAccount);
            return true;
        }
        return false;
    }
}