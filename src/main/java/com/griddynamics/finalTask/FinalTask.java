package com.griddynamics.finalTask;

import com.griddynamics.finalTask.actions.Action;
import com.griddynamics.finalTask.actions.ActionParser;
import com.griddynamics.finalTask.actions.MenuAction;

import java.util.*;

public class FinalTask {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Hello!");
        while (true) {
            new MenuAction().execute();
            int actionNumber = in.nextInt();
            Action action = new ActionParser().parse(actionNumber);
            action.execute();
        }
    }
}
