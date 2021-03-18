package com.griddynamics.main;

import java.util.Scanner;

public class Ticket {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter: ");
        String ticket = in.nextLine();
        isMyTicketLucky(ticket);
        in.close();
    }

    public static boolean isMyTicketLucky(String ticketNumber) {
        if (ticketNumber.matches("\\d{6}")) {
            char[] x = new char[3];
            char[] y = new char[3];
            ticketNumber.getChars(0, 3, x, 0);
            ticketNumber.getChars(3, 6, y, 0);
            int sumX = sum(x);
            int sumY = sum(y);
            if (sumX == sumY) {
                System.out.println("Congratulations! You have a lucky ticket!");
                return true;
            } else {
                System.out.println("Sorry, try again later.");
                return false;
            }
        } else {
            throw new IllegalArgumentException("Give correct ticket, please.");
        }
    }


    public static int sum(char[] ticketHalf) {
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            sum += ticketHalf[i];
        }
        return sum;
    }
}
