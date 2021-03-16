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

    public static Answer isMyTicketLucky(String ticketNumber) {
        if (ticketNumber.matches("\\d{6}")) {
            char[] x = new char[3];
            char[] y = new char[3];
            ticketNumber.getChars(0, 3, x, 0);
            ticketNumber.getChars(3, 6, y, 0);
            int sumX = 0;
            int sumY = 0;
            for (int i = 0; i < 3; i++) {
                sumX += x[i];
                sumY += y[i];
            }
            if (sumX == sumY) {
                System.out.println("Congratulations! You have a lucky ticket!");
                return Answer.LUCKY;
            } else {
                System.out.println("Sorry, try again later.");
                return Answer.NOT_LUCKY;
            }
        } else {
            System.out.println("Give correct ticket, please.");
            return Answer.INVALID_TICKET;
        }
    }

    public static int sum(int x) {
        int sum = 0;
        while (x != 0) {
            sum += x % 10;
            x /= 10;
        }
        return sum;
    }

    public enum Answer {
        LUCKY,
        NOT_LUCKY,
        INVALID_TICKET
    }
}
