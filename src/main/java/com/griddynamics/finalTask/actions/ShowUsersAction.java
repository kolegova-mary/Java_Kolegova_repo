package com.griddynamics.finalTask.actions;

import com.griddynamics.finalTask.Manager;

public class ShowUsersAction implements Action{
    @Override
    public void execute(){
        Manager.writeUsers();
    }
}
