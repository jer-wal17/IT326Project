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
            int selection;
            String changeInfo;
            System.out.println("--Edit Account Inforamtion--\n1) Change Username\n2) Change Password\n3) Change Phone Number\n4) Save Changes\n5) Cancel Changes\nSelection (1-5): ");
            selection=keyboard.nextInt();
            while(selection!=4||selection!=5){
                switch(selection){
                    case 1: //Change Username
                        System.out.println("New Username: ");
                        changeInfo = keyboard.nextLine();
                        changeAccount.setUsername(changeInfo);
                        break;
                    case 2: //Change Password
                        System.out.print("New Password: ");
                        changeInfo = keyboard.nextLine();
                        changeAccount.setPassword(changeInfo);
                        break;
                    case 3: //Change Phone Number
                        System.out.print("New Phone Number: ");
                        changeInfo = keyboard.nextLine();
                        changeAccount.setPhoneNumber(changeInfo);
                        break;
                    case 4: //Save & Exit
                        if(accountH.validateEditAccountRequest(changeAccount)){
                            System.out.println("Changes Saved!");
                            this.currentAccount=changeAccount;
                            return true;
                        }
                        else{
                            System.out.println("Problem Saving Account. Reverting to Original");
                            return false;
                        }
                    case 5:
                        System.out.println("Changes not Saved");
                        return true;
                    default:
                        System.out.println("Provide an entry within 1-5");
                }
            }
        }
        return false;
    }
    public boolean changePrefrences() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        if(this.currentAccount!=null){
            int favDecade;
            Account changeAccount = this.currentAccount;
            int selection;
            System.out.println("--Edit Prefrences--\n1) Add Prefered Decade\n2) Change Perfered Decade\n3) Change Remove Perfered Decade\n4) Save Changes\n5) Cancel Changes\nSelection (1-5): ");
            selection=keyboard.nextInt();
            while(selection!=4||selection!=5){
                switch(selection){
                    case 1: //Add Prefered Decade
                        System.out.println("Add Favorite Decade");
                        favDecade= keyboard.nextInt();
                        changeAccount.setPerferedDecade(favDecade);
                        break;
                    case 2: //Change Perfered Decade
                        if(currentAccount.getDecade()==404){
                            System.out.println("Set a Favorite Decade Before Changing it");
                        }
                        else{
                            System.out.print("New Favorite Decade: ");
                            favDecade= keyboard.nextInt();
                            changeAccount.setPerferedDecade(favDecade);
                        }
                        break;
                    case 3: //Change Delete Perfered Decade
                        System.out.println("Perfered Decade Removed");
                        changeAccount.setPerferedDecade(404);
                        break;
                    case 4: //Save & Exit
                        if(accountH.validateEditAccountRequest(changeAccount)){
                            System.out.println("Changes Saved!");
                            this.currentAccount=changeAccount;
                            return true;
                        }
                        else{
                            System.out.println("Problem Saving Account. Reverting to Original");
                            return false;
                        }
                    case 5:
                        System.out.println("Changes not Saved");
                        return true;
                    default:
                        System.out.println("Provide an entry within 1-5");
                }
            }
        }
        return false;
    }
    public boolean deleteAccount() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        String confirm;
        String doubleConfirm;
        if(this.currentAccount!=null){
            System.out.println("Would you like to go back? (y:n): ");
            confirm=keyboard.nextLine();
            if(confirm.toLowerCase().equals("n")){
                System.out.print("Delete Account? (y:n): ");
                doubleConfirm=keyboard.nextLine();
                if(confirm.toLowerCase().equals("n") && doubleConfirm.toLowerCase().equals("y")){
                    return accountH.validateDeleteAccountRequest();
                }
            }
        }
        return false;
    }
    public boolean createGroup(Group group){
        return groupH.validateCreateGroupRequest(group);
    }
    public boolean joinGroup(int groupID){
        return groupH.validateFindGroupRequest(this.currentAccount, groupID);
    }
    public boolean leaveGroup(){
        return groupH.validateLeaveGroupRequest(this.currentAccount);
    }
    public boolean retrieveMovies(String title){
        OMDbAPI myAPI = (OMDbAPI) APIFactory.getAPI(APIFactory.APIType.OMDB);
        return myAPI.retrieveTop3Movies(title);
    }
}