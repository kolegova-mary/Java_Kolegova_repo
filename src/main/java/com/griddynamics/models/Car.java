package com.griddynamics.models;


public class Car extends Vehicle {
    protected String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Car(String model, String producer, int age, String type) {
        super(model, producer, age);
        this.type = type;
        this.typeOfVehicle = "CAR";
    }

    @Override
    public boolean isMoving() {
        return false;
    }

    @Override
    public String toString() {
        return "CAR: " + super.toString() + "," + type;
    }

}
