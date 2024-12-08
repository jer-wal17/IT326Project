package main.java.movienights;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Group {
    private int groupID;
    private Movie movie;
    private String meetingAddress;
    private LocalDate meetingDate;
    private Account owner; // Owner of the group
    private int maxSize; // Maximum size of the group
    private List<Account> members = new ArrayList<>(); // Members of the group
  
  public Group(int groupID, Movie movie, String meetingAddress, LocalDate meetingDate, Account owner, int maxSize) {
        this.groupID = groupID;
        this.movie = movie;
        this.meetingAddress = meetingAddress;
        this.meetingDate = meetingDate;
        this.owner = owner;
        this.maxSize = maxSize;
        this.members = new ArrayList<>();
        this.members.add(owner); // Add the owner as the first member
    }
    // Method to add a member
    public boolean addMember(Account member) {
        if (members == null) {
            members = new ArrayList<>(); // Initialize the list if null
        }
        if (members.contains(member)) {
            return false; // Member already exists
        }
        if (maxSize > 0 && members.size() >= maxSize) {
            return false; // Group is at maximum capacity
        }
        members.add(member);
        return true; // Member added successfully
    }
    public boolean removeMember(Account member){
        return members.removeIf(account -> account.getUID() == member.getUID());
    }
// Getters and setters
public int getGroupID() {
    return groupID;
}

public void setGroupID(int groupID) {
    this.groupID = groupID;
}

public Movie getMovie() {
    return movie;
}

public void setMovie(Movie movie) {
    this.movie = movie;
}

public String getMeetingAddress() {
    return meetingAddress;
}

public void setMeetingAddress(String meetingAddress) {
    this.meetingAddress = meetingAddress;
}

public LocalDate getMeetingDate() {
    return meetingDate;
}

public void setMeetingDate(LocalDate meetingDate) {
    this.meetingDate = meetingDate;
}

public Account getOwner() {
    return owner;
}

public void setOwner(Account owner) {
    this.owner = owner;
}

public int getMaxSize() {
    return maxSize;
}

public void setMaxSize(int maxSize) {
    this.maxSize = maxSize;
}

public List<Account> getMembers() {
    return members;
}
    }


  

