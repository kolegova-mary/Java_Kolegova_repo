package testSerialDeserialTask;

import org.testng.annotations.Test;
import com.griddynamics.serialDeserialTask.SerialDeserial;
import com.griddynamics.serialDeserialTask.SerialDeserial.FileFormatException;

import java.io.IOException;
import java.net.URL;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.fail;

public class TestSerialDeserial {
    @Test
    public void testUsual() {
        String filePath = getFilePath("vehicle_data.txt");
        try {
            SerialDeserial.readFromMainFile(filePath);
        } catch (FileFormatException | IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test(expectedExceptions = FileFormatException.class)
    public void testInvalidValue() throws IOException, FileFormatException {
        SerialDeserial.readFromMainFile(getFilePath("invalid_value.txt"));
    }

    @Test(expectedExceptions = IOException.class)
    public void testNotExistFile() throws IOException, FileFormatException {
        SerialDeserial.readFromMainFile(getFilePath("not-existed.txt"));
    }

    @Test(expectedExceptions = FileFormatException.class)
    public void testMissedComma() throws IOException, FileFormatException {
        SerialDeserial.readFromMainFile(getFilePath("missed_comma.txt"));
    }

    @Test
    public void testRightLineMissedComma() throws IOException {
        try {
            SerialDeserial.readFromMainFile(getFilePath("missed_comma.txt"));
        } catch (FileFormatException e) {
            assertEquals(4, e.getLineNumber());
        }
    }

    @Test
    public void textEmpty() throws IOException, FileFormatException {
        SerialDeserial.readFromMainFile(getFilePath("empty.txt"));
    }

    @Test(expectedExceptions = FileFormatException.class)
    public void testExtraComma() throws IOException, FileFormatException {
        SerialDeserial.readFromMainFile(getFilePath("extra_comma.txt"));
    }

    private String getFilePath(String fileName) {
        URL url = getClass().getClassLoader().getResource(fileName);
        return url == null ? "" : url.getPath();
    }
}
