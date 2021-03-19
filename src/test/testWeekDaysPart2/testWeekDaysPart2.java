package testWeekDaysPart2;

import org.testng.annotations.DataProvider;
import weekDaysPart2.WeekDaysWithFiles;

import static weekDaysPart2.WeekDaysWithFiles.*;

public class testWeekDaysPart2 {
    @DataProvider(name = "test1")
    public Object[][] createDaysOfWeek() {
        return new Object[][] {
                {WeeksDays.MONDAY, new Integer(1) },
                { WeeksDays.TUESDAY, new Integer(2)},
        };
    }

}
