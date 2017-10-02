package battleship;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@code EmptySeaTest} test class.
 */
public class EmptySeaTest
{
    private final static int EMPTY_SEA_LENGTH = 1;

    private EmptySea emptySea;

    @Before
    public void setUp() throws Exception
    {
        emptySea = new EmptySea();
    }

    @Test
    public void emptySeaShootAtIsAlwaysFalse() throws Exception
    {
        assertFalse(emptySea.shootAt(0, 0));
        assertFalse(emptySea.shootAt(5, 5));
        assertFalse(emptySea.shootAt(9, 9));
    }

    @Test
    public void emptySeaIsNotRealShip() throws Exception
    {
        assertFalse(emptySea.isRealShip());
    }

    @Test
    public void emptySeaIsNotSunkShip() throws Exception
    {
        assertFalse(emptySea.isSunk());
    }

    @Test
    public void emptySeaReturnsCorrectShipType() throws Exception
    {
        assertTrue(emptySea.getShipType().equals("empty sea"));
    }

    @Test
    public void whenEmptySeaCreatedReturnsLengthOne() throws Exception
    {
        assertThat(emptySea.getLength(), is(EMPTY_SEA_LENGTH));
    }
}
