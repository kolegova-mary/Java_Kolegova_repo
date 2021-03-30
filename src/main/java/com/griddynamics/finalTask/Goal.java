package com.griddynamics.finalTask;

import java.util.UUID;

public class Goal {
    public String goalId;
    private final String name;
    public String description;
    public boolean isReached;

    public Goal(String name, String description, boolean reachedIf) {
        this.goalId = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.isReached = reachedIf;
    }



    public String getGoalId() {
        return goalId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isReachedIf() {
        return isReached;
    }

    public void setGoalId(String goalId) {
        this.goalId = goalId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReachedIf(boolean reachedIf) {
        this.isReached = reachedIf;
    }


    public void setReachedByString(String isReached){
        if (isReached.equalsIgnoreCase("yes")){
            setReachedIf(true);
        } else if (isReached.equalsIgnoreCase("no")){
            setReachedIf(false);
        }else{
            throw new IllegalArgumentException("Choose yes or no,please");
        }
    }
}
