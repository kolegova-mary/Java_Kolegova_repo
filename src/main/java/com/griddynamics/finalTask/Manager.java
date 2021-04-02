package com.griddynamics.finalTask;

import java.util.*;

public class Manager {
    private static List<User> users = new ArrayList<>();

    public static void addUser(User user) {
        users.add(user);
    }

    /**
     *
     * @param firstName - first name of the user, that is wanted to be found
     * @param lastName - last name of the user, that is wanted to be found
     * @return Object User, if such exists
     * @throws NoSuchElementException - there is no such user
     */
    public static User findUser(String firstName, String lastName) throws NoSuchElementException {
        return users.stream()
                .filter(user -> user.getFirstName().equalsIgnoreCase(firstName) && user.getLastName().equalsIgnoreCase(lastName))
                .findAny().get();
    }

    public static void writeUsers() {
        if (users != null && users.isEmpty()) {
            System.out.println("There are no users");
        } else {
            for (User user : users) {
                System.out.println(user);
            }
        }
    }

}
