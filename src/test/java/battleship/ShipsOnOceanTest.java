package battleship;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * {@code ShipsOnOceanTest} class.
 */
public class ShipsOnOceanTest
{
    private final static int OCEAN_LENGTH = 10;

    private Ocean ocean;
    private Ship[][] shipsArray;

    @Before
    public void setUp() throws Exception
    {
        ocean = new Ocean();
        shipsArray = ocean.getShipArray();
        putShip(0, 0, 4, false, new Battleship());
        putShip(4, 2, 3, true, new Cruiser());
        putShip(2, 9, 3, false, new Cruiser());
        putShip(0, 3, 2, true, new Destroyer());
        putShip(0, 6, 2, false, new Destroyer());
        putShip(6, 0, 2, true, new Destroyer());
        putShip(2, 4, 1, false, new Submarine());
        putShip(4, 7, 1, true, new Submarine());
        putShip(9, 9, 1, false, new Submarine());
        putShip(9, 0, 1, true, new Submarine());
    }

    @Test
    public void printTest() throws Exception
    {
        ocean.shootAt(0, 0); // vertical battleship is not sunk
        ocean.shootAt(1, 0); // vertical battleship is not sunk
        ocean.shootAt(2, 0); // vertical battleship is not sunk
        ocean.shootAt(0, 0); // vertical battleship is not sunk

        ocean.shootAt(9, 0); // submarine is sunk
        ocean.shootAt(7, 0); // empty sea
        ocean.print();
    }

    @Test
    public void isGameOverReturnsFalseIfNotAllShipsAreSunk() throws Exception
    {
        ocean.shootAt(0, 0); // vertical battleship
        ocean.shootAt(1, 0); // vertical battleship
        ocean.shootAt(2, 0); // vertical battleship
        ocean.shootAt(3, 0); // vertical battleship
        ocean.shootAt(4, 2); // horizontal cruiser
        ocean.shootAt(4, 3); // horizontal cruiser
        ocean.shootAt(4, 4); // horizontal cruiser
        assertFalse(ocean.isGameOver());
    }

    @Test
    public void isGameOverReturnsTrueIfAllShipsAreSunk() throws Exception
    {
        for (int i = 0; i < OCEAN_LENGTH; i++)
        {
            for (int j = 0; j < OCEAN_LENGTH; j++)
            {
                ocean.shootAt(i, j);
            }
        }
        System.out.println(ocean.getShipsSunk());
        assertTrue(ocean.isGameOver());
    }

    @Test
    public void ifShootAtEachCellNumberOfShotsFiredIsOneHundred() throws Exception
    {
        int shotsFired = 0;
        int hits = 0;
        for (int i = 0; i < OCEAN_LENGTH; i++)
        {
            for (int j = 0; j < OCEAN_LENGTH; j++)
            {
                if (ocean.shootAt(i, j))
                {
                    hits++;
                }
                shotsFired++;
            }
        }
        assertEquals("Number of hits must be 20", ocean.getHitCount(), hits);
        assertEquals("Number of shots fired must be 100", ocean.getShotsFired(), shotsFired);
    }

    @Test
    public void ifShootAtEachCellNumberOfShipHitsIsTwenty() throws Exception
    {
        int hits = 0;
        for (int i = 0; i < OCEAN_LENGTH; i++)
        {
            for (int j = 0; j < OCEAN_LENGTH; j++)
            {
                if (ocean.shootAt(i, j))
                {
                    hits++;
                }
            }
        }
        assertEquals("Number of hits must be 20", ocean.getHitCount(), hits);
    }

    @Test
    public void ifShootAtOceanLocationTwiceAndShipIsNotSunkReturnsTrue() throws Exception
    {
        ocean.shootAt(0, 0); // vertical battleship
        ocean.shootAt(1, 0); // vertical battleship
        ocean.shootAt(3, 0); // vertical battleship
        assertFalse(ocean.hasSunkShipAt(2, 0));
        assertTrue(ocean.shootAt(3, 0));
    }

    @Test
    public void ifShootAtOceanLocationTwiceAndShipIsSunkReturnsFalse() throws Exception
    {
        ocean.shootAt(0, 0); // vertical battleship
        ocean.shootAt(1, 0); // vertical battleship
        ocean.shootAt(2, 0); // vertical battleship
        ocean.shootAt(3, 0); // vertical battleship
        assertTrue(ocean.hasSunkShipAt(3, 0));
        assertFalse(ocean.shootAt(3, 0));
    }

    @Test
    public void hasSunkShipAtReturnsFalseIfSomeCellsHaveNotBeenHit() throws Exception
    {
        ocean.shootAt(0, 0); // vertical battleship
        ocean.shootAt(1, 0); // vertical battleship
        ocean.shootAt(3, 0); // vertical battleship
        assertFalse(ocean.hasSunkShipAt(2, 0));
    }

    @Test
    public void hasSunkShipAtReturnsTrueIfHitAnyCellOfSunkenShip() throws Exception
    {
        ocean.shootAt(0, 0); // vertical battleship
        ocean.shootAt(1, 0); // vertical battleship
        ocean.shootAt(2, 0); // vertical battleship
        ocean.shootAt(3, 0); // vertical battleship
        assertTrue(ocean.hasSunkShipAt(2, 0));
    }

    @Test
    public void ifShootAtOceanLocationAndMissedReturnFalse() throws Exception
    {
        assertFalse(ocean.shootAt(0, 9)); // empty sea
        assertFalse(ocean.shootAt(5, 5)); // empty sea
        assertFalse(ocean.shootAt(4, 8)); // empty sea
    }

    @Test
    public void ifShootAtOceanLocationAndShipIsHitReturnTrue() throws Exception
    {
        assertTrue(ocean.shootAt(0, 0)); // vertical battleship
        assertTrue(ocean.shootAt(1, 0)); // vertical battleship
        assertTrue(ocean.shootAt(2, 0)); // vertical battleship
        assertTrue(ocean.shootAt(3, 0)); // vertical battleship
        assertTrue(ocean.shootAt(4, 2)); // horizontal cruiser
        assertTrue(ocean.shootAt(4, 3)); // horizontal cruiser
        assertTrue(ocean.shootAt(4, 4)); // horizontal cruiser
    }

    @Test
    public void emptySeaIsNotHorizontal() throws Exception
    {
        assertTrue(!shipsArray[0][9].isHorizontal());
    }

    @Test
    public void ifShipIsNotHorizontalReturnTrue() throws Exception
    {
        assertTrue(!shipsArray[0][0].isHorizontal());
        assertTrue(!shipsArray[1][0].isHorizontal());
        assertTrue(!shipsArray[2][0].isHorizontal());
        assertTrue(!shipsArray[3][0].isHorizontal());
    }

    @Test
    public void ifShipIsHorizontalReturnTrue() throws Exception
    {
        assertTrue(shipsArray[0][3].isHorizontal());
        assertTrue(shipsArray[0][4].isHorizontal());
    }

    private void putShip(int row, int column, int length, boolean isHorizontal, Ship shipType)
    {
        shipType.setHorizontal(isHorizontal);
        for (int i = 0; i < length; i++)
        {
            if (shipType.isHorizontal())
            {
                shipsArray[row][column++] = shipType;
            }
            else
            {
                shipsArray[row++][column] = shipType;
            }
        }
    }

    private void print()
    {
        System.out.println("ACTUAL PLACEMENT OF SHIPS");
        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        for (int i = 0; i < shipsArray.length; i++)
        {
            System.out.print(i);
            for (int j = 0; j < shipsArray.length; j++)
            {
                if (shipsArray[i][j].getShipType().equals("battleship"))
                {
                    System.out.print(" B");
                }
                else if (shipsArray[i][j].getShipType().equals("cruiser"))
                {
                    System.out.print(" C");
                }
                else if (shipsArray[i][j].getShipType().equals("destroyer"))
                {
                    System.out.print(" D");
                }
                else if (shipsArray[i][j].getShipType().equals("submarine"))
                {
                    System.out.print(" S");
                }
                else
                {
                    System.out.print(" .");
                }
            }
            System.out.println();
        }
    }
}
