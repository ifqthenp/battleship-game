package battleship;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * {@code SubmarineTest} test class.
 */
public class SubmarineTest
{
    private final static int SUBMARINE_LENGTH = 1;

    private Submarine submarine;

    @Before
    public void setUp() throws Exception
    {
        submarine = new Submarine();
    }

    @Test
    public void submarineGetShipTypeReturnsSubmarine() throws Exception
    {
        String actual = submarine.getShipType();
        String expected = "submarine";
        assertEquals(expected, actual);
    }

    @Test
    public void submarineGetLengthReturnsCorrectLength() throws Exception
    {
        assertTrue(submarine.getLength() == SUBMARINE_LENGTH);
    }
}
