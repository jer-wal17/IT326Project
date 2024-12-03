import java.util.Scanner;

public class Main {
    private static Controller controller;

    public static void main(String[] args) {
        // Initialize the application
        GroupManager groupManager = new GroupManager();
        controller = new Controller(groupManager);
        Scanner scanner = new Scanner(System.in);

        // Simulate application logic
        System.out.println("Welcome to Movie Night!");

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Log In");
            System.out.println("2. Find a Group");
            System.out.println("3. Edit Account");
            System.out.println("4. Leave Group");
            System.out.println("5. Log Out");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    logIn(scanner);
                    break;
                case 2:
                    findGroup(scanner);
                    break;
                case 3:
                    editAccount(scanner);
                    break;
                case 4:
                    leaveGroup(scanner);
                    break;
                case 5:
                    logOut();
                    break;
                case 6:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void logIn(Scanner scanner) {
        // collecting log in details from user
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Verify login (dummy logic here, should call AccountManager in a real app)
        if ("admin".equals(username) && "password".equals(password)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid credentials. Try again.");
        }
    }

    public static void logOut() {
        System.out.println("You have been logged out.");
    }

    public static void findGroup(Scanner scanner) {
        System.out.print("Enter group ID to search for: ");
        int groupID = scanner.nextInt();

        Group group = controller.retrieveGroup(groupID); // Call Controller
        if (group != null) {
            System.out.println("Group found:");
            System.out.println("Movie: " + group.getMovie());
            System.out.println("Meeting Address: " + group.getMeetingAddress());
            System.out.println("Meeting Date: " + group.getMeetingDate());
        } else {
            System.out.println("Group not found.");
        }
    }

    public static void editAccount(Scanner scanner) {
        // Simulate editing account
        System.out.print("Enter new username: ");
        String newUsername = scanner.nextLine();

        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();

        // Simulate updating account (would call an AccountManager in a real app)
        System.out.println("Account updated successfully!");
    }

    public static void leaveGroup(Scanner scanner) {
        System.out.print("Enter group ID to leave: ");
        int groupID = scanner.nextInt();

        // Simulate group leave process (dummy logic for now)
        boolean left = controller.leaveGroup(groupID); // Call Controller
        if (left) {
            System.out.println("You have successfully left the group.");
        } else {
            System.out.println("Failed to leave the group.");
        }
    }
}


