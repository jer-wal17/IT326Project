import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller();
        boolean flag= true;

        System.out.println("Welcome to Movie Night");
        while (flag) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Log In");
            System.out.println("2. Log Out");
            System.out.println("3. Join a Group");
            System.out.println("4. Edit Account");
            System.out.println("5. Leave a Group");
            System.out.println("6. Retrieve Movies");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: {
                    if (controller.logIn()) {
                        System.out.println("Login successful!");
                    } else {
                        System.out.println("Login failed. Please try again.");
                    }
                }
                case 2 : {
                    if (controller.logOut()) {
                        System.out.println("Logged out successfully.");
                    } else {
                        System.out.println("No active session to log out from.");
                    }
                }
                case 3: {
                    if (controller.joinGroup()) {
                        System.out.println("Joined the group successfully.");
                    } else {
                        System.out.println("Failed to join the group. Ensure you're logged in and the group exists.");
                    }
                }
                case 4: {
                    if (controller.editAccount()) {
                        System.out.println("Account updated successfully.");
                    } else {
                        System.out.println("Failed to update account. Ensure you're logged in.");
                    }
                }
                case 5: {
                    if (controller.leaveGroup()) {
                        System.out.println("Left the group successfully.");
                    } else {
                        System.out.println("Failed to leave the group. Ensure you're logged in and part of the group.");
                    }
                }
                case 6: {
                    System.out.print("Enter movie title to search: ");
                    String title = scanner.nextLine();
                    if (!controller.retrieveMovies(title)) {
                        System.out.println("Error with printing movies. Please see error above.");
                    }
                    break;
                }
                case 7: {
                    System.out.println("Exiting the system. Goodbye!");
                   flag = false;
                }
                default: System.out.println("Invalid choice. Please select a valid option.");
            }
        }
        scanner.close();
    }
}
