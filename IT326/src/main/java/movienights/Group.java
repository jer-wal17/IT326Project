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
    private List<Account> members; // Members of the group
  
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


  

