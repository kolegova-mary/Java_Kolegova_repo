package com.griddynamics.test;

import com.griddynamics.main.Ticket;
import org.testng.annotations.Test;

import static com.griddynamics.main.Ticket.isMyTicketLucky;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

public class TicketTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNegativeValue() throws IllegalArgumentException {
        isMyTicketLucky("-1");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testShortValue() throws IllegalArgumentException{
       isMyTicketLucky("2882");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testZeroValue() throws IllegalArgumentException{
        isMyTicketLucky("0");
    }

    @Test
    public void testRepeatedValue()throws IllegalArgumentException {
        assertTrue(isMyTicketLucky("101101"));
    }

    @Test
    public void testSumValue() throws IllegalArgumentException{
        assertTrue(isMyTicketLucky("346922"));
    }

    @Test
    public void testSumValueWithZero() throws IllegalArgumentException{
        assertTrue(isMyTicketLucky("056902"));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testLongValue() throws IllegalArgumentException{
        isMyTicketLucky("78567856");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSpecialChars() throws IllegalArgumentException{
        isMyTicketLucky("0^&1@$");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testEmpty() throws IllegalArgumentException{
       isMyTicketLucky("");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSpace()throws IllegalArgumentException {
       isMyTicketLucky(" 029 0");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testExpression()throws IllegalArgumentException {
       isMyTicketLucky("12.3+1");
    }

    @Test
    public void testWrongValue()throws IllegalArgumentException {
        assertFalse(isMyTicketLucky("123456"));
    }
}
