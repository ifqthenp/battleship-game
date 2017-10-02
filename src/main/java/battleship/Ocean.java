package battleship;

import java.util.Arrays;
import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * {@code Ocean} class.
 *
 * @author Andrei Bogomja
 */
public class Ocean
{
    /** Total number of ships placed on the ocean, constant value. */
    private final static int SHIPS_TOTAL = 10;

    /** The length of the ocean, constant value. */
    private final static int OCEAN_LENGTH = 10;

    /** The exact lenght of the string builder needed to build ocean string in {@code print()} method */
    private final static int STRING_BUILDER_CAPACITY = 252;

    /** Minimum number of ships that should be placed on the ocean based on the ship type */
    private static final int MINIMUM_NUMBER_OF_SHIPS = 1;

    /** Maximum number of ships that should be placed on the ocean based on the ship type */
    private static final int MAXIMUM_NUMBER_OF_SHIPS = 4;

    /** 10 x 10 matrix to determine which ship is in any given location. */
    private Ship[][] ships;

    /** 10 x 10 boolean matrix that keeps a record of which location has been hit. */
    private boolean[][] isHit;

    /** The total number of shots fired by the user. */
    private int shotsFired;

    /** The number of times a shot hit a ship. */
    private int hitCount;

    /** The number of ships sunk. */
    private int shipsSunk;

    /**
     * Constructs an empty {@code Ocean} object by filling
     * the {@code ships} array with {@code EmptySea}s.
     */
    public Ocean()
    {
        this.shotsFired = 0;
        this.hitCount = 0;
        this.shipsSunk = 0;
        this.isHit = new boolean[OCEAN_LENGTH][OCEAN_LENGTH];
        this.ships = new Ship[OCEAN_LENGTH][OCEAN_LENGTH];

        Ship emptySea = new EmptySea();
        for (Ship[] row : this.ships)
        {
            Arrays.fill(row, emptySea);
        }
    }

    /**
     * Places all ten ships randomly on the initially empty ocean. This method
     * calls {@code placeAllShips(int numberOfShips)} in the nested for loop
     * placing ships by the numbers in ascending order, i.e. large ships go first.
     */
    public void placeAllShipsRandomly()
    {
        for (int i = MINIMUM_NUMBER_OF_SHIPS; i <= MAXIMUM_NUMBER_OF_SHIPS; i++)
        {
            for (int j = MINIMUM_NUMBER_OF_SHIPS; j <= i; j++)
            {
                this.placeAllShips(i);
            }
        }
    }

    /**
     * Helper private method that chooses which type of ship to be placed on
     * the ocean. The choice is based on the number of ships of particular
     * type that supposed to be placed on the ocean. Battleship will be placed
     * in case of 1, Cruiser in case of 2, Destroyer in case of 3, and
     * Submarine in case of 4. This method is called from within
     * {@code placeAllShipsRandomly()} method.
     *
     * @param numberOfShips defines the number of ships of particular type
     */
    private void placeAllShips(final int numberOfShips)
    {
        switch (numberOfShips)
        {
            case 1:
                this.setCoordinatesFor(new Battleship());
                break;
            case 2:
                this.setCoordinatesFor(new Cruiser());
                break;
            case 3:
                this.setCoordinatesFor(new Destroyer());
                break;
            case 4:
                this.setCoordinatesFor(new Submarine());
                break;
        }
    }

    /**
     * Private helper method that sets coordinates for selected type of ship
     * in {@code placeAllShips(int numberOfShips)} method. This method utilizes
     * do/while loop to randomly set ship bow row and bow column coordinates,
     * and to choose vertical or horizontal position for the given type of ship.
     * <p>
     * The method will attempt to choose valid coordinates for the ship until
     * conditions for the method {@code placeIsNotFoundFor(Ship ship)} in the
     * do/while loop are satisfied. After ship coordinates have been defined,
     * the method {@code placeShipOnOcean(Ship ship)} will be called to place
     * current type of ship on the ocean.
     *
     * @param ship the current type of ship to set coordinates for
     */
    private void setCoordinatesFor(final Ship ship)
    {
        do
        {
            ship.setHorizontal(new Random().nextBoolean());
            ship.setBowRow(new Random().nextInt(OCEAN_LENGTH));
            ship.setBowColumn(new Random().nextInt(OCEAN_LENGTH));
        } while (placeIsNotFoundFor(ship));

        this.placeShipOnOcean(ship);
    }

    /**
     * Private helper method to place a ship with valid coordinates on the
     * ocean. This method is called whithin {@code setCoordinates(Ship ship)}
     * method, when coordinates for ship are checked and validated.
     *
     * @param ship the current type of ship to be placed on the ocean
     */
    private void placeShipOnOcean(final Ship ship)
    {
        int actualRow = ship.getBowRow();
        int actualColumn = ship.getBowColumn();
        boolean shipIsHorizontal = ship.isHorizontal();

        for (int i = 0; i < ship.getLength(); i++)
        {
            if (shipIsHorizontal)
            {
                actualColumn++;
            }
            else
            {
                actualRow++;
            }
            this.ships[actualRow][actualColumn] = ship;
        }
    }

    /**
     * Private helper method to validate coordinates for the ship. This method
     * is used as a condition for do/while loop in {@code setCoordinatesFor(Ship
     * ship)} method.
     * <p>
     * First, it checks whether ship coordinates are out of ocean boundary by
     * calling {@code isOutOfBoundary(Ship ship)} method. If the coordinates
     * do not fall within a range of ocean boundary, {@code isOutOfBoundary}
     * method validates to true and do/while loop runs again with new
     * coordinates and position for the ship.
     * <p>
     * Second, if coordinates fall within a range of ocean boundary, the method
     * checks whether the area around the ship as well as the actual coordinates
     * for the new ship are not occupied by any other ship.
     *
     * @param ship the ship with coordinates to be checked and validated
     * @return false if coordinates for the ship are valid
     */
    public boolean placeIsNotFoundFor(final Ship ship)
    {
        if (this.isOutOfOceanBoundary(ship))
        {
            return true;
        }

        boolean isHorizontal = ship.isHorizontal();
        int shipLen = ship.getLength();
        int bowRow = ship.getBowRow();
        int bowCol = ship.getBowColumn();

        int upperLeft = max(0, ship.getBowRow() - 1);
        int lowerLeft = max(0, ship.getBowColumn() - 1);
        int upperRight = min(OCEAN_LENGTH - 1, bowRow + 1 + (isHorizontal ? 0 : shipLen));
        int lowerRight = min(OCEAN_LENGTH - 1, bowCol + 1 + (isHorizontal ? shipLen : 0));

        for (int x = upperLeft; x <= upperRight; x++)
        {
            for (int y = lowerLeft; y <= lowerRight; y++)
            {
                if (this.isOccupied(x, y))
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Private helper method to check whether ship coordinates fall within a
     * range of ocean boundary. This method is to be called from {@code
     * placeIsNotFoundFor(Ship ship)} as the first condition before validating
     * coordinates for the ship and the area around the ship.
     *
     * @param ship the ship to check for its coordinates to be in range of ocean
     * @return true if ship coordinates are out of ocean boundary
     */
    private boolean isOutOfOceanBoundary(final Ship ship)
    {
        int shipLen = ship.getLength();
        int bowRow = ship.getBowRow();
        int bowCol = ship.getBowColumn();

        boolean horizontalIsOutOfBound = bowCol < 0 || OCEAN_LENGTH <= (bowCol + shipLen);
        boolean verticalIsOutOfBound = bowRow < 0 || OCEAN_LENGTH <= (bowRow + shipLen);

        return verticalIsOutOfBound || horizontalIsOutOfBound;
    }

    /**
     * Returns true if the given location contains a ship, false otherwise.
     *
     * @param row    the number of row to test for ship location
     * @param column the number of column to test for ship location
     * @return true if the given location contains a ship, false otherwise
     */
    public boolean isOccupied(final int row, final int column)
    {
        return this.ships[row][column].isRealShip();
    }

    /**
     * Returns true if the given location contains a <em>sunk</em> ship,
     * false if it does not.
     *
     * @param row    the number of row the ship has been sunk at
     * @param column the number of column the ship has been sunk at
     * @return true if the given location contains a ship, false if it does not
     */
    public boolean hasSunkShipAt(final int row, final int column)
    {
        return this.ships[row][column].isSunk();
    }

    /**
     * Returns the ship type at the given location.
     *
     * @param row    the row number to get the ship type
     * @param column the column number to get the ship type
     * @return the ship type at the given location
     */
    public String getShipTypeAt(final int row, final int column)
    {
        return this.ships[row][column].getShipType();
    }

    /**
     * Shoots at the part of the ship at that location. Returns true
     * if the given location contains a <em>real</em> ship (not an
     * {@code EmptySea}), still afloat, false if it does not.
     * In addition, this method updates the number of shots that have
     * been fired, the number of hits, and the number of ships sunk.
     * <p>
     * If a location contains a {@code real} ship, {@code shootAt}
     * returns true every time the user shoots at that same location.
     * Once a ship has been <em>sunk</em>, additional shots at its
     * location return false.
     *
     * @param row    the row number to shoot at
     * @param column the column number to shoot at
     * @return true if the given location contains a real ship, still afloat
     */
    public boolean shootAt(final int row, final int column)
    {
        this.shotsFired++;
        this.isHit[row][column] = true;
        if (this.isOccupied(row, column) && !this.hasSunkShipAt(row, column))
        {
            this.hitCount++;
            this.ships[row][column].shootAt(row, column);

            if (this.hasSunkShipAt(row, column))
            {
                this.shipsSunk++;
            }
            return true;
        }
        return false;
    }

    /**
     * Gets the number of shots fired.
     *
     * @return the number of shots fired
     */
    public int getShotsFired()
    {
        return this.shotsFired;
    }

    /**
     * Gets the number of hits recorded. All hits are counted,
     * not just the first time a given square is hit (see also
     * the documentation for the {@link #shootAt(int, int)} method).
     *
     * @return the number of all hits recorded
     */
    public int getHitCount()
    {
        return this.hitCount;
    }

    /**
     * Gets the number of ships sunk.
     *
     * @return the total number of sunken ships
     */
    public int getShipsSunk()
    {
        return this.shipsSunk;
    }

    /**
     * Returns true if all ships have been sunk, false otherwise.
     *
     * @return true if all ships have been sunk, false otherwise
     */
    public boolean isGameOver()
    {
        return this.shipsSunk == SHIPS_TOTAL;
    }

    /**
     * Returns the 10x10 array of ships. To be used only for unit testing.
     *
     * @return the 10x10 array of ships
     */
    public Ship[][] getShipArray()
    {
        return this.ships;
    }

    /**
     * Prints the ocean on stdout. To aid the user, row numbers are
     * displayed along the left edge of the array, and column numbers
     * are displayed along the top. Numbers displayed are 0 to 9.
     * <p>
     * {@code S} indicates a location that have been fired upon and hit
     * a (real) ship, {@code -} indicates a location that have been fired
     * upon and found nothing there, {@code x} indicates a location
     * containing a sunken ship, and {@code .} indicates a location that
     * have been never fired upon.
     */
    public void print()
    {
        StringBuilder ocean = new StringBuilder(STRING_BUILDER_CAPACITY);
        ocean.append("  0 1 2 3 4 5 6 7 8 9\n");
        for (int row = 0; row < OCEAN_LENGTH; row++)
        {
            ocean.append(row).append(" ");
            for (int column = 0; column < OCEAN_LENGTH; column++)
            {
                boolean isOccupied = this.isOccupied(row, column);
                boolean cellIsHit = this.isHit[row][column];
                boolean isSunk = this.hasSunkShipAt(row, column);

                ocean = cellIsHit && isSunk ? ocean.append("x ")
                        : cellIsHit && isOccupied ? ocean.append("S ")
                        : cellIsHit && !isOccupied ? ocean.append("- ")
                        : ocean.append(". ");
            }
            ocean.append("\n");
        }
        System.out.print(ocean.toString());
    }
}
