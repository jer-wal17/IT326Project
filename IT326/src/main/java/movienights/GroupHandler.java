import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupHandler {

    private final GroupManager groupManager;  // Reference to GroupManager
    private List<Group> availableGroups;  // List to store available groups

    // Constructor
    public GroupHandler() {
        this.groupManager = new GroupManager();  // Initialize GroupManager
        this.availableGroups = new ArrayList<>();  // Initialize the list of available groups
    }

    // Method to list available groups
    public void listAvailableGroups() {
        if (availableGroups.isEmpty()) {
            System.out.println("No available groups to join.");
        } else {
            System.out.println("Available Groups:");
            for (Group group : availableGroups) {
                System.out.println("Group ID: " + group.getGroupID() +
                                   ", Movie: " + group.getMovie().getMovieName() +
                                   ", Meeting Address: " + group.getMeetingAddress() +
                                   ", Meeting Date: " + group.getMeetingDate());
            }
        }
    }
    
    // Validate if the current account can join a group
    public boolean validateFindGroupRequest(Account currentAccount) {
        // Check if there are available groups to join
        if (availableGroups.isEmpty()) {
            System.out.println("No available groups to join.");
            return false;
        }

        // Example check: ensure that the current account is not already a member of the group
        for (Group group : availableGroups) {
            if (group.getMembers().size() < group.getMaxSize() && !group.getMembers().contains(currentAccount)) {
                // If group has space and the current account isn't already a member, allow joining
                return true;
            }
        }

        // No available group to join
        System.out.println("No groups with available spots.");
        return false;
    }

    // Validate the request to leave a group
    public boolean validateLeaveGroupRequest() {
        return true;  // always valid to leave a group
    }

    // Validate the group creation request
    public boolean validateCreateGroup(Group group) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        // Delegate the group creation task to GroupManager and return the result
        boolean isCreated = groupManager.createGroup(group);
        if (isCreated) {
            availableGroups.add(group);  // Add the newly created group to available groups
        }
        return isCreated;  // Return the result from GroupManager's createGroup method
    }
}
