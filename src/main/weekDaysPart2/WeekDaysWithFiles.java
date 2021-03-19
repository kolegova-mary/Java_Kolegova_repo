package weekDaysPart2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class WeekDaysWithFiles {
    public static void main(String[] args) {
        URL resource = WeekDaysWithFiles.class.getClassLoader().getResource("number_of_days.txt");
        if (resource == null) {
            System.out.println("Can't found input file.");
            return;
        }
        try {
            read(resource.getPath());
        } catch (FileFormatException | IOException e) {
            e.printStackTrace();
        }

    }

    public static void read(String inputFile) throws FileFormatException, IOException {
        Path inputFilePath = Paths.get(inputFile);
        List<String> lines = Files.readAllLines(inputFilePath);
        List<Integer> numbersOfDays = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String cur = line.replace(',', ' ');
            cur = cur.replaceAll("\\s+", "");
            if (cur.matches("\\d+")) {
                numbersOfDays.add(Integer.parseInt(cur));
            } else {
                throw new FileFormatException("Your values in file are invalid." + "Check them and try again.", i + 1);
            }
        }
        if (numbersOfDays.isEmpty()) {
            System.out.println("Empty file provided.");
            return;
        }
        try (Writer daysWriter = new FileWriter(inputFilePath.getParent().resolve("NamesOfDays").toFile())) {
            for (Integer numbersOfDay : numbersOfDays) {
                WeeksDays day = lookForDay(numbersOfDay);
                chooseNoticeForDay(day, daysWriter);
            }
        }
    }

    public static WeeksDays lookForDay(int x) {
        switch (x) {
            case 1:
                return WeeksDays.MONDAY;
            case 2:
                return WeeksDays.TUESDAY;
            case 3:
                return WeeksDays.WEDNESDAY;
            case 4:
                return WeeksDays.THURSDAY;
            case 5:
                return WeeksDays.FRIDAY;
            case 6:
                return WeeksDays.SATURDAY;
            case 7:
                return WeeksDays.SUNDAY;
            default:
                throw new IllegalArgumentException("Sorry, we don't have such number of day.");
        }
    }

    public static WeeksDays chooseNoticeForDay(WeeksDays x, Writer daysWriter) throws IOException {
        switch (x) {
            case MONDAY:
                daysWriter.write("Have a nice week! Today is Monday!\n");
                return WeeksDays.MONDAY;
            case TUESDAY:
                daysWriter.write("Have a good time on Tuesday!\n");
                return WeeksDays.TUESDAY;
            case WEDNESDAY:
                daysWriter.write("Have a nice time on Wednesday!\n");
                return WeeksDays.WEDNESDAY;
            case THURSDAY:
                daysWriter.write("Have fun on Thursday!\n");
                return WeeksDays.THURSDAY;
            case FRIDAY:
                daysWriter.write("Happy Friday!\n");
                return WeeksDays.FRIDAY;
            case SATURDAY:
                daysWriter.write("Have a rest on Saturday!\n");
                return WeeksDays.SATURDAY;
            case SUNDAY:
                daysWriter.write("Prepare for a new week! Today is Sunday!\n");
                return WeeksDays.SUNDAY;
            default:
                throw new IllegalArgumentException("Impossible");
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

    public enum WeeksDays {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY

    }
}
