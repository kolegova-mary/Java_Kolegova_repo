package com.griddynamics.vehicleSolutionTask;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.lineSeparator;

public class VehicleSolution {
    public static void main(String[] args) {
        URL resource = VehicleSolution.class.getClassLoader().getResource("vehicle_data.txt");
        if (resource == null) {
            System.out.println("Can't find input file.");
            return;
        }
        try {
            read(resource.getPath());
        } catch (FileFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void read(String inputFile) throws FileFormatException, IOException {
        Path inputFilePath = Paths.get(inputFile);
        List<String> lines = Files.readAllLines(inputFilePath);
        List<Vehicle> vehicles = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String cur = line.replaceAll("\\s+", "");
            cur = cur.replace(':', ',');
            String[] parameters = cur.split(",");
            if (parameters.length == 5) {
                if (parameters[0].equalsIgnoreCase("CAR")) {
                    vehicles.add(new Car(parameters[1], parameters[2],
                            Integer.parseInt(parameters[3]), parameters[4]));
                } else {
                    vehicles.add(new Truck(parameters[1], parameters[2],
                            Integer.parseInt(parameters[3]), Long.parseLong(parameters[4])));
                }
            } else {
                throw new FileFormatException("Your values in file are invalid. " +
                        "Check them and try again.", i + 1);
            }
        }
        if (vehicles.isEmpty()) {
            System.out.println("Empty file provided.");
            return;
        }
        try (Writer carWriter = new FileWriter(inputFilePath.getParent().resolve("CarOut").toFile());
             Writer truckWriter = new FileWriter(inputFilePath.getParent().resolve("TruckOut").toFile())) {
            for (Vehicle vehicle : vehicles) {
                String s = vehicle.toString();
                if (vehicle instanceof Car) {
                    carWriter.write(s + lineSeparator());
                } else {
                    truckWriter.write(s + lineSeparator());
                }
            }
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

        @Override
        public String toString() {
            return model + "," + producer + "," + age;
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
