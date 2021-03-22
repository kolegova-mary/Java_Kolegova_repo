package com.griddynamics.testSerializationTask;

import com.griddynamics.serializationTask.SerializationSolution;
import org.testng.annotations.Test;
import com.griddynamics.serializationTask.SerializationSolution.FileFormatException;

import java.io.IOException;
import java.net.URL;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.fail;

public class TestSerializationSolution {

    @Test
    public void testUsual() throws FileFormatException, IOException {
        String filePath = getFilePath("vehicle_dataSerializationTask.txt");
        SerializationSolution.readFromMainFile(filePath);
    }

    @Test(expectedExceptions = FileFormatException.class)
    public void testInvalidValue() throws IOException, FileFormatException {
        SerializationSolution.readFromMainFile(getFilePath("invalid_valueSerializationTask.txt"));
    }

    @Test(expectedExceptions = IOException.class)
    public void testNotExistFile() throws IOException, FileFormatException {
        SerializationSolution.readFromMainFile(getFilePath("not-existed.txt"));
    }

    @Test(expectedExceptions = FileFormatException.class)
    public void testMissedComma() throws IOException, FileFormatException {
        SerializationSolution.readFromMainFile(getFilePath("missed_commaSerializationTask.txt"));
    }

    @Test
    public void testRightLineMissedComma() throws IOException {
        try {
            SerializationSolution.readFromMainFile(getFilePath("missed_commaSerializationTask.txt"));
        } catch (FileFormatException e) {
            assertEquals(e.getLineNumber(), 2);
        }
    }

    @Test
    public void textEmpty() throws IOException, FileFormatException {
        SerializationSolution.readFromMainFile(getFilePath("emptySerializationTask.txt"));
    }

    @Test(expectedExceptions = FileFormatException.class)
    public void testExtraComma() throws IOException, FileFormatException {
        SerializationSolution.readFromMainFile(getFilePath("extra_commaSerializationTask.txt"));
    }

    private String getFilePath(String fileName) {
        URL url = getClass().getClassLoader().getResource(fileName);
        return url == null ? "" : url.getPath();
    }

}
