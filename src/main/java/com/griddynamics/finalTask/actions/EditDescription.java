package com.griddynamics.finalTask.actions;

import com.griddynamics.finalTask.Goal;
import com.griddynamics.finalTask.Manager;
import com.griddynamics.finalTask.User;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class EditDescription implements Action {
    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("Write name of the goal:");
        String name = in.nextLine();
        System.out.println("Write first name, who has/had this goal?");
        String firstName = in.nextLine();
        System.out.println("Write last name, who has/had this goal?");
        String lastName = in.nextLine();
        User user = null;
        Goal goal = null;
        try {
            user = Manager.findUser(firstName, lastName);
        } catch (NoSuchElementException e) {
            System.out.println("User not found.");
        }
        try {
            goal = user.findGoal(name);
        } catch (NoSuchElementException e) {
            System.out.println("Goal not found.");
        }
        System.out.println("Write new description:");
        String description = in.nextLine();
        goal.setDescription(description);

    }
}
