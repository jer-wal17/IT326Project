package main.java.movienights;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupHandler {

    private final GroupManager groupManager;  // Reference to GroupManager
    private final List<Group> availableGroups;  // List to store available groups

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
    public Group validateFindGroupRequest(Account currentAccount, int groupID) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        // Check if there are available groups to join
        if (availableGroups.isEmpty()) {
            System.out.println("No available groups to join.");
            return null;
        }
    
        // Iterate through available groups
        for (Group group : availableGroups) {
            // Check if the group has space and the current account isn't already a member
            if (group.getMembers().size() < group.getMaxSize() && !group.getMembers().contains(currentAccount) &&group.getGroupID()==groupID) {
                // Use GroupManager to add the current account as a member of this group
                 // Assuming GroupManager takes a group as a parameter
                if (groupManager.addMember(currentAccount, groupID)) {
                    System.out.println("Successfully joined the group: " + group.getGroupID());
                    return group;
                } else {
                    System.out.println("Failed to join the group: " + group.getGroupID());
                    return null;
                }
            }
        }
        // If no suitable group was found
        System.out.println("No suitable group found for joining.");
        return null;
    }
    // Validate the request to leave a group
    public boolean validateLeaveGroupRequest(Account currentAccount, int groupID) {
        try {
            if(currentAccount!=null){
            for(Group group : availableGroups){
                if(group.getGroupID()==groupID){
                    group.removeMember(currentAccount);
                    return groupManager.updateGroup(group);
                }
            }
        }
        return false;
        } catch (Exception e) {
            return false;
        }

    }

    // Validate the group creation request
    public boolean validateCreateGroupRequest(Group group) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        // Delegate the group creation task to GroupManager and return the result
        boolean isCreated = groupManager.createGroup(group);
        if (isCreated) {
            availableGroups.add(group);  // Add the newly created group to available groups
        }
        return isCreated;  // Return the result from GroupManager's createGroup method
    }
}
