/**
 * Author: Zachary Kunzer
 * Class: IT326
 * Date: 12/08/2024
 * Controller.java - recieves incoming data from main and redirects the requests to either the AccountHandler or GroupHandler.
 */
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

/**
 * Creates a new account after validation.
 * 
 * @param username the username for the account
 * @param password the password for the account
 * @param phoneNumber the phone number for the account
 * @param UID the unique ID for the account
 * @return true if the account is successfully created, false otherwise
 */
    public boolean createAccount(String username, String password, String phoneNumber, int UID) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        this.currentAccount=accountH.validteCreateAccount(username, password, phoneNumber, UID);
        return this.currentAccount!=null;
    }

/**
 * Logs in to an existing account after validation.
 * 
 * @param uid the unique ID of the account
 * @param password the password for the account
 * @return true if login is successful, false otherwise
 */
    public boolean logIn(int uid, String password) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        this.currentAccount=accountH.validateLoginRequest(uid, password);
        return this.currentAccount!=null;
    }

/**
 * Logs out the currently logged-in account.
 * 
 * @return true if logout is successful, false otherwise
 */
    public boolean logOut() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        return accountH.validateLogoutRequest();
    }

/**
 * Allows the user to edit the current account's information interactively.
 * 
 * @return true if the changes are saved successfully, false otherwise
 */
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

/**
 * Allows the user to change preferences of the current account interactively.
 * 
 * @return true if the changes are saved successfully, false otherwise
 */
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

/**
 * Deletes the currently logged-in account after confirmation.
 * 
 * @return true if the account is successfully deleted, false otherwise
 */
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

/**
 * Creates a new group.
 * 
 * @param group the Group object containing the group details
 * @return true if the group is successfully created, false otherwise
 */
    public boolean createGroup(Group group){
        try {
            return groupH.validateCreateGroupRequest(group);
        }
        catch (Exception e) {
        }
        return false;
    }

/**
 * Joins a group by its group ID.
 * 
 * @param groupID the ID of the group to join
 * @return true if the group is successfully joined, false otherwise
 */
    public boolean joinGroup(int groupID){
        try {
            Group joinGroup = groupH.validateFindGroupRequest(this.currentAccount, groupID);
            if(joinGroup!=null){
                Account changeTo = this.currentAccount;
                changeTo.joinGroup(joinGroup);
                if (accountH.validateEditAccountRequest(changeTo)){
                    this.currentAccount=changeTo;
                    return true;
                }
            return false;
        }
        } 
        catch (Exception e) {
        }
        return false;
    }

/**
 * Leaves a group by its group ID.
 * 
 * @param groupID the ID of the group to leave
 * @return true if the group is successfully left, false otherwise
 */
    public boolean leaveGroup(int groupID){
        try {
            if(groupH.validateLeaveGroupRequest(this.currentAccount, groupID)){
                Account changeTo = this.currentAccount;
                changeTo.removeGroup(groupID);
                if(accountH.validateEditAccountRequest(changeTo)){
                    this.currentAccount=changeTo;
                    return true;
                }
            }
        }
        catch (Exception e) {
        }
        return false;
    }

/**
 * Retrieves the top three movies based on a given title using an external API.
 * 
 * @param title the title of the movie to search for
 * @return true if the movies are successfully retrieved, false otherwise
 */
    public boolean retrieveMovies(String title){
        OMDbAPI myAPI = (OMDbAPI) APIFactory.getAPI(APIFactory.APIType.OMDB);
        return myAPI.retrieveTop3Movies(title);
    }
}