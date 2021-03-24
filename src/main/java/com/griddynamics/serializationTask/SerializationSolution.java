package com.griddynamics.serializationTask;

import com.google.gson.Gson;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SerializationSolution {
    public static void main(String[] args){
        URL resource = SerializationSolution.class.getClassLoader().getResource("vehicle_dataSerializationTask.txt");
        List<Vehicle> vehicles = null;
        if (resource == null) {
            System.out.println("Can't find input file.");
            return;
        }
        try {
            vehicles = readFromMainFile(resource.getPath());
        } catch (FileFormatException | IOException e) {
            e.printStackTrace();
        }
        if (!vehicles.isEmpty()) {
            writeIntoFiles(vehicles, resource);
        try {
            readFromCreatedFiles(vehicles.size(), resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

    public static void readFromCreatedFiles(int size, URL resource) throws IOException {
        for (int i = 0; i < 2; i++) {
            int fileNum = (int) (Math.random() * size) + 1;
            List<String> lines = Files.readAllLines(Paths.get(resource.getPath()).getParent().resolve("object"
                    + (fileNum) + ".json"));
            Gson gson = new Gson();
            Vehicle notJsonStr = gson.fromJson(lines.get(0), Vehicle.class);
            System.out.println(notJsonStr);
        }
    }

    public static void writeIntoFiles(List<Vehicle> vehicles, URL resource) {
        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle object = vehicles.get(i);
            Gson gson = new Gson();
            String jsonStr = gson.toJson(object);
            try (Writer vehiclesWriter = new FileWriter(Paths.get(resource.getPath()).getParent().resolve("object" + (i + 1)
                    + ".json").toFile())) {
                vehiclesWriter.write(jsonStr);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Vehicle> readFromMainFile(String inputFile) throws FileFormatException, IOException {
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
        }
        return vehicles;
    }


    public interface MovingObject {
        boolean isMoving();
    }

    public static class Vehicle implements MovingObject {
        private String model;
        private String producer;
        private int age;

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

    public static class Car extends Vehicle {
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

    protected static class Truck extends Vehicle {
        private long tonnage;

        public long getTonnage() {
            return tonnage;
        }

        public void setTonnage(long tonnage) {
            this.tonnage = tonnage;
        }

        public Truck(String model, String producer, int age, long tonnage) {
            super(model, producer, age);
            this.tonnage = tonnage;
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
}
