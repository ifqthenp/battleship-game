package battleship;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * {@code DestroyerTest} test class.
 */
public class DestroyerTest
{
    private final static int DESTROYER_LENGTH = 2;

    private Destroyer destroyer;

    @Before
    public void setUp() throws Exception
    {
        destroyer = new Destroyer();
    }

    @Test
    public void destroyerGetShipTypeReturnsDestroyer() throws Exception
    {
        assertTrue(destroyer.getShipType().equals("destroyer"));
    }

    @Test
    public void destroyerReturnsCorrectLength() throws Exception
    {
        assertThat(destroyer.getLength() == DESTROYER_LENGTH, is(true));
    }
}
