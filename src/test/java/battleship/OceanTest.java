package battleship;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * {@code OceanTest} test class.
 */
public class OceanTest
{
    private final static int OCEAN_LENGTH = 10;

    private Ocean ocean;

    @Before
    public void setUp() throws Exception
    {
        ocean = new Ocean();
    }

    @Test
    public void numberOfCellsOccupiedIsTwenty() throws Exception
    {
        ocean.placeAllShipsRandomly();
        int count = 0;
        for (int i = 0; i < OCEAN_LENGTH; i++)
        {
            for (int j = 0; j < OCEAN_LENGTH; j++)
            {
                if (ocean.isOccupied(i, j))
                {
                    count++;
                }
            }
        }
        assertEquals("Number of cells occupied must be 20", 20, count);
    }

    @Test
    public void whenNewOceanCreatedEachCellOfItIsEmptySea() throws Exception
    {
        int emptySeaCount = 0;
        for (int i = 0; i < OCEAN_LENGTH; i++)
        {
            for (int j = 0; j < OCEAN_LENGTH; j++)
            {
                if (ocean.getShipTypeAt(i, j).equals("empty sea"))
                {
                    emptySeaCount++;
                }
            }
        }
        assertEquals("Ocean must be empty sea", 100, emptySeaCount);
    }

    @Test
    public void whenNewOceanCreatedArrayOfShipsIsNotNull() throws Exception
    {
        assertNotNull(ocean.getShipArray());
    }
}
