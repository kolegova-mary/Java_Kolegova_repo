package com.griddynamics.finalTask;

import com.sun.istack.internal.NotNull;

import java.util.UUID;

public class Goal {
    public String goalId;
    private final String name;
    public String description;
    public boolean isReached;

    /**
     *
     * @param name - name of a goal
     * @param description - provides the main idea of the goal
     * @param reachedIf - is it reached (true/false)
     */
    public Goal(@NotNull String name,@NotNull String description,@NotNull boolean reachedIf) {
        this.goalId = UUID.randomUUID().toString();
        validateGoalName(name);
        this.name = name;
        setDescription(description);
        this.isReached = reachedIf;
    }

    /**
     *
     * @return - generated ID for goal
     */
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

    @Override
    public String toString() {
        return "Goal:" +
                "goalId='" + goalId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isReached=" + isReached + '\'';
    }

    public void setDescription(@NotNull String description) {
        if (!(description.length() >= 10 && description.length() <= 50)) {
            throw new IllegalArgumentException("Description should be no less than 10 and no more than 50.");
        }
        this.description = description;

    }

    public void setReachedIf(@NotNull boolean reachedIf) {
        this.isReached = reachedIf;
    }

    public void setReachedByString(@NotNull String isReached) {
        if (isReached.equalsIgnoreCase("yes")) {
            setReachedIf(true);
        } else if (isReached.equalsIgnoreCase("no")) {
            setReachedIf(false);
        } else {
            throw new IllegalArgumentException("Choose yes or no,please");
        }
    }

    /**
     *
     * @param name - name of a goal
     */
    public static void validateGoalName(String name){
        if (!name.matches("[a-zA-Z0-9 ]{6,20}")) {
            throw new IllegalArgumentException("Goal name should be no less than 6 " +
                    "and no more than 20. It shouldn't contain symbols like: \"!@#$%^&*(){}”|?><:;\"");
        }
    }
}
