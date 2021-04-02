package com.griddynamics.finalTask.actions;

public class ActionParser {
    /**
     *
     * @param actionNum - number of action from 1 to 9
     * @return - executing of chosen action
     */
    public Action parse(int actionNum) {
        Action action;
        switch (actionNum) {
            case 1:
                action = new EditPhoneAction();
                break;
            case 2:
                action = new EditNicknameAction();
                break;
            case 3:
                action = new AddGoalAction();
                break;
            case 4:
                action = new EditDescription();
                break;
            case 5:
                action = new SetIfReached();
                break;
            case 6:
                action = new AddUserAction();
                break;
            case 7:
                action = new ShowUsersAction();
                break;
            case 8:
                action = new ShowGoalsAction();
                break;
            case 9:
                action=new ExitAction();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + actionNum);
        }
        return action;
    }

}
