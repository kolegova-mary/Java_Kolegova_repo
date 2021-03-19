package testWeekDaysPart2;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import weekDaysPart2.WeekDaysWithFiles;

import java.io.IOException;
import java.net.URL;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.fail;
import static weekDaysPart2.WeekDaysWithFiles.*;

public class testWeekDaysPart2 {

    @Test
    public void testUsual() {
        String filePath = getFilePath("number_of_days.txt");
        try {
            WeekDaysWithFiles.read(filePath);
        } catch (FileFormatException | IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test()
    public void testEmptyFile()throws FileFormatException,IOException {
        WeekDaysWithFiles.read(getFilePath("empty.txt"));
    }

    @Test(expectedExceptions = IOException.class)
    public void testNotExistedFile() throws FileFormatException,IOException{
        WeekDaysWithFiles.read(getFilePath("not-existed.txt"));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testInOneRow()throws FileFormatException,IOException{
        WeekDaysWithFiles.read(getFilePath("oneRow.txt"));
    }

    @Test(expectedExceptions = FileFormatException.class)
    public void testInvalidFormat()throws FileFormatException,IOException{
        WeekDaysWithFiles.read(getFilePath("invalidFormat.txt"));
    }

    private String getFilePath(String fileName) {
        URL url = getClass().getClassLoader().getResource(fileName);
        return url == null ? "" : url.getPath();
    }

}
