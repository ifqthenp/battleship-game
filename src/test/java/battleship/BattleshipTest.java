package battleship;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * {@code BattleshipTest} test class.
 */
public class BattleshipTest
{
    private final static int BATTLESHIP_LENGTH = 4;

    private Battleship battleship;

    @Before
    public void setUp() throws Exception
    {
        battleship = new Battleship();
    }

    @Test
    public void whenIsRealShipInvokedReturnTrue() throws Exception
    {
        assertTrue(battleship.isRealShip());
    }

    @Test
    public void battleshipGetShipTypeReturnsBattleship() throws Exception
    {
        assertThat(battleship.getShipType(), equalTo("battleship"));
    }

    @Test
    public void battleshipReturnsCorrectLength() throws Exception
    {
        assertTrue(battleship.getLength() == BATTLESHIP_LENGTH);
    }
}
