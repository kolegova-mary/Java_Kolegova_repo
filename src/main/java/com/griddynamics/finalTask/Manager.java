package com.griddynamics.finalTask;

import java.util.*;

public class Manager {
    private static List<User> users= new ArrayList<>();

    public static void addUser(User user) {
        users.add(user);
    }

    public static User findUser(String firstName, String lastName) throws NoSuchElementException {
        return users.stream()
                .filter(user -> user.getFirstName().equalsIgnoreCase(firstName) && user.getLastName().equalsIgnoreCase(lastName))
                .findAny().get();
    }

}
