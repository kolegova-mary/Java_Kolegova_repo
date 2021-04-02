package com.griddynamics.finalTask.actions;

import com.griddynamics.finalTask.Manager;
import com.griddynamics.finalTask.User;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class EditPhoneAction implements Action {
    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("Write first name of the user:");
        String firstName = in.nextLine();
        System.out.println("Write last name of the user:");
        String lastName = in.nextLine();
        User user = null;
        try {
            user = Manager.findUser(firstName, lastName);
        } catch (NoSuchElementException e) {
            System.out.println("User not found");
        }
        System.out.println("Write new phone:");
        String phone = in.nextLine();
        user.setPhoneNumber(phone);
    }
}
