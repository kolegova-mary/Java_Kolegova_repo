package com.griddynamics.testWeekDaysPart2;

import org.testng.annotations.Test;
import java.io.IOException;
import java.net.URL;
import com.griddynamics.weekDaysPart2.WeekDaysWithFiles.FileFormatException;
import static com.griddynamics.weekDaysPart2.WeekDaysWithFiles.read;


public class testWeekDaysPart2 {

    @Test
    public void testUsual() throws FileFormatException, IOException {
        String filePath = getFilePath("number_of_days.txt");
        read(filePath);
    }

    @Test()
    public void testEmptyFile() throws FileFormatException, IOException {
        read(getFilePath("empty.txt"));
    }

    @Test(expectedExceptions = IOException.class)
    public void testNotExistedFile() throws FileFormatException, IOException {
        read(getFilePath("not-existed.txt"));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testInOneRow() throws FileFormatException, IOException {
        read(getFilePath("oneRow.txt"));
    }

    @Test(expectedExceptions = FileFormatException.class)
    public void testInvalidFormat() throws FileFormatException, IOException {
        read(getFilePath("invalidFormat.txt"));
    }

    private String getFilePath(String fileName) {
        URL url = getClass().getClassLoader().getResource(fileName);
        return url == null ? "" : url.getPath();
    }

}
