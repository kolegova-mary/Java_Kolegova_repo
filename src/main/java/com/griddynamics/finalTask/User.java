package com.griddynamics.finalTask;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class User {
    public String userId;
    private final String firstName;
    private final String lastName;
    public String nickName;
    public String phoneNumber;
    List<Goal> goals;

    public User( String firstName, String lastName, String nickName, String phoneNumber) {
        this(firstName, lastName, nickName, phoneNumber, new ArrayList<>());
    }

    public User(String firstName, String lastName, String nickName, String phoneNumber, List<Goal> goals) {
        this.userId =  UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.goals = goals;
    }

    public Goal findGoal(String goalName) throws NoSuchElementException {
        return goals.stream()
                .filter(goal -> goal.getName().equalsIgnoreCase(goalName))
                .findAny().get();
    }


    public void addGoal(Goal goal){
        goals.add(goal);
    }

    public String getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }
}
