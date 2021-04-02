package com.griddynamics.finalTask.actions;

import com.griddynamics.finalTask.Manager;

import java.util.Scanner;

public class MenuAction implements Action {

    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("Write number of action, which you'd like to do, please:\n" +
                "1) Edit user's phone\n" +
                "2) Edit user's nickname\n" +
                "3) Add a goal\n" +
                "4) Edit the goal\n" +
                "5) Set reached/not reached\n" +
                "6) Add a user\n" +
                "7) Show all users\n" +
                "8) Show all goals of the user\n" +
                "9) Exit");
    }
}
