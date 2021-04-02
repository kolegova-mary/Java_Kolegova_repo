package com.griddynamics.finalTask;

import com.sun.istack.internal.NotNull;

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

    public User(@NotNull String firstName, @NotNull String lastName, @NotNull String nickName, @NotNull String phoneNumber) {
        this(firstName, lastName, nickName, phoneNumber, new ArrayList<>());
    }

    /**
     * @param firstName - first name couldn't be edited
     * @param lastName  - last name couldn't be edited
     * @param nickName  - could be changed
     * @param goals - list of goals for one user
     */
    public User(@NotNull String firstName, @NotNull String lastName, @NotNull String nickName, @NotNull String phoneNumber, List<Goal> goals) {
        this.userId = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.goals = goals;
    }

    /**
     *
     * @param goalName - name of a goal
     * @return - Object Goal
     * @throws NoSuchElementException - there is no such goal of user
     */
    public Goal findGoal(String goalName) throws NoSuchElementException {
        return goals.stream()
                .filter(goal -> goal.getName().equalsIgnoreCase(goalName))
                .findAny().get();
    }

    public void writeGoals() {
        if (goals != null && goals.isEmpty()) {
            System.out.println("There are no goals");
        } else {
            for (Goal goal : goals) {
                System.out.println(goal);
            }
        }
    }

    /**
     *
     * @param goal - name of a goal, that needs to be added
     */
    public void addGoal(Goal goal) {
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

    public void setNickName(@NotNull String nickName) {
        this.nickName = nickName;
    }

    public void setPhoneNumber(@NotNull String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setGoals(@NotNull List<Goal> goals) {
        this.goals = goals;
    }

    @Override
    public String toString() {
        return "User:" +
                "userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'';
    }


}
