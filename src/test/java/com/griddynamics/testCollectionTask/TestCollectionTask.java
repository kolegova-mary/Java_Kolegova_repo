package com.griddynamics.testCollectionTask;

import com.griddynamics.collectionsTask.CollectionsTask.FileFormatException;
import com.griddynamics.models.Car;
import com.griddynamics.models.Truck;
import com.griddynamics.models.Vehicle;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.griddynamics.collectionsTask.CollectionsTask.*;
import static org.testng.Assert.fail;
import static org.testng.AssertJUnit.assertEquals;

public class TestCollectionTask {
    List<Car> cars = new ArrayList<>();
    List<Truck> trucks = new ArrayList<>();
    List<Vehicle> vehicles = new ArrayList<>();

    @BeforeMethod
    public void init() {
        cars.clear();
        trucks.clear();
        vehicles.clear();
    }

    @Test
    public void testUsual() throws FileFormatException, IOException {
        String filePath = getFilePath("vehicle_data_collections.txt");
        List<Vehicle> rightVehiclesOrder = new ArrayList<>(Arrays.asList(new Car("VAZ-2000", "VAZ", 50, "pupu"),
                new Car("VAZ-2101", "VAZ", 40, "sedan"),
                new Truck("KamAZ-3011", "AZ", 2, 900),
                new Truck("KamAZ-4211", "KamAZ", 5, 8000)));
        read(cars, trucks, filePath);
        merge(cars, trucks, vehicles);
        sortListOfVehicles(vehicles);
        if (rightVehiclesOrder.size() == vehicles.size()) {
            for (int i = 0, vehiclesSize = vehicles.size(); i < vehiclesSize; i++) {
                Vehicle vehicle = vehicles.get(i);
                assertEquals(rightVehiclesOrder.get(i), vehicle);
            }
        } else {
            fail();
        }
    }

    @Test(expectedExceptions = FileFormatException.class)
    public void testInvalidValue() throws IOException, FileFormatException {
        read(cars, trucks, getFilePath("invalid_value_collections.txt"));
    }

    @Test(expectedExceptions = IOException.class)
    public void testNotExistFile() throws IOException, FileFormatException {
        read(cars, trucks, getFilePath("not-existed_collections.txt"));
    }

    @Test(expectedExceptions = FileFormatException.class)
    public void testMissedComma() throws IOException, FileFormatException {
        read(cars, trucks, getFilePath("missed_comma_collections.txt"));
    }

    @Test
    public void testRightLineMissedComma() throws IOException {
        try {
            read(cars, trucks, getFilePath("missed_comma_collections.txt"));
        } catch (FileFormatException e) {
            assertEquals(2, e.getLineNumber());
        }
    }

    @Test
    public void testEmpty() throws IOException, FileFormatException {
        read(cars, trucks, getFilePath("empty_collections.txt"));
    }

    @Test(expectedExceptions = FileFormatException.class)
    public void testExtraComma() throws IOException, FileFormatException {
        read(cars, trucks, getFilePath("extra_comma_collections.txt"));
    }

    private String getFilePath(String fileName) {
        URL url = getClass().getClassLoader().getResource(fileName);
        return url == null ? "" : url.getPath();
    }
}
