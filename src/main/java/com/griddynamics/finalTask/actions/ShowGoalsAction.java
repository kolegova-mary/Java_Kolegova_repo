package com.griddynamics.finalTask.actions;

import com.griddynamics.finalTask.Manager;
import com.griddynamics.finalTask.User;

import java.util.Scanner;

public class ShowGoalsAction implements Action {
    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("Write first name of the user:");
        String firstName = in.nextLine();
        System.out.println("Write last name of the user:");
        String lastName = in.nextLine();
        Manager.findUser(firstName, lastName).writeGoals();
    }
}
