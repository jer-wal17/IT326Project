package main.java.movienights;

import java.sql.SQLException;

public class AccountManager{
    Account curAccount;
    Account loginAccount;
    private final QuerySerializer dataBase = new MySQLQuerySerializer();

    public AccountManager(){
    }

    public Account createAccount(String username, String password, String phoneNumber, int uid) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        if(dataBase.hasAlreadyStored(uid)){
            this.curAccount = new Account(username, phoneNumber, uid, password);
            dataBase.save(curAccount);
            return this.curAccount;
        }
        else{
            System.out.println("User ID has an Account");
            return null;
        }
    }
    public Account logIn(int uid, String password) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        if(dataBase.hasAlreadyStored(uid)){
            this.loginAccount=dataBase.retrieve(new Account(uid));
            if(loginAccount.password.equals(password)){
                System.out.println("Successfully Logged In");
                this.curAccount = this.loginAccount;
                listGroups();
                return this.curAccount;
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
    public boolean editAccount(Account changeTo) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        if(this.curAccount!=null){
            if(dataBase.hasAlreadyStored(changeTo.uid)){
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
    public boolean changePrefrences(Account changeTo) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        if(this.curAccount!=null){
            if(dataBase.hasAlreadyStored(changeTo.uid)){
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
    public boolean deleteAccount() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        if(this.curAccount!=null){
            if(dataBase.hasAlreadyStored(this.curAccount.uid)){
                dataBase.delete(this.curAccount);
                return true;
            }
            else{
                System.out.println("Cannot Delete Account. UID Changed");
            }
        }
        System.out.println("Log in First");
        return false;
    }
    private void listGroups(){
        if(this.curAccount.group.isEmpty()){
            System.out.println("Here are your current groups");
            for(int i=0; i<this.curAccount.group.size(); i++){
                System.out.println("Group ID: "+this.curAccount.group.get(i).getGroupID()+"\nMovie: "+this.curAccount.group.get(i).getMovie()+"\nMeeting Date: "+this.curAccount.group.get(i).getMeetingDate()+"\nMeeting Address: "+this.curAccount.group.get(i).getMeetingAddress()+"\n");
            }
        }
        else{
            System.out.println("You have not joined any groups.");
        }
    }
}