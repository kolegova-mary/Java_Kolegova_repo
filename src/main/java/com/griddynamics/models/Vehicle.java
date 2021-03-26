package com.griddynamics.models;

import java.util.Objects;

public class Vehicle implements MovingObject {
    public String model;
    public String producer;
    public int age;
    public String typeOfVehicle;


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

    public int compareTo(Vehicle vehicle) {
        return toString().compareTo(vehicle.toString());
    }

    @Override
    public String toString() {
        return model + "," + producer + "," + age;
    }

    @Override
    public boolean isMoving() {
        return false;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return age == vehicle.age && Objects.equals(model, vehicle.model) && Objects.equals(producer, vehicle.producer);

    }
}

