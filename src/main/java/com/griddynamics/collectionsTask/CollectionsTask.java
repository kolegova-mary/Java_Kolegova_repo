package com.griddynamics.collectionsTask;

import com.griddynamics.vehicleSolutionTask.VehicleSolution;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CollectionsTask {

    public static void main(String[] args) {
        URL resource = VehicleSolution.class.getClassLoader().getResource("vehicle_data_collections.txt");
        if (resource == null) {
            System.out.println("Can't find input file.");
            return;
        }
        List<Car> cars = new ArrayList<>();
        List<Truck> trucks = new ArrayList<>();
        List<Vehicle> vehicles = new ArrayList<>();
        Map<String, List<Vehicle>> producers = new HashMap<>();
        try {
            read(cars, trucks, resource.getPath());
        } catch (FileFormatException | IOException e) {
            e.printStackTrace();
        }
        merge(cars, trucks, vehicles);
        sortListOfVehicles(vehicles);
        System.out.println(vehicles);
        fillMap(vehicles, producers);
        System.out.println(producers);
    }

    public static void sortListOfVehicles(List<Vehicle> vehicles) {
        vehicles.sort(Vehicle::compareTo);
    }

    public static void fillMap(List<Vehicle> vehicles, Map<String, List<Vehicle>> producers) {
        for (Vehicle vehicle : vehicles) {
            if (!producers.containsKey(vehicle.producer)) {
                producers.put(vehicle.producer, new ArrayList<>());
            }
            producers.get(vehicle.producer).add(vehicle);
        }
    }

    public static void merge(List<Car> cars, List<Truck> trucks, List<Vehicle> vehicles) {
        vehicles.addAll(cars);
        vehicles.addAll(trucks);
    }

    public static void read(List<Car> cars, List<Truck> trucks, String inputFile) throws FileFormatException, IOException {
        Path inputFilePath = Paths.get(inputFile);
        List<String> lines = Files.readAllLines(inputFilePath);
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String cur = line.replaceAll("\\s+", "");
            cur = cur.replace(':', ',');
            String[] parameters = cur.split(",");
            if (parameters.length == 5) {
                if (parameters[0].equalsIgnoreCase("CAR")) {
                    cars.add(new Car(parameters[1], parameters[2],
                            Integer.parseInt(parameters[3]), parameters[4]));
                } else {
                    trucks.add(new Truck(parameters[1], parameters[2],
                            Integer.parseInt(parameters[3]), Long.parseLong(parameters[4])));
                }
            } else {
                throw new FileFormatException("Your values in file are invalid. " +
                        "Check them and try again.", i + 1);
            }
        }
        if ((cars != null && cars.isEmpty()) && (trucks != null && trucks.isEmpty())) {
            System.out.println("Empty file provided.");
        }
    }

    public static class FileFormatException extends Exception {
        private final int lineNumber;

        public FileFormatException(String message, int lineNumber) {
            super(message);
            this.lineNumber = lineNumber;
        }

        public int getLineNumber() {
            return lineNumber;
        }
    }

    public static class Vehicle {
        protected String model;
        protected String producer;
        protected int age;

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
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vehicle vehicle = (Vehicle) o;
            return age == vehicle.age && Objects.equals(model, vehicle.model) && Objects.equals(producer, vehicle.producer);
        }
    }

    public static class Car extends Vehicle {
        protected String type;

        public Car(String model, String producer, int age, String type) {
            super(model, producer, age);
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }


        @Override
        public String toString() {
            return "CAR: " + super.toString() + "," + type;
        }
    }

    public static class Truck extends Vehicle {
        long tonnage;

        public Truck(String model, String producer, int age, long tonnage) {
            super(model, producer, age);
            this.tonnage = tonnage;
        }

        public long getTonnage() {
            return tonnage;
        }

        public void setTonnage(long tonnage) {
            this.tonnage = tonnage;
        }

        @Override
        public String toString() {
            return "TRUCK: " + super.toString() + "," + tonnage;
        }
    }
}