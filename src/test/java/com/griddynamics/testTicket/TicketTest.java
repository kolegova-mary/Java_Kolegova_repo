package com.griddynamics.testTicket;

import org.testng.annotations.Test;

import static com.griddynamics.ticketTask.Ticket.isMyTicketLucky;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

public class TicketTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNegativeValue() {
        isMyTicketLucky("-1");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testShortValue() {
        isMyTicketLucky("2882");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testZeroValue() {
        isMyTicketLucky("0");
    }

    @Test
    public void testRepeatedValue() {
        assertTrue(isMyTicketLucky("101101"));
    }

    @Test
    public void testSumValue() {
        assertTrue(isMyTicketLucky("346922"));
    }

    @Test
    public void testSumValueWithZero() {
        assertTrue(isMyTicketLucky("056902"));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testLongValue() {
        isMyTicketLucky("78567856");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSpecialChars() {
        isMyTicketLucky("0^&1@$");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testEmpty() {
        isMyTicketLucky("");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSpace() {
        isMyTicketLucky(" 029 0");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testExpression() {
        isMyTicketLucky("12.3+1");
    }

    @Test
    public void testWrongValue() {
        assertFalse(isMyTicketLucky("123456"));
    }
}
