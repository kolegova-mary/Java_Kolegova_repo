package lucky.ticket.task;

import org.junit.Test;

import static lucky.ticket.task.Ticket.isMyTicketLucky;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TicketTest {

    @Test
    public void testNegativeValue() {
        boolean myTicketLucky = isMyTicketLucky(-1);
        assertFalse(myTicketLucky);
    }

    @Test
    public void testShortValue() {
        boolean myTicketLucky = isMyTicketLucky(2882);
        assertFalse(myTicketLucky);
    }

    @Test
    public void testZeroValue() {
        boolean myTicketLucky = isMyTicketLucky(0);
        assertFalse(myTicketLucky);
    }

    @Test
    public void testRepeatedValue() {
        boolean myTicketLucky = isMyTicketLucky(101101);
        assertTrue(myTicketLucky);
    }

    @Test
    public void testSumValue() {
        boolean myTicketLucky = isMyTicketLucky(346922);
        assertTrue(myTicketLucky);
    }

    @Test
    public void testLongValue() {
        boolean myTicketLucky = isMyTicketLucky(78567856);
        assertFalse(myTicketLucky);
    }

}
