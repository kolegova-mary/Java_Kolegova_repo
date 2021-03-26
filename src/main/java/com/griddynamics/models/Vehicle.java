package com.griddynamics.models;

public class Vehicle implements MovingObject {
    private String model;
    private String producer;
    private int age;
    protected String typeOfVehicle;

    public Vehicle() {
    }

    public Vehicle(String model, String producer, int age) {
        this.model = model;
        this.producer = producer;
        this.age = age;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return model + "," + producer + "," + age;
    }

    @Override
    public boolean isMoving() {
        return false;
    }
}
