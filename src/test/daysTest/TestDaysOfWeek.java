package daysTest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static comgriddynamics.days.DaysOfWeek.*;
import static comgriddynamics.days.DaysOfWeek.chooseNoticeForDay;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertSame;


public class TestDaysOfWeek {
    @DataProvider(name = "testAllDays")
    public Object[][] createDays() {
        return new Object[][]{
                {WeeksDays.MONDAY, 1},
                {WeeksDays.TUESDAY, 2},
                {WeeksDays.WEDNESDAY, 3},
                {WeeksDays.THURSDAY, 4},
                {WeeksDays.FRIDAY, 5},
                {WeeksDays.SATURDAY, 6},
                {WeeksDays.SUNDAY, 7}
        };
    }

    @Test(dataProvider = "testAllDays")
    public void verifyData(WeeksDays day, Integer num) {
        WeeksDays curDay = chooseNoticeForDay(lookForDay(num));
        assertEquals(curDay, day);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testOutOfRange1() {
        chooseNoticeForDay(lookForDay(100));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testOutOfRange2() {
        chooseNoticeForDay(lookForDay(0));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testOutOfRange3() {
        chooseNoticeForDay(lookForDay(-1));
    }
}
