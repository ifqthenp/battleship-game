package battleship;

/**
 * Abstract class {@code Ship}.
 *
 * @author Andrei Bogomja
 */
public abstract class Ship
{
    /** The row (0 to 9) which contains the bow (front) of the ship. */
    private int bowRow;

    /** The column (0 to 9) which contains the bow (front) of the ship. */
    private int bowColumn;

    /** The number of squares occupied by the ship. */
    private final int length;

    /** Set to true if the ship occupies a single row, false otherwise. */
    private boolean horizontal;

    /**
     * An array of boolean values telling whether that part of the ship
     * has been hit (fired upon). Battleships use 4 locations; cruises
     * use 3; destroyers 2; submarines and empty sea 1. Here {@code hit[0]}
     * stands for the location at the bow of the ship.
     */
    private boolean[] hit;

    /**
     * Creates an object of {@code Ship} type.
     *
     * @param length the length of the ship
     */
    public Ship(final int length)
    {
        this.bowRow = 0;
        this.bowColumn = 0;
        this.horizontal = false;
        this.length = length;
        this.hit = new boolean[this.length];
    }

    /**
     * Gets the {@code bowRow} value.
     *
     * @return the row which contains the front of the ship
     */
    public int getBowRow()
    {
        return this.bowRow;
    }

    /**
     * Sets the value of this {@code bowRow}.
     *
     * @param row the value to be set for this {@code bowRow}
     */
    public void setBowRow(final int row)
    {
        this.bowRow = row;
    }

    /**
     * Gets the {@code bowColumn} value.
     *
     * @return the column which contains the front of the ship
     */
    public int getBowColumn()
    {
        return this.bowColumn;
    }

    /**
     * Sets the value for this {@code column}.
     *
     * @param column the value to be set for this {@code bowColumn}
     */
    public void setBowColumn(final int column)
    {
        this.bowColumn = column;
    }

    /**
     * Gets the position of the ship (horizontal or vertical).
     *
     * @return true if the ship occupies a single row, false otherwise.
     */
    public boolean isHorizontal()
    {
        return this.horizontal;
    }

    /**
     * Sets the value of {@code horizontal} instance variable.
     *
     * @param horizontal sets the value to true of false
     */
    public void setHorizontal(final boolean horizontal)
    {
        this.horizontal = horizontal;
    }

    /**
     * Gets the length of the ship.
     *
     * @return the number of squares occupied by the ship
     */
    public int getLength()
    {
        return this.length;
    }

    /**
     * Gets the type of the ship.
     *
     * @return the type of the ship
     */
    abstract String getShipType();

    /**
     * If a part of the ship occupies the given row and column, and the
     * ship has not been sunk, marks that part of the ship as hit (in the
     * hit array, index 0 indicates the bow) and returns true,
     * otherwise returns false.
     *
     * @param row    the row occupied by ship
     * @param column the column occupied by ship
     * @return true if ship has been hit at given row and column, false otherwise
     */
    public boolean shootAt(final int row, final int column)
    {
        int position = this.isHorizontal() ? (column % this.length) : (row % this.length);

        if (this.isRealShip() && !this.isSunk())
        {
            this.hit[position] = true;
        }
        return this.hit[position];
    }

    /**
     * Eases development of classes for real ships.
     *
     * @return true if the ship is real ship, false otherwise
     */
    public boolean isRealShip()
    {
        return true;
    }

    /**
     * Indicates whether every part of the ship has been hit.
     *
     * @return true if every part of the ship has been hit, false otherwise
     */
    public boolean isSunk()
    {
        for (boolean isHit : this.hit)
        {
            if (!isHit)
            {
                return false;
            }
        }
        return true;
    }
}
