package battleship;

/**
 * {@code Cruiser} class describes a ship of length 3.
 *
 * @author Andrei Bogomja
 */
public class Cruiser extends Ship
{
    /** This ship length, constant value. */
    private final static int CRUISER_LENGTH = 3;

    /**
     * Constructs a {@code Cruiser} object. The length value
     * of cruiser always equals 3.
     */
    public Cruiser()
    {
        super(CRUISER_LENGTH);
    }

    /**
     * Overrides abstract method {@link Ship#getShipType()} and
     * returns the description of this type of ship.
     *
     * @return "cruiser" string
     */
    @Override
    public String getShipType()
    {
        return "cruiser";
    }
}
