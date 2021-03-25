package com.griddynamics.models;

public class Truck extends Vehicle {
    public long tonnage;

    public long getTonnage() {
        return tonnage;
    }

    public void setTonnage(long tonnage) {
        this.tonnage = tonnage;
    }

    public Truck(String model, String producer, int age, long tonnage) {
        super(model, producer, age);
        this.tonnage = tonnage;
        this.typeOfVehicle = "TRUCK";
    }

    @Override
    public boolean isMoving() {
        return false;
    }

    @Override
    public String toString() {
        return "TRUCK: " + super.toString() + "," + tonnage;
    }
}
