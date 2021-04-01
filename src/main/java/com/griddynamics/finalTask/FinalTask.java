package com.griddynamics.finalTask;

import com.griddynamics.finalTask.actions.Action;
import com.griddynamics.finalTask.actions.ActionParser;
import com.griddynamics.finalTask.actions.MenuAction;

import java.util.*;

/**
 * @author mkolegova
 */
public class FinalTask {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Hello!");
        while (true) {
            new MenuAction().execute();
            int actionNumber = in.nextInt();
            /**
             * @see ActionParser#parse(int)
             */
            Action action = new ActionParser().parse(actionNumber);
            action.execute();
        }
    }
}
