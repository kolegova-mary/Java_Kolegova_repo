package lucky.ticket.task;

import java.util.Scanner;

public class Ticket {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter: ");
        int inputInt = in.nextInt();
        if (isMyTicketLucky(inputInt)) {
            System.out.println("Congratulations, you have a lucky ticket!");
        } else {
            System.out.println("Sorry, try again later");
        }
        in.close();
    }

    public static boolean isMyTicketLucky(int x) {
        if (x <= 999999 && x >= 100000){
            int second = sum(x % 1000);
            int first = sum(x / 1000);
            return first == second;
        }
        else{
            return false;
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
}
