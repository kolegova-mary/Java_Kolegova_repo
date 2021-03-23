package com.griddynamics.test;

import com.griddynamics.main.VehicleSolution;
import com.griddynamics.main.VehicleSolution.FileFormatException;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class TestVehicle {

    @Test
    public void testUsual() {
        String filePath = getFilePath("vehicle_data.txt");
        try {
            VehicleSolution.read(filePath);
        } catch (FileFormatException | IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test(expectedExceptions = FileFormatException.class)
    public void testInvalidValue() throws IOException, FileFormatException {
        VehicleSolution.read(getFilePath("invalid_value.txt"));
    }

    @Test(expectedExceptions = IOException.class)
    public void testNotExistFile() throws IOException, FileFormatException {
        VehicleSolution.read(getFilePath("not-existed.txt"));
    }

    @Test(expectedExceptions = FileFormatException.class)
    public void testMissedComma() throws IOException, FileFormatException {
        VehicleSolution.read(getFilePath("missed_comma.txt"));
    }

    @Test
    public void testRightLineMissedComma() throws IOException {
        try {
            VehicleSolution.read(getFilePath("missed_comma.txt"));
        } catch (FileFormatException e) {
            assertEquals(e.getLineNumber(), 2);
        }
    }

    @Test
    public void textEmpty() throws IOException, FileFormatException {
        //
        VehicleSolution.read(getFilePath("empty.txt"));
    }

    @Test(expectedExceptions = FileFormatException.class)
    public void testExtraComma() throws IOException, FileFormatException {
        VehicleSolution.read(getFilePath("extra_comma.txt"));
    }

    private String getFilePath(String fileName) {
        Integer a = 5;
        URL url = getClass().getClassLoader().getResource(fileName);
        return url == null ? "" : url.getPath();
    }

}
