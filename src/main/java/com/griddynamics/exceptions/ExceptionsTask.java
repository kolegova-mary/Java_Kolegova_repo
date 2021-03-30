package com.griddynamics.exceptions;

import com.griddynamics.models.Car;
import com.griddynamics.models.Truck;
import com.griddynamics.models.Vehicle;
import com.griddynamics.vehicleSolutionTask.VehicleSolution;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.lang.System.lineSeparator;

public class ExceptionsTask {

    public static void main(String[] args) throws NullPointerException {
        URL resource = VehicleSolution.class.getClassLoader().getResource("vehicle_data.txt");
        try {
            if (resource != null) {
                read(resource.getPath());
            }
        } catch (FileFormatException | EmptyFileException | NotTxtFileException | NoSuchFileException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void read(String inputFile) throws FileFormatException, NoSuchFileException,
            EmptyFileException, NotTxtFileException,
            IOException {
        if (inputFile.isEmpty()) {
            throw new NoSuchFileException("There is no such file.");
        }
        if (!inputFile.endsWith(".txt")) {
            throw new NotTxtFileException("Binary file was provided instead of text one");
        }
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
            throw new EmptyFileException("Empty file was provided.");
        }
        try (Writer carWriter = new FileWriter("CarOut");
             Writer truckWriter = new FileWriter("TruckOut")) {
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

    public static class NoSuchFileException extends Exception {
        public NoSuchFileException(String message) {
            super(message);
        }
    }

    public static class NotTxtFileException extends Exception {
        public NotTxtFileException(String message) {
            super(message);
        }
    }

    public static class EmptyFileException extends Exception {
        public EmptyFileException(String message) {
            super(message);
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