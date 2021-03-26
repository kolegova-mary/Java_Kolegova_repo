package com.griddynamics.serialDeserialTask;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.griddynamics.models.Car;
import com.griddynamics.models.Truck;
import com.griddynamics.models.Vehicle;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SerialDeserial {
    public static void main(String[] args) {
        URL resource = SerialDeserial.class.getClassLoader().getResource("vehicle_data_serial_deserial.txt");
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
        if (vehicles != null && !vehicles.isEmpty()) {
            writeIntoOneFile(vehicles, resource);
            try {
                readFromCreatedFile(resource);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class VehicleDeserializer implements JsonDeserializer<Vehicle> {
        private String vehicleTypeElementName;
        private Gson gson;
        private Map<String, Class<? extends Vehicle>> vehicleTypeRegistry;

        public VehicleDeserializer(String vehicleTypeElementName) {
            this.vehicleTypeElementName = vehicleTypeElementName;
            this.gson = new Gson();
            this.vehicleTypeRegistry = new HashMap<>();
        }

        public void registerGarageType(String vehicleTypeName, Class<? extends Vehicle> vehicleType) {
            vehicleTypeRegistry.put(vehicleTypeName, vehicleType);
        }

        public Vehicle deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
            JsonObject vehicleObject = json.getAsJsonObject();
            JsonElement vehicleTypeElement = vehicleObject.get(vehicleTypeElementName);

            Class<? extends Vehicle> vehicleType = vehicleTypeRegistry.get(vehicleTypeElement.getAsString());
            return gson.fromJson(vehicleObject, vehicleType);
        }
    }

    public static void readFromCreatedFile(URL resource) throws IOException {
        String line = Files.readAllLines(Paths.get(resource.getPath()).getParent().resolve("new_format_vehicles.json")).get(0);
        VehicleDeserializer deserializer = new VehicleDeserializer("typeOfVehicle");
        deserializer.registerGarageType("CAR", Car.class);
        deserializer.registerGarageType("TRUCK", Truck.class);
        Gson gson = new GsonBuilder().registerTypeAdapter(Vehicle.class, deserializer).create();
        List<Vehicle> outList = gson.fromJson(line, new TypeToken<List<Vehicle>>() {
        }.getType());
        for (Vehicle cur : outList) {
            System.out.println(cur);
        }
    }

    public static void writeIntoOneFile(List<Vehicle> vehicles, URL resource) {
        try (Writer vehiclesWriter = new FileWriter("new_format_vehicles.json")) {
            Gson gson = new Gson();
            String jsonStr = gson.toJson(vehicles);
            vehiclesWriter.write(jsonStr);
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
