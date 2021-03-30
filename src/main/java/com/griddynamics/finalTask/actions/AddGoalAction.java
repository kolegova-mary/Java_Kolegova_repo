package com.griddynamics.finalTask.actions;

import com.griddynamics.finalTask.Goal;
import com.griddynamics.finalTask.Manager;
import com.griddynamics.finalTask.User;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.UUID;

public class AddGoalAction implements Action {
    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("Write name of the goal:");
        String name = in.nextLine();
        System.out.println("Write description of the goal:");
        String description = in.nextLine();//как читать больше строки
        System.out.println("Is it reached? Write yes or no, please:");
        String isReached = in.nextLine();
        System.out.println("Write first name, who has/had this goal?");
        String firstName = in.nextLine();
        System.out.println("Write last name, who has/had this goal?");
        String lastName = in.nextLine();
        try {
            Goal goal = new Goal(name,description,false);
            goal.setReachedByString(isReached);
            Manager.findUser(firstName, lastName).addGoal(goal);
        } catch (NoSuchElementException e) {
            System.out.println("User not found.");
        }
    }
}
