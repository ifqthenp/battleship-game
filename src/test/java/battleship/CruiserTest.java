package battleship;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * {@code CruiserTest} test class.
 */
public class CruiserTest
{
    private final static int CRUISER_LENGTH = 3;

    private Cruiser cruiser;

    @Before
    public void setUp() throws Exception
    {
        cruiser = new Cruiser();
    }

    @Test
    public void cruiserGetShipTypeReturnsCruiser() throws Exception
    {
        assertTrue(cruiser.getShipType().equals("cruiser"));
    }

    @Test
    public void cruiserReturnsCorrectLength() throws Exception
    {
        assertTrue(cruiser.getLength() == CRUISER_LENGTH);
    }
}
