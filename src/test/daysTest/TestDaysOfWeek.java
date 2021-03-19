package daysTest;

import org.testng.annotations.Test;

import static comgriddynamics.days.DaysOfWeek.*;
import static comgriddynamics.days.DaysOfWeek.chooseNoticeForDay;
import static org.testng.Assert.assertSame;


public class TestDaysOfWeek {

    @Test
    public void testMonday() {
        WeeksDays day = chooseNoticeForDay(lookForDay(1));
        assertSame(day, WeeksDays.MONDAY);
    }

    @Test
    public void testTuesday() {
        WeeksDays day = chooseNoticeForDay(lookForDay(2));
        assertSame(day, WeeksDays.TUESDAY);
    }

    @Test
    public void testWednesday() {
        WeeksDays day = chooseNoticeForDay(lookForDay(3));
        assertSame(day, WeeksDays.WEDNESDAY);
    }

    @Test
    public void testThursday() {
        WeeksDays day = chooseNoticeForDay(lookForDay(4));
        assertSame(day, WeeksDays.THURSDAY);
    }

    @Test
    public void testFriday() {
        WeeksDays day = chooseNoticeForDay(lookForDay(5));
        assertSame(day, WeeksDays.FRIDAY);
    }

    @Test
    public void testSaturday() {
        WeeksDays day = chooseNoticeForDay(lookForDay(6));
        assertSame(day, WeeksDays.SATURDAY);
    }

    @Test
    public void testSunday() {
        WeeksDays day = chooseNoticeForDay(lookForDay(7));
        assertSame(day, WeeksDays.SUNDAY);
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
