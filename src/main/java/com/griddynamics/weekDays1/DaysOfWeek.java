package com.griddynamics.weekDays1;

import java.util.Scanner;

public class DaysOfWeek {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int day = in.nextInt();
        chooseNoticeForDay(lookForDay(day));
    }

    public static WeeksDays lookForDay(int x) {
        switch (x) {
            case 1:
                return WeeksDays.MONDAY;
            case 2:
                return WeeksDays.TUESDAY;
            case 3:
                return WeeksDays.WEDNESDAY;
            case 4:
                return WeeksDays.THURSDAY;
            case 5:
                return WeeksDays.FRIDAY;
            case 6:
                return WeeksDays.SATURDAY;
            case 7:
                return WeeksDays.SUNDAY;
            default:
                throw new IllegalArgumentException("Sorry, we don't have such number of day.");
        }
    }

    public static WeeksDays chooseNoticeForDay(WeeksDays x) {
        switch (x) {
            case MONDAY:
                System.out.println("Have a nice week! Today is Monday!");
                return WeeksDays.MONDAY;
            case TUESDAY:
                System.out.println("Have a good time on Tuesday!");
                return WeeksDays.TUESDAY;
            case WEDNESDAY:
                System.out.println("Have a nice time on Wednesday!");
                return WeeksDays.WEDNESDAY;
            case THURSDAY:
                System.out.println("Have fun on Thursday!");
                return WeeksDays.THURSDAY;
            case FRIDAY:
                System.out.println("Happy Friday!");
                return WeeksDays.FRIDAY;
            case SATURDAY:
                System.out.println("Have a rest on Saturday!");
                return WeeksDays.SATURDAY;
            case SUNDAY:
                System.out.println("Prepare for a new week! Today is Sunday!");
                return WeeksDays.SUNDAY;
            default:
                throw new IllegalArgumentException("Impossible");
        }
    }

    public enum WeeksDays {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY

    }
}
