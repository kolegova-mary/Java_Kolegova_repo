package com.griddynamics.testExceptions;

import com.griddynamics.exceptions.ExceptionsTask;
import com.griddynamics.exceptions.ExceptionsTask.*;


import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;

import static org.testng.Assert.assertEquals;


public class TestExceptions {

    @Test
    public void testUsual() throws FileFormatException, NoSuchFileException, EmptyFileException, NotTxtFileException, IOException {
        String filePath = getFilePath("vehicle_data_collections.txt");
            ExceptionsTask.read(filePath);
    }

    @Test(expectedExceptions = FileFormatException.class)
    public void testInvalidValue() throws FileFormatException, NoSuchFileException, EmptyFileException, NotTxtFileException, IOException {
        ExceptionsTask.read(getFilePath("invalid_value_collections.txt"));
    }

    @Test(expectedExceptions = NoSuchFileException.class)
    public void testNotExistFile() throws FileFormatException, NoSuchFileException, EmptyFileException, NotTxtFileException, IOException {
        ExceptionsTask.read(getFilePath("not-existed.txt"));
    }

    @Test(expectedExceptions = FileFormatException.class)
    public void testMissedComma() throws FileFormatException, NoSuchFileException, EmptyFileException, NotTxtFileException, IOException {
        ExceptionsTask.read(getFilePath("missed_comma_collections.txt"));
    }

    @Test
    public void testRightLineMissedComma() throws FileFormatException, NoSuchFileException, EmptyFileException, NotTxtFileException, IOException {
        try {
            ExceptionsTask.read(getFilePath("missed_comma_collections.txt"));
        } catch (FileFormatException e) {
            assertEquals(e.getLineNumber(), 2);
        }
    }

    @Test(expectedExceptions = EmptyFileException.class)
    public void testEmpty() throws FileFormatException, NoSuchFileException, EmptyFileException, NotTxtFileException, IOException {
        ExceptionsTask.read(getFilePath("empty_collections.txt"));
    }

    @Test(expectedExceptions = FileFormatException.class)
    public void testExtraComma() throws FileFormatException, NoSuchFileException, EmptyFileException, NotTxtFileException, IOException {
        ExceptionsTask.read(getFilePath("extra_comma_collections.txt"));
    }
    
    @Test(expectedExceptions = NotTxtFileException.class)
    public void testBinaryFile()throws FileFormatException, NoSuchFileException, EmptyFileException, NotTxtFileException, IOException{
        ExceptionsTask.read(getFilePath("not_txt.XML"));
    }

    private String getFilePath(String fileName) {
        URL url = getClass().getClassLoader().getResource(fileName);
        return url == null ? "" : url.getPath();
    }

}
