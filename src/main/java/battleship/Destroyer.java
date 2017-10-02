package battleship;

/**
 * {@code Destroyer} class describes a ship of length 2.
 *
 * @author Andrei Bogomja
 */
public class Destroyer extends Ship
{
    /** This ship length, constant value. */
    private final static int DESTROYER_LENGTH = 2;

    /**
     * Constructs a {@code Destroyer} object. The length value
     * of destroyer always equals 2.
     */
    public Destroyer()
    {
        super(DESTROYER_LENGTH);
    }

    /**
     * Overrides abstract method {@link Ship#getShipType()} and
     * returns the description of this type of ship.
     *
     * @return "destroyer" string
     */
    @Override
    public String getShipType()
    {
        return "destroyer";
    }
}
