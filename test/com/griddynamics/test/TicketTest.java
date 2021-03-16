package com.griddynamics.test;

import com.griddynamics.main.Ticket.Answer;
import org.testng.annotations.Test;

import static com.griddynamics.main.Ticket.isMyTicketLucky;
import static org.testng.Assert.assertSame;

public class TicketTest {

    @Test
    public void testNegativeValue() {
        Answer myTicketLucky = isMyTicketLucky("-1");
        assertSame(myTicketLucky, Answer.INVALID_TICKET);
    }

    @Test
    public void testShortValue() {
        Answer myTicketLucky = isMyTicketLucky("2882");
        assertSame(myTicketLucky, Answer.INVALID_TICKET);
    }

    @Test
    public void testZeroValue() {
        Answer myTicketLucky = isMyTicketLucky("0");
        assertSame(myTicketLucky, Answer.INVALID_TICKET);
    }

    @Test
    public void testRepeatedValue() {
        Answer myTicketLucky = isMyTicketLucky("101101");
        assertSame(myTicketLucky, Answer.LUCKY);
    }

    @Test
    public void testSumValue() {
        Answer myTicketLucky = isMyTicketLucky("346922");
        assertSame(myTicketLucky, Answer.LUCKY);
    }

    @Test
    public void testSumValueWithZero() {
        Answer myTicketLucky = isMyTicketLucky("056902");
        assertSame(myTicketLucky, Answer.LUCKY);
    }

    @Test
    public void testLongValue() {
        Answer myTicketLucky = isMyTicketLucky("78567856");
        assertSame(myTicketLucky, Answer.INVALID_TICKET);
    }

    @Test
    public void testSpecialChars() {
        Answer myTicketLucky = isMyTicketLucky("0^&1@$");
        assertSame(myTicketLucky, Answer.INVALID_TICKET);
    }

    @Test
    public void testEmpty() {
        Answer myTicketLucky = isMyTicketLucky("");
        assertSame(myTicketLucky, Answer.INVALID_TICKET);
    }

    @Test
    public void testSpace() {
        Answer myTicketLucky = isMyTicketLucky(" 029 0");
        assertSame(myTicketLucky, Answer.INVALID_TICKET);
    }

    @Test
    public void testExpression() {
        Answer myTicketLucky = isMyTicketLucky("12.3+1");
        assertSame(myTicketLucky, Answer.INVALID_TICKET);
    }

    @Test
    public void testWrongValue() {
        Answer myTicketLucky = isMyTicketLucky("123456");
        assertSame(myTicketLucky, Answer.NOT_LUCKY);
    }
}
