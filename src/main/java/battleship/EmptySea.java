package battleship;

/**
 * {@code EmptySea} class describes a part of the ocean
 * that doesn't have a ship in it.
 *
 * @author Andrei Bogomja
 */
public class EmptySea extends Ship
{
    /** The length of the empty sea cell, constant value. */
    private static final int EMPTY_SEA_LENGTH = 1;

    /**
     * Constructs an {@code EmptySea} object. The length
     * value of empty sea always equals to 1.
     */
    public EmptySea()
    {
        super(EMPTY_SEA_LENGTH);
    }

    /**
     * Overrides method {@link Ship#shootAt(int, int)} and always
     * returns false to indicate that nothing was hit.
     *
     * @param row    the row occupied by empty sea
     * @param column the column occupied by empty sea
     * @return false
     */
    @Override
    public boolean shootAt(int row, int column)
    {
        return false;
    }

    /**
     * Overrides method {@link Ship#isSunk()} and always returns
     * false to indicate that no ship has been sunk.
     *
     * @return false
     */
    @Override
    public boolean isSunk()
    {
        return false;
    }

    /**
     * Overrides method {@link Ship#isRealShip()} and always
     * returns false as the empty sea is not the real ship.
     *
     * @return false
     */
    @Override
    public boolean isRealShip()
    {
        return false;
    }

    /**
     * Overrides abstract method {@link Ship#getShipType()} and
     * returns the description of this type of ship.
     *
     * @return "empty sea" string
     */
    @Override
    public String getShipType()
    {
        return "empty sea";
    }
}
