package com.griddynamics.testSerialDeserialTask;

import org.testng.annotations.Test;
import com.griddynamics.serialDeserialTask.SerialDeserial;
import com.griddynamics.serialDeserialTask.SerialDeserial.FileFormatException;

import java.io.IOException;
import java.net.URL;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.fail;

public class TestSerialDeserial {
    @Test
    public void testUsual() throws FileFormatException,IOException{
        String filePath = getFilePath("vehicle_data_serial_deserial.txt");
            SerialDeserial.readFromMainFile(filePath);
    }

    @Test(expectedExceptions = FileFormatException.class)
    public void testInvalidValue() throws IOException, FileFormatException {
        SerialDeserial.readFromMainFile(getFilePath("invalid_value_serial_deserial.txt"));
    }

    @Test(expectedExceptions = IOException.class)
    public void testNotExistFile() throws IOException, FileFormatException {
        SerialDeserial.readFromMainFile(getFilePath("not-existed.txt"));
    }

    @Test(expectedExceptions = FileFormatException.class)
    public void testMissedComma() throws IOException, FileFormatException {
        SerialDeserial.readFromMainFile(getFilePath("missed_comma_serial_deserial.txt"));
    }

    @Test
    public void testRightLineMissedComma() throws IOException {
        try {
            SerialDeserial.readFromMainFile(getFilePath("missed_comma_serial_deserial.txt"));
        } catch (FileFormatException e) {
            assertEquals(4, e.getLineNumber());
        }
    }

    @Test
    public void textEmpty() throws IOException, FileFormatException {
        SerialDeserial.readFromMainFile(getFilePath("empty_serial_deserial.txt"));
    }

    @Test(expectedExceptions = FileFormatException.class)
    public void testExtraComma() throws IOException, FileFormatException {
        SerialDeserial.readFromMainFile(getFilePath("extra_comma_serial_deserial.txt"));
    }

    private String getFilePath(String fileName) {
        URL url = getClass().getClassLoader().getResource(fileName);
        return url == null ? "" : url.getPath();
    }
}
