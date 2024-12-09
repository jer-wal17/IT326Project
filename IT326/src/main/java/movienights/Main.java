package main.java.movienights;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller();
        boolean flag = true;

        System.out.println("Welcome to Movie Night");

        // Main menu loop
        while (flag) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Log In");// we assume they already have an account
            System.out.println("2. Create Account");// we assume that they do not have an account
            System.out.println("3. Exit"); 
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: { // Log In
                    System.out.print("Enter UID: ");
                    int uid = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Password: ");
                    String password = scanner.nextLine();
                    try {
                        if (controller.logIn(uid, password)) {
                            System.out.println("Login successful!");

                            // Show logged-in menu
                            loggedInMenu(scanner, controller);

                        } else {
                            System.out.println("Login failed. Please try again.");
                        }
                    } catch (Exception e) {
                        System.out.println("Error during login: " + e.getMessage());
                    }
                    break;
                }
                case 2: { // Create Account
                    System.out.println("\nCreate Account Menu:");
                
                    // Ask for the required information to create an account
                    System.out.print("Enter a Username: ");
                    String username = scanner.nextLine();
                
                    System.out.print("Enter a Password: ");
                    String password = scanner.nextLine();
                
                    System.out.print("Enter a Phone Number: ");
                    String phoneNumber = scanner.nextLine();
                
                    System.out.print("Enter a UID (Unique ID): ");
                    int uid = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                
                    try {
                        // Call the createAccount method in the Controller class
                        if (controller.createAccount(username, password, phoneNumber, uid)) {
                            System.out.println("Account created successfully!");
                        } else {
                            System.out.println("Account creation failed. Please try again.");
                        }
                    } catch (Exception e) {
                        System.out.println("Error during account creation: " + e.getMessage());
                    }
                    break;
                }
                case 3: { // Exit
                    System.out.println("Exiting the system. Goodbye!");
                    flag = false;
                    break;
                }
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
        scanner.close();
    }
// Private helper method loggedInMenu that will further display what the user will be interested on doing nce they log in
    private static void loggedInMenu(Scanner scanner, Controller controller) {
    boolean loggedIn = true;
    Account currentAccount = new Account();

    while (loggedIn) {
        System.out.println("\nLogged In Menu:");
        System.out.println("1. Log Out");
        System.out.println("2. Edit Account");
        System.out.println("3. Create Group");
        System.out.println("4. Join Group");
        System.out.println("5. Find Group");
        System.out.println("6. Leave Group");
        System.out.println("7. Retrieve Movie");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1: { // Log Out
                try {
                    if (controller.logOut()) {
                        System.out.println("Logged out successfully.");
                        loggedIn = false; // Exit logged-in menu
                    } else {
                        System.out.println("No active session to log out from.");
                    }
                }
                catch (Exception e) {
                    System.out.println("Error Logging Out");
                }
                break;
            }
            case 2: { // Edit Information
                editInformation(scanner, controller);
                break;
            }
            
            // Case 3: Create Group
            case 3: { 
                System.out.println("Creating a new group...");
            
                try {
                    // Prompt for user input for group details
                    System.out.print("Enter the movie name: ");
                    String movieName = scanner.nextLine();
            
                    System.out.print("Enter the meeting address: ");
                    String meetingAddress = scanner.nextLine();
            
                    System.out.print("Enter the meeting date (YYYY-MM-DD): ");
                    String dateInput = scanner.nextLine();
                    LocalDate meetingDate = LocalDate.parse(dateInput); // Parse input to LocalDate
            
                    System.out.print("Enter the maximum size of the group: ");
                    int maxSize = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
            
                    System.out.print("Enter a unique Group ID: ");
                    int groupID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
            
                    // Create a `Movie` and `Group` object
                    Movie movie = new Movie(movieName); // Assuming a simple constructor in the `Movie` class
                    Group newGroup = new Group(groupID, movie, meetingAddress, meetingDate, currentAccount, maxSize);
            
                    // Use the controller to validate and create the group
                    if (controller.createGroup(newGroup)) {
                        System.out.println("Group created successfully!");
                    } else {
                        System.out.println("Group creation failed. A group with this ID may already exist.");
                    }
                } catch (Exception e) {
                    System.out.println("Error during group creation: " + e.getMessage());
                }
                break;
            }
            case 4: { // Join Group
                controller.groupH.listAvailableGroups();  // Show available groups
                System.out.print("Enter the Group ID to join: ");
                int groupId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (controller.joinGroup(groupId)) {
                    System.out.println("Joined the group successfully.");
                } else {
                    System.out.println("Failed to join the group.");
                }
                break;
            }
            case 5: { // Find Group
                System.out.println("Finding Group...");
                // List all groups 
                controller.groupH.listAvailableGroups();  // Show available groups
                break;
            }
            case 6: { // Leave Group
                System.out.println("Which group to leave: ");
                int groupId = scanner.nextInt();
                if (controller.leaveGroup(groupId)) {
                    System.out.println("Left the group successfully.");
                } else {
                    System.out.println("Failed to leave the group. Ensure you're a member of a group.");
                }
                break;
            }case 7: // Retrieve Movies
            System.out.print("Enter the movie title to search: ");
            String title = scanner.nextLine();
            boolean success = controller.retrieveMovies(title);
            if (success) {
                System.out.println("Movies retrieved successfully!");
            } else {
                System.out.println("No movies found for the given title.");
            }
            break;
            default:
                System.out.println("Invalid choice. Please select a valid option.");
        }
    }
}

// creating a private helper method editInformation 
private static void editInformation(Scanner scanner, Controller controller) {
    System.out.println("\nEdit Information Menu:");
    System.out.println("1. Edit Account Information");
    System.out.println("2. Edit Preferences");
    System.out.println("3. Delete Account");
    System.out.print("Enter your choice: ");
    int choice = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    switch (choice) {
        case 1: { // Edit Account Information
            try {
                System.out.println("Editing account information...");
                if (controller.editAccount()) {
                    System.out.println("Account information updated successfully.");
                } else {
                    System.out.println("Failed to update account information.");
                }
            }
            catch (Exception e) {
                System.out.println("Error updating account information");
            }
            break;
        }
        case 2: { // Edit Preferences
            try {
                System.out.println("Editing preferences...");
                if (controller.changePrefrences()) {
                    System.out.println("Preferences updated successfully.");
                } else {
                    System.out.println("Failed to update preferences.");
                }
            }
            catch (Exception e) {
                System.out.println("Failed to update preferences");
            }
            break;
        }
        case 3: { // Delete Account
            try {
                System.out.print("Are you sure you want to delete your account? (yes/no): ");
                String confirmation = scanner.nextLine();
                if (confirmation.equalsIgnoreCase("yes")) {
                    if (controller.deleteAccount()) {
                        System.out.println("Account deleted successfully.");
                    } else {
                        System.out.println("Failed to delete account.");
                    }
                } else {
                    System.out.println("Account deletion canceled.");
                }
            }
            catch (Exception e) {
                System.out.println("Failed deleting account");
            }
            break;
        }
        default:
            System.out.println("Invalid choice. Please select a valid option.");
    }
}


    }

