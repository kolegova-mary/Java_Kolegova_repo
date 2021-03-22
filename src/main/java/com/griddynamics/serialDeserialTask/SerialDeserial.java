package com.griddynamics.serialDeserialTask;

import com.google.gson.Gson;

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

public class SerialDeserial {
    public static void main(String[] args) {
        URL resource = SerialDeserial.class.getClassLoader().getResource("vehicle_data.txt");
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
            writeIntoOneFile(vehicles, resource);
            try {
                readFromCreatedFile(vehicles.size(), resource);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void readFromCreatedFile(int size, URL resource) throws IOException {
        for (int i = 0; i < size; i++) {
            List<String> lines = Files.readAllLines(Paths.get(resource.getPath()).getParent().resolve("new_format_vehicles.json"));
            Gson gson = new Gson();
            String cur = lines.get(i);
            if (cur.contains("tonnage")) {
                Truck notJsonTruck = gson.fromJson(cur, Truck.class);
                System.out.println(notJsonTruck);
            } else {
                Car notJsonCar = gson.fromJson(cur, Car.class);
                System.out.println(notJsonCar);
            }
        }
    }

    public static void writeIntoOneFile(List<Vehicle> vehicles, URL resource) {
        try (Writer vehiclesWriter = new FileWriter(Paths.get(resource.getPath()).getParent().resolve("new_format_vehicles.json").toFile())) {
            for (Vehicle vehicle : vehicles) {
                Gson gson = new Gson();
                String jsonStr = gson.toJson(vehicle);
                vehiclesWriter.write(jsonStr + lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
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
